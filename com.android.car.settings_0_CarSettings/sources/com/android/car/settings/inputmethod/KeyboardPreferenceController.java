package com.android.car.settings.inputmethod;

import android.app.admin.DevicePolicyManager;
import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.pm.PackageManager;
import android.icu.text.ListFormatter;
import android.text.BidiFormatter;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class KeyboardPreferenceController extends PreferenceController<Preference> {
    private static final String SUMMARY_EMPTY = "";
    private final DevicePolicyManager mDevicePolicyManager;
    private final InputMethodManager mInputMethodManager;
    private final PackageManager mPackageManager;

    public KeyboardPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mPackageManager = context.getPackageManager();
        this.mDevicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        this.mInputMethodManager = (InputMethodManager) context.getSystemService("input_method");
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        List<InputMethodInfo> inputMethodInfos = InputMethodUtil.getPermittedAndEnabledInputMethodList(this.mInputMethodManager, this.mDevicePolicyManager);
        if (inputMethodInfos == null) {
            preference.setSummary(SUMMARY_EMPTY);
            return;
        }
        List<String> labels = new ArrayList<>();
        for (InputMethodInfo inputMethodInfo : inputMethodInfos) {
            labels.add(InputMethodUtil.getPackageLabel(this.mPackageManager, inputMethodInfo));
        }
        if (labels.isEmpty()) {
            preference.setSummary(SUMMARY_EMPTY);
            return;
        }
        BidiFormatter bidiFormatter = BidiFormatter.getInstance();
        List<String> summaries = new ArrayList<>();
        for (String label : labels) {
            summaries.add(bidiFormatter.unicodeWrap(label));
        }
        preference.setSummary(ListFormatter.getInstance().format(summaries));
    }
}
