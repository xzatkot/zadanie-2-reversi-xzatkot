package sk.stuba.fei.uim.oop.controls;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import sk.stuba.fei.uim.oop.game.Game;
import sk.stuba.fei.uim.oop.controls.steal.*;
import sk.stuba.fei.uim.oop.controls.check.checkBoard;

public class GameLogic extends UniversalAdapter{

    private final String path = "src/main/java/sk/stuba/fei/uim/oop/controls/images";
    private final ImageIcon empty = new ImageIcon(this.path+"/empty_spot.png", "empty");
    private final ImageIcon white = new ImageIcon(this.path+"/white_spot.png", "white");
    private final ImageIcon black = new ImageIcon(this.path+"/black_spot.png", "black");
    public final ImageIcon viable = new ImageIcon(this.path+"/viable_spot.png", "viable");
    private final ImageIcon focused = new ImageIcon(this.path+"/focused_spot.png", "focused");

    public JPanel board;
    public int size;
    public char[][] playground;

    private final Game game;
    private int playerMove = 1;
    private boolean isPlayable = true;
    private final stealPC stealPC = new stealPC();
    private final stealPlayer stealPlayer = new stealPlayer(this);
    private final checkBoard check = new checkBoard(this);

    public GameLogic(Game game, int size) {
        this.size = size;
        this.game = game;
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
                spot.addMouseListener(this);
                board.add(spot);
            }
        }

        board.setLayout(new GridLayout(this.size, this.size));

        markViableSpots();
    }

    private int convertX (int x) {
        return x/68;
    }

    private int convertY (int y) {
        return y/68;
    }

    public int[] getLives() {
        int player = 0;
        int computer = 0;
        int[] lives = new int[2];
        for (int i=0;i<this.size;i++) {
            for (int j=0;j<this.size;j++) {
                if (this.playground[i][j] == 'w') {
                    player++;
                }
                if (this.playground[i][j] == 'b') {
                    computer++;
                }
            }
        }
        lives[0] = player;
        lives[1] = computer;
        return lives;
    }

    private int stealRocks(int row, int col, char symbol, char opponent) {
        ImageIcon icon;
        if (symbol == 'w') {
            icon = white;
        }
        else {
            icon = black;
        }
        this.playground[row][col] = symbol;
        return this.stealPlayer.stealDown(row, col, symbol, opponent, icon) +
                this.stealPlayer.stealUp(row, col, symbol, opponent, icon) +
                this.stealPlayer.stealRight(row, col, symbol, opponent, icon) +
                this.stealPlayer.stealLeft(row, col, symbol, opponent, icon) +
                this.stealPlayer.stealRightDown(row, col, symbol, opponent, icon) +
                this.stealPlayer.stealRightUp(row, col, symbol, opponent, icon) +
                this.stealPlayer.stealLeftDown(row, col, symbol, opponent, icon) +
                this.stealPlayer.stealLeftUp(row, col, symbol, opponent, icon);
    }

    private void markViableSpots() {
        char opponent;
        char symbol;
        if (this.playerMove == 1) {
            opponent = 'b';
            symbol = 'w';
        }
        else {
            opponent = 'w';
            symbol = 'b';
        }
        for (int i=0;i<this.size;i++) {
            for (int j=0;j<this.size;j++) {
                this.isPlayable = check.checkDown(i, j, symbol, opponent) || check.checkUp(i, j, symbol, opponent) ||
                        check.checkRight(i, j, symbol, opponent) || check.checkLeft(i, j, symbol, opponent) ||
                        check.checkLeftUp(i, j, symbol, opponent) || check.checkLeftDown(i, j, symbol, opponent) ||
                        check.checkRightUp(i, j, symbol, opponent) || check.checkRightDown(i, j, symbol, opponent);
            }
        }
    }

    private void findBestMove() {
        int[] best = new int[2];
        int bestVal = 0;
        char[][] playgroundCopy = new char[this.size][this.size];
        for(int i=0; i<this.size; i++) {
            System.arraycopy(this.playground[i], 0, playgroundCopy[i], 0, this.size);
        }
        int newNum;
        for (int i=0;i<this.size;i++) {
            for (int j=0;j<this.size;j++) {
                if (playgroundCopy[i][j] == 'v') {
                    newNum = stealPC.stealDownPC(i, j, 'b', 'w', playgroundCopy);
                    if (newNum >= bestVal) {
                        bestVal = newNum;
                        best[0] = i;
                        best[1] = j;
                    }
                    newNum = stealPC.stealUpPC(i, j, 'b', 'w', playgroundCopy);
                    if (newNum >= bestVal) {
                        bestVal = newNum;
                        best[0] = i;
                        best[1] = j;
                    }
                    newNum = stealPC.stealRightPC(i, j, 'b', 'w', playgroundCopy);
                    if (newNum >= bestVal) {
                        bestVal = newNum;
                        best[0] = i;
                        best[1] = j;
                    }
                    newNum = stealPC.stealLeftPC(i, j, 'b', 'w', playgroundCopy);
                    if (newNum >= bestVal) {
                        bestVal = newNum;
                        best[0] = i;
                        best[1] = j;
                    }
                    newNum = stealPC.stealRightDownPC(i, j, 'b', 'w', playgroundCopy);
                    if (newNum >= bestVal) {
                        bestVal = newNum;
                        best[0] = i;
                        best[1] = j;
                    }
                    newNum = stealPC.stealRightUpPC(i, j, 'b', 'w', playgroundCopy);
                    if (newNum >= bestVal) {
                        bestVal = newNum;
                        best[0] = i;
                        best[1] = j;
                    }
                    newNum = stealPC.stealLeftDownPC(i, j, 'b', 'w', playgroundCopy);
                    if (newNum >= bestVal) {
                        bestVal = newNum;
                        best[0] = i;
                        best[1] = j;
                    }
                    newNum = stealPC.stealLeftUpPC(i, j, 'b', 'w', playgroundCopy);
                    if (newNum >= bestVal) {
                        bestVal = newNum;
                        best[0] = i;
                        best[1] = j;
                    }
                }
            }
        }
        int stolenRocks = stealRocks(best[0], best[1], 'b', 'w');
        System.out.println("PC stole " + stolenRocks + " rocks from player.");
        this.game.stealRocks();
    }

    private void cleanUp() {
        for (int i=0;i<this.size;i++) {
            for (int j=0;j<this.size;j++) {
                if (this.playground[i][j] == 'v') {
                    this.playground[i][j] = ' ';
                    JLabel currentLabel = (JLabel) this.board.getComponent(j+i*this.size);
                    if (currentLabel.getIcon().equals(viable)) {
                        currentLabel.setIcon(empty);
                    }
                }
            }
        }
    }

    private void computerMove() {
        cleanUp();
        markViableSpots();
        if (!this.isPlayable) {
            System.out.println("No possible moves.");
        }
        if (checkPossibleMove() == 0) {
            game.setWinner();
            this.isPlayable = false;
            return;
        }
        findBestMove();
        cleanUp();
        this.playerMove = (this.playerMove+1)%2;
    }

    private int checkPossibleMove() {
        int cnt = 0;
        for (int i=0;i<this.size;i++) {
            for (int j=0;j<this.size;j++) {
                if (this.playground[i][j] == 'v') {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
            this.game.dispose();
            System.exit(0);
        }
        if (e.getKeyChar() == 'r') {
            this.game.dispose();
            new Game();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JLabel targetLabel = (JLabel) e.getSource();
        int col = this.convertX(targetLabel.getX());
        int row = this.convertY(targetLabel.getY());
        if (targetLabel.getIcon().equals(focused)) {
            targetLabel.setIcon(white);
            int stolenRocks = stealRocks(row, col, 'w', 'b');
            if (!this.isPlayable) {
                System.out.println("No possible moves.");
            }
            System.out.println("Player stole " + stolenRocks + " rocks from the PC.");
            this.game.stealRocks();
            this.playerMove = (this.playerMove+1)%2;
            computerMove();
            markViableSpots();
            if (checkPossibleMove() == 0) {
                game.setWinner();
                this.isPlayable = false;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel targetLabel = (JLabel) e.getSource();
        if (targetLabel.getIcon().equals(viable)) {
            targetLabel.setIcon(focused);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel targetLabel = (JLabel) e.getSource();
        if (targetLabel.getIcon().equals(focused)) {
            targetLabel.setIcon(viable);
        }
    }
}
