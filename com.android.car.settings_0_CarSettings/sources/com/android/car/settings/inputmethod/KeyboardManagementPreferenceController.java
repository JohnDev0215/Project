package com.android.car.settings.inputmethod;

import android.app.admin.DevicePolicyManager;
import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.SwitchPreference;
import com.android.car.settings.R;
import com.android.car.settings.common.ConfirmationDialogFragment;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.ui.preference.CarUiSwitchPreference;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: loaded from: classes5.dex */
public class KeyboardManagementPreferenceController extends PreferenceController<PreferenceGroup> {
    static final String DIRECT_BOOT_WARN_DIALOG_TAG = "DirectBootWarnDialog";
    private static final String KEY_INPUT_METHOD_INFO = "INPUT_METHOD_INFO";
    static final String SECURITY_WARN_DIALOG_TAG = "SecurityWarnDialog";
    private final DevicePolicyManager mDevicePolicyManager;
    private final ConfirmationDialogFragment.ConfirmListener mDirectBootWarnConfirmListener;
    private final InputMethodManager mInputMethodManager;
    private final PackageManager mPackageManager;
    private final ConfirmationDialogFragment.RejectListener mRejectListener;
    private final ConfirmationDialogFragment.ConfirmListener mSecurityWarnDialogConfirmListener;

    public /* synthetic */ void lambda$new$0$KeyboardManagementPreferenceController(Bundle args) {
        InputMethodInfo inputMethodInfo = (InputMethodInfo) args.getParcelable(KEY_INPUT_METHOD_INFO);
        InputMethodUtil.enableInputMethod(getContext().getContentResolver(), inputMethodInfo);
        refreshUi();
    }

    public /* synthetic */ void lambda$new$1$KeyboardManagementPreferenceController(Bundle args) {
        refreshUi();
    }

    public /* synthetic */ void lambda$new$2$KeyboardManagementPreferenceController(Bundle args) {
        InputMethodInfo inputMethodInfo = (InputMethodInfo) args.getParcelable(KEY_INPUT_METHOD_INFO);
        if (inputMethodInfo.getServiceInfo().directBootAware) {
            InputMethodUtil.enableInputMethod(getContext().getContentResolver(), inputMethodInfo);
            refreshUi();
        } else {
            showDirectBootWarnDialog(inputMethodInfo);
        }
    }

    public KeyboardManagementPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mDirectBootWarnConfirmListener = new ConfirmationDialogFragment.ConfirmListener() { // from class: com.android.car.settings.inputmethod.-$$Lambda$KeyboardManagementPreferenceController$CcXuzXMakuZEPrWMIhGKfGBzWIQ
            @Override // com.android.car.settings.common.ConfirmationDialogFragment.ConfirmListener
            public final void onConfirm(Bundle bundle) {
                this.f$0.lambda$new$0$KeyboardManagementPreferenceController(bundle);
            }
        };
        this.mRejectListener = new ConfirmationDialogFragment.RejectListener() { // from class: com.android.car.settings.inputmethod.-$$Lambda$KeyboardManagementPreferenceController$3VcLbIn5lLDTTEdW1QfohSDbQJM
            @Override // com.android.car.settings.common.ConfirmationDialogFragment.RejectListener
            public final void onReject(Bundle bundle) {
                this.f$0.lambda$new$1$KeyboardManagementPreferenceController(bundle);
            }
        };
        this.mSecurityWarnDialogConfirmListener = new ConfirmationDialogFragment.ConfirmListener() { // from class: com.android.car.settings.inputmethod.-$$Lambda$KeyboardManagementPreferenceController$aaXBJ8iDdn2QyLjrTPt0ib4onSk
            @Override // com.android.car.settings.common.ConfirmationDialogFragment.ConfirmListener
            public final void onConfirm(Bundle bundle) {
                this.f$0.lambda$new$2$KeyboardManagementPreferenceController(bundle);
            }
        };
        this.mPackageManager = context.getPackageManager();
        this.mDevicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        this.mInputMethodManager = (InputMethodManager) context.getSystemService("input_method");
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        super.onCreateInternal();
        ConfirmationDialogFragment dialogFragment = (ConfirmationDialogFragment) getFragmentController().findDialogByTag(DIRECT_BOOT_WARN_DIALOG_TAG);
        ConfirmationDialogFragment.resetListeners(dialogFragment, this.mDirectBootWarnConfirmListener, this.mRejectListener, null);
        ConfirmationDialogFragment dialogFragment2 = (ConfirmationDialogFragment) getFragmentController().findDialogByTag(SECURITY_WARN_DIALOG_TAG);
        ConfirmationDialogFragment.resetListeners(dialogFragment2, this.mSecurityWarnDialogConfirmListener, this.mRejectListener, null);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preferenceGroup) {
        List<String> permittedInputMethods = this.mDevicePolicyManager.getPermittedInputMethodsForCurrentUser();
        Set<String> permittedInputMethodsSet = permittedInputMethods == null ? null : new HashSet<>(permittedInputMethods);
        preferenceGroup.removeAll();
        List<InputMethodInfo> inputMethodInfos = this.mInputMethodManager.getInputMethodList();
        if (inputMethodInfos == null || inputMethodInfos.size() == 0) {
            return;
        }
        Collections.sort(inputMethodInfos, Comparator.comparing(new Function() { // from class: com.android.car.settings.inputmethod.-$$Lambda$KeyboardManagementPreferenceController$wUphf5H5upIJgoWms2vv-6N6W70
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$updateState$3$KeyboardManagementPreferenceController((InputMethodInfo) obj);
            }
        }).thenComparing(new Function() { // from class: com.android.car.settings.inputmethod.-$$Lambda$KeyboardManagementPreferenceController$0Sasosv2Uq4CT2wbkBYyMy40BVY
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$updateState$4$KeyboardManagementPreferenceController((InputMethodInfo) obj);
            }
        }));
        for (InputMethodInfo inputMethodInfo : inputMethodInfos) {
            if (isInputMethodAllowedByOrganization(permittedInputMethodsSet, inputMethodInfo) && !inputMethodInfo.getPackageName().equals(InputMethodUtil.GOOGLE_VOICE_TYPING)) {
                preferenceGroup.addPreference(createSwitchPreference(inputMethodInfo));
            }
        }
    }

    public /* synthetic */ String lambda$updateState$3$KeyboardManagementPreferenceController(InputMethodInfo a) {
        return InputMethodUtil.getPackageLabel(this.mPackageManager, a);
    }

    public /* synthetic */ String lambda$updateState$4$KeyboardManagementPreferenceController(InputMethodInfo a) {
        return InputMethodUtil.getSummaryString(getContext(), this.mInputMethodManager, a);
    }

    private boolean isInputMethodAllowedByOrganization(Set<String> permittedList, InputMethodInfo inputMethodInfo) {
        return permittedList == null || permittedList.contains(inputMethodInfo.getPackageName());
    }

    private boolean isInputMethodEnabled(InputMethodInfo inputMethodInfo) {
        return InputMethodUtil.isInputMethodEnabled(getContext().getContentResolver(), inputMethodInfo);
    }

    private boolean isOnlyEnabledDefaultInputMethod(InputMethodInfo inputMethodInfo) {
        if (!inputMethodInfo.isDefault(getContext())) {
            return false;
        }
        List<InputMethodInfo> inputMethodInfos = this.mInputMethodManager.getEnabledInputMethodList();
        for (InputMethodInfo imi : inputMethodInfos) {
            if (imi.isDefault(getContext()) && !imi.getId().equals(inputMethodInfo.getId())) {
                return false;
            }
        }
        return true;
    }

    private SwitchPreference createSwitchPreference(final InputMethodInfo inputMethodInfo) {
        SwitchPreference switchPreference = new CarUiSwitchPreference(getContext());
        switchPreference.setKey(String.valueOf(inputMethodInfo.getId()));
        switchPreference.setIcon(InputMethodUtil.getPackageIcon(this.mPackageManager, inputMethodInfo));
        switchPreference.setTitle(InputMethodUtil.getPackageLabel(this.mPackageManager, inputMethodInfo));
        switchPreference.setChecked(InputMethodUtil.isInputMethodEnabled(getContext().getContentResolver(), inputMethodInfo));
        switchPreference.setSummary(InputMethodUtil.getSummaryString(getContext(), this.mInputMethodManager, inputMethodInfo));
        if (!isInputMethodEnabled(inputMethodInfo)) {
            switchPreference.setEnabled(true);
        } else {
            switchPreference.setEnabled(!isOnlyEnabledDefaultInputMethod(inputMethodInfo));
        }
        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.car.settings.inputmethod.-$$Lambda$KeyboardManagementPreferenceController$8C0Z0UXKjvatpwFjPyZsvrGg25s
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public final boolean onPreferenceChange(Preference preference, Object obj) {
                return this.f$0.lambda$createSwitchPreference$5$KeyboardManagementPreferenceController(inputMethodInfo, preference, obj);
            }
        });
        return switchPreference;
    }

    public /* synthetic */ boolean lambda$createSwitchPreference$5$KeyboardManagementPreferenceController(InputMethodInfo inputMethodInfo, Preference switchPref, Object newValue) {
        boolean enable = ((Boolean) newValue).booleanValue();
        if (enable) {
            showSecurityWarnDialog(inputMethodInfo);
            return false;
        }
        InputMethodUtil.disableInputMethod(getContext(), this.mInputMethodManager, inputMethodInfo);
        refreshUi();
        return false;
    }

    private void showDirectBootWarnDialog(InputMethodInfo inputMethodInfo) {
        ConfirmationDialogFragment dialog = new ConfirmationDialogFragment.Builder(getContext()).setMessage(getContext().getString(R.string.direct_boot_unaware_dialog_message_car)).setPositiveButton(android.R.string.ok, this.mDirectBootWarnConfirmListener).setNegativeButton(android.R.string.cancel, this.mRejectListener).addArgumentParcelable(KEY_INPUT_METHOD_INFO, inputMethodInfo).build();
        getFragmentController().showDialog(dialog, DIRECT_BOOT_WARN_DIALOG_TAG);
    }

    private void showSecurityWarnDialog(InputMethodInfo inputMethodInfo) {
        CharSequence label = inputMethodInfo.loadLabel(this.mPackageManager);
        ConfirmationDialogFragment dialog = new ConfirmationDialogFragment.Builder(getContext()).setTitle(android.R.string.dialog_alert_title).setMessage(getContext().getString(R.string.ime_security_warning, label)).setPositiveButton(android.R.string.ok, this.mSecurityWarnDialogConfirmListener).setNegativeButton(android.R.string.cancel, this.mRejectListener).addArgumentParcelable(KEY_INPUT_METHOD_INFO, inputMethodInfo).build();
        getFragmentController().showDialog(dialog, SECURITY_WARN_DIALOG_TAG);
    }
}
