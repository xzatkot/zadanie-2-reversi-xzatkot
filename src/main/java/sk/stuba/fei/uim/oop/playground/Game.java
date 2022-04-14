package sk.stuba.fei.uim.oop.playground;

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
    private JButton restartButton;
    private int size = 6;
    public Game() {
        this.setTitle("Reverzi game");
        this.initializeGame();
    }

    public Game(int size) {
        this.size = size;

        this.initializeGame();
    }

    private void initializeGame() {
        this.setTitle("Reverzi game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(this.size*68+21, this.size*68+96);
        this.setResizable(true);
        this.setFocusable(true);
        this.addKeyListener(this);

        this.sizeLabel.setText("Size: "+this.size+"x"+this.size);

        this.setLayout(new BorderLayout());
        GameLogic logic = new GameLogic(this, this.size);
        this.addKeyListener(logic);

        JPanel sideMenu = new JPanel();
        sideMenu.setBackground(Color.LIGHT_GRAY);
        restartButton = new JButton("Restart");
        restartButton.addActionListener(this);
        restartButton.setFocusable(false);

        sideMenu.setLayout(new GridLayout(1, 3));
        sideMenu.add(restartButton);
        sideMenu.add(sizeLabel);
        sideMenu.add(playerLabel);
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

    public void setWinner(int winner) {
        if (winner == 1) {
            this.playerLabel.setText("Winner: Player");
        }
        else if (winner == 2){
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
            new Game(12);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == restartButton) {
            this.dispose();
            new Game(12);
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
