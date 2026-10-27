package com.android.car.developeroptions.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityManager;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.InstrumentedFragment;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settingslib.accessibility.AccessibilityUtils;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AccessibilityDetailsSettingsFragment extends InstrumentedFragment {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1682;
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getActivity().getIntent().getStringExtra("android.intent.extra.COMPONENT_NAME");
        if (stringExtra == null) {
            Log.w("A11yDetailsSettings", "Open accessibility services list due to no component name.");
            openAccessibilitySettingsAndFinish();
            return;
        }
        ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(stringExtra);
        AccessibilityServiceInfo accessibilityServiceInfo = getAccessibilityServiceInfo(componentNameUnflattenFromString);
        if (accessibilityServiceInfo == null) {
            Log.w("A11yDetailsSettings", "Open accessibility services list due to invalid component name.");
            openAccessibilitySettingsAndFinish();
        } else if (!isServiceAllowed(componentNameUnflattenFromString.getPackageName())) {
            Log.w("A11yDetailsSettings", "Open accessibility services list due to target accessibility service is prohibited by Device Admin.");
            openAccessibilitySettingsAndFinish();
        } else {
            openAccessibilityDetailsSettingsAndFinish(buildArguments(accessibilityServiceInfo));
        }
    }

    @VisibleForTesting
    void openAccessibilitySettingsAndFinish() {
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(getActivity());
        subSettingLauncher.setDestination(AccessibilitySettings.class.getName());
        subSettingLauncher.setSourceMetricsCategory(getMetricsCategory());
        subSettingLauncher.launch();
        finish();
    }

    @VisibleForTesting
    void openAccessibilityDetailsSettingsAndFinish(Bundle bundle) {
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(getActivity());
        subSettingLauncher.setDestination(ToggleAccessibilityServicePreferenceFragment.class.getName());
        subSettingLauncher.setSourceMetricsCategory(getMetricsCategory());
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.launch();
        finish();
    }

    @VisibleForTesting
    boolean isServiceAllowed(String str) {
        List permittedAccessibilityServices = ((DevicePolicyManager) getContext().getSystemService(DevicePolicyManager.class)).getPermittedAccessibilityServices(UserHandle.myUserId());
        return permittedAccessibilityServices == null || permittedAccessibilityServices.contains(str);
    }

    private AccessibilityServiceInfo getAccessibilityServiceInfo(ComponentName componentName) {
        if (componentName == null) {
            return null;
        }
        List<AccessibilityServiceInfo> installedAccessibilityServiceList = AccessibilityManager.getInstance(getActivity()).getInstalledAccessibilityServiceList();
        int size = installedAccessibilityServiceList.size();
        for (int i = 0; i < size; i++) {
            AccessibilityServiceInfo accessibilityServiceInfo = installedAccessibilityServiceList.get(i);
            ResolveInfo resolveInfo = accessibilityServiceInfo.getResolveInfo();
            if (componentName.getPackageName().equals(resolveInfo.serviceInfo.packageName) && componentName.getClassName().equals(resolveInfo.serviceInfo.name)) {
                return accessibilityServiceInfo;
            }
        }
        return null;
    }

    private Bundle buildArguments(AccessibilityServiceInfo accessibilityServiceInfo) {
        ResolveInfo resolveInfo = accessibilityServiceInfo.getResolveInfo();
        String string = resolveInfo.loadLabel(getActivity().getPackageManager()).toString();
        ServiceInfo serviceInfo = resolveInfo.serviceInfo;
        String str = serviceInfo.packageName;
        ComponentName componentName = new ComponentName(str, serviceInfo.name);
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = AccessibilityManager.getInstance(getActivity()).getEnabledAccessibilityServiceList(-1);
        boolean zContains = AccessibilityUtils.getEnabledServicesFromSettings(getActivity()).contains(componentName);
        String strLoadDescription = accessibilityServiceInfo.loadDescription(getActivity().getPackageManager());
        if (TextUtils.isEmpty(strLoadDescription)) {
            strLoadDescription = getString(R.string.accessibility_service_default_description);
        }
        if (zContains && AccessibilityUtils.hasServiceCrashed(str, serviceInfo.name, enabledAccessibilityServiceList)) {
            strLoadDescription = getString(R.string.accessibility_description_state_stopped);
        }
        Bundle bundle = new Bundle();
        bundle.putString("preference_key", componentName.flattenToString());
        bundle.putBoolean("checked", zContains);
        bundle.putString("title", string);
        bundle.putParcelable("resolve_info", resolveInfo);
        bundle.putString("summary", strLoadDescription);
        String settingsActivityName = accessibilityServiceInfo.getSettingsActivityName();
        if (!TextUtils.isEmpty(settingsActivityName)) {
            bundle.putString("settings_title", getString(R.string.accessibility_menu_item_settings));
            bundle.putString("settings_component_name", new ComponentName(str, settingsActivityName).flattenToString());
        }
        bundle.putParcelable("component_name", componentName);
        return bundle;
    }

    private void finish() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.finish();
    }
}
