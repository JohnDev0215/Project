package com.android.car.settings.applications.managedomainurls;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.UserHandle;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.settingslib.applications.ApplicationsState;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AppLaunchSettingsBasePreferenceController<V extends Preference> extends PreferenceController<V> {
    static final Intent sBrowserIntent = new Intent().setAction("android.intent.action.VIEW").addCategory("android.intent.category.BROWSABLE").setData(Uri.parse("http:"));
    private ApplicationsState.AppEntry mAppEntry;
    private final PackageManager mPm;

    public AppLaunchSettingsBasePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mPm = context.getPackageManager();
    }

    public void setAppEntry(ApplicationsState.AppEntry entry) {
        this.mAppEntry = entry;
    }

    public ApplicationsState.AppEntry getAppEntry() {
        return this.mAppEntry;
    }

    public String getPackageName() {
        return this.mAppEntry.info.packageName;
    }

    protected int getCurrentUserId() {
        return UserHandle.myUserId();
    }

    protected boolean isBrowserApp() {
        sBrowserIntent.setPackage(getPackageName());
        List<ResolveInfo> list = this.mPm.queryIntentActivitiesAsUser(sBrowserIntent, 131072, getCurrentUserId());
        for (ResolveInfo info : list) {
            if (info.activityInfo != null && info.handleAllWebDataURI) {
                return true;
            }
        }
        return false;
    }
}
