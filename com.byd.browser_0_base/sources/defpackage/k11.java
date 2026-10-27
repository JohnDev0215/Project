package defpackage;

import J.N;
import android.content.SharedPreferences;
import java.util.Map;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: IntCachedFieldTrialParameter.java */
/* JADX INFO: loaded from: classes.dex */
public final class k11 extends rk {
    public final int c;

    public k11(int i, String str, String str2) {
        super(str, str2);
        this.c = i;
    }

    @Override // defpackage.rk
    public final void a() {
        sk skVar = bq.a;
        SharedPreferencesManager.getInstance().m(N.M37SqSAy(this.a, this.b, this.c), b());
    }

    public final int c() {
        int i;
        Integer numValueOf;
        String strB = b();
        int i2 = this.c;
        uk ukVar = CachedFeatureFlags.d;
        ukVar.d();
        Object obj = CachedFeatureFlags.c.f;
        if (((Map) obj) != null) {
            String str = (String) ((Map) obj).get(strB);
            return str != null ? Integer.valueOf(str).intValue() : i2;
        }
        wk3 wk3Var = CachedFeatureFlags.b;
        synchronized (wk3Var.c) {
            Integer num = (Integer) wk3Var.c.get(strB);
            if (num != null) {
                return num.intValue();
            }
            if (ukVar.c() && (i = ukVar.b.get()) != 1) {
                if (i != 2) {
                    numValueOf = (i == 3 || i == 4) ? Integer.valueOf(i2) : null;
                } else {
                    SharedPreferences sharedPreferencesB = uk.b();
                    if (sharedPreferencesB.contains(strB)) {
                        numValueOf = Integer.valueOf(sharedPreferencesB.getInt(strB, 0));
                    }
                }
            }
            if (numValueOf == null) {
                numValueOf = Integer.valueOf(SharedPreferencesManager.getInstance().d(i2, strB));
            }
            wk3Var.c.put(strB, numValueOf);
            return numValueOf.intValue();
        }
    }
}
