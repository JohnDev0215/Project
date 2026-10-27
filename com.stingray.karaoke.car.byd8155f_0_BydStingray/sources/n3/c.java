package N3;

import A9.k;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final K3.c f4574a = new K3.c();

    public static final boolean a(K3.i iVar) {
        int iOrdinal = iVar.f3544e.ordinal();
        if (iOrdinal == 0) {
            return false;
        }
        if (iOrdinal != 1) {
            if (iOrdinal != 2) {
                throw new k();
            }
            if (iVar.f3562y.f3524a != null || !(iVar.f3559v instanceof L3.c)) {
                return false;
            }
        }
        return true;
    }
}
