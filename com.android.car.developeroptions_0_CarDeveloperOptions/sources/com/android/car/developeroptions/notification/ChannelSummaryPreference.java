package com.android.car.developeroptions.notification;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.settingslib.TwoTargetPreference;

/* JADX INFO: loaded from: classes.dex */
public class ChannelSummaryPreference extends TwoTargetPreference {
    private CheckBox mCheckBox;
    private boolean mChecked;
    private Context mContext;
    private boolean mEnableCheckBox;
    private Intent mIntent;
    private View.OnClickListener mOnCheckBoxClickListener;

    public ChannelSummaryPreference(Context context) {
        super(context);
        this.mEnableCheckBox = true;
        this.mOnCheckBoxClickListener = new View.OnClickListener() { // from class: com.android.car.developeroptions.notification.ChannelSummaryPreference.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChannelSummaryPreference.this.mCheckBox == null || ChannelSummaryPreference.this.mCheckBox.isEnabled()) {
                    ChannelSummaryPreference.this.setChecked(!r2.mChecked);
                    ChannelSummaryPreference channelSummaryPreference = ChannelSummaryPreference.this;
                    if (!channelSummaryPreference.callChangeListener(Boolean.valueOf(channelSummaryPreference.mChecked))) {
                        ChannelSummaryPreference.this.setChecked(!r1.mChecked);
                    } else {
                        ChannelSummaryPreference channelSummaryPreference2 = ChannelSummaryPreference.this;
                        channelSummaryPreference2.persistBoolean(channelSummaryPreference2.mChecked);
                    }
                }
            }
        };
        setLayoutResource(R.layout.preference_checkable_two_target);
        this.mContext = context;
        setWidgetLayoutResource(R.layout.zen_rule_widget);
    }

    @Override // com.android.settingslib.TwoTargetPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View viewFindViewById = preferenceViewHolder.findViewById(android.R.id.widget_frame);
        View viewFindViewById2 = preferenceViewHolder.findViewById(R.id.two_target_divider);
        if (this.mIntent != null) {
            viewFindViewById2.setVisibility(0);
            viewFindViewById.setVisibility(0);
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.notification.-$$Lambda$ChannelSummaryPreference$iP1pcqXZaBTUnvhbVmy8J7ppz6Q
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onBindViewHolder$0$ChannelSummaryPreference(view);
                }
            });
        } else {
            viewFindViewById2.setVisibility(8);
            viewFindViewById.setVisibility(8);
            viewFindViewById.setOnClickListener(null);
        }
        View viewFindViewById3 = preferenceViewHolder.findViewById(R.id.checkbox_container);
        if (viewFindViewById3 != null) {
            viewFindViewById3.setOnClickListener(this.mOnCheckBoxClickListener);
        }
        CheckBox checkBox = (CheckBox) preferenceViewHolder.findViewById(android.R.id.checkbox);
        this.mCheckBox = checkBox;
        if (checkBox != null) {
            checkBox.setChecked(this.mChecked);
            this.mCheckBox.setEnabled(this.mEnableCheckBox);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onBindViewHolder$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onBindViewHolder$0$ChannelSummaryPreference(View view) {
        this.mContext.startActivity(this.mIntent);
    }

    @Override // androidx.preference.Preference
    public void setIntent(Intent intent) {
        this.mIntent = intent;
    }

    @Override // androidx.preference.Preference
    public void onClick() {
        this.mOnCheckBoxClickListener.onClick(null);
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
