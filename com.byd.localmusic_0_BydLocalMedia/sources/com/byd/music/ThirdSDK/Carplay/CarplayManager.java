package com.byd.music.ThirdSDK.Carplay;

import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.blankj.utilcode.util.Utils;
import com.byd.music.OnLineMusic;
import com.byd.music.ThirdSDK.Carplay.CarplayManager;
import com.byd.music.ThirdSDK.ThirdManager;
import com.byd.music.utils.CurrentMediaInfoUtils;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.StringSubUtil;
import com.ts.car.core.ServiceProvider;
import java.util.List;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.carplay.ICallStateListener;
import ts.car.service.carplay.ICarPlayAppListener;
import ts.car.service.carplay.ICarPlayAppManager;
import ts.car.service.carplay.ICarPlayDeviceListManager;
import ts.car.service.carplay.ICarPlayStateListener;
import ts.car.service.carplay.INowPlayingListener;
import ts.car.service.carplay.data.CallStateInfo;
import ts.car.service.carplay.data.NowPlayingInfo;
import ts.car.service.carplay.data.PlaybackMediaItemInfo;

/* JADX INFO: loaded from: classes5.dex */
public class CarplayManager {
    public static final String CarplayAudioFocusPKG = "com.ts.carplay";
    public static final String CarplayPKG = "com.ts.carplay.app";
    private static final String TAG = "CarplayManager";
    private static boolean callStateRegister;
    private static boolean calling;
    private static boolean isInit;
    private static ICarPlayAppManager mCarPlayAppManager;
    private static int mConnectionStatus;
    private static int playState;
    private static long playedTime;
    static INowPlayingListener nowPlayingListener = new INowPlayingListener() { // from class: com.byd.music.ThirdSDK.Carplay.CarplayManager.2
        @Override // ts.car.service.carplay.INowPlayingListener
        public void onPlaybackListUpdated(int i, int i2, List<PlaybackMediaItemInfo> list) {
        }

        @Override // ts.car.service.carplay.INowPlayingListener
        public void onNowPlayingInfoChanged(NowPlayingInfo nowPlayingInfo) {
            LogUtil.d(CarplayManager.TAG, "onNowPlayingInfoChanged getMediaItemTitle = " + StringSubUtil.subString(nowPlayingInfo.getMediaItemTitle()));
            ThirdManager.getInstance().setCpMusic(nowPlayingInfo);
            if (CarplayManager.playState != nowPlayingInfo.getPlaybackStatus() && ThirdManager.isCarplayConnected()) {
                int unused = CarplayManager.playState = nowPlayingInfo.getPlaybackStatus();
                if (CarplayManager.CarplayAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage) && CarplayManager.playState == 1) {
                    OnLineMusic.setMusicEngine(102);
                }
                if (OnLineMusic.musicEngine == 102) {
                    CurrentMediaInfoUtils.getInstance().setPlayingState(CarplayManager.playState == 1);
                }
            }
            if (CarplayManager.playedTime != nowPlayingInfo.getPlaybackElapsedTimeMs()) {
                LogUtil.d(CarplayManager.TAG, "getPlaybackElapsedTimeMs position = " + CarplayManager.playedTime + ", playState = " + CarplayManager.playState);
                long unused2 = CarplayManager.playedTime = nowPlayingInfo.getPlaybackElapsedTimeMs();
                if (!CurrentMediaInfoUtils.getInstance().getPlayingState() && OnLineMusic.musicEngine == 102 && CarplayManager.playState == 1) {
                    CurrentMediaInfoUtils.getInstance().setPlayingState(true);
                }
                if (ThirdManager.isCarplayConnected() && OnLineMusic.musicEngine == 102) {
                    CurrentMediaInfoUtils.getInstance().updateProgressAndNotify(CarplayManager.playedTime, nowPlayingInfo.getMediaItemPlaybackDurationMs(), nowPlayingInfo.getMediaItemPlaybackDurationMs());
                }
            }
        }

        @Override // ts.car.service.carplay.INowPlayingListener
        public void onArtworkUpdate(NowPlayingInfo nowPlayingInfo) {
            LogUtil.d(CarplayManager.TAG, "onArtworkUpdate getMediaItemTitle = " + nowPlayingInfo.getMediaItemTitle());
            if (nowPlayingInfo.getArtworkData() == null) {
                nowPlayingInfo.setArtworkSize(3);
                nowPlayingInfo.setArtworkData(new byte[3]);
            } else {
                LogUtil.d(CarplayManager.TAG, "onArtworkUpdate getArtworkData length = " + nowPlayingInfo.getArtworkData().length);
            }
            ThirdManager.getInstance().setCpMusic(nowPlayingInfo);
        }
    };
    static ICarPlayAppListener carPlayAppListener = new ICarPlayAppListener() { // from class: com.byd.music.ThirdSDK.Carplay.CarplayManager.3
        @Override // ts.car.service.carplay.ICarPlayAppListener
        public void onCarPlayDisplayStatusChanged(boolean z) {
            LogUtil.d(CarplayManager.TAG, "onCarPlayDisplayStatusChanged isShow = " + z);
        }
    };
    static ICarPlayStateListener carPlayStateListener = new ICarPlayStateListener() { // from class: com.byd.music.ThirdSDK.Carplay.CarplayManager.4
        @Override // ts.car.service.carplay.ICarPlayStateListener
        public void onSessionStateChanged(int i) {
            int unused = CarplayManager.mConnectionStatus = i;
            CarplayManager.updateConnectState();
        }
    };
    static ICallStateListener carplayCallStateListener = new ICallStateListener() { // from class: com.byd.music.ThirdSDK.Carplay.CarplayManager.5
        @Override // ts.car.service.carplay.ICallStateListener
        public void onCallStateInfoChanged(CallStateInfo callStateInfo) {
            int status = callStateInfo.getStatus();
            if (status == 1 || status == 2 || status == 3 || status == 4 || status == 5) {
                boolean unused = CarplayManager.calling = true;
            } else {
                boolean unused2 = CarplayManager.calling = false;
            }
        }
    };

    public static void init() {
        LogUtil.e(TAG, "init enter");
        if (!ThirdManager.isSupportType(102)) {
            LogUtil.e(TAG, "init with carplay disable");
            return;
        }
        ICarPlayAppManager iCarPlayAppManager = (ICarPlayAppManager) ServiceProvider.get(ICarPlayAppManager.class, Utils.getApp());
        mCarPlayAppManager = iCarPlayAppManager;
        if (iCarPlayAppManager == null) {
            LogUtil.e(TAG, "init with get = null, just return.");
        } else {
            iCarPlayAppManager.registerConnectionListener(new AnonymousClass1());
            LogUtil.e(TAG, "init finish.");
        }
    }

    /* JADX INFO: renamed from: com.byd.music.ThirdSDK.Carplay.CarplayManager$1, reason: invalid class name */
    class AnonymousClass1 implements IServiceConnectionListener {
        AnonymousClass1() {
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService iPlatformService) {
            LogUtil.e(CarplayManager.TAG, "registerConnectionListener onServiceConnected enter.");
            ICarPlayAppManager unused = CarplayManager.mCarPlayAppManager = (ICarPlayAppManager) iPlatformService;
            boolean unused2 = CarplayManager.isInit = true;
            new Handler(Looper.getMainLooper()).post(new RunnableC00311());
        }

        /* JADX INFO: renamed from: com.byd.music.ThirdSDK.Carplay.CarplayManager$1$1, reason: invalid class name and collision with other inner class name */
        class RunnableC00311 implements Runnable {
            RunnableC00311() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    LogUtil.e(CarplayManager.TAG, "initHandler registerMultiListeners stage 1.");
                    if (CarplayManager.mCarPlayAppManager.registerCarPlayStateListener(CarplayManager.carPlayStateListener)) {
                        LogUtil.e(CarplayManager.TAG, "initHandler registerMultiListeners stage 2.");
                        if (CarplayManager.mCarPlayAppManager.registerCarPlayAppListener(CarplayManager.carPlayAppListener)) {
                            LogUtil.e(CarplayManager.TAG, "initHandler registerMultiListeners stage 3.");
                            int unused = CarplayManager.mConnectionStatus = CarplayManager.mCarPlayAppManager.getCarPlaySessionStatus();
                            CarplayManager.updateConnectState();
                            LogUtil.e(CarplayManager.TAG, "initHandler registerMultiListeners finish.");
                            return;
                        }
                    }
                } catch (Throwable th) {
                    LogUtil.e(CarplayManager.TAG, "initHandler registerMultiListeners error! message = " + th.getMessage());
                    th.printStackTrace();
                }
                LogUtil.e(CarplayManager.TAG, "initHandler registerMultiListeners post again");
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.byd.music.ThirdSDK.Carplay.-$$Lambda$9LbnxeciayaG1MsiGHMTuwjeeis
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.run();
                    }
                }, 500L);
            }
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService iPlatformService) {
            LogUtil.e(CarplayManager.TAG, "registerConnectionListener onServiceDisconnected enter.");
            boolean unused = CarplayManager.isInit = false;
            int unused2 = CarplayManager.mConnectionStatus = 4;
            CarplayManager.updateConnectState();
            try {
                CarplayManager.mCarPlayAppManager.unregisterCallStateListener(CarplayManager.carplayCallStateListener);
                CarplayManager.mCarPlayAppManager.unregisterCarPlayAppListener(CarplayManager.carPlayAppListener);
                CarplayManager.mCarPlayAppManager.unregisterCarPlayStateListener(CarplayManager.carPlayStateListener);
            } catch (Throwable th) {
                LogUtil.e(CarplayManager.TAG, "onServiceDisconnected error! message = " + th.getMessage());
                th.printStackTrace();
            }
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.byd.music.ThirdSDK.Carplay.-$$Lambda$CarplayManager$1$QUselYvzVN16YK07nevzoHftwX0
                @Override // java.lang.Runnable
                public final void run() {
                    CarplayManager.AnonymousClass1.lambda$onServiceDisconnected$0();
                }
            }, 2000L);
        }

        static /* synthetic */ void lambda$onServiceDisconnected$0() {
            if (!CarplayManager.isInit) {
                LogUtil.e(CarplayManager.TAG, "init reconnect runnable running...");
                CarplayManager.init();
            } else {
                LogUtil.e(CarplayManager.TAG, "init isInit = true, reconnect runnable removed");
            }
        }
    }

    public static boolean getCarplayConnectState() {
        try {
            return mCarPlayAppManager.getCarPlaySessionStatus() == 2;
        } catch (Throwable th) {
            LogUtil.e(TAG, "getCarplayConnectState failed with error");
            th.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateConnectState() {
        try {
            LogUtil.e(TAG, "updateConnectState mConnectionStatus = " + mConnectionStatus);
            int i = mConnectionStatus;
            if (i != 1) {
                if (i == 2) {
                    ThirdManager.setCarplayConnectState(true);
                    LogUtil.e(TAG, "registerNowPlayingListener in updateConnectState finish with ret = " + mCarPlayAppManager.registerNowPlayingListener(nowPlayingListener));
                    callStateRegister = mCarPlayAppManager.registerCallStateListener(carplayCallStateListener);
                    LogUtil.e(TAG, "registerCallStateListener in updateConnectState finish with ret = " + callStateRegister);
                } else {
                    ThirdManager.setCarplayConnectState(false);
                    LogUtil.e(TAG, "unregisterNowPlayingListener in updateConnectState finish with ret = " + mCarPlayAppManager.unregisterNowPlayingListener(nowPlayingListener) + ", callStateRegister = " + callStateRegister);
                    if (callStateRegister) {
                        callStateRegister = mCarPlayAppManager.unregisterCallStateListener(carplayCallStateListener);
                        calling = false;
                        LogUtil.e(TAG, "unregisterNowPlayingListener in updateConnectState finish with ret = " + callStateRegister);
                    }
                }
            }
        } catch (RemoteException e) {
            LogUtil.e(TAG, "updateConnectState error = " + e.getMessage());
            callStateRegister = false;
            calling = false;
            e.printStackTrace();
        }
    }

    public static void playNext() {
        if (!isInit) {
            init();
            return;
        }
        if (ThirdManager.isCarplayConnected()) {
            try {
                LogUtil.d(TAG, "go to next");
                mCarPlayAppManager.sendRemoteMediaPlayback(4, 0);
                mCarPlayAppManager.sendRemoteMediaPlayback(4, 1);
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
        if (ThirdManager.isCarplayConnected()) {
            try {
                LogUtil.d(TAG, "go to previous");
                mCarPlayAppManager.sendRemoteMediaPlayback(8, 0);
                mCarPlayAppManager.sendRemoteMediaPlayback(8, 1);
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
        if (ThirdManager.isCarplayConnected()) {
            StringBuilder sb = new StringBuilder();
            sb.append("playOrStop enter, goto play = ");
            sb.append(z);
            sb.append(", playState = ");
            sb.append(playState == 1);
            LogUtil.e(TAG, sb.toString());
            try {
                if (playState == 1 && !z) {
                    LogUtil.d(TAG, "go to stop");
                    mCarPlayAppManager.sendRemoteMediaPlayback(64, 0);
                    mCarPlayAppManager.sendRemoteMediaPlayback(64, 1);
                } else if (playState != 1 && z) {
                    LogUtil.d(TAG, "go to play");
                    mCarPlayAppManager.sendRemoteMediaPlayback(64, 0);
                    mCarPlayAppManager.sendRemoteMediaPlayback(64, 1);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public static void launchNowPlaying() {
        if (mCarPlayAppManager != null) {
            LogUtil.d(TAG, "launchNowPlaying enter.");
            try {
                mCarPlayAppManager.requestUi(4);
                return;
            } catch (RemoteException e) {
                LogUtil.d(TAG, "launchNowPlaying RemoteException.");
                e.printStackTrace();
                return;
            }
        }
        LogUtil.d(TAG, "launchNowPlaying with mCarPlayAppManager = null, just return.");
    }

    public static String getCarplayMac() {
        try {
            return ((ICarPlayDeviceListManager) ServiceProvider.get(ICarPlayDeviceListManager.class, Utils.getApp())).getActivedWirelessDevice().getBtAddr();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static boolean isCarplayCalling() {
        return calling;
    }
}
