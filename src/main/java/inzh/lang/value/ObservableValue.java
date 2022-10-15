package inzh.lang.value;

import java.util.ArrayList;
import java.util.List;

/**
 * An {@code ObservableValue} is an entity that wraps a value and allows to
 * observe the value for changes. This implementation is builded for
 * SimpleProcess usage, but it is generic.
 *
 * @author Jean-Raffi Nazareth
 * @param <T> Type of observable value
 */
public class ObservableValue<T extends Object> {

    protected T value;
    protected List<ChangeListener<T>> listeners = new ArrayList<>();

    public ObservableValue() {
    }

    public ObservableValue(T value) {
        this.value = value;
    }

    /**
     * Adds a {@link ChangeListener} which will be notified whenever the value
     * of the {@code ObservableValue} changes.
     *
     * @see #removeListener(ChangeListener)
     *
     * @param listener The listener to register
     */
    public void addListener(ChangeListener<T> listener) {
        listeners.add(listener);
    }

    /**
     * Removes the given listener from the list of listeners that are notified
     * whenever the value of the {@code ObservableValue} changes.
     *
     * @see #addListener(ChangeListener)
     *
     * @param listener The listener to remove
     */
    public void removeListener(ChangeListener<T> listener) {
        listeners.remove(listener);
    }

    /**
     * Set the wrapped value.
     *
     * @param value The new value
     * @return The current {@code ObservableValue}
     */
    public ObservableValue<T> setValue(T value) {
        T pValue = this.value;
        this.value = value;

        for (ChangeListener<T> l : listeners) {
            l.changed(this, pValue, value);
        }

        return this;
    }

    /**
     * Get the wrapped value of this {@code ObservableValue}.
     *
     * @return The current value
     */
    public T getValue() {
        return value;
    }
}
