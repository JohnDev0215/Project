package com.android.bluetooth.opp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.os.UserManager;
import androidx.core.content.FileProvider;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothOppFileProvider extends FileProvider {
    private static final String TAG = "BluetoothOppFileProvider";
    private Context mContext = null;
    private ProviderInfo mProviderInfo = null;
    private boolean mRegisteredReceiver = false;
    private boolean mInitialized = false;
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.bluetooth.opp.BluetoothOppFileProvider.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.USER_UNLOCKED".equals(intent.getAction())) {
                BluetoothOppFileProvider bluetoothOppFileProvider = BluetoothOppFileProvider.this;
                bluetoothOppFileProvider.attachInfo(bluetoothOppFileProvider.mContext, BluetoothOppFileProvider.this.mProviderInfo);
            }
        }
    };

    @Override // androidx.core.content.FileProvider, android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo info) {
        synchronized (this) {
            this.mContext = context;
            this.mProviderInfo = info;
            if (!this.mRegisteredReceiver) {
                IntentFilter userFilter = new IntentFilter();
                userFilter.addAction("android.intent.action.USER_UNLOCKED");
                this.mContext.registerReceiver(this.mBroadcastReceiver, userFilter, null, null);
                this.mRegisteredReceiver = true;
            }
            UserManager userManager = (UserManager) this.mContext.getSystemService("user");
            if (userManager.isUserUnlocked()) {
                if (!this.mInitialized) {
                    super.attachInfo(this.mContext, this.mProviderInfo);
                    this.mInitialized = true;
                }
                if (this.mRegisteredReceiver) {
                    this.mContext.unregisterReceiver(this.mBroadcastReceiver);
                    this.mRegisteredReceiver = false;
                }
            }
        }
    }

    public static Uri getUriForFile(Context context, String authority, File file) {
        UserManager userManager = (UserManager) context.getSystemService("user");
        if (!userManager.isUserUnlocked()) {
            return null;
        }
        return FileProvider.getUriForFile(context.createCredentialProtectedStorageContext(), authority, file);
    }
}
