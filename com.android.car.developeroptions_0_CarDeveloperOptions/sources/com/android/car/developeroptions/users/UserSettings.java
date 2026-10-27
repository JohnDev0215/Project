package com.android.car.developeroptions.users;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.ContactsContract;
import android.provider.SearchIndexableResource;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.dashboard.SummaryLoader;
import com.android.car.developeroptions.intelligence.LogProto$SettingsLog;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.users.EditUserInfoController;
import com.android.car.developeroptions.users.MultiUserSwitchBarController;
import com.android.car.developeroptions.widget.SwitchBar;
import com.android.car.developeroptions.widget.SwitchBarController;
import com.android.internal.util.UserIcons;
import com.android.internal.widget.LockPatternUtils;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.RestrictedPreference;
import com.android.settingslib.drawable.CircleFramedDrawable;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class UserSettings extends SettingsPreferenceFragment implements Preference.OnPreferenceClickListener, View.OnClickListener, MultiUserSwitchBarController.OnMultiUserSwitchChangedListener, DialogInterface.OnDismissListener, EditUserInfoController.OnContentChangedCallback {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER;
    public static final SummaryLoader.SummaryProviderFactory SUMMARY_PROVIDER_FACTORY;
    private static final IntentFilter USER_REMOVED_INTENT_FILTER;
    private static SparseArray<Bitmap> sDarkDefaultUserBitmapCache;
    RestrictedPreference mAddUser;
    private AddUserWhenLockedPreferenceController mAddUserWhenLockedPreferenceController;
    private boolean mAddingUser;
    private String mAddingUserName;
    private Drawable mDefaultIconDrawable;
    UserPreference mMePreference;
    private MultiUserFooterPreferenceController mMultiUserFooterPreferenceController;
    private MultiUserSwitchBarController mSwitchBarController;
    private UserCapabilities mUserCaps;
    PreferenceGroup mUserListCategory;
    private UserManager mUserManager;
    private int mRemovingUserId = -1;
    private int mAddedUserId = 0;
    private boolean mShouldUpdateUserList = true;
    private final Object mUserLock = new Object();
    private SparseArray<Bitmap> mUserIcons = new SparseArray<>();
    private EditUserInfoController mEditUserInfoController = new EditUserInfoController();
    private Handler mHandler = new Handler() { // from class: com.android.car.developeroptions.users.UserSettings.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                UserSettings.this.updateUserList();
            } else if (i == 2) {
                UserSettings.this.onUserCreated(message.arg1);
            } else {
                if (i != 3) {
                    return;
                }
                UserSettings.this.onManageUserClicked(message.arg1, true);
            }
        }
    };
    private BroadcastReceiver mUserChangeReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.users.UserSettings.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra;
            if (intent.getAction().equals("android.intent.action.USER_REMOVED")) {
                UserSettings.this.mRemovingUserId = -1;
            } else if (intent.getAction().equals("android.intent.action.USER_INFO_CHANGED") && (intExtra = intent.getIntExtra("android.intent.extra.user_handle", -1)) != -1) {
                UserSettings.this.mUserIcons.remove(intExtra);
            }
            UserSettings.this.mHandler.sendEmptyMessage(1);
        }
    };

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.DialogCreatable
    public int getDialogMetricsCategory(int i) {
        switch (i) {
            case 1:
                return 591;
            case 2:
                return 595;
            case 3:
                return 596;
            case 4:
                return 597;
            case 5:
                return 594;
            case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
                return 598;
            case 7:
                return 599;
            case 8:
                return 600;
            case 9:
                return 601;
            default:
                return 0;
        }
    }

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_users;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 96;
    }

    static {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.USER_REMOVED");
        USER_REMOVED_INTENT_FILTER = intentFilter;
        intentFilter.addAction("android.intent.action.USER_INFO_CHANGED");
        sDarkDefaultUserBitmapCache = new SparseArray<>();
        SUMMARY_PROVIDER_FACTORY = new SummaryLoader.SummaryProviderFactory() { // from class: com.android.car.developeroptions.users.-$$Lambda$UserSettings$mU_k4JxCpEEmGZK02dUgaLOBBZs
            @Override // com.android.car.developeroptions.dashboard.SummaryLoader.SummaryProviderFactory
            public final SummaryLoader.SummaryProvider createSummaryProvider(Activity activity, SummaryLoader summaryLoader) {
                return UserSettings.lambda$static$1(activity, summaryLoader);
            }
        };
        SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.users.UserSettings.14
            @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
            public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
                ArrayList arrayList = new ArrayList();
                SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
                searchIndexableResource.xmlResId = R.xml.user_settings;
                arrayList.add(searchIndexableResource);
                return arrayList;
            }

            @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider
            public List<String> getNonIndexableKeysFromXml(Context context, int i, boolean z) {
                List<String> nonIndexableKeysFromXml = super.getNonIndexableKeysFromXml(context, i, z);
                new AddUserWhenLockedPreferenceController(context, "user_settings_add_users_when_locked").updateNonIndexableKeys(nonIndexableKeysFromXml);
                new AutoSyncDataPreferenceController(context, null).updateNonIndexableKeys(nonIndexableKeysFromXml);
                new AutoSyncPersonalDataPreferenceController(context, null).updateNonIndexableKeys(nonIndexableKeysFromXml);
                new AutoSyncWorkDataPreferenceController(context, null).updateNonIndexableKeys(nonIndexableKeysFromXml);
                return nonIndexableKeysFromXml;
            }
        };
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        SwitchBar switchBar = settingsActivity.getSwitchBar();
        this.mSwitchBarController = new MultiUserSwitchBarController(settingsActivity, new SwitchBarController(switchBar), this);
        getSettingsLifecycle().addObserver(this.mSwitchBarController);
        switchBar.show();
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.user_settings);
        FragmentActivity activity = getActivity();
        if (!Utils.isDeviceProvisioned(activity)) {
            activity.finish();
            return;
        }
        this.mAddUserWhenLockedPreferenceController = new AddUserWhenLockedPreferenceController(activity, "user_settings_add_users_when_locked");
        this.mMultiUserFooterPreferenceController = new MultiUserFooterPreferenceController(activity, "multiuser_footer");
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        this.mAddUserWhenLockedPreferenceController.displayPreference(preferenceScreen);
        this.mMultiUserFooterPreferenceController.displayPreference(preferenceScreen);
        preferenceScreen.findPreference(this.mAddUserWhenLockedPreferenceController.getPreferenceKey()).setOnPreferenceChangeListener(this.mAddUserWhenLockedPreferenceController);
        if (bundle != null) {
            if (bundle.containsKey("adding_user")) {
                this.mAddedUserId = bundle.getInt("adding_user");
            }
            if (bundle.containsKey("removing_user")) {
                this.mRemovingUserId = bundle.getInt("removing_user");
            }
            this.mEditUserInfoController.onRestoreInstanceState(bundle);
        }
        this.mUserCaps = UserCapabilities.create(activity);
        this.mUserManager = (UserManager) activity.getSystemService("user");
        if (this.mUserCaps.mEnabled) {
            int iMyUserId = UserHandle.myUserId();
            this.mUserListCategory = (PreferenceGroup) findPreference("user_list");
            UserPreference userPreference = new UserPreference(getPrefContext(), null, iMyUserId, null, null);
            this.mMePreference = userPreference;
            userPreference.setKey("user_me");
            this.mMePreference.setOnPreferenceClickListener(this);
            if (this.mUserCaps.mIsAdmin) {
                this.mMePreference.setSummary(R.string.user_admin);
            }
            RestrictedPreference restrictedPreference = (RestrictedPreference) findPreference("user_add");
            this.mAddUser = restrictedPreference;
            restrictedPreference.setOnPreferenceClickListener(this);
            activity.registerReceiverAsUser(this.mUserChangeReceiver, UserHandle.ALL, USER_REMOVED_INTENT_FILTER, null, this.mHandler);
            updateUI();
            this.mShouldUpdateUserList = false;
        }
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mUserCaps.mEnabled) {
            PreferenceScreen preferenceScreen = getPreferenceScreen();
            AddUserWhenLockedPreferenceController addUserWhenLockedPreferenceController = this.mAddUserWhenLockedPreferenceController;
            addUserWhenLockedPreferenceController.updateState(preferenceScreen.findPreference(addUserWhenLockedPreferenceController.getPreferenceKey()));
            if (this.mShouldUpdateUserList) {
                updateUI();
            }
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        this.mShouldUpdateUserList = true;
        super.onPause();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        UserCapabilities userCapabilities = this.mUserCaps;
        if (userCapabilities == null || !userCapabilities.mEnabled) {
            return;
        }
        getActivity().unregisterReceiver(this.mUserChangeReceiver);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mEditUserInfoController.onSaveInstanceState(bundle);
        bundle.putInt("adding_user", this.mAddedUserId);
        bundle.putInt("removing_user", this.mRemovingUserId);
    }

    @Override // androidx.fragment.app.Fragment
    public void startActivityForResult(Intent intent, int i) {
        this.mEditUserInfoController.startingActivityForResult();
        super.startActivityForResult(intent, i);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        boolean zCanSwitchUsers = this.mUserManager.canSwitchUsers();
        if (!this.mUserCaps.mIsAdmin && zCanSwitchUsers) {
            MenuItem menuItemAdd = menu.add(0, 1, 0, getResources().getString(R.string.user_remove_user_menu, this.mUserManager.getUserName()));
            menuItemAdd.setShowAsAction(0);
            RestrictedLockUtilsInternal.setMenuItemAsDisabledByAdmin(getContext(), menuItemAdd, RestrictedLockUtilsInternal.checkIfRestrictionEnforced(getContext(), "no_remove_user", UserHandle.myUserId()));
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 1) {
            onRemoveUserClicked(UserHandle.myUserId());
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // com.android.car.developeroptions.users.MultiUserSwitchBarController.OnMultiUserSwitchChangedListener
    public void onMultiUserSwitchChanged(boolean z) {
        updateUI();
    }

    private void updateUI() {
        this.mUserCaps.updateAddUserCapabilities(getActivity());
        loadProfile();
        updateUserList();
    }

    private void loadProfile() {
        if (this.mUserCaps.mIsGuest) {
            this.mMePreference.setIcon(getEncircledDefaultIcon());
            this.mMePreference.setTitle(R.string.user_exit_guest_title);
        } else {
            new AsyncTask<Void, Void, String>() { // from class: com.android.car.developeroptions.users.UserSettings.3
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public void onPostExecute(String str) {
                    UserSettings.this.finishLoadProfile(str);
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public String doInBackground(Void... voidArr) {
                    UserInfo userInfo = UserSettings.this.mUserManager.getUserInfo(UserHandle.myUserId());
                    String str = userInfo.iconPath;
                    if (str == null || str.equals("")) {
                        UserSettings.copyMeProfilePhoto(UserSettings.this.getActivity(), userInfo);
                    }
                    return userInfo.name;
                }
            }.execute(new Void[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishLoadProfile(String str) {
        if (getActivity() == null) {
            return;
        }
        this.mMePreference.setTitle(getString(R.string.user_you, str));
        int iMyUserId = UserHandle.myUserId();
        Bitmap userIcon = this.mUserManager.getUserIcon(iMyUserId);
        if (userIcon != null) {
            this.mMePreference.setIcon(encircle(userIcon));
            this.mUserIcons.put(iMyUserId, userIcon);
        }
    }

    private boolean hasLockscreenSecurity() {
        return new LockPatternUtils(getActivity()).isSecure(UserHandle.myUserId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchChooseLockscreen() {
        Intent intent = new Intent("android.app.action.SET_NEW_PASSWORD");
        intent.putExtra("minimum_quality", 65536);
        startActivityForResult(intent, 10);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 10) {
            if (i2 == 0 || !hasLockscreenSecurity()) {
                return;
            }
            addUserNow(2);
            return;
        }
        this.mEditUserInfoController.onActivityResult(i, i2, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAddUserClicked(int i) {
        synchronized (this.mUserLock) {
            if (this.mRemovingUserId == -1 && !this.mAddingUser) {
                if (i == 1) {
                    showDialog(2);
                } else if (i == 2) {
                    if (hasLockscreenSecurity()) {
                        addUserNow(2);
                    } else {
                        showDialog(7);
                    }
                }
            }
        }
    }

    private void onRemoveUserClicked(int i) {
        synchronized (this.mUserLock) {
            if (this.mRemovingUserId == -1 && !this.mAddingUser) {
                this.mRemovingUserId = i;
                showDialog(1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UserInfo createRestrictedProfile() {
        UserInfo userInfoCreateRestrictedProfile = this.mUserManager.createRestrictedProfile(this.mAddingUserName);
        if (userInfoCreateRestrictedProfile == null || assignDefaultPhoto(getActivity(), userInfoCreateRestrictedProfile.id)) {
            return userInfoCreateRestrictedProfile;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UserInfo createTrustedUser() {
        UserInfo userInfoCreateUser = this.mUserManager.createUser(this.mAddingUserName, 0);
        if (userInfoCreateUser == null || assignDefaultPhoto(getActivity(), userInfoCreateUser.id)) {
            return userInfoCreateUser;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onManageUserClicked(int i, boolean z) {
        this.mAddingUser = false;
        if (i == -11) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("guest_user", true);
            SubSettingLauncher subSettingLauncher = new SubSettingLauncher(getContext());
            subSettingLauncher.setDestination(UserDetailsSettings.class.getName());
            subSettingLauncher.setArguments(bundle);
            subSettingLauncher.setTitleRes(R.string.user_guest);
            subSettingLauncher.setSourceMetricsCategory(getMetricsCategory());
            subSettingLauncher.launch();
            return;
        }
        UserInfo userInfo = this.mUserManager.getUserInfo(i);
        if (userInfo.isRestricted() && this.mUserCaps.mIsAdmin) {
            Bundle bundle2 = new Bundle();
            bundle2.putInt("user_id", i);
            bundle2.putBoolean("new_user", z);
            SubSettingLauncher subSettingLauncher2 = new SubSettingLauncher(getContext());
            subSettingLauncher2.setDestination(RestrictedProfileSettings.class.getName());
            subSettingLauncher2.setArguments(bundle2);
            subSettingLauncher2.setTitleRes(R.string.user_restrictions_title);
            subSettingLauncher2.setSourceMetricsCategory(getMetricsCategory());
            subSettingLauncher2.launch();
            return;
        }
        if (userInfo.id == UserHandle.myUserId()) {
            OwnerInfoSettings.show(this);
            return;
        }
        if (this.mUserCaps.mIsAdmin) {
            Bundle bundle3 = new Bundle();
            bundle3.putInt("user_id", i);
            SubSettingLauncher subSettingLauncher3 = new SubSettingLauncher(getContext());
            subSettingLauncher3.setDestination(UserDetailsSettings.class.getName());
            subSettingLauncher3.setArguments(bundle3);
            subSettingLauncher3.setTitleText(userInfo.name);
            subSettingLauncher3.setSourceMetricsCategory(getMetricsCategory());
            subSettingLauncher3.launch();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUserCreated(int i) {
        this.mAddedUserId = i;
        this.mAddingUser = false;
        if (!isResumed()) {
            Log.w("UserSettings", "Cannot show dialog after onPause");
        } else if (this.mUserManager.getUserInfo(i).isRestricted()) {
            showDialog(4);
        } else {
            showDialog(3);
        }
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment
    public void onDialogShowing() {
        super.onDialogShowing();
        setOnDismissListener(this);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.DialogCreatable
    public Dialog onCreateDialog(int i) {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return null;
        }
        switch (i) {
            case 2:
                final SharedPreferences preferences = getActivity().getPreferences(0);
                final boolean z = preferences.getBoolean("key_add_user_long_message_displayed", false);
                int i2 = z ? R.string.user_add_user_message_short : R.string.user_add_user_message_long;
                final int i3 = i == 2 ? 1 : 2;
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle(R.string.user_add_user_title);
                builder.setMessage(i2);
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.users.UserSettings.5
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i4) {
                        UserSettings.this.addUserNow(i3);
                        if (z) {
                            return;
                        }
                        preferences.edit().putBoolean("key_add_user_long_message_displayed", true).apply();
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null);
                break;
            case 3:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(activity);
                builder2.setTitle(R.string.user_setup_dialog_title);
                builder2.setMessage(R.string.user_setup_dialog_message);
                builder2.setPositiveButton(R.string.user_setup_button_setup_now, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.users.UserSettings.6
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i4) {
                        UserSettings userSettings = UserSettings.this;
                        userSettings.switchUserNow(userSettings.mAddedUserId);
                    }
                });
                builder2.setNegativeButton(R.string.user_setup_button_setup_later, (DialogInterface.OnClickListener) null);
                break;
            case 4:
                AlertDialog.Builder builder3 = new AlertDialog.Builder(activity);
                builder3.setMessage(R.string.user_setup_profile_dialog_message);
                builder3.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.users.UserSettings.7
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i4) {
                        UserSettings userSettings = UserSettings.this;
                        userSettings.switchUserNow(userSettings.mAddedUserId);
                    }
                });
                builder3.setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null);
                break;
            case 5:
                AlertDialog.Builder builder4 = new AlertDialog.Builder(activity);
                builder4.setMessage(R.string.user_cannot_manage_message);
                builder4.setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null);
                break;
            case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
                ArrayList arrayList = new ArrayList();
                HashMap map = new HashMap();
                map.put("title", getString(R.string.user_add_user_item_title));
                map.put("summary", getString(R.string.user_add_user_item_summary));
                HashMap map2 = new HashMap();
                map2.put("title", getString(R.string.user_add_profile_item_title));
                map2.put("summary", getString(R.string.user_add_profile_item_summary));
                arrayList.add(map);
                arrayList.add(map2);
                AlertDialog.Builder builder5 = new AlertDialog.Builder(activity);
                SimpleAdapter simpleAdapter = new SimpleAdapter(builder5.getContext(), arrayList, R.layout.two_line_list_item, new String[]{"title", "summary"}, new int[]{R.id.title, R.id.summary});
                builder5.setTitle(R.string.user_add_user_type_title);
                builder5.setAdapter(simpleAdapter, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.users.UserSettings.8
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i4) {
                        UserSettings.this.onAddUserClicked(i4 == 0 ? 1 : 2);
                    }
                });
                break;
            case 7:
                AlertDialog.Builder builder6 = new AlertDialog.Builder(activity);
                builder6.setMessage(R.string.user_need_lock_message);
                builder6.setPositiveButton(R.string.user_set_lock_button, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.users.UserSettings.9
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i4) {
                        UserSettings.this.launchChooseLockscreen();
                    }
                });
                builder6.setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null);
                break;
            case 8:
                AlertDialog.Builder builder7 = new AlertDialog.Builder(activity);
                builder7.setTitle(R.string.user_exit_guest_confirm_title);
                builder7.setMessage(R.string.user_exit_guest_confirm_message);
                builder7.setPositiveButton(R.string.user_exit_guest_dialog_remove, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.users.UserSettings.10
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i4) {
                        UserSettings.this.exitGuest();
                    }
                });
                builder7.setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null);
                break;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeUserNow() {
        if (this.mRemovingUserId == UserHandle.myUserId()) {
            removeThisUser();
        } else {
            new Thread() { // from class: com.android.car.developeroptions.users.UserSettings.11
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    synchronized (UserSettings.this.mUserLock) {
                        UserSettings.this.mUserManager.removeUser(UserSettings.this.mRemovingUserId);
                        UserSettings.this.mHandler.sendEmptyMessage(1);
                    }
                }
            }.start();
        }
    }

    private void removeThisUser() {
        if (!this.mUserManager.canSwitchUsers()) {
            Log.w("UserSettings", "Cannot remove current user when switching is disabled");
            return;
        }
        try {
            ActivityManager.getService().switchUser(0);
            ((UserManager) getContext().getSystemService(UserManager.class)).removeUser(UserHandle.myUserId());
        } catch (RemoteException unused) {
            Log.e("UserSettings", "Unable to remove self user");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addUserNow(final int i) {
        String string;
        synchronized (this.mUserLock) {
            this.mAddingUser = true;
            if (i == 1) {
                string = getString(R.string.user_new_user_name);
            } else {
                string = getString(R.string.user_new_profile_name);
            }
            this.mAddingUserName = string;
            new Thread() { // from class: com.android.car.developeroptions.users.UserSettings.12
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    UserInfo userInfoCreateTrustedUser = i == 1 ? UserSettings.this.createTrustedUser() : UserSettings.this.createRestrictedProfile();
                    if (userInfoCreateTrustedUser == null) {
                        UserSettings.this.mAddingUser = false;
                        return;
                    }
                    synchronized (UserSettings.this.mUserLock) {
                        if (i == 1) {
                            UserSettings.this.mHandler.sendEmptyMessage(1);
                            if (!UserSettings.this.mUserCaps.mDisallowSwitchUser) {
                                UserSettings.this.mHandler.sendMessage(UserSettings.this.mHandler.obtainMessage(2, userInfoCreateTrustedUser.id, userInfoCreateTrustedUser.serialNumber));
                            }
                        } else {
                            UserSettings.this.mHandler.sendMessage(UserSettings.this.mHandler.obtainMessage(3, userInfoCreateTrustedUser.id, userInfoCreateTrustedUser.serialNumber));
                        }
                    }
                }
            }.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchUserNow(int i) {
        try {
            ActivityManager.getService().switchUser(i);
        } catch (RemoteException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void exitGuest() {
        if (this.mUserCaps.mIsGuest) {
            removeThisUser();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1, types: [android.view.View$OnClickListener] */
    /* JADX WARN: Type inference failed for: r15v2 */
    /* JADX WARN: Type inference failed for: r17v0 */
    /* JADX WARN: Type inference failed for: r17v1, types: [android.view.View$OnClickListener] */
    /* JADX WARN: Type inference failed for: r17v2 */
    /* JADX WARN: Type inference failed for: r18v0 */
    /* JADX WARN: Type inference failed for: r18v1, types: [android.view.View$OnClickListener] */
    /* JADX WARN: Type inference failed for: r18v2 */
    /* JADX WARN: Type inference failed for: r19v0, types: [androidx.fragment.app.Fragment, androidx.preference.Preference$OnPreferenceClickListener, androidx.preference.PreferenceFragmentCompat, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.car.developeroptions.users.UserSettings] */
    void updateUserList() {
        UserPreference userPreference;
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        List<UserInfo> users = this.mUserManager.getUsers(true);
        boolean zIsVoiceCapable = Utils.isVoiceCapable(activity);
        ArrayList arrayList = new ArrayList();
        ArrayList<UserPreference> arrayList2 = new ArrayList();
        final int i = -11;
        arrayList2.add(this.mMePreference);
        for (UserInfo userInfo : users) {
            if (userInfo.supportsSwitchToByUser()) {
                if (userInfo.id == UserHandle.myUserId()) {
                    userPreference = this.mMePreference;
                } else if (userInfo.isGuest()) {
                    i = userInfo.id;
                } else {
                    UserPreference userPreference2 = new UserPreference(getPrefContext(), null, userInfo.id, this.mUserCaps.mIsAdmin && (zIsVoiceCapable || userInfo.isRestricted()) ? this : 0, this.mUserCaps.mIsAdmin && !zIsVoiceCapable && !userInfo.isRestricted() && !userInfo.isGuest() ? this : 0);
                    userPreference2.setKey("id=" + userInfo.id);
                    arrayList2.add(userPreference2);
                    if (userInfo.isAdmin()) {
                        userPreference2.setSummary(R.string.user_admin);
                    }
                    userPreference2.setTitle(userInfo.name);
                    userPreference2.setSelectable(false);
                    userPreference = userPreference2;
                }
                if (userPreference != null) {
                    if (!isInitialized(userInfo)) {
                        if (userInfo.isRestricted()) {
                            userPreference.setSummary(R.string.user_summary_restricted_not_set_up);
                        } else {
                            userPreference.setSummary(R.string.user_summary_not_set_up);
                        }
                        if (!this.mUserCaps.mDisallowSwitchUser) {
                            userPreference.setOnPreferenceClickListener(this);
                            userPreference.setSelectable(this.mUserManager.canSwitchUsers());
                        }
                    } else if (userInfo.isRestricted()) {
                        userPreference.setSummary(R.string.user_summary_restricted_profile);
                    }
                    if (userInfo.iconPath != null) {
                        if (this.mUserIcons.get(userInfo.id) == null) {
                            arrayList.add(Integer.valueOf(userInfo.id));
                            userPreference.setIcon(getEncircledDefaultIcon());
                        } else {
                            setPhotoId(userPreference, userInfo);
                        }
                    } else {
                        userPreference.setIcon(getEncircledDefaultIcon());
                    }
                }
            }
        }
        if (this.mAddingUser) {
            UserPreference userPreference3 = new UserPreference(getPrefContext(), null, -10, null, null);
            userPreference3.setEnabled(false);
            userPreference3.setTitle(this.mAddingUserName);
            userPreference3.setIcon(getEncircledDefaultIcon());
            arrayList2.add(userPreference3);
        }
        UserCapabilities userCapabilities = this.mUserCaps;
        if (!userCapabilities.mIsGuest && (userCapabilities.mCanAddGuest || userCapabilities.mDisallowAddUserSetByAdmin)) {
            UserPreference userPreference4 = new UserPreference(getPrefContext(), null, -11, (this.mUserCaps.mIsAdmin && zIsVoiceCapable) ? this : 0, null);
            userPreference4.setTitle(R.string.user_guest);
            userPreference4.setIcon(getEncircledDefaultIcon());
            userPreference4.setKey("user_guest");
            arrayList2.add(userPreference4);
            UserCapabilities userCapabilities2 = this.mUserCaps;
            if (userCapabilities2.mDisallowAddUser) {
                userPreference4.setDisabledByAdmin(userCapabilities2.mEnforcedAdmin);
            } else if (userCapabilities2.mDisallowSwitchUser) {
                userPreference4.setDisabledByAdmin(RestrictedLockUtilsInternal.getDeviceOwner(activity));
            } else {
                userPreference4.setDisabledByAdmin(null);
            }
            if (!this.mUserManager.canSwitchUsers()) {
                userPreference4.setSelectable(false);
            }
            userPreference4.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.users.-$$Lambda$UserSettings$lRJQubIX06kQyUiKBFnMsERpIgQ
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return this.f$0.lambda$updateUserList$0$UserSettings(i, preference);
                }
            });
        }
        Collections.sort(arrayList2, UserPreference.SERIAL_NUMBER_COMPARATOR);
        getActivity().invalidateOptionsMenu();
        if (arrayList.size() > 0) {
            loadIconsAsync(arrayList);
        }
        if (this.mUserCaps.mCanAddRestrictedProfile) {
            this.mUserListCategory.setTitle(R.string.user_list_title);
        } else {
            this.mUserListCategory.setTitle((CharSequence) null);
        }
        this.mUserListCategory.removeAll();
        this.mAddUserWhenLockedPreferenceController.updateState(getPreferenceScreen().findPreference(this.mAddUserWhenLockedPreferenceController.getPreferenceKey()));
        this.mMultiUserFooterPreferenceController.updateState(getPreferenceScreen().findPreference(this.mMultiUserFooterPreferenceController.getPreferenceKey()));
        this.mUserListCategory.setVisible(this.mUserCaps.mUserSwitcherEnabled);
        updateAddUser(activity);
        if (this.mUserCaps.mUserSwitcherEnabled) {
            for (UserPreference userPreference5 : arrayList2) {
                userPreference5.setOrder(Integer.MAX_VALUE);
                this.mUserListCategory.addPreference(userPreference5);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateUserList$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$updateUserList$0$UserSettings(int i, Preference preference) {
        UserInfo userInfoCreateGuest;
        if (i == -11 && (userInfoCreateGuest = this.mUserManager.createGuest(getContext(), preference.getTitle().toString())) != null) {
            i = userInfoCreateGuest.id;
        }
        try {
            ActivityManager.getService().switchUser(i);
            return true;
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
            return true;
        }
    }

    private void updateAddUser(Context context) {
        UserCapabilities userCapabilities = this.mUserCaps;
        if ((userCapabilities.mCanAddUser || userCapabilities.mDisallowAddUserSetByAdmin) && Utils.isDeviceProvisioned(context) && this.mUserCaps.mUserSwitcherEnabled) {
            this.mAddUser.setVisible(true);
            boolean zCanAddMoreUsers = this.mUserManager.canAddMoreUsers();
            this.mAddUser.setEnabled(zCanAddMoreUsers && !this.mAddingUser && this.mUserManager.canSwitchUsers());
            if (!zCanAddMoreUsers) {
                this.mAddUser.setSummary(getString(R.string.user_add_max_count, Integer.valueOf(getMaxRealUsers())));
            } else {
                this.mAddUser.setSummary((CharSequence) null);
            }
            if (this.mAddUser.isEnabled()) {
                RestrictedPreference restrictedPreference = this.mAddUser;
                UserCapabilities userCapabilities2 = this.mUserCaps;
                restrictedPreference.setDisabledByAdmin(userCapabilities2.mDisallowAddUser ? userCapabilities2.mEnforcedAdmin : null);
                return;
            }
            return;
        }
        this.mAddUser.setVisible(false);
    }

    private int getMaxRealUsers() {
        int maxSupportedUsers = UserManager.getMaxSupportedUsers() + 1;
        Iterator it = this.mUserManager.getUsers().iterator();
        int i = 0;
        while (it.hasNext()) {
            if (((UserInfo) it.next()).isManagedProfile()) {
                i++;
            }
        }
        return maxSupportedUsers - i;
    }

    private void loadIconsAsync(List<Integer> list) {
        new AsyncTask<List<Integer>, Void, Void>() { // from class: com.android.car.developeroptions.users.UserSettings.13
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Void r1) {
                UserSettings.this.updateUserList();
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(List<Integer>... listArr) {
                Iterator<Integer> it = listArr[0].iterator();
                while (it.hasNext()) {
                    int iIntValue = it.next().intValue();
                    Bitmap userIcon = UserSettings.this.mUserManager.getUserIcon(iIntValue);
                    if (userIcon == null) {
                        userIcon = UserSettings.getDefaultUserIconAsBitmap(UserSettings.this.getContext().getResources(), iIntValue);
                    }
                    UserSettings.this.mUserIcons.append(iIntValue, userIcon);
                }
                return null;
            }
        }.execute(list);
    }

    private Drawable getEncircledDefaultIcon() {
        if (this.mDefaultIconDrawable == null) {
            this.mDefaultIconDrawable = encircle(getDefaultUserIconAsBitmap(getContext().getResources(), -10000));
        }
        return this.mDefaultIconDrawable;
    }

    private void setPhotoId(Preference preference, UserInfo userInfo) {
        Bitmap bitmap = this.mUserIcons.get(userInfo.id);
        if (bitmap != null) {
            preference.setIcon(encircle(bitmap));
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        if (preference == this.mMePreference) {
            if (this.mUserCaps.mIsGuest) {
                showDialog(8);
                return true;
            }
            if (this.mUserManager.isLinkedUser()) {
                onManageUserClicked(UserHandle.myUserId(), false);
            } else {
                showDialog(9);
            }
        } else if (preference instanceof UserPreference) {
            UserInfo userInfo = this.mUserManager.getUserInfo(((UserPreference) preference).getUserId());
            if (!isInitialized(userInfo)) {
                Handler handler = this.mHandler;
                handler.sendMessage(handler.obtainMessage(2, userInfo.id, userInfo.serialNumber));
            }
        } else if (preference == this.mAddUser) {
            if (this.mUserCaps.mCanAddRestrictedProfile) {
                showDialog(6);
            } else {
                onAddUserClicked(1);
            }
        }
        return false;
    }

    private boolean isInitialized(UserInfo userInfo) {
        return (userInfo.flags & 16) != 0;
    }

    private Drawable encircle(Bitmap bitmap) {
        return CircleFramedDrawable.getInstance(getActivity(), bitmap);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getTag() instanceof UserPreference) {
            int userId = ((UserPreference) view.getTag()).getUserId();
            int id = view.getId();
            if (id == R.id.manage_user) {
                onManageUserClicked(userId, false);
                return;
            }
            if (id != R.id.trash_user) {
                return;
            }
            RestrictedLockUtils.EnforcedAdmin enforcedAdminCheckIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(getContext(), "no_remove_user", UserHandle.myUserId());
            if (enforcedAdminCheckIfRestrictionEnforced != null) {
                RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getContext(), enforcedAdminCheckIfRestrictionEnforced);
            } else {
                onRemoveUserClicked(userId);
            }
        }
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        synchronized (this.mUserLock) {
            this.mRemovingUserId = -1;
            updateUserList();
        }
    }

    @Override // com.android.car.developeroptions.users.EditUserInfoController.OnContentChangedCallback
    public void onPhotoChanged(Drawable drawable) {
        this.mMePreference.setIcon(drawable);
    }

    @Override // com.android.car.developeroptions.users.EditUserInfoController.OnContentChangedCallback
    public void onLabelChanged(CharSequence charSequence) {
        this.mMePreference.setTitle(charSequence);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bitmap getDefaultUserIconAsBitmap(Resources resources, int i) {
        Bitmap bitmap = sDarkDefaultUserBitmapCache.get(i);
        if (bitmap != null) {
            return bitmap;
        }
        Bitmap bitmapConvertToBitmap = UserIcons.convertToBitmap(UserIcons.getDefaultUserIcon(resources, i, false));
        sDarkDefaultUserBitmapCache.put(i, bitmapConvertToBitmap);
        return bitmapConvertToBitmap;
    }

    static boolean assignDefaultPhoto(Context context, int i) {
        if (context == null) {
            return false;
        }
        ((UserManager) context.getSystemService("user")).setUserIcon(i, getDefaultUserIconAsBitmap(context.getResources(), i));
        return true;
    }

    static void copyMeProfilePhoto(Context context, UserInfo userInfo) {
        Uri uri = ContactsContract.Profile.CONTENT_URI;
        int iMyUserId = userInfo != null ? userInfo.id : UserHandle.myUserId();
        InputStream inputStreamOpenContactPhotoInputStream = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(), uri, true);
        if (inputStreamOpenContactPhotoInputStream == null) {
            assignDefaultPhoto(context, iMyUserId);
            return;
        }
        ((UserManager) context.getSystemService("user")).setUserIcon(iMyUserId, BitmapFactory.decodeStream(inputStreamOpenContactPhotoInputStream));
        try {
            inputStreamOpenContactPhotoInputStream.close();
        } catch (IOException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class SummaryProvider implements SummaryLoader.SummaryProvider {
        private final Context mContext;
        private final SummaryLoader mSummaryLoader;

        public SummaryProvider(Context context, SummaryLoader summaryLoader) {
            this.mContext = context;
            this.mSummaryLoader = summaryLoader;
        }

        @Override // com.android.car.developeroptions.dashboard.SummaryLoader.SummaryProvider
        public void setListening(boolean z) {
            if (z) {
                this.mSummaryLoader.setSummary(this, this.mContext.getString(R.string.users_summary, ((UserManager) this.mContext.getSystemService(UserManager.class)).getUserInfo(UserHandle.myUserId()).name));
            }
        }
    }

    static /* synthetic */ SummaryLoader.SummaryProvider lambda$static$1(Activity activity, SummaryLoader summaryLoader) {
        return new SummaryProvider(activity, summaryLoader);
    }
}
