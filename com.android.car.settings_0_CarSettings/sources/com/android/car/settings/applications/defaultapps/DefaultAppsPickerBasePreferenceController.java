package com.android.car.settings.applications.defaultapps;

import android.R;
import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import androidx.preference.TwoStatePreference;
import com.android.car.settings.common.ConfirmationDialogFragment;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.GroupSelectionPreferenceController;
import com.android.car.settings.common.Logger;
import com.android.car.ui.preference.CarUiRadioButtonPreference;
import com.android.settingslib.applications.DefaultAppInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DefaultAppsPickerBasePreferenceController extends GroupSelectionPreferenceController {
    private static final String DIALOG_KEY_ARG = "key_arg";
    private static final Logger LOG = new Logger((Class<?>) DefaultAppsPickerBasePreferenceController.class);
    protected static final String NONE_PREFERENCE_KEY = "";
    private final ConfirmationDialogFragment.ConfirmListener mConfirmListener;
    private final Map<String, DefaultAppInfo> mDefaultAppInfoMap;

    protected abstract List<DefaultAppInfo> getCandidates();

    protected abstract String getCurrentDefaultKey();

    protected abstract void setCurrentDefault(String str);

    public /* synthetic */ void lambda$new$0$DefaultAppsPickerBasePreferenceController(Bundle arguments) {
        setCurrentDefault(arguments.getString(DIALOG_KEY_ARG));
        notifyCheckedKeyChanged();
    }

    public DefaultAppsPickerBasePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mDefaultAppInfoMap = new HashMap();
        this.mConfirmListener = new ConfirmationDialogFragment.ConfirmListener() { // from class: com.android.car.settings.applications.defaultapps.-$$Lambda$DefaultAppsPickerBasePreferenceController$2Ebe821cBoQgPgWmkqLM6S4raE8
            @Override // com.android.car.settings.common.ConfirmationDialogFragment.ConfirmListener
            public final void onConfirm(Bundle bundle) {
                this.f$0.lambda$new$0$DefaultAppsPickerBasePreferenceController(bundle);
            }
        };
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        ConfirmationDialogFragment.resetListeners((ConfirmationDialogFragment) getFragmentController().findDialogByTag(ConfirmationDialogFragment.TAG), this.mConfirmListener, null, null);
    }

    @Override // com.android.car.settings.common.GroupSelectionPreferenceController
    protected List<TwoStatePreference> getGroupPreferences() {
        List<TwoStatePreference> entries = new ArrayList<>();
        if (includeNonePreference()) {
            entries.add(createNoneOption());
        }
        List<DefaultAppInfo> currentCandidates = getCandidates();
        if (currentCandidates != null) {
            for (DefaultAppInfo info : currentCandidates) {
                this.mDefaultAppInfoMap.put(info.getKey(), info);
                entries.add(createOption(info));
            }
        } else {
            LOG.i("no candidate provided");
        }
        return entries;
    }

    @Override // com.android.car.settings.common.GroupSelectionPreferenceController
    protected final boolean handleGroupItemSelected(TwoStatePreference preference) {
        String selectedKey = preference.getKey();
        if (TextUtils.equals(selectedKey, getCurrentCheckedKey())) {
            return false;
        }
        CharSequence message = getConfirmationMessage(this.mDefaultAppInfoMap.get(selectedKey));
        if (!TextUtils.isEmpty(message)) {
            ConfirmationDialogFragment dialogFragment = new ConfirmationDialogFragment.Builder(getContext()).setMessage(message.toString()).setPositiveButton(R.string.ok, this.mConfirmListener).setNegativeButton(R.string.cancel, (ConfirmationDialogFragment.RejectListener) null).addArgumentString(DIALOG_KEY_ARG, selectedKey).build();
            getFragmentController().showDialog(dialogFragment, ConfirmationDialogFragment.TAG);
            return false;
        }
        setCurrentDefault(selectedKey);
        return true;
    }

    @Override // com.android.car.settings.common.GroupSelectionPreferenceController
    protected final String getCurrentCheckedKey() {
        return getCurrentDefaultKey();
    }

    protected TwoStatePreference createOption(DefaultAppInfo info) {
        CarUiRadioButtonPreference preference = new CarUiRadioButtonPreference(getContext());
        preference.setKey(info.getKey());
        preference.setTitle(info.loadLabel());
        preference.setIcon(DefaultAppUtils.getSafeIcon(info.loadIcon(), getContext().getResources().getInteger(com.android.car.settings.R.integer.default_app_safe_icon_size)));
        preference.setEnabled(info.enabled);
        return preference;
    }

    protected CharSequence getConfirmationMessage(DefaultAppInfo info) {
        return null;
    }

    protected int getCurrentProcessUserId() {
        return UserHandle.myUserId();
    }

    protected boolean includeNonePreference() {
        return true;
    }

    private CarUiRadioButtonPreference createNoneOption() {
        CarUiRadioButtonPreference preference = new CarUiRadioButtonPreference(getContext());
        preference.setKey(NONE_PREFERENCE_KEY);
        preference.setTitle(com.android.car.settings.R.string.app_list_preference_none);
        preference.setIcon(com.android.car.settings.R.drawable.ic_remove_circle);
        return preference;
    }
}
