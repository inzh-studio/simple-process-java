package inzh.lang.value.system;

import inzh.lang.value.ChangeListener;
import inzh.lang.value.ObservableValue;

/**
 * Implementation of ChangeListener for print new value in System.err.
 *
 * @author Jean-Raffi Nazareth
 * @param <T> Type of observable value
 */
public class ErrChangeListener<T> implements ChangeListener<T> {

    @Override
    public void changed(ObservableValue<? extends T> ov, T pValue, T nValue) {
        System.err.println(nValue);
    }
}
