package com.android.car.settings.wifi;

import android.car.drivingstate.CarUxRestrictions;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;

/* JADX INFO: loaded from: classes5.dex */
public class NetworkPasswordPreferenceController extends PreferenceController<NetworkNameRestrictedPasswordEditTextPreference> {
    private static final Logger LOG = new Logger((Class<?>) NetworkPasswordPreferenceController.class);
    protected static final String SHARED_PREFERENCE_PATH = "com.android.car.settings.wifi.NetworkPasswordPreferenceController";
    private static final int SHARED_SECURITY_TYPE_UNSET = -1;
    private final WifiManager.ActionListener mConnectionListener;
    private final BroadcastReceiver mNameChangeReceiver;
    private String mNetworkName;
    private final BroadcastReceiver mSecurityChangeReceiver;
    private int mSecurityType;
    private final SharedPreferences mSharedPreferences;
    private final Handler mUiHandler;

    /* JADX INFO: renamed from: com.android.car.settings.wifi.NetworkPasswordPreferenceController$3, reason: invalid class name */
    class AnonymousClass3 implements WifiManager.ActionListener {
        AnonymousClass3() {
        }

        public void onSuccess() {
            NetworkPasswordPreferenceController.LOG.d("connected to network");
            NetworkPasswordPreferenceController.this.mUiHandler.post(new Runnable() { // from class: com.android.car.settings.wifi.-$$Lambda$NetworkPasswordPreferenceController$3$vQODD3-TY_F28yO-Vjg5Hmyisa4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onSuccess$0$NetworkPasswordPreferenceController$3();
                }
            });
        }

        public /* synthetic */ void lambda$onSuccess$0$NetworkPasswordPreferenceController$3() {
            NetworkPasswordPreferenceController.this.getFragmentController().goBack();
        }

        public void onFailure(int reason) {
            NetworkPasswordPreferenceController.LOG.d("Failed to connect to network. Failure code: " + reason);
        }
    }

    public NetworkPasswordPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mSharedPreferences = getContext().getSharedPreferences(SHARED_PREFERENCE_PATH, 0);
        this.mNameChangeReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.wifi.NetworkPasswordPreferenceController.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                NetworkPasswordPreferenceController.this.mNetworkName = intent.getStringExtra(NetworkNamePreferenceController.KEY_NETWORK_NAME);
                ((NetworkNameRestrictedPasswordEditTextPreference) NetworkPasswordPreferenceController.this.getPreference()).setNetworkName(NetworkPasswordPreferenceController.this.mNetworkName);
                NetworkPasswordPreferenceController.this.refreshUi();
            }
        };
        this.mSecurityChangeReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.wifi.NetworkPasswordPreferenceController.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                NetworkPasswordPreferenceController.this.mSecurityType = intent.getIntExtra(NetworkSecurityPreferenceController.KEY_SECURITY_TYPE, 0);
                NetworkPasswordPreferenceController.this.refreshUi();
            }
        };
        this.mUiHandler = new Handler(Looper.getMainLooper());
        this.mConnectionListener = new AnonymousClass3();
        this.mSecurityType = 0;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<NetworkNameRestrictedPasswordEditTextPreference> getPreferenceType() {
        return NetworkNameRestrictedPasswordEditTextPreference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.mNameChangeReceiver, new IntentFilter(NetworkNamePreferenceController.ACTION_NAME_CHANGE));
        int newSecurityType = this.mSharedPreferences.getInt("com.android.car.settings.wifi.SHARED_SECURITY_TYPE", -1);
        if (this.mSecurityType != newSecurityType && newSecurityType != -1) {
            this.mSecurityType = newSecurityType;
            refreshUi();
        }
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.mSecurityChangeReceiver, new IntentFilter(NetworkSecurityPreferenceController.ACTION_SECURITY_CHANGE));
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onDestroyInternal() {
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.mNameChangeReceiver);
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.mSecurityChangeReceiver);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(NetworkNameRestrictedPasswordEditTextPreference preference) {
        if (TextUtils.isEmpty(this.mNetworkName)) {
            getPreference().setDialogTitle(R.string.wifi_password);
        } else {
            getPreference().setDialogTitle(this.mNetworkName);
        }
        preference.setVisible(!WifiUtil.isOpenNetwork(this.mSecurityType));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceChanged(NetworkNameRestrictedPasswordEditTextPreference preference, Object newValue) {
        String password = newValue.toString();
        WifiUtil.connectToAccessPoint(getContext(), this.mNetworkName, this.mSecurityType, password, true, this.mConnectionListener);
        return true;
    }
}
