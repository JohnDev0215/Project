package com.android.car.settings.system;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;

/* JADX INFO: loaded from: classes5.dex */
public class RegulatoryInfoPreferenceController extends PreferenceController<Preference> {
    private static final Intent INTENT_PROBE = new Intent("android.settings.SHOW_REGULATORY_INFO");
    private PackageManager mPm;

    public RegulatoryInfoPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mPm = context.getPackageManager();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected int getAvailabilityStatus() {
        return this.mPm.queryIntentActivities(INTENT_PROBE, 0).isEmpty() ? 2 : 0;
    }

    void setPackageManager(PackageManager pm) {
        this.mPm = pm;
    }
}
