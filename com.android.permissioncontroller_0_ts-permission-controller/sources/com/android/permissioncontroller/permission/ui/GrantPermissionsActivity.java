package com.android.permissioncontroller.permission.ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.IActivityManager;
import android.app.IProcessObserver;
import android.app.KeyguardManager;
import android.app.UiModeManager;
import android.app.admin.DevicePolicyManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.permission.PermissionManager;
import android.text.style.ClickableSpan;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.core.util.Consumer;
import com.android.permissioncontroller.Constants;
import com.android.permissioncontroller.DeviceUtils;
import com.android.permissioncontroller.PermissionControllerStatsLog;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.model.AppPermissionGroup;
import com.android.permissioncontroller.permission.model.AppPermissions;
import com.android.permissioncontroller.permission.model.Permission;
import com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler;
import com.android.permissioncontroller.permission.ui.auto.GrantPermissionsAutoViewHandler;
import com.android.permissioncontroller.permission.ui.television.GrantPermissionsViewHandlerImpl;
import com.android.permissioncontroller.permission.ui.wear.GrantPermissionsWearViewHandler;
import com.android.permissioncontroller.permission.utils.ArrayUtils;
import com.android.permissioncontroller.permission.utils.PackageRemovalMonitor;
import com.android.permissioncontroller.permission.utils.SafetyNetLogger;
import com.android.permissioncontroller.permission.utils.Utils;
import com.ts.permission.CarPermissionManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ts.car.utils.LogUtil;

/* JADX INFO: loaded from: classes8.dex */
public class GrantPermissionsActivity extends Activity implements GrantPermissionsViewHandler.ResultListener {
    public static final int ALLOW_ALWAYS_BUTTON = 1;
    public static final int ALLOW_BUTTON = 0;
    public static final int ALLOW_FOREGROUND_BUTTON = 2;
    public static final int ALLOW_ONE_TIME_BUTTON = 5;
    public static final String ANNOTATION_ID = "link";
    public static String APP_NAME = null;
    private static final int APP_PERMISSION_REQUEST_CODE = 1;
    private static final int DEFAULT_VALUE = -1;
    public static final int DENY_AND_DONT_ASK_AGAIN_BUTTON = 4;
    public static final int DENY_BUTTON = 3;
    public static final int LINK_TO_SETTINGS = 10;
    private static final String LOG_TAG = "GrantPermissionsActivity";
    public static final int NEXT_BUTTON = 11;
    public static final int NO_UPGRADE_AND_DONT_ASK_AGAIN_BUTTON = 7;
    public static final int NO_UPGRADE_BUTTON = 6;
    public static final int NO_UPGRADE_OT_AND_DONT_ASK_AGAIN_BUTTON = 9;
    public static final int NO_UPGRADE_OT_BUTTON = 8;
    private static final String TSPS_TAG = "TSPS_GrantPermissionsActivity";
    private static final String UI_MODE_CHANGED = "ui_mode_changed";
    private static final int UI_MODE_NIGHT_NO = 19;
    private static final int UI_MODE_NIGHT_YES = 35;
    List<String> groupName;
    private ActivityManager mActivityManager;
    private Consumer<Intent> mActivityResultCallback;
    private IActivityManager mAm;
    private AppPermissions mAppPermissions;
    private AutoGrantPermissionsNotifier mAutoGrantPermissionsNotifier;
    private boolean[] mButtonVisibilities;
    private String mCallingPackage;
    private PackageInfo mCallingPackageInfo;
    private int mCallingPackagePid;
    private int mCallingUid;
    private boolean mCouldHaveFgCapabilities;
    private int mCurrentDayNightMode;
    private PackageRemovalMonitor mPackageRemovalMonitor;
    private PackageManager.OnPermissionsChangedListener mPermissionChangeListener;
    private ProcessObserver mProcessObserver;
    private long mRequestId;
    private String[] mRequestedPermissions;
    boolean mResultSet;
    SharedPreferences mSp;
    UiModeManager mUiModeManager;
    private GrantPermissionsViewHandler mViewHandler;
    private static final String KEY_REQUEST_ID = GrantPermissionsActivity.class.getName() + "_REQUEST_ID";
    public static boolean IS_CONFIG_CHANGED = false;
    private ArrayMap<Pair<String, Boolean>, GroupState> mRequestGrantPermissionGroups = new ArrayMap<>();
    private ArraySet<String> mPermissionGroupsToSkip = new ArraySet<>();

    private int getPermissionPolicy() {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) getSystemService(DevicePolicyManager.class);
        return devicePolicyManager.getPermissionPolicy(null);
    }

    private void addRequestedPermissions(AppPermissionGroup group, String permName, boolean isFirstInstance) {
        if (!group.isGrantingAllowed()) {
            reportRequestResult(permName, 1);
            return;
        }
        Permission permission = group.getPermission(permName);
        if (permission == null && ArrayUtils.contains(this.mAppPermissions.getPackageInfo().requestedPermissions, permName)) {
            reportRequestResult(permName, 9);
            return;
        }
        if (group.isUserFixed()) {
            reportRequestResult(permName, 2);
            return;
        }
        if ((group.isPolicyFixed() && !group.areRuntimePermissionsGranted()) || permission.isPolicyFixed()) {
            reportRequestResult(permName, 3);
            return;
        }
        Pair<String, Boolean> groupKey = new Pair<>(group.getName(), Boolean.valueOf(group.isBackgroundGroup()));
        GroupState state = this.mRequestGrantPermissionGroups.get(groupKey);
        if (state == null) {
            state = new GroupState(group);
            this.mRequestGrantPermissionGroups.put(groupKey, state);
        }
        state.affectedPermissions = ArrayUtils.appendString(state.affectedPermissions, permName);
        int result = CarPermissionManager.getPermissionManager(this).checkPermission(this.mCallingPackageInfo.packageName, permName);
        if (result == 1) {
            LogUtil.info("package name (" + this.mCallingPackageInfo.packageName + ") runtime permission (" + permName + ") is GRANTED");
            String[] filterPermissions = {permName};
            group.grantRuntimePermissions(false, false, filterPermissions);
            group.setPolicyFixed(filterPermissions);
            state.mState = 1;
            getAutoGrantNotifier().onPermissionAutoGranted(permName);
            return;
        }
        if (result == 2) {
            LogUtil.info("package name (" + this.mCallingPackageInfo.packageName + ") runtime permission (" + permName + ") is denied");
            group.setPolicyFixed(new String[]{permName});
            state.mState = 2;
            reportRequestResult(permName, 8);
            return;
        }
        boolean skipGroup = false;
        int permissionPolicy = getPermissionPolicy();
        if (permissionPolicy == 1) {
            String[] filterPermissions2 = {permName};
            group.grantRuntimePermissions(false, false, filterPermissions2);
            group.setPolicyFixed(filterPermissions2);
            state.mState = 1;
            skipGroup = true;
            getAutoGrantNotifier().onPermissionAutoGranted(permName);
            reportRequestResult(permName, 5);
        } else if (permissionPolicy == 2) {
            group.setPolicyFixed(new String[]{permName});
            state.mState = 2;
            skipGroup = true;
            reportRequestResult(permName, 8);
        } else if (group.areRuntimePermissionsGranted()) {
            group.grantRuntimePermissions(false, false, new String[]{permName});
            state.mState = 1;
            skipGroup = true;
            reportRequestResult(permName, 5);
        }
        if (skipGroup && isFirstInstance) {
            state.mState = 3;
        }
    }

    private void reportRequestResult(String permission, int result) {
        boolean isImplicit = !ArrayUtils.contains(this.mRequestedPermissions, permission);
        Log.v(LOG_TAG, "Permission grant result requestId=" + this.mRequestId + " callingUid=" + this.mCallingUid + " callingPackage=" + this.mCallingPackage + " permission=" + permission + " isImplicit=" + isImplicit + " result=" + result);
        PermissionControllerStatsLog.write(PermissionControllerStatsLog.PERMISSION_GRANT_REQUEST_RESULT_REPORTED, this.mRequestId, this.mCallingUid, this.mCallingPackage, permission, isImplicit, result);
    }

    private void reportRequestResult(String[] permissions, int result) {
        for (String permission : permissions) {
            reportRequestResult(permission, result);
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Log.e(LOG_TAG, "onCreate: ");
        if (icicle == null) {
            this.mRequestId = new Random().nextLong();
        } else {
            this.mRequestId = icicle.getLong(KEY_REQUEST_ID);
        }
        getWindow().addSystemFlags(524288);
        this.mCallingPackage = getCallingPackage();
        this.mActivityManager = (ActivityManager) getSystemService("activity");
        this.mAm = ActivityManager.getService();
        this.mProcessObserver = new ProcessObserver();
        try {
            this.mCallingPackagePid = getPidByPackageName(this.mCallingPackage);
            Log.e(LOG_TAG, "getPidByPackageName mCallingPackagePid : " + this.mCallingPackagePid);
        } catch (Exception ex) {
            Log.e(LOG_TAG, "getPidByPackageName ex ", ex);
        }
        try {
            this.mAm.registerProcessObserver(this.mProcessObserver);
        } catch (RemoteException ex2) {
            Log.e(LOG_TAG, "cannot register activity monitoring", ex2);
        }
        try {
            this.mCouldHaveFgCapabilities = Utils.couldHaveForegroundCapabilities(this, this.mCallingPackage);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(LOG_TAG, "Calling package " + this.mCallingPackage + " not found", e);
        }
        setFinishOnTouchOutside(false);
        setTitle(R.string.permission_request_title);
        String[] stringArrayExtra = getIntent().getStringArrayExtra("android.content.pm.extra.REQUEST_PERMISSIONS_NAMES");
        this.mRequestedPermissions = stringArrayExtra;
        if (stringArrayExtra == null) {
            this.mRequestedPermissions = new String[0];
        }
        int requestedPermCount = this.mRequestedPermissions.length;
        if (requestedPermCount == 0) {
            setResultAndFinish();
            return;
        }
        PackageInfo callingPackageInfo = getCallingPackageInfo();
        if (callingPackageInfo == null || callingPackageInfo.requestedPermissions == null || callingPackageInfo.requestedPermissions.length <= 0) {
            setResultAndFinish();
            return;
        }
        if (callingPackageInfo.applicationInfo.targetSdkVersion < 23) {
            this.mRequestedPermissions = new String[0];
            setResultAndFinish();
            return;
        }
        this.mCallingPackageInfo = callingPackageInfo;
        int i = callingPackageInfo.applicationInfo.uid;
        this.mCallingUid = i;
        UserHandle userHandle = UserHandle.getUserHandleForUid(i);
        if (DeviceUtils.isTelevision(this)) {
            this.mViewHandler = new GrantPermissionsViewHandlerImpl(this, this.mCallingPackage).setResultListener((GrantPermissionsViewHandler.ResultListener) this);
        } else if (DeviceUtils.isWear(this)) {
            this.mViewHandler = new GrantPermissionsWearViewHandler(this).setResultListener((GrantPermissionsViewHandler.ResultListener) this);
        } else if (DeviceUtils.isAuto(this)) {
            this.mViewHandler = new GrantPermissionsAutoViewHandler(this, this.mCallingPackage).setResultListener(this);
        } else {
            this.mViewHandler = new com.android.permissioncontroller.permission.ui.handheld.GrantPermissionsViewHandlerImpl(this, this.mCallingPackage, userHandle).setResultListener((GrantPermissionsViewHandler.ResultListener) this);
        }
        this.mAppPermissions = new AppPermissions(this, callingPackageInfo, false, new Runnable() { // from class: com.android.permissioncontroller.permission.ui.GrantPermissionsActivity.1
            @Override // java.lang.Runnable
            public void run() {
                GrantPermissionsActivity.this.setResultAndFinish();
            }
        });
        String[] strArr = this.mRequestedPermissions;
        int length = strArr.length;
        int i2 = 0;
        while (true) {
            int i3 = 1;
            if (i2 >= length) {
                break;
            }
            String requestedPermission = strArr[i2];
            if (requestedPermission != null) {
                ArrayList<String> affectedPermissions = computeAffectedPermissions(requestedPermission);
                LogUtil.info("requestedPermission:" + requestedPermission + "affectedPermissions:" + affectedPermissions.toString());
                int numAffectedPermissions = affectedPermissions.size();
                int i4 = 0;
                while (i4 < numAffectedPermissions) {
                    AppPermissionGroup group = this.mAppPermissions.getGroupForPermission(affectedPermissions.get(i4));
                    if (group == null) {
                        reportRequestResult(affectedPermissions.get(i4), i3);
                    } else {
                        if (this.mAppPermissions.getPackageInfo().applicationInfo.targetSdkVersion >= 30 && this.mRequestedPermissions.length > 1 && group.isBackgroundGroup()) {
                            Log.e(LOG_TAG, "Apps targeting 30 must have foreground permission before requesting background and must request background on its own.");
                            finish();
                        }
                        addRequestedPermissions(group, affectedPermissions.get(i4), icicle == null);
                    }
                    i4++;
                    i3 = 1;
                }
            }
            i2++;
        }
        int numGroupStates = this.mRequestGrantPermissionGroups.size();
        for (int groupStateNum = 0; groupStateNum < numGroupStates; groupStateNum++) {
            GroupState groupState = this.mRequestGrantPermissionGroups.valueAt(groupStateNum);
            AppPermissionGroup group2 = groupState.mGroup;
            if (icicle != null) {
                groupState.mState = icicle.getInt(getInstanceStateKey(this.mRequestGrantPermissionGroups.keyAt(groupStateNum)), groupState.mState);
            }
            if (group2.isBackgroundGroup()) {
                boolean foregroundGroupAlreadyGranted = this.mAppPermissions.getPermissionGroup(group2.getName()).areRuntimePermissionsGranted();
                boolean hasForegroundRequest = getForegroundGroupState(group2.getName()) != null;
                if (!foregroundGroupAlreadyGranted && !hasForegroundRequest) {
                    int numPermissions = groupState.affectedPermissions.length;
                    for (int permissionNum = 0; permissionNum < numPermissions; permissionNum++) {
                        Log.w(LOG_TAG, "Cannot grant " + groupState.affectedPermissions[permissionNum] + " as the matching foreground permission is not already granted.");
                    }
                    groupState.mState = 3;
                    reportRequestResult(groupState.affectedPermissions, 1);
                }
            }
        }
        setContentView(this.mViewHandler.createView());
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        this.mViewHandler.updateWindowAttributes(layoutParams);
        window.setAttributes(layoutParams);
        if (!showNextPermissionGroupGrantRequest()) {
            setResultAndFinish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateIfPermissionsWereGranted() {
        PackageManager pm = getPackageManager();
        boolean mightShowNextGroup = true;
        int numGroupStates = this.mRequestGrantPermissionGroups.size();
        for (int i = 0; i < numGroupStates; i++) {
            GroupState groupState = this.mRequestGrantPermissionGroups.valueAt(i);
            if (groupState != null && groupState.mState == 0) {
                boolean allAffectedPermissionsOfThisGroupAreGranted = true;
                if (groupState.affectedPermissions == null) {
                    allAffectedPermissionsOfThisGroupAreGranted = false;
                } else {
                    int permNum = 0;
                    while (true) {
                        if (permNum >= groupState.affectedPermissions.length) {
                            break;
                        }
                        if (pm.checkPermission(groupState.affectedPermissions[permNum], this.mCallingPackage) != -1) {
                            permNum++;
                        } else {
                            allAffectedPermissionsOfThisGroupAreGranted = false;
                            break;
                        }
                    }
                }
                if (allAffectedPermissionsOfThisGroupAreGranted) {
                    groupState.mState = 1;
                    if (mightShowNextGroup && !showNextPermissionGroupGrantRequest()) {
                        setResultAndFinish();
                    }
                } else {
                    mightShowNextGroup = false;
                }
            }
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        try {
            this.mPermissionChangeListener = new PermissionChangeListener();
            PackageManager pm = getPackageManager();
            pm.addOnPermissionsChangeListener(this.mPermissionChangeListener);
            PackageRemovalMonitor packageRemovalMonitor = new PackageRemovalMonitor(this, this.mCallingPackage) { // from class: com.android.permissioncontroller.permission.ui.GrantPermissionsActivity.2
                @Override // com.android.permissioncontroller.permission.utils.PackageRemovalMonitor
                public void onPackageRemoved() {
                    Log.w(GrantPermissionsActivity.LOG_TAG, GrantPermissionsActivity.this.mCallingPackage + " was uninstalled");
                    GrantPermissionsActivity.this.finish();
                }
            };
            this.mPackageRemovalMonitor = packageRemovalMonitor;
            packageRemovalMonitor.register();
            try {
                pm.getPackageInfo(this.mCallingPackage, 0);
            } catch (PackageManager.NameNotFoundException e) {
                Log.w(LOG_TAG, this.mCallingPackage + " was uninstalled while this activity was stopped", e);
                finish();
            }
            updateIfPermissionsWereGranted();
        } catch (PackageManager.NameNotFoundException e2) {
            setResultAndFinish();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        Log.e(LOG_TAG, "onStop: ");
        PackageRemovalMonitor packageRemovalMonitor = this.mPackageRemovalMonitor;
        if (packageRemovalMonitor != null) {
            packageRemovalMonitor.unregister();
            this.mPackageRemovalMonitor = null;
        }
        if (this.mPermissionChangeListener != null) {
            getPackageManager().removeOnPermissionsChangeListener(this.mPermissionChangeListener);
            this.mPermissionChangeListener = null;
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Log.e(LOG_TAG, "onDestroy: ");
        GrantPermissionsViewHandler grantPermissionsViewHandler = this.mViewHandler;
        if (grantPermissionsViewHandler != null) {
            grantPermissionsViewHandler.onDestroy();
        }
        try {
            this.mAm.unregisterProcessObserver(this.mProcessObserver);
        } catch (RemoteException ex) {
            Log.e(LOG_TAG, "Failed to unregister listeners", ex);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View rootView = getWindow().getDecorView();
        if (rootView.getTop() != 0) {
            ev.setLocation(ev.getX(), ev.getY() - rootView.getTop());
        }
        return super.dispatchTouchEvent(ev);
    }

    private static String getInstanceStateKey(Pair<String, Boolean> requestGrantPermissionGroupsKey) {
        return GrantPermissionsActivity.class.getName() + "_" + ((String) requestGrantPermissionGroupsKey.first) + "_" + requestGrantPermissionGroupsKey.second;
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.mViewHandler.saveInstanceState(outState);
        outState.putLong(KEY_REQUEST_ID, this.mRequestId);
        int numGroups = this.mRequestGrantPermissionGroups.size();
        for (int i = 0; i < numGroups; i++) {
            int state = this.mRequestGrantPermissionGroups.valueAt(i).mState;
            if (state != 0) {
                outState.putInt(getInstanceStateKey(this.mRequestGrantPermissionGroups.keyAt(i)), state);
            }
        }
    }

    private GroupState getBackgroundGroupState(String name) {
        return this.mRequestGrantPermissionGroups.get(new Pair(name, true));
    }

    private GroupState getForegroundGroupState(String name) {
        return this.mRequestGrantPermissionGroups.get(new Pair(name, false));
    }

    private boolean shouldShowRequestForGroupState(GroupState groupState) {
        if (groupState.mState == 3) {
            return false;
        }
        GroupState foregroundGroup = getForegroundGroupState(groupState.mGroup.getName());
        if (groupState.mGroup.isBackgroundGroup() && foregroundGroup != null && shouldShowRequestForGroupState(foregroundGroup)) {
            return false;
        }
        return !this.mPermissionGroupsToSkip.contains(groupState.mGroup.getName());
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x034f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean showNextPermissionGroupGrantRequest() {
        /*
            Method dump skipped, instruction units count: 926
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.permission.ui.GrantPermissionsActivity.showNextPermissionGroupGrantRequest():boolean");
    }

    static /* synthetic */ int lambda$showNextPermissionGroupGrantRequest$0(GroupState s1, GroupState s2) {
        return -Boolean.compare(s1.mGroup.supportsOneTimeGrant(), s2.mGroup.supportsOneTimeGrant());
    }

    private void sendToSettings(final GroupState groupState) {
        if (this.mActivityResultCallback == null) {
            startAppPermissionFragment(groupState);
            this.mActivityResultCallback = new Consumer() { // from class: com.android.permissioncontroller.permission.ui.-$$Lambda$GrantPermissionsActivity$xSuPeiPvtefs9ePWJ-I6NgTE6aU
                @Override // androidx.core.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$sendToSettings$1$GrantPermissionsActivity(groupState, (Intent) obj);
                }
            };
        }
    }

    public /* synthetic */ void lambda$sendToSettings$1$GrantPermissionsActivity(GroupState groupState, Intent data) {
        if (data == null || data.getStringExtra("com.android.permissioncontroller.extra.RESULT_PERMISSION_INTERACTED") == null) {
            if (groupState.mGroup.isUserSet()) {
                groupState.mGroup.setUserFixed(true);
            } else {
                groupState.mGroup.setUserSet(true);
            }
        }
        this.mPermissionGroupsToSkip.add(groupState.mGroup.getName());
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.permission.ui.GrantPermissionsActivity$3, reason: invalid class name */
    class AnonymousClass3 extends ClickableSpan {
        final /* synthetic */ GroupState val$groupState;

        AnonymousClass3(GroupState groupState) {
            this.val$groupState = groupState;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View widget) {
            GrantPermissionsActivity.this.logGrantPermissionActivityButtons(this.val$groupState.mGroup.getName(), -2);
            GrantPermissionsActivity.this.startAppPermissionFragment(this.val$groupState);
            GrantPermissionsActivity.this.mActivityResultCallback = new Consumer() { // from class: com.android.permissioncontroller.permission.ui.-$$Lambda$GrantPermissionsActivity$3$GgpZErds3daSvtLDevuUrRQHS24
                @Override // androidx.core.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$onClick$0$GrantPermissionsActivity$3((Intent) obj);
                }
            };
        }

        public /* synthetic */ void lambda$onClick$0$GrantPermissionsActivity$3(Intent data) {
            String groupName;
            if (data != null && (groupName = data.getStringExtra("com.android.permissioncontroller.extra.RESULT_PERMISSION_INTERACTED")) != null) {
                GrantPermissionsActivity.this.mPermissionGroupsToSkip.add(groupName);
                int result = data.getIntExtra("com.android.permissioncontroller.extra.PERMISSION_RESULT", -1);
                GrantPermissionsActivity.this.logSettingsInteraction(groupName, result);
            }
        }
    }

    private ClickableSpan getLinkToAppPermissions(GroupState groupState) {
        return new AnonymousClass3(groupState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logSettingsInteraction(String groupName, int result) {
        GroupState foregroundGroupState = getForegroundGroupState(groupName);
        GroupState backgroundGroupState = getBackgroundGroupState(groupName);
        if (result == 0) {
            if (foregroundGroupState != null) {
                reportRequestResult(foregroundGroupState.affectedPermissions, 12);
            }
            if (backgroundGroupState != null) {
                reportRequestResult(backgroundGroupState.affectedPermissions, 12);
                return;
            }
            return;
        }
        if (result == 1) {
            if (foregroundGroupState != null) {
                reportRequestResult(foregroundGroupState.affectedPermissions, 12);
            }
            if (backgroundGroupState != null) {
                reportRequestResult(backgroundGroupState.affectedPermissions, 13);
                return;
            }
            return;
        }
        if (result == 2) {
            if (foregroundGroupState != null) {
                reportRequestResult(foregroundGroupState.affectedPermissions, 13);
            }
            if (backgroundGroupState != null) {
                reportRequestResult(backgroundGroupState.affectedPermissions, 13);
                return;
            }
            return;
        }
        if (result == 3) {
            if (foregroundGroupState != null) {
                reportRequestResult(foregroundGroupState.affectedPermissions, 14);
            }
            if (backgroundGroupState != null) {
                reportRequestResult(backgroundGroupState.affectedPermissions, 14);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAppPermissionFragment(GroupState groupState) {
        Intent intent = new Intent("android.intent.action.MANAGE_APP_PERMISSION").putExtra("android.intent.extra.PACKAGE_NAME", this.mAppPermissions.getPackageInfo().packageName).putExtra("android.intent.extra.PERMISSION_GROUP_NAME", groupState.mGroup.getName()).putExtra("android.intent.extra.USER", groupState.mGroup.getUser()).putExtra("com.android.permissioncontroller.extra.CALLER_NAME", GrantPermissionsActivity.class.getName()).putExtra(Constants.EXTRA_SESSION_ID, this.mRequestId).addFlags(32768);
        startActivityForResult(intent, 1);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Consumer<Intent> consumer;
        if (requestCode == 1 && (consumer = this.mActivityResultCallback) != null) {
            consumer.accept(data);
            this.mActivityResultCallback = null;
        }
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler.ResultListener
    public void onPermissionGrantResult(final String name, final int result) {
        GroupState foregroundGroupState = getForegroundGroupState(name);
        GroupState backgroundGroupState = getBackgroundGroupState(name);
        if (result == 0 || result == 1 || result == 3) {
            KeyguardManager kgm = (KeyguardManager) getSystemService(KeyguardManager.class);
            if (kgm.isDeviceLocked()) {
                kgm.requestDismissKeyguard(this, new KeyguardManager.KeyguardDismissCallback() { // from class: com.android.permissioncontroller.permission.ui.GrantPermissionsActivity.4
                    @Override // android.app.KeyguardManager.KeyguardDismissCallback
                    public void onDismissError() {
                        Log.e(GrantPermissionsActivity.LOG_TAG, "Cannot dismiss keyguard perm=" + name + " result=" + result);
                    }

                    @Override // android.app.KeyguardManager.KeyguardDismissCallback
                    public void onDismissCancelled() {
                    }

                    @Override // android.app.KeyguardManager.KeyguardDismissCallback
                    public void onDismissSucceeded() {
                        GrantPermissionsActivity.this.onPermissionGrantResult(name, result);
                    }
                });
                return;
            }
        }
        logGrantPermissionActivityButtons(name, result);
        if (result == -1) {
            if (foregroundGroupState != null) {
                reportRequestResult(foregroundGroupState.affectedPermissions, 11);
            }
            if (backgroundGroupState != null) {
                reportRequestResult(backgroundGroupState.affectedPermissions, 11);
            }
            setResultAndFinish();
            return;
        }
        if (result == 0) {
            if (foregroundGroupState != null) {
                onPermissionGrantResultSingleState(foregroundGroupState, true, false, false);
            }
            if (backgroundGroupState != null) {
                onPermissionGrantResultSingleState(backgroundGroupState, true, false, false);
            }
        } else if (result == 1) {
            if (foregroundGroupState != null) {
                onPermissionGrantResultSingleState(foregroundGroupState, true, false, false);
            }
            if (backgroundGroupState != null) {
                onPermissionGrantResultSingleState(backgroundGroupState, false, false, false);
            }
        } else if (result != 2) {
            if (result == 3) {
                if (foregroundGroupState != null) {
                    onPermissionGrantResultSingleState(foregroundGroupState, false, false, true);
                }
                if (backgroundGroupState != null) {
                    onPermissionGrantResultSingleState(backgroundGroupState, false, false, true);
                }
            } else if (result == 4) {
                if (foregroundGroupState != null) {
                    onPermissionGrantResultSingleState(foregroundGroupState, true, true, false);
                }
                if (backgroundGroupState != null) {
                    onPermissionGrantResultSingleState(backgroundGroupState, false, true, false);
                }
            }
        } else {
            if (foregroundGroupState != null) {
                onPermissionGrantResultSingleState(foregroundGroupState, false, false, false);
            }
            if (backgroundGroupState != null) {
                onPermissionGrantResultSingleState(backgroundGroupState, false, false, false);
            }
        }
        Log.d(LOG_TAG, "onPermissionGrantResult showNextPermissionGroupGrantRequest start");
        if (!showNextPermissionGroupGrantRequest()) {
            setResultAndFinish();
        }
        Log.d(LOG_TAG, "onPermissionGrantResult showNextPermissionGroupGrantRequest end");
    }

    private void onPermissionGrantResultSingleState(GroupState groupState, boolean granted, boolean isOneTime, boolean doNotAskAgain) {
        int i;
        if (groupState == null || groupState.mGroup == null || groupState.mState != 0) {
            return;
        }
        if (granted) {
            int permissionGrantRequestResult = 4;
            if (!isOneTime) {
                groupState.mGroup.setOneTime(false);
            } else {
                groupState.mGroup.setOneTime(true);
                permissionGrantRequestResult = 10;
            }
            groupState.mGroup.grantRuntimePermissions(true, doNotAskAgain, groupState.affectedPermissions);
            groupState.mState = 1;
            reportRequestResult(groupState.affectedPermissions, permissionGrantRequestResult);
            return;
        }
        groupState.mGroup.revokeRuntimePermissions(doNotAskAgain, groupState.affectedPermissions);
        groupState.mGroup.setOneTime(false);
        groupState.mState = 2;
        String[] strArr = groupState.affectedPermissions;
        if (doNotAskAgain) {
            i = 7;
        } else {
            i = 6;
        }
        reportRequestResult(strArr, i);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        this.mViewHandler.onBackPressed();
    }

    @Override // android.app.Activity
    public void finish() {
        setResultIfNeeded(0);
        AutoGrantPermissionsNotifier autoGrantPermissionsNotifier = this.mAutoGrantPermissionsNotifier;
        if (autoGrantPermissionsNotifier != null) {
            autoGrantPermissionsNotifier.notifyOfAutoGrantPermissions(true);
        }
        super.finish();
    }

    private PackageInfo getCallingPackageInfo() {
        try {
            return getPackageManager().getPackageInfo(this.mCallingPackage, 4096);
        } catch (PackageManager.NameNotFoundException e) {
            Log.i(LOG_TAG, "No package: " + this.mCallingPackage, e);
            return null;
        }
    }

    private void setResultIfNeeded(int resultCode) {
        if (!this.mResultSet) {
            this.mResultSet = true;
            logRequestedPermissionGroups();
            Intent result = new Intent("android.content.pm.action.REQUEST_PERMISSIONS");
            result.putExtra("android.content.pm.extra.REQUEST_PERMISSIONS_NAMES", this.mRequestedPermissions);
            PackageManager pm = getPackageManager();
            int numRequestedPermissions = this.mRequestedPermissions.length;
            int[] grantResults = new int[numRequestedPermissions];
            for (int i = 0; i < numRequestedPermissions; i++) {
                grantResults[i] = pm.checkPermission(this.mRequestedPermissions[i], this.mCallingPackage);
            }
            result.putExtra("android.content.pm.extra.REQUEST_PERMISSIONS_RESULTS", grantResults);
            setResult(resultCode, result);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResultAndFinish() {
        setResultIfNeeded(-1);
        finish();
    }

    private void logRequestedPermissionGroups() {
        if (this.mRequestGrantPermissionGroups.isEmpty()) {
            return;
        }
        int groupCount = this.mRequestGrantPermissionGroups.size();
        List<AppPermissionGroup> groups = new ArrayList<>(groupCount);
        for (GroupState groupState : this.mRequestGrantPermissionGroups.values()) {
            groups.add(groupState.mGroup);
        }
        SafetyNetLogger.logPermissionsRequested(this.mAppPermissions.getPackageInfo(), groups);
    }

    private ArrayList<String> computeAffectedPermissions(String permission) {
        int requestingAppTargetSDK = this.mAppPermissions.getPackageInfo().applicationInfo.targetSdkVersion;
        ArrayList<String> extendedBySplitPerms = new ArrayList<>();
        extendedBySplitPerms.add(permission);
        List<PermissionManager.SplitPermissionInfo> splitPerms = ((PermissionManager) getSystemService(PermissionManager.class)).getSplitPermissions();
        int numSplitPerms = splitPerms.size();
        for (int i = 0; i < numSplitPerms; i++) {
            PermissionManager.SplitPermissionInfo splitPerm = splitPerms.get(i);
            if (requestingAppTargetSDK < splitPerm.getTargetSdk() && permission.equals(splitPerm.getSplitPermission())) {
                extendedBySplitPerms.addAll(splitPerm.getNewPermissions());
            }
        }
        if (requestingAppTargetSDK <= 25) {
            ArrayList<String> extendedBySplitPermsAndGroup = new ArrayList<>();
            int numExtendedBySplitPerms = extendedBySplitPerms.size();
            for (int splitPermNum = 0; splitPermNum < numExtendedBySplitPerms; splitPermNum++) {
                AppPermissionGroup group = this.mAppPermissions.getGroupForPermission(extendedBySplitPerms.get(splitPermNum));
                if (group != null) {
                    ArrayList<Permission> permissionsInGroup = group.getPermissions();
                    int numPermissionsInGroup = permissionsInGroup.size();
                    for (int permNum = 0; permNum < numPermissionsInGroup; permNum++) {
                        extendedBySplitPermsAndGroup.add(permissionsInGroup.get(permNum).getName());
                    }
                }
            }
            return extendedBySplitPermsAndGroup;
        }
        return extendedBySplitPerms;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logGrantPermissionActivityButtons(String permissionGroupName, int grantResult) {
        int clickedButton = 0;
        int presentedButtons = getButtonState();
        if (grantResult == -2) {
            clickedButton = 1024;
        } else if (grantResult == 0) {
            clickedButton = this.mButtonVisibilities[0] ? 1 : 2;
        } else if (grantResult == 1) {
            clickedButton = 4;
        } else if (grantResult == 2) {
            boolean[] zArr = this.mButtonVisibilities;
            if (zArr[6]) {
                clickedButton = 64;
            } else if (zArr[8]) {
                clickedButton = 256;
            } else if (zArr[3]) {
                clickedButton = 8;
            }
        } else if (grantResult == 3) {
            boolean[] zArr2 = this.mButtonVisibilities;
            if (zArr2[7]) {
                clickedButton = 128;
            } else if (zArr2[9]) {
                clickedButton = 512;
            } else if (zArr2[4]) {
                clickedButton = 16;
            }
        } else if (grantResult == 4) {
            clickedButton = 32;
        }
        PermissionControllerStatsLog.write(PermissionControllerStatsLog.GRANT_PERMISSIONS_ACTIVITY_BUTTON_ACTIONS, permissionGroupName, this.mCallingUid, this.mCallingPackage, presentedButtons, clickedButton, this.mRequestId);
        Log.v(LOG_TAG, "Logged buttons presented and clicked permissionGroupName=" + permissionGroupName + " uid=" + this.mCallingUid + " package=" + this.mCallingPackage + " presentedButtons=" + presentedButtons + " clickedButton=" + clickedButton + " sessionId=" + this.mRequestId);
    }

    private int getButtonState() {
        if (this.mButtonVisibilities == null) {
            return 0;
        }
        int buttonState = 0;
        for (int i = 10; i >= 0; i--) {
            buttonState *= 2;
            if (this.mButtonVisibilities[i]) {
                buttonState++;
            }
        }
        return buttonState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class GroupState {
        static final int STATE_ALLOWED = 1;
        static final int STATE_DENIED = 2;
        static final int STATE_SKIPPED = 3;
        static final int STATE_UNKNOWN = 0;
        String[] affectedPermissions;
        final AppPermissionGroup mGroup;
        int mState = 0;

        GroupState(AppPermissionGroup group) {
            this.mGroup = group;
        }
    }

    private class PermissionChangeListener implements PackageManager.OnPermissionsChangedListener {
        final int mCallingPackageUid;

        PermissionChangeListener() throws PackageManager.NameNotFoundException {
            this.mCallingPackageUid = GrantPermissionsActivity.this.getPackageManager().getPackageUid(GrantPermissionsActivity.this.mCallingPackage, 0);
        }

        public void onPermissionsChanged(int uid) {
            if (uid == this.mCallingPackageUid) {
                GrantPermissionsActivity.this.updateIfPermissionsWereGranted();
            }
        }
    }

    private AutoGrantPermissionsNotifier getAutoGrantNotifier() {
        if (this.mAutoGrantPermissionsNotifier == null) {
            this.mAutoGrantPermissionsNotifier = new AutoGrantPermissionsNotifier(this, this.mCallingPackageInfo);
        }
        return this.mAutoGrantPermissionsNotifier;
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        GrantPermissionsViewHandler grantPermissionsViewHandler = this.mViewHandler;
        if (grantPermissionsViewHandler != null) {
            grantPermissionsViewHandler.onConfigurationChanged(newConfig);
        }
    }

    private class ProcessObserver extends IProcessObserver.Stub {
        private ProcessObserver() {
        }

        public void onForegroundActivitiesChanged(int pid, int uid, boolean foregroundActivities) {
        }

        public void onForegroundServicesChanged(int pid, int uid, int fgServiceTypes) {
        }

        public void onProcessDied(int pid, int uid) {
            Log.w(GrantPermissionsActivity.LOG_TAG, "onProcessDied called with pid=" + pid + " and uid=" + uid + " CallingPackaged : " + GrantPermissionsActivity.this.mCallingPackage + " and died package : " + GrantPermissionsActivity.this.mCallingPackagePid);
            if (pid == GrantPermissionsActivity.this.mCallingPackagePid) {
                GrantPermissionsActivity.this.setResultAndFinish();
            }
        }
    }

    public int getPidByPackageName(String packageName) {
        List<ActivityManager.RunningAppProcessInfo> processes = this.mActivityManager.getRunningAppProcesses();
        if (processes != null) {
            for (ActivityManager.RunningAppProcessInfo processInfo : processes) {
                if (processInfo.pkgList.length > 0 && processInfo.processName.equals(packageName)) {
                    return processInfo.pid;
                }
            }
            return -1;
        }
        return -1;
    }
}
