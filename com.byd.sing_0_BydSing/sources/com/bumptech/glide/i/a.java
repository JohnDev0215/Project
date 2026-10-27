package com.bumptech.glide.i;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: GifDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public interface a {

    /* JADX INFO: renamed from: com.bumptech.glide.i.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: GifDecoder.java */
    public interface InterfaceC0083a {
        Bitmap a(int i, int i2, Bitmap.Config config);

        void b(byte[] bArr);

        byte[] c(int i);

        void d(int[] iArr);

        int[] e(int i);

        void f(Bitmap bitmap);
    }

    int a();

    Bitmap b();

    void c();

    void clear();

    int d();

    int e();

    int f();

    void g(Bitmap.Config config);

    ByteBuffer h();

    void i();
}
