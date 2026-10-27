package com.ts.androidauto.sdk;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.Surface;
import com.ts.androidauto.sdk.aidl.IAndroidAutoService;
import com.ts.androidauto.sdk.aidl.IAndroidAutoServiceCallback;
import com.ts.androidauto.sdk.aidl.IAndroidAutoSessionStatusCallback;
import com.ts.androidauto.sdk.common.LogUtil;
import com.ts.car.common.service.SimpleServiceBase;
import com.ts.car.core.annotation.PlatformConstruction;
import com.ts.car.core.annotation.PlatformService;
import java.util.ArrayList;
import java.util.List;
import ts.car.service.androidauto.IAndroidAutoServiceListener;
import ts.car.service.androidauto.IAndroidAutoServiceManager;
import ts.car.service.androidauto.IAndroidAutoSessionStatusListener;
import ts.car.service.androidauto.common.VehicleConst;
import ts.car.service.androidauto.data.UiConfigData;
import ts.car.service.androidauto.data.VehicleData;

/* JADX INFO: loaded from: classes2.dex */
@PlatformService
public class PlatformAndroidAutoServiceManager extends SimpleServiceBase implements IAndroidAutoServiceManager {
    private static final String PERMISSION_ANDROIDAUTO = "car.permission.ANDROIDAUTO";
    private static final String REMOTE_SERVICE_NAME = "SmartProjectionService";
    private static final String SERVICE_COMPONENT = "com.ts.androidauto.projectionservice/.AndroidAutoService";
    private static final String TAG = PlatformAndroidAutoServiceManager.class.getSimpleName();
    private List<AndroidAutoServiceCallback> mAndroidAutoServiceCallbackList;
    private List<AndroidAutoSessionStatusCallback> mAndroidAutoSessionStatusCallbackList;
    private Object mLock;
    private IAndroidAutoService mService;

    @PlatformConstruction
    public PlatformAndroidAutoServiceManager(Context context, IBinder binder) {
        super(context, SERVICE_COMPONENT, "SmartProjectionService", binder);
        this.mLock = new Object();
        this.mAndroidAutoSessionStatusCallbackList = new ArrayList();
        this.mAndroidAutoServiceCallbackList = new ArrayList();
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
            LogUtil.info(PlatformAndroidAutoServiceManager.TAG, "LinkCallback --- onSessionStatusChanged --- linkStatus " + linkStatus);
            this.mListener.onSessionStatusChanged(linkStatus, errorCode, message);
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoSessionStatusCallback
        public void onProjectionAppStatesChanged(int appType, boolean enabled) throws RemoteException {
            LogUtil.info(PlatformAndroidAutoServiceManager.TAG, "LinkCallback --- onProjectionAppStatesChanged --- appType " + appType + ",enabled " + enabled);
            this.mListener.onProjectionAppStatesChanged(appType, enabled);
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoSessionStatusCallback
        public void onSessionConnectStatusChanged(int linkStatus, int errorCode, String message, int projectionConnectType) throws RemoteException {
            LogUtil.info(PlatformAndroidAutoServiceManager.TAG, "LinkCallback --- onSessionConnectStatusChanged --- linkStatus " + linkStatus + ",projectionConnectType " + projectionConnectType);
            this.mListener.onSessionConnectStatusChanged(linkStatus, errorCode, message, projectionConnectType);
        }
    }

    static class AndroidAutoServiceCallback extends IAndroidAutoServiceCallback.Stub {
        IAndroidAutoServiceListener mListener;

        public AndroidAutoServiceCallback(IAndroidAutoServiceListener listener) {
            this.mListener = listener;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoServiceCallback
        public void onRequestVideoFocus(int requestType) throws RemoteException {
            LogUtil.info(PlatformAndroidAutoServiceManager.TAG, "LinkCallback --- onRequestVideoFocus " + requestType);
            this.mListener.onRequestVideoFocus(requestType);
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoServiceCallback
        public void onRequestClusterVideoFocus(int requestType) throws RemoteException {
            LogUtil.info(PlatformAndroidAutoServiceManager.TAG, "LinkCallback --- onRequestClusterVideoFocus " + requestType);
            this.mListener.onRequestClusterVideoFocus(requestType);
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoServiceCallback
        public void onClusterVideoUpdateUiConfigReply(boolean replyOk) throws RemoteException {
            LogUtil.info(PlatformAndroidAutoServiceManager.TAG, "LinkCallback --- onClusterVideoUpdateUiConfigReply " + replyOk);
            this.mListener.onClusterVideoUpdateUiConfigReply(replyOk);
        }
    }

    @Override // ts.car.service.androidauto.IAndroidAutoServiceManager
    public void registerAndroidAutoServiceListener(IAndroidAutoServiceListener listener) throws RemoteException {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("registerAndroidAutoServiceListener listener: ");
        sb.append(listener == null);
        LogUtil.info(str, sb.toString());
        if (this.mService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        AndroidAutoServiceCallback localListener = new AndroidAutoServiceCallback(listener);
        this.mService.addServiceCallback(localListener);
        synchronized (this) {
            this.mAndroidAutoServiceCallbackList.add(localListener);
        }
    }

    @Override // ts.car.service.androidauto.IAndroidAutoServiceManager
    public void unregisterAndroidAutoServiceListener(IAndroidAutoServiceListener listener) throws RemoteException {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("unregisterListener listener: ");
        sb.append(listener == null);
        LogUtil.info(str, sb.toString());
        if (this.mService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        List<AndroidAutoServiceCallback> list = new ArrayList<>();
        synchronized (this) {
            int i = 0;
            while (i < this.mAndroidAutoServiceCallbackList.size()) {
                AndroidAutoServiceCallback lstn = this.mAndroidAutoServiceCallbackList.get(i);
                if (lstn.mListener == listener) {
                    list.add(lstn);
                    lstn.mListener = null;
                    this.mAndroidAutoServiceCallbackList.remove(i);
                    i--;
                }
                i++;
            }
        }
        for (AndroidAutoServiceCallback l : list) {
            try {
                this.mService.removeServiceCallback(l);
            } catch (RemoteException exp) {
                LogUtil.warning(TAG, exp);
            }
        }
    }

    @Override // ts.car.service.androidauto.IAndroidAutoServiceManager
    public void registerAndroidAutoSessionStatusListener(IAndroidAutoSessionStatusListener listener) throws RemoteException {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("registerListener listener: ");
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

    @Override // ts.car.service.androidauto.IAndroidAutoServiceManager
    public void unregisterAndroidAutoSessionStatusListener(IAndroidAutoSessionStatusListener listener) throws RemoteException {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("unregisterListener listener: ");
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

    @Override // ts.car.service.androidauto.IAndroidAutoServiceManager
    public void setSurface(Surface surface, int width, int height) throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        iAndroidAutoService.setSurface(surface, width, height);
    }

    @Override // ts.car.service.androidauto.IAndroidAutoServiceManager
    public void setClusterSurface(Surface surface, int width, int height) throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        iAndroidAutoService.setClusterSurface(surface, width, height);
    }

    @Override // ts.car.service.androidauto.IAndroidAutoServiceManager
    public void screenTouchEvent(MotionEvent event) throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        iAndroidAutoService.screenTouchEvent(event);
    }

    @Override // ts.car.service.androidauto.IAndroidAutoServiceManager
    @Deprecated
    public void sendKeyEvent(VehicleConst.AapHardkeyEvent keyCode, int keyAction) throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        iAndroidAutoService.sendKeyEvent(Converter.convertKeyCode(keyCode).ordinal(), keyAction);
    }

    @Override // ts.car.service.androidauto.IAndroidAutoServiceManager
    @Deprecated
    public void sendKeyEvent(int keyCode, int keyAction) throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        iAndroidAutoService.sendKeyEvent(keyCode, keyAction);
    }

    @Override // ts.car.service.androidauto.IAndroidAutoServiceManager
    @Deprecated
    public void sendKeyLongPressEvent(VehicleConst.AapHardkeyEvent keyCode) throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        iAndroidAutoService.sendKeyLongPressEvent(Converter.convertKeyCode(keyCode).ordinal());
    }

    @Override // ts.car.service.androidauto.IAndroidAutoServiceManager
    @Deprecated
    public void sendKeyLongPressEvent(int keyCode) throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        iAndroidAutoService.sendKeyLongPressEvent(keyCode);
    }

    @Override // ts.car.service.androidauto.IAndroidAutoServiceManager
    public void setVehicleInfo(VehicleData vehicleInfo) throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        iAndroidAutoService.setVehicleInfo(Converter.dataToVehicleInfo(vehicleInfo));
    }

    @Override // ts.car.service.androidauto.IAndroidAutoServiceManager
    public int getAndroidAutoSessionStatus() throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        return iAndroidAutoService.getAndroidAutoSessionStatus();
    }

    @Override // ts.car.service.androidauto.IAndroidAutoServiceManager
    public void setNativeAppStates(int appState, boolean enabled) throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        iAndroidAutoService.setNativeAppStates(appState, enabled);
    }

    @Override // ts.car.service.androidauto.IAndroidAutoServiceManager
    @Deprecated
    public void requestUi(int launchMode) throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        iAndroidAutoService.requestUi(launchMode);
    }

    @Override // ts.car.service.androidauto.IAndroidAutoServiceManager
    public void setNativePhoneCallStatus(int status) throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        iAndroidAutoService.setNativePhoneCallStatus(status);
    }

    @Override // ts.car.service.androidauto.IAndroidAutoServiceManager
    public void setClusterUiConfig(UiConfigData uiConfig) throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mService;
        if (iAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        iAndroidAutoService.setClusterUiConfig(Converter.dataToUiConfig(uiConfig));
    }
}
