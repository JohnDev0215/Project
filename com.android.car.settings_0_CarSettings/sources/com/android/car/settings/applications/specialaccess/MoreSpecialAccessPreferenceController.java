package com.android.car.settings.applications.specialaccess;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;

/* JADX INFO: loaded from: classes5.dex */
public class MoreSpecialAccessPreferenceController extends PreferenceController<Preference> {
    private final Intent mIntent;

    public MoreSpecialAccessPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        PackageManager packageManager = getContext().getPackageManager();
        String packageName = packageManager.getPermissionControllerPackageName();
        if (packageName != null) {
            Intent intent = new Intent("android.intent.action.MANAGE_SPECIAL_APP_ACCESSES").setPackage(packageName);
            ResolveInfo resolveInfo = packageManager.resolveActivity(intent, 65536);
            this.mIntent = resolveInfo != null ? intent : null;
            return;
        }
        this.mIntent = null;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected int getAvailabilityStatus() {
        return this.mIntent != null ? 0 : 2;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected boolean handlePreferenceClicked(Preference preference) {
        if (this.mIntent != null) {
            getContext().startActivity(this.mIntent);
            return true;
        }
        return false;
    }
}
