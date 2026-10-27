package com.byd.systemsettings.system;

import android.car.Car;
import android.content.Context;
import android.hardware.bydauto.audio.AbsBYDAutoAudioListener;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.hardware.bydauto.audio.tsimpl.BYDCarServiceBase;
import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.media.AudioFocusInfo;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public class SoundBoxManager {
    public static final long CAN_INTERVAL = 300;
    public static final int CAR_ALL = 0;
    public static final int DYNAMIC = 1;
    public static final int INVALID = -1;
    public static final int STYLE_ROCK = 7;
    private static final String TAG_M = "SoundBoxManager";
    private volatile boolean isInited;
    private BYDAutoAudioDevice mAudio;
    private BYDCarServiceBase mCarServiceBase;
    private BYDAutoInstrumentDevice mDevice;
    private final ConcurrentHashMap<String, AbsBYDAutoAudioListener> mListenerMap;

    public static class Singleton {
        private static final SoundBoxManager instance = new SoundBoxManager();

        public static SoundBoxManager get() {
            return instance;
        }
    }

    public List<AudioFocusInfo> getCurAudioFocusInfo() {
        return this.mAudio.getCurAudioFocusInfo(0);
    }

    public void init(Context context) {
        a.c(TAG_M, "init", "---init---isInited: " + this.isInited);
        if (context == null || this.isInited) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        this.isInited = true;
        BYDAutoStudy.getInstance(applicationContext);
        if (this.mCarServiceBase == null) {
            this.mCarServiceBase = new BYDCarServiceBase(applicationContext) { // from class: com.byd.systemsettings.system.SoundBoxManager.1
                @Override // android.hardware.bydauto.audio.tsimpl.BYDCarServiceBase
                public void onServiceConnected(Car car) {
                    a.a(SoundBoxManager.TAG_M, "init", ", onServiceConnected car:" + car);
                }

                @Override // android.hardware.bydauto.audio.tsimpl.BYDCarServiceBase
                public void onServiceDied() {
                    a.a(SoundBoxManager.TAG_M, "init", ", onServiceDied:");
                }
            };
        }
        if (this.mAudio == null) {
            this.mAudio = BYDAutoAudioDevice.getInstance(applicationContext);
        }
        this.mDevice = BYDAutoInstrumentDevice.getInstance(applicationContext);
    }

    public void registerBydAudioListener(String str, AbsBYDAutoAudioListener absBYDAutoAudioListener) {
        this.mListenerMap.put(str, absBYDAutoAudioListener);
    }

    public void registerListener(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.mDevice;
        if (bYDAutoInstrumentDevice == null) {
            return;
        }
        bYDAutoInstrumentDevice.registerListener(absBYDAutoInstrumentListener);
    }

    public void unregisterBydAudioListener(String str) {
        this.mListenerMap.remove(str);
    }

    public void unregisterListener(AbsBYDAutoInstrumentListener absBYDAutoInstrumentListener) {
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = this.mDevice;
        if (bYDAutoInstrumentDevice == null) {
            return;
        }
        bYDAutoInstrumentDevice.unregisterListener(absBYDAutoInstrumentListener);
    }

    private SoundBoxManager() {
        this.isInited = false;
        this.mListenerMap = new ConcurrentHashMap<>();
    }
}
