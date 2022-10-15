package inzh.lang.value.reader;

import inzh.lang.value.ObservableReader;
import inzh.lang.value.ObservableValue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Reader for {@code ObservableValue} based on input stream.
 *
 * @author Jean-Raffi Nazareth
 */
public class ObservableValueReader extends ObservableReader<String> {

    protected InputStream is;

    public ObservableValueReader(InputStream is, ObservableValue<String> value) {
        super(value);
        this.is = is;
    }

    @Override
    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.defaultCharset()));
        try {
            String line;
            while ((line = br.readLine()) != null) {
                value.setValue(line);
            }
        } catch (IOException ex) {
            super.exception = ex;
        }
    }
}
