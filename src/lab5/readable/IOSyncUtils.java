package lab5.readable;

import lab4.InputOutputUtils;
import lab4.readable.ReadableIO;

public class IOSyncUtils extends InputOutputUtils {

    public static ReadableIO synchronizedReadable(ReadableIO readable) {
        return new SynchronizedReadableIO(readable);
    }

}
