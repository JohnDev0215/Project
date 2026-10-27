package v6;

import A7.m;
import U5.i;
import n7.AbstractC1998m;
import t6.C2443d;

/* JADX INFO: renamed from: v6.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2559g implements w6.d {
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f23035m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final w6.b f23036n;

    public C2559g(C2443d c2443d) {
        m.f(c2443d, "lyric");
        this.l = c2443d.f22480b;
        this.f23035m = c2443d.f22479a;
        this.f23036n = new w6.b();
        int length = c2443d.c.length();
        for (int i7 = 0; i7 < length; i7++) {
            w6.b bVar = this.f23036n;
            String str = c2443d.c;
            bVar.add(new C2556d(str.charAt(i7), this.f23035m + ((this.l * i7) / str.length())));
        }
        this.f23036n.l();
    }

    public final String a() {
        return AbstractC1998m.f0(this.f23036n, "", null, null, new i(12), 30);
    }

    @Override // w6.d
    public final boolean d() {
        return this.f23036n.d();
    }

    @Override // w6.d
    public final void l() {
        this.f23036n.l();
    }
}
