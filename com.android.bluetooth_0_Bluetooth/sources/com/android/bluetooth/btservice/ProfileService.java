package com.android.bluetooth.btservice;

import android.app.ActivityManager;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.UserManager;
import android.util.Log;
import com.android.bluetooth.BluetoothMetricsProto;
import com.android.bluetooth.Utils;

/* JADX INFO: loaded from: classes.dex */
public abstract class ProfileService extends Service {
    public static final String BLUETOOTH_ADMIN_PERM = "android.permission.BLUETOOTH_ADMIN";
    public static final String BLUETOOTH_PERM = "android.permission.BLUETOOTH";
    public static final String BLUETOOTH_PRIVILEGED = "android.permission.BLUETOOTH_PRIVILEGED";
    private static final boolean DBG = false;
    private static final int PROFILE_SERVICE_MODE = 2;
    private BluetoothAdapter mAdapter;
    private AdapterService mAdapterService;
    private IProfileServiceBinder mBinder;
    private BroadcastReceiver mUserSwitchedReceiver;
    private boolean mProfileStarted = false;
    private final String mName = getName();

    public interface IProfileServiceBinder extends IBinder {
        void cleanup();
    }

    protected abstract IProfileServiceBinder initBinder();

    protected abstract boolean start();

    protected abstract boolean stop();

    public String getName() {
        return getClass().getSimpleName();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isAvailable() {
        return this.mProfileStarted;
    }

    protected void create() {
    }

    protected void cleanup() {
    }

    protected void setCurrentUser(int userId) {
    }

    protected void setUserUnlocked(int userId) {
    }

    protected ProfileService() {
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mBinder = initBinder();
        create();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (checkCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN") != 0) {
            Log.e(this.mName, "Permission denied!");
            return 2;
        }
        if (intent == null) {
            Log.d(this.mName, "onStartCommand ignoring null intent.");
            return 2;
        }
        String action = intent.getStringExtra(AdapterService.EXTRA_ACTION);
        if (AdapterService.ACTION_SERVICE_STATE_CHANGED.equals(action)) {
            int state = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
            if (state == 10) {
                doStop();
            } else if (state == 12) {
                doStart();
            }
        }
        return 2;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (this.mAdapter != null && this.mBinder == null) {
            throw new UnsupportedOperationException("Cannot bind to " + this.mName);
        }
        return this.mBinder;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void dump(StringBuilder sb) {
        sb.append("\nProfile: ");
        sb.append(this.mName);
        sb.append("\n");
    }

    public void dumpProto(BluetoothMetricsProto.BluetoothLog.Builder builder) {
    }

    public static void println(StringBuilder sb, String s) {
        sb.append("  ");
        sb.append(s);
        sb.append("\n");
    }

    @Override // android.app.Service
    public void onDestroy() {
        cleanup();
        IProfileServiceBinder iProfileServiceBinder = this.mBinder;
        if (iProfileServiceBinder != null) {
            iProfileServiceBinder.cleanup();
            this.mBinder = null;
        }
        this.mAdapter = null;
        super.onDestroy();
    }

    private void doStart() {
        if (this.mAdapter == null) {
            Log.w(this.mName, "Can't start profile service: device does not have BT");
            return;
        }
        AdapterService adapterService = AdapterService.getAdapterService();
        this.mAdapterService = adapterService;
        if (adapterService == null) {
            Log.w(this.mName, "Could not add this profile because AdapterService is null.");
            return;
        }
        adapterService.addProfile(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.USER_SWITCHED");
        filter.addAction("android.intent.action.USER_UNLOCKED");
        this.mUserSwitchedReceiver = new BroadcastReceiver() { // from class: com.android.bluetooth.btservice.ProfileService.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                int userId = intent.getIntExtra("android.intent.extra.user_handle", -10000);
                if (userId == -10000) {
                    Log.e(ProfileService.this.mName, "userChangeReceiver received an invalid EXTRA_USER_HANDLE");
                    return;
                }
                if ("android.intent.action.USER_SWITCHED".equals(action)) {
                    Log.d(ProfileService.this.mName, "User switched to userId " + userId);
                    ProfileService.this.setCurrentUser(userId);
                    return;
                }
                if ("android.intent.action.USER_UNLOCKED".equals(intent.getAction())) {
                    Log.d(ProfileService.this.mName, "Unlocked userId " + userId);
                    ProfileService.this.setUserUnlocked(userId);
                }
            }
        };
        getApplicationContext().registerReceiver(this.mUserSwitchedReceiver, filter);
        int currentUserId = ActivityManager.getCurrentUser();
        setCurrentUser(currentUserId);
        UserManager userManager = UserManager.get(getApplicationContext());
        if (userManager.isUserUnlocked(currentUserId)) {
            setUserUnlocked(currentUserId);
        }
        boolean zStart = start();
        this.mProfileStarted = zStart;
        if (!zStart) {
            Log.e(this.mName, "Error starting profile. start() returned false.");
        } else {
            this.mAdapterService.onProfileServiceStateChanged(this, 12);
        }
    }

    private void doStop() {
        if (!this.mProfileStarted) {
            Log.w(this.mName, "doStop() called, but the profile is not running.");
        }
        this.mProfileStarted = false;
        AdapterService adapterService = this.mAdapterService;
        if (adapterService != null) {
            adapterService.onProfileServiceStateChanged(this, 10);
        }
        if (!stop()) {
            Log.e(this.mName, "Unable to stop profile");
        }
        AdapterService adapterService2 = this.mAdapterService;
        if (adapterService2 != null) {
            adapterService2.removeProfile(this);
        }
        if (this.mUserSwitchedReceiver != null) {
            getApplicationContext().unregisterReceiver(this.mUserSwitchedReceiver);
            this.mUserSwitchedReceiver = null;
        }
        stopSelf();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BluetoothDevice getDevice(byte[] address) {
        BluetoothAdapter bluetoothAdapter = this.mAdapter;
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.getRemoteDevice(Utils.getAddressStringFromByte(address));
        }
        return null;
    }
}
