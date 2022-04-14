package sk.stuba.fei.uim.oop.controls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import sk.stuba.fei.uim.oop.playground.Game;

public class GameLogic extends UniversalAdapter{

    private final String path = "src/main/java/sk/stuba/fei/uim/oop/controls/images";
    private final ImageIcon empty = new ImageIcon(this.path+"/empty_spot.png", "empty");
    private final ImageIcon white = new ImageIcon(this.path+"/white_spot.png", "white");
    private final ImageIcon black = new ImageIcon(this.path+"/black_spot.png", "black");
    private final ImageIcon viable = new ImageIcon(this.path+"/viable_spot.png", "viable");
    private final ImageIcon focused = new ImageIcon(this.path+"/focused_spot.png", "focused");

    public JPanel board;
    public int size;
    public char[][] playground;

    private int playerMove = 1;
    private boolean isPlayable = true;

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
                spot.addMouseListener(this);
                board.add(spot);
            }
        }
        JLabel place;
        place = (JLabel) this.board.getComponent(64);
        place.setIcon(black);
        this.playground[5][4] = 'b';

        place = (JLabel) this.board.getComponent(88);
        place.setIcon(black);
        this.playground[7][4] = 'b';

        place = (JLabel) this.board.getComponent(55);
        place.setIcon(black);
        this.playground[4][7] = 'b';

        place = (JLabel) this.board.getComponent(90);
        place.setIcon(black);
        this.playground[7][6] = 'b';

        board.setLayout(new GridLayout(this.size, this.size));

        while (this.isPlayable) {
            markViableSpots();

        }
    }

    private int convertX (int x) {
        return x/68;
    }

    private int convertY (int y) {
        return y/68;
    }

    private void stealRocks(int row, int col, char symbol, char opponent) {
        ImageIcon icon;
        if (symbol == 'w') {
            icon = white;
        }
        else {
            icon = black;
        }
        int place = col+row*this.size;
        while (checkDown(row, col, symbol, opponent)) {
            JLabel targetLabel = (JLabel) this.board.getComponent(place+this.size);
            targetLabel.setIcon(icon);
            this.playground[row+1][col] = symbol;
        }
        while (checkUp(row, col, symbol, opponent)) {
            JLabel targetLabel = (JLabel) this.board.getComponent(place-this.size);
            targetLabel.setIcon(icon);
            this.playground[row-1][col] = symbol;
        }
        while (checkRight(row, col, symbol, opponent)) {
            JLabel targetLabel = (JLabel) this.board.getComponent(place+1);
            targetLabel.setIcon(icon);
            this.playground[row][col+1] = symbol;
        }
        while (checkLeft(row, col, symbol, opponent)) {
            JLabel targetLabel = (JLabel) this.board.getComponent(place-1);
            targetLabel.setIcon(icon);
            this.playground[row][col-1] = symbol;
        }
    }

    private boolean checkDown(int row, int col, char symbol, char opponent) {
        boolean stolenRocks = false;
        for (int i=row+1;i<this.size-row;i++) {
            if (this.playground[i][col] == opponent) {
                stolenRocks = true;
                continue;
            }
            else if (this.playground[i][col] == ' ') {
                stolenRocks = false;
            }
            else if (this.playground[i][col] == symbol && stolenRocks) {
                this.playground[row][col] = 'v';
                int place = col+row*this.size;
                JLabel currentLabel = (JLabel) this.board.getComponent(place);
                currentLabel.setIcon(viable);
                return true;
            }
            row++;
        }
        return false;
    }

    private boolean checkUp(int row, int col, char symbol, char opponent) {
        boolean stolenRocks = false;
        for (int i=row-1;i>=0;i--) {
            if (this.playground[i][col] == opponent) {
                stolenRocks = true;
                continue;
            }
            else if (this.playground[i][col] == ' ') {
                stolenRocks = false;
            }
            else if (this.playground[i][col] == symbol && stolenRocks) {
                this.playground[row][col] = 'v';
                int place = col+row*this.size;
                JLabel currentLabel = (JLabel) this.board.getComponent(place);
                currentLabel.setIcon(viable);
                return true;
            }
            row--;
        }
        return false;
    }

    private boolean checkRight(int row, int col, char symbol, char opponent) {
        boolean stolenRocks = false;
        for (int i=col+1;i<this.size-col;i++) {
            if (this.playground[row][i] == opponent) {
                stolenRocks = true;
                continue;
            }
            else if (this.playground[row][i] == ' ') {
                stolenRocks = false;
            }
            else if (this.playground[row][i] == symbol && stolenRocks) {
                this.playground[row][col] = 'v';
                int place = col+row*this.size;
                JLabel currentLabel = (JLabel) this.board.getComponent(place);
                currentLabel.setIcon(viable);
                return true;
            }
            col++;
        }
        return false;
    }

    private boolean checkLeft(int row, int col, char symbol, char opponent) {
        boolean stolenRocks = false;
        for (int i=col-1;i>=0;i--) {
            if (this.playground[row][i] == opponent) {
                stolenRocks = true;
                continue;
            }
            else if (this.playground[row][i] == ' ') {
                stolenRocks = false;
            }
            else if (this.playground[row][i] == symbol && stolenRocks) {
                this.playground[row][col] = 'v';
                int place = col+row*this.size;
                JLabel currentLabel = (JLabel) this.board.getComponent(place);
                currentLabel.setIcon(viable);
                return true;
            }
            col--;
        }
        return false;
    }

    private boolean checkRightDown(int row, int col, char symbol, char opponent) {
        boolean stolenRocks = false;
        row++;
        col++;
        while (row<this.size-row && col < this.size-col) {
            if (this.playground[row][col] == opponent) {
                stolenRocks = true;
            }
            else if (this.playground[row][col] == symbol && stolenRocks) {
                this.playground[row][col] = 'v';
                int place = col+row*this.size;
                JLabel currentLabel = (JLabel) this.board.getComponent(place);
                currentLabel.setIcon(viable);
                return true;
            }
            row++;
            col++;
        }
        return false;
    }

    private boolean checkLeftDown(int row, int col, char symbol, char opponent) {
        boolean stolenRocks = false;
        int x = row;
        int y = col;
        while (row>=0 && col>=0 && row<this.size && col<this.size) {
            if (this.playground[row][col] == opponent && !stolenRocks) {
                stolenRocks = true;
                x = row-1;
                y = col+1;
            }
            else if (this.playground[row][col] == opponent) {
                stolenRocks = true;
            }
            else if (this.playground[row][col] == ' ') {
                stolenRocks = false;
            }
            else if (this.playground[row][col] == symbol && stolenRocks) {
                this.playground[x][y] = 'v';
                int place = y+x*this.size;
                JLabel currentLabel = (JLabel) this.board.getComponent(place);
                currentLabel.setIcon(viable);
                return true;
            }
            row++;
            col--;
        }
        return false;
    }

    private boolean checkRightUp(int row, int col, char symbol, char opponent) {
        boolean stolenRocks = false;
        int x = row;
        int y = col;
        while (row>=0 && col>=0 && row<6 && col<6) {
            if (this.playground[row][col] == opponent && !stolenRocks) {
                stolenRocks = true;
                x = row-1;
                y = col+1;
            }
            else if (this.playground[row][col] == opponent) {
                stolenRocks = true;
            }
            else if (this.playground[row][col] == ' ') {
                stolenRocks = false;
            }
            else if (this.playground[row][col] == symbol && stolenRocks) {
                this.playground[x][y] = 'v';
                int place = y+x*this.size;
                JLabel currentLabel = (JLabel) this.board.getComponent(place);
                currentLabel.setIcon(viable);
                return true;
            }
            row++;
            col++;
        }
        return false;
    }

    private boolean checkLeftUp(int row, int col, char symbol, char opponent) {
        boolean stolenRocks = false;
        int x = row;
        int y = col;
        while (row>=0 && col>=0 && row<6 && col<6) {
            if (this.playground[row][col] == opponent && !stolenRocks) {
                stolenRocks = true;
                x = row-1;
                y = col-1;
            }
            else if (this.playground[row][col] == opponent) {
                stolenRocks = true;
            }
            else if (this.playground[row][col] == ' ') {
                stolenRocks = false;
            }
            else if (this.playground[row][col] == symbol && stolenRocks) {
                this.playground[x][y] = 'v';
                int place = y+x*this.size;
                JLabel currentLabel = (JLabel) this.board.getComponent(place);
                currentLabel.setIcon(viable);
                return true;
            }
            row++;
            col++;
        }
        return false;
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
                this.isPlayable = checkDown(i, j, symbol, opponent) || checkUp(i, j, symbol, opponent) ||
                        checkRight(i, j, symbol, opponent) || checkLeft(i, j, symbol, opponent) ||
                        checkLeftUp(i, j, symbol, opponent) || checkLeftDown(i, j, symbol, opponent);
            }
        }
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
//        findBestMove();
        cleanUp();
    }

    @Override
    public void mousePressed(MouseEvent e) {
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
        JLabel targetLabel = (JLabel) e.getSource();
        int row = this.convertX(targetLabel.getX());
        int col = this.convertY(targetLabel.getY());
        if (targetLabel.getIcon().equals(focused)) {
            targetLabel.setIcon(white);
            this.stealRocks(row, col, symbol, opponent);
            this.playerMove = (this.playerMove+1)%2;
            computerMove();
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
