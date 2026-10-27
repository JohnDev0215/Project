package com.android.permissioncontroller.permission.ui;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import com.android.permissioncontroller.Constants;
import com.android.permissioncontroller.DeviceUtils;
import com.android.permissioncontroller.PermissionControllerStatsLog;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.ui.auto.AutoAllAppPermissionsFragment;
import com.android.permissioncontroller.permission.ui.auto.AutoAppPermissionsFragment;
import com.android.permissioncontroller.permission.ui.auto.AutoManageStandardPermissionsFragment;
import com.android.permissioncontroller.permission.ui.auto.AutoPermissionAppsFragment;
import com.android.permissioncontroller.permission.ui.handheld.AppPermissionFragment;
import com.android.permissioncontroller.permission.ui.handheld.AppPermissionGroupsFragment;
import com.android.permissioncontroller.permission.ui.handheld.AutoRevokeFragment;
import com.android.permissioncontroller.permission.ui.legacy.AppPermissionActivity;
import com.android.permissioncontroller.permission.ui.television.AppPermissionsFragment;
import com.android.permissioncontroller.permission.ui.television.ManagePermissionsFragment;
import com.android.permissioncontroller.permission.ui.television.PermissionAppsFragment;
import com.android.permissioncontroller.permission.ui.wear.AppPermissionsFragmentWear;
import com.android.permissioncontroller.permission.utils.KotlinUtils;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.Random;

/* JADX INFO: loaded from: classes8.dex */
public final class ManagePermissionsActivity extends FragmentActivity {
    public static final String EXTRA_ALL_PERMISSIONS = "com.android.permissioncontroller.extra.ALL_PERMISSIONS";
    public static final String EXTRA_CALLER_NAME = "com.android.permissioncontroller.extra.CALLER_NAME";
    public static final String EXTRA_RESULT_PERMISSION_INTERACTED = "com.android.permissioncontroller.extra.RESULT_PERMISSION_INTERACTED";
    public static final String EXTRA_RESULT_PERMISSION_RESULT = "com.android.permissioncontroller.extra.PERMISSION_RESULT";
    private static final String LOG_TAG = ManagePermissionsActivity.class.getSimpleName();
    private static final int PROXY_ACTIVITY_REQUEST_CODE = 5;

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v18 */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        Fragment fragmentNewInstance;
        UserHandle userHandleOf;
        boolean z;
        if (DeviceUtils.isAuto(this)) {
            setTheme(R.style.CarSettings);
        }
        super.onCreate(bundle);
        if ((DeviceUtils.isTelevision(this) || DeviceUtils.isAuto(this) || DeviceUtils.isWear(this)) && bundle != null) {
            return;
        }
        String action = getIntent().getAction();
        getWindow().addSystemFlags(524288);
        long longExtra = getIntent().getLongExtra(Constants.EXTRA_SESSION_ID, 0L);
        while (longExtra == 0) {
            longExtra = new Random().nextLong();
        }
        byte b = -1;
        switch (action.hashCode()) {
            case -1201579942:
                if (action.equals(Constants.ACTION_MANAGE_AUTO_REVOKE)) {
                    b = 4;
                }
                break;
            case -1168603379:
                if (action.equals("android.intent.action.MANAGE_PERMISSION_APPS")) {
                    b = 3;
                }
                break;
            case 1439844674:
                if (action.equals("android.intent.action.MANAGE_APP_PERMISSION")) {
                    b = 1;
                }
                break;
            case 1685512017:
                if (action.equals("android.intent.action.MANAGE_APP_PERMISSIONS")) {
                    b = 2;
                }
                break;
            case 1861372431:
                if (action.equals("android.intent.action.MANAGE_PERMISSIONS")) {
                    b = 0;
                }
                break;
        }
        if (b != 0) {
            if (b == 1) {
                if (DeviceUtils.isAuto(this) || DeviceUtils.isTelevision(this) || DeviceUtils.isWear(this)) {
                    Intent intent = new Intent(this, (Class<?>) AppPermissionActivity.class);
                    intent.putExtras(getIntent().getExtras());
                    startActivityForResult(intent, 5);
                    return;
                }
                setNavGraph(AppPermissionFragment.createArgs(getIntent().getStringExtra("android.intent.extra.PACKAGE_NAME"), getIntent().getStringExtra("android.intent.extra.PERMISSION_NAME"), getIntent().getStringExtra("android.intent.extra.PERMISSION_GROUP_NAME"), (UserHandle) getIntent().getParcelableExtra("android.intent.extra.USER"), getIntent().getStringExtra("com.android.permissioncontroller.extra.CALLER_NAME"), longExtra, null), R.id.app_permission);
                return;
            }
            if (b == 2) {
                String stringExtra = getIntent().getStringExtra("android.intent.extra.PACKAGE_NAME");
                if (stringExtra == null) {
                    Log.i(LOG_TAG, "Missing mandatory argument EXTRA_PACKAGE_NAME");
                    finish();
                    return;
                }
                UserHandle userHandle = (UserHandle) getIntent().getParcelableExtra("android.intent.extra.USER");
                if (userHandle != null) {
                    userHandleOf = userHandle;
                } else {
                    userHandleOf = UserHandle.of(UserHandle.myUserId());
                }
                try {
                    int i = getPackageManager().getApplicationInfoAsUser(stringExtra, 0, userHandleOf).uid;
                    long longExtra2 = getIntent().getLongExtra("android.intent.action.AUTO_REVOKE_PERMISSIONS", 0L);
                    z = (longExtra2 > 0L ? 1 : (longExtra2 == 0L ? 0 : -1));
                    try {
                        if (z != 0) {
                            longExtra = longExtra2;
                            Log.i(LOG_TAG, "sessionId: " + longExtra + " Reaching AppPermissionGroupsFragment for auto revoke. packageName: " + stringExtra + " uid " + i);
                            z = 0;
                            PermissionControllerStatsLog.write(PermissionControllerStatsLog.APP_PERMISSION_GROUPS_FRAGMENT_AUTO_REVOKE_ACTION, longExtra, i, stringExtra, 1);
                        } else {
                            boolean z2 = false;
                            z = z2;
                            if (KotlinUtils.INSTANCE.isROrAutoRevokeEnabled(getApplication(), stringExtra, userHandleOf)) {
                                Log.i(LOG_TAG, "sessionId: " + longExtra + " Reaching AppPermissionGroupsFragment from intent. packageName " + stringExtra + " uid " + i);
                                PermissionControllerStatsLog.write(PermissionControllerStatsLog.APP_PERMISSION_GROUPS_FRAGMENT_AUTO_REVOKE_ACTION, longExtra, i, stringExtra, 2);
                                z = z2;
                            }
                        }
                    } catch (PackageManager.NameNotFoundException e) {
                    }
                } catch (PackageManager.NameNotFoundException e2) {
                    z = 0;
                }
                boolean booleanExtra = getIntent().getBooleanExtra(EXTRA_ALL_PERMISSIONS, z);
                if (DeviceUtils.isAuto(this)) {
                    if (booleanExtra) {
                        fragmentNewInstance = AutoAllAppPermissionsFragment.newInstance(stringExtra, userHandleOf);
                    } else {
                        fragmentNewInstance = AutoAppPermissionsFragment.newInstance(stringExtra, userHandleOf);
                    }
                } else if (DeviceUtils.isWear(this)) {
                    fragmentNewInstance = AppPermissionsFragmentWear.newInstance(stringExtra);
                } else {
                    if (!DeviceUtils.isTelevision(this)) {
                        setNavGraph(AppPermissionGroupsFragment.createArgs(stringExtra, userHandleOf, longExtra, true), R.id.app_permission_groups);
                        return;
                    }
                    fragmentNewInstance = AppPermissionsFragment.newInstance(stringExtra, userHandleOf);
                }
            } else if (b == 3) {
                String stringExtra2 = getIntent().getStringExtra("android.intent.extra.PERMISSION_NAME");
                String stringExtra3 = getIntent().getStringExtra("android.intent.extra.PERMISSION_GROUP_NAME");
                if (stringExtra3 == null) {
                    try {
                        stringExtra3 = Utils.getGroupOfPermission(getPackageManager().getPermissionInfo(stringExtra2, 0));
                    } catch (PackageManager.NameNotFoundException e3) {
                        Log.i(LOG_TAG, "Permission " + stringExtra2 + " does not exist");
                    }
                }
                if (stringExtra2 == null && stringExtra3 == null) {
                    Log.i(LOG_TAG, "Missing mandatory argument EXTRA_PERMISSION_NAME orEXTRA_PERMISSION_GROUP_NAME");
                    finish();
                    return;
                } else if (DeviceUtils.isAuto(this)) {
                    fragmentNewInstance = AutoPermissionAppsFragment.newInstance(stringExtra2);
                } else if (DeviceUtils.isTelevision(this)) {
                    fragmentNewInstance = PermissionAppsFragment.newInstance(stringExtra2);
                } else {
                    Bundle bundleCreateArgs = com.android.permissioncontroller.permission.ui.handheld.PermissionAppsFragment.createArgs(stringExtra3, longExtra);
                    bundleCreateArgs.putString("android.intent.extra.PERMISSION_NAME", stringExtra2);
                    setNavGraph(bundleCreateArgs, R.id.permission_apps);
                    return;
                }
            } else if (b == 4) {
                Log.i(LOG_TAG, "sessionId " + longExtra + " starting auto revoke fragment from notification");
                PermissionControllerStatsLog.write(PermissionControllerStatsLog.AUTO_REVOKE_NOTIFICATION_CLICKED, longExtra);
                if (!DeviceUtils.isWear(this) && !DeviceUtils.isAuto(this) && !DeviceUtils.isTelevision(this)) {
                    setNavGraph(AutoRevokeFragment.createArgs(longExtra), R.id.auto_revoke);
                    return;
                } else {
                    fragmentNewInstance = AutoRevokeFragment.newInstance();
                    fragmentNewInstance.setArguments(AutoRevokeFragment.createArgs(longExtra));
                }
            } else {
                Log.w(LOG_TAG, "Unrecognized action " + action);
                finish();
                return;
            }
        } else if (DeviceUtils.isAuto(this)) {
            fragmentNewInstance = AutoManageStandardPermissionsFragment.newInstance();
        } else if (DeviceUtils.isTelevision(this)) {
            fragmentNewInstance = ManagePermissionsFragment.newInstance();
        } else {
            Bundle bundle2 = new Bundle();
            bundle2.putLong(Constants.EXTRA_SESSION_ID, longExtra);
            setContentView(R.layout.nav_host_fragment);
            Navigation.findNavController(this, R.id.nav_host_fragment).setGraph(R.navigation.nav_graph, bundle2);
            return;
        }
        if (0 != 0) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, null).commit();
        } else if (fragmentNewInstance != null) {
            getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fragmentNewInstance).commit();
        }
    }

    private void setNavGraph(Bundle args, int startDestination) {
        setContentView(R.layout.nav_host_fragment);
        NavHostFragment navHost = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavInflater inflater = navHost.getNavController().getNavInflater();
        NavGraph graph = inflater.inflate(R.navigation.nav_graph);
        graph.setStartDestination(startDestination);
        navHost.getNavController().setGraph(graph, args);
    }

    @Override // android.app.Activity
    public ActionBar getActionBar() {
        ActionBar ab = super.getActionBar();
        if (ab != null) {
            ab.setHomeActionContentDescription(R.string.back);
        }
        return ab;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        if (DeviceUtils.isAuto(this)) {
            if (item.getItemId() == 16908332) {
                onBackPressed();
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5) {
            setResult(resultCode, data);
            finish();
        }
    }
}
