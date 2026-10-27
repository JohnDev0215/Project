package O5;

import A7.m;
import N0.L;
import N0.y;
import S0.A;
import S0.s;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final L f5158a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final L f5159b;
    public final L c;
    public final L d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final L f5160e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final L f5161f;

    public d() {
        s sVar = c.f5156a;
        L l = new L(0L, 0L, A.f7800s, null, sVar, 0L, 0, 0L, new y(), 16252891);
        L l10 = new L(0L, 0L, A.f7799r, null, sVar, 0L, 0, 0L, new y(), 16252891);
        L l11 = new L(0L, 0L, A.f7803v, null, sVar, 0L, 0, 0L, new y(), 16252891);
        L l12 = new L(0L, 0L, A.f7801t, null, sVar, 0L, 0, 0L, new y(), 16252891);
        L l13 = new L(0L, 0L, A.f7802u, null, sVar, 0L, 0, 0L, new y(), 16252891);
        L l14 = new L(0L, 0L, A.f7805x, null, sVar, 0L, 0, 0L, new y(), 16252891);
        this.f5158a = l;
        this.f5159b = l10;
        this.c = l11;
        this.d = l12;
        this.f5160e = l13;
        this.f5161f = l14;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return m.a(this.f5158a, dVar.f5158a) && m.a(this.f5159b, dVar.f5159b) && m.a(this.c, dVar.c) && m.a(this.d, dVar.d) && m.a(this.f5160e, dVar.f5160e) && m.a(this.f5161f, dVar.f5161f);
    }

    public final int hashCode() {
        return this.f5161f.hashCode() + M5.b.b(M5.b.b(M5.b.b(M5.b.b(this.f5158a.hashCode() * 31, 31, this.f5159b), 31, this.c), 31, this.d), 31, this.f5160e);
    }

    public final String toString() {
        return "Typography(regular=" + this.f5158a + ", light=" + this.f5159b + ", bold=" + this.c + ", medium=" + this.d + ", semiBold=" + this.f5160e + ", black=" + this.f5161f + ")";
    }
}
