package com.android.permissioncontroller.permission.ui.handheld;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.permissioncontroller.Constants;
import com.android.permissioncontroller.PermissionControllerStatsLog;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.ui.Category;
import com.android.permissioncontroller.permission.ui.model.PermissionAppsViewModel;
import com.android.permissioncontroller.permission.ui.model.PermissionAppsViewModelFactory;
import com.android.permissioncontroller.permission.utils.KotlinUtils;
import com.android.permissioncontroller.permission.utils.Utils;
import com.android.settingslib.HelpUtils;
import com.android.settingslib.utils.applications.AppUtils;
import java.text.Collator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import kotlin.Pair;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: classes8.dex */
public final class PermissionAppsFragment extends SettingsWithLargeHeader {
    private static final String CREATION_LOGGED_SYSTEM_PREFS = "_creationLogged";
    private static final String KEY_EMPTY = "_empty";
    private static final String KEY_FOOTER = "_footer";
    private static final String KEY_SHOW_SYSTEM_PREFS = "_showSystem";
    private static final String LOG_TAG = "PermissionAppsFragment";
    private static final int SHOW_LOAD_DELAY_MS = 200;
    private static final String STORAGE_ALLOWED_FULL = "allowed_storage_full";
    private static final String STORAGE_ALLOWED_SCOPED = "allowed_storage_scoped";
    private Collator mCollator;
    private MenuItem mHideSystemMenu;
    private String mPermGroupName;
    private MenuItem mShowSystemMenu;
    private PermissionAppsViewModel mViewModel;

    public static Bundle createArgs(String permGroupName, long sessionId) {
        Bundle arguments = new Bundle();
        arguments.putString("android.intent.extra.PERMISSION_GROUP_NAME", permGroupName);
        arguments.putLong(Constants.EXTRA_SESSION_ID, sessionId);
        return arguments;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String string = getArguments().getString("android.intent.extra.PERMISSION_GROUP_NAME");
        this.mPermGroupName = string;
        if (string == null) {
            this.mPermGroupName = getArguments().getString("android.intent.extra.PERMISSION_NAME");
        }
        this.mCollator = Collator.getInstance(getContext().getResources().getConfiguration().getLocales().get(0));
        PermissionAppsViewModelFactory factory = new PermissionAppsViewModelFactory(getActivity().getApplication(), this.mPermGroupName, this, new Bundle());
        PermissionAppsViewModel permissionAppsViewModel = (PermissionAppsViewModel) new ViewModelProvider(this, factory).get(PermissionAppsViewModel.class);
        this.mViewModel = permissionAppsViewModel;
        permissionAppsViewModel.getCategorizedAppsLiveData().observe(this, new Observer() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$PermissionAppsFragment$QIi6O7Gu3BDAAxRh73y_NhjWsCw
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.onPackagesLoaded((Map) obj);
            }
        });
        this.mViewModel.getShouldShowSystemLiveData().observe(this, new Observer() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$PermissionAppsFragment$gd9479zWPQ8S28KCFXaAV2CHuaY
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.updateMenu((Boolean) obj);
            }
        });
        this.mViewModel.getHasSystemAppsLiveData().observe(this, new Observer() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$PermissionAppsFragment$JVDTUGi5-lwwuiXh_fDDhN6JHTU
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$onCreate$0$PermissionAppsFragment((Boolean) obj);
            }
        });
        if (!this.mViewModel.arePackagesLoaded()) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$PermissionAppsFragment$0i8r5Q-Dcc8OLlP8d29Bxz6i970
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCreate$1$PermissionAppsFragment();
                }
            }, 200L);
        }
        setHasOptionsMenu(true);
        ActionBar ab = getActivity().getActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    public /* synthetic */ void lambda$onCreate$0$PermissionAppsFragment(Boolean hasSystem) {
        getActivity().invalidateOptionsMenu();
    }

    public /* synthetic */ void lambda$onCreate$1$PermissionAppsFragment() {
        if (!this.mViewModel.arePackagesLoaded()) {
            setLoading(true, false);
        }
    }

    @Override // com.android.permissioncontroller.permission.ui.handheld.PermissionsFrameFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (this.mViewModel.getHasSystemAppsLiveData().getValue().booleanValue()) {
            this.mShowSystemMenu = menu.add(0, 3, 0, R.string.menu_show_system);
            this.mHideSystemMenu = menu.add(0, 4, 0, R.string.menu_hide_system);
            updateMenu(this.mViewModel.getShouldShowSystemLiveData().getValue());
        }
        HelpUtils.prepareHelpMenuItem(getActivity(), menu, R.string.help_app_permissions, getClass().getName());
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == 3 || itemId == 4) {
            this.mViewModel.updateShowSystem(item.getItemId() == 3);
        } else if (itemId == 16908332) {
            this.mViewModel.updateShowSystem(false);
            UtilsKt.pressBack(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMenu(Boolean showSystem) {
        if (showSystem == null) {
            showSystem = false;
        }
        MenuItem menuItem = this.mShowSystemMenu;
        if (menuItem != null && this.mHideSystemMenu != null) {
            menuItem.setVisible(!showSystem.booleanValue());
            this.mHideSystemMenu.setVisible(showSystem.booleanValue());
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindUi(this, this.mPermGroupName);
    }

    private static void bindUi(SettingsWithLargeHeader fragment, String groupName) {
        Context context = fragment.getContext();
        if (context == null || fragment.getActivity() == null) {
            return;
        }
        Drawable icon = KotlinUtils.INSTANCE.getPermGroupIcon(context, groupName);
        CharSequence label = KotlinUtils.INSTANCE.getPermGroupLabel(context, groupName);
        CharSequence description = KotlinUtils.INSTANCE.getPermGroupDescription(context, groupName);
        fragment.setHeader(icon, label, null, null, true);
        fragment.setSummary(Utils.getPermissionGroupDescriptionString(fragment.getActivity(), groupName, description), null);
        fragment.getActivity().setTitle(label);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPackagesLoaded(Map<Category, List<Pair<String, UserHandle>>> categories) {
        boolean z;
        boolean z2;
        PreferenceCategory category;
        Map<String, Preference> existingPrefs;
        PreferenceCategory category2;
        Category grantCategory;
        PreferenceCategory category3;
        Map<Category, List<Pair<String, UserHandle>>> map = categories;
        boolean isStorage = this.mPermGroupName.equals("android.permission-group.STORAGE");
        boolean z3 = false;
        if (getPreferenceScreen() == null) {
            if (isStorage) {
                addPreferencesFromResource(R.xml.allowed_denied_storage);
            } else {
                addPreferencesFromResource(R.xml.allowed_denied);
            }
            findPreference(Category.ALLOWED_FOREGROUND.getCategoryName()).setVisible(false);
        }
        Context context = getPreferenceManager().getContext();
        if (context == null || getActivity() == null || map == null) {
            return;
        }
        Map<String, Preference> existingPrefs2 = new ArrayMap<>();
        int i = 0;
        while (true) {
            z = true;
            if (i >= getPreferenceScreen().getPreferenceCount()) {
                break;
            }
            PreferenceCategory category4 = (PreferenceCategory) getPreferenceScreen().getPreference(i);
            category4.setOrderingAsAdded(true);
            int numPreferences = category4.getPreferenceCount();
            for (int j = 0; j < numPreferences; j++) {
                Preference preference = category4.getPreference(j);
                existingPrefs2.put(preference.getKey(), preference);
            }
            category4.removeAll();
            i++;
        }
        long viewIdForLogging = new Random().nextLong();
        final long sessionId = getArguments().getLong(Constants.EXTRA_SESSION_ID, 0L);
        Boolean showAlways = this.mViewModel.getShowAllowAlwaysStringLiveData().getValue();
        if (!isStorage) {
            if (showAlways == null || !showAlways.booleanValue()) {
                findPreference(Category.ALLOWED.getCategoryName()).setTitle(R.string.allowed_header);
            } else {
                findPreference(Category.ALLOWED.getCategoryName()).setTitle(R.string.allowed_always_header);
            }
        }
        Iterator<Category> it = categories.keySet().iterator();
        while (it.hasNext()) {
            Category grantCategory2 = it.next();
            List<Pair<String, UserHandle>> packages = map.get(grantCategory2);
            PreferenceCategory category5 = (PreferenceCategory) findPreference(grantCategory2.getCategoryName());
            if (packages.size() == 0 && (!isStorage || !grantCategory2.equals(Category.ALLOWED))) {
                Preference empty = new Preference(context);
                empty.setSelectable(z3);
                empty.setKey(category5.getKey() + KEY_EMPTY);
                if (grantCategory2.equals(Category.ALLOWED)) {
                    empty.setTitle(getString(R.string.no_apps_allowed));
                } else if (!grantCategory2.equals(Category.ALLOWED_FOREGROUND) && !grantCategory2.equals(Category.ASK)) {
                    empty.setTitle(getString(R.string.no_apps_denied));
                } else {
                    category5.setVisible(z3);
                }
                category5.addPreference(empty);
            } else {
                if (grantCategory2.equals(Category.ALLOWED_FOREGROUND) || grantCategory2.equals(Category.ASK)) {
                    category5.setVisible(z);
                }
                for (Pair<String, UserHandle> packageUserLabel : packages) {
                    final String packageName = packageUserLabel.getFirst();
                    final UserHandle user = packageUserLabel.getSecond();
                    String key = user + packageName;
                    if (isStorage && grantCategory2.equals(Category.ALLOWED)) {
                        if (this.mViewModel.packageHasFullStorage(packageName, user)) {
                            category3 = (PreferenceCategory) findPreference(STORAGE_ALLOWED_FULL);
                        } else {
                            category3 = (PreferenceCategory) findPreference(STORAGE_ALLOWED_SCOPED);
                        }
                        category = category3;
                    } else {
                        category = category5;
                    }
                    Preference existingPref = existingPrefs2.get(key);
                    if (existingPref != null) {
                        category.addPreference(existingPref);
                        category2 = category;
                        grantCategory = grantCategory2;
                        existingPrefs = existingPrefs2;
                    } else {
                        SmartIconLoadPackagePermissionPreference pref = new SmartIconLoadPackagePermissionPreference(getActivity().getApplication(), packageName, user, context);
                        pref.setKey(key);
                        pref.setTitle(KotlinUtils.INSTANCE.getPackageLabel(getActivity().getApplication(), packageName, user));
                        PreferenceCategory category6 = category;
                        existingPrefs = existingPrefs2;
                        final Category category7 = grantCategory2;
                        pref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$PermissionAppsFragment$4tzAj5fNJFFRCkrvnVovsZE_GMM
                            @Override // androidx.preference.Preference.OnPreferenceClickListener
                            public final boolean onPreferenceClick(Preference preference2) {
                                return this.f$0.lambda$onPackagesLoaded$2$PermissionAppsFragment(packageName, user, sessionId, category7, preference2);
                            }
                        });
                        pref.setTitleContentDescription(AppUtils.getAppContentDescription(context, packageName, user.getIdentifier()));
                        category6.addPreference(pref);
                        if (this.mViewModel.getCreationLogged()) {
                            category2 = category6;
                            grantCategory = grantCategory2;
                        } else {
                            category2 = category6;
                            grantCategory = grantCategory2;
                            logPermissionAppsFragmentCreated(packageName, user, viewIdForLogging, grantCategory2.equals(Category.ALLOWED), grantCategory2.equals(Category.ALLOWED_FOREGROUND), grantCategory2.equals(Category.DENIED));
                        }
                    }
                    grantCategory2 = grantCategory;
                    existingPrefs2 = existingPrefs;
                    category5 = category2;
                }
                Category grantCategory3 = grantCategory2;
                Map<String, Preference> existingPrefs3 = existingPrefs2;
                if (!isStorage || !grantCategory3.equals(Category.ALLOWED)) {
                    z2 = false;
                    KotlinUtils.INSTANCE.sortPreferenceGroup(category5, new Function2() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$PermissionAppsFragment$MmiPWyNXHQ9bucvjKu25L7wah6A
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return Integer.valueOf(this.f$0.comparePreference((Preference) obj, (Preference) obj2));
                        }
                    }, false);
                } else {
                    PreferenceCategory full = (PreferenceCategory) findPreference(STORAGE_ALLOWED_FULL);
                    PreferenceCategory scoped = (PreferenceCategory) findPreference(STORAGE_ALLOWED_SCOPED);
                    if (full.getPreferenceCount() == 0) {
                        Preference empty2 = new Preference(context);
                        empty2.setSelectable(false);
                        empty2.setKey("allowed_storage_full_empty");
                        empty2.setTitle(getString(R.string.no_apps_allowed_full));
                        full.addPreference(empty2);
                    }
                    if (scoped.getPreferenceCount() == 0) {
                        Preference empty3 = new Preference(context);
                        empty3.setSelectable(false);
                        empty3.setKey("allowed_storage_full_empty");
                        empty3.setTitle(getString(R.string.no_apps_allowed_scoped));
                        scoped.addPreference(empty3);
                    }
                    z2 = false;
                    KotlinUtils.INSTANCE.sortPreferenceGroup(full, new Function2() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$PermissionAppsFragment$MmiPWyNXHQ9bucvjKu25L7wah6A
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return Integer.valueOf(this.f$0.comparePreference((Preference) obj, (Preference) obj2));
                        }
                    }, false);
                    KotlinUtils.INSTANCE.sortPreferenceGroup(scoped, new Function2() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$PermissionAppsFragment$MmiPWyNXHQ9bucvjKu25L7wah6A
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return Integer.valueOf(this.f$0.comparePreference((Preference) obj, (Preference) obj2));
                        }
                    }, false);
                }
                map = categories;
                z3 = z2;
                existingPrefs2 = existingPrefs3;
                z = true;
            }
        }
        this.mViewModel.setCreationLogged(true);
        setLoading(z3, true);
    }

    public /* synthetic */ boolean lambda$onPackagesLoaded$2$PermissionAppsFragment(String packageName, UserHandle user, long sessionId, Category grantCategory, Preference p) {
        this.mViewModel.navigateToAppPermission(this, packageName, user, AppPermissionFragment.createArgs(packageName, null, this.mPermGroupName, user, getClass().getName(), sessionId, grantCategory.getCategoryName()));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int comparePreference(Preference lhs, Preference rhs) {
        int result = this.mCollator.compare(lhs.getTitle().toString(), rhs.getTitle().toString());
        if (result == 0) {
            return lhs.getKey().compareTo(rhs.getKey());
        }
        return result;
    }

    private void logPermissionAppsFragmentCreated(String packageName, UserHandle user, long viewId, boolean isAllowed, boolean isAllowedForeground, boolean isDenied) {
        int category;
        long sessionId = getArguments().getLong(Constants.EXTRA_SESSION_ID, 0L);
        if (isAllowed) {
            category = 1;
        } else if (isAllowedForeground) {
            category = 2;
        } else if (!isDenied) {
            category = 0;
        } else {
            category = 3;
        }
        Integer uid = KotlinUtils.INSTANCE.getPackageUid(getActivity().getApplication(), packageName, user);
        if (uid == null) {
            return;
        }
        PermissionControllerStatsLog.write(PermissionControllerStatsLog.PERMISSION_APPS_FRAGMENT_VIEWED, sessionId, viewId, this.mPermGroupName, uid.intValue(), packageName, category);
        Log.v(LOG_TAG, "PermissionAppsFragment created with sessionId=" + sessionId + " permissionGroupName=" + this.mPermGroupName + " appUid=" + uid + " packageName=" + packageName + " category=" + category);
    }
}
