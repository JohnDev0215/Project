package com.android.bluetooth.sap;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.bluetooth.BluetoothUuid;
import android.bluetooth.IBluetoothSap;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.android.bluetooth.BluetoothMetricsProto;
import com.android.bluetooth.R;
import com.android.bluetooth.Utils;
import com.android.bluetooth.btservice.AdapterService;
import com.android.bluetooth.btservice.MetricsLogger;
import com.android.bluetooth.btservice.ProfileService;
import com.android.bluetooth.sdp.SdpManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class SapService extends ProfileService {
    private static final int CREATE_RETRY_TIME = 10;
    public static final boolean DEBUG = false;
    public static final int MSG_ACQUIRE_WAKE_LOCK = 5005;
    public static final int MSG_CHANGE_STATE = 5007;
    public static final int MSG_RELEASE_WAKE_LOCK = 5006;
    public static final int MSG_SERVERSESSION_CLOSE = 5000;
    public static final int MSG_SESSION_DISCONNECTED = 5002;
    public static final int MSG_SESSION_ESTABLISHED = 5001;
    private static final int RELEASE_WAKE_LOCK_DELAY = 1000;
    private static final String SDP_SAP_SERVICE_NAME = "SIM Access";
    private static final int SDP_SAP_VERSION = 258;
    private static final int SHUTDOWN = 3;
    private static final int START_LISTENER = 1;
    private static final String TAG = "SapService";
    public static final String USER_CONFIRM_TIMEOUT_ACTION = "com.android.bluetooth.sap.USER_CONFIRM_TIMEOUT";
    private static final int USER_CONFIRM_TIMEOUT_VALUE = 25000;
    private static final int USER_TIMEOUT = 2;
    public static final boolean VERBOSE = false;
    private static SapService sSapService;
    private BluetoothAdapter mAdapter;
    private volatile boolean mInterrupted;
    private static String sRemoteDeviceName = null;
    private static final ParcelUuid[] SAP_UUIDS = {BluetoothUuid.SAP};
    private PowerManager.WakeLock mWakeLock = null;
    private SocketAcceptThread mAcceptThread = null;
    private BluetoothServerSocket mServerSocket = null;
    private int mSdpHandle = -1;
    private BluetoothSocket mConnSocket = null;
    private BluetoothDevice mRemoteDevice = null;
    private SapServer mSapServer = null;
    private AlarmManager mAlarmManager = null;
    private boolean mRemoveTimeoutMsg = false;
    private boolean mIsWaitingAuthorization = false;
    private boolean mIsRegistered = false;
    private final Handler mSessionStatusHandler = new Handler() { // from class: com.android.bluetooth.sap.SapService.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int i = msg.what;
            if (i == 1) {
                if (SapService.this.mAdapter.isEnabled()) {
                    SapService.this.startRfcommSocketListener();
                    return;
                }
                return;
            }
            if (i == 2) {
                if (SapService.this.mIsWaitingAuthorization) {
                    SapService sapService = SapService.this;
                    sapService.sendCancelUserConfirmationIntent(sapService.mRemoteDevice);
                    SapService.this.cancelUserTimeoutAlarm();
                    SapService.this.mIsWaitingAuthorization = false;
                    SapService.this.stopSapServerSession();
                    return;
                }
                return;
            }
            if (i == 3) {
                SapService.this.closeService();
                return;
            }
            if (i != 5000) {
                switch (i) {
                    case SapService.MSG_ACQUIRE_WAKE_LOCK /* 5005 */:
                        if (SapService.this.mWakeLock == null) {
                            PowerManager pm = (PowerManager) SapService.this.getSystemService("power");
                            SapService.this.mWakeLock = pm.newWakeLock(1, "StartingObexMapTransaction");
                            SapService.this.mWakeLock.setReferenceCounted(false);
                        }
                        if (!SapService.this.mWakeLock.isHeld()) {
                            SapService.this.mWakeLock.acquire();
                        }
                        SapService.this.mSessionStatusHandler.removeMessages(SapService.MSG_RELEASE_WAKE_LOCK);
                        SapService.this.mSessionStatusHandler.sendMessageDelayed(SapService.this.mSessionStatusHandler.obtainMessage(SapService.MSG_RELEASE_WAKE_LOCK), 1000L);
                        break;
                    case SapService.MSG_RELEASE_WAKE_LOCK /* 5006 */:
                        if (SapService.this.mWakeLock != null) {
                            SapService.this.mWakeLock.release();
                        }
                        break;
                    case SapService.MSG_CHANGE_STATE /* 5007 */:
                        SapService.this.setState(msg.arg1);
                        break;
                }
                return;
            }
            SapService.this.stopSapServerSession();
        }
    };
    private SapBroadcastReceiver mSapReceiver = new SapBroadcastReceiver();
    private int mState = 0;

    public static void notifyUpdateWakeLock(Handler messageHandler) {
        if (messageHandler != null) {
            Message msg = Message.obtain(messageHandler);
            msg.what = MSG_ACQUIRE_WAKE_LOCK;
            msg.sendToTarget();
        }
    }

    private void removeSdpRecord() {
        if (this.mAdapter != null && this.mSdpHandle >= 0 && SdpManager.getDefaultManager() != null) {
            SdpManager.getDefaultManager().removeSdpRecord(this.mSdpHandle);
            this.mSdpHandle = -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startRfcommSocketListener() {
        if (this.mAcceptThread == null) {
            SocketAcceptThread socketAcceptThread = new SocketAcceptThread();
            this.mAcceptThread = socketAcceptThread;
            socketAcceptThread.setName("SapAcceptThread");
            this.mAcceptThread.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean initSocket() {
        BluetoothAdapter bluetoothAdapter;
        boolean initSocketOK = false;
        int i = 0;
        while (true) {
            if (i < 10 && !this.mInterrupted) {
                initSocketOK = true;
                try {
                    this.mServerSocket = this.mAdapter.listenUsingRfcommOn(-2, true, true);
                    removeSdpRecord();
                    this.mSdpHandle = SdpManager.getDefaultManager().createSapsRecord(SDP_SAP_SERVICE_NAME, this.mServerSocket.getChannel(), 258);
                } catch (IOException e) {
                    Log.e(TAG, "Error create RfcommServerSocket ", e);
                    initSocketOK = false;
                }
                if (!initSocketOK && (bluetoothAdapter = this.mAdapter) != null) {
                    int state = bluetoothAdapter.getState();
                    if (state != 11 && state != 12) {
                        Log.w(TAG, "initServerSocket failed as BT is (being) turned off");
                        break;
                    }
                    try {
                        Thread.sleep(300L);
                    } catch (InterruptedException e2) {
                        Log.e(TAG, "socketAcceptThread thread was interrupted (3)", e2);
                    }
                    i++;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        if (!initSocketOK) {
            Log.e(TAG, "Error to create listening socket after 10 try");
        }
        return initSocketOK;
    }

    private synchronized void closeServerSocket() {
        if (this.mServerSocket != null) {
            try {
                this.mServerSocket.close();
                this.mServerSocket = null;
            } catch (IOException ex) {
                Log.e(TAG, "Close Server Socket error: ", ex);
            }
        }
    }

    private synchronized void closeConnectionSocket() {
        if (this.mConnSocket != null) {
            try {
                this.mConnSocket.close();
                this.mConnSocket = null;
            } catch (IOException e) {
                Log.e(TAG, "Close Connection Socket error: ", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeService() {
        this.mInterrupted = true;
        closeServerSocket();
        SocketAcceptThread socketAcceptThread = this.mAcceptThread;
        if (socketAcceptThread != null) {
            try {
                socketAcceptThread.shutdown();
                this.mAcceptThread.join();
                this.mAcceptThread = null;
            } catch (InterruptedException ex) {
                Log.w(TAG, "mAcceptThread close error", ex);
            }
        }
        if (this.mWakeLock != null) {
            this.mSessionStatusHandler.removeMessages(MSG_ACQUIRE_WAKE_LOCK);
            this.mSessionStatusHandler.removeMessages(MSG_RELEASE_WAKE_LOCK);
            this.mWakeLock.release();
            this.mWakeLock = null;
        }
        closeConnectionSocket();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSapServerSession() throws IOException {
        if (this.mWakeLock == null) {
            PowerManager pm = (PowerManager) getSystemService("power");
            PowerManager.WakeLock wakeLockNewWakeLock = pm.newWakeLock(1, "StartingSapTransaction");
            this.mWakeLock = wakeLockNewWakeLock;
            wakeLockNewWakeLock.setReferenceCounted(false);
            this.mWakeLock.acquire();
        }
        SapServer sapServer = new SapServer(this.mSessionStatusHandler, this, this.mConnSocket.getInputStream(), this.mConnSocket.getOutputStream());
        this.mSapServer = sapServer;
        sapServer.start();
        this.mSessionStatusHandler.removeMessages(MSG_RELEASE_WAKE_LOCK);
        Handler handler = this.mSessionStatusHandler;
        handler.sendMessageDelayed(handler.obtainMessage(MSG_RELEASE_WAKE_LOCK), 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopSapServerSession() {
        this.mAcceptThread = null;
        closeConnectionSocket();
        closeServerSocket();
        setState(0);
        PowerManager.WakeLock wakeLock = this.mWakeLock;
        if (wakeLock != null) {
            wakeLock.release();
            this.mWakeLock = null;
        }
        if (this.mAdapter.isEnabled()) {
            startRfcommSocketListener();
        }
    }

    private class SocketAcceptThread extends Thread {
        private boolean mStopped;

        private SocketAcceptThread() {
            this.mStopped = false;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            BluetoothServerSocket serverSocket;
            if (SapService.this.mServerSocket == null && !SapService.this.initSocket()) {
                return;
            }
            while (!this.mStopped) {
                try {
                    serverSocket = SapService.this.mServerSocket;
                } catch (IOException e) {
                    this.mStopped = true;
                }
                if (serverSocket == null) {
                    Log.w(SapService.TAG, "mServerSocket is null");
                    return;
                }
                SapService.this.mConnSocket = SapService.this.mServerSocket.accept();
                synchronized (SapService.this) {
                    if (SapService.this.mConnSocket == null) {
                        Log.w(SapService.TAG, "mConnSocket is null");
                        return;
                    }
                    SapService.this.mRemoteDevice = SapService.this.mConnSocket.getRemoteDevice();
                    if (SapService.this.mRemoteDevice != null) {
                        String unused = SapService.sRemoteDeviceName = SapService.this.mRemoteDevice.getName();
                        if (TextUtils.isEmpty(SapService.sRemoteDeviceName)) {
                            String unused2 = SapService.sRemoteDeviceName = SapService.this.getString(R.string.defaultname);
                        }
                        int permission = SapService.this.mRemoteDevice.getSimAccessPermission();
                        if (permission == 1) {
                            try {
                                SapService.this.startSapServerSession();
                            } catch (IOException ex) {
                                Log.e(SapService.TAG, "catch exception starting obex server session", ex);
                            }
                        } else if (permission == 2) {
                            Log.w(SapService.TAG, "Can't connect with " + SapService.sRemoteDeviceName + " as access is rejected");
                            if (SapService.this.mSessionStatusHandler != null) {
                                SapService.this.mSessionStatusHandler.sendEmptyMessage(SapService.MSG_SERVERSESSION_CLOSE);
                            }
                        } else {
                            Intent intent = new Intent("android.bluetooth.device.action.CONNECTION_ACCESS_REQUEST");
                            intent.setPackage(SapService.this.getString(R.string.pairing_ui_package));
                            intent.putExtra("android.bluetooth.device.extra.ACCESS_REQUEST_TYPE", 4);
                            intent.putExtra("android.bluetooth.device.extra.DEVICE", SapService.this.mRemoteDevice);
                            intent.putExtra("android.bluetooth.device.extra.PACKAGE_NAME", SapService.this.getPackageName());
                            SapService.this.mIsWaitingAuthorization = true;
                            SapService.this.setUserTimeoutAlarm();
                            SapService.this.sendBroadcast(intent, "android.permission.BLUETOOTH_ADMIN");
                        }
                        this.mStopped = true;
                    } else {
                        Log.i(SapService.TAG, "getRemoteDevice() = null");
                        return;
                    }
                    this.mStopped = true;
                }
            }
        }

        void shutdown() {
            this.mStopped = true;
            interrupt();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setState(int state) {
        setState(state, 1);
    }

    private synchronized void setState(int state, int result) {
        if (state != this.mState) {
            if (state == 2) {
                MetricsLogger.logProfileConnectionEvent(BluetoothMetricsProto.ProfileId.SAP);
            }
            int prevState = this.mState;
            this.mState = state;
            Intent intent = new Intent("android.bluetooth.sap.profile.action.CONNECTION_STATE_CHANGED");
            intent.putExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", prevState);
            intent.putExtra("android.bluetooth.profile.extra.STATE", this.mState);
            intent.putExtra("android.bluetooth.device.extra.DEVICE", this.mRemoteDevice);
            sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
        }
    }

    public int getState() {
        return this.mState;
    }

    public BluetoothDevice getRemoteDevice() {
        return this.mRemoteDevice;
    }

    public static String getRemoteDeviceName() {
        return sRemoteDeviceName;
    }

    public boolean disconnect(BluetoothDevice device) {
        boolean result = false;
        synchronized (this) {
            if (this.mRemoteDevice != null && this.mRemoteDevice.equals(device) && this.mState == 2) {
                closeConnectionSocket();
                setState(0, 2);
                result = true;
            }
        }
        return result;
    }

    public List<BluetoothDevice> getConnectedDevices() {
        List<BluetoothDevice> devices = new ArrayList<>();
        synchronized (this) {
            if (this.mState == 2 && this.mRemoteDevice != null) {
                devices.add(this.mRemoteDevice);
            }
        }
        return devices;
    }

    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
        List<BluetoothDevice> deviceList = new ArrayList<>();
        Set<BluetoothDevice> bondedDevices = this.mAdapter.getBondedDevices();
        synchronized (this) {
            for (BluetoothDevice device : bondedDevices) {
                ParcelUuid[] featureUuids = device.getUuids();
                if (BluetoothUuid.containsAnyUuid(featureUuids, SAP_UUIDS)) {
                    int connectionState = getConnectionState(device);
                    for (int i : states) {
                        if (connectionState == i) {
                            deviceList.add(device);
                        }
                    }
                }
            }
        }
        return deviceList;
    }

    public int getConnectionState(BluetoothDevice device) {
        synchronized (this) {
            return (getState() == 2 && getRemoteDevice().equals(device)) ? 2 : 0;
        }
    }

    public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        AdapterService.getAdapterService().getDatabase().setProfileConnectionPolicy(device, 10, connectionPolicy);
        if (connectionPolicy == 0) {
            disconnect(device);
            return true;
        }
        return true;
    }

    public int getConnectionPolicy(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        return AdapterService.getAdapterService().getDatabase().getProfileConnectionPolicy(device, 10);
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected ProfileService.IProfileServiceBinder initBinder() {
        return new SapBinder(this);
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean start() {
        Log.v(TAG, "start()");
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.bluetooth.device.action.CONNECTION_ACCESS_REPLY");
        filter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        filter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        filter.addAction(USER_CONFIRM_TIMEOUT_ACTION);
        try {
            registerReceiver(this.mSapReceiver, filter);
            this.mIsRegistered = true;
        } catch (Exception e) {
            Log.w(TAG, "Unable to register sap receiver", e);
        }
        this.mInterrupted = false;
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        Handler handler = this.mSessionStatusHandler;
        handler.sendMessage(handler.obtainMessage(1));
        setSapService(this);
        return true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean stop() {
        Log.v(TAG, "stop()");
        if (!this.mIsRegistered) {
            Log.i(TAG, "Avoid unregister when receiver it is not registered");
            return true;
        }
        setSapService(null);
        try {
            this.mIsRegistered = false;
            unregisterReceiver(this.mSapReceiver);
        } catch (Exception e) {
            Log.w(TAG, "Unable to unregister sap receiver", e);
        }
        setState(0, 2);
        sendShutdownMessage();
        return true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    public void cleanup() {
        setState(0, 2);
        closeService();
        Handler handler = this.mSessionStatusHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public static synchronized SapService getSapService() {
        if (sSapService == null) {
            Log.w(TAG, "getSapService(): service is null");
            return null;
        }
        if (!sSapService.isAvailable()) {
            Log.w(TAG, "getSapService(): service is not available");
            return null;
        }
        return sSapService;
    }

    private static synchronized void setSapService(SapService instance) {
        sSapService = instance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUserTimeoutAlarm() {
        cancelUserTimeoutAlarm();
        this.mRemoveTimeoutMsg = true;
        Intent timeoutIntent = new Intent(USER_CONFIRM_TIMEOUT_ACTION);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, 0, timeoutIntent, 0);
        this.mAlarmManager.set(0, System.currentTimeMillis() + 25000, pIntent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelUserTimeoutAlarm() {
        if (this.mAlarmManager == null) {
            this.mAlarmManager = (AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM);
        }
        if (this.mRemoveTimeoutMsg) {
            Intent timeoutIntent = new Intent(USER_CONFIRM_TIMEOUT_ACTION);
            PendingIntent sender = PendingIntent.getBroadcast(this, 0, timeoutIntent, 0);
            this.mAlarmManager.cancel(sender);
            this.mRemoveTimeoutMsg = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCancelUserConfirmationIntent(BluetoothDevice device) {
        Intent intent = new Intent("android.bluetooth.device.action.CONNECTION_ACCESS_CANCEL");
        intent.setPackage(getString(R.string.pairing_ui_package));
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        intent.putExtra("android.bluetooth.device.extra.ACCESS_REQUEST_TYPE", 4);
        sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendShutdownMessage() {
        if (this.mRemoveTimeoutMsg) {
            Intent timeoutIntent = new Intent(USER_CONFIRM_TIMEOUT_ACTION);
            sendBroadcast(timeoutIntent, ProfileService.BLUETOOTH_PERM);
            this.mIsWaitingAuthorization = false;
            cancelUserTimeoutAlarm();
        }
        removeSdpRecord();
        this.mSessionStatusHandler.removeCallbacksAndMessages(null);
        this.mSessionStatusHandler.obtainMessage(3).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendConnectTimeoutMessage() {
        Handler handler = this.mSessionStatusHandler;
        if (handler != null) {
            Message msg = handler.obtainMessage(2);
            msg.sendToTarget();
        }
    }

    private class SapBroadcastReceiver extends BroadcastReceiver {
        private SapBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                int state = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                if (state == 13) {
                    SapService.this.sendShutdownMessage();
                    return;
                } else {
                    if (state == 12) {
                        SapService.this.mSessionStatusHandler.sendMessage(SapService.this.mSessionStatusHandler.obtainMessage(1));
                        return;
                    }
                    return;
                }
            }
            if (action.equals("android.bluetooth.device.action.CONNECTION_ACCESS_REPLY")) {
                Log.v(SapService.TAG, " - Received BluetoothDevice.ACTION_CONNECTION_ACCESS_REPLY");
                int requestType = intent.getIntExtra("android.bluetooth.device.extra.ACCESS_REQUEST_TYPE", -1);
                if (requestType == 4) {
                    SapService.this.mIsWaitingAuthorization = false;
                    if (intent.getIntExtra("android.bluetooth.device.extra.CONNECTION_ACCESS_RESULT", 2) == 1) {
                        if (intent.getBooleanExtra("android.bluetooth.device.extra.ALWAYS_ALLOWED", false)) {
                            SapService.this.mRemoteDevice.setSimAccessPermission(1);
                        }
                        SapService sapService = SapService.this;
                        boolean result = sapService.setConnectionPolicy(sapService.mRemoteDevice, 100);
                        Log.d(SapService.TAG, "setConnectionPolicy ALLOWED, result = " + result);
                        try {
                            if (SapService.this.mConnSocket != null) {
                                SapService.this.startSapServerSession();
                            } else {
                                SapService.this.stopSapServerSession();
                            }
                            return;
                        } catch (IOException ex) {
                            Log.e(SapService.TAG, "Caught the error: ", ex);
                            return;
                        }
                    }
                    if (intent.getBooleanExtra("android.bluetooth.device.extra.ALWAYS_ALLOWED", false)) {
                        SapService.this.mRemoteDevice.setSimAccessPermission(2);
                    }
                    SapService sapService2 = SapService.this;
                    boolean result2 = sapService2.setConnectionPolicy(sapService2.mRemoteDevice, 0);
                    Log.d(SapService.TAG, "setConnectionPolicy FORBIDDEN, result = " + result2);
                    SapService.this.mSessionStatusHandler.sendEmptyMessage(SapService.MSG_SERVERSESSION_CLOSE);
                    return;
                }
                return;
            }
            if (action.equals(SapService.USER_CONFIRM_TIMEOUT_ACTION)) {
                SapService.this.sendConnectTimeoutMessage();
                return;
            }
            if (action.equals("android.bluetooth.device.action.ACL_DISCONNECTED") && SapService.this.mIsWaitingAuthorization) {
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (SapService.this.mRemoteDevice != null && device != null) {
                    if (SapService.this.mRemoteDevice.equals(device)) {
                        if (SapService.this.mRemoveTimeoutMsg) {
                            SapService.this.mSessionStatusHandler.removeMessages(2);
                            SapService.this.mSessionStatusHandler.obtainMessage(2).sendToTarget();
                        }
                        SapService.this.setState(0);
                        SapService.this.mSessionStatusHandler.sendEmptyMessage(SapService.MSG_SERVERSESSION_CLOSE);
                        return;
                    }
                    return;
                }
                Log.i(SapService.TAG, "Unexpected error!");
            }
        }
    }

    private static class SapBinder extends IBluetoothSap.Stub implements ProfileService.IProfileServiceBinder {
        private SapService mService;

        private SapService getService() {
            if (!Utils.checkCaller()) {
                Log.w(SapService.TAG, "call not allowed for non-active user");
                return null;
            }
            SapService sapService = this.mService;
            if (sapService == null || !sapService.isAvailable()) {
                return null;
            }
            this.mService.enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
            return this.mService;
        }

        SapBinder(SapService service) {
            Log.v(SapService.TAG, "SapBinder()");
            this.mService = service;
        }

        @Override // com.android.bluetooth.btservice.ProfileService.IProfileServiceBinder
        public void cleanup() {
            this.mService = null;
        }

        public int getState() {
            Log.v(SapService.TAG, "getState()");
            SapService service = getService();
            if (service == null) {
                return 0;
            }
            return getService().getState();
        }

        public BluetoothDevice getClient() {
            Log.v(SapService.TAG, "getClient()");
            SapService service = getService();
            if (service == null) {
                return null;
            }
            Log.v(SapService.TAG, "getClient() - returning " + service.getRemoteDevice());
            return service.getRemoteDevice();
        }

        public boolean isConnected(BluetoothDevice device) {
            Log.v(SapService.TAG, "isConnected()");
            SapService service = getService();
            return service != null && service.getState() == 2 && service.getRemoteDevice().equals(device);
        }

        public boolean connect(BluetoothDevice device) {
            Log.v(SapService.TAG, "connect()");
            SapService service = getService();
            return service == null ? false : false;
        }

        public boolean disconnect(BluetoothDevice device) {
            Log.v(SapService.TAG, "disconnect()");
            SapService service = getService();
            if (service == null) {
                return false;
            }
            return service.disconnect(device);
        }

        public List<BluetoothDevice> getConnectedDevices() {
            Log.v(SapService.TAG, "getConnectedDevices()");
            SapService service = getService();
            if (service == null) {
                return new ArrayList(0);
            }
            return service.getConnectedDevices();
        }

        public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
            Log.v(SapService.TAG, "getDevicesMatchingConnectionStates()");
            SapService service = getService();
            if (service == null) {
                return new ArrayList(0);
            }
            return service.getDevicesMatchingConnectionStates(states);
        }

        public int getConnectionState(BluetoothDevice device) {
            Log.v(SapService.TAG, "getConnectionState()");
            SapService service = getService();
            if (service == null) {
                return 0;
            }
            return service.getConnectionState(device);
        }

        public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
            SapService service = getService();
            if (service == null) {
                return false;
            }
            return service.setConnectionPolicy(device, connectionPolicy);
        }

        public int getConnectionPolicy(BluetoothDevice device) {
            SapService service = getService();
            if (service == null) {
                return -1;
            }
            return service.getConnectionPolicy(device);
        }
    }
}
