package org.chromium.chrome.browser.dom_distiller;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import defpackage.n3;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$style;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes.dex */
public final class DomDistillerUIUtils {
    @CalledByNative
    public static void openSettings(WebContents webContents) {
        WindowAndroid windowAndroidV;
        Activity activity = (webContents == null || (windowAndroidV = webContents.V()) == null) ? null : (Activity) windowAndroidV.k().get();
        if (webContents == null || activity == null) {
            return;
        }
        n3.X0("DomDistiller_DistilledPagePrefsOpened");
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R$style.ThemeOverlay_BrowserUI_AlertDialog);
        int i = DistilledPagePrefsView.l;
        builder.a.r = (DistilledPagePrefsView) LayoutInflater.from(activity).inflate(DistilledPagePrefsView.l, (ViewGroup) null);
        builder.k();
    }
}
