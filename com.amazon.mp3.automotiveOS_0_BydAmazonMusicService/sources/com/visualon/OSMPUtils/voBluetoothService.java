package com.visualon.OSMPUtils;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import java.util.List;

/* JADX INFO: loaded from: classes7.dex */
public class voBluetoothService extends Service {
    private static final String TAG = "@@@voBluetoothService.java";
    private BluetoothDevice mConnectedHeadset;
    private final IBinder mBinder = new LocalBinder();
    onStatusChangeListener mStatusChangeListener = null;
    private BluetoothAdapter mBluetoothAdapter = null;
    private BluetoothHeadset mBluetoothHeadset = null;
    private BluetoothProfile.ServiceListener mProfileListener = new BluetoothProfile.ServiceListener() { // from class: com.visualon.OSMPUtils.voBluetoothService.1
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            if (i == 1) {
                voBluetoothService.this.mBluetoothHeadset = (BluetoothHeadset) bluetoothProfile;
                List<BluetoothDevice> connectedDevices = voBluetoothService.this.mBluetoothHeadset.getConnectedDevices();
                if (connectedDevices.size() > 0) {
                    voBluetoothService.this.mConnectedHeadset = connectedDevices.get(0);
                }
                onStatusChangeListener onstatuschangelistener = voBluetoothService.this.mStatusChangeListener;
                if (onstatuschangelistener != null) {
                    onstatuschangelistener.SetParam(4134L, 1);
                }
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            onStatusChangeListener onstatuschangelistener;
            if (i != 1 || (onstatuschangelistener = voBluetoothService.this.mStatusChangeListener) == null) {
                return;
            }
            onstatuschangelistener.SetParam(4134L, 0);
        }
    };
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.visualon.OSMPUtils.voBluetoothService.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.bluetooth.adapter.action.STATE_CHANGED") && intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE) == 12 && voBluetoothService.this.mBluetoothHeadset == null && voBluetoothService.this.mBluetoothAdapter.isEnabled()) {
                voBluetoothService.this.mBluetoothAdapter.getProfileProxy(context, voBluetoothService.this.mProfileListener, 1);
            }
        }
    };

    public interface onStatusChangeListener {
        int SetParam(long j, Object obj);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }

    public void setStatusChangeListener(onStatusChangeListener onstatuschangelistener) {
        this.mStatusChangeListener = onstatuschangelistener;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    public class LocalBinder extends Binder {
        public LocalBinder() {
        }

        public voBluetoothService getService() {
            return voBluetoothService.this;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (voLog.enablePrintLog()) {
            voLog.d(TAG, "onBind", new Object[0]);
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBluetoothAdapter = defaultAdapter;
        if (defaultAdapter != null && defaultAdapter.isEnabled()) {
            this.mBluetoothAdapter.getProfileProxy(this, this.mProfileListener, 1);
        }
        registerReceiver(this.mBroadcastReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        return this.mBinder;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        if (voLog.enablePrintLog()) {
            voLog.d(TAG, "onUnbind", new Object[0]);
        }
        this.mBluetoothAdapter.closeProfileProxy(1, this.mBluetoothHeadset);
        this.mBluetoothHeadset = null;
        this.mBluetoothAdapter = null;
        onStatusChangeListener onstatuschangelistener = this.mStatusChangeListener;
        if (onstatuschangelistener != null) {
            onstatuschangelistener.SetParam(4134L, 0);
        }
        this.mStatusChangeListener = null;
        unregisterReceiver(this.mBroadcastReceiver);
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }
}
