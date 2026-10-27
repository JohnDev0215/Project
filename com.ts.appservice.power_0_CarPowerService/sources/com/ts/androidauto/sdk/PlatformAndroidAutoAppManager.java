package com.ts.androidauto.sdk;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.ts.androidauto.sdk.aidl.IAndroidAutoAppCallback;
import com.ts.androidauto.sdk.aidl.IAndroidAutoService;
import com.ts.androidauto.sdk.aidl.IAndroidAutoSessionStatusCallback;
import com.ts.androidauto.sdk.aidl.IBatteryStatusCallback;
import com.ts.androidauto.sdk.aidl.IMediaDataCallback;
import com.ts.androidauto.sdk.aidl.INavigationDataCallback;
import com.ts.androidauto.sdk.aidl.data.IfNavigationPositionData;
import com.ts.androidauto.sdk.aidl.data.IfNavigationStateData;
import com.ts.androidauto.sdk.aidl.data.MediaMetadata;
import com.ts.androidauto.sdk.common.LogEncrypt;
import com.ts.androidauto.sdk.common.LogUtil;
import com.ts.car.common.service.SimpleServiceBase;
import com.ts.car.core.annotation.PlatformConstruction;
import com.ts.car.core.annotation.PlatformService;
import java.util.ArrayList;
import java.util.List;
import ts.car.service.androidauto.IAndroidAutoAppListener;
import ts.car.service.androidauto.IAndroidAutoAppManager;
import ts.car.service.androidauto.IAndroidAutoSessionStatusListener;
import ts.car.service.androidauto.IBatteryStatusListener;
import ts.car.service.androidauto.IMediaDataListener;
import ts.car.service.androidauto.INavigationDataListener;
import ts.car.service.androidauto.common.VehicleConst;

/* JADX INFO: loaded from: classes.dex */
@PlatformService
public class PlatformAndroidAutoAppManager extends SimpleServiceBase implements IAndroidAutoAppManager {
    private static final int KEY_ACTION_NATIVE_UI = -1;
    private static final String PERMISSION_ANDROIDAUTO_APP = "car.permission.ANDROIDAUTO_APP";
    private static final String REMOTE_SERVICE_NAME = "SmartProjectionService";
    private static final String SERVICE_COMPONENT = "com.ts.androidauto.projectionservice/.AndroidAutoService";
    private static final String TAG = PlatformAndroidAutoAppManager.class.getSimpleName();
    private List<AndroidAutoAppCallback> mAndroidAutoAppCallbackList;
    private List<AndroidAutoSessionStatusCallback> mAndroidAutoSessionStatusCallbackList;
    private List<BatteryStatusCallback> mBatteryStatusCallbackList;
    private List<MediaDataCallback> mMediaDataCallbackList;
    private List<NavigationDataCallback> mNavigationDataCallbackList;
    private IAndroidAutoService mService;

    @PlatformConstruction
    public PlatformAndroidAutoAppManager(Context context, IBinder binder) {
        super(context, SERVICE_COMPONENT, REMOTE_SERVICE_NAME, binder);
        this.mAndroidAutoSessionStatusCallbackList = new ArrayList();
        this.mAndroidAutoAppCallbackList = new ArrayList();
        this.mBatteryStatusCallbackList = new ArrayList();
        this.mNavigationDataCallbackList = new ArrayList();
        this.mMediaDataCallbackList = new ArrayList();
    }

    @Override // ts.car.service.IPlatformService
    public String serviceName() {
        return "TS-AndroidAutoService_4.5.0.3";
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceConnected(IBinder iBinder) {
        LogUtil.info(TAG, "onServiceConnected.");
        this.mService = IAndroidAutoService.Stub.asInterface(iBinder);
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceDied() {
        LogUtil.info(TAG, "onServiceDied.");
        this.mService = null;
    }

    static class AndroidAutoSessionStatusCallback extends IAndroidAutoSessionStatusCallback.Stub {
        IAndroidAutoSessionStatusListener mListener;

        public AndroidAutoSessionStatusCallback(IAndroidAutoSessionStatusListener listener) {
            this.mListener = listener;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoSessionStatusCallback
        public void onSessionStatusChanged(int linkStatus, int errorCode, String message) throws RemoteException {
            LogUtil.info(PlatformAndroidAutoAppManager.TAG, "LinkCallback --- onSessionStatusChanged --- linkStatus " + linkStatus);
            this.mListener.onSessionStatusChanged(linkStatus, errorCode, message);
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoSessionStatusCallback
        public void onProjectionAppStatesChanged(int appType, boolean enabled) throws RemoteException {
            LogUtil.info(PlatformAndroidAutoAppManager.TAG, "LinkCallback --- onProjectionAppStatesChanged --- appType " + appType + ",enabled " + enabled);
            this.mListener.onProjectionAppStatesChanged(appType, enabled);
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoSessionStatusCallback
        public void onSessionConnectStatusChanged(int linkStatus, int errorCode, String message, int projectionConnectType) throws RemoteException {
            LogUtil.info(PlatformAndroidAutoAppManager.TAG, "LinkCallback --- onSessionConnectStatusChanged --- linkStatus " + linkStatus + ",projectionConnectType " + projectionConnectType);
            this.mListener.onSessionConnectStatusChanged(linkStatus, errorCode, message, projectionConnectType);
        }
    }

    static class AndroidAutoAppCallback extends IAndroidAutoAppCallback.Stub {
        IAndroidAutoAppListener mListener;

        public AndroidAutoAppCallback(IAndroidAutoAppListener listener) {
            this.mListener = listener;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoAppCallback
        public void onLastAvailableDeviceChanged(String deviceId, int capability) throws RemoteException {
            LogUtil.debug(PlatformAndroidAutoAppManager.TAG, "LinkCallback --- onLastAvailableDeviceChanged --- deviceId " + LogEncrypt.MacAddressEncrypt(deviceId) + ",capability " + capability);
            this.mListener.onLastAvailableDeviceChanged(deviceId, capability);
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoAppCallback
        public void onVideoFocusStatusChanged(int videoFocusStatus) throws RemoteException {
            LogUtil.info(PlatformAndroidAutoAppManager.TAG, "LinkCallback --- onVideoFocusStatusChanged --- videoFocusStatus " + videoFocusStatus);
            this.mListener.onVideoFocusStatusChanged(videoFocusStatus);
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoAppCallback
        public void onClusterVideoFocusStatusChanged(int videoFocusStatus) throws RemoteException {
            LogUtil.info(PlatformAndroidAutoAppManager.TAG, "LinkCallback --- onClusterFocusStatusChanged --- videoFocusStatus " + videoFocusStatus);
            this.mListener.onClusterVideoFocusStatusChanged(videoFocusStatus);
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoAppCallback
        public void onProjectionBluetoothAddressChanged(String address) throws RemoteException {
            LogUtil.debug(PlatformAndroidAutoAppManager.TAG, "LinkCallback --- onProjectionBluetoothAddressChanged --- address " + LogEncrypt.MacAddressEncrypt(address));
            this.mListener.onProjectionBluetoothAddressChanged(address);
        }
    }

    static class BatteryStatusCallback extends IBatteryStatusCallback.Stub {
        IBatteryStatusListener mListener;

        public BatteryStatusCallback(IBatteryStatusListener listener) {
            this.mListener = listener;
        }

        @Override // com.ts.androidauto.sdk.aidl.IBatteryStatusCallback
        public void onBatteryStatusNotification(int batteryLevel, int timeRemainingS, boolean batteryCritical) {
            LogUtil.info(PlatformAndroidAutoAppManager.TAG, "LinkCallback --- onBatteryStatusNotification batteryLevel: " + batteryLevel + " timeRemainingS: " + timeRemainingS + " batteryCritical: " + batteryCritical);
            this.mListener.onBatteryStatusChanged(batteryLevel, timeRemainingS, batteryCritical);
        }
    }

    static class MediaDataCallback extends IMediaDataCallback.Stub {
        IMediaDataListener mListener;

        public MediaDataCallback(IMediaDataListener listener) {
            this.mListener = listener;
        }

        @Override // com.ts.androidauto.sdk.aidl.IMediaDataCallback
        public void onMusicStatusChanged(int status) throws RemoteException {
            LogUtil.info(PlatformAndroidAutoAppManager.TAG, "LinkCallback --- ontMusicStatusChanged status: " + status);
            this.mListener.onMusicStatusChanged(status);
        }

        @Override // com.ts.androidauto.sdk.aidl.IMediaDataCallback
        public void onMediaMetadataChanged(MediaMetadata metadata) throws RemoteException {
            LogUtil.info(PlatformAndroidAutoAppManager.TAG, "LinkCallback --- onMediaMetadataChanged metadata : " + metadata.toString());
            this.mListener.onMediaMetadataChanged(Converter.dataToMediaMetadata(metadata));
        }

        @Override // com.ts.androidauto.sdk.aidl.IMediaDataCallback
        public void onMediaProgressChanged(int progress) throws RemoteException {
            LogUtil.verbose(PlatformAndroidAutoAppManager.TAG, "LinkCallback --- onMediaProgressChanged progress : " + progress);
            this.mListener.onMediaProgressChanged(progress);
        }
    }

    static class NavigationDataCallback extends INavigationDataCallback.Stub {
        INavigationDataListener mListener;

        public NavigationDataCallback(INavigationDataListener listener) {
            this.mListener = listener;
        }

        @Override // com.ts.androidauto.sdk.aidl.INavigationDataCallback
        public void onNavigationStatusChanged(int status) {
            LogUtil.info(PlatformAndroidAutoAppManager.TAG, "LinkCallback --- onNavigationStateChanged status: " + status);
            this.mListener.onNavigationStatusChanged(status);
        }

        @Override // com.ts.androidauto.sdk.aidl.INavigationDataCallback
        public void onNavigationState(IfNavigationStateData ifNavigationStateData) {
            LogUtil.verbose(PlatformAndroidAutoAppManager.TAG, "LinkCallback --- onNavigationState IfNavigationStateData: " + ifNavigationStateData.toString());
            this.mListener.onNavigationStateChanged(Converter.dataToNavigationStateData(ifNavigationStateData));
        }

        @Override // com.ts.androidauto.sdk.aidl.INavigationDataCallback
        public void onNavigationCurrentPosition(IfNavigationPositionData ifNavigationPositionData) {
            LogUtil.verbose(PlatformAndroidAutoAppManager.TAG, "LinkCallback --- onNavigationCurrentPosition ifNavigationPositionData: " + ifNavigationPositionData.toString());
            this.mListener.onNavigationCurrentPositionChanged(Converter.dataToNavigationPositionData(ifNavigationPositionData));
        }
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public void registerAndroidAutoSessionStatusListener(IAndroidAutoSessionStatusListener listener) throws RemoteException {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("registerAndroidAutoSessionStatusListener listener: ");
        sb.append(listener == null);
        LogUtil.info(str, sb.toString());
        if (this.mService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        AndroidAutoSessionStatusCallback localListener = new AndroidAutoSessionStatusCallback(listener);
        this.mService.addSessionStatusCallback(localListener);
        synchronized (this) {
            this.mAndroidAutoSessionStatusCallbackList.add(localListener);
        }
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public void unregisterAndroidAutoSessionStatusListener(IAndroidAutoSessionStatusListener listener) throws RemoteException {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("unregisterAndroidAutoSessionStatusListener listener: ");
        sb.append(listener == null);
        LogUtil.info(str, sb.toString());
        if (this.mService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        List<AndroidAutoSessionStatusCallback> list = new ArrayList<>();
        synchronized (this) {
            int i = 0;
            while (i < this.mAndroidAutoSessionStatusCallbackList.size()) {
                AndroidAutoSessionStatusCallback lstn = this.mAndroidAutoSessionStatusCallbackList.get(i);
                if (lstn.mListener == listener) {
                    list.add(lstn);
                    lstn.mListener = null;
                    this.mAndroidAutoSessionStatusCallbackList.remove(i);
                    i--;
                }
                i++;
            }
        }
        for (AndroidAutoSessionStatusCallback l : list) {
            try {
                this.mService.removeSessionStatusCallback(l);
            } catch (RemoteException exp) {
                LogUtil.warning(TAG, exp);
            }
        }
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public void registerAndroidAutoAppListener(IAndroidAutoAppListener listener) throws RemoteException {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("registerAndroidAutoAppListener listener: ");
        sb.append(listener == null);
        LogUtil.info(str, sb.toString());
        if (this.mService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        AndroidAutoAppCallback localListener = new AndroidAutoAppCallback(listener);
        this.mService.addAppCallback(localListener);
        synchronized (this) {
            this.mAndroidAutoAppCallbackList.add(localListener);
        }
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public void unregisterAndroidAutoAppListener(IAndroidAutoAppListener listener) throws RemoteException {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("unregisterAndroidAutoAppListener listener: ");
        sb.append(listener == null);
        LogUtil.info(str, sb.toString());
        if (this.mService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        List<AndroidAutoAppCallback> list = new ArrayList<>();
        synchronized (this) {
            int i = 0;
            while (i < this.mAndroidAutoAppCallbackList.size()) {
                AndroidAutoAppCallback lstn = this.mAndroidAutoAppCallbackList.get(i);
                if (lstn.mListener == listener) {
                    list.add(lstn);
                    lstn.mListener = null;
                    this.mAndroidAutoAppCallbackList.remove(i);
                    i--;
                }
                i++;
            }
        }
        for (AndroidAutoAppCallback l : list) {
            try {
                this.mService.removeAppCallback(l);
            } catch (RemoteException exp) {
                LogUtil.warning(TAG, exp);
            }
        }
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public void registerBatteryStatusListener(IBatteryStatusListener listener) throws RemoteException {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("registerListener listener: ");
        sb.append(listener == null);
        LogUtil.info(str, sb.toString());
        if (this.mService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        BatteryStatusCallback localListener = new BatteryStatusCallback(listener);
        this.mService.addBatteryStatusCallback(localListener);
        synchronized (this) {
            this.mBatteryStatusCallbackList.add(localListener);
        }
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public void unregisterBatteryStatusListener(IBatteryStatusListener listener) throws RemoteException {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("unregisterListener listener: ");
        sb.append(listener == null);
        LogUtil.info(str, sb.toString());
        if (this.mService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        List<BatteryStatusCallback> list = new ArrayList<>();
        synchronized (this) {
            int i = 0;
            while (i < this.mBatteryStatusCallbackList.size()) {
                BatteryStatusCallback lstn = this.mBatteryStatusCallbackList.get(i);
                if (lstn.mListener == listener) {
                    list.add(lstn);
                    lstn.mListener = null;
                    this.mBatteryStatusCallbackList.remove(i);
                    i--;
                }
                i++;
            }
        }
        for (BatteryStatusCallback l : list) {
            try {
                this.mService.removeBatteryStatusCallback(l);
            } catch (RemoteException exp) {
                LogUtil.warning(TAG, exp);
            }
        }
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public void registerMediaDataListener(IMediaDataListener listener) throws RemoteException {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("registerMediaDataListener listener: ");
        sb.append(listener == null);
        LogUtil.info(str, sb.toString());
        if (this.mService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        MediaDataCallback localListener = new MediaDataCallback(listener);
        this.mService.addMediaDataCallback(localListener);
        synchronized (this) {
            this.mMediaDataCallbackList.add(localListener);
        }
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public void unregisterMediaDataListener(IMediaDataListener listener) throws RemoteException {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("unregisterMediaDataListener listener: ");
        sb.append(listener == null);
        LogUtil.info(str, sb.toString());
        if (this.mService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        List<MediaDataCallback> list = new ArrayList<>();
        synchronized (this) {
            int i = 0;
            while (i < this.mMediaDataCallbackList.size()) {
                MediaDataCallback lstn = this.mMediaDataCallbackList.get(i);
                if (lstn.mListener == listener) {
                    list.add(lstn);
                    lstn.mListener = null;
                    this.mMediaDataCallbackList.remove(i);
                    i--;
                }
                i++;
            }
        }
        for (MediaDataCallback l : list) {
            try {
                this.mService.removeMediaDataCallback(l);
            } catch (RemoteException exp) {
                LogUtil.warning(TAG, exp);
            }
        }
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public void registerNavigationDataListener(INavigationDataListener listener) throws RemoteException {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("registerNavigationListener listener: ");
        sb.append(listener == null);
        LogUtil.info(str, sb.toString());
        if (this.mService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        NavigationDataCallback localListener = new NavigationDataCallback(listener);
        this.mService.addNavigationDataCallback(localListener);
        synchronized (this) {
            this.mNavigationDataCallbackList.add(localListener);
        }
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public void unregisterNavigationDataListener(INavigationDataListener listener) throws RemoteException {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("unregisterNavigationListener listener: ");
        sb.append(listener == null);
        LogUtil.info(str, sb.toString());
        if (this.mService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        List<NavigationDataCallback> list = new ArrayList<>();
        synchronized (this) {
            int i = 0;
            while (i < this.mNavigationDataCallbackList.size()) {
                NavigationDataCallback lstn = this.mNavigationDataCallbackList.get(i);
                if (lstn.mListener == listener) {
                    list.add(lstn);
                    lstn.mListener = null;
                    this.mNavigationDataCallbackList.remove(i);
                    i--;
                }
                i++;
            }
        }
        for (NavigationDataCallback l : list) {
            try {
                this.mService.removeNavigationDataCallback(l);
            } catch (RemoteException exp) {
                LogUtil.warning(TAG, exp);
            }
        }
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public int getAndroidAutoSessionStatus() throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        return iAndroidAutoService.getAndroidAutoSessionStatus();
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public String getLastAvailableDevice() throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        return iAndroidAutoService.getLastAvailableDevice();
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public int getLastAvailableCapability(String deviceId) throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        return iAndroidAutoService.getLastAvailableCapability(deviceId);
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public List<String> getAvailableDevices() throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        return iAndroidAutoService.getAvailableDevices();
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public void requestUi(int launchMode) throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        iAndroidAutoService.requestUi(launchMode);
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public int getVideoFocusStatus() throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        return iAndroidAutoService.getVideoFocusStatus();
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public String getProjectionBluetoothAddress() throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        return iAndroidAutoService.getProjectionBluetoothAddress();
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public boolean getAndroidAutoAppStatus(int appType) throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        return iAndroidAutoService.getAndroidAutoAppStatus(appType);
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public void play() throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        iAndroidAutoService.play();
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public void pause() throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        iAndroidAutoService.pause();
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public void previous() throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        iAndroidAutoService.previous();
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public void next() throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        iAndroidAutoService.next();
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public int getMusicStatus() throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        return iAndroidAutoService.getMusicStatus();
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public ts.car.service.androidauto.data.MediaMetadata getMediaMetadata() throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        return Converter.dataToMediaMetadata(iAndroidAutoService.getMediaMetadata());
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public int getMediaProgress() throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        return iAndroidAutoService.getMediaProgress();
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public void sendKeyEvent(VehicleConst.AapHardkeyEvent keyCode, int keyAction) throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        iAndroidAutoService.sendKeyEvent(Converter.convertKeyCode(keyCode).ordinal(), keyAction);
    }

    @Override // ts.car.service.androidauto.IAndroidAutoAppManager
    public void sendKeyLongPressEvent(VehicleConst.AapHardkeyEvent keyCode) throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        iAndroidAutoService.sendKeyLongPressEvent(Converter.convertKeyCode(keyCode).ordinal());
    }
}
