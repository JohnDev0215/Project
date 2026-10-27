package com.android.car.developeroptions.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.settingslib.RestrictedPreference;

/* JADX INFO: loaded from: classes.dex */
public class GearPreference extends RestrictedPreference implements View.OnClickListener {
    private OnGearClickListener mOnGearClickListener;

    public interface OnGearClickListener {
        void onGearClick(GearPreference gearPreference);
    }

    @Override // com.android.settingslib.RestrictedPreference, com.android.settingslib.TwoTargetPreference
    protected int getSecondTargetResId() {
        return R.layout.preference_widget_gear;
    }

    public GearPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setOnGearClickListener(OnGearClickListener onGearClickListener) {
        this.mOnGearClickListener = onGearClickListener;
        notifyChanged();
    }

    @Override // com.android.settingslib.RestrictedPreference, com.android.settingslib.TwoTargetPreference
    protected boolean shouldHideSecondTarget() {
        return this.mOnGearClickListener == null;
    }

    @Override // com.android.settingslib.RestrictedPreference, com.android.settingslib.TwoTargetPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View viewFindViewById = preferenceViewHolder.findViewById(R.id.settings_button);
        if (this.mOnGearClickListener != null) {
            viewFindViewById.setVisibility(0);
            viewFindViewById.setOnClickListener(this);
        } else {
            viewFindViewById.setVisibility(8);
            viewFindViewById.setOnClickListener(null);
        }
        viewFindViewById.setEnabled(true);
    }

    public void onClick(View view) {
        OnGearClickListener onGearClickListener;
        if (view.getId() != R.id.settings_button || (onGearClickListener = this.mOnGearClickListener) == null) {
            return;
        }
        onGearClickListener.onGearClick(this);
    }
}
