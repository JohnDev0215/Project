package com.android.car.developeroptions.fuelgauge.batterysaver;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.widget.RadioButtonPickerFragment;
import com.android.car.developeroptions.widget.RadioButtonPreference;
import com.android.settingslib.fuelgauge.BatterySaverUtils;
import com.android.settingslib.widget.CandidateInfo;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BatterySaverScheduleSettings extends RadioButtonPickerFragment {
    public BatterySaverScheduleRadioButtonsController mRadioButtonController;
    private BatterySaverScheduleSeekBarController mSeekBarController;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 0;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.battery_saver_schedule_settings;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        BatterySaverScheduleSeekBarController batterySaverScheduleSeekBarController = new BatterySaverScheduleSeekBarController(context);
        this.mSeekBarController = batterySaverScheduleSeekBarController;
        this.mRadioButtonController = new BatterySaverScheduleRadioButtonsController(context, batterySaverScheduleSeekBarController);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setDivider(new ColorDrawable(0));
        setDividerHeight(0);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected List<? extends CandidateInfo> getCandidates() {
        Context context = getContext();
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        String string = getContext().getResources().getString(android.R.string.capability_desc_canTakeScreenshot);
        arrayListNewArrayList.add(new BatterySaverScheduleCandidateInfo(context.getText(R.string.battery_saver_auto_no_schedule), null, "key_battery_saver_no_schedule", true));
        if (!TextUtils.isEmpty(string)) {
            arrayListNewArrayList.add(new BatterySaverScheduleCandidateInfo(context.getText(R.string.battery_saver_auto_routine), context.getText(R.string.battery_saver_auto_routine_summary), "key_battery_saver_routine", true));
        } else {
            BatterySaverUtils.revertScheduleToNoneIfNeeded(context);
        }
        arrayListNewArrayList.add(new BatterySaverScheduleCandidateInfo(context.getText(R.string.battery_saver_auto_percentage), null, "key_battery_saver_percentage", true));
        return arrayListNewArrayList;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    public void bindPreferenceExtra(RadioButtonPreference radioButtonPreference, String str, CandidateInfo candidateInfo, String str2, String str3) {
        CharSequence summary = ((BatterySaverScheduleCandidateInfo) candidateInfo).getSummary();
        if (summary != null) {
            radioButtonPreference.setSummary(summary);
            radioButtonPreference.setAppendixVisibility(8);
        }
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected void addStaticPreferences(PreferenceScreen preferenceScreen) {
        this.mSeekBarController.updateSeekBar();
        this.mSeekBarController.addToScreen(preferenceScreen);
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected String getDefaultKey() {
        return this.mRadioButtonController.getDefaultKey();
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected boolean setDefaultKey(String str) {
        return this.mRadioButtonController.setDefaultKey(str);
    }

    static class BatterySaverScheduleCandidateInfo extends CandidateInfo {
        private final String mKey;
        private final CharSequence mLabel;
        private final CharSequence mSummary;

        @Override // com.android.settingslib.widget.CandidateInfo
        public Drawable loadIcon() {
            return null;
        }

        BatterySaverScheduleCandidateInfo(CharSequence charSequence, CharSequence charSequence2, String str, boolean z) {
            super(z);
            this.mLabel = charSequence;
            this.mKey = str;
            this.mSummary = charSequence2;
        }

        @Override // com.android.settingslib.widget.CandidateInfo
        public CharSequence loadLabel() {
            return this.mLabel;
        }

        @Override // com.android.settingslib.widget.CandidateInfo
        public String getKey() {
            return this.mKey;
        }

        public CharSequence getSummary() {
            return this.mSummary;
        }
    }
}
