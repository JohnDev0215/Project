package com.android.car.settings.system;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorDescription;
import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.ArrayMap;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import com.android.car.ui.preference.CarUiPreference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public class MasterClearAccountsPreferenceController extends PreferenceController<PreferenceGroup> {
    private static final Logger LOG = new Logger((Class<?>) MasterClearAccountsPreferenceController.class);
    private final Map<Account, Preference> mAccountPreferenceMap;

    public MasterClearAccountsPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mAccountPreferenceMap = new ArrayMap();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        getPreference().addPreference(createPreference(getContext().getString(R.string.master_clear_accounts), null));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preferenceGroup) {
        List<UserInfo> profiles;
        Iterator<UserInfo> it;
        Set<Account> accountsToRemove = new HashSet<>(this.mAccountPreferenceMap.keySet());
        List<UserInfo> profiles2 = UserManager.get(getContext()).getProfiles(UserHandle.myUserId());
        Iterator<UserInfo> it2 = profiles2.iterator();
        while (it2.hasNext()) {
            UserInfo profile = it2.next();
            UserHandle userHandle = new UserHandle(profile.id);
            AuthenticatorDescription[] descriptions = AccountManager.get(getContext()).getAuthenticatorTypesAsUser(profile.id);
            Account[] accounts = AccountManager.get(getContext()).getAccountsAsUser(profile.id);
            int length = accounts.length;
            int i = 0;
            while (i < length) {
                Account account = accounts[i];
                AuthenticatorDescription description = null;
                int length2 = descriptions.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length2) {
                        profiles = profiles2;
                        it = it2;
                        break;
                    }
                    AuthenticatorDescription desc = descriptions[i2];
                    profiles = profiles2;
                    it = it2;
                    if (!account.type.equals(desc.type)) {
                        i2++;
                        profiles2 = profiles;
                        it2 = it;
                    } else {
                        description = desc;
                        break;
                    }
                }
                if (description == null) {
                    LOG.w("No descriptor for account name=" + account.name + " type=" + account.type);
                } else {
                    accountsToRemove.remove(account);
                    if (!this.mAccountPreferenceMap.containsKey(account)) {
                        Preference accountPref = createPreference(account.name, getAccountIcon(description, userHandle));
                        this.mAccountPreferenceMap.put(account, accountPref);
                        preferenceGroup.addPreference(accountPref);
                    }
                }
                i++;
                profiles2 = profiles;
                it2 = it;
            }
        }
        for (Account accountToRemove : accountsToRemove) {
            preferenceGroup.removePreference(this.mAccountPreferenceMap.get(accountToRemove));
        }
        preferenceGroup.setVisible(preferenceGroup.getPreferenceCount() > 1);
    }

    private Drawable getAccountIcon(AuthenticatorDescription description, UserHandle userHandle) {
        Drawable icon = null;
        try {
            if (description.iconId != 0) {
                Context authContext = getContext().createPackageContextAsUser(description.packageName, 0, userHandle);
                icon = getContext().getPackageManager().getUserBadgedIcon(authContext.getDrawable(description.iconId), userHandle);
            }
        } catch (PackageManager.NameNotFoundException e) {
            LOG.w("Bad package name for account type " + description.type, e);
        } catch (Resources.NotFoundException e2) {
            LOG.w("Invalid icon id for account type " + description.type, e2);
        }
        if (icon == null) {
            return getContext().getPackageManager().getDefaultActivityIcon();
        }
        return icon;
    }

    private Preference createPreference(String title, Drawable icon) {
        CarUiPreference preference = new CarUiPreference(getContext());
        preference.setTitle(title);
        preference.setIcon(icon);
        preference.setSelectable(false);
        preference.setSingleLineTitle(false);
        return preference;
    }
}
