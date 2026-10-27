package com.android.permissioncontroller.role.ui.handheld;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.preference.PreferenceViewHolder;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.role.ui.TwoTargetPreference;

/* JADX INFO: loaded from: classes8.dex */
class SettingsButtonPreference extends HandHeldTwoTargetPreference {
    private TwoTargetPreference.OnSecondTargetClickListener mOnSecondTargetClickListener;

    SettingsButtonPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    SettingsButtonPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    SettingsButtonPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    SettingsButtonPreference(Context context) {
        super(context);
        init();
    }

    private void init() {
        setWidgetLayoutResource(R.layout.settings_button_preference_widget);
    }

    @Override // com.android.permissioncontroller.role.ui.TwoTargetPreference
    public void setOnSecondTargetClickListener(TwoTargetPreference.OnSecondTargetClickListener listener) {
        this.mOnSecondTargetClickListener = listener;
        notifyChanged();
    }

    @Override // com.android.permissioncontroller.role.ui.handheld.HandHeldTwoTargetPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        View widgetFrame = holder.findViewById(android.R.id.widget_frame);
        widgetFrame.setPadding(0, 0, 0, 0);
        View settingsButton = holder.findViewById(R.id.settings_button);
        if (this.mOnSecondTargetClickListener != null) {
            widgetFrame.setVisibility(0);
            settingsButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.permissioncontroller.role.ui.handheld.-$$Lambda$SettingsButtonPreference$UwT4I-SBsW3FPxpzrhNAjRMjBws
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onBindViewHolder$0$SettingsButtonPreference(view);
                }
            });
        } else {
            widgetFrame.setVisibility(8);
            settingsButton.setOnClickListener(null);
        }
        settingsButton.setEnabled(true);
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$SettingsButtonPreference(View view) {
        this.mOnSecondTargetClickListener.onSecondTargetClick(this);
    }
}
