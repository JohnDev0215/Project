package com.android.car.developeroptions.network.telephony;

import android.app.ActionBar;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.SettingsBaseActivity;
import com.android.car.developeroptions.development.featureflags.FeatureFlagPersistent;
import com.android.internal.util.CollectionUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class MobileNetworkActivity extends SettingsBaseActivity {
    static final String MOBILE_SETTINGS_TAG = "mobile_settings:";
    static final int SUB_ID_NULL = Integer.MIN_VALUE;
    int mCurSubscriptionId;
    private PhoneChangeReceiver mPhoneChangeReceiver;
    SubscriptionManager mSubscriptionManager;
    List<SubscriptionInfo> mSubscriptionInfos = new ArrayList();
    private final SubscriptionManager.OnSubscriptionsChangedListener mOnSubscriptionsChangeListener = new SubscriptionManager.OnSubscriptionsChangedListener() { // from class: com.android.car.developeroptions.network.telephony.MobileNetworkActivity.1
        @Override // android.telephony.SubscriptionManager.OnSubscriptionsChangedListener
        public void onSubscriptionsChanged() {
            MobileNetworkActivity mobileNetworkActivity = MobileNetworkActivity.this;
            if (Objects.equals(mobileNetworkActivity.mSubscriptionInfos, mobileNetworkActivity.mSubscriptionManager.getActiveSubscriptionInfoList(true))) {
                return;
            }
            MobileNetworkActivity.this.updateSubscriptions(null);
        }
    };

    @Override // com.android.car.developeroptions.core.SettingsBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (FeatureFlagPersistent.isEnabled(this, "settings_network_and_internet_v2")) {
            setContentView(R.layout.mobile_network_settings_container_v2);
        } else {
            setContentView(R.layout.mobile_network_settings_container);
        }
        setActionBar((Toolbar) findViewById(R.id.mobile_action_bar));
        this.mPhoneChangeReceiver = new PhoneChangeReceiver(this, new PhoneChangeReceiver.Client() { // from class: com.android.car.developeroptions.network.telephony.-$$Lambda$MobileNetworkActivity$9rs7UmTS8dyHK30ZMj93MmPd78k
            @Override // com.android.car.developeroptions.network.telephony.MobileNetworkActivity.PhoneChangeReceiver.Client
            public final void onPhoneChange() {
                this.f$0.lambda$onCreate$0$MobileNetworkActivity();
            }
        });
        SubscriptionManager subscriptionManager = (SubscriptionManager) getSystemService(SubscriptionManager.class);
        this.mSubscriptionManager = subscriptionManager;
        this.mSubscriptionInfos = subscriptionManager.getActiveSubscriptionInfoList(true);
        int i = SUB_ID_NULL;
        if (bundle != null) {
            i = bundle.getInt("android.provider.extra.SUB_ID", SUB_ID_NULL);
        }
        this.mCurSubscriptionId = i;
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        updateSubscriptions(bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreate$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreate$0$MobileNetworkActivity() {
        if (this.mCurSubscriptionId != SUB_ID_NULL) {
            switchFragment(new MobileNetworkSettings(), this.mCurSubscriptionId, true);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mPhoneChangeReceiver.register();
        this.mSubscriptionManager.addOnSubscriptionsChangedListener(this.mOnSubscriptionsChangeListener);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        this.mPhoneChangeReceiver.unregister();
        this.mSubscriptionManager.removeOnSubscriptionsChangedListener(this.mOnSubscriptionsChangeListener);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        saveInstanceState(bundle);
    }

    void saveInstanceState(Bundle bundle) {
        bundle.putInt("android.provider.extra.SUB_ID", this.mCurSubscriptionId);
    }

    void updateSubscriptions(Bundle bundle) {
        SubscriptionInfo subscription = getSubscription();
        if (subscription != null) {
            setTitle(subscription.getDisplayName());
        }
        this.mSubscriptionInfos = this.mSubscriptionManager.getActiveSubscriptionInfoList(true);
        if (!FeatureFlagPersistent.isEnabled(this, "settings_network_and_internet_v2")) {
            updateBottomNavigationView();
        }
        if (bundle == null) {
            switchFragment(new MobileNetworkSettings(), getSubscriptionId());
        }
    }

    SubscriptionInfo getSubscription() {
        int intExtra;
        Intent intent = getIntent();
        if (intent != null && (intExtra = intent.getIntExtra("android.provider.extra.SUB_ID", SUB_ID_NULL)) != SUB_ID_NULL) {
            for (SubscriptionInfo subscriptionInfo : this.mSubscriptionManager.getSelectableSubscriptionInfoList()) {
                if (subscriptionInfo.getSubscriptionId() == intExtra) {
                    return subscriptionInfo;
                }
            }
        }
        if (CollectionUtils.isEmpty(this.mSubscriptionInfos)) {
            return null;
        }
        return this.mSubscriptionInfos.get(0);
    }

    int getSubscriptionId() {
        SubscriptionInfo subscription = getSubscription();
        if (subscription != null) {
            return subscription.getSubscriptionId();
        }
        return -1;
    }

    void updateBottomNavigationView() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        if (CollectionUtils.size(this.mSubscriptionInfos) <= 1) {
            bottomNavigationView.setVisibility(8);
            return;
        }
        Menu menu = bottomNavigationView.getMenu();
        menu.clear();
        int size = this.mSubscriptionInfos.size();
        for (int i = 0; i < size; i++) {
            SubscriptionInfo subscriptionInfo = this.mSubscriptionInfos.get(i);
            menu.add(0, subscriptionInfo.getSubscriptionId(), i, subscriptionInfo.getDisplayName()).setIcon(R.drawable.ic_settings_sim);
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { // from class: com.android.car.developeroptions.network.telephony.-$$Lambda$MobileNetworkActivity$Sv5tto94xT2rwWrDGzIIvv3gPeQ
            @Override // com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener
            public final boolean onNavigationItemSelected(MenuItem menuItem) {
                return this.f$0.lambda$updateBottomNavigationView$1$MobileNetworkActivity(menuItem);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateBottomNavigationView$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$updateBottomNavigationView$1$MobileNetworkActivity(MenuItem menuItem) {
        switchFragment(new MobileNetworkSettings(), menuItem.getItemId());
        return true;
    }

    void switchFragment(Fragment fragment, int i) {
        switchFragment(fragment, i, false);
    }

    void switchFragment(Fragment fragment, int i, boolean z) {
        int i2 = this.mCurSubscriptionId;
        if (i2 == SUB_ID_NULL || i != i2 || z) {
            FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putInt("android.provider.extra.SUB_ID", i);
            fragment.setArguments(bundle);
            fragmentTransactionBeginTransaction.replace(R.id.main_content, fragment, buildFragmentTag(i));
            fragmentTransactionBeginTransaction.commit();
            this.mCurSubscriptionId = i;
        }
    }

    private String buildFragmentTag(int i) {
        return MOBILE_SETTINGS_TAG + i;
    }

    static class PhoneChangeReceiver extends BroadcastReceiver {
        private static final IntentFilter RADIO_TECHNOLOGY_CHANGED_FILTER = new IntentFilter("android.intent.action.RADIO_TECHNOLOGY");
        private Client mClient;
        private Context mContext;

        interface Client {
            void onPhoneChange();
        }

        public PhoneChangeReceiver(Context context, Client client) {
            this.mContext = context;
            this.mClient = client;
        }

        public void register() {
            this.mContext.registerReceiver(this, RADIO_TECHNOLOGY_CHANGED_FILTER);
        }

        public void unregister() {
            this.mContext.unregisterReceiver(this);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (isInitialStickyBroadcast()) {
                return;
            }
            this.mClient.onPhoneChange();
        }
    }
}
