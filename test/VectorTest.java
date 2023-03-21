import lab2.Vector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VectorTest {

    @Test
    void testGetElement() {
        Vector v = new Vector(3);
        v.setElement(0, 1.0);
        v.setElement(1, 2.0);
        v.setElement(2, 3.0);
        assertEquals(1.0, v.getElement(0));
        assertEquals(2.0, v.getElement(1));
        assertEquals(3.0, v.getElement(2));
    }

    @Test
    void testSetElement() {
        Vector v = new Vector(3);
        v.setElement(0, 1.0);
        v.setElement(1, 2.0);
        v.setElement(2, 3.0);
        double[] elements = v.getElements();
        assertEquals(1.0, elements[0]);
        assertEquals(2.0, elements[1]);
        assertEquals(3.0, elements[2]);
    }

    @Test
    void testGetMin() {
        Vector v = new Vector(3);
        v.setElement(0, 3.0);
        v.setElement(1, 1.0);
        v.setElement(2, 2.0);
        assertEquals(1.0, v.getMin());
    }

    @Test
    void testGetMax() {
        Vector v = new Vector(3);
        v.setElement(0, 3.0);
        v.setElement(1, 1.0);
        v.setElement(2, 2.0);
        assertEquals(3.0, v.getMax());
    }

    @Test
    void testSort() {
        Vector v = new Vector(3);
        v.setElement(0, 1.0);
        v.setElement(1, 2.0);
        v.setElement(2, 3.0);
        v.sort();
        double[] elements = v.getElements();
        assertEquals(3.0, elements[0]);
        assertEquals(2.0, elements[1]);
        assertEquals(1.0, elements[2]);
    }

    @Test
    void testGetNorm() {
        Vector v = new Vector(3);
        v.setElement(0, 1.0);
        v.setElement(1, 2.0);
        v.setElement(2, 2.0);
        assertEquals(Math.sqrt(9), v.getNorm());
    }

    @Test
    void testMultiply() {
        Vector v = new Vector(3);
        v.setElement(0, 1.0);
        v.setElement(1, 2.0);
        v.setElement(2, 2.0);
        v.multiply(2.0);
        double[] elements = v.getElements();
        assertEquals(2.0, elements[0]);
        assertEquals(4.0, elements[1]);
        assertEquals(4.0, elements[2]);
    }

    @Test
    void testAdd() {
        Vector v1 = new Vector(3);
        v1.setElement(0, 1);
        v1.setElement(1, 2);
        v1.setElement(2, 3);

        Vector v2 = new Vector(3);
        v2.setElement(0, 4);
        v2.setElement(1, 5);
        v2.setElement(2, 6);

        Vector expected = new Vector(3);
        expected.setElement(0, 5);
        expected.setElement(1, 7);
        expected.setElement(2, 9);

        v1.add(v2);

        for (int i = 0; i < v1.getLength(); i++) {
            assertEquals(expected.getElement(i), v1.getElement(i), 0.00001);
        }
    }
}
