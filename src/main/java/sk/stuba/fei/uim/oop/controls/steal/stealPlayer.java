package sk.stuba.fei.uim.oop.controls.steal;

import javax.swing.*;
import sk.stuba.fei.uim.oop.controls.GameLogic;

public class stealPlayer {
    private final GameLogic logic;
    public stealPlayer(GameLogic logic) {
        this.logic = logic;
    }

    public int stealDown(int row, int col, char symbol, char opponent, ImageIcon icon) {
        int stolenRocks = 0;
        int finish = 0;
        boolean hasFinish = false;
        for (int i=row+1;i<logic.size-row;i++) {
            if (logic.playground[i][col] == opponent) {
                stolenRocks++;
            }
            else if (logic.playground[i][col] == ' ' || logic.playground[i][col] == 'v') {
                stolenRocks = 0;
            }
            else if (logic.playground[i][col] == symbol && stolenRocks>0) {
                finish = i;
                logic.playground[row][col] = symbol;
                hasFinish = true;
                break;
            }
        }
        if (stolenRocks>0 && hasFinish) {
            for (int j = row; j <= finish; j++) {
                logic.playground[j][col] = symbol;
                int place = col + j * logic.size;
                JLabel currentLabel = (JLabel) logic.board.getComponent(place);
                currentLabel.setIcon(icon);
            }
        }
        return stolenRocks;
    }

    public int stealUp(int row, int col, char symbol, char opponent, ImageIcon icon) {
        int stolenRocks = 0;
        int finish = 0;
        boolean hasFinish = false;
        for (int i=row-1;i>=0;i--) {
            if (logic.playground[i][col] == opponent) {
                stolenRocks++;
            }
            else if (logic.playground[i][col] == ' ' || logic.playground[i][col] == 'v') {
                stolenRocks = 0;
            }
            else if (logic.playground[i][col] == symbol && stolenRocks>0) {
                finish = i;
                logic.playground[row][col] = symbol;
                hasFinish = true;
                break;
            }
        }
        if (stolenRocks>0 && hasFinish) {
            for (int j = row; j >= finish; j--) {
                logic.playground[j][col] = symbol;
                int place = col + j * logic.size;
                JLabel currentLabel = (JLabel) logic.board.getComponent(place);
                currentLabel.setIcon(icon);
            }
        }
        return stolenRocks;
    }

    public int stealRight(int row, int col, char symbol, char opponent, ImageIcon icon) {
        int stolenRocks = 0;
        int finish = 0;
        boolean hasFinish = false;
        for (int i=col+1;i<logic.size-col;i++) {
            if (logic.playground[row][i] == opponent) {
                stolenRocks++;
            }
            else if (logic.playground[row][i] == ' ' || logic.playground[row][i] == 'v') {
                stolenRocks = 0;
            }
            else if (logic.playground[row][i] == symbol && stolenRocks>0) {
                finish = i;
                logic.playground[row][col] = symbol;
                hasFinish = true;
                break;
            }
        }
        if (stolenRocks>0 && hasFinish) {
            for (int j = col;j<=finish;j++) {
                logic.playground[row][j] = symbol;
                int place = j+row*logic.size;
                JLabel currentLabel = (JLabel) logic.board.getComponent(place);
                currentLabel.setIcon(icon);
            }
        }
        return stolenRocks;
    }

    public int stealLeft(int row, int col, char symbol, char opponent, ImageIcon icon) {
        int stolenRocks = 0;
        int finish = 0;
        boolean hasFinish = false;
        for (int i=col-1;i>=0;i--) {
            if (logic.playground[row][i] == opponent) {
                stolenRocks++;
            }
            else if (logic.playground[row][i] == ' ' || logic.playground[row][i] == 'v') {
                stolenRocks = 0;
            }
            else if (logic.playground[row][i] == symbol && stolenRocks>0) {
                finish = i;
                logic.playground[row][col] = symbol;
                hasFinish = true;
                break;
            }
        }
        if (stolenRocks>0 && hasFinish) {
            for (int j=col;j>=finish;j--) {
                logic.playground[row][j] = symbol;
                int place = j + row * logic.size;
                JLabel currentLabel = (JLabel) logic.board.getComponent(place);
                currentLabel.setIcon(icon);
            }
        }
        return stolenRocks;
    }

    public int stealRightDown(int row, int col, char symbol, char opponent, ImageIcon icon) {
        int stolenRocks = 0;
        int finishRow = row+1;
        int finishCol = col+1;
        boolean hasFinish = false;
        while (finishRow<logic.size && finishCol<logic.size && finishRow>=0 && finishCol>=0) {
            if (logic.playground[finishRow][finishCol] == opponent) {
                stolenRocks++;
            }
            else if (logic.playground[finishRow][finishCol] == ' ' || logic.playground[finishRow][finishCol] == 'v') {
                stolenRocks = 0;
            }
            else if (logic.playground[finishRow][finishCol] == symbol && stolenRocks>0) {
                logic.playground[row][col] = symbol;
                hasFinish = true;
                break;
            }
            finishRow++;
            finishCol++;
        }
        if (stolenRocks>0 && hasFinish) {
            while (row<finishRow && col<finishCol && row>=0 && col>=0) {
                logic.playground[row][col] = symbol;
                int place = col+row*logic.size;
                JLabel currentLabel = (JLabel) logic.board.getComponent(place);
                currentLabel.setIcon(icon);
                row++;
                col++;
            }
        }
        return stolenRocks;
    }

    public int stealRightUp(int row, int col, char symbol, char opponent, ImageIcon icon) {
        int stolenRocks = 0;
        int finishRow = row-1;
        int finishCol = col+1;
        boolean hasFinish = false;
        while (finishRow<logic.size && finishCol<logic.size && finishRow>=0 && finishCol>=0) {
            if (logic.playground[finishRow][finishCol] == opponent) {
                stolenRocks++;
            }
            else if (logic.playground[finishRow][finishCol] == ' ' || logic.playground[finishRow][finishCol] == 'v') {
                stolenRocks = 0;
            }
            else if (logic.playground[finishRow][finishCol] == symbol && stolenRocks>0) {
                logic.playground[row][col] = symbol;
                hasFinish = true;
                break;
            }
            finishRow--;
            finishCol++;
        }
        if (stolenRocks>0 && hasFinish) {
            while (row>finishRow && col<finishCol && col>=0) {
                logic.playground[row][col] = symbol;
                int place = col+row*logic.size;
                JLabel currentLabel = (JLabel) logic.board.getComponent(place);
                currentLabel.setIcon(icon);
                row--;
                col++;
            }
        }
        return stolenRocks;
    }

    public int stealLeftDown(int row, int col, char symbol, char opponent, ImageIcon icon) {
        int stolenRocks = 0;
        int finishRow = row+1;
        int finishCol = col-1;
        boolean hasFinish = false;
        while (finishRow<logic.size && finishCol<logic.size && finishRow>=0 && finishCol>=0) {
            if (logic.playground[finishRow][finishCol] == opponent) {
                stolenRocks++;
            }
            else if (logic.playground[finishRow][finishCol] == ' ' || logic.playground[finishRow][finishCol] == 'v') {
                stolenRocks = 0;
            }
            else if (logic.playground[finishRow][finishCol] == symbol && stolenRocks>0) {
                logic.playground[row][col] = symbol;
                hasFinish = true;
                break;
            }
            finishRow++;
            finishCol--;
        }
        if (stolenRocks>0 && hasFinish) {
            while (row<finishRow && col>finishCol && row>=0) {
                logic.playground[row][col] = symbol;
                int place = col+row*logic.size;
                JLabel currentLabel = (JLabel) logic.board.getComponent(place);
                currentLabel.setIcon(icon);
                row++;
                col--;
            }
        }
        return stolenRocks;
    }

    public int stealLeftUp(int row, int col, char symbol, char opponent, ImageIcon icon) {
        int stolenRocks = 0;
        int finishRow = row-1;
        int finishCol = col-1;
        boolean hasFinish = false;
        while (finishRow<logic.size && finishCol<logic.size && finishRow>=0 && finishCol>=0) {
            if (logic.playground[finishRow][finishCol] == opponent) {
                stolenRocks++;
            }
            else if (logic.playground[finishRow][finishCol] == ' ' || logic.playground[finishRow][finishCol] == 'v') {
                stolenRocks = 0;
            }
            else if (logic.playground[finishRow][finishCol] == symbol && stolenRocks>0) {
                logic.playground[row][col] = symbol;
                hasFinish = true;
                break;
            }
            finishRow--;
            finishCol--;
        }
        if (stolenRocks>0 && hasFinish) {
            while (row>finishRow && col>finishCol) {
                logic.playground[row][col] = symbol;
                int place = col+row*logic.size;
                JLabel currentLabel = (JLabel) logic.board.getComponent(place);
                currentLabel.setIcon(icon);
                row--;
                col--;
            }
        }
        return stolenRocks;
    }
}
