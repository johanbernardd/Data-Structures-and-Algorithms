import java.util.*;

class Graph {
    int totalEdge;
    String[] edge;
    int[] value;
    boolean[] isVisited;
    int[][] adjMatrix;

    public Graph(int totalNodeEdge) {
        this.totalEdge = totalNodeEdge;
        adjMatrix = new int[totalNodeEdge][totalNodeEdge];
        edge = new String[totalNodeEdge];
        value = new int[totalNodeEdge];
        isVisited = new boolean[totalNodeEdge];
        Arrays.fill(value, Integer.MAX_VALUE);
    }

    public int searchEdge(String vertex) {
        for (int i = 0; i < totalEdge; i++) {
            if (edge[i].equals(vertex)) {
                return i;
            }
        }
        return -1;
    }

    public void addSimpul(String edge) {
        for (int i = 0; i < totalEdge; i++) {
            if (this.edge[i] == null) {
                this.edge[i] = edge;
                break;
            }
        }
    }

    public void addEdge(String src, String dest, int weight) {
        int a = searchEdge(src);
        int b = searchEdge(dest);
        if (a != -1 && b != -1) {
            adjMatrix[a][b] = weight;
            if (a != b) {
                adjMatrix[b][a] = weight;
            }
        }
    }

    public int lowCost(String awal, String akhir) {
        int mulaiAwal = searchEdge(awal);
        int akhirEnd = searchEdge(akhir);
        value = new int[totalEdge];
        isVisited = new boolean[totalEdge];
        Arrays.fill(value, Integer.MAX_VALUE);

        value[mulaiAwal] = 0;

        PriorityQueue pq = new PriorityQueue();
        pq.enqueue(mulaiAwal, 0);

        while (!pq.isEmpty()) {
            int currNode = (int) pq.peek();

            if (currNode == akhirEnd) {
                return value[currNode];
            }
            isVisited[currNode] = true;

            for (int i = 0; i < totalEdge; i++) {
                if (adjMatrix[currNode][i] != 0 && !isVisited[i]) {
                    int est = value[currNode] + adjMatrix[currNode][i];

                    if (est < value[i]) {
                        value[i] = est;
                        pq.enqueue(i, est);
                    }
                }
            }
        }
        return -1;
    }
}

class PriorityQueue {
    public PriorityQueue() {
        this.head = null;
        this.tail = head;
    }

    public class Node {
        Node next;
        int prior;
        Object value;

        public Node(Object value, int prior) {
            this.value = value;
            this.prior = prior;
        }
    }

    Node head;
    Node tail;

    void enqueue(Object value, int prior) {
        Node n1 = new Node(value, prior);
        if (head == null) {
            head = tail = n1;
        } else {
            Node target = head;
            Node prevTarget = head;
            while (n1.prior >= target.prior) {
                prevTarget = target;
                target = target.next;
                if (target == null) {
                    break;
                }
            }
            if (target == null) {
                tail.next = n1;
                tail = tail.next;
                return;
            }
            if (target == head) {
                n1.next = head;
                head = n1;
                return;
            }
            n1.next = target;
            prevTarget.next = n1;
        }
    }

    boolean isEmpty() {
        return head == null;
    }

    Object peek() {
        if (isEmpty()) {
            return null;
        }
        Object hasil = head.value;
        head = head.next;
        return hasil;
    }
}

public class LK9_Graph {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalNode = sc.nextInt();
        int totalEdge = sc.nextInt();

        Graph g = new Graph(totalNode);
        sc.nextLine();
        String[] edge = sc.nextLine().split(" ");
        for (String vertex : edge) {
            g.addSimpul(vertex);
        }

        for (int i = 0; i < totalEdge; i++) {
            String[] connect = sc.nextLine().split(" ");
            for (int j = 2; j < connect.length; j += 2) {
                String src = connect[j - 2];
                String dest = connect[j - 1];
                int cost = Integer.parseInt(connect[j]);
                g.addEdge(src, dest, cost);
            }
        }
        String[] findSrcDest = sc.nextLine().split(" ");
        int lowCost = g.lowCost(findSrcDest[0], findSrcDest[1]);
        System.out.println(lowCost);
    }
}