package com.byd.bluetoothcall.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;
import c.a.b.a;
import c.a.b.b;
import com.byd.bluetoothcall.f.f;
import com.byd.bluetoothcall.view.BlueToothApplication;
import com.byd.bluetoothcall.view.activity.MainActivity;
import com.bydux.api.R;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes.dex */
public class WidgetProvide extends AppWidgetProvider {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static RemoteViews f3496a;

    private static PendingIntent a(Context context, int i) {
        Intent intent = new Intent();
        intent.setClass(context, WidgetProvide.class);
        intent.addCategory("android.intent.category.SELECTED_ALTERNATIVE");
        intent.setData(Uri.parse("hx:" + i));
        return PendingIntent.getBroadcast(context, 0, intent, 134217728);
    }

    private static void b(Context context) {
        f3496a.setOnClickPendingIntent(R.id.ts_connected_widget_layout, d(context));
        f3496a.setOnClickPendingIntent(R.id.ts_disconnect_widget_layout, d(context));
        f3496a.setOnClickPendingIntent(R.id.bt_widget_connect_icon, d(context));
        f3496a.setOnClickPendingIntent(R.id.ts_disconnect_widget_click_1, d(context));
        f3496a.setOnClickPendingIntent(R.id.ts_disconnect_widget_click_2, d(context));
        try {
            f();
            g();
        } catch (Exception unused) {
        }
    }

    private static void c(Context context) {
        f3496a.setOnClickPendingIntent(R.id.connected_widget_layout, d(context));
        f3496a.setOnClickPendingIntent(R.id.disconnect_widget_layout, d(context));
        f3496a.setOnClickPendingIntent(R.id.bt_widget_connect_icon, d(context));
        f3496a.setOnClickPendingIntent(R.id.disconnect_widget_click_1, d(context));
        f3496a.setOnClickPendingIntent(R.id.disconnect_widget_click_2, d(context));
        try {
            h();
            i();
        } catch (Exception unused) {
        }
    }

    private static PendingIntent d(Context context) {
        return PendingIntent.getActivity(context, 0, new Intent(context, (Class<?>) MainActivity.class), 134217728);
    }

    public static RemoteViews e(Context context) {
        f3496a = new RemoteViews(context.getPackageName(), R.layout.bt_widget_layout);
        if (f.l(context) == 1) {
            f3496a.setViewVisibility(R.id.ts_bt_widget, 0);
            f3496a.setViewVisibility(R.id.tang_bt_widget, 8);
            b(context);
        } else {
            f3496a.setViewVisibility(R.id.tang_bt_widget, 0);
            f3496a.setViewVisibility(R.id.ts_bt_widget, 8);
            c(context);
        }
        return f3496a;
    }

    public static void f() {
        Context applicationContext = BlueToothApplication.a().getApplicationContext();
        RemoteViews remoteViews = f3496a;
        if (remoteViews == null || applicationContext == null) {
            return;
        }
        remoteViews.setImageViewBitmap(R.id.ts_bt_widget_notconnect_icon, ((BitmapDrawable) SkinManager.getInstance().getDrawable(R.drawable.denza_bt_widget_notconnect_icon)).getBitmap());
        f3496a.setImageViewBitmap(R.id.ts_bt_widget_connect_icon, ((BitmapDrawable) SkinManager.getInstance().getDrawable(R.drawable.denza_bt_widget_notconnect_icon)).getBitmap());
        if (b.f0().n0() == 2) {
            f3496a.setViewVisibility(R.id.ts_disconnect_widget_layout, 8);
            f3496a.setViewVisibility(R.id.ts_connected_widget_layout, 0);
            f3496a.setOnClickPendingIntent(R.id.ts_bt_widget_2_calllog, a(applicationContext, R.id.bt_widget_2_calllog));
            f3496a.setOnClickPendingIntent(R.id.ts_bt_widget_2_calllog_2, a(applicationContext, R.id.bt_widget_2_calllog_2));
            f3496a.setOnClickPendingIntent(R.id.ts_bt_widget_2_misscall, a(applicationContext, R.id.bt_widget_2_misscall));
            f3496a.setOnClickPendingIntent(R.id.ts_bt_widget_2_misscall_2, a(applicationContext, R.id.bt_widget_2_misscall_2));
            f3496a.setImageViewResource(R.id.ts_bt_widget_misscall_icon, R.drawable.denza_bt_widget_misscall_icon);
            f3496a.setImageViewResource(R.id.ts_bt_widget_calllog_icon, R.drawable.denza_bt_widget_calllog_icon);
            f3496a.setTextColor(R.id.ts_bt_widget_calllog_text, applicationContext.getColor(R.color.ts_widget_text_color));
            f3496a.setTextColor(R.id.ts_bt_widget_misscall_text, applicationContext.getColor(R.color.ts_widget_text_color));
        } else {
            f3496a.setViewVisibility(R.id.ts_disconnect_widget_layout, 0);
            f3496a.setViewVisibility(R.id.ts_connected_widget_layout, 8);
            if (a.K().M() == 1) {
                f3496a.setTextViewText(R.id.ts_widget_not_connect, applicationContext.getString(R.string.audio_disabled));
                f3496a.setImageViewResource(R.id.ts_widget_bt_icon, R.drawable.denza_bt_audio_icon);
                f3496a.setViewVisibility(R.id.ts_widget_bt_icon, 8);
            } else {
                f3496a.setTextViewText(R.id.ts_widget_not_connect, applicationContext.getString(R.string.not_connect));
                f3496a.setViewVisibility(R.id.ts_widget_bt_icon, 0);
                f3496a.setImageViewResource(R.id.ts_widget_bt_icon, R.drawable.denza_bt_notconnect_icon);
            }
            f3496a.setTextColor(R.id.ts_widget_not_connect, applicationContext.getColor(R.color.ts_widget_text_color));
        }
        if (com.byd.bluetoothcall.c.a.F().I() <= 0) {
            f3496a.setViewVisibility(R.id.ts_unread_missed_calls, 8);
            return;
        }
        if (com.byd.bluetoothcall.c.a.F().I() > 99) {
            f3496a.setTextViewText(R.id.ts_unread_missed_calls, "99+");
        } else {
            f3496a.setTextViewText(R.id.ts_unread_missed_calls, com.byd.bluetoothcall.c.a.F().I() + "");
        }
        f3496a.setViewVisibility(R.id.ts_unread_missed_calls, 0);
    }

    public static void g() {
        if (f3496a != null) {
            if (com.byd.bluetoothcall.c.a.F().X()) {
                f3496a.setImageViewBitmap(R.id.ts_bt_app_widget_bg, ((BitmapDrawable) SkinManager.getInstance().getDrawable(R.drawable.denza_bt_widget_bg)).getBitmap());
                f3496a.setImageViewBitmap(R.id.ts_bt_app_widget_piont, ((BitmapDrawable) SkinManager.getInstance().getDrawable(R.drawable.denza_bt_widget_bg_select)).getBitmap());
                f3496a.setViewVisibility(R.id.ts_bt_app_widget_piont, 0);
            } else {
                f3496a.setImageViewBitmap(R.id.ts_bt_app_widget_bg, ((BitmapDrawable) SkinManager.getInstance().getDrawable(R.drawable.denza_bt_widget_bg)).getBitmap());
                f3496a.setImageViewBitmap(R.id.ts_bt_app_widget_piont, ((BitmapDrawable) SkinManager.getInstance().getDrawable(R.drawable.denza_bt_widget_bg_select)).getBitmap());
                f3496a.setViewVisibility(R.id.ts_bt_app_widget_piont, 8);
            }
        }
    }

    public static void h() {
        Context applicationContext = BlueToothApplication.a().getApplicationContext();
        RemoteViews remoteViews = f3496a;
        if (remoteViews == null || applicationContext == null) {
            return;
        }
        remoteViews.setImageViewBitmap(R.id.bt_widget_notconnect_icon, ((BitmapDrawable) SkinManager.getInstance().getDrawable(R.drawable.bt_widget_notconnect_icon)).getBitmap());
        f3496a.setImageViewBitmap(R.id.bt_widget_connect_icon, ((BitmapDrawable) SkinManager.getInstance().getDrawable(R.drawable.bt_widget_notconnect_icon)).getBitmap());
        if (b.f0().n0() == 2) {
            f3496a.setViewVisibility(R.id.disconnect_widget_layout, 8);
            f3496a.setViewVisibility(R.id.connected_widget_layout, 0);
            f3496a.setOnClickPendingIntent(R.id.bt_widget_2_calllog, a(applicationContext, R.id.bt_widget_2_calllog));
            f3496a.setOnClickPendingIntent(R.id.bt_widget_2_calllog_2, a(applicationContext, R.id.bt_widget_2_calllog_2));
            f3496a.setOnClickPendingIntent(R.id.bt_widget_2_misscall, a(applicationContext, R.id.bt_widget_2_misscall));
            f3496a.setOnClickPendingIntent(R.id.bt_widget_2_misscall_2, a(applicationContext, R.id.bt_widget_2_misscall_2));
            f3496a.setImageViewResource(R.id.bt_widget_misscall_icon, R.drawable.bt_widget_misscall_icon);
            f3496a.setImageViewResource(R.id.bt_widget_calllog_icon, R.drawable.bt_widget_calllog_icon);
            f3496a.setImageViewResource(R.id.bt_widget_misscall_icon_bg, R.drawable.widget_btn_bg);
            f3496a.setImageViewResource(R.id.bt_widget_calllog_icon_bg, R.drawable.widget_btn_bg);
            f3496a.setTextColor(R.id.bt_widget_calllog_text, applicationContext.getColor(R.color.widget_text_color));
            f3496a.setTextColor(R.id.bt_widget_misscall_text, applicationContext.getColor(R.color.widget_text_color));
        } else {
            f3496a.setViewVisibility(R.id.disconnect_widget_layout, 0);
            f3496a.setViewVisibility(R.id.connected_widget_layout, 8);
            if (a.K().M() == 1) {
                f3496a.setTextViewText(R.id.widget_not_connect, applicationContext.getString(R.string.audio_disabled));
                f3496a.setImageViewResource(R.id.widget_bt_icon, R.drawable.bt_audio_icon);
                f3496a.setViewVisibility(R.id.widget_bt_icon, 8);
            } else {
                f3496a.setTextViewText(R.id.widget_not_connect, applicationContext.getString(R.string.not_connect));
                f3496a.setViewVisibility(R.id.widget_bt_icon, 0);
                f3496a.setImageViewResource(R.id.widget_bt_icon, R.drawable.bt_notconnect_icon);
            }
            f3496a.setTextColor(R.id.widget_not_connect, applicationContext.getColor(R.color.widget_text_color));
        }
        if (com.byd.bluetoothcall.c.a.F().I() <= 0) {
            f3496a.setViewVisibility(R.id.unread_missed_calls, 8);
            return;
        }
        if (com.byd.bluetoothcall.c.a.F().I() > 99) {
            f3496a.setTextViewText(R.id.unread_missed_calls, "99+");
        } else {
            f3496a.setTextViewText(R.id.unread_missed_calls, com.byd.bluetoothcall.c.a.F().I() + "");
        }
        f3496a.setViewVisibility(R.id.unread_missed_calls, 0);
    }

    public static void i() {
        if (f3496a != null) {
            if (com.byd.bluetoothcall.c.a.F().X()) {
                f3496a.setImageViewBitmap(R.id.bt_app_widget_bg, ((BitmapDrawable) SkinManager.getInstance().getDrawable(R.drawable.bt_widget_bg)).getBitmap());
                f3496a.setImageViewBitmap(R.id.bt_app_widget_piont, ((BitmapDrawable) SkinManager.getInstance().getDrawable(R.drawable.bt_widget_bg_select)).getBitmap());
                f3496a.setViewVisibility(R.id.bt_app_widget_piont, 0);
            } else {
                f3496a.setImageViewBitmap(R.id.bt_app_widget_bg, ((BitmapDrawable) SkinManager.getInstance().getDrawable(R.drawable.bt_widget_bg)).getBitmap());
                f3496a.setImageViewBitmap(R.id.bt_app_widget_piont, ((BitmapDrawable) SkinManager.getInstance().getDrawable(R.drawable.bt_widget_bg_select)).getBitmap());
                f3496a.setViewVisibility(R.id.bt_app_widget_piont, 8);
            }
        }
    }

    public static void j() {
        AppWidgetManager.getInstance(BlueToothApplication.a().getApplicationContext()).updateAppWidget(new ComponentName(BlueToothApplication.a().getApplicationContext(), (Class<?>) WidgetProvide.class), e(BlueToothApplication.a().getApplicationContext()));
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int i, Bundle bundle) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, i, bundle);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onDeleted(Context context, int[] iArr) {
        super.onDeleted(context, iArr);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0027 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0042 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1091)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:23)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:370)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:85)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:33)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:23)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    @Override // android.appwidget.AppWidgetProvider, android.content.BroadcastReceiver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onReceive(android.content.Context r5, android.content.Intent r6) {
        /*
            r4 = this;
            super.onReceive(r5, r6)
            if (r6 != 0) goto L6
            return
        L6:
            java.lang.String r0 = "android.intent.category.SELECTED_ALTERNATIVE"
            boolean r0 = r6.hasCategory(r0)
            if (r0 == 0) goto L5c
            android.net.Uri r6 = r6.getData()
            java.lang.String r6 = r6.getSchemeSpecificPart()
            int r6 = java.lang.Integer.parseInt(r6)
            java.lang.String r0 = "page"
            r1 = 268435456(0x10000000, float:2.524355E-29)
            java.lang.String r2 = "DANDAN"
            switch(r6) {
                case 2131296375: goto L42;
                case 2131296376: goto L42;
                case 2131296377: goto L27;
                case 2131296378: goto L27;
                default: goto L23;
            }
        L23:
            switch(r6) {
                case 2131296898: goto L42;
                case 2131296899: goto L42;
                case 2131296900: goto L27;
                case 2131296901: goto L27;
                default: goto L26;
            }
        L26:
            goto L5c
        L27:
            android.content.Intent r6 = new android.content.Intent     // Catch: java.lang.Exception -> L39
            java.lang.Class<com.byd.bluetoothcall.view.activity.MainActivity> r3 = com.byd.bluetoothcall.view.activity.MainActivity.class
            r6.<init>(r5, r3)     // Catch: java.lang.Exception -> L39
            r6.setFlags(r1)     // Catch: java.lang.Exception -> L39
            r1 = 2
            r6.putExtra(r0, r1)     // Catch: java.lang.Exception -> L39
            r5.startActivity(r6)     // Catch: java.lang.Exception -> L39
            goto L5c
        L39:
            r5 = move-exception
            java.lang.String r5 = r5.getMessage()
            com.bydux.api.common.tools.log.BydLogHelper.logD(r2, r5)
            goto L5c
        L42:
            android.content.Intent r6 = new android.content.Intent     // Catch: java.lang.Exception -> L54
            java.lang.Class<com.byd.bluetoothcall.view.activity.MainActivity> r3 = com.byd.bluetoothcall.view.activity.MainActivity.class
            r6.<init>(r5, r3)     // Catch: java.lang.Exception -> L54
            r6.setFlags(r1)     // Catch: java.lang.Exception -> L54
            r1 = 1
            r6.putExtra(r0, r1)     // Catch: java.lang.Exception -> L54
            r5.startActivity(r6)     // Catch: java.lang.Exception -> L54
            goto L5c
        L54:
            r5 = move-exception
            java.lang.String r5 = r5.getMessage()
            com.bydux.api.common.tools.log.BydLogHelper.logD(r2, r5)
        L5c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.bluetoothcall.widget.WidgetProvide.onReceive(android.content.Context, android.content.Intent):void");
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onRestored(Context context, int[] iArr, int[] iArr2) {
        super.onRestored(context, iArr, iArr2);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        super.onUpdate(context, appWidgetManager, iArr);
        for (int i : iArr) {
            appWidgetManager.updateAppWidget(i, e(BlueToothApplication.a().getApplicationContext()));
        }
    }
}
