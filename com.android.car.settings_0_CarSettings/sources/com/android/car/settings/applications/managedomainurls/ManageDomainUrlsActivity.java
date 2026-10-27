package com.android.car.settings.applications.managedomainurls;

import androidx.fragment.app.Fragment;
import com.android.car.settings.common.BaseCarSettingsActivity;

/* JADX INFO: loaded from: classes5.dex */
public class ManageDomainUrlsActivity extends BaseCarSettingsActivity {
    @Override // com.android.car.settings.common.BaseCarSettingsActivity
    protected Fragment getInitialFragment() {
        return new ManageDomainUrlsFragment();
    }
}
