package com.android.car.developeroptions.deviceinfo;

import android.accounts.Account;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Process;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.accounts.AccountDetailDashboardFragment;
import com.android.car.developeroptions.accounts.AccountFeatureProvider;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class BrandedAccountPreferenceController extends BasePreferenceController {
    private final Account[] mAccounts;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public BrandedAccountPreferenceController(Context context, String str) {
        super(context, str);
        this.mAccounts = FeatureFactory.getFactory(this.mContext).getAccountFeatureProvider().getAccounts(this.mContext);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        if (!this.mContext.getResources().getBoolean(R.bool.config_show_branded_account_in_device_info)) {
            return 3;
        }
        Account[] accountArr = this.mAccounts;
        return (accountArr == null || accountArr.length <= 0) ? 4 : 0;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        Account[] accountArr;
        super.displayPreference(preferenceScreen);
        final AccountFeatureProvider accountFeatureProvider = FeatureFactory.getFactory(this.mContext).getAccountFeatureProvider();
        Preference preferenceFindPreference = preferenceScreen.findPreference(getPreferenceKey());
        if (preferenceFindPreference != null && ((accountArr = this.mAccounts) == null || accountArr.length == 0)) {
            preferenceScreen.removePreference(preferenceFindPreference);
        } else {
            preferenceFindPreference.setSummary(this.mAccounts[0].name);
            preferenceFindPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.deviceinfo.-$$Lambda$BrandedAccountPreferenceController$6Gx2XX4zQQAOD4gqjcwSXX1mQlI
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return this.f$0.lambda$displayPreference$0$BrandedAccountPreferenceController(accountFeatureProvider, preference);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$displayPreference$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$displayPreference$0$BrandedAccountPreferenceController(AccountFeatureProvider accountFeatureProvider, Preference preference) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("account", this.mAccounts[0]);
        bundle.putParcelable("user_handle", Process.myUserHandle());
        bundle.putString("account_type", accountFeatureProvider.getAccountType());
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mContext);
        subSettingLauncher.setDestination(AccountDetailDashboardFragment.class.getName());
        subSettingLauncher.setTitleRes(R.string.account_sync_title);
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.setSourceMetricsCategory(40);
        subSettingLauncher.launch();
        return true;
    }
}
