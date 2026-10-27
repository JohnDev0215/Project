package u6;

import A7.m;
import B2.C0064d;
import F1.C0254m;
import java.util.Iterator;
import r1.C2273e;
import t6.C2442c;
import t6.C2443d;
import t6.C2445f;
import v6.C2559g;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2445f f22930a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e f22931b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f22932e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C2273e f22933f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final w6.b f22934g;

    public b(C2445f c2445f, e eVar, int i7, int i10, int i11, C2273e c2273e, C2442c c2442c) {
        m.f(c2445f, "kmlPlayer");
        m.f(eVar, "timingConfig");
        this.f22930a = c2445f;
        this.f22931b = eVar;
        this.c = i7;
        this.d = i10;
        this.f22932e = i11;
        this.f22933f = c2273e;
        this.f22934g = new w6.b();
        Iterator it = c2442c.f22478b.iterator();
        while (it.hasNext()) {
            this.f22934g.add(new C2559g((C2443d) it.next()));
        }
    }

    public final boolean a(int i7) {
        int iMin;
        C2273e c2273e = this.f22933f;
        C0064d c0064d = (C0064d) c2273e.f21657h;
        if (c0064d.f556b > 0) {
            iMin = c0064d.c;
        } else {
            int i10 = ((C0254m) c2273e.f21658i).f2343b;
            iMin = i10 > 0 ? Math.min(c2273e.c, i10) : c2273e.c;
        }
        return i7 >= iMin && i7 < c2273e.c();
    }

    public final String toString() {
        return "{Id: " + this.c + ", LineIndex: " + this.f22932e + ", SingerIndex: " + this.d + ", TimeCode: " + this.f22933f + "}";
    }
}
