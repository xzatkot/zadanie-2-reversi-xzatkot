package sk.stuba.fei.uim.oop.controls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.playground.Game;

public class GameLogic extends UniversalAdapter{
    @Getter
    private int counter;
    @Getter
    private JLabel label;
    @Setter
    private boolean playerClicked;

    private final String path = "src/main/java/sk/stuba/fei/uim/oop/controls/images";
    private final ImageIcon empty = new ImageIcon(this.path+"/empty_spot.png");
    private final ImageIcon white = new ImageIcon(this.path+"/white_spot.png");
    private final ImageIcon black = new ImageIcon(this.path+"/black_spot.png");
    private final ImageIcon viable = new ImageIcon(this.path+"/viable_spot.png");
    private final ImageIcon focused = new ImageIcon(this.path+"/focused_spot.png");

//    public Playground playground;
    public JPanel board;
    public int size;
    public char[][] playground;

    public GameLogic(Game game, int size) {
        this.size = size;
        board = new JPanel();
        game.add(board, BorderLayout.CENTER);

        playground = new char[this.size][this.size];

        for (int i=0;i<this.size;i++) {
            for (int j=0;j< this.size;j++) {
                JLabel spot;
                if ((i==this.size/2-1 && j==this.size/2-1)||(i==this.size/2 && j==this.size/2)) {
                    playground[i][j] = 'b';
                    spot = new JLabel(black);
                    spot.setBounds(0, 0, board.getWidth()/this.size, board.getHeight()/this.size);
                }
                else if ((i==this.size/2 && j==this.size/2-1)||(i==this.size/2-1 && j==this.size/2)) {
                    spot = new JLabel(white);
                    playground[i][j] = 'w';
                    spot.setBounds(0, 0, board.getWidth()/this.size, board.getHeight()/this.size);
                }
                else {
                    playground[i][j] = ' ';
                    spot = new JLabel(empty);
                    spot.setBounds(0, 0, board.getWidth()/this.size, board.getHeight()/this.size);
                }
                board.addMouseListener(this);
                board.add(spot);
            }
        }
        board.setLayout(new GridLayout(this.size, this.size));

        int player = 1;
//        while(this.getWinner() == 0) {
//            int[] options = this.findPlayersStones(player);
//        }
    }

    public int[] getDimensions(Game game) {
        int[] dimensions = new int[2];
        dimensions[0] = game.getX();
        dimensions[1] = game.getY();
        return dimensions;
    }

//    private int[][] findPlayersStones(int player) {
//        char[][] options = new char[this.size][this.size];
//        for (int i=0;i<this.size)
//    }

    private String[] checkDirections(int x, int y, int player) {
        String[] a = new String[1];
        a[0] = "a";
        return a;
    }

    public int getWinner() {
        return 0;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        e.getComponent();
    }


}
