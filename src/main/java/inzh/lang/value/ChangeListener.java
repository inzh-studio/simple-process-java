package inzh.lang.value;

/**
 * {@code ChangeListener}, for listen by custom code {@link ObservableValue} changes.
 *
 * @author Jean-Raffi Nazareth
 * @param <T> Type of observable value
 * 
 * @see ObservableValue
 */
public interface ChangeListener<T extends Object> {

    /**
     * Called when the value of an {@link ObservableValue} changes.
     *
     * @param ov
     *            The {@code ObservableValue} which value changed
     * @param pValue
     *            The previous value
     * @param nValue
     *            The new value
     */
    public void changed(ObservableValue<? extends T> ov, T pValue, T nValue);
}
