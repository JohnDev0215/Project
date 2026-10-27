package com.ts.androidauto.sdk;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.ts.androidauto.sdk.aidl.IAndroidAutoDeviceListCallback;
import com.ts.androidauto.sdk.aidl.IAndroidAutoService;
import com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice;
import com.ts.androidauto.sdk.common.LogUtil;
import com.ts.car.common.service.SimpleServiceBase;
import com.ts.car.core.annotation.PlatformConstruction;
import com.ts.car.core.annotation.PlatformService;
import java.util.ArrayList;
import java.util.List;
import ts.car.service.androidauto.IAndroidAutoDeviceListListener;
import ts.car.service.androidauto.IAndroidAutoDeviceListManager;
import ts.car.service.androidauto.data.ConnectRequest;

/* JADX INFO: loaded from: classes.dex */
@PlatformService
public class PlatformAndroidAutoDeviceListManager extends SimpleServiceBase implements IAndroidAutoDeviceListManager {
    private static final String PERMISSION_ANDROIDAUTO_DEVICELIST = "car.permission.ANDROIDAUTO_DEVICE_LIST";
    private static final String REMOTE_SERVICE_NAME = "SmartProjectionService";
    private static final String SERVICE_COMPONENT = "com.ts.androidauto.projectionservice/.AndroidAutoService";
    private static final String TAG = PlatformAndroidAutoDeviceListManager.class.getSimpleName();
    private List<IAndroidAutoDeviceListCallbackImpl> mAndroidAutoDeviceListCallbackList;
    private IAndroidAutoService mAndroidAutoService;

    @PlatformConstruction
    public PlatformAndroidAutoDeviceListManager(Context context, IBinder binder) {
        super(context, SERVICE_COMPONENT, REMOTE_SERVICE_NAME, binder);
        this.mAndroidAutoDeviceListCallbackList = new ArrayList();
    }

    @Override // ts.car.service.IPlatformService
    public String serviceName() {
        return "TS-AndroidAutoService_4.5.0.3";
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceConnected(IBinder iBinder) {
        LogUtil.debug(TAG, "onServiceConnected.");
        this.mAndroidAutoService = IAndroidAutoService.Stub.asInterface(iBinder);
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceDied() {
        LogUtil.debug(TAG, "onServiceDied.");
        this.mAndroidAutoService = null;
    }

    private class IAndroidAutoDeviceListCallbackImpl extends IAndroidAutoDeviceListCallback.Stub {
        IAndroidAutoDeviceListListener mListener;

        public IAndroidAutoDeviceListCallbackImpl(IAndroidAutoDeviceListListener listener) {
            this.mListener = listener;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoDeviceListCallback
        public void onRequestProjection(AndroidAutoDevice device, int type) {
            LogUtil.debug(PlatformAndroidAutoDeviceListManager.TAG, "ICarPlayDeviceListCallback --- onRequestProjection device：" + device + " type:" + type);
            IAndroidAutoDeviceListListener iAndroidAutoDeviceListListener = this.mListener;
            if (iAndroidAutoDeviceListListener != null) {
                iAndroidAutoDeviceListListener.onProjectionRequested(Converter.dataToAndroidAutoDevice(device), type);
            }
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoDeviceListCallback
        public void onDevicesMerged(AndroidAutoDevice device) {
            LogUtil.debug(PlatformAndroidAutoDeviceListManager.TAG, "onDevicesMerged, device：" + device);
            IAndroidAutoDeviceListListener iAndroidAutoDeviceListListener = this.mListener;
            if (iAndroidAutoDeviceListListener != null) {
                iAndroidAutoDeviceListListener.onDeviceMerged(Converter.dataToAndroidAutoDevice(device));
            }
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoDeviceListCallback
        public void onDeviceStatusChanged(AndroidAutoDevice device) {
            LogUtil.debug(PlatformAndroidAutoDeviceListManager.TAG, "onDeviceStatusChanged, device：" + device);
            IAndroidAutoDeviceListListener iAndroidAutoDeviceListListener = this.mListener;
            if (iAndroidAutoDeviceListListener != null) {
                iAndroidAutoDeviceListListener.onAndroidAutoDeviceStatusChanged(Converter.dataToAndroidAutoDevice(device));
            }
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoDeviceListCallback
        public void onAndroidDetachedOverUsb(AndroidAutoDevice device) {
            LogUtil.debug(PlatformAndroidAutoDeviceListManager.TAG, "onAndroidDetachedOverUsb, device：" + device);
            IAndroidAutoDeviceListListener iAndroidAutoDeviceListListener = this.mListener;
            if (iAndroidAutoDeviceListListener != null) {
                iAndroidAutoDeviceListListener.onAndroidDetachedOverUsb(Converter.dataToAndroidAutoDevice(device));
            }
        }
    }

    @Override // ts.car.service.androidauto.IAndroidAutoDeviceListManager
    public void registerAndroidAutoDeviceListListener(IAndroidAutoDeviceListListener listener) throws RemoteException {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("registerAndroidAutoDeviceListListener listener: ");
        sb.append(listener == null);
        LogUtil.info(str, sb.toString());
        if (this.mAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        IAndroidAutoDeviceListCallbackImpl localListener = new IAndroidAutoDeviceListCallbackImpl(listener);
        this.mAndroidAutoService.addDeviceListCallback(localListener);
        synchronized (this) {
            this.mAndroidAutoDeviceListCallbackList.add(localListener);
        }
    }

    @Override // ts.car.service.androidauto.IAndroidAutoDeviceListManager
    public void unregisterAndroidAutoDeviceListListener(IAndroidAutoDeviceListListener listener) throws RemoteException {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("unregisterAndroidAutoSessionStatusListener listener: ");
        sb.append(listener == null);
        LogUtil.info(str, sb.toString());
        if (this.mAndroidAutoService == null) {
            throw new IllegalStateException("IAndroidAutoService is not Ready!");
        }
        List<IAndroidAutoDeviceListCallbackImpl> list = new ArrayList<>();
        synchronized (this) {
            int i = 0;
            while (i < this.mAndroidAutoDeviceListCallbackList.size()) {
                IAndroidAutoDeviceListCallbackImpl lstn = this.mAndroidAutoDeviceListCallbackList.get(i);
                if (lstn.mListener == listener) {
                    list.add(lstn);
                    lstn.mListener = null;
                    this.mAndroidAutoDeviceListCallbackList.remove(i);
                    i--;
                }
                i++;
            }
        }
        for (IAndroidAutoDeviceListCallbackImpl l : list) {
            try {
                this.mAndroidAutoService.removeDeviceListCallback(l);
            } catch (RemoteException exp) {
                LogUtil.warning(TAG, exp);
            }
        }
    }

    @Override // ts.car.service.androidauto.IAndroidAutoDeviceListManager
    public void connectAndroidAuto(String devId, boolean isUserRequest) throws RemoteException {
        this.mAndroidAutoService.connectAndroidAuto(devId, isUserRequest);
    }

    @Override // ts.car.service.androidauto.IAndroidAutoDeviceListManager
    public void connectAndroidAuto(ConnectRequest request) throws RemoteException {
        com.ts.androidauto.sdk.aidl.data.ConnectRequest param = new com.ts.androidauto.sdk.aidl.data.ConnectRequest();
        param.setDeviceId(request.getDeviceId());
        param.setUserRequest(request.isUserRequest());
        param.setConnectType(request.getConnectType());
        param.setForceProjection(request.isForceProjection());
        this.mAndroidAutoService.connectAndroidAutoByRequest(param);
    }

    @Override // ts.car.service.androidauto.IAndroidAutoDeviceListManager
    public void disconnectAndroidAuto(String devId) throws RemoteException {
        this.mAndroidAutoService.disconnectAndroidAuto(devId);
    }

    @Override // ts.car.service.androidauto.IAndroidAutoDeviceListManager
    public void deleteAndroidAutoDevice(String devId) throws RemoteException {
        this.mAndroidAutoService.deleteAndroidAutoDevice(devId);
    }

    @Override // ts.car.service.androidauto.IAndroidAutoDeviceListManager
    public ts.car.service.androidauto.data.AndroidAutoDevice getAndroidAutoDevice(String devId) throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mAndroidAutoService;
        if (iAndroidAutoService == null) {
            LogUtil.info(TAG, "getAndroidAutoDevice, mAndroidAutoService is null");
            return null;
        }
        return Converter.dataToAndroidAutoDevice(iAndroidAutoService.getAndroidAutoDevice(devId));
    }

    @Override // ts.car.service.androidauto.IAndroidAutoDeviceListManager
    public List<ts.car.service.androidauto.data.AndroidAutoDevice> getUsbAndroidAutoDeviceList() throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mAndroidAutoService;
        if (iAndroidAutoService == null) {
            LogUtil.info(TAG, "getUsbAndroidAutoDeviceList, mAndroidAutoService is null");
            return null;
        }
        List<AndroidAutoDevice> list = iAndroidAutoService.getUsbAndroidAutoDeviceList();
        ArrayList<ts.car.service.androidauto.data.AndroidAutoDevice> result = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (AndroidAutoDevice device : list) {
                result.add(Converter.dataToAndroidAutoDevice(device));
            }
        }
        return result;
    }

    @Override // ts.car.service.androidauto.IAndroidAutoDeviceListManager
    public List<ts.car.service.androidauto.data.AndroidAutoDevice> getAndroidAutoDeviceList() throws RemoteException {
        IAndroidAutoService iAndroidAutoService = this.mAndroidAutoService;
        if (iAndroidAutoService == null) {
            LogUtil.info(TAG, "getAndroidAutoDeviceList, mAndroidAutoService is null");
            return null;
        }
        List<AndroidAutoDevice> list = iAndroidAutoService.getAndroidAutoDeviceList();
        ArrayList<ts.car.service.androidauto.data.AndroidAutoDevice> result = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (AndroidAutoDevice device : list) {
                result.add(Converter.dataToAndroidAutoDevice(device));
            }
        }
        return result;
    }
}
