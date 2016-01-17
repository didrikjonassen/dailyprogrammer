package me.didrik.dailyprogrammer.hard.c247;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Math.max;

public class ZombieHighway {
    /*Use a modified version of Dijkstra's algorithm:
    * To be stored on stack: (distance, node, blasts_left)
    * If the popped node's blasts_left is > 0: add all unvisited neighbours with same distance as the popped node*/

    public static void main(String[] args) throws IOException {
        String input = new BufferedReader(new InputStreamReader(System.in)).readLine();
        input = input.replaceAll("\\(", "");
        input = input.replaceAll("\\)", "");
        String[] split = input.split(", ");
        int[][] edges = new int[split.length/3][3];
        for (int i = 0; i < edges.length; i++) {
            edges[i][0] = Integer.parseInt(split[i*3]);
            edges[i][1] = Integer.parseInt(split[i*3+1]);
            edges[i][2] = Integer.parseInt(split[i*3+2]);
        }
        int blasters = 3;
        int lastChance = 0;
        for (int[] edge : edges) {
            lastChance = max(lastChance, max(edge[0], edge[1]));
        }

        int nodes = lastChance + 1;
        int[][] distances = bellmanFord(nodes*(blasters+1), createEdges(nodes, blasters, edges), 0);
        System.out.println(distances[lastChance+blasters*nodes][0]);// TODO: 17.01.2016 Generate output of path

    }

    public static int[][] createEdges(int nodes, int blasters, int[][] edges) {
        int[][] outputEdges = new int[edges.length*2*(blasters+1) + edges.length*blasters][3];
        int i = 0;
        for (int[] edge : edges) {
            for (int b = 0; b <= blasters; b++, i+=2) {
                outputEdges[i][0] = edge[0]+nodes*b;
                outputEdges[i][1] = edge[1]+nodes*b;
                outputEdges[i][2] = edge[2];
                outputEdges[i+1][1] = edge[0]+nodes*b;
                outputEdges[i+1][0] = edge[1]+nodes*b;
                outputEdges[i+1][2] = edge[2];
            }
            for (int b = 0; b < blasters; b++, i++) {
                outputEdges[i][0] = edge[0]+nodes*b;
                outputEdges[i][1] = edge[1]+nodes*(b+1);
            }
        }
        return outputEdges;
    }

    public static int[][] bellmanFord(int v, int[][] e, int s) {
        int[][] best = new int[v][2];
        for (int i = 0; i < v; i++) {
            best[i][0] = Integer.MAX_VALUE/2;
            best[i][1] = -1;
        }
        best[s][0] = 0;
        int cost;
        boolean changed = true;
        for (int i = 0; i < v && changed; i++) {
            changed = false;
            for (int[] edge : e) {
                cost = best[edge[0]][0] + edge[2];
                if (cost < best[edge[1]][0]) {
                    best[edge[1]][0] = cost;
                    best[edge[1]][1] = edge[0];
                    changed = true;
                }
            }
        }

        return best;
    }
}
