package com.android.car.developeroptions.network.telephony;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.telephony.CarrierConfigManager;
import android.telephony.CellInfo;
import android.telephony.NetworkRegistrationInfo;
import android.telephony.TelephonyManager;
import android.util.Pair;
import android.view.View;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.network.telephony.NetworkScanHelper;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.internal.telephony.OperatorInfo;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.utils.ThreadUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class NetworkSelectSettings extends DashboardFragment {
    List<CellInfo> mCellInfoList;
    PreferenceCategory mConnectedPreferenceCategory;
    private List<String> mForbiddenPlmns;
    private MetricsFeatureProvider mMetricsFeatureProvider;
    private NetworkScanHelper mNetworkScanHelper;
    PreferenceCategory mPreferenceCategory;
    private View mProgressHeader;
    NetworkOperatorPreference mSelectedPreference;
    private Preference mStatusMessagePreference;
    TelephonyManager mTelephonyManager;
    private boolean mUseNewApi;
    private int mSubId = -1;
    private boolean mShow4GForLTE = false;
    private final ExecutorService mNetworkScanExecutor = Executors.newFixedThreadPool(1);
    private final Handler mHandler = new Handler() { // from class: com.android.car.developeroptions.network.telephony.NetworkSelectSettings.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                NetworkSelectSettings.this.setProgressBarVisible(false);
                NetworkSelectSettings.this.getPreferenceScreen().setEnabled(true);
                NetworkSelectSettings.this.mSelectedPreference.setSummary(((Boolean) message.obj).booleanValue() ? R.string.network_connected : R.string.network_could_not_connect);
                return;
            }
            if (i == 2) {
                NetworkSelectSettings.this.mCellInfoList = new ArrayList(NetworkSelectSettings.this.aggregateCellInfoList((List) message.obj));
                List<CellInfo> list = NetworkSelectSettings.this.mCellInfoList;
                if (list == null || list.size() == 0) {
                    NetworkSelectSettings.this.addMessagePreference(R.string.empty_networks_list);
                    return;
                } else {
                    NetworkSelectSettings.this.updateAllPreferenceCategory();
                    return;
                }
            }
            if (i == 3) {
                NetworkSelectSettings.this.stopNetworkQuery();
                NetworkSelectSettings.this.addMessagePreference(R.string.network_query_error);
            } else {
                if (i != 4) {
                    return;
                }
                NetworkSelectSettings.this.stopNetworkQuery();
                NetworkSelectSettings networkSelectSettings = NetworkSelectSettings.this;
                if (networkSelectSettings.mCellInfoList == null) {
                    networkSelectSettings.addMessagePreference(R.string.empty_networks_list);
                }
            }
        }
    };
    private final NetworkScanHelper.NetworkScanCallback mCallback = new NetworkScanHelper.NetworkScanCallback() { // from class: com.android.car.developeroptions.network.telephony.NetworkSelectSettings.2
        @Override // com.android.car.developeroptions.network.telephony.NetworkScanHelper.NetworkScanCallback
        public void onResults(List<CellInfo> list) {
            NetworkSelectSettings.this.mHandler.obtainMessage(2, list).sendToTarget();
        }

        @Override // com.android.car.developeroptions.network.telephony.NetworkScanHelper.NetworkScanCallback
        public void onComplete() {
            NetworkSelectSettings.this.mHandler.obtainMessage(4).sendToTarget();
        }

        @Override // com.android.car.developeroptions.network.telephony.NetworkScanHelper.NetworkScanCallback
        public void onError(int i) {
            NetworkSelectSettings.this.mHandler.obtainMessage(3, i, 0).sendToTarget();
        }
    };

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "NetworkSelectSettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1581;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.choose_network;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mUseNewApi = getContext().getResources().getBoolean(android.R.bool.config_cecHdmiCecVersion20_default);
        this.mSubId = getArguments().getInt("android.provider.extra.SUB_ID");
        this.mConnectedPreferenceCategory = (PreferenceCategory) findPreference("connected_network_operator_preference");
        this.mPreferenceCategory = (PreferenceCategory) findPreference("network_operators_preference");
        Preference preference = new Preference(getContext());
        this.mStatusMessagePreference = preference;
        preference.setSelectable(false);
        this.mSelectedPreference = null;
        this.mTelephonyManager = TelephonyManager.from(getContext()).createForSubscriptionId(this.mSubId);
        this.mNetworkScanHelper = new NetworkScanHelper(this.mTelephonyManager, this.mCallback, this.mNetworkScanExecutor);
        PersistableBundle configForSubId = ((CarrierConfigManager) getContext().getSystemService("carrier_config")).getConfigForSubId(this.mSubId);
        if (configForSubId != null) {
            this.mShow4GForLTE = configForSubId.getBoolean("show_4g_for_lte_data_icon_bool");
        }
        this.mMetricsFeatureProvider = FeatureFactory.getFactory(getContext()).getMetricsFeatureProvider();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getActivity() != null) {
            this.mProgressHeader = setPinnedHeaderView(R.layout.progress_header).findViewById(R.id.progress_bar_animation);
            setProgressBarVisible(false);
        }
        forceUpdateConnectedPreferenceCategory();
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        updateForbiddenPlmns();
        setProgressBarVisible(true);
        this.mNetworkScanHelper.startNetworkScan(this.mUseNewApi ? 2 : 1);
    }

    void updateForbiddenPlmns() {
        List<String> arrayList;
        String[] forbiddenPlmns = this.mTelephonyManager.getForbiddenPlmns();
        if (forbiddenPlmns != null) {
            arrayList = Arrays.asList(forbiddenPlmns);
        } else {
            arrayList = new ArrayList<>();
        }
        this.mForbiddenPlmns = arrayList;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        stopNetworkQuery();
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference != this.mSelectedPreference) {
            stopNetworkQuery();
            NetworkOperatorPreference networkOperatorPreference = this.mSelectedPreference;
            if (networkOperatorPreference != null) {
                networkOperatorPreference.setSummary((CharSequence) null);
            }
            NetworkOperatorPreference networkOperatorPreference2 = (NetworkOperatorPreference) preference;
            this.mSelectedPreference = networkOperatorPreference2;
            CellInfo cellInfo = networkOperatorPreference2.getCellInfo();
            this.mSelectedPreference.setSummary(R.string.network_connecting);
            this.mMetricsFeatureProvider.action(getContext(), 1210, new Pair[0]);
            if (this.mConnectedPreferenceCategory.getPreferenceCount() > 0) {
                NetworkOperatorPreference networkOperatorPreference3 = (NetworkOperatorPreference) this.mConnectedPreferenceCategory.getPreference(0);
                if (!CellInfoUtil.getNetworkTitle(cellInfo).equals(CellInfoUtil.getNetworkTitle(networkOperatorPreference3.getCellInfo()))) {
                    networkOperatorPreference3.setSummary(R.string.network_disconnected);
                }
            }
            setProgressBarVisible(true);
            getPreferenceScreen().setEnabled(false);
            final OperatorInfo operatorInfoFromCellInfo = CellInfoUtil.getOperatorInfoFromCellInfo(cellInfo);
            ThreadUtils.postOnBackgroundThread(new Runnable() { // from class: com.android.car.developeroptions.network.telephony.-$$Lambda$NetworkSelectSettings$FbmB0bsP53WYIgGO50d7cTwYPo0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onPreferenceTreeClick$0$NetworkSelectSettings(operatorInfoFromCellInfo);
                }
            });
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onPreferenceTreeClick$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onPreferenceTreeClick$0$NetworkSelectSettings(OperatorInfo operatorInfo) {
        Message messageObtainMessage = this.mHandler.obtainMessage(1);
        messageObtainMessage.obj = Boolean.valueOf(this.mTelephonyManager.setNetworkSelectionModeManual(operatorInfo, true));
        messageObtainMessage.sendToTarget();
    }

    void updateAllPreferenceCategory() {
        updateConnectedPreferenceCategory();
        this.mPreferenceCategory.removeAll();
        for (int i = 0; i < this.mCellInfoList.size(); i++) {
            if (!this.mCellInfoList.get(i).isRegistered()) {
                NetworkOperatorPreference networkOperatorPreference = new NetworkOperatorPreference(this.mCellInfoList.get(i), getPrefContext(), this.mForbiddenPlmns, this.mShow4GForLTE);
                networkOperatorPreference.setKey(CellInfoUtil.getNetworkTitle(this.mCellInfoList.get(i)));
                networkOperatorPreference.setOrder(i);
                this.mPreferenceCategory.addPreference(networkOperatorPreference);
            }
        }
    }

    private void forceUpdateConnectedPreferenceCategory() {
        int dataState = this.mTelephonyManager.getDataState();
        TelephonyManager telephonyManager = this.mTelephonyManager;
        if (dataState == 2) {
            List networkRegistrationInfoListForTransportType = telephonyManager.getServiceState().getNetworkRegistrationInfoListForTransportType(1);
            if (networkRegistrationInfoListForTransportType == null || networkRegistrationInfoListForTransportType.size() == 0) {
                this.mConnectedPreferenceCategory.setVisible(false);
                return;
            }
            CellInfo cellInfoWrapCellInfoWithCellIdentity = CellInfoUtil.wrapCellInfoWithCellIdentity(((NetworkRegistrationInfo) networkRegistrationInfoListForTransportType.get(0)).getCellIdentity());
            if (cellInfoWrapCellInfoWithCellIdentity != null) {
                NetworkOperatorPreference networkOperatorPreference = new NetworkOperatorPreference(cellInfoWrapCellInfoWithCellIdentity, getPrefContext(), this.mForbiddenPlmns, this.mShow4GForLTE);
                networkOperatorPreference.setTitle(this.mTelephonyManager.getNetworkOperatorName());
                networkOperatorPreference.setSummary(R.string.network_connected);
                networkOperatorPreference.setIcon(4);
                this.mConnectedPreferenceCategory.addPreference(networkOperatorPreference);
                return;
            }
            this.mConnectedPreferenceCategory.setVisible(false);
            return;
        }
        this.mConnectedPreferenceCategory.setVisible(false);
    }

    private void updateConnectedPreferenceCategory() {
        CellInfo next;
        Iterator<CellInfo> it = this.mCellInfoList.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (next.isRegistered()) {
                    break;
                }
            }
        }
        if (next != null) {
            addConnectedNetworkOperatorPreference(next);
        }
    }

    private void addConnectedNetworkOperatorPreference(CellInfo cellInfo) {
        this.mConnectedPreferenceCategory.removeAll();
        NetworkOperatorPreference networkOperatorPreference = new NetworkOperatorPreference(cellInfo, getPrefContext(), this.mForbiddenPlmns, this.mShow4GForLTE);
        networkOperatorPreference.setSummary(R.string.network_connected);
        this.mConnectedPreferenceCategory.addPreference(networkOperatorPreference);
        this.mConnectedPreferenceCategory.setVisible(true);
    }

    protected void setProgressBarVisible(boolean z) {
        View view = this.mProgressHeader;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addMessagePreference(int i) {
        setProgressBarVisible(false);
        this.mStatusMessagePreference.setTitle(i);
        this.mConnectedPreferenceCategory.setVisible(false);
        this.mPreferenceCategory.removeAll();
        this.mPreferenceCategory.addPreference(this.mStatusMessagePreference);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<CellInfo> aggregateCellInfoList(List<CellInfo> list) {
        HashMap map = new HashMap();
        for (CellInfo cellInfo : list) {
            String operatorNumeric = CellInfoUtil.getOperatorInfoFromCellInfo(cellInfo).getOperatorNumeric();
            if (cellInfo.isRegistered() || !map.containsKey(operatorNumeric)) {
                map.put(operatorNumeric, cellInfo);
            } else if (!((CellInfo) map.get(operatorNumeric)).isRegistered() && ((CellInfo) map.get(operatorNumeric)).getCellSignalStrength().getLevel() <= cellInfo.getCellSignalStrength().getLevel()) {
                map.put(operatorNumeric, cellInfo);
            }
        }
        return new ArrayList(map.values());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopNetworkQuery() {
        setProgressBarVisible(false);
        NetworkScanHelper networkScanHelper = this.mNetworkScanHelper;
        if (networkScanHelper != null) {
            networkScanHelper.stopNetworkQuery();
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        this.mNetworkScanExecutor.shutdown();
        super.onDestroy();
    }
}
