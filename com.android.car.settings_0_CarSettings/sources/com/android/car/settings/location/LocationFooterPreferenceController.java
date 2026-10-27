package com.android.car.settings.location;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import com.android.car.ui.preference.CarUiPreference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class LocationFooterPreferenceController extends PreferenceController<PreferenceGroup> {
    private PackageManager mPackageManager;
    private static final Logger LOG = new Logger((Class<?>) LocationFooterPreferenceController.class);
    private static final Intent INJECT_INTENT = new Intent("com.android.settings.location.DISPLAYED_FOOTER");

    public LocationFooterPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mPackageManager = context.getPackageManager();
    }

    void setPackageManager(PackageManager packageManager) {
        this.mPackageManager = packageManager;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        for (LocationFooter footer : getInjectedLocationFooters()) {
            try {
                String footerString = this.mPackageManager.getResourcesForApplication(footer.mApplicationInfo).getString(footer.mFooterStringRes);
                CarUiPreference newPreference = new CarUiPreference(getContext());
                newPreference.setSummary(footerString);
                newPreference.setIcon(R.drawable.ic_settings_about);
                newPreference.setSelectable(false);
                getPreference().addPreference(newPreference);
            } catch (PackageManager.NameNotFoundException e) {
                LOG.w("Resources not found for application " + footer.mApplicationInfo.packageName);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preferenceGroup) {
        preferenceGroup.setVisible(preferenceGroup.getPreferenceCount() > 0);
    }

    private List<LocationFooter> getInjectedLocationFooters() {
        List<ResolveInfo> resolveInfos = this.mPackageManager.queryBroadcastReceivers(INJECT_INTENT, 128);
        if (resolveInfos == null) {
            LOG.e("Unable to resolve intent " + INJECT_INTENT);
            return Collections.emptyList();
        }
        LOG.d("Found broadcast receivers: " + resolveInfos);
        List<LocationFooter> locationFooters = new ArrayList<>(resolveInfos.size());
        for (ResolveInfo resolveInfo : resolveInfos) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            ApplicationInfo appInfo = activityInfo.applicationInfo;
            if ((appInfo.flags & 1) == 0) {
                LOG.w("Ignoring attempt to inject footer from a non-system app: " + resolveInfo);
            } else if (activityInfo.metaData == null) {
                LOG.d("No METADATA in broadcast receiver " + activityInfo.name);
            } else {
                int footerTextRes = activityInfo.metaData.getInt("com.android.settings.location.FOOTER_STRING");
                if (footerTextRes == 0) {
                    LOG.w("No mapping of integer exists for com.android.settings.location.FOOTER_STRING");
                } else {
                    locationFooters.add(new LocationFooter(footerTextRes, appInfo));
                }
            }
        }
        return locationFooters;
    }

    private static class LocationFooter {
        private final ApplicationInfo mApplicationInfo;
        private final int mFooterStringRes;

        LocationFooter(int footerRes, ApplicationInfo appInfo) {
            this.mFooterStringRes = footerRes;
            this.mApplicationInfo = appInfo;
        }
    }
}
