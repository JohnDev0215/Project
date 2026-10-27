package com.android.car.developeroptions.display;

import android.content.Context;
import android.content.IntentFilter;
import android.hardware.display.ColorDisplayManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.TogglePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.widget.LayoutPreference;
import java.time.LocalTime;

/* JADX INFO: loaded from: classes.dex */
public class NightDisplayActivationPreferenceController extends TogglePreferenceController {
    private ColorDisplayManager mColorDisplayManager;
    private final View.OnClickListener mListener;
    private NightDisplayTimeFormatter mTimeFormatter;
    private Button mTurnOffButton;
    private Button mTurnOnButton;

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public NightDisplayActivationPreferenceController(Context context, String str) {
        super(context, str);
        this.mListener = new View.OnClickListener() { // from class: com.android.car.developeroptions.display.NightDisplayActivationPreferenceController.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NightDisplayActivationPreferenceController.this.mColorDisplayManager.setNightDisplayActivated(!NightDisplayActivationPreferenceController.this.mColorDisplayManager.isNightDisplayActivated());
                NightDisplayActivationPreferenceController.this.updateStateInternal();
            }
        };
        this.mColorDisplayManager = (ColorDisplayManager) context.getSystemService(ColorDisplayManager.class);
        this.mTimeFormatter = new NightDisplayTimeFormatter(context);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return ColorDisplayManager.isNightDisplayAvailable(this.mContext) ? 0 : 3;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public boolean isSliceable() {
        return TextUtils.equals(getPreferenceKey(), "night_display_activated");
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        LayoutPreference layoutPreference = (LayoutPreference) preferenceScreen.findPreference(getPreferenceKey());
        Button button = (Button) layoutPreference.findViewById(R.id.night_display_turn_on_button);
        this.mTurnOnButton = button;
        button.setOnClickListener(this.mListener);
        Button button2 = (Button) layoutPreference.findViewById(R.id.night_display_turn_off_button);
        this.mTurnOffButton = button2;
        button2.setOnClickListener(this.mListener);
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public final void updateState(Preference preference) {
        updateStateInternal();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean isChecked() {
        return this.mColorDisplayManager.isNightDisplayActivated();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        return this.mColorDisplayManager.setNightDisplayActivated(z);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        return this.mTimeFormatter.getAutoModeTimeSummary(this.mContext, this.mColorDisplayManager);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateStateInternal() {
        String string;
        LocalTime nightDisplayCustomEndTime;
        if (this.mTurnOnButton == null || this.mTurnOffButton == null) {
            return;
        }
        boolean zIsNightDisplayActivated = this.mColorDisplayManager.isNightDisplayActivated();
        int nightDisplayAutoMode = this.mColorDisplayManager.getNightDisplayAutoMode();
        if (nightDisplayAutoMode == 1) {
            Context context = this.mContext;
            int i = zIsNightDisplayActivated ? R.string.night_display_activation_off_custom : R.string.night_display_activation_on_custom;
            Object[] objArr = new Object[1];
            NightDisplayTimeFormatter nightDisplayTimeFormatter = this.mTimeFormatter;
            if (zIsNightDisplayActivated) {
                nightDisplayCustomEndTime = this.mColorDisplayManager.getNightDisplayCustomStartTime();
            } else {
                nightDisplayCustomEndTime = this.mColorDisplayManager.getNightDisplayCustomEndTime();
            }
            objArr[0] = nightDisplayTimeFormatter.getFormattedTimeString(nightDisplayCustomEndTime);
            string = context.getString(i, objArr);
        } else if (nightDisplayAutoMode == 2) {
            string = this.mContext.getString(zIsNightDisplayActivated ? R.string.night_display_activation_off_twilight : R.string.night_display_activation_on_twilight);
        } else {
            string = this.mContext.getString(zIsNightDisplayActivated ? R.string.night_display_activation_off_manual : R.string.night_display_activation_on_manual);
        }
        if (zIsNightDisplayActivated) {
            this.mTurnOnButton.setVisibility(8);
            this.mTurnOffButton.setVisibility(0);
            this.mTurnOffButton.setText(string);
        } else {
            this.mTurnOnButton.setVisibility(0);
            this.mTurnOffButton.setVisibility(8);
            this.mTurnOnButton.setText(string);
        }
    }
}
