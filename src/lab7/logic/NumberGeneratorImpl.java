package lab7.logic;

import lab7.exception.IncorrectNumberIntervalException;

public class NumberGeneratorImpl implements NumberGenerator{

    private int min;

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    private int max;


    @Override
    public int generate(int min, int max) throws IncorrectNumberIntervalException {

        this.min = min;
        this.max = max;

        if(this.min>=this.max){
            throw new IncorrectNumberIntervalException("min must be lower than max");
        }
        return (max+min)/2;
    }
}
