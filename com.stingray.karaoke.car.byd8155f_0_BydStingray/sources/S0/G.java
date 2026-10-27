package S0;

import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class G {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7808a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final A f7809b;
    public final int c;
    public final z d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f7810e;

    public G(int i7, A a10, int i10, z zVar, int i11) {
        this.f7808a = i7;
        this.f7809b = a10;
        this.c = i10;
        this.d = zVar;
        this.f7810e = i11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof G)) {
            return false;
        }
        G g7 = (G) obj;
        return this.f7808a == g7.f7808a && A7.m.a(this.f7809b, g7.f7809b) && w.a(this.c, g7.c) && this.d.equals(g7.d) && l9.l.J(this.f7810e, g7.f7810e);
    }

    public final int hashCode() {
        return this.d.f7862a.hashCode() + AbstractC2641j.b(this.f7810e, AbstractC2641j.b(this.c, ((this.f7808a * 31) + this.f7809b.l) * 31, 31), 31);
    }

    public final String toString() {
        return "ResourceFont(resId=" + this.f7808a + ", weight=" + this.f7809b + ", style=" + ((Object) w.b(this.c)) + ", loadingStrategy=" + ((Object) l9.l.Z(this.f7810e)) + ')';
    }
}
