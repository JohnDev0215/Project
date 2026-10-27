package x1;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import java.io.InputStream;

/* JADX INFO: compiled from: InputStreamBitmapImageDecoderResourceDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class s implements n1.k<InputStream, Bitmap> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d f9799a = new d();

    @Override // n1.k
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public q1.v<Bitmap> b(InputStream inputStream, int i7, int i8, n1.i iVar) {
        return this.f9799a.b(ImageDecoder.createSource(k2.a.b(inputStream)), i7, i8, iVar);
    }

    @Override // n1.k
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean a(InputStream inputStream, n1.i iVar) {
        return true;
    }
}
