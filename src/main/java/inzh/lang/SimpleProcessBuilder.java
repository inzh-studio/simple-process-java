package inzh.lang;

import inzh.lang.value.ChangeListener;
import inzh.lang.value.ObservableValue;
import java.io.IOException;

/**
 * SimpleProcessBuilder class, easy way for build SimpleProcess.
 *
 * @author Jean-Raffi Nazareth
 */
public class SimpleProcessBuilder {

    protected ProcessBuilder processBuilder;
    protected ObservableValue<String> outputRowValue;
    protected ObservableValue<String> errorRowValue;
    protected ObservableValue<Boolean> finishedValue;
    protected ObservableValue<String> inputValue;
    protected int returnOkCode = 0;

    protected boolean builded = false;

    public SimpleProcessBuilder processBuilder(ProcessBuilder processBuilder) {
        this.processBuilder = processBuilder;
        return this;
    }

    /**
     * Add row listener on the normal output of the builded process, with a
     * observable value. Each data is separated with '\n' by reader.
     *
     * @param listener Listener added on this output
     * @return SimpleProcessBuilder
     */
    public SimpleProcessBuilder outputRowListener(ChangeListener<String> listener) {
        if (listener != null) {
            outputRowValue = outputRowValue == null ? new ObservableValue<>() : outputRowValue;
            outputRowValue.addListener(listener);
        }
        return this;
    }

    /**
     * Add row listener on the error output of the builded process, with a
     * observable value. Each data is separated with '\n' by reader.
     *
     * @param listener Listener added on this output
     * @return SimpleProcessBuilder
     */
    public SimpleProcessBuilder errorRowListener(ChangeListener<String> listener) {
        if (listener != null) {
            errorRowValue = errorRowValue == null ? new ObservableValue<>() : errorRowValue;
            errorRowValue.addListener(listener);
        }
        return this;
    }

    /**
     * Add row listener on finished status of the builded process, with a
     * observable value.
     *
     * @param listener Listener added on this trigger
     * @return SimpleProcessBuilder
     */
    public SimpleProcessBuilder finishedListener(ChangeListener<Boolean> listener) {
        if (listener != null) {
            finishedValue = finishedValue == null ? new ObservableValue<>(Boolean.FALSE) : finishedValue;
            finishedValue.addListener(listener);
        }
        return this;
    }

    public SimpleProcessBuilder inputValue(ObservableValue<String> ov) {
        inputValue = ov;
        return this;
    }

    public SimpleProcessBuilder returnOkCode(int returnOkCode) {
        this.returnOkCode = returnOkCode;
        return this;
    }

    public SimpleProcess build() throws IOException {
        if (builded) {
            throw new IOException("Builder already used");
        }
        builded = true;

        SimpleProcess p = new SimpleProcess(processBuilder, outputRowValue, errorRowValue, finishedValue, inputValue);
        return p;
    }
}
