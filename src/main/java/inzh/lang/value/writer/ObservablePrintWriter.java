package inzh.lang.value.writer;

import inzh.lang.value.ObservableValue;
import inzh.lang.value.ObservableWriter;
import java.io.PrintWriter;

/**
 * PrintWriter for Observable value.
 *
 * @author Jean-Raffi Nazareth
 */
public class ObservablePrintWriter extends ObservableWriter<String> {

    private final Process process;

    public ObservablePrintWriter(Process process, ObservableValue<String> value) {
        super(value);
        this.process = process;
    }

    @Override
    public void run() {
        PrintWriter pw = new PrintWriter(process.getOutputStream(), true);
        getValue().addListener((ObservableValue<? extends String> ov, String pValue, String nValue) -> {
            if (process.isAlive()) {
                pw.println(nValue);
            }
        });
    }
}
