package com.ts.car.dcm.dccontroller.projectionmanager;

import android.os.Looper;
import android.os.Message;
import android.support.v4.media.MediaPlayer2;
import android.text.TextUtils;
import android.util.Log;
import com.ts.car.dcm.common.data.DcmUtils;
import com.ts.car.dcm.common.data.LogEncrypt;
import com.ts.car.dcm.dccontroller.DcController;
import com.ts.car.dcm.dccontroller.configdata.ConfigManager;
import com.ts.car.dcm.dccontroller.connectionmanagerservice.CmsMessage;
import com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback;
import com.ts.car.dcm.dccontroller.storemanager.Device;
import com.ts.car.dcm.dccontroller.storemanager.DeviceManager;
import com.ts.car.dcm.utils.statemachine.State;
import com.ts.car.dcm.utils.statemachine.StateMachine;
import com.ts.car.dcm.wirelesscontroller.WirelessMonitorConstants;
import ts.car.dcm.common.data.ProjectionLinkParameter;
import ts.car.dcm.common.data.WifiApConfiguration;

/* JADX INFO: loaded from: classes.dex */
public class ProjectionConnection extends StateMachine {
    private static final int ENABLE_BLUETOOTH_TIMEOUT_MILLIS = 18000;
    private static final int ENABLE_WIFI_AP_TIMEOUT_MILLIS = 15000;
    static final int EVENT_AA_SWITCH = 707;
    static final int EVENT_ACTIVATE_SERVICE = 700;
    private static final int EVENT_BASE = 700;
    static final int EVENT_BLUETOOTH_STATE_CHANGED = 758;
    static final int EVENT_BT_PAIRING_STATE_CHANGED = 709;
    static final int EVENT_CARPLAY_WIRELESS_DETECTED = 708;
    static final int EVENT_CONNECT_PROJECTION_SERVICE_TIMEOUT = 750;
    static final int EVENT_DEACTIVATE_SERVICE = 701;
    static final int EVENT_DISCONNECT_PROJECTION_SERVICE_TIMEOUT = 751;
    static final int EVENT_ENABLE_BLUETOOTH_TIMEOUT = 757;
    static final int EVENT_ENABLE_WIFI_AP_TIMEOUT = 755;
    static final int EVENT_IAP_DISCONNECTED_FINISHED = 754;
    static final int EVENT_PAIRING_BLUETOOTH_TIMEOUT = 759;
    static final int EVENT_PROJECTION_CAPABILITY_CHANGED = 704;
    static final int EVENT_PROJECTION_CONNECT_TYPE_SWITCH_TIMEOUT = 752;
    static final int EVENT_PROJECTION_PRCONNECT_TIMEOUT = 753;
    static final int EVENT_RESET_USB_FINISHED = 710;
    static final int EVENT_SHOW_PROJECTION = 705;
    static final int EVENT_START_PRECONNECT = 706;
    static final int EVENT_UPDATE_CONNECTION_STATE = 702;
    static final int EVENT_UPDATE_FUNCTION_STATE = 703;
    static final int EVENT_WIFI_AP_STATE_CHANGED = 756;
    private static final int PAIRING_BLUETOOTH_TIMEOUT_MILLIS = 35000;
    private static final int PROJECTION_TYPE_SWITCH_TIME = 3000;
    private static String TAG = "ProjectionConnection";
    private int mActiveCapability;
    private Device mActiveDevice;
    private int mActiveService;
    private ActiveState mActiveState;
    private BluetoothEnablingState mBluetoothEnablingState;
    private BluetoothPairingState mBluetoothPairingState;
    private int mBtProfile;
    private CheckCpwlCapabilityState mCheckCpwlCapabilityState;
    ICmsCallback mCmsCallback;
    private ConfigManager mConfigManager;
    private ConnectedState mConnectedState;
    private ConnectingState mConnectingState;
    private ConnectionSwitchState mConnectionSwitchState;
    private DcController mDcController;
    private DeviceManager mDeviceManager;
    private int mDisconnectProjectionReason;
    private DisconnectedState mDisconnectedState;
    private DisconnectingState mDisconnectingState;
    private ProjectionLinkParameter mLinkParameter;
    private PreUsbReadyState mPreUsbReadyState;
    private ProjectionManager mProjectionManager;
    private ResetUsbState mResetUsbState;
    private boolean mSendBtProjectionOn;
    private int mSessionUpdateReason;
    private int mSwitchCapability;
    private Device mSwitchingDevice;
    private WifiApEnablingState mWifiApEnablingState;

    public ProjectionConnection(String name, ProjectionManager mgr, Looper looper, ICmsCallback cb, DcController dcc) {
        super(name, looper);
        this.mActiveDevice = null;
        this.mActiveService = 0;
        this.mActiveCapability = 0;
        this.mSendBtProjectionOn = false;
        this.mSessionUpdateReason = 0;
        this.mDisconnectProjectionReason = 0;
        this.mWifiApEnablingState = new WifiApEnablingState();
        this.mBluetoothEnablingState = new BluetoothEnablingState();
        this.mPreUsbReadyState = new PreUsbReadyState();
        this.mBluetoothPairingState = new BluetoothPairingState();
        this.mCheckCpwlCapabilityState = new CheckCpwlCapabilityState();
        this.mActiveState = new ActiveState();
        this.mConnectingState = new ConnectingState();
        this.mConnectedState = new ConnectedState();
        this.mDisconnectingState = new DisconnectingState();
        this.mResetUsbState = new ResetUsbState();
        this.mDisconnectedState = new DisconnectedState();
        this.mConnectionSwitchState = new ConnectionSwitchState();
        this.mProjectionManager = mgr;
        this.mCmsCallback = cb;
        this.mDcController = dcc;
        this.mConfigManager = dcc.getConfigManager();
        this.mDeviceManager = this.mDcController.getDeviceManager();
        addState(this.mDisconnectedState);
        addState(this.mWifiApEnablingState, this.mDisconnectedState);
        addState(this.mBluetoothEnablingState, this.mDisconnectedState);
        addState(this.mPreUsbReadyState, this.mDisconnectedState);
        addState(this.mBluetoothPairingState, this.mDisconnectedState);
        addState(this.mCheckCpwlCapabilityState, this.mDisconnectedState);
        addState(this.mActiveState, this.mDisconnectedState);
        addState(this.mConnectingState, this.mDisconnectedState);
        addState(this.mConnectedState, this.mDisconnectedState);
        addState(this.mDisconnectingState, this.mDisconnectedState);
        addState(this.mResetUsbState, this.mDisconnectedState);
        addState(this.mConnectionSwitchState, this.mDisconnectedState);
        setInitialState(this.mDisconnectedState);
        start();
    }

    public int activateConnection(Device device, ProjectionLinkParameter plp, boolean needDetecting) {
        Log.d(TAG, "activateConnection, plp:" + plp + ", needDetecting:" + needDetecting);
        int service = DcmUtils.getSrvTypeByCapability(plp.getCapability());
        this.mLinkParameter = plp;
        plp.setFirst(device.isProjectionFirstConnecting(service));
        int activeCapability = this.mLinkParameter.getCapability();
        if (this.mLinkParameter.getFromUser()) {
            this.mSessionUpdateReason = 17;
        } else {
            this.mSessionUpdateReason = 18;
        }
        this.mDisconnectProjectionReason = 0;
        Log.e(TAG, "activateConnection, mSessionUpdateReason:" + this.mSessionUpdateReason);
        if ((activeCapability & 1024) > 0) {
            sendMessage(EVENT_START_PRECONNECT, service, activeCapability, device);
            Log.d(TAG, "activateConnection, send EVENT_START_PRECONNECT message.");
            return 0;
        }
        if (!needDetecting) {
            if (!device.isAvailableCapability(activeCapability)) {
                return 5;
            }
            if (getCurrentState() != this.mDisconnectedState && (getCurrentState() != this.mBluetoothPairingState || getCurrentState() != this.mCheckCpwlCapabilityState)) {
                Log.e(TAG, "activateConnection, failed. current state:" + getCurrentState());
                return 2;
            }
            sendMessage(MediaPlayer2.MEDIA_INFO_VIDEO_TRACK_LAGGING, service, activeCapability, device);
            Log.d(TAG, "activateConnection, send EVENT_ACTIVATE_SERVICE message.");
        } else if ((activeCapability & 512) > 0) {
            sendMessage(EVENT_START_PRECONNECT, service, activeCapability, device);
            Log.d(TAG, "activateConnection, send EVENT_START_PRECONNECT message.");
        }
        return 0;
    }

    public boolean deactivateConnection(int reason) {
        Log.d(TAG, "deactivateConnection, devId:" + LogEncrypt.IpAddressEncrypt(this.mActiveDevice.getId()) + ", reason:" + reason);
        if (getCurrentState() != this.mDisconnectedState) {
            if (this.mDisconnectProjectionReason != 1) {
                this.mDisconnectProjectionReason = reason;
            }
            if (this.mDisconnectProjectionReason == 3) {
                this.mSessionUpdateReason = 16;
                Log.e(TAG, "deactivateConnection, click disconnect in devicelist");
            }
            if (this.mActiveDevice.getDeviceConnectedState() == 2) {
                this.mDisconnectProjectionReason = 3;
                this.mSessionUpdateReason = 16;
                Log.e(TAG, "deactivateConnection, click delete in devicelist");
            }
            sendMessage(701);
            return true;
        }
        Log.e(TAG, "deactivateConnection, current state is mDisconnectedState.");
        return false;
    }

    Device getActiveDevice() {
        return this.mActiveDevice;
    }

    void setActiveDevice(Device dev) {
        this.mActiveDevice = dev;
    }

    int getActiveService() {
        return this.mActiveService;
    }

    int getActiveCapability() {
        return this.mActiveCapability;
    }

    void setActiveCapability(int capa) {
        this.mActiveCapability = capa;
    }

    public Device getSwitchDevice() {
        return this.mSwitchingDevice;
    }

    public ProjectionLinkParameter getProjectionLinkParameter() {
        return this.mLinkParameter;
    }

    public boolean isConnected() {
        return getCurrentState() == this.mConnectedState;
    }

    public boolean isConnecting() {
        return getCurrentState() == this.mConnectingState;
    }

    public boolean isWifiApEnablingState() {
        return getCurrentState() == this.mWifiApEnablingState;
    }

    public boolean isBluetoothEnablingState() {
        return getCurrentState() == this.mBluetoothEnablingState;
    }

    public boolean isDisconnecting() {
        return getCurrentState() == this.mDisconnectingState || getCurrentState() == this.mResetUsbState;
    }

    public boolean isDisconnected() {
        return getCurrentState() == this.mDisconnectedState;
    }

    public boolean isBluetoothPairingState() {
        return getCurrentState() == this.mBluetoothPairingState;
    }

    public boolean isCheckCpwlCapabilityState() {
        return getCurrentState() == this.mCheckCpwlCapabilityState;
    }

    public boolean isPreUsbReadyState() {
        return getCurrentState() == this.mPreUsbReadyState;
    }

    public boolean isProjectionStateNotify() {
        return this.mSendBtProjectionOn;
    }

    public int getProjectionDisconnectReason() {
        return this.mDisconnectProjectionReason;
    }

    public void setProjectionStateNotify(boolean flag) {
        this.mSendBtProjectionOn = flag;
    }

    public void updateBtProfile(int profile) {
        this.mBtProfile = profile;
        Log.d(TAG, "updateBtProfile, save profile: " + this.mBtProfile);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAuthenticationFailedEvent(int code) {
        Device device = this.mActiveDevice;
        if (device != null && code == 1 && device.getDeviceType() == 2) {
            Log.d(TAG, "handleAuthenticationFailedEvent:" + this.mActiveCapability);
            this.mActiveDevice.removeActiveCapability(this.mActiveCapability);
            this.mDcController.notifyDeviceCapabilityChanged(this.mActiveDevice);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleProjectionCapabilityChanged(int availableCapa, int lastAvailableCapa) {
        int matchCapa;
        Log.d(TAG, "handleProjectionCapabilityChanged, availableCapa:" + availableCapa + ", lastAvailableCapa:" + lastAvailableCapa);
        int matchCapa2 = this.mActiveCapability;
        if (matchCapa2 == 8) {
            if ((matchCapa2 & availableCapa) == 0) {
                this.mProjectionManager.releaseProjectionConnection(this.mActiveDevice.getId(), 8, 6);
                return;
            }
            return;
        }
        if (matchCapa2 == 64) {
            matchCapa = 512;
        } else if (matchCapa2 == 128) {
            matchCapa = 1024;
        } else if (matchCapa2 == 256) {
            matchCapa = 2048;
        } else if (matchCapa2 == 512) {
            matchCapa = 64;
        } else if (matchCapa2 == 1024) {
            matchCapa = 128;
        } else if (matchCapa2 == 2048) {
            matchCapa = 256;
        } else {
            Log.e(TAG, "handleProjectionCapabilityChanged, wrong mActiveCapability:" + this.mActiveCapability);
            return;
        }
        if ((this.mActiveCapability & availableCapa) == 0 && (availableCapa & matchCapa) == 0) {
            Log.d(TAG, "handleProjectionCapabilityChanged, releaseProjectionConnection. devId:" + LogEncrypt.IpAddressEncrypt(this.mActiveDevice.getId()));
            return;
        }
        if ((this.mActiveCapability & availableCapa) == 0 && (availableCapa & matchCapa) > 0) {
            if (this.mConfigManager.isProjectionHandover()) {
                Log.d(TAG, "handleProjectionCapabilityChanged, switchProjectionConnection. devId:" + LogEncrypt.IpAddressEncrypt(this.mActiveDevice.getId()) + ", old capa:" + this.mActiveCapability + ", new capa:" + matchCapa);
                Device device = this.mActiveDevice;
                this.mSwitchingDevice = device;
                this.mSwitchCapability = matchCapa;
                this.mCmsCallback.onSwitchProjectionConnection(device, matchCapa);
                transitionTo(this.mConnectionSwitchState);
                return;
            }
            Log.d(TAG, "handleProjectionCapabilityChanged, not switch projection, releaseProjectionConnection. devId:" + LogEncrypt.IpAddressEncrypt(this.mActiveDevice.getId()));
            return;
        }
        if ((availableCapa & matchCapa) > 0 && (lastAvailableCapa & matchCapa) == 0) {
            if (this.mActiveService != 1 || getCurrentState() == this.mConnectedState) {
                this.mCmsCallback.onDeviceConnectionUpdated(this.mActiveDevice, this.mActiveCapability, 1, this.mActiveService);
                return;
            }
            Log.d(TAG, "handleProjectionCapabilityChanged, waiting carplay projection connected finished, statue:" + getCurrentState());
            return;
        }
        if ((availableCapa & matchCapa) == 0 && (lastAvailableCapa & matchCapa) > 0) {
            if (this.mActiveService != 1 || getCurrentState() == this.mConnectedState) {
                this.mCmsCallback.onDeviceConnectionUpdated(this.mActiveDevice, this.mActiveCapability, 0, this.mActiveService);
            }
        }
    }

    public void removeMessageHandler(int what) {
        Log.d(TAG, "removeMessageHandler, what:" + what);
        removeMessages(what);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleProjectionDisconnected(int disconnectedReason) {
        this.mActiveDevice.updateActiveCapaState(this.mActiveCapability, 1);
        this.mActiveDevice.removeActiveCapability(this.mActiveCapability);
        this.mCmsCallback.onDeviceStatusChanged(this.mActiveDevice);
        if (this.mActiveService != 4) {
            if (this.mActiveCapability == 512 && !this.mActiveDevice.hasConnection(2)) {
                Log.d(TAG, "DisconnectedState handleProjectionDisconnected, remove the carplay wireless.");
                this.mActiveDevice.removeAvailableCapability(512);
            }
            if (this.mConfigManager.enableProjectionNotify()) {
                if (isProjectionStateNotify()) {
                    this.mProjectionManager.notifyBtProjectionState(false, this.mActiveDevice.getBtAddr(), this.mActiveCapability);
                    Log.d(TAG, "DisconnectedState handleProjectionDisconnected, projectionOff calling.");
                    setProjectionStateNotify(false);
                }
            } else if (this.mBtProfile != 0) {
                this.mDcController.connectBtProfile(this.mActiveDevice.getId(), this.mActiveDevice.getBtAddr(), this.mBtProfile);
                Log.d(TAG, "DisconnectedState handleProjectionDisconnected, resume profile:" + this.mBtProfile);
            }
        }
        this.mCmsCallback.onProjectionConnectionChanged(this.mActiveDevice, this.mActiveCapability, 1, this.mSessionUpdateReason);
        this.mDeviceManager.enableSaveDatabase(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void triggerProjection() {
        if (this.mCmsCallback.onConnectProjection(this.mActiveDevice, this.mLinkParameter)) {
            sendMessageDelayed(EVENT_CONNECT_PROJECTION_SERVICE_TIMEOUT, 10000L);
            this.mActiveDevice.updateActiveCapaState(this.mActiveCapability, 3);
            this.mCmsCallback.onDeviceStatusChanged(this.mActiveDevice);
            this.mCmsCallback.onProjectionConnectionChanged(this.mActiveDevice, this.mActiveCapability, 3, this.mSessionUpdateReason);
            transitionTo(this.mActiveState);
            return;
        }
        this.mSessionUpdateReason = 3;
        this.mDisconnectProjectionReason = 7;
        transitionTo(this.mDisconnectedState);
    }

    private class DisconnectedState extends State {
        private DisconnectedState() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void enter() {
            Log.e(ProjectionConnection.TAG, "enter DisconnectedState.");
            if (ProjectionConnection.this.mActiveDevice != null) {
                ProjectionConnection projectionConnection = ProjectionConnection.this;
                projectionConnection.handleProjectionDisconnected(projectionConnection.mSessionUpdateReason);
                ProjectionConnection.this.mProjectionManager.onProjectionDisconnected(ProjectionConnection.this.mActiveDevice.getId(), ProjectionConnection.this.mDisconnectProjectionReason);
                ProjectionConnection.this.mActiveDevice.setDisconnectByUserAction(false);
                ProjectionConnection.this.mActiveDevice = null;
                ProjectionConnection.this.mActiveService = 0;
                ProjectionConnection.this.mActiveCapability = 0;
                ProjectionConnection.this.mSwitchingDevice = null;
                ProjectionConnection.this.mSwitchCapability = 0;
                ProjectionConnection.this.mSessionUpdateReason = 0;
            }
            ProjectionConnection.this.removeMessages(ProjectionConnection.EVENT_ENABLE_WIFI_AP_TIMEOUT);
            ProjectionConnection.this.removeMessages(ProjectionConnection.EVENT_ENABLE_BLUETOOTH_TIMEOUT);
            ProjectionConnection.this.removeMessages(ProjectionConnection.EVENT_PAIRING_BLUETOOTH_TIMEOUT);
            ProjectionConnection.this.removeMessages(ProjectionConnection.EVENT_PROJECTION_PRCONNECT_TIMEOUT);
            ProjectionConnection.this.removeMessages(ProjectionConnection.EVENT_CONNECT_PROJECTION_SERVICE_TIMEOUT);
            ProjectionConnection.this.removeMessages(ProjectionConnection.EVENT_DISCONNECT_PROJECTION_SERVICE_TIMEOUT);
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            Log.e(ProjectionConnection.TAG, "DisconnectedState msg:" + msg.what);
            int i = msg.what;
            if (i == 700) {
                Log.d(ProjectionConnection.TAG, "DisconnectedState EVENT_ACTIVATE_SERVICE");
                ProjectionConnection.this.mActiveDevice = (Device) msg.obj;
                ProjectionConnection.this.mActiveService = msg.arg1;
                ProjectionConnection.this.mConfigManager.setHppService(ProjectionConnection.this.mActiveService);
                ProjectionConnection.this.mActiveCapability = msg.arg2;
                ProjectionConnection.this.mActiveDevice.addActiveCapability(ProjectionConnection.this.mActiveCapability);
                if ((ProjectionConnection.this.mActiveCapability & 448) <= 0 || (!ProjectionConnection.this.mActiveDevice.isInUsbDeviceMode() && !ProjectionConnection.this.mActiveDevice.isInUsbAoaMode())) {
                    ProjectionConnection.this.triggerProjection();
                } else {
                    ProjectionConnection projectionConnection = ProjectionConnection.this;
                    projectionConnection.transitionTo(projectionConnection.mPreUsbReadyState);
                }
            } else if (i != ProjectionConnection.EVENT_START_PRECONNECT) {
                Log.d(ProjectionConnection.TAG, "DisconnectedState IdleState ignore msg.what:" + msg.what);
            } else {
                Log.d(ProjectionConnection.TAG, "DisconnectedState EVENT_START_PRECONNECT");
                ProjectionConnection.this.mActiveDevice = (Device) msg.obj;
                ProjectionConnection.this.mActiveService = msg.arg1;
                ProjectionConnection.this.mActiveCapability = msg.arg2;
                ProjectionConnection.this.mActiveDevice.updateActiveCapaState(ProjectionConnection.this.mActiveCapability, 3);
                ProjectionConnection.this.mCmsCallback.onDeviceStatusChanged(ProjectionConnection.this.mActiveDevice);
                ProjectionConnection.this.mCmsCallback.onProjectionConnectionChanged(ProjectionConnection.this.mActiveDevice, ProjectionConnection.this.mActiveCapability, 3, ProjectionConnection.this.mSessionUpdateReason);
                String btAddress = ProjectionConnection.this.mActiveDevice.getBtAddr();
                if (btAddress == null || btAddress.isEmpty()) {
                    Log.e(ProjectionConnection.TAG, "DisconnectedState bt address is null.");
                    ProjectionConnection.this.mSessionUpdateReason = 13;
                    ProjectionConnection.this.mDisconnectProjectionReason = 7;
                    ProjectionConnection projectionConnection2 = ProjectionConnection.this;
                    projectionConnection2.transitionTo(projectionConnection2.mDisconnectedState);
                } else if (ProjectionConnection.this.mActiveService == 3) {
                    Log.i(ProjectionConnection.TAG, "DisconnectedState triggerProjection directly.");
                    ProjectionConnection.this.triggerProjection();
                } else {
                    Log.i(ProjectionConnection.TAG, "DisconnectedState mActiveCapability:" + ProjectionConnection.this.mActiveCapability);
                    if (ProjectionConnection.this.mDcController.isBluetoothEnabled()) {
                        if (ProjectionConnection.this.mActiveDevice.isPaired()) {
                            if (ProjectionConnection.this.mLinkParameter.isCapabilityAlreadyChecked()) {
                                ProjectionConnection.this.triggerProjection();
                            } else {
                                ProjectionConnection projectionConnection3 = ProjectionConnection.this;
                                projectionConnection3.transitionTo(projectionConnection3.mCheckCpwlCapabilityState);
                            }
                        } else {
                            ProjectionConnection projectionConnection4 = ProjectionConnection.this;
                            projectionConnection4.transitionTo(projectionConnection4.mBluetoothPairingState);
                        }
                    } else {
                        ProjectionConnection projectionConnection5 = ProjectionConnection.this;
                        projectionConnection5.transitionTo(projectionConnection5.mBluetoothEnablingState);
                    }
                }
            }
            return true;
        }
    }

    private class WifiApEnablingState extends State {
        private WifiApEnablingState() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void enter() {
            Log.e(ProjectionConnection.TAG, "enter WifiApEnablingState service:" + ProjectionConnection.this.mActiveService + ", device:" + ProjectionConnection.this.mActiveDevice.getFriendlyName());
            boolean isSuccess = false;
            int wantedband = -1;
            int wantedChannel = 0;
            if (ProjectionConnection.this.mActiveService == 1) {
                wantedband = 1;
                if (ProjectionConnection.this.mConfigManager.getWifiApBandForCarplay() == 2) {
                    wantedband = 2;
                }
                wantedChannel = ProjectionConnection.this.mConfigManager.getWifiApChannel(wantedband, 1);
            }
            if (ProjectionConnection.this.mActiveService == 3) {
                wantedband = 2;
                wantedChannel = ProjectionConnection.this.mConfigManager.getWifiApChannel(2, 3);
            }
            WifiApConfiguration config = new WifiApConfiguration(wantedband, wantedChannel);
            if (ProjectionConnection.this.mDcController.enableWifiAp(true, config)) {
                isSuccess = true;
                ProjectionConnection.this.sendMessageDelayed(ProjectionConnection.EVENT_ENABLE_WIFI_AP_TIMEOUT, WirelessMonitorConstants.DISCOVERY_DEVICE_TIME);
            }
            if (!isSuccess) {
                Log.e(ProjectionConnection.TAG, "WifiApEnablingState setup wifi ap failed.");
                ProjectionConnection.this.mSessionUpdateReason = 3;
                ProjectionConnection.this.mDisconnectProjectionReason = 7;
                ProjectionConnection projectionConnection = ProjectionConnection.this;
                projectionConnection.transitionTo(projectionConnection.mDisconnectedState);
            }
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            Log.e(ProjectionConnection.TAG, "WifiApEnablingState msg:" + msg.what);
            int i = msg.what;
            if (i == 701) {
                Log.d(ProjectionConnection.TAG, "WifiApEnablingState EVENT_DEACTIVATE_SERVICE");
                ProjectionConnection projectionConnection = ProjectionConnection.this;
                projectionConnection.transitionTo(projectionConnection.mDisconnectedState);
            } else if (i == ProjectionConnection.EVENT_ENABLE_WIFI_AP_TIMEOUT) {
                Log.d(ProjectionConnection.TAG, "WifiApEnablingState EVENT_ENABLE_WIFI_AP_TIMEOUT");
                Log.e(ProjectionConnection.TAG, "WifiApEnablingState setup wifi ap timeout.");
                ProjectionConnection.this.mSessionUpdateReason = 3;
                ProjectionConnection.this.mDisconnectProjectionReason = 7;
                ProjectionConnection projectionConnection2 = ProjectionConnection.this;
                projectionConnection2.transitionTo(projectionConnection2.mDisconnectedState);
            } else if (i != ProjectionConnection.EVENT_WIFI_AP_STATE_CHANGED) {
                Log.d(ProjectionConnection.TAG, "WifiApEnablingState ignore msg.what:" + msg.what);
            } else {
                Log.d(ProjectionConnection.TAG, "WifiApEnablingState EVENT_WIFI_AP_STATE_CHANGED");
                ProjectionConnection.this.removeMessages(ProjectionConnection.EVENT_ENABLE_WIFI_AP_TIMEOUT);
                if (msg.arg1 == 13) {
                    if (ProjectionConnection.this.mActiveCapability == 1024) {
                        if (ProjectionConnection.this.mDcController.isBluetoothEnabled()) {
                            ProjectionConnection.this.triggerProjection();
                        } else {
                            ProjectionConnection projectionConnection3 = ProjectionConnection.this;
                            projectionConnection3.transitionTo(projectionConnection3.mBluetoothEnablingState);
                        }
                    }
                    if (ProjectionConnection.this.mActiveCapability == 512) {
                        if (ProjectionConnection.this.mDcController.isBluetoothEnabled()) {
                            if (ProjectionConnection.this.mActiveDevice.isPaired()) {
                                if (ProjectionConnection.this.mLinkParameter.isCapabilityAlreadyChecked()) {
                                    ProjectionConnection.this.triggerProjection();
                                } else {
                                    ProjectionConnection projectionConnection4 = ProjectionConnection.this;
                                    projectionConnection4.transitionTo(projectionConnection4.mCheckCpwlCapabilityState);
                                }
                            } else {
                                ProjectionConnection projectionConnection5 = ProjectionConnection.this;
                                projectionConnection5.transitionTo(projectionConnection5.mBluetoothPairingState);
                            }
                        } else {
                            ProjectionConnection projectionConnection6 = ProjectionConnection.this;
                            projectionConnection6.transitionTo(projectionConnection6.mBluetoothEnablingState);
                        }
                    }
                }
                if (msg.arg1 == 14) {
                    ProjectionConnection.this.mSessionUpdateReason = 3;
                    ProjectionConnection.this.mDisconnectProjectionReason = 7;
                    ProjectionConnection projectionConnection7 = ProjectionConnection.this;
                    projectionConnection7.transitionTo(projectionConnection7.mDisconnectedState);
                }
            }
            return true;
        }
    }

    private class BluetoothEnablingState extends State {
        private BluetoothEnablingState() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void enter() {
            Log.e(ProjectionConnection.TAG, "enter BluetoothEnablingState service:" + ProjectionConnection.this.mActiveService + ", device:" + ProjectionConnection.this.mActiveDevice.getFriendlyName());
            if (!ProjectionConnection.this.mDcController.enableBluetooth(true)) {
                Log.d(ProjectionConnection.TAG, "BluetoothEnablingState, enable Bluetooth failed.");
                ProjectionConnection.this.mSessionUpdateReason = 3;
                ProjectionConnection.this.mDisconnectProjectionReason = 7;
                ProjectionConnection projectionConnection = ProjectionConnection.this;
                projectionConnection.transitionTo(projectionConnection.mDisconnectedState);
                return;
            }
            ProjectionConnection.this.sendMessageDelayed(ProjectionConnection.EVENT_ENABLE_BLUETOOTH_TIMEOUT, 18000L);
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            Log.e(ProjectionConnection.TAG, "BluetoothEnablingState msg:" + msg.what);
            int i = msg.what;
            if (i == 701) {
                Log.d(ProjectionConnection.TAG, "BluetoothEnablingState EVENT_DEACTIVATE_SERVICE");
                ProjectionConnection projectionConnection = ProjectionConnection.this;
                projectionConnection.transitionTo(projectionConnection.mDisconnectedState);
            } else if (i == ProjectionConnection.EVENT_ENABLE_BLUETOOTH_TIMEOUT) {
                Log.d(ProjectionConnection.TAG, "BluetoothEnablingState EVENT_ENABLE_BLUETOOTH_TIMEOUT");
                Log.d(ProjectionConnection.TAG, "BluetoothEnablingState enable bluetooth time out!");
                ProjectionConnection.this.mSessionUpdateReason = 3;
                ProjectionConnection.this.mDisconnectProjectionReason = 7;
                ProjectionConnection projectionConnection2 = ProjectionConnection.this;
                projectionConnection2.transitionTo(projectionConnection2.mDisconnectedState);
            } else if (i != ProjectionConnection.EVENT_BLUETOOTH_STATE_CHANGED) {
                Log.d(ProjectionConnection.TAG, "BluetoothEnablingState ignore msg.what:" + msg.what);
            } else {
                Log.d(ProjectionConnection.TAG, "BluetoothEnablingState EVENT_BLUETOOTH_STATE_CHANGED");
                ProjectionConnection.this.removeMessages(ProjectionConnection.EVENT_ENABLE_BLUETOOTH_TIMEOUT);
                if (msg.arg1 == 12) {
                    if (ProjectionConnection.this.mActiveCapability == 512) {
                        if (ProjectionConnection.this.mActiveDevice.isPaired()) {
                            if (ProjectionConnection.this.mLinkParameter.isCapabilityAlreadyChecked()) {
                                ProjectionConnection.this.triggerProjection();
                            } else {
                                ProjectionConnection projectionConnection3 = ProjectionConnection.this;
                                projectionConnection3.transitionTo(projectionConnection3.mCheckCpwlCapabilityState);
                            }
                        } else {
                            ProjectionConnection projectionConnection4 = ProjectionConnection.this;
                            projectionConnection4.transitionTo(projectionConnection4.mBluetoothPairingState);
                        }
                    }
                    if (ProjectionConnection.this.mActiveCapability == 1024) {
                        ProjectionConnection.this.triggerProjection();
                    }
                }
            }
            return true;
        }
    }

    private class PreUsbReadyState extends State {
        private PreUsbReadyState() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void enter() {
            Log.d(ProjectionConnection.TAG, "enter PreUsbReadyState, service:" + ProjectionConnection.this.mActiveService + ", device:" + ProjectionConnection.this.mActiveDevice);
            if (ProjectionConnection.this.mActiveDevice.getUsbRoleSwitchPurpose() == 3) {
                if (ProjectionConnection.this.mActiveCapability == 64) {
                    ProjectionConnection.this.mActiveDevice.setUsbRoleSwitchPurpose(1);
                    ProjectionConnection.this.triggerProjection();
                    return;
                } else {
                    if (ProjectionConnection.this.mActiveCapability == 256 && !ProjectionConnection.this.mCmsCallback.onDisconnectIapSession(ProjectionConnection.this.mActiveDevice.getId(), 1)) {
                        ProjectionConnection.this.mActiveDevice.setUsbRoleSwitchPurpose(0);
                        ProjectionConnection.this.mSessionUpdateReason = 3;
                        ProjectionConnection.this.mDisconnectProjectionReason = 7;
                        ProjectionConnection projectionConnection = ProjectionConnection.this;
                        projectionConnection.transitionTo(projectionConnection.mDisconnectedState);
                        return;
                    }
                    return;
                }
            }
            if (!ProjectionConnection.this.mDcController.resetUsb(ProjectionConnection.this.mActiveDevice.getId())) {
                ProjectionConnection.this.mSessionUpdateReason = 3;
                ProjectionConnection.this.mDisconnectProjectionReason = 7;
                ProjectionConnection projectionConnection2 = ProjectionConnection.this;
                projectionConnection2.transitionTo(projectionConnection2.mDisconnectedState);
            }
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            Log.d(ProjectionConnection.TAG, "PreUsbReadyState msg:" + msg.what);
            int i = msg.what;
            if (i == 701) {
                Log.d(ProjectionConnection.TAG, "PreUsbReadyState EVENT_DEACTIVATE_SERVICE");
                ProjectionConnection projectionConnection = ProjectionConnection.this;
                projectionConnection.transitionTo(projectionConnection.mDisconnectedState);
            } else if (i == ProjectionConnection.EVENT_RESET_USB_FINISHED) {
                Log.d(ProjectionConnection.TAG, "PreUsbReadyState EVENT_RESET_USB_FINISHED");
                Log.d(ProjectionConnection.TAG, "PreUsbReadyState reset usb finished. aoa:" + ProjectionConnection.this.mActiveDevice.isInUsbAoaMode() + ", device mode:" + ProjectionConnection.this.mActiveDevice.isInUsbDeviceMode() + ", result:" + msg.arg1);
                ProjectionConnection.this.triggerProjection();
            } else if (i != ProjectionConnection.EVENT_IAP_DISCONNECTED_FINISHED) {
                Log.d(ProjectionConnection.TAG, " PreUsbReadyState ignore msg.what:" + msg.what);
            } else {
                Log.d(ProjectionConnection.TAG, "PreUsbReadyState EVENT_IAP_DISCONNECTED_FINISHED");
                if (!ProjectionConnection.this.mActiveDevice.isInUsbDeviceMode()) {
                    ProjectionConnection.this.triggerProjection();
                } else if (!ProjectionConnection.this.mDcController.resetUsb(ProjectionConnection.this.mActiveDevice.getId())) {
                    ProjectionConnection.this.mSessionUpdateReason = 3;
                    ProjectionConnection.this.mDisconnectProjectionReason = 7;
                    ProjectionConnection projectionConnection2 = ProjectionConnection.this;
                    projectionConnection2.transitionTo(projectionConnection2.mDisconnectedState);
                }
            }
            return true;
        }
    }

    private class BluetoothPairingState extends State {
        private BluetoothPairingState() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void enter() {
            Log.i(ProjectionConnection.TAG, "enter BluetoothPairingState service:" + ProjectionConnection.this.mActiveService + ", device:" + ProjectionConnection.this.mActiveDevice);
            if (!ProjectionConnection.this.mDcController.requestBluetoothPairing(ProjectionConnection.this.mActiveDevice.getBtAddr())) {
                Log.d(ProjectionConnection.TAG, "BluetoothPairingState paired failed, mac:" + ProjectionConnection.this.mActiveDevice.getBtAddr());
                ProjectionConnection.this.mSessionUpdateReason = 10;
                ProjectionConnection.this.mDisconnectProjectionReason = 7;
                ProjectionConnection projectionConnection = ProjectionConnection.this;
                projectionConnection.transitionTo(projectionConnection.mDisconnectedState);
                return;
            }
            ProjectionConnection.this.sendMessageDelayed(ProjectionConnection.EVENT_PAIRING_BLUETOOTH_TIMEOUT, 35000L);
            Log.d(ProjectionConnection.TAG, "BluetoothPairingState request pairing success, mac:" + ProjectionConnection.this.mActiveDevice.getBtAddr());
        }

        private void handleBtPairingStateChanged(Message msg) {
            int status = msg.arg1;
            if (status == 10) {
                ProjectionConnection.this.mSessionUpdateReason = 10;
                ProjectionConnection.this.mDisconnectProjectionReason = 7;
                Log.e(ProjectionConnection.TAG, "BluetoothPairingState handleBtPairingStateChanged, failed, transitionTo disconnected state.");
                ProjectionConnection projectionConnection = ProjectionConnection.this;
                projectionConnection.transitionTo(projectionConnection.mDisconnectedState);
                return;
            }
            if (status == 11) {
                ProjectionConnection.this.mCmsCallback.onProjectionConnectionChanged(ProjectionConnection.this.mActiveDevice, ProjectionConnection.this.mActiveCapability, 3, ProjectionConnection.this.mSessionUpdateReason);
                return;
            }
            if (status != 12) {
                Log.e(ProjectionConnection.TAG, "BluetoothPairingState handleBtPairingStateChanged, unknown bt bond State:" + status);
                return;
            }
            ProjectionConnection.this.mCmsCallback.onProjectionConnectionChanged(ProjectionConnection.this.mActiveDevice, ProjectionConnection.this.mActiveCapability, 3, ProjectionConnection.this.mSessionUpdateReason);
            if (ProjectionConnection.this.mLinkParameter.isCapabilityAlreadyChecked()) {
                ProjectionConnection.this.triggerProjection();
            } else {
                ProjectionConnection projectionConnection2 = ProjectionConnection.this;
                projectionConnection2.transitionTo(projectionConnection2.mCheckCpwlCapabilityState);
            }
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            Log.e(ProjectionConnection.TAG, "BluetoothPairingState msg:" + msg.what);
            int i = msg.what;
            if (i == 701) {
                Log.d(ProjectionConnection.TAG, "BluetoothPairingState EVENT_DEACTIVATE_SERVICE");
                ProjectionConnection projectionConnection = ProjectionConnection.this;
                projectionConnection.transitionTo(projectionConnection.mDisconnectedState);
            } else if (i == ProjectionConnection.EVENT_BT_PAIRING_STATE_CHANGED) {
                Log.d(ProjectionConnection.TAG, "BluetoothPairingState EVENT_BT_PAIRING_STATE_CHANGED");
                handleBtPairingStateChanged(msg);
            } else if (i == ProjectionConnection.EVENT_CONNECT_PROJECTION_SERVICE_TIMEOUT) {
                String deviceId = ProjectionConnection.TAG;
                Log.d(deviceId, "BluetoothPairingState EVENT_CONNECT_PROJECTION_SERVICE_TIMEOUT");
                ProjectionConnection.this.mSessionUpdateReason = 4;
                ProjectionConnection.this.mDisconnectProjectionReason = 7;
                ProjectionConnection projectionConnection2 = ProjectionConnection.this;
                projectionConnection2.transitionTo(projectionConnection2.mDisconnectedState);
                Log.i(ProjectionConnection.TAG, "BluetoothPairingState Projection Service start timeout. dev:" + LogEncrypt.IpAddressEncrypt(ProjectionConnection.this.mActiveDevice.getId()));
            } else if (i != ProjectionConnection.EVENT_PAIRING_BLUETOOTH_TIMEOUT) {
                Log.d(ProjectionConnection.TAG, "BluetoothPairingState ignore msg.what:" + msg.what);
            } else {
                Log.d(ProjectionConnection.TAG, "BluetoothPairingState EVENT_PAIRING_BLUETOOTH_TIMEOUT");
                ProjectionConnection.this.mSessionUpdateReason = 10;
                ProjectionConnection.this.mDisconnectProjectionReason = 7;
                String deviceId2 = (String) msg.obj;
                ProjectionConnection projectionConnection3 = ProjectionConnection.this;
                projectionConnection3.transitionTo(projectionConnection3.mDisconnectedState);
                Log.i(ProjectionConnection.TAG, "BluetoothPairingState bluetooth pairing connect timeout. dev:" + LogEncrypt.IpAddressEncrypt(deviceId2));
            }
            return true;
        }
    }

    private class CheckCpwlCapabilityState extends State {
        private CheckCpwlCapabilityState() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void enter() {
            Log.i(ProjectionConnection.TAG, "enter CheckCpwlCapabilityState service:" + ProjectionConnection.this.mActiveService + ", device:" + ProjectionConnection.this.mActiveDevice);
            if ((ProjectionConnection.this.mActiveCapability & 512) > 0) {
                Log.e(ProjectionConnection.TAG, "CheckCpwlCapabilityState start wireless carplay checking by iap2");
                if (!ProjectionConnection.this.mProjectionManager.checkWirelessCarplayCapability(ProjectionConnection.this.mActiveDevice.getId(), 2)) {
                    ProjectionConnection.this.mSessionUpdateReason = 14;
                    ProjectionConnection.this.mDisconnectProjectionReason = 7;
                    Log.e(ProjectionConnection.TAG, "CheckCpwlCapabilityState failed to trigger iAP2 Authentication,transitionTo disconnected state.");
                    ProjectionConnection projectionConnection = ProjectionConnection.this;
                    projectionConnection.transitionTo(projectionConnection.mDisconnectedState);
                    return;
                }
                ProjectionConnection projectionConnection2 = ProjectionConnection.this;
                projectionConnection2.sendMessageDelayed(ProjectionConnection.EVENT_PROJECTION_PRCONNECT_TIMEOUT, projectionConnection2.mActiveDevice.getId(), 20000L);
            }
        }

        private void handleBtPairingStateChanged(Message msg) {
            int status = msg.arg1;
            if (status == 10) {
                ProjectionConnection.this.mSessionUpdateReason = 10;
                ProjectionConnection.this.mDisconnectProjectionReason = 7;
                Log.e(ProjectionConnection.TAG, "CheckCpwlCapabilityState handleBtPairingStateChanged, bond failed,transitionTo disconnected state.");
                ProjectionConnection projectionConnection = ProjectionConnection.this;
                projectionConnection.transitionTo(projectionConnection.mDisconnectedState);
                return;
            }
            if (status == 11) {
                if (ProjectionConnection.this.mLinkParameter.getFromUser()) {
                    ProjectionConnection.this.mCmsCallback.onProjectionConnectionChanged(ProjectionConnection.this.mActiveDevice, ProjectionConnection.this.mActiveCapability, 3, ProjectionConnection.this.mSessionUpdateReason);
                    return;
                }
                ProjectionConnection.this.removeMessageHandler(ProjectionConnection.EVENT_PROJECTION_PRCONNECT_TIMEOUT);
                ProjectionConnection.this.mSessionUpdateReason = 3;
                ProjectionConnection.this.mDisconnectProjectionReason = 7;
                ProjectionConnection projectionConnection2 = ProjectionConnection.this;
                projectionConnection2.transitionTo(projectionConnection2.mDisconnectedState);
                Log.d(ProjectionConnection.TAG, "CheckCpwlCapabilityState handleBtPairingStateChanged, exception,bt paired twice. dev:" + LogEncrypt.IpAddressEncrypt(ProjectionConnection.this.mActiveDevice.getId()));
                return;
            }
            if (status != 12) {
                Log.e(ProjectionConnection.TAG, "CheckCpwlCapabilityState, invalid bt pairing state:" + status);
                return;
            }
            ProjectionConnection.this.mCmsCallback.onProjectionConnectionChanged(ProjectionConnection.this.mActiveDevice, ProjectionConnection.this.mActiveCapability, 3, ProjectionConnection.this.mSessionUpdateReason);
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            Log.e(ProjectionConnection.TAG, "CheckCpwlCapabilityState msg:" + msg.what);
            int i = msg.what;
            if (i == 701) {
                Log.d(ProjectionConnection.TAG, "CheckCpwlCapabilityState EVENT_DEACTIVATE_SERVICE");
                ProjectionConnection projectionConnection = ProjectionConnection.this;
                projectionConnection.transitionTo(projectionConnection.mDisconnectedState);
            } else if (i == 704) {
                Log.d(ProjectionConnection.TAG, "CheckCpwlCapabilityState EVENT_PROJECTION_CAPABILITY_CHANGED");
                if ((ProjectionConnection.this.mActiveCapability & 512) <= 0) {
                    if (ProjectionConnection.this.mActiveDevice.isAvailableCapability(ProjectionConnection.this.mActiveCapability)) {
                        ProjectionConnection.this.triggerProjection();
                    } else {
                        Log.e(ProjectionConnection.TAG, "CheckCpwlCapabilityState no wireless carplay capability,transtition to disconnected.");
                        ProjectionConnection.this.mSessionUpdateReason = 9;
                        ProjectionConnection.this.mDisconnectProjectionReason = 7;
                        ProjectionConnection projectionConnection2 = ProjectionConnection.this;
                        projectionConnection2.transitionTo(projectionConnection2.mDisconnectedState);
                    }
                }
            } else if (i == ProjectionConnection.EVENT_CONNECT_PROJECTION_SERVICE_TIMEOUT) {
                String deviceId = ProjectionConnection.TAG;
                Log.d(deviceId, "CheckCpwlCapabilityState EVENT_CONNECT_PROJECTION_SERVICE_TIMEOUT");
                ProjectionConnection.this.mSessionUpdateReason = 4;
                ProjectionConnection.this.mDisconnectProjectionReason = 7;
                ProjectionConnection projectionConnection3 = ProjectionConnection.this;
                projectionConnection3.transitionTo(projectionConnection3.mDisconnectedState);
                Log.i(ProjectionConnection.TAG, "CheckCpwlCapabilityState Projection Service start timeout. dev:" + LogEncrypt.IpAddressEncrypt(ProjectionConnection.this.mActiveDevice.getId()));
            } else if (i == ProjectionConnection.EVENT_PROJECTION_PRCONNECT_TIMEOUT) {
                Log.d(ProjectionConnection.TAG, "CheckCpwlCapabilityState EVENT_PROJECTION_PRCONNECT_TIMEOUT");
                ProjectionConnection.this.mProjectionManager.removeCpwlIap2AuthRequest(ProjectionConnection.this.mActiveDevice.getBtAddr(), 2);
                String deviceId2 = (String) msg.obj;
                ProjectionConnection.this.mSessionUpdateReason = 4;
                ProjectionConnection.this.mDisconnectProjectionReason = 7;
                ProjectionConnection projectionConnection4 = ProjectionConnection.this;
                projectionConnection4.transitionTo(projectionConnection4.mDisconnectedState);
                Log.i(ProjectionConnection.TAG, "CheckCpwlCapabilityState projection pre connect timeout. dev:" + LogEncrypt.IpAddressEncrypt(deviceId2));
            } else if (i == ProjectionConnection.EVENT_CARPLAY_WIRELESS_DETECTED) {
                Log.d(ProjectionConnection.TAG, "CheckCpwlCapabilityState EVENT_CARPLAY_WIRELESS_DETECTED");
                int reason = msg.arg1;
                if (ProjectionConnection.this.mActiveCapability == 512) {
                    if (ProjectionConnection.this.mActiveDevice.isAvailableCapability(ProjectionConnection.this.mActiveCapability)) {
                        if (!ProjectionConnection.this.mCmsCallback.onConnectProjection(ProjectionConnection.this.mActiveDevice, ProjectionConnection.this.mLinkParameter)) {
                            ProjectionConnection.this.mSessionUpdateReason = 3;
                            ProjectionConnection.this.mDisconnectProjectionReason = 7;
                            ProjectionConnection projectionConnection5 = ProjectionConnection.this;
                            projectionConnection5.transitionTo(projectionConnection5.mDisconnectedState);
                        } else {
                            ProjectionConnection.this.sendMessageDelayed(ProjectionConnection.EVENT_CONNECT_PROJECTION_SERVICE_TIMEOUT, 10000L);
                            ProjectionConnection.this.mConfigManager.setHppService(ProjectionConnection.this.mActiveService);
                            ProjectionConnection projectionConnection6 = ProjectionConnection.this;
                            projectionConnection6.transitionTo(projectionConnection6.mActiveState);
                        }
                    } else {
                        Log.e(ProjectionConnection.TAG, "CheckCpwlCapabilityState no pre projection wireless carplay capability,transtition to disconnected");
                        if (reason == 1) {
                            ProjectionConnection.this.mSessionUpdateReason = 8;
                        } else if (reason == 2) {
                            ProjectionConnection.this.mSessionUpdateReason = 1;
                        } else {
                            ProjectionConnection.this.mSessionUpdateReason = 9;
                        }
                        ProjectionConnection.this.mDisconnectProjectionReason = 7;
                        ProjectionConnection projectionConnection7 = ProjectionConnection.this;
                        projectionConnection7.transitionTo(projectionConnection7.mDisconnectedState);
                    }
                }
            } else if (i != ProjectionConnection.EVENT_BT_PAIRING_STATE_CHANGED) {
                Log.d(ProjectionConnection.TAG, "CheckCpwlCapabilityState PreConnectingState ignore msg.what:" + msg.what);
            } else {
                Log.d(ProjectionConnection.TAG, "CheckCpwlCapabilityState EVENT_BT_PAIRING_STATE_CHANGED");
                handleBtPairingStateChanged(msg);
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleBtPairingStateChanged(Message msg) {
        int i;
        int status = msg.arg1;
        Log.e(TAG, "handleBtPairingStateChanged, state:" + status);
        if ((status == 11 || status == 10) && !this.mLinkParameter.getFromUser() && (i = this.mActiveCapability) == 512) {
            this.mSessionUpdateReason = 10;
            this.mDisconnectProjectionReason = 7;
            this.mCmsCallback.onDisconnectProjection(this.mActiveDevice, i, 7);
            transitionTo(this.mDisconnectingState);
        }
    }

    private class ActiveState extends State {
        private ActiveState() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void enter() {
            Log.i(ProjectionConnection.TAG, "enter ActiveState service:" + ProjectionConnection.this.mActiveService + ", device:" + ProjectionConnection.this.mActiveDevice);
            ProjectionConnection.this.mActiveDevice.addActiveCapability(ProjectionConnection.this.mActiveCapability);
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            Log.e(ProjectionConnection.TAG, "ActiveState msg:" + msg.what);
            int i = msg.what;
            if (i == 701) {
                Log.d(ProjectionConnection.TAG, "ActiveState EVENT_DEACTIVATE_SERVICE");
                ProjectionConnection.this.removeMessages(ProjectionConnection.EVENT_CONNECT_PROJECTION_SERVICE_TIMEOUT);
                if (ProjectionConnection.this.mCmsCallback.onDisconnectProjection(ProjectionConnection.this.mActiveDevice, ProjectionConnection.this.mActiveCapability, ProjectionConnection.this.mDisconnectProjectionReason)) {
                    Log.e(ProjectionConnection.TAG, "ActiveState onDisconnectProjection success.");
                    ProjectionConnection projectionConnection = ProjectionConnection.this;
                    projectionConnection.transitionTo(projectionConnection.mDisconnectingState);
                } else {
                    ProjectionConnection projectionConnection2 = ProjectionConnection.this;
                    projectionConnection2.transitionTo(projectionConnection2.mDisconnectedState);
                }
            } else if (i == 702) {
                Log.d(ProjectionConnection.TAG, "ActiveState EVENT_UPDATE_CONNECTION_STATE");
                ProjectionConnection.this.removeMessages(ProjectionConnection.EVENT_CONNECT_PROJECTION_SERVICE_TIMEOUT);
                CmsMessage dccMsg = (CmsMessage) msg.obj;
                Device device = (Device) dccMsg.obj;
                if (device != null && !TextUtils.isEmpty(device.getId())) {
                    device = ProjectionConnection.this.mDcController.findMergedDevice(device.getId());
                }
                if (ProjectionConnection.this.mActiveDevice.equals(device)) {
                    int status = dccMsg.arg1;
                    ProjectionConnection.this.handleAuthenticationFailedEvent(dccMsg.arg2);
                    ProjectionConnection.this.mActiveDevice.updateActiveCapaState(ProjectionConnection.this.mActiveCapability, status);
                    if (dccMsg.arg2 == 0) {
                        if (status == 3) {
                            ProjectionConnection.this.mCmsCallback.onDeviceStatusChanged(ProjectionConnection.this.mActiveDevice);
                            ProjectionConnection projectionConnection3 = ProjectionConnection.this;
                            projectionConnection3.transitionTo(projectionConnection3.mConnectingState);
                        } else {
                            Log.e(ProjectionConnection.TAG, "ActiveState Received Wrong state in activated State:" + status);
                            ProjectionConnection.this.mSessionUpdateReason = 19;
                            ProjectionConnection.this.mDisconnectProjectionReason = 7;
                            if (status != 2 && status != 1) {
                                ProjectionConnection.this.mCmsCallback.onDisconnectProjection(ProjectionConnection.this.mActiveDevice, ProjectionConnection.this.mActiveCapability, ProjectionConnection.this.mDisconnectProjectionReason);
                            }
                            ProjectionConnection projectionConnection4 = ProjectionConnection.this;
                            projectionConnection4.transitionTo(projectionConnection4.mDisconnectingState);
                        }
                    } else if (dccMsg.arg2 == 1 || dccMsg.arg2 == 3) {
                        Log.e(ProjectionConnection.TAG, "ActiveState stop projeciton and transitionTo disconnected state case:" + dccMsg.arg2);
                        ProjectionConnection.this.mSessionUpdateReason = dccMsg.arg2;
                        ProjectionConnection.this.mDisconnectProjectionReason = 7;
                        ProjectionConnection.this.mCmsCallback.onDisconnectProjection(ProjectionConnection.this.mActiveDevice, ProjectionConnection.this.mActiveCapability, ProjectionConnection.this.mDisconnectProjectionReason);
                        ProjectionConnection projectionConnection5 = ProjectionConnection.this;
                        projectionConnection5.transitionTo(projectionConnection5.mDisconnectingState);
                    }
                }
            } else if (i == 704) {
                Log.d(ProjectionConnection.TAG, "ActiveState EVENT_PROJECTION_CAPABILITY_CHANGED");
                ProjectionConnection.this.handleProjectionCapabilityChanged(msg.arg1, msg.arg2);
            } else if (i == ProjectionConnection.EVENT_AA_SWITCH) {
                Log.d(ProjectionConnection.TAG, "ActiveState EVENT_AA_SWITCH");
                Device device2 = (Device) msg.obj;
                ProjectionConnection.this.mSwitchingDevice = device2;
                ProjectionConnection.this.mSwitchCapability = 128;
                Log.i(ProjectionConnection.TAG, "ActiveState android auto switch. dev:" + LogEncrypt.IpAddressEncrypt(device2.getId()));
                ProjectionConnection projectionConnection6 = ProjectionConnection.this;
                projectionConnection6.transitionTo(projectionConnection6.mConnectionSwitchState);
            } else if (i == ProjectionConnection.EVENT_BT_PAIRING_STATE_CHANGED) {
                Log.d(ProjectionConnection.TAG, "ActiveState EVENT_BT_PAIRING_STATE_CHANGED");
                ProjectionConnection.this.handleBtPairingStateChanged(msg);
            } else if (i != ProjectionConnection.EVENT_CONNECT_PROJECTION_SERVICE_TIMEOUT) {
                Log.d(ProjectionConnection.TAG, "ActiveState ignore msg.what:" + msg.what);
            } else {
                Log.d(ProjectionConnection.TAG, "ActiveState EVENT_CONNECT_PROJECTION_SERVICE_TIMEOUT");
                Log.i(ProjectionConnection.TAG, "ActiveState Projection Service start timeout. dev:" + LogEncrypt.IpAddressEncrypt(ProjectionConnection.this.mActiveDevice.getId()));
                ProjectionConnection.this.mSessionUpdateReason = 4;
                ProjectionConnection.this.mActiveDevice.updateActiveCapaState(ProjectionConnection.this.mActiveCapability, 1);
                ProjectionConnection projectionConnection7 = ProjectionConnection.this;
                projectionConnection7.transitionTo(projectionConnection7.mDisconnectedState);
            }
            return true;
        }
    }

    private class ConnectingState extends State {
        private ConnectingState() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void enter() {
            Log.i(ProjectionConnection.TAG, "enter ConnectingState service:" + ProjectionConnection.this.mActiveService + ", device:" + ProjectionConnection.this.mActiveDevice);
            ProjectionConnection.this.mDeviceManager.enableSaveDatabase(false);
            if (ProjectionConnection.this.mActiveDevice.isProjectionFirstConnecting(ProjectionConnection.this.mActiveService)) {
                Log.e(ProjectionConnection.TAG, "enter ConnectingState, is projection first connecting:" + ProjectionConnection.this.mActiveService);
                ProjectionConnection.this.mActiveDevice.setProjectionFirstConnStatus(ProjectionConnection.this.mActiveService, false);
                ProjectionConnection.this.mDeviceManager.saveDevice(ProjectionConnection.this.mActiveDevice);
            }
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            Log.e(ProjectionConnection.TAG, "ConnectingState msg:" + msg.what);
            int i = msg.what;
            if (i != ProjectionConnection.EVENT_AA_SWITCH) {
                if (i == ProjectionConnection.EVENT_BT_PAIRING_STATE_CHANGED) {
                    Log.d(ProjectionConnection.TAG, "ConnectingState EVENT_BT_PAIRING_STATE_CHANGED");
                    ProjectionConnection.this.handleBtPairingStateChanged(msg);
                } else {
                    switch (i) {
                        case 701:
                            if (ProjectionConnection.this.mCmsCallback.onDisconnectProjection(ProjectionConnection.this.mActiveDevice, ProjectionConnection.this.mActiveCapability, ProjectionConnection.this.mDisconnectProjectionReason)) {
                                Log.e(ProjectionConnection.TAG, "ConnectingState onDisconnectProjection success.");
                                ProjectionConnection projectionConnection = ProjectionConnection.this;
                                projectionConnection.transitionTo(projectionConnection.mDisconnectingState);
                            } else {
                                ProjectionConnection projectionConnection2 = ProjectionConnection.this;
                                projectionConnection2.transitionTo(projectionConnection2.mDisconnectedState);
                            }
                            break;
                        case 702:
                            Log.d(ProjectionConnection.TAG, "ConnectingState EVENT_UPDATE_CONNECTION_STATE");
                            CmsMessage dccMsg = (CmsMessage) msg.obj;
                            Device device = (Device) dccMsg.obj;
                            if (device != null && !TextUtils.isEmpty(device.getId())) {
                                device = ProjectionConnection.this.mDcController.findMergedDevice(device.getId());
                            }
                            if (ProjectionConnection.this.mActiveDevice.equals(device)) {
                                int status = dccMsg.arg1;
                                Log.d(ProjectionConnection.TAG, "ConnectingState status:" + status);
                                ProjectionConnection.this.mSessionUpdateReason = dccMsg.arg2;
                                ProjectionConnection.this.handleAuthenticationFailedEvent(dccMsg.arg2);
                                ProjectionConnection.this.mActiveDevice.updateActiveCapaState(ProjectionConnection.this.mActiveCapability, status);
                                if (dccMsg.arg2 == 0 || dccMsg.arg2 == 16) {
                                    if (status == 4) {
                                        Log.e(ProjectionConnection.TAG, "ConnectingState transitionTo ConnectedState.");
                                        ProjectionConnection.this.mCmsCallback.onDeviceStatusChanged(ProjectionConnection.this.mActiveDevice);
                                        ProjectionConnection projectionConnection3 = ProjectionConnection.this;
                                        projectionConnection3.transitionTo(projectionConnection3.mConnectedState);
                                    } else if (status == 2) {
                                        Log.e(ProjectionConnection.TAG, "ConnectingState transitionTo DisconnectingState.");
                                        ProjectionConnection.this.mCmsCallback.onDeviceStatusChanged(ProjectionConnection.this.mActiveDevice);
                                        ProjectionConnection.this.mSessionUpdateReason = 3;
                                        ProjectionConnection.this.mDisconnectProjectionReason = 7;
                                        ProjectionConnection projectionConnection4 = ProjectionConnection.this;
                                        projectionConnection4.transitionTo(projectionConnection4.mDisconnectingState);
                                    } else if (status == 1) {
                                        ProjectionConnection.this.mSessionUpdateReason = 3;
                                        ProjectionConnection.this.mDisconnectProjectionReason = 7;
                                        Log.e(ProjectionConnection.TAG, "ConnectingState transitionTo disconnected state.");
                                        ProjectionConnection projectionConnection5 = ProjectionConnection.this;
                                        projectionConnection5.transitionTo(projectionConnection5.mDisconnectedState);
                                    } else {
                                        Log.e(ProjectionConnection.TAG, "ConnectingState Wrong state in ConnectingState State:" + status);
                                    }
                                } else if (dccMsg.arg2 == 1 || dccMsg.arg2 == 3) {
                                    Log.e(ProjectionConnection.TAG, "ConnectingState stop projection and transition to disconnected state with:" + dccMsg.arg2);
                                    ProjectionConnection.this.mSessionUpdateReason = dccMsg.arg2;
                                    ProjectionConnection.this.mDisconnectProjectionReason = 7;
                                    ProjectionConnection.this.mCmsCallback.onDisconnectProjection(ProjectionConnection.this.mActiveDevice, ProjectionConnection.this.mActiveCapability, ProjectionConnection.this.mDisconnectProjectionReason);
                                    ProjectionConnection projectionConnection6 = ProjectionConnection.this;
                                    projectionConnection6.transitionTo(projectionConnection6.mDisconnectingState);
                                }
                            }
                            break;
                        case 703:
                            Log.d(ProjectionConnection.TAG, "ConnectingState EVENT_UPDATE_FUNCTION_STATE");
                            ProjectionConnection.this.deferMessage(msg);
                            break;
                        case 704:
                            Log.d(ProjectionConnection.TAG, "ConnectingState EVENT_PROJECTION_CAPABILITY_CHANGED");
                            ProjectionConnection.this.handleProjectionCapabilityChanged(msg.arg1, msg.arg2);
                            break;
                        default:
                            Log.d(ProjectionConnection.TAG, "ConnectingState ignore msg.what:" + msg.what);
                            break;
                    }
                }
            } else {
                Log.d(ProjectionConnection.TAG, "ConnectingState EVENT_AA_SWITCH");
                Device device2 = (Device) msg.obj;
                Log.d(ProjectionConnection.TAG, "ConnectingState android auto. dev:" + LogEncrypt.IpAddressEncrypt(device2.getId()));
                ProjectionConnection.this.mSwitchingDevice = device2;
                ProjectionConnection.this.mSwitchCapability = 128;
                ProjectionConnection projectionConnection7 = ProjectionConnection.this;
                projectionConnection7.transitionTo(projectionConnection7.mConnectionSwitchState);
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDisconnectReason(int reason) {
        if (this.mDisconnectProjectionReason != 3) {
            if (reason == 0) {
                this.mSessionUpdateReason = 0;
                this.mDisconnectProjectionReason = 0;
            } else if (reason == 1 || reason == 3) {
                this.mSessionUpdateReason = reason;
                this.mDisconnectProjectionReason = 7;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleConnectionStateChanged(int status, int reason) {
        if (status == 4) {
            Log.d(TAG, "ConnectedState It is already Connected State.");
            return;
        }
        if (status == 2) {
            this.mCmsCallback.onDeviceStatusChanged(this.mActiveDevice);
            if ((this.mActiveCapability & 128) > 0) {
                if (reason == 16 || this.mActiveDevice.isDisconnectByUserAction()) {
                    transitionTo(this.mDisconnectingState);
                    return;
                } else {
                    Log.e(TAG, "ConnectedState keep in connected state until device detached.");
                    return;
                }
            }
            transitionTo(this.mDisconnectingState);
            return;
        }
        if (status == 1) {
            Log.e(TAG, "ConnectedState transitionTo disconnected state.");
            transitionTo(this.mDisconnectedState);
            return;
        }
        Log.e(TAG, "ConnectedState Wrong state in ConnectingState State:" + status);
    }

    private class ConnectedState extends State {
        private ConnectedState() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void enter() {
            Log.e(ProjectionConnection.TAG, "enter ConnectedState service:" + ProjectionConnection.this.mActiveService + ", device:" + ProjectionConnection.this.mActiveDevice.getFriendlyName());
            ProjectionConnection.this.mCmsCallback.onProjectionConnectionChanged(ProjectionConnection.this.mActiveDevice, ProjectionConnection.this.mActiveCapability, 4, ProjectionConnection.this.mSessionUpdateReason);
            if (ProjectionConnection.this.mConfigManager.isProjectionAutoShow()) {
                ProjectionConnection.this.mCmsCallback.onShowProjection(ProjectionConnection.this.mActiveService, true);
            }
            ProjectionConnection.this.mDeviceManager.enableSaveDatabase(true);
            if (ProjectionConnection.this.mActiveCapability == 512) {
                if ((ProjectionConnection.this.mActiveDevice.getAvailableCapability() & 64) > 0) {
                    ProjectionConnection.this.mCmsCallback.onDeviceConnectionUpdated(ProjectionConnection.this.mActiveDevice, ProjectionConnection.this.mActiveCapability, 1, ProjectionConnection.this.mActiveService);
                }
                ProjectionConnection.this.mActiveDevice.setWirelessCarplayConnectedTime(System.currentTimeMillis());
                Log.d(ProjectionConnection.TAG, "Device: " + ProjectionConnection.this.mActiveDevice.getId() + " Last service: 512");
                ProjectionConnection.this.mActiveDevice.setLastService(512);
            } else if (ProjectionConnection.this.mActiveCapability == 64) {
                ProjectionConnection.this.mActiveDevice.setUsbCarplayConnectedTime(System.currentTimeMillis());
            }
            if (ProjectionConnection.this.mActiveCapability == 1024) {
                if ((ProjectionConnection.this.mActiveDevice.getAvailableCapability() & 128) > 0) {
                    ProjectionConnection.this.mCmsCallback.onDeviceConnectionUpdated(ProjectionConnection.this.mActiveDevice, ProjectionConnection.this.mActiveCapability, 1, ProjectionConnection.this.mActiveService);
                }
                ProjectionConnection.this.mActiveDevice.setWirelessAndroidAutoConnectedTime(System.currentTimeMillis());
                Log.d(ProjectionConnection.TAG, "Device: " + ProjectionConnection.this.mActiveDevice.getId() + " Last service: 1024");
                ProjectionConnection.this.mActiveDevice.setLastService(1024);
            } else if (ProjectionConnection.this.mActiveCapability == 128) {
                ProjectionConnection.this.mActiveDevice.setUsbAndroidAutoConnectedTime(System.currentTimeMillis());
            }
            ProjectionConnection.this.mDeviceManager.saveDevice(ProjectionConnection.this.mActiveDevice);
            ProjectionConnection.this.mSessionUpdateReason = 0;
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            Log.e(ProjectionConnection.TAG, "ConnectedState msg:" + msg.what);
            switch (msg.what) {
                case 701:
                    if (ProjectionConnection.this.mCmsCallback.onDisconnectProjection(ProjectionConnection.this.mActiveDevice, ProjectionConnection.this.mActiveCapability, ProjectionConnection.this.mDisconnectProjectionReason)) {
                        Log.e(ProjectionConnection.TAG, "ConnectedState onDisconnectProjection success.");
                        ProjectionConnection.this.mActiveDevice.updateActiveCapaState(ProjectionConnection.this.mActiveCapability, 2);
                        ProjectionConnection.this.mCmsCallback.onDeviceStatusChanged(ProjectionConnection.this.mActiveDevice);
                        ProjectionConnection projectionConnection = ProjectionConnection.this;
                        projectionConnection.transitionTo(projectionConnection.mDisconnectingState);
                    } else {
                        ProjectionConnection projectionConnection2 = ProjectionConnection.this;
                        projectionConnection2.transitionTo(projectionConnection2.mDisconnectedState);
                    }
                    return true;
                case 702:
                    Log.d(ProjectionConnection.TAG, "ConnectedState EVENT_UPDATE_CONNECTION_STATE");
                    CmsMessage dccMsg = (CmsMessage) msg.obj;
                    Device device = (Device) dccMsg.obj;
                    if (device != null && !TextUtils.isEmpty(device.getId())) {
                        device = ProjectionConnection.this.mDcController.findMergedDevice(device.getId());
                    }
                    if (ProjectionConnection.this.mActiveDevice.equals(device)) {
                        int status = dccMsg.arg1;
                        ProjectionConnection.this.handleAuthenticationFailedEvent(dccMsg.arg2);
                        ProjectionConnection.this.updateDisconnectReason(dccMsg.arg2);
                        ProjectionConnection.this.mActiveDevice.updateActiveCapaState(ProjectionConnection.this.mActiveCapability, status);
                        if (dccMsg.arg2 == 0 || dccMsg.arg2 == 16) {
                            ProjectionConnection.this.handleConnectionStateChanged(status, dccMsg.arg2);
                        } else if (dccMsg.arg2 == 1 || dccMsg.arg2 == 3) {
                            Log.e(ProjectionConnection.TAG, "ConnectedState stop projection and transition to disconnected state with:" + dccMsg.arg2);
                            ProjectionConnection.this.mCmsCallback.onDisconnectProjection(ProjectionConnection.this.mActiveDevice, ProjectionConnection.this.mActiveCapability, ProjectionConnection.this.mDisconnectProjectionReason);
                            ProjectionConnection projectionConnection3 = ProjectionConnection.this;
                            projectionConnection3.transitionTo(projectionConnection3.mDisconnectingState);
                        }
                    }
                    return true;
                case 703:
                case ProjectionConnection.EVENT_START_PRECONNECT /* 706 */:
                default:
                    Log.d(ProjectionConnection.TAG, "ConnectedState ignore msg.what:" + msg.what);
                    return true;
                case 704:
                    Log.d(ProjectionConnection.TAG, "ConnectedState EVENT_PROJECTION_CAPABILITY_CHANGED");
                    ProjectionConnection.this.handleProjectionCapabilityChanged(msg.arg1, msg.arg2);
                    return true;
                case ProjectionConnection.EVENT_SHOW_PROJECTION /* 705 */:
                    Log.d(ProjectionConnection.TAG, "ConnectedState EVENT_SHOW_PROJECTION");
                    boolean flag = ((Boolean) msg.obj).booleanValue();
                    ProjectionConnection.this.mCmsCallback.onShowProjection(ProjectionConnection.this.mActiveService, flag);
                    Log.d(ProjectionConnection.TAG, "ConnectedState show Projection service:" + ProjectionConnection.this.mActiveService + ", devId:" + LogEncrypt.IpAddressEncrypt(ProjectionConnection.this.mActiveDevice.getId()) + ", flag:" + flag);
                    return true;
                case ProjectionConnection.EVENT_AA_SWITCH /* 707 */:
                    Log.d(ProjectionConnection.TAG, "ConnectedState EVENT_AA_SWITCH");
                    Device device2 = (Device) msg.obj;
                    Log.d(ProjectionConnection.TAG, "ConnectedState android auto. dev:" + device2.getId());
                    ProjectionConnection.this.mSwitchingDevice = device2;
                    ProjectionConnection.this.mSwitchCapability = 128;
                    ProjectionConnection projectionConnection4 = ProjectionConnection.this;
                    projectionConnection4.transitionTo(projectionConnection4.mConnectionSwitchState);
                    return true;
            }
        }
    }

    private class DisconnectingState extends State {
        private boolean isDisconnectingMessageSent;

        private DisconnectingState() {
            this.isDisconnectingMessageSent = false;
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void enter() {
            Log.i(ProjectionConnection.TAG, "enter DisconnectingState service:" + ProjectionConnection.this.mActiveService + ", device:" + ProjectionConnection.this.mActiveDevice.getFriendlyName());
            ProjectionConnection.this.mActiveDevice.updateActiveCapaState(ProjectionConnection.this.mActiveCapability, 2);
            if (!this.isDisconnectingMessageSent) {
                ProjectionConnection.this.removeMessageHandler(ProjectionConnection.EVENT_CONNECT_PROJECTION_SERVICE_TIMEOUT);
                ProjectionConnection.this.mCmsCallback.onDeviceStatusChanged(ProjectionConnection.this.mActiveDevice);
                ProjectionConnection.this.mCmsCallback.onProjectionConnectionChanged(ProjectionConnection.this.mActiveDevice, ProjectionConnection.this.mActiveCapability, 2, ProjectionConnection.this.mSessionUpdateReason);
                this.isDisconnectingMessageSent = true;
            }
            if (!ProjectionConnection.this.mActiveDevice.isInUsbDeviceMode() && !ProjectionConnection.this.mActiveDevice.isInUsbAoaMode()) {
                Log.d(ProjectionConnection.TAG, "DisconnectingState current DisconnectingState waiting for disconnection to complete, activeState:" + ProjectionConnection.this.mActiveDevice.getActiveCapaState(ProjectionConnection.this.mActiveCapability) + ", aoa:" + ProjectionConnection.this.mActiveDevice.isInUsbAoaMode() + ", device mode:" + ProjectionConnection.this.mActiveDevice.isInUsbDeviceMode());
                ProjectionConnection.this.sendMessageDelayed(ProjectionConnection.EVENT_DISCONNECT_PROJECTION_SERVICE_TIMEOUT, 20000L);
            } else {
                ProjectionConnection projectionConnection = ProjectionConnection.this;
                projectionConnection.transitionTo(projectionConnection.mResetUsbState);
            }
            ProjectionConnection.this.mDeviceManager.enableSaveDatabase(true);
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            Log.e(ProjectionConnection.TAG, "DisconnectingState msg=" + msg.what);
            int i = msg.what;
            if (i == 702) {
                Log.d(ProjectionConnection.TAG, "DisconnectingState EVENT_UPDATE_CONNECTION_STATE");
                CmsMessage dccMsg = (CmsMessage) msg.obj;
                Device device = (Device) dccMsg.obj;
                if (device != null && !TextUtils.isEmpty(device.getId())) {
                    device = ProjectionConnection.this.mDcController.findMergedDevice(device.getId());
                }
                if (ProjectionConnection.this.mActiveDevice.equals(device)) {
                    int status = dccMsg.arg1;
                    ProjectionConnection.this.mActiveDevice.updateActiveCapaState(ProjectionConnection.this.mActiveCapability, status);
                    ProjectionConnection.this.handleAuthenticationFailedEvent(dccMsg.arg2);
                    ProjectionConnection.this.updateDisconnectReason(dccMsg.arg2);
                    ProjectionConnection.this.mCmsCallback.onDeviceStatusChanged(ProjectionConnection.this.mActiveDevice);
                    if (dccMsg.arg2 == 0 || dccMsg.arg2 == 16) {
                        if (status == 2) {
                            Log.d(ProjectionConnection.TAG, "DisconnectingState It is already DISCONNECTING State.");
                        } else if (status != 1) {
                            Log.e(ProjectionConnection.TAG, "DisconnectingState Wrong state in Disconnecting State:" + status);
                        } else {
                            this.isDisconnectingMessageSent = false;
                            ProjectionConnection projectionConnection = ProjectionConnection.this;
                            projectionConnection.transitionTo(projectionConnection.mDisconnectedState);
                        }
                    } else if (dccMsg.arg2 == 1 || dccMsg.arg2 == 3) {
                        Log.e(ProjectionConnection.TAG, "DisconnectingState stop projection and transition to disconnected state with:" + dccMsg.arg2 + ", state:" + status);
                        if (status == 1) {
                            this.isDisconnectingMessageSent = false;
                            ProjectionConnection projectionConnection2 = ProjectionConnection.this;
                            projectionConnection2.transitionTo(projectionConnection2.mDisconnectedState);
                        }
                    }
                }
            } else if (i != ProjectionConnection.EVENT_DISCONNECT_PROJECTION_SERVICE_TIMEOUT) {
                Log.d(ProjectionConnection.TAG, "DisconnectingState ignore msg.what:" + msg.what);
            } else {
                Log.i(ProjectionConnection.TAG, "DisconnectingState Projection Service disconnect timeout. dev:" + LogEncrypt.IpAddressEncrypt(ProjectionConnection.this.mActiveDevice.getId()));
                if (ProjectionConnection.this.mDisconnectProjectionReason != 3) {
                    ProjectionConnection.this.mDisconnectProjectionReason = 7;
                    ProjectionConnection.this.mSessionUpdateReason = 5;
                }
                this.isDisconnectingMessageSent = false;
                ProjectionConnection projectionConnection3 = ProjectionConnection.this;
                projectionConnection3.transitionTo(projectionConnection3.mDisconnectedState);
            }
            return true;
        }
    }

    private class ResetUsbState extends State {
        private ResetUsbState() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void enter() {
            Log.e(ProjectionConnection.TAG, "enter ResetUsbState service:" + ProjectionConnection.this.mActiveService + ", device:" + ProjectionConnection.this.mActiveDevice.getFriendlyName() + ", aoa:" + ProjectionConnection.this.mActiveDevice.isInUsbAoaMode() + ", device mode:" + ProjectionConnection.this.mActiveDevice.isInUsbDeviceMode());
            if (!ProjectionConnection.this.mDcController.resetUsb(ProjectionConnection.this.mActiveDevice.getId())) {
                ProjectionConnection projectionConnection = ProjectionConnection.this;
                projectionConnection.transitionTo(projectionConnection.mDisconnectingState);
            }
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            Log.e(ProjectionConnection.TAG, "ResetUsbState msg:" + msg.what);
            int i = msg.what;
            if (i == 702) {
                Log.d(ProjectionConnection.TAG, "ResetUsbState EVENT_UPDATE_CONNECTION_STATE");
                ProjectionConnection.this.deferMessage(msg);
            } else if (i != ProjectionConnection.EVENT_RESET_USB_FINISHED) {
                Log.d(ProjectionConnection.TAG, "ResetUsbState ignore msg.what:" + msg.what);
            } else {
                Log.d(ProjectionConnection.TAG, "ResetUsbState EVENT_RESET_USB_FINISHED");
                Log.e(ProjectionConnection.TAG, "ResetUsbState reset usb finished. aoa:" + ProjectionConnection.this.mActiveDevice.isInUsbAoaMode() + ", device mode:" + ProjectionConnection.this.mActiveDevice.isInUsbDeviceMode() + ", result:" + msg.arg1);
                ProjectionConnection projectionConnection = ProjectionConnection.this;
                projectionConnection.transitionTo(projectionConnection.mDisconnectingState);
            }
            return true;
        }
    }

    private class ConnectionSwitchState extends State {
        private ConnectionSwitchState() {
        }

        private void handleConnectionState(Message msg) {
            CmsMessage dccMsg = (CmsMessage) msg.obj;
            Device device = (Device) dccMsg.obj;
            if (!device.equals(ProjectionConnection.this.mActiveDevice) || dccMsg.arg3 != ProjectionConnection.this.mActiveCapability) {
                if (device.equals(ProjectionConnection.this.mSwitchingDevice) || dccMsg.arg3 == ProjectionConnection.this.mSwitchCapability) {
                    if (dccMsg.arg2 == 0) {
                        if (dccMsg.arg1 == 3 || dccMsg.arg1 == 2) {
                            ProjectionConnection.this.removeMessages(ProjectionConnection.EVENT_PROJECTION_CONNECT_TYPE_SWITCH_TIMEOUT);
                            device.updateActiveCapaState(ProjectionConnection.this.mSwitchCapability, dccMsg.arg1);
                            ProjectionConnection.this.mCmsCallback.onDeviceStatusChanged(device);
                            if (device.equals(ProjectionConnection.this.mSwitchingDevice)) {
                                ProjectionConnection.this.mProjectionManager.onProjectionSwitchDone(ProjectionConnection.this.mActiveDevice.getId(), ProjectionConnection.this.mSwitchingDevice.getId());
                                ProjectionConnection.this.mActiveDevice = device;
                                ProjectionConnection.this.mActiveCapability = 128;
                                ProjectionConnection.this.mActiveDevice.addActiveCapability(ProjectionConnection.this.mSwitchCapability);
                            }
                        }
                        if (dccMsg.arg1 == 3) {
                            ProjectionConnection projectionConnection = ProjectionConnection.this;
                            projectionConnection.transitionTo(projectionConnection.mConnectingState);
                            return;
                        }
                        if (dccMsg.arg1 == 2) {
                            ProjectionConnection.this.mSessionUpdateReason = 3;
                            ProjectionConnection.this.mDisconnectProjectionReason = 7;
                            ProjectionConnection projectionConnection2 = ProjectionConnection.this;
                            projectionConnection2.transitionTo(projectionConnection2.mDisconnectedState);
                            return;
                        }
                        Log.e(ProjectionConnection.TAG, "ConnectionSwitchState handleConnectionState, Received Wrong state in switch State:" + dccMsg.arg1);
                        return;
                    }
                    if (dccMsg.arg2 == 1 || dccMsg.arg2 == 3) {
                        Log.e(ProjectionConnection.TAG, "ConnectionSwitchState handleConnectionState, stop projection and transition to disconnected state with:" + dccMsg.arg2);
                        ProjectionConnection.this.mSessionUpdateReason = dccMsg.arg2;
                        ProjectionConnection.this.mDisconnectProjectionReason = 7;
                        ProjectionConnection.this.mCmsCallback.onDisconnectProjection(ProjectionConnection.this.mActiveDevice, ProjectionConnection.this.mActiveCapability, ProjectionConnection.this.mDisconnectProjectionReason);
                        ProjectionConnection projectionConnection3 = ProjectionConnection.this;
                        projectionConnection3.transitionTo(projectionConnection3.mDisconnectedState);
                        return;
                    }
                    return;
                }
                return;
            }
            if (dccMsg.arg1 != 2 && dccMsg.arg1 != 1) {
                Log.e(ProjectionConnection.TAG, "ConnectionSwitchState handleConnectionState, Wrong state in ConnectSwitchState State:" + dccMsg.arg1);
                return;
            }
            device.updateActiveCapaState(dccMsg.arg3, dccMsg.arg1);
            ProjectionConnection.this.mCmsCallback.onDeviceStatusChanged(device);
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void enter() {
            Log.e(ProjectionConnection.TAG, "enter ConnectionSwitchState.");
            if (ProjectionConnection.this.mSwitchingDevice != ProjectionConnection.this.mActiveDevice) {
                ProjectionConnection.this.mCmsCallback.onSwitchAaConnection(ProjectionConnection.this.mActiveDevice, ProjectionConnection.this.mSwitchingDevice);
            }
            ProjectionConnection.this.removeMessages(ProjectionConnection.EVENT_CONNECT_PROJECTION_SERVICE_TIMEOUT);
            ProjectionConnection.this.removeMessages(ProjectionConnection.EVENT_DISCONNECT_PROJECTION_SERVICE_TIMEOUT);
            ProjectionConnection.this.sendMessageDelayed(ProjectionConnection.EVENT_PROJECTION_CONNECT_TYPE_SWITCH_TIMEOUT, 3000L);
            ProjectionConnection.this.mDisconnectProjectionReason = 3;
            ProjectionConnection.this.mSessionUpdateReason = 16;
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            Log.e(ProjectionConnection.TAG, "ConnectionSwitchState msg:" + msg.what);
            int i = msg.what;
            if (i == 701) {
                ProjectionConnection.this.mCmsCallback.onDisconnectProjection(ProjectionConnection.this.mSwitchingDevice, ProjectionConnection.this.mSwitchCapability, ProjectionConnection.this.mDisconnectProjectionReason);
                ProjectionConnection projectionConnection = ProjectionConnection.this;
                projectionConnection.transitionTo(projectionConnection.mDisconnectedState);
            } else if (i == 702) {
                Log.d(ProjectionConnection.TAG, "ConnectionSwitchState EVENT_UPDATE_CONNECTION_STATE");
                handleConnectionState(msg);
            } else if (i == ProjectionConnection.EVENT_AA_SWITCH) {
                Log.d(ProjectionConnection.TAG, "ConnectionSwitchState EVENT_AA_SWITCH");
                Device device = (Device) msg.obj;
                ProjectionConnection.this.mSwitchingDevice = device;
                Log.i(ProjectionConnection.TAG, "ConnectionSwitchState android auto. dev:" + device.getId());
                ProjectionConnection.this.mCmsCallback.onSwitchAaConnection(ProjectionConnection.this.mActiveDevice, ProjectionConnection.this.mSwitchingDevice);
            } else if (i != ProjectionConnection.EVENT_PROJECTION_CONNECT_TYPE_SWITCH_TIMEOUT) {
                Log.d(ProjectionConnection.TAG, "ConnectionSwitchState ignore msg.what:" + msg.what);
            } else {
                Log.d(ProjectionConnection.TAG, "ConnectionSwitchState EVENT_PROJECTION_CONNECT_TYPE_SWITCH_TIMEOUT");
                Log.e(ProjectionConnection.TAG, "ConnectionSwitchState transitionTo disconnected state.");
                ProjectionConnection.this.mSessionUpdateReason = 6;
                ProjectionConnection.this.mCmsCallback.onDisconnectProjection(ProjectionConnection.this.mSwitchingDevice, ProjectionConnection.this.mSwitchCapability, ProjectionConnection.this.mDisconnectProjectionReason);
                ProjectionConnection projectionConnection2 = ProjectionConnection.this;
                projectionConnection2.transitionTo(projectionConnection2.mDisconnectedState);
            }
            return true;
        }
    }
}
