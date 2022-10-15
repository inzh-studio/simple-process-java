package inzh.lang.value;

/**
 * Reader specification for {@code ObservableValue}.
 *
 * @author Jean-Raffi Nazareth
 * @param <T> Type of observable value
 */
public abstract class ObservableReader<T> implements Runnable {

    protected ObservableValue<T> value;
    protected Exception exception;

    public ObservableReader(ObservableValue<T> value) {
        this.value = value;
    }

    /**
     * Returns the current {@code ObservableValue}
     *
     * @return The current {@code ObservableValue}
     */
    public ObservableValue<T> getValue() {
        return value;
    }

    public Exception getException() {
        return exception;
    }

}
