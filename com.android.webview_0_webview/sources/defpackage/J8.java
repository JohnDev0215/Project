package defpackage;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class J8 extends OutputStream {
    public final /* synthetic */ Executor s;
    public final /* synthetic */ OutputStream t;

    public J8(K8 k8, Executor executor, OutputStream outputStream) {
        this.s = executor;
        this.t = outputStream;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Executor executor = this.s;
        final OutputStream outputStream = this.t;
        executor.execute(new Runnable(outputStream) { // from class: I8
            public final OutputStream s;

            {
                this.s = outputStream;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    this.s.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
    }

    @Override // java.io.OutputStream
    public void write(int i) {
    }

    @Override // java.io.OutputStream
    public void write(final byte[] bArr) {
        Executor executor = this.s;
        final OutputStream outputStream = this.t;
        executor.execute(new Runnable(outputStream, bArr) { // from class: H8
            public final OutputStream s;
            public final byte[] t;

            {
                this.s = outputStream;
                this.t = bArr;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    this.s.write(this.t);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
    }
}
