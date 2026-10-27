package com.android.car.developeroptions.notification;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.settingslib.TwoTargetPreference;

/* JADX INFO: loaded from: classes.dex */
public class ZenCustomRadioButtonPreference extends TwoTargetPreference implements View.OnClickListener {
    private RadioButton mButton;
    private boolean mChecked;
    private OnGearClickListener mOnGearClickListener;
    private OnRadioButtonClickListener mOnRadioButtonClickListener;

    public interface OnGearClickListener {
        void onGearClick(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference);
    }

    public interface OnRadioButtonClickListener {
        void onRadioButtonClick(ZenCustomRadioButtonPreference zenCustomRadioButtonPreference);
    }

    @Override // com.android.settingslib.TwoTargetPreference
    protected int getSecondTargetResId() {
        return R.layout.preference_widget_gear;
    }

    public ZenCustomRadioButtonPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setLayoutResource(R.layout.preference_two_target_radio);
    }

    public ZenCustomRadioButtonPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setLayoutResource(R.layout.preference_two_target_radio);
    }

    public ZenCustomRadioButtonPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayoutResource(R.layout.preference_two_target_radio);
    }

    public ZenCustomRadioButtonPreference(Context context) {
        super(context);
        setLayoutResource(R.layout.preference_two_target_radio);
    }

    public void setOnGearClickListener(OnGearClickListener onGearClickListener) {
        this.mOnGearClickListener = onGearClickListener;
        notifyChanged();
    }

    public void setOnRadioButtonClickListener(OnRadioButtonClickListener onRadioButtonClickListener) {
        this.mOnRadioButtonClickListener = onRadioButtonClickListener;
        notifyChanged();
    }

    @Override // com.android.settingslib.TwoTargetPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View viewFindViewById = preferenceViewHolder.findViewById(R.id.checkbox_frame);
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(this);
        }
        RadioButton radioButton = (RadioButton) preferenceViewHolder.findViewById(android.R.id.checkbox);
        this.mButton = radioButton;
        if (radioButton != null) {
            radioButton.setChecked(this.mChecked);
        }
        View viewFindViewById2 = preferenceViewHolder.findViewById(android.R.id.widget_frame);
        View viewFindViewById3 = preferenceViewHolder.findViewById(R.id.two_target_divider);
        if (this.mOnGearClickListener != null) {
            viewFindViewById3.setVisibility(0);
            viewFindViewById2.setVisibility(0);
            viewFindViewById2.setOnClickListener(this);
        } else {
            viewFindViewById3.setVisibility(8);
            viewFindViewById2.setVisibility(8);
            viewFindViewById2.setOnClickListener(null);
        }
    }

    public void setChecked(boolean z) {
        this.mChecked = z;
        RadioButton radioButton = this.mButton;
        if (radioButton != null) {
            radioButton.setChecked(z);
        }
    }

    @Override // androidx.preference.Preference
    public void onClick() {
        OnRadioButtonClickListener onRadioButtonClickListener = this.mOnRadioButtonClickListener;
        if (onRadioButtonClickListener != null) {
            onRadioButtonClickListener.onRadioButtonClick(this);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        OnRadioButtonClickListener onRadioButtonClickListener;
        if (view.getId() == 16908312) {
            OnGearClickListener onGearClickListener = this.mOnGearClickListener;
            if (onGearClickListener != null) {
                onGearClickListener.onGearClick(this);
                return;
            }
            return;
        }
        if (view.getId() != R.id.checkbox_frame || (onRadioButtonClickListener = this.mOnRadioButtonClickListener) == null) {
            return;
        }
        onRadioButtonClickListener.onRadioButtonClick(this);
    }
}
