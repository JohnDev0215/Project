package spi;

import androidx.annotation.NonNull;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class v2 implements wm<byte[]> {
    public final byte[] a;

    public v2(byte[] bArr) {
        Objects.requireNonNull(bArr, "Argument must not be null");
        this.a = bArr;
    }

    @Override // spi.wm
    public final int a() {
        return this.a.length;
    }

    @Override // spi.wm
    @NonNull
    public final Class<byte[]> c() {
        return byte[].class;
    }

    @Override // spi.wm
    @NonNull
    public final byte[] get() {
        return this.a;
    }

    @Override // spi.wm
    public final void recycle() {
    }
}
