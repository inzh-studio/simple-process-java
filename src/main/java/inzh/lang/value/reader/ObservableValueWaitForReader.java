package inzh.lang.value.reader;

import inzh.lang.value.ObservableReader;
import inzh.lang.value.ObservableValue;

/**
 * Reader for {@code ObservableValue} based on process waitFor status.
 *
 * @author Jean-Raffi Nazareth
 */
public class ObservableValueWaitForReader extends ObservableReader<Boolean> {

    protected Process process;

    public ObservableValueWaitForReader(Process process, ObservableValue<Boolean> value) {
        super(value);
        this.process = process;
    }

    @Override
    public void run() {
        try {
            process.waitFor();
            value.setValue(Boolean.TRUE);
        } catch (InterruptedException ex) {
            super.exception = ex;
        }
    }
}
