package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class q2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f506a = "com.amazon.identity.auth.device.q2";

    public static Set<String> a(Context context, String str) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        try {
            for (Signature signature : context.getPackageManager().getPackageInfo(str, 64).signatures) {
                linkedHashSet.add(Base64.encodeToString(signature.toByteArray(), 2));
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e(ga.a(f506a), "NameNotFoundException");
        } catch (SecurityException unused2) {
            Log.e(ga.a(f506a), "SecurityException");
        }
        return linkedHashSet;
    }
}
