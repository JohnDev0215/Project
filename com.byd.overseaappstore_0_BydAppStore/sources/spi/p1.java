package spi;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.ByteArrayOutputStream;

/* JADX INFO: loaded from: classes.dex */
public final class p1 implements in<Bitmap, byte[]> {
    public final Bitmap.CompressFormat a;
    public final int b;

    public p1() {
        this(Bitmap.CompressFormat.JPEG, 100);
    }

    public p1(@NonNull Bitmap.CompressFormat compressFormat, int i) {
        this.a = compressFormat;
        this.b = i;
    }

    @Override // spi.in
    @Nullable
    public final wm<byte[]> a(@NonNull wm<Bitmap> wmVar, @NonNull lk lkVar) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        wmVar.get().compress(this.a, this.b, byteArrayOutputStream);
        wmVar.recycle();
        return new v2(byteArrayOutputStream.toByteArray());
    }
}
