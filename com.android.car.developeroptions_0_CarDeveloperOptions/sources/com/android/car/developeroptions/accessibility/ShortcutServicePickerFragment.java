package com.android.car.developeroptions.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Binder;
import android.os.Bundle;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;
import com.android.car.developeroptions.widget.RadioButtonPickerFragment;
import com.android.car.developeroptions.widget.RadioButtonPreference;
import com.android.internal.accessibility.AccessibilityShortcutController;
import com.android.settingslib.accessibility.AccessibilityUtils;
import com.android.settingslib.widget.CandidateInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ShortcutServicePickerFragment extends RadioButtonPickerFragment {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 6;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.accessibility_shortcut_service_settings;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected List<? extends CandidateInfo> getCandidates() {
        int i;
        List<AccessibilityServiceInfo> installedAccessibilityServiceList = ((AccessibilityManager) getContext().getSystemService(AccessibilityManager.class)).getInstalledAccessibilityServiceList();
        int size = installedAccessibilityServiceList.size();
        ArrayList arrayList = new ArrayList(size);
        Map frameworkShortcutFeaturesMap = AccessibilityShortcutController.getFrameworkShortcutFeaturesMap();
        for (ComponentName componentName : frameworkShortcutFeaturesMap.keySet()) {
            if (componentName.equals(AccessibilityShortcutController.COLOR_INVERSION_COMPONENT_NAME)) {
                i = R.drawable.ic_color_inversion;
            } else {
                i = componentName.equals(AccessibilityShortcutController.DALTONIZER_COMPONENT_NAME) ? R.drawable.ic_daltonizer : R.drawable.empty_icon;
            }
            arrayList.add(new FrameworkCandidateInfo((AccessibilityShortcutController.ToggleableFrameworkFeatureInfo) frameworkShortcutFeaturesMap.get(componentName), i, componentName.flattenToString()));
        }
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(new ServiceCandidateInfo(installedAccessibilityServiceList.get(i2)));
        }
        return arrayList;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected String getDefaultKey() {
        ComponentName componentNameUnflattenFromString;
        String shortcutTargetServiceComponentNameString = AccessibilityUtils.getShortcutTargetServiceComponentNameString(getContext(), UserHandle.myUserId());
        if (shortcutTargetServiceComponentNameString == null || (componentNameUnflattenFromString = ComponentName.unflattenFromString(shortcutTargetServiceComponentNameString)) == null) {
            return null;
        }
        return componentNameUnflattenFromString.flattenToString();
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected boolean setDefaultKey(String str) {
        Settings.Secure.putString(getContext().getContentResolver(), "accessibility_shortcut_target_service", str);
        return true;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.widget.RadioButtonPreference.OnClickListener
    public void onRadioButtonClicked(RadioButtonPreference radioButtonPreference) {
        String key = radioButtonPreference.getKey();
        if (TextUtils.isEmpty(key)) {
            super.onRadioButtonClicked(radioButtonPreference);
            return;
        }
        if (AccessibilityShortcutController.getFrameworkShortcutFeaturesMap().containsKey(ComponentName.unflattenFromString(key))) {
            onRadioButtonConfirmed(key);
            return;
        }
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ConfirmationDialogFragment.newInstance(this, key).show(activity.getSupportFragmentManager(), "ConfirmationDialogFragment");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onServiceConfirmed(String str) {
        onRadioButtonConfirmed(str);
    }

    public static class ConfirmationDialogFragment extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 6;
        }

        public static ConfirmationDialogFragment newInstance(ShortcutServicePickerFragment shortcutServicePickerFragment, String str) {
            ConfirmationDialogFragment confirmationDialogFragment = new ConfirmationDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("extra_key", str);
            confirmationDialogFragment.setArguments(bundle);
            confirmationDialogFragment.setTargetFragment(shortcutServicePickerFragment, 0);
            new Binder();
            return confirmationDialogFragment;
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            return AccessibilityServiceWarning.createCapabilitiesDialog(getActivity(), ((AccessibilityManager) getActivity().getSystemService(AccessibilityManager.class)).getInstalledServiceInfoWithComponentName(ComponentName.unflattenFromString(getArguments().getString("extra_key"))), this);
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            Fragment targetFragment = getTargetFragment();
            if (i == -1 && (targetFragment instanceof ShortcutServicePickerFragment)) {
                ((ShortcutServicePickerFragment) targetFragment).onServiceConfirmed(getArguments().getString("extra_key"));
            }
        }
    }

    private class FrameworkCandidateInfo extends CandidateInfo {
        final int mIconResId;
        final String mKey;
        final AccessibilityShortcutController.ToggleableFrameworkFeatureInfo mToggleableFrameworkFeatureInfo;

        public FrameworkCandidateInfo(AccessibilityShortcutController.ToggleableFrameworkFeatureInfo toggleableFrameworkFeatureInfo, int i, String str) {
            super(true);
            this.mToggleableFrameworkFeatureInfo = toggleableFrameworkFeatureInfo;
            this.mIconResId = i;
            this.mKey = str;
        }

        @Override // com.android.settingslib.widget.CandidateInfo
        public CharSequence loadLabel() {
            return this.mToggleableFrameworkFeatureInfo.getLabel(ShortcutServicePickerFragment.this.getContext());
        }

        @Override // com.android.settingslib.widget.CandidateInfo
        public Drawable loadIcon() {
            return ShortcutServicePickerFragment.this.getContext().getDrawable(this.mIconResId);
        }

        @Override // com.android.settingslib.widget.CandidateInfo
        public String getKey() {
            return this.mKey;
        }
    }

    private class ServiceCandidateInfo extends CandidateInfo {
        final AccessibilityServiceInfo mServiceInfo;

        public ServiceCandidateInfo(AccessibilityServiceInfo accessibilityServiceInfo) {
            super(true);
            this.mServiceInfo = accessibilityServiceInfo;
        }

        @Override // com.android.settingslib.widget.CandidateInfo
        public CharSequence loadLabel() {
            PackageManager packageManager = ShortcutServicePickerFragment.this.getContext().getPackageManager();
            CharSequence charSequenceLoadLabel = this.mServiceInfo.getResolveInfo().serviceInfo.loadLabel(packageManager);
            if (charSequenceLoadLabel != null) {
                return charSequenceLoadLabel;
            }
            ComponentName componentName = this.mServiceInfo.getComponentName();
            if (componentName != null) {
                try {
                    return packageManager.getApplicationInfoAsUser(componentName.getPackageName(), 0, UserHandle.myUserId()).loadLabel(packageManager);
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
            return null;
        }

        @Override // com.android.settingslib.widget.CandidateInfo
        public Drawable loadIcon() {
            ResolveInfo resolveInfo = this.mServiceInfo.getResolveInfo();
            if (resolveInfo.getIconResource() == 0) {
                return ShortcutServicePickerFragment.this.getContext().getDrawable(R.drawable.ic_accessibility_generic);
            }
            return resolveInfo.loadIcon(ShortcutServicePickerFragment.this.getContext().getPackageManager());
        }

        @Override // com.android.settingslib.widget.CandidateInfo
        public String getKey() {
            return this.mServiceInfo.getComponentName().flattenToString();
        }
    }
}
