package defpackage;

import android.graphics.Bitmap;
import android.graphics.Color;
import java.nio.ByteBuffer;

/* JADX INFO: renamed from: Ij, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0153Ij {
    public C0135Hj a = new C0135Hj();
    public ByteBuffer b = null;
    public Bitmap c = null;

    public C0153Ij(AbstractC0028Bk abstractC0028Bk) {
    }

    public ByteBuffer a() {
        Bitmap bitmap = this.c;
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = this.c.getHeight();
        int i = width * height;
        this.c.getPixels(new int[i], 0, width, 0, 0, width, height);
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) ((Color.blue(r9[i2]) * 0.114f) + (Color.green(r9[i2]) * 0.587f) + (Color.red(r9[i2]) * 0.299f));
        }
        return ByteBuffer.wrap(bArr);
    }
}
