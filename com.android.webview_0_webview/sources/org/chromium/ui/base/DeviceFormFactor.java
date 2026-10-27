package org.chromium.ui.base;

import android.content.Context;
import defpackage.AbstractC0389Vm;
import defpackage.Is;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class DeviceFormFactor {
    public static boolean a(WindowAndroid windowAndroid) {
        ThreadUtils.b();
        Context context = (Context) windowAndroid.S().get();
        return (context == null ? 0 : context.getResources().getInteger(AbstractC0389Vm.o)) >= 2;
    }

    @Deprecated
    public static boolean isTablet() {
        return Is.a.getResources().getInteger(AbstractC0389Vm.o) >= 2;
    }
}
