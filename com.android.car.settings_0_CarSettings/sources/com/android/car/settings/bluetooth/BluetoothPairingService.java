package com.android.car.settings.bluetooth;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.IBinder;
import android.text.TextUtils;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import com.android.car.settings.R;
import com.android.car.settings.common.Logger;

/* JADX INFO: loaded from: classes5.dex */
public final class BluetoothPairingService extends Service {
    private static final String ACTION_DISMISS_PAIRING = "com.android.settings.bluetooth.ACTION_DISMISS_PAIRING";
    private static final String BLUETOOTH_NOTIFICATION_CHANNEL = "bluetooth_notification_channel";
    private static final Logger LOG = new Logger((Class<?>) BluetoothPairingService.class);
    private static final int NOTIFICATION_ID = 17301632;
    private BluetoothDevice mDevice;
    private boolean mRegistered = false;
    private final BroadcastReceiver mCancelReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.bluetooth.BluetoothPairingService.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.bluetooth.device.action.BOND_STATE_CHANGED")) {
                int bondState = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", Integer.MIN_VALUE);
                if (bondState != 10 && bondState != 12) {
                    return;
                }
            } else if (action.equals(BluetoothPairingService.ACTION_DISMISS_PAIRING)) {
                BluetoothPairingService.LOG.d("Notification cancel " + BluetoothPairingService.this.mDevice.getAddress() + " (" + BluetoothPairingService.this.mDevice.getName() + ")");
                BluetoothPairingService.this.mDevice.cancelPairing();
            } else {
                int bondState2 = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", Integer.MIN_VALUE);
                BluetoothPairingService.LOG.d("Dismiss pairing for " + BluetoothPairingService.this.mDevice.getAddress() + " (" + BluetoothPairingService.this.mDevice.getName() + "), BondState: " + bondState2);
            }
            BluetoothPairingService.this.stopForeground(true);
            BluetoothPairingService.this.stopSelf();
        }
    };

    public static Intent getPairingDialogIntent(Context context, Intent intent) {
        BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int type = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_VARIANT", Integer.MIN_VALUE);
        Intent pairingIntent = new Intent();
        pairingIntent.setClass(context, BluetoothPairingDialog.class);
        pairingIntent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        pairingIntent.putExtra("android.bluetooth.device.extra.PAIRING_VARIANT", type);
        if (type == 2 || type == 4 || type == 5) {
            int pairingKey = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_KEY", Integer.MIN_VALUE);
            pairingIntent.putExtra("android.bluetooth.device.extra.PAIRING_KEY", pairingKey);
        }
        pairingIntent.setAction("android.bluetooth.device.action.PAIRING_REQUEST");
        return pairingIntent;
    }

    @Override // android.app.Service
    public void onCreate() {
        NotificationManager mgr = (NotificationManager) getSystemService("notification");
        NotificationChannel notificationChannel = new NotificationChannel(BLUETOOTH_NOTIFICATION_CHANNEL, getString(R.string.bluetooth_settings_title), 4);
        mgr.createNotificationChannel(notificationChannel);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            LOG.e("Can't start: null intent!");
            stopSelf();
            return 2;
        }
        Resources res = getResources();
        Notification.Builder builder = new Notification.Builder(this, BLUETOOTH_NOTIFICATION_CHANNEL).setSmallIcon(17301632).setTicker(res.getString(R.string.bluetooth_notif_ticker)).setLocalOnly(true);
        PendingIntent pairIntent = PendingIntent.getActivity(this, 0, getPairingDialogIntent(this, intent), BasicMeasure.EXACTLY);
        PendingIntent dismissIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_DISMISS_PAIRING), BasicMeasure.EXACTLY);
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        this.mDevice = bluetoothDevice;
        if (bluetoothDevice != null && bluetoothDevice.getBondState() != 11) {
            LOG.w("Device " + this.mDevice + " not bonding: " + this.mDevice.getBondState());
            stopSelf();
            return 2;
        }
        String name = intent.getStringExtra("android.bluetooth.device.extra.NAME");
        if (TextUtils.isEmpty(name)) {
            BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            name = device != null ? device.getAlias() : res.getString(android.R.string.unknownName);
        }
        LOG.d("Show pairing notification for " + this.mDevice.getAddress() + " (" + name + ")");
        Notification.Action pairAction = new Notification.Action.Builder(0, res.getString(R.string.bluetooth_device_context_pair_connect), pairIntent).build();
        Notification.Action dismissAction = new Notification.Action.Builder(0, res.getString(android.R.string.cancel), dismissIntent).build();
        builder.setContentTitle(res.getString(R.string.bluetooth_notif_title)).setContentText(res.getString(R.string.bluetooth_notif_message, name)).setContentIntent(pairIntent).setDefaults(1).setColor(getColor(android.R.color.system_notification_accent_color)).addAction(pairAction).addAction(dismissAction);
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        filter.addAction("android.bluetooth.device.action.PAIRING_CANCEL");
        filter.addAction(ACTION_DISMISS_PAIRING);
        registerReceiver(this.mCancelReceiver, filter);
        this.mRegistered = true;
        startForeground(17301632, builder.getNotification());
        return 3;
    }

    @Override // android.app.Service
    public void onDestroy() {
        if (this.mRegistered) {
            unregisterReceiver(this.mCancelReceiver);
            this.mRegistered = false;
        }
        stopForeground(true);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }
}
