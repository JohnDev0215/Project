package com.android.car.developeroptions.applications.manageapplications;

import android.R;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.settingslib.applications.ApplicationsState;

/* JADX INFO: loaded from: classes.dex */
public class ApplicationViewHolder extends RecyclerView.ViewHolder {
    private final ImageView mAppIcon;
    private final TextView mAppName;
    final TextView mDisabled;
    final TextView mSummary;
    final Switch mSwitch;
    final ViewGroup mWidgetContainer;

    ApplicationViewHolder(View view) {
        super(view);
        this.mAppName = (TextView) view.findViewById(R.id.title);
        this.mAppIcon = (ImageView) view.findViewById(R.id.icon);
        this.mSummary = (TextView) view.findViewById(R.id.summary);
        this.mDisabled = (TextView) view.findViewById(com.android.car.developeroptions.R.id.appendix);
        this.mSwitch = (Switch) view.findViewById(com.android.car.developeroptions.R.id.switchWidget);
        this.mWidgetContainer = (ViewGroup) view.findViewById(R.id.widget_frame);
    }

    static View newView(ViewGroup viewGroup, boolean z) {
        ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(com.android.car.developeroptions.R.layout.preference_app, viewGroup, false);
        ViewGroup viewGroup3 = (ViewGroup) viewGroup2.findViewById(R.id.widget_frame);
        if (z) {
            if (viewGroup3 != null) {
                LayoutInflater.from(viewGroup.getContext()).inflate(com.android.car.developeroptions.R.layout.preference_widget_master_switch, viewGroup3, true);
                viewGroup2.addView(LayoutInflater.from(viewGroup.getContext()).inflate(com.android.car.developeroptions.R.layout.preference_two_target_divider, viewGroup2, false), viewGroup2.getChildCount() - 1);
            }
        } else if (viewGroup3 != null) {
            viewGroup3.setVisibility(8);
        }
        return viewGroup2;
    }

    void setSummary(CharSequence charSequence) {
        this.mSummary.setText(charSequence);
    }

    void setSummary(int i) {
        this.mSummary.setText(i);
    }

    void setEnabled(boolean z) {
        this.itemView.setEnabled(z);
    }

    void setTitle(CharSequence charSequence) {
        if (charSequence == null) {
            return;
        }
        this.mAppName.setText(charSequence);
    }

    void setIcon(int i) {
        this.mAppIcon.setImageResource(i);
    }

    void setIcon(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        this.mAppIcon.setImageDrawable(drawable);
    }

    void updateDisableView(ApplicationInfo applicationInfo) {
        if ((applicationInfo.flags & 8388608) == 0) {
            this.mDisabled.setVisibility(0);
            this.mDisabled.setText(com.android.car.developeroptions.R.string.not_installed);
        } else if (!applicationInfo.enabled || applicationInfo.enabledSetting == 4) {
            this.mDisabled.setVisibility(0);
            this.mDisabled.setText(com.android.car.developeroptions.R.string.disabled);
        } else {
            this.mDisabled.setVisibility(8);
        }
    }

    void updateSizeText(ApplicationsState.AppEntry appEntry, CharSequence charSequence, int i) {
        Log.d("ManageApplications", "updateSizeText of " + appEntry.label + " " + appEntry + ": " + appEntry.sizeStr);
        String str = appEntry.sizeStr;
        if (str == null) {
            if (appEntry.size == -2) {
                setSummary(charSequence);
            }
        } else if (i == 1) {
            setSummary(appEntry.internalSizeStr);
        } else if (i == 2) {
            setSummary(appEntry.externalSizeStr);
        } else {
            setSummary(str);
        }
    }

    void updateSwitch(View.OnClickListener onClickListener, boolean z, boolean z2) {
        ViewGroup viewGroup;
        if (this.mSwitch == null || (viewGroup = this.mWidgetContainer) == null) {
            return;
        }
        viewGroup.setOnClickListener(onClickListener);
        this.mSwitch.setChecked(z2);
        this.mSwitch.setEnabled(z);
    }
}
