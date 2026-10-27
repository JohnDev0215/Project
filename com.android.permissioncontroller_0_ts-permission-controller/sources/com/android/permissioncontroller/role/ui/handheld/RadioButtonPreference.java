package com.android.permissioncontroller.role.ui.handheld;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.TwoStatePreference;
import com.android.permissioncontroller.R;

/* JADX INFO: loaded from: classes8.dex */
class RadioButtonPreference extends TwoStatePreference {
    private final OnCheckedChangeListener mOnCheckedChangeListener;

    RadioButtonPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mOnCheckedChangeListener = new OnCheckedChangeListener();
        setWidgetLayoutResource(R.layout.radio_button_preference_widget);
    }

    RadioButtonPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    RadioButtonPreference(Context context, AttributeSet attrs) {
        this(context, attrs, TypedArrayUtils.getAttr(context, R.attr.preferenceStyle, android.R.attr.preferenceStyle));
    }

    RadioButtonPreference(Context context) {
        this(context, null);
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        ViewGroup itemView = (ViewGroup) holder.itemView;
        View widgetFrame = holder.findViewById(android.R.id.widget_frame);
        if (itemView.indexOfChild(widgetFrame) != 0) {
            widgetFrame.setPaddingRelative(widgetFrame.getPaddingEnd(), widgetFrame.getPaddingTop(), widgetFrame.getPaddingStart(), widgetFrame.getPaddingBottom());
            itemView.removeView(widgetFrame);
            itemView.addView(widgetFrame, 0);
            itemView.setPaddingRelative(0, itemView.getPaddingTop(), itemView.getPaddingEnd(), itemView.getPaddingBottom());
        }
        RadioButton radioButton = (RadioButton) holder.findViewById(R.id.radio_button);
        radioButton.setOnCheckedChangeListener(null);
        radioButton.setChecked(this.mChecked);
        radioButton.setOnCheckedChangeListener(this.mOnCheckedChangeListener);
    }

    private class OnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        OnCheckedChangeListener() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (!RadioButtonPreference.this.callChangeListener(Boolean.valueOf(isChecked))) {
                buttonView.setChecked(!isChecked);
            } else {
                RadioButtonPreference.this.setChecked(isChecked);
            }
        }
    }
}
