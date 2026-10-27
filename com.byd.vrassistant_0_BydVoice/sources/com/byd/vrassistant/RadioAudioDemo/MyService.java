package com.byd.vrassistant.RadioAudioDemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.R;
import com.byd.vrassistant.conversation.AudioHelper;
import com.byd.vrassistant.conversation.AudioSdk;
import com.byd.vrassistant.conversation.RadioSdk;
import com.byd.vrassistant.customizedimpl.CustomRadioDomain;
import com.byd.vrassistant.utils.RLog;
import com.google.common.base.Ascii;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class MyService extends Service {
    private final String TAG = "BYD_Radio MyService";
    private IntentFilter intentFilter;
    private MyReceiver myReceiver;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        RLog.d("BYD_Radio MyService", "service onCreate");
        IntentFilter intentFilter = new IntentFilter();
        this.intentFilter = intentFilter;
        intentFilter.addAction(CommonConst.OPEN_RADIO);
        this.intentFilter.addAction(CommonConst.getMediaMode);
        this.intentFilter.addAction(CommonConst.getPacketAudio);
        this.intentFilter.addAction(CommonConst.muteRadio);
        this.intentFilter.addAction(CommonConst.getPlayStatus);
        this.intentFilter.addAction(CommonConst.playFrequency);
        this.intentFilter.addAction(CommonConst.playFavoriteFre);
        this.intentFilter.addAction(CommonConst.changeBandAM);
        this.intentFilter.addAction(CommonConst.changeBandFM);
        this.intentFilter.addAction(CommonConst.addfavoriteRadio);
        this.intentFilter.addAction(CommonConst.removefavoriteRadio);
        this.intentFilter.addAction(CommonConst.Controlfavorite);
        this.intentFilter.addAction("playRadio");
        this.intentFilter.addAction(CommonConst.pauseRadio);
        this.intentFilter.addAction(CommonConst.playPreFre);
        this.intentFilter.addAction(CommonConst.playNextFre);
        this.intentFilter.addAction(CommonConst.Search_orStopSearch);
        this.intentFilter.addAction(CommonConst.StopSearch);
        this.intentFilter.addAction(CommonConst.openStationList);
        this.intentFilter.addAction(CommonConst.closeStationList);
        this.intentFilter.addAction(CommonConst.openFavoriteStationList);
        this.intentFilter.addAction(CommonConst.SearchFrequency);
        this.intentFilter.addAction(CommonConst.SearchFrequencyTest);
        this.intentFilter.addAction("playMusic");
        this.intentFilter.addAction(CommonConst.pauseMusic);
        this.intentFilter.addAction(CommonConst.playPre);
        this.intentFilter.addAction(CommonConst.playNext);
        this.intentFilter.addAction(CommonConst.addFavorite);
        this.intentFilter.addAction(CommonConst.removeFavorite);
        this.intentFilter.addAction("setPlayMode");
        this.intentFilter.addAction(CommonConst.playFavoriteMusic);
        this.intentFilter.addAction(CommonConst.openMusicList);
        this.intentFilter.addAction(CommonConst.showVolumeBar);
        this.intentFilter.addAction(CommonConst.ChangeSOURCE_LOCAL);
        this.intentFilter.addAction(CommonConst.ChangeSSOURCE_USB);
        this.intentFilter.addAction(CommonConst.ChangeSSOURCE_SD);
        MyReceiver myReceiver = new MyReceiver();
        this.myReceiver = myReceiver;
        registerReceiver(myReceiver, this.intentFilter);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        RLog.d("BYD_Radio MyService", "service onStartCommand1111");
        if (intent == null) {
            intent = new Intent();
            intent.setComponent(new ComponentName(getPackageName(), getPackageName() + ".activity.MainActivity"));
        }
        PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 0);
        ((NotificationManager) getSystemService("notification")).createNotificationChannel(new NotificationChannel("channel_1", "123", 2));
        Notification notificationBuild = new Notification.Builder(this, "channel_1").setCategory(NotificationCompat.CATEGORY_MESSAGE).setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("This is a content title").setContentText("This is a content text").setContentIntent(activity).setAutoCancel(true).build();
        notificationBuild.flags = 32;
        startForeground(1234567, notificationBuild);
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        RLog.d("BYD_Radio MyService", "service onDestroy");
        super.onDestroy();
        unregisterReceiver(this.myReceiver);
    }

    class MyReceiver extends BroadcastReceiver {
        MyReceiver() {
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            byte b;
            RLog.d("BYD_Radio MyService", "onReceive action===" + intent.getAction());
            String action = intent.getAction();
            action.hashCode();
            switch (action.hashCode()) {
                case -1929680375:
                    b = !action.equals(CommonConst.playFavoriteFre) ? (byte) -1 : (byte) 0;
                    break;
                case -1894100143:
                    b = !action.equals("playMusic") ? (byte) -1 : (byte) 1;
                    break;
                case -1890092761:
                    b = !action.equals("playRadio") ? (byte) -1 : (byte) 2;
                    break;
                case -1751756610:
                    b = !action.equals(CommonConst.addfavoriteRadio) ? (byte) -1 : (byte) 3;
                    break;
                case -1667434053:
                    b = !action.equals(CommonConst.removefavoriteRadio) ? (byte) -1 : (byte) 4;
                    break;
                case -1411429876:
                    b = !action.equals(CommonConst.openFavoriteStationList) ? (byte) -1 : (byte) 5;
                    break;
                case -596651791:
                    b = !action.equals(CommonConst.changeBandAM) ? (byte) -1 : (byte) 6;
                    break;
                case -596651636:
                    b = !action.equals(CommonConst.changeBandFM) ? (byte) -1 : (byte) 7;
                    break;
                case -593495224:
                    b = !action.equals(CommonConst.openStationList) ? (byte) -1 : (byte) 8;
                    break;
                case -543661504:
                    b = !action.equals(CommonConst.removeFavorite) ? (byte) -1 : (byte) 9;
                    break;
                case -493587761:
                    b = !action.equals(CommonConst.playPre) ? (byte) -1 : (byte) 10;
                    break;
                case -427090567:
                    b = !action.equals("setPlayMode") ? (byte) -1 : (byte) 11;
                    break;
                case -418971182:
                    b = !action.equals(CommonConst.ChangeSSOURCE_SD) ? (byte) -1 : (byte) 12;
                    break;
                case -320310136:
                    b = !action.equals(CommonConst.playFrequency) ? (byte) -1 : Ascii.CR;
                    break;
                case -293319974:
                    b = !action.equals(CommonConst.closeStationList) ? (byte) -1 : Ascii.SO;
                    break;
                case -136365320:
                    b = !action.equals(CommonConst.getPacketAudio) ? (byte) -1 : Ascii.SI;
                    break;
                case -103202301:
                    b = !action.equals(CommonConst.ChangeSSOURCE_USB) ? (byte) -1 : (byte) 16;
                    break;
                case -63757873:
                    b = !action.equals(CommonConst.pauseMusic) ? (byte) -1 : (byte) 17;
                    break;
                case -59750491:
                    b = !action.equals(CommonConst.pauseRadio) ? (byte) -1 : (byte) 18;
                    break;
                case 76510557:
                    b = !action.equals(CommonConst.addFavorite) ? (byte) -1 : (byte) 19;
                    break;
                case 220018596:
                    b = !action.equals(CommonConst.Search_orStopSearch) ? (byte) -1 : Ascii.DC4;
                    break;
                case 577909542:
                    b = !action.equals(CommonConst.SearchFrequencyTest) ? (byte) -1 : Ascii.NAK;
                    break;
                case 630041706:
                    b = !action.equals(CommonConst.StopSearch) ? (byte) -1 : Ascii.SYN;
                    break;
                case 1009602325:
                    b = !action.equals(CommonConst.playFavoriteMusic) ? (byte) -1 : Ascii.ETB;
                    break;
                case 1147812194:
                    b = !action.equals(CommonConst.muteRadio) ? (byte) -1 : Ascii.CAN;
                    break;
                case 1270254418:
                    b = !action.equals(CommonConst.playNextFre) ? (byte) -1 : (byte) 25;
                    break;
                case 1285015612:
                    b = !action.equals(CommonConst.getPlayStatus) ? (byte) -1 : Ascii.SUB;
                    break;
                case 1358025844:
                    b = !action.equals(CommonConst.SearchFrequency) ? (byte) -1 : Ascii.ESC;
                    break;
                case 1495104458:
                    b = !action.equals(CommonConst.playPreFre) ? (byte) -1 : Ascii.FS;
                    break;
                case 1525342236:
                    b = !action.equals(CommonConst.showVolumeBar) ? (byte) -1 : Ascii.GS;
                    break;
                case 1533762513:
                    b = !action.equals(CommonConst.OPEN_RADIO) ? (byte) -1 : Ascii.RS;
                    break;
                case 1771154935:
                    b = !action.equals(CommonConst.ChangeSOURCE_LOCAL) ? (byte) -1 : Ascii.US;
                    break;
                case 1878577223:
                    b = !action.equals(CommonConst.playNext) ? (byte) -1 : (byte) 32;
                    break;
                case 1905474009:
                    b = !action.equals(CommonConst.Controlfavorite) ? (byte) -1 : (byte) 33;
                    break;
                case 2130640113:
                    b = !action.equals(CommonConst.getMediaMode) ? (byte) -1 : (byte) 34;
                    break;
                default:
                    b = -1;
                    break;
            }
            switch (b) {
                case 0:
                    RLog.d("BYD_Radio MyService", "onReceive playFavoriteFre");
                    try {
                        CountDownLatch countDownLatch = new CountDownLatch(1);
                        RadioSdk.getInstance(MyService.this.getApplicationContext()).playFavoriteFre(countDownLatch);
                        countDownLatch.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    RLog.d("BYD_Radio MyService", "onReceive playMusic");
                    AudioSdk.getInstance(MyService.this.getApplicationContext()).playMusic();
                    break;
                case 2:
                    RLog.d("BYD_Radio MyService", "onReceive playRadio");
                    RadioSdk.getInstance(MyService.this.getApplicationContext()).playRadio();
                    break;
                case 3:
                    RLog.d("BYD_Radio MyService", "onReceive addfavoriteRadio");
                    try {
                        CountDownLatch countDownLatch2 = new CountDownLatch(1);
                        RadioSdk.getInstance(MyService.this.getApplicationContext()).Controlfavorite(64, countDownLatch2);
                        countDownLatch2.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                        return;
                    }
                    break;
                case 4:
                    RLog.d("BYD_Radio MyService", "onReceive removefavoriteRadio");
                    try {
                        CountDownLatch countDownLatch3 = new CountDownLatch(1);
                        RadioSdk.getInstance(MyService.this.getApplicationContext()).Controlfavorite(65, countDownLatch3);
                        countDownLatch3.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e3) {
                        e3.printStackTrace();
                        return;
                    }
                    break;
                case 5:
                    RLog.d("BYD_Radio MyService", "onReceive openFavoriteStationList");
                    try {
                        CountDownLatch countDownLatch4 = new CountDownLatch(1);
                        RadioSdk.getInstance(MyService.this.getApplicationContext()).openFavoriteStationList(countDownLatch4, true);
                        countDownLatch4.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e4) {
                        e4.printStackTrace();
                        return;
                    }
                    break;
                case 6:
                    RLog.d("BYD_Radio MyService", "onReceive changeBandAM");
                    try {
                        CountDownLatch countDownLatch5 = new CountDownLatch(1);
                        RadioSdk.getInstance(MyService.this.getApplicationContext()).changeBand(0, countDownLatch5);
                        countDownLatch5.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e5) {
                        e5.printStackTrace();
                        return;
                    }
                    break;
                case 7:
                    RLog.d("BYD_Radio MyService", "onReceive changeBandFM");
                    try {
                        CountDownLatch countDownLatch6 = new CountDownLatch(1);
                        RadioSdk.getInstance(MyService.this.getApplicationContext()).changeBand(1, countDownLatch6);
                        countDownLatch6.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e6) {
                        e6.printStackTrace();
                        return;
                    }
                    break;
                case 8:
                    RLog.d("BYD_Radio MyService", "onReceive openStationList");
                    try {
                        CountDownLatch countDownLatch7 = new CountDownLatch(1);
                        RadioSdk.getInstance(MyService.this.getApplicationContext()).openStationList(countDownLatch7);
                        countDownLatch7.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e7) {
                        e7.printStackTrace();
                        return;
                    }
                    break;
                case 9:
                    RLog.d("BYD_Radio MyService", "onReceive removeFavoriteMusic");
                    try {
                        CountDownLatch countDownLatch8 = new CountDownLatch(1);
                        AudioSdk.getInstance(MyService.this.getApplicationContext()).removeFavorite(countDownLatch8);
                        countDownLatch8.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e8) {
                        e8.printStackTrace();
                        return;
                    }
                    break;
                case 10:
                    RLog.d("BYD_Radio MyService", "onReceive playPre");
                    AudioSdk.getInstance(MyService.this.getApplicationContext()).playPre();
                    break;
                case 11:
                    RLog.d("BYD_Radio MyService", "onReceive setPlayMode");
                    try {
                        CountDownLatch countDownLatch9 = new CountDownLatch(1);
                        AudioSdk.getInstance(MyService.this.getApplicationContext()).setPlayMode(2, countDownLatch9);
                        countDownLatch9.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e9) {
                        e9.printStackTrace();
                        return;
                    }
                    break;
                case 12:
                    RLog.d("BYD_Radio MyService", "onReceive ChangeSSOURCE_SD");
                    AudioHelper.getInstance(MyService.this.getApplicationContext()).ChangeSourceAudioHelper(2, false);
                    break;
                case 13:
                    RLog.d("BYD_Radio MyService", "onReceive playFrequency");
                    try {
                        CountDownLatch countDownLatch10 = new CountDownLatch(1);
                        RadioSdk.getInstance(MyService.this.getApplicationContext()).playFrequency(92500, 1, countDownLatch10);
                        countDownLatch10.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e10) {
                        e10.printStackTrace();
                        return;
                    }
                    break;
                case 14:
                    RLog.d("BYD_Radio MyService", "onReceive closeStationList");
                    RadioSdk.getInstance(MyService.this.getApplicationContext()).closeStationList();
                    break;
                case 15:
                    RLog.d("BYD_Radio MyService", "onReceive getPacketAudio");
                    RadioSdk.getInstance(MyService.this.getApplicationContext()).getPacketAudio();
                    break;
                case 16:
                    RLog.d("BYD_Radio MyService", "onReceive ChangeSSOURCE_USB");
                    AudioHelper.getInstance(MyService.this.getApplicationContext()).ChangeSourceAudioHelper(1, true);
                    break;
                case 17:
                    RLog.d("BYD_Radio MyService", "onReceive pauseMusic");
                    AudioSdk.getInstance(MyService.this.getApplicationContext()).pauseMusic();
                    break;
                case 18:
                    RLog.d("BYD_Radio MyService", "onReceive pauseRadio");
                    RadioSdk.getInstance(MyService.this.getApplicationContext()).pauseRadio();
                    break;
                case 19:
                    RLog.d("BYD_Radio MyService", "onReceive addFavoriteMusic");
                    try {
                        CountDownLatch countDownLatch11 = new CountDownLatch(1);
                        AudioSdk.getInstance(MyService.this.getApplicationContext()).addFavorite(countDownLatch11);
                        countDownLatch11.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e11) {
                        e11.printStackTrace();
                        return;
                    }
                    break;
                case 20:
                    RLog.d("BYD_Radio MyService", "onReceive Search_orStopSearch");
                    try {
                        CustomRadioDomain.RadioResult = -1;
                        CountDownLatch countDownLatch12 = new CountDownLatch(1);
                        RadioSdk.getInstance(MyService.this.getApplicationContext()).Search_orStopSearch(16, countDownLatch12);
                        countDownLatch12.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e12) {
                        e12.printStackTrace();
                        return;
                    }
                    break;
                case 21:
                    try {
                        CountDownLatch countDownLatch13 = new CountDownLatch(1);
                        RadioSdk.getInstance(BydVoiceApplication.getContext()).SearchFrequencyTest("92.5", countDownLatch13);
                        countDownLatch13.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e13) {
                        e13.printStackTrace();
                        return;
                    }
                    break;
                case 22:
                    RLog.d("BYD_Radio MyService", "onReceive StopSearch");
                    try {
                        CustomRadioDomain.RadioResult = -1;
                        CountDownLatch countDownLatch14 = new CountDownLatch(1);
                        RadioSdk.getInstance(MyService.this.getApplicationContext()).Search_orStopSearch(18, countDownLatch14);
                        countDownLatch14.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e14) {
                        e14.printStackTrace();
                        return;
                    }
                    break;
                case 23:
                    RLog.d("BYD_Radio MyService", "onReceive playFavoriteMusic");
                    AudioSdk.getInstance(MyService.this.getApplicationContext()).playFavoriteMusic();
                    break;
                case 24:
                    RLog.d("BYD_Radio MyService", "onReceive muteRadio");
                    try {
                        CustomRadioDomain.RadioResult = -1;
                        CountDownLatch countDownLatch15 = new CountDownLatch(1);
                        RadioSdk.getInstance(BydVoiceApplication.getContext()).muteRadio(countDownLatch15);
                        countDownLatch15.await(4L, TimeUnit.SECONDS);
                        RLog.d("BYD_Radio MyService", "onReceive muteRadio  CustomRadioDomain.RadioResult==" + CustomRadioDomain.RadioResult);
                    } catch (InterruptedException e15) {
                        e15.printStackTrace();
                        return;
                    }
                    break;
                case 25:
                    RLog.d("BYD_Radio MyService", "onReceive playNextFre");
                    try {
                        CountDownLatch countDownLatch16 = new CountDownLatch(1);
                        RadioSdk.getInstance(MyService.this.getApplicationContext()).playNextFre(countDownLatch16);
                        countDownLatch16.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e16) {
                        e16.printStackTrace();
                        return;
                    }
                    break;
                case 26:
                    RLog.d("BYD_Radio MyService", "onReceive getPlayStatus");
                    RadioSdk.getInstance(MyService.this.getApplicationContext()).getPlayStatus();
                    break;
                case 27:
                    try {
                        CountDownLatch countDownLatch17 = new CountDownLatch(1);
                        RadioSdk.getInstance(BydVoiceApplication.getContext()).SearchFrequency("92.5", countDownLatch17);
                        countDownLatch17.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e17) {
                        e17.printStackTrace();
                        return;
                    }
                    break;
                case 28:
                    RLog.d("BYD_Radio MyService", "onReceive playPreFre");
                    try {
                        CountDownLatch countDownLatch18 = new CountDownLatch(1);
                        RadioSdk.getInstance(MyService.this.getApplicationContext()).playPreFre(countDownLatch18);
                        countDownLatch18.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e18) {
                        e18.printStackTrace();
                        return;
                    }
                    break;
                case 29:
                    RLog.d("BYD_Radio MyService", "onReceive showVolumeBar");
                    AudioSdk.getInstance(MyService.this.getApplicationContext()).showVolumeBar();
                    break;
                case 30:
                    RLog.d("BYD_Radio MyService", "onReceive OPEN_RADIO");
                    RLog.d("BYD_Radio MyService", "onReceive test getPacketAudio");
                    RLog.d("BYD_Radio MyService", "getPacketAudio  getCurrentAudioFocusPackageName==" + RadioSdk.getInstance(BydVoiceApplication.getContext()).getPacketAudio());
                    try {
                        CustomRadioDomain.RadioResult = -1;
                        CountDownLatch countDownLatch19 = new CountDownLatch(1);
                        RadioSdk.getInstance(BydVoiceApplication.getContext()).openradio(countDownLatch19, true);
                        countDownLatch19.await(4L, TimeUnit.SECONDS);
                        RLog.d("BYD_Radio MyService", "onReceive OPEN_RADIO  CustomRadioDomain.RadioResult==" + CustomRadioDomain.RadioResult);
                    } catch (InterruptedException e19) {
                        e19.printStackTrace();
                        return;
                    }
                    break;
                case 31:
                    RLog.d("BYD_Radio MyService", "onReceive ChangeSOURCE_LOCAL");
                    AudioHelper.getInstance(MyService.this.getApplicationContext()).ChangeSourceAudioHelper(0, true);
                    break;
                case 32:
                    RLog.d("BYD_Radio MyService", "onReceive playNext");
                    AudioSdk.getInstance(MyService.this.getApplicationContext()).playNext();
                    break;
                case 33:
                    RLog.d("BYD_Radio MyService", "onReceive Controlfavorite");
                    try {
                        CountDownLatch countDownLatch20 = new CountDownLatch(1);
                        RadioSdk.getInstance(MyService.this.getApplicationContext()).Controlfavorite(25, countDownLatch20);
                        countDownLatch20.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e20) {
                        e20.printStackTrace();
                        return;
                    }
                    break;
                case 34:
                    RLog.d("BYD_Radio MyService", "onReceive getMediaMode");
                    RadioSdk.getInstance(MyService.this.getApplicationContext()).getMediaMode();
                    break;
            }
        }
    }
}
