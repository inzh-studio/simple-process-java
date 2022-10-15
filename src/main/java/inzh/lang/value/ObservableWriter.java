package inzh.lang.value;

/**
 * Writer specification for {@code ObservableValue}.
 *
 * @author Jean-Raffi Nazareth
 * @param <T> Type of observable value
 */
public abstract class ObservableWriter<T> implements Runnable {

    protected ObservableValue<T> value;
    protected Exception exception;

    public ObservableWriter(ObservableValue<T> value) {
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
