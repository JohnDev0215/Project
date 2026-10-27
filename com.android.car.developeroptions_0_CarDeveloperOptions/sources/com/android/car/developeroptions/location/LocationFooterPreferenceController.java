package com.android.car.developeroptions.location;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.settingslib.widget.FooterPreference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LocationFooterPreferenceController extends LocationBasePreferenceController {
    private static final Intent INJECT_INTENT = new Intent("com.android.settings.location.DISPLAYED_FOOTER");
    private final PackageManager mPackageManager;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "location_footer";
    }

    @Override // com.android.car.developeroptions.location.LocationEnabler.LocationModeChangeListener
    public void onLocationModeChanged(int i, boolean z) {
    }

    public LocationFooterPreferenceController(Context context) {
        super(context, null);
        this.mPackageManager = this.mContext.getPackageManager();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        PreferenceCategory preferenceCategory = (PreferenceCategory) preference;
        preferenceCategory.removeAll();
        for (FooterData footerData : getFooterData()) {
            try {
                String string = this.mPackageManager.getResourcesForApplication(footerData.applicationInfo).getString(footerData.footerStringRes);
                FooterPreference footerPreference = new FooterPreference(preference.getContext());
                footerPreference.setTitle(string);
                preferenceCategory.addPreference(footerPreference);
            } catch (PackageManager.NameNotFoundException unused) {
                Log.w("LocationFooter", "Resources not found for application " + footerData.applicationInfo.packageName);
            }
        }
    }

    @Override // com.android.car.developeroptions.location.LocationBasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return !getFooterData().isEmpty();
    }

    private List<FooterData> getFooterData() {
        List<ResolveInfo> listQueryBroadcastReceivers = this.mPackageManager.queryBroadcastReceivers(INJECT_INTENT, 128);
        if (listQueryBroadcastReceivers == null) {
            Log.e("LocationFooter", "Unable to resolve intent " + INJECT_INTENT);
            return Collections.emptyList();
        }
        if (Log.isLoggable("LocationFooter", 3)) {
            Log.d("LocationFooter", "Found broadcast receivers: " + listQueryBroadcastReceivers);
        }
        ArrayList arrayList = new ArrayList(listQueryBroadcastReceivers.size());
        for (ResolveInfo resolveInfo : listQueryBroadcastReceivers) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            ApplicationInfo applicationInfo = activityInfo.applicationInfo;
            if ((applicationInfo.flags & 1) == 0) {
                Log.w("LocationFooter", "Ignoring attempt to inject footer from app not in system image: " + resolveInfo);
            } else {
                Bundle bundle = activityInfo.metaData;
                if (bundle == null) {
                    if (Log.isLoggable("LocationFooter", 3)) {
                        Log.d("LocationFooter", "No METADATA in broadcast receiver " + activityInfo.name);
                    }
                } else {
                    int i = bundle.getInt("com.android.settings.location.FOOTER_STRING");
                    if (i == 0) {
                        Log.w("LocationFooter", "No mapping of integer exists for com.android.settings.location.FOOTER_STRING");
                    } else {
                        arrayList.add(new FooterData(i, applicationInfo));
                    }
                }
            }
        }
        return arrayList;
    }

    private static class FooterData {
        final ApplicationInfo applicationInfo;
        final int footerStringRes;

        FooterData(int i, ApplicationInfo applicationInfo) {
            this.footerStringRes = i;
            this.applicationInfo = applicationInfo;
        }
    }
}
