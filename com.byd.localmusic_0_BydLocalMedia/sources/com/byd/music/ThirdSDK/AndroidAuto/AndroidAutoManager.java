package com.byd.music.ThirdSDK.AndroidAuto;

import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.blankj.utilcode.util.Utils;
import com.byd.music.OnLineMusic;
import com.byd.music.ThirdSDK.AndroidAuto.AndroidAutoManager;
import com.byd.music.ThirdSDK.ThirdManager;
import com.byd.music.utils.CurrentMediaInfoUtils;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.StringSubUtil;
import com.ts.car.core.ServiceProvider;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.androidauto.IAndroidAutoAppListener;
import ts.car.service.androidauto.IAndroidAutoAppManager;
import ts.car.service.androidauto.IAndroidAutoSessionStatusListener;
import ts.car.service.androidauto.IMediaDataListener;
import ts.car.service.androidauto.common.VehicleConst;
import ts.car.service.androidauto.data.MediaMetadata;

/* JADX INFO: loaded from: classes5.dex */
public class AndroidAutoManager {
    public static final String AndroidAutoAudioFocusPKG = "com.ts.androidauto.projectionservice";
    public static final String AndroidAutoPKG = "com.ts.androidauto.app";
    private static final String TAG = "AndroidAutoManager";
    private static boolean isInit;
    private static IAndroidAutoAppManager mAndroidAutoAppManager;
    private static int mConnectionStatus;
    private static int playState;

    public static void init() {
        LogUtil.e(TAG, "init enter");
        if (!ThirdManager.isSupportType(101)) {
            LogUtil.e(TAG, "init with androidauto disable");
            return;
        }
        IAndroidAutoAppManager iAndroidAutoAppManager = (IAndroidAutoAppManager) ServiceProvider.get(IAndroidAutoAppManager.class, Utils.getApp());
        mAndroidAutoAppManager = iAndroidAutoAppManager;
        if (iAndroidAutoAppManager == null) {
            LogUtil.e(TAG, "init with get = null, just return.");
        } else {
            iAndroidAutoAppManager.registerConnectionListener(new AnonymousClass1());
        }
    }

    /* JADX INFO: renamed from: com.byd.music.ThirdSDK.AndroidAuto.AndroidAutoManager$1, reason: invalid class name */
    class AnonymousClass1 implements IServiceConnectionListener {
        AnonymousClass1() {
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService iPlatformService) {
            LogUtil.e(AndroidAutoManager.TAG, "registerConnectionListener onServiceConnected");
            IAndroidAutoAppManager unused = AndroidAutoManager.mAndroidAutoAppManager = (IAndroidAutoAppManager) iPlatformService;
            boolean z = true;
            boolean unused2 = AndroidAutoManager.isInit = true;
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("mAndroidAutoAppManager.registerAndroidAutoSessionStatusListener enter with mAndroidAutoAppManager == null ? --> ");
                sb.append(AndroidAutoManager.mAndroidAutoAppManager == null);
                LogUtil.d(AndroidAutoManager.TAG, sb.toString());
                AndroidAutoManager.mAndroidAutoAppManager.registerAndroidAutoSessionStatusListener(new IAndroidAutoSessionStatusListener() { // from class: com.byd.music.ThirdSDK.AndroidAuto.AndroidAutoManager.1.1
                    @Override // ts.car.service.androidauto.IAndroidAutoSessionStatusListener
                    public void onSessionStatusChanged(int i, int i2, String str) {
                        LogUtil.d(AndroidAutoManager.TAG, "onSessionStatusChanged: status=" + i + " errorCode=" + i2 + " message=" + str);
                        int unused3 = AndroidAutoManager.mConnectionStatus = i;
                        AndroidAutoManager.updateConnectState();
                    }
                });
                LogUtil.d(AndroidAutoManager.TAG, "mAndroidAutoAppManager.registerAndroidAutoSessionStatusListener finish...");
                AndroidAutoManager.mAndroidAutoAppManager.registerAndroidAutoAppListener(new IAndroidAutoAppListener() { // from class: com.byd.music.ThirdSDK.AndroidAuto.AndroidAutoManager.1.2
                    @Override // ts.car.service.androidauto.IAndroidAutoAppListener
                    public void onVideoFocusStatusChanged(int i) {
                    }
                });
                AndroidAutoManager.mAndroidAutoAppManager.registerMediaDataListener(new IMediaDataListener() { // from class: com.byd.music.ThirdSDK.AndroidAuto.AndroidAutoManager.1.3
                    @Override // ts.car.service.androidauto.IMediaDataListener
                    public void onMusicStatusChanged(int i) {
                        LogUtil.d(AndroidAutoManager.TAG, "onMusicStatusChanged status = " + i);
                        int unused3 = AndroidAutoManager.playState = i;
                        if (ThirdManager.isAndroidAutoConnected()) {
                            if (AndroidAutoManager.AndroidAutoAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage) && i == 1) {
                                OnLineMusic.setMusicEngine(101);
                            }
                            if (OnLineMusic.musicEngine == 101) {
                                CurrentMediaInfoUtils.getInstance().setPlayingState(i == 1);
                            }
                        }
                    }

                    @Override // ts.car.service.androidauto.IMediaDataListener
                    public void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
                        if (mediaMetadata != null) {
                            LogUtil.d(AndroidAutoManager.TAG, "onMediaMetadataChanged metadata getSong = " + StringSubUtil.subString(mediaMetadata.getSong()) + ", art.length = " + (mediaMetadata.getAlbumArt() == null ? -1 : mediaMetadata.getAlbumArt().length));
                        } else {
                            LogUtil.d(AndroidAutoManager.TAG, "onMediaMetadataChanged metadata get a null object ");
                        }
                        ThirdManager.getInstance().setAaMusic(mediaMetadata);
                        if (ThirdManager.isAndroidAutoConnected()) {
                            int i = OnLineMusic.musicEngine;
                        }
                    }

                    @Override // ts.car.service.androidauto.IMediaDataListener
                    public void onMediaProgressChanged(int i) {
                        LogUtil.d(AndroidAutoManager.TAG, "onMediaProgressChanged progress = " + i + ", playState = " + AndroidAutoManager.playState);
                        if (!CurrentMediaInfoUtils.getInstance().getPlayingState() && OnLineMusic.musicEngine == 101 && AndroidAutoManager.playState == 1) {
                            CurrentMediaInfoUtils.getInstance().setPlayingState(true);
                        }
                        if (ThirdManager.isAndroidAutoConnected() && OnLineMusic.musicEngine == 101 && ThirdManager.getInstance().getAaMusic() != null) {
                            CurrentMediaInfoUtils.getInstance().updateProgressAndNotify(i * 1000, ThirdManager.getInstance().getAaMusic().getDurationSeconds() * 1000, ThirdManager.getInstance().getAaMusic().getDurationSeconds() * 1000);
                        }
                    }
                });
                LogUtil.d(AndroidAutoManager.TAG, "mAndroidAutoAppManager.registerMediaDataListener finish...");
                AndroidAutoManager.initConnectState();
                if (ThirdManager.isAndroidAutoConnected()) {
                    int unused3 = AndroidAutoManager.playState = AndroidAutoManager.mAndroidAutoAppManager.getMusicStatus();
                    if (AndroidAutoManager.AndroidAutoAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage) && AndroidAutoManager.playState == 1) {
                        OnLineMusic.setMusicEngine(101);
                    }
                    if (OnLineMusic.musicEngine == 101) {
                        CurrentMediaInfoUtils currentMediaInfoUtils = CurrentMediaInfoUtils.getInstance();
                        if (AndroidAutoManager.playState != 1) {
                            z = false;
                        }
                        currentMediaInfoUtils.setPlayingState(z);
                    }
                    LogUtil.d(AndroidAutoManager.TAG, "ThirdManager.isAndroidAutoConnected getSong = " + StringSubUtil.subString(AndroidAutoManager.mAndroidAutoAppManager.getMediaMetadata().getSong()));
                    ThirdManager.getInstance().setAaMusic(AndroidAutoManager.mAndroidAutoAppManager.getMediaMetadata());
                }
            } catch (RemoteException e) {
                LogUtil.e(AndroidAutoManager.TAG, "mAndroidAutoAppManager.register Error!");
                e.printStackTrace();
            }
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService iPlatformService) {
            LogUtil.d(AndroidAutoManager.TAG, "registerConnectionListener onServiceDisconnected");
            boolean unused = AndroidAutoManager.isInit = false;
            int unused2 = AndroidAutoManager.mConnectionStatus = 2;
            AndroidAutoManager.updateConnectState();
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.byd.music.ThirdSDK.AndroidAuto.-$$Lambda$AndroidAutoManager$1$kuAJjfFP5gtTs4nO0mKQ5hurFW0
                @Override // java.lang.Runnable
                public final void run() {
                    AndroidAutoManager.AnonymousClass1.lambda$onServiceDisconnected$0();
                }
            }, 2000L);
        }

        static /* synthetic */ void lambda$onServiceDisconnected$0() {
            if (!AndroidAutoManager.isInit) {
                LogUtil.e(AndroidAutoManager.TAG, "init reconnect runnable running...");
                AndroidAutoManager.init();
            } else {
                LogUtil.e(AndroidAutoManager.TAG, "init isInit = true, reconnect runnable removed");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void initConnectState() throws RemoteException {
        IAndroidAutoAppManager iAndroidAutoAppManager = mAndroidAutoAppManager;
        if (iAndroidAutoAppManager == null) {
            LogUtil.e(TAG, "initConnectState with mAndroidAutoAppManager = null, just return.");
            return;
        }
        mConnectionStatus = iAndroidAutoAppManager.getAndroidAutoSessionStatus();
        LogUtil.e(TAG, "initConnectState mConnectionStatus = " + mConnectionStatus);
        updateConnectState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateConnectState() {
        if (mConnectionStatus == 4) {
            ThirdManager.setAndroidAutoConnectState(true);
        } else {
            ThirdManager.setAndroidAutoConnectState(false);
        }
    }

    public static void playNext() {
        if (!isInit) {
            init();
            return;
        }
        if (ThirdManager.isAndroidAutoConnected()) {
            try {
                LogUtil.d(TAG, "go to next");
                mAndroidAutoAppManager.next();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public static void playPre() {
        if (!isInit) {
            init();
            return;
        }
        if (ThirdManager.isAndroidAutoConnected()) {
            try {
                LogUtil.d(TAG, "go to previous");
                mAndroidAutoAppManager.previous();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public static void playOrStop(boolean z) {
        if (!isInit) {
            init();
            return;
        }
        if (ThirdManager.isAndroidAutoConnected()) {
            if (z) {
                try {
                    LogUtil.d(TAG, "go to play");
                    mAndroidAutoAppManager.play();
                    return;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return;
                }
            }
            try {
                LogUtil.d(TAG, "go to pause");
                mAndroidAutoAppManager.pause();
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void launchNowPlaying() {
        if (!isInit) {
            init();
            return;
        }
        if (ThirdManager.isAndroidAutoConnected()) {
            try {
                LogUtil.d(TAG, "go to launch android auto playing view");
                mAndroidAutoAppManager.sendKeyEvent(VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA, 0);
                mAndroidAutoAppManager.sendKeyEvent(VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA, 1);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public static void playWithHardKey() {
        if (!isInit) {
            init();
            return;
        }
        if (ThirdManager.isAndroidAutoConnected()) {
            try {
                LogUtil.d(TAG, "go to playWithHardKey");
                mAndroidAutoAppManager.sendKeyEvent(VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_PLAY, 0);
                mAndroidAutoAppManager.sendKeyEvent(VehicleConst.AapHardkeyEvent.AAP_KEYCODE_MEDIA_PLAY, 1);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
