package c2;

import java.io.Closeable;

/* JADX INFO: compiled from: JSONReader.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements Closeable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final f2.a f2302d;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f2302d.close();
    }
}
