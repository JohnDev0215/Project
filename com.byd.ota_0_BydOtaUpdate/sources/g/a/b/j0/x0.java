package g.a.b.j0;

import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class x0 implements g.a.b.i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public SecureRandom f5081c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.b.i f5082d;

    public x0(g.a.b.i iVar, SecureRandom secureRandom) {
        this.f5081c = secureRandom;
        this.f5082d = iVar;
    }

    public g.a.b.i a() {
        return this.f5082d;
    }

    public SecureRandom b() {
        return this.f5081c;
    }
}
