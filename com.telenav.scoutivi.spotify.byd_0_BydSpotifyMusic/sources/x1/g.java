package x1;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: ByteBufferBitmapDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements n1.k<ByteBuffer, Bitmap> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final m f9755a;

    public g(m mVar) {
        this.f9755a = mVar;
    }

    @Override // n1.k
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public q1.v<Bitmap> b(ByteBuffer byteBuffer, int i7, int i8, n1.i iVar) {
        return this.f9755a.d(k2.a.f(byteBuffer), i7, i8, iVar);
    }

    @Override // n1.k
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean a(ByteBuffer byteBuffer, n1.i iVar) {
        return this.f9755a.n(byteBuffer);
    }
}
