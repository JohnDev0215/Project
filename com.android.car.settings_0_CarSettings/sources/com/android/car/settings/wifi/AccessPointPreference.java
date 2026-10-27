package com.android.car.settings.wifi;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import androidx.preference.PreferenceViewHolder;
import com.android.car.settings.common.Logger;
import com.android.settingslib.R;
import com.android.settingslib.wifi.AccessPoint;

/* JADX INFO: loaded from: classes5.dex */
public class AccessPointPreference extends ButtonPasswordEditTextPreference {
    private final AccessPoint mAccessPoint;
    private final StateListDrawable mWifiSld;
    private static final Logger LOG = new Logger((Class<?>) AccessPointPreference.class);
    private static final int[] STATE_SECURED = {R.attr.state_encrypted};
    private static final int[] STATE_NONE = new int[0];
    private static int[] sWifiSignalAttributes = {R.attr.wifi_signal};

    public AccessPointPreference(Context context, AccessPoint accessPoint) {
        super(context);
        this.mWifiSld = (StateListDrawable) context.getTheme().obtainStyledAttributes(sWifiSignalAttributes).getDrawable(0);
        this.mAccessPoint = accessPoint;
        LOG.d("creating preference for ap: " + this.mAccessPoint);
        setIcon(getAccessPointIcon());
    }

    public AccessPoint getAccessPoint() {
        return this.mAccessPoint;
    }

    @Override // com.android.car.settings.wifi.ButtonPasswordEditTextPreference, com.android.car.settings.common.ValidatedEditTextPreference, com.android.car.ui.preference.CarUiEditTextPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        setIcon(getAccessPointIcon());
    }

    @Override // androidx.preference.DialogPreference, androidx.preference.Preference
    protected void onClick() {
        if (shouldShowPasswordDialog()) {
            super.onClick();
        }
    }

    private boolean shouldShowPasswordDialog() {
        return !WifiUtil.isOpenNetwork(this.mAccessPoint.getSecurity()) && (!this.mAccessPoint.isSaved() || WifiUtil.isAccessPointDisabledByWrongPassword(this.mAccessPoint));
    }

    private Drawable getAccessPointIcon() {
        int[] iArr;
        StateListDrawable stateListDrawable = this.mWifiSld;
        if (stateListDrawable == null) {
            LOG.w("wifiSld is null.");
            return null;
        }
        if (WifiUtil.isOpenNetwork(this.mAccessPoint.getSecurity())) {
            iArr = STATE_NONE;
        } else {
            iArr = STATE_SECURED;
        }
        stateListDrawable.setState(iArr);
        Drawable drawable = this.mWifiSld.getCurrent();
        drawable.setLevel(this.mAccessPoint.getLevel());
        return drawable;
    }
}
