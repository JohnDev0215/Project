package c2;

import h2.d1;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

/* JADX INFO: compiled from: JSONWriter.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements Closeable, Flushable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public d1 f2303d;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f2303d.close();
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        this.f2303d.flush();
    }
}
