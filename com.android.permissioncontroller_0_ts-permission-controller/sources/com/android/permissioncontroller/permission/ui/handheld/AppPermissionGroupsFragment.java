package com.android.permissioncontroller.permission.ui.handheld;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.icu.text.ListFormatter;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;
import com.android.permissioncontroller.Constants;
import com.android.permissioncontroller.PermissionControllerStatsLog;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.model.livedatatypes.AutoRevokeState;
import com.android.permissioncontroller.permission.ui.Category;
import com.android.permissioncontroller.permission.ui.model.AppPermissionGroupsViewModel;
import com.android.permissioncontroller.permission.ui.model.AppPermissionGroupsViewModelFactory;
import com.android.permissioncontroller.permission.utils.KotlinUtils;
import com.android.permissioncontroller.permission.utils.Utils;
import com.android.settingslib.HelpUtils;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes8.dex */
public final class AppPermissionGroupsFragment extends SettingsWithLargeHeader {
    private static final String AUTO_REVOKE_CATEGORY_KEY = "_AUTO_REVOKE_KEY";
    private static final String AUTO_REVOKE_SUMMARY_KEY = "_AUTO_REVOKE_SUMMARY_KEY";
    private static final String AUTO_REVOKE_SWITCH_KEY = "_AUTO_REVOKE_SWITCH_KEY";
    static final String EXTRA_HIDE_INFO_BUTTON = "hideInfoButton";
    private static final String IS_SYSTEM_PERMS_SCREEN = "_is_system_screen";
    private static final String LOG_TAG = AppPermissionGroupsFragment.class.getSimpleName();
    private Collator mCollator;
    private boolean mIsFirstLoad;
    private boolean mIsSystemPermsScreen;
    private String mPackageName;
    private UserHandle mUser;
    private AppPermissionGroupsViewModel mViewModel;

    public static Bundle createArgs(String packageName, UserHandle userHandle, long sessionId, boolean isSystemPermsScreen) {
        Bundle arguments = new Bundle();
        arguments.putString("android.intent.extra.PACKAGE_NAME", packageName);
        arguments.putParcelable("android.intent.extra.USER", userHandle);
        arguments.putLong(Constants.EXTRA_SESSION_ID, sessionId);
        arguments.putBoolean(IS_SYSTEM_PERMS_SCREEN, isSystemPermsScreen);
        return arguments;
    }

    public static Bundle createArgs(String packageName, UserHandle userHandle, long sessionId) {
        return createArgs(packageName, userHandle, sessionId, true);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.mIsFirstLoad = true;
        ActionBar ab = getActivity().getActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        this.mPackageName = getArguments().getString("android.intent.extra.PACKAGE_NAME");
        this.mUser = (UserHandle) getArguments().getParcelable("android.intent.extra.USER");
        this.mIsSystemPermsScreen = getArguments().getBoolean(IS_SYSTEM_PERMS_SCREEN, true);
        AppPermissionGroupsViewModelFactory factory = new AppPermissionGroupsViewModelFactory(this.mPackageName, this.mUser, getArguments().getLong(Constants.EXTRA_SESSION_ID, 0L));
        AppPermissionGroupsViewModel appPermissionGroupsViewModel = (AppPermissionGroupsViewModel) new ViewModelProvider(this, factory).get(AppPermissionGroupsViewModel.class);
        this.mViewModel = appPermissionGroupsViewModel;
        appPermissionGroupsViewModel.getPackagePermGroupsLiveData().observe(this, new Observer() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AppPermissionGroupsFragment$vJzeAIwcliaZEU-25pQ_4sZa6SA
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.updatePreferences((Map) obj);
            }
        });
        this.mViewModel.getAutoRevokeLiveData().observe(this, new Observer() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AppPermissionGroupsFragment$vT6-M7BjSM4GxSkpQjeYvQZENrc
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.setAutoRevokeToggleState((AutoRevokeState) obj);
            }
        });
        this.mCollator = Collator.getInstance(getContext().getResources().getConfiguration().getLocales().get(0));
        if (this.mViewModel.getPackagePermGroupsLiveData().getValue() != null) {
            updatePreferences(this.mViewModel.getPackagePermGroupsLiveData().getValue());
        }
    }

    @Override // com.android.permissioncontroller.permission.ui.handheld.SettingsWithLargeHeader, com.android.permissioncontroller.permission.ui.handheld.PermissionsFrameFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle(R.string.app_permissions);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == 2) {
            this.mViewModel.showAllPermissions(this, AllAppPermissionsFragment.createArgs(this.mPackageName, this.mUser));
            return true;
        }
        if (itemId == 16908332) {
            UtilsKt.pressBack(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override // com.android.permissioncontroller.permission.ui.handheld.PermissionsFrameFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (this.mIsSystemPermsScreen) {
            menu.add(0, 2, 0, R.string.all_permissions);
            HelpUtils.prepareHelpMenuItem(getActivity(), menu, R.string.help_app_permissions, getClass().getName());
        }
    }

    private static void bindUi(SettingsWithLargeHeader fragment, String packageName, UserHandle user) {
        Activity activity = fragment.getActivity();
        Intent infoIntent = null;
        if (!activity.getIntent().getBooleanExtra(EXTRA_HIDE_INFO_BUTTON, false)) {
            infoIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts("package", packageName, null));
        }
        Drawable icon = KotlinUtils.INSTANCE.getBadgedPackageIcon(activity.getApplication(), packageName, user);
        fragment.setHeader(icon, KotlinUtils.INSTANCE.getPackageLabel(activity.getApplication(), packageName, user), infoIntent, user, false);
    }

    private void createPreferenceScreenIfNeeded() {
        if (getPreferenceScreen() == null) {
            addPreferencesFromResource(R.xml.allowed_denied);
            addAutoRevokePreferences(getPreferenceScreen());
            bindUi(this, this.mPackageName, this.mUser);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePreferences(Map<Category, List<AppPermissionGroupsViewModel.GroupUiInfo>> groupMap) {
        PreferenceCategory category;
        PreferenceCategory category2;
        int numExtraPerms;
        createPreferenceScreenIfNeeded();
        Context context = getPreferenceManager().getContext();
        if (context != null) {
            if (groupMap != null || !this.mViewModel.getPackagePermGroupsLiveData().getIsInitialized()) {
                boolean z = false;
                findPreference(Category.ALLOWED_FOREGROUND.getCategoryName()).setVisible(false);
                long sessionId = getArguments().getLong(Constants.EXTRA_SESSION_ID, 0L);
                Iterator<Category> it = groupMap.keySet().iterator();
                while (it.hasNext()) {
                    Category grantCategory = it.next();
                    PreferenceCategory category3 = (PreferenceCategory) findPreference(grantCategory.getCategoryName());
                    category3.removeAll();
                    if (!grantCategory.equals(Category.ALLOWED_FOREGROUND)) {
                        category = category3;
                    } else {
                        category3.setVisible(z);
                        category = (PreferenceCategory) findPreference(Category.ALLOWED.getCategoryName());
                    }
                    if (grantCategory.equals(Category.ASK)) {
                        if (groupMap.get(grantCategory).size() == 0) {
                            category.setVisible(z);
                        } else {
                            category.setVisible(true);
                        }
                    }
                    int numExtraPerms2 = 0;
                    for (AppPermissionGroupsViewModel.GroupUiInfo groupInfo : groupMap.get(grantCategory)) {
                        String groupName = groupInfo.getGroupName();
                        int numExtraPerms3 = numExtraPerms2;
                        PreferenceCategory category4 = category;
                        Category grantCategory2 = grantCategory;
                        PermissionControlPreference preference = new PermissionControlPreference(context, this.mPackageName, groupName, this.mUser, AppPermissionGroupsFragment.class.getName(), sessionId, grantCategory.getCategoryName(), true);
                        preference.setTitle(KotlinUtils.INSTANCE.getPermGroupLabel(context, groupName));
                        preference.setIcon(KotlinUtils.INSTANCE.getPermGroupIcon(context, groupName));
                        preference.setKey(groupName);
                        int i = AnonymousClass1.$SwitchMap$com$android$permissioncontroller$permission$ui$model$AppPermissionGroupsViewModel$PermSubtitle[groupInfo.getSubtitle().ordinal()];
                        if (i == 1) {
                            preference.setSummary(R.string.permission_subtitle_only_in_foreground);
                        } else if (i == 2) {
                            preference.setSummary(R.string.permission_subtitle_media_only);
                        } else if (i == 3) {
                            preference.setSummary(R.string.permission_subtitle_all_files);
                        }
                        if (groupInfo.getSubtitle() == AppPermissionGroupsViewModel.PermSubtitle.FOREGROUND_ONLY) {
                            preference.setSummary(R.string.permission_subtitle_only_in_foreground);
                        }
                        if (groupInfo.isSystem() == this.mIsSystemPermsScreen) {
                            category2 = category4;
                            category2.addPreference(preference);
                            numExtraPerms = numExtraPerms3;
                        } else {
                            category2 = category4;
                            if (groupInfo.isSystem()) {
                                numExtraPerms = numExtraPerms3;
                            } else {
                                numExtraPerms2 = numExtraPerms3 + 1;
                                category = category2;
                                grantCategory = grantCategory2;
                            }
                        }
                        numExtraPerms2 = numExtraPerms;
                        category = category2;
                        grantCategory = grantCategory2;
                    }
                    int numExtraPerms4 = numExtraPerms2;
                    PreferenceCategory category5 = category;
                    Category grantCategory3 = grantCategory;
                    int noPermsStringRes = grantCategory3.equals(Category.DENIED) ? R.string.no_permissions_denied : R.string.no_permissions_allowed;
                    if (numExtraPerms4 > 0) {
                        Preference extraPerms = setUpCustomPermissionsScreen(context, numExtraPerms4, grantCategory3.getCategoryName());
                        category5.addPreference(extraPerms);
                    }
                    if (category5.getPreferenceCount() == 0) {
                        setNoPermissionPreference(category5, noPermsStringRes, context);
                    }
                    KotlinUtils.INSTANCE.sortPreferenceGroup(category5, new Function2() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AppPermissionGroupsFragment$1fgtT9LNOcl7_E21n_zuZukIgyE
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return Integer.valueOf(this.f$0.comparePreferences((Preference) obj, (Preference) obj2));
                        }
                    }, false);
                    z = false;
                }
                setAutoRevokeToggleState(this.mViewModel.getAutoRevokeLiveData().getValue());
                if (this.mIsFirstLoad) {
                    logAppPermissionGroupsFragmentView();
                    this.mIsFirstLoad = false;
                    return;
                }
                return;
            }
            Toast.makeText(getActivity(), R.string.app_not_found_dlg_title, 1).show();
            Log.w(LOG_TAG, "invalid package " + this.mPackageName);
            UtilsKt.pressBack(this);
        }
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.ui.handheld.AppPermissionGroupsFragment$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$permissioncontroller$permission$ui$model$AppPermissionGroupsViewModel$PermSubtitle;

        static {
            int[] iArr = new int[AppPermissionGroupsViewModel.PermSubtitle.values().length];
            $SwitchMap$com$android$permissioncontroller$permission$ui$model$AppPermissionGroupsViewModel$PermSubtitle = iArr;
            try {
                iArr[AppPermissionGroupsViewModel.PermSubtitle.FOREGROUND_ONLY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$permissioncontroller$permission$ui$model$AppPermissionGroupsViewModel$PermSubtitle[AppPermissionGroupsViewModel.PermSubtitle.MEDIA_ONLY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$android$permissioncontroller$permission$ui$model$AppPermissionGroupsViewModel$PermSubtitle[AppPermissionGroupsViewModel.PermSubtitle.ALL_FILES.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    private void addAutoRevokePreferences(PreferenceScreen preferenceScreen) {
        Context context = preferenceScreen.getPreferenceManager().getContext();
        PreferenceCategory autoRevokeCategory = new PreferenceCategory(context);
        autoRevokeCategory.setKey(AUTO_REVOKE_CATEGORY_KEY);
        preferenceScreen.addPreference(autoRevokeCategory);
        final SwitchPreference autoRevokeSwitch = new SwitchPreference(context);
        autoRevokeSwitch.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AppPermissionGroupsFragment$hFyaUi7fwk3rc6rOhrr5hw4eqGU
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$addAutoRevokePreferences$0$AppPermissionGroupsFragment(autoRevokeSwitch, preference);
            }
        });
        autoRevokeSwitch.setTitle(R.string.auto_revoke_label);
        autoRevokeSwitch.setKey(AUTO_REVOKE_SWITCH_KEY);
        autoRevokeCategory.addPreference(autoRevokeSwitch);
        Preference autoRevokeSummary = new Preference(context);
        autoRevokeSummary.setIcon(Utils.applyTint(getActivity(), R.drawable.ic_info_outline, android.R.attr.colorControlNormal));
        autoRevokeSummary.setKey(AUTO_REVOKE_SUMMARY_KEY);
        autoRevokeCategory.addPreference(autoRevokeSummary);
    }

    public /* synthetic */ boolean lambda$addAutoRevokePreferences$0$AppPermissionGroupsFragment(SwitchPreference autoRevokeSwitch, Preference preference) {
        this.mViewModel.setAutoRevoke(autoRevokeSwitch.isChecked());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAutoRevokeToggleState(AutoRevokeState state) {
        if (state == null || !this.mViewModel.getPackagePermGroupsLiveData().getIsInitialized() || getListView() == null || getView() == null) {
            return;
        }
        PreferenceCategory autoRevokeCategory = (PreferenceCategory) getPreferenceScreen().findPreference(AUTO_REVOKE_CATEGORY_KEY);
        SwitchPreference autoRevokeSwitch = (SwitchPreference) autoRevokeCategory.findPreference(AUTO_REVOKE_SWITCH_KEY);
        Preference autoRevokeSummary = autoRevokeCategory.findPreference(AUTO_REVOKE_SUMMARY_KEY);
        if (!state.getIsEnabledGlobal() || !state.getShouldShowSwitch()) {
            autoRevokeSwitch.setVisible(false);
            autoRevokeSummary.setVisible(false);
            return;
        }
        autoRevokeSwitch.setVisible(true);
        autoRevokeSummary.setVisible(true);
        autoRevokeSwitch.setChecked(state.getIsEnabledForApp());
        List<String> groupLabels = new ArrayList<>();
        for (String groupName : state.getRevocableGroupNames()) {
            PreferenceCategory category = (PreferenceCategory) getPreferenceScreen().findPreference(Category.ALLOWED.getCategoryName());
            Preference pref = category.findPreference(groupName);
            if (pref != null) {
                groupLabels.add(pref.getTitle().toString());
            }
        }
        groupLabels.sort(this.mCollator);
        if (groupLabels.isEmpty()) {
            autoRevokeSummary.setSummary(R.string.auto_revoke_summary);
        } else {
            autoRevokeSummary.setSummary(getString(R.string.auto_revoke_summary_with_permissions, ListFormatter.getInstance().format(groupLabels)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int comparePreferences(Preference lhs, Preference rhs) {
        String additionalTitle = lhs.getContext().getString(R.string.additional_permissions);
        if (lhs.getTitle().equals(additionalTitle)) {
            return 1;
        }
        if (rhs.getTitle().equals(additionalTitle)) {
            return -1;
        }
        return this.mCollator.compare(lhs.getTitle().toString(), rhs.getTitle().toString());
    }

    private Preference setUpCustomPermissionsScreen(Context context, int count, String category) {
        Preference extraPerms = new Preference(context);
        extraPerms.setIcon(Utils.applyTint(getActivity(), R.drawable.ic_toc, android.R.attr.colorControlNormal));
        extraPerms.setTitle(R.string.additional_permissions);
        extraPerms.setKey(((Object) extraPerms.getTitle()) + category);
        extraPerms.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AppPermissionGroupsFragment$LP0st3ccdhhTmtUZT8FqtFIHSWU
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$setUpCustomPermissionsScreen$1$AppPermissionGroupsFragment(preference);
            }
        });
        extraPerms.setSummary(getResources().getQuantityString(R.plurals.additional_permissions_more, count, Integer.valueOf(count)));
        return extraPerms;
    }

    public /* synthetic */ boolean lambda$setUpCustomPermissionsScreen$1$AppPermissionGroupsFragment(Preference preference) {
        this.mViewModel.showExtraPerms(this, createArgs(this.mPackageName, this.mUser, getArguments().getLong(Constants.EXTRA_SESSION_ID), false));
        return true;
    }

    private void setNoPermissionPreference(PreferenceCategory category, int stringId, Context context) {
        Preference empty = new Preference(context);
        empty.setKey(getString(stringId));
        empty.setTitle(empty.getKey());
        empty.setSelectable(false);
        category.addPreference(empty);
    }

    private void logAppPermissionGroupsFragmentView() {
        int i;
        int numDenied;
        PreferenceCategory denied;
        int category;
        int i2;
        Context context = getPreferenceManager().getContext();
        if (context == null) {
            return;
        }
        String permissionSubtitleOnlyInForeground = context.getString(R.string.permission_subtitle_only_in_foreground);
        long sessionId = getArguments().getLong(Constants.EXTRA_SESSION_ID, 0L);
        long viewId = new Random().nextLong();
        PreferenceCategory allowed = (PreferenceCategory) findPreference(Category.ALLOWED.getCategoryName());
        int numAllowed = allowed.getPreferenceCount();
        int i3 = 0;
        while (i3 < numAllowed) {
            Preference preference = allowed.getPreference(i3);
            if (preference.getTitle().equals(getString(R.string.no_permissions_allowed))) {
                i2 = i3;
            } else {
                if (preference.getSummary() != null && permissionSubtitleOnlyInForeground.contentEquals(preference.getSummary())) {
                    category = 2;
                } else {
                    category = 1;
                }
                i2 = i3;
                int i4 = category;
                logAppPermissionsFragmentViewEntry(sessionId, viewId, preference.getKey(), i4);
            }
            i3 = i2 + 1;
        }
        PreferenceCategory denied2 = (PreferenceCategory) findPreference(Category.DENIED.getCategoryName());
        int numDenied2 = denied2.getPreferenceCount();
        int i5 = 0;
        while (i5 < numDenied2) {
            Preference preference2 = denied2.getPreference(i5);
            if (preference2.getTitle().equals(getString(R.string.no_permissions_denied))) {
                i = i5;
                numDenied = numDenied2;
                denied = denied2;
            } else {
                i = i5;
                numDenied = numDenied2;
                denied = denied2;
                logAppPermissionsFragmentViewEntry(sessionId, viewId, preference2.getKey(), 3);
            }
            i5 = i + 1;
            denied2 = denied;
            numDenied2 = numDenied;
        }
    }

    private void logAppPermissionsFragmentViewEntry(long sessionId, long viewId, String permissionGroupName, int category) {
        Integer uid = KotlinUtils.INSTANCE.getPackageUid(getActivity().getApplication(), this.mPackageName, this.mUser);
        if (uid == null) {
            return;
        }
        PermissionControllerStatsLog.write(PermissionControllerStatsLog.APP_PERMISSIONS_FRAGMENT_VIEWED, sessionId, viewId, permissionGroupName, uid.intValue(), this.mPackageName, category);
        Log.v(LOG_TAG, "AppPermissionFragment view logged with sessionId=" + sessionId + " viewId=" + viewId + " permissionGroupName=" + permissionGroupName + " uid=" + uid + " packageName=" + this.mPackageName + " category=" + category);
    }
}
