package x1;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* JADX INFO: compiled from: RoundedCorners.java */
/* JADX INFO: loaded from: classes.dex */
public final class w extends f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final byte[] f9810c = "com.bumptech.glide.load.resource.bitmap.RoundedCorners".getBytes(n1.f.f8504a);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f9811b;

    public w(int i7) {
        k2.j.a(i7 > 0, "roundingRadius must be greater than 0.");
        this.f9811b = i7;
    }

    @Override // n1.f
    public void a(MessageDigest messageDigest) {
        messageDigest.update(f9810c);
        messageDigest.update(ByteBuffer.allocate(4).putInt(this.f9811b).array());
    }

    @Override // x1.f
    protected Bitmap c(r1.d dVar, Bitmap bitmap, int i7, int i8) {
        return y.n(dVar, bitmap, this.f9811b);
    }

    @Override // n1.f
    public boolean equals(Object obj) {
        return (obj instanceof w) && this.f9811b == ((w) obj).f9811b;
    }

    @Override // n1.f
    public int hashCode() {
        return k2.k.m(-569625254, k2.k.l(this.f9811b));
    }
}
