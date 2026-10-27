package n4;

import android.graphics.Bitmap;
import h4.InterfaceC1359a;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class u extends e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f19964b = "com.bumptech.glide.load.resource.bitmap.FitCenter".getBytes(e4.e.f14430a);

    @Override // e4.e
    public final void b(MessageDigest messageDigest) {
        messageDigest.update(f19964b);
    }

    @Override // n4.e
    public final Bitmap c(InterfaceC1359a interfaceC1359a, Bitmap bitmap, int i7, int i10) {
        return y.b(interfaceC1359a, bitmap, i7, i10);
    }

    @Override // e4.e
    public final boolean equals(Object obj) {
        return obj instanceof u;
    }

    @Override // e4.e
    public final int hashCode() {
        return 1572326941;
    }
}
