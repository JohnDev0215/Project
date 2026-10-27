package org.chromium.chrome.browser.safe_browsing;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import defpackage.q11;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.safe_browsing.settings.SafeBrowsingSettingsFragment;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes2.dex */
public class SafeBrowsingSettingsLauncher {
    @CalledByNative
    public static void showSafeBrowsingSettings(WebContents webContents, int i) {
        WindowAndroid windowAndroidV = webContents.V();
        if (windowAndroidV == null) {
            return;
        }
        Context context = windowAndroidV.i.get();
        int i2 = SafeBrowsingSettingsFragment.l0;
        Bundle bundle = new Bundle();
        bundle.putInt("SafeBrowsingSettingsFragment.AccessPoint", i);
        String name = SafeBrowsingSettingsFragment.class.getName();
        Intent intent = new Intent();
        intent.setClass(context, SettingsActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
            intent.addFlags(67108864);
        }
        intent.putExtra("show_fragment", name);
        intent.putExtra("show_fragment_args", bundle);
        ComponentName componentName = q11.a;
        try {
            context.startActivity(intent, null);
        } catch (ActivityNotFoundException unused) {
        }
    }
}
