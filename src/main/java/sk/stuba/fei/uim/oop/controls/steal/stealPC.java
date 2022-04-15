package sk.stuba.fei.uim.oop.controls.steal;

public class stealPC {
    public stealPC() {
    }
    public int stealDownPC(int row, int col, char symbol, char opponent, char[][] playground) {
        int stolenRocks = 0;
        for (int i=row+1;i<playground.length-row;i++) {
            if (playground[i][col] == opponent) {
                stolenRocks++;
            } else if (playground[i][col] == ' ' || playground[i][col] == 'v') {
                stolenRocks = 0;
            } else if (playground[i][col] == symbol && stolenRocks > 0) {
                playground[row][col] = symbol;
                return stolenRocks;
            }
        }
        return 0;
    }

    public int stealUpPC(int row, int col, char symbol, char opponent, char[][] playground) {
        int stolenRocks = 0;
        for (int i=row-1;i>=0;i--) {
            if (playground[i][col] == opponent) {
                stolenRocks++;
            }
            else if (playground[i][col] == ' ' || playground[i][col] == 'v') {
                stolenRocks = 0;
            }
            else if (playground[i][col] == symbol && stolenRocks>0) {
                playground[row][col] = symbol;
                return stolenRocks;
            }
        }
        return 0;
    }

    public int stealRightPC(int row, int col, char symbol, char opponent, char[][] playground) {
        int stolenRocks = 0;
        for (int i=col+1;i<playground.length-col;i++) {
            if (playground[row][i] == opponent) {
                stolenRocks++;
            }
            else if (playground[row][i] == ' ' || playground[row][i] == 'v') {
                stolenRocks = 0;
            }
            else if (playground[row][i] == symbol && stolenRocks>0) {
                playground[row][col] = symbol;
                return stolenRocks;
            }
        }
        return 0;
    }

    public int stealLeftPC(int row, int col, char symbol, char opponent, char[][] playground) {
        int stolenRocks = 0;
        for (int i=col-1;i>=0;i--) {
            if (playground[row][i] == opponent) {
                stolenRocks++;
            }
            else if (playground[row][i] == ' ' || playground[row][i] == 'v') {
                stolenRocks = 0;
            }
            else if (playground[row][i] == symbol && stolenRocks>0) {
                playground[row][col] = symbol;
                return stolenRocks;
            }
        }
        return 0;
    }

    public int stealRightDownPC(int row, int col, char symbol, char opponent, char[][] playground) {
        int stolenRocks = 0;
        int finishRow = row+1;
        int finishCol = col+1;
        while (finishRow<playground.length && finishCol<playground.length && finishRow>=0 && finishCol>=0) {
            if (playground[finishRow][finishCol] == opponent) {
                stolenRocks++;
            }
            else if (playground[finishRow][finishCol] == ' ' || playground[finishRow][finishCol] == 'v') {
                stolenRocks = 0;
            }
            else if (playground[finishRow][finishCol] == symbol && stolenRocks>0) {
                playground[row][col] = symbol;
                return stolenRocks;
            }
            finishRow++;
            finishCol++;
        }
        return 0;
    }

    public int stealRightUpPC(int row, int col, char symbol, char opponent, char[][] playground) {
        int stolenRocks = 0;
        int finishRow = row-1;
        int finishCol = col+1;
        while (finishRow<playground.length && finishCol<playground.length && finishRow>=0 && finishCol>=0) {
            if (playground[finishRow][finishCol] == opponent) {
                stolenRocks++;
            }
            else if (playground[finishRow][finishCol] == ' ' || playground[finishRow][finishCol] == 'v') {
                stolenRocks = 0;
            }
            else if (playground[finishRow][finishCol] == symbol && stolenRocks>0) {
                playground[row][col] = symbol;
                return stolenRocks;
            }
            finishRow--;
            finishCol++;
        }
        return 0;
    }

    public int stealLeftDownPC(int row, int col, char symbol, char opponent, char[][] playground) {
        int stolenRocks = 0;
        int finishRow = row+1;
        int finishCol = col-1;
        while (finishRow<playground.length && finishCol<playground.length && finishRow>=0 && finishCol>=0) {
            if (playground[finishRow][finishCol] == opponent) {
                stolenRocks++;
            }
            else if (playground[finishRow][finishCol] == ' ' || playground[finishRow][finishCol] == 'v') {
                stolenRocks = 0;
            }
            else if (playground[finishRow][finishCol] == symbol && stolenRocks>0) {
                playground[row][col] = symbol;
                return stolenRocks;
            }
            finishRow++;
            finishCol--;
        }
        return 0;
    }

    public int stealLeftUpPC(int row, int col, char symbol, char opponent, char[][] playground) {
        int stolenRocks = 0;
        int finishRow = row-1;
        int finishCol = col-1;
        while (finishRow<playground.length && finishCol<playground.length && finishRow>=0 && finishCol>=0) {
            if (playground[finishRow][finishCol] == opponent) {
                stolenRocks++;
            }
            else if (playground[finishRow][finishCol] == ' ' || playground[finishRow][finishCol] == 'v') {
                stolenRocks = 0;
            }
            else if (playground[finishRow][finishCol] == symbol && stolenRocks>0) {
                playground[row][col] = symbol;
                return stolenRocks;
            }
            finishRow--;
            finishCol--;
        }
        return 0;
    }
}
