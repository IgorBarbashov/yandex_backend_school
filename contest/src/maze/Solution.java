package maze;

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        Maze maze = readData();
        ArrayList<int[]> route = new ArrayList();
        route.add(maze.S);

        while (route.size() > 0) {
            int[] P = route.remove(0);
            int cN = P[0];
            int cM = P[1];

            maze.field[cN][cM].visited = true;

            if (cN > 0 && maze.field[cN - 1][cM].value != '#' && !maze.field[cN - 1][cM].visited) {
                maze.field[cN - 1][cM].direction = "U";
                route.add(new int[]{cN - 1, cM});
            }

            if (cN < maze.N - 1 && maze.field[cN + 1][cM].value != '#' && !maze.field[cN + 1][cM].visited) {
                maze.field[cN + 1][cM].direction = "D";
                route.add(new int[]{cN + 1, cM});
            }

            if (cM > 0 && maze.field[cN][cM - 1].value != '#' && !maze.field[cN][cM - 1].visited) {
                maze.field[cN][cM - 1].direction = "L";
                route.add(new int[]{cN, cM - 1});
            }

            if (cM < maze.M - 1 && maze.field[cN][cM + 1].value != '#' && !maze.field[cN][cM + 1].visited) {
                maze.field[cN][cM + 1].direction = "R";
                route.add(new int[]{cN, cM + 1});
            }
        }

        printMaze(maze);
    }

    private static Maze readData() {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in));) {
            String[] measures = r.readLine().split(" ");
            int N = Integer.parseInt(measures[0]);
            int M = Integer.parseInt(measures[1]);
            Maze maze = new Maze(N, M);

            for (int i = 0; i < N; i++) {
                String line = r.readLine();
                maze.field[i] = new Cell[M];
                for (int j = 0; j < M; j++) {
                    maze.field[i][j] = new Cell(line.charAt(j));
                    if (line.charAt(j) == 'S') {
                        maze.S[0] = i;
                        maze.S[1] = j;
                    }
                }
            }

            return maze;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printMaze(Maze maze) {
        for (Cell[] ints : maze.field) {
            for (Cell anInt : ints) {
                System.out.print(anInt.value == '.' ? anInt.direction : anInt.value);
            }
            System.out.println();
        }
    }
}

class Cell {
    char value;
    boolean visited;
    String direction;

    public Cell(char value) {
        this.value = value;
    }
}

class Maze {
    int N;
    int M;
    Cell[][] field;
    int[] S;

    public Maze(int N, int M) {
        this.N = N;
        this.M = M;
        this.field = new Cell[N][M];
        this.S = new int[2];
    }
}