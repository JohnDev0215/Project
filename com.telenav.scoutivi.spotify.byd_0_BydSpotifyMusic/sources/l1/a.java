package l1;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: GifDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public interface a {

    /* JADX INFO: renamed from: l1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: GifDecoder.java */
    public interface InterfaceC0114a {
        int[] a(int i7);

        Bitmap b(int i7, int i8, Bitmap.Config config);

        void c(byte[] bArr);

        void d(Bitmap bitmap);

        byte[] e(int i7);

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
