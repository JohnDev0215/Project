package com.ts.dm.lib.devicemanager.bluetooth;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.ts.car.core.FoundationProvider;
import com.ts.car.sdk.dm.DmConsumer;
import com.ts.car.sdk.dm.data.DmDevice;
import com.ts.dm.lib.devicemanager.bluetooth.IBluetoothDeviceManager;
import com.ts.dm.lib.util.DmListenerManager;
import java.util.HashMap;
import java.util.List;
import ts.car.dm.fsdk.interfaces.BluetoothDevice;
import ts.car.dm.fsdk.interfaces.IBluetoothListener;
import ts.car.dm.fsdk.interfaces.IBluetoothManger;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothDeviceManager implements IBluetoothDeviceManager, IBluetoothListener {
    private static final String OPERATE_CONNECT = "CONNECT";
    private static final String OPERATE_DISCONNECT = "DISCONNECT";
    private static final int OPERATE_PROFILE_TIME_OUT = 10000;
    private static final String TAG = "BluetoothDeviceManager";
    private Handler mHandler;
    private IBluetoothManger mIBluetoothManger;
    private DmListenerManager<IBluetoothDeviceManager.OnBluetoothDeviceStatusChangedListener> mDmListenerManager = new DmListenerManager<>();
    private final HashMap<String, Object> mOperateDevices = new HashMap<>();

    public BluetoothDeviceManager(Context context, Looper looper) {
        this.mHandler = new Handler(looper);
        this.mIBluetoothManger = (IBluetoothManger) FoundationProvider.create(IBluetoothManger.class, context, this);
    }

    @Override // com.ts.dm.lib.devicemanager.bluetooth.IBluetoothDeviceManager
    public void registerOnBluetoothDeviceStatusChangedListener(IBluetoothDeviceManager.OnBluetoothDeviceStatusChangedListener listener) {
        this.mDmListenerManager.registerListener(listener);
    }

    @Override // com.ts.dm.lib.devicemanager.bluetooth.IBluetoothDeviceManager
    public void unregisterOnBluetoothDeviceStatusChangedListener(IBluetoothDeviceManager.OnBluetoothDeviceStatusChangedListener listener) {
        this.mDmListenerManager.unregisterDmListener(listener);
    }

    @Override // com.ts.dm.lib.devicemanager.bluetooth.IBluetoothDeviceManager
    public List<BluetoothDevice> getBondedDevices() {
        return this.mIBluetoothManger.getBondedDevices();
    }

    @Override // com.ts.dm.lib.devicemanager.bluetooth.IBluetoothDeviceManager
    public int connectProfile(DmDevice dmDevice, int type) {
        if (dmDevice == null) {
            return 0;
        }
        if (getProfileState(dmDevice, type) == 3 || getProfileState(dmDevice, type) == 4) {
            return 1;
        }
        synchronized (this.mOperateDevices) {
            if (this.mOperateDevices.containsKey(generateToken(dmDevice, OPERATE_CONNECT, type))) {
                return 1;
            }
            int result = this.mIBluetoothManger.connectProfile(dmDevice.getMacAddress(), type);
            if (result == 1) {
                if (type == 1 || type == 2) {
                    onDeviceProfileStatusChanged(createBluetoothDevice(dmDevice, type, 3), type);
                }
                startConnectTimeoutTask(dmDevice, type);
            }
            return result;
        }
    }

    private void startConnectTimeoutTask(final DmDevice dmDevice, final int profile) {
        final String tokenKey = generateToken(dmDevice, OPERATE_CONNECT, profile);
        Object token = new Object();
        this.mHandler.postDelayed(new Runnable() { // from class: com.ts.dm.lib.devicemanager.bluetooth.BluetoothDeviceManager.1
            @Override // java.lang.Runnable
            public void run() {
                BluetoothDeviceManager bluetoothDeviceManager = BluetoothDeviceManager.this;
                DmDevice dmDevice2 = dmDevice;
                int i = profile;
                bluetoothDeviceManager.onDeviceProfileStatusChanged(bluetoothDeviceManager.createBluetoothDevice(dmDevice2, i, bluetoothDeviceManager.getBluetoothDeviceProfileStatus(dmDevice2, i)), profile);
                synchronized (BluetoothDeviceManager.this.mOperateDevices) {
                    BluetoothDeviceManager.this.mOperateDevices.remove(tokenKey);
                }
            }
        }, token, 10000L);
        synchronized (this.mOperateDevices) {
            this.mOperateDevices.put(tokenKey, token);
        }
    }

    @Override // com.ts.dm.lib.devicemanager.bluetooth.IBluetoothDeviceManager
    public int disconnectProfile(DmDevice dmDevice, int type) {
        if (dmDevice == null) {
            return 0;
        }
        if (getProfileState(dmDevice, type) != 3 && getProfileState(dmDevice, type) != 4) {
            return 1;
        }
        synchronized (this.mOperateDevices) {
            if (this.mOperateDevices.containsKey(generateToken(dmDevice, OPERATE_DISCONNECT, type))) {
                return 1;
            }
            int result = this.mIBluetoothManger.disconnectProfile(dmDevice.getMacAddress(), type);
            if (result == 1) {
                if (type == 1 || type == 2) {
                    onDeviceProfileStatusChanged(createBluetoothDevice(dmDevice, type, 2), type);
                }
                startDisconnectTimeoutTask(dmDevice, type);
            }
            return result;
        }
    }

    private void startDisconnectTimeoutTask(final DmDevice dmDevice, final int profile) {
        final String tokenKey = generateToken(dmDevice, OPERATE_DISCONNECT, profile);
        Object token = new Object();
        this.mHandler.postDelayed(new Runnable() { // from class: com.ts.dm.lib.devicemanager.bluetooth.BluetoothDeviceManager.2
            @Override // java.lang.Runnable
            public void run() {
                BluetoothDeviceManager bluetoothDeviceManager = BluetoothDeviceManager.this;
                DmDevice dmDevice2 = dmDevice;
                int i = profile;
                bluetoothDeviceManager.onDeviceProfileStatusChanged(bluetoothDeviceManager.createBluetoothDevice(dmDevice2, i, bluetoothDeviceManager.getBluetoothDeviceProfileStatus(dmDevice2, i)), profile);
                synchronized (BluetoothDeviceManager.this.mOperateDevices) {
                    BluetoothDeviceManager.this.mOperateDevices.remove(tokenKey);
                }
            }
        }, token, 10000L);
        synchronized (this.mOperateDevices) {
            this.mOperateDevices.put(tokenKey, token);
        }
    }

    @Override // com.ts.dm.lib.devicemanager.bluetooth.IBluetoothDeviceManager
    public boolean removeBond(String macAddress) {
        return this.mIBluetoothManger.removeBond(macAddress);
    }

    private int getProfileState(DmDevice dmDevice, int profile) {
        if (profile == 1) {
            return dmDevice.getHfpState();
        }
        if (profile == 2) {
            return dmDevice.getA2dpState();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BluetoothDevice createBluetoothDevice(DmDevice dmDevice, int profile, int status) {
        BluetoothDevice target = new BluetoothDevice();
        target.setName(dmDevice.getFriendlyName());
        target.setMacAddress(dmDevice.getMacAddress());
        target.putProfileStatus(profile, status);
        return target;
    }

    private String generateToken(DmDevice dmDevice, String operate, int profile) {
        StringBuilder sb = new StringBuilder(dmDevice.getMacAddress());
        sb.append(":");
        sb.append(operate);
        sb.append(":");
        StringBuilder stringBuilder = sb.append(profile);
        return stringBuilder.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getBluetoothDeviceProfileStatus(DmDevice dmDevice, int profile) {
        return this.mIBluetoothManger.getProfileConnectionState(dmDevice.getMacAddress(), profile);
    }

    @Override // ts.car.dm.fsdk.interfaces.IBluetoothListener
    public void onBluetoothStateChanged(final int state) {
        this.mDmListenerManager.broadcastListener(new DmConsumer.ReConsumer() { // from class: com.ts.dm.lib.devicemanager.bluetooth.-$$Lambda$BluetoothDeviceManager$hNRxU2xGvVIkCYE9GjRhRXA2jTg
            public final void accept(Object obj) {
                ((IBluetoothDeviceManager.OnBluetoothDeviceStatusChangedListener) obj).onBluetoothStateChanged(state);
            }
        });
    }

    @Override // ts.car.dm.fsdk.interfaces.IBluetoothListener
    public void onDeviceBondStatusChanged(final BluetoothDevice device, final int bondStatus) {
        this.mDmListenerManager.broadcastListener(new DmConsumer.ReConsumer() { // from class: com.ts.dm.lib.devicemanager.bluetooth.-$$Lambda$BluetoothDeviceManager$XYBOQkYxWg2NF75Nb0Moewwqh0I
            public final void accept(Object obj) {
                ((IBluetoothDeviceManager.OnBluetoothDeviceStatusChangedListener) obj).onDeviceBondStatusChanged(device, bondStatus);
            }
        });
    }

    @Override // ts.car.dm.fsdk.interfaces.IBluetoothListener
    public void onDeviceProfileStatusChanged(final BluetoothDevice device, final int profile) {
        this.mDmListenerManager.broadcastListener(new DmConsumer.ReConsumer() { // from class: com.ts.dm.lib.devicemanager.bluetooth.-$$Lambda$BluetoothDeviceManager$kTXpNrDWOLnaQM2SznTV7BeviJY
            public final void accept(Object obj) {
                ((IBluetoothDeviceManager.OnBluetoothDeviceStatusChangedListener) obj).onDeviceProfileStatusChanged(device, profile);
            }
        });
    }

    @Override // ts.car.dm.fsdk.interfaces.IBluetoothListener
    public void onDeviceNameChanged(final BluetoothDevice device) {
        this.mDmListenerManager.broadcastListener(new DmConsumer.ReConsumer() { // from class: com.ts.dm.lib.devicemanager.bluetooth.-$$Lambda$BluetoothDeviceManager$r4A17K_r6IEde5pAIuX9oYUTn4c
            public final void accept(Object obj) {
                ((IBluetoothDeviceManager.OnBluetoothDeviceStatusChangedListener) obj).onDeviceNameChanged(device);
            }
        });
    }

    @Override // com.ts.dm.lib.devicemanager.bluetooth.IBluetoothDeviceManager
    public void checkProfileConnectStatus(DmDevice device) {
        if (this.mOperateDevices.isEmpty()) {
            return;
        }
        checkProfileConnectStatus(device, OPERATE_CONNECT, 1);
        checkProfileConnectStatus(device, OPERATE_DISCONNECT, 1);
        checkProfileConnectStatus(device, OPERATE_CONNECT, 2);
        checkProfileConnectStatus(device, OPERATE_DISCONNECT, 2);
    }

    private void checkProfileConnectStatus(DmDevice dmDevice, String operate, int profile) {
        String token = generateToken(dmDevice, operate, profile);
        synchronized (this.mOperateDevices) {
            if (this.mOperateDevices.containsKey(token)) {
                int status = getProfileState(dmDevice, profile);
                if (operate.equals(OPERATE_CONNECT)) {
                    if (status != 3) {
                        this.mHandler.removeCallbacksAndMessages(this.mOperateDevices.get(token));
                        this.mOperateDevices.remove(token);
                    }
                } else if (operate.equals(OPERATE_DISCONNECT) && status != 5) {
                    this.mHandler.removeCallbacksAndMessages(this.mOperateDevices.get(token));
                    this.mOperateDevices.remove(token);
                }
            }
        }
    }
}
