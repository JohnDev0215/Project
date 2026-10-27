package com.byd.sing;

import android.content.Context;
import android.provider.Settings;

/* JADX INFO: compiled from: GuideUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3244a = "f";

    public static boolean a(Context context) {
        int i = Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0);
        a.b(f3244a, "Provision status:" + i);
        return i == 1;
    }

    public static boolean b(Context context) {
        int i = Settings.Secure.getInt(context.getContentResolver(), "user_setup_complete", 0);
        a.b(f3244a, "Setup status:" + i);
        return i == 1;
    }
}
