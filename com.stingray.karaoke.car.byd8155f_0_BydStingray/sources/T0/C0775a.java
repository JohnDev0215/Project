package T0;

import N0.C0411f;
import x3.AbstractC2673a;

/* JADX INFO: renamed from: T0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0775a implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0411f f8428a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f8429b;

    public C0775a(C0411f c0411f, int i7) {
        this.f8428a = c0411f;
        this.f8429b = i7;
    }

    @Override // T0.i
    public final void a(j jVar) {
        int i7 = jVar.o;
        boolean z6 = i7 != -1;
        C0411f c0411f = this.f8428a;
        if (z6) {
            jVar.g(c0411f.l, i7, jVar.f8456p);
        } else {
            jVar.g(c0411f.l, jVar.f8454m, jVar.f8455n);
        }
        int i10 = jVar.f8454m;
        int i11 = jVar.f8455n;
        int i12 = i10 == i11 ? i11 : -1;
        int i13 = this.f8429b;
        int iV = AbstractC2673a.v(i13 > 0 ? (i12 + i13) - 1 : (i12 + i13) - c0411f.l.length(), 0, ((A2.g) jVar.f8457q).o());
        jVar.i(iV, iV);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0775a)) {
            return false;
        }
        C0775a c0775a = (C0775a) obj;
        return A7.m.a(this.f8428a.l, c0775a.f8428a.l) && this.f8429b == c0775a.f8429b;
    }

    public final int hashCode() {
        return (this.f8428a.l.hashCode() * 31) + this.f8429b;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("CommitTextCommand(text='");
        sb.append(this.f8428a.l);
        sb.append("', newCursorPosition=");
        return android.hardware.bydauto.ac.tsimpl.a.j(sb, this.f8429b, ')');
    }

    public C0775a(String str, int i7) {
        this(new C0411f(str, null, 6), i7);
    }
}
