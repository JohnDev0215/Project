package org.chromium.chrome.browser.feedback;

import J.N;
import defpackage.em0;
import defpackage.gq2;
import defpackage.w8;
import java.util.HashMap;
import java.util.Map;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class ProcessIdFeedbackSource implements w8 {
    public static final HashMap c;
    public HashMap a;
    public boolean b;

    static {
        HashMap map = new HashMap();
        c = map;
        map.put("renderer", 3);
        map.put("utility", 6);
        map.put("gpu-process", 9);
    }

    @Override // defpackage.w8
    public final boolean a() {
        return this.b;
    }

    @Override // defpackage.w8
    public final void b(em0 em0Var) {
        N.Mx7ChZtk(this);
    }

    @Override // defpackage.gm0
    public final Map<String, String> c() {
        return this.a;
    }

    @CalledByNative
    public final void prepareCompleted(long j) {
        this.a = new HashMap();
        for (Map.Entry entry : c.entrySet()) {
            long[] jArrMKHKXOCT = N.MKHKXOCT(j, this, ((Integer) entry.getValue()).intValue());
            if (jArrMKHKXOCT.length != 0) {
                StringBuilder sb = new StringBuilder();
                for (long j2 : jArrMKHKXOCT) {
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    sb.append(String.valueOf(j2));
                }
                this.a.put(gq2.g("Process IDs (", (String) entry.getKey(), ")"), sb.toString());
            }
        }
        this.a.put("Process IDs (browser)", Long.toString(N.MXN_TQGn()));
        this.b = true;
    }
}
