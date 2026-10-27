package org.chromium.chrome.browser.price_tracking;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import defpackage.a8;
import defpackage.bq;
import defpackage.q11;
import defpackage.sk;
import defpackage.so1;
import defpackage.sp;
import defpackage.u32;
import defpackage.xz;
import defpackage.zm;
import java.util.Collections;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.document.ChromeLauncherActivity;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.price_tracking.PriceDropNotificationManagerImpl;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;
import org.chromium.components.commerce.core.ShoppingService;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public final class PriceDropNotificationManagerImpl implements u32 {
    public final Context a;
    public final so1 b;
    public final SharedPreferencesManager c = SharedPreferencesManager.getInstance();

    public static class DismissNotificationChromeActivity extends ChromeLauncherActivity {
        @Override // org.chromium.chrome.browser.document.ChromeLauncherActivity, android.app.Activity
        public final void onCreate(Bundle bundle) {
            new NotificationManagerProxyImpl(xz.a).d(q11.l(getIntent(), "org.chromium.chrome.browser.price_tracking.NOTIFICATION_ID", 0), "price_drop");
            super.onCreate(bundle);
            finish();
        }
    }

    public static class TrampolineActivity extends Activity {
        public static final /* synthetic */ int e = 0;

        @Override // android.app.Activity
        public final void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            Intent intent = getIntent();
            final String strR = q11.r(intent, "org.chromium.chrome.browser.price_tracking.DESTINATION_URL");
            final String strR2 = q11.r(intent, "org.chromium.chrome.browser.price_tracking.ACTION_ID");
            final String strR3 = q11.r(intent, "org.chromium.chrome.browser.price_tracking.OFFER_ID");
            final String strR4 = q11.r(intent, "org.chromium.chrome.browser.price_tracking.PRODUCT_CLUSTER_ID");
            new NotificationManagerProxyImpl(xz.a).d(q11.l(intent, "org.chromium.chrome.browser.price_tracking.NOTIFICATION_ID", 0), "price_drop");
            if (!TextUtils.isEmpty(strR3)) {
                sp.a().f(new Runnable(strR2, strR, strR3, strR4) { // from class: w32
                    public final /* synthetic */ String f;
                    public final /* synthetic */ String g;
                    public final /* synthetic */ String h;

                    {
                        this.g = strR3;
                        this.h = strR4;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        String str = this.g;
                        String str2 = this.h;
                        int i = PriceDropNotificationManagerImpl.TrampolineActivity.e;
                        PriceDropNotificationManagerImpl.TrampolineActivity trampolineActivity = this.e;
                        trampolineActivity.getClass();
                        n3.B();
                        String str3 = this.f;
                        str3.equals("visit_site");
                        if (str3.equals("turn_off_alert") && (str != null || str2 != null)) {
                            ShoppingService shoppingServiceY = n3.Y(Profile.d());
                            ps psVar = new ps(8);
                            BookmarkModel bookmarkModelV = BookmarkModel.v(Profile.d());
                            xi xiVar = new xi(str, shoppingServiceY, psVar, str2, 2);
                            if (bookmarkModelV.c) {
                                xiVar.run();
                            } else {
                                bookmarkModelV.c(new v32(xiVar, bookmarkModelV));
                            }
                        }
                        trampolineActivity.finish();
                    }
                });
            } else {
                Log.e("cr_PriceDropNotif", "No offer id is provided when handling turn off alert action.");
                finish();
            }
        }
    }

    public PriceDropNotificationManagerImpl(Context context, NotificationManagerProxyImpl notificationManagerProxyImpl) {
        this.a = context;
        this.b = notificationManagerProxyImpl;
    }

    public final boolean a() {
        return this.b.f();
    }

    public final void b() {
        so1 so1Var = this.b;
        if (so1Var.g("shopping_price_drop_alerts_default") != null) {
            return;
        }
        new zm(so1Var, this.a.getResources()).a(Collections.emptyList(), Collections.singletonList("shopping_price_drop_alerts_default"));
    }

    public final Intent c(int i, String str) {
        Intent data = new Intent().setAction("android.intent.action.VIEW").setData(Uri.parse(str));
        Context context = this.a;
        Intent intentPutExtra = data.setClass(context, DismissNotificationChromeActivity.class).addFlags(268959744).putExtra("com.android.browser.application_id", context.getPackageName()).putExtra("REUSE_URL_MATCHING_TAB_ELSE_NEW_TAB", true).putExtra("org.chromium.chrome.browser.price_tracking.NOTIFICATION_ID", i);
        q11.a(intentPutExtra);
        return intentPutExtra;
    }

    public final int d(int i, boolean z) {
        SharedPreferencesManager sharedPreferencesManager = this.c;
        long jCurrentTimeMillis = System.currentTimeMillis();
        JSONArray jSONArray = new JSONArray();
        String strG = "";
        try {
            if (i == 32) {
                strG = sharedPreferencesManager.g("Chrome.PriceTracking.ChromeManagedNotificationsTimestamps", "");
            } else if (i == 33) {
                strG = sharedPreferencesManager.g("Chrome.PriceTracking.UserManagedNotificationsTimestamps", "");
            }
            JSONArray jSONArray2 = new JSONArray(strG);
            int i2 = 0;
            while (i2 < jSONArray2.length()) {
                long j = jSONArray2.getLong(i2);
                long j2 = jCurrentTimeMillis - j;
                int i3 = i2;
                int millis = (int) TimeUnit.DAYS.toMillis(1L);
                if (a8.P()) {
                    sk skVar = bq.a;
                    millis = N.M37SqSAy("CommercePriceTracking", "notification_timestamps_store_window_ms", millis);
                }
                if (j2 <= millis) {
                    jSONArray.put(j);
                }
                i2 = i3 + 1;
            }
        } catch (JSONException e) {
            Log.e("cr_PriceDropNotif", String.format(Locale.US, "Failed to parse notification timestamps. Details: %s", e.getMessage()));
            jSONArray = new JSONArray();
        }
        if (z) {
            jSONArray.put(jCurrentTimeMillis);
        }
        String string = jSONArray.toString();
        if (i == 32) {
            sharedPreferencesManager.o("Chrome.PriceTracking.ChromeManagedNotificationsTimestamps", string);
        } else if (i == 33) {
            sharedPreferencesManager.o("Chrome.PriceTracking.UserManagedNotificationsTimestamps", string);
        }
        return jSONArray.length();
    }
}
