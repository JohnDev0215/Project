package x1;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: ByteBufferBitmapImageDecoderResourceDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class h implements n1.k<ByteBuffer, Bitmap> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d f9756a = new d();

    @Override // n1.k
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public q1.v<Bitmap> b(ByteBuffer byteBuffer, int i7, int i8, n1.i iVar) {
        return this.f9756a.b(ImageDecoder.createSource(byteBuffer), i7, i8, iVar);
    }

    @Override // n1.k
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean a(ByteBuffer byteBuffer, n1.i iVar) {
        return true;
    }
}
