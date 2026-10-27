package com.android.car.developeroptions.applications;

import android.os.Bundle;
import android.util.IconDrawableFactory;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.widget.EntityHeaderController;
import com.android.settingslib.applications.AppUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class AppInfoWithHeader extends AppInfoBase {
    private boolean mCreated;

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mCreated) {
            Log.w("AppInfoWithHeader", "onActivityCreated: ignoring duplicate call");
            return;
        }
        this.mCreated = true;
        if (this.mPackageInfo == null) {
            return;
        }
        FragmentActivity activity = getActivity();
        EntityHeaderController entityHeaderControllerNewInstance = EntityHeaderController.newInstance(activity, this, null);
        entityHeaderControllerNewInstance.setRecyclerView(getListView(), getSettingsLifecycle());
        entityHeaderControllerNewInstance.setIcon(IconDrawableFactory.newInstance(getContext()).getBadgedIcon(this.mPackageInfo.applicationInfo));
        entityHeaderControllerNewInstance.setLabel(this.mPackageInfo.applicationInfo.loadLabel(this.mPm));
        entityHeaderControllerNewInstance.setSummary(this.mPackageInfo);
        entityHeaderControllerNewInstance.setIsInstantApp(AppUtils.isInstant(this.mPackageInfo.applicationInfo));
        entityHeaderControllerNewInstance.setPackageName(this.mPackageName);
        entityHeaderControllerNewInstance.setUid(this.mPackageInfo.applicationInfo.uid);
        entityHeaderControllerNewInstance.setHasAppInfoLink(true);
        entityHeaderControllerNewInstance.setButtonActions(0, 0);
        getPreferenceScreen().addPreference(entityHeaderControllerNewInstance.done(activity, getPrefContext()));
    }
}
