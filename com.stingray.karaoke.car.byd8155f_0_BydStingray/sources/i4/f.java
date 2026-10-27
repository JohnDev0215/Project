package i4;

import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class f implements B4.b {
    public final MessageDigest l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final B4.e f15753m = new B4.e();

    public f(MessageDigest messageDigest) {
        this.l = messageDigest;
    }

    @Override // B4.b
    public final B4.e c() {
        return this.f15753m;
    }
}
