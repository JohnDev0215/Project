package com.amazon.music.weblab;

import android.content.Context;
import android.os.Build;
import com.amazon.weblab.mobile.IMobileWeblabClient;
import com.amazon.weblab.mobile.settings.MobileWeblabOS;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
class WeblabClientFactory {
    WeblabClientFactory() {
    }

    public static IMobileWeblabClient create(Context context, WeblabData weblabData, List<String> list) {
        new VersionParser(context).getVersion();
        if ("Amazon".equals(Build.MANUFACTURER)) {
            MobileWeblabOS mobileWeblabOS = MobileWeblabOS.ANDROID;
        } else {
            MobileWeblabOS mobileWeblabOS2 = MobileWeblabOS.ANDROID;
        }
        Integer.toString(Build.VERSION.SDK_INT);
        throw null;
    }
}
