package lab2;

public class Vector {
    private final int length;
    private final double[] elements;

    public Vector(int length) {
        this.length = length;
        this.elements = new double[length];
    }

    public int getLength() {
        return length;
    }

    public double getElement(int index) {
        return elements[index];
    }

    public double[] getElements(){
        return elements;
    }

    public void setElement(int index, double value) {
        elements[index] = value;
    }

    public double getMin() {
        double min = elements[0];
        for (int i = 1; i < length; i++) {
            if (elements[i] < min) {
                min = elements[i];
            }
        }
        return min;
    }

    public double getMax() {
        double max = elements[0];
        for (int i = 1; i < length; i++) {
            if (elements[i] > max) {
                max = elements[i];
            }
        }
        return max;
    }

    public void sort() {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < length - 1; i++) {
                if (elements[i] < elements[i + 1]) {
                    double temp = elements[i];
                    elements[i] = elements[i + 1];
                    elements[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }



    public double getNorm() {
        double sum = 0;
        for (int i = 0; i < length; i++) {
            sum += elements[i] * elements[i];
        }
        return Math.sqrt(sum);
    }

    public void multiply(double scalar) {
        for (int i = 0; i < length; i++) {
            elements[i] *= scalar;
        }
    }

    public void add(Vector other) {
        if (length != other.getLength()) {
            throw new IllegalArgumentException("Vectors must have same length");
        }
        for (int i = 0; i < length; i++) {
            elements[i] += other.getElement(i);
        }
    }

    public double dotProduct(Vector other) {
        if (length != other.getLength()) {
            throw new IllegalArgumentException("Vectors must have same length");
        }
        double product = 0;
        for (int i = 0; i < length; i++) {
            product += elements[i] * other.getElement(i);
        }
        return product;
    }
}
