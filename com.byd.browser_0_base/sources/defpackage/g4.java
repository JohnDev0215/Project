package defpackage;

import J.N;
import java.util.HashMap;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.json.JSONObject;

/* JADX INFO: compiled from: AllCachedFieldTrialParameters.java */
/* JADX INFO: loaded from: classes.dex */
public final class g4 extends rk {
    public g4() {
        super("ExperimentsForAgsa", "");
    }

    @Override // defpackage.rk
    public final void a() {
        sk skVar = bq.a;
        HashMap map = new HashMap();
        String[] strArrM1ealghd = N.M1ealghd(this.a);
        for (int i = 0; i < strArrM1ealghd.length; i += 2) {
            map.put(strArrM1ealghd[i], strArrM1ealghd[i + 1]);
        }
        SharedPreferencesManager.getInstance().o(b(), new JSONObject(map).toString());
    }
}
