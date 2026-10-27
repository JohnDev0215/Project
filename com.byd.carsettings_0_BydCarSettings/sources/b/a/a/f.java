package b.a.a;

import b.a.a.k.d1;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

/* JADX INFO: compiled from: JSONWriter.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements Closeable, Flushable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d1 f180a;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f180a.close();
    }

    @Override // java.io.Flushable
    public void flush() throws IOException {
        this.f180a.flush();
    }
}
