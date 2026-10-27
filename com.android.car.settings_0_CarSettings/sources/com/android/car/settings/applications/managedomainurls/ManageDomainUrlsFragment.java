package com.android.car.settings.applications.managedomainurls;

import android.content.Context;
import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;

/* JADX INFO: loaded from: classes5.dex */
public class ManageDomainUrlsFragment extends SettingsFragment {
    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.manage_domain_urls_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        ((DomainAppPreferenceController) use(DomainAppPreferenceController.class, R.string.pk_opening_links_options)).setLifecycle(getLifecycle());
    }
}
