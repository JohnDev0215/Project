package com.ts.dm.lib.connectionmanager;

import com.ts.car.sdk.dm.data.ConnectRequest;
import com.ts.car.sdk.dm.data.DmDevice;
import com.ts.dm.lib.devicemanager.DeviceManager;

/* JADX INFO: loaded from: classes.dex */
public class ConnectionStateManager implements DeviceManager.OnDeviceChangedListener {
    private static final String TAG = "ConnectionStateManager";
    private DeviceManager mDeviceManager;
    private IdelState mIdelState = new IdelState();
    private ConnectingState mConnectingState = new ConnectingState();
    private ConnectedState mConnectedState = new ConnectedState();
    private DisconnectingState mDisconnectingState = new DisconnectingState();
    private BaseConnectState mCurrentState = this.mIdelState;
    private DmDevice mActiveDevice = null;

    public ConnectionStateManager(DeviceManager deviceManager) {
        this.mDeviceManager = deviceManager;
        deviceManager.addOnDeviceChangedListener(this);
    }

    private void setActiveDevice(DmDevice device) {
        this.mActiveDevice = device;
    }

    public DmDevice getActiveDevice() {
        return this.mActiveDevice;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentState(BaseConnectState state) {
        if (this.mCurrentState == state) {
            return;
        }
        this.mCurrentState = state;
        state.enter();
    }

    public boolean isIdle() {
        return this.mCurrentState == this.mIdelState;
    }

    public boolean isConnecting() {
        return this.mCurrentState == this.mConnectingState;
    }

    public boolean isConnected() {
        return this.mCurrentState == this.mConnectedState;
    }

    public boolean isDisconnecting() {
        return this.mCurrentState == this.mDisconnectingState;
    }

    public boolean enterIdleState() {
        if (isIdle()) {
            return false;
        }
        this.mCurrentState.enterIdleState();
        return true;
    }

    public boolean enterConnectingState() {
        if (isConnecting()) {
            return false;
        }
        this.mCurrentState.enterConnectingState();
        return true;
    }

    public boolean enterConnectedState() {
        if (isConnected()) {
            return false;
        }
        this.mCurrentState.enterConnectedState();
        return true;
    }

    public boolean enterDisconnectingState() {
        if (isDisconnecting()) {
            return false;
        }
        this.mCurrentState.enterDisconnectingState();
        return true;
    }

    @Override // com.ts.dm.lib.devicemanager.DeviceManager.OnDeviceChangedListener
    public void onDeviceStatusChanged(DmDevice device) {
        DmDevice dmDevice = this.mActiveDevice;
        if (dmDevice == null || dmDevice != device) {
            if (checkProjectionState(device, 3)) {
                setActiveDevice(device);
                enterConnectingState();
                return;
            } else {
                if (checkProjectionState(device, 4)) {
                    setActiveDevice(device);
                    enterConnectedState();
                    return;
                }
                return;
            }
        }
        if (checkProjectionState(device, 4)) {
            setActiveDevice(device);
            enterConnectedState();
        } else if (checkProjectionState(device, 3)) {
            setActiveDevice(device);
            enterConnectingState();
        } else if (checkProjectionState(device, 5)) {
            setActiveDevice(device);
            enterDisconnectingState();
        } else {
            setActiveDevice(null);
            enterIdleState();
        }
    }

    @Override // com.ts.dm.lib.devicemanager.DeviceManager.OnDeviceChangedListener
    public void onDeviceProjectionRequested(boolean supported, ConnectRequest request) {
    }

    @Override // com.ts.dm.lib.devicemanager.DeviceManager.OnDeviceChangedListener
    public void onDeviceDetachedOverUsb(DmDevice device) {
    }

    private boolean checkProjectionState(DmDevice device, int checkState) {
        return device.getAndroidAutoProjectionState() == checkState || device.getCarlifeProjectionState() == checkState || device.getCarPlayProjectionState() == checkState || device.getAndroidAutoProjectionState() == checkState;
    }

    public class IdelState extends BaseConnectState {
        public IdelState() {
            super();
        }

        @Override // com.ts.dm.lib.connectionmanager.ConnectionStateManager.BaseConnectState
        public void enter() {
        }

        @Override // com.ts.dm.lib.connectionmanager.ConnectionStateManager.BaseConnectState
        public void enterConnectingState() {
            ConnectionStateManager connectionStateManager = ConnectionStateManager.this;
            connectionStateManager.setCurrentState(connectionStateManager.mConnectingState);
        }

        @Override // com.ts.dm.lib.connectionmanager.ConnectionStateManager.BaseConnectState
        public void enterConnectedState() {
            ConnectionStateManager connectionStateManager = ConnectionStateManager.this;
            connectionStateManager.setCurrentState(connectionStateManager.mConnectedState);
        }

        @Override // com.ts.dm.lib.connectionmanager.ConnectionStateManager.BaseConnectState
        public void enterDisconnectingState() {
            ConnectionStateManager connectionStateManager = ConnectionStateManager.this;
            connectionStateManager.setCurrentState(connectionStateManager.mDisconnectingState);
        }
    }

    public class ConnectingState extends BaseConnectState {
        public ConnectingState() {
            super();
        }

        @Override // com.ts.dm.lib.connectionmanager.ConnectionStateManager.BaseConnectState
        public void enter() {
        }

        @Override // com.ts.dm.lib.connectionmanager.ConnectionStateManager.BaseConnectState
        public void enterIdleState() {
            ConnectionStateManager connectionStateManager = ConnectionStateManager.this;
            connectionStateManager.setCurrentState(connectionStateManager.mIdelState);
        }

        @Override // com.ts.dm.lib.connectionmanager.ConnectionStateManager.BaseConnectState
        public void enterConnectedState() {
            ConnectionStateManager connectionStateManager = ConnectionStateManager.this;
            connectionStateManager.setCurrentState(connectionStateManager.mConnectedState);
        }

        @Override // com.ts.dm.lib.connectionmanager.ConnectionStateManager.BaseConnectState
        public void enterDisconnectingState() {
            ConnectionStateManager connectionStateManager = ConnectionStateManager.this;
            connectionStateManager.setCurrentState(connectionStateManager.mDisconnectingState);
        }
    }

    public class ConnectedState extends BaseConnectState {
        public ConnectedState() {
            super();
        }

        @Override // com.ts.dm.lib.connectionmanager.ConnectionStateManager.BaseConnectState
        public void enter() {
        }

        @Override // com.ts.dm.lib.connectionmanager.ConnectionStateManager.BaseConnectState
        public void enterIdleState() {
            ConnectionStateManager connectionStateManager = ConnectionStateManager.this;
            connectionStateManager.setCurrentState(connectionStateManager.mIdelState);
        }

        @Override // com.ts.dm.lib.connectionmanager.ConnectionStateManager.BaseConnectState
        public void enterConnectingState() {
            ConnectionStateManager connectionStateManager = ConnectionStateManager.this;
            connectionStateManager.setCurrentState(connectionStateManager.mConnectingState);
        }

        @Override // com.ts.dm.lib.connectionmanager.ConnectionStateManager.BaseConnectState
        public void enterDisconnectingState() {
            ConnectionStateManager connectionStateManager = ConnectionStateManager.this;
            connectionStateManager.setCurrentState(connectionStateManager.mDisconnectingState);
        }
    }

    public class DisconnectingState extends BaseConnectState {
        public DisconnectingState() {
            super();
        }

        @Override // com.ts.dm.lib.connectionmanager.ConnectionStateManager.BaseConnectState
        public void enter() {
        }

        @Override // com.ts.dm.lib.connectionmanager.ConnectionStateManager.BaseConnectState
        public void enterIdleState() {
            ConnectionStateManager connectionStateManager = ConnectionStateManager.this;
            connectionStateManager.setCurrentState(connectionStateManager.mIdelState);
        }

        @Override // com.ts.dm.lib.connectionmanager.ConnectionStateManager.BaseConnectState
        public void enterConnectingState() {
            ConnectionStateManager connectionStateManager = ConnectionStateManager.this;
            connectionStateManager.setCurrentState(connectionStateManager.mConnectingState);
        }

        @Override // com.ts.dm.lib.connectionmanager.ConnectionStateManager.BaseConnectState
        public void enterConnectedState() {
            ConnectionStateManager connectionStateManager = ConnectionStateManager.this;
            connectionStateManager.setCurrentState(connectionStateManager.mConnectedState);
        }
    }

    public class BaseConnectState {
        public BaseConnectState() {
        }

        public void enter() {
        }

        public void enterIdleState() {
        }

        public void enterConnectingState() {
        }

        public void enterDisconnectingState() {
        }

        public void enterConnectedState() {
        }
    }
}
