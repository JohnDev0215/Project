package r9;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import n0.C1886o;

/* JADX INFO: loaded from: classes.dex */
public final class z implements Comparable {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final String f21951m;
    public final C2322l l;

    static {
        String str = File.separator;
        A7.m.e(str, "separator");
        f21951m = str;
    }

    public z(C2322l c2322l) {
        A7.m.f(c2322l, "bytes");
        this.l = c2322l;
    }

    public final ArrayList a() {
        ArrayList arrayList = new ArrayList();
        int iA = s9.c.a(this);
        C2322l c2322l = this.l;
        if (iA == -1) {
            iA = 0;
        } else if (iA < c2322l.d() && c2322l.i(iA) == 92) {
            iA++;
        }
        int iD = c2322l.d();
        int i7 = iA;
        while (iA < iD) {
            if (c2322l.i(iA) == 47 || c2322l.i(iA) == 92) {
                arrayList.add(c2322l.n(i7, iA));
                i7 = iA + 1;
            }
            iA++;
        }
        if (i7 < c2322l.d()) {
            arrayList.add(c2322l.n(i7, c2322l.d()));
        }
        return arrayList;
    }

    public final String b() {
        C2322l c2322l = s9.c.f22382a;
        C2322l c2322l2 = s9.c.f22382a;
        C2322l c2322lO = this.l;
        int iK = C2322l.k(c2322lO, c2322l2);
        if (iK == -1) {
            iK = C2322l.k(c2322lO, s9.c.f22383b);
        }
        if (iK != -1) {
            c2322lO = C2322l.o(c2322lO, iK + 1, 0, 2);
        } else if (h() != null && c2322lO.d() == 2) {
            c2322lO = C2322l.o;
        }
        return c2322lO.r();
    }

    public final z c() {
        C2322l c2322l = s9.c.d;
        C2322l c2322l2 = this.l;
        if (A7.m.a(c2322l2, c2322l)) {
            return null;
        }
        C2322l c2322l3 = s9.c.f22382a;
        if (A7.m.a(c2322l2, c2322l3)) {
            return null;
        }
        C2322l c2322l4 = s9.c.f22383b;
        if (A7.m.a(c2322l2, c2322l4)) {
            return null;
        }
        C2322l c2322l5 = s9.c.f22384e;
        c2322l2.getClass();
        A7.m.f(c2322l5, "suffix");
        int iD = c2322l2.d();
        byte[] bArr = c2322l5.l;
        if (c2322l2.m(iD - bArr.length, c2322l5, bArr.length) && (c2322l2.d() == 2 || c2322l2.m(c2322l2.d() - 3, c2322l3, 1) || c2322l2.m(c2322l2.d() - 3, c2322l4, 1))) {
            return null;
        }
        int iK = C2322l.k(c2322l2, c2322l3);
        if (iK == -1) {
            iK = C2322l.k(c2322l2, c2322l4);
        }
        if (iK == 2 && h() != null) {
            if (c2322l2.d() == 3) {
                return null;
            }
            return new z(C2322l.o(c2322l2, 0, 3, 1));
        }
        if (iK == 1) {
            A7.m.f(c2322l4, "prefix");
            if (c2322l2.m(0, c2322l4, c2322l4.d())) {
                return null;
            }
        }
        if (iK != -1 || h() == null) {
            return iK == -1 ? new z(c2322l) : iK == 0 ? new z(C2322l.o(c2322l2, 0, 1, 1)) : new z(C2322l.o(c2322l2, 0, iK, 1));
        }
        if (c2322l2.d() == 2) {
            return null;
        }
        return new z(C2322l.o(c2322l2, 0, 2, 1));
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        z zVar = (z) obj;
        A7.m.f(zVar, "other");
        return this.l.compareTo(zVar.l);
    }

    public final z d(z zVar) {
        A7.m.f(zVar, "other");
        int iA = s9.c.a(this);
        C2322l c2322l = this.l;
        z zVar2 = iA == -1 ? null : new z(c2322l.n(0, iA));
        int iA2 = s9.c.a(zVar);
        C2322l c2322l2 = zVar.l;
        if (!A7.m.a(zVar2, iA2 != -1 ? new z(c2322l2.n(0, iA2)) : null)) {
            throw new IllegalArgumentException(("Paths of different roots cannot be relative to each other: " + this + " and " + zVar).toString());
        }
        ArrayList arrayListA = a();
        ArrayList arrayListA2 = zVar.a();
        int iMin = Math.min(arrayListA.size(), arrayListA2.size());
        int i7 = 0;
        while (i7 < iMin && A7.m.a(arrayListA.get(i7), arrayListA2.get(i7))) {
            i7++;
        }
        if (i7 == iMin && c2322l.d() == c2322l2.d()) {
            return C1886o.i(".", false);
        }
        if (arrayListA2.subList(i7, arrayListA2.size()).indexOf(s9.c.f22384e) != -1) {
            throw new IllegalArgumentException(("Impossible relative path to resolve: " + this + " and " + zVar).toString());
        }
        C2319i c2319i = new C2319i();
        C2322l c2322lC = s9.c.c(zVar);
        if (c2322lC == null && (c2322lC = s9.c.c(this)) == null) {
            c2322lC = s9.c.f(f21951m);
        }
        int size = arrayListA2.size();
        for (int i10 = i7; i10 < size; i10++) {
            c2319i.U(s9.c.f22384e);
            c2319i.U(c2322lC);
        }
        int size2 = arrayListA.size();
        while (i7 < size2) {
            c2319i.U((C2322l) arrayListA.get(i7));
            c2319i.U(c2322lC);
            i7++;
        }
        return s9.c.d(c2319i, false);
    }

    public final z e(String str) {
        A7.m.f(str, "child");
        C2319i c2319i = new C2319i();
        c2319i.c0(str);
        return s9.c.b(this, s9.c.d(c2319i, false), false);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof z) && A7.m.a(((z) obj).l, this.l);
    }

    public final File f() {
        return new File(this.l.r());
    }

    public final Path g() {
        Path path = Paths.get(this.l.r(), new String[0]);
        A7.m.e(path, "get(...)");
        return path;
    }

    public final Character h() {
        C2322l c2322l = s9.c.f22382a;
        C2322l c2322l2 = this.l;
        if (C2322l.g(c2322l2, c2322l) != -1 || c2322l2.d() < 2 || c2322l2.i(1) != 58) {
            return null;
        }
        char cI = (char) c2322l2.i(0);
        if (('a' > cI || cI >= '{') && ('A' > cI || cI >= '[')) {
            return null;
        }
        return Character.valueOf(cI);
    }

    public final int hashCode() {
        return this.l.hashCode();
    }

    public final String toString() {
        return this.l.r();
    }
}
