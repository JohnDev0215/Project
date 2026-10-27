package org.apache.commons.net.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/* JADX INFO: loaded from: classes.dex */
public class SocketInputStream extends FilterInputStream {
    private final Socket socket;

    public SocketInputStream(Socket socket, InputStream stream) {
        super(stream);
        this.socket = socket;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        this.socket.close();
    }
}
