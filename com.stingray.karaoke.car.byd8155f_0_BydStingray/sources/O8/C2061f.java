package o8;

/* JADX INFO: renamed from: o8.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2061f implements Comparable {
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f20573m;

    public C2061f(String str, boolean z6) {
        if (str == null) {
            a(0);
            throw null;
        }
        this.l = str;
        this.f20573m = z6;
    }

    public static /* synthetic */ void a(int i7) {
        String str = (i7 == 1 || i7 == 2 || i7 == 3 || i7 == 4) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i7 == 1 || i7 == 2 || i7 == 3 || i7 == 4) ? 2 : 3];
        if (i7 == 1 || i7 == 2 || i7 == 3 || i7 == 4) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/name/Name";
        } else {
            objArr[0] = "name";
        }
        if (i7 == 1) {
            objArr[1] = "asString";
        } else if (i7 == 2) {
            objArr[1] = "getIdentifier";
        } else if (i7 == 3 || i7 == 4) {
            objArr[1] = "asStringStripSpecialMarkers";
        } else {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/name/Name";
        }
        switch (i7) {
            case 1:
            case 2:
            case 3:
            case 4:
                break;
            case 5:
                objArr[2] = "identifier";
                break;
            case 6:
                objArr[2] = "isValidIdentifier";
                break;
            case 7:
                objArr[2] = "special";
                break;
            case 8:
                objArr[2] = "guessByFirstCharacter";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i7 != 1 && i7 != 2 && i7 != 3 && i7 != 4) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static C2061f d(String str) {
        if (str != null) {
            return str.startsWith("<") ? g(str) : e(str);
        }
        a(8);
        throw null;
    }

    public static C2061f e(String str) {
        if (str != null) {
            return new C2061f(str, false);
        }
        a(5);
        throw null;
    }

    public static boolean f(String str) {
        if (str == null) {
            a(6);
            throw null;
        }
        if (str.isEmpty() || str.startsWith("<")) {
            return false;
        }
        for (int i7 = 0; i7 < str.length(); i7++) {
            char cCharAt = str.charAt(i7);
            if (cCharAt == '.' || cCharAt == '/' || cCharAt == '\\') {
                return false;
            }
        }
        return true;
    }

    public static C2061f g(String str) {
        if (str == null) {
            a(7);
            throw null;
        }
        if (str.startsWith("<")) {
            return new C2061f(str, true);
        }
        throw new IllegalArgumentException("special name must start with '<': ".concat(str));
    }

    public final String b() {
        String str = this.l;
        if (str != null) {
            return str;
        }
        a(1);
        throw null;
    }

    public final String c() {
        if (this.f20573m) {
            throw new IllegalStateException("not identifier: " + this);
        }
        String strB = b();
        if (strB != null) {
            return strB;
        }
        a(2);
        throw null;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.l.compareTo(((C2061f) obj).l);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2061f)) {
            return false;
        }
        C2061f c2061f = (C2061f) obj;
        return this.f20573m == c2061f.f20573m && this.l.equals(c2061f.l);
    }

    public final int hashCode() {
        return (this.l.hashCode() * 31) + (this.f20573m ? 1 : 0);
    }

    public final String toString() {
        return this.l;
    }
}
