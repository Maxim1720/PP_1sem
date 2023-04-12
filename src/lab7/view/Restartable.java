package lab7.view;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface Restartable {
    void onRestart(Runnable runnable);
}
