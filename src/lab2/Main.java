package lab2;

public class Main {

    public static void main(String[] args) {
        Vector v1 = new Vector(3);
        v1.setElement(0, 1);
        v1.setElement(1, 2);
        v1.setElement(2, 3);

        Vector v2 = new Vector(3);
        v2.setElement(0, 4);
        v2.setElement(1, 5);
        v2.setElement(2, 6);

        System.out.println("v1: ");
        printVector(v1);

        System.out.println("v2: ");
        printVector(v2);

        v1.add(v2);
        System.out.println("v1 after adding v2: ");
        printVector(v1);

        double dotProduct = v1.dotProduct(v2);
        System.out.println("Dot product of v1 and v2: " + dotProduct);

        v1.sort();
        System.out.println("v1 after sorting: ");
        printVector(v1);

        double norm = v1.getNorm();
        System.out.println("Norm of v1: " + norm);

        v1.multiply(2);
        System.out.println("v1 after multiplying by 2: ");
        printVector(v1);
    }

    public static void printVector(Vector v) {
        double[] elements = v.getElements();
        for (int i = 0; i < v.getLength(); i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }
}
