package com.byd.systemsettings.screensaver.present;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.audio.AbsBYDAutoAudioListener;
import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.media.AudioFocusInfo;
import android.media.AudioManager;
import android.os.Parcel;
import android.os.UserHandle;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import com.byd.systemsettings.cpaa.CarPlayAAConstant;
import com.byd.systemsettings.screensaver.AudioScreenPager;
import com.byd.systemsettings.screensaver.model.BydAudioFocusInfo;
import com.byd.systemsettings.screensaver.model.MediaPlayData;
import com.byd.systemsettings.system.BydSystemSettings;
import com.byd.systemsettings.system.SoundBoxManager;
import com.byd.systemsettings.system.callback.SettingsCallback;
import com.byd.systemsettings.system.model.ClockStyle;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import m4.e;
import x3.a;
import y2.b;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class AudioScreenPresenter extends f<AudioScreenPager> {
    private static final String ACTION_REQUEST_FOR_MEDIA_INFO = "com.byd.ACTION_REQUEST_FOR_MEDIA_INFO";
    public static final int ARG1_UPDATE_CLOCK_STYLE = 50;
    public static final int ARG1_UPDATE_MEDIA = 49;
    private static final int[] MEDIA_USAGE_SUPPROT = {1, 24, 33, 25};
    private static final String PKG_ANDROID_AUTO_FOCUS = "com.ts.androidauto.projectionservice";
    private static final String PKG_ANDROID_BT_FOCUS = "com.android.bluetooth";
    private static final String PKG_CARPLAY_FOCUS = "com.ts.carplay";
    private static final String PKG_LOCAL = "com.byd.localmusic";
    private static final String PKG_LOCAL_FOCUS = "com.byd.localmusic";
    private static final String PKG_SPOTIFY = "com.telenav.scoutivi.spotify.byd";
    private static final String PKG_SPOTIFY_FOCUS = "com.spotify.music";
    private static final String RECEIVER_MEDIA_CENTER = "com.byd.music.utils.mediahelper.MediaButtonReceiver";
    private static final String TAG_S = "AudioScreenPresenter";
    private static final long TIMEOUT_PLAY_STATE_PAUSE = 60000;
    private AudioManager am;
    private ClockStyleChangedCallback clockStyleCallback;
    private InstrumentCallback musicCallback;
    private volatile int curPlayState = 0;
    private volatile int curMusicSrc = 0;
    private volatile long musicInfoUpdateTime = 0;
    private final BydSystemSettings settings = BydSystemSettings.Singleton.get();

    public static class AutoAudioFocusChangedCallback extends AbsBYDAutoAudioListener {
        private final WeakReference<AudioScreenPresenter> ref;

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener, b.d
        public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
            super.isConnected(z7);
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener, b.d
        public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
            super.isOptimizationApi(z7);
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onAudioFocusChanged(int i8, List<AudioFocusInfo> list) {
            WeakReference<AudioScreenPresenter> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().audioFocusChanged(i8, list);
        }

        private AutoAudioFocusChangedCallback(AudioScreenPresenter audioScreenPresenter) {
            this.ref = new WeakReference<>(audioScreenPresenter);
        }
    }

    public static class ClockStyleChangedCallback extends SettingsCallback {
        private final WeakReference<AudioScreenPresenter> ref;

        @Override // com.byd.systemsettings.system.callback.SettingsCallback
        public void onValueChanged() {
            WeakReference<AudioScreenPresenter> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null || this.ref.get().getV() == null) {
                return;
            }
            ((AudioScreenPager) this.ref.get().getV()).sendUIMessage(50, this.ref.get().getClockStyle());
        }

        private ClockStyleChangedCallback(AudioScreenPresenter audioScreenPresenter) {
            super(BydSystemSettings.BYD_SCREENSAVER_MODE);
            this.ref = new WeakReference<>(audioScreenPresenter);
        }
    }

    public static class InstrumentCallback extends AbsBYDAutoInstrumentListener {
        private final WeakReference<AudioScreenPresenter> ref;

        @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener, b.d
        public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
            super.isConnected(z7);
        }

        @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener, b.d
        public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
            super.isOptimizationApi(z7);
        }

        @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
        public void onMusicInfoUrlChange(String str, String str2, int i8, int i9, String str3) {
            a.a(AudioScreenPresenter.TAG_S, "---onMusicInfoUrlChange---mn: " + str + ", playState: " + i8 + ", source: " + i9);
            if (this.ref.get() != null) {
                this.ref.get().curMusicSrc = i9;
                this.ref.get().curPlayState = i8;
                this.ref.get().musicInfoUpdateTime = System.currentTimeMillis();
            }
            MediaPlayData mediaPlayData = new MediaPlayData(str, str2, i8, i9, str3);
            if (this.ref.get() == null || this.ref.get().getV() == null) {
                return;
            }
            ((AudioScreenPager) this.ref.get().getV()).sendUIMessage(49, mediaPlayData);
        }

        private InstrumentCallback(AudioScreenPresenter audioScreenPresenter) {
            this.ref = new WeakReference<>(audioScreenPresenter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void audioFocusChanged(int i8, List<AudioFocusInfo> list) {
        AudioFocusInfo audioFocusInfo;
        a.a(TAG_S, "---audioFocusChanged---zoneId: " + i8 + ", focusInfoList: " + list);
        BydAudioFocusInfo musicAudioFocusInfo = getMusicAudioFocusInfo(list);
        if (musicAudioFocusInfo == null || (audioFocusInfo = musicAudioFocusInfo.info) == null) {
            return;
        }
        onMusicFocusInfoChanged(audioFocusInfo);
    }

    private String getAudioFocusPkg() {
        int i8 = this.curMusicSrc;
        if (i8 == 1 || i8 == 2 || i8 == 3 || i8 == 4) {
            return "com.byd.localmusic";
        }
        if (i8 == 6) {
            return PKG_ANDROID_BT_FOCUS;
        }
        if (i8 == 7) {
            return "com.byd.localmusic";
        }
        if (i8 == 30) {
            return PKG_CARPLAY_FOCUS;
        }
        if (i8 == 31) {
            return PKG_ANDROID_AUTO_FOCUS;
        }
        switch (i8) {
            case 16:
                return PKG_SPOTIFY_FOCUS;
            case 17:
            case 18:
                return "com.byd.localmusic";
            default:
                return null;
        }
    }

    private BydAudioFocusInfo getMusicAudioFocusInfo(List<AudioFocusInfo> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        BydAudioFocusInfo bydAudioFocusInfo = null;
        for (AudioFocusInfo audioFocusInfo : list) {
            int usage = audioFocusInfo.getAttributes().getUsage();
            a.a(TAG_S, "---getMusicAudioFocusInfo---usage: " + usage + ", afi.pkg: " + audioFocusInfo.getPackageName());
            if (supprtMedia(usage)) {
                a.a(TAG_S, "---getMusicAudioFocusInfo---afi.getAttributes: " + audioFocusInfo.getAttributes());
                if (1 == usage && PKG_ANDROID_BT_FOCUS.equals(audioFocusInfo.getPackageName()) && isBTInvailable()) {
                    return null;
                }
                return new BydAudioFocusInfo(audioFocusInfo, true);
            }
            bydAudioFocusInfo = new BydAudioFocusInfo(audioFocusInfo, false);
        }
        return bydAudioFocusInfo;
    }

    private boolean isBTInvailable() {
        b bVarA = b.o.a();
        if (!bVarA.U()) {
            a.a(TAG_S, "---isBTInvailable---BT is not connected.");
            return true;
        }
        Iterator<BluetoothDevice> it = bVarA.G().iterator();
        while (it.hasNext()) {
            if (bVarA.R(it.next())) {
                a.a(TAG_S, "---isBTInvailable---A2DP is connected.");
                return false;
            }
        }
        return true;
    }

    private boolean isNonMediaPlaying(boolean z7) {
        if (z7) {
            return false;
        }
        int i8 = this.curPlayState;
        if (i8 == 1) {
            return true;
        }
        long jLongValue = new BigDecimal(System.currentTimeMillis()).subtract(new BigDecimal(this.musicInfoUpdateTime)).longValue();
        a.a(TAG_S, "---isNonMediaPlaying---state: " + i8 + ", delta: " + jLongValue);
        return i8 == 2 && jLongValue < TIMEOUT_PLAY_STATE_PAUSE;
    }

    private void mediaPause() {
        a.a(TAG_S, "---mediaPause---");
        KeyEvent keyEvent = new KeyEvent(0, 127);
        KeyEvent keyEvent2 = new KeyEvent(1, 127);
        this.am.dispatchMediaKeyEvent(keyEvent);
        this.am.dispatchMediaKeyEvent(keyEvent2);
    }

    private void mediaPlay() {
        a.a(TAG_S, "---mediaPlay---");
        KeyEvent keyEvent = new KeyEvent(0, 126);
        KeyEvent keyEvent2 = new KeyEvent(1, 126);
        this.am.dispatchMediaKeyEvent(keyEvent);
        this.am.dispatchMediaKeyEvent(keyEvent2);
    }

    private void onMusicFocusInfoChanged(AudioFocusInfo audioFocusInfo) {
        a.a(TAG_S, "---onMusicFocusInfoChanged---info: " + audioFocusInfo);
    }

    private boolean supprtMedia(int i8) {
        for (int i9 : MEDIA_USAGE_SUPPROT) {
            if (i9 == i8) {
                return true;
            }
        }
        return false;
    }

    public ClockStyle getClockStyle() {
        return this.settings.getScreenSaverClockStyle(getV().getContext());
    }

    public String getConnectedBtName() {
        return b.o.a().H();
    }

    public Locale getCurLanguage() {
        return this.settings.getCurLanguage(getV().getContext());
    }

    public Drawable getLocalMediaAppIcon() {
        return e.g(getV().getContext(), "com.byd.localmusic");
    }

    public String getLocalMediaAppName() {
        return e.h(getV().getContext(), "com.byd.localmusic");
    }

    public Drawable getSpotifyAppIcon() {
        return e.g(getV().getContext(), PKG_SPOTIFY);
    }

    public String getSpotifyAppName() {
        return e.h(getV().getContext(), PKG_SPOTIFY);
    }

    public void init() {
        a.a(TAG_S, "---init---");
        this.settings.init(getV().getContext());
        if (this.clockStyleCallback == null) {
            this.clockStyleCallback = new ClockStyleChangedCallback();
        }
        this.settings.registerGlobalSettingsCallback(getV().getContext(), BydSystemSettings.BYD_SCREENSAVER_MODE, this.clockStyleCallback);
        this.am = (AudioManager) getV().getContext().getSystemService("audio");
        if (this.musicCallback == null) {
            this.musicCallback = new InstrumentCallback();
        }
        SoundBoxManager soundBoxManager = SoundBoxManager.Singleton.get();
        soundBoxManager.init(getV().getContext().getApplicationContext());
        soundBoxManager.registerListener(this.musicCallback);
        soundBoxManager.registerBydAudioListener(getClass().getName(), new AutoAudioFocusChangedCallback());
    }

    @SuppressLint({"MissingPermission"})
    public void initMediaInfo() {
        Parcel parcelObtain = Parcel.obtain();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.byd.localmusic", RECEIVER_MEDIA_CENTER));
        intent.setAction(ACTION_REQUEST_FOR_MEDIA_INFO);
        getV().getContext().sendBroadcastAsUser(intent, UserHandle.readFromParcel(parcelObtain));
        parcelObtain.recycle();
        a.a(TAG_S, "---initMediaInfo---sendBroadcastAsUser---");
    }

    public boolean is24H() {
        return DateFormat.is24HourFormat(getV().getContext());
    }

    public boolean isMusicActive() {
        String str;
        List<AudioFocusInfo> curAudioFocusInfo = SoundBoxManager.Singleton.get().getCurAudioFocusInfo();
        String[] strArr = new String[1];
        StringBuilder sb = new StringBuilder();
        sb.append("---isMusicActive---");
        if (curAudioFocusInfo == null) {
            str = "list is null";
        } else {
            str = "list.size: " + curAudioFocusInfo.size();
        }
        sb.append(str);
        strArr[0] = sb.toString();
        a.a(TAG_S, strArr);
        BydAudioFocusInfo musicAudioFocusInfo = getMusicAudioFocusInfo(curAudioFocusInfo);
        String packageName = null;
        if (musicAudioFocusInfo != null) {
            a.a(TAG_S, "---isMusicActive--musicFocusInfo: " + musicAudioFocusInfo);
            if (isNonMediaPlaying(musicAudioFocusInfo.isMediaType)) {
                return true;
            }
            AudioFocusInfo audioFocusInfo = musicAudioFocusInfo.info;
            if (audioFocusInfo != null) {
                packageName = audioFocusInfo.getPackageName();
            }
        }
        if (this.curMusicSrc == 16) {
            a.c(TAG_S, "---isMusicActive--MUSIC_SRC_SPOTIFY--pkgMusic: " + packageName);
            return packageName == null || PKG_SPOTIFY_FOCUS.equals(packageName) || PKG_SPOTIFY.equals(packageName);
        }
        String audioFocusPkg = getAudioFocusPkg();
        a.a(TAG_S, "---isMusicActive---pkgMusic: " + packageName + ", srcPkg: " + audioFocusPkg);
        return packageName != null && packageName.equals(audioFocusPkg);
    }

    public void nextSong() {
        KeyEvent keyEvent = new KeyEvent(0, 87);
        KeyEvent keyEvent2 = new KeyEvent(1, 87);
        this.am.dispatchMediaKeyEvent(keyEvent);
        this.am.dispatchMediaKeyEvent(keyEvent2);
        a.a(TAG_S, "---nextSong---");
    }

    public void preSong() {
        KeyEvent keyEvent = new KeyEvent(0, 88);
        KeyEvent keyEvent2 = new KeyEvent(1, 88);
        this.am.dispatchMediaKeyEvent(keyEvent);
        this.am.dispatchMediaKeyEvent(keyEvent2);
        a.a(TAG_S, "---preSong---");
    }

    public void setClockStyle(ClockStyle clockStyle) {
        this.settings.setScreenSaverClockStyle(getV().getContext(), clockStyle);
    }

    public void setNavibarModeScreensaver(ClockStyle clockStyle) {
        a.a(TAG_S, "---setNavibarModeScreensaver---style: " + clockStyle);
        Context context = getV().getContext();
        if (clockStyle == ClockStyle.CLOSE) {
            this.settings.setNavibarModeScreensaver(context, 0);
        } else {
            this.settings.setNavibarModeScreensaver(context, 2);
        }
    }

    public void startOrStopPlay() {
        a.a(TAG_S, "---startOrStopPlay---curPlayState: " + this.curPlayState);
        if (this.curPlayState == 1) {
            mediaPause();
        } else {
            mediaPlay();
        }
    }

    public void startPlayerApp(int i8) {
        a.a(TAG_S, "---startPlayerApp---musicSrc: " + i8);
        if (i8 != 1 && i8 != 2 && i8 != 3 && i8 != 4 && i8 != 6 && i8 != 7) {
            if (i8 == 30) {
                startPlayerApp(CarPlayAAConstant.CAR_PLAY_PACKAGE_NAME);
                return;
            } else {
                if (i8 == 31) {
                    startPlayerApp(CarPlayAAConstant.ANDROID_AUTO_PACKAGE_NAME);
                    return;
                }
                switch (i8) {
                    case 16:
                        startPlayerApp(PKG_SPOTIFY);
                        break;
                }
            }
        }
        startPlayerApp("com.byd.localmusic");
    }

    public void systemSleep() {
    }

    @Override // z3.f
    public void unbindV() {
        BydSystemSettings bydSystemSettings = this.settings;
        if (bydSystemSettings != null) {
            bydSystemSettings.unregisterSettingsCallback(getV().getContext(), this.clockStyleCallback);
        }
        super.unbindV();
        a.a(TAG_S, "---unbindV---");
        SoundBoxManager soundBoxManager = SoundBoxManager.Singleton.get();
        soundBoxManager.unregisterListener(this.musicCallback);
        soundBoxManager.unregisterBydAudioListener(getClass().getName());
        this.musicCallback = null;
        this.clockStyleCallback = null;
    }

    @Override // z3.f
    public void bindV(AudioScreenPager audioScreenPager) {
        super.bindV(audioScreenPager);
    }

    private void startPlayerApp(String str) {
        a.a(TAG_S, "---startPlayerApp---pkg: " + str);
        e.k(getV().getContext(), str);
    }
}
