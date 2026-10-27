package com.android.car.settings.datausage;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.text.format.Time;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.datausage.UsageCycleResetDayOfMonthPickerDialog;

/* JADX INFO: loaded from: classes5.dex */
public class CycleResetDayOfMonthPickerPreferenceController extends DataWarningAndLimitBasePreferenceController<Preference> implements UsageCycleResetDayOfMonthPickerDialog.ResetDayOfMonthPickedListener {
    private static final String CYCLE_PICKER_DIALOG_TAG = "cycle_picker_dialog_tag";

    public CycleResetDayOfMonthPickerPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        UsageCycleResetDayOfMonthPickerDialog dialog = (UsageCycleResetDayOfMonthPickerDialog) getFragmentController().findDialogByTag(CYCLE_PICKER_DIALOG_TAG);
        if (dialog != null) {
            dialog.setResetDayOfMonthPickedListener(this);
        }
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected boolean handlePreferenceClicked(Preference preference) {
        UsageCycleResetDayOfMonthPickerDialog dialog = UsageCycleResetDayOfMonthPickerDialog.newInstance(getNetworkPolicyEditor().getPolicyCycleDay(getNetworkTemplate()));
        dialog.setResetDayOfMonthPickedListener(this);
        getFragmentController().showDialog(dialog, CYCLE_PICKER_DIALOG_TAG);
        return true;
    }

    @Override // com.android.car.settings.datausage.UsageCycleResetDayOfMonthPickerDialog.ResetDayOfMonthPickedListener
    public void onDayOfMonthPicked(int dayOfMonth) {
        getNetworkPolicyEditor().setPolicyCycleDay(getNetworkTemplate(), dayOfMonth, new Time().timezone);
    }
}
