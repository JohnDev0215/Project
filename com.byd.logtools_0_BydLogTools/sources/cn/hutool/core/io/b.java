package cn.hutool.core.io;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.function.Supplier;

/* JADX INFO: compiled from: FastByteArrayOutputStream.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends OutputStream {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c f2917b;

    public b() {
        this(1024);
    }

    public byte[] a() {
        return this.f2917b.d();
    }

    public String b(Charset charset) {
        return new String(a(), (Charset) cn.hutool.core.util.f.a(charset, new Supplier() { // from class: cn.hutool.core.io.a
            @Override // java.util.function.Supplier
            public final Object get() {
                return cn.hutool.core.util.c.b();
            }
        }));
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public String toString() {
        return b(cn.hutool.core.util.c.b());
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        this.f2917b.b(bArr, i, i2);
    }

    public b(int i) {
        this.f2917b = new c(i);
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        this.f2917b.a((byte) i);
    }
}
