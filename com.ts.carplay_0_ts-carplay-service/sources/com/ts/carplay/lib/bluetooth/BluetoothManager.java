package com.ts.carplay.lib.bluetooth;

import android.bluetooth.BluetoothA2dpSink;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClient;
import android.bluetooth.BluetoothHeadsetClientCall;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.OobData;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.ParcelUuid;
import android.util.Log;
import com.ts.carplay.common.util.LogEncrypt;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.bluetooth.BluetoothManager;
import com.ts.carplay.lib.bluetooth.BluetoothSerialPort;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothManager {
    public static final String ACTION_BOND_STATE_CHANGED = "android.bluetooth.device.action.BOND_STATE_CHANGED";
    public static final String ACTION_LOCAL_OOB_DATA = "android.bluetooth.adapter.action.LOCAL_OOB_DATA";
    private static final String BT_A2DPSINK_ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED";
    private static final String BT_AVRCP_ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.avrcp-controller.profile.action.CONNECTION_STATE_CHANGED";
    private static final String BT_HEADSET_ACTION_CONNECTION_STATE_CHANGED = "android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED";
    private static final String BT_UUID_ACTION = "android.bluetooth.device.action.UUID";
    public static final String EXTRA_LOCAL_OOB_DATA = "android.bluetooth.adapter.extra.LOCAL_OOB_DATA";
    public static final String EXTRA_LOCAL_OOB_RESULT = "android.bluetooth.adapter.extra.EXTRA_OOB_RESULT";
    private static final int OBJECT_ARRAY_0 = 0;
    private static final int OBJECT_ARRAY_1 = 1;
    private static final int OBJECT_ARRAY_2 = 2;
    private static final int OBJECT_LEN = 3;
    private static final int SEND_SDP_SERVER_UUID_DELAY = 1000;
    private static final String TAG = "BluetoothManager";
    private static final UUID UUID_IAP = UUID.fromString("00000000-deca-fade-deca-deafdecacafe");
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothSerialPort mBtSerialPort;
    private Context mContext;
    private String mBluetoothMacAddr = null;
    private CopyOnWriteArraySet<IBluetoothManagerListener> mListener = new CopyOnWriteArraySet<>();
    private BluetoothHeadsetClient mBluetoothHeadset = null;
    private BluetoothA2dpSink mBluetoothA2dp = null;
    private Handler mHandler = null;
    private BsBroadcastReceiver mReceiver = null;
    private OobData mOobData = null;
    private BluetoothSerialPort.BluetoothSerialPortCallback mBtSerialPortCallback = new AnonymousClass1();
    private BluetoothProfile.ServiceListener mServiceListener = new BluetoothProfile.ServiceListener() { // from class: com.ts.carplay.lib.bluetooth.BluetoothManager.2
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            LogUtil.debug(BluetoothManager.TAG, "onServiceConnected profile:" + profile);
            if (profile == 16 && BluetoothManager.this.mBluetoothHeadset == null) {
                BluetoothManager.this.mBluetoothHeadset = (BluetoothHeadsetClient) proxy;
            } else if (profile == 11 && BluetoothManager.this.mBluetoothA2dp == null) {
                BluetoothManager.this.mBluetoothA2dp = (BluetoothA2dpSink) proxy;
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int profile) {
            LogUtil.debug(BluetoothManager.TAG, "onServiceDisconnected profile:" + profile);
        }
    };

    public interface IBluetoothManagerListener {
        void onBtPairingChanged(String str, boolean z);

        void onLocalBluetoothProfileStateChanged(String str, int i, int i2, int i3);

        void onLocalBluetoothStateChanged(int i);

        void onLocalSppPeerConnected(String str, int i, String str2);

        void onReceiveOobData(boolean z);

        void onSdpRecorder(String str);

        void onSdpServerUuidChanged(String str);

        void onUuid(String str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    class BsBroadcastReceiver extends BroadcastReceiver {
        private BsBroadcastReceiver() {
        }

        /* synthetic */ BsBroadcastReceiver(BluetoothManager x0, AnonymousClass1 x1) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context ctx, final Intent intent) {
            try {
                String action = intent.getAction();
                LogUtil.debug(BluetoothManager.TAG, "onReceive(): action=" + action);
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    for (String key : bundle.keySet()) {
                        Log.e(BluetoothManager.TAG, "key:" + key + ",value:" + LogEncrypt.MacAddressEncrypt(String.valueOf(bundle.get(key))));
                    }
                }
                if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                    final int state = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10);
                    LogUtil.debug(BluetoothManager.TAG, "onBtStateChanged state : " + state);
                    BluetoothManager.this.broadcastListener(new Consumer() { // from class: com.ts.carplay.lib.bluetooth.-$$Lambda$BluetoothManager$BsBroadcastReceiver$NfnmgWQpcMJ_Lp97vCdYy1iThqs
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ((BluetoothManager.IBluetoothManagerListener) obj).onLocalBluetoothStateChanged(state);
                        }
                    });
                    return;
                }
                if (action.equals(BluetoothManager.BT_HEADSET_ACTION_CONNECTION_STATE_CHANGED)) {
                    BluetoothManager.this.handleConnectionStateChange(1, intent);
                    return;
                }
                if (action.equals(BluetoothManager.BT_A2DPSINK_ACTION_CONNECTION_STATE_CHANGED)) {
                    BluetoothManager.this.handleConnectionStateChange(11, intent);
                    return;
                }
                if (action.equals(BluetoothManager.BT_AVRCP_ACTION_CONNECTION_STATE_CHANGED)) {
                    BluetoothManager.this.handleConnectionStateChange(12, intent);
                    return;
                }
                if (action.equals("android.bluetooth.device.action.SDP_RECORD")) {
                    final BluetoothDevice btDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    BluetoothManager.this.broadcastListener(new Consumer() { // from class: com.ts.carplay.lib.bluetooth.-$$Lambda$BluetoothManager$BsBroadcastReceiver$5vA3zNbvqk94kWnuUCXBACRnxnw
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ((BluetoothManager.IBluetoothManagerListener) obj).onSdpRecorder(btDevice.getAddress());
                        }
                    });
                    return;
                }
                if (action.equals(BluetoothManager.ACTION_BOND_STATE_CHANGED)) {
                    final BluetoothDevice btDevice2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    BluetoothManager.this.broadcastListener(new Consumer() { // from class: com.ts.carplay.lib.bluetooth.-$$Lambda$BluetoothManager$BsBroadcastReceiver$LVu2nNAHATWGq-xvImdSdeFg1wk
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ((BluetoothManager.IBluetoothManagerListener) obj).onBtPairingChanged(btDevice2.getAddress(), intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", 10) == 12);
                        }
                    });
                    return;
                }
                if (action.equals(BluetoothManager.BT_UUID_ACTION)) {
                    final BluetoothDevice btDevice3 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    BluetoothManager.this.broadcastListener(new Consumer() { // from class: com.ts.carplay.lib.bluetooth.-$$Lambda$BluetoothManager$BsBroadcastReceiver$-zRK0QBFYnUgV4q32edZKWxuHBs
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ((BluetoothManager.IBluetoothManagerListener) obj).onUuid(btDevice3.getAddress());
                        }
                    });
                    return;
                }
                if (action.equals(BluetoothManager.ACTION_LOCAL_OOB_DATA)) {
                    int result = intent.getIntExtra(BluetoothManager.EXTRA_LOCAL_OOB_RESULT, -1);
                    LogUtil.debug(BluetoothManager.TAG, "onReceive oob data result is" + result);
                    if (result != 0) {
                        BluetoothManager.this.mOobData = null;
                    } else {
                        BluetoothManager.this.mOobData = intent.getParcelableExtra(BluetoothManager.EXTRA_LOCAL_OOB_DATA);
                    }
                    BluetoothManager.this.broadcastListener(new Consumer() { // from class: com.ts.carplay.lib.bluetooth.-$$Lambda$BluetoothManager$BsBroadcastReceiver$BXGyienpxntPlXkrVbm56q3hdd8
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            this.f$0.lambda$onReceive$4$BluetoothManager$BsBroadcastReceiver((BluetoothManager.IBluetoothManagerListener) obj);
                        }
                    });
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        public /* synthetic */ void lambda$onReceive$4$BluetoothManager$BsBroadcastReceiver(IBluetoothManagerListener listener) {
            listener.onReceiveOobData(BluetoothManager.this.mOobData != null);
        }
    }

    /* JADX INFO: renamed from: com.ts.carplay.lib.bluetooth.BluetoothManager$1, reason: invalid class name */
    class AnonymousClass1 extends BluetoothSerialPort.BluetoothSerialPortCallback {
        AnonymousClass1() {
        }

        @Override // com.ts.carplay.lib.bluetooth.BluetoothSerialPort.BluetoothSerialPortCallback
        public void onPeerConnected(final String uuid, final int handle, final String address) {
            LogUtil.debug(BluetoothManager.TAG, "onPeerConnected uuid:" + LogEncrypt.MacAddressEncrypt(uuid) + "handle" + handle + " address:" + LogEncrypt.MacAddressEncrypt(address));
            BluetoothManager.this.broadcastListener(new Consumer() { // from class: com.ts.carplay.lib.bluetooth.-$$Lambda$BluetoothManager$1$4ShI8mUON0fR39Ye3xuDm4ocagI
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((BluetoothManager.IBluetoothManagerListener) obj).onLocalSppPeerConnected(uuid, handle, address);
                }
            });
        }
    }

    public void init(Context context) {
        if (this.mContext != null) {
            Log.w(TAG, "BluetoothAdapter has already init");
            return;
        }
        this.mContext = context;
        HandlerThread thread = new HandlerThread(TAG);
        thread.start();
        this.mReceiver = new BsBroadcastReceiver(this, null);
        this.mHandler = new Handler(thread.getLooper());
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        filter.addAction(BT_HEADSET_ACTION_CONNECTION_STATE_CHANGED);
        filter.addAction(BT_AVRCP_ACTION_CONNECTION_STATE_CHANGED);
        filter.addAction(BT_A2DPSINK_ACTION_CONNECTION_STATE_CHANGED);
        filter.addAction(BT_UUID_ACTION);
        filter.addAction(ACTION_BOND_STATE_CHANGED);
        filter.addAction("android.bluetooth.device.action.SDP_RECORD");
        filter.addAction(ACTION_LOCAL_OOB_DATA);
        this.mContext.registerReceiver(this.mReceiver, filter, null, this.mHandler);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBluetoothAdapter = defaultAdapter;
        if (defaultAdapter != null) {
            defaultAdapter.getProfileProxy(this.mContext, this.mServiceListener, 16);
            this.mBluetoothAdapter.getProfileProxy(this.mContext, this.mServiceListener, 11);
        }
        try {
            this.mBtSerialPort = BluetoothSerialPort.getInstance(this.mContext);
        } catch (Exception ex) {
            LogUtil.warning(TAG, "BluetoothSerialPort.getInstance failed" + ex);
            this.mBtSerialPort = null;
        }
        BluetoothSerialPort bluetoothSerialPort = this.mBtSerialPort;
        if (bluetoothSerialPort != null) {
            bluetoothSerialPort.registerCallback(this.mBtSerialPortCallback);
        }
    }

    public void uninit() {
    }

    public void registerListener(IBluetoothManagerListener listener) {
        this.mListener.add(listener);
    }

    public void unregisterListener(IBluetoothManagerListener listener) {
        this.mListener.remove(listener);
    }

    private void onSdpServerUuidChanged(final String uuid) {
        this.mHandler.postDelayed(new Runnable() { // from class: com.ts.carplay.lib.bluetooth.-$$Lambda$BluetoothManager$4X08CWa7HezzGsGxXgsEilZaYWU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onSdpServerUuidChanged$1$BluetoothManager(uuid);
            }
        }, 1000L);
    }

    public /* synthetic */ void lambda$onSdpServerUuidChanged$1$BluetoothManager(final String uuid) {
        LogUtil.warning(TAG, "onSdpServerUuidChanged, uuid:" + LogEncrypt.MacAddressEncrypt(uuid));
        broadcastListener(new Consumer() { // from class: com.ts.carplay.lib.bluetooth.-$$Lambda$BluetoothManager$ZcbBc0W_fZk2gz5B8bWnlZkEb4c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((BluetoothManager.IBluetoothManagerListener) obj).onSdpServerUuidChanged(uuid);
            }
        });
    }

    public boolean getLocalOobData() {
        boolean ret = false;
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter != null) {
            ret = bluetoothAdapter.readLocalOobData();
        }
        LogUtil.debug(TAG, "getLocalOobData:" + ret);
        return ret;
    }

    public byte[][] getPairingDataP192() {
        LogUtil.debug(TAG, "getPairingDataP192 enter.");
        OobData oobData = this.mOobData;
        if (oobData != null) {
            byte[] arrayC192 = oobData.getC192();
            byte[] arrayR192 = this.mOobData.getR192();
            LogUtil.debug(TAG, "arrayC192 = " + Arrays.toString(arrayC192) + "arrayR192 = " + Arrays.toString(arrayR192));
            byte[] returnArrayC192 = reverse(arrayC192);
            byte[] returnArrayR192 = reverse(arrayR192);
            LogUtil.debug(TAG, "returnArrayC192 = " + Arrays.toString(returnArrayC192) + "returnArrayR192 = " + Arrays.toString(returnArrayR192));
            return readByteArray(returnArrayC192, returnArrayR192);
        }
        return null;
    }

    public byte[][] getPairingDataP256() {
        LogUtil.debug(TAG, "getPairingDataP256 enter.");
        OobData oobData = this.mOobData;
        if (oobData != null) {
            byte[] arrayC256 = oobData.getC256();
            byte[] arrayR256 = this.mOobData.getR256();
            LogUtil.debug(TAG, "arrayC256 = " + Arrays.toString(arrayC256) + "arrayR256 = " + Arrays.toString(arrayR256));
            byte[] returnArrayC256 = reverse(arrayC256);
            byte[] returnArrayR256 = reverse(arrayR256);
            LogUtil.debug(TAG, "returnArrayC256 = " + Arrays.toString(returnArrayC256) + "returnArrayR256 = " + Arrays.toString(returnArrayR256));
            return readByteArray(returnArrayC256, returnArrayR256);
        }
        return null;
    }

    public byte[] reverse(byte[] array) {
        if (array != null) {
            for (int i = 0; i < array.length / 2; i++) {
                byte temp = array[i];
                array[i] = array[(array.length - 1) - i];
                array[(array.length - 1) - i] = temp;
            }
        }
        return array;
    }

    private byte[][] readByteArray(byte[] c, byte[] r) {
        byte[][] result = (byte[][]) Array.newInstance((Class<?>) byte.class, 2, 1);
        if (c != null && r != null) {
            result[0] = c;
            result[1] = r;
            LogUtil.debug(TAG, "readByteArray, result=" + Arrays.deepToString(result));
        } else {
            LogUtil.warning(TAG, "readByteArray, error c=" + c + ", r=" + r);
        }
        return result;
    }

    public boolean enableBluetooth() {
        LogUtil.debug(TAG, "enableBluetooth start.");
        boolean ret = false;
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter == null) {
            LogUtil.warning(TAG, "enableBluetooth mBtManager is null");
        } else {
            ret = bluetoothAdapter.enable();
        }
        LogUtil.warning(TAG, "enableBluetooth, ret is:" + ret);
        return ret;
    }

    public int getBluetoothDeviceClass() {
        BluetoothClass bluetoothClass;
        int deviceClass = -1;
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter != null && (bluetoothClass = bluetoothAdapter.getBluetoothClass()) != null) {
            deviceClass = bluetoothClass.getClassOfDevice();
        }
        LogUtil.debug(TAG, "getBluetoothDeviceClass deviceClass: " + deviceClass);
        return deviceClass;
    }

    public String getBluetoothMacAddress() {
        String str = this.mBluetoothMacAddr;
        if (str != null) {
            return str;
        }
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter != null) {
            this.mBluetoothMacAddr = bluetoothAdapter.getAddress();
        }
        LogUtil.debug(TAG, "getBluetoothMacAddress mac: " + LogEncrypt.MacAddressEncrypt(this.mBluetoothMacAddr));
        return this.mBluetoothMacAddr;
    }

    public ParcelUuid[] getBluetoothDeviceUuids(String mac) {
        ParcelUuid[] uuids = null;
        try {
            if (this.mBluetoothAdapter != null) {
                uuids = this.mBluetoothAdapter.getRemoteDevice(mac).getUuids();
            }
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        LogUtil.debug(TAG, "getBluetoothDeviceUuids mac: " + LogEncrypt.MacAddressEncrypt(mac));
        return uuids;
    }

    public BluetoothDevice getBluetoothDevice(String mac) {
        LogUtil.debug(TAG, "getBluetoothDevice mac: " + LogEncrypt.MacAddressEncrypt(mac));
        try {
            if (this.mBluetoothAdapter != null) {
                return this.mBluetoothAdapter.getRemoteDevice(mac);
            }
            return null;
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean isDuringBtPhone(String mac) {
        boolean ret = false;
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter != null && mac != null) {
            BluetoothDevice device = bluetoothAdapter.getRemoteDevice(mac);
            BluetoothHeadsetClient bluetoothHeadsetClient = this.mBluetoothHeadset;
            if (bluetoothHeadsetClient != null) {
                List<BluetoothHeadsetClientCall> calls = bluetoothHeadsetClient.getCurrentCalls(device);
                if (calls != null && calls.size() > 0) {
                    Iterator<BluetoothHeadsetClientCall> it = calls.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        BluetoothHeadsetClientCall call = it.next();
                        if (call != null && call.getState() != 7) {
                            ret = true;
                            break;
                        }
                    }
                }
            } else {
                LogUtil.warning(TAG, "isDuringBtPhone mBluetoothHeadset is null.");
            }
        } else {
            LogUtil.debug(TAG, "isDuringBtPhone Adapter:" + this.mBluetoothAdapter + "mac:" + LogEncrypt.MacAddressEncrypt(mac) + "is null.");
        }
        LogUtil.debug(TAG, "isDuringBtPhone mac:" + LogEncrypt.MacAddressEncrypt(mac) + " ret:" + ret);
        return ret;
    }

    public boolean startRfCommServer(String name, String uuid) {
        BluetoothSerialPort bluetoothSerialPort = this.mBtSerialPort;
        if (bluetoothSerialPort != null) {
            boolean ret = bluetoothSerialPort.startServer(name, uuid);
            onSdpServerUuidChanged(uuid);
            LogUtil.debug(TAG, "startRfCommServer name:" + name + " uuid:" + LogEncrypt.MacAddressEncrypt(uuid) + " ret:" + ret);
            return ret;
        }
        LogUtil.warning(TAG, "startRfCommServer mBtSerialPort is null.");
        return false;
    }

    public boolean stopRfCommServer(String uuid) {
        BluetoothSerialPort bluetoothSerialPort = this.mBtSerialPort;
        if (bluetoothSerialPort != null) {
            boolean ret = bluetoothSerialPort.stopServer(uuid);
            LogUtil.debug(TAG, "stopRfCommServer uuid:" + LogEncrypt.MacAddressEncrypt(uuid) + " ret:" + ret);
            return ret;
        }
        LogUtil.warning(TAG, "stopRfCommServer mBtSerialPort is null.");
        return false;
    }

    public void closeRfCommConnection(int handle) {
        LogUtil.debug(TAG, "closeRfCommConnection handle:" + handle);
        this.mBtSerialPort.closeConnection(handle);
    }

    public Object[] getRfcommSocketIpsAndOps(String bluetoothAddress) {
        if (bluetoothAddress == null || bluetoothAddress.isEmpty()) {
            return null;
        }
        int handle = this.mBtSerialPort.connectToServer(bluetoothAddress, UUID_IAP.toString());
        InputStream inputStream = this.mBtSerialPort.getInputStream(handle);
        OutputStream outputStream = this.mBtSerialPort.getOutputStream(handle);
        StringBuilder sb = new StringBuilder();
        sb.append("getInputStream handle:");
        sb.append(handle);
        sb.append(" is:");
        sb.append(inputStream == null);
        sb.append(" os:");
        sb.append(outputStream == null);
        LogUtil.debug(TAG, sb.toString());
        Object[] obj = {inputStream, outputStream, Integer.valueOf(handle)};
        return obj;
    }

    public int connectToServer(String address, String uuid) {
        int ret = -1;
        BluetoothSerialPort bluetoothSerialPort = this.mBtSerialPort;
        if (bluetoothSerialPort != null) {
            ret = bluetoothSerialPort.connectToServer(address, uuid);
        }
        LogUtil.debug(TAG, "connectToServer ret:" + ret + " address:" + LogEncrypt.MacAddressEncrypt(address) + " uuid:" + LogEncrypt.MacAddressEncrypt(uuid));
        return ret;
    }

    public boolean closeConnection(int handle) {
        boolean ret = false;
        BluetoothSerialPort bluetoothSerialPort = this.mBtSerialPort;
        if (bluetoothSerialPort != null) {
            ret = bluetoothSerialPort.closeConnection(handle);
        }
        LogUtil.debug(TAG, "closeConnection ret:" + ret + " handle:" + handle);
        return ret;
    }

    public InputStream getInputStream(int handle) {
        InputStream stream = null;
        BluetoothSerialPort bluetoothSerialPort = this.mBtSerialPort;
        if (bluetoothSerialPort != null) {
            stream = bluetoothSerialPort.getInputStream(handle);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("getInputStream valid:");
        sb.append(stream == null);
        sb.append(" handle:");
        sb.append(handle);
        LogUtil.debug(TAG, sb.toString());
        return stream;
    }

    public OutputStream getOutputStream(int handle) {
        OutputStream stream = null;
        BluetoothSerialPort bluetoothSerialPort = this.mBtSerialPort;
        if (bluetoothSerialPort != null) {
            stream = bluetoothSerialPort.getOutputStream(handle);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("getOutputStream valid:");
        sb.append(stream == null);
        sb.append(" handle:");
        sb.append(handle);
        LogUtil.debug(TAG, sb.toString());
        return stream;
    }

    public boolean connectBtClassicProfile(String mac) {
        BluetoothA2dpSink bluetoothA2dpSink;
        BluetoothDevice device = null;
        if (mac != null) {
            device = this.mBluetoothAdapter.getRemoteDevice(mac);
        }
        BluetoothHeadsetClient bluetoothHeadsetClient = this.mBluetoothHeadset;
        if (bluetoothHeadsetClient != null && device != null) {
            boolean result = bluetoothHeadsetClient.connect(device);
            LogUtil.debug(TAG, "connectBtClassicProfile, connect HFP profile,result:" + result);
            if (true == result && (bluetoothA2dpSink = this.mBluetoothA2dp) != null) {
                boolean result2 = bluetoothA2dpSink.connect(device);
                LogUtil.debug(TAG, "connectBtClassicProfile, connect A2DP profile,result:" + result2);
                return result2;
            }
            return result;
        }
        LogUtil.warning(TAG, "connectBtClassicProfile null point.");
        return false;
    }

    public boolean isBluetoothEnabled() {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.isEnabled();
        }
        return false;
    }

    public Set<String> getBondedDevices() {
        Set<BluetoothDevice> bondedDevices;
        Set<String> bondedMacs = null;
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter != null && (bondedDevices = bluetoothAdapter.getBondedDevices()) != null && bondedDevices.size() > 0) {
            bondedMacs = new HashSet<>();
            for (BluetoothDevice device : bondedDevices) {
                if (device != null) {
                    bondedMacs.add(device.getAddress());
                }
            }
        }
        if (bondedMacs == null) {
            LogUtil.debug(TAG, "getBondedDevices bondedDevices is null");
        } else {
            LogUtil.debug(TAG, "getBondedDevices bondedDevices:" + bondedMacs.size());
        }
        return bondedMacs;
    }

    public List<BluetoothDevice> getBondedBluetoothDevices() {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter != null) {
            Set<BluetoothDevice> list = bluetoothAdapter.getBondedDevices();
            if (list == null || list.isEmpty()) {
                return Collections.EMPTY_LIST;
            }
            List<BluetoothDevice> resultList = new ArrayList<>();
            for (BluetoothDevice dev : list) {
                resultList.add(dev);
            }
            return resultList;
        }
        LogUtil.error(TAG, "getBondedBluetoothDevices mBluetoothAdapter is null");
        return Collections.EMPTY_LIST;
    }

    public boolean isConnected(String mac) {
        BluetoothDevice device;
        boolean connected = false;
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter != null && (device = bluetoothAdapter.getRemoteDevice(mac)) != null) {
            connected = device.isConnected();
        }
        LogUtil.debug(TAG, "isConnected connected:" + connected);
        return connected;
    }

    public boolean disableBluetooth(String mac) {
        BluetoothDevice device = null;
        if (mac != null) {
            device = this.mBluetoothAdapter.getRemoteDevice(mac);
        }
        BluetoothHeadsetClient bluetoothHeadsetClient = this.mBluetoothHeadset;
        if (bluetoothHeadsetClient != null && device != null) {
            boolean result = bluetoothHeadsetClient.disconnect(device);
            LogUtil.debug(TAG, "disableBluetooth, disconnect HFP profile, result:" + result);
            BluetoothA2dpSink bluetoothA2dpSink = this.mBluetoothA2dp;
            if (bluetoothA2dpSink != null) {
                boolean result2 = bluetoothA2dpSink.disconnect(device);
                LogUtil.debug(TAG, "disableBluetooth, disconnect A2DP profile, result:" + result2);
                return result2;
            }
            return result;
        }
        LogUtil.warning(TAG, "disableBluetooth null point.");
        return false;
    }

    public void setBluetoothCoexistence(String macAddr, boolean established) {
        if (this.mBluetoothAdapter != null) {
            if (macAddr == null) {
                LogUtil.warning(TAG, "setBluetoothCoexistence, macAddr is null.");
                return;
            }
            LogUtil.debug(TAG, "setBluetoothCoexistence macAddr:" + LogEncrypt.MacAddressEncrypt(macAddr));
            if (BluetoothAdapter.checkBluetoothAddress(macAddr)) {
                BluetoothDevice bluetoothDevice = this.mBluetoothAdapter.getRemoteDevice(macAddr);
                BluetoothHeadsetClient bluetoothHeadsetClient = this.mBluetoothHeadset;
                if (bluetoothHeadsetClient == null) {
                    LogUtil.warning(TAG, "setBluetoothCoexistence, mBluetoothHeadset is null.");
                } else if (established) {
                    bluetoothHeadsetClient.setPriority2(bluetoothDevice, 0);
                } else {
                    bluetoothHeadsetClient.setPriority2(bluetoothDevice, 100);
                }
                BluetoothA2dpSink bluetoothA2dpSink = this.mBluetoothA2dp;
                if (bluetoothA2dpSink == null) {
                    LogUtil.warning(TAG, "setBluetoothCoexistence, mBluetoothA2dp is null.");
                    return;
                } else if (established) {
                    bluetoothA2dpSink.setPriority2(bluetoothDevice, 0);
                    return;
                } else {
                    bluetoothA2dpSink.setPriority2(bluetoothDevice, 100);
                    return;
                }
            }
            LogUtil.debug(TAG, "setBluetoothCoexistence macAddr: " + LogEncrypt.MacAddressEncrypt(macAddr) + " is not a valid Bluetooth address");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleConnectionStateChange(final int profile, Intent i) {
        LogUtil.debug(TAG, "handleConnectionStateChange: enter, profile=" + profile + ", i=" + i);
        final int state = i.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
        final int prevState = i.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", 0);
        BluetoothDevice btDevice = (BluetoothDevice) i.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        final String address = btDevice.getAddress();
        String uuid = Arrays.toString(btDevice.getUuids());
        LogUtil.debug(TAG, "handleConnectionStateChange: enter, profile=" + profile + ", state=" + state + ", prevState=" + prevState + ", address=" + LogEncrypt.MacAddressEncrypt(address) + ",uuid=" + LogEncrypt.MacAddressEncrypt(uuid));
        broadcastListener(new Consumer() { // from class: com.ts.carplay.lib.bluetooth.-$$Lambda$BluetoothManager$r4BK5jqX7b-mw_ICpOBP4cHT_K0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((BluetoothManager.IBluetoothManagerListener) obj).onLocalBluetoothProfileStateChanged(address, profile, state, prevState);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastListener(final Consumer<IBluetoothManagerListener> consumer) {
        this.mHandler.post(new Runnable() { // from class: com.ts.carplay.lib.bluetooth.-$$Lambda$BluetoothManager$tUQxTfcLHSN3E0L2lRhqxZLJFq4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$broadcastListener$3$BluetoothManager(consumer);
            }
        });
    }

    public /* synthetic */ void lambda$broadcastListener$3$BluetoothManager(Consumer consumer) {
        if (!this.mListener.isEmpty()) {
            for (IBluetoothManagerListener listener : this.mListener) {
                consumer.accept(listener);
            }
        }
    }
}
