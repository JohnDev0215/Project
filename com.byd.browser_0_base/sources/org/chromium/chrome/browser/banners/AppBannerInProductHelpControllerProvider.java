package org.chromium.chrome.browser.banners;

import defpackage.aj3;
import defpackage.gq2;
import defpackage.i6;
import defpackage.oj2;
import defpackage.xe3;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes.dex */
public class AppBannerInProductHelpControllerProvider {
    public static final aj3<i6> a = new aj3<>(i6.class);

    @CalledByNative
    public static String showInProductHelp(WebContents webContents) {
        xe3 xe3VarT = oj2.t(Profile.b(webContents));
        if (!xe3VarT.wouldTriggerHelpUI("IPH_PwaInstallAvailableFeature")) {
            return gq2.e("Trigger state: ", xe3VarT.getTriggerState("IPH_PwaInstallAvailableFeature"));
        }
        if (webContents.r() != 2) {
            return "Not visible";
        }
        WindowAndroid windowAndroidV = webContents.V();
        if (windowAndroidV == null) {
            return "No window";
        }
        i6 i6Var = (i6) a.e(windowAndroidV.r);
        if (i6Var == null) {
            return "No controller";
        }
        i6Var.a();
        return "";
    }
}
