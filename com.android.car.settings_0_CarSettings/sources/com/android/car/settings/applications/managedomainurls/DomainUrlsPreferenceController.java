package com.android.car.settings.applications.managedomainurls;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.util.ArraySet;
import androidx.preference.Preference;
import com.android.car.settings.R;
import com.android.car.settings.common.ConfirmationDialogFragment;
import com.android.car.settings.common.FragmentController;

/* JADX INFO: loaded from: classes5.dex */
public class DomainUrlsPreferenceController extends AppLaunchSettingsBasePreferenceController<Preference> {
    private ArraySet<String> mDomains;

    public DomainUrlsPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        this.mDomains = DomainUrlsUtils.getHandledDomains(getContext().getPackageManager(), getPackageName());
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        preference.setEnabled(!isBrowserApp());
        preference.setSummary(DomainUrlsUtils.getDomainsSummary(getContext(), getPackageName(), getCurrentUserId(), this.mDomains));
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected boolean handlePreferenceClicked(Preference preference) {
        String newLines = System.lineSeparator() + System.lineSeparator();
        String message = String.join(newLines, this.mDomains);
        ConfirmationDialogFragment dialogFragment = new ConfirmationDialogFragment.Builder(getContext()).setTitle(R.string.app_launch_supported_domain_urls_title).setMessage(message).setNegativeButton(android.R.string.cancel, (ConfirmationDialogFragment.RejectListener) null).build();
        getFragmentController().showDialog(dialogFragment, ConfirmationDialogFragment.TAG);
        return true;
    }
}
