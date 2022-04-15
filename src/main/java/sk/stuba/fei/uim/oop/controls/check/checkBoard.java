package sk.stuba.fei.uim.oop.controls.check;

import sk.stuba.fei.uim.oop.controls.GameLogic;

import javax.swing.*;

public class checkBoard {
    private final GameLogic logic;
    public checkBoard(GameLogic logic) {
        this.logic = logic;
    }

    public boolean checkDown(int row, int col, char symbol, char opponent) {
        boolean stolenRocks = false;
        for (int i=row+1;i<this.logic.size;i++) {
            if (this.logic.playground[i][col] == opponent) {
                stolenRocks = true;
            }
            else if (this.logic.playground[i][col] == symbol && stolenRocks) {
                if (this.logic.playground[row][col] == ' ') {
                    this.logic.playground[row][col] = 'v';
                    int place = col+row*this.logic.size;
                    JLabel currentLabel = (JLabel) this.logic.board.getComponent(place);
                    currentLabel.setIcon(logic.viable);
                    return true;
                }
            }
            else if (this.logic.playground[i][col] == ' ' || this.logic.playground[i][col] == 'v') {
                stolenRocks = false;
                row++;
            }
        }
        return false;
    }

    public boolean checkUp(int row, int col, char symbol, char opponent) {
        boolean stolenRocks = false;
        for (int i=row-1;i>=0;i--) {
            if (this.logic.playground[i][col] == opponent) {
                stolenRocks = true;
            } else if (this.logic.playground[i][col] == symbol && stolenRocks) {
                if (this.logic.playground[row][col] == ' ') {
                    this.logic.playground[row][col] = 'v';
                    int place = col+row*this.logic.size;
                    JLabel currentLabel = (JLabel) this.logic.board.getComponent(place);
                    currentLabel.setIcon(logic.viable);
                    return true;
                }
            } else if (this.logic.playground[i][col] == ' ' || this.logic.playground[i][col] == 'v') {
                stolenRocks = false;
                row--;
            }
        }
        return false;
    }

    public boolean checkRight(int row, int col, char symbol, char opponent) {
        boolean stolenRocks = false;
        for (int i=col+1;i<this.logic.size;i++) {
            if (this.logic.playground[row][i] == opponent) {
                stolenRocks = true;
            } else if (this.logic.playground[row][i] == symbol && stolenRocks) {
                if (this.logic.playground[row][col] == ' ') {
                    this.logic.playground[row][col] = 'v';
                    int place = col+row*this.logic.size;
                    JLabel currentLabel = (JLabel) this.logic.board.getComponent(place);
                    currentLabel.setIcon(logic.viable);
                    return true;
                }
            } else if (this.logic.playground[row][i] == ' '  || this.logic.playground[row][i] == 'v') {
                stolenRocks = false;
                col++;
            }
        }
        return false;
    }

    public boolean checkLeft(int row, int col, char symbol, char opponent) {
        boolean stolenRocks = false;
        for (int i=col-1;i>=0;i--) {
            if (this.logic.playground[row][i] == opponent) {
                stolenRocks = true;
            } else if (this.logic.playground[row][i] == symbol && stolenRocks) {
                if (this.logic.playground[row][col] == ' ') {
                    this.logic.playground[row][col] = 'v';
                    int place = col+row*this.logic.size;
                    JLabel currentLabel = (JLabel) this.logic.board.getComponent(place);
                    currentLabel.setIcon(logic.viable);
                    return true;
                }
            } else if (this.logic.playground[row][i] == ' ' || this.logic.playground[row][i] == 'v') {
                stolenRocks = false;
                col--;
            }
        }
        return false;
    }

    public boolean checkRightDown(int row, int col, char symbol, char opponent) {
        boolean stolenRocks = false;
        int x = row+1;
        int y = col+1;
        while (x<this.logic.size && y<this.logic.size && x>=0 && y>=0) {
            if (this.logic.playground[x][y] == opponent) {
                stolenRocks = true;
            }
            else if (this.logic.playground[x][y] == symbol && stolenRocks) {
                if (this.logic.playground[row][col] == ' ') {
                    this.logic.playground[row][col] = 'v';
                    int place = col+row*this.logic.size;
                    JLabel currentLabel = (JLabel) this.logic.board.getComponent(place);
                    currentLabel.setIcon(logic.viable);
                    return true;
                }
            }
            else if (this.logic.playground[x][y] == ' ' || this.logic.playground[x][y] == 'v') {
                stolenRocks = false;
                row++;
                col++;
            }
            x++;
            y++;
        }
        return false;
    }

    public boolean checkLeftDown(int row, int col, char symbol, char opponent) {
        boolean stolenRocks = false;
        int x = row+1;
        int y = col-1;
        while (x>=0 && y>=0 && x<this.logic.size && y<this.logic.size) {
            if (this.logic.playground[x][y] == opponent) {
                stolenRocks = true;
            }
            else if (this.logic.playground[x][y] == symbol && stolenRocks) {
                if (this.logic.playground[row][col] == ' ') {
                    this.logic.playground[row][col] = 'v';
                    int place = col+row*this.logic.size;
                    JLabel currentLabel = (JLabel) this.logic.board.getComponent(place);
                    currentLabel.setIcon(logic.viable);
                    return true;
                }
            }
            else if (this.logic.playground[x][y] == ' ' || this.logic.playground[x][y] == 'v') {
                stolenRocks = false;
                row++;
                col--;
            }
            x++;
            y--;
        }
        return false;
    }

    public boolean checkRightUp(int row, int col, char symbol, char opponent) {
        boolean stolenRocks = false;
        int x = row-1;
        int y = col+1;
        while (x>=0 && y>=0 && x<this.logic.size && y<this.logic.size) {
            if (this.logic.playground[x][y] == opponent) {
                stolenRocks = true;
            }
            else if (this.logic.playground[x][y] == symbol && stolenRocks) {
                if (this.logic.playground[row][col] == ' ') {
                    this.logic.playground[row][col] = 'v';
                    int place = col+row*this.logic.size;
                    JLabel currentLabel = (JLabel) this.logic.board.getComponent(place);
                    currentLabel.setIcon(logic.viable);
                    return true;
                }
            }
            else if (this.logic.playground[x][y] == ' ' || this.logic.playground[x][y] == 'v') {
                stolenRocks = false;
                row--;
                col++;
            }
            x--;
            y++;
        }
        return false;
    }

    public boolean checkLeftUp(int row, int col, char symbol, char opponent) {
        boolean stolenRocks = false;
        int x = row-1;
        int y = col-1;
        while (x>=0 && y>=0 && x<this.logic.size && y<this.logic.size) {
            if (this.logic.playground[x][y] == opponent) {
                stolenRocks = true;
            }
            else if (this.logic.playground[x][y] == symbol && stolenRocks) {
                if (this.logic.playground[row][col] == ' ') {
                    this.logic.playground[row][col] = 'v';
                    int place = col+row*this.logic.size;
                    JLabel currentLabel = (JLabel) this.logic.board.getComponent(place);
                    currentLabel.setIcon(logic.viable);
                    return true;
                }
            }
            else if (this.logic.playground[x][y] == ' ' || this.logic.playground[x][y] == 'v') {
                stolenRocks = false;
                row--;
                col--;
            }
            x--;
            y--;
        }
        return false;
    }
}
