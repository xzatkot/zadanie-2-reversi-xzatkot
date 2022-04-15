package sk.stuba.fei.uim.oop.game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
import sk.stuba.fei.uim.oop.controls.GameLogic;
import sk.stuba.fei.uim.oop.controls.UniversalAdapter;

public class Game extends UniversalAdapter {
    private JRadioButton size6, size8, size10, size12;
    private final JLabel sizeLabel = new JLabel("Size: 6x6");
    private final JLabel playerLabel = new JLabel("Player: White");
    private int playerLives = 2;
    private int computerLives = 2;
    private final JLabel playerStones = new JLabel("Player: " + playerLives);
    private final JLabel computerStones = new JLabel("PC: " + computerLives);
    private JButton restartButton;
    private int size = 6;
    private GameLogic logic;
    public Game() {
        this.setTitle("Reversi game");
        this.initializeGame();
    }

    public Game(int size) {
        this.size = size;

        this.initializeGame();
    }

    private void initializeGame() {
        this.setTitle("Reversi game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(this.size*68+31, this.size*68+106);
        this.setResizable(true);
        this.setFocusable(true);
        this.addKeyListener(this);

        this.sizeLabel.setText("Size: "+this.size+"x"+this.size);

        this.setLayout(new BorderLayout());
        this.logic = new GameLogic(this, this.size);
        this.addKeyListener(this.logic);

        JPanel sideMenu = new JPanel();
        sideMenu.setBackground(Color.LIGHT_GRAY);
        restartButton = new JButton("Restart");
        restartButton.addActionListener(this);
        restartButton.setFocusable(false);

        sideMenu.setLayout(new GridLayout(1, 6));
        sideMenu.add(restartButton);
        sideMenu.add(sizeLabel);
        sideMenu.add(playerLabel);
        sideMenu.add(playerStones);
        sideMenu.add(computerStones);
        this.add(sideMenu, BorderLayout.NORTH);

        JPanel sizeSelection = new JPanel();
        sizeSelection.setLayout(new GridLayout(1, 5));
        this.size6 = new JRadioButton("6x6");
        size8 = new JRadioButton("8x8");
        size10 = new JRadioButton("10x10");
        size12 = new JRadioButton("12x12");
        JButton sizeConfirm = new JButton("Change size");
        sizeConfirm.addActionListener(this);
        sizeConfirm.setFocusable(false);

        ButtonGroup sizeGroup = new ButtonGroup();
        sizeGroup.add(size6);
        sizeGroup.add(size8);
        sizeGroup.add(size10);
        sizeGroup.add(size12);
        sizeSelection.add(size6);
        sizeSelection.add(size8);
        sizeSelection.add(size10);
        sizeSelection.add(size12);
        sizeSelection.add(sizeConfirm);
        this.add(sizeSelection, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public void stealRocks() {
        this.playerLives = logic.getLives()[0];
        this.computerLives = logic.getLives()[1];
        this.playerStones.setText("Player: " + this.playerLives);
        this.computerStones.setText("PC: " + this.computerLives);
    }

    public void setWinner() {
        if (this.playerLives > this.computerLives) {
            this.playerLabel.setText("Winner: Player");
        }
        else if (this.playerLives < this.computerLives){
            this.playerLabel.setText("Winner: PC");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
            this.dispose();
            System.exit(0);
        }
        if (e.getKeyChar() == KeyEvent.VK_R) {
            this.dispose();
            new Game();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == restartButton) {
            this.dispose();
            new Game();
        }
        if(this.size6.isSelected()){
            this.dispose();
            new Game(6);
        }
        if(this.size8.isSelected()){
            this.dispose();
            new Game(8);
        }
        if(this.size10.isSelected()){
            this.dispose();
            new Game(10);
        }
        if(this.size12.isSelected()){
            this.dispose();
            new Game(12);
        }
    }
}
