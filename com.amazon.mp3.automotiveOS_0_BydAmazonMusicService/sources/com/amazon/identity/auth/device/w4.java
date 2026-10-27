package com.amazon.identity.auth.device;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class w4 extends l7 {
    public static final String c = "com.amazon.identity.auth.device.w4";
    public static byte[] d;
    public final Context b;

    public w4(Context context) {
        this.b = t5.a(context.getApplicationContext());
    }

    @Override // com.amazon.identity.auth.device.l7
    public synchronized byte[] b() {
        byte[] bArrDecode;
        if (d == null) {
            String strA = u4.a(this.b).a();
            if (strA == null) {
                Log.e(ga.a(c), "Could not generate a MAP only encryption key. Aborting.");
                bArrDecode = null;
            } else {
                bArrDecode = Base64.decode(strA, 0);
            }
            d = bArrDecode;
        }
        return d;
    }
}
