package T0;

import N0.C0411f;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class w implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0411f f8477a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f8478b;

    public w(String str, int i7) {
        this.f8477a = new C0411f(str, null, 6);
        this.f8478b = i7;
    }

    @Override // T0.i
    public final void a(j jVar) {
        int i7 = jVar.o;
        boolean z6 = i7 != -1;
        C0411f c0411f = this.f8477a;
        if (z6) {
            jVar.g(c0411f.l, i7, jVar.f8456p);
            String str = c0411f.l;
            if (str.length() > 0) {
                jVar.h(i7, str.length() + i7);
            }
        } else {
            int i10 = jVar.f8454m;
            jVar.g(c0411f.l, i10, jVar.f8455n);
            String str2 = c0411f.l;
            if (str2.length() > 0) {
                jVar.h(i10, str2.length() + i10);
            }
        }
        int i11 = jVar.f8454m;
        int i12 = jVar.f8455n;
        int i13 = i11 == i12 ? i12 : -1;
        int i14 = this.f8478b;
        int iV = AbstractC2673a.v(i14 > 0 ? (i13 + i14) - 1 : (i13 + i14) - c0411f.l.length(), 0, ((A2.g) jVar.f8457q).o());
        jVar.i(iV, iV);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof w)) {
            return false;
        }
        w wVar = (w) obj;
        return A7.m.a(this.f8477a.l, wVar.f8477a.l) && this.f8478b == wVar.f8478b;
    }

    public final int hashCode() {
        return (this.f8477a.l.hashCode() * 31) + this.f8478b;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SetComposingTextCommand(text='");
        sb.append(this.f8477a.l);
        sb.append("', newCursorPosition=");
        return android.hardware.bydauto.ac.tsimpl.a.j(sb, this.f8478b, ')');
    }
}
