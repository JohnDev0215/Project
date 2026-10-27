package org.chromium.components.webxr;

import android.app.Activity;
import android.content.Context;
import defpackage.aq1;
import defpackage.c8;
import defpackage.xz;
import java.util.HashMap;
import org.chromium.base.BundleUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes3.dex */
public class ArCoreJavaUtils {
    public static ArCoreJavaUtils c;
    public static final aq1<Boolean> d = new aq1<>();
    public long a;
    public c8 b;

    public ArCoreJavaUtils(long j) {
        this.a = j;
    }

    @CalledByNative
    public static ArCoreJavaUtils create(long j) {
        Object obj = ThreadUtils.a;
        return new ArCoreJavaUtils(j);
    }

    @CalledByNative
    public static Context getApplicationContext() {
        return xz.a;
    }

    @CalledByNative
    public static String getArCoreShimLibraryPath() {
        return BundleUtils.getNativeLibraryPath("arcore_sdk_c", "");
    }

    @CalledByNative
    public final void endSession() {
        c8 c8Var = this.b;
        if (c8Var == null) {
            return;
        }
        c8Var.a();
        this.b = null;
        c = null;
        d.r(Boolean.FALSE);
    }

    @CalledByNative
    public final void onNativeDestroy() {
        this.a = 0L;
    }

    @CalledByNative
    public final void startSession(ArCompositorDelegateProvider arCompositorDelegateProvider, WebContents webContents, boolean z, boolean z2) {
        WindowAndroid windowAndroidV;
        this.b = new c8();
        c = this;
        d.r(Boolean.TRUE);
        c8 c8Var = this.b;
        arCompositorDelegateProvider.a();
        c8Var.e = this;
        c8Var.j = webContents;
        c8Var.f = (webContents == null || (windowAndroidV = webContents.V()) == null) ? null : (Activity) windowAndroidV.k().get();
        c8Var.l = new HashMap<>();
        c8Var.m = null;
        c8Var.k = z;
        throw null;
    }
}
