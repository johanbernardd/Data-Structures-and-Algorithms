import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class NodeTree {
    String operatorand;
    NodeTree anakKiri, anakKanan;

    NodeTree(String operatorand) {
        this.operatorand = operatorand;
        this.anakKiri = null;
        this.anakKanan = null;
    }

    int ekspresiMath() {
        if (!MathExp.cekOperator(operatorand)) {
            return Integer.parseInt(operatorand);
        }

        int num1 = anakKiri.ekspresiMath();
        int num2 = anakKanan.ekspresiMath();
        switch (operatorand) {
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
        }
        return 0;
    }
}

class MathExp {

    static boolean cekOperator(String operat) {
        return (operat.equals("*") || operat.equals("/") || operat.equals("+") || operat.equals("-"));
    }

    static NodeTree aturNodeTree(String post) {
        Stack<NodeTree> aturTree = new Stack<>();

        for (int i = 0; i < post.length(); i++) {
            char indeks = post.charAt(i);
            String cek = String.valueOf(indeks);

            if (!cekOperator(cek)) {
                aturTree.push(new NodeTree(cek));
            } else {
                NodeTree node = new NodeTree(cek);
                node.anakKanan = aturTree.pop();
                node.anakKiri = aturTree.pop();
                aturTree.push(node);
            }
        }
        return aturTree.pop();
    }

    static void aturNodeTreeCek(NodeTree root, boolean kondisiRoot) {
        if (root != null) {
            boolean panggilOperator = cekOperator(root.operatorand);
            if (!kondisiRoot && panggilOperator) {
                System.out.print("(");
            }

            aturNodeTreeCek(root.anakKiri, false);
            System.out.print(root.operatorand);
            aturNodeTreeCek(root.anakKanan, false);
            if (!kondisiRoot && panggilOperator) {
                System.out.print(")");
            }
        }
    }

    static void barisBaru() {
        System.out.println();
    }
}

public class LK7_BinaryTree {

    public static void main(String[] args) {
        /*
         * Enter your code here. Read input from STDIN. Print output to STDOUT. Your
         * class should be named Solution.
         */
        Scanner sc = new Scanner(System.in);
        String bentukPostfix = sc.nextLine();

        NodeTree atur = MathExp.aturNodeTree(bentukPostfix);
        System.out.println("Infix expression :");
        MathExp.aturNodeTreeCek(atur, true);
        MathExp.barisBaru();
        System.out.println("Hasil: " + atur.ekspresiMath());
    }
}