package com.amazon.identity.auth.device;

import android.content.Context;
import android.util.Log;
import com.amazon.identity.auth.device.features.Feature;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f307a = "com.amazon.identity.auth.device.f0";

    public static boolean a(Context context, String str) {
        return !z9.a(context, str);
    }

    public static boolean b(Context context, String str) {
        if (a4.a(new b4(context)).a(Feature.OverrideDeviceAttributes)) {
            return ia.a(context, str, "MAPDeviceAttributeRuntimeOverride", false).booleanValue();
        }
        return false;
    }

    public static boolean a(Context context, u7 u7Var, String str, String str2) {
        if (!a(context, str2)) {
            Log.e(ga.a(f307a), String.format("%s is not a child application", str2));
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(".");
        sb.append("is_registered_key");
        return u7Var.d(str, sb.toString()) != null;
    }
}
