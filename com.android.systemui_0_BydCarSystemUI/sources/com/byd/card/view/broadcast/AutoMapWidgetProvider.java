package com.byd.card.view.broadcast;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;
import com.android.systemui.R;
import com.android.systemui.util.StyleUtil;
import com.byd.card.bean.Constant;
import com.byd.card.utils.AppUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes.dex */
public class AutoMapWidgetProvider extends AppWidgetProvider {
    private static final String TAG = "AutoMapWidgetProvider";
    public static final String UPDATE_ACTION = "com.android.systemui.action.ACTION_UPDATE_MAP_WIDGET";

    @Override // android.appwidget.AppWidgetProvider, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        BydLogHelper.logI(TAG, "onReceive intent " + intent.getAction());
        if (TextUtils.equals(intent.getAction(), UPDATE_ACTION)) {
            updateWidget(context, AppWidgetManager.getInstance(context));
        }
        super.onReceive(context, intent);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onEnabled(Context context) {
        BydLogHelper.logI(TAG, "onEnabled");
        super.onEnabled(context);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onDeleted(Context context, int[] appWidgetIds) {
        BydLogHelper.logI(TAG, "onDeleted");
        super.onDeleted(context, appWidgetIds);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        String str = TAG;
        BydLogHelper.logI(str, "onUpdate");
        if (appWidgetManager == null || appWidgetIds == null || appWidgetIds.length <= 0) {
            BydLogHelper.logI(str, "onUpdate error, appWidgetManager=" + appWidgetManager);
        } else {
            updateWidget(context, AppWidgetManager.getInstance(context));
        }
    }

    private void updateWidget(Context context, AppWidgetManager appWidgetManager) {
        RemoteViews remoteViews;
        String str;
        String str2;
        String str3;
        String str4 = TAG;
        BydLogHelper.logI(str4, "updateWidget");
        if (StyleUtil.isDenza()) {
            remoteViews = new RemoteViews(context.getPackageName(), R.layout.map_widget_layout_denza);
            setThemeDrawable(remoteViews, R.id.all_background, "img_navi_bg", R.mipmap.denza_img_land_navi_bg);
            setThemeDrawable(remoteViews, R.id.im_location, "img_navi", R.mipmap.denza_img_dark_land_navi);
            setThemeDrawable(remoteViews, R.id.img_home, "icon_navi_home", R.mipmap.denza_icon_dark_card_home);
            setThemeDrawable(remoteViews, R.id.img_company, "icon_navi_work", R.mipmap.denza_icon_dark_card_work);
        } else {
            remoteViews = new RemoteViews(context.getPackageName(), R.layout.map_widget_layout_dynasty);
            setThemeDrawable(remoteViews, R.id.all_background, "img_navi_bg", R.mipmap.img_navi_bg);
            setThemeDrawable(remoteViews, R.id.im_location, "img_navi", R.mipmap.img_navi);
            setThemeDrawable(remoteViews, R.id.img_home, "icon_navi_home", R.mipmap.icon_navi_home);
            setThemeDrawable(remoteViews, R.id.img_company, "icon_navi_work", R.mipmap.icon_navi_work);
        }
        int color = SkinManager.getInstance().getColor(R.color.app_map_title_text);
        remoteViews.setTextColor(R.id.text_home, color);
        remoteViews.setTextColor(R.id.text_company, color);
        if (AppUtils.isInstalled(context, Constant.PackageName.T_MAP_PKG)) {
            setTMapIntent(context, remoteViews);
        } else {
            if (AppUtils.isInstalled(context, Constant.PackageName.AUTO_MAP_PKG)) {
                str = "bydautomap://launch_do_nothing";
                str2 = "bydautomap://launch_go_home";
                str3 = "bydautomap://launch_go_company";
            } else {
                str = "znav://launch_do_nothing";
                str2 = "znav://launch_go_home";
                str3 = "znav://launch_go_company";
            }
            Log.w(str4, "updateWidget! nothingAction = " + str);
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.setFlags(268435456);
            PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 0);
            intent.putExtra("start_from", "from_widget");
            remoteViews.setOnClickPendingIntent(R.id.widget_container, activity);
            Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(str2));
            intent2.setFlags(268435456);
            PendingIntent activity2 = PendingIntent.getActivity(context, 0, intent2, 0);
            intent2.putExtra("start_from", "from_widget");
            remoteViews.setOnClickPendingIntent(R.id.home, activity2);
            Intent intent3 = new Intent("android.intent.action.VIEW", Uri.parse(str3));
            intent3.setFlags(268435456);
            PendingIntent activity3 = PendingIntent.getActivity(context, 0, intent3, 0);
            intent3.putExtra("start_from", "from_widget");
            remoteViews.setOnClickPendingIntent(R.id.company, activity3);
        }
        appWidgetManager.updateAppWidget(new ComponentName(context.getPackageName(), getClass().getName()), remoteViews);
    }

    private void setTMapIntent(Context context, RemoteViews remoteViews) {
        if (AppUtils.isClassExist(context, Constant.PackageName.T_MAP_PKG, "com.skt.tmap.auto.activity.SplashActivity")) {
            Intent intent = new Intent();
            intent.setAction("com.skt.tmap.auto.ACTION_GOTO");
            intent.setClassName(Constant.PackageName.T_MAP_PKG, "com.skt.tmap.auto.activity.SplashActivity");
            intent.addFlags(335544320);
            remoteViews.setOnClickPendingIntent(R.id.widget_container, PendingIntent.getActivity(context, 0, intent, 0));
            Intent intent2 = new Intent();
            intent2.setAction("com.skt.tmap.auto.ACTION_LAUNCHER_GOTO");
            intent2.putExtra("type", "home");
            intent2.addFlags(335544320);
            remoteViews.setOnClickPendingIntent(R.id.home, PendingIntent.getActivity(context, 1, intent2, 0));
            Intent intent3 = new Intent();
            intent3.setAction("com.skt.tmap.auto.ACTION_LAUNCHER_GOTO");
            intent3.putExtra("type", "office");
            intent3.addFlags(335544320);
            remoteViews.setOnClickPendingIntent(R.id.company, PendingIntent.getActivity(context, 2, intent3, 0));
            return;
        }
        Log.d(TAG, "setTMapIntent new class isExist = " + AppUtils.isClassExist(context, Constant.PackageName.T_MAP_PKG, "com.skt.tmap.auto.activity.TmapAutoNaviActivity"));
        Intent intent4 = new Intent();
        intent4.setAction("com.skt.tmap.auto.ACTION_GOTO");
        intent4.setClassName(Constant.PackageName.T_MAP_PKG, "com.skt.tmap.auto.activity.TmapAutoNaviActivity");
        intent4.addFlags(335544320);
        remoteViews.setOnClickPendingIntent(R.id.widget_container, PendingIntent.getActivity(context, 0, intent4, 0));
        Intent intent5 = new Intent();
        intent5.setAction("com.skt.tmap.auto.ACTION_GOTO");
        intent5.setClassName(Constant.PackageName.T_MAP_PKG, "com.skt.tmap.auto.activity.TmapAutoNaviActivity");
        intent5.putExtra("type", "home");
        intent5.addFlags(335544320);
        remoteViews.setOnClickPendingIntent(R.id.home, PendingIntent.getActivity(context, 1, intent5, 0));
        Intent intent6 = new Intent();
        intent6.setAction("com.skt.tmap.auto.ACTION_GOTO");
        intent6.setClassName(Constant.PackageName.T_MAP_PKG, "com.skt.tmap.auto.activity.TmapAutoNaviActivity");
        intent6.putExtra("type", "office");
        intent6.addFlags(335544320);
        remoteViews.setOnClickPendingIntent(R.id.company, PendingIntent.getActivity(context, 2, intent6, 0));
    }

    private void setThemeDrawable(RemoteViews remoteViews, int viewId, String drawableId, int defaultDrawable) {
        Drawable drawable = SkinManager.getInstance().getDrawable(drawableId);
        Log.d(TAG, "setThemeDrawable drawable = " + drawable);
        if (drawable instanceof BitmapDrawable) {
            remoteViews.setImageViewBitmap(viewId, ((BitmapDrawable) drawable).getBitmap());
        } else {
            remoteViews.setImageViewResource(viewId, defaultDrawable);
        }
    }
}
