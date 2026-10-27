package com.android.server.telecom;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.provider.Settings;
import android.telecom.DefaultDialerManager;
import android.telecom.Log;
import android.util.SparseArray;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.util.IndentingPrintWriter;
import com.android.server.telecom.TelecomSystem;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes.dex */
public class DefaultDialerCache {
    private final Context mContext;
    private final DefaultDialerManagerAdapter mDefaultDialerManagerAdapter;
    private final TelecomSystem.SyncRoot mLock;
    private ComponentName mOverrideSystemDialerComponentName;
    private final RoleManagerAdapter mRoleManagerAdapter;
    private final ComponentName mSystemDialerComponentName;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.server.telecom.DefaultDialerCache.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.startSession("DDC.oR");
            try {
                String schemeSpecificPart = null;
                if (!"android.intent.action.PACKAGE_CHANGED".equals(intent.getAction())) {
                    if ("android.intent.action.PACKAGE_REMOVED".equals(intent.getAction()) && !intent.getBooleanExtra("android.intent.extra.REPLACING", false)) {
                        schemeSpecificPart = intent.getData().getSchemeSpecificPart();
                    } else if (!"android.intent.action.PACKAGE_ADDED".equals(intent.getAction()) && !"android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
                        return;
                    }
                }
                synchronized (DefaultDialerCache.this.mLock) {
                    DefaultDialerCache.this.refreshCachesForUsersWithPackage(schemeSpecificPart);
                }
            } finally {
                Log.endSession();
            }
        }
    };
    private final BroadcastReceiver mUserRemovedReceiver = new BroadcastReceiver() { // from class: com.android.server.telecom.DefaultDialerCache.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.USER_REMOVED".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("android.intent.extra.user_handle", -10000);
                if (intExtra != -10000) {
                    DefaultDialerCache.this.removeUserFromCache(intExtra);
                    Log.i("DefaultDialerCache", "Removing user %s", new Object[]{Integer.valueOf(intExtra)});
                } else {
                    Log.w("DefaultDialerCache", "Expected EXTRA_USER_HANDLE with ACTION_USER_REMOVED", new Object[0]);
                }
            }
        }
    };
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final ContentObserver mDefaultDialerObserver = new ContentObserver(this.mHandler) { // from class: com.android.server.telecom.DefaultDialerCache.3
        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            Log.startSession("DDC.oC");
            try {
                synchronized (DefaultDialerCache.this.mLock) {
                    DefaultDialerCache.this.refreshCachesForUsersWithPackage(null);
                }
            } finally {
                Log.endSession();
            }
        }
    };
    private SparseArray<String> mCurrentDefaultDialerPerUser = new SparseArray<>();

    public interface DefaultDialerManagerAdapter {
        boolean setDefaultDialerApplication(Context context, String str, int i);
    }

    static class DefaultDialerManagerAdapterImpl implements DefaultDialerManagerAdapter {
        DefaultDialerManagerAdapterImpl() {
        }

        @Override // com.android.server.telecom.DefaultDialerCache.DefaultDialerManagerAdapter
        public boolean setDefaultDialerApplication(Context context, String str, int i) {
            return DefaultDialerManager.setDefaultDialerApplication(context, str, i);
        }
    }

    public DefaultDialerCache(Context context, DefaultDialerManagerAdapter defaultDialerManagerAdapter, RoleManagerAdapter roleManagerAdapter, TelecomSystem.SyncRoot syncRoot) {
        this.mContext = context;
        this.mDefaultDialerManagerAdapter = defaultDialerManagerAdapter;
        this.mRoleManagerAdapter = roleManagerAdapter;
        this.mLock = syncRoot;
        Resources resources = context.getResources();
        this.mSystemDialerComponentName = new ComponentName(resources.getString(android.R.string.config_defaultDialer), resources.getString(R.string.incall_default_class));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        context.registerReceiverAsUser(this.mReceiver, UserHandle.ALL, intentFilter, null, null);
        context.registerReceiverAsUser(this.mReceiver, UserHandle.ALL, new IntentFilter("android.intent.action.BOOT_COMPLETED"), null, null);
        context.registerReceiver(this.mUserRemovedReceiver, new IntentFilter("android.intent.action.USER_REMOVED"));
        context.getContentResolver().registerContentObserver(Settings.Secure.getUriFor("dialer_default_application"), false, this.mDefaultDialerObserver, -1);
    }

    public String getDefaultDialerApplication(int i) {
        if (i == -2) {
            i = ActivityManager.getCurrentUser();
        }
        if (i >= 0) {
            return refreshCacheForUser(i);
        }
        Log.w("DefaultDialerCache", "Attempting to get default dialer for a meta-user %d", new Object[]{Integer.valueOf(i)});
        return null;
    }

    public void setSystemDialerComponentName(ComponentName componentName) {
        this.mOverrideSystemDialerComponentName = componentName;
    }

    public String getSystemDialerApplication() {
        ComponentName componentName = this.mOverrideSystemDialerComponentName;
        if (componentName != null) {
            return componentName.getPackageName();
        }
        return this.mSystemDialerComponentName.getPackageName();
    }

    public ComponentName getSystemDialerComponent() {
        ComponentName componentName = this.mOverrideSystemDialerComponentName;
        return componentName != null ? componentName : this.mSystemDialerComponentName;
    }

    public void observeDefaultDialerApplication(Executor executor, IntConsumer intConsumer) {
        this.mRoleManagerAdapter.observeDefaultDialerApp(executor, intConsumer);
    }

    public boolean isDefaultOrSystemDialer(String str, int i) {
        return Objects.equals(str, getDefaultDialerApplication(i)) || Objects.equals(str, getSystemDialerApplication());
    }

    public boolean setDefaultDialer(String str, int i) {
        boolean defaultDialerApplication = this.mDefaultDialerManagerAdapter.setDefaultDialerApplication(this.mContext, str, i);
        if (defaultDialerApplication) {
            synchronized (this.mLock) {
                this.mCurrentDefaultDialerPerUser.put(i, str);
            }
        }
        return defaultDialerApplication;
    }

    private String refreshCacheForUser(int i) {
        String defaultDialerApp = this.mRoleManagerAdapter.getDefaultDialerApp(i);
        synchronized (this.mLock) {
            this.mCurrentDefaultDialerPerUser.put(i, defaultDialerApp);
        }
        return defaultDialerApp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshCachesForUsersWithPackage(String str) {
        for (int i = 0; i < this.mCurrentDefaultDialerPerUser.size(); i++) {
            int iKeyAt = this.mCurrentDefaultDialerPerUser.keyAt(i);
            if (str == null || Objects.equals(str, this.mCurrentDefaultDialerPerUser.get(iKeyAt))) {
                Log.i("DefaultDialerCache", "Refreshing default dialer for user %d: now %s", new Object[]{Integer.valueOf(iKeyAt), refreshCacheForUser(iKeyAt)});
            }
        }
    }

    public void dumpCache(IndentingPrintWriter indentingPrintWriter) {
        synchronized (this.mLock) {
            for (int i = 0; i < this.mCurrentDefaultDialerPerUser.size(); i++) {
                indentingPrintWriter.printf("User %d: %s\n", new Object[]{Integer.valueOf(this.mCurrentDefaultDialerPerUser.keyAt(i)), this.mCurrentDefaultDialerPerUser.valueAt(i)});
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeUserFromCache(int i) {
        synchronized (this.mLock) {
            this.mCurrentDefaultDialerPerUser.remove(i);
        }
    }

    @VisibleForTesting
    public ContentObserver getContentObserver() {
        return this.mDefaultDialerObserver;
    }
}
