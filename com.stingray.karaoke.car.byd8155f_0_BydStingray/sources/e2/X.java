package e2;

import android.os.Bundle;
import h2.AbstractC1343A;
import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public abstract class X {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f14014a;

    static {
        int i7 = AbstractC1343A.f15195a;
        f14014a = Integer.toString(0, 36);
    }

    public static X a(Bundle bundle) {
        String str = f14014a;
        int i7 = bundle.getInt(str, -1);
        if (i7 == 0) {
            String str2 = r.d;
            AbstractC1346c.c(bundle.getInt(str, -1) == 0);
            return bundle.getBoolean(r.d, false) ? new r(bundle.getBoolean(r.f14334e, false)) : new r();
        }
        if (i7 == 1) {
            String str3 = N.c;
            AbstractC1346c.c(bundle.getInt(str, -1) == 1);
            float f10 = bundle.getFloat(N.c, -1.0f);
            return f10 == -1.0f ? new N() : new N(f10);
        }
        if (i7 != 2) {
            if (i7 != 3) {
                throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "Unknown RatingType: "));
            }
            String str4 = a0.d;
            AbstractC1346c.c(bundle.getInt(str, -1) == 3);
            return bundle.getBoolean(a0.d, false) ? new a0(bundle.getBoolean(a0.f14035e, false)) : new a0();
        }
        String str5 = Y.d;
        AbstractC1346c.c(bundle.getInt(str, -1) == 2);
        int i10 = bundle.getInt(Y.d, 5);
        float f11 = bundle.getFloat(Y.f14015e, -1.0f);
        return f11 == -1.0f ? new Y(i10) : new Y(f11, i10);
    }

    public abstract boolean b();

    public abstract Bundle c();
}
