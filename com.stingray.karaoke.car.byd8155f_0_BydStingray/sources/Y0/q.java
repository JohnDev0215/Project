package y0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f24077a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f24078b;
    public final long c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f24079e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f24080f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f24081g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f24082h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f24083i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final long f24084j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final ArrayList f24085k;
    public final long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C2725c f24086m;

    public q(long j3, long j10, long j11, boolean z6, float f10, long j12, long j13, boolean z10, boolean z11, int i7, long j14) {
        this.f24077a = j3;
        this.f24078b = j10;
        this.c = j11;
        this.d = z6;
        this.f24079e = f10;
        this.f24080f = j12;
        this.f24081g = j13;
        this.f24082h = z10;
        this.f24083i = i7;
        this.f24084j = j14;
        this.l = 0L;
        C2725c c2725c = new C2725c();
        c2725c.f24050a = z11;
        c2725c.f24051b = z11;
        this.f24086m = c2725c;
    }

    public final void a() {
        C2725c c2725c = this.f24086m;
        c2725c.f24051b = true;
        c2725c.f24050a = true;
    }

    public final boolean b() {
        C2725c c2725c = this.f24086m;
        return c2725c.f24051b || c2725c.f24050a;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("PointerInputChange(id=");
        sb.append((Object) C2738p.b(this.f24077a));
        sb.append(", uptimeMillis=");
        sb.append(this.f24078b);
        sb.append(", position=");
        sb.append((Object) m0.c.j(this.c));
        sb.append(", pressed=");
        sb.append(this.d);
        sb.append(", pressure=");
        sb.append(this.f24079e);
        sb.append(", previousUptimeMillis=");
        sb.append(this.f24080f);
        sb.append(", previousPosition=");
        sb.append((Object) m0.c.j(this.f24081g));
        sb.append(", previousPressed=");
        sb.append(this.f24082h);
        sb.append(", isConsumed=");
        sb.append(b());
        sb.append(", type=");
        int i7 = this.f24083i;
        sb.append((Object) (i7 != 1 ? i7 != 2 ? i7 != 3 ? i7 != 4 ? "Unknown" : "Eraser" : "Stylus" : "Mouse" : "Touch"));
        sb.append(", historical=");
        Object obj = this.f24085k;
        if (obj == null) {
            obj = n7.u.l;
        }
        sb.append(obj);
        sb.append(",scrollDelta=");
        sb.append((Object) m0.c.j(this.f24084j));
        sb.append(')');
        return sb.toString();
    }

    public q(long j3, long j10, long j11, boolean z6, float f10, long j12, long j13, boolean z10, int i7, ArrayList arrayList, long j14, long j15) {
        this(j3, j10, j11, z6, f10, j12, j13, z10, false, i7, j14);
        this.f24085k = arrayList;
        this.l = j15;
    }
}
