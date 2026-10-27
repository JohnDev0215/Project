package spi;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class is implements bn<InputStream, GifDrawable> {
    public final List<ImageHeaderParser> a;
    public final bn<ByteBuffer, GifDrawable> b;
    public final m0 c;

    public is(List<ImageHeaderParser> list, bn<ByteBuffer, GifDrawable> bnVar, m0 m0Var) {
        this.a = list;
        this.b = bnVar;
        this.c = m0Var;
    }

    @Override // spi.bn
    public final boolean a(@NonNull InputStream inputStream, @NonNull lk lkVar) throws IOException {
        return !((Boolean) lkVar.c(kd.b)).booleanValue() && com.bumptech.glide.load.a.c(this.a, inputStream, this.c) == ImageHeaderParser.ImageType.GIF;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    @Override // spi.bn
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final spi.wm<com.bumptech.glide.load.resource.gif.GifDrawable> b(@androidx.annotation.NonNull java.io.InputStream r6, int r7, int r8, @androidx.annotation.NonNull spi.lk r9) throws java.io.IOException {
        /*
            r5 = this;
            java.io.InputStream r6 = (java.io.InputStream) r6
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r1 = 16384(0x4000, float:2.2959E-41)
            r0.<init>(r1)
            r2 = 0
            byte[] r1 = new byte[r1]     // Catch: java.io.IOException -> L20
        Lc:
            int r3 = r6.read(r1)     // Catch: java.io.IOException -> L20
            r4 = -1
            if (r3 == r4) goto L18
            r4 = 0
            r0.write(r1, r4, r3)     // Catch: java.io.IOException -> L20
            goto Lc
        L18:
            r0.flush()     // Catch: java.io.IOException -> L20
            byte[] r6 = r0.toByteArray()
            goto L30
        L20:
            r6 = move-exception
            r0 = 5
            java.lang.String r1 = "StreamGifDecoder"
            boolean r0 = android.util.Log.isLoggable(r1, r0)
            if (r0 == 0) goto L2f
            java.lang.String r0 = "Error reading data from stream"
            android.util.Log.w(r1, r0, r6)
        L2f:
            r6 = r2
        L30:
            if (r6 != 0) goto L33
            goto L3d
        L33:
            java.nio.ByteBuffer r6 = java.nio.ByteBuffer.wrap(r6)
            spi.bn<java.nio.ByteBuffer, com.bumptech.glide.load.resource.gif.GifDrawable> r0 = r5.b
            spi.wm r2 = r0.b(r6, r7, r8, r9)
        L3d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.is.b(java.lang.Object, int, int, spi.lk):spi.wm");
    }
}
