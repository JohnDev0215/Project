package org.apache.http.impl.conn;

import com.amplifyframework.core.model.ModelIdentifier;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.logging.Log;
import org.apache.http.util.Args;

/* JADX INFO: loaded from: classes7.dex */
public class Wire {
    private final String id;
    private final Log log;

    public Wire(Log log, String str) {
        this.log = log;
        this.id = str;
    }

    public Wire(Log log) {
        this(log, "");
    }

    private void wire(String str, InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i = inputStream.read();
            if (i == -1) {
                break;
            }
            if (i == 13) {
                sb.append("[\\r]");
            } else if (i == 10) {
                sb.append("[\\n]\"");
                sb.insert(0, ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR);
                sb.insert(0, str);
                this.log.debug(this.id + " " + sb.toString());
                sb.setLength(0);
            } else if (i < 32 || i > 127) {
                sb.append("[0x");
                sb.append(Integer.toHexString(i));
                sb.append("]");
            } else {
                sb.append((char) i);
            }
        }
        if (sb.length() > 0) {
            sb.append('\"');
            sb.insert(0, '\"');
            sb.insert(0, str);
            this.log.debug(this.id + " " + sb.toString());
        }
    }

    public boolean enabled() {
        return this.log.isDebugEnabled();
    }

    public void output(byte[] bArr, int i, int i2) throws IOException {
        Args.notNull(bArr, "Output");
        wire(">> ", new ByteArrayInputStream(bArr, i, i2));
    }

    public void input(byte[] bArr, int i, int i2) throws IOException {
        Args.notNull(bArr, "Input");
        wire("<< ", new ByteArrayInputStream(bArr, i, i2));
    }

    public void output(byte[] bArr) throws IOException {
        Args.notNull(bArr, "Output");
        wire(">> ", new ByteArrayInputStream(bArr));
    }

    public void input(byte[] bArr) throws IOException {
        Args.notNull(bArr, "Input");
        wire("<< ", new ByteArrayInputStream(bArr));
    }

    public void output(int i) throws IOException {
        output(new byte[]{(byte) i});
    }

    public void input(int i) throws IOException {
        input(new byte[]{(byte) i});
    }
}
