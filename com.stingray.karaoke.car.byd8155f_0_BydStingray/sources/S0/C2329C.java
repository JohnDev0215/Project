package s0;

import T.C0735d;
import T.C0738e0;
import T.S;
import n0.C1883l;

/* JADX INFO: renamed from: s0.C, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2329C extends AbstractC2327A {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2335b f21957b;
    public String c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final io.sentry.android.core.internal.util.d f21958e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public A7.o f21959f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C0738e0 f21960g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public C1883l f21961h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final C0738e0 f21962i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public long f21963j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f21964k;
    public float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C2328B f21965m;

    public C2329C(C2335b c2335b) {
        this.f21957b = c2335b;
        c2335b.f21994i = new C2328B(this, 0);
        this.c = "";
        this.d = true;
        this.f21958e = new io.sentry.android.core.internal.util.d();
        this.f21959f = C2338e.o;
        S s6 = S.f8224q;
        this.f21960g = C0735d.O(null, s6);
        this.f21962i = C0735d.O(new m0.f(0L), s6);
        this.f21963j = 9205357640488583168L;
        this.f21964k = 1.0f;
        this.l = 1.0f;
        this.f21965m = new C2328B(this, 1);
    }

    @Override // s0.AbstractC2327A
    public final void a(p0.d dVar) {
        e(dVar, 1.0f, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00f2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e(p0.d r29, float r30, n0.C1883l r31) {
        /*
            Method dump skipped, instruction units count: 402
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: s0.C2329C.e(p0.d, float, n0.l):void");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Params: \tname: ");
        sb.append(this.c);
        sb.append("\n\tviewportWidth: ");
        C0738e0 c0738e0 = this.f21962i;
        sb.append(m0.f.d(((m0.f) c0738e0.getValue()).f18890a));
        sb.append("\n\tviewportHeight: ");
        sb.append(m0.f.b(((m0.f) c0738e0.getValue()).f18890a));
        sb.append("\n");
        String string = sb.toString();
        A7.m.e(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
