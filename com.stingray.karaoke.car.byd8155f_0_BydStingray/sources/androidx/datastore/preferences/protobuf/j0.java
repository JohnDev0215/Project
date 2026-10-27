package androidx.datastore.preferences.protobuf;

/* JADX INFO: loaded from: classes.dex */
public final class j0 {
    public static i0 a(Object obj) {
        AbstractC0957u abstractC0957u = (AbstractC0957u) obj;
        i0 i0Var = abstractC0957u.unknownFields;
        if (i0Var != i0.f11858f) {
            return i0Var;
        }
        i0 i0Var2 = new i0(0, new int[8], new Object[8], true);
        abstractC0957u.unknownFields = i0Var2;
        return i0Var2;
    }

    public static void b(Object obj) {
        ((AbstractC0957u) obj).unknownFields.f11861e = false;
    }

    public static boolean c(Object obj, E5.z zVar) throws C0961y {
        int i7 = zVar.f1841b;
        int i10 = i7 >>> 3;
        int i11 = i7 & 7;
        AbstractC0947j abstractC0947j = (AbstractC0947j) zVar.f1842e;
        if (i11 == 0) {
            zVar.D(0);
            ((i0) obj).c(i10 << 3, Long.valueOf(abstractC0947j.n()));
            return true;
        }
        if (i11 == 1) {
            zVar.D(1);
            ((i0) obj).c((i10 << 3) | 1, Long.valueOf(abstractC0947j.k()));
            return true;
        }
        if (i11 == 2) {
            ((i0) obj).c((i10 << 3) | 2, zVar.k());
            return true;
        }
        if (i11 != 3) {
            if (i11 == 4) {
                return false;
            }
            if (i11 != 5) {
                throw C0961y.b();
            }
            zVar.D(5);
            ((i0) obj).c((i10 << 3) | 5, Integer.valueOf(abstractC0947j.j()));
            return true;
        }
        i0 i0Var = new i0(0, new int[8], new Object[8], true);
        int i12 = i10 << 3;
        int i13 = i12 | 4;
        while (zVar.e() != Integer.MAX_VALUE && c(i0Var, zVar)) {
        }
        if (i13 != zVar.f1841b) {
            throw new C0961y("Protocol message end-group tag did not match expected tag.");
        }
        i0Var.f11861e = false;
        ((i0) obj).c(i12 | 3, i0Var);
        return true;
    }
}
