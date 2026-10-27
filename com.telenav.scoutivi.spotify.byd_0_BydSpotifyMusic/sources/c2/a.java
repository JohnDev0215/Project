package c2;

import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;
import n1.i;
import q1.v;

/* JADX INFO: compiled from: BitmapBytesTranscoder.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements e<Bitmap, byte[]> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Bitmap.CompressFormat f3575a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f3576b;

    public a() {
        this(Bitmap.CompressFormat.JPEG, 100);
    }

    @Override // c2.e
    public v<byte[]> a(v<Bitmap> vVar, i iVar) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        vVar.get().compress(this.f3575a, this.f3576b, byteArrayOutputStream);
        vVar.a();
        return new y1.b(byteArrayOutputStream.toByteArray());
    }

    public a(Bitmap.CompressFormat compressFormat, int i7) {
        this.f3575a = compressFormat;
        this.f3576b = i7;
    }
}
