package org.apache.tools.ant.util;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class OutputStreamFunneler {
    public static final long DEFAULT_TIMEOUT_MILLIS = 1000;
    private boolean closed;
    private int count;
    private OutputStream out;
    private long timeoutMillis;

    /* JADX INFO: renamed from: org.apache.tools.ant.util.OutputStreamFunneler$1, reason: invalid class name */
    class AnonymousClass1 {
    }

    static /* synthetic */ int access$004(OutputStreamFunneler x0) {
        int i = x0.count + 1;
        x0.count = i;
        return i;
    }

    private final class Funnel extends OutputStream {
        private boolean closed;

        /* synthetic */ Funnel(OutputStreamFunneler x0, AnonymousClass1 x1) {
            this();
        }

        private Funnel() {
            this.closed = false;
            synchronized (OutputStreamFunneler.this) {
                OutputStreamFunneler.access$004(OutputStreamFunneler.this);
            }
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
            synchronized (OutputStreamFunneler.this) {
                OutputStreamFunneler.this.dieIfClosed();
                OutputStreamFunneler.this.out.flush();
            }
        }

        @Override // java.io.OutputStream
        public void write(int b) throws IOException {
            synchronized (OutputStreamFunneler.this) {
                OutputStreamFunneler.this.dieIfClosed();
                OutputStreamFunneler.this.out.write(b);
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] b) throws IOException {
            synchronized (OutputStreamFunneler.this) {
                OutputStreamFunneler.this.dieIfClosed();
                OutputStreamFunneler.this.out.write(b);
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] b, int off, int len) throws IOException {
            synchronized (OutputStreamFunneler.this) {
                OutputStreamFunneler.this.dieIfClosed();
                OutputStreamFunneler.this.out.write(b, off, len);
            }
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            OutputStreamFunneler.this.release(this);
        }
    }

    public OutputStreamFunneler(OutputStream out) {
        this(out, 1000L);
    }

    public OutputStreamFunneler(OutputStream out, long timeoutMillis) {
        this.count = 0;
        if (out == null) {
            throw new IllegalArgumentException("OutputStreamFunneler.<init>:  out == null");
        }
        this.out = out;
        this.closed = false;
        setTimeout(timeoutMillis);
    }

    public synchronized void setTimeout(long timeoutMillis) {
        this.timeoutMillis = timeoutMillis;
    }

    public synchronized OutputStream getFunnelInstance() throws IOException {
        dieIfClosed();
        try {
        } finally {
            notifyAll();
        }
        return new Funnel(this, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void release(Funnel funnel) throws IOException {
        if (!funnel.closed) {
            try {
                if (this.timeoutMillis > 0) {
                    try {
                        try {
                            wait(this.timeoutMillis);
                        } catch (InterruptedException e) {
                        }
                    } catch (Throwable th) {
                        th = th;
                        funnel.closed = true;
                        throw th;
                    }
                }
                int i = this.count - 1;
                this.count = i;
                if (i == 0) {
                    close();
                }
                funnel.closed = true;
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    private synchronized void close() throws IOException {
        try {
            dieIfClosed();
            this.out.close();
        } finally {
            this.closed = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void dieIfClosed() throws IOException {
        if (this.closed) {
            throw new IOException("The funneled OutputStream has been closed.");
        }
    }
}
