package o8;

/* JADX INFO: renamed from: o8.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2058c {
    public static final C2058c c = new C2058c("");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2060e f20566a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public transient C2058c f20567b;

    public C2058c(String str) {
        if (str != null) {
            this.f20566a = new C2060e(str, this);
        } else {
            a(1);
            throw null;
        }
    }

    public static /* synthetic */ void a(int i7) {
        String str;
        int i10;
        switch (i7) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
            case 11:
                str = "@NotNull method %s.%s must not return null";
                break;
            case 8:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i7) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
            case 11:
                i10 = 2;
                break;
            case 8:
            default:
                i10 = 3;
                break;
        }
        Object[] objArr = new Object[i10];
        switch (i7) {
            case 1:
            case 2:
            case 3:
                objArr[0] = "fqName";
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
            case 11:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/name/FqName";
                break;
            case 8:
                objArr[0] = "name";
                break;
            case 12:
                objArr[0] = "segment";
                break;
            case 13:
                objArr[0] = "shortName";
                break;
            default:
                objArr[0] = "names";
                break;
        }
        switch (i7) {
            case 4:
                objArr[1] = "asString";
                break;
            case 5:
                objArr[1] = "toUnsafe";
                break;
            case 6:
            case 7:
                objArr[1] = "parent";
                break;
            case 8:
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/name/FqName";
                break;
            case 9:
                objArr[1] = "shortName";
                break;
            case 10:
                objArr[1] = "shortNameOrSpecial";
                break;
            case 11:
                objArr[1] = "pathSegments";
                break;
        }
        switch (i7) {
            case 1:
            case 2:
            case 3:
                objArr[2] = "<init>";
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
            case 11:
                break;
            case 8:
                objArr[2] = "child";
                break;
            case 12:
                objArr[2] = "startsWith";
                break;
            case 13:
                objArr[2] = "topLevel";
                break;
            default:
                objArr[2] = "fromSegments";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i7) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
            case 11:
                throw new IllegalStateException(str2);
            case 8:
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    public static C2058c j(C2061f c2061f) {
        if (c2061f == null) {
            a(13);
            throw null;
        }
        if (c2061f != null) {
            return new C2058c(new C2060e(c2061f.b(), c.i(), c2061f));
        }
        C2060e.a(16);
        throw null;
    }

    public final String b() {
        String str = this.f20566a.f20571a;
        if (str != null) {
            return str;
        }
        C2060e.a(4);
        throw null;
    }

    public final C2058c c(C2061f c2061f) {
        if (c2061f != null) {
            return new C2058c(this.f20566a.b(c2061f), this);
        }
        a(8);
        throw null;
    }

    public final boolean d() {
        return this.f20566a.f20571a.isEmpty();
    }

    public final C2058c e() {
        C2058c c2058c = this.f20567b;
        if (c2058c != null) {
            if (c2058c != null) {
                return c2058c;
            }
            a(6);
            throw null;
        }
        if (d()) {
            throw new IllegalStateException("root");
        }
        C2060e c2060e = this.f20566a;
        C2060e c2060e2 = c2060e.c;
        if (c2060e2 == null) {
            if (c2060e.f20571a.isEmpty()) {
                throw new IllegalStateException("root");
            }
            c2060e.c();
            c2060e2 = c2060e.c;
            if (c2060e2 == null) {
                C2060e.a(8);
                throw null;
            }
        }
        C2058c c2058c2 = new C2058c(c2060e2);
        this.f20567b = c2058c2;
        return c2058c2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof C2058c) && this.f20566a.equals(((C2058c) obj).f20566a);
    }

    public final C2061f f() {
        C2061f c2061fF = this.f20566a.f();
        if (c2061fF != null) {
            return c2061fF;
        }
        a(9);
        throw null;
    }

    public final C2061f g() {
        C2061f c2061fF;
        C2060e c2060e = this.f20566a;
        if (c2060e.f20571a.isEmpty()) {
            c2061fF = C2060e.f20568e;
            if (c2061fF == null) {
                C2060e.a(12);
                throw null;
            }
        } else {
            c2061fF = c2060e.f();
            if (c2061fF == null) {
                C2060e.a(13);
                throw null;
            }
        }
        return c2061fF;
    }

    public final boolean h(C2061f c2061f) {
        if (c2061f == null) {
            a(12);
            throw null;
        }
        String str = this.f20566a.f20571a;
        if (str.isEmpty()) {
            return false;
        }
        int iIndexOf = str.indexOf(46);
        String strB = c2061f.b();
        if (iIndexOf == -1) {
            iIndexOf = Math.max(str.length(), strB.length());
        }
        return str.regionMatches(0, strB, 0, iIndexOf);
    }

    public final int hashCode() {
        return this.f20566a.f20571a.hashCode();
    }

    public final C2060e i() {
        C2060e c2060e = this.f20566a;
        if (c2060e != null) {
            return c2060e;
        }
        a(5);
        throw null;
    }

    public final String toString() {
        return this.f20566a.toString();
    }

    public C2058c(C2060e c2060e) {
        if (c2060e != null) {
            this.f20566a = c2060e;
        } else {
            a(2);
            throw null;
        }
    }

    public C2058c(C2060e c2060e, C2058c c2058c) {
        this.f20566a = c2060e;
        this.f20567b = c2058c;
    }
}
