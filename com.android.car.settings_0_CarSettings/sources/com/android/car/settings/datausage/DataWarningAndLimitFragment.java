package com.android.car.settings.datausage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkPolicyManager;
import android.net.NetworkTemplate;
import android.os.Bundle;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.network.NetworkUtils;
import com.android.car.settings.search.CarBaseSearchIndexProvider;
import com.android.settingslib.NetworkPolicyEditor;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class DataWarningAndLimitFragment extends SettingsFragment {
    public static final CarBaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new CarBaseSearchIndexProvider(R.xml.data_warning_and_limit_fragment, DataWarningAndLimitActivity.class) { // from class: com.android.car.settings.datausage.DataWarningAndLimitFragment.1
        @Override // com.android.car.settings.search.CarBaseSearchIndexProvider
        protected boolean isPageSearchEnabled(Context context) {
            return NetworkUtils.hasMobileNetwork((ConnectivityManager) context.getSystemService(ConnectivityManager.class));
        }
    };
    private NetworkTemplate mNetworkTemplate;
    private NetworkPolicyEditor mPolicyEditor;
    private SubscriptionManager mSubscriptionManager;
    private TelephonyManager mTelephonyManager;

    public static DataWarningAndLimitFragment newInstance(NetworkTemplate template) {
        DataWarningAndLimitFragment fragment = new DataWarningAndLimitFragment();
        Bundle args = new Bundle();
        args.putParcelable("android.net.NETWORK_TEMPLATE", template);
        fragment.setArguments(args);
        return fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.data_warning_and_limit_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mPolicyEditor = new NetworkPolicyEditor(NetworkPolicyManager.from(context));
        NetworkTemplate parcelable = getArguments().getParcelable("android.net.NETWORK_TEMPLATE");
        this.mNetworkTemplate = parcelable;
        if (parcelable == null) {
            this.mTelephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
            SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
            this.mSubscriptionManager = subscriptionManager;
            this.mNetworkTemplate = DataUsageUtils.getMobileNetworkTemplate(this.mTelephonyManager, DataUsageUtils.getDefaultSubscriptionId(subscriptionManager));
        }
        this.mPolicyEditor.read();
        List<DataWarningAndLimitBasePreferenceController> preferenceControllers = Arrays.asList((DataWarningAndLimitBasePreferenceController) use(CycleResetDayOfMonthPickerPreferenceController.class, R.string.pk_data_usage_cycle), (DataWarningAndLimitBasePreferenceController) use(DataWarningPreferenceController.class, R.string.pk_data_warning_group), (DataWarningAndLimitBasePreferenceController) use(DataLimitPreferenceController.class, R.string.pk_data_limit_group));
        for (DataWarningAndLimitBasePreferenceController preferenceController : preferenceControllers) {
            preferenceController.setNetworkPolicyEditor(this.mPolicyEditor);
            preferenceController.setNetworkTemplate(this.mNetworkTemplate);
        }
    }
}
