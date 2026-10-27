package com.android.car.developeroptions.wifi;

import android.content.Context;
import android.view.View;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.settingslib.wifi.AccessPoint;
import com.android.settingslib.wifi.AccessPointPreference;

/* JADX INFO: loaded from: classes.dex */
public class ConnectedAccessPointPreference extends AccessPointPreference implements View.OnClickListener {
    private boolean mIsCaptivePortal;
    private OnGearClickListener mOnGearClickListener;

    public interface OnGearClickListener {
        void onGearClick(ConnectedAccessPointPreference connectedAccessPointPreference);
    }

    @Override // com.android.settingslib.wifi.AccessPointPreference
    protected int getWidgetLayoutResourceId() {
        return R.layout.preference_widget_gear_optional_background;
    }

    public ConnectedAccessPointPreference(AccessPoint accessPoint, Context context, AccessPointPreference.UserBadgeCache userBadgeCache, int i, boolean z) {
        super(accessPoint, context, userBadgeCache, i, z);
    }

    @Override // com.android.settingslib.wifi.AccessPointPreference
    public void refresh() {
        super.refresh();
        setShowDivider(this.mIsCaptivePortal);
        if (this.mIsCaptivePortal) {
            setSummary(R.string.wifi_tap_to_sign_in);
        }
    }

    public void setOnGearClickListener(OnGearClickListener onGearClickListener) {
        this.mOnGearClickListener = onGearClickListener;
        notifyChanged();
    }

    @Override // com.android.settingslib.wifi.AccessPointPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View viewFindViewById = preferenceViewHolder.findViewById(R.id.settings_button);
        viewFindViewById.setOnClickListener(this);
        preferenceViewHolder.findViewById(R.id.settings_button_no_background).setVisibility(this.mIsCaptivePortal ? 4 : 0);
        viewFindViewById.setVisibility(this.mIsCaptivePortal ? 0 : 4);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        OnGearClickListener onGearClickListener;
        if (view.getId() != R.id.settings_button || (onGearClickListener = this.mOnGearClickListener) == null) {
            return;
        }
        onGearClickListener.onGearClick(this);
    }

    public void setCaptivePortal(boolean z) {
        if (this.mIsCaptivePortal != z) {
            this.mIsCaptivePortal = z;
            refresh();
        }
    }
}
