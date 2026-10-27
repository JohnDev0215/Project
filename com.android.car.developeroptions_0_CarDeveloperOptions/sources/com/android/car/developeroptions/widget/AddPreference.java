package com.android.car.developeroptions.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.settingslib.RestrictedPreference;

/* JADX INFO: loaded from: classes.dex */
public class AddPreference extends RestrictedPreference implements View.OnClickListener {
    private View mAddWidget;
    private OnAddClickListener mListener;
    private View mWidgetFrame;

    public interface OnAddClickListener {
        void onAddClick(AddPreference addPreference);
    }

    int getAddWidgetResId() {
        return R.id.add_preference_widget;
    }

    @Override // com.android.settingslib.RestrictedPreference, com.android.settingslib.TwoTargetPreference
    protected int getSecondTargetResId() {
        return R.layout.preference_widget_add;
    }

    public AddPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setOnAddClickListener(OnAddClickListener onAddClickListener) {
        this.mListener = onAddClickListener;
        View view = this.mWidgetFrame;
        if (view != null) {
            view.setVisibility(shouldHideSecondTarget() ? 8 : 0);
        }
    }

    public void setAddWidgetEnabled(boolean z) {
        View view = this.mAddWidget;
        if (view != null) {
            view.setEnabled(z);
        }
    }

    @Override // com.android.settingslib.RestrictedPreference, com.android.settingslib.TwoTargetPreference
    protected boolean shouldHideSecondTarget() {
        return this.mListener == null;
    }

    @Override // com.android.settingslib.RestrictedPreference, com.android.settingslib.TwoTargetPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        this.mWidgetFrame = preferenceViewHolder.findViewById(android.R.id.widget_frame);
        View viewFindViewById = preferenceViewHolder.findViewById(getAddWidgetResId());
        this.mAddWidget = viewFindViewById;
        viewFindViewById.setEnabled(true);
        this.mAddWidget.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        OnAddClickListener onAddClickListener;
        if (view.getId() != getAddWidgetResId() || (onAddClickListener = this.mListener) == null) {
            return;
        }
        onAddClickListener.onAddClick(this);
    }
}
