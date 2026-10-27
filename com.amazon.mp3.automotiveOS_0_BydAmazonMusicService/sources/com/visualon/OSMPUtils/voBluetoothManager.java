package com.visualon.OSMPUtils;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* JADX INFO: loaded from: classes7.dex */
public class voBluetoothManager {
    private static final String TAG = "@@@voBluetoothManager.java";
    private static boolean isPermissionGranted = false;
    Context mContext;
    onStatusChangeListener mStatusChangeListener;
    private boolean mIsConnected = false;
    private BluetoothHeadset mBluetoothHeadset = null;
    private BluetoothA2dp mBluetoothA2dp = null;
    private BluetoothAdapter mBluetoothAdapter = null;
    private BluetoothProfile.ServiceListener mProfileListener = null;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.visualon.OSMPUtils.voBluetoothManager.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                voLog.i(voBluetoothManager.TAG, "BluetoothAdapter.ACTION_STATE_CHANGED, state = " + intExtra, new Object[0]);
                if (intExtra == 12 && voBluetoothManager.this.mBluetoothHeadset == null) {
                    if (voBluetoothManager.this.mBluetoothAdapter.isEnabled()) {
                        voLog.i(voBluetoothManager.TAG, "GetProfileProxy", new Object[0]);
                        voBluetoothManager.this.mBluetoothAdapter.getProfileProxy(context, voBluetoothManager.this.mProfileListener, 1);
                        return;
                    }
                    return;
                }
                if (intExtra == 10 || intExtra == 13) {
                    voBluetoothManager.this.mStatusChangeListener.SetParam(4134L, 0);
                    return;
                }
                return;
            }
            if (action.equals("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED")) {
                voLog.i(voBluetoothManager.TAG, "Receive BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED", new Object[0]);
                if (voBluetoothManager.this.updateConnectionStatus()) {
                    voLog.i(voBluetoothManager.TAG, "BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED causes connection status changed. mIsConnected " + voBluetoothManager.this.mIsConnected, new Object[0]);
                    voBluetoothManager vobluetoothmanager = voBluetoothManager.this;
                    vobluetoothmanager.mStatusChangeListener.SetParam(4141L, Integer.valueOf(vobluetoothmanager.mIsConnected ? 1 : 0));
                    return;
                }
                return;
            }
            if (action.equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
                voLog.i(voBluetoothManager.TAG, "Receive BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED", new Object[0]);
                if (voBluetoothManager.this.updateConnectionStatus()) {
                    voLog.i(voBluetoothManager.TAG, "BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED causes connection status changed. mIsConnected " + voBluetoothManager.this.mIsConnected, new Object[0]);
                    voBluetoothManager vobluetoothmanager2 = voBluetoothManager.this;
                    vobluetoothmanager2.mStatusChangeListener.SetParam(4134L, Integer.valueOf(vobluetoothmanager2.mIsConnected ? 1 : 0));
                    return;
                }
                return;
            }
            if (action.equals("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED")) {
                voLog.i(voBluetoothManager.TAG, "Receive BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED", new Object[0]);
                if (voBluetoothManager.this.updateConnectionStatus()) {
                    voLog.i(voBluetoothManager.TAG, "BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED causes connection status changed. mIsConnected " + voBluetoothManager.this.mIsConnected, new Object[0]);
                    voBluetoothManager vobluetoothmanager3 = voBluetoothManager.this;
                    vobluetoothmanager3.mStatusChangeListener.SetParam(4142L, Integer.valueOf(vobluetoothmanager3.mIsConnected ? 1 : 0));
                }
            }
        }
    };

    public interface onStatusChangeListener {
        int SetParam(long j, Object obj);
    }

    public voBluetoothManager(Context context, onStatusChangeListener onstatuschangelistener) {
        this.mStatusChangeListener = null;
        this.mContext = context;
        this.mStatusChangeListener = onstatuschangelistener;
    }

    public void init() {
        if (this.mContext.checkCallingOrSelfPermission("android.permission.BLUETOOTH") == 0) {
            isPermissionGranted = true;
            try {
                if (this.mProfileListener == null) {
                    this.mProfileListener = new BluetoothProfile.ServiceListener() { // from class: com.visualon.OSMPUtils.voBluetoothManager.2
                        @Override // android.bluetooth.BluetoothProfile.ServiceListener
                        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                            voLog.i(voBluetoothManager.TAG, "onServiceConnected, %d", Integer.valueOf(i));
                            if (i == 1) {
                                if (voBluetoothManager.this.mBluetoothHeadset != null) {
                                    voBluetoothManager.this.mBluetoothAdapter.closeProfileProxy(1, voBluetoothManager.this.mBluetoothHeadset);
                                }
                                voBluetoothManager.this.mBluetoothHeadset = (BluetoothHeadset) bluetoothProfile;
                                int profileConnectionState = voBluetoothManager.this.mBluetoothAdapter.getProfileConnectionState(1);
                                voLog.i(voBluetoothManager.TAG, "getProfileConnectionState: HEADSET, %d", Integer.valueOf(profileConnectionState));
                                if (profileConnectionState == 2) {
                                    voBluetoothManager.this.mStatusChangeListener.SetParam(4134L, 1);
                                    return;
                                }
                                return;
                            }
                            if (i == 2) {
                                if (voBluetoothManager.this.mBluetoothA2dp != null) {
                                    voBluetoothManager.this.mBluetoothAdapter.closeProfileProxy(2, voBluetoothManager.this.mBluetoothA2dp);
                                }
                                voBluetoothManager.this.mBluetoothA2dp = (BluetoothA2dp) bluetoothProfile;
                                int profileConnectionState2 = voBluetoothManager.this.mBluetoothAdapter.getProfileConnectionState(2);
                                voLog.i(voBluetoothManager.TAG, "getProfileConnectionState: A2DP, %d", Integer.valueOf(profileConnectionState2));
                                if (profileConnectionState2 == 2) {
                                    voBluetoothManager.this.mStatusChangeListener.SetParam(4134L, 1);
                                }
                            }
                        }

                        @Override // android.bluetooth.BluetoothProfile.ServiceListener
                        public void onServiceDisconnected(int i) {
                            voLog.i(voBluetoothManager.TAG, "onServiceDisconnected, %d", Integer.valueOf(i));
                            if (i == 1 || i == 2) {
                                voBluetoothManager.this.mStatusChangeListener.SetParam(4134L, 0);
                            }
                        }
                    };
                    BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                    this.mBluetoothAdapter = defaultAdapter;
                    if (defaultAdapter != null && defaultAdapter.isEnabled()) {
                        voLog.i(TAG, "GetProfileProxy", new Object[0]);
                        this.mBluetoothAdapter.getProfileProxy(this.mContext, this.mProfileListener, 1);
                        this.mBluetoothAdapter.getProfileProxy(this.mContext, this.mProfileListener, 2);
                    }
                    IntentFilter intentFilter = new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED");
                    IntentFilter intentFilter2 = new IntentFilter("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
                    IntentFilter intentFilter3 = new IntentFilter("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
                    IntentFilter intentFilter4 = new IntentFilter("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
                    this.mContext.registerReceiver(this.mBroadcastReceiver, intentFilter);
                    this.mContext.registerReceiver(this.mBroadcastReceiver, intentFilter2);
                    this.mContext.registerReceiver(this.mBroadcastReceiver, intentFilter3);
                    this.mContext.registerReceiver(this.mBroadcastReceiver, intentFilter4);
                }
            } catch (Exception e) {
                voLog.e(TAG, "%s", e.getMessage());
            }
            this.mIsConnected = isBluetoothConnected();
        }
    }

    public void uninit() {
        if (this.mContext.checkCallingOrSelfPermission("android.permission.BLUETOOTH") == 0) {
            try {
                BluetoothHeadset bluetoothHeadset = this.mBluetoothHeadset;
                if (bluetoothHeadset != null) {
                    this.mBluetoothAdapter.closeProfileProxy(1, bluetoothHeadset);
                }
                BluetoothA2dp bluetoothA2dp = this.mBluetoothA2dp;
                if (bluetoothA2dp != null) {
                    this.mBluetoothAdapter.closeProfileProxy(2, bluetoothA2dp);
                }
                this.mContext.unregisterReceiver(this.mBroadcastReceiver);
            } catch (Exception e) {
                voLog.e(TAG, "%s", e.getMessage());
            }
        }
    }

    public boolean updateConnectionStatus() {
        boolean zIsBluetoothConnected = isBluetoothConnected();
        if (this.mIsConnected == zIsBluetoothConnected) {
            return false;
        }
        this.mIsConnected = zIsBluetoothConnected;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isBluetoothConnected() {
        /*
            java.lang.String r0 = "@@@voBluetoothManager.java"
            r1 = 0
            boolean r2 = com.visualon.OSMPUtils.voBluetoothManager.isPermissionGranted     // Catch: java.lang.Exception -> L22
            if (r2 == 0) goto L43
            android.bluetooth.BluetoothAdapter r2 = android.bluetooth.BluetoothAdapter.getDefaultAdapter()     // Catch: java.lang.Exception -> L22
            if (r2 == 0) goto L43
            boolean r3 = r2.isEnabled()     // Catch: java.lang.Exception -> L22
            if (r3 == 0) goto L43
            r3 = 2
            int r4 = r2.getProfileConnectionState(r3)     // Catch: java.lang.Exception -> L22
            r5 = 1
            if (r3 == r4) goto L44
            int r2 = r2.getProfileConnectionState(r5)     // Catch: java.lang.Exception -> L22
            if (r3 != r2) goto L43
            goto L44
        L22:
            r2 = move-exception
            boolean r3 = com.visualon.OSMPUtils.voLog.enablePrintLog()
            if (r3 == 0) goto L43
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "isBluetoothConnected Exception:"
            r3.append(r4)
            java.lang.String r2 = r2.toString()
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            java.lang.Object[] r3 = new java.lang.Object[r1]
            com.visualon.OSMPUtils.voLog.i(r0, r2, r3)
        L43:
            r5 = r1
        L44:
            boolean r2 = com.visualon.OSMPUtils.voLog.enablePrintLog()
            if (r2 == 0) goto L60
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "[BT] Bluetooth connection state:"
            r2.append(r3)
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            java.lang.Object[] r1 = new java.lang.Object[r1]
            com.visualon.OSMPUtils.voLog.i(r0, r2, r1)
        L60:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.visualon.OSMPUtils.voBluetoothManager.isBluetoothConnected():boolean");
    }
}
