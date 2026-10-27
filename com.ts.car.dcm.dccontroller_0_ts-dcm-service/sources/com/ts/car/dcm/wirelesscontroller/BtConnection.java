package com.ts.car.dcm.wirelesscontroller;

import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.ts.car.dcm.common.data.DeviceItem;
import com.ts.car.dcm.common.data.LinkDevice;
import com.ts.car.dcm.common.data.LogEncrypt;
import com.ts.car.dcm.utils.statemachine.State;
import ts.car.dcm.common.data.CmsConstants;

/* JADX INFO: loaded from: classes.dex */
public class BtConnection extends WirelessConnection {
    public static final int BT_CALLBACK_UUIDS = 28;
    public static final int BT_DEVICE_DISCONNECTED = 25;
    public static final int BT_DEVICE_PAIRED = 26;
    public static final int BT_PROFILE_CHANGED = 30;
    public static final int DCC_SET_DEVICE_CAPABILITY = 27;
    private static final String TAG = BtConnection.class.getSimpleName();
    private BluetoothDeviceMonitor mBtDeviceMonitor;
    private Connected mConnected;
    private Detecting mDetecting;
    private DeviceItem mDeviceItem;
    private Disconnected mDisconnected;
    private boolean mInAudioPlayState;
    private boolean mInCallState;
    private int mLastReportDeviceState;
    private boolean mPaired;

    private BtConnection(Looper looper, DeviceItem deviceItem, BluetoothDeviceMonitor monitor, int platformCapability) {
        super("BtConnection", looper);
        this.mLastReportDeviceState = 0;
        this.mDeviceItem = deviceItem;
        this.mBtDeviceMonitor = monitor;
        this.mPlatformCapability = platformCapability;
        this.mLinkDevice = new LinkDevice();
        this.mDisconnected = new Disconnected();
        this.mDetecting = new Detecting();
        this.mConnected = new Connected();
        addState(this.mDisconnected);
        addState(this.mDetecting);
        addState(this.mConnected);
        setInitialState(this.mDisconnected);
        Log.d(TAG, "BtConnection, platformCapability:" + platformCapability);
    }

    public static BtConnection make(Looper looper, DeviceItem deviceItem, BluetoothDeviceMonitor monitor, int platformCapability) {
        BtConnection btConnection = new BtConnection(looper, deviceItem, monitor, platformCapability);
        btConnection.start();
        return btConnection;
    }

    public DeviceItem getDeviceItem() {
        return this.mDeviceItem;
    }

    public boolean isInCallState() {
        return this.mInCallState;
    }

    public void setInCallState(boolean inCallState) {
        this.mInCallState = inCallState;
    }

    public boolean isInAudioPlayState() {
        return this.mInAudioPlayState;
    }

    public void setInAudioPlayState(boolean inAudioPlayState) {
        this.mInAudioPlayState = inAudioPlayState;
    }

    public boolean isInDisconnectState() {
        return getCurrentState() instanceof Disconnected;
    }

    private class Disconnected extends State {
        private Disconnected() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void enter() {
            Log.d(BtConnection.TAG, "enter Disconnected");
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void exit() {
        }

        private void handleProfileChanged(Message msg) {
            int state = msg.arg2;
            if (state == 2) {
                BtConnection.this.mPaired = true;
                BtConnection.this.mLinkDevice.setPaired(BtConnection.this.mPaired);
                BtConnection.this.updateBtDeviceInfo((DeviceItem) msg.obj);
                BtConnection.this.mLinkDevice.setDeviceState(2);
                BtConnection.this.updateLinkDeviceAttributes(0, true, 0, 1);
                BtConnection.this.notifyWirelessDeviceAttach();
                BtConnection.this.deferMessage(msg);
                BtConnection btConnection = BtConnection.this;
                btConnection.transitionTo(btConnection.mDetecting);
            }
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            Log.d(BtConnection.TAG, "Disconnected processMessage msg.what:" + BtConnection.this.btWhat(msg.what));
            int i = msg.what;
            if (i == 26) {
                Log.d(BtConnection.TAG, "Disconnected processMessage BT_DEVICE_PAIRED");
                BtConnection.this.mPaired = msg.arg1 == 1;
                BtConnection.this.mLinkDevice.setPaired(BtConnection.this.mPaired);
                BtConnection.this.updateBtDeviceInfo((DeviceItem) msg.obj);
                BtConnection.this.updateLinkDeviceAttributes(0, true, 0, 1);
                BtConnection.this.notifyWirelessDeviceAttach();
                if (BtConnection.this.mPaired) {
                    BtConnection.this.mLinkDevice.setDeviceState(3);
                    BtConnection btConnection = BtConnection.this;
                    btConnection.transitionTo(btConnection.mConnected);
                } else {
                    BtConnection.this.mLinkDevice.setDeviceState(2);
                    BtConnection btConnection2 = BtConnection.this;
                    btConnection2.transitionTo(btConnection2.mDetecting);
                }
            } else if (i == 28) {
                BtConnection.this.mPaired = msg.arg1 == 1;
                BtConnection.this.mLinkDevice.setPaired(BtConnection.this.mPaired);
                BtConnection.this.updateBtDeviceInfo((DeviceItem) msg.obj);
                BtConnection.this.mLinkDevice.setDeviceState(2);
                BtConnection.this.updateLinkDeviceAttributes(0, true, 0, 1);
                BtConnection.this.notifyWirelessDeviceAttach();
                BtConnection btConnection3 = BtConnection.this;
                btConnection3.transitionTo(btConnection3.mDetecting);
            } else if (i == 30) {
                handleProfileChanged(msg);
            }
            return super.processMessage(msg);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String btWhat(int what) {
        if (what == 26) {
            return "BT_DEVICE_PAIRED";
        }
        if (what == 28) {
            return "BT_CALLBACK_UUIDS";
        }
        if (what == 30) {
            return "BT_PROFILE_CHANGED";
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleProfileChanged(Message msg) {
        int newProfile;
        int capa;
        int profile = msg.arg1;
        int state = msg.arg2;
        if (profile != 0) {
            if (profile == 2) {
                newProfile = 4;
            } else {
                newProfile = 58;
            }
        } else {
            newProfile = 1;
        }
        Log.e(TAG, "handleProfileChanged,profile:" + profile + ",state:" + state + ",newProfile:" + newProfile);
        if (state == 2 && newProfile != 1 && newProfile != 4) {
            Log.e(TAG, "handleProfileChanged1");
            this.mLinkDevice.setPaired(true);
            updateBtDeviceInfo((DeviceItem) msg.obj);
            this.mLinkDevice.setDeviceState(3);
            return;
        }
        notifyBtProfileChange(newProfile, state);
        if (newProfile == 1) {
            capa = 2;
        } else {
            capa = 4;
        }
        if (state == 2 && (newProfile == 1 || newProfile == 4)) {
            Log.e(TAG, "handleProfileChanged2");
            this.mLinkDevice.setPaired(true);
            updateBtDeviceInfo((DeviceItem) msg.obj);
            this.mLinkDevice.setDeviceState(3);
            updateLinkDeviceCapability(true, capa);
            notifyWirelessDeviceChanged(3);
        }
        if (state == 0) {
            updateLinkDeviceCapability(false, capa);
            notifyWirelessDeviceChanged(3);
        }
    }

    private class Detecting extends State {
        private Detecting() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void enter() {
            Log.d(BtConnection.TAG, "enter Detecting");
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void exit() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            Log.d(BtConnection.TAG, "Detecting processMessage msg.what:" + msg.what);
            int i = msg.what;
            if (i != 25) {
                if (i != 26) {
                    if (i != 28) {
                        if (i == 30) {
                            BtConnection.this.handleProfileChanged(msg);
                        } else {
                            switch (i) {
                                case 10:
                                    BtConnection.this.mPlatformCapability = msg.arg1;
                                    BtConnection.this.mLinkDevice.setCapability(BtConnection.this.mPlatformCapability & BtConnection.this.mDeviceRealCapability);
                                    BtConnection.this.notifyWirelessDeviceChanged(3);
                                    break;
                                case 11:
                                    BtConnection.this.processApproveConnect();
                                    break;
                                case 12:
                                    Bundle data = msg.getData();
                                    int capability = data.getInt(WirelessMonitorConstants.BUNDLE_CAPABILITY);
                                    int devType = data.getInt(WirelessMonitorConstants.BUNDLE_DEVTYPE);
                                    int isSupportCp = data.getInt(WirelessMonitorConstants.BUNDLE_ISSUPPORTCPBYUUID);
                                    BtConnection.this.updateLinkDeviceAttributes(devType, true, capability, isSupportCp);
                                    if (BtConnection.this.mPaired) {
                                        BtConnection.this.mLinkDevice.setDeviceState(3);
                                        BtConnection.this.notifyWirelessDeviceChanged(3);
                                        BtConnection btConnection = BtConnection.this;
                                        btConnection.transitionTo(btConnection.mConnected);
                                    } else if (capability != BtConnection.this.mDeviceRealCapability || isSupportCp == 2) {
                                        BtConnection.this.notifyWirelessDeviceChanged(3);
                                    } else {
                                        BtConnection.this.notifyWirelessDeviceDetach();
                                        BtConnection btConnection2 = BtConnection.this;
                                        btConnection2.transitionTo(btConnection2.mDisconnected);
                                    }
                                    break;
                            }
                        }
                    } else {
                        BtConnection.this.updateBtDeviceInfo((DeviceItem) msg.obj);
                        BtConnection.this.removeMessages(12);
                    }
                } else {
                    BtConnection.this.mPaired = msg.arg1 == 1;
                    BtConnection.this.mLinkDevice.setPaired(BtConnection.this.mPaired);
                    int newState = msg.arg2;
                    BtConnection.this.updateBtDeviceInfo((DeviceItem) msg.obj);
                    Log.d(BtConnection.TAG, "Detecting BT DEVICE PAIRED, mac: " + LogEncrypt.MacAddressEncrypt(BtConnection.this.mLinkDevice.getBtAddress()) + ", newState:" + newState);
                    if (BtConnection.this.mPaired) {
                        Log.d(BtConnection.TAG, "Detecting state device paired, mac:" + LogEncrypt.MacAddressEncrypt(BtConnection.this.mLinkDevice.getBtAddress()));
                        BtConnection.this.mLinkDevice.setDeviceState(3);
                        BtConnection.this.notifyWirelessDeviceChanged(3);
                        BtConnection btConnection3 = BtConnection.this;
                        btConnection3.transitionTo(btConnection3.mConnected);
                    } else if (newState != 10) {
                        Log.d(BtConnection.TAG, "Detecting state device unpaired, and only scanned before.");
                    } else {
                        BtConnection.this.notifyWirelessDeviceDetach();
                        BtConnection btConnection4 = BtConnection.this;
                        btConnection4.transitionTo(btConnection4.mDisconnected);
                    }
                }
            } else {
                BtConnection.this.notifyBtProfileChange(1, 0);
                BtConnection.this.updateLinkDeviceCapability(false, 2);
                BtConnection.this.notifyWirelessDeviceChanged(3);
                Log.e(BtConnection.TAG, "BT_DEVICE_DISCONNECTED-HFP disconnected" + BtConnection.this.mLinkDevice);
                BtConnection.this.notifyBtProfileChange(4, 0);
                BtConnection.this.updateLinkDeviceCapability(false, 4);
                BtConnection.this.notifyWirelessDeviceChanged(3);
                Log.e(BtConnection.TAG, "BT_DEVICE_DISCONNECTED-A2DP disconnected" + BtConnection.this.mLinkDevice);
                BtConnection.this.notifyWirelessDeviceDetach();
                BtConnection btConnection5 = BtConnection.this;
                btConnection5.transitionTo(btConnection5.mDisconnected);
            }
            return super.processMessage(msg);
        }
    }

    private class Connected extends State {
        private Connected() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void enter() {
            Log.e(BtConnection.TAG, "enter Connected");
            Log.d(BtConnection.TAG, "Connected mac:" + LogEncrypt.IpAddressEncrypt(BtConnection.this.mLinkDevice.getId()) + ", capability:" + BtConnection.this.mLinkDevice.getCapability() + ", realseCapability:" + BtConnection.this.mDeviceRealCapability + ", deviceType:" + CmsConstants.DeviceType.getDeviceTypeDescribe(BtConnection.this.mLinkDevice.getDeviceType()));
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void exit() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            Log.e(BtConnection.TAG, "Connected processMessage msg.what:" + msg.what);
            int i = msg.what;
            if (i != 25) {
                if (i != 26) {
                    if (i != 28) {
                        if (i == 30) {
                            BtConnection.this.handleProfileChanged(msg);
                        } else {
                            switch (i) {
                                case 10:
                                    BtConnection.this.mPlatformCapability = msg.arg1;
                                    BtConnection.this.mLinkDevice.setCapability(BtConnection.this.mPlatformCapability & BtConnection.this.mDeviceRealCapability);
                                    BtConnection.this.notifyWirelessDeviceChanged(3);
                                    break;
                                case 11:
                                    BtConnection.this.processApproveConnect();
                                    break;
                                case 12:
                                    Bundle data = msg.getData();
                                    int capability = data.getInt(WirelessMonitorConstants.BUNDLE_CAPABILITY);
                                    int devType = data.getInt(WirelessMonitorConstants.BUNDLE_DEVTYPE);
                                    int isSupportCp = data.getInt(WirelessMonitorConstants.BUNDLE_ISSUPPORTCPBYUUID);
                                    if (capability != BtConnection.this.mDeviceRealCapability || isSupportCp == 2) {
                                        BtConnection.this.updateLinkDeviceAttributes(devType, true, capability, isSupportCp);
                                        BtConnection.this.notifyWirelessDeviceChanged(3);
                                    } else {
                                        Log.d(BtConnection.TAG, "Connected device detected no change in capability, mac:" + LogEncrypt.MacAddressEncrypt(BtConnection.this.mLinkDevice.getBtAddress()));
                                    }
                                    break;
                            }
                        }
                    } else {
                        BtConnection.this.updateBtDeviceInfo((DeviceItem) msg.obj);
                        Log.d(BtConnection.TAG, "Connected get UUID event recheck iap2 capability.");
                    }
                } else {
                    BtConnection.this.mPaired = msg.arg1 == 1;
                    BtConnection.this.mLinkDevice.setPaired(BtConnection.this.mPaired);
                    int newState = msg.arg2;
                    BtConnection.this.updateBtDeviceInfo((DeviceItem) msg.obj);
                    Log.d(BtConnection.TAG, "Connected BT DEVICE PAIRED, mac: " + LogEncrypt.MacAddressEncrypt(BtConnection.this.mLinkDevice.getBtAddress()) + ", newState:" + newState);
                    if (!BtConnection.this.mPaired) {
                        if (newState == 10) {
                            BtConnection.this.notifyWirelessDeviceDetach();
                            BtConnection btConnection = BtConnection.this;
                            btConnection.transitionTo(btConnection.mDisconnected);
                        } else {
                            BtConnection btConnection2 = BtConnection.this;
                            btConnection2.transitionTo(btConnection2.mDetecting);
                        }
                    }
                }
            } else {
                BtConnection.this.notifyBtProfileChange(1, 0);
                BtConnection.this.updateLinkDeviceCapability(false, 2);
                BtConnection.this.notifyWirelessDeviceChanged(3);
                Log.e(BtConnection.TAG, "BT_DEVICE_DISCONNECTED-HFP disconnected" + BtConnection.this.mLinkDevice);
                BtConnection.this.notifyBtProfileChange(4, 0);
                BtConnection.this.updateLinkDeviceCapability(false, 4);
                BtConnection.this.notifyWirelessDeviceChanged(3);
                Log.e(BtConnection.TAG, "BT_DEVICE_DISCONNECTED-A2DP disconnected" + BtConnection.this.mLinkDevice);
                BtConnection.this.notifyWirelessDeviceDetach();
                BtConnection btConnection3 = BtConnection.this;
                btConnection3.transitionTo(btConnection3.mDisconnected);
            }
            return super.processMessage(msg);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateLinkDeviceAttributes(int devType, boolean addCapability, int capability, int isSupportCp) {
        Log.d(TAG, "updateLinkDeviceAttributes, devType: " + devType + ", isSupportCp: " + isSupportCp);
        this.mLinkDevice.setDeviceType(devType);
        if (isSupportCp == 2) {
            this.mLinkDevice.setSupportCpByUuid(true);
        }
        updateLinkDeviceCapability(addCapability, capability);
    }

    @Override // com.ts.car.dcm.wirelesscontroller.WirelessConnection
    public void notifyWirelessDeviceAttach() {
        Log.d(TAG, "notifyWirelessDeviceAttach:" + this.mDeviceItem.address);
        this.mLinkDevice.setId(this.mDeviceItem.address);
        this.mLinkDevice.setFriendlyName(this.mDeviceItem.name);
        this.mLinkDevice.setBtAddress(this.mDeviceItem.address);
        this.mLinkDevice.setConnectionType(2);
        if (this.mPaired) {
            this.mLinkDevice.setDeviceState(3);
        }
        this.mBtDeviceMonitor.processWirelessDeviceAttach(this.mLinkDevice);
    }

    @Override // com.ts.car.dcm.wirelesscontroller.WirelessConnection
    public void notifyWirelessDeviceChanged(int reason) {
        if (this.mDeviceApproved) {
            notifyUnApprovedWirelessDeviceChanged(reason);
        }
    }

    public void notifyUnApprovedWirelessDeviceChanged(int reason) {
        int deviceCapability = this.mLinkDevice.getCapability();
        int deviceState = this.mLinkDevice.getDeviceState();
        Log.d(TAG, "notifyUnApprovedWirelessDeviceChanged, mLastReportCapability:" + this.mLastReportCapability + ", capability:" + deviceCapability + ", mLastReportDeviceState:" + this.mLastReportDeviceState + ", deviceState:" + deviceState + ", reason:" + reason);
        this.mLastReportCapability = deviceCapability;
        this.mLastReportDeviceState = deviceState;
        this.mBtDeviceMonitor.processWirelessDeviceChange(this.mLinkDevice, reason);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyBtProfileChange(int profile, int connState) {
        this.mBtDeviceMonitor.processBtConnProfileStateChanged(this.mLinkDevice.getId(), profile, connState);
    }

    @Override // com.ts.car.dcm.wirelesscontroller.WirelessConnection
    public void notifyWirelessDeviceDetach() {
        this.mLinkDevice.setDeviceState(4);
        this.mBtDeviceMonitor.processWirelessDeviceDetach(this.mLinkDevice);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBtDeviceInfo(DeviceItem deviceItem) {
        if (deviceItem != null) {
            this.mDeviceItem = deviceItem;
            this.mLinkDevice.setFriendlyName(this.mDeviceItem.name);
        } else {
            Log.e(TAG, "updateBtDeviceInfo, deviceItem is null.");
        }
    }
}
