package x1;

import android.graphics.Bitmap;
import java.security.MessageDigest;

/* JADX INFO: compiled from: FitCenter.java */
/* JADX INFO: loaded from: classes.dex */
public class q extends f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final byte[] f9791b = "com.bumptech.glide.load.resource.bitmap.FitCenter".getBytes(n1.f.f8504a);

    @Override // n1.f
    public void a(MessageDigest messageDigest) {
        messageDigest.update(f9791b);
    }

    @Override // x1.f
    protected Bitmap c(r1.d dVar, Bitmap bitmap, int i7, int i8) {
        return y.e(dVar, bitmap, i7, i8);
    }

    @Override // n1.f
    public boolean equals(Object obj) {
        return obj instanceof q;
    }

    @Override // n1.f
    public int hashCode() {
        return 1572326941;
    }
}
