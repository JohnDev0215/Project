package com.android.car.settings.applications;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.settingslib.applications.ApplicationsState;

/* JADX INFO: loaded from: classes5.dex */
public class ApplicationPreferenceController extends PreferenceController<Preference> {
    private ApplicationsState.AppEntry mAppEntry;
    private ApplicationsState mApplicationsState;

    public ApplicationPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    public ApplicationPreferenceController setAppEntry(ApplicationsState.AppEntry appEntry) {
        this.mAppEntry = appEntry;
        return this;
    }

    public ApplicationPreferenceController setAppState(ApplicationsState applicationsState) {
        this.mApplicationsState = applicationsState;
        return this;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void checkInitialized() {
        if (this.mAppEntry == null || this.mApplicationsState == null) {
            throw new IllegalStateException("AppEntry and AppState should be set before calling this function");
        }
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        preference.setTitle(getAppName());
        preference.setIcon(getAppIcon());
    }

    protected String getAppName() {
        this.mAppEntry.ensureLabel(getContext());
        return this.mAppEntry.label;
    }

    protected Drawable getAppIcon() {
        this.mApplicationsState.ensureIcon(this.mAppEntry);
        return this.mAppEntry.icon;
    }

    protected String getAppVersion() {
        return this.mAppEntry.getVersion(getContext());
    }
}
