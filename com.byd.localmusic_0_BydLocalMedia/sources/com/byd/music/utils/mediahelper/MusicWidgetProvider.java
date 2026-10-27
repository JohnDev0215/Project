package com.byd.music.utils.mediahelper;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.RemoteViews;
import com.blankj.utilcode.util.Utils;
import com.byd.music.OnLineMusic;
import com.byd.music.R;
import com.byd.music.ThirdSDK.AndroidAuto.AndroidAutoManager;
import com.byd.music.ThirdSDK.Carplay.CarplayManager;
import com.byd.music.ThirdSDK.Spotify.SpotifyManager;
import com.byd.music.ThirdSDK.ThirdManager;
import com.byd.music.ThirdSDK.amazon.AmazonManager;
import com.byd.music.api.ApiMethod;
import com.byd.music.beans.MultiMusicBean;
import com.byd.music.utils.CurrentMediaInfoUtils;
import com.byd.music.utils.DeviceInfoUtils;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.WidgetHintTextUtil;
import com.byd.music.view.ProgressBitmap;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes5.dex */
public class MusicWidgetProvider extends AppWidgetProvider {
    public static final String CLICK_MUSIC_NEXT = "com.byd.music.appwidgetprovider.music.next";
    public static final String CLICK_MUSIC_OPEN_PLAYER = "com.byd.music.appwidgetprovider.music.open.player";
    public static final String CLICK_MUSIC_PLAY = "com.byd.music.appwidgetprovider.music.play";
    public static final String CLICK_MUSIC_PREVIOUS = "com.byd.music.appwidgetprovider.music.previous";
    public static final String CLICK_SOURCE_CHOICE = "com.byd.music.appwidgetprovider.music.source.choice";
    public static final String HIDE_MEDIA_POPUP = "com.byd.card.hide_media_popup";
    public static final String SHOW_MEDIA_POPUP = "com.byd.card.show_media_popup";
    private static final String TAG = "MusicWidgetProvider";
    public static final String UPDATE_MUSIC_INFO = "com.byd.music.appwidgetprovider.music.update";
    public static boolean isSelected;
    public static ProgressBitmap progressBitmap;

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        super.onUpdate(context, appWidgetManager, iArr);
        updateAppWidget(context, appWidgetManager);
    }

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager) {
        CurrentMediaInfoUtils.getInstance().cancelWidgetUpdate();
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), context.getResources().getIdentifier("music_widget_main_day", "layout", context.getPackageName()));
        remoteViews.setOnClickPendingIntent(R.id.btn_play_pause, getPendingIntent(CLICK_MUSIC_PLAY));
        remoteViews.setOnClickPendingIntent(R.id.music_widget_container, getPendingIntent(CLICK_MUSIC_OPEN_PLAYER));
        remoteViews.setOnClickPendingIntent(R.id.btn_previous, getPendingIntent(CLICK_MUSIC_PREVIOUS));
        remoteViews.setOnClickPendingIntent(R.id.btn_next, getPendingIntent(CLICK_MUSIC_NEXT));
        remoteViews.setOnClickPendingIntent(R.id.group_music_type, getPendingIntent(CLICK_SOURCE_CHOICE));
        int i = OnLineMusic.musicEngine;
        switch (i) {
            case 3:
                remoteViews.setImageViewResource(R.id.iv_music_type, R.drawable.icon_widget_sd);
                break;
            case 4:
                remoteViews.setImageViewResource(R.id.iv_music_type, R.drawable.icon_widget_usb);
                break;
            case 5:
                remoteViews.setImageViewResource(R.id.iv_music_type, R.drawable.icon_widget_local);
                break;
            case 6:
                remoteViews.setImageViewResource(R.id.iv_music_type, R.drawable.icon_widget_btmusic);
                break;
            case 7:
                remoteViews.setImageViewResource(R.id.iv_music_type, R.drawable.icon_widget_fm);
                break;
            case 8:
                remoteViews.setImageViewResource(R.id.iv_music_type, R.drawable.icon_widget_am);
                break;
            case 9:
                remoteViews.setImageViewResource(R.id.iv_music_type, R.drawable.icon_widget_dab);
                break;
            default:
                switch (i) {
                    case 101:
                        remoteViews.setImageViewResource(R.id.iv_music_type, R.drawable.icon_app_android_auto);
                        break;
                    case 102:
                        remoteViews.setImageViewResource(R.id.iv_music_type, R.drawable.icon_app_carplay);
                        break;
                    case 103:
                        remoteViews.setImageViewResource(R.id.iv_music_type, R.drawable.icon_app_spotify);
                        break;
                    case 104:
                        remoteViews.setImageViewResource(R.id.iv_music_type, R.drawable.icon_app_amazon);
                        break;
                    case 105:
                        remoteViews.setImageViewResource(R.id.iv_music_type, R.drawable.icon_app_flo);
                        break;
                    case 106:
                        remoteViews.setImageViewResource(R.id.iv_music_type, R.drawable.icon_app_audials);
                        break;
                    case 107:
                        remoteViews.setImageViewResource(R.id.iv_music_type, R.drawable.icon_app_audible);
                        break;
                    case 108:
                        remoteViews.setImageViewResource(R.id.iv_music_type, R.drawable.icon_app_bloomberg);
                        break;
                    case 109:
                        remoteViews.setImageViewResource(R.id.iv_music_type, R.drawable.icon_app_deezer);
                        break;
                    case 110:
                        remoteViews.setImageViewResource(R.id.iv_music_type, R.drawable.icon_app_iheart);
                        break;
                    case 111:
                        remoteViews.setImageViewResource(R.id.iv_music_type, R.drawable.icon_app_libby);
                        break;
                    case 112:
                        remoteViews.setImageViewResource(R.id.iv_music_type, R.drawable.icon_app_melon);
                        break;
                    case 113:
                        remoteViews.setImageViewResource(R.id.iv_music_type, R.drawable.icon_app_pocket_casts);
                        break;
                    case 114:
                        remoteViews.setImageViewResource(R.id.iv_music_type, R.drawable.icon_app_radioline);
                        break;
                    case 115:
                        remoteViews.setImageViewResource(R.id.iv_music_type, R.drawable.icon_app_tidal);
                        break;
                    case 116:
                        remoteViews.setImageViewResource(R.id.iv_music_type, R.drawable.icon_app_tunein);
                        break;
                }
                break;
        }
        if (CurrentMediaInfoUtils.getInstance().getPlayingState()) {
            setRemoteViewBitmapCustom(remoteViews, context, R.id.btn_play_pause, R.drawable.icon_card_suspend_48);
        } else {
            setRemoteViewBitmapCustom(remoteViews, context, R.id.btn_play_pause, R.drawable.icon_card_play_48);
        }
        setRemoteViewBitmapCustom(remoteViews, context, R.id.music_widget_container, R.drawable.media_widget_bg);
        setRemoteViewBitmapCustom(remoteViews, context, R.id.iv_select_mask, R.drawable.media_widget_mask_selected);
        setRemoteViewBitmapCustom(remoteViews, context, R.id.btn_previous, R.drawable.icon_card_previous_48);
        setRemoteViewBitmapCustom(remoteViews, context, R.id.btn_next, R.drawable.icon_card_next_48);
        setRemoteViewBitmapCustom(remoteViews, context, R.id.iv_app_shift, R.drawable.icon_app_shift);
        MultiMusicBean multiMusicBean = CurrentMediaInfoUtils.getInstance().currentMusic.get();
        if (multiMusicBean != null) {
            if (multiMusicBean.mEntity == null) {
                handleMusicEntityNullLogic(context, remoteViews);
            } else {
                remoteViews.setTextViewText(R.id.tv_music_name, multiMusicBean.getMusicName());
                remoteViews.setTextViewText(R.id.tv_music_singer, multiMusicBean.getArtist());
                remoteViews.setTextColor(R.id.tv_music_name, context.getResources().getColor(R.color.color_white_front));
                remoteViews.setTextColor(R.id.tv_music_singer, context.getResources().getColor(R.color.color_white_front));
                remoteViews.setImageViewBitmap(R.id.iv_music_pic, CurrentMediaInfoUtils.getInstance().currentAlbum.getValue());
            }
        } else {
            LogUtil.e(TAG, "update musicWidget and MultiMusicBean is Null ");
        }
        if (progressBitmap == null) {
            progressBitmap = new ProgressBitmap(DeviceInfoUtils.dip2px(context, context.getResources().getDimension(R.dimen.dp_64)), context.getResources().getDimension(R.dimen.dp_64));
        }
        remoteViews.setImageViewBitmap(R.id.iv_music_progress, progressBitmap.generateBitmap(context, CurrentMediaInfoUtils.getInstance().curProgress.get(), CurrentMediaInfoUtils.getInstance().maxProgress.get()));
        remoteViews.setViewVisibility(R.id.iv_select_mask, isSelected ? 0 : 4);
        appWidgetManager.updateAppWidget(new ComponentName(context, (Class<?>) MusicWidgetProvider.class), remoteViews);
        LogUtil.d("after to appWidgetManager updateAppWidget");
    }

    private static PendingIntent getPendingIntent(String str) {
        return PendingIntent.getBroadcast(Utils.getApp(), 0, new Intent(Utils.getApp(), (Class<?>) MusicWidgetProvider.class).setAction(str), 134217728);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.appwidget.AppWidgetProvider, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String packageName;
        super.onReceive(context, intent);
        if (intent == null) {
        }
        String action = intent.getAction();
        byte b = -1;
        switch (action.hashCode()) {
            case -1502582705:
                if (action.equals(CLICK_MUSIC_PREVIOUS)) {
                    b = 1;
                }
                break;
            case -967294852:
                if (action.equals(CLICK_SOURCE_CHOICE)) {
                    b = 5;
                }
                break;
            case -963017043:
                if (action.equals(CLICK_MUSIC_OPEN_PLAYER)) {
                    b = 0;
                }
                break;
            case 300721227:
                if (action.equals(CLICK_MUSIC_NEXT)) {
                    b = 2;
                }
                break;
            case 300786828:
                if (action.equals(CLICK_MUSIC_PLAY)) {
                    b = 3;
                }
                break;
            case 1440242721:
                if (action.equals(UPDATE_MUSIC_INFO)) {
                    b = 4;
                }
                break;
        }
        if (b != 0) {
            if (b == 1) {
                ApiMethod.playPre();
                return;
            }
            if (b == 2) {
                ApiMethod.playNext();
                return;
            }
            if (b == 3) {
                ApiMethod.playOrStop(!CurrentMediaInfoUtils.getInstance().getPlayingState());
                return;
            }
            if (b == 4) {
                updateAppWidget(context, AppWidgetManager.getInstance(context));
                return;
            } else {
                if (b == 5 && !DeviceInfoUtils.isInCall()) {
                    context.sendBroadcast(new Intent(SHOW_MEDIA_POPUP));
                    return;
                }
                return;
            }
        }
        if (DeviceInfoUtils.isInCall()) {
            return;
        }
        try {
            switch (OnLineMusic.musicEngine) {
                case 101:
                    ApiMethod.hideWidgetCards(context);
                    packageName = AndroidAutoManager.AndroidAutoPKG;
                    context.startActivity(context.getPackageManager().getLaunchIntentForPackage(packageName));
                    break;
                case 102:
                    CarplayManager.launchNowPlaying();
                    ApiMethod.playOrStop(true);
                    break;
                case 103:
                    packageName = SpotifyManager.SpotifyPKG;
                    context.startActivity(context.getPackageManager().getLaunchIntentForPackage(packageName));
                    break;
                case 104:
                    ApiMethod.hideWidgetCards(context);
                    packageName = AmazonManager.AmazonPKG;
                    context.startActivity(context.getPackageManager().getLaunchIntentForPackage(packageName));
                    break;
                case 105:
                case 106:
                case 107:
                case 108:
                case 109:
                case 110:
                case 111:
                case 112:
                case 113:
                case 114:
                case 115:
                case 116:
                    if (ThirdManager.getInstance().FceEngineMap != null && ThirdManager.getInstance().FceEngineMap.containsKey(Integer.valueOf(OnLineMusic.musicEngine))) {
                        packageName = ThirdManager.getInstance().FceEngineMap.get(Integer.valueOf(OnLineMusic.musicEngine));
                    } else {
                        packageName = Utils.getApp().getPackageName();
                    }
                    context.startActivity(context.getPackageManager().getLaunchIntentForPackage(packageName));
                    break;
                default:
                    packageName = Utils.getApp().getPackageName();
                    context.startActivity(context.getPackageManager().getLaunchIntentForPackage(packageName));
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setRemoteViewBitmapCustom(RemoteViews remoteViews, Context context, int i, int i2) {
        Drawable drawable = SkinManager.getInstance().getDrawable(i2);
        if (drawable instanceof BitmapDrawable) {
            remoteViews.setImageViewBitmap(i, ((BitmapDrawable) drawable).getBitmap());
            return;
        }
        if (drawable == null) {
            LogUtil.e(TAG, "setRemoteViewBitmapCustom error with drawable null");
            return;
        }
        LogUtil.e(TAG, "setRemoteViewBitmapCustom error with drawable class = " + drawable.getClass());
    }

    private static void handleMusicEntityNullLogic(Context context, RemoteViews remoteViews) {
        LogUtil.d(TAG, "updateAppWidget mEntity is null");
        remoteViews.setTextViewText(R.id.tv_music_name, WidgetHintTextUtil.getHintMusic(OnLineMusic.musicEngine));
        remoteViews.setTextViewText(R.id.tv_music_singer, WidgetHintTextUtil.getHintArtist(OnLineMusic.musicEngine));
        setRemoteViewBitmapCustom(remoteViews, context, R.id.iv_music_pic, R.drawable.img_logo_media64);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onDeleted(Context context, int[] iArr) {
        super.onDeleted(context, iArr);
        LogUtil.d(TAG, "onDeleted!");
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onDisabled(Context context) {
        super.onDisabled(context);
        LogUtil.d(TAG, "onDisabled!");
    }
}
