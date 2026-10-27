package com.bumptech.glide.k;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: GifDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public interface a {

    /* JADX INFO: renamed from: com.bumptech.glide.k.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: GifDecoder.java */
    public interface InterfaceC0060a {
        int[] a(int i);

        Bitmap b(int i, int i2, Bitmap.Config config);

        void c(byte[] bArr);

        void d(Bitmap bitmap);

        byte[] e(int i);

        void f(int[] iArr);
    }

    int a();

    int b();

    void c(Bitmap.Config config);

    void clear();

    Bitmap d();

    void e();

    int f();

    int g();

    ByteBuffer h();

    void i();
}
