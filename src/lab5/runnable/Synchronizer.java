package lab5.runnable;

import lab4.readable.ReadableIO;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Synchronizer<R extends ReadableIO> {
    private boolean isWriteTurn = true;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public void waitForReadTurn() {
        lock.lock();
        try {
            while (isWriteTurn) {
                condition.await();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void waitForWriteTurn() {
        lock.lock();
        try {
            while (!isWriteTurn) {
                condition.await();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void notifyReadTurn() {
        lock.lock();
        try {
            isWriteTurn = true;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void notifyWriteTurn() {
        lock.lock();
        try {
            isWriteTurn = false;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}

