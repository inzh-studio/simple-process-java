package inzh.lang;

import inzh.lang.value.ObservableReader;
import inzh.lang.value.ObservableValue;
import inzh.lang.value.ObservableWriter;
import inzh.lang.value.reader.ObservableValueReader;
import inzh.lang.value.reader.ObservableValueWaitForReader;
import inzh.lang.value.writer.ObservablePrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * SimpleProcess class, created with SimpleProcessBuilder, for embedded native
 * process usage and simplify io.
 *
 * @author Jean-Raffi Nazareth
 */
public class SimpleProcess {

    protected Process process;

    protected ProcessBuilder processBuilder;

    protected ObservableValue<String> outputRowValue;
    protected ObservableValue<String> errorRowValue;
    protected ObservableValue<Boolean> finishedValue;
    protected ObservableValue<String> inputValue;

    protected List<Runnable> rs;

    protected SimpleProcess(ProcessBuilder processBuilder,
            ObservableValue<String> outputRowValue,
            ObservableValue<String> errorRowValue,
            ObservableValue<Boolean> finishedValue,
            ObservableValue<String> inputValue) {
        this.processBuilder = processBuilder;
        this.outputRowValue = outputRowValue;
        this.errorRowValue = errorRowValue;
        this.finishedValue = finishedValue;
        this.inputValue = inputValue;
    }

    public List<String> command() {
        return processBuilder.command();
    }

    public Process process() {
        return process;
    }

    public ObservableValue<String> outputRowValue() {
        return outputRowValue;
    }

    public ObservableValue<String> errorRowValue() {
        return errorRowValue;
    }

    public ObservableValue<Boolean> finishedValue() {
        return finishedValue;
    }

    protected void astart(Runnable or) {
        if (rs == null) {
            rs = new ArrayList<>();
        }
        rs.add(or);
        new Thread(or).start();
    }

    /**
     * Start a new process using the attributes of native process builder and
     * {@code ObservableValue}s specified.This method wait end of current
     * process.
     *
     * @throws IOException if an I/O error occurs
     */
    public void start() throws IOException {
        try {
            startAsync();
            process.waitFor();
        } catch (InterruptedException ex) {
            throw new IOException(ex);
        }
    }

    /**
     * Start a new process using the attributes of native process builder and
     * {@code ObservableValue}s specified.
     *
     * @throws IOException if an I/O error occurs
     */
    public void startAsync() throws IOException {
        process = processBuilder.start();

        if (outputRowValue != null) {
            astart(new ObservableValueReader(process.getInputStream(), outputRowValue));
        }

        if (errorRowValue != null) {
            astart(new ObservableValueReader(process.getErrorStream(), errorRowValue));
        }

        if (finishedValue != null) {
            astart(new ObservableValueWaitForReader(process, finishedValue));
        }

        if (inputValue != null) {
            astart(new ObservablePrintWriter(process, inputValue));
        }
    }

    /**
     * Get all exceptions throwed by Reader or Printer of current process.
     *
     * @return List of exceptions.
     */
    public List<Exception> getExceptions() {
        List<Exception> exs = new ArrayList<>();
        for (Runnable r : rs) {
            Exception ex = null;
            if (r instanceof ObservableReader) {
                ex = ((ObservableReader) r).getException();
            }
            if (r instanceof ObservableWriter) {
                ex = ((ObservableWriter) r).getException();
            }
            if (ex != null) {
                exs.add(ex);
            }
        }
        return exs;
    }

    public <T extends Exception> T toTargetException(Class<T> exceptionTarget) throws Exception {
        List<Exception> exs = getExceptions();
        if (exs.isEmpty()) {
            return null;
        }
        T ex = exceptionTarget.getConstructor(exs.getClass()).newInstance(exs);
        return ex;
    }

    /**
     * Throw exception contains all exceptions throwed by Reader or Printer of
     * current process.
     *
     * @param <T> The type of the exception format.
     * @param exceptionTarget External exception class
     *
     * @throws java.lang.Exception Exception on create new target exception.
     */
    public <T extends Exception> void throwExceptionAsNeed(Class<T> exceptionTarget) throws Exception {
        T ex = toTargetException(exceptionTarget);
        if (ex != null) {
            throw ex;
        }
    }
}
