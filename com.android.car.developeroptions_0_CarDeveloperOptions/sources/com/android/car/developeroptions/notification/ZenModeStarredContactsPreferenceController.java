package com.android.car.developeroptions.notification;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class ZenModeStarredContactsPreferenceController extends AbstractZenModePreferenceController implements Preference.OnPreferenceClickListener {
    private Intent mFallbackIntent;
    private final PackageManager mPackageManager;
    private Preference mPreference;
    private final int mPriorityCategory;
    private Intent mStarredContactsIntent;

    public ZenModeStarredContactsPreferenceController(Context context, Lifecycle lifecycle, int i, String str) {
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

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        int i = this.mPriorityCategory;
        if (i == 8) {
            return this.mBackend.isPriorityCategoryEnabled(8) && this.mBackend.getPriorityCallSenders() == 2 && isIntentValid();
        }
        if (i == 4) {
            return this.mBackend.isPriorityCategoryEnabled(4) && this.mBackend.getPriorityMessageSenders() == 2 && isIntentValid();
        }
        return false;
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
