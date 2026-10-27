package com.android.car.developeroptions.notification;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class ZenRuleStarredContactsPreferenceController extends AbstractZenCustomRulePreferenceController implements Preference.OnPreferenceClickListener {
    private Intent mFallbackIntent;
    private final PackageManager mPackageManager;
    private Preference mPreference;
    private final int mPriorityCategory;
    private Intent mStarredContactsIntent;

    public ZenRuleStarredContactsPreferenceController(Context context, Lifecycle lifecycle, int i, String str) {
        super(context, str, lifecycle);
        this.mPriorityCategory = i;
        this.mPackageManager = this.mContext.getPackageManager();
        this.mStarredContactsIntent = new Intent("com.android.contacts.action.LIST_STARRED");
        Intent intent = new Intent("android.intent.action.MAIN");
        this.mFallbackIntent = intent;
        intent.addCategory("android.intent.category.APP_CONTACTS");
    }

    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference preferenceFindPreference = preferenceScreen.findPreference(this.KEY);
        this.mPreference = preferenceFindPreference;
        if (preferenceFindPreference != null) {
            preferenceFindPreference.setOnPreferenceClickListener(this);
        }
    }

    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return this.KEY;
    }

    @Override // com.android.car.developeroptions.notification.AbstractZenCustomRulePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        if (!super.isAvailable() || this.mRule.getZenPolicy() == null || !isIntentValid()) {
            return false;
        }
        int i = this.mPriorityCategory;
        return i == 3 ? this.mRule.getZenPolicy().getPriorityCallSenders() == 3 : i == 2 && this.mRule.getZenPolicy().getPriorityMessageSenders() == 3;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        return this.mBackend.getStarredContactsSummary();
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        if (this.mStarredContactsIntent.resolveActivity(this.mPackageManager) != null) {
            this.mContext.startActivity(this.mStarredContactsIntent);
            return true;
        }
        this.mContext.startActivity(this.mFallbackIntent);
        return true;
    }

    private boolean isIntentValid() {
        return (this.mStarredContactsIntent.resolveActivity(this.mPackageManager) == null && this.mFallbackIntent.resolveActivity(this.mPackageManager) == null) ? false : true;
    }
}
