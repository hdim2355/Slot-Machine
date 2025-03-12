import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class CasinoFrame extends JFrame {
    private SouthPanel panel;
    private EastPanel eastPanel;
    private WestPanel westPanel;
    private FreeSpinPanel freeSpinPanel;
    private CardLayout layout;
    private JPanel CenterPanel;
    private DoublePanel doublePanel;
    private ConfirmPanel confirmpanel;

    private AudioInputStream audiobackground;
    private Clip clipbackground, clip;
    private boolean isSoundOn = true;

    private int gambleBalance = 0;
    private int gambleRounds = 0;


    private ImgMatrix model;
    private ImgView view;
    private int bet;
    private boolean isnotSpinning = true;
    private boolean isWinSoundOn = false;
    private int rounds = 0;
    private int balance;
    private int win = 0;
    private boolean freespins = false;
    private int bonus;
    private boolean bigWin = false;

    private int[][] winMatrix;
    private data[] lines;
    private ArrayList<Integer> winList;


    public CasinoFrame(int bets, int balances) {
        try {
            audiobackground = AudioSystem.getAudioInputStream(new File("sound/magicChristmas.wav"));
            clipbackground = AudioSystem.getClip();
            clipbackground.open(audiobackground);
            clipbackground.loop(Clip.LOOP_CONTINUOUSLY);
            clipbackground.start();

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sound/WinSound.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            System.out.println("Hiba");
        }
        bonus = 0;
        bet = bets;
        balance = balances;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //setBounds(0, 0, 1300, 800);
        setBounds(0, 0, 1294, 794);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        this.setBackground(Color.black);

        layout = new CardLayout();
        CenterPanel = new JPanel();
        CenterPanel.setLayout(layout);
        add(CenterPanel, BorderLayout.CENTER);

        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(e -> Save());
        menu.add(saveItem);
        bar.add(menu);
        setJMenuBar(bar);


        panel = new SouthPanel(bet);
        eastPanel = new EastPanel();
        westPanel = new WestPanel();
        add(panel, BorderLayout.SOUTH);
        add(westPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);

        panel.getBalanceLabel().setText("Balance:" + balance);
        updatelabels();

        westPanel.getQuarter().addActionListener(e -> {
            if (isnotSpinning) {
                bet = Integer.parseInt(westPanel.getQuarterlabel().getText());
                updatelabels();
                updateFreespinBuylabels();
            }
        });

        westPanel.getHalf().addActionListener(e -> {
            if (isnotSpinning) {
                bet = Integer.parseInt(westPanel.getHalflabel().getText());
                updatelabels();
                updateFreespinBuylabels();
            }
        });

        eastPanel.getDoble().addActionListener(e -> {
            if (isnotSpinning) {
                if (Integer.parseInt(eastPanel.getDoblelabel().getText()) <= balance) {
                    bet = Integer.parseInt(eastPanel.getDoblelabel().getText());
                    updatelabels();
                    updateFreespinBuylabels();
                }
            }
        });

        eastPanel.getQuadra().addActionListener(e -> {
            if (isnotSpinning) {
                if (Integer.parseInt(eastPanel.getQuadralabel().getText()) <= balance) {
                    bet = Integer.parseInt(eastPanel.getQuadralabel().getText());
                    updatelabels();
                    updateFreespinBuylabels();
                }
            }
        });

        panel.getLowerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isnotSpinning) {
                    if (bet - 10 >= 0 && bet >= 0) {
                        bet -= 10;
                        updatelabels();
                        updateFreespinBuylabels();
                    }
                }
            }
        });

        panel.getUpperButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isnotSpinning) {
                    if (bet + 10 <= balance) {
                        bet += 10;
                        updatelabels();
                        updateFreespinBuylabels();
                    }
                }
            }
        });

        model = new ImgMatrix();

        view = new ImgView(model);

        freeSpinPanel = new FreeSpinPanel();
        doublePanel = new DoublePanel();
        confirmpanel = new ConfirmPanel();

        CenterPanel.add(view, "P1");
        CenterPanel.add(freeSpinPanel, "P2");
        CenterPanel.add(doublePanel, "P3");
        CenterPanel.add(confirmpanel, "P4");

        eastPanel.getButton().addActionListener(e -> freeSpinBuy());
        westPanel.getButton().addActionListener(e -> makedouble());

        panel.getSpinButton().addActionListener(e -> spinner());
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    spinner();
                }
            }
        });


        confirmpanel.getYesButton().addActionListener(e -> {
            if (balance - gambleBalance >= 0) {
                balance -= gambleBalance;
                rounds = gambleRounds;
                freespins = true;
                layout.show(CenterPanel, "P1");
                bonus = 0;
            }
        });
        confirmpanel.getNoButton().addActionListener(e -> layout.show(CenterPanel, "P1"));


        doublePanel.getRedButton().addActionListener(e -> {
            new Thread(() -> {
                doublePanel.setWin(win);
                doublePanel.redCheck(panel, layout, CenterPanel);
                win = doublePanel.getWin();
            }).start();
        });

        doublePanel.getBlackButton().addActionListener(e -> {
            new Thread(() -> {
                doublePanel.setWin(win);
                doublePanel.blackCheck(panel, layout, CenterPanel);
                win = doublePanel.getWin();
            }).start();
        });

        doublePanel.getStopButton().addActionListener(e -> {
            balance += win;
            panel.getBalanceLabel().setText("Balance:" + balance);
            layout.show(CenterPanel, "P1");
            win = 0;
            panel.getWinLabel().setText("Win:" + win);
        });

        westPanel.getSound().addActionListener(e -> {
            if (isSoundOn) {
                isSoundOn = false;
                westPanel.setSoundOff();
                clipbackground.stop();
            } else {
                isSoundOn = true;
                westPanel.setSoundOn();
                clipbackground.loop(Clip.LOOP_CONTINUOUSLY);
                clipbackground.start();
            }
        });

        freeSpinPanel.getButtonSc3().addActionListener(e -> {
            gambleBalance = bet * 10;
            gambleRounds = 10;
            confirmpanel.getLabel().setText("Are you sure,that you want to buy 10  free spins for " + (bet * 10));
            layout.show(CenterPanel, "P4");
        });

        freeSpinPanel.getButtonSc4().addActionListener(e -> {
            gambleBalance = bet * 20;
            gambleRounds = 15;
            confirmpanel.getLabel().setText("Are you sure,that you want to buy 15 free spins for " + (bet * 20));
            layout.show(CenterPanel, "P4");
        });

        freeSpinPanel.getButtonSc5().addActionListener(e -> {
            gambleBalance = bet * 40;
            gambleRounds = 20;
            confirmpanel.getLabel().setText("Are you sure,that you want to buy 20 free spins for " + (bet * 40));
            layout.show(CenterPanel, "P4");
        });

        freeSpinPanel.getButtonRandSc().addActionListener(e -> {
            gambleBalance = bet * 18;
            gambleRounds = 10;
            confirmpanel.getLabel().setText("Are you sure,that you want to gamble 10/15/20 free spins for " + (bet * 18));
            layout.show(CenterPanel, "P4");
        });

        setVisible(true);
    }

    public void Save() {
        IntMatrix intMatrix = model.getRealValue();
        JFileChooser fileChooser = new JFileChooser(new File("C:/INFO/Java/Kaszino/Casino/Casino"));
        int r = fileChooser.showOpenDialog(this);
        if (r == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter write = new BufferedWriter(new FileWriter(file))) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 5; j++) {
                        write.write(String.valueOf(intMatrix.getValue(i, j)));
                        write.newLine();
                    }
                }
            } catch (Exception e) {
                System.out.println("Hiba");
            }
        }
    }

    private void updatelabels() {
        panel.getLabel().setText("Bet: " + bet);
        westPanel.getQuarterlabel().setText(Integer.toString(bet / 4));
        westPanel.getHalflabel().setText(Integer.toString(bet / 2));
        eastPanel.getDoblelabel().setText(Integer.toString(bet * 2));
        eastPanel.getQuadralabel().setText(Integer.toString(bet * 4));
    }

    private void makedouble() {
        if (isnotSpinning) {
            if (win > 0) {
                layout.show(CenterPanel, "P3");
            }
        }
    }

    private void freeSpinBuy() {
        if (isnotSpinning && win == 0) {
            layout.show(CenterPanel, "P2");
            updateFreespinBuylabels();
        }
    }

    private void updateFreespinBuylabels() {
        freeSpinPanel.getLabelSc3().setText("10 Free spins for:" + (bet * 10));
        freeSpinPanel.getLabelSc4().setText("15 Free spins for:" + (bet * 20));
        freeSpinPanel.getLabelSc5().setText("20 Free spins for:" + (bet * 40));
        freeSpinPanel.getLabelRandSc().setText("10/15/20 Free spins for:" + (bet * 18));
    }

    private void ThreadsSpinner() {
        List<Thread> threads = new ArrayList<>();

        for (int j = 0; j < 5; j++) {
            Thread thread = new Thread(new ImgController(model.getColumn(j), view));
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (Exception exception) {
                System.out.println("Szal hiba");
            }
        }
    }

    private void spinner() {
        new Thread(() -> {
            if (bigWin) {
                view.getFire().setBigwin(false);
                bigWin = false;
            }
            if (isnotSpinning) {
                if (isSoundOn) {
                    if (isWinSoundOn) {
                        clip.stop();
                        clipbackground.loop(Clip.LOOP_CONTINUOUSLY);
                        clipbackground.start();
                    }
                }
                if (freespins) {
                    freeSpinspinner();
                } else {
                    layout.show(CenterPanel, "P1");
                    if (win > 0) {
                        balance += win;
                        win = 0;
                        panel.getWinLabel().setText("Win:" + win);
                        panel.getBalanceLabel().setText("Balance:" + balance);
                    } else {
                        if (balance - bet >= 0) {
                            view.setDidWewon(false);
                            bonus = 0;
                            eastPanel.getBonuslabel().setText(Integer.toString(bonus));
                            isnotSpinning = false;
                            balance -= bet;
                            panel.getBalanceLabel().setText("Balance:" + balance);
                            panel.getSpinLabel().setText("Good luck!");

                            ThreadsSpinner();

                            IntMatrix intMatrix = model.getRealValue();
                            bonus += intMatrix.checkBonusGifts(1);
                            eastPanel.getBonuslabel().setText(Integer.toString(bonus));
                            win = ((bonus == 0) ? win + (intMatrix.checkLines(bet)) : win + (intMatrix.checkLines(bet) * bonus));

                            intMatrix.checkLines(bet);
                            winList = intMatrix.getLista();
                            view.setWinList(winList);


                            panel.getWinLabel().setText("Win:" + win);


                            if (intMatrix.checkFreespins() > 2) {
                                rounds += ((intMatrix.checkFreespins()) - 1) * 5;
                                panel.getSpinLabel().setText("Congratulation you won " + ((intMatrix.checkFreespins() - 1) * 5) + " free spins");
                                win += (bet * intMatrix.checkFreespins());
                                freespins = true;
                            }

                            if (win > 0) {
                                view.setDidWewon(true);
                                view.repaint();
                                if (win > bet * 10000) {
                                    bigWin = true;
                                    view.method();
                                }
                                if (isSoundOn) {
                                    try {
                                        clipbackground.stop();
                                        isWinSoundOn = true;
                                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                                        clip.start();
                                    } catch (Exception exception) {
                                        System.out.println("Hiba zene");
                                    }
                                }
                            }

                        }
                    }
                }
                isnotSpinning = true;
            }
        }).start();
    }

    private void freeSpinspinner() {
        isnotSpinning = false;
        //bonus = 0;
        eastPanel.getBonuslabel().setText(Integer.toString(bonus));
        while (rounds > 0) {
            int current_win = win;
            if (isSoundOn) {
                if (isWinSoundOn) {
                    clip.stop();
                    clipbackground.loop(Clip.LOOP_CONTINUOUSLY);
                    clipbackground.start();
                }
            }
            view.setDidWewon(false);
            panel.getBalanceLabel().setText("Balance:" + balance);
            panel.getSpinLabel().setText("Free spins left:" + (rounds - 1));

            ThreadsSpinner();

            IntMatrix intMatrix = model.getRealValue();
            bonus += intMatrix.checkBonusGifts(1);
            eastPanel.getBonuslabel().setText(Integer.toString(bonus));
            win += ((bonus == 0) ? (intMatrix.checkLines(bet)) : (intMatrix.checkLines(bet) * bonus));


            winList = intMatrix.getLista();
            view.setWinList(winList);

            panel.getWinLabel().setText("Win:" + win);
            if (intMatrix.checkFreespins() > 2) {
                rounds += ((intMatrix.checkFreespins()) - 1) * 3;
                win += (bet * intMatrix.checkFreespins());
                panel.getSpinLabel().setText("Congratulation you won " + ((intMatrix.checkFreespins() - 1) * 3) + " free spins");
            }

            if (win > current_win) {
                view.setDidWewon(true);
                view.repaint();
                if (isSoundOn) {
                    try {
                        clipbackground.stop();
                        isWinSoundOn = true;
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                        clip.start();
                    } catch (Exception exception) {
                        System.out.println("Hiba zene");
                    }
                }
                if (win > bet * 10000) {
                    bigWin = true;
                    rounds--;
                    isnotSpinning = true;
                    freespins = true;
                    try {
                        sleep(1000);
                    } catch (Exception e) {
                        System.out.println("Hiba");
                    }
                    view.repaint();
                    view.method();
                    return;
                }
            }
            try {
                sleep(2000);
            } catch (Exception e) {
                System.out.println("Hiba");
            }
            rounds--;
        }
        freespins = false;
        isnotSpinning = true;
    }

}
