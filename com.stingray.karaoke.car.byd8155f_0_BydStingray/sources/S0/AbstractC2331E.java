package s0;

import n0.AbstractC1861K;
import n0.C1883l;
import n0.C1891t;

/* JADX INFO: renamed from: s0.E, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2331E {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f21974a = 0;

    static {
        int i7 = C1891t.l;
    }

    public static final boolean a(C1883l c1883l) {
        if (c1883l instanceof C1883l) {
            if (AbstractC1861K.p(c1883l.c, 5) || AbstractC1861K.p(c1883l.c, 3)) {
                return true;
            }
        } else if (c1883l == null) {
            return true;
        }
        return false;
    }
}
