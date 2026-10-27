package com.android.car.settings.users;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.os.UserManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.SwitchPreference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.ui.preference.CarUiSwitchPreference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class PermissionsPreferenceController extends UserDetailsBasePreferenceController<PreferenceGroup> {
    private static final List<UserPermission> PERMISSIONS_LIST;
    static final String PERMISSION_TYPE_KEY = "permission_type_key";
    private final List<SwitchPreference> mPermissionPreferences;

    private static class UserPermission {
        private final String mPermissionKey;
        private final int mPermissionTitle;

        UserPermission(String key, int title) {
            this.mPermissionKey = key;
            this.mPermissionTitle = title;
        }

        public String getPermissionKey() {
            return this.mPermissionKey;
        }

        public int getPermissionTitle() {
            return this.mPermissionTitle;
        }
    }

    static {
        ArrayList arrayList = new ArrayList();
        PERMISSIONS_LIST = arrayList;
        arrayList.add(new UserPermission("no_add_user", R.string.create_user_permission_title));
        PERMISSIONS_LIST.add(new UserPermission("no_outgoing_calls", R.string.outgoing_calls_permission_title));
        PERMISSIONS_LIST.add(new UserPermission("no_sms", R.string.sms_messaging_permission_title));
        PERMISSIONS_LIST.add(new UserPermission("no_install_apps", R.string.install_apps_permission_title));
        PERMISSIONS_LIST.add(new UserPermission("no_uninstall_apps", R.string.uninstall_apps_permission_title));
    }

    public PermissionsPreferenceController(final Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mPermissionPreferences = new ArrayList();
        for (UserPermission permission : PERMISSIONS_LIST) {
            SwitchPreference preference = new CarUiSwitchPreference(context);
            preference.setTitle(permission.getPermissionTitle());
            preference.getExtras().putString(PERMISSION_TYPE_KEY, permission.getPermissionKey());
            preference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.car.settings.users.-$$Lambda$PermissionsPreferenceController$LgkyEbERDlznhS_8TJfrvNmo7Bw
                @Override // androidx.preference.Preference.OnPreferenceChangeListener
                public final boolean onPreferenceChange(Preference preference2, Object obj) {
                    return this.f$0.lambda$new$0$PermissionsPreferenceController(context, preference2, obj);
                }
            });
            this.mPermissionPreferences.add(preference);
        }
    }

    public /* synthetic */ boolean lambda$new$0$PermissionsPreferenceController(Context context, Preference pref, Object newValue) {
        boolean granted = ((Boolean) newValue).booleanValue();
        UserManager.get(context).setUserRestriction(pref.getExtras().getString(PERMISSION_TYPE_KEY), !granted, getUserInfo().getUserHandle());
        return true;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.car.settings.users.UserDetailsBasePreferenceController, com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        super.onCreateInternal();
        for (SwitchPreference switchPreference : this.mPermissionPreferences) {
            ((PreferenceGroup) getPreference()).addPreference(switchPreference);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preferenceGroup) {
        for (SwitchPreference switchPreference : this.mPermissionPreferences) {
            UserManager userManager = UserManager.get(getContext());
            switchPreference.setChecked(!userManager.hasUserRestriction(switchPreference.getExtras().getString(PERMISSION_TYPE_KEY), getUserInfo().getUserHandle()));
        }
    }
}
