package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class j6 implements i6 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f394a = "j6";

    public boolean a(Context context, Bundle bundle) {
        String string = bundle.getString("central_package_name");
        if (TextUtils.equals(string, context.getPackageName())) {
            ga.a(f394a);
            return true;
        }
        boolean z = bundle.getBoolean("ignore_isolation_mode");
        String str = f394a;
        Log.i(ga.a(str), "Current package is: " + context.getPackageName() + ". Going to check central package's signature:" + string + ", since it is AOSP MAP library");
        if (new z5(context, z).b(string)) {
            return true;
        }
        Log.e(ga.a(str), "Central package signature check failed! This probably means someone is squatting or a platform bug in the signature check. The account authenticator's package name is: " + string);
        return false;
    }
}
