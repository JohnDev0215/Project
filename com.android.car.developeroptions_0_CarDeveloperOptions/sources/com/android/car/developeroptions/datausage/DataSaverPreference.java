package com.android.car.developeroptions.datausage;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.datausage.DataSaverBackend;

/* JADX INFO: loaded from: classes.dex */
public class DataSaverPreference extends Preference implements DataSaverBackend.Listener {
    private final DataSaverBackend mDataSaverBackend;

    @Override // com.android.car.developeroptions.datausage.DataSaverBackend.Listener
    public void onBlacklistStatusChanged(int i, boolean z) {
    }

    @Override // com.android.car.developeroptions.datausage.DataSaverBackend.Listener
    public void onWhitelistStatusChanged(int i, boolean z) {
    }

    public DataSaverPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDataSaverBackend = new DataSaverBackend(context);
    }

    @Override // androidx.preference.Preference
    public void onAttached() {
        super.onAttached();
        this.mDataSaverBackend.addListener(this);
    }

    @Override // androidx.preference.Preference
    public void onDetached() {
        super.onDetached();
        this.mDataSaverBackend.remListener(this);
    }

    @Override // com.android.car.developeroptions.datausage.DataSaverBackend.Listener
    public void onDataSaverChanged(boolean z) {
        setSummary(z ? R.string.data_saver_on : R.string.data_saver_off);
    }
}
