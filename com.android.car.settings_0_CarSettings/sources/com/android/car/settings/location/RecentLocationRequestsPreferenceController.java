package com.android.car.settings.location;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.R;
import com.android.car.settings.applications.ApplicationDetailsFragment;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.ui.preference.CarUiPreference;
import com.android.settingslib.location.RecentLocationApps;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class RecentLocationRequestsPreferenceController extends PreferenceController<PreferenceGroup> {
    private final PackageManager mPackageManager;
    private RecentLocationApps mRecentLocationApps;
    private List<RecentLocationApps.Request> mRecentLocationRequests;

    public RecentLocationRequestsPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mRecentLocationApps = new RecentLocationApps(context);
        this.mPackageManager = context.getPackageManager();
    }

    void setRecentLocationApps(RecentLocationApps apps) {
        this.mRecentLocationApps = apps;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup group) {
        if (this.mRecentLocationRequests == null) {
            this.mRecentLocationRequests = this.mRecentLocationApps.getAppListSorted(true);
        } else {
            List<RecentLocationApps.Request> newRequests = this.mRecentLocationApps.getAppListSorted(true);
            if (listsEqual(newRequests, this.mRecentLocationRequests)) {
                return;
            } else {
                this.mRecentLocationRequests = newRequests;
            }
        }
        if (this.mRecentLocationRequests.isEmpty()) {
            CarUiPreference emptyMessagePref = new CarUiPreference(getContext());
            emptyMessagePref.setTitle(R.string.location_settings_recent_requests_empty_message);
            group.addPreference(emptyMessagePref);
        } else {
            group.removeAll();
            for (RecentLocationApps.Request request : this.mRecentLocationRequests) {
                Preference appPref = createPreference(request);
                group.addPreference(appPref);
            }
        }
    }

    private Preference createPreference(final RecentLocationApps.Request request) {
        CarUiPreference pref = new CarUiPreference(getContext());
        pref.setSummary(request.contentDescription);
        pref.setIcon(request.icon);
        pref.setTitle(request.label);
        if (isPackageInstalled(request.packageName)) {
            pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.settings.location.-$$Lambda$RecentLocationRequestsPreferenceController$Kr2Uxmtb99KiXmzRe0JRl0KINC8
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return this.f$0.lambda$createPreference$0$RecentLocationRequestsPreferenceController(request, preference);
                }
            });
        }
        return pref;
    }

    public /* synthetic */ boolean lambda$createPreference$0$RecentLocationRequestsPreferenceController(RecentLocationApps.Request request, Preference p) {
        getFragmentController().launchFragment(ApplicationDetailsFragment.getInstance(request.packageName));
        return true;
    }

    private boolean isPackageInstalled(String packageName) {
        try {
            this.mPackageManager.getPackageInfo(packageName, 128);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private boolean listsEqual(List<RecentLocationApps.Request> a, List<RecentLocationApps.Request> b) {
        if (a.size() != b.size()) {
            return false;
        }
        for (int i = 0; i < a.size(); i++) {
            if (!a.get(i).packageName.equals(b.get(i).packageName)) {
                return false;
            }
        }
        return true;
    }
}
