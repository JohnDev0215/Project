package com.android.permissioncontroller.permission.ui.television;

import android.content.Context;
import android.view.View;
import android.widget.RadioButton;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.permissioncontroller.R;

/* JADX INFO: loaded from: classes8.dex */
public class RadioButtonPreference extends Preference {
    private boolean mIsChecked;
    private View.OnClickListener mOnClickListener;
    private PreferenceViewHolder mViewHolder;

    public RadioButtonPreference(Context context, int titleResId) {
        super(context);
        this.mOnClickListener = null;
        this.mViewHolder = null;
        this.mIsChecked = false;
        setWidgetLayoutResource(R.layout.radio_button_preference_widget);
        setTitle(titleResId);
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder viewHolder) {
        super.onBindViewHolder(viewHolder);
        RadioButton rb = (RadioButton) viewHolder.findViewById(R.id.radio_button);
        rb.setChecked(this.mIsChecked);
        this.mViewHolder = viewHolder;
    }

    @Override // androidx.preference.Preference
    public void onClick() {
        super.onClick();
        setChecked(true);
    }

    public void setChecked(boolean isChecked) {
        this.mIsChecked = isChecked;
        PreferenceViewHolder preferenceViewHolder = this.mViewHolder;
        if (preferenceViewHolder != null) {
            ((RadioButton) preferenceViewHolder.findViewById(R.id.radio_button)).setChecked(this.mIsChecked);
        }
    }
}
