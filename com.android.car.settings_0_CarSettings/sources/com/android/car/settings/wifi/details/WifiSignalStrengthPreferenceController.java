package com.android.car.settings.wifi.details;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;

/* JADX INFO: loaded from: classes5.dex */
public class WifiSignalStrengthPreferenceController extends WifiDetailsBasePreferenceController<WifiDetailsPreference> {
    private int mRssiSignalLevel;
    private String[] mSignalStr;

    public WifiSignalStrengthPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mRssiSignalLevel = -1;
        this.mSignalStr = context.getResources().getStringArray(R.array.wifi_signals);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<WifiDetailsPreference> getPreferenceType() {
        return WifiDetailsPreference.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(WifiDetailsPreference preference) {
        int level = getAccessPoint().getLevel();
        this.mRssiSignalLevel = level;
        preference.setDetailText(this.mSignalStr[level]);
    }
}
