package com.android.car.developeroptions.users;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.RestrictionEntry;
import android.content.RestrictionsManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.ListPreference;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.Utils;
import com.android.settingslib.users.AppRestrictionsHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/* JADX INFO: loaded from: classes.dex */
public class AppRestrictionsFragment extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener, View.OnClickListener, Preference.OnPreferenceClickListener, AppRestrictionsHelper.OnDisableUiForPackageListener {
    private static final String TAG = AppRestrictionsFragment.class.getSimpleName();
    private PreferenceGroup mAppList;
    private boolean mAppListChanged;
    private AsyncTask mAppLoadingTask;
    private AppRestrictionsHelper mHelper;
    protected IPackageManager mIPm;
    private boolean mNewUser;
    protected PackageManager mPackageManager;
    protected boolean mRestrictedProfile;
    private PackageInfo mSysPackageInfo;
    protected UserHandle mUser;
    protected UserManager mUserManager;
    private boolean mFirstTime = true;
    private int mCustomRequestCode = 1000;
    private HashMap<Integer, AppRestrictionsPreference> mCustomRequestMap = new HashMap<>();
    private BroadcastReceiver mUserBackgrounding = new BroadcastReceiver() { // from class: com.android.car.developeroptions.users.AppRestrictionsFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (AppRestrictionsFragment.this.mAppListChanged) {
                AppRestrictionsFragment.this.mHelper.applyUserAppsStates(AppRestrictionsFragment.this);
            }
        }
    };
    private BroadcastReceiver mPackageObserver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.users.AppRestrictionsFragment.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            AppRestrictionsFragment.this.onPackageChanged(intent);
        }
    };

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 97;
    }

    static class AppRestrictionsPreference extends SwitchPreference {
        private boolean hasSettings;
        private boolean immutable;
        private View.OnClickListener listener;
        private List<Preference> mChildren;
        private boolean panelOpen;
        private ArrayList<RestrictionEntry> restrictions;

        AppRestrictionsPreference(Context context, View.OnClickListener onClickListener) {
            super(context);
            this.mChildren = new ArrayList();
            setLayoutResource(R.layout.preference_app_restrictions);
            this.listener = onClickListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSettingsEnabled(boolean z) {
            this.hasSettings = z;
        }

        void setRestrictions(ArrayList<RestrictionEntry> arrayList) {
            this.restrictions = arrayList;
        }

        void setImmutable(boolean z) {
            this.immutable = z;
        }

        boolean isImmutable() {
            return this.immutable;
        }

        ArrayList<RestrictionEntry> getRestrictions() {
            return this.restrictions;
        }

        boolean isPanelOpen() {
            return this.panelOpen;
        }

        void setPanelOpen(boolean z) {
            this.panelOpen = z;
        }

        @Override // androidx.preference.SwitchPreference, androidx.preference.Preference
        public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
            super.onBindViewHolder(preferenceViewHolder);
            View viewFindViewById = preferenceViewHolder.findViewById(R.id.app_restrictions_settings);
            viewFindViewById.setVisibility(this.hasSettings ? 0 : 8);
            preferenceViewHolder.findViewById(R.id.settings_divider).setVisibility(this.hasSettings ? 0 : 8);
            viewFindViewById.setOnClickListener(this.listener);
            viewFindViewById.setTag(this);
            View viewFindViewById2 = preferenceViewHolder.findViewById(R.id.app_restrictions_pref);
            viewFindViewById2.setOnClickListener(this.listener);
            viewFindViewById2.setTag(this);
            ViewGroup viewGroup = (ViewGroup) preferenceViewHolder.findViewById(android.R.id.widget_frame);
            viewGroup.setEnabled(!isImmutable());
            if (viewGroup.getChildCount() > 0) {
                final Switch r6 = (Switch) viewGroup.getChildAt(0);
                r6.setEnabled(!isImmutable());
                r6.setTag(this);
                r6.setClickable(true);
                r6.setFocusable(true);
                r6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.android.car.developeroptions.users.AppRestrictionsFragment.AppRestrictionsPreference.1
                    @Override // android.widget.CompoundButton.OnCheckedChangeListener
                    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        AppRestrictionsPreference.this.listener.onClick(r6);
                    }
                });
            }
        }
    }

    protected void init(Bundle bundle) {
        if (bundle != null) {
            this.mUser = new UserHandle(bundle.getInt("user_id"));
        } else {
            Bundle arguments = getArguments();
            if (arguments != null) {
                if (arguments.containsKey("user_id")) {
                    this.mUser = new UserHandle(arguments.getInt("user_id"));
                }
                this.mNewUser = arguments.getBoolean("new_user", false);
            }
        }
        if (this.mUser == null) {
            this.mUser = Process.myUserHandle();
        }
        this.mHelper = new AppRestrictionsHelper(getContext(), this.mUser);
        this.mPackageManager = getActivity().getPackageManager();
        this.mIPm = IPackageManager.Stub.asInterface(ServiceManager.getService("package"));
        UserManager userManager = (UserManager) getActivity().getSystemService("user");
        this.mUserManager = userManager;
        this.mRestrictedProfile = userManager.getUserInfo(this.mUser.getIdentifier()).isRestricted();
        try {
            this.mSysPackageInfo = this.mPackageManager.getPackageInfo("android", 64);
        } catch (PackageManager.NameNotFoundException unused) {
        }
        addPreferencesFromResource(R.xml.app_restrictions);
        PreferenceGroup appPreferenceGroup = getAppPreferenceGroup();
        this.mAppList = appPreferenceGroup;
        appPreferenceGroup.setOrderingAsAdded(false);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("user_id", this.mUser.getIdentifier());
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(this.mUserBackgrounding, new IntentFilter("android.intent.action.USER_BACKGROUND"));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        getActivity().registerReceiver(this.mPackageObserver, intentFilter);
        this.mAppListChanged = false;
        AsyncTask asyncTask = this.mAppLoadingTask;
        if (asyncTask == null || asyncTask.getStatus() == AsyncTask.Status.FINISHED) {
            this.mAppLoadingTask = new AppLoadingTask().execute(new Void[0]);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mNewUser = false;
        getActivity().unregisterReceiver(this.mUserBackgrounding);
        getActivity().unregisterReceiver(this.mPackageObserver);
        if (this.mAppListChanged) {
            new AsyncTask<Void, Void, Void>() { // from class: com.android.car.developeroptions.users.AppRestrictionsFragment.3
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public Void doInBackground(Void... voidArr) {
                    AppRestrictionsFragment.this.mHelper.applyUserAppsStates(AppRestrictionsFragment.this);
                    return null;
                }
            }.execute(new Void[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPackageChanged(Intent intent) {
        String action = intent.getAction();
        AppRestrictionsPreference appRestrictionsPreference = (AppRestrictionsPreference) findPreference(getKeyForPackage(intent.getData().getSchemeSpecificPart()));
        if (appRestrictionsPreference == null) {
            return;
        }
        if (!("android.intent.action.PACKAGE_ADDED".equals(action) && appRestrictionsPreference.isChecked()) && (!"android.intent.action.PACKAGE_REMOVED".equals(action) || appRestrictionsPreference.isChecked())) {
            return;
        }
        appRestrictionsPreference.setEnabled(true);
    }

    protected PreferenceGroup getAppPreferenceGroup() {
        return getPreferenceScreen();
    }

    @Override // com.android.settingslib.users.AppRestrictionsHelper.OnDisableUiForPackageListener
    public void onDisableUiForPackage(String str) {
        AppRestrictionsPreference appRestrictionsPreference = (AppRestrictionsPreference) findPreference(getKeyForPackage(str));
        if (appRestrictionsPreference != null) {
            appRestrictionsPreference.setEnabled(false);
        }
    }

    private class AppLoadingTask extends AsyncTask<Void, Void, Void> {
        private AppLoadingTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            AppRestrictionsFragment.this.mHelper.fetchAndMergeApps();
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            AppRestrictionsFragment.this.populateApps();
        }
    }

    private boolean isPlatformSigned(PackageInfo packageInfo) {
        Signature[] signatureArr;
        return (packageInfo == null || (signatureArr = packageInfo.signatures) == null || !this.mSysPackageInfo.signatures[0].equals(signatureArr[0])) ? false : true;
    }

    private boolean isAppEnabledForUser(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        return (applicationInfo.flags & 8388608) != 0 && (applicationInfo.privateFlags & 1) == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void populateApps() {
        PackageInfo packageInfo;
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        PackageManager packageManager = this.mPackageManager;
        IPackageManager iPackageManager = this.mIPm;
        int identifier = this.mUser.getIdentifier();
        if (Utils.getExistingUser(this.mUserManager, this.mUser) == null) {
            return;
        }
        this.mAppList.removeAll();
        List<ResolveInfo> listQueryBroadcastReceivers = packageManager.queryBroadcastReceivers(new Intent("android.intent.action.GET_RESTRICTION_ENTRIES"), 0);
        for (AppRestrictionsHelper.SelectableAppInfo selectableAppInfo : this.mHelper.getVisibleApps()) {
            String str = selectableAppInfo.packageName;
            if (str != null) {
                boolean zEquals = str.equals(activity.getPackageName());
                AppRestrictionsPreference appRestrictionsPreference = new AppRestrictionsPreference(getPrefContext(), this);
                boolean zResolveInfoListHasPackage = resolveInfoListHasPackage(listQueryBroadcastReceivers, str);
                if (zEquals) {
                    addLocationAppRestrictionsPreference(selectableAppInfo, appRestrictionsPreference);
                    this.mHelper.setPackageSelected(str, true);
                } else {
                    try {
                        packageInfo = iPackageManager.getPackageInfo(str, 4194368, identifier);
                    } catch (RemoteException unused) {
                        packageInfo = null;
                    }
                    if (packageInfo != null && (!this.mRestrictedProfile || !isAppUnsupportedInRestrictedProfile(packageInfo))) {
                        Drawable drawable = selectableAppInfo.icon;
                        appRestrictionsPreference.setIcon(drawable != null ? drawable.mutate() : null);
                        appRestrictionsPreference.setChecked(false);
                        appRestrictionsPreference.setTitle(selectableAppInfo.activityName);
                        appRestrictionsPreference.setKey(getKeyForPackage(str));
                        appRestrictionsPreference.setSettingsEnabled(zResolveInfoListHasPackage && selectableAppInfo.masterEntry == null);
                        appRestrictionsPreference.setPersistent(false);
                        appRestrictionsPreference.setOnPreferenceChangeListener(this);
                        appRestrictionsPreference.setOnPreferenceClickListener(this);
                        appRestrictionsPreference.setSummary(getPackageSummary(packageInfo, selectableAppInfo));
                        if (packageInfo.requiredForAllUsers || isPlatformSigned(packageInfo)) {
                            appRestrictionsPreference.setChecked(true);
                            appRestrictionsPreference.setImmutable(true);
                            if (zResolveInfoListHasPackage) {
                                if (selectableAppInfo.masterEntry == null) {
                                    requestRestrictionsForApp(str, appRestrictionsPreference, false);
                                }
                            }
                        } else if (!this.mNewUser && isAppEnabledForUser(packageInfo)) {
                            appRestrictionsPreference.setChecked(true);
                        }
                        if (selectableAppInfo.masterEntry != null) {
                            appRestrictionsPreference.setImmutable(true);
                            appRestrictionsPreference.setChecked(this.mHelper.isPackageSelected(str));
                        }
                        appRestrictionsPreference.setOrder((this.mAppList.getPreferenceCount() + 2) * 100);
                        this.mHelper.setPackageSelected(str, appRestrictionsPreference.isChecked());
                        this.mAppList.addPreference(appRestrictionsPreference);
                    }
                }
            }
        }
        this.mAppListChanged = true;
        if (this.mNewUser && this.mFirstTime) {
            this.mFirstTime = false;
            this.mHelper.applyUserAppsStates(this);
        }
    }

    private String getPackageSummary(PackageInfo packageInfo, AppRestrictionsHelper.SelectableAppInfo selectableAppInfo) {
        AppRestrictionsHelper.SelectableAppInfo selectableAppInfo2 = selectableAppInfo.masterEntry;
        if (selectableAppInfo2 != null) {
            return (!this.mRestrictedProfile || packageInfo.restrictedAccountType == null) ? getString(R.string.user_restrictions_controlled_by, selectableAppInfo.masterEntry.activityName) : getString(R.string.app_sees_restricted_accounts_and_controlled_by, selectableAppInfo2.activityName);
        }
        if (packageInfo.restrictedAccountType != null) {
            return getString(R.string.app_sees_restricted_accounts);
        }
        return null;
    }

    private static boolean isAppUnsupportedInRestrictedProfile(PackageInfo packageInfo) {
        return packageInfo.requiredAccountType != null && packageInfo.restrictedAccountType == null;
    }

    private void addLocationAppRestrictionsPreference(AppRestrictionsHelper.SelectableAppInfo selectableAppInfo, AppRestrictionsPreference appRestrictionsPreference) {
        String str = selectableAppInfo.packageName;
        appRestrictionsPreference.setIcon(R.drawable.ic_preference_location);
        appRestrictionsPreference.setKey(getKeyForPackage(str));
        ArrayList<RestrictionEntry> restrictions = RestrictionUtils.getRestrictions(getActivity(), this.mUser);
        RestrictionEntry restrictionEntry = restrictions.get(0);
        appRestrictionsPreference.setTitle(restrictionEntry.getTitle());
        appRestrictionsPreference.setRestrictions(restrictions);
        appRestrictionsPreference.setSummary(restrictionEntry.getDescription());
        appRestrictionsPreference.setChecked(restrictionEntry.getSelectedState());
        appRestrictionsPreference.setPersistent(false);
        appRestrictionsPreference.setOnPreferenceClickListener(this);
        appRestrictionsPreference.setOrder(100);
        this.mAppList.addPreference(appRestrictionsPreference);
    }

    private String getKeyForPackage(String str) {
        return "pkg_" + str;
    }

    private boolean resolveInfoListHasPackage(List<ResolveInfo> list, String str) {
        Iterator<ResolveInfo> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().activityInfo.packageName.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private void updateAllEntries(String str, boolean z) {
        for (int i = 0; i < this.mAppList.getPreferenceCount(); i++) {
            Preference preference = this.mAppList.getPreference(i);
            if ((preference instanceof AppRestrictionsPreference) && str.equals(preference.getKey())) {
                ((AppRestrictionsPreference) preference).setChecked(z);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getTag() instanceof AppRestrictionsPreference) {
            AppRestrictionsPreference appRestrictionsPreference = (AppRestrictionsPreference) view.getTag();
            if (view.getId() == R.id.app_restrictions_settings) {
                onAppSettingsIconClicked(appRestrictionsPreference);
                return;
            }
            if (appRestrictionsPreference.isImmutable()) {
                return;
            }
            appRestrictionsPreference.setChecked(!appRestrictionsPreference.isChecked());
            String strSubstring = appRestrictionsPreference.getKey().substring(4);
            if (strSubstring.equals(getActivity().getPackageName())) {
                ((RestrictionEntry) appRestrictionsPreference.restrictions.get(0)).setSelectedState(appRestrictionsPreference.isChecked());
                RestrictionUtils.setRestrictions(getActivity(), appRestrictionsPreference.restrictions, this.mUser);
                return;
            }
            this.mHelper.setPackageSelected(strSubstring, appRestrictionsPreference.isChecked());
            if (appRestrictionsPreference.isChecked() && appRestrictionsPreference.hasSettings && appRestrictionsPreference.restrictions == null) {
                requestRestrictionsForApp(strSubstring, appRestrictionsPreference, false);
            }
            this.mAppListChanged = true;
            if (!this.mRestrictedProfile) {
                this.mHelper.applyUserAppState(strSubstring, appRestrictionsPreference.isChecked(), this);
            }
            updateAllEntries(appRestrictionsPreference.getKey(), appRestrictionsPreference.isChecked());
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        String key = preference.getKey();
        if (key == null || !key.contains(";")) {
            return false;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(key, ";");
        String strNextToken = stringTokenizer.nextToken();
        String strNextToken2 = stringTokenizer.nextToken();
        ArrayList<RestrictionEntry> restrictions = ((AppRestrictionsPreference) this.mAppList.findPreference("pkg_" + strNextToken)).getRestrictions();
        if (restrictions != null) {
            for (RestrictionEntry restrictionEntry : restrictions) {
                if (restrictionEntry.getKey().equals(strNextToken2)) {
                    int type = restrictionEntry.getType();
                    if (type == 1) {
                        restrictionEntry.setSelectedState(((Boolean) obj).booleanValue());
                    } else if (type == 2 || type == 3) {
                        String str = (String) obj;
                        restrictionEntry.setSelectedString(str);
                        ((ListPreference) preference).setSummary(findInArray(restrictionEntry.getChoiceEntries(), restrictionEntry.getChoiceValues(), str));
                    } else if (type == 4) {
                        Set set = (Set) obj;
                        String[] strArr = new String[set.size()];
                        set.toArray(strArr);
                        restrictionEntry.setAllSelectedStrings(strArr);
                    }
                    this.mUserManager.setApplicationRestrictions(strNextToken, RestrictionsManager.convertRestrictionsToBundle(restrictions), this.mUser);
                    break;
                }
            }
        }
        return true;
    }

    private void removeRestrictionsForApp(AppRestrictionsPreference appRestrictionsPreference) {
        Iterator it = appRestrictionsPreference.mChildren.iterator();
        while (it.hasNext()) {
            this.mAppList.removePreference((Preference) it.next());
        }
        appRestrictionsPreference.mChildren.clear();
    }

    private void onAppSettingsIconClicked(AppRestrictionsPreference appRestrictionsPreference) {
        if (appRestrictionsPreference.getKey().startsWith("pkg_")) {
            if (appRestrictionsPreference.isPanelOpen()) {
                removeRestrictionsForApp(appRestrictionsPreference);
            } else {
                requestRestrictionsForApp(appRestrictionsPreference.getKey().substring(4), appRestrictionsPreference, true);
            }
            appRestrictionsPreference.setPanelOpen(!appRestrictionsPreference.isPanelOpen());
        }
    }

    private void requestRestrictionsForApp(String str, AppRestrictionsPreference appRestrictionsPreference, boolean z) {
        Bundle applicationRestrictions = this.mUserManager.getApplicationRestrictions(str, this.mUser);
        Intent intent = new Intent("android.intent.action.GET_RESTRICTION_ENTRIES");
        intent.setPackage(str);
        intent.putExtra("android.intent.extra.restrictions_bundle", applicationRestrictions);
        intent.addFlags(32);
        getActivity().sendOrderedBroadcast(intent, null, new RestrictionsResultReceiver(str, appRestrictionsPreference, z), null, -1, null, null);
    }

    class RestrictionsResultReceiver extends BroadcastReceiver {
        boolean invokeIfCustom;
        String packageName;
        AppRestrictionsPreference preference;

        RestrictionsResultReceiver(String str, AppRestrictionsPreference appRestrictionsPreference, boolean z) {
            this.packageName = str;
            this.preference = appRestrictionsPreference;
            this.invokeIfCustom = z;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Bundle resultExtras = getResultExtras(true);
            ArrayList<RestrictionEntry> parcelableArrayList = resultExtras.getParcelableArrayList("android.intent.extra.restrictions_list");
            Intent intent2 = (Intent) resultExtras.getParcelable("android.intent.extra.restrictions_intent");
            if (parcelableArrayList != null && intent2 == null) {
                AppRestrictionsFragment.this.onRestrictionsReceived(this.preference, parcelableArrayList);
                AppRestrictionsFragment appRestrictionsFragment = AppRestrictionsFragment.this;
                if (appRestrictionsFragment.mRestrictedProfile) {
                    appRestrictionsFragment.mUserManager.setApplicationRestrictions(this.packageName, RestrictionsManager.convertRestrictionsToBundle(parcelableArrayList), AppRestrictionsFragment.this.mUser);
                    return;
                }
                return;
            }
            if (intent2 != null) {
                this.preference.setRestrictions(parcelableArrayList);
                if (this.invokeIfCustom && AppRestrictionsFragment.this.isResumed()) {
                    assertSafeToStartCustomActivity(intent2);
                    AppRestrictionsFragment.this.startActivityForResult(intent2, AppRestrictionsFragment.this.generateCustomActivityRequestCode(this.preference));
                }
            }
        }

        private void assertSafeToStartCustomActivity(Intent intent) {
            if (intent.getPackage() == null || !intent.getPackage().equals(this.packageName)) {
                List<ResolveInfo> listQueryIntentActivities = AppRestrictionsFragment.this.mPackageManager.queryIntentActivities(intent, 0);
                if (listQueryIntentActivities.size() != 1) {
                    return;
                }
                if (this.packageName.equals(listQueryIntentActivities.get(0).activityInfo.packageName)) {
                    return;
                }
                throw new SecurityException("Application " + this.packageName + " is not allowed to start activity " + intent);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void onRestrictionsReceived(AppRestrictionsPreference appRestrictionsPreference, ArrayList<RestrictionEntry> arrayList) {
        removeRestrictionsForApp(appRestrictionsPreference);
        int i = 1;
        for (RestrictionEntry restrictionEntry : arrayList) {
            Preference preference = null;
            int type = restrictionEntry.getType();
            if (type == 1) {
                SwitchPreference switchPreference = new SwitchPreference(getPrefContext());
                switchPreference.setTitle(restrictionEntry.getTitle());
                switchPreference.setSummary(restrictionEntry.getDescription());
                switchPreference.setChecked(restrictionEntry.getSelectedState());
                preference = switchPreference;
            } else if (type == 2 || type == 3) {
                ListPreference listPreference = new ListPreference(getPrefContext());
                listPreference.setTitle(restrictionEntry.getTitle());
                String selectedString = restrictionEntry.getSelectedString();
                if (selectedString == null) {
                    selectedString = restrictionEntry.getDescription();
                }
                listPreference.setSummary(findInArray(restrictionEntry.getChoiceEntries(), restrictionEntry.getChoiceValues(), selectedString));
                listPreference.setEntryValues(restrictionEntry.getChoiceValues());
                listPreference.setEntries(restrictionEntry.getChoiceEntries());
                listPreference.setValue(selectedString);
                listPreference.setDialogTitle(restrictionEntry.getTitle());
                preference = listPreference;
            } else if (type == 4) {
                MultiSelectListPreference multiSelectListPreference = new MultiSelectListPreference(getPrefContext());
                multiSelectListPreference.setTitle(restrictionEntry.getTitle());
                multiSelectListPreference.setEntryValues(restrictionEntry.getChoiceValues());
                multiSelectListPreference.setEntries(restrictionEntry.getChoiceEntries());
                HashSet hashSet = new HashSet();
                Collections.addAll(hashSet, restrictionEntry.getAllSelectedStrings());
                multiSelectListPreference.setValues(hashSet);
                multiSelectListPreference.setDialogTitle(restrictionEntry.getTitle());
                preference = multiSelectListPreference;
            }
            if (preference != null) {
                preference.setPersistent(false);
                preference.setOrder(appRestrictionsPreference.getOrder() + i);
                preference.setKey(appRestrictionsPreference.getKey().substring(4) + ";" + restrictionEntry.getKey());
                this.mAppList.addPreference(preference);
                preference.setOnPreferenceChangeListener(this);
                preference.setIcon(R.drawable.empty_icon);
                appRestrictionsPreference.mChildren.add(preference);
                i++;
            }
        }
        appRestrictionsPreference.setRestrictions(arrayList);
        if (i == 1 && appRestrictionsPreference.isImmutable() && appRestrictionsPreference.isChecked()) {
            this.mAppList.removePreference(appRestrictionsPreference);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int generateCustomActivityRequestCode(AppRestrictionsPreference appRestrictionsPreference) {
        int i = this.mCustomRequestCode + 1;
        this.mCustomRequestCode = i;
        this.mCustomRequestMap.put(Integer.valueOf(i), appRestrictionsPreference);
        return this.mCustomRequestCode;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        AppRestrictionsPreference appRestrictionsPreference = this.mCustomRequestMap.get(Integer.valueOf(i));
        if (appRestrictionsPreference == null) {
            Log.w(TAG, "Unknown requestCode " + i);
            return;
        }
        if (i2 == -1) {
            String strSubstring = appRestrictionsPreference.getKey().substring(4);
            ArrayList<RestrictionEntry> parcelableArrayListExtra = intent.getParcelableArrayListExtra("android.intent.extra.restrictions_list");
            Bundle bundleExtra = intent.getBundleExtra("android.intent.extra.restrictions_bundle");
            if (parcelableArrayListExtra != null) {
                appRestrictionsPreference.setRestrictions(parcelableArrayListExtra);
                this.mUserManager.setApplicationRestrictions(strSubstring, RestrictionsManager.convertRestrictionsToBundle(parcelableArrayListExtra), this.mUser);
            } else if (bundleExtra != null) {
                this.mUserManager.setApplicationRestrictions(strSubstring, bundleExtra, this.mUser);
            }
        }
        this.mCustomRequestMap.remove(Integer.valueOf(i));
    }

    private String findInArray(String[] strArr, String[] strArr2, String str) {
        for (int i = 0; i < strArr2.length; i++) {
            if (strArr2[i].equals(str)) {
                return strArr[i];
            }
        }
        return str;
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        if (!preference.getKey().startsWith("pkg_")) {
            return false;
        }
        AppRestrictionsPreference appRestrictionsPreference = (AppRestrictionsPreference) preference;
        if (!appRestrictionsPreference.isImmutable()) {
            String strSubstring = appRestrictionsPreference.getKey().substring(4);
            boolean z = !appRestrictionsPreference.isChecked();
            appRestrictionsPreference.setChecked(z);
            this.mHelper.setPackageSelected(strSubstring, z);
            updateAllEntries(appRestrictionsPreference.getKey(), z);
            this.mAppListChanged = true;
            this.mHelper.applyUserAppState(strSubstring, z, this);
        }
        return true;
    }
}
