package com.android.car.developeroptions.wifi.details;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.wifi.WifiDialog;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.wifi.AccessPoint;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WifiNetworkDetailsFragment extends DashboardFragment {
    private AccessPoint mAccessPoint;
    private WifiDetailPreferenceController mWifiDetailPreferenceController;

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.DialogCreatable
    public int getDialogMetricsCategory(int i) {
        return i == 1 ? 603 : 0;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "WifiNetworkDetailsFrg";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 849;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.wifi_network_details_fragment;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        this.mAccessPoint = new AccessPoint(context, getArguments());
        super.onAttach(context);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.DialogCreatable
    public Dialog onCreateDialog(int i) {
        if (getActivity() == null || this.mWifiDetailPreferenceController == null || this.mAccessPoint == null) {
            return null;
        }
        return WifiDialog.createModal(getActivity(), this.mWifiDetailPreferenceController, this.mAccessPoint, 2);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        MenuItem menuItemAdd = menu.add(0, 1, 0, R.string.wifi_modify);
        menuItemAdd.setIcon(android.R.drawable.ic_media_route_connecting_dark_01_mtrl);
        menuItemAdd.setShowAsAction(2);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 1) {
            if (!this.mWifiDetailPreferenceController.canModifyNetwork()) {
                RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getContext(), RestrictedLockUtilsInternal.getDeviceOwner(getContext()));
            } else {
                showDialog(1);
            }
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        WifiDetailPreferenceController wifiDetailPreferenceControllerNewInstance = WifiDetailPreferenceController.newInstance(this.mAccessPoint, (ConnectivityManager) context.getSystemService(ConnectivityManager.class), context, this, new Handler(Looper.getMainLooper()), getSettingsLifecycle(), (WifiManager) context.getSystemService(WifiManager.class), this.mMetricsFeatureProvider);
        this.mWifiDetailPreferenceController = wifiDetailPreferenceControllerNewInstance;
        arrayList.add(wifiDetailPreferenceControllerNewInstance);
        arrayList.add(new AddDevicePreferenceController(context).init(this.mAccessPoint));
        arrayList.add(new WifiMeteredPreferenceController(context, this.mAccessPoint.getConfig()));
        WifiPrivacyPreferenceController wifiPrivacyPreferenceController = new WifiPrivacyPreferenceController(context);
        wifiPrivacyPreferenceController.setWifiConfiguration(this.mAccessPoint.getConfig());
        wifiPrivacyPreferenceController.setIsEphemeral(this.mAccessPoint.isEphemeral());
        wifiPrivacyPreferenceController.setIsPasspoint(this.mAccessPoint.isPasspoint() || this.mAccessPoint.isPasspointConfig());
        arrayList.add(wifiPrivacyPreferenceController);
        return arrayList;
    }
}
