package com.byd.avm;

import android.content.Context;
import android.provider.Settings;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: GuideUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f2127a = "a";

    public static boolean a(Context context) {
        int i = Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0);
        BydLogHelper.logD(f2127a, "Provision status:" + i);
        return i == 1;
    }

    public static boolean b(Context context) {
        int i = Settings.Secure.getInt(context.getContentResolver(), "user_setup_complete", 0);
        BydLogHelper.logD(f2127a, "Setup status:" + i);
        return i == 1;
    }
}
