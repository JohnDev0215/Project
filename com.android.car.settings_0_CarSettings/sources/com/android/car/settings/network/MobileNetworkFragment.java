package com.android.car.settings.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.network.MobileNetworkUpdateManager;
import com.android.car.settings.search.CarBaseSearchIndexProvider;
import com.android.internal.util.CollectionUtils;
import com.google.android.collect.Lists;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class MobileNetworkFragment extends SettingsFragment implements MobileNetworkUpdateManager.MobileNetworkUpdateListener {
    static final String ARG_NETWORK_SUB_ID = "network_sub_id";
    public static final CarBaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new CarBaseSearchIndexProvider(R.xml.mobile_network_fragment, "android.settings.NETWORK_OPERATOR_SETTINGS") { // from class: com.android.car.settings.network.MobileNetworkFragment.1
        @Override // com.android.car.settings.search.CarBaseSearchIndexProvider
        protected boolean isPageSearchEnabled(Context context) {
            return NetworkUtils.hasMobileNetwork((ConnectivityManager) context.getSystemService(ConnectivityManager.class));
        }
    };
    private MobileNetworkUpdateManager mMobileNetworkUpdateManager;
    private SubscriptionManager mSubscriptionManager;
    private CharSequence mTitle;

    public static MobileNetworkFragment newInstance(int subId) {
        MobileNetworkFragment fragment = new MobileNetworkFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_NETWORK_SUB_ID, subId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.mobile_network_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mSubscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        int subId = getArguments() != null ? getArguments().getInt(ARG_NETWORK_SUB_ID, Integer.MIN_VALUE) : Integer.MIN_VALUE;
        this.mMobileNetworkUpdateManager = new MobileNetworkUpdateManager(context, subId);
        getLifecycle().addObserver(this.mMobileNetworkUpdateManager);
        List<MobileNetworkUpdateManager.MobileNetworkUpdateListener> listeners = Lists.newArrayList(new MobileNetworkUpdateManager.MobileNetworkUpdateListener[]{this, (MobileNetworkUpdateManager.MobileNetworkUpdateListener) use(MobileDataTogglePreferenceController.class, R.string.pk_mobile_data_toggle), (MobileNetworkUpdateManager.MobileNetworkUpdateListener) use(RoamingPreferenceController.class, R.string.pk_mobile_roaming_toggle)});
        for (MobileNetworkUpdateManager.MobileNetworkUpdateListener listener : listeners) {
            this.mMobileNetworkUpdateManager.registerListener(listener);
        }
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (this.mTitle != null) {
            getToolbar().setTitle(this.mTitle);
        }
    }

    @Override // com.android.car.settings.network.MobileNetworkUpdateManager.MobileNetworkUpdateListener
    public void onMobileNetworkUpdated(int subId) {
        SubscriptionInfo info = null;
        if (subId != Integer.MIN_VALUE) {
            for (SubscriptionInfo subscriptionInfo : this.mSubscriptionManager.getSelectableSubscriptionInfoList()) {
                if (subscriptionInfo.getSubscriptionId() == subId) {
                    info = subscriptionInfo;
                }
            }
        }
        if (info == null && !CollectionUtils.isEmpty(this.mSubscriptionManager.getActiveSubscriptionInfoList())) {
            info = this.mSubscriptionManager.getActiveSubscriptionInfoList().get(0);
        }
        if (info != null) {
            this.mTitle = info.getDisplayName();
            if (getToolbar() != null) {
                getToolbar().setTitle(this.mTitle);
            }
        }
    }
}
