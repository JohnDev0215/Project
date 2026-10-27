package org.apache.commons.net.finger;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.net.SocketClient;
import org.apache.commons.net.util.Charsets;

/* JADX INFO: loaded from: classes3.dex */
public class FingerClient extends SocketClient {
    public static final int DEFAULT_PORT = 79;
    private static final String LONG_FLAG = "/W ";
    private transient char[] buffer = new char[1024];

    public FingerClient() {
        setDefaultPort(79);
    }

    public String query(boolean longOutput, String username) throws IOException {
        StringBuilder result = new StringBuilder(this.buffer.length);
        BufferedReader input = new BufferedReader(new InputStreamReader(getInputStream(longOutput, username), getCharset()));
        while (true) {
            try {
                int read = input.read(this.buffer, 0, this.buffer.length);
                if (read > 0) {
                    result.append(this.buffer, 0, read);
                } else {
                    input.close();
                    return result.toString();
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        input.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
    }

    public String query(boolean longOutput) throws IOException {
        return query(longOutput, "");
    }

    public InputStream getInputStream(boolean longOutput, String username) throws IOException {
        return getInputStream(longOutput, username, null);
    }

    public InputStream getInputStream(boolean longOutput, String username, String encoding) throws IOException {
        StringBuilder buffer = new StringBuilder(64);
        if (longOutput) {
            buffer.append(LONG_FLAG);
        }
        buffer.append(username);
        buffer.append(SocketClient.NETASCII_EOL);
        byte[] encodedQuery = buffer.toString().getBytes(Charsets.toCharset(encoding).name());
        DataOutputStream output = new DataOutputStream(new BufferedOutputStream(this._output_, 1024));
        output.write(encodedQuery, 0, encodedQuery.length);
        output.flush();
        return this._input_;
    }

    public InputStream getInputStream(boolean longOutput) throws IOException {
        return getInputStream(longOutput, "");
    }
}
