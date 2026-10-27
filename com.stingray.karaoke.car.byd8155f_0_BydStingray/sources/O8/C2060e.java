package o8;

import A7.m;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: o8.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2060e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C2061f f20568e = C2061f.g("<root>");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Pattern f20569f = Pattern.compile("\\.");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C2059d f20570g = new C2059d();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f20571a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public transient C2058c f20572b;
    public transient C2060e c;
    public transient C2061f d;

    public C2060e(String str, C2058c c2058c) {
        if (str == null) {
            a(0);
            throw null;
        }
        if (c2058c == null) {
            a(1);
            throw null;
        }
        this.f20571a = str;
        this.f20572b = c2058c;
    }

    public static /* synthetic */ void a(int i7) {
        String str;
        int i10;
        switch (i7) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 17:
                str = "@NotNull method %s.%s must not return null";
                break;
            case 9:
            case 15:
            case 16:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i7) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 17:
                i10 = 2;
                break;
            case 9:
            case 15:
            case 16:
            default:
                i10 = 3;
                break;
        }
        Object[] objArr = new Object[i10];
        if (i7 != 1) {
            switch (i7) {
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 17:
                    objArr[0] = "kotlin/reflect/jvm/internal/impl/name/FqNameUnsafe";
                    break;
                case 9:
                    objArr[0] = "name";
                    break;
                case 15:
                    objArr[0] = "segment";
                    break;
                case 16:
                    objArr[0] = "shortName";
                    break;
                default:
                    objArr[0] = "fqName";
                    break;
            }
        } else {
            objArr[0] = "safe";
        }
        switch (i7) {
            case 4:
                objArr[1] = "asString";
                break;
            case 5:
            case 6:
                objArr[1] = "toSafe";
                break;
            case 7:
            case 8:
                objArr[1] = "parent";
                break;
            case 9:
            case 15:
            case 16:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/name/FqNameUnsafe";
                break;
            case 10:
            case 11:
                objArr[1] = "shortName";
                break;
            case 12:
            case 13:
                objArr[1] = "shortNameOrSpecial";
                break;
            case 14:
                objArr[1] = "pathSegments";
                break;
            case 17:
                objArr[1] = "toString";
                break;
        }
        switch (i7) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 17:
                break;
            case 9:
                objArr[2] = "child";
                break;
            case 15:
                objArr[2] = "startsWith";
                break;
            case 16:
                objArr[2] = "topLevel";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i7) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 17:
                throw new IllegalStateException(str2);
            case 9:
            case 15:
            case 16:
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    public final C2060e b(C2061f c2061f) {
        String strB;
        if (c2061f == null) {
            a(9);
            throw null;
        }
        String str = this.f20571a;
        if (str.isEmpty()) {
            strB = c2061f.b();
        } else {
            strB = str + "." + c2061f.b();
        }
        return new C2060e(strB, this, c2061f);
    }

    public final void c() {
        String str = this.f20571a;
        int iLastIndexOf = str.lastIndexOf(46);
        if (iLastIndexOf >= 0) {
            this.d = C2061f.d(str.substring(iLastIndexOf + 1));
            this.c = new C2060e(str.substring(0, iLastIndexOf));
        } else {
            this.d = C2061f.d(str);
            this.c = C2058c.c.i();
        }
    }

    public final boolean d() {
        if (this.f20572b == null) {
            String str = this.f20571a;
            if (str == null) {
                a(4);
                throw null;
            }
            if (str.indexOf(60) >= 0) {
                return false;
            }
        }
        return true;
    }

    public final List e() {
        List listEmptyList;
        String str = this.f20571a;
        if (str.isEmpty()) {
            listEmptyList = Collections.emptyList();
        } else {
            String[] strArrSplit = f20569f.split(str);
            m.f(strArrSplit, "<this>");
            m.f(f20570g, "transform");
            ArrayList arrayList = new ArrayList(strArrSplit.length);
            for (String str2 : strArrSplit) {
                arrayList.add(C2061f.d(str2));
            }
            listEmptyList = arrayList;
        }
        if (listEmptyList != null) {
            return listEmptyList;
        }
        a(14);
        throw null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof C2060e) && this.f20571a.equals(((C2060e) obj).f20571a);
    }

    public final C2061f f() {
        C2061f c2061f = this.d;
        if (c2061f != null) {
            if (c2061f != null) {
                return c2061f;
            }
            a(10);
            throw null;
        }
        if (this.f20571a.isEmpty()) {
            throw new IllegalStateException("root");
        }
        c();
        C2061f c2061f2 = this.d;
        if (c2061f2 != null) {
            return c2061f2;
        }
        a(11);
        throw null;
    }

    public final C2058c g() {
        C2058c c2058c = this.f20572b;
        if (c2058c == null) {
            C2058c c2058c2 = new C2058c(this);
            this.f20572b = c2058c2;
            return c2058c2;
        }
        if (c2058c != null) {
            return c2058c;
        }
        a(5);
        throw null;
    }

    public final int hashCode() {
        return this.f20571a.hashCode();
    }

    public final String toString() {
        String strB = this.f20571a;
        if (strB.isEmpty()) {
            strB = f20568e.b();
        }
        if (strB != null) {
            return strB;
        }
        a(17);
        throw null;
    }

    public C2060e(String str) {
        if (str != null) {
            this.f20571a = str;
        } else {
            a(2);
            throw null;
        }
    }

    public C2060e(String str, C2060e c2060e, C2061f c2061f) {
        if (str != null) {
            this.f20571a = str;
            this.c = c2060e;
            this.d = c2061f;
            return;
        }
        a(3);
        throw null;
    }
}
