package com.android.car.developeroptions.deviceinfo.aboutphone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.os.Bundle;
import android.os.Process;
import android.os.UserManager;
import android.provider.SearchIndexableResource;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.deviceinfo.BluetoothAddressPreferenceController;
import com.android.car.developeroptions.deviceinfo.BuildNumberPreferenceController;
import com.android.car.developeroptions.deviceinfo.DeviceNamePreferenceController;
import com.android.car.developeroptions.deviceinfo.FccEquipmentIdPreferenceController;
import com.android.car.developeroptions.deviceinfo.FeedbackPreferenceController;
import com.android.car.developeroptions.deviceinfo.IpAddressPreferenceController;
import com.android.car.developeroptions.deviceinfo.ManualPreferenceController;
import com.android.car.developeroptions.deviceinfo.RegulatoryInfoPreferenceController;
import com.android.car.developeroptions.deviceinfo.SafetyInfoPreferenceController;
import com.android.car.developeroptions.deviceinfo.UptimePreferenceController;
import com.android.car.developeroptions.deviceinfo.WifiMacAddressPreferenceController;
import com.android.car.developeroptions.deviceinfo.imei.ImeiInfoPreferenceController;
import com.android.car.developeroptions.deviceinfo.simstatus.SimStatusPreferenceController;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.widget.EntityHeaderController;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import com.android.settingslib.widget.LayoutPreference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MyDeviceInfoFragment extends DashboardFragment implements DeviceNamePreferenceController.DeviceNamePreferenceHost {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.deviceinfo.aboutphone.MyDeviceInfoFragment.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.my_device_info;
            return Arrays.asList(searchIndexableResource);
        }
    };
    private BuildNumberPreferenceController mBuildNumberPreferenceController;

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_uri_about;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "MyDeviceInfoFragment";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 40;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.my_device_info;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        ((ImeiInfoPreferenceController) use(ImeiInfoPreferenceController.class)).setHost(this);
        ((DeviceNamePreferenceController) use(DeviceNamePreferenceController.class)).setHost(this);
        BuildNumberPreferenceController buildNumberPreferenceController = (BuildNumberPreferenceController) use(BuildNumberPreferenceController.class);
        this.mBuildNumberPreferenceController = buildNumberPreferenceController;
        buildNumberPreferenceController.setHost(this);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        initHeader();
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, this, getSettingsLifecycle());
    }

    private static List<AbstractPreferenceController> buildPreferenceControllers(Context context, MyDeviceInfoFragment myDeviceInfoFragment, Lifecycle lifecycle) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SimStatusPreferenceController(context, myDeviceInfoFragment));
        arrayList.add(new IpAddressPreferenceController(context, lifecycle));
        arrayList.add(new WifiMacAddressPreferenceController(context, lifecycle));
        arrayList.add(new BluetoothAddressPreferenceController(context, lifecycle));
        arrayList.add(new RegulatoryInfoPreferenceController(context));
        arrayList.add(new SafetyInfoPreferenceController(context));
        arrayList.add(new ManualPreferenceController(context));
        arrayList.add(new FeedbackPreferenceController(myDeviceInfoFragment, context));
        arrayList.add(new FccEquipmentIdPreferenceController(context));
        arrayList.add(new UptimePreferenceController(context, lifecycle));
        return arrayList;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.mBuildNumberPreferenceController.onActivityResult(i, i2, intent)) {
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    private void initHeader() {
        LayoutPreference layoutPreference = (LayoutPreference) getPreferenceScreen().findPreference("my_device_info_header");
        boolean z = getContext().getResources().getBoolean(R.bool.config_show_device_header_in_device_info);
        layoutPreference.setVisible(z);
        if (z) {
            View viewFindViewById = layoutPreference.findViewById(R.id.entity_header);
            FragmentActivity activity = getActivity();
            Bundle arguments = getArguments();
            EntityHeaderController entityHeaderControllerNewInstance = EntityHeaderController.newInstance(activity, this, viewFindViewById);
            entityHeaderControllerNewInstance.setRecyclerView(getListView(), getSettingsLifecycle());
            entityHeaderControllerNewInstance.setButtonActions(0, 0);
            if (arguments.getInt("icon_id", 0) == 0) {
                UserManager userManager = (UserManager) getActivity().getSystemService("user");
                UserInfo existingUser = Utils.getExistingUser(userManager, Process.myUserHandle());
                entityHeaderControllerNewInstance.setLabel(existingUser.name);
                entityHeaderControllerNewInstance.setIcon(com.android.settingslib.Utils.getUserIcon(getActivity(), userManager, existingUser));
            }
            entityHeaderControllerNewInstance.done((Activity) activity, true);
        }
    }

    @Override // com.android.car.developeroptions.deviceinfo.DeviceNamePreferenceController.DeviceNamePreferenceHost
    public void showDeviceNameWarningDialog(String str) {
        DeviceNameWarningDialog.show(this);
    }

    public void onSetDeviceNameConfirm(boolean z) {
        ((DeviceNamePreferenceController) use(DeviceNamePreferenceController.class)).updateDeviceName(z);
    }
}
