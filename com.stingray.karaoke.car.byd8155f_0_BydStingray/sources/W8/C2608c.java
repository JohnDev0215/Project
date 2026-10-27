package w8;

import o8.C2057b;
import o8.C2058c;

/* JADX INFO: renamed from: w8.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2608c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f23346a;

    public C2608c(String str) {
        if (str != null) {
            this.f23346a = str;
        } else {
            a(5);
            throw null;
        }
    }

    public static /* synthetic */ void a(int i7) {
        String str = (i7 == 3 || i7 == 6 || i7 == 7 || i7 == 8) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i7 == 3 || i7 == 6 || i7 == 7 || i7 == 8) ? 2 : 3];
        switch (i7) {
            case 1:
                objArr[0] = "classId";
                break;
            case 2:
            case 4:
                objArr[0] = "fqName";
                break;
            case 3:
            case 6:
            case 7:
            case 8:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/resolve/jvm/JvmClassName";
                break;
            case 5:
            default:
                objArr[0] = "internalName";
                break;
        }
        if (i7 == 3) {
            objArr[1] = "byFqNameWithoutInnerClasses";
        } else if (i7 == 6) {
            objArr[1] = "getFqNameForClassNameWithoutDollars";
        } else if (i7 == 7) {
            objArr[1] = "getPackageFqName";
        } else if (i7 != 8) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/jvm/JvmClassName";
        } else {
            objArr[1] = "getInternalName";
        }
        switch (i7) {
            case 1:
                objArr[2] = "byClassId";
                break;
            case 2:
            case 4:
                objArr[2] = "byFqNameWithoutInnerClasses";
                break;
            case 3:
            case 6:
            case 7:
            case 8:
                break;
            case 5:
                objArr[2] = "<init>";
                break;
            default:
                objArr[2] = "byInternalName";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i7 != 3 && i7 != 6 && i7 != 7 && i7 != 8) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static C2608c b(C2057b c2057b) {
        C2058c c2058cG = c2057b.g();
        String strReplace = c2057b.h().b().replace('.', '$');
        if (c2058cG.d()) {
            return new C2608c(strReplace);
        }
        return new C2608c(c2058cG.b().replace('.', '/') + "/" + strReplace);
    }

    public static C2608c c(C2058c c2058c) {
        if (c2058c != null) {
            return new C2608c(c2058c.b().replace('.', '/'));
        }
        a(2);
        throw null;
    }

    public static C2608c d(String str) {
        if (str != null) {
            return new C2608c(str);
        }
        a(0);
        throw null;
    }

    public final String e() {
        String str = this.f23346a;
        if (str != null) {
            return str;
        }
        a(8);
        throw null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C2608c.class != obj.getClass()) {
            return false;
        }
        return this.f23346a.equals(((C2608c) obj).f23346a);
    }

    public final int hashCode() {
        return this.f23346a.hashCode();
    }

    public final String toString() {
        return this.f23346a;
    }
}
