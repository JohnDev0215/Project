package org.chromium.ui.base;

import android.content.Context;
import defpackage.xz;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.ui.R$integer;

/* JADX INFO: loaded from: classes3.dex */
public class DeviceFormFactor {
    public static boolean a(Context context) {
        return context.getResources().getInteger(R$integer.min_screen_width_bucket) >= 2;
    }

    public static boolean b(WindowAndroid windowAndroid) {
        Object obj = ThreadUtils.a;
        Context context = windowAndroid.i.get();
        return (context == null ? 0 : context.getResources().getInteger(R$integer.min_screen_width_bucket)) >= 2;
    }

    @Deprecated
    @CalledByNative
    public static boolean isTablet() {
        return xz.a.getResources().getInteger(R$integer.min_screen_width_bucket) >= 2;
    }
}
