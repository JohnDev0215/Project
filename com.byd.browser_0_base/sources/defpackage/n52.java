package defpackage;

import J.N;
import android.util.Log;

/* JADX INFO: compiled from: ProbabilisticCryptidRenderer.java */
/* JADX INFO: loaded from: classes.dex */
public final class n52 {
    public static final n52 a = new n52();

    public static long a(long j, String str) {
        sk skVar = bq.a;
        String strMMltG$kc = N.MMltG$kc("ProbabilisticCryptidRenderer", str);
        try {
            if (strMMltG$kc.length() > 0) {
                return Long.parseLong(strMMltG$kc);
            }
        } catch (NumberFormatException e) {
            Log.e("cr_ProbabilisticCryptid", String.format("Invalid long value %s for param %s", strMMltG$kc, str), e);
        }
        return j;
    }
}
