import graphs.*;
import graphs.List;

import java.util.Arrays;


public class Labone {

    public static void main(String[] args) {
        Tiefensuche ts = new Tiefensuche();

        var l = new Lab2();
        Graph graph = l.gen();

        ts.find(new Vertex("Z"), graph.getNeighbours(graph.getVertex("A")), new List<>());



    }

    public static class Tiefensuche{

        private static List<List<Vertex>> paths;

        private static List find(Vertex d, List<Vertex> sub, List<Vertex> way) {
            sub.toFirst();

            while (sub.hasAccess()) {
                var current = sub.getContent();
                way.append(current);

                if (current == d) {
                    paths.append(way);
                    way.clear();
                    return paths;

                } else if (!current.isMarked()) {
                        d.setMark(true);
                        return find(d, sub.getNeighbours(current), way);
                    }
                sub.next();
                }
            return paths;
        }


        private static int[][] adjMatrix;



        private Graph graph = MeinGraph.graph();

        int[][] toAdjancyMatrix() {

            Vertex[] vertecies;
            List<Edge> list = new List<Edge>();

            int length = 0;
            while (list.hasAccess()) {
                length++;
                list.next();
            }

            int[][] temp = new int[length][length];

            list.concat(graph.getEdges());
            list.toFirst();
            while (list.hasAccess()) {
                vertecies = list.getContent().getVertices();
                for (int i = 0; i < vertecies.length; i++) {
                    temp[Integer.parseInt(vertecies[i].getID())][Integer.parseInt(vertecies[i+1].getID())] = 1;
                }
            }

            return temp;
        }
    }


class GraphMatrix {

    public GraphMatrix(Graph graph) {
        int length = 0;
        while (graph.getVertices().hasAccess()) {
            length++;
        }
        this.numVertices = numVertices;
        adjMatrix = new boolean[length][length];

        var e = graph.getEdges();


    }

    private boolean adjMatrix[][];
    private int numVertices;

    public GraphMatrix(int numVertices) {
        this.numVertices = numVertices;
        adjMatrix = new boolean[numVertices][numVertices];
    }

    public void addEdge(int i, int j) {
        adjMatrix[i][j] = true;
        adjMatrix[j][i] = true;
    }

    public void removeEdge(int i, int j) {
        adjMatrix[i][j] = false;
        adjMatrix[j][i] = false;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < numVertices; i++) {
            s.append(i + ": ");
            for (boolean j : adjMatrix[i]) {
                s.append((j ? 1 : 0) + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    }
}

class MeinGraph {

        public static graphs.Graph graph() {
            Graph graf = new Graph();

            Vertex a = new Vertex("A");
            graf.addVertex(a);
            Vertex b = new Vertex("B");
            Vertex c = new Vertex("C");
            Vertex d = new Vertex("D");
            Vertex e = new Vertex("E");
            Vertex f = new Vertex("F");
            Vertex g = new Vertex("G");
            Vertex h = new Vertex("H");
            Vertex i = new Vertex("I");
            Vertex z = new Vertex("Z");
            graf.addVertex(b);
            graf.addVertex(c);
            graf.addVertex(d);
            graf.addVertex(e);
            graf.addVertex(f);
            graf.addVertex(g);
            graf.addVertex(h);
            graf.addVertex(i);
            graf.addVertex(z);
            Edge ab = new Edge(a, b, 1);
            Edge bc = new Edge(b, c, 1);
            Edge cd = new Edge(c, d, 1);
            Edge ce = new Edge(c, e, 1);
            Edge bf = new Edge(b, f, 1);
            Edge fh = new Edge(f, h, 1);
            Edge fg = new Edge(f, g, 1);
            Edge gz = new Edge(g, z, 1);
            Edge gi = new Edge(g, i, 1);

            graf.addEdge(ab);
            graf.addEdge(bc);
            graf.addEdge(ce);
            graf.addEdge(bf);
            graf.addEdge(fh);
            graf.addEdge(fg);
            graf.addEdge(gz);
            graf.addEdge(gi);
            graf.addEdge(cd);

            return graf;
        }
    }

class Lab2 {

    Graph graph = new Graph();
    Vertex A = new Vertex("A");
    Vertex B = new Vertex("B");
    Vertex C = new Vertex("C");
    Vertex D = new Vertex("D");
    Vertex E = new Vertex("E");
    Vertex F = new Vertex("F");
    Vertex G = new Vertex("G");
    Vertex H = new Vertex("H");
    Vertex I = new Vertex("I");
    Vertex Z = new Vertex("Z");



    public Graph gen() {

        graph.addVertex(A);
        graph.addVertex(B);
        graph.addVertex(C);
        graph.addVertex(D);
        graph.addVertex(E);
        graph.addVertex(F);
        graph.addVertex(G);
        graph.addVertex(H);
        graph.addVertex(I);
        graph.addVertex(Z);
        //Edges
        Edge AB = new Edge(A, B, 0);
        graph.addEdge(AB);
        Edge BC = new Edge(B, C, 0);
        graph.addEdge(BC);
        Edge CD = new Edge(C, D, 0);
        graph.addEdge(CD);
        Edge CE = new Edge(C, E, 0);
        graph.addEdge(CE);
        Edge BF = new Edge(B, F, 0);
        graph.addEdge(BF);
        Edge FH = new Edge(F, H, 0);
        graph.addEdge(FH);
        Edge FG = new Edge(F, G, 0);
        graph.addEdge(FG);
        Edge GZ = new Edge(G, Z, 0);
        graph.addEdge(GZ);
        Edge GI = new Edge(G, I, 0);
        graph.addEdge(GI);

        return graph;

    }
}