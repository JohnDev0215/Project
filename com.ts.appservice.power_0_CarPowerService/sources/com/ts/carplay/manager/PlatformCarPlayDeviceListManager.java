package com.ts.carplay.manager;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.ts.car.common.service.SimpleServiceBase;
import com.ts.car.core.annotation.PlatformConstruction;
import com.ts.car.core.annotation.PlatformService;
import com.ts.carplay.common.CarPlayConstants;
import com.ts.carplay.common.Converter;
import com.ts.carplay.common.aidl.ICarPlayDeviceListCallback;
import com.ts.carplay.common.aidl.ICarPlayService;
import com.ts.carplay.common.aidl.IWirelessCarPlayAvailabilityCallback;
import com.ts.carplay.common.data.CarPlayDevice;
import com.ts.carplay.common.data.ConnectRequest;
import com.ts.carplay.common.util.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import ts.car.service.carplay.ICarPlayDeviceListListener;
import ts.car.service.carplay.ICarPlayDeviceListManager;

/* JADX INFO: loaded from: classes.dex */
@PlatformService
public class PlatformCarPlayDeviceListManager extends SimpleServiceBase implements ICarPlayDeviceListManager {
    private static final String TAG = PlatformCarPlayDeviceListManager.class.getSimpleName();
    private Map<ICarPlayDeviceListListener, ICarPlayDeviceListCallbackImpl> mCarPlayDeviceListListeners;
    private ICarPlayService mCarPlayService;

    @PlatformConstruction
    public PlatformCarPlayDeviceListManager(Context context, IBinder binder) {
        super(context, CarPlayConstants.SERVICE_COMPONENT, CarPlayConstants.REMOTE_SERVICE_NAME, binder);
        this.mCarPlayDeviceListListeners = new ConcurrentHashMap();
    }

    @Override // ts.car.service.IPlatformService
    public String serviceName() {
        return "TS-CarPlayService_4.5.2.2";
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceConnected(IBinder iBinder) {
        LogUtil.debug(TAG, "onServiceConnected.");
        this.mCarPlayService = ICarPlayService.Stub.asInterface(iBinder);
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceDied() {
        LogUtil.debug(TAG, "onServiceDied.");
        this.mCarPlayService = null;
    }

    private class ICarPlayDeviceListCallbackImpl extends ICarPlayDeviceListCallback.Stub {
        ICarPlayDeviceListListener mListener;

        public ICarPlayDeviceListCallbackImpl(ICarPlayDeviceListListener listener) {
            this.mListener = listener;
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayDeviceListCallback
        public void onDeviceMerged(CarPlayDevice device) throws RemoteException {
            LogUtil.debug(PlatformCarPlayDeviceListManager.TAG, "ICarPlayDeviceListCallback --- onDeviceMerged:" + device);
            this.mListener.onDeviceMerged(Converter.aidlToCarPlayDevice(device));
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayDeviceListCallback
        public void onCarPlayDeviceStatusChanged(CarPlayDevice device) throws RemoteException {
            LogUtil.debug(PlatformCarPlayDeviceListManager.TAG, "ICarPlayDeviceListCallback --- onCarPlayDeviceStatusChanged :" + device);
            this.mListener.onCarPlayDeviceStatusChanged(Converter.aidlToCarPlayDevice(device));
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayDeviceListCallback
        public void onCarPlayOobStartEvent(byte[] macAddress) throws RemoteException {
            LogUtil.debug(PlatformCarPlayDeviceListManager.TAG, "ICarPlayDeviceListCallback --- onCarPlayOobStartEvent");
            this.mListener.onCarPlayOobStartEvent(macAddress);
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayDeviceListCallback
        public void onRequestProjection(CarPlayDevice device, int type) throws RemoteException {
            LogUtil.debug(PlatformCarPlayDeviceListManager.TAG, "ICarPlayDeviceListCallback --- onRequestProjection device:" + device + " type: " + type);
            this.mListener.onProjectionRequested(Converter.aidlToCarPlayDevice(device), type);
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayDeviceListCallback
        public void oniPhoneAttachedOverUsb(CarPlayDevice device, boolean supported) throws RemoteException {
            LogUtil.debug(PlatformCarPlayDeviceListManager.TAG, "ICarPlayDeviceListCallback --- oniPhoneAttachedOverUsb supported: " + supported + ", device: " + device);
            this.mListener.oniPhoneAttachedOverUsb(Converter.aidlToCarPlayDevice(device), supported);
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayDeviceListCallback
        public void oniPhoneDetachedOverUsb(CarPlayDevice device) throws RemoteException {
            LogUtil.debug(PlatformCarPlayDeviceListManager.TAG, "ICarPlayDeviceListCallback --- onDeviceDetachedOverUsb device:" + device);
            this.mListener.oniPhoneDetachedOverUsb(Converter.aidlToCarPlayDevice(device));
        }
    }

    private class IWirelessCarPlayAvailabilityCallbackImpl extends IWirelessCarPlayAvailabilityCallback.Stub {
        ts.car.service.carplay.IWirelessCarPlayAvailabilityCallback callback;

        public IWirelessCarPlayAvailabilityCallbackImpl(ts.car.service.carplay.IWirelessCarPlayAvailabilityCallback callback) {
            this.callback = callback;
        }

        @Override // com.ts.carplay.common.aidl.IWirelessCarPlayAvailabilityCallback
        public void onWirelessCarPlayAvailabilityChanged(String btAddr, boolean enabled) throws RemoteException {
            ts.car.service.carplay.IWirelessCarPlayAvailabilityCallback iWirelessCarPlayAvailabilityCallback = this.callback;
            if (iWirelessCarPlayAvailabilityCallback != null) {
                iWirelessCarPlayAvailabilityCallback.onWirelessCarPlayAvailabilityChanged(btAddr, enabled);
            }
        }
    }

    @Override // ts.car.service.carplay.ICarPlayDeviceListManager
    public boolean registerCarPlayDeviceListListener(ICarPlayDeviceListListener listener) throws RemoteException {
        if (this.mCarPlayDeviceListListeners.containsKey(listener)) {
            return false;
        }
        ICarPlayDeviceListCallbackImpl carPlayDeviceListCallback = new ICarPlayDeviceListCallbackImpl(listener);
        this.mCarPlayDeviceListListeners.put(listener, carPlayDeviceListCallback);
        this.mCarPlayService.registerCarPlayDeviceListListener(carPlayDeviceListCallback);
        return true;
    }

    @Override // ts.car.service.carplay.ICarPlayDeviceListManager
    public boolean unregisterCarPlayDeviceListListener(ICarPlayDeviceListListener listener) throws RemoteException {
        ICarPlayDeviceListCallbackImpl callback = this.mCarPlayDeviceListListeners.remove(listener);
        if (callback != null) {
            this.mCarPlayService.unregisterCarPlayDeviceListListener(callback);
            return true;
        }
        return false;
    }

    @Override // ts.car.service.carplay.ICarPlayDeviceListManager
    public void checkWirelessCarPlayAvailability(String btAddr, ts.car.service.carplay.IWirelessCarPlayAvailabilityCallback callback) throws RemoteException {
        ICarPlayService iCarPlayService = this.mCarPlayService;
        if (iCarPlayService == null) {
            LogUtil.error(TAG, "checkWirelessCarPlayAvailability mCarPlayService is null.");
        } else {
            iCarPlayService.checkWirelessCarPlayAvailability(btAddr, new IWirelessCarPlayAvailabilityCallbackImpl(callback));
        }
    }

    @Override // ts.car.service.carplay.ICarPlayDeviceListManager
    public void connectCarPlay(String devId, boolean isUserRequest) throws RemoteException {
        if (this.mCarPlayService == null) {
            LogUtil.error(TAG, "connectCarPlay mCarPlayService is null.");
            return;
        }
        ConnectRequest param = new ConnectRequest();
        param.setDeviceId(devId);
        param.setUserRequest(isUserRequest);
        this.mCarPlayService.connectCarPlay(param);
    }

    @Override // ts.car.service.carplay.ICarPlayDeviceListManager
    public void connectCarPlay(ts.car.service.carplay.data.ConnectRequest request) throws RemoteException {
        if (this.mCarPlayService == null) {
            LogUtil.error(TAG, "connectCarPlay2 mCarPlayService is null.");
            return;
        }
        ConnectRequest param = new ConnectRequest();
        param.setDeviceId(request.getDeviceId());
        param.setUserRequest(request.isUserRequest());
        if (request.getConnectType() == 1) {
            param.setConnectType(1);
        } else if (request.getConnectType() == 2) {
            param.setConnectType(3);
        }
        this.mCarPlayService.connectCarPlay(param);
    }

    @Override // ts.car.service.carplay.ICarPlayDeviceListManager
    public void disconnectCarPlay(String devId) throws RemoteException {
        ICarPlayService iCarPlayService = this.mCarPlayService;
        if (iCarPlayService == null) {
            LogUtil.error(TAG, "disconnectCarPlay mCarPlayService is null.");
        } else {
            iCarPlayService.disconnectCarPlay(devId);
        }
    }

    @Override // ts.car.service.carplay.ICarPlayDeviceListManager
    public void deleteCarPlayDevice(String devId) throws RemoteException {
        ICarPlayService iCarPlayService = this.mCarPlayService;
        if (iCarPlayService == null) {
            LogUtil.error(TAG, "deleteCarPlayDevice mCarPlayService is null.");
        } else {
            iCarPlayService.deleteCarPlayDevice(devId);
        }
    }

    @Override // ts.car.service.carplay.ICarPlayDeviceListManager
    public ts.car.service.carplay.data.CarPlayDevice getCarPlayDevice(String devId) throws RemoteException {
        ICarPlayService iCarPlayService = this.mCarPlayService;
        if (iCarPlayService == null) {
            LogUtil.error(TAG, "getCarPlayDevice getCarPlayDevice is null.");
            return null;
        }
        return Converter.aidlToCarPlayDevice(iCarPlayService.getCarPlayDevice(devId));
    }

    @Override // ts.car.service.carplay.ICarPlayDeviceListManager
    public ts.car.service.carplay.data.CarPlayDevice getActivedWirelessDevice() throws RemoteException {
        CarPlayDevice device = null;
        ICarPlayService iCarPlayService = this.mCarPlayService;
        if (iCarPlayService != null) {
            device = iCarPlayService.getActivedWirelessDevice();
        } else {
            LogUtil.warning(TAG, "getActivedWirelessDevice mCarPlayService is null.");
        }
        LogUtil.debug(TAG, "getActivedWirelessDevice device: " + device);
        return Converter.aidlToCarPlayDevice(device);
    }

    @Override // ts.car.service.carplay.ICarPlayDeviceListManager
    public List<ts.car.service.carplay.data.CarPlayDevice> getUsbCarPlayDeviceList() throws RemoteException {
        ICarPlayService iCarPlayService = this.mCarPlayService;
        if (iCarPlayService == null) {
            LogUtil.info(TAG, "getUsbCarPlayDeviceList, mCarPlayService is null");
            return null;
        }
        List<CarPlayDevice> list = iCarPlayService.getUsbCarPlayDeviceList();
        ArrayList<ts.car.service.carplay.data.CarPlayDevice> result = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (CarPlayDevice device : list) {
                result.add(Converter.aidlToCarPlayDevice(device));
            }
        }
        LogUtil.debug(TAG, "getUsbCarPlayDeviceList, result: " + result);
        return result;
    }

    @Override // ts.car.service.carplay.ICarPlayDeviceListManager
    public boolean cancelWirelessCarPlay(String mac) throws RemoteException {
        LogUtil.debug(TAG, "cancelWirelessCarPlay");
        ICarPlayService iCarPlayService = this.mCarPlayService;
        if (iCarPlayService == null) {
            LogUtil.info(TAG, "cancelWirelessCarPlay, mCarPlayService is null");
            return false;
        }
        return iCarPlayService.cancelWirelessCarPlay(mac);
    }

    @Override // ts.car.service.carplay.ICarPlayDeviceListManager
    public List<ts.car.service.carplay.data.CarPlayDevice> getCarPlayDeviceList() throws RemoteException {
        ICarPlayService iCarPlayService = this.mCarPlayService;
        if (iCarPlayService == null) {
            LogUtil.info(TAG, "getCarPlayDeviceList, mCarPlayService is null");
            return null;
        }
        List<CarPlayDevice> list = iCarPlayService.getCarPlayDeviceList();
        ArrayList<ts.car.service.carplay.data.CarPlayDevice> result = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (CarPlayDevice device : list) {
                result.add(Converter.aidlToCarPlayDevice(device));
            }
        }
        LogUtil.debug(TAG, "getCarPlayDeviceList, result: " + result);
        return result;
    }
}
