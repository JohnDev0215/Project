package com.android.car.settings.system.legal;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class LegalPreferenceController extends PreferenceController<Preference> {
    private final PackageManager mPackageManager;
    private ResolveInfo mResolveInfo;

    protected abstract Intent getIntent();

    public LegalPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mPackageManager = context.getPackageManager();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    public int getAvailabilityStatus() {
        ResolveInfo resolveInfoFindMatchingSpecificActivity = findMatchingSpecificActivity();
        this.mResolveInfo = resolveInfoFindMatchingSpecificActivity;
        return resolveInfoFindMatchingSpecificActivity != null ? 0 : 2;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        if (this.mResolveInfo == null) {
            return;
        }
        preference.setIntent(new Intent().setClassName(this.mResolveInfo.activityInfo.packageName, this.mResolveInfo.activityInfo.name));
        preference.setTitle(this.mResolveInfo.loadLabel(this.mPackageManager));
    }

    private ResolveInfo findMatchingSpecificActivity() {
        List<ResolveInfo> list;
        Intent intent = getIntent();
        if (intent == null || (list = this.mPackageManager.queryIntentActivities(intent, 0)) == null) {
            return null;
        }
        for (ResolveInfo resolveInfo : list) {
            if ((resolveInfo.activityInfo.applicationInfo.flags & 1) != 0) {
                return resolveInfo;
            }
        }
        return null;
    }
}
