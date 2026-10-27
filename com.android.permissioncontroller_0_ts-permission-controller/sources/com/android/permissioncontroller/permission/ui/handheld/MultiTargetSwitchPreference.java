package com.android.permissioncontroller.permission.ui.handheld;

import android.R;
import android.content.Context;
import android.view.View;
import android.widget.Switch;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.SwitchPreference;

/* JADX INFO: loaded from: classes8.dex */
class MultiTargetSwitchPreference extends SwitchPreference {
    private View.OnClickListener mSwitchOnClickLister;

    public MultiTargetSwitchPreference(Context context) {
        super(context);
    }

    public void setCheckedOverride(boolean checked) {
        super.setChecked(checked);
    }

    @Override // androidx.preference.TwoStatePreference
    public void setChecked(boolean checked) {
        if (this.mSwitchOnClickLister == null) {
            super.setChecked(checked);
        }
    }

    public void setSwitchOnClickListener(View.OnClickListener listener) {
        this.mSwitchOnClickLister = listener;
    }

    @Override // androidx.preference.SwitchPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        Switch switchView = (Switch) holder.itemView.findViewById(R.id.switch_widget);
        if (switchView != null) {
            switchView.setOnClickListener(this.mSwitchOnClickLister);
            if (this.mSwitchOnClickLister != null) {
                int padding = (int) (((holder.itemView.getMeasuredHeight() - switchView.getMeasuredHeight()) / 2) + 0.5f);
                switchView.setPaddingRelative(padding, padding, 0, padding);
            }
        }
    }
}
