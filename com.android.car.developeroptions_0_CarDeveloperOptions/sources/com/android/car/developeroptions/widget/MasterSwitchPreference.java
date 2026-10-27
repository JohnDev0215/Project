package com.android.car.developeroptions.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Switch;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.TwoTargetPreference;

/* JADX INFO: loaded from: classes.dex */
public class MasterSwitchPreference extends TwoTargetPreference {
    private boolean mChecked;
    private boolean mEnableSwitch;
    private Switch mSwitch;

    @Override // com.android.settingslib.TwoTargetPreference
    protected int getSecondTargetResId() {
        return R.layout.preference_widget_master_switch;
    }

    public MasterSwitchPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mEnableSwitch = true;
    }

    public MasterSwitchPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mEnableSwitch = true;
    }

    public MasterSwitchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mEnableSwitch = true;
    }

    public MasterSwitchPreference(Context context) {
        super(context);
        this.mEnableSwitch = true;
    }

    @Override // com.android.settingslib.TwoTargetPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View viewFindViewById = preferenceViewHolder.findViewById(android.R.id.widget_frame);
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.widget.MasterSwitchPreference.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (MasterSwitchPreference.this.mSwitch == null || MasterSwitchPreference.this.mSwitch.isEnabled()) {
                        MasterSwitchPreference.this.setChecked(!r2.mChecked);
                        MasterSwitchPreference masterSwitchPreference = MasterSwitchPreference.this;
                        if (!masterSwitchPreference.callChangeListener(Boolean.valueOf(masterSwitchPreference.mChecked))) {
                            MasterSwitchPreference.this.setChecked(!r1.mChecked);
                        } else {
                            MasterSwitchPreference masterSwitchPreference2 = MasterSwitchPreference.this;
                            masterSwitchPreference2.persistBoolean(masterSwitchPreference2.mChecked);
                        }
                    }
                }
            });
        }
        Switch r3 = (Switch) preferenceViewHolder.findViewById(R.id.switchWidget);
        this.mSwitch = r3;
        if (r3 != null) {
            r3.setContentDescription(getTitle());
            this.mSwitch.setChecked(this.mChecked);
            this.mSwitch.setEnabled(this.mEnableSwitch);
        }
    }

    public boolean isChecked() {
        return this.mSwitch != null && this.mChecked;
    }

    public void setChecked(boolean z) {
        this.mChecked = z;
        Switch r0 = this.mSwitch;
        if (r0 != null) {
            r0.setChecked(z);
        }
    }

    public void setSwitchEnabled(boolean z) {
        this.mEnableSwitch = z;
        Switch r0 = this.mSwitch;
        if (r0 != null) {
            r0.setEnabled(z);
        }
    }

    public void setDisabledByAdmin(RestrictedLockUtils.EnforcedAdmin enforcedAdmin) {
        setSwitchEnabled(enforcedAdmin == null);
    }
}
