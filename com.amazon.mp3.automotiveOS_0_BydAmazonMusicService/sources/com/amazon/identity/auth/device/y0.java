package com.amazon.identity.auth.device;

import android.content.Context;
import android.util.Log;
import com.amazon.identity.auth.accounts.AmazonAccountManager;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class y0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f666a = "com.amazon.identity.auth.device.y0";

    public static synchronized x0 a(Context context) {
        t5 t5VarA = t5.a(context.getApplicationContext());
        if (qe.b(((i5) t5VarA.getSystemService("sso_platform")).f382a)) {
            Log.i(ga.a(f666a), "Returning Profile multiple profile settings");
            return new b1(t5VarA, z0.a(t5VarA), new b6(t5VarA), new AmazonAccountManager(t5VarA));
        }
        Log.i(ga.a(f666a), "Returning Default multiple profile settings");
        return new o0(t5VarA, z0.a(t5VarA));
    }
}
