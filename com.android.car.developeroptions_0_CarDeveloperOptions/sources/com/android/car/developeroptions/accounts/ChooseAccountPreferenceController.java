package com.android.car.developeroptions.accounts;

import android.accounts.AccountManager;
import android.accounts.AuthenticatorDescription;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SyncAdapterType;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.google.android.collect.Maps;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class ChooseAccountPreferenceController extends BasePreferenceController {
    private static final String TAG = "ChooseAccountPrefCtrler";
    private Map<String, List<String>> mAccountTypeToAuthorities;
    private Set<String> mAccountTypesFilter;
    private Activity mActivity;
    private AuthenticatorDescription[] mAuthDescs;
    private String[] mAuthorities;
    private final List<ProviderEntry> mProviderList;
    private PreferenceScreen mScreen;
    private final Map<String, AuthenticatorDescription> mTypeToAuthDescription;
    private UserHandle mUserHandle;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
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

    public ChooseAccountPreferenceController(Context context, String str) {
        super(context, str);
        this.mProviderList = new ArrayList();
        this.mTypeToAuthDescription = new HashMap();
    }

    public void initialize(String[] strArr, String[] strArr2, UserHandle userHandle, Activity activity) {
        this.mActivity = activity;
        this.mAuthorities = strArr;
        this.mUserHandle = userHandle;
        if (strArr2 != null) {
            this.mAccountTypesFilter = new HashSet();
            for (String str : strArr2) {
                this.mAccountTypesFilter.add(str);
            }
        }
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mScreen = preferenceScreen;
        updateAuthDescriptions();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!(preference instanceof ProviderPreference)) {
            return false;
        }
        ProviderPreference providerPreference = (ProviderPreference) preference;
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "Attempting to add account of type " + providerPreference.getAccountType());
        }
        finishWithAccountType(providerPreference.getAccountType());
        return true;
    }

    private void updateAuthDescriptions() {
        this.mAuthDescs = AccountManager.get(this.mContext).getAuthenticatorTypesAsUser(this.mUserHandle.getIdentifier());
        int i = 0;
        while (true) {
            AuthenticatorDescription[] authenticatorDescriptionArr = this.mAuthDescs;
            if (i < authenticatorDescriptionArr.length) {
                this.mTypeToAuthDescription.put(authenticatorDescriptionArr[i].type, authenticatorDescriptionArr[i]);
                i++;
            } else {
                onAuthDescriptionsUpdated();
                return;
            }
        }
    }

    private void onAuthDescriptionsUpdated() {
        Set<String> set;
        int i = 0;
        while (true) {
            AuthenticatorDescription[] authenticatorDescriptionArr = this.mAuthDescs;
            boolean z = true;
            if (i >= authenticatorDescriptionArr.length) {
                break;
            }
            String str = authenticatorDescriptionArr[i].type;
            CharSequence labelForType = getLabelForType(str);
            List<String> authoritiesForAccountType = getAuthoritiesForAccountType(str);
            String[] strArr = this.mAuthorities;
            if (strArr != null && strArr.length > 0 && authoritiesForAccountType != null) {
                int i2 = 0;
                while (true) {
                    String[] strArr2 = this.mAuthorities;
                    if (i2 >= strArr2.length) {
                        z = false;
                        break;
                    } else if (authoritiesForAccountType.contains(strArr2[i2])) {
                        break;
                    } else {
                        i2++;
                    }
                }
            }
            if (z && (set = this.mAccountTypesFilter) != null && !set.contains(str)) {
                z = false;
            }
            if (z) {
                this.mProviderList.add(new ProviderEntry(labelForType, str));
            } else if (Log.isLoggable(TAG, 2)) {
                Log.v(TAG, "Skipped pref " + ((Object) labelForType) + ": has no authority we need");
            }
            i++;
        }
        Context context = this.mScreen.getContext();
        if (this.mProviderList.size() == 1) {
            RestrictedLockUtils.EnforcedAdmin enforcedAdminCheckIfAccountManagementDisabled = RestrictedLockUtilsInternal.checkIfAccountManagementDisabled(context, this.mProviderList.get(0).getType(), this.mUserHandle.getIdentifier());
            if (enforcedAdminCheckIfAccountManagementDisabled != null) {
                this.mActivity.setResult(0, RestrictedLockUtils.getShowAdminSupportDetailsIntent(context, enforcedAdminCheckIfAccountManagementDisabled));
                this.mActivity.finish();
                return;
            } else {
                finishWithAccountType(this.mProviderList.get(0).getType());
                return;
            }
        }
        if (this.mProviderList.size() > 0) {
            Collections.sort(this.mProviderList);
            for (ProviderEntry providerEntry : this.mProviderList) {
                ProviderPreference providerPreference = new ProviderPreference(context, providerEntry.getType(), getDrawableForType(providerEntry.getType()), providerEntry.getName());
                providerPreference.setKey(providerEntry.getType().toString());
                providerPreference.checkAccountManagementAndSetDisabled(this.mUserHandle.getIdentifier());
                this.mScreen.addPreference(providerPreference);
            }
            return;
        }
        if (Log.isLoggable(TAG, 2)) {
            StringBuilder sb = new StringBuilder();
            for (String str2 : this.mAuthorities) {
                sb.append(str2);
                sb.append(' ');
            }
            Log.v(TAG, "No providers found for authorities: " + ((Object) sb));
        }
        this.mActivity.setResult(0);
        this.mActivity.finish();
    }

    private List<String> getAuthoritiesForAccountType(String str) {
        if (this.mAccountTypeToAuthorities == null) {
            this.mAccountTypeToAuthorities = Maps.newHashMap();
            for (SyncAdapterType syncAdapterType : ContentResolver.getSyncAdapterTypesAsUser(this.mUserHandle.getIdentifier())) {
                List<String> arrayList = this.mAccountTypeToAuthorities.get(syncAdapterType.accountType);
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                    this.mAccountTypeToAuthorities.put(syncAdapterType.accountType, arrayList);
                }
                if (Log.isLoggable(TAG, 2)) {
                    Log.v(TAG, "added authority " + syncAdapterType.authority + " to accountType " + syncAdapterType.accountType);
                }
                arrayList.add(syncAdapterType.authority);
            }
        }
        return this.mAccountTypeToAuthorities.get(str);
    }

    Drawable getDrawableForType(String str) {
        Drawable userBadgedIcon;
        if (this.mTypeToAuthDescription.containsKey(str)) {
            try {
                AuthenticatorDescription authenticatorDescription = this.mTypeToAuthDescription.get(str);
                userBadgedIcon = this.mContext.getPackageManager().getUserBadgedIcon(this.mActivity.createPackageContextAsUser(authenticatorDescription.packageName, 0, this.mUserHandle).getDrawable(authenticatorDescription.iconId), this.mUserHandle);
            } catch (PackageManager.NameNotFoundException unused) {
                Log.w(TAG, "No icon name for account type " + str);
                userBadgedIcon = null;
            } catch (Resources.NotFoundException unused2) {
                Log.w(TAG, "No icon resource for account type " + str);
                userBadgedIcon = null;
            }
        } else {
            userBadgedIcon = null;
        }
        return userBadgedIcon != null ? userBadgedIcon : this.mContext.getPackageManager().getDefaultActivityIcon();
    }

    CharSequence getLabelForType(String str) {
        if (this.mTypeToAuthDescription.containsKey(str)) {
            try {
                AuthenticatorDescription authenticatorDescription = this.mTypeToAuthDescription.get(str);
                return this.mActivity.createPackageContextAsUser(authenticatorDescription.packageName, 0, this.mUserHandle).getResources().getText(authenticatorDescription.labelId);
            } catch (PackageManager.NameNotFoundException unused) {
                Log.w(TAG, "No label name for account type " + str);
            } catch (Resources.NotFoundException unused2) {
                Log.w(TAG, "No label resource for account type " + str);
            }
        }
        return null;
    }

    private void finishWithAccountType(String str) {
        Intent intent = new Intent();
        intent.putExtra("selected_account", str);
        intent.putExtra("android.intent.extra.USER", this.mUserHandle);
        this.mActivity.setResult(-1, intent);
        this.mActivity.finish();
    }
}
