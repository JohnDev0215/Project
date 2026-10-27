package com.ts.car.dcm.dccontroller.projectionmanager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.media.MediaPlayer2;
import android.util.Log;
import com.ts.car.dcm.common.data.DcmUtils;
import com.ts.car.dcm.common.data.LogEncrypt;
import com.ts.car.dcm.dccontroller.DcController;
import com.ts.car.dcm.dccontroller.configdata.ConfigManager;
import com.ts.car.dcm.dccontroller.connectionmanagerservice.CmsMessage;
import com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback;
import com.ts.car.dcm.dccontroller.projectionmanager.ProjectionManager;
import com.ts.car.dcm.dccontroller.storemanager.Device;
import com.ts.car.dcm.dccontroller.storemanager.DeviceManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import ts.car.dcm.common.data.CmsConstants;
import ts.car.dcm.common.data.ConnDevice;
import ts.car.dcm.common.data.ProjectionLinkParameter;

/* JADX INFO: loaded from: classes.dex */
public class ProjectionManager {
    private static final int DCM_ALL_REPORT_MODE = 1;
    private static final int DCM_AUTO_CONNECT_MODE = 0;
    private static final int DCM_SMART_REPORT_INCLUDE_SAME_DEVICE_MODE = 3;
    private static final int DCM_SMART_REPORT_MODE = 2;
    private static final int MAX_CONNECTION_NUMBER = 3;
    private static final int MAX_IPOD_NUMBER = 2;
    public static final int MAX_SYNC_TIME = 1000;
    private static final int PROJECTION_BT = 1536;
    private static final int PROJECTION_USB = 448;
    private static final int PROJECTION_WIFI = 2048;
    private static String TAG = "ProjectionManager";
    private ICmsCallback mCmsCallback;
    private ConfigManager mConfigManager;
    private DcController mDcController;
    private DeviceManager mDeviceManager;
    private Handler mHandler;
    private Looper mLooper;
    private final Object mCmsCbLock = new Object();
    private final List<PreProjectionConn> mPreConnProjections = new ArrayList();
    private Map<String, ProjectionConnection> mProjectionConnMap = new ConcurrentHashMap();
    private boolean mIsFirstCaplayRegister = false;
    private List<CpwlIap2AuthRequest> mCpwlIap2AuthList = new ArrayList();
    private String mReattachWifiMac = null;
    private String mReattachDeviceId = null;

    public static class DcmLock {
        boolean mNotified;
        int mResult;
    }

    public ProjectionManager(DcController dcc, Looper looper, Context context) {
        this.mDcController = dcc;
        this.mLooper = looper;
        this.mHandler = new Handler(looper);
        this.mDeviceManager = this.mDcController.getDeviceManager();
        this.mConfigManager = this.mDcController.getConfigManager();
    }

    public void registerCmsCallback(ICmsCallback cb) {
        Log.d(TAG, "registerCmsCallback, cb:" + cb);
        synchronized (this.mCmsCbLock) {
            this.mCmsCallback = cb;
        }
    }

    public int getActiveProjectionService() {
        for (ProjectionConnection conn : this.mProjectionConnMap.values()) {
            if (DcmUtils.isProjectionService(conn.getActiveService())) {
                return conn.getActiveService();
            }
        }
        return 0;
    }

    public ProjectionConnection getActiveProjectionConnection() {
        for (ProjectionConnection conn : this.mProjectionConnMap.values()) {
            if (DcmUtils.isProjectionService(conn.getActiveService())) {
                return conn;
            }
        }
        return null;
    }

    public List<Integer> getActiveServices() {
        List<Integer> srvList = new ArrayList<>();
        for (ProjectionConnection conn : this.mProjectionConnMap.values()) {
            srvList.add(Integer.valueOf(conn.getActiveService()));
        }
        return srvList;
    }

    public int getActiveService(String devId) {
        ProjectionConnection conn = this.mProjectionConnMap.get(devId);
        if (conn != null) {
            return conn.getActiveService();
        }
        return 0;
    }

    public Device getActiveProjectionDevice(String devId) {
        ProjectionConnection conn = this.mProjectionConnMap.get(devId);
        if (conn != null) {
            return conn.getActiveDevice();
        }
        return null;
    }

    private int arbResult2ActSvcResult(int res) {
        if (res == 1) {
            return 2;
        }
        if (res == 2) {
            return 1;
        }
        if (res == 4) {
            return 0;
        }
        return 5;
    }

    private boolean isWirelessProjectionCapability(int capability) {
        if (capability == 512 || capability == 1024 || capability == 2048) {
            return true;
        }
        Log.d(TAG, "isWirelessProjectionCapability, " + capability + " is not a wireless projection capability.");
        return false;
    }

    public int connectProjection(final Device device, final ProjectionLinkParameter plp) {
        Log.d(TAG, "connectProjection, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()) + ", plp:" + plp);
        int result = 5;
        if (isRunningInSameThread()) {
            if (!device.isOnline()) {
                if (plp.getAutoConnectDevice()) {
                    int result2 = connectOfflineProjection(device, plp);
                    return result2;
                }
                Log.e(TAG, "connectProjection, device is offline, can not connect projection.");
                return 5;
            }
            int result3 = connectOnlineProjection(device, plp);
            return result3;
        }
        final DcmLock locker = new DcmLock();
        locker.mResult = 6;
        this.mHandler.post(new Runnable() { // from class: com.ts.car.dcm.dccontroller.projectionmanager.-$$Lambda$ProjectionManager$v_lGhJmBjza8yDZHk6vddxBZZsc
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$connectProjection$0$ProjectionManager(device, plp, locker);
            }
        });
        synchronized (locker) {
            try {
                if (!locker.mNotified) {
                    locker.wait(10000L);
                    Log.d(TAG, "connectProjection, wait end.");
                }
                result = locker.mResult;
            } catch (InterruptedException expection) {
                expection.printStackTrace();
            }
        }
        return result;
    }

    public /* synthetic */ void lambda$connectProjection$0$ProjectionManager(Device device, ProjectionLinkParameter plp, DcmLock locker) {
        if (!device.isOnline()) {
            if (plp.getAutoConnectDevice()) {
                locker.mResult = connectOfflineProjection(device, plp);
            } else {
                Log.e(TAG, "connectProjection, device is offline, can not connect projection.");
            }
        } else {
            locker.mResult = connectOnlineProjection(device, plp);
        }
        synchronized (locker) {
            Log.d(TAG, "connectProjection, result = " + locker.mResult);
            locker.mNotified = true;
            locker.notifyAll();
        }
    }

    private int connectOnlineProjection(Device device, ProjectionLinkParameter plp) {
        boolean needDetecting = false;
        if (plp.getCapability() == 512) {
            needDetecting = true;
        }
        return makeProjectionConnection(device, plp, needDetecting);
    }

    private int connectOfflineProjection(Device device, ProjectionLinkParameter plp) {
        if (isWirelessProjectionCapability(plp.getCapability())) {
            if ((plp.getCapability() & 1536) <= 0) {
                return 5;
            }
            Log.d(TAG, "connectOfflineProjection, create a projection connection. plp:" + plp);
            int result = makeProjectionConnection(device, plp, true);
            return result;
        }
        Log.d(TAG, "connectOfflineProjection, device offline. devId:" + device.getId());
        return 5;
    }

    private void addPreConnProjection(ProjectionLinkParameter plp, boolean needDetecting) {
        if (this.mPreConnProjections.isEmpty()) {
            PreProjectionConn ppc = new PreProjectionConn(plp, needDetecting);
            this.mPreConnProjections.add(ppc);
            return;
        }
        PreProjectionConn preProjectionConn = this.mPreConnProjections.get(0);
        if (preProjectionConn.mLinkParameter.getDevId().equals(plp.getDevId()) && preProjectionConn.mLinkParameter.getCapability() == plp.getCapability()) {
            Log.d(TAG, "addPreConnProjection, ignore the same connection request. devId:" + LogEncrypt.IpAddressEncrypt(plp.getDevId()) + ", capability:" + plp.getCapability());
            return;
        }
        this.mPreConnProjections.clear();
        PreProjectionConn ppc2 = new PreProjectionConn(plp, needDetecting);
        this.mPreConnProjections.add(ppc2);
    }

    private synchronized int makeProjectionConnection(Device device, ProjectionLinkParameter plp, boolean needDetecting) {
        int result;
        if (plp == null) {
            Log.e(TAG, "makeProjectionConnection, plp is null.");
            return 5;
        }
        int service = DcmUtils.getSrvTypeByCapability(plp.getCapability());
        Log.d(TAG, "makeProjectionConnection, plp:" + plp + ", needDetecting:" + needDetecting + ",service:" + service);
        int result2 = selectionArbitration(device, service, plp.getCapability(), plp.getFromUser());
        if (result2 == 0) {
            synchronized (this.mCmsCbLock) {
                if (this.mCmsCallback != null) {
                    ProjectionConnection conn = new ProjectionConnection("Sm-" + device.getId(), this, this.mLooper, this.mCmsCallback, this.mDcController);
                    result = conn.activateConnection(device, plp, needDetecting);
                    if (result == 0) {
                        this.mProjectionConnMap.put(device.getId(), conn);
                    }
                } else {
                    Log.e(TAG, "makeProjectionConnection, mCmsCallback is null.");
                    result = 4;
                }
            }
        } else if (result2 == 5) {
            Log.d(TAG, "makeProjectionConnection, projection Arbitration result:" + result2 + ", devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
            ProjectionConnection pconn = getActiveProjectionConnection();
            if (pconn != null) {
                if (!plp.getFromUser() && (plp.getCapability() & 3584) != 0) {
                    result = 1;
                    Log.e(TAG, "makeProjectionConnection, there is already a projection connection,reject automatic reconnection.");
                } else {
                    if (!pconn.isDisconnecting()) {
                        pconn.deactivateConnection(2);
                        addPreConnProjection(plp, needDetecting);
                    } else {
                        addPreConnProjection(plp, needDetecting);
                    }
                    result = 0;
                }
            } else {
                result = 5;
            }
        } else {
            result = arbResult2ActSvcResult(result2);
        }
        Log.d(TAG, "makeProjectionConnection, result:" + result + ", devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
        return result;
    }

    private void stopWifiApForAccOff(int reason) {
        if (this.mConfigManager.isMonitorPower() && reason == 1) {
            boolean isExistWirelessCarplay = false;
            for (Map.Entry<String, ProjectionConnection> entry : this.mProjectionConnMap.entrySet()) {
                ProjectionConnection projectionConnection = entry.getValue();
                if (projectionConnection.getActiveCapability() == 512 && projectionConnection.getProjectionDisconnectReason() == 1) {
                    isExistWirelessCarplay = true;
                }
            }
            if (!isExistWirelessCarplay) {
                this.mDcController.enableWifiAp(false, null);
            }
        }
    }

    synchronized void onProjectionDisconnected(String devId, int reason) {
        Log.d(TAG, "onProjectionDisconnected, devId:" + LogEncrypt.IpAddressEncrypt(devId));
        PreProjectionConn ppc = null;
        boolean isReused = false;
        ProjectionConnection conn = this.mProjectionConnMap.get(devId);
        Device dev = this.mDeviceManager.getDevice(devId);
        if (dev != null && conn != null) {
            Log.e(TAG, "onProjectionDisconnected, ActiveCapability:" + conn.getActiveCapability());
            if (dev.getDeviceConnectedState() == 2 && conn.getActiveCapability() == 1024) {
                String wifiAddr = dev.getWifiAddr();
                this.mReattachWifiMac = wifiAddr;
                if (wifiAddr != null) {
                    this.mReattachDeviceId = devId;
                    Log.d(TAG, "onProjectionDisconnected, deviceId: " + LogEncrypt.IpAddressEncrypt(this.mReattachDeviceId) + ", wifiMac: " + LogEncrypt.MacAddressEncrypt(this.mReattachWifiMac));
                }
            }
        }
        synchronized (this.mPreConnProjections) {
            if (!this.mPreConnProjections.isEmpty()) {
                Log.d(TAG, "onProjectionDisconnected, mProjectionConnMap size:" + this.mPreConnProjections.size());
                ppc = this.mPreConnProjections.remove(0);
            }
        }
        if (ppc != null) {
            Device device = this.mDeviceManager.getDevice(ppc.mLinkParameter.getDevId());
            if (conn != null && device != null && device.getDeviceConnectedState() != 2) {
                int result = conn.activateConnection(device, ppc.mLinkParameter, ppc.mNeedChecking);
                if (result == 0) {
                    isReused = true;
                    if (device.getId().equals(devId)) {
                        return;
                    }
                    Log.d(TAG, "onProjectionDisconnected, remove invalid projectionconnection. devId:" + LogEncrypt.IpAddressEncrypt(devId));
                    this.mProjectionConnMap.put(device.getId(), conn);
                }
            }
        }
        if (conn != null && !isReused) {
            Log.d(TAG, "onProjectionDisconnected, quitNow");
            conn.quitNow();
        }
        this.mProjectionConnMap.remove(devId);
        this.mDcController.deleteDeviceFromDb(devId);
        stopWifiApForAccOff(reason);
    }

    public String getReattchWifiDevice(String devId) {
        String str = this.mReattachDeviceId;
        if (str == null || !str.equals(devId)) {
            return null;
        }
        Log.d(TAG, "getReattchWifiDevice, deviceId: " + LogEncrypt.IpAddressEncrypt(this.mReattachDeviceId) + ", wifiMac: " + LogEncrypt.MacAddressEncrypt(this.mReattachWifiMac));
        String wifiMac = this.mReattachWifiMac;
        this.mReattachDeviceId = null;
        this.mReattachWifiMac = null;
        return wifiMac;
    }

    public synchronized void updateProjectionMap(String devId, Device mergedDev) {
        ProjectionConnection conn = this.mProjectionConnMap.get(devId);
        if (conn != null && mergedDev != null) {
            conn.setActiveDevice(mergedDev);
            this.mProjectionConnMap.put(mergedDev.getId(), conn);
            this.mProjectionConnMap.remove(devId);
            Log.d(TAG, "updateProjectionMap, remove devId: " + LogEncrypt.IpAddressEncrypt(devId) + ", update devId: " + LogEncrypt.IpAddressEncrypt(mergedDev.getId()));
        }
    }

    public synchronized void onProjectionSwitchDone(String devId, String switchDevId) {
        ProjectionConnection conn = this.mProjectionConnMap.get(devId);
        this.mProjectionConnMap.put(switchDevId, conn);
        this.mProjectionConnMap.remove(devId);
    }

    boolean releaseProjectionConnection(String devId, int capa, int reason) {
        Log.d(TAG, "releaseProjectionConnection, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", capa:" + capa);
        ProjectionConnection conn = this.mProjectionConnMap.get(devId);
        if (conn != null) {
            if (!conn.deactivateConnection(reason)) {
                Log.d(TAG, "releaseProjectionConnection, Projection disconnect failed. devId:" + LogEncrypt.IpAddressEncrypt(devId));
                return false;
            }
            return true;
        }
        if (capa == 128) {
            for (ProjectionConnection projectConn : this.mProjectionConnMap.values()) {
                Device device = projectConn.getSwitchDevice();
                if (device != null && device.getId().equals(devId)) {
                    Log.d(TAG, "releaseProjectionConnection, switchDevice:" + LogEncrypt.IpAddressEncrypt(device.getId()));
                    if (!projectConn.deactivateConnection(reason)) {
                        Log.i(TAG, "releaseProjectionConnection, Projection disconnect failed, switchDevice:" + LogEncrypt.IpAddressEncrypt(device.getId()));
                        return false;
                    }
                }
            }
            return true;
        }
        Log.d(TAG, "releaseProjectionConnection, do not find the projection connection, devId:" + LogEncrypt.IpAddressEncrypt(devId));
        return true;
    }

    private boolean isRunningInSameThread() {
        boolean ret = Looper.myLooper() == this.mHandler.getLooper();
        Log.d(TAG, "isRunningInSameThread, ret:" + ret);
        return ret;
    }

    private boolean disconnectProjection(Device dev, int capa, int reason) {
        final String devId = dev.getId();
        dev.setQuickReconnectCapability(0);
        PreProjectionConn ppc = this.mPreConnProjections.stream().filter(new Predicate() { // from class: com.ts.car.dcm.dccontroller.projectionmanager.-$$Lambda$ProjectionManager$JuELS5bmP322ViYKil8G7OmoHZ4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return devId.equals(((ProjectionManager.PreProjectionConn) obj).mLinkParameter.getDevId());
            }
        }).findAny().orElse(null);
        if (ppc != null) {
            Log.d(TAG, "disconnectProjection, remove pre connecting projection!");
            this.mPreConnProjections.remove(ppc);
        }
        return releaseProjectionConnection(devId, capa, reason);
    }

    public boolean disconnectProjection(final String devId, final int capa, final int reason) {
        Log.d(TAG, "disconnectProjection, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", capa:" + capa);
        boolean result = false;
        final Device device = this.mDeviceManager.getDevice(devId);
        final DcmLock lock = new DcmLock();
        if (device == null) {
            return false;
        }
        if (!isRunningInSameThread()) {
            lock.mResult = 0;
            this.mHandler.post(new Runnable() { // from class: com.ts.car.dcm.dccontroller.projectionmanager.-$$Lambda$ProjectionManager$rKDADAVxiatU_OJ_Z7sTC0r-uSQ
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$disconnectProjection$3$ProjectionManager(devId, capa, device, reason, lock);
                }
            });
            synchronized (lock) {
                try {
                    if (!lock.mNotified) {
                        lock.wait(1000L);
                    }
                    if (lock.mResult == 1) {
                        result = true;
                    }
                    Log.d(TAG, "disconnectProjection, lock wait end.");
                } catch (InterruptedException expection) {
                    expection.printStackTrace();
                }
            }
            return result;
        }
        return disconnectProjection(device, capa, reason);
    }

    public /* synthetic */ void lambda$disconnectProjection$3$ProjectionManager(final String devId, int capa, Device device, int reason, DcmLock lock) {
        Log.d(TAG, "disconnectProjection, process disconnectProjection, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", capa:" + capa);
        if (capa == 512) {
            removeCpwlIap2AuthRequest(device.getBtAddr(), 2);
        }
        device.setQuickReconnectCapability(0);
        PreProjectionConn ppc = this.mPreConnProjections.stream().filter(new Predicate() { // from class: com.ts.car.dcm.dccontroller.projectionmanager.-$$Lambda$ProjectionManager$IPcwyxnL_WJAwGgbv_AJnkmVn0s
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return devId.equals(((ProjectionManager.PreProjectionConn) obj).mLinkParameter.getDevId());
            }
        }).findAny().orElse(null);
        if (ppc != null) {
            Log.d(TAG, "disconnectProjection, remove pre connecting projection. devId:" + LogEncrypt.IpAddressEncrypt(devId));
            this.mPreConnProjections.remove(ppc);
        }
        if (releaseProjectionConnection(devId, capa, reason)) {
            lock.mResult = 1;
        }
        synchronized (lock) {
            Log.d(TAG, "disconnectProjection, lock nofity.");
            lock.notifyAll();
            lock.mNotified = true;
        }
    }

    public void updateProjectionConnectionStatus(String devId, int capa, int status, int code) {
        Log.d(TAG, "updateProjectionConnectionStatus, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", service:" + capa + ", status:" + status + ", code:" + code);
        Device conDev = this.mDeviceManager.getDevice(devId);
        if (conDev == null) {
            Log.d(TAG, "updateProjectionConnectionStatus, not found device by deviceId:" + LogEncrypt.IpAddressEncrypt(devId) + ", return");
            return;
        }
        String newDevId = conDev.getId();
        ProjectionConnection projectionConn = this.mProjectionConnMap.get(newDevId);
        if (projectionConn == null) {
            Log.e(TAG, "updateProjectionConnectionStatus, projectionConnection is null.");
            Iterator<ProjectionConnection> it = this.mProjectionConnMap.values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ProjectionConnection conn = it.next();
                Device device = conn.getSwitchDevice();
                if (device != null && device.getId().equals(newDevId)) {
                    projectionConn = conn;
                    break;
                }
            }
        }
        if (conDev != null && projectionConn != null) {
            Log.e(TAG, "updateProjectionConnectionStatus, sendMessage: EVENT_UPDATE_CONNECTION_STATE.");
            CmsMessage dccMsg = new CmsMessage();
            dccMsg.arg1 = status;
            dccMsg.arg2 = code;
            dccMsg.arg3 = capa;
            dccMsg.obj = conDev;
            projectionConn.sendMessage(702, 0, 0, dccMsg);
        }
    }

    private int projectionArbitration(Device device, int service, int capability, boolean fromUser) {
        if (getActiveProjectionConnection() == null) {
            return 0;
        }
        ProjectionConnection conn = this.mProjectionConnMap.get(device.getId());
        if (conn != null) {
            ProjectionLinkParameter param = conn.getProjectionLinkParameter();
            Log.e(TAG, "projectionArbitration, service:" + service + ",capability:" + capability + ",fromUser:" + fromUser + ",ActiveService:" + conn.getActiveService() + ",state:" + conn.getCurrentState().getName() + ",ProjectionLinkParameter:" + param);
            if ((param == null || param.getFromUser() || !fromUser) && service == conn.getActiveService() && !conn.isDisconnecting()) {
                return 4;
            }
        }
        return 5;
    }

    private int ipodArbitration(Device device) {
        int ipodNum = 0;
        Iterator<ProjectionConnection> it = this.mProjectionConnMap.values().iterator();
        while (it.hasNext()) {
            if (it.next().getActiveService() == 4) {
                ipodNum++;
            }
        }
        if (ipodNum >= 2) {
            Log.e(TAG, "ipodArbitration, the connection number arrived max number.");
            return 1;
        }
        ProjectionConnection conn = this.mProjectionConnMap.get(device.getId());
        if (conn != null) {
            if (conn.getActiveService() == 1) {
                return 2;
            }
            return device.isAvailableCapability(8) ? 4 : 3;
        }
        return 0;
    }

    private synchronized int selectionArbitration(Device device, int service, int capability, boolean user) {
        if (!CmsConstants.ServiceType.isAvailableType(service)) {
            Log.e(TAG, "selectionArbitration, the service parameter input exception:" + service);
            return 3;
        }
        if (!isWirelessProjectionCapability(capability)) {
            if (!device.isAvailableCapability(capability)) {
                Log.e(TAG, "selectionArbitration, activate projection failed. capa(" + capability + ") is not available.");
                return 3;
            }
            if (service == 4) {
                return ipodArbitration(device);
            }
        }
        return projectionArbitration(device, service, capability, user);
    }

    private boolean isCapabilityChanged(Device device) {
        int availableCapa = device.getAvailableCapability();
        int availableCapa2 = availableCapa & 4040;
        int lastAvailableCapa = device.getLastAvailableCapability();
        return availableCapa2 != (lastAvailableCapa & 4040);
    }

    private boolean isCapabilityChangedOnLink(Device device, int conType) {
        int availableCapa = device.getAvailableCapability();
        int lastAvailableCapa = device.getLastAvailableCapability();
        if (conType == 1) {
            availableCapa &= PROJECTION_USB;
            lastAvailableCapa &= PROJECTION_USB;
        } else if (conType == 2) {
            availableCapa &= 1536;
            lastAvailableCapa &= 1536;
        } else if (conType == 3) {
            availableCapa &= 2048;
            lastAvailableCapa &= 2048;
        }
        if (availableCapa == 0) {
            Log.e(TAG, "isCapabilityChangedOnLink, no projection capability. contype:" + conType);
            return false;
        }
        if (availableCapa != lastAvailableCapa) {
            return true;
        }
        return false;
    }

    public boolean hasIpodCapabilityChanged(Device device, int conType) {
        if (conType != 1) {
            return false;
        }
        int availableCapa = device.getAvailableCapability();
        int lastAvailableCapa = device.getLastAvailableCapability();
        if (availableCapa != 0) {
            return availableCapa != lastAvailableCapa;
        }
        Log.d(TAG, "hasIpodCapabilityChanged, no ipod capability. devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
        return false;
    }

    public synchronized void handleProjectionDeviceDetach(Device device, int connType) {
        Log.d(TAG, "handleProjectionDeviceDetach, device:" + device + ", connType:" + connType);
        ProjectionConnection conn = this.mProjectionConnMap.get(device.getId());
        if (conn != null) {
            if (device.hasConnection(1) && ((device.hasConnection(2) || device.getActiveCapaState().size() > 0) && device == conn.getActiveDevice())) {
                if (isCapabilityChanged(device)) {
                    Log.d(TAG, "handleProjectionDeviceDetach, device capability changed.");
                    conn.sendMessage(MediaPlayer2.MEDIA_INFO_BUFFERING_UPDATE, device.getAvailableCapability(), device.getLastAvailableCapability());
                }
                device.resetLastProjectionCapability();
            } else {
                if (!needDisconnectProjection(device, conn, connType)) {
                    return;
                }
                if (!conn.isDisconnected() && !conn.deactivateConnection(4)) {
                    Log.d(TAG, "handleProjectionDeviceDetach, Projection disconnect failed. devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
                }
            }
        }
    }

    private boolean needDisconnectProjection(Device device, ProjectionConnection conn, int connType) {
        if (connType != 2 || (conn.getActiveCapability() != 128 && conn.getActiveCapability() != 64)) {
            if (connType == 2 && (conn.getActiveCapability() == 512 || conn.getActiveCapability() == 1024)) {
                Log.d(TAG, "handleProjectionDeviceDetach, ignore BT disconnection due to wireless projection.");
                return false;
            }
            if (connType == 3 && conn.isWifiApEnablingState()) {
                Log.d(TAG, "handleProjectionDeviceDetach, ignore wifi disconnection due to it's in WifiApEnablingState.");
                return false;
            }
            if (connType == 3 && ((conn.getActiveCapability() == 512 || conn.getActiveCapability() == 1024) && device.getDeviceConnectedState() != 2)) {
                Log.d(TAG, "handleProjectionDeviceDetach, ignore WIFI disconnection for wireless projection.");
                return false;
            }
            if (connType != 1 || (conn.getActiveCapability() != 512 && conn.getActiveCapability() != 1024)) {
                return true;
            }
            Log.d(TAG, "handleProjectionDeviceDetach, ignore USB disconnection for wireless projection.");
            return false;
        }
        Log.d(TAG, "handleProjectionDeviceDetach, ignore BT disconnection due to usb projection.");
        return false;
    }

    private void updateProjectionConnCapability(ProjectionConnection conn, Device device, int connType, int source) {
        int policy = this.mConfigManager.getProjectionConnRequestMode();
        if (!conn.isDisconnecting() || policy == 3) {
            if (isCapabilityChanged(device)) {
                Log.d(TAG, "updateProjectionConnCapability, device capability changed.");
                conn.sendMessage(MediaPlayer2.MEDIA_INFO_BUFFERING_UPDATE, device.getAvailableCapability(), device.getLastAvailableCapability(), null);
            }
            device.resetLastProjectionCapability();
            return;
        }
        if (policy == 0) {
            if (device.isAvailableCapability(conn.getActiveCapability()) && device.getQuickReconnectCapability() == conn.getActiveCapability()) {
                ProjectionLinkParameter plp = new ProjectionLinkParameter(device.getId(), device.getQuickReconnectCapability());
                if (makeProjectionConnection(device, plp, false) == 0) {
                    Log.d(TAG, "updateProjectionConnCapability, Activate projection. devId:" + LogEncrypt.IpAddressEncrypt(device.getId()) + ", capa:" + device.getQuickReconnectCapability());
                } else {
                    Log.d(TAG, "updateProjectionConnCapability, auto reconnect failed, notify user to select a projection.");
                    notifyUserSelectionProjection(device, connType);
                }
            }
        } else {
            Log.d(TAG, "updateProjectionConnCapability, conn is in disconnecting, Notify user to select a projection.");
            notifyUserSelectionProjection(device, connType);
        }
        device.resetLastProjectionCapability();
    }

    private int getDefaultProjection(int devType, int connType) {
        Log.d(TAG, "getDefaultProjection, devType:" + devType + ", conntype:" + connType);
        int capaType = 0;
        int projection = this.mConfigManager.getDefaultProjection(devType);
        if (connType != 1) {
            if (connType != 2) {
                if (connType == 3 && projection == 2) {
                    capaType = 2048;
                }
            } else if (projection == 1) {
                capaType = 512;
            } else if (projection == 2) {
                capaType = 2048;
            } else if (projection == 3) {
                capaType = 1024;
            }
        } else if (projection == 1) {
            capaType = 64;
        } else if (projection == 2) {
            capaType = 256;
        } else if (projection == 3) {
            capaType = 128;
        }
        Log.e(TAG, "getDefaultProjection, high projection type:" + capaType);
        return capaType;
    }

    private int getHighPriorityProjection(Device device, int connType) {
        Log.d(TAG, "getHighPriorityProjection, conntype:" + connType);
        int capaType = device.getAlwaysCapability();
        int cntType = DcmUtils.getConnectionTypeFromCapability(capaType);
        if (cntType != connType || !device.isAvailableCapability(capaType)) {
            capaType = getDefaultProjection(device.getDeviceType(), connType);
            if (!device.isAvailableCapability(capaType)) {
                if (capaType == 64 || capaType == 128) {
                    capaType = 256;
                } else if (capaType != 256) {
                    if (capaType == 512 || capaType == 1024) {
                        capaType = 2048;
                    } else if (capaType == 2048) {
                        if (device.getDeviceType() == 2) {
                            capaType = 512;
                        } else if (device.getDeviceType() == 1) {
                            capaType = 1024;
                        }
                    }
                } else if (device.getDeviceType() == 2) {
                    capaType = 64;
                } else if (device.getDeviceType() == 1) {
                    capaType = 128;
                }
                if (device.isAvailableCapability(capaType)) {
                    return capaType;
                }
                capaType = 0;
            }
        }
        Log.e(TAG, "getHighPriorityProjection, high projection type:" + capaType);
        return capaType;
    }

    public void notifyUserSelectionProjection(Device device, int connType) {
        int capa = getHighPriorityProjection(device, connType);
        if (capa != 0 && (device.getAvailableCapability() & 4040) > 0) {
            if (capa == 1024 && device.isWirelessAaRequested()) {
                return;
            }
            if (capa == 512 && !device.isProjectionFirstConnecting(1)) {
                Log.d(TAG, "Do not notify CP onrequestprojection case of not first.");
                return;
            }
            synchronized (this.mCmsCbLock) {
                if (this.mCmsCallback != null) {
                    this.mCmsCallback.onRequestProjection(device, capa);
                    Log.d(TAG, "notifyUserSelectionProjection, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
                }
            }
        }
    }

    private void handleSmartReportMode(Device device, int connType) {
        if (isCapabilityChangedOnLink(device, connType)) {
            Log.d(TAG, "handleSmartReportMode, has projection capability, request user select projection.");
            if (connType != 1 || !device.isResetUsbDone()) {
                notifyUserSelectionProjection(device, connType);
            } else {
                Log.e(TAG, "handleSmartReportMode, The usb device has been reset, DO NOT report onRequestProjection again.");
            }
            device.resetLastProjectionCapability();
        }
    }

    private void handleAutoConnectMode(Device device, int connType) {
        if (this.mProjectionConnMap.size() < 3) {
            if (!isCapabilityChangedOnLink(device, connType) && !hasIpodCapabilityChanged(device, connType)) {
                Log.e(TAG, "handleAutoConnectMode, no projection or ipod capability changed.");
                return;
            }
            ProjectionConnection conn = this.mProjectionConnMap.values().stream().filter(new Predicate() { // from class: com.ts.car.dcm.dccontroller.projectionmanager.-$$Lambda$ProjectionManager$-xKIsTllFEkrazJ6PSrdMMMxZaM
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return DcmUtils.isProjectionService(((ProjectionConnection) obj).getActiveService());
                }
            }).findAny().orElse(null);
            if (conn == null) {
                if (device.getAlwaysCapability() != 0 && device.isAvailableCapability(device.getAlwaysCapability())) {
                    ProjectionLinkParameter plp = new ProjectionLinkParameter(device.getId(), device.getAlwaysCapability());
                    makeProjectionConnection(device, plp, false);
                } else {
                    notifyUserSelectionProjection(device, connType);
                }
            } else if (conn.isDisconnecting()) {
                notifyUserSelectionProjection(device, connType);
            } else if ((device.getDeviceType() == 2 || device.getDeviceType() == 3) && device.isAvailableCapability(8)) {
                Log.d(TAG, "handleAutoConnectMode, make Ipod Connection. Ipod devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
                ProjectionLinkParameter plp2 = new ProjectionLinkParameter(device.getId(), 8);
                makeProjectionConnection(device, plp2, false);
            }
        } else {
            Log.d(TAG, "handleAutoConnectMode, Projection connection arrived max nubmer. new DevId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
        }
        device.resetLastProjectionCapability();
    }

    private void handleSmartReportIncludeSameDeviceMode(Device device, int connType, int source, ProjectionConnection conn) {
        if (isCapabilityChangedOnLink(device, connType)) {
            Log.d(TAG, "handleSmartReportIncludeSameDeviceMode, has projection capability,request user select projection.");
            if (connType != 1 || !device.isResetUsbDone()) {
                ProjectionConnection activeConn = getActiveProjectionConnection();
                if (!this.mConfigManager.isNeedGetPhoneName() || conn != null || this.mProjectionConnMap.size() != 1 || activeConn == null || activeConn.getActiveCapability() != 512 || activeConn.isDisconnecting() || connType != 1 || !device.isAvailableCapability(64) || !this.mCmsCallback.onCheckUsbDeviceName(device, 1)) {
                    notifyUserSelectionProjection(device, connType);
                }
            } else {
                Log.e(TAG, "handleSmartReportIncludeSameDeviceMode, The usb device has been reset, DO NOT report onRequestProjection again.");
            }
            if (conn == null) {
                device.resetLastProjectionCapability();
            }
        }
        if (conn != null) {
            updateProjectionConnCapability(conn, device, connType, source);
        }
    }

    public synchronized void requestProjectionConnection(Device device, int connType, int source) {
        ProjectionConnection conn = this.mProjectionConnMap.get(device.getId());
        int policy = this.mConfigManager.getProjectionConnRequestMode();
        Log.i(TAG, "requestProjectionConnection, device:" + device + ", connType:" + connType + ", policy:" + policy);
        if (policy == 3) {
            handleSmartReportIncludeSameDeviceMode(device, connType, source, conn);
            return;
        }
        if (conn != null) {
            updateProjectionConnCapability(conn, device, connType, source);
        } else {
            if (device.getDeviceConnectedState() != 1) {
                Log.e(TAG, "requestProjectionConnection, device not in online.");
            }
            if (policy == 0) {
                handleAutoConnectMode(device, connType);
            } else if (policy == 1) {
                Log.d(TAG, "requestProjectionConnection, request user select projection.");
                notifyUserSelectionProjection(device, connType);
                device.resetLastProjectionCapability();
            } else if (policy == 2) {
                handleSmartReportMode(device, connType);
            } else {
                Log.e(TAG, "requestProjectionConnection, unknown policy about request projection.");
            }
        }
    }

    private Device getDeviceByCapability(String id, int capability) {
        if ((capability & 1536) > 0) {
            Device device = this.mDeviceManager.getDeviceByConntype(2, id);
            return device;
        }
        if ((capability & 2048) > 0) {
            Device device2 = this.mDeviceManager.getDeviceByConntype(3, id);
            return device2;
        }
        if ((capability & PROJECTION_USB) > 0) {
            Device device3 = this.mDeviceManager.getDeviceByConntype(1, id);
            return device3;
        }
        Log.d(TAG, "getDeviceByCapability, not projection service capability:" + capability + ", devId:" + LogEncrypt.IpAddressEncrypt(id));
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int sendCpwlIap2AuthRequest() {
        int result = 1;
        if (this.mCpwlIap2AuthList.size() != 0) {
            Iterator<CpwlIap2AuthRequest> iterator = this.mCpwlIap2AuthList.iterator();
            while (true) {
                if (!iterator.hasNext()) {
                    break;
                }
                CpwlIap2AuthRequest cpwlIap2AuthRequest = iterator.next();
                Device device = this.mDeviceManager.getDeviceByConntype(2, cpwlIap2AuthRequest.btAddress);
                if (device != null) {
                    synchronized (this.mCmsCbLock) {
                        if (this.mCmsCallback != null && !this.mCmsCallback.onIap2BtDeviceConnected(device, cpwlIap2AuthRequest.reason)) {
                            result = 4;
                        }
                    }
                } else {
                    iterator.remove();
                    result = 2;
                }
            }
        } else {
            result = 3;
        }
        Log.e(TAG, "sendCpwlIap2AuthRequest, result:" + result);
        return result;
    }

    public void onProjectionServiceRegister(int srvType, boolean isRegister) {
        if (srvType == 1) {
            if (isRegister) {
                if (!this.mIsFirstCaplayRegister) {
                    this.mHandler.post(new Runnable() { // from class: com.ts.car.dcm.dccontroller.projectionmanager.-$$Lambda$ProjectionManager$smi-AJHe4o8nNu_1OY0_L0SUMiY
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.sendCpwlIap2AuthRequest();
                        }
                    });
                    this.mIsFirstCaplayRegister = true;
                    return;
                }
                return;
            }
            this.mIsFirstCaplayRegister = false;
        }
    }

    public void removeCpwlIap2AuthRequest(String btAddr, int reason) {
        CpwlIap2AuthRequest cpwlIap2AuthRequest = null;
        synchronized (this.mCpwlIap2AuthList) {
            Iterator<CpwlIap2AuthRequest> it = this.mCpwlIap2AuthList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                CpwlIap2AuthRequest cpwlIap2AuthRequestTmp = it.next();
                if (cpwlIap2AuthRequestTmp.btAddress.equals(btAddr) && cpwlIap2AuthRequestTmp.reason == reason) {
                    cpwlIap2AuthRequest = cpwlIap2AuthRequestTmp;
                    break;
                }
            }
            if (cpwlIap2AuthRequest != null) {
                this.mCpwlIap2AuthList.remove(cpwlIap2AuthRequest);
            }
        }
    }

    private void addCpwlIap2AuthRequest(String btAddr, int reason) {
        synchronized (this.mCpwlIap2AuthList) {
            if (!isExistInCpwlIap2AuthList(btAddr, reason)) {
                this.mCpwlIap2AuthList.add(new CpwlIap2AuthRequest(btAddr, reason));
            }
        }
    }

    private boolean isExistInCpwlIap2AuthList(String btAddr, int reason) {
        for (CpwlIap2AuthRequest cpwlIap2AuthRequest : this.mCpwlIap2AuthList) {
            if (cpwlIap2AuthRequest.btAddress.equals(btAddr) && cpwlIap2AuthRequest.reason == reason) {
                return true;
            }
        }
        return false;
    }

    private boolean isExistInCpwlIap2AuthList(String btAddr) {
        for (CpwlIap2AuthRequest cpwlIap2AuthRequest : this.mCpwlIap2AuthList) {
            if (cpwlIap2AuthRequest.btAddress.equals(btAddr)) {
                return true;
            }
        }
        return false;
    }

    private CpwlIap2AuthRequest getCpwlIap2AuthRequest(String btAddr) {
        for (CpwlIap2AuthRequest cpwlIap2AuthRequest : this.mCpwlIap2AuthList) {
            if (cpwlIap2AuthRequest.btAddress != null && cpwlIap2AuthRequest.btAddress.equals(btAddr)) {
                return cpwlIap2AuthRequest;
            }
        }
        return null;
    }

    private void processWirelessCarplayOverIapDetected(Device device, boolean enabled, int reason) {
        Log.e(TAG, "processWirelessCarplayOverIapDetected, flag:" + enabled + ", reason:" + reason);
        if (enabled) {
            if (!device.isOnline() && !device.hasConnection(2)) {
                device.setDeviceConnectedState(1);
                device.addConnectedTypeState(2, 3);
            }
            device.setDeviceType(2);
            device.setAvailableCapability(device.getAvailableCapability() | 512);
        } else {
            device.removeAvailableCapability(512);
        }
        device.setCpwlOverIapDetectStarting(false);
        device.setIsIapChecked(true);
        ProjectionConnection conn = this.mProjectionConnMap.get(device.getId());
        if (conn != null && conn.isCheckCpwlCapabilityState() && conn.getActiveCapability() == 512) {
            conn.removeMessageHandler(753);
            conn.sendMessage(708, reason, 0, Boolean.valueOf(enabled));
            device.resetLastProjectionCapability();
            return;
        }
        requestProjectionConnection(device, 2, 1);
    }

    private void updateDeviceCapability(Device device, int capability, boolean enabled) {
        if (enabled) {
            device.setAvailableCapability(device.getAvailableCapability() | capability);
            if (capability == 1024) {
                device.setDeviceType(1);
                return;
            }
            return;
        }
        device.removeAvailableCapability(capability);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateProjectionCapabilityInternal, reason: merged with bridge method [inline-methods] */
    public void lambda$updateProjectionCapability$5$ProjectionManager(String id, int capability, boolean enabled, int reason) {
        Device device = getDeviceByCapability(id, capability);
        Log.i(TAG, "updateProjectionCapabilityInternal, device:" + device + ", capability:" + capability + ", enabled:" + enabled + ", reason:" + reason);
        boolean saveDb = device != null && enabled && (device.getCapability() & capability) == 0;
        if (capability == 512) {
            CpwlIap2AuthRequest cpwlIap2AuthRequest = getCpwlIap2AuthRequest(id);
            if (cpwlIap2AuthRequest != null) {
                if (device != null) {
                    if (cpwlIap2AuthRequest.reason == 4) {
                        this.mCmsCallback.onDeviceCapabilityChecked(device.getId(), capability, enabled, reason);
                        updateDeviceCapability(device, capability, enabled);
                    } else if (cpwlIap2AuthRequest.reason == 5) {
                        updateDeviceCapability(device, capability, enabled);
                    } else {
                        processWirelessCarplayOverIapDetected(device, enabled, reason);
                    }
                    this.mCmsCallback.onDeviceStatusChanged(device);
                }
                removeCpwlIap2AuthRequest(id, cpwlIap2AuthRequest.reason);
            } else if (device != null) {
                processWirelessCarplayOverIapDetected(device, enabled, 0);
                this.mCmsCallback.onDeviceStatusChanged(device);
            }
            sendCpwlIap2AuthRequest();
        } else if (device != null) {
            updateDeviceCapability(device, capability, enabled);
            if (capability == 1024 && (device.getActiveCapability() == 128 || device.isWirelessAaRequested())) {
                Log.e(TAG, "updateProjectionCapabilityInternal: onRequesrProjection will not be triggered");
            } else {
                int connType = DcmUtils.getConnectionTypeFromCapability(capability);
                requestProjectionConnection(device, connType, 1);
                Log.e(TAG, "updateProjectionCapabilityInternal: request projection connection");
                if (capability == 1024 && enabled && !device.isWirelessAaRequested()) {
                    Log.e(TAG, "updateProjectionCapabilityInternal: onRequesrProjection is triggered for the first time for wireless aa.");
                    device.setIsWirelessAaRequested(true);
                    saveDb = true;
                }
            }
            this.mCmsCallback.onDeviceStatusChanged(device);
        }
        if (saveDb) {
            this.mDeviceManager.saveDevice(device);
        }
    }

    public void updateProjectionCapability(final String id, final int capability, final boolean flag, final int reason) {
        int service = DcmUtils.getSrvTypeByCapability(capability);
        if (service != 0) {
            this.mHandler.post(new Runnable() { // from class: com.ts.car.dcm.dccontroller.projectionmanager.-$$Lambda$ProjectionManager$GAQfD53ZsoJjEZ5LEpW2a4nafaU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$updateProjectionCapability$5$ProjectionManager(id, capability, flag, reason);
                }
            });
        }
    }

    public boolean enableBluetooth(boolean flag) {
        Log.e(TAG, "enableBluetooth, flag:" + flag);
        ProjectionConnection conn = getActiveProjectionConnection();
        if (conn != null && ((conn.isConnecting() || conn.isConnected()) && getActiveProjectionService() == 1)) {
            Log.d(TAG, "enableBluetooth, current in carplay projection service.");
            if (flag) {
                return false;
            }
            if (this.mConfigManager.enableProjectionNotify()) {
                if (conn.isProjectionStateNotify()) {
                    return true;
                }
                if (notifyBtProjectionState(true, conn.getActiveDevice().getBtAddr(), conn.getActiveCapability())) {
                    conn.setProjectionStateNotify(true);
                    return true;
                }
                Log.e(TAG, "enableBluetooth, notify bt module failure.");
                return false;
            }
            Log.e(TAG, "enableBluetooth, not enable projection notify feature.");
            return this.mDcController.enableBluetooth(flag);
        }
        return this.mDcController.enableBluetooth(flag);
    }

    public boolean connectBtDevice(String devId, String mac) {
        Log.d(TAG, "connectBtDevice, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", mac:" + LogEncrypt.MacAddressEncrypt(mac));
        if (this.mConfigManager.enableProjectionNotify()) {
            ProjectionConnection conn = getActiveProjectionConnection();
            if (conn != null && (conn.isConnecting() || conn.isConnected())) {
                Log.d(TAG, "connectBtDevice, current in projection service.");
                if (conn.isProjectionStateNotify()) {
                    return true;
                }
                if (notifyBtProjectionState(true, mac, conn.getActiveCapability())) {
                    conn.setProjectionStateNotify(true);
                    return true;
                }
                Log.e(TAG, "connectBtDevice, notify bt module failure.");
                return false;
            }
            return this.mDcController.connectBtDevice(devId, mac);
        }
        Log.e(TAG, "connectBtDevice, not enable projection notify feature.");
        return this.mDcController.connectBtDevice(devId, mac);
    }

    public boolean disconnectBtDevice(String devId, String mac) {
        Log.d(TAG, "disconnectBtDevice, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", mac:" + LogEncrypt.MacAddressEncrypt(mac));
        if (this.mConfigManager.enableProjectionNotify()) {
            ProjectionConnection conn = getActiveProjectionConnection();
            if (conn != null && (conn.isConnected() || conn.isConnecting())) {
                Log.d(TAG, "disconnectBtDevice, current in projection service.");
                Device device = conn.getActiveDevice();
                if ((device.getBtAddr() == null || device.getBtAddr().isEmpty()) && mac != null) {
                    this.mDcController.mergeDevices(devId, mac, 2);
                }
                if (conn.isProjectionStateNotify()) {
                    return true;
                }
                if (notifyBtProjectionState(true, mac, conn.getActiveCapability())) {
                    conn.setProjectionStateNotify(true);
                    return true;
                }
                Log.e(TAG, "disconnectBtDevice, notify bt module failure.");
                return false;
            }
            return this.mDcController.disconnectBtDevice(devId, mac);
        }
        Log.e(TAG, "disconnectBtDevice, not enable projection notify feature.");
        return this.mDcController.disconnectBtDevice(devId, mac);
    }

    public boolean disconnectOtherBtDevices(String devId, String mac) {
        Log.d(TAG, "disconnectOtherBtDevices, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", mac:" + LogEncrypt.MacAddressEncrypt(mac));
        if (this.mConfigManager.enableProjectionNotify()) {
            ProjectionConnection conn = getActiveProjectionConnection();
            if (conn != null && (conn.isConnected() || conn.isConnecting())) {
                Log.d(TAG, "disconnectOtherBtDevices, current in projection service.");
                if (conn.isProjectionStateNotify()) {
                    return true;
                }
                if (notifyBtProjectionState(true, mac, conn.getActiveCapability())) {
                    conn.setProjectionStateNotify(true);
                    return true;
                }
                Log.e(TAG, "disconnectOtherBtDevices, notify bt module failure.");
                return false;
            }
            return this.mDcController.disconnectOtherBtDevices(devId, mac);
        }
        Log.e(TAG, "disconnectOtherBtDevices, not enable projection notify feature.");
        return this.mDcController.disconnectOtherBtDevices(devId, mac);
    }

    public boolean disconnectBtProfile(String devId, String mac, int profile) {
        Log.d(TAG, "disconnectBtProfile, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", mac:" + LogEncrypt.MacAddressEncrypt(mac) + ", profile:" + profile);
        ProjectionConnection conn = getActiveProjectionConnection();
        if (conn != null && (conn.isConnected() || conn.isConnecting())) {
            Log.d(TAG, "disconnectBtProfile, current in projection service.");
            Device device = conn.getActiveDevice();
            if ((device.getBtAddr() == null || device.getBtAddr().isEmpty()) && mac != null) {
                this.mDcController.mergeDevices(devId, mac, 2);
            }
            if (this.mConfigManager.enableProjectionNotify()) {
                Log.d(TAG, "disconnectBtProfile, projectionOn calling.");
                if (conn.isProjectionStateNotify()) {
                    return true;
                }
                if (notifyBtProjectionState(true, mac, conn.getActiveCapability())) {
                    conn.setProjectionStateNotify(true);
                    return true;
                }
                Log.e(TAG, "disconnectBtProfile, notify bt module failure.");
                return false;
            }
            Log.e(TAG, "disconnectBtProfile, not enable projection notify feature.");
            conn.updateBtProfile(profile);
            return this.mDcController.disconnectBtProfile(devId, mac, profile);
        }
        return this.mDcController.disconnectBtProfile(devId, mac, profile);
    }

    public boolean connectBtProfile(String devId, String mac, int profile) {
        Log.d(TAG, "connectBtProfile, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", mac:" + LogEncrypt.MacAddressEncrypt(mac) + ", profile:" + profile);
        ProjectionConnection conn = this.mProjectionConnMap.get(devId);
        int activeService = getActiveService(devId);
        if (conn != null && ((conn.isConnected() || conn.isConnecting()) && (activeService == 3 || activeService == 2))) {
            Log.d(TAG, "connectBtProfile, current in projection service.");
            if (this.mConfigManager.enableProjectionNotify()) {
                if (conn.isProjectionStateNotify()) {
                    return true;
                }
                if (notifyBtProjectionState(true, mac, conn.getActiveCapability())) {
                    conn.setProjectionStateNotify(true);
                    return true;
                }
                Log.e(TAG, "connectBtProfile, notify bt module failure.");
                return false;
            }
            Log.e(TAG, "connectBtProfile, not enable projection notify feature.");
            return this.mDcController.connectBtProfile(devId, mac, profile);
        }
        return this.mDcController.connectBtProfile(devId, mac, profile);
    }

    public boolean notifyBtProjectionState(boolean flag, String mac, int capability) {
        return true;
    }

    public List<ConnDevice> getProjectionDevices() {
        Log.d(TAG, "getProjectionDevices.");
        if (this.mProjectionConnMap.size() > 0) {
            List<ConnDevice> devices = new ArrayList<>();
            for (ProjectionConnection conn : this.mProjectionConnMap.values()) {
                Device dev = conn.getActiveDevice();
                if (dev != null) {
                    Log.d(TAG, "getProjectionDevices, devId:" + LogEncrypt.IpAddressEncrypt(dev.getId()));
                    devices.add(dev.getConnDevice());
                }
            }
            return devices;
        }
        return null;
    }

    public void handleBtPairingStateChanged(Device device, int status) {
        ProjectionConnection conn = this.mProjectionConnMap.get(device.getId());
        if (conn != null) {
            conn.sendMessage(709, status);
        }
    }

    public void sendRestUsbFinishedMessage(Device device, int result) {
        ProjectionConnection projectionConnection = this.mProjectionConnMap.get(device.getId());
        if (projectionConnection != null) {
            projectionConnection.sendMessage(710, result);
        }
    }

    public void handleIapSessionDisconnected(Device device) {
        ProjectionConnection projectionConnection = this.mProjectionConnMap.get(device.getId());
        if (projectionConnection != null && projectionConnection.isPreUsbReadyState()) {
            projectionConnection.sendMessage(754);
        } else {
            this.mDcController.resetUsb(device.getId());
        }
    }

    public void handleProjectionServiceDeath(int service) {
        Device device;
        synchronized (this.mPreConnProjections) {
            Iterator<PreProjectionConn> iterator = this.mPreConnProjections.iterator();
            while (iterator.hasNext()) {
                PreProjectionConn preProjectionConn = iterator.next();
                if (preProjectionConn != null && service == DcmUtils.getSrvTypeByCapability(preProjectionConn.mLinkParameter.getCapability())) {
                    Log.d(TAG, "handleProjectionServiceDeath, remove preConnProjection. devId:" + preProjectionConn.mLinkParameter.getDevId());
                    iterator.remove();
                }
            }
        }
        List<String> pcList = new ArrayList<>();
        for (Map.Entry<String, ProjectionConnection> entry : this.mProjectionConnMap.entrySet()) {
            ProjectionConnection projectionConnection = entry.getValue();
            if (projectionConnection.getActiveDevice() != null && projectionConnection.getActiveService() == service && !projectionConnection.isDisconnected()) {
                projectionConnection.sendMessage(701);
            }
        }
        for (String pcKey : pcList) {
            if (this.mProjectionConnMap.get(pcKey) != null) {
                this.mProjectionConnMap.remove(pcKey);
            }
        }
        if (service == 1) {
            for (CpwlIap2AuthRequest cpwlIap2AuthRequest : this.mCpwlIap2AuthList) {
                if (cpwlIap2AuthRequest.reason == 1) {
                    this.mCmsCallback.onWirelessCarplayDetected(cpwlIap2AuthRequest.btAddress, 0);
                } else if (cpwlIap2AuthRequest.reason == 4 && (device = this.mDeviceManager.getDeviceByConntype(2, cpwlIap2AuthRequest.btAddress)) != null) {
                    this.mCmsCallback.onDeviceCapabilityChecked(device.getId(), 512, false, 3);
                }
            }
            this.mCpwlIap2AuthList.clear();
        }
        this.mCmsCallback.onProjectionServiceStatus(service, false);
    }

    public boolean checkWirelessCarplayCapability(String devId, int reason) {
        if (!this.mConfigManager.isEnableCarPlay() || !this.mConfigManager.isCpwlDetectingOverIapEnabled()) {
            Log.e(TAG, "checkWirelessCarplayCapability, carplay is not enabled or don't support to check wireless carplay capability by iap authentication.");
            return false;
        }
        Device device = this.mDeviceManager.getDevice(devId);
        if (device == null || device.getBtAddr() == null || device.getDeviceConnectedState() == 2) {
            Log.d(TAG, "checkWirelessCarplayCapability, invalid parameter, devId:" + LogEncrypt.IpAddressEncrypt(devId));
            return false;
        }
        ProjectionConnection projectionConnection = this.mProjectionConnMap.get(device.getId());
        if (projectionConnection != null) {
            if (projectionConnection.getActiveService() == 1) {
                Log.d(TAG, "checkWirelessCarplayCapability, there is a carplay connection. current:" + projectionConnection.getCurrentState() + ", reason:" + reason);
                if (reason != 2) {
                    if (reason == 4) {
                        this.mCmsCallback.onDeviceCapabilityChecked(devId, 512, true, 0);
                    }
                    return true;
                }
            }
        } else if (reason == 2) {
            Log.d(TAG, "checkWirelessCarplayCapability, failed, CONNECTING need projectionConnection. devId:" + LogEncrypt.IpAddressEncrypt(devId));
            return false;
        }
        if (reason == 5 && isExistInCpwlIap2AuthList(device.getBtAddr())) {
            Log.d(TAG, "checkWirelessCarplayCapability, exist in list, devId:" + LogEncrypt.IpAddressEncrypt(devId));
            return true;
        }
        String btAddr = device.getBtAddr();
        int sendResult = 1;
        if (!this.mCmsCallback.isAvailablePmService(1)) {
            sendResult = 4;
        } else if (this.mCpwlIap2AuthList.size() == 0) {
            addCpwlIap2AuthRequest(btAddr, reason);
            sendResult = sendCpwlIap2AuthRequest();
        } else {
            addCpwlIap2AuthRequest(btAddr, reason);
        }
        Log.d(TAG, "checkWirelessCarplayCapability, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", sendResult:" + sendResult + ", mCpwlIap2AuthList:" + this.mCpwlIap2AuthList);
        return handleSendCpwlIap2Result(device, reason, sendResult);
    }

    private boolean handleSendCpwlIap2Result(Device device, int reason, int sendResult) {
        boolean result = true;
        if (sendResult == 1) {
            if ((reason == 1 || reason == 2) && !device.isCpwlOverIapDetected()) {
                device.setCpwlOverIapDetected(true);
                device.setCpwlOverIapDetectStarting(true);
                this.mDeviceManager.saveDevice(device);
            }
        } else if (sendResult == 4) {
            result = false;
            if (reason == 1) {
                if (!device.isCpwlOverIapDetectedNotified()) {
                    this.mCmsCallback.onWirelessCarplayDetected(device.getBtAddr(), 0);
                    device.setCpwlOverIapDetected(true);
                    device.setCpwlOverIapDetectedNotified(true);
                    this.mDeviceManager.saveDevice(device);
                }
                addCpwlIap2AuthRequest(device.getBtAddr(), reason);
            } else if (reason == 2) {
                if (!device.isCpwlOverIapDetected()) {
                    device.setCpwlOverIapDetected(true);
                    device.setCpwlOverIapDetectedNotified(true);
                    this.mCmsCallback.onWirelessCarplayDetected(device.getBtAddr(), 0);
                    this.mDeviceManager.saveDevice(device);
                }
            } else if (reason == 5) {
                addCpwlIap2AuthRequest(device.getBtAddr(), reason);
            }
        }
        Log.d(TAG, "handleSendCpwlIap2Result, result:" + result);
        return result;
    }

    public boolean isAllowUsbRoleSwitch(String devId, int purpose) {
        if (purpose == 3) {
            return true;
        }
        if (purpose == 1 || purpose == 2) {
            ProjectionConnection projectionConnection = this.mProjectionConnMap.get(devId);
            if (projectionConnection != null) {
                return true;
            }
            return false;
        }
        return false;
    }

    public void handleBtDeviceRemoved(String devId, String mac) {
        ProjectionConnection projectionConn = this.mProjectionConnMap.get(devId);
        if (projectionConn != null && projectionConn.getActiveCapability() == 512 && !projectionConn.isConnected()) {
            disconnectProjection(devId, 512, 4);
        }
        if (projectionConn != null && projectionConn.getActiveCapability() == 1024 && projectionConn.isConnected() && projectionConn.getActiveDevice().getDeviceConnectedState() == 2) {
            disconnectProjection(devId, 1024, 4);
        }
        for (CpwlIap2AuthRequest cpwlIap2AuthRequest : this.mCpwlIap2AuthList) {
            if (cpwlIap2AuthRequest.reason == 4 && cpwlIap2AuthRequest.btAddress.equals(mac)) {
                this.mCmsCallback.onDeviceCapabilityChecked(devId, 512, false, 4);
            }
        }
    }

    public void handlePowerModeChanged(int mode) {
        if (mode == 1) {
            synchronized (this.mPreConnProjections) {
                this.mPreConnProjections.clear();
            }
            boolean isExistWirelessCarplay = false;
            for (Map.Entry<String, ProjectionConnection> entry : this.mProjectionConnMap.entrySet()) {
                ProjectionConnection projectionConnection = entry.getValue();
                if (projectionConnection.getActiveCapability() == 512 && !projectionConnection.isDisconnected()) {
                    projectionConnection.deactivateConnection(1);
                    isExistWirelessCarplay = true;
                }
            }
            if (!isExistWirelessCarplay) {
                this.mDcController.enableWifiAp(false, null);
            }
        }
    }

    public void handleWifiApStateChanged(int state) {
        for (Map.Entry<String, ProjectionConnection> entry : this.mProjectionConnMap.entrySet()) {
            ProjectionConnection projectionConnection = entry.getValue();
            if (projectionConnection.getActiveCapability() == 512 || projectionConnection.getActiveCapability() == 1024) {
                projectionConnection.sendMessage(756, state);
            }
        }
    }

    public void handleBluetoothStateChanged(int state) {
        for (Map.Entry<String, ProjectionConnection> entry : this.mProjectionConnMap.entrySet()) {
            ProjectionConnection projectionConnection = entry.getValue();
            if (projectionConnection.getActiveCapability() == 512 || projectionConnection.getActiveCapability() == 1024) {
                projectionConnection.sendMessage(758, state);
            }
        }
    }

    public void handleBtProfileStateChanged(String mac, int profile, int state) {
        Device device = this.mDeviceManager.getDeviceByConntype(2, mac);
        if (device == null) {
            Log.d(TAG, "handleBtProfileStateChanged got bt-device is null.");
            return;
        }
        if ((profile == 1 || profile == 4) && state == 2) {
            for (Map.Entry<String, ProjectionConnection> entry : this.mProjectionConnMap.entrySet()) {
                ProjectionConnection projectionConnection = entry.getValue();
                Device dev = projectionConnection.getActiveDevice();
                if (device.equals(dev) && projectionConnection.isConnected() && projectionConnection.getActiveCapability() == 1024) {
                    return;
                }
            }
            Log.d(TAG, "Device: " + LogEncrypt.IpAddressEncrypt(device.getId()) + " Last service: 6");
            device.setLastService(6);
            this.mDeviceManager.saveDevice(device);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class PreProjectionConn {
        ProjectionLinkParameter mLinkParameter;
        boolean mNeedChecking;

        PreProjectionConn(ProjectionLinkParameter plp, boolean needChecking) {
            this.mLinkParameter = plp;
            this.mNeedChecking = needChecking;
        }
    }

    public class CpwlIap2AuthRequest {
        String btAddress;
        int reason;

        CpwlIap2AuthRequest(String btAddress, int reason) {
            this.btAddress = btAddress;
            this.reason = reason;
        }

        public String toString() {
            return "CpwlIap2AuthRequest{btAddress='" + LogEncrypt.MacAddressEncrypt(this.btAddress) + "', reason=" + this.reason + '}';
        }
    }
}
