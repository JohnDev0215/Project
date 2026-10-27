package com.ts.carplay.lib.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.ParcelUuid;
import android.util.Log;
import com.ts.carplay.common.util.LogEncrypt;
import com.ts.carplay.lib.bluetooth.BluetoothManager;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothUuidHandler implements BluetoothManager.IBluetoothManagerListener {
    private static final String RECEIVE_UUID_TIMEOUT = "RECEIVE_UUID_TIMEOUT";
    private static final int RECEIVE_UUID_TIMEOUT_DURATION = 5000;
    private static final String TAG = BluetoothUuidHandler.class.getSimpleName();
    private static final int UUID_COUNT = 1;
    private BluetoothManager mBluetoothManager;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private BluetoothUuidListener mListener;
    private Set<String> receiveUuidsBondedActionMarks = new HashSet();
    private Set<String> receiveUuidsUnBondedActionMarks = new HashSet();
    private String waitForUuidDeviceMac = null;

    public BluetoothUuidHandler(Context context, BluetoothManager bluetoothManager) {
        this.mBluetoothManager = bluetoothManager;
        if (bluetoothManager != null) {
            bluetoothManager.registerListener(this);
        }
        HandlerThread handlerThread = new HandlerThread("BluetoothUuidHandler");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
    }

    public void setListener(BluetoothUuidListener listener) {
        this.mListener = listener;
    }

    @Override // com.ts.carplay.lib.bluetooth.BluetoothManager.IBluetoothManagerListener
    public void onLocalBluetoothStateChanged(int state) {
    }

    @Override // com.ts.carplay.lib.bluetooth.BluetoothManager.IBluetoothManagerListener
    public void onBtPairingChanged(String address, boolean isPaired) {
        if (isPaired) {
            receiveUuidWhenBonded(address);
        }
    }

    @Override // com.ts.carplay.lib.bluetooth.BluetoothManager.IBluetoothManagerListener
    public void onLocalSppPeerConnected(String uuid, int handle, String address) {
    }

    @Override // com.ts.carplay.lib.bluetooth.BluetoothManager.IBluetoothManagerListener
    public void onLocalBluetoothProfileStateChanged(String address, int profile, int newState, int oldState) {
        if ((profile == 1 || profile == 11) && newState == 2) {
            receiveUuidFromProfile(address);
        }
    }

    @Override // com.ts.carplay.lib.bluetooth.BluetoothManager.IBluetoothManagerListener
    public void onSdpRecorder(String address) {
        sdpRecordFromAction(address);
    }

    @Override // com.ts.carplay.lib.bluetooth.BluetoothManager.IBluetoothManagerListener
    public void onUuid(String address) {
        receiveUuidFromAction(address);
    }

    @Override // com.ts.carplay.lib.bluetooth.BluetoothManager.IBluetoothManagerListener
    public void onSdpServerUuidChanged(String uuid) {
    }

    @Override // com.ts.carplay.lib.bluetooth.BluetoothManager.IBluetoothManagerListener
    public void onReceiveOobData(boolean success) {
    }

    private void receiveUuidFromProfile(String address) {
        BluetoothDevice device = this.mBluetoothManager.getBluetoothDevice(address);
        if (device == null) {
            Log.e(TAG, "receiveUuidFromProfile ,The device with address " + LogEncrypt.MacAddressEncrypt(address) + " is null");
            return;
        }
        Log.d(TAG, "receiveUuidFromProfile:" + LogEncrypt.MacAddressEncrypt(device.getAddress()));
        if (existUuids(device)) {
            onReceiveUuid(device);
        }
    }

    private void receiveUuidFromAction(String address) {
        if (this.waitForUuidDeviceMac == null) {
            return;
        }
        BluetoothDevice device = this.mBluetoothManager.getBluetoothDevice(address);
        if (device == null) {
            Log.e(TAG, "receiveUuidFromAction ,The device with address " + LogEncrypt.MacAddressEncrypt(address) + " is null");
            return;
        }
        Log.d(TAG, "receiveUuidFromAction:" + LogEncrypt.MacAddressEncrypt(device.getAddress()));
        Log.d(TAG, "receiveUuidFromAction:" + LogEncrypt.MacAddressEncrypt(Arrays.toString(device.getUuids())));
        if (existUuids(device)) {
            onReceiveUuid(device);
        }
    }

    private void receiveUuidWhenBonded(String address) {
        this.waitForUuidDeviceMac = address;
        BluetoothDevice device = this.mBluetoothManager.getBluetoothDevice(address);
        if (device == null) {
            Log.d(TAG, "onReceiveUuidWhenBonded device is null");
            return;
        }
        int state = device.getBondState();
        Log.d(TAG, "onReceiveUuidWhenBonded state:" + state);
        if (state == 12) {
            this.receiveUuidsUnBondedActionMarks.remove(address);
            this.receiveUuidsBondedActionMarks.add(address);
            if (existUuids(device)) {
                onReceiveUuid(device);
                return;
            }
            return;
        }
        this.receiveUuidsUnBondedActionMarks.add(address);
    }

    private boolean existUuids(BluetoothDevice device) {
        ParcelUuid[] uuids;
        return (device == null || (uuids = device.getUuids()) == null || uuids.length <= 1) ? false : true;
    }

    private void onReceiveUuid(BluetoothDevice device) {
        String uuids = Arrays.toString(device.getUuids());
        Log.d(TAG, "onReceiveUuid: device:" + device + ",uuids:" + LogEncrypt.MacAddressEncrypt(uuids));
        String str = this.waitForUuidDeviceMac;
        if (str != null && str.equals(device.getAddress())) {
            this.mHandler.removeCallbacksAndMessages(RECEIVE_UUID_TIMEOUT);
            this.waitForUuidDeviceMac = null;
            Log.d(TAG, "onReceiveUuid, remove receive uuid timeout task.");
        }
        BluetoothUuidListener bluetoothUuidListener = this.mListener;
        if (bluetoothUuidListener != null) {
            bluetoothUuidListener.onReceiveUuid(device);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onReceiveUuidTimeout, reason: merged with bridge method [inline-methods] */
    public void lambda$sdpRecordFromAction$0$BluetoothUuidHandler(BluetoothDevice device) {
        Log.d(TAG, "onReceiveUuidTimeout:" + device);
        BluetoothUuidListener bluetoothUuidListener = this.mListener;
        if (bluetoothUuidListener != null) {
            bluetoothUuidListener.onReceiveUuid(device);
        }
        this.waitForUuidDeviceMac = null;
    }

    private void sdpRecordFromAction(String address) {
        final BluetoothDevice device = this.mBluetoothManager.getBluetoothDevice(address);
        if (device == null) {
            Log.e(TAG, "sdpRecord device is null:" + LogEncrypt.MacAddressEncrypt(address));
            return;
        }
        if (existBondedAction(device.getAddress())) {
            if (existUuids(device)) {
                Log.d(TAG, "sdpRecord it has received uuids.");
                return;
            }
            boolean ok = device.fetchUuidsWithSdp();
            this.mHandler.postDelayed(new Runnable() { // from class: com.ts.carplay.lib.bluetooth.-$$Lambda$BluetoothUuidHandler$pTPA9Vs27evuQSd5ZzDfUGwCsWY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$sdpRecordFromAction$0$BluetoothUuidHandler(device);
                }
            }, RECEIVE_UUID_TIMEOUT, 5000L);
            Log.d(TAG, "sdpRecord fetchUuidsWithSdp " + ok + " wait for uuid action:5000 millisecond");
            return;
        }
        Log.d(TAG, "sdpRecord fetchUuidsWithSdp2 " + LogEncrypt.MacAddressEncrypt(device.getAddress()));
        if (existBondAction(device.getAddress())) {
            Log.d(TAG, "sdpRecord fetchUuidsWithSdp2.1 ");
            if (this.mListener != null) {
                Log.d(TAG, "sdpRecord fetchUuidsWithSdp2.2 ");
                this.mListener.onReceiveUuid(device);
            }
        }
    }

    private boolean existBondedAction(String address) {
        return this.receiveUuidsBondedActionMarks.remove(address);
    }

    private boolean existBondAction(String address) {
        return this.receiveUuidsUnBondedActionMarks.remove(address);
    }
}
