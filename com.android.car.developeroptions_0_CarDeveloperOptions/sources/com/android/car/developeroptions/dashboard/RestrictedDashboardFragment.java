package com.android.car.developeroptions.dashboard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.RestrictionsManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.enterprise.ActionDisabledByAdminDialogHelper;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;

/* JADX INFO: loaded from: classes.dex */
public abstract class RestrictedDashboardFragment extends DashboardFragment {
    private AlertDialog mActionDisabledDialog;
    private boolean mChallengeRequested;
    private boolean mChallengeSucceeded;
    private TextView mEmptyTextView;
    private RestrictedLockUtils.EnforcedAdmin mEnforcedAdmin;
    private boolean mIsAdminUser;
    private final String mRestrictionKey;
    private RestrictionsManager mRestrictionsManager;
    private UserManager mUserManager;
    private boolean mOnlyAvailableForAdmins = false;
    private BroadcastReceiver mScreenOffReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.dashboard.RestrictedDashboardFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (RestrictedDashboardFragment.this.mChallengeRequested) {
                return;
            }
            RestrictedDashboardFragment.this.mChallengeSucceeded = false;
            RestrictedDashboardFragment.this.mChallengeRequested = false;
        }
    };

    public RestrictedDashboardFragment(String str) {
        this.mRestrictionKey = str;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mRestrictionsManager = (RestrictionsManager) getSystemService("restrictions");
        UserManager userManager = (UserManager) getSystemService("user");
        this.mUserManager = userManager;
        this.mIsAdminUser = userManager.isAdminUser();
        if (bundle != null) {
            this.mChallengeSucceeded = bundle.getBoolean("chsc", false);
            this.mChallengeRequested = bundle.getBoolean("chrq", false);
        }
        IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        getActivity().registerReceiver(this.mScreenOffReceiver, intentFilter);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mEmptyTextView = initEmptyTextView();
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (getActivity().isChangingConfigurations()) {
            bundle.putBoolean("chrq", this.mChallengeRequested);
            bundle.putBoolean("chsc", this.mChallengeSucceeded);
        }
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (shouldBeProviderProtected(this.mRestrictionKey)) {
            ensurePin();
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        getActivity().unregisterReceiver(this.mScreenOffReceiver);
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 12309) {
            super.onActivityResult(i, i2, intent);
        } else if (i2 == -1) {
            this.mChallengeSucceeded = true;
            this.mChallengeRequested = false;
        } else {
            this.mChallengeSucceeded = false;
        }
    }

    private void ensurePin() {
        Intent intentCreateLocalApprovalIntent;
        if (this.mChallengeSucceeded || this.mChallengeRequested || !this.mRestrictionsManager.hasRestrictionsProvider() || (intentCreateLocalApprovalIntent = this.mRestrictionsManager.createLocalApprovalIntent()) == null) {
            return;
        }
        this.mChallengeRequested = true;
        this.mChallengeSucceeded = false;
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString("android.request.mesg", getResources().getString(R.string.restr_pin_enter_admin_pin));
        intentCreateLocalApprovalIntent.putExtra("android.content.extra.REQUEST_BUNDLE", persistableBundle);
        startActivityForResult(intentCreateLocalApprovalIntent, 12309);
    }

    protected boolean isRestrictedAndNotProviderProtected() {
        String str = this.mRestrictionKey;
        return (str == null || "restrict_if_overridable".equals(str) || !this.mUserManager.hasUserRestriction(this.mRestrictionKey) || this.mRestrictionsManager.hasRestrictionsProvider()) ? false : true;
    }

    protected boolean hasChallengeSucceeded() {
        return (this.mChallengeRequested && this.mChallengeSucceeded) || !this.mChallengeRequested;
    }

    protected boolean shouldBeProviderProtected(String str) {
        if (str == null) {
            return false;
        }
        return ("restrict_if_overridable".equals(str) || this.mUserManager.hasUserRestriction(this.mRestrictionKey)) && this.mRestrictionsManager.hasRestrictionsProvider();
    }

    protected TextView initEmptyTextView() {
        return (TextView) getActivity().findViewById(android.R.id.empty);
    }

    public RestrictedLockUtils.EnforcedAdmin getRestrictionEnforcedAdmin() {
        RestrictedLockUtils.EnforcedAdmin enforcedAdminCheckIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(getActivity(), this.mRestrictionKey, UserHandle.myUserId());
        this.mEnforcedAdmin = enforcedAdminCheckIfRestrictionEnforced;
        if (enforcedAdminCheckIfRestrictionEnforced != null && enforcedAdminCheckIfRestrictionEnforced.user == null) {
            enforcedAdminCheckIfRestrictionEnforced.user = UserHandle.of(UserHandle.myUserId());
        }
        return this.mEnforcedAdmin;
    }

    public TextView getEmptyTextView() {
        return this.mEmptyTextView;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment
    protected void onDataSetChanged() {
        AlertDialog alertDialog;
        highlightPreferenceIfNeeded();
        if (isUiRestrictedByOnlyAdmin() && ((alertDialog = this.mActionDisabledDialog) == null || !alertDialog.isShowing())) {
            AlertDialog.Builder builderPrepareDialogBuilder = new ActionDisabledByAdminDialogHelper(getActivity()).prepareDialogBuilder(this.mRestrictionKey, getRestrictionEnforcedAdmin());
            builderPrepareDialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.car.developeroptions.dashboard.-$$Lambda$RestrictedDashboardFragment$W7EVtePKv4BVoPZrRSEs6X-8Hto
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    this.f$0.lambda$onDataSetChanged$0$RestrictedDashboardFragment(dialogInterface);
                }
            });
            this.mActionDisabledDialog = builderPrepareDialogBuilder.show();
            setEmptyView(new View(getContext()));
        } else {
            TextView textView = this.mEmptyTextView;
            if (textView != null) {
                setEmptyView(textView);
            }
        }
        super.onDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onDataSetChanged$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onDataSetChanged$0$RestrictedDashboardFragment(DialogInterface dialogInterface) {
        getActivity().finish();
    }

    public void setIfOnlyAvailableForAdmins(boolean z) {
        this.mOnlyAvailableForAdmins = z;
    }

    protected boolean isUiRestricted() {
        return isRestrictedAndNotProviderProtected() || !hasChallengeSucceeded() || (!this.mIsAdminUser && this.mOnlyAvailableForAdmins);
    }

    protected boolean isUiRestrictedByOnlyAdmin() {
        return isUiRestricted() && !this.mUserManager.hasBaseUserRestriction(this.mRestrictionKey, UserHandle.of(UserHandle.myUserId())) && (this.mIsAdminUser || !this.mOnlyAvailableForAdmins);
    }
}
