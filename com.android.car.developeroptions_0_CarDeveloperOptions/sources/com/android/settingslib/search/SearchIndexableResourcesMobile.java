package com.android.settingslib.search;

import com.android.car.developeroptions.DisplaySettings;
import com.android.car.developeroptions.applications.defaultapps.AutofillPicker;
import com.android.car.developeroptions.applications.managedomainurls.ManageDomainUrls;
import com.android.car.developeroptions.connecteddevice.AdvancedConnectedDeviceDashboardFragment;
import com.android.car.developeroptions.connecteddevice.ConnectedDeviceDashboardFragment;
import com.android.car.developeroptions.connecteddevice.PreviouslyConnectedDeviceDashboardFragment;
import com.android.car.developeroptions.connecteddevice.usb.UsbDetailsFragment;
import com.android.car.developeroptions.datausage.DataUsageSummary;
import com.android.car.developeroptions.development.DevelopmentSettingsDashboardFragment;
import com.android.car.developeroptions.display.AdaptiveSleepSettings;
import com.android.car.developeroptions.display.AutoBrightnessSettings;
import com.android.car.developeroptions.display.DarkUISettings;
import com.android.car.developeroptions.display.NightDisplaySettings;
import com.android.car.developeroptions.display.ScreenZoomSettings;
import com.android.car.developeroptions.flashlight.FlashlightHandleActivity;
import com.android.car.developeroptions.fuelgauge.PowerUsageAdvanced;
import com.android.car.developeroptions.fuelgauge.PowerUsageSummary;
import com.android.car.developeroptions.fuelgauge.SmartBatterySettings;
import com.android.car.developeroptions.fuelgauge.batterysaver.BatterySaverSettings;
import com.android.car.developeroptions.homepage.TopLevelSettings;
import com.android.car.developeroptions.location.ScanningSettings;
import com.android.car.developeroptions.network.MobileNetworkListFragment;
import com.android.car.developeroptions.network.telephony.MobileNetworkSettings;
import com.android.car.developeroptions.notification.ZenModeRestrictNotificationsSettings;
import com.android.car.developeroptions.wfd.WifiDisplaySettings;

/* JADX INFO: loaded from: classes.dex */
public class SearchIndexableResourcesMobile extends SearchIndexableResourcesBase {
    public SearchIndexableResourcesMobile() {
        addIndex(new SearchIndexableData(DisplaySettings.class, DisplaySettings.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(AutofillPicker.class, AutofillPicker.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(ManageDomainUrls.class, ManageDomainUrls.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(AdvancedConnectedDeviceDashboardFragment.class, AdvancedConnectedDeviceDashboardFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(ConnectedDeviceDashboardFragment.class, ConnectedDeviceDashboardFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(PreviouslyConnectedDeviceDashboardFragment.class, PreviouslyConnectedDeviceDashboardFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(UsbDetailsFragment.class, UsbDetailsFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(DataUsageSummary.class, DataUsageSummary.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(DevelopmentSettingsDashboardFragment.class, DevelopmentSettingsDashboardFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(AdaptiveSleepSettings.class, AdaptiveSleepSettings.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(AutoBrightnessSettings.class, AutoBrightnessSettings.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(DarkUISettings.class, DarkUISettings.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(NightDisplaySettings.class, NightDisplaySettings.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(ScreenZoomSettings.class, ScreenZoomSettings.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(FlashlightHandleActivity.class, FlashlightHandleActivity.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(PowerUsageAdvanced.class, PowerUsageAdvanced.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(PowerUsageSummary.class, PowerUsageSummary.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(SmartBatterySettings.class, SmartBatterySettings.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(BatterySaverSettings.class, BatterySaverSettings.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(TopLevelSettings.class, TopLevelSettings.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(ScanningSettings.class, ScanningSettings.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(MobileNetworkListFragment.class, MobileNetworkListFragment.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(MobileNetworkSettings.class, MobileNetworkSettings.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(ZenModeRestrictNotificationsSettings.class, ZenModeRestrictNotificationsSettings.SEARCH_INDEX_DATA_PROVIDER));
        addIndex(new SearchIndexableData(WifiDisplaySettings.class, WifiDisplaySettings.SEARCH_INDEX_DATA_PROVIDER));
    }
}
