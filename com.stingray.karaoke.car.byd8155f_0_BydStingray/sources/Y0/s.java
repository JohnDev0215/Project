package y0;

import h2.AbstractC1347d;
import java.util.ArrayList;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f24089a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f24090b;
    public final long c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f24091e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f24092f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f24093g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f24094h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f24095i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final long f24096j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final long f24097k;

    public s(long j3, long j10, long j11, long j12, boolean z6, float f10, int i7, boolean z10, ArrayList arrayList, long j13, long j14) {
        this.f24089a = j3;
        this.f24090b = j10;
        this.c = j11;
        this.d = j12;
        this.f24091e = z6;
        this.f24092f = f10;
        this.f24093g = i7;
        this.f24094h = z10;
        this.f24095i = arrayList;
        this.f24096j = j13;
        this.f24097k = j14;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof s)) {
            return false;
        }
        s sVar = (s) obj;
        return C2738p.a(this.f24089a, sVar.f24089a) && this.f24090b == sVar.f24090b && m0.c.b(this.c, sVar.c) && m0.c.b(this.d, sVar.d) && this.f24091e == sVar.f24091e && Float.compare(this.f24092f, sVar.f24092f) == 0 && AbstractC2737o.e(this.f24093g, sVar.f24093g) && this.f24094h == sVar.f24094h && this.f24095i.equals(sVar.f24095i) && m0.c.b(this.f24096j, sVar.f24096j) && m0.c.b(this.f24097k, sVar.f24097k);
    }

    public final int hashCode() {
        return Long.hashCode(this.f24097k) + AbstractC1347d.c((this.f24095i.hashCode() + AbstractC1347d.d(AbstractC2641j.b(this.f24093g, AbstractC1347d.b(AbstractC1347d.d(AbstractC1347d.c(AbstractC1347d.c(AbstractC1347d.c(Long.hashCode(this.f24089a) * 31, 31, this.f24090b), 31, this.c), 31, this.d), 31, this.f24091e), 31, this.f24092f), 31), 31, this.f24094h)) * 31, 31, this.f24096j);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("PointerInputEventData(id=");
        sb.append((Object) C2738p.b(this.f24089a));
        sb.append(", uptime=");
        sb.append(this.f24090b);
        sb.append(", positionOnScreen=");
        sb.append((Object) m0.c.j(this.c));
        sb.append(", position=");
        sb.append((Object) m0.c.j(this.d));
        sb.append(", down=");
        sb.append(this.f24091e);
        sb.append(", pressure=");
        sb.append(this.f24092f);
        sb.append(", type=");
        int i7 = this.f24093g;
        sb.append((Object) (i7 != 1 ? i7 != 2 ? i7 != 3 ? i7 != 4 ? "Unknown" : "Eraser" : "Stylus" : "Mouse" : "Touch"));
        sb.append(", activeHover=");
        sb.append(this.f24094h);
        sb.append(", historical=");
        sb.append(this.f24095i);
        sb.append(", scrollDelta=");
        sb.append((Object) m0.c.j(this.f24096j));
        sb.append(", originalEventPosition=");
        sb.append((Object) m0.c.j(this.f24097k));
        sb.append(')');
        return sb.toString();
    }
}
