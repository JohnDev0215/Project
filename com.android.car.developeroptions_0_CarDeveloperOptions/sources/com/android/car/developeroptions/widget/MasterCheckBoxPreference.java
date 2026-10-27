package com.android.car.developeroptions.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.settingslib.TwoTargetPreference;

/* JADX INFO: loaded from: classes.dex */
public class MasterCheckBoxPreference extends TwoTargetPreference {
    private CheckBox mCheckBox;
    private boolean mChecked;
    private boolean mEnableCheckBox;

    @Override // com.android.settingslib.TwoTargetPreference
    protected int getSecondTargetResId() {
        return R.layout.preference_widget_master_checkbox;
    }

    public MasterCheckBoxPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mEnableCheckBox = true;
    }

    public MasterCheckBoxPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mEnableCheckBox = true;
    }

    public MasterCheckBoxPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mEnableCheckBox = true;
    }

    @Override // com.android.settingslib.TwoTargetPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View viewFindViewById = preferenceViewHolder.findViewById(android.R.id.widget_frame);
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.widget.MasterCheckBoxPreference.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (MasterCheckBoxPreference.this.mCheckBox == null || MasterCheckBoxPreference.this.mCheckBox.isEnabled()) {
                        MasterCheckBoxPreference.this.setChecked(!r2.mChecked);
                        MasterCheckBoxPreference masterCheckBoxPreference = MasterCheckBoxPreference.this;
                        if (!masterCheckBoxPreference.callChangeListener(Boolean.valueOf(masterCheckBoxPreference.mChecked))) {
                            MasterCheckBoxPreference.this.setChecked(!r1.mChecked);
                        } else {
                            MasterCheckBoxPreference masterCheckBoxPreference2 = MasterCheckBoxPreference.this;
                            masterCheckBoxPreference2.persistBoolean(masterCheckBoxPreference2.mChecked);
                        }
                    }
                }
            });
        }
        CheckBox checkBox = (CheckBox) preferenceViewHolder.findViewById(R.id.checkboxWidget);
        this.mCheckBox = checkBox;
        if (checkBox != null) {
            checkBox.setContentDescription(getTitle());
            this.mCheckBox.setChecked(this.mChecked);
            this.mCheckBox.setEnabled(this.mEnableCheckBox);
        }
    }

    @Override // androidx.preference.Preference
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setCheckBoxEnabled(z);
    }

    public void setChecked(boolean z) {
        this.mChecked = z;
        CheckBox checkBox = this.mCheckBox;
        if (checkBox != null) {
            checkBox.setChecked(z);
        }
    }

    public void setCheckBoxEnabled(boolean z) {
        this.mEnableCheckBox = z;
        CheckBox checkBox = this.mCheckBox;
        if (checkBox != null) {
            checkBox.setEnabled(z);
        }
    }
}
