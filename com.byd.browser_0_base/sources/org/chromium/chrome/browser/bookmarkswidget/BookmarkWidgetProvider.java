package org.chromium.chrome.browser.bookmarkswidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;
import com.google.android.apps.chrome.appwidget.bookmarks.BookmarkThumbnailWidgetProvider;
import defpackage.mh;
import defpackage.q11;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;

/* JADX INFO: loaded from: classes.dex */
public class BookmarkWidgetProvider extends AppWidgetProvider {
    public static final /* synthetic */ int a = 0;

    public static String a(Context context) {
        return context.getPackageName() + ".BOOKMARK_APPWIDGET_UPDATE";
    }

    public static void b(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        for (int i : iArr) {
            Intent intent = new Intent(context, (Class<?>) BookmarkWidgetService.class);
            intent.putExtra("appWidgetId", i);
            intent.setData(Uri.parse(intent.toUri(1)));
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), appWidgetManager.getAppWidgetOptions(i).getInt("appWidgetMinWidth") < 110 ? R$layout.bookmark_widget_icons_only : R$layout.bookmark_widget);
            int i2 = R$id.bookmarks_list;
            remoteViews.setRemoteAdapter(i2, intent);
            appWidgetManager.notifyAppWidgetViewDataChanged(i, i2);
            Intent intent2 = new Intent(context, (Class<?>) BookmarkWidgetProxy.class);
            q11.a(intent2);
            intent2.setFlags(268435456);
            remoteViews.setPendingIntentTemplate(i2, PendingIntent.getActivity(context, 0, intent2, q11.d(true) | 134217728));
            appWidgetManager.updateAppWidget(i, remoteViews);
        }
    }

    @Override // android.appwidget.AppWidgetProvider
    public final void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int i, Bundle bundle) {
        b(context, appWidgetManager, new int[]{i});
    }

    @Override // android.appwidget.AppWidgetProvider
    public final void onDeleted(Context context, int[] iArr) {
        super.onDeleted(context, iArr);
        for (int i : iArr) {
            mh.b(i);
        }
        for (int i2 : AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context, (Class<?>) BookmarkThumbnailWidgetProvider.class))) {
            mh.b(i2);
        }
    }

    @Override // android.appwidget.AppWidgetProvider
    public final void onDisabled(Context context) {
        super.onDisabled(context);
        for (int i : AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context, (Class<?>) BookmarkThumbnailWidgetProvider.class))) {
            mh.b(i);
        }
    }

    @Override // android.appwidget.AppWidgetProvider, android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (!a(context).equals(intent.getAction())) {
            super.onReceive(context, intent);
            return;
        }
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        if (intent.hasExtra("appWidgetId")) {
            b(context, appWidgetManager, new int[]{q11.l(intent, "appWidgetId", -1)});
        } else {
            b(context, appWidgetManager, appWidgetManager.getAppWidgetIds(new ComponentName(context, (Class<?>) BookmarkThumbnailWidgetProvider.class)));
        }
    }

    @Override // android.appwidget.AppWidgetProvider
    public final void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        super.onUpdate(context, appWidgetManager, iArr);
        b(context, appWidgetManager, iArr);
    }
}
