package org.chromium.chrome.browser.feedback;

import defpackage.em0;
import defpackage.gq2;
import defpackage.w8;
import java.util.HashMap;
import java.util.Map;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.feedback.d;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: ConnectivityFeedbackSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class c implements w8, d.a {
    public final Profile a;
    public Runnable b;
    public d c;

    public c(Profile profile) {
        this.a = profile;
    }

    @Override // defpackage.w8
    public final boolean a() {
        d dVar = this.c;
        dVar.getClass();
        Object obj = ThreadUtils.a;
        return dVar.a.size() == 4;
    }

    @Override // defpackage.w8
    public final void b(em0 em0Var) {
        this.b = em0Var;
        Object obj = ThreadUtils.a;
        this.c = new d(this.a, this);
    }

    @Override // defpackage.gm0
    public final Map<String, String> c() {
        String strE;
        d dVar = this.c;
        if (dVar == null) {
            return null;
        }
        d.b bVarB = dVar.b();
        HashMap map = new HashMap();
        for (Map.Entry<Integer, Integer> entry : bVarB.a.entrySet()) {
            map.put(d.a(entry.getKey().intValue()), d.c(entry.getValue().intValue()));
        }
        map.put("Connection check elapsed (ms)", String.valueOf(bVarB.b));
        int i = bVarB.c;
        switch (i) {
            case 0:
                strE = "Unknown";
                break;
            case 1:
                strE = "Ethernet";
                break;
            case 2:
                strE = "WiFi";
                break;
            case 3:
                strE = "2G";
                break;
            case 4:
                strE = "3G";
                break;
            case 5:
                strE = "4G";
                break;
            case 6:
                strE = "NONE";
                break;
            case 7:
                strE = "Bluetooth";
                break;
            case 8:
                strE = "5G";
                break;
            default:
                strE = gq2.e("Unknown connection type ", i);
                break;
        }
        map.put("Connection type", strE);
        return map;
    }
}
