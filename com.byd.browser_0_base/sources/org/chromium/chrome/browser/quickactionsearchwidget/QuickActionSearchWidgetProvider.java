package org.chromium.chrome.browser.quickactionsearchwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.SizeF;
import android.widget.RemoteViews;
import defpackage.q11;
import defpackage.u82;
import defpackage.xz;
import defpackage.zf2;
import java.util.ArrayList;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.document.ChromeLauncherActivity;
import org.chromium.chrome.browser.searchwidget.SearchActivity;

/* JADX INFO: loaded from: classes2.dex */
public abstract class QuickActionSearchWidgetProvider extends AppWidgetProvider {
    public static u82 a;

    public static class QuickActionSearchWidgetProviderDino extends QuickActionSearchWidgetProvider {
        @Override // org.chromium.chrome.browser.quickactionsearchwidget.QuickActionSearchWidgetProvider
        public final RemoteViews a(Context context, zf2.a aVar, int i, int i2) {
            return QuickActionSearchWidgetProvider.b().b(context, i, i2);
        }
    }

    public static class QuickActionSearchWidgetProviderSearch extends QuickActionSearchWidgetProvider {
        @Override // org.chromium.chrome.browser.quickactionsearchwidget.QuickActionSearchWidgetProvider
        public final RemoteViews a(Context context, zf2.a aVar, int i, int i2) {
            u82 u82VarB = QuickActionSearchWidgetProvider.b();
            u82.b bVar = u82VarB.e;
            if (i2 < bVar.c) {
                bVar = u82VarB.f;
            } else {
                u82.b bVar2 = u82VarB.d;
                if (i2 >= bVar2.c) {
                    bVar = bVar2;
                }
            }
            RemoteViews remoteViewsE = u82VarB.e(context, bVar.a);
            u82.a(remoteViewsE, aVar, bVar, i);
            return remoteViewsE;
        }
    }

    public static u82 b() {
        u82 u82Var = a;
        if (u82Var != null) {
            return u82Var;
        }
        Context context = xz.a;
        ComponentName componentName = new ComponentName(context, (Class<?>) SearchActivity.class);
        Intent intentB = b.b(context, true);
        intentB.putExtra("com.android.chrome.invoked_from_app_widget", true);
        intentB.addFlags(268959744);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("chrome://dino/"));
        intent.setComponent(new ComponentName(context, (Class<?>) ChromeLauncherActivity.class));
        intent.putExtra("REUSE_URL_MATCHING_TAB_ELSE_NEW_TAB", true);
        intent.putExtra("com.android.chrome.invoked_from_app_widget", true);
        intent.setFlags(268959744);
        q11.a(intent);
        u82 u82Var2 = new u82(context, componentName, intentB, intent);
        a = u82Var2;
        return u82Var2;
    }

    public abstract RemoteViews a(Context context, zf2.a aVar, int i, int i2);

    public final void c(Context context, AppWidgetManager appWidgetManager, zf2.a aVar, int[] iArr) {
        RemoteViews remoteViews;
        ArrayList<SizeF> parcelableArrayList;
        if (iArr == null) {
            iArr = appWidgetManager.getAppWidgetIds(new ComponentName(context, getClass().getName()));
        }
        for (int i : iArr) {
            Bundle appWidgetOptions = appWidgetManager.getAppWidgetOptions(i);
            if (Build.VERSION.SDK_INT < 31 || (parcelableArrayList = appWidgetOptions.getParcelableArrayList("appWidgetSizes")) == null || parcelableArrayList.isEmpty()) {
                remoteViews = null;
            } else {
                ArrayMap arrayMap = new ArrayMap();
                for (SizeF sizeF : parcelableArrayList) {
                    arrayMap.put(sizeF, a(context, aVar, (int) sizeF.getWidth(), (int) sizeF.getHeight()));
                }
                remoteViews = new RemoteViews(arrayMap);
            }
            if (remoteViews == null) {
                remoteViews = new RemoteViews(a(context, aVar, appWidgetOptions.getInt("appWidgetMaxWidth"), appWidgetOptions.getInt("appWidgetMinHeight")), a(context, aVar, appWidgetOptions.getInt("appWidgetMinWidth"), appWidgetOptions.getInt("appWidgetMaxHeight")));
            }
            appWidgetManager.updateAppWidget(i, remoteViews);
        }
    }

    @Override // android.appwidget.AppWidgetProvider
    public final void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int i, Bundle bundle) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, i, bundle);
        onUpdate(context, appWidgetManager, new int[]{i});
    }

    @Override // android.appwidget.AppWidgetProvider
    public final void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        c(context, appWidgetManager, zf2.a().f, iArr);
    }
}
