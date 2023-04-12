package lab7.logic;

import lab7.exception.IncorrectNumberIntervalException;

public interface NumberGenerator {

    int getMin();
    int getMax();

    int generate(int min, int max) throws IncorrectNumberIntervalException;

}
