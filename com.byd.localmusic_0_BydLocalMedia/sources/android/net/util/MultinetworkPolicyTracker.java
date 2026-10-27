package android.net.util;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.UserHandle;
import android.provider.Settings;
import android.telephony.PhoneStateListener;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Slog;
import com.android.internal.R;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MultinetworkPolicyTracker {
    private static String TAG = MultinetworkPolicyTracker.class.getSimpleName();
    private int mActiveSubId;
    private volatile boolean mAvoidBadWifi;
    private final Runnable mAvoidBadWifiCallback;
    private final BroadcastReceiver mBroadcastReceiver;
    private final Context mContext;
    private final Handler mHandler;
    private volatile int mMeteredMultipathPreference;
    private final ContentResolver mResolver;
    private final SettingObserver mSettingObserver;
    private final List<Uri> mSettingsUris;

    public MultinetworkPolicyTracker(Context context, Handler handler) {
        this(context, handler, null);
    }

    public MultinetworkPolicyTracker(Context context, Handler handler, Runnable runnable) {
        this.mAvoidBadWifi = true;
        this.mActiveSubId = -1;
        this.mContext = context;
        this.mHandler = handler;
        this.mAvoidBadWifiCallback = runnable;
        this.mSettingsUris = Arrays.asList(Settings.Global.getUriFor(Settings.Global.NETWORK_AVOID_BAD_WIFI), Settings.Global.getUriFor(Settings.Global.NETWORK_METERED_MULTIPATH_PREFERENCE));
        this.mResolver = this.mContext.getContentResolver();
        this.mSettingObserver = new SettingObserver();
        this.mBroadcastReceiver = new BroadcastReceiver() { // from class: android.net.util.MultinetworkPolicyTracker.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                MultinetworkPolicyTracker.this.reevaluateInternal();
            }
        };
        ((TelephonyManager) context.getSystemService(TelephonyManager.class)).listen(new PhoneStateListener(handler.getLooper()) { // from class: android.net.util.MultinetworkPolicyTracker.2
            @Override // android.telephony.PhoneStateListener
            public void onActiveDataSubscriptionIdChanged(int i) {
                MultinetworkPolicyTracker.this.mActiveSubId = i;
                MultinetworkPolicyTracker.this.reevaluateInternal();
            }
        }, 4194304);
        updateAvoidBadWifi();
        updateMeteredMultipathPreference();
    }

    public void start() {
        Iterator<Uri> it = this.mSettingsUris.iterator();
        while (it.hasNext()) {
            this.mResolver.registerContentObserver(it.next(), false, this.mSettingObserver);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_CONFIGURATION_CHANGED);
        this.mContext.registerReceiverAsUser(this.mBroadcastReceiver, UserHandle.ALL, intentFilter, null, this.mHandler);
        reevaluate();
    }

    public void shutdown() {
        this.mResolver.unregisterContentObserver(this.mSettingObserver);
        this.mContext.unregisterReceiver(this.mBroadcastReceiver);
    }

    public boolean getAvoidBadWifi() {
        return this.mAvoidBadWifi;
    }

    public int getMeteredMultipathPreference() {
        return this.mMeteredMultipathPreference;
    }

    public boolean configRestrictsAvoidBadWifi() {
        return getResourcesForActiveSubId().getInteger(R.integer.config_networkAvoidBadWifi) == 0;
    }

    private Resources getResourcesForActiveSubId() {
        return SubscriptionManager.getResourcesForSubId(this.mContext, this.mActiveSubId);
    }

    public boolean shouldNotifyWifiUnvalidated() {
        return configRestrictsAvoidBadWifi() && getAvoidBadWifiSetting() == null;
    }

    public String getAvoidBadWifiSetting() {
        return Settings.Global.getString(this.mResolver, Settings.Global.NETWORK_AVOID_BAD_WIFI);
    }

    public void reevaluate() {
        this.mHandler.post(new Runnable() { // from class: android.net.util.-$$Lambda$MultinetworkPolicyTracker$8YMQ0fPTKk7Fw-_gJjln0JT-g8E
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.reevaluateInternal();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reevaluateInternal() {
        Runnable runnable;
        if (updateAvoidBadWifi() && (runnable = this.mAvoidBadWifiCallback) != null) {
            runnable.run();
        }
        updateMeteredMultipathPreference();
    }

    public boolean updateAvoidBadWifi() {
        boolean zEquals = "1".equals(getAvoidBadWifiSetting());
        boolean z = this.mAvoidBadWifi;
        this.mAvoidBadWifi = zEquals || !configRestrictsAvoidBadWifi();
        return this.mAvoidBadWifi != z;
    }

    public int configMeteredMultipathPreference() {
        return this.mContext.getResources().getInteger(R.integer.config_networkMeteredMultipathPreference);
    }

    public void updateMeteredMultipathPreference() {
        try {
            this.mMeteredMultipathPreference = Integer.parseInt(Settings.Global.getString(this.mResolver, Settings.Global.NETWORK_METERED_MULTIPATH_PREFERENCE));
        } catch (NumberFormatException unused) {
            this.mMeteredMultipathPreference = configMeteredMultipathPreference();
        }
    }

    private class SettingObserver extends ContentObserver {
        public SettingObserver() {
            super(null);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            Slog.wtf(MultinetworkPolicyTracker.TAG, "Should never be reached.");
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            if (!MultinetworkPolicyTracker.this.mSettingsUris.contains(uri)) {
                Slog.wtf(MultinetworkPolicyTracker.TAG, "Unexpected settings observation: " + uri);
            }
            MultinetworkPolicyTracker.this.reevaluate();
        }
    }
}
