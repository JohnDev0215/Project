package com.ts.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import com.ts.bluetooth.profile.DeviceBluetoothProfileManager;
import com.ts.bluetooth.profile.Profile;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothConnectManager {
    private static final String ACTION_A2DP_CONNECT_STATE = "android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED";
    private static final String ACTION_HFP_CONNECT_STATE = "android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED";
    private static final String EXTRA_PROFILE_CONNECT_STATE = "android.bluetooth.profile.extra.STATE";
    private static final int PROFILE_STATE_CONNECTED = 2;
    private static final int PROFILE_STATE_DISCONNECTED = 0;
    private static final String TAG = BluetoothConnectManager.class.getName();
    private BluetoothAdapter bluetoothAdapter;
    private Context context;
    private BluetoothCallback mCallback;
    private DeviceBluetoothProfileManager mProfileManager;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.ts.bluetooth.BluetoothConnectManager.1
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            byte b;
            BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            Log.d(BluetoothConnectManager.TAG, "action:" + intent.getAction() + " ,device:" + device);
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                for (String key : bundle.keySet()) {
                    Log.d(BluetoothConnectManager.TAG, "key:" + key + ",value:" + bundle.get(key));
                }
            }
            String action = intent.getAction();
            switch (action.hashCode()) {
                case -1530327060:
                    b = action.equals("android.bluetooth.adapter.action.STATE_CHANGED") ? (byte) 3 : (byte) -1;
                    break;
                case 40146574:
                    b = action.equals(BluetoothConnectManager.ACTION_A2DP_CONNECT_STATE) ? (byte) 2 : (byte) -1;
                    break;
                case 448318136:
                    b = action.equals(BluetoothConnectManager.ACTION_HFP_CONNECT_STATE) ? (byte) 1 : (byte) -1;
                    break;
                case 2116862345:
                    b = action.equals("android.bluetooth.device.action.BOND_STATE_CHANGED") ? (byte) 0 : (byte) -1;
                    break;
                default:
                    b = -1;
                    break;
            }
            if (b == 0) {
                if (BluetoothConnectManager.this.mCallback != null) {
                    BluetoothConnectManager.this.mCallback.onBondedStateChanged(device, intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", 10));
                    return;
                }
                return;
            }
            if (b != 1 && b != 2) {
                if (b == 3 && BluetoothConnectManager.this.mCallback != null) {
                    BluetoothConnectManager.this.mCallback.onBtStateChanged(intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1));
                    return;
                }
                return;
            }
            int state = intent.getIntExtra(BluetoothConnectManager.EXTRA_PROFILE_CONNECT_STATE, 0);
            if (state != 0 && state != 2) {
                return;
            }
            boolean connect = intent.getIntExtra(BluetoothConnectManager.EXTRA_PROFILE_CONNECT_STATE, 0) == 2;
            Profile profile = BluetoothConnectManager.ACTION_HFP_CONNECT_STATE.equals(intent.getAction()) ? Profile.Headset : Profile.A2dp;
            if (BluetoothConnectManager.this.mCallback != null) {
                BluetoothConnectManager.this.mCallback.onProfileConnectedStateChanged(device, profile, connect);
            }
        }
    };
    private String[] actions = {"android.bluetooth.device.action.BOND_STATE_CHANGED", ACTION_HFP_CONNECT_STATE, ACTION_A2DP_CONNECT_STATE, "android.bluetooth.adapter.action.STATE_CHANGED"};

    public BluetoothConnectManager(Context context) {
        this.context = context;
        this.mProfileManager = new DeviceBluetoothProfileManager(context);
        BluetoothManager manager = (BluetoothManager) context.getSystemService("bluetooth");
        this.bluetoothAdapter = manager.getAdapter();
        IntentFilter filter = new IntentFilter();
        for (String a : this.actions) {
            Log.e(TAG, "register action:" + a);
            filter.addAction(a);
        }
        context.registerReceiver(this.mReceiver, filter);
    }

    public DeviceBluetoothProfileManager getProfileManager() {
        return this.mProfileManager;
    }

    public boolean bindDevice(BluetoothDevice device) {
        if (device.getBondState() == 12) {
            Log.e(TAG, "bindDevice getBluetoothClass:" + device.getBluetoothClass());
            BluetoothCallback bluetoothCallback = this.mCallback;
            if (bluetoothCallback != null) {
                bluetoothCallback.onBondedStateChanged(device, 12);
                return true;
            }
            return true;
        }
        try {
            Method method = BluetoothDevice.class.getMethod("createBond", new Class[0]);
            return ((Boolean) method.invoke(device, new Object[0])).booleanValue();
        } catch (Exception e1) {
            e1.printStackTrace();
            return false;
        }
    }

    public boolean connectProfile(BluetoothDevice device, Profile profile) {
        boolean b1 = this.mProfileManager.connect(device, profile);
        Log.e(TAG, "connectProfile:" + b1);
        return b1;
    }

    public boolean disconnectProfile(BluetoothDevice device, Profile profile) {
        return this.mProfileManager.disconnect(device, profile);
    }

    public boolean disconnectAllProfile(BluetoothDevice device) {
        return this.mProfileManager.disconnect(device);
    }

    public boolean removeBond(BluetoothDevice device) {
        try {
            Method method = BluetoothDevice.class.getMethod("removeBond", new Class[0]);
            return ((Boolean) method.invoke(device, new Object[0])).booleanValue();
        } catch (Exception e1) {
            e1.printStackTrace();
            return false;
        }
    }

    public boolean createBond(BluetoothDevice device) {
        try {
            Method method = BluetoothDevice.class.getMethod("createBond", new Class[0]);
            return ((Boolean) method.invoke(device, new Object[0])).booleanValue();
        } catch (Exception e1) {
            e1.printStackTrace();
            return false;
        }
    }

    public boolean isEnabled() {
        return this.bluetoothAdapter.isEnabled();
    }

    public boolean enable() {
        return this.bluetoothAdapter.enable();
    }

    public boolean disable() {
        return this.bluetoothAdapter.disable();
    }

    public Set<BluetoothDevice> getBondedDevices() {
        return this.bluetoothAdapter.getBondedDevices();
    }

    public String getBluetoothName() {
        return this.bluetoothAdapter.getName();
    }

    public String getBluetoothAddress() {
        return this.bluetoothAdapter.getAddress();
    }

    public List<BluetoothDevice> getConnectedBtDevice() {
        List<BluetoothDevice> list = new ArrayList<>();
        Set<BluetoothDevice> bondedDevices = getBondedDevices();
        if (bondedDevices != null && bondedDevices.size() > 0) {
            for (BluetoothDevice bondedDevice : bondedDevices) {
                try {
                    Method isConnectedMethod = BluetoothDevice.class.getDeclaredMethod("isConnected", (Class[]) null);
                    isConnectedMethod.setAccessible(true);
                    boolean isConnected = ((Boolean) isConnectedMethod.invoke(bondedDevice, (Object[]) null)).booleanValue();
                    if (isConnected) {
                        list.add(bondedDevice);
                    }
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (NoSuchMethodException e12) {
                    e12.printStackTrace();
                } catch (InvocationTargetException e13) {
                    e13.printStackTrace();
                }
            }
        }
        return list;
    }

    public boolean isConnected(BluetoothDevice device) {
        try {
            Method isConnectedMethod = BluetoothDevice.class.getDeclaredMethod("isConnected", (Class[]) null);
            isConnectedMethod.setAccessible(true);
            return ((Boolean) isConnectedMethod.invoke(device, (Object[]) null)).booleanValue();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
            return false;
        } catch (NoSuchMethodException e12) {
            e12.printStackTrace();
            return false;
        } catch (InvocationTargetException e13) {
            e13.printStackTrace();
            return false;
        }
    }

    public boolean isPaired(BluetoothDevice device) {
        return device.getBondState() == 12;
    }

    public void destroy() {
        this.context.unregisterReceiver(this.mReceiver);
    }

    public void setBluetoothCallback(BluetoothCallback callback) {
        this.mCallback = callback;
    }
}
