package p;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class os1 implements Comparable {
    public static final String e;
    public final ByteString c;

    static {
        String str = File.separator;
        v03.i(str, "separator");
        e = str;
    }

    public os1(ByteString byteString) {
        v03.j(byteString, "bytes");
        this.c = byteString;
    }

    public final ArrayList a() {
        ArrayList arrayList = new ArrayList();
        int iA = f.a(this);
        ByteString byteString = this.c;
        if (iA == -1) {
            iA = 0;
        } else if (iA < byteString.e() && byteString.k(iA) == 92) {
            iA++;
        }
        int iE = byteString.e();
        int i = iA;
        while (iA < iE) {
            if (byteString.k(iA) == 47 || byteString.k(iA) == 92) {
                arrayList.add(byteString.p(i, iA));
                i = iA + 1;
            }
            iA++;
        }
        if (i < byteString.e()) {
            arrayList.add(byteString.p(i, byteString.e()));
        }
        return arrayList;
    }

    public final os1 b() {
        ByteString byteString = f.d;
        ByteString byteString2 = this.c;
        if (v03.b(byteString2, byteString)) {
            return null;
        }
        ByteString byteString3 = f.a;
        if (v03.b(byteString2, byteString3)) {
            return null;
        }
        ByteString byteString4 = f.b;
        if (v03.b(byteString2, byteString4)) {
            return null;
        }
        ByteString byteString5 = f.e;
        byteString2.getClass();
        v03.j(byteString5, "suffix");
        if (byteString2.o(byteString2.e() - byteString5.e(), byteString5, byteString5.e()) && (byteString2.e() == 2 || byteString2.o(byteString2.e() - 3, byteString3, 1) || byteString2.o(byteString2.e() - 3, byteString4, 1))) {
            return null;
        }
        int iM = ByteString.m(byteString2, byteString3);
        if (iM == -1) {
            iM = ByteString.m(byteString2, byteString4);
        }
        if (iM == 2 && g() != null) {
            if (byteString2.e() == 3) {
                return null;
            }
            return new os1(ByteString.q(byteString2, 0, 3, 1));
        }
        if (iM == 1) {
            v03.j(byteString4, "prefix");
            if (byteString2.o(0, byteString4, byteString4.e())) {
                return null;
            }
        }
        if (iM != -1 || g() == null) {
            return iM == -1 ? new os1(byteString) : iM == 0 ? new os1(ByteString.q(byteString2, 0, 1, 1)) : new os1(ByteString.q(byteString2, 0, iM, 1));
        }
        if (byteString2.e() == 2) {
            return null;
        }
        return new os1(ByteString.q(byteString2, 0, 2, 1));
    }

    public final os1 c(os1 os1Var) {
        v03.j(os1Var, "other");
        int iA = f.a(this);
        ByteString byteString = this.c;
        os1 os1Var2 = iA == -1 ? null : new os1(byteString.p(0, iA));
        int iA2 = f.a(os1Var);
        ByteString byteString2 = os1Var.c;
        if (!v03.b(os1Var2, iA2 != -1 ? new os1(byteString2.p(0, iA2)) : null)) {
            throw new IllegalArgumentException(("Paths of different roots cannot be relative to each other: " + this + " and " + os1Var).toString());
        }
        ArrayList arrayListA = a();
        ArrayList arrayListA2 = os1Var.a();
        int iMin = Math.min(arrayListA.size(), arrayListA2.size());
        int i = 0;
        while (i < iMin && v03.b(arrayListA.get(i), arrayListA2.get(i))) {
            i++;
        }
        if (i == iMin && byteString.e() == byteString2.e()) {
            return u11.z(".", false);
        }
        if (arrayListA2.subList(i, arrayListA2.size()).indexOf(f.e) != -1) {
            throw new IllegalArgumentException(("Impossible relative path to resolve: " + this + " and " + os1Var).toString());
        }
        fo foVar = new fo();
        ByteString byteStringC = f.c(os1Var);
        if (byteStringC == null && (byteStringC = f.c(this)) == null) {
            byteStringC = f.f(e);
        }
        int size = arrayListA2.size();
        for (int i2 = i; i2 < size; i2++) {
            foVar.T0(f.e);
            foVar.T0(byteStringC);
        }
        int size2 = arrayListA.size();
        while (i < size2) {
            foVar.T0((ByteString) arrayListA.get(i));
            foVar.T0(byteStringC);
            i++;
        }
        return f.d(foVar, false);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        os1 os1Var = (os1) obj;
        v03.j(os1Var, "other");
        return this.c.compareTo(os1Var.c);
    }

    public final os1 d(String str) {
        v03.j(str, "child");
        fo foVar = new fo();
        foVar.d1(str);
        return f.b(this, f.d(foVar, false), false);
    }

    public final File e() {
        return new File(this.c.s());
    }

    public final boolean equals(Object obj) {
        return (obj instanceof os1) && v03.b(((os1) obj).c, this.c);
    }

    public final Path f() {
        Path path = Paths.get(this.c.s(), new String[0]);
        v03.i(path, "get(...)");
        return path;
    }

    public final Character g() {
        ByteString byteString = f.a;
        ByteString byteString2 = this.c;
        if (ByteString.i(byteString2, byteString) != -1 || byteString2.e() < 2 || byteString2.k(1) != 58) {
            return null;
        }
        char cK = (char) byteString2.k(0);
        if (('a' > cK || cK >= '{') && ('A' > cK || cK >= '[')) {
            return null;
        }
        return Character.valueOf(cK);
    }

    public final int hashCode() {
        return this.c.hashCode();
    }

    public final String toString() {
        return this.c.s();
    }
}
