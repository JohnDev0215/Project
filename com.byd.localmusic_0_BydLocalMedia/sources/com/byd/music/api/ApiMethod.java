package com.byd.music.api;

import android.content.Context;
import android.content.Intent;
import com.blankj.utilcode.util.Utils;
import com.byd.mediacenter.api.media.MediaControl;
import com.byd.music.OnLineMusic;
import com.byd.music.R;
import com.byd.music.ThirdSDK.ThirdManager;
import com.byd.music.fragment.bluetooth.BluetoothViewModel;
import com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper;
import com.byd.music.service.RadioServiceWrapper;
import com.byd.music.utils.DeviceInfoUtils;
import com.byd.music.utils.LogUtil;
import com.byd.musiclib.BuildConfig;

/* JADX INFO: loaded from: classes5.dex */
public class ApiMethod {
    public static final String CLOSE_CARD = "com.byd.card.close.FloatCard";
    public static final String OPEN_CARD = "com.byd.card.open.FloatCard";
    private static final String TAG = "ApiMethod";

    public static void showWidgetCards(Context context) {
        LogUtil.d(TAG, "showWidgetCards enter");
        Intent intent = new Intent(OPEN_CARD);
        intent.putExtra("package", BuildConfig.APPLICATION_ID);
        context.sendBroadcast(intent);
    }

    public static void hideWidgetCards(Context context) {
        LogUtil.d(TAG, "hideWidgetCards enter");
        Intent intent = new Intent(CLOSE_CARD);
        intent.putExtra("package", BuildConfig.APPLICATION_ID);
        context.sendBroadcast(intent);
    }

    public static String playNext() {
        if (DeviceInfoUtils.isInCall()) {
            return Utils.getApp().getResources().getString(R.string.incall_forbid_operation);
        }
        int i = OnLineMusic.musicEngine;
        switch (i) {
            case 3:
            case 4:
            case 5:
                return MusicPlayHelper.getInstance().playNext();
            case 6:
                BluetoothViewModel.nextMusic();
                return "";
            case 7:
            case 8:
            case 9:
                return RadioServiceWrapper.getInstance().tuneToNext();
            default:
                switch (i) {
                    case 101:
                    case 102:
                    case 103:
                    case 104:
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
                        return ThirdManager.playNext();
                    default:
                        return MediaControl.REASON_UNKNOWN;
                }
        }
    }

    public static String playPre() {
        if (DeviceInfoUtils.isInCall()) {
            return Utils.getApp().getResources().getString(R.string.incall_forbid_operation);
        }
        int i = OnLineMusic.musicEngine;
        switch (i) {
            case 3:
            case 4:
            case 5:
                return MusicPlayHelper.getInstance().playPre();
            case 6:
                BluetoothViewModel.preMusic();
                return "";
            case 7:
            case 8:
            case 9:
                return RadioServiceWrapper.getInstance().tuneToPre();
            default:
                switch (i) {
                    case 101:
                    case 102:
                    case 103:
                    case 104:
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
                        return ThirdManager.playPre();
                    default:
                        return MediaControl.REASON_UNKNOWN;
                }
        }
    }

    public static String playOrStop(boolean z) {
        LogUtil.d(TAG, "playOrStop enter with isPlay = " + z + " musicEngine=" + OnLineMusic.musicEngine);
        int i = OnLineMusic.musicEngine;
        switch (i) {
            case 3:
            case 4:
            case 5:
                if (z) {
                    return MusicPlayHelper.getInstance().playMusicFromApi();
                }
                MusicPlayHelper.getInstance().pauseMusic();
                return "";
            case 6:
                if (z) {
                    BluetoothViewModel.playMusic();
                } else {
                    BluetoothViewModel.pauseMusic();
                }
                return "";
            case 7:
            case 8:
            case 9:
                return RadioServiceWrapper.getInstance().setRadioPlayState(z);
            default:
                switch (i) {
                    case 101:
                    case 102:
                    case 103:
                    case 104:
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
                        return ThirdManager.playOrStop(z);
                    default:
                        return MediaControl.REASON_UNKNOWN;
                }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String collect(boolean r2) {
        /*
            int r0 = com.byd.music.OnLineMusic.musicEngine
            switch(r0) {
                case 3: goto L5d;
                case 4: goto L5d;
                case 5: goto L5d;
                case 6: goto L5;
                case 7: goto L8;
                case 8: goto L8;
                case 9: goto L33;
                default: goto L5;
            }
        L5:
            java.lang.String r2 = "unknown reason"
            return r2
        L8:
            com.byd.music.service.RadioServiceWrapper r0 = com.byd.music.service.RadioServiceWrapper.getInstance()
            androidx.lifecycle.MutableLiveData<com.byd.music.beans.RadioDBBean> r0 = r0.mCurrentRadio
            java.lang.Object r0 = r0.getValue()
            com.byd.music.beans.RadioDBBean r0 = (com.byd.music.beans.RadioDBBean) r0
            if (r0 == 0) goto L33
            if (r2 == 0) goto L25
            com.byd.music.service.RadioServiceWrapper r2 = com.byd.music.service.RadioServiceWrapper.getInstance()
            int r0 = r0.getRadioFrequency()
            java.lang.String r2 = r2.addTargetFreqToCollect(r0)
            return r2
        L25:
            com.byd.music.service.RadioServiceWrapper r2 = com.byd.music.service.RadioServiceWrapper.getInstance()
            int r0 = r0.getRadioFrequency()
            long r0 = (long) r0
            java.lang.String r2 = r2.deleteTargetFreqFromCollect(r0)
            return r2
        L33:
            com.byd.music.service.RadioServiceWrapper r0 = com.byd.music.service.RadioServiceWrapper.getInstance()
            androidx.lifecycle.MutableLiveData<com.byd.music.beans.RadioDBBean> r0 = r0.mCurrentRadio
            java.lang.Object r0 = r0.getValue()
            com.byd.music.beans.RadioDBBean r0 = (com.byd.music.beans.RadioDBBean) r0
            if (r0 == 0) goto L5d
            if (r2 == 0) goto L50
            com.byd.music.service.RadioServiceWrapper r2 = com.byd.music.service.RadioServiceWrapper.getInstance()
            long r0 = r0.getDAB_SID()
            java.lang.String r2 = r2.addTargetDabToCollect(r0)
            return r2
        L50:
            com.byd.music.service.RadioServiceWrapper r2 = com.byd.music.service.RadioServiceWrapper.getInstance()
            long r0 = r0.getDAB_SID()
            java.lang.String r2 = r2.deleteTargetDABSIDFromCollect(r0)
            return r2
        L5d:
            com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper r0 = com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper.getInstance()
            java.lang.String r2 = r0.setCollectMusic(r2)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.api.ApiMethod.collect(boolean):java.lang.String");
    }
}
