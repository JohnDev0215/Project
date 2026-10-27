package g.a.b.j0;

import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f5052a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5053b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f5054c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f5055d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final SecureRandom f5056e;

    public q(int i2, int i3, int i4, SecureRandom secureRandom) {
        this(i2, i3, i4, secureRandom, -1);
    }

    public q(int i2, int i3, int i4, SecureRandom secureRandom, int i5) {
        this.f5052a = i2;
        this.f5053b = i3;
        this.f5055d = i4;
        this.f5054c = i5;
        this.f5056e = secureRandom;
    }

    public int a() {
        return this.f5055d;
    }

    public int b() {
        return this.f5052a;
    }

    public int c() {
        return this.f5053b;
    }

    public SecureRandom d() {
        return this.f5056e;
    }

    public int e() {
        return this.f5054c;
    }
}
