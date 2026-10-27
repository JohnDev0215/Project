package com.android.bluetooth.hfpclient.connserv;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClient;
import android.bluetooth.BluetoothHeadsetClientCall;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.telecom.Connection;
import android.telecom.ConnectionRequest;
import android.telecom.ConnectionService;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.util.Log;
import com.android.bluetooth.R;
import com.android.bluetooth.hfpclient.HeadsetClientService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class HfpClientConnectionService extends ConnectionService {
    private static final boolean DBG = true;
    public static final String HFP_SCHEME = "hfpc";
    private static final String TAG = "HfpClientConnService";
    private BluetoothAdapter mAdapter;
    private BluetoothHeadsetClient mHeadsetProfile;
    private TelecomManager mTelecomManager;
    private final Map<BluetoothDevice, HfpClientDeviceBlock> mDeviceBlocks = new HashMap();
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.bluetooth.hfpclient.connserv.HfpClientConnectionService.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.d(HfpClientConnectionService.TAG, "onReceive " + intent);
            String action = intent != null ? intent.getAction() : null;
            if ("android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED".equals(action)) {
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                int newState = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                if (newState == 2) {
                    Log.d(HfpClientConnectionService.TAG, "Established connection with " + device.getAddressOnlyforLog());
                    if (HfpClientConnectionService.this.createBlockForDevice(device) == null) {
                        Log.w(HfpClientConnectionService.TAG, "Block already exists for device " + device.getAddressOnlyforLog() + " ignoring.");
                        return;
                    }
                    return;
                }
                if (newState == 0) {
                    Log.d(HfpClientConnectionService.TAG, "Disconnecting from " + device);
                    synchronized (HfpClientConnectionService.this) {
                        HfpClientDeviceBlock block = (HfpClientDeviceBlock) HfpClientConnectionService.this.mDeviceBlocks.remove(device);
                        if (block == null) {
                            Log.w(HfpClientConnectionService.TAG, "Disconnect for device but no block " + device.getAddressOnlyforLog());
                            return;
                        }
                        block.cleanup();
                        for (Map.Entry<BluetoothDevice, HfpClientDeviceBlock> entry : HfpClientConnectionService.this.mDeviceBlocks.entrySet()) {
                            if (entry.getValue() != null) {
                                block = entry.getValue();
                            }
                        }
                        block.setUserSelectedOutgoingPhoneAccount();
                        return;
                    }
                }
                return;
            }
            if ("android.bluetooth.headsetclient.profile.action.AG_CALL_CHANGED".equals(action)) {
                BluetoothHeadsetClientCall call = intent.getParcelableExtra("android.bluetooth.headsetclient.extra.CALL");
                BluetoothDevice device2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                HfpClientDeviceBlock block2 = HfpClientConnectionService.this.findBlockForDevice(call.getDevice());
                if (block2 == null) {
                    Log.w(HfpClientConnectionService.TAG, "Call changed but no block for device " + device2.getAddressOnlyforLog());
                    return;
                }
                block2.handleCall(call);
            }
        }
    };
    BluetoothProfile.ServiceListener mServiceListener = new BluetoothProfile.ServiceListener() { // from class: com.android.bluetooth.hfpclient.connserv.HfpClientConnectionService.2
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            Log.d(HfpClientConnectionService.TAG, "onServiceConnected");
            HfpClientConnectionService.this.mHeadsetProfile = (BluetoothHeadsetClient) proxy;
            List<BluetoothDevice> devices = HfpClientConnectionService.this.mHeadsetProfile.getConnectedDevices();
            if (devices == null) {
                Log.w(HfpClientConnectionService.TAG, "No connected or more than one connected devices found." + devices);
                return;
            }
            for (BluetoothDevice device : devices) {
                Log.d(HfpClientConnectionService.TAG, "Creating phone account for device " + device.getAddressOnlyforLog());
                HfpClientConnectionService.this.createBlockForDevice(device);
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int profile) {
            Log.d(HfpClientConnectionService.TAG, "onServiceDisconnected " + profile);
            HfpClientConnectionService.this.mHeadsetProfile = null;
            HfpClientConnectionService.this.disconnectAll();
        }
    };

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        TelecomManager telecomManager = (TelecomManager) getSystemService("telecom");
        this.mTelecomManager = telecomManager;
        if (telecomManager != null) {
            telecomManager.clearPhoneAccounts();
        }
        this.mAdapter.getProfileProxy(this, this.mServiceListener, 16);
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.d(TAG, "onDestroy called");
        BluetoothProfile bluetoothProfile = this.mHeadsetProfile;
        if (bluetoothProfile != null) {
            this.mAdapter.closeProfileProxy(16, bluetoothProfile);
        }
        try {
            unregisterReceiver(this.mBroadcastReceiver);
        } catch (IllegalArgumentException e) {
            Log.w(TAG, "Receiver was not registered.");
        }
        disconnectAll();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void disconnectAll() {
        Iterator<Map.Entry<BluetoothDevice, HfpClientDeviceBlock>> it = this.mDeviceBlocks.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().cleanup();
            it.remove();
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand " + intent);
        if (intent != null && intent.getBooleanExtra(HeadsetClientService.HFP_CLIENT_STOP_TAG, false)) {
            stopSelf();
            return 0;
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED");
        filter.addAction("android.bluetooth.headsetclient.profile.action.AG_CALL_CHANGED");
        registerReceiver(this.mBroadcastReceiver, filter);
        return 1;
    }

    @Override // android.telecom.ConnectionService
    public Connection onCreateIncomingConnection(PhoneAccountHandle connectionManagerAccount, ConnectionRequest request) {
        Log.d(TAG, "onCreateIncomingConnection " + connectionManagerAccount + " req: " + request);
        HfpClientDeviceBlock block = findBlockForHandle(connectionManagerAccount);
        if (block == null) {
            Log.w(TAG, "HfpClient does not support having a connection manager");
            return null;
        }
        BluetoothHeadsetClientCall call = (BluetoothHeadsetClientCall) request.getExtras().getParcelable("android.telecom.extra.INCOMING_CALL_EXTRAS");
        HfpClientConnection connection = block.onCreateIncomingConnection(call);
        if (connection != null) {
            connection.setHfpClientConnectionService(this);
        }
        return connection;
    }

    @Override // android.telecom.ConnectionService
    public Connection onCreateOutgoingConnection(PhoneAccountHandle connectionManagerAccount, ConnectionRequest request) {
        Log.d(TAG, "onCreateOutgoingConnection " + connectionManagerAccount);
        HfpClientDeviceBlock block = findBlockForHandle(connectionManagerAccount);
        if (block == null) {
            Log.w(TAG, "HfpClient does not support having a connection manager");
            return null;
        }
        HfpClientConnection connection = block.onCreateOutgoingConnection(request.getAddress());
        if (connection != null) {
            connection.setHfpClientConnectionService(this);
        }
        return connection;
    }

    public Connection onCreateUnknownConnection(PhoneAccountHandle connectionManagerAccount, ConnectionRequest request) {
        Log.d(TAG, "onCreateUnknownConnection " + connectionManagerAccount);
        HfpClientDeviceBlock block = findBlockForHandle(connectionManagerAccount);
        if (block == null) {
            Log.w(TAG, "HfpClient does not support having a connection manager");
            return null;
        }
        BluetoothHeadsetClientCall call = (BluetoothHeadsetClientCall) request.getExtras().getParcelable("android.telecom.extra.OUTGOING_CALL_EXTRAS");
        HfpClientConnection connection = block.onCreateUnknownConnection(call);
        if (connection != null) {
            connection.setHfpClientConnectionService(this);
        }
        return connection;
    }

    @Override // android.telecom.ConnectionService
    public void onConference(Connection connection1, Connection connection2) {
        Log.d(TAG, "onConference " + connection1 + " " + connection2);
        BluetoothDevice bd1 = ((HfpClientConnection) connection1).getDevice();
        BluetoothDevice bd2 = ((HfpClientConnection) connection2).getDevice();
        if (!Objects.equals(bd1, bd2)) {
            Log.e(TAG, "Cannot conference calls from two different devices bd1 " + bd1 + " bd2 " + bd2 + " conn1 " + connection1 + "connection2 " + connection2);
            return;
        }
        HfpClientDeviceBlock block = findBlockForDevice(bd1);
        block.onConference(connection1, connection2);
    }

    private BluetoothDevice getDevice(PhoneAccountHandle handle) {
        PhoneAccount account = this.mTelecomManager.getPhoneAccount(handle);
        String btAddr = account.getAddress().getSchemeSpecificPart();
        return this.mAdapter.getRemoteDevice(btAddr);
    }

    synchronized HfpClientDeviceBlock createBlockForDevice(BluetoothDevice device) {
        Log.d(TAG, "Creating block for device " + device.getAddressOnlyforLog());
        if (this.mDeviceBlocks.containsKey(device)) {
            Log.e(TAG, "Device already exists " + device.getAddressOnlyforLog() + " blocks " + this.mDeviceBlocks);
            return null;
        }
        HfpClientDeviceBlock block = new HfpClientDeviceBlock(this, device, this.mHeadsetProfile);
        this.mDeviceBlocks.put(device, block);
        return block;
    }

    synchronized HfpClientDeviceBlock findBlockForDevice(BluetoothDevice device) {
        Log.d(TAG, "Finding block for device " + device.getAddressOnlyforLog() + " blocks " + this.mDeviceBlocks);
        return this.mDeviceBlocks.get(device);
    }

    synchronized HfpClientDeviceBlock findBlockForHandle(PhoneAccountHandle handle) {
        BluetoothDevice device;
        PhoneAccount account = this.mTelecomManager.getPhoneAccount(handle);
        String btAddr = account.getAddress().getSchemeSpecificPart();
        device = this.mAdapter.getRemoteDevice(btAddr);
        return this.mDeviceBlocks.get(device);
    }

    public static PhoneAccount createAccount(Context context, BluetoothDevice device) {
        Uri addr = Uri.fromParts(HFP_SCHEME, device.getAddress(), null);
        PhoneAccountHandle handle = new PhoneAccountHandle(new ComponentName(context, (Class<?>) HfpClientConnectionService.class), device.getAddress());
        int capabilities = 2;
        if (context.getApplicationContext().getResources().getBoolean(R.bool.hfp_client_connection_service_support_emergency_call)) {
            capabilities = 2 | 16;
        }
        PhoneAccount account = new PhoneAccount.Builder(handle, "HFP " + device.toString()).setAddress(addr).setSupportedUriSchemes(Arrays.asList("tel")).setCapabilities(capabilities).build();
        Log.d(TAG, "phoneaccount: " + account);
        return account;
    }

    public static boolean hasHfpClientEcc(BluetoothHeadsetClient client, BluetoothDevice device) {
        Bundle features = client.getCurrentAgEvents(device);
        return features != null && features.getBoolean("android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_ECC", false);
    }
}
