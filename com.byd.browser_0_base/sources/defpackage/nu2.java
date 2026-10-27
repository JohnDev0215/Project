package defpackage;

import J.N;
import android.content.SharedPreferences;
import java.util.Map;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: StringCachedFieldTrialParameter.java */
/* JADX INFO: loaded from: classes.dex */
public final class nu2 extends rk {
    public final String c;

    public nu2(String str, String str2, String str3) {
        super(str, str2);
        this.c = str3;
    }

    @Override // defpackage.rk
    public final void a() {
        sk skVar = bq.a;
        String strMMltG$kc = N.MMltG$kc(this.a, this.b);
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        String strB = b();
        if (strMMltG$kc.isEmpty()) {
            strMMltG$kc = this.c;
        }
        sharedPreferencesManager.o(strB, strMMltG$kc);
    }

    public final String c() {
        int i;
        String strB = b();
        String str = this.c;
        uk ukVar = CachedFeatureFlags.d;
        ukVar.d();
        Object obj = CachedFeatureFlags.c.f;
        if (((Map) obj) != null) {
            String str2 = (String) ((Map) obj).get(strB);
            return str2 != null ? str2 : str;
        }
        wk3 wk3Var = CachedFeatureFlags.b;
        synchronized (wk3Var.b) {
            String str3 = (String) wk3Var.b.get(strB);
            if (str3 != null) {
                return str3;
            }
            String string = null;
            if (ukVar.c() && (i = ukVar.b.get()) != 1) {
                if (i == 2) {
                    SharedPreferences sharedPreferencesB = uk.b();
                    if (sharedPreferencesB.contains(strB)) {
                        string = sharedPreferencesB.getString(strB, null);
                    }
                } else if (i == 3 || i == 4) {
                    string = str;
                }
            }
            String strG = string == null ? SharedPreferencesManager.getInstance().g(strB, str) : string;
            wk3Var.b.put(strB, strG);
            return strG;
        }
    }
}
