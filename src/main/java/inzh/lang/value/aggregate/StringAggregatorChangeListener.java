package inzh.lang.value.aggregate;

import inzh.lang.value.ChangeListener;
import inzh.lang.value.ObservableValue;

/**
 * Aggregator implementation for String {@code ChangeListener}.
 *
 * @author Jean-Raffi Nazareth
 */
public class StringAggregatorChangeListener implements ChangeListener<String> {

    protected StringBuilder builder = new StringBuilder();

    @Override
    public void changed(ObservableValue<? extends String> ov, String pValue, String nValue) {
        builder.append(nValue);
    }

    public String getValue() {
        return builder.toString();
    }
}
