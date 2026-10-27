package ts.car.service.dcm;

import android.os.RemoteException;
import java.util.HashMap;
import java.util.List;
import ts.car.service.IPlatformService;
import ts.car.service.dcm.data.AbnormalDevice;
import ts.car.service.dcm.data.ConnectionDevice;
import ts.car.service.dcm.data.Device;
import ts.car.service.dcm.data.ProjectionLinkParameter;

/* JADX INFO: loaded from: classes2.dex */
public interface IDeviceConnectionManager extends IPlatformService {
    public static final String ACCESS_DCM_STATE_PERMISSION = "car.permission.ACCESS_DCM_STATE";
    public static final String CHANGE_DCM_STATE_PERMISSION = "car.permission.CHANGE_DCM_STATE";

    boolean cancelProjectConnection(Device device, int i) throws RemoteException;

    boolean checkDeviceCapability(Device device, int i) throws RemoteException;

    int connectProjection(Device device, ProjectionLinkParameter projectionLinkParameter) throws RemoteException;

    boolean deleteAllDevices() throws RemoteException;

    boolean deleteDevice(Device device) throws RemoteException;

    boolean disconnectProjection(Device device, int i, int i2) throws RemoteException;

    boolean enableProjection(int i, int i2, boolean z) throws RemoteException;

    boolean enableProjection(int i, boolean z) throws RemoteException;

    List<AbnormalDevice> getAbnormalDeviceList() throws RemoteException;

    List<ConnectionDevice> getActiveProjections() throws RemoteException;

    ConnectionDevice getDevice(String str) throws RemoteException;

    HashMap<String, ConnectionDevice> getDeviceList() throws RemoteException;

    ConnectionDevice getLastDevice() throws RemoteException;

    HashMap<String, ConnectionDevice> getOnlineDeviceList() throws RemoteException;

    int getSupportedWifiChannel() throws RemoteException;

    boolean isProjectionEnabled(int i) throws RemoteException;

    boolean isProjectionSupported(int i) throws RemoteException;

    void registerListener(IDeviceConnectionListener iDeviceConnectionListener, int i) throws RemoteException;

    boolean setAutoProjectionConnectCapability(Device device, int i, boolean z) throws RemoteException;

    boolean setDeviceConnectionDetection(boolean z, boolean z2) throws RemoteException;

    boolean setLastDevice(Device device) throws RemoteException;

    void unregisterListener(IDeviceConnectionListener iDeviceConnectionListener, int i) throws RemoteException;
}
