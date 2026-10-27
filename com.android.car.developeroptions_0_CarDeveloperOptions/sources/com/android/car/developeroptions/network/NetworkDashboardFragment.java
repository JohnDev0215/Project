package com.android.car.developeroptions.network;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.SearchIndexableResource;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.development.featureflags.FeatureFlagPersistent;
import com.android.car.developeroptions.network.MobilePlanPreferenceController;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.wifi.WifiMasterSwitchPreferenceController;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class NetworkDashboardFragment extends DashboardFragment implements MobilePlanPreferenceController.MobilePlanPreferenceHost {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.network.NetworkDashboardFragment.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            if (FeatureFlagPersistent.isEnabled(context, "settings_network_and_internet_v2")) {
                searchIndexableResource.xmlResId = R.xml.network_and_internet_v2;
            } else {
                searchIndexableResource.xmlResId = R.xml.network_and_internet;
            }
            return Arrays.asList(searchIndexableResource);
        }
    };

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.DialogCreatable
    public int getDialogMetricsCategory(int i) {
        return 1 == i ? 609 : 0;
    }

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_network_dashboard;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "NetworkDashboardFrag";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 746;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return FeatureFlagPersistent.isEnabled(getContext(), "settings_network_and_internet_v2") ? R.xml.network_and_internet_v2 : R.xml.network_and_internet;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (FeatureFlagPersistent.isEnabled(context, "settings_network_and_internet_v2")) {
            ((MultiNetworkHeaderController) use(MultiNetworkHeaderController.class)).init(getSettingsLifecycle());
        }
        ((AirplaneModePreferenceController) use(AirplaneModePreferenceController.class)).setFragment(this);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, getSettingsLifecycle(), this.mMetricsFeatureProvider, this, this);
    }

    private static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Lifecycle lifecycle, MetricsFeatureProvider metricsFeatureProvider, Fragment fragment, MobilePlanPreferenceController.MobilePlanPreferenceHost mobilePlanPreferenceHost) {
        MobilePlanPreferenceController mobilePlanPreferenceController = new MobilePlanPreferenceController(context, mobilePlanPreferenceHost);
        WifiMasterSwitchPreferenceController wifiMasterSwitchPreferenceController = new WifiMasterSwitchPreferenceController(context, metricsFeatureProvider);
        MobileNetworkPreferenceController mobileNetworkPreferenceController = !FeatureFlagPersistent.isEnabled(context, "settings_network_and_internet_v2") ? new MobileNetworkPreferenceController(context) : null;
        VpnPreferenceController vpnPreferenceController = new VpnPreferenceController(context);
        PrivateDnsPreferenceController privateDnsPreferenceController = new PrivateDnsPreferenceController(context);
        if (lifecycle != null) {
            lifecycle.addObserver(mobilePlanPreferenceController);
            lifecycle.addObserver(wifiMasterSwitchPreferenceController);
            if (mobileNetworkPreferenceController != null) {
                lifecycle.addObserver(mobileNetworkPreferenceController);
            }
            lifecycle.addObserver(vpnPreferenceController);
            lifecycle.addObserver(privateDnsPreferenceController);
        }
        ArrayList arrayList = new ArrayList();
        if (FeatureFlagPersistent.isEnabled(context, "settings_network_and_internet_v2")) {
            arrayList.add(new MobileNetworkSummaryController(context, lifecycle));
        }
        if (mobileNetworkPreferenceController != null) {
            arrayList.add(mobileNetworkPreferenceController);
        }
        arrayList.add(new TetherPreferenceController(context, lifecycle));
        arrayList.add(vpnPreferenceController);
        arrayList.add(new ProxyPreferenceController(context));
        arrayList.add(mobilePlanPreferenceController);
        arrayList.add(wifiMasterSwitchPreferenceController);
        arrayList.add(privateDnsPreferenceController);
        return arrayList;
    }

    @Override // com.android.car.developeroptions.network.MobilePlanPreferenceController.MobilePlanPreferenceHost
    public void showMobilePlanMessageDialog() {
        showDialog(1);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.DialogCreatable
    public Dialog onCreateDialog(int i) {
        Log.d("NetworkDashboardFrag", "onCreateDialog: dialogId=" + i);
        if (i == 1) {
            final MobilePlanPreferenceController mobilePlanPreferenceController = (MobilePlanPreferenceController) use(MobilePlanPreferenceController.class);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(mobilePlanPreferenceController.getMobilePlanDialogMessage());
            builder.setCancelable(false);
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.network.-$$Lambda$NetworkDashboardFragment$eDkgx0nb0Dwsm9OGvurPyS8lMr0
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    mobilePlanPreferenceController.setMobilePlanDialogMessage(null);
                }
            });
            return builder.create();
        }
        return super.onCreateDialog(i);
    }
}
