package com.amazon.identity.platform.setting;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.identity.auth.device.v5;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class PlatformSettings {
    public static PlatformSettings c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public v5 f714a = new v5();
    public Context b;

    /* JADX INFO: compiled from: DCP */
    public enum Namespace {
        DeviceGlobal,
        Default
    }

    public PlatformSettings(Context context) {
        this.b = context;
    }

    public static synchronized PlatformSettings a(Context context) {
        PlatformSettings platformSettings = c;
        if (platformSettings != null) {
            return platformSettings;
        }
        PlatformSettings platformSettings2 = new PlatformSettings(context);
        c = platformSettings2;
        return platformSettings2;
    }

    public Boolean a(String str, boolean z) {
        String strA = this.f714a.a(str);
        if (TextUtils.isEmpty(strA)) {
            return Boolean.valueOf(z);
        }
        return Boolean.valueOf(Boolean.parseBoolean(strA));
    }
}
