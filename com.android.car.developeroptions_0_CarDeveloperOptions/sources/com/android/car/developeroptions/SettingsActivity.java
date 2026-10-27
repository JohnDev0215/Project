package com.android.car.developeroptions;

import android.app.ActionBar;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Icon;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import com.android.car.developeroptions.applications.manageapplications.ManageApplications;
import com.android.car.developeroptions.core.OnActivityResultListener;
import com.android.car.developeroptions.core.SettingsBaseActivity;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.core.gateway.SettingsGateway;
import com.android.car.developeroptions.dashboard.DashboardFeatureProvider;
import com.android.car.developeroptions.homepage.TopLevelSettings;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.widget.SwitchBar;
import com.android.internal.util.ArrayUtils;
import com.android.settingslib.core.instrumentation.Instrumentable;
import com.android.settingslib.core.instrumentation.SharedPreferencesLogger;
import com.android.settingslib.development.DevelopmentSettingsEnabler;
import com.android.settingslib.drawer.DashboardCategory;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SettingsActivity extends SettingsBaseActivity implements PreferenceManager.OnPreferenceTreeClickListener, PreferenceFragmentCompat.OnPreferenceStartFragmentCallback, ButtonBarHandler, FragmentManager.OnBackStackChangedListener {
    private DashboardFeatureProvider mDashboardFeatureProvider;
    private BroadcastReceiver mDevelopmentSettingsListener;
    private String mFragmentClass;
    private CharSequence mInitialTitle;
    private int mInitialTitleResId;
    private Button mNextButton;
    private SwitchBar mSwitchBar;
    private boolean mBatteryPresent = true;
    private BroadcastReceiver mBatteryInfoReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.SettingsActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            boolean zIsBatteryPresent;
            if (!"android.intent.action.BATTERY_CHANGED".equals(intent.getAction()) || SettingsActivity.this.mBatteryPresent == (zIsBatteryPresent = Utils.isBatteryPresent(intent))) {
                return;
            }
            SettingsActivity.this.mBatteryPresent = zIsBatteryPresent;
            SettingsActivity.this.updateTilesList();
        }
    };
    private ArrayList<DashboardCategory> mCategories = new ArrayList<>();

    @Override // androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        return false;
    }

    public SwitchBar getSwitchBar() {
        return this.mSwitchBar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.preference.PreferenceFragmentCompat.OnPreferenceStartFragmentCallback
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat preferenceFragmentCompat, Preference preference) {
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this);
        subSettingLauncher.setDestination(preference.getFragment());
        subSettingLauncher.setArguments(preference.getExtras());
        subSettingLauncher.setSourceMetricsCategory(preferenceFragmentCompat instanceof Instrumentable ? ((Instrumentable) preferenceFragmentCompat).getMetricsCategory() : 0);
        subSettingLauncher.setTitleRes(-1);
        subSettingLauncher.launch();
        return true;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public SharedPreferences getSharedPreferences(String str, int i) {
        if (str.equals(getPackageName() + "_preferences")) {
            return new SharedPreferencesLogger(this, getMetricsTag(), FeatureFactory.getFactory(this).getMetricsFeatureProvider());
        }
        return super.getSharedPreferences(str, i);
    }

    private String getMetricsTag() {
        String name = getClass().getName();
        if (getIntent() != null && getIntent().hasExtra(":settings:show_fragment")) {
            name = getIntent().getStringExtra(":settings:show_fragment");
        }
        return name.startsWith("com.android.car.developeroptions.") ? name.replace("com.android.car.developeroptions.", "") : name;
    }

    @Override // com.android.car.developeroptions.core.SettingsBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        View viewFindViewById;
        super.onCreate(bundle);
        Log.d("SettingsActivity", "Starting onCreate");
        System.currentTimeMillis();
        this.mDashboardFeatureProvider = FeatureFactory.getFactory(this).getDashboardFeatureProvider(this);
        getMetaData();
        Intent intent = getIntent();
        if (intent.hasExtra("settings:ui_options")) {
            getWindow().setUiOptions(intent.getIntExtra("settings:ui_options", 0));
        }
        String stringExtra = intent.getStringExtra(":settings:show_fragment");
        if ((this instanceof SubSettings) || intent.getBooleanExtra(":settings:show_fragment_as_subsetting", false)) {
            setTheme(R.style.Theme_SubSettings);
        }
        setContentView(R.layout.settings_main_prefs);
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        if (bundle != null) {
            setTitleFromIntent(intent);
            ArrayList parcelableArrayList = bundle.getParcelableArrayList(":settings:categories");
            if (parcelableArrayList != null) {
                this.mCategories.clear();
                this.mCategories.addAll(parcelableArrayList);
                setTitleFromBackStack();
            }
        } else {
            launchSettingFragment(stringExtra, intent);
        }
        boolean zIsDeviceProvisioned = Utils.isDeviceProvisioned(this);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(zIsDeviceProvisioned);
            actionBar.setHomeButtonEnabled(zIsDeviceProvisioned);
            actionBar.setDisplayShowTitleEnabled(true);
        }
        SwitchBar switchBar = (SwitchBar) findViewById(R.id.switch_bar);
        this.mSwitchBar = switchBar;
        if (switchBar != null) {
            switchBar.setMetricsTag(getMetricsTag());
        }
        if (!intent.getBooleanExtra("extra_prefs_show_button_bar", false) || (viewFindViewById = findViewById(R.id.button_bar)) == null) {
            return;
        }
        viewFindViewById.setVisibility(0);
        Button button = (Button) findViewById(R.id.back_button);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.-$$Lambda$SettingsActivity$ypyRTfHxbBEGP40PwnsAWyDEaaQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$0$SettingsActivity(view);
            }
        });
        Button button2 = (Button) findViewById(R.id.skip_button);
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.-$$Lambda$SettingsActivity$b-QJvOdVRMxNKwyl8zA8Z1RJ0ZU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$1$SettingsActivity(view);
            }
        });
        Button button3 = (Button) findViewById(R.id.next_button);
        this.mNextButton = button3;
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.-$$Lambda$SettingsActivity$y5MJwRU48o9nvISmubyDimdiRBo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$2$SettingsActivity(view);
            }
        });
        if (intent.hasExtra("extra_prefs_set_next_text")) {
            String stringExtra2 = intent.getStringExtra("extra_prefs_set_next_text");
            if (TextUtils.isEmpty(stringExtra2)) {
                this.mNextButton.setVisibility(8);
            } else {
                this.mNextButton.setText(stringExtra2);
            }
        }
        if (intent.hasExtra("extra_prefs_set_back_text")) {
            String stringExtra3 = intent.getStringExtra("extra_prefs_set_back_text");
            if (TextUtils.isEmpty(stringExtra3)) {
                button.setVisibility(8);
            } else {
                button.setText(stringExtra3);
            }
        }
        if (intent.getBooleanExtra("extra_prefs_show_skip", false)) {
            button2.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreate$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreate$0$SettingsActivity(View view) {
        setResult(0, null);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreate$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreate$1$SettingsActivity(View view) {
        setResult(-1, null);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreate$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreate$2$SettingsActivity(View view) {
        setResult(-1, null);
        finish();
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper
    protected void onApplyThemeResource(Resources.Theme theme, int i, boolean z) {
        theme.applyStyle(R.style.SetupWizardPartnerResource, true);
        super.onApplyThemeResource(theme, i, z);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment instanceof OnActivityResultListener) {
                    fragment.onActivityResult(i, i2, intent);
                }
            }
        }
    }

    void launchSettingFragment(String str, Intent intent) {
        if (str != null) {
            setTitleFromIntent(intent);
            switchToFragment(str, intent.getBundleExtra(":settings:show_fragment_args"), true, this.mInitialTitleResId, this.mInitialTitle);
        } else {
            this.mInitialTitleResId = R.string.dashboard_title;
            switchToFragment(TopLevelSettings.class.getName(), null, false, this.mInitialTitleResId, this.mInitialTitle);
        }
    }

    private void setTitleFromIntent(Intent intent) {
        Log.d("SettingsActivity", "Starting to set activity title");
        int intExtra = intent.getIntExtra(":settings:show_fragment_title_resid", -1);
        if (intExtra > 0) {
            this.mInitialTitle = null;
            this.mInitialTitleResId = intExtra;
            String stringExtra = intent.getStringExtra(":settings:show_fragment_title_res_package_name");
            if (stringExtra != null) {
                try {
                    CharSequence text = createPackageContextAsUser(stringExtra, 0, new UserHandle(UserHandle.myUserId())).getResources().getText(this.mInitialTitleResId);
                    this.mInitialTitle = text;
                    setTitle(text);
                    this.mInitialTitleResId = -1;
                    return;
                } catch (PackageManager.NameNotFoundException unused) {
                    Log.w("SettingsActivity", "Could not find package" + stringExtra);
                }
            } else {
                setTitle(this.mInitialTitleResId);
            }
        } else {
            this.mInitialTitleResId = -1;
            CharSequence stringExtra2 = intent.getStringExtra(":settings:show_fragment_title");
            if (stringExtra2 == null) {
                stringExtra2 = getTitle();
            }
            this.mInitialTitle = stringExtra2;
            setTitle(stringExtra2);
        }
        Log.d("SettingsActivity", "Done setting title");
    }

    @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
    public void onBackStackChanged() {
        setTitleFromBackStack();
    }

    private void setTitleFromBackStack() {
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackEntryCount == 0) {
            int i = this.mInitialTitleResId;
            if (i > 0) {
                setTitle(i);
                return;
            } else {
                setTitle(this.mInitialTitle);
                return;
            }
        }
        setTitleFromBackStackEntry(getSupportFragmentManager().getBackStackEntryAt(backStackEntryCount - 1));
    }

    private void setTitleFromBackStackEntry(FragmentManager.BackStackEntry backStackEntry) {
        CharSequence breadCrumbTitle;
        int breadCrumbTitleRes = backStackEntry.getBreadCrumbTitleRes();
        if (breadCrumbTitleRes > 0) {
            breadCrumbTitle = getText(breadCrumbTitleRes);
        } else {
            breadCrumbTitle = backStackEntry.getBreadCrumbTitle();
        }
        if (breadCrumbTitle != null) {
            setTitle(breadCrumbTitle);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        saveState(bundle);
    }

    void saveState(Bundle bundle) {
        if (this.mCategories.size() > 0) {
            bundle.putParcelableArrayList(":settings:categories", this.mCategories);
        }
    }

    @Override // com.android.car.developeroptions.core.SettingsBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.mDevelopmentSettingsListener = new BroadcastReceiver() { // from class: com.android.car.developeroptions.SettingsActivity.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                SettingsActivity.this.updateTilesList();
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mDevelopmentSettingsListener, new IntentFilter("com.android.settingslib.development.DevelopmentSettingsEnabler.SETTINGS_CHANGED"));
        registerReceiver(this.mBatteryInfoReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        updateTilesList();
    }

    @Override // com.android.car.developeroptions.core.SettingsBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mDevelopmentSettingsListener);
        this.mDevelopmentSettingsListener = null;
        unregisterReceiver(this.mBatteryInfoReceiver);
    }

    @Override // android.app.Activity
    public void setTaskDescription(ActivityManager.TaskDescription taskDescription) {
        taskDescription.setIcon(Icon.createWithResource(this, R.drawable.ic_launcher_settings));
        super.setTaskDescription(taskDescription);
    }

    protected boolean isValidFragment(String str) {
        int i = 0;
        while (true) {
            String[] strArr = SettingsGateway.ENTRY_FRAGMENTS;
            if (i >= strArr.length) {
                return false;
            }
            if (strArr[i].equals(str)) {
                return true;
            }
            i++;
        }
    }

    @Override // android.app.Activity
    public Intent getIntent() {
        Bundle bundle;
        Intent intent = super.getIntent();
        String startingFragmentClass = getStartingFragmentClass(intent);
        if (startingFragmentClass == null) {
            return intent;
        }
        Intent intent2 = new Intent(intent);
        intent2.putExtra(":settings:show_fragment", startingFragmentClass);
        Bundle bundleExtra = intent.getBundleExtra(":settings:show_fragment_args");
        if (bundleExtra != null) {
            bundle = new Bundle(bundleExtra);
        } else {
            bundle = new Bundle();
        }
        bundle.putParcelable("intent", intent);
        intent2.putExtra(":settings:show_fragment_args", bundle);
        return intent2;
    }

    private String getStartingFragmentClass(Intent intent) {
        String str = this.mFragmentClass;
        if (str != null) {
            return str;
        }
        String className = intent.getComponent().getClassName();
        if (className.equals(getClass().getName())) {
            return null;
        }
        return ("com.android.car.developeroptions.RunningServices".equals(className) || "com.android.car.developeroptions.applications.StorageUse".equals(className)) ? ManageApplications.class.getName() : className;
    }

    public void finishPreferencePanel(int i, Intent intent) {
        setResult(i, intent);
        finish();
    }

    private Fragment switchToFragment(String str, Bundle bundle, boolean z, int i, CharSequence charSequence) {
        Log.d("SettingsActivity", "Switching to fragment " + str);
        if (z && !isValidFragment(str)) {
            throw new IllegalArgumentException("Invalid fragment for this activity: " + str);
        }
        Fragment fragmentInstantiate = Fragment.instantiate(this, str, bundle);
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.main_content, fragmentInstantiate);
        if (i > 0) {
            fragmentTransactionBeginTransaction.setBreadCrumbTitle(i);
        } else if (charSequence != null) {
            fragmentTransactionBeginTransaction.setBreadCrumbTitle(charSequence);
        }
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        getSupportFragmentManager().executePendingTransactions();
        Log.d("SettingsActivity", "Executed frag manager pendingTransactions");
        return fragmentInstantiate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTilesList() {
        AsyncTask.execute(new Runnable() { // from class: com.android.car.developeroptions.SettingsActivity.3
            @Override // java.lang.Runnable
            public void run() {
                SettingsActivity.this.doUpdateTilesList();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doUpdateTilesList() {
        getPackageManager();
        boolean zIsAdminUser = UserManager.get(this).isAdminUser();
        String packageName = getPackageName();
        StringBuilder sb = new StringBuilder();
        boolean z = setTileEnabled(sb, new ComponentName(packageName, Settings$DevelopmentSettingsDashboardActivity.class.getName()), DevelopmentSettingsEnabler.isDevelopmentSettingsEnabled(this) && !Utils.isMonkeyRunning(), zIsAdminUser);
        if (!zIsAdminUser) {
            List<DashboardCategory> allCategories = this.mDashboardFeatureProvider.getAllCategories();
            synchronized (allCategories) {
                for (DashboardCategory dashboardCategory : allCategories) {
                    int tilesCount = dashboardCategory.getTilesCount();
                    for (int i = 0; i < tilesCount; i++) {
                        ComponentName component = dashboardCategory.getTile(i).getIntent().getComponent();
                        boolean zContains = ArrayUtils.contains(SettingsGateway.SETTINGS_FOR_RESTRICTED, component.getClassName());
                        if (packageName.equals(component.getPackageName()) && !zContains) {
                            z = setTileEnabled(sb, component, false, zIsAdminUser) || z;
                        }
                    }
                }
            }
        }
        if (z) {
            Log.d("SettingsActivity", "Enabled state changed for some tiles, reloading all categories " + sb.toString());
            updateCategories();
            return;
        }
        Log.d("SettingsActivity", "No enabled state changed, skipping updateCategory call");
    }

    private boolean setTileEnabled(StringBuilder sb, ComponentName componentName, boolean z, boolean z2) {
        if (!z2 && getPackageName().equals(componentName.getPackageName()) && !ArrayUtils.contains(SettingsGateway.SETTINGS_FOR_RESTRICTED, componentName.getClassName())) {
            z = false;
        }
        boolean tileEnabled = setTileEnabled(componentName, z);
        if (tileEnabled) {
            sb.append(componentName.toShortString());
            sb.append(",");
        }
        return tileEnabled;
    }

    private void getMetaData() {
        try {
            ActivityInfo activityInfo = getPackageManager().getActivityInfo(getComponentName(), 128);
            if (activityInfo != null && activityInfo.metaData != null) {
                this.mFragmentClass = activityInfo.metaData.getString("com.android.car.developeroptions.FRAGMENT_CLASS");
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.d("SettingsActivity", "Cannot get Metadata for: " + getComponentName().toString());
        }
    }

    @Override // com.android.car.developeroptions.ButtonBarHandler
    public boolean hasNextButton() {
        return this.mNextButton != null;
    }

    @Override // com.android.car.developeroptions.ButtonBarHandler
    public Button getNextButton() {
        return this.mNextButton;
    }
}
