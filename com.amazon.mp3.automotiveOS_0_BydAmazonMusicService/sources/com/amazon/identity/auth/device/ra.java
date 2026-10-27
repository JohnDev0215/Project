package com.amazon.identity.auth.device;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class ra {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f531a = "ra";
    public static String b;

    @SuppressLint({"NewApi"})
    public static synchronized String a(Context context) {
        if (qe.f(context)) {
            if (!qe.g(context) || t9.f(context) || Build.VERSION.SDK_INT < 26) {
                return null;
            }
            String str = f531a;
            Log.i(ga.a(str), "MAP Client side on FireOS 7+ calls IPC to get the DSN.");
            if (!TextUtils.isEmpty(b)) {
                return b;
            }
            try {
                p3 p3VarA = new o3(t5.a(context)).a(DeviceDataKeys.KEY_DEVICE_SERIAL_NUMBER);
                if (p3VarA != null) {
                    String str2 = p3VarA.f488a;
                    b = str2;
                    return str2;
                }
                Log.e(ga.a(str), "Cannot get device DSN from IPC");
            } catch (DeviceDataStoreException e) {
                Log.e(ga.a(f531a), "Cannot get device DSN", e);
            }
        }
        return null;
    }
}
