package com.ts.appservice.settings.impl.microphone;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.AudioRecordingConfiguration;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.RemoteException;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.microphone.IMicSettingsCallback;
import com.ts.lib.settings.microphone.IMicSettingsService;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class IMicroPhoneServiceBinder extends IMicSettingsService.Stub {
    private static final String TAG = IMicroPhoneServiceBinder.class.getSimpleName();
    private AudioManager mAudioManager;
    private Context mContext;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private MicCallback mMicCallback;
    private String mMicUsePackageName;
    private boolean mMicUserStatus;

    public IMicroPhoneServiceBinder(Context context) {
        this.mContext = context;
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        this.mAudioManager = (AudioManager) this.mContext.getSystemService("audio");
        registerMicCallback();
        this.mMicCallback = new MicCallback(this.mContext);
    }

    public int getChargeStatus() {
        LogUtil.debug(TAG, "getChargeStatus value ");
        return -1;
    }

    public int getPowerStatus() {
        LogUtil.debug(TAG, "getPowerStatus value ");
        return -1;
    }

    public String getMicUsePackageName() {
        LogUtil.info(TAG, "getMicUsePackageName value = " + this.mMicUsePackageName);
        return this.mMicUsePackageName;
    }

    public void subscribeMicCallback(IMicSettingsCallback callback) throws RemoteException {
        if (callback != null) {
            this.mMicCallback.register(callback);
        }
    }

    public void unsubscribeMicCallback(IMicSettingsCallback callback) throws RemoteException {
        if (callback != null) {
            this.mMicCallback.unregister(callback);
        }
    }

    public void destoryMicBinder() {
        MicCallback micCallback = this.mMicCallback;
        if (micCallback != null) {
            micCallback.kill();
        }
    }

    private void registerMicCallback() {
        LogUtil.debug(TAG, "registerMicCallback: start");
        this.mAudioManager.registerAudioRecordingCallback(new AudioManager.AudioRecordingCallback() { // from class: com.ts.appservice.settings.impl.microphone.IMicroPhoneServiceBinder.1
            @Override // android.media.AudioManager.AudioRecordingCallback
            public void onRecordingConfigChanged(List<AudioRecordingConfiguration> configs) {
                LogUtil.info(IMicroPhoneServiceBinder.TAG, "configs.size is " + configs.size());
                super.onRecordingConfigChanged(configs);
                if (configs.size() == 0) {
                    LogUtil.info(IMicroPhoneServiceBinder.TAG, "no mic run !");
                    IMicroPhoneServiceBinder.this.mMicCallback.onMicUseChanged(IMicroPhoneServiceBinder.this.mMicUsePackageName, false);
                    IMicroPhoneServiceBinder.this.mMicUsePackageName = null;
                    return;
                }
                if (configs.size() != 0) {
                    for (AudioRecordingConfiguration configuration : configs) {
                        if (configuration.getClientAudioSource() == 1) {
                            String micUsePackageName = configuration.getClientPackageName();
                            PackageManager pm = IMicroPhoneServiceBinder.this.mContext.getPackageManager();
                            try {
                                ApplicationInfo appInfo = pm.getApplicationInfo(micUsePackageName, 0);
                                if ((appInfo.flags & 1) != 0) {
                                    LogUtil.info(IMicroPhoneServiceBinder.TAG, "onRecordingConfigChanged ignored packageName : " + micUsePackageName);
                                } else {
                                    IMicroPhoneServiceBinder.this.mMicUsePackageName = micUsePackageName;
                                    IMicroPhoneServiceBinder.this.mMicUserStatus = true;
                                    IMicroPhoneServiceBinder.this.mMicCallback.onMicUseChanged(IMicroPhoneServiceBinder.this.mMicUsePackageName, true);
                                    LogUtil.info(IMicroPhoneServiceBinder.TAG, "configuration getClientPackageName is " + IMicroPhoneServiceBinder.this.mMicUsePackageName);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    if (!IMicroPhoneServiceBinder.this.mMicUserStatus) {
                        IMicroPhoneServiceBinder.this.mMicCallback.onMicUseChanged(IMicroPhoneServiceBinder.this.mMicUsePackageName, false);
                    }
                    IMicroPhoneServiceBinder.this.mMicUserStatus = false;
                }
            }
        }, this.mHandler);
    }
}
