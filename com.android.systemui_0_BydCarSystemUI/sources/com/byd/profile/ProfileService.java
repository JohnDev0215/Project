package com.byd.profile;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.bydauto.audio.AbsBYDAutoAudioListener;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import com.byd.profile.aidl.IProfileService;
import com.byd.profile.aidl.IRestListener;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
public class ProfileService extends Service {
    private static final int WHAT_FINISH = 2;
    private static final int WHAT_UPDATE = 1;
    private BYDAutoAudioDevice mBYDAutoAudioDevice;
    private BYDAutoBodyworkDevice mBYDAutoBodyworkDevice;
    private BYDAutoGearboxDevice mBYDAutoGearboxDevice;
    private BYDAutoStatisticDevice mBYDAutoStatisticDevice;
    private ScheduledExecutorService mExecutorService;
    private ScheduledFuture mFuture;
    private IRestListener mListener;
    private final AtomicInteger mRemainder = new AtomicInteger(0);
    private final IProfileService.Stub mBinder = new IProfileService.Stub() { // from class: com.byd.profile.ProfileService.1
        @Override // com.byd.profile.aidl.IProfileService
        public void registerRestListener(IRestListener listener) throws RemoteException {
            ProfileService.this.mListener = listener;
        }

        @Override // com.byd.profile.aidl.IProfileService
        public void startTimer(int duration) throws RemoteException {
            ProfileService.this.startTimer(duration);
        }

        @Override // com.byd.profile.aidl.IProfileService
        public void cancelTimer() throws RemoteException {
            ProfileService.this.cancelTimer();
        }

        @Override // com.byd.profile.aidl.IProfileService
        public int getRestTime() throws RemoteException {
            return ProfileService.this.getRemainder();
        }
    };
    private final Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.byd.profile.ProfileService.3
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (ProfileService.this.mListener != null) {
                try {
                    if (msg.what == 1) {
                        ProfileService.this.mListener.onRestTimeChange(((Integer) msg.obj).intValue());
                    } else if (msg.what == 2) {
                        ProfileService.this.mListener.onRestFinish();
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private final AbsBYDAutoGearboxListener mAbsBYDAutoGearboxListener = new AbsBYDAutoGearboxListener() { // from class: com.byd.profile.ProfileService.4
        @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
        public void onGearboxAutoModeTypeChanged(int level) {
            super.onGearboxAutoModeTypeChanged(level);
            BydLogHelper.logD("onGearboxAutoModeTypeChanged level = " + level);
            if (level != 1) {
                ProfileManager.getInstance(ProfileService.this).onGearboxChange();
            }
        }
    };
    private final AbsBYDAutoBodyworkListener mAbsBYDAutoBodyworkListener = new AbsBYDAutoBodyworkListener() { // from class: com.byd.profile.ProfileService.5
        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onDoorStateChanged(int area, int state) {
            super.onDoorStateChanged(area, state);
            BydLogHelper.logD("onDoorStatusChanged area = " + area + " ,state = " + state);
            if (state == 2) {
                ProfileManager.getInstance(ProfileService.this).onDoorOpened();
            }
        }
    };
    private final AbsBYDAutoStatisticListener mAbsBYDAutoStatisticListener = new AbsBYDAutoStatisticListener() { // from class: com.byd.profile.ProfileService.6
        @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onElecPercentageChanged(double value) {
            super.onElecPercentageChanged(value);
            BydLogHelper.logD("onElecPercentageChanged");
            ProfileManager.getInstance(ProfileService.this).onBatteryChange();
        }
    };
    private final AbsBYDAutoAudioListener mAbsBYDAutoAudioListener = new AbsBYDAutoAudioListener() { // from class: com.byd.profile.ProfileService.7
        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onVolumeChanged(int value, int flag) {
            super.onVolumeChanged(value, flag);
            BydLogHelper.logD("onVolumeChanged value = " + value + " ,flag = " + flag);
        }
    };

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        return 1;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        this.mListener = null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        BydLogHelper.logD("onCreate");
        this.mExecutorService = Executors.newSingleThreadScheduledExecutor();
        try {
            BYDAutoBodyworkDevice bYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(this);
            this.mBYDAutoBodyworkDevice = bYDAutoBodyworkDevice;
            bYDAutoBodyworkDevice.registerListener(this.mAbsBYDAutoBodyworkListener);
            BYDAutoStatisticDevice bYDAutoStatisticDevice = BYDAutoStatisticDevice.getInstance(this);
            this.mBYDAutoStatisticDevice = bYDAutoStatisticDevice;
            bYDAutoStatisticDevice.registerListener(this.mAbsBYDAutoStatisticListener);
            BYDAutoGearboxDevice bYDAutoGearboxDevice = BYDAutoGearboxDevice.getInstance(this);
            this.mBYDAutoGearboxDevice = bYDAutoGearboxDevice;
            bYDAutoGearboxDevice.registerListener(this.mAbsBYDAutoGearboxListener);
            BYDAutoAudioDevice bYDAutoAudioDevice = BYDAutoAudioDevice.getInstance(this);
            this.mBYDAutoAudioDevice = bYDAutoAudioDevice;
            bYDAutoAudioDevice.registerListener(this.mAbsBYDAutoAudioListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacksAndMessages(null);
        cancelTimer();
        this.mExecutorService.shutdown();
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.mBYDAutoBodyworkDevice;
        if (bYDAutoBodyworkDevice != null) {
            bYDAutoBodyworkDevice.unregisterListener(this.mAbsBYDAutoBodyworkListener);
        }
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.mBYDAutoStatisticDevice;
        if (bYDAutoStatisticDevice != null) {
            bYDAutoStatisticDevice.unregisterListener(this.mAbsBYDAutoStatisticListener);
        }
        BYDAutoGearboxDevice bYDAutoGearboxDevice = this.mBYDAutoGearboxDevice;
        if (bYDAutoGearboxDevice != null) {
            bYDAutoGearboxDevice.unregisterListener(this.mAbsBYDAutoGearboxListener);
        }
        BYDAutoAudioDevice bYDAutoAudioDevice = this.mBYDAutoAudioDevice;
        if (bYDAutoAudioDevice != null) {
            bYDAutoAudioDevice.unregisterListener(this.mAbsBYDAutoAudioListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getRemainder() {
        BydLogHelper.logE("getRemainder = " + this.mRemainder.get());
        if (!ProfileManager.getInstance(this).isRestStarted()) {
            this.mRemainder.set(0);
        }
        return this.mRemainder.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startTimer(int duration) {
        BydLogHelper.logE("startTimer duration = " + duration);
        ScheduledFuture scheduledFuture = this.mFuture;
        if (scheduledFuture != null && !scheduledFuture.isDone()) {
            this.mFuture.cancel(true);
        }
        this.mRemainder.set(duration);
        this.mFuture = this.mExecutorService.scheduleWithFixedDelay(new Runnable() { // from class: com.byd.profile.ProfileService.2
            @Override // java.lang.Runnable
            public void run() {
                ProfileService.this.mRemainder.decrementAndGet();
                Message messageObtainMessage = ProfileService.this.mHandler.obtainMessage();
                if (ProfileService.this.mRemainder.get() == 0) {
                    messageObtainMessage.what = 2;
                    messageObtainMessage.sendToTarget();
                    ProfileManager.getInstance(ProfileService.this).onRestFinish();
                    ProfileService.this.mFuture.cancel(true);
                    return;
                }
                messageObtainMessage.what = 1;
                messageObtainMessage.obj = Integer.valueOf(ProfileService.this.mRemainder.get());
                messageObtainMessage.sendToTarget();
            }
        }, 1L, 1L, TimeUnit.MINUTES);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelTimer() {
        this.mRemainder.set(0);
        ScheduledFuture scheduledFuture = this.mFuture;
        if (scheduledFuture != null && !scheduledFuture.isDone()) {
            this.mFuture.cancel(true);
        }
        this.mFuture = null;
    }
}
