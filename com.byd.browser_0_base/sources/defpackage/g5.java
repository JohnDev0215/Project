package defpackage;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Pair;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.autofill.settings.AndroidPaymentAppPreference;
import org.chromium.chrome.browser.autofill.settings.AndroidPaymentAppsFragment;
import org.chromium.chrome.browser.payments.ServiceWorkerPaymentAppBridge;
import org.chromium.components.browser_ui.settings.TextMessagePreference;

/* JADX INFO: compiled from: AndroidPaymentAppsFragment.java */
/* JADX INFO: loaded from: classes.dex */
public final class g5 implements ServiceWorkerPaymentAppBridge.GetServiceWorkerPaymentAppsInfoCallback {
    public final /* synthetic */ AndroidPaymentAppsFragment a;

    public g5(AndroidPaymentAppsFragment androidPaymentAppsFragment) {
        this.a = androidPaymentAppsFragment;
    }

    @Override // org.chromium.chrome.browser.payments.ServiceWorkerPaymentAppBridge.GetServiceWorkerPaymentAppsInfoCallback
    public final void a(Map<String, Pair<String, Bitmap>> map) {
        HashMap map2 = new HashMap();
        new ut1();
        List<ResolveInfo> listC = wt1.c(new Intent("org.chromium.intent.action.PAY"), 0);
        if (!listC.isEmpty()) {
            for (ResolveInfo resolveInfo : listC) {
                CharSequence charSequenceLoadLabel = resolveInfo.loadLabel(xz.a.getPackageManager());
                if (!TextUtils.isEmpty(charSequenceLoadLabel)) {
                    map2.put(resolveInfo.activityInfo.packageName, new Pair(charSequenceLoadLabel.toString(), resolveInfo.loadIcon(xz.a.getPackageManager())));
                }
            }
        }
        int i = AndroidPaymentAppsFragment.h0;
        AndroidPaymentAppsFragment androidPaymentAppsFragment = this.a;
        androidPaymentAppsFragment.getClass();
        if (map2.isEmpty() && map.isEmpty()) {
            return;
        }
        for (Map.Entry entry : map2.entrySet()) {
            AndroidPaymentAppPreference androidPaymentAppPreference = new AndroidPaymentAppPreference(androidPaymentAppsFragment.a0.a);
            androidPaymentAppPreference.T((CharSequence) ((Pair) entry.getValue()).first);
            androidPaymentAppPreference.L((Drawable) ((Pair) entry.getValue()).second);
            androidPaymentAppsFragment.I0().Y(androidPaymentAppPreference);
        }
        for (Map.Entry<String, Pair<String, Bitmap>> entry2 : map.entrySet()) {
            AndroidPaymentAppPreference androidPaymentAppPreference2 = new AndroidPaymentAppPreference(androidPaymentAppsFragment.a0.a);
            androidPaymentAppPreference2.T((CharSequence) entry2.getValue().first);
            androidPaymentAppPreference2.R(entry2.getKey());
            androidPaymentAppPreference2.L(entry2.getValue().second == null ? new ColorDrawable(0) : new BitmapDrawable(androidPaymentAppsFragment.N(), (Bitmap) entry2.getValue().second));
            androidPaymentAppsFragment.I0().Y(androidPaymentAppPreference2);
        }
        TextMessagePreference textMessagePreference = new TextMessagePreference(androidPaymentAppsFragment.a0.a, null);
        textMessagePreference.S(R$string.payment_apps_usage_message);
        textMessagePreference.Y(false);
        androidPaymentAppsFragment.I0().Y(textMessagePreference);
    }
}
