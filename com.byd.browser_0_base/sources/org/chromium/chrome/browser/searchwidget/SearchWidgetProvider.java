package org.chromium.chrome.browser.searchwidget;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import defpackage.ku2;
import defpackage.q11;
import defpackage.xz;
import defpackage.zf2;
import java.util.function.Consumer;
import org.chromium.chrome.R$anim;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: loaded from: classes2.dex */
public class SearchWidgetProvider extends AppWidgetProvider {
    public static final Object a = new Object();

    @SuppressLint({"StaticFieldLeak"})
    public static SearchWidgetProviderDelegate b;

    public static class SearchWidgetProviderDelegate implements Consumer<zf2.a> {
        public final Context a;
        public final AppWidgetManager b;

        public SearchWidgetProviderDelegate(Context context) {
            context = context == null ? xz.a : context;
            this.a = context;
            this.b = AppWidgetManager.getInstance(context);
        }

        @Override // java.util.function.Consumer
        public final void accept(zf2.a aVar) {
            SearchWidgetProvider.c(null, aVar);
        }
    }

    public static PendingIntent a(Context context, boolean z) {
        Intent intent = new Intent(z ? "org.chromium.chrome.browser.ui.searchactivityutils.ACTION_START_VOICE_SEARCH" : "org.chromium.chrome.browser.ui.searchactivityutils.ACTION_START_TEXT_SEARCH");
        intent.setClass(context, SearchActivity.class);
        intent.addFlags(268435456);
        intent.addFlags(524288);
        intent.putExtra("org.chromium.chrome.browser.searchwidget.FROM_SEARCH_WIDGET", true);
        return PendingIntent.getActivity(context, 0, intent, 134217728 | q11.d(false), ActivityOptions.makeCustomAnimation(context, R$anim.activity_open_enter, 0).toBundle());
    }

    public static SearchWidgetProviderDelegate b() {
        synchronized (a) {
            if (b == null) {
                b = new SearchWidgetProviderDelegate(null);
            }
        }
        return b;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0083  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void c(int[] r11, zf2.a r12) {
        /*
            org.chromium.chrome.browser.searchwidget.SearchWidgetProvider$SearchWidgetProviderDelegate r0 = b()
            r1 = 0
            if (r11 != 0) goto L1f
            android.appwidget.AppWidgetManager r11 = r0.b
            if (r11 != 0) goto Le
            int[] r11 = new int[r1]
            goto L1f
        Le:
            android.content.ComponentName r2 = new android.content.ComponentName
            java.lang.Class<org.chromium.chrome.browser.searchwidget.SearchWidgetProvider> r3 = org.chromium.chrome.browser.searchwidget.SearchWidgetProvider.class
            java.lang.String r3 = r3.getName()
            android.content.Context r4 = r0.a
            r2.<init>(r4, r3)
            int[] r11 = r11.getAppWidgetIds(r2)
        L1f:
            if (r12 != 0) goto L27
            zf2 r12 = defpackage.zf2.a()
            zf2$a r12 = r12.f
        L27:
            int r2 = r11.length
            r3 = r1
        L29:
            if (r3 >= r2) goto L98
            r4 = r11[r3]
            android.content.Context r5 = r0.a
            java.lang.String r6 = r12.a
            android.widget.RemoteViews r7 = new android.widget.RemoteViews
            java.lang.String r8 = r5.getPackageName()
            int r9 = org.chromium.chrome.R$layout.search_widget_template
            r7.<init>(r8, r9)
            int r8 = org.chromium.chrome.R$id.text_container
            android.app.PendingIntent r9 = a(r5, r1)
            r7.setOnClickPendingIntent(r8, r9)
            int r8 = org.chromium.chrome.R$id.microphone_icon
            r9 = 1
            android.app.PendingIntent r10 = a(r5, r9)
            r7.setOnClickPendingIntent(r8, r10)
            boolean r10 = r12.c
            if (r10 == 0) goto L55
            r10 = r1
            goto L57
        L55:
            r10 = 8
        L57:
            r7.setViewVisibility(r8, r10)
            boolean r8 = android.text.TextUtils.isEmpty(r6)
            if (r8 != 0) goto L83
            boolean r8 = defpackage.gn0.b(r1, r1)
            r8 = r8 ^ r9
            org.chromium.chrome.browser.locale.LocaleManager r10 = org.chromium.chrome.browser.locale.LocaleManager.getInstance()
            boolean r10 = r10.a()
            r10 = r10 ^ r9
            if (r8 == 0) goto L74
            if (r10 == 0) goto L74
            r8 = r9
            goto L75
        L74:
            r8 = r1
        L75:
            if (r8 != 0) goto L78
            goto L83
        L78:
            int r8 = org.chromium.chrome.R$string.search_with_product
            java.lang.Object[] r9 = new java.lang.Object[r9]
            r9[r1] = r6
            java.lang.String r5 = r5.getString(r8, r9)
            goto L89
        L83:
            int r6 = org.chromium.chrome.R$string.search_widget_default
            java.lang.String r5 = r5.getString(r6)
        L89:
            int r6 = org.chromium.chrome.R$id.title
            java.lang.String r8 = "setHint"
            r7.setCharSequence(r6, r8, r5)
            android.appwidget.AppWidgetManager r5 = r0.b
            r5.updateAppWidget(r4, r7)
            int r3 = r3 + 1
            goto L29
        L98:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.searchwidget.SearchWidgetProvider.c(int[], zf2$a):void");
    }

    @SuppressLint({"ApplySharedPref", "CommitPrefEdits"})
    public static void d(int i) {
        b().getClass();
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        if (sharedPreferencesManager.d(0, "org.chromium.chrome.browser.searchwidget.NUM_CONSECUTIVE_CRASHES") == i) {
            return;
        }
        sharedPreferencesManager.a.getClass();
        SharedPreferences.Editor editorEdit = xz.a.a.edit();
        editorEdit.putInt("org.chromium.chrome.browser.searchwidget.NUM_CONSECUTIVE_CRASHES", i);
        ku2 ku2VarC = ku2.C();
        try {
            editorEdit.commit();
            ku2VarC.close();
        } catch (Throwable th) {
            try {
                ku2VarC.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    @Override // android.appwidget.AppWidgetProvider
    public final void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) throws Exception {
        try {
            c(iArr, null);
            d(0);
        } catch (Exception e) {
            b().getClass();
            int iD = SharedPreferencesManager.getInstance().d(0, "org.chromium.chrome.browser.searchwidget.NUM_CONSECUTIVE_CRASHES") + 1;
            d(iD);
            if (iD >= 3) {
                throw e;
            }
            Log.e("cr_searchwidget", "Absorbing exception caught when attempting to launch widget.", e);
        }
    }
}
