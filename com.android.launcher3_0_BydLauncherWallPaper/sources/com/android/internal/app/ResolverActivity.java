package com.android.internal.app;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.app.ActivityThread;
import android.app.VoiceInteractor;
import android.app.admin.DevicePolicyEventLogger;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.PermissionChecker;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.UserInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Insets;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PatternMatcher;
import android.os.RemoteException;
import android.os.StrictMode;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.provider.SettingsStringUtil;
import android.text.TextUtils;
import android.util.Log;
import android.util.Slog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Space;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import com.android.internal.R;
import com.android.internal.app.AbstractMultiProfilePagerAdapter;
import com.android.internal.app.ResolverListAdapter;
import com.android.internal.app.chooser.ChooserTargetInfo;
import com.android.internal.app.chooser.DisplayResolveInfo;
import com.android.internal.app.chooser.TargetInfo;
import com.android.internal.content.PackageMonitor;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.widget.ResolverDrawerLayout;
import com.android.internal.widget.ViewPager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class ResolverActivity extends Activity implements ResolverListAdapter.ResolverListCommunicator {
    private static final boolean DEBUG = false;
    private static final String DEFAULT_LAUNCHER_NAME = "persist.sys.default_launcher_name";
    public static boolean ENABLE_TABBED_VIEW = true;
    static final String EXTRA_CALLING_USER = "com.android.internal.app.ResolverActivity.EXTRA_CALLING_USER";
    private static final String EXTRA_FRAGMENT_ARG_KEY = ":settings:fragment_args_key";
    public static final String EXTRA_IS_AUDIO_CAPTURE_DEVICE = "is_audio_capture_device";
    static final String EXTRA_SELECTED_PROFILE = "com.android.internal.app.ResolverActivity.EXTRA_SELECTED_PROFILE";
    private static final String EXTRA_SHOW_FRAGMENT_ARGS = ":settings:show_fragment_args";
    private static final String FALLBACK_CLASS_NAME = "com.android.car.settings.FallbackHome";
    private static final String FALLBACK_PKG_NAME = "com.android.car.settings";
    private static final String LAST_SHOWN_TAB_KEY = "last_shown_tab_key";
    private static final String LAUNCHER_CLASS_NAME = "com.android.launcher3.home.MainActivity";
    private static final String LAUNCHER_PKG_NAME = "com.android.launcher3";
    protected static final String METRICS_CATEGORY_CHOOSER = "intent_chooser";
    protected static final String METRICS_CATEGORY_RESOLVER = "intent_resolver";
    private static final String NAVI_CLASS_NAME = "com.neusoft.na.mots.MainActivity";
    private static final String NAVI_PKG_NAME = "com.neusoft.na.navigation";
    private static final String OPEN_LINKS_COMPONENT_KEY = "app_link_state";
    static final int PROFILE_PERSONAL = 0;
    static final int PROFILE_WORK = 1;
    private static final String TAB_TAG_PERSONAL = "personal";
    private static final String TAB_TAG_WORK = "work";
    private static final String TAG = "ResolverActivity";
    private Button mAlwaysButton;
    private int mDefaultTitleResId;
    private UserHandle mHeaderCreatorUser;
    protected int mLaunchedFromUid;
    private int mLayoutId;
    protected AbstractMultiProfilePagerAdapter mMultiProfilePagerAdapter;
    private Button mOnceButton;
    private PackageMonitor mPersonalPackageMonitor;
    private PickTargetOptionRequest mPickOptionRequest;
    protected PackageManager mPm;
    protected View mProfileView;
    private String mReferrerPackage;
    private boolean mRegistered;
    protected ResolverDrawerLayout mResolverDrawerLayout;
    private boolean mRetainInOnStop;
    private boolean mSafeForwardingMode;
    protected boolean mSupportsAlwaysUseOption;
    private CharSequence mTitle;
    private PackageMonitor mWorkPackageMonitor;
    private BroadcastReceiver mWorkProfileStateReceiver;
    private UserHandle mWorkProfileUserHandle;
    private int mLastSelected = -1;
    private boolean mResolvingHome = false;
    private int mProfileSwitchMessageId = -1;
    protected final ArrayList<Intent> mIntents = new ArrayList<>();
    protected Insets mSystemWindowInsets = null;
    private Space mFooterSpacer = null;
    private boolean mWorkProfileHasBeenEnabled = false;

    static final boolean isSpecificUriMatch(int i) {
        int i2 = i & IntentFilter.MATCH_CATEGORY_MASK;
        return i2 >= 3145728 && i2 <= 5242880;
    }

    protected int appliedThemeResId() {
        return R.style.Theme_DeviceDefault_Resolver;
    }

    public int getLayoutResource() {
        return R.layout.resolver_list;
    }

    protected String getMetricsCategory() {
        return METRICS_CATEGORY_RESOLVER;
    }

    public Intent getReplacementIntent(ActivityInfo activityInfo, Intent intent) {
        return intent;
    }

    protected void maybeLogProfileChange() {
    }

    public void onActivityStarted(TargetInfo targetInfo) {
    }

    void onHorizontalSwipeStateChanged(int i) {
    }

    protected void onProfileTabSelected() {
    }

    public boolean shouldGetActivityMetadata() {
        return false;
    }

    public static int getLabelRes(String str) {
        return ActionTitle.forAction(str).labelRes;
    }

    private enum ActionTitle {
        VIEW("android.intent.action.VIEW", R.string.whichViewApplication, R.string.whichViewApplicationNamed, R.string.whichViewApplicationLabel),
        EDIT(Intent.ACTION_EDIT, R.string.whichEditApplication, R.string.whichEditApplicationNamed, R.string.whichEditApplicationLabel),
        SEND(Intent.ACTION_SEND, R.string.whichSendApplication, R.string.whichSendApplicationNamed, R.string.whichSendApplicationLabel),
        SENDTO(Intent.ACTION_SENDTO, R.string.whichSendToApplication, R.string.whichSendToApplicationNamed, R.string.whichSendToApplicationLabel),
        SEND_MULTIPLE(Intent.ACTION_SEND_MULTIPLE, R.string.whichSendApplication, R.string.whichSendApplicationNamed, R.string.whichSendApplicationLabel),
        CAPTURE_IMAGE("android.media.action.IMAGE_CAPTURE", R.string.whichImageCaptureApplication, R.string.whichImageCaptureApplicationNamed, R.string.whichImageCaptureApplicationLabel),
        DEFAULT(null, R.string.whichApplication, R.string.whichApplicationNamed, R.string.whichApplicationLabel),
        HOME(Intent.ACTION_MAIN, R.string.whichHomeApplication, R.string.whichHomeApplicationNamed, R.string.whichHomeApplicationLabel);

        public static final int BROWSABLE_APP_TITLE_RES = 17041489;
        public static final int BROWSABLE_HOST_APP_TITLE_RES = 17041487;
        public static final int BROWSABLE_HOST_TITLE_RES = 17041486;
        public static final int BROWSABLE_TITLE_RES = 17041488;
        public final String action;
        public final int labelRes;
        public final int namedTitleRes;
        public final int titleRes;

        ActionTitle(String str, int i, int i2, int i3) {
            this.action = str;
            this.titleRes = i;
            this.namedTitleRes = i2;
            this.labelRes = i3;
        }

        public static ActionTitle forAction(String str) {
            for (ActionTitle actionTitle : values()) {
                if (actionTitle != HOME && str != null && str.equals(actionTitle.action)) {
                    return actionTitle;
                }
            }
            return DEFAULT;
        }
    }

    protected PackageMonitor createPackageMonitor(final ResolverListAdapter resolverListAdapter) {
        return new PackageMonitor() { // from class: com.android.internal.app.ResolverActivity.1
            @Override // com.android.internal.content.PackageMonitor
            public boolean onPackageChanged(String str, int i, String[] strArr) {
                return true;
            }

            @Override // com.android.internal.content.PackageMonitor
            public void onSomePackagesChanged() {
                resolverListAdapter.handlePackagesChanged();
                ResolverActivity.this.updateProfileViewButton();
            }
        };
    }

    private Intent makeMyIntent() {
        Intent intent = new Intent(getIntent());
        intent.setComponent(null);
        intent.setFlags(intent.getFlags() & (-8388609));
        return intent;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        Intent intentMakeMyIntent = makeMyIntent();
        Set<String> categories = intentMakeMyIntent.getCategories();
        if (Intent.ACTION_MAIN.equals(intentMakeMyIntent.getAction()) && categories != null && categories.size() == 1 && categories.contains(Intent.CATEGORY_HOME)) {
            this.mResolvingHome = true;
        }
        setSafeForwardingMode(true);
        onCreate(bundle, intentMakeMyIntent, null, 0, null, null, true);
    }

    protected void onCreate(Bundle bundle, Intent intent, CharSequence charSequence, Intent[] intentArr, List<ResolveInfo> list, boolean z) {
        onCreate(bundle, intent, charSequence, 0, intentArr, list, z);
    }

    protected void onCreate(Bundle bundle, Intent intent, CharSequence charSequence, int i, Intent[] intentArr, List<ResolveInfo> list, boolean z) {
        setTheme(appliedThemeResId());
        super.onCreate(bundle);
        if (this.mResolvingHome) {
            setDefaultLauncher();
            finish();
            return;
        }
        setProfileSwitchMessageId(intent.getContentUserHint());
        try {
            this.mLaunchedFromUid = ActivityTaskManager.getService().getLaunchedFromUid(getActivityToken());
        } catch (RemoteException unused) {
            this.mLaunchedFromUid = -1;
        }
        int i2 = this.mLaunchedFromUid;
        if (i2 < 0 || UserHandle.isIsolated(i2)) {
            finish();
            return;
        }
        this.mPm = getPackageManager();
        this.mReferrerPackage = getReferrerPackageName();
        this.mIntents.add(0, new Intent(intent));
        this.mTitle = charSequence;
        this.mDefaultTitleResId = i;
        this.mSupportsAlwaysUseOption = z;
        this.mWorkProfileUserHandle = fetchWorkProfileUserProfile();
        this.mMultiProfilePagerAdapter = createMultiProfilePagerAdapter(intentArr, list, (!this.mSupportsAlwaysUseOption || isVoiceInteraction() || shouldShowTabs()) ? false : true);
        if (configureContentView()) {
            return;
        }
        PackageMonitor packageMonitorCreatePackageMonitor = createPackageMonitor(this.mMultiProfilePagerAdapter.getPersonalListAdapter());
        this.mPersonalPackageMonitor = packageMonitorCreatePackageMonitor;
        packageMonitorCreatePackageMonitor.register((Context) this, getMainLooper(), getPersonalProfileUserHandle(), false);
        if (shouldShowTabs()) {
            PackageMonitor packageMonitorCreatePackageMonitor2 = createPackageMonitor(this.mMultiProfilePagerAdapter.getWorkListAdapter());
            this.mWorkPackageMonitor = packageMonitorCreatePackageMonitor2;
            packageMonitorCreatePackageMonitor2.register((Context) this, getMainLooper(), getWorkProfileUserHandle(), false);
        }
        this.mRegistered = true;
        ResolverDrawerLayout resolverDrawerLayout = (ResolverDrawerLayout) findViewById(R.id.contentPanel);
        if (resolverDrawerLayout != null) {
            resolverDrawerLayout.setOnDismissedListener(new ResolverDrawerLayout.OnDismissedListener() { // from class: com.android.internal.app.ResolverActivity.2
                @Override // com.android.internal.widget.ResolverDrawerLayout.OnDismissedListener
                public void onDismissed() {
                    ResolverActivity.this.finish();
                }
            });
            boolean zHasSystemFeature = getPackageManager().hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN);
            if (isVoiceInteraction() || !zHasSystemFeature) {
                resolverDrawerLayout.setCollapsed(false);
            }
            resolverDrawerLayout.setSystemUiVisibility(768);
            resolverDrawerLayout.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.android.internal.app.-$$Lambda$Q5eKtIaY1epIDLUOTngd2qxV_88
                @Override // android.view.View.OnApplyWindowInsetsListener
                public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                    return this.f$0.onApplyWindowInsets(view, windowInsets);
                }
            });
            this.mResolverDrawerLayout = resolverDrawerLayout;
        }
        View viewFindViewById = findViewById(R.id.profile_button);
        this.mProfileView = viewFindViewById;
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.android.internal.app.-$$Lambda$77YrNXoGjVv2nlNRXJfo1ZMgvWg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.onProfileClick(view);
                }
            });
            updateProfileViewButton();
        }
        Set<String> categories = intent.getCategories();
        MetricsLogger.action(this, this.mMultiProfilePagerAdapter.getActiveListAdapter().hasFilteredItem() ? 451 : 453, intent.getAction() + SettingsStringUtil.DELIMITER + intent.getType() + SettingsStringUtil.DELIMITER + (categories != null ? Arrays.toString(categories.toArray()) : ""));
    }

    private void setDefaultLauncher() {
        ComponentName componentName;
        ComponentName componentName2;
        try {
            Slog.e(TAG, " setDefaultLauncher ");
            PackageManager packageManager = getPackageManager();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Intent.ACTION_MAIN);
            intentFilter.addCategory(Intent.CATEGORY_HOME);
            intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 0);
            int size = listQueryIntentActivities.size();
            ComponentName[] componentNameArr = new ComponentName[size];
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                ResolveInfo resolveInfo = listQueryIntentActivities.get(i2);
                componentNameArr[i2] = new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
                if (resolveInfo.match > i) {
                    i = resolveInfo.match;
                }
            }
            String str = SystemProperties.get(DEFAULT_LAUNCHER_NAME);
            if (isUserSetup()) {
                if (str == null) {
                    componentName = new ComponentName("com.android.launcher3", LAUNCHER_CLASS_NAME);
                    SystemProperties.set(DEFAULT_LAUNCHER_NAME, "com.android.launcher3");
                } else if ((str == null || !str.equals("com.android.launcher3")) && str != null && str.equals("com.neusoft.na.navigation")) {
                    componentName2 = new ComponentName("com.neusoft.na.navigation", NAVI_CLASS_NAME);
                } else {
                    componentName = new ComponentName("com.android.launcher3", LAUNCHER_CLASS_NAME);
                }
                componentName2 = componentName;
            } else {
                componentName2 = new ComponentName(FALLBACK_PKG_NAME, FALLBACK_CLASS_NAME);
                SystemProperties.set(DEFAULT_LAUNCHER_NAME, "com.android.launcher3");
            }
            Slog.e(TAG, "setDefaultLauncher preActivity: " + componentName2.getClassName() + " defaultLauncherPackage: " + str);
            packageManager.addPreferredActivity(intentFilter, i, componentNameArr, componentName2);
        } catch (Exception e) {
            Slog.e(TAG, "setDefaultLauncher failed", e);
        }
    }

    private boolean isUserSetup() {
        ContentResolver contentResolver = getApplicationContext().getContentResolver();
        return (Settings.Global.getInt(contentResolver, "device_provisioned", 0) == 0 || Settings.Secure.getInt(contentResolver, Settings.Secure.USER_SETUP_COMPLETE, 0) == 0) ? false : true;
    }

    private boolean isIntentPicker() {
        return getClass().equals(ResolverActivity.class);
    }

    protected AbstractMultiProfilePagerAdapter createMultiProfilePagerAdapter(Intent[] intentArr, List<ResolveInfo> list, boolean z) {
        if (shouldShowTabs()) {
            return createResolverMultiProfilePagerAdapterForTwoProfiles(intentArr, list, z);
        }
        return createResolverMultiProfilePagerAdapterForOneProfile(intentArr, list, z);
    }

    private ResolverMultiProfilePagerAdapter createResolverMultiProfilePagerAdapterForOneProfile(Intent[] intentArr, List<ResolveInfo> list, boolean z) {
        return new ResolverMultiProfilePagerAdapter(this, createResolverListAdapter(this, this.mIntents, intentArr, list, z, UserHandle.of(UserHandle.myUserId())), getPersonalProfileUserHandle(), null);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.android.internal.app.ResolverMultiProfilePagerAdapter createResolverMultiProfilePagerAdapterForTwoProfiles(android.content.Intent[] r15, java.util.List<android.content.pm.ResolveInfo> r16, boolean r17) {
        /*
            r14 = this;
            r7 = r14
            int r0 = r14.getCurrentProfile()
            android.content.Intent r1 = r14.getIntent()
            java.lang.String r2 = "com.android.internal.app.ResolverActivity.EXTRA_CALLING_USER"
            boolean r1 = r1.hasExtra(r2)
            if (r1 == 0) goto L1c
            android.content.Intent r1 = r14.getIntent()
            android.os.Parcelable r1 = r1.getParcelableExtra(r2)
            android.os.UserHandle r1 = (android.os.UserHandle) r1
            goto L20
        L1c:
            android.os.UserHandle r1 = r14.getUser()
        L20:
            r8 = r1
            android.os.UserHandle r1 = r14.getUser()
            boolean r1 = r1.equals(r8)
            r9 = 0
            r10 = 1
            if (r1 != 0) goto L45
            android.os.UserHandle r1 = r14.getPersonalProfileUserHandle()
            boolean r1 = r1.equals(r8)
            if (r1 == 0) goto L39
            r11 = r9
            goto L4f
        L39:
            android.os.UserHandle r1 = r14.getWorkProfileUserHandle()
            boolean r1 = r1.equals(r8)
            if (r1 == 0) goto L4e
            r11 = r10
            goto L4f
        L45:
            int r1 = r14.getSelectedProfileExtra()
            r2 = -1
            if (r1 == r2) goto L4e
            r11 = r1
            goto L4f
        L4e:
            r11 = r0
        L4f:
            java.util.ArrayList<android.content.Intent> r2 = r7.mIntents
            r12 = 0
            if (r11 != 0) goto L56
            r3 = r15
            goto L57
        L56:
            r3 = r12
        L57:
            if (r17 == 0) goto L69
            int r0 = android.os.UserHandle.myUserId()
            android.os.UserHandle r1 = r14.getPersonalProfileUserHandle()
            int r1 = r1.getIdentifier()
            if (r0 != r1) goto L69
            r5 = r10
            goto L6a
        L69:
            r5 = r9
        L6a:
            android.os.UserHandle r6 = r14.getPersonalProfileUserHandle()
            r0 = r14
            r1 = r14
            r4 = r16
            com.android.internal.app.ResolverListAdapter r13 = r0.createResolverListAdapter(r1, r2, r3, r4, r5, r6)
            android.os.UserHandle r6 = r14.getWorkProfileUserHandle()
            java.util.ArrayList<android.content.Intent> r2 = r7.mIntents
            if (r11 != r10) goto L80
            r3 = r15
            goto L81
        L80:
            r3 = r12
        L81:
            if (r17 == 0) goto L8f
            int r0 = android.os.UserHandle.myUserId()
            int r1 = r6.getIdentifier()
            if (r0 != r1) goto L8f
            r5 = r10
            goto L90
        L8f:
            r5 = r9
        L90:
            r0 = r14
            r1 = r14
            r4 = r16
            com.android.internal.app.ResolverListAdapter r3 = r0.createResolverListAdapter(r1, r2, r3, r4, r5, r6)
            com.android.internal.app.ResolverMultiProfilePagerAdapter r9 = new com.android.internal.app.ResolverMultiProfilePagerAdapter
            android.os.UserHandle r5 = r14.getPersonalProfileUserHandle()
            android.os.UserHandle r6 = r14.getWorkProfileUserHandle()
            android.os.UserHandle r0 = r14.getUser()
            boolean r8 = r0.equals(r8)
            r0 = r9
            r2 = r13
            r4 = r11
            r7 = r8
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.app.ResolverActivity.createResolverMultiProfilePagerAdapterForTwoProfiles(android.content.Intent[], java.util.List, boolean):com.android.internal.app.ResolverMultiProfilePagerAdapter");
    }

    int getSelectedProfileExtra() {
        int intExtra = -1;
        if (!getIntent().hasExtra(EXTRA_SELECTED_PROFILE) || (intExtra = getIntent().getIntExtra(EXTRA_SELECTED_PROFILE, -1)) == 0 || intExtra == 1) {
            return intExtra;
        }
        throw new IllegalArgumentException("com.android.internal.app.ResolverActivity.EXTRA_SELECTED_PROFILE has invalid value " + intExtra + ". Must be either ResolverActivity.PROFILE_PERSONAL or ResolverActivity.PROFILE_WORK.");
    }

    protected int getCurrentProfile() {
        return UserHandle.myUserId() == 0 ? 0 : 1;
    }

    protected UserHandle getPersonalProfileUserHandle() {
        return UserHandle.of(ActivityManager.getCurrentUser());
    }

    protected UserHandle getWorkProfileUserHandle() {
        return this.mWorkProfileUserHandle;
    }

    protected UserHandle fetchWorkProfileUserProfile() {
        this.mWorkProfileUserHandle = null;
        for (UserInfo userInfo : ((UserManager) getSystemService(UserManager.class)).getProfiles(ActivityManager.getCurrentUser())) {
            if (userInfo.isManagedProfile()) {
                this.mWorkProfileUserHandle = userInfo.getUserHandle();
            }
        }
        return this.mWorkProfileUserHandle;
    }

    private boolean hasWorkProfile() {
        return getWorkProfileUserHandle() != null;
    }

    protected boolean shouldShowTabs() {
        return hasWorkProfile() && ENABLE_TABBED_VIEW;
    }

    protected void onProfileClick(View view) {
        DisplayResolveInfo otherProfile = this.mMultiProfilePagerAdapter.getActiveListAdapter().getOtherProfile();
        if (otherProfile == null) {
            return;
        }
        this.mProfileSwitchMessageId = -1;
        onTargetSelected(otherProfile, false);
        finish();
    }

    protected boolean shouldAddFooterView() {
        View viewFindViewById;
        return useLayoutWithDefault() || (viewFindViewById = findViewById(R.id.button_bar)) == null || viewFindViewById.getVisibility() == 8;
    }

    protected void applyFooterView(int i) {
        if (this.mFooterSpacer == null) {
            this.mFooterSpacer = new Space(getApplicationContext());
        } else {
            ((ResolverMultiProfilePagerAdapter) this.mMultiProfilePagerAdapter).getActiveAdapterView().removeFooterView(this.mFooterSpacer);
        }
        this.mFooterSpacer.setLayoutParams(new AbsListView.LayoutParams(-1, this.mSystemWindowInsets.bottom));
        ((ResolverMultiProfilePagerAdapter) this.mMultiProfilePagerAdapter).getActiveAdapterView().addFooterView(this.mFooterSpacer);
    }

    protected WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        Insets systemWindowInsets = windowInsets.getSystemWindowInsets();
        this.mSystemWindowInsets = systemWindowInsets;
        this.mResolverDrawerLayout.setPadding(systemWindowInsets.left, this.mSystemWindowInsets.top, this.mSystemWindowInsets.right, 0);
        resetButtonBar();
        if (shouldAddFooterView()) {
            applyFooterView(this.mSystemWindowInsets.bottom);
        }
        return windowInsets.consumeSystemWindowInsets();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mMultiProfilePagerAdapter.getActiveListAdapter().handlePackagesChanged();
        if (isIntentPicker() && shouldShowTabs() && !useLayoutWithDefault()) {
            updateIntentPickerPaddings();
        }
        Insets insets = this.mSystemWindowInsets;
        if (insets != null) {
            this.mResolverDrawerLayout.setPadding(insets.left, this.mSystemWindowInsets.top, this.mSystemWindowInsets.right, 0);
        }
    }

    private void updateIntentPickerPaddings() {
        View viewFindViewById = findViewById(R.id.title_container);
        viewFindViewById.setPadding(viewFindViewById.getPaddingLeft(), viewFindViewById.getPaddingTop(), viewFindViewById.getPaddingRight(), getResources().getDimensionPixelSize(R.dimen.resolver_title_padding_bottom));
        View viewFindViewById2 = findViewById(R.id.button_bar);
        viewFindViewById2.setPadding(viewFindViewById2.getPaddingLeft(), getResources().getDimensionPixelSize(R.dimen.resolver_button_bar_spacing), viewFindViewById2.getPaddingRight(), getResources().getDimensionPixelSize(R.dimen.resolver_button_bar_spacing));
        this.mMultiProfilePagerAdapter.updateAfterConfigChange();
    }

    @Override // com.android.internal.app.ResolverListAdapter.ResolverListCommunicator
    public void sendVoiceChoicesIfNeeded() {
        if (isVoiceInteraction()) {
            int count = this.mMultiProfilePagerAdapter.getActiveListAdapter().getCount();
            VoiceInteractor.PickOptionRequest.Option[] optionArr = new VoiceInteractor.PickOptionRequest.Option[count];
            for (int i = 0; i < count; i++) {
                TargetInfo item = this.mMultiProfilePagerAdapter.getActiveListAdapter().getItem(i);
                if (item == null) {
                    return;
                }
                optionArr[i] = optionForChooserTarget(item, i);
            }
            this.mPickOptionRequest = new PickTargetOptionRequest(new VoiceInteractor.Prompt(getTitle()), optionArr, null);
            getVoiceInteractor().submitRequest(this.mPickOptionRequest);
        }
    }

    VoiceInteractor.PickOptionRequest.Option optionForChooserTarget(TargetInfo targetInfo, int i) {
        return new VoiceInteractor.PickOptionRequest.Option(targetInfo.getDisplayLabel(), i);
    }

    protected final void setAdditionalTargets(Intent[] intentArr) {
        if (intentArr != null) {
            for (Intent intent : intentArr) {
                this.mIntents.add(intent);
            }
        }
    }

    @Override // com.android.internal.app.ResolverListAdapter.ResolverListCommunicator
    public Intent getTargetIntent() {
        if (this.mIntents.isEmpty()) {
            return null;
        }
        return this.mIntents.get(0);
    }

    protected String getReferrerPackageName() {
        Uri referrer = getReferrer();
        if (referrer == null || !"android-app".equals(referrer.getScheme())) {
            return null;
        }
        return referrer.getHost();
    }

    @Override // com.android.internal.app.ResolverListAdapter.ResolverListCommunicator
    public void updateProfileViewButton() {
        if (this.mProfileView == null) {
            return;
        }
        DisplayResolveInfo otherProfile = this.mMultiProfilePagerAdapter.getActiveListAdapter().getOtherProfile();
        if (otherProfile != null && !shouldShowTabs()) {
            this.mProfileView.setVisibility(0);
            View viewFindViewById = this.mProfileView.findViewById(R.id.profile_button);
            if (!(viewFindViewById instanceof TextView)) {
                viewFindViewById = this.mProfileView.findViewById(16908308);
            }
            ((TextView) viewFindViewById).setText(otherProfile.getDisplayLabel());
            return;
        }
        this.mProfileView.setVisibility(8);
    }

    private void setProfileSwitchMessageId(int i) {
        if (i == -2 || i == UserHandle.myUserId()) {
            return;
        }
        UserManager userManager = (UserManager) getSystemService("user");
        UserInfo userInfo = userManager.getUserInfo(i);
        boolean zIsManagedProfile = userInfo != null ? userInfo.isManagedProfile() : false;
        boolean zIsManagedProfile2 = userManager.isManagedProfile();
        if (zIsManagedProfile && !zIsManagedProfile2) {
            this.mProfileSwitchMessageId = R.string.forward_intent_to_owner;
        } else {
            if (zIsManagedProfile || !zIsManagedProfile2) {
                return;
            }
            this.mProfileSwitchMessageId = R.string.forward_intent_to_work;
        }
    }

    public void setSafeForwardingMode(boolean z) {
        this.mSafeForwardingMode = z;
    }

    protected CharSequence getTitleForAction(Intent intent, int i) {
        ActionTitle actionTitleForAction;
        if (this.mResolvingHome) {
            actionTitleForAction = ActionTitle.HOME;
        } else {
            actionTitleForAction = ActionTitle.forAction(intent.getAction());
        }
        boolean z = this.mMultiProfilePagerAdapter.getActiveListAdapter().getFilteredPosition() >= 0;
        if (actionTitleForAction == ActionTitle.DEFAULT && i != 0) {
            return getString(i);
        }
        if (z) {
            return getString(actionTitleForAction.namedTitleRes, this.mMultiProfilePagerAdapter.getActiveListAdapter().getFilteredItem().getDisplayLabel());
        }
        return getString(actionTitleForAction.titleRes);
    }

    void dismiss() {
        if (isFinishing()) {
            return;
        }
        finish();
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        if (!this.mRegistered) {
            this.mPersonalPackageMonitor.register((Context) this, getMainLooper(), getPersonalProfileUserHandle(), false);
            if (shouldShowTabs()) {
                if (this.mWorkPackageMonitor == null) {
                    this.mWorkPackageMonitor = createPackageMonitor(this.mMultiProfilePagerAdapter.getWorkListAdapter());
                }
                this.mWorkPackageMonitor.register((Context) this, getMainLooper(), getWorkProfileUserHandle(), false);
            }
            this.mRegistered = true;
        }
        if (shouldShowTabs() && this.mMultiProfilePagerAdapter.isWaitingToEnableWorkProfile() && this.mMultiProfilePagerAdapter.isQuietModeEnabled(getWorkProfileUserHandle())) {
            this.mMultiProfilePagerAdapter.markWorkProfileEnabledBroadcastReceived();
        }
        this.mMultiProfilePagerAdapter.getActiveListAdapter().handlePackagesChanged();
        updateProfileViewButton();
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        if (shouldShowTabs()) {
            this.mWorkProfileStateReceiver = createWorkProfileStateReceiver();
            registerWorkProfileStateReceiver();
            this.mWorkProfileHasBeenEnabled = isWorkProfileEnabled();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isWorkProfileEnabled() {
        UserHandle workProfileUserHandle = getWorkProfileUserHandle();
        UserManager userManager = (UserManager) getSystemService(UserManager.class);
        return !userManager.isQuietModeEnabled(workProfileUserHandle) && userManager.isUserUnlocked(workProfileUserHandle);
    }

    private void registerWorkProfileStateReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_USER_UNLOCKED);
        intentFilter.addAction(Intent.ACTION_MANAGED_PROFILE_AVAILABLE);
        intentFilter.addAction(Intent.ACTION_MANAGED_PROFILE_UNAVAILABLE);
        registerReceiverAsUser(this.mWorkProfileStateReceiver, UserHandle.ALL, intentFilter, null, null);
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        if (this.mRegistered) {
            this.mPersonalPackageMonitor.unregister();
            PackageMonitor packageMonitor = this.mWorkPackageMonitor;
            if (packageMonitor != null) {
                packageMonitor.unregister();
            }
            this.mRegistered = false;
        }
        if ((getIntent().getFlags() & 268435456) != 0 && !isVoiceInteraction() && !this.mResolvingHome && !this.mRetainInOnStop && !isChangingConfigurations()) {
            finish();
        }
        if (this.mWorkPackageMonitor != null) {
            unregisterReceiver(this.mWorkProfileStateReceiver);
            this.mWorkPackageMonitor = null;
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        PickTargetOptionRequest pickTargetOptionRequest;
        super.onDestroy();
        if (!isChangingConfigurations() && (pickTargetOptionRequest = this.mPickOptionRequest) != null) {
            pickTargetOptionRequest.cancel();
        }
        AbstractMultiProfilePagerAdapter abstractMultiProfilePagerAdapter = this.mMultiProfilePagerAdapter;
        if (abstractMultiProfilePagerAdapter == null || abstractMultiProfilePagerAdapter.getActiveListAdapter() == null) {
            return;
        }
        this.mMultiProfilePagerAdapter.getActiveListAdapter().onDestroy();
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(LAST_SHOWN_TAB_KEY, ((ViewPager) findViewById(R.id.profile_pager)).getCurrentItem());
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        resetButtonBar();
        ((ViewPager) findViewById(R.id.profile_pager)).setCurrentItem(bundle.getInt(LAST_SHOWN_TAB_KEY));
        this.mMultiProfilePagerAdapter.clearInactiveProfileCache();
    }

    private boolean hasManagedProfile() {
        UserManager userManager = (UserManager) getSystemService("user");
        if (userManager == null) {
            return false;
        }
        try {
            for (UserInfo userInfo : userManager.getProfiles(getUserId())) {
                if (userInfo != null && userInfo.isManagedProfile()) {
                    return true;
                }
            }
        } catch (SecurityException unused) {
        }
        return false;
    }

    private boolean supportsManagedProfiles(ResolveInfo resolveInfo) {
        try {
            return getPackageManager().getApplicationInfo(resolveInfo.activityInfo.packageName, 0).targetSdkVersion >= 21;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAlwaysButtonEnabled(boolean z, int i, boolean z2) {
        boolean z3;
        if (!this.mMultiProfilePagerAdapter.getCurrentUserHandle().equals(getUser())) {
            this.mAlwaysButton.setEnabled(false);
            return;
        }
        ResolveInfo resolveInfoResolveInfoForPosition = null;
        if (z) {
            resolveInfoResolveInfoForPosition = this.mMultiProfilePagerAdapter.getActiveListAdapter().resolveInfoForPosition(i, z2);
            if (resolveInfoResolveInfoForPosition == null) {
                Log.e(TAG, "Invalid position supplied to setAlwaysButtonEnabled");
                return;
            } else if (resolveInfoResolveInfoForPosition.targetUserId != -2) {
                Log.e(TAG, "Attempted to set selection to resolve info for another user");
                return;
            } else {
                this.mAlwaysButton.setText(getResources().getString(R.string.activity_resolver_use_always));
                z3 = true;
            }
        } else {
            z3 = false;
        }
        if (resolveInfoResolveInfoForPosition != null) {
            if (!(this.mPm.checkPermission(Manifest.permission.RECORD_AUDIO, resolveInfoResolveInfoForPosition.activityInfo.packageName) == 0)) {
                z3 = !getIntent().getBooleanExtra(EXTRA_IS_AUDIO_CAPTURE_DEVICE, false);
            }
        }
        this.mAlwaysButton.setEnabled(z3);
    }

    public void onButtonClick(View view) {
        int checkedItemPosition;
        int id = view.getId();
        ListView listView = (ListView) this.mMultiProfilePagerAdapter.getActiveAdapterView();
        ResolverListAdapter activeListAdapter = this.mMultiProfilePagerAdapter.getActiveListAdapter();
        if (activeListAdapter.hasFilteredItem()) {
            checkedItemPosition = activeListAdapter.getFilteredPosition();
        } else {
            checkedItemPosition = listView.getCheckedItemPosition();
        }
        startSelected(checkedItemPosition, id == 16908810, !activeListAdapter.hasFilteredItem());
    }

    public void startSelected(int i, boolean z, boolean z2) {
        if (isFinishing()) {
            return;
        }
        ResolveInfo resolveInfoResolveInfoForPosition = this.mMultiProfilePagerAdapter.getActiveListAdapter().resolveInfoForPosition(i, z2);
        if (this.mResolvingHome && hasManagedProfile() && !supportsManagedProfiles(resolveInfoResolveInfoForPosition)) {
            Toast.makeText(this, String.format(getResources().getString(R.string.activity_resolver_work_profiles_support), resolveInfoResolveInfoForPosition.activityInfo.loadLabel(getPackageManager()).toString()), 1).show();
            return;
        }
        TargetInfo targetInfoTargetInfoForPosition = this.mMultiProfilePagerAdapter.getActiveListAdapter().targetInfoForPosition(i, z2);
        if (targetInfoTargetInfoForPosition != null && onTargetSelected(targetInfoTargetInfoForPosition, z)) {
            if (z && this.mSupportsAlwaysUseOption) {
                MetricsLogger.action(this, 455);
            } else if (this.mSupportsAlwaysUseOption) {
                MetricsLogger.action(this, 456);
            } else {
                MetricsLogger.action(this, 457);
            }
            MetricsLogger.action(this, this.mMultiProfilePagerAdapter.getActiveListAdapter().hasFilteredItem() ? 452 : 454);
            finish();
        }
    }

    @Override // com.android.internal.app.ResolverListAdapter.ResolverListCommunicator
    public final void onPostListReady(ResolverListAdapter resolverListAdapter, boolean z, boolean z2) {
        if (isAutolaunching()) {
            return;
        }
        if (isIntentPicker()) {
            ((ResolverMultiProfilePagerAdapter) this.mMultiProfilePagerAdapter).setUseLayoutWithDefault(useLayoutWithDefault());
        }
        if (this.mMultiProfilePagerAdapter.shouldShowEmptyStateScreen(resolverListAdapter)) {
            this.mMultiProfilePagerAdapter.showEmptyResolverListEmptyState(resolverListAdapter);
        } else {
            this.mMultiProfilePagerAdapter.showListView(resolverListAdapter);
        }
        if (!(z2 && maybeAutolaunchActivity()) && z) {
            maybeCreateHeader(resolverListAdapter);
            resetButtonBar();
            onListRebuilt(resolverListAdapter);
        }
    }

    protected void onListRebuilt(ResolverListAdapter resolverListAdapter) {
        ResolverDrawerLayout resolverDrawerLayout;
        setupAdapterListView((ListView) this.mMultiProfilePagerAdapter.getActiveAdapterView(), new ItemClickListener());
        if (shouldShowTabs() && isIntentPicker() && (resolverDrawerLayout = (ResolverDrawerLayout) findViewById(R.id.contentPanel)) != null) {
            resolverDrawerLayout.setMaxCollapsedHeight(getResources().getDimensionPixelSize(useLayoutWithDefault() ? R.dimen.resolver_max_collapsed_height_with_default_with_tabs : R.dimen.resolver_max_collapsed_height_with_tabs));
        }
    }

    protected boolean onTargetSelected(TargetInfo targetInfo, boolean z) {
        ComponentName[] componentNameArr;
        String strResolveType;
        ResolveInfo resolveInfo = targetInfo.getResolveInfo();
        Intent resolvedIntent = targetInfo != null ? targetInfo.getResolvedIntent() : null;
        if (resolvedIntent != null && ((this.mSupportsAlwaysUseOption || this.mMultiProfilePagerAdapter.getActiveListAdapter().hasFilteredItem()) && this.mMultiProfilePagerAdapter.getActiveListAdapter().getUnfilteredResolveList() != null)) {
            IntentFilter intentFilter = new IntentFilter();
            Intent selector = resolvedIntent.getSelector() != null ? resolvedIntent.getSelector() : resolvedIntent;
            String action = selector.getAction();
            if (action != null) {
                intentFilter.addAction(action);
            }
            Set<String> categories = selector.getCategories();
            if (categories != null) {
                Iterator<String> it = categories.iterator();
                while (it.hasNext()) {
                    intentFilter.addCategory(it.next());
                }
            }
            intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
            int i = resolveInfo.match & IntentFilter.MATCH_CATEGORY_MASK;
            Uri data = selector.getData();
            if (i == 6291456 && (strResolveType = selector.resolveType(this)) != null) {
                try {
                    intentFilter.addDataType(strResolveType);
                } catch (IntentFilter.MalformedMimeTypeException e) {
                    Log.w(TAG, e);
                    intentFilter = null;
                }
            }
            if (data != null && data.getScheme() != null && (i != 6291456 || (!ContentResolver.SCHEME_FILE.equals(data.getScheme()) && !"content".equals(data.getScheme())))) {
                intentFilter.addDataScheme(data.getScheme());
                Iterator<PatternMatcher> itSchemeSpecificPartsIterator = resolveInfo.filter.schemeSpecificPartsIterator();
                if (itSchemeSpecificPartsIterator != null) {
                    String schemeSpecificPart = data.getSchemeSpecificPart();
                    while (true) {
                        if (schemeSpecificPart == null || !itSchemeSpecificPartsIterator.hasNext()) {
                            break;
                        }
                        PatternMatcher next = itSchemeSpecificPartsIterator.next();
                        if (next.match(schemeSpecificPart)) {
                            intentFilter.addDataSchemeSpecificPart(next.getPath(), next.getType());
                            break;
                        }
                    }
                }
                Iterator<IntentFilter.AuthorityEntry> itAuthoritiesIterator = resolveInfo.filter.authoritiesIterator();
                if (itAuthoritiesIterator != null) {
                    while (true) {
                        if (!itAuthoritiesIterator.hasNext()) {
                            break;
                        }
                        IntentFilter.AuthorityEntry next2 = itAuthoritiesIterator.next();
                        if (next2.match(data) >= 0) {
                            int port = next2.getPort();
                            intentFilter.addDataAuthority(next2.getHost(), port >= 0 ? Integer.toString(port) : null);
                        }
                    }
                }
                Iterator<PatternMatcher> itPathsIterator = resolveInfo.filter.pathsIterator();
                if (itPathsIterator != null) {
                    String path = data.getPath();
                    while (true) {
                        if (path == null || !itPathsIterator.hasNext()) {
                            break;
                        }
                        PatternMatcher next3 = itPathsIterator.next();
                        if (next3.match(path)) {
                            intentFilter.addDataPath(next3.getPath(), next3.getType());
                            break;
                        }
                    }
                }
            }
            if (intentFilter != null) {
                int size = this.mMultiProfilePagerAdapter.getActiveListAdapter().getUnfilteredResolveList().size();
                boolean z2 = this.mMultiProfilePagerAdapter.getActiveListAdapter().getOtherProfile() != null;
                if (!z2) {
                    componentNameArr = new ComponentName[size];
                } else {
                    componentNameArr = new ComponentName[size + 1];
                }
                int i2 = 0;
                for (int i3 = 0; i3 < size; i3++) {
                    ResolveInfo resolveInfoAt = this.mMultiProfilePagerAdapter.getActiveListAdapter().getUnfilteredResolveList().get(i3).getResolveInfoAt(0);
                    componentNameArr[i3] = new ComponentName(resolveInfoAt.activityInfo.packageName, resolveInfoAt.activityInfo.name);
                    if (resolveInfoAt.match > i2) {
                        i2 = resolveInfoAt.match;
                    }
                }
                if (z2) {
                    componentNameArr[size] = this.mMultiProfilePagerAdapter.getActiveListAdapter().getOtherProfile().getResolvedComponentName();
                    int i4 = this.mMultiProfilePagerAdapter.getActiveListAdapter().getOtherProfile().getResolveInfo().match;
                    if (i4 > i2) {
                        i2 = i4;
                    }
                }
                if (z) {
                    int userId = getUserId();
                    PackageManager packageManager = getPackageManager();
                    packageManager.addPreferredActivity(intentFilter, i2, componentNameArr, resolvedIntent.getComponent());
                    if (resolveInfo.handleAllWebDataURI && TextUtils.isEmpty(packageManager.getDefaultBrowserPackageNameAsUser(userId))) {
                        packageManager.setDefaultBrowserPackageNameAsUser(resolveInfo.activityInfo.packageName, userId);
                    }
                } else {
                    try {
                        this.mMultiProfilePagerAdapter.getActiveListAdapter().mResolverListController.setLastChosen(resolvedIntent, intentFilter, i2);
                    } catch (RemoteException e2) {
                        Log.d(TAG, "Error calling setLastChosenActivity\n" + e2);
                    }
                }
            }
        }
        if (targetInfo != null) {
            if (resolvedIntent != null && isLaunchingTargetInOtherProfile()) {
                prepareIntentForCrossProfileLaunch(resolvedIntent);
            }
            safelyStartActivity(targetInfo);
            if (targetInfo.isSuspended()) {
                return false;
            }
        }
        return true;
    }

    private void prepareIntentForCrossProfileLaunch(Intent intent) {
        intent.fixUris(UserHandle.myUserId());
    }

    private boolean isLaunchingTargetInOtherProfile() {
        return this.mMultiProfilePagerAdapter.getCurrentUserHandle().getIdentifier() != UserHandle.myUserId();
    }

    public void safelyStartActivity(TargetInfo targetInfo) {
        StrictMode.disableDeathOnFileUriExposure();
        try {
            safelyStartActivityInternal(targetInfo);
        } finally {
            StrictMode.enableDeathOnFileUriExposure();
        }
    }

    private void safelyStartActivityInternal(TargetInfo targetInfo) {
        String launchedFromPackage;
        if (!targetInfo.isSuspended()) {
            PackageMonitor packageMonitor = this.mPersonalPackageMonitor;
            if (packageMonitor != null) {
                packageMonitor.unregister();
            }
            PackageMonitor packageMonitor2 = this.mWorkPackageMonitor;
            if (packageMonitor2 != null) {
                packageMonitor2.unregister();
            }
            this.mRegistered = false;
        }
        int i = this.mProfileSwitchMessageId;
        if (i != -1) {
            Toast.makeText(this, getString(i), 1).show();
        }
        UserHandle currentUserHandle = this.mMultiProfilePagerAdapter.getCurrentUserHandle();
        if (!this.mSafeForwardingMode) {
            if (targetInfo.startAsUser(this, null, currentUserHandle)) {
                onActivityStarted(targetInfo);
                maybeLogCrossProfileTargetLaunch(targetInfo, currentUserHandle);
                return;
            }
            return;
        }
        try {
            if (targetInfo.startAsCaller(this, null, currentUserHandle.getIdentifier())) {
                onActivityStarted(targetInfo);
                maybeLogCrossProfileTargetLaunch(targetInfo, currentUserHandle);
            }
        } catch (RuntimeException e) {
            try {
                launchedFromPackage = ActivityTaskManager.getService().getLaunchedFromPackage(getActivityToken());
            } catch (RemoteException unused) {
                launchedFromPackage = "??";
            }
            Slog.wtf(TAG, "Unable to launch as uid " + this.mLaunchedFromUid + " package " + launchedFromPackage + ", while running in " + ActivityThread.currentProcessName(), e);
        }
    }

    private void maybeLogCrossProfileTargetLaunch(TargetInfo targetInfo, UserHandle userHandle) {
        if (!hasWorkProfile() || userHandle.equals(getUser())) {
            return;
        }
        DevicePolicyEventLogger devicePolicyEventLogger = DevicePolicyEventLogger.createEvent(155).setBoolean(userHandle.equals(getPersonalProfileUserHandle()));
        String[] strArr = new String[2];
        strArr[0] = getMetricsCategory();
        strArr[1] = targetInfo instanceof ChooserTargetInfo ? ChooserActivity.LAUNCH_LOCATION_DIRECT_SHARE : "other_target";
        devicePolicyEventLogger.setStrings(strArr).write();
    }

    public boolean startAsCallerImpl(Intent intent, Bundle bundle, boolean z, int i) {
        try {
            IBinder iBinderRequestStartActivityPermissionToken = ActivityTaskManager.getService().requestStartActivityPermissionToken(getActivityToken());
            Intent intent2 = new Intent();
            ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(Resources.getSystem().getString(R.string.config_chooserActivity));
            intent2.setClassName(componentNameUnflattenFromString.getPackageName(), componentNameUnflattenFromString.getClassName());
            intent2.putExtra(ActivityTaskManager.EXTRA_PERMISSION_TOKEN, iBinderRequestStartActivityPermissionToken);
            intent2.putExtra(Intent.EXTRA_INTENT, intent);
            intent2.putExtra(ActivityTaskManager.EXTRA_OPTIONS, bundle);
            intent2.putExtra(ActivityTaskManager.EXTRA_IGNORE_TARGET_SECURITY, z);
            intent2.putExtra(Intent.EXTRA_USER_ID, i);
            intent2.addFlags(50331648);
            startActivity(intent2);
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, e.toString());
            return true;
        }
    }

    public boolean shouldAutoLaunchSingleChoice(TargetInfo targetInfo) {
        return !targetInfo.isSuspended();
    }

    void showTargetDetails(ResolveInfo resolveInfo) {
        startActivityAsUser(new Intent().setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(Uri.fromParts("package", resolveInfo.activityInfo.packageName, null)).addFlags(524288), this.mMultiProfilePagerAdapter.getCurrentUserHandle());
    }

    protected ResolverListAdapter createResolverListAdapter(Context context, List<Intent> list, Intent[] intentArr, List<ResolveInfo> list2, boolean z, UserHandle userHandle) {
        return new ResolverListAdapter(context, list, intentArr, list2, z, createListController(userHandle), this, getIntent().getBooleanExtra(EXTRA_IS_AUDIO_CAPTURE_DEVICE, false));
    }

    protected ResolverListController createListController(UserHandle userHandle) {
        return new ResolverListController(this, this.mPm, getTargetIntent(), getReferrerPackageName(), this.mLaunchedFromUid, userHandle);
    }

    private boolean configureContentView() {
        if (this.mMultiProfilePagerAdapter.getActiveListAdapter() == null) {
            throw new IllegalStateException("mMultiProfilePagerAdapter.getCurrentListAdapter() cannot be null.");
        }
        boolean z = this.mMultiProfilePagerAdapter.rebuildActiveTab(true) || this.mMultiProfilePagerAdapter.getActiveListAdapter().isTabLoaded();
        if (shouldShowTabs()) {
            z = z && (this.mMultiProfilePagerAdapter.rebuildInactiveTab(false) || this.mMultiProfilePagerAdapter.getInactiveListAdapter().isTabLoaded());
        }
        if (useLayoutWithDefault()) {
            this.mLayoutId = R.layout.resolver_list_with_default;
        } else {
            this.mLayoutId = getLayoutResource();
        }
        setContentView(this.mLayoutId);
        this.mMultiProfilePagerAdapter.setupViewPager((ViewPager) findViewById(R.id.profile_pager));
        return postRebuildList(z);
    }

    protected boolean postRebuildList(boolean z) {
        return postRebuildListInternal(z);
    }

    final boolean postRebuildListInternal(boolean z) {
        this.mMultiProfilePagerAdapter.getActiveListAdapter().getUnfilteredCount();
        if (z && maybeAutolaunchActivity()) {
            return true;
        }
        setupViewVisibilities();
        if (!shouldShowTabs()) {
            return false;
        }
        setupProfileTabs();
        return false;
    }

    private int isPermissionGranted(String str, int i) {
        return ActivityManager.checkComponentPermission(str, i, -1, true);
    }

    private boolean maybeAutolaunchActivity() {
        int itemCount = this.mMultiProfilePagerAdapter.getItemCount();
        if (itemCount == 1 && maybeAutolaunchIfSingleTarget()) {
            return true;
        }
        if (itemCount == 2 && this.mMultiProfilePagerAdapter.getActiveListAdapter().isTabLoaded() && this.mMultiProfilePagerAdapter.getInactiveListAdapter().isTabLoaded()) {
            return maybeAutolaunchIfNoAppsOnInactiveTab() || maybeAutolaunchIfCrossProfileSupported();
        }
        return false;
    }

    private boolean maybeAutolaunchIfSingleTarget() {
        if (this.mMultiProfilePagerAdapter.getActiveListAdapter().getUnfilteredCount() != 1 || this.mMultiProfilePagerAdapter.getActiveListAdapter().getOtherProfile() != null) {
            return false;
        }
        TargetInfo targetInfoTargetInfoForPosition = this.mMultiProfilePagerAdapter.getActiveListAdapter().targetInfoForPosition(0, false);
        if (!shouldAutoLaunchSingleChoice(targetInfoTargetInfoForPosition)) {
            return false;
        }
        safelyStartActivity(targetInfoTargetInfoForPosition);
        finish();
        return true;
    }

    private boolean maybeAutolaunchIfNoAppsOnInactiveTab() {
        if (this.mMultiProfilePagerAdapter.getActiveListAdapter().getUnfilteredCount() != 1 || this.mMultiProfilePagerAdapter.getInactiveListAdapter().getUnfilteredCount() != 0) {
            return false;
        }
        safelyStartActivity(this.mMultiProfilePagerAdapter.getActiveListAdapter().targetInfoForPosition(0, false));
        finish();
        return true;
    }

    private boolean maybeAutolaunchIfCrossProfileSupported() {
        ResolverListAdapter activeListAdapter = this.mMultiProfilePagerAdapter.getActiveListAdapter();
        if (activeListAdapter.getUnfilteredCount() != 1) {
            return false;
        }
        ResolverListAdapter inactiveListAdapter = this.mMultiProfilePagerAdapter.getInactiveListAdapter();
        if (inactiveListAdapter.getUnfilteredCount() != 1) {
            return false;
        }
        TargetInfo targetInfoTargetInfoForPosition = activeListAdapter.targetInfoForPosition(0, false);
        if (!Objects.equals(targetInfoTargetInfoForPosition.getResolvedComponentName(), inactiveListAdapter.targetInfoForPosition(0, false).getResolvedComponentName()) || !shouldAutoLaunchSingleChoice(targetInfoTargetInfoForPosition) || !canAppInteractCrossProfiles(targetInfoTargetInfoForPosition.getResolvedComponentName().getPackageName())) {
            return false;
        }
        DevicePolicyEventLogger.createEvent(161).setBoolean(activeListAdapter.getUserHandle().equals(getPersonalProfileUserHandle())).setStrings(getMetricsCategory()).write();
        safelyStartActivity(targetInfoTargetInfoForPosition);
        finish();
        return true;
    }

    private boolean canAppInteractCrossProfiles(String str) {
        try {
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(str, 0);
            if (!applicationInfo.crossProfile) {
                return false;
            }
            int i = applicationInfo.uid;
            return isPermissionGranted(Manifest.permission.INTERACT_ACROSS_USERS_FULL, i) == 0 || isPermissionGranted(Manifest.permission.INTERACT_ACROSS_USERS, i) == 0 || PermissionChecker.checkPermissionForPreflight(this, Manifest.permission.INTERACT_ACROSS_PROFILES, -1, i, str) == 0;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e(TAG, "Package " + str + " does not exist on current user.");
            return false;
        }
    }

    private boolean isAutolaunching() {
        return !this.mRegistered && isFinishing();
    }

    private void setupProfileTabs() {
        maybeHideDivider();
        final TabHost tabHost = (TabHost) findViewById(R.id.profile_tabhost);
        tabHost.setup();
        final ViewPager viewPager = (ViewPager) findViewById(R.id.profile_pager);
        viewPager.setSaveEnabled(false);
        tabHost.addTab(tabHost.newTabSpec("personal").setContent(R.id.profile_pager).setIndicator(getString(R.string.resolver_personal_tab)));
        tabHost.addTab(tabHost.newTabSpec(TAB_TAG_WORK).setContent(R.id.profile_pager).setIndicator(getString(R.string.resolver_work_tab)));
        final TabWidget tabWidget = tabHost.getTabWidget();
        tabWidget.setVisibility(0);
        resetTabsHeaderStyle(tabWidget);
        updateActiveTabStyle(tabHost);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() { // from class: com.android.internal.app.-$$Lambda$ResolverActivity$cgnMFvZhOzc6Du0108NcHVSlbGg
            @Override // android.widget.TabHost.OnTabChangeListener
            public final void onTabChanged(String str) {
                this.f$0.lambda$setupProfileTabs$0$ResolverActivity(tabWidget, tabHost, viewPager, str);
            }
        });
        viewPager.setVisibility(0);
        tabHost.setCurrentTab(this.mMultiProfilePagerAdapter.getCurrentPage());
        this.mMultiProfilePagerAdapter.setOnProfileSelectedListener(new AbstractMultiProfilePagerAdapter.OnProfileSelectedListener() { // from class: com.android.internal.app.ResolverActivity.3
            @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter.OnProfileSelectedListener
            public void onProfileSelected(int i) {
                tabHost.setCurrentTab(i);
                ResolverActivity.this.resetButtonBar();
                ResolverActivity.this.resetCheckedItem();
            }

            @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter.OnProfileSelectedListener
            public void onProfilePageStateChanged(int i) {
                ResolverActivity.this.onHorizontalSwipeStateChanged(i);
            }
        });
        this.mMultiProfilePagerAdapter.setOnSwitchOnWorkSelectedListener(new AbstractMultiProfilePagerAdapter.OnSwitchOnWorkSelectedListener() { // from class: com.android.internal.app.-$$Lambda$ResolverActivity$Ty2gHCo3QkhLKMXPCZ93HzQg9h4
            @Override // com.android.internal.app.AbstractMultiProfilePagerAdapter.OnSwitchOnWorkSelectedListener
            public final void onSwitchOnWorkSelected() {
                ResolverActivity.lambda$setupProfileTabs$1(tabHost);
            }
        });
        findViewById(R.id.resolver_tab_divider).setVisibility(0);
    }

    public /* synthetic */ void lambda$setupProfileTabs$0$ResolverActivity(TabWidget tabWidget, TabHost tabHost, ViewPager viewPager, String str) {
        resetTabsHeaderStyle(tabWidget);
        updateActiveTabStyle(tabHost);
        if ("personal".equals(str)) {
            viewPager.setCurrentItem(0);
        } else {
            viewPager.setCurrentItem(1);
        }
        setupViewVisibilities();
        maybeLogProfileChange();
        onProfileTabSelected();
        DevicePolicyEventLogger.createEvent(156).setInt(viewPager.getCurrentItem()).setStrings(getMetricsCategory()).write();
    }

    static /* synthetic */ void lambda$setupProfileTabs$1(TabHost tabHost) {
        View childAt = tabHost.getTabWidget().getChildAt(1);
        childAt.setFocusable(true);
        childAt.setFocusableInTouchMode(true);
        childAt.requestFocus();
    }

    private void maybeHideDivider() {
        View viewFindViewById;
        if (isIntentPicker() && (viewFindViewById = findViewById(R.id.divider)) != null) {
            viewFindViewById.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetCheckedItem() {
        if (isIntentPicker()) {
            this.mLastSelected = -1;
            ListView listView = (ListView) this.mMultiProfilePagerAdapter.getInactiveAdapterView();
            if (listView.getCheckedItemCount() > 0) {
                listView.setItemChecked(listView.getCheckedItemPosition(), false);
            }
        }
    }

    private void resetTabsHeaderStyle(TabWidget tabWidget) {
        String string = getString(R.string.resolver_work_tab_accessibility);
        String string2 = getString(R.string.resolver_personal_tab_accessibility);
        for (int i = 0; i < tabWidget.getChildCount(); i++) {
            View childAt = tabWidget.getChildAt(i);
            TextView textView = (TextView) childAt.findViewById(16908310);
            textView.setTextAppearance(16974264);
            textView.setTextColor(getAttrColor(this, 16843282));
            textView.setTextSize(0, getResources().getDimension(R.dimen.resolver_tab_text_size));
            if (textView.getText().equals(getString(R.string.resolver_personal_tab))) {
                childAt.setContentDescription(string2);
            } else if (textView.getText().equals(getString(R.string.resolver_work_tab))) {
                childAt.setContentDescription(string);
            }
        }
    }

    private static int getAttrColor(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        int color = typedArrayObtainStyledAttributes.getColor(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        return color;
    }

    private void updateActiveTabStyle(TabHost tabHost) {
        ((TextView) tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).findViewById(16908310)).setTextColor(getAttrColor(this, 16843829));
    }

    private void setupViewVisibilities() {
        ResolverListAdapter activeListAdapter = this.mMultiProfilePagerAdapter.getActiveListAdapter();
        if (this.mMultiProfilePagerAdapter.shouldShowEmptyStateScreen(activeListAdapter)) {
            return;
        }
        addUseDifferentAppLabelIfNecessary(activeListAdapter);
    }

    public void addUseDifferentAppLabelIfNecessary(ResolverListAdapter resolverListAdapter) {
        if (resolverListAdapter.hasFilteredItem()) {
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.stub);
            frameLayout.setVisibility(0);
            TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.resolver_different_item_header, (ViewGroup) null, false);
            if (shouldShowTabs()) {
                textView.setGravity(17);
            }
            frameLayout.addView(textView);
        }
    }

    private void setupAdapterListView(ListView listView, ItemClickListener itemClickListener) {
        listView.setOnItemClickListener(itemClickListener);
        listView.setOnItemLongClickListener(itemClickListener);
        if (this.mSupportsAlwaysUseOption) {
            listView.setChoiceMode(1);
        }
    }

    private void maybeCreateHeader(ResolverListAdapter resolverListAdapter) {
        TextView textView;
        if (this.mHeaderCreatorUser == null || resolverListAdapter.getUserHandle().equals(this.mHeaderCreatorUser)) {
            if (!shouldShowTabs() && resolverListAdapter.getCount() == 0 && resolverListAdapter.getPlaceholderCount() == 0 && (textView = (TextView) findViewById(16908310)) != null) {
                textView.setVisibility(8);
            }
            CharSequence titleForAction = this.mTitle;
            if (titleForAction == null) {
                titleForAction = getTitleForAction(getTargetIntent(), this.mDefaultTitleResId);
            }
            if (!TextUtils.isEmpty(titleForAction)) {
                TextView textView2 = (TextView) findViewById(16908310);
                if (textView2 != null) {
                    textView2.setText(titleForAction);
                }
                setTitle(titleForAction);
            }
            ImageView imageView = (ImageView) findViewById(16908294);
            if (imageView != null) {
                resolverListAdapter.loadFilteredItemIconTaskAsync(imageView);
            }
            this.mHeaderCreatorUser = resolverListAdapter.getUserHandle();
        }
    }

    protected void resetButtonBar() {
        if (this.mSupportsAlwaysUseOption) {
            ViewGroup viewGroup = (ViewGroup) findViewById(R.id.button_bar);
            if (viewGroup == null) {
                Log.e(TAG, "Layout unexpectedly does not have a button bar");
                return;
            }
            ResolverListAdapter activeListAdapter = this.mMultiProfilePagerAdapter.getActiveListAdapter();
            View viewFindViewById = findViewById(R.id.resolver_button_bar_divider);
            if (!useLayoutWithDefault()) {
                Insets insets = this.mSystemWindowInsets;
                viewGroup.setPadding(viewGroup.getPaddingLeft(), viewGroup.getPaddingTop(), viewGroup.getPaddingRight(), getResources().getDimensionPixelSize(R.dimen.resolver_button_bar_spacing) + (insets != null ? insets.bottom : 0));
            }
            if (activeListAdapter.isTabLoaded() && this.mMultiProfilePagerAdapter.shouldShowEmptyStateScreen(activeListAdapter) && !useLayoutWithDefault()) {
                viewGroup.setVisibility(4);
                if (viewFindViewById != null) {
                    viewFindViewById.setVisibility(4);
                }
                setButtonBarIgnoreOffset(false);
                return;
            }
            if (viewFindViewById != null) {
                viewFindViewById.setVisibility(0);
            }
            viewGroup.setVisibility(0);
            setButtonBarIgnoreOffset(true);
            this.mOnceButton = (Button) viewGroup.findViewById(R.id.button_once);
            this.mAlwaysButton = (Button) viewGroup.findViewById(R.id.button_always);
            resetAlwaysOrOnceButtonBar();
        }
    }

    private void setButtonBarIgnoreOffset(boolean z) {
        View viewFindViewById = findViewById(R.id.button_bar_container);
        if (viewFindViewById != null) {
            ResolverDrawerLayout.LayoutParams layoutParams = (ResolverDrawerLayout.LayoutParams) viewFindViewById.getLayoutParams();
            layoutParams.ignoreOffset = z;
            viewFindViewById.setLayoutParams(layoutParams);
        }
    }

    private void resetAlwaysOrOnceButtonBar() {
        setAlwaysButtonEnabled(false, -1, false);
        this.mOnceButton.setEnabled(false);
        int filteredPosition = this.mMultiProfilePagerAdapter.getActiveListAdapter().getFilteredPosition();
        if (useLayoutWithDefault() && filteredPosition != -1) {
            setAlwaysButtonEnabled(true, filteredPosition, false);
            this.mOnceButton.setEnabled(true);
            this.mOnceButton.requestFocus();
        } else {
            ListView listView = (ListView) this.mMultiProfilePagerAdapter.getActiveAdapterView();
            if (listView == null || listView.getCheckedItemPosition() == -1) {
                return;
            }
            setAlwaysButtonEnabled(true, listView.getCheckedItemPosition(), true);
            this.mOnceButton.setEnabled(true);
        }
    }

    @Override // com.android.internal.app.ResolverListAdapter.ResolverListCommunicator
    public boolean useLayoutWithDefault() {
        boolean zHasFilteredItem;
        if (this.mMultiProfilePagerAdapter.getCurrentUserHandle().getIdentifier() == UserHandle.myUserId()) {
            zHasFilteredItem = this.mMultiProfilePagerAdapter.getActiveListAdapter().hasFilteredItem();
        } else {
            zHasFilteredItem = this.mMultiProfilePagerAdapter.getInactiveListAdapter().hasFilteredItem();
        }
        return this.mSupportsAlwaysUseOption && zHasFilteredItem;
    }

    protected void setRetainInOnStop(boolean z) {
        this.mRetainInOnStop = z;
    }

    @Override // com.android.internal.app.ResolverListAdapter.ResolverListCommunicator
    public boolean resolveInfoMatch(ResolveInfo resolveInfo, ResolveInfo resolveInfo2) {
        if (resolveInfo == null) {
            if (resolveInfo2 == null) {
                return true;
            }
        } else if (resolveInfo.activityInfo == null) {
            if (resolveInfo2.activityInfo == null) {
                return true;
            }
        } else if (Objects.equals(resolveInfo.activityInfo.name, resolveInfo2.activityInfo.name) && Objects.equals(resolveInfo.activityInfo.packageName, resolveInfo2.activityInfo.packageName)) {
            return true;
        }
        return false;
    }

    public void onHandlePackagesChanged(ResolverListAdapter resolverListAdapter) {
        if (resolverListAdapter == this.mMultiProfilePagerAdapter.getActiveListAdapter()) {
            if (!(resolverListAdapter.getUserHandle().equals(getWorkProfileUserHandle()) && this.mMultiProfilePagerAdapter.isWaitingToEnableWorkProfile()) && this.mMultiProfilePagerAdapter.rebuildActiveTab(true)) {
                ResolverListAdapter activeListAdapter = this.mMultiProfilePagerAdapter.getActiveListAdapter();
                activeListAdapter.notifyDataSetChanged();
                if (activeListAdapter.getCount() != 0 || inactiveListAdapterHasItems()) {
                    return;
                }
                finish();
                return;
            }
            return;
        }
        this.mMultiProfilePagerAdapter.clearInactiveProfileCache();
    }

    private boolean inactiveListAdapterHasItems() {
        return shouldShowTabs() && this.mMultiProfilePagerAdapter.getInactiveListAdapter().getCount() > 0;
    }

    private BroadcastReceiver createWorkProfileStateReceiver() {
        return new BroadcastReceiver() { // from class: com.android.internal.app.ResolverActivity.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if ((TextUtils.equals(action, Intent.ACTION_USER_UNLOCKED) || TextUtils.equals(action, Intent.ACTION_MANAGED_PROFILE_UNAVAILABLE) || TextUtils.equals(action, Intent.ACTION_MANAGED_PROFILE_AVAILABLE)) && intent.getIntExtra(Intent.EXTRA_USER_HANDLE, -1) == ResolverActivity.this.getWorkProfileUserHandle().getIdentifier()) {
                    if (ResolverActivity.this.isWorkProfileEnabled()) {
                        if (ResolverActivity.this.mWorkProfileHasBeenEnabled) {
                            return;
                        }
                        ResolverActivity.this.mWorkProfileHasBeenEnabled = true;
                        ResolverActivity.this.mMultiProfilePagerAdapter.markWorkProfileEnabledBroadcastReceived();
                    } else {
                        ResolverActivity.this.mWorkProfileHasBeenEnabled = false;
                    }
                    if (ResolverActivity.this.mMultiProfilePagerAdapter.getCurrentUserHandle().equals(ResolverActivity.this.getWorkProfileUserHandle())) {
                        ResolverActivity.this.mMultiProfilePagerAdapter.rebuildActiveTab(true);
                    } else {
                        ResolverActivity.this.mMultiProfilePagerAdapter.clearInactiveProfileCache();
                    }
                }
            }
        };
    }

    public static final class ResolvedComponentInfo {
        private boolean mPinned;
        public final ComponentName name;
        private final List<Intent> mIntents = new ArrayList();
        private final List<ResolveInfo> mResolveInfos = new ArrayList();

        public ResolvedComponentInfo(ComponentName componentName, Intent intent, ResolveInfo resolveInfo) {
            this.name = componentName;
            add(intent, resolveInfo);
        }

        public void add(Intent intent, ResolveInfo resolveInfo) {
            this.mIntents.add(intent);
            this.mResolveInfos.add(resolveInfo);
        }

        public int getCount() {
            return this.mIntents.size();
        }

        public Intent getIntentAt(int i) {
            if (i >= 0) {
                return this.mIntents.get(i);
            }
            return null;
        }

        public ResolveInfo getResolveInfoAt(int i) {
            if (i >= 0) {
                return this.mResolveInfos.get(i);
            }
            return null;
        }

        public int findIntent(Intent intent) {
            int size = this.mIntents.size();
            for (int i = 0; i < size; i++) {
                if (intent.equals(this.mIntents.get(i))) {
                    return i;
                }
            }
            return -1;
        }

        public int findResolveInfo(ResolveInfo resolveInfo) {
            int size = this.mResolveInfos.size();
            for (int i = 0; i < size; i++) {
                if (resolveInfo.equals(this.mResolveInfos.get(i))) {
                    return i;
                }
            }
            return -1;
        }

        public boolean isPinned() {
            return this.mPinned;
        }

        public void setPinned(boolean z) {
            this.mPinned = z;
        }
    }

    class ItemClickListener implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
        ItemClickListener() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            ListView listView = adapterView instanceof ListView ? (ListView) adapterView : null;
            if (listView != null) {
                i -= listView.getHeaderViewsCount();
            }
            if (i >= 0 && ResolverActivity.this.mMultiProfilePagerAdapter.getActiveListAdapter().resolveInfoForPosition(i, true) != null) {
                ListView listView2 = (ListView) ResolverActivity.this.mMultiProfilePagerAdapter.getActiveAdapterView();
                int checkedItemPosition = listView2.getCheckedItemPosition();
                boolean z = checkedItemPosition != -1;
                if (!ResolverActivity.this.useLayoutWithDefault() && ((!z || ResolverActivity.this.mLastSelected != checkedItemPosition) && ResolverActivity.this.mAlwaysButton != null)) {
                    ResolverActivity.this.setAlwaysButtonEnabled(z, checkedItemPosition, true);
                    ResolverActivity.this.mOnceButton.setEnabled(z);
                    if (z) {
                        listView2.smoothScrollToPosition(checkedItemPosition);
                        ResolverActivity.this.mOnceButton.requestFocus();
                    }
                    ResolverActivity.this.mLastSelected = checkedItemPosition;
                    return;
                }
                ResolverActivity.this.startSelected(i, false, true);
            }
        }

        @Override // android.widget.AdapterView.OnItemLongClickListener
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            ListView listView = adapterView instanceof ListView ? (ListView) adapterView : null;
            if (listView != null) {
                i -= listView.getHeaderViewsCount();
            }
            if (i < 0) {
                return false;
            }
            ResolverActivity.this.showTargetDetails(ResolverActivity.this.mMultiProfilePagerAdapter.getActiveListAdapter().resolveInfoForPosition(i, true));
            return true;
        }
    }

    static class PickTargetOptionRequest extends VoiceInteractor.PickOptionRequest {
        public PickTargetOptionRequest(VoiceInteractor.Prompt prompt, VoiceInteractor.PickOptionRequest.Option[] optionArr, Bundle bundle) {
            super(prompt, optionArr, bundle);
        }

        @Override // android.app.VoiceInteractor.Request
        public void onCancel() {
            super.onCancel();
            ResolverActivity resolverActivity = (ResolverActivity) getActivity();
            if (resolverActivity != null) {
                resolverActivity.mPickOptionRequest = null;
                resolverActivity.finish();
            }
        }

        @Override // android.app.VoiceInteractor.PickOptionRequest
        public void onPickOptionResult(boolean z, VoiceInteractor.PickOptionRequest.Option[] optionArr, Bundle bundle) {
            ResolverActivity resolverActivity;
            super.onPickOptionResult(z, optionArr, bundle);
            if (optionArr.length == 1 && (resolverActivity = (ResolverActivity) getActivity()) != null && resolverActivity.onTargetSelected(resolverActivity.mMultiProfilePagerAdapter.getActiveListAdapter().getItem(optionArr[0].getIndex()), false)) {
                resolverActivity.mPickOptionRequest = null;
                resolverActivity.finish();
            }
        }
    }
}
