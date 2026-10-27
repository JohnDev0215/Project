package com.android.car.settings.system.legal;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.Intent;
import com.android.car.settings.common.FragmentController;

/* JADX INFO: loaded from: classes5.dex */
public class ThirdPartyLicensePreferenceController extends LegalPreferenceController {
    private static final Intent INTENT = new Intent("android.settings.THIRD_PARTY_LICENSE");

    public ThirdPartyLicensePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.system.legal.LegalPreferenceController
    protected Intent getIntent() {
        return INTENT;
    }
}
