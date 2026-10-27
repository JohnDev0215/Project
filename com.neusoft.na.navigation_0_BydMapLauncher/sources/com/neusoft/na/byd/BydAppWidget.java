package com.neusoft.na.byd;

import android.app.PendingIntent;
import android.app.UiStyleConfig;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;
import com.bydux.api.common.tools.util.ListUtils;
import com.bydux.api.common.views.skin.listener.ILoaderListener;
import com.bydux.api.common.views.skin.loader.SkinManager;
import com.neusoft.na.common.ServiceBase;

/* JADX INFO: loaded from: classes.dex */
public class BydAppWidget extends AppWidgetProvider {
    private static final String TAG = "XJBydAppWidget";
    public static boolean isEvCar = true;
    public static boolean isGuide = false;
    public static boolean isShow = false;

    @Override // android.appwidget.AppWidgetProvider, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        String action = intent.getAction();
        Log.i(TAG, "onReceive: " + action);
        if (Byd8155fSystem.ACTION_WIDGET_SCREEN_CHANGED.equals(action)) {
            Log.i(TAG, "onReceive: Byd8155fSystem.ACTION_WIDGET_SCREEN_CHANGED");
            updateWidget(context);
            Configuration configuration = (Configuration) intent.getParcelableExtra(ServiceBase.PAYLOAD_CONFIG);
            if (configuration != null) {
                Log.i(TAG, "onReceive: " + (configuration.uiMode & 48));
                SkinManager.getInstance().updateResource(configuration);
                SkinManager.getInstance().notifySkinUpdate();
                updateWidget(context);
                Log.i(TAG, "onReceive: done " + (configuration.uiMode & 48));
                return;
            }
            return;
        }
        if (Byd8155fSystem.ACTION_WIDGET_INFO.equals(action)) {
            isGuide = intent.getBooleanExtra(Byd8155fSystem.WIDGET_BUNDLE_KEY, false);
            isEvCar = intent.getBooleanExtra(Byd8155fSystem.WIDGET_CAR_TYPE_KEY, true);
            Log.i(TAG, "onReceive: BydSystem.ACTION_WIDGET_INFO " + isGuide);
            updateWidget(context);
            return;
        }
        if ("com.neusoft.na.navigation.action.widget.update".equals(action)) {
            isShow = intent.getBooleanExtra("isShow", false);
            Log.i(TAG, "onReceive: widget.update " + isShow);
            updateWidget(context);
        } else if (Byd8155fSystem.ACTION_WIDGET_UUID.equals(action)) {
            Log.i(TAG, "onReceive: Byd8155fSystem.ACTION_WIDGET_UUID " + intent.getIntExtra(Byd8155fSystem.WIDGET_UUID_KEY, -1));
            updateWidget(context);
        } else if (Byd8155fSystem.ACTION_WIDGET_RESET.equals(action)) {
            isGuide = false;
            isShow = false;
            updateWidget(context);
        }
    }

    private static void updateWidget(final Context context) {
        final AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        if (TextUtils.isEmpty(SkinManager.getInstance().getSkinPackageName())) {
            Log.i(TAG, "updateWidget: SkinManager init");
            SkinManager.getInstance().init(context, new ILoaderListener() { // from class: com.neusoft.na.byd.BydAppWidget.1
                @Override // com.bydux.api.common.views.skin.listener.ILoaderListener
                public void onChangeSkinStart() {
                    Log.i(BydAppWidget.TAG, "onChangeSkinStart");
                }

                @Override // com.bydux.api.common.views.skin.listener.ILoaderListener
                public void onChangeSkinSuccess() {
                    Log.i(BydAppWidget.TAG, "onChangeSkinSuccess");
                    BydAppWidget.updateAppWidget(context, appWidgetManager);
                }

                @Override // com.bydux.api.common.views.skin.listener.ILoaderListener
                public void onChangeSkinFail() {
                    Log.i(BydAppWidget.TAG, "onChangeSkinFail");
                }
            }, true);
        }
        updateAppWidget(context, appWidgetManager);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.byd_app_map_widget);
        int uiStyle = UiStyleConfig.getInstance(context).getUiStyle();
        boolean z = uiStyle == 1;
        Log.i(TAG, "updateAppWidget: " + isEvCar + ListUtils.DEFAULT_JOIN_SEPARATOR + isGuide + ListUtils.DEFAULT_JOIN_SEPARATOR + isShow + ListUtils.DEFAULT_JOIN_SEPARATOR + uiStyle + ListUtils.DEFAULT_JOIN_SEPARATOR + remoteViews.getLayoutId());
        if (z) {
            remoteViews.setImageViewBitmap(R.id.iv_root, getBitmap(R.drawable.denza_img_navi_bg));
            remoteViews.setImageViewBitmap(R.id.iv_navigation, getBitmap(R.drawable.denza_img_navi));
            remoteViews.setImageViewBitmap(R.id.iv_shadow, getBitmap(R.drawable.denza_img_card_bg_selected));
            remoteViews.setImageViewBitmap(R.id.iv_home, getBitmap(isGuide ? R.drawable.denza_icon_navi_search : R.drawable.denza_icon_navi_home));
            remoteViews.setImageViewBitmap(R.id.iv_work, getBitmap(isGuide ? isEvCar ? R.drawable.denza_icon_navi_charge : R.drawable.denza_icon_navi_fuel : R.drawable.denza_icon_navi_work));
            remoteViews.setTextColor(R.id.tv_home, getColor(R.color.denza_app_map_title_text));
            remoteViews.setTextColor(R.id.tv_work, getColor(R.color.denza_app_map_title_text));
        } else {
            remoteViews.setImageViewBitmap(R.id.iv_root, getBitmap(R.drawable.img_navi_bg));
            remoteViews.setImageViewBitmap(R.id.iv_navigation, getBitmap(R.drawable.img_navi));
            remoteViews.setImageViewBitmap(R.id.iv_shadow, getBitmap(R.drawable.img_card_bg_selected));
            remoteViews.setImageViewBitmap(R.id.iv_home, getBitmap(isGuide ? R.drawable.icon_navi_search : R.drawable.icon_navi_home));
            remoteViews.setImageViewBitmap(R.id.iv_work, getBitmap(isGuide ? isEvCar ? R.drawable.icon_navi_charge : R.drawable.icon_navi_fuel : R.drawable.icon_navi_work));
            remoteViews.setTextColor(R.id.tv_home, getColor(R.color.app_map_title_text));
            remoteViews.setTextColor(R.id.tv_work, getColor(R.color.app_map_title_text));
        }
        remoteViews.setViewVisibility(R.id.iv_home_bg, z ? 8 : 0);
        remoteViews.setViewVisibility(R.id.iv_work_bg, z ? 8 : 0);
        remoteViews.setTextViewText(R.id.tv_home, context.getString(isGuide ? R.string.app_map_search : R.string.app_map_home));
        remoteViews.setTextViewText(R.id.tv_work, context.getString(isGuide ? isEvCar ? R.string.app_map_charge : R.string.app_map_fuel : R.string.app_map_company));
        remoteViews.setViewVisibility(R.id.iv_shadow, isShow ? 0 : 4);
        remoteViews.setOnClickPendingIntent(R.id.rl_root, startActivity(context, -1));
        remoteViews.setOnClickPendingIntent(R.id.ll_home, startActivity(context, isGuide ? 3 : 1));
        remoteViews.setOnClickPendingIntent(R.id.ll_work, startActivity(context, isGuide ? 4 : 2));
        appWidgetManager.updateAppWidget(new ComponentName(context, (Class<?>) BydAppWidget.class), remoteViews);
        Log.i(TAG, "updateAppWidget done");
    }

    private static PendingIntent startActivity(Context context, int i) {
        Intent intent = new Intent();
        intent.putExtra("type", i);
        intent.addFlags(268435456);
        intent.setClassName(context, "com.neusoft.na.mots.MainActivity");
        return PendingIntent.getActivity(context, i, intent, 0);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        Log.i(TAG, "onUpdate");
        updateWidget(context);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onEnabled(Context context) {
        Log.i(TAG, "onEnabled");
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onDisabled(Context context) {
        Log.i(TAG, "onDisabled");
    }

    public static int getColor(int i) {
        int color = SkinManager.getInstance().getColor(i);
        Log.i(TAG, "getColor: #" + Integer.toHexString(color));
        return color;
    }

    public static Bitmap getBitmap(int i) {
        return drawableToBitmap(SkinManager.getInstance().getDrawable(i), i);
    }

    public static Bitmap drawableToBitmap(Drawable drawable, int i) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Log.i(TAG, "drawable: id=" + i + ",width=" + intrinsicWidth + ",height=" + intrinsicHeight);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        drawable.draw(new Canvas(bitmapCreateBitmap));
        return bitmapCreateBitmap;
    }
}
