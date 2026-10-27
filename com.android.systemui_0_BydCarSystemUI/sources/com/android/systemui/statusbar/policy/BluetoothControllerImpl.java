package com.android.systemui.statusbar.policy;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClientCall;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.policy.BluetoothController;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothControllerImpl implements BluetoothController {
    private static final int A2DP_SINK = 11;
    private static final int HEADSET_CLIENT = 16;
    public static final int INVALID = -1;
    private static final int PAN = 5;
    private static final String TAG = "BluetoothControllerImpl";
    private Context context;
    private BluetoothAdapter mAdapter;
    private BluetoothProfile mBtProxy;
    private boolean mediaConnected;
    private boolean panConnected;
    private boolean phoneConnected;
    private BluetoothReceiver receiver;
    private boolean isHfp = false;
    private boolean isA2dp = false;
    private boolean isEnable = false;
    private final ArrayList<BluetoothController.Callback> mCallbacks = new ArrayList<>();
    private int mState = Integer.MIN_VALUE;
    private int mConnectionState = 0;
    private int mCallState = 7;
    protected BluetoothProfile.ServiceListener mPhoneServiceListener = new BluetoothProfile.ServiceListener() { // from class: com.android.systemui.statusbar.policy.BluetoothControllerImpl.1
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            BydLogHelper.logD("profile = " + profile);
            BluetoothControllerImpl.this.mBtProxy = proxy;
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int profile) {
            BydLogHelper.logD("profile = " + profile);
            BluetoothControllerImpl.this.mBtProxy = null;
        }
    };
    protected BluetoothProfile.ServiceListener mMediaServiceListener = new BluetoothProfile.ServiceListener() { // from class: com.android.systemui.statusbar.policy.BluetoothControllerImpl.2
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            BydLogHelper.logD("profile = " + profile);
            BluetoothControllerImpl.this.mediaConnected = BluetoothControllerImpl.this.handlerServiceConnected(proxy);
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int profile) {
            BydLogHelper.logD("profile = " + profile);
        }
    };
    protected BluetoothProfile.ServiceListener mPanServiceListener = new BluetoothProfile.ServiceListener() { // from class: com.android.systemui.statusbar.policy.BluetoothControllerImpl.3
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            BydLogHelper.logD("profile = " + profile);
            BluetoothControllerImpl.this.panConnected = BluetoothControllerImpl.this.handlerServiceConnected(proxy);
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int profile) {
            BydLogHelper.logD("profile = " + profile);
        }
    };

    @Override // com.android.systemui.Dumpable
    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
    }

    public BluetoothControllerImpl(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mAdapter = defaultAdapter;
        defaultAdapter.getProfileProxy(this.context, this.mPhoneServiceListener, 16);
        registerReceiver();
    }

    private void unInit() {
        this.mCallState = 7;
        this.mAdapter = null;
        unRegisterReceiver();
    }

    private void registerReceiver() {
        this.receiver = new BluetoothReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.headsetclient.profile.action.AG_CALL_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        this.context.getApplicationContext().registerReceiver(this.receiver, intentFilter);
    }

    private void unRegisterReceiver() {
        this.context.getApplicationContext().unregisterReceiver(this.receiver);
        this.receiver = null;
    }

    @Override // com.android.systemui.statusbar.policy.BluetoothController
    public void setEnable(boolean enable) {
        if (this.mAdapter != null) {
            BydLogHelper.logD("setEnable:" + enable);
            if (enable) {
                this.mAdapter.enable();
                return;
            } else {
                this.mAdapter.disable();
                return;
            }
        }
        BydLogHelper.logD("mAdapter is null");
    }

    @Override // com.android.systemui.statusbar.policy.BluetoothController
    public boolean isConnected() {
        return getConnectedDevice() != null;
    }

    private boolean isBondedDeviceConn() {
        Set<BluetoothDevice> bondedDevices = this.mAdapter.getBondedDevices();
        if (bondedDevices != null && bondedDevices.size() > 0) {
            for (BluetoothDevice bluetoothDevice : bondedDevices) {
                if (bluetoothDevice.isConnected()) {
                    BydLogHelper.logD("isBondedDeviceConn name:" + bluetoothDevice.getName());
                    return true;
                }
            }
        }
        BydLogHelper.logD("isBondedDeviceConn null");
        return false;
    }

    private int getBluetoothState() {
        int state = this.mAdapter.getState();
        this.mState = state;
        return state;
    }

    private int getConnectionState() {
        this.mConnectionState = this.mAdapter.getConnectionState();
        BydLogHelper.logD("mConnectionState:" + this.mConnectionState);
        return this.mConnectionState;
    }

    @Override // com.android.systemui.statusbar.policy.BluetoothController
    public BluetoothDevice getConnectedDevice() {
        BluetoothAdapter bluetoothAdapter = this.mAdapter;
        if (bluetoothAdapter == null) {
            BydLogHelper.logD("mAdapter is null");
            return null;
        }
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
        if (bondedDevices != null && bondedDevices.size() > 0) {
            if (this.mBtProxy != null) {
                for (BluetoothDevice bluetoothDevice : bondedDevices) {
                    int connectionState = this.mBtProxy.getConnectionState(bluetoothDevice);
                    BydLogHelper.logD("hfpStatus:" + connectionState);
                    if (connectionState == 2 && bluetoothDevice.isConnected()) {
                        BydLogHelper.logD("mBtProxy  STATE_CONNECTED");
                        return bluetoothDevice;
                    }
                }
            }
            for (BluetoothDevice bluetoothDevice2 : bondedDevices) {
                if (bluetoothDevice2.isConnected()) {
                    return bluetoothDevice2;
                }
            }
        }
        return null;
    }

    @Override // com.android.systemui.statusbar.policy.BluetoothController
    public boolean isPhoneEnable() {
        int profileConnectionState = this.mAdapter.getProfileConnectionState(16);
        if (profileConnectionState == 2 || profileConnectionState == 1) {
            this.phoneConnected = true;
        } else {
            this.phoneConnected = false;
        }
        BydLogHelper.logD("phone status:" + profileConnectionState);
        return this.phoneConnected;
    }

    @Override // com.android.systemui.statusbar.policy.BluetoothController
    public boolean isMediaEnable() {
        int profileConnectionState = this.mAdapter.getProfileConnectionState(11);
        if (profileConnectionState == 2) {
            this.mediaConnected = true;
        } else {
            this.mediaConnected = false;
        }
        BydLogHelper.logD("media status:" + profileConnectionState);
        return this.mediaConnected;
    }

    @Override // com.android.systemui.statusbar.policy.BluetoothController
    public boolean isPanEnable() {
        int profileConnectionState = this.mAdapter.getProfileConnectionState(5);
        if (profileConnectionState == 2) {
            this.panConnected = true;
        } else {
            this.panConnected = false;
        }
        BydLogHelper.logD("pan status:" + profileConnectionState);
        return this.panConnected;
    }

    @Override // com.android.systemui.statusbar.policy.BluetoothController
    public boolean isEnable() {
        BluetoothAdapter bluetoothAdapter = this.mAdapter;
        if (bluetoothAdapter == null) {
            BydLogHelper.logD("mAdapter is null");
            return false;
        }
        boolean zIsEnabled = bluetoothAdapter.isEnabled();
        BydLogHelper.logD("bt isEnable:" + zIsEnabled);
        return zIsEnabled;
    }

    @Override // com.android.systemui.statusbar.policy.BluetoothController
    public boolean onCalling() {
        BydLogHelper.logD("onCalling mCallState:" + this.mCallState);
        if (this.mCallState == 7) {
            return false;
        }
        if (isConnected()) {
            return true;
        }
        BydLogHelper.logD("not connected , not calling");
        this.mCallState = 7;
        return false;
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public void addCallback(BluetoothController.Callback callback) {
        synchronized (this.mCallbacks) {
            if (callback != null) {
                if (!this.mCallbacks.contains(callback)) {
                    this.mCallbacks.add(callback);
                }
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public void removeCallback(BluetoothController.Callback callback) {
        synchronized (this.mCallbacks) {
            if (callback != null) {
                if (this.mCallbacks.contains(callback)) {
                    this.mCallbacks.remove(callback);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseBluetoothState(Intent intent) {
        int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0);
        intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", 0);
        BydLogHelper.logD("parseBluetoothState blueNewState：" + intExtra);
        if (intExtra == 10) {
            this.mCallState = 7;
            synchronized (this.mCallbacks) {
                Iterator<BluetoothController.Callback> it = this.mCallbacks.iterator();
                while (it.hasNext()) {
                    it.next().onEnableChanged(false);
                }
            }
            return;
        }
        if (intExtra != 12) {
            return;
        }
        synchronized (this.mCallbacks) {
            Iterator<BluetoothController.Callback> it2 = this.mCallbacks.iterator();
            while (it2.hasNext()) {
                it2.next().onEnableChanged(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseBluetoothConnect(Intent intent) {
        int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.CONNECTION_STATE", 0);
        intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_CONNECTION_STATE", 0);
        BydLogHelper.logD("parseBluetoothConnect newConnState：" + intExtra);
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (intExtra == 0) {
            this.mCallState = 7;
            synchronized (this.mCallbacks) {
                Iterator<BluetoothController.Callback> it = this.mCallbacks.iterator();
                while (it.hasNext()) {
                    it.next().onConnectedChanged(false, bluetoothDevice != null ? bluetoothDevice.getAddress() : null);
                }
            }
            return;
        }
        if (intExtra != 2) {
            return;
        }
        synchronized (this.mCallbacks) {
            Iterator<BluetoothController.Callback> it2 = this.mCallbacks.iterator();
            while (it2.hasNext()) {
                it2.next().onConnectedChanged(isConnected(), bluetoothDevice != null ? bluetoothDevice.getAddress() : null);
            }
        }
    }

    private class BluetoothReceiver extends BroadcastReceiver {
        private BluetoothReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BydLogHelper.logD("action:" + action);
            action.hashCode();
            switch (action) {
                case "android.bluetooth.adapter.action.STATE_CHANGED":
                    BluetoothControllerImpl.this.parseBluetoothState(intent);
                    break;
                case "android.bluetooth.headsetclient.profile.action.AG_CALL_CHANGED":
                    BluetoothControllerImpl.this.parseBluePhoneStatus(intent);
                    break;
                case "android.bluetooth.device.action.ACL_CONNECTED":
                    BluetoothControllerImpl.this.onBtAclChange(intent, true);
                    break;
                case "android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED":
                    BluetoothControllerImpl.this.parseBluetoothProfileConnectForPhone(intent);
                    break;
                case "android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED":
                    BluetoothControllerImpl.this.parseBluetoothConnect(intent);
                    break;
                case "android.bluetooth.device.action.ACL_DISCONNECTED":
                    BluetoothControllerImpl.this.onBtAclChange(intent, false);
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBtAclChange(Intent intent, boolean isConnected) {
        BydLogHelper.logD("onBtAclChange acl isConnected: " + isConnected);
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (bluetoothDevice != null) {
            BydLogHelper.logD("onBtAclChange device isConnected: " + bluetoothDevice.isConnected());
        }
        synchronized (this.mCallbacks) {
            Iterator<BluetoothController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onConnectedChanged(isConnected, bluetoothDevice != null ? bluetoothDevice.getAddress() : null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseBluetoothProfileConnectForPhone(Intent intent) {
        int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
        int intExtra2 = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        BydLogHelper.logD("phone:state = " + intExtra + ", preState = " + intExtra2);
        if (intExtra == 2) {
            synchronized (this.mCallbacks) {
                Iterator<BluetoothController.Callback> it = this.mCallbacks.iterator();
                while (it.hasNext()) {
                    it.next().onConnectedChanged(true, bluetoothDevice != null ? bluetoothDevice.getAddress() : null);
                }
            }
            return;
        }
        if (intExtra == 0) {
            BydLogHelper.logD("handle HeadsetClient --> mCallState");
            this.mCallState = 7;
            synchronized (this.mCallbacks) {
                Iterator<BluetoothController.Callback> it2 = this.mCallbacks.iterator();
                while (it2.hasNext()) {
                    it2.next().onConnectedChanged(isConnected(), bluetoothDevice != null ? bluetoothDevice.getAddress() : null);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseBluePhoneStatus(Intent intent) {
        BluetoothHeadsetClientCall bluetoothHeadsetClientCall = (BluetoothHeadsetClientCall) intent.getExtra("android.bluetooth.headsetclient.extra.CALL");
        dismissQSPanel(bluetoothHeadsetClientCall.getState());
        this.mCallState = bluetoothHeadsetClientCall.getState();
        BydLogHelper.logD("call state = " + this.mCallState);
        synchronized (this.mCallbacks) {
            Iterator<BluetoothController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onCallStateChanged(this.mCallState);
            }
        }
    }

    private void dismissQSPanel(int state) {
        BydLogHelper.logD("mCallState = " + this.mCallState + " state = " + state);
        if ((state == 4 || state == 2 || state == 3) && this.mCallState != state) {
            ((StatusBar) SysUiServiceProvider.getComponent(this.context, StatusBar.class)).closePanelsIfNeed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean handlerServiceConnected(BluetoothProfile proxy) {
        for (BluetoothDevice bluetoothDevice : proxy.getConnectedDevices()) {
            if (bluetoothDevice != null && !TextUtils.isEmpty(bluetoothDevice.getAddress())) {
                return true;
            }
        }
        return false;
    }
}
