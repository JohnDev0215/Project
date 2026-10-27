package ts.car.dcm.common.aidl;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
import ts.car.dcm.common.aidl.IConnectionManagementServiceCallback;
import ts.car.dcm.common.aidl.IDmListener;
import ts.car.dcm.common.aidl.IPcmListener;
import ts.car.dcm.common.data.AbnormalDevice;
import ts.car.dcm.common.data.BtOobInfo;
import ts.car.dcm.common.data.CmDevice;
import ts.car.dcm.common.data.ConnDevice;
import ts.car.dcm.common.data.ProjectionLinkParameter;
import ts.car.dcm.common.data.WifiApConfiguration;

/* JADX INFO: loaded from: classes.dex */
public interface IConnectionManagementService extends IInterface {
    void addDmCallback(IDmListener iDmListener, int i) throws RemoteException;

    void addPcmCallback(IPcmListener iPcmListener, List<String> list) throws RemoteException;

    boolean cancelWirelessCarplay(String str) throws RemoteException;

    boolean checkDeviceCapability(String str, int i) throws RemoteException;

    boolean connectBtDevice(String str, String str2) throws RemoteException;

    boolean connectBtProfile(String str, String str2, int i) throws RemoteException;

    boolean connectDevice(String str, int i) throws RemoteException;

    int connectProjection(ProjectionLinkParameter projectionLinkParameter) throws RemoteException;

    boolean deleteAllDevices(IConnectionManagementServiceCallback iConnectionManagementServiceCallback) throws RemoteException;

    boolean deleteDevice(String str) throws RemoteException;

    boolean deleteProjectionData(String str, int i) throws RemoteException;

    boolean disconnectBtDevice(String str, String str2) throws RemoteException;

    boolean disconnectBtProfile(String str, String str2, int i) throws RemoteException;

    boolean disconnectDevice(String str, int i) throws RemoteException;

    boolean disconnectOtherBtDevices(String str, String str2) throws RemoteException;

    boolean disconnectProjection(String str, int i, int i2) throws RemoteException;

    boolean doUsbRoleSwitch(String str, int i, int i2) throws RemoteException;

    boolean enableBluetooth(boolean z) throws RemoteException;

    boolean enableProjection(int i, boolean z) throws RemoteException;

    boolean enableProjectionByDevType(int i, int i2, boolean z) throws RemoteException;

    boolean enableUsbAoa(String str, int i, boolean z) throws RemoteException;

    boolean enableUsbAoaForAndroidAuto(String str, String str2, int i, boolean z) throws RemoteException;

    void enableWifiAp(boolean z, WifiApConfiguration wifiApConfiguration) throws RemoteException;

    List<AbnormalDevice> getAbnormalDeviceList() throws RemoteException;

    List<ConnDevice> getActiveProjections() throws RemoteException;

    BtOobInfo getBtOobInfo() throws RemoteException;

    ConnDevice getDevice(String str) throws RemoteException;

    void getDevices(Bundle bundle) throws RemoteException;

    ConnDevice getFavoritePhone() throws RemoteException;

    ConnDevice getLastDevice() throws RemoteException;

    void getOnlineDevices(Bundle bundle) throws RemoteException;

    List<CmDevice> getProjectionDevices(int i) throws RemoteException;

    List<String> getProjectionPriorityList() throws RemoteException;

    int getSupportedWifiChannel() throws RemoteException;

    boolean isEnableProjection(int i) throws RemoteException;

    boolean isProjectionSupport(int i) throws RemoteException;

    boolean mergeDevices(String str, String str2, int i) throws RemoteException;

    boolean mergeDevicesByUsbAndBt(String str, String str2) throws RemoteException;

    boolean resetUsb(String str) throws RemoteException;

    void rmDmCallback(IDmListener iDmListener, int i) throws RemoteException;

    void rmPcmCallback(IPcmListener iPcmListener, List<String> list) throws RemoteException;

    boolean scanWirelessProjection(boolean z) throws RemoteException;

    boolean setAlwaysProjection(String str, int i, boolean z) throws RemoteException;

    void setAlwaysProjectionByDevType(int i, int i2, boolean z) throws RemoteException;

    void setBtOobInfo(String str, String str2, String str3, int i) throws RemoteException;

    boolean setDeviceConnectionDetection(boolean z, boolean z2) throws RemoteException;

    boolean setFavoritePhone(String str) throws RemoteException;

    boolean setLastDevice(String str) throws RemoteException;

    void setMobileDeviceId(String str, String str2) throws RemoteException;

    boolean setProjectionPriorityList(List<String> list) throws RemoteException;

    boolean updateDeviceFriendlyName(String str, String str2) throws RemoteException;

    void updateIapSessionState(String str, int i, int i2) throws RemoteException;

    void updateProjectionCapability(String str, int i, boolean z, int i2) throws RemoteException;

    void updateProjectionConnectionStatus(String str, int i, int i2, int i3) throws RemoteException;

    boolean updateUsbDeviceName(String str, String str2) throws RemoteException;

    public static class Default implements IConnectionManagementService {
        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void addDmCallback(IDmListener cb, int objectType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void addPcmCallback(IPcmListener cb, List<String> services) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void rmDmCallback(IDmListener cb, int objectType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void rmPcmCallback(IPcmListener cb, List<String> services) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean setLastDevice(String devId) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public ConnDevice getLastDevice() throws RemoteException {
            return null;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean setFavoritePhone(String devId) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public ConnDevice getFavoritePhone() throws RemoteException {
            return null;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean isProjectionSupport(int type) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean isEnableProjection(int type) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean enableProjection(int type, boolean flag) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean enableProjectionByDevType(int serviceType, int deviceType, boolean flag) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void getDevices(Bundle devices) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void getOnlineDevices(Bundle devices) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public List<CmDevice> getProjectionDevices(int service) throws RemoteException {
            return null;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean deleteDevice(String devId) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean deleteAllDevices(IConnectionManagementServiceCallback callback) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean deleteProjectionData(String devId, int service) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public List<String> getProjectionPriorityList() throws RemoteException {
            return null;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean setProjectionPriorityList(List<String> list) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean connectDevice(String device, int type) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean disconnectDevice(String devId, int type) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean updateDeviceFriendlyName(String devId, String name) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public int connectProjection(ProjectionLinkParameter plp) throws RemoteException {
            return 0;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean disconnectProjection(String devId, int capa, int reason) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void updateProjectionConnectionStatus(String devId, int capa, int state, int code) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean doUsbRoleSwitch(String devId, int mode, int purpose) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean resetUsb(String devId) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean enableUsbAoa(String devId, int mode, boolean flag) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean enableUsbAoaForAndroidAuto(String devId, String serialNumber, int mode, boolean flag) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean enableBluetooth(boolean flag) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean connectBtDevice(String devId, String mac) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean disconnectBtDevice(String devId, String mac) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean disconnectOtherBtDevices(String devId, String mac) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean connectBtProfile(String devId, String mac, int profile) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean disconnectBtProfile(String devId, String mac, int profile) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public BtOobInfo getBtOobInfo() throws RemoteException {
            return null;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void setBtOobInfo(String name, String linkKey, String mac, int linkKeyType) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean mergeDevices(String devId, String key, int keyType) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean mergeDevicesByUsbAndBt(String usb, String bt) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void enableWifiAp(boolean enable, WifiApConfiguration config) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean scanWirelessProjection(boolean enable) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void updateProjectionCapability(String id, int capability, boolean flag, int reason) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean setAlwaysProjection(String devId, int capa, boolean always) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void setAlwaysProjectionByDevType(int devType, int capa, boolean always) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public List<ConnDevice> getActiveProjections() throws RemoteException {
            return null;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean cancelWirelessCarplay(String devId) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public ConnDevice getDevice(String devId) throws RemoteException {
            return null;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean setDeviceConnectionDetection(boolean on, boolean persistent) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public List<AbnormalDevice> getAbnormalDeviceList() throws RemoteException {
            return null;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean updateUsbDeviceName(String devId, String name) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void updateIapSessionState(String id, int state, int reason) throws RemoteException {
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean checkDeviceCapability(String devId, int capability) throws RemoteException {
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public int getSupportedWifiChannel() throws RemoteException {
            return 0;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void setMobileDeviceId(String devId, String mobileDeviceId) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IConnectionManagementService {
        private static final String DESCRIPTOR = "ts.car.dcm.common.aidl.IConnectionManagementService";
        static final int TRANSACTION_addDmCallback = 1;
        static final int TRANSACTION_addPcmCallback = 2;
        static final int TRANSACTION_cancelWirelessCarplay = 47;
        static final int TRANSACTION_checkDeviceCapability = 53;
        static final int TRANSACTION_connectBtDevice = 32;
        static final int TRANSACTION_connectBtProfile = 35;
        static final int TRANSACTION_connectDevice = 21;
        static final int TRANSACTION_connectProjection = 24;
        static final int TRANSACTION_deleteAllDevices = 17;
        static final int TRANSACTION_deleteDevice = 16;
        static final int TRANSACTION_deleteProjectionData = 18;
        static final int TRANSACTION_disconnectBtDevice = 33;
        static final int TRANSACTION_disconnectBtProfile = 36;
        static final int TRANSACTION_disconnectDevice = 22;
        static final int TRANSACTION_disconnectOtherBtDevices = 34;
        static final int TRANSACTION_disconnectProjection = 25;
        static final int TRANSACTION_doUsbRoleSwitch = 27;
        static final int TRANSACTION_enableBluetooth = 31;
        static final int TRANSACTION_enableProjection = 11;
        static final int TRANSACTION_enableProjectionByDevType = 12;
        static final int TRANSACTION_enableUsbAoa = 29;
        static final int TRANSACTION_enableUsbAoaForAndroidAuto = 30;
        static final int TRANSACTION_enableWifiAp = 41;
        static final int TRANSACTION_getAbnormalDeviceList = 50;
        static final int TRANSACTION_getActiveProjections = 46;
        static final int TRANSACTION_getBtOobInfo = 37;
        static final int TRANSACTION_getDevice = 48;
        static final int TRANSACTION_getDevices = 13;
        static final int TRANSACTION_getFavoritePhone = 8;
        static final int TRANSACTION_getLastDevice = 6;
        static final int TRANSACTION_getOnlineDevices = 14;
        static final int TRANSACTION_getProjectionDevices = 15;
        static final int TRANSACTION_getProjectionPriorityList = 19;
        static final int TRANSACTION_getSupportedWifiChannel = 54;
        static final int TRANSACTION_isEnableProjection = 10;
        static final int TRANSACTION_isProjectionSupport = 9;
        static final int TRANSACTION_mergeDevices = 39;
        static final int TRANSACTION_mergeDevicesByUsbAndBt = 40;
        static final int TRANSACTION_resetUsb = 28;
        static final int TRANSACTION_rmDmCallback = 3;
        static final int TRANSACTION_rmPcmCallback = 4;
        static final int TRANSACTION_scanWirelessProjection = 42;
        static final int TRANSACTION_setAlwaysProjection = 44;
        static final int TRANSACTION_setAlwaysProjectionByDevType = 45;
        static final int TRANSACTION_setBtOobInfo = 38;
        static final int TRANSACTION_setDeviceConnectionDetection = 49;
        static final int TRANSACTION_setFavoritePhone = 7;
        static final int TRANSACTION_setLastDevice = 5;
        static final int TRANSACTION_setMobileDeviceId = 55;
        static final int TRANSACTION_setProjectionPriorityList = 20;
        static final int TRANSACTION_updateDeviceFriendlyName = 23;
        static final int TRANSACTION_updateIapSessionState = 52;
        static final int TRANSACTION_updateProjectionCapability = 43;
        static final int TRANSACTION_updateProjectionConnectionStatus = 26;
        static final int TRANSACTION_updateUsbDeviceName = 51;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IConnectionManagementService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IConnectionManagementService)) {
                return (IConnectionManagementService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            ProjectionLinkParameter projectionLinkParameterCreateFromParcel;
            WifiApConfiguration wifiApConfigurationCreateFromParcel;
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    addDmCallback(IDmListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    addPcmCallback(IPcmListener.Stub.asInterface(parcel.readStrongBinder()), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    rmDmCallback(IDmListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    rmPcmCallback(IPcmListener.Stub.asInterface(parcel.readStrongBinder()), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean lastDevice = setLastDevice(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(lastDevice ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    ConnDevice lastDevice2 = getLastDevice();
                    parcel2.writeNoException();
                    if (lastDevice2 != null) {
                        parcel2.writeInt(1);
                        lastDevice2.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean favoritePhone = setFavoritePhone(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(favoritePhone ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    ConnDevice favoritePhone2 = getFavoritePhone();
                    parcel2.writeNoException();
                    if (favoritePhone2 != null) {
                        parcel2.writeInt(1);
                        favoritePhone2.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsProjectionSupport = isProjectionSupport(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsProjectionSupport ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsEnableProjection = isEnableProjection(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsEnableProjection ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zEnableProjection = enableProjection(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zEnableProjection ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zEnableProjectionByDevType = enableProjectionByDevType(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zEnableProjectionByDevType ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle bundle = new Bundle();
                    getDevices(bundle);
                    parcel2.writeNoException();
                    parcel2.writeInt(1);
                    bundle.writeToParcel(parcel2, 1);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle bundle2 = new Bundle();
                    getOnlineDevices(bundle2);
                    parcel2.writeNoException();
                    parcel2.writeInt(1);
                    bundle2.writeToParcel(parcel2, 1);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<CmDevice> projectionDevices = getProjectionDevices(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(projectionDevices);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDeleteDevice = deleteDevice(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zDeleteDevice ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDeleteAllDevices = deleteAllDevices(IConnectionManagementServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zDeleteAllDevices ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDeleteProjectionData = deleteProjectionData(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zDeleteProjectionData ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> projectionPriorityList = getProjectionPriorityList();
                    parcel2.writeNoException();
                    parcel2.writeStringList(projectionPriorityList);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean projectionPriorityList2 = setProjectionPriorityList(parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(projectionPriorityList2 ? 1 : 0);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zConnectDevice = connectDevice(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zConnectDevice ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDisconnectDevice = disconnectDevice(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zDisconnectDevice ? 1 : 0);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUpdateDeviceFriendlyName = updateDeviceFriendlyName(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zUpdateDeviceFriendlyName ? 1 : 0);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        projectionLinkParameterCreateFromParcel = ProjectionLinkParameter.CREATOR.createFromParcel(parcel);
                    } else {
                        projectionLinkParameterCreateFromParcel = null;
                    }
                    int iConnectProjection = connectProjection(projectionLinkParameterCreateFromParcel);
                    parcel2.writeNoException();
                    parcel2.writeInt(iConnectProjection);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDisconnectProjection = disconnectProjection(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zDisconnectProjection ? 1 : 0);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateProjectionConnectionStatus(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDoUsbRoleSwitch = doUsbRoleSwitch(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zDoUsbRoleSwitch ? 1 : 0);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zResetUsb = resetUsb(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zResetUsb ? 1 : 0);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zEnableUsbAoa = enableUsbAoa(parcel.readString(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zEnableUsbAoa ? 1 : 0);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zEnableUsbAoaForAndroidAuto = enableUsbAoaForAndroidAuto(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zEnableUsbAoaForAndroidAuto ? 1 : 0);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zEnableBluetooth = enableBluetooth(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zEnableBluetooth ? 1 : 0);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zConnectBtDevice = connectBtDevice(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zConnectBtDevice ? 1 : 0);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDisconnectBtDevice = disconnectBtDevice(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zDisconnectBtDevice ? 1 : 0);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDisconnectOtherBtDevices = disconnectOtherBtDevices(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zDisconnectOtherBtDevices ? 1 : 0);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zConnectBtProfile = connectBtProfile(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zConnectBtProfile ? 1 : 0);
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDisconnectBtProfile = disconnectBtProfile(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zDisconnectBtProfile ? 1 : 0);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    BtOobInfo btOobInfo = getBtOobInfo();
                    parcel2.writeNoException();
                    if (btOobInfo != null) {
                        parcel2.writeInt(1);
                        btOobInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    setBtOobInfo(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zMergeDevices = mergeDevices(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zMergeDevices ? 1 : 0);
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zMergeDevicesByUsbAndBt = mergeDevicesByUsbAndBt(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zMergeDevicesByUsbAndBt ? 1 : 0);
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean z = parcel.readInt() != 0;
                    if (parcel.readInt() != 0) {
                        wifiApConfigurationCreateFromParcel = WifiApConfiguration.CREATOR.createFromParcel(parcel);
                    } else {
                        wifiApConfigurationCreateFromParcel = null;
                    }
                    enableWifiAp(z, wifiApConfigurationCreateFromParcel);
                    parcel2.writeNoException();
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zScanWirelessProjection = scanWirelessProjection(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zScanWirelessProjection ? 1 : 0);
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateProjectionCapability(parcel.readString(), parcel.readInt(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean alwaysProjection = setAlwaysProjection(parcel.readString(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(alwaysProjection ? 1 : 0);
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAlwaysProjectionByDevType(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<ConnDevice> activeProjections = getActiveProjections();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(activeProjections);
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCancelWirelessCarplay = cancelWirelessCarplay(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zCancelWirelessCarplay ? 1 : 0);
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    ConnDevice device = getDevice(parcel.readString());
                    parcel2.writeNoException();
                    if (device != null) {
                        parcel2.writeInt(1);
                        device.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 49:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean deviceConnectionDetection = setDeviceConnectionDetection(parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(deviceConnectionDetection ? 1 : 0);
                    return true;
                case 50:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<AbnormalDevice> abnormalDeviceList = getAbnormalDeviceList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(abnormalDeviceList);
                    return true;
                case 51:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUpdateUsbDeviceName = updateUsbDeviceName(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zUpdateUsbDeviceName ? 1 : 0);
                    return true;
                case 52:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateIapSessionState(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 53:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCheckDeviceCapability = checkDeviceCapability(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zCheckDeviceCapability ? 1 : 0);
                    return true;
                case TRANSACTION_getSupportedWifiChannel /* 54 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int supportedWifiChannel = getSupportedWifiChannel();
                    parcel2.writeNoException();
                    parcel2.writeInt(supportedWifiChannel);
                    return true;
                case TRANSACTION_setMobileDeviceId /* 55 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    setMobileDeviceId(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IConnectionManagementService {
            public static IConnectionManagementService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public void addDmCallback(IDmListener cb, int objectType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    _data.writeInt(objectType);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addDmCallback(cb, objectType);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public void addPcmCallback(IPcmListener cb, List<String> services) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    _data.writeStringList(services);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addPcmCallback(cb, services);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public void rmDmCallback(IDmListener cb, int objectType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    _data.writeInt(objectType);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().rmDmCallback(cb, objectType);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public void rmPcmCallback(IPcmListener cb, List<String> services) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    _data.writeStringList(services);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().rmPcmCallback(cb, services);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean setLastDevice(String devId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setLastDevice(devId);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public ConnDevice getLastDevice() throws RemoteException {
                ConnDevice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLastDevice();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ConnDevice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean setFavoritePhone(String devId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setFavoritePhone(devId);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public ConnDevice getFavoritePhone() throws RemoteException {
                ConnDevice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFavoritePhone();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ConnDevice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean isProjectionSupport(int type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(type);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isProjectionSupport(type);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean isEnableProjection(int type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(type);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isEnableProjection(type);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean enableProjection(int type, boolean flag) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(type);
                    _data.writeInt(flag ? 1 : 0);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableProjection(type, flag);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean enableProjectionByDevType(int serviceType, int deviceType, boolean flag) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serviceType);
                    _data.writeInt(deviceType);
                    _data.writeInt(flag ? 1 : 0);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableProjectionByDevType(serviceType, deviceType, flag);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public void getDevices(Bundle devices) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getDevices(devices);
                        return;
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        devices.readFromParcel(_reply);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public void getOnlineDevices(Bundle devices) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getOnlineDevices(devices);
                        return;
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        devices.readFromParcel(_reply);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public List<CmDevice> getProjectionDevices(int service) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(service);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProjectionDevices(service);
                    }
                    _reply.readException();
                    List<CmDevice> _result = _reply.createTypedArrayList(CmDevice.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean deleteDevice(String devId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteDevice(devId);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean deleteAllDevices(IConnectionManagementServiceCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteAllDevices(callback);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean deleteProjectionData(String devId, int service) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeInt(service);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteProjectionData(devId, service);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public List<String> getProjectionPriorityList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProjectionPriorityList();
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean setProjectionPriorityList(List<String> list) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStringList(list);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setProjectionPriorityList(list);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean connectDevice(String device, int type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(device);
                    _data.writeInt(type);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().connectDevice(device, type);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean disconnectDevice(String devId, int type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeInt(type);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disconnectDevice(devId, type);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean updateDeviceFriendlyName(String devId, String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeString(name);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateDeviceFriendlyName(devId, name);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public int connectProjection(ProjectionLinkParameter plp) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (plp != null) {
                        _data.writeInt(1);
                        plp.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().connectProjection(plp);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean disconnectProjection(String devId, int capa, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeInt(capa);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disconnectProjection(devId, capa, reason);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public void updateProjectionConnectionStatus(String devId, int capa, int state, int code) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeInt(capa);
                    _data.writeInt(state);
                    _data.writeInt(code);
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateProjectionConnectionStatus(devId, capa, state, code);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean doUsbRoleSwitch(String devId, int mode, int purpose) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeInt(mode);
                    _data.writeInt(purpose);
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().doUsbRoleSwitch(devId, mode, purpose);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean resetUsb(String devId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().resetUsb(devId);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean enableUsbAoa(String devId, int mode, boolean flag) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeInt(mode);
                    _data.writeInt(flag ? 1 : 0);
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableUsbAoa(devId, mode, flag);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean enableUsbAoaForAndroidAuto(String devId, String serialNumber, int mode, boolean flag) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeString(serialNumber);
                    _data.writeInt(mode);
                    _data.writeInt(flag ? 1 : 0);
                    boolean _status = this.mRemote.transact(30, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableUsbAoaForAndroidAuto(devId, serialNumber, mode, flag);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean enableBluetooth(boolean flag) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(flag ? 1 : 0);
                    boolean _status = this.mRemote.transact(31, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableBluetooth(flag);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean connectBtDevice(String devId, String mac) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeString(mac);
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().connectBtDevice(devId, mac);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean disconnectBtDevice(String devId, String mac) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeString(mac);
                    boolean _status = this.mRemote.transact(33, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disconnectBtDevice(devId, mac);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean disconnectOtherBtDevices(String devId, String mac) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeString(mac);
                    boolean _status = this.mRemote.transact(34, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disconnectOtherBtDevices(devId, mac);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean connectBtProfile(String devId, String mac, int profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeString(mac);
                    _data.writeInt(profile);
                    boolean _status = this.mRemote.transact(35, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().connectBtProfile(devId, mac, profile);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean disconnectBtProfile(String devId, String mac, int profile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeString(mac);
                    _data.writeInt(profile);
                    boolean _status = this.mRemote.transact(36, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disconnectBtProfile(devId, mac, profile);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public BtOobInfo getBtOobInfo() throws RemoteException {
                BtOobInfo _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(37, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBtOobInfo();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = BtOobInfo.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public void setBtOobInfo(String name, String linkKey, String mac, int linkKeyType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    _data.writeString(linkKey);
                    _data.writeString(mac);
                    _data.writeInt(linkKeyType);
                    boolean _status = this.mRemote.transact(38, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setBtOobInfo(name, linkKey, mac, linkKeyType);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean mergeDevices(String devId, String key, int keyType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeString(key);
                    _data.writeInt(keyType);
                    boolean _status = this.mRemote.transact(39, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().mergeDevices(devId, key, keyType);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean mergeDevicesByUsbAndBt(String usb, String bt) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(usb);
                    _data.writeString(bt);
                    boolean _status = this.mRemote.transact(40, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().mergeDevicesByUsbAndBt(usb, bt);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public void enableWifiAp(boolean enable, WifiApConfiguration config) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enable ? 1 : 0);
                    if (config != null) {
                        _data.writeInt(1);
                        config.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(41, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableWifiAp(enable, config);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean scanWirelessProjection(boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(42, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().scanWirelessProjection(enable);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public void updateProjectionCapability(String id, int capability, boolean flag, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(id);
                    _data.writeInt(capability);
                    _data.writeInt(flag ? 1 : 0);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(43, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateProjectionCapability(id, capability, flag, reason);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean setAlwaysProjection(String devId, int capa, boolean always) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeInt(capa);
                    _data.writeInt(always ? 1 : 0);
                    boolean _status = this.mRemote.transact(44, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAlwaysProjection(devId, capa, always);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public void setAlwaysProjectionByDevType(int devType, int capa, boolean always) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(devType);
                    _data.writeInt(capa);
                    _data.writeInt(always ? 1 : 0);
                    boolean _status = this.mRemote.transact(45, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAlwaysProjectionByDevType(devType, capa, always);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public List<ConnDevice> getActiveProjections() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(46, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveProjections();
                    }
                    _reply.readException();
                    List<ConnDevice> _result = _reply.createTypedArrayList(ConnDevice.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean cancelWirelessCarplay(String devId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    boolean _status = this.mRemote.transact(47, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cancelWirelessCarplay(devId);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public ConnDevice getDevice(String devId) throws RemoteException {
                ConnDevice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    boolean _status = this.mRemote.transact(48, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDevice(devId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ConnDevice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean setDeviceConnectionDetection(boolean on, boolean persistent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(on ? 1 : 0);
                    _data.writeInt(persistent ? 1 : 0);
                    boolean _status = this.mRemote.transact(49, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDeviceConnectionDetection(on, persistent);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public List<AbnormalDevice> getAbnormalDeviceList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(50, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAbnormalDeviceList();
                    }
                    _reply.readException();
                    List<AbnormalDevice> _result = _reply.createTypedArrayList(AbnormalDevice.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean updateUsbDeviceName(String devId, String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeString(name);
                    boolean _status = this.mRemote.transact(51, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateUsbDeviceName(devId, name);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public void updateIapSessionState(String id, int state, int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(id);
                    _data.writeInt(state);
                    _data.writeInt(reason);
                    boolean _status = this.mRemote.transact(52, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateIapSessionState(id, state, reason);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public boolean checkDeviceCapability(String devId, int capability) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeInt(capability);
                    boolean _status = this.mRemote.transact(53, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkDeviceCapability(devId, capability);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public int getSupportedWifiChannel() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getSupportedWifiChannel, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSupportedWifiChannel();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // ts.car.dcm.common.aidl.IConnectionManagementService
            public void setMobileDeviceId(String devId, String mobileDeviceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeString(mobileDeviceId);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setMobileDeviceId, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMobileDeviceId(devId, mobileDeviceId);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IConnectionManagementService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IConnectionManagementService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
