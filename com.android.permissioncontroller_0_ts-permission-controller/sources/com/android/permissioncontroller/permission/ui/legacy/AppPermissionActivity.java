package com.android.permissioncontroller.permission.ui.legacy;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.android.permissioncontroller.DeviceUtils;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.ui.LocationProviderInterceptDialog;
import com.android.permissioncontroller.permission.ui.auto.AutoAppPermissionFragment;
import com.android.permissioncontroller.permission.ui.television.AppPermissionFragment;
import com.android.permissioncontroller.permission.utils.LocationUtils;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
@Deprecated
public final class AppPermissionActivity extends FragmentActivity {
    public static final String EXTRA_CALLER_NAME = "com.android.permissioncontroller.extra.CALLER_NAME";
    public static final String EXTRA_RESULT_PERMISSION_INTERACTED = "com.android.permissioncontroller.extra.RESULT_PERMISSION_INTERACTED";
    public static final String EXTRA_RESULT_PERMISSION_RESULT = "com.android.permissioncontroller.extra.PERMISSION_RESULT";
    private static final String LOG_TAG = AppPermissionActivity.class.getSimpleName();

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        String groupName;
        if (!DeviceUtils.isAuto(this) && !DeviceUtils.isWear(this) && !DeviceUtils.isTelevision(this)) {
            throw new IllegalStateException("Do not use AppPermissionActivity for handheld ui. Create intent with ACTION_MANAGE_APP_PERMISSION instead.");
        }
        if (DeviceUtils.isAuto(this)) {
            setTheme(R.style.CarSettings);
        }
        super.onCreate(savedInstanceState);
        getWindow().addSystemFlags(524288);
        String packageName = getIntent().getStringExtra("android.intent.extra.PACKAGE_NAME");
        if (packageName == null) {
            Log.e(LOG_TAG, "Missing mandatory argument EXTRA_PACKAGE_NAME");
            finish();
            return;
        }
        String permissionName = getIntent().getStringExtra("android.intent.extra.PERMISSION_NAME");
        String groupName2 = getIntent().getStringExtra("android.intent.extra.PERMISSION_GROUP_NAME");
        if (permissionName == null && groupName2 == null) {
            Log.e(LOG_TAG, "Missing argument EXTRA_PERMISSION_NAME or EXTRA_PERMISSION_GROUP_NAME, at least one must be present.");
            finish();
            return;
        }
        if (groupName2 != null) {
            groupName = groupName2;
        } else {
            String groupName3 = Utils.getGroupOfPlatformPermission(permissionName);
            try {
                PermissionInfo permission = getPackageManager().getPermissionInfo(permissionName, 0);
                if (!permission.packageName.equals(Utils.OS_PKG)) {
                    List<PermissionGroupInfo> groupInfos = getPackageManager().getAllPermissionGroups(0);
                    for (PermissionGroupInfo groupInfo : groupInfos) {
                        if (groupInfo.name.equals(permission.group)) {
                            groupName3 = permission.group;
                        }
                    }
                }
                groupName = groupName3;
            } catch (PackageManager.NameNotFoundException e) {
                groupName = null;
            }
        }
        UserHandle userHandle = (UserHandle) getIntent().getParcelableExtra("android.intent.extra.USER");
        if (userHandle == null) {
            Log.e(LOG_TAG, "Missing mandatory argument EXTRA_USER");
            finish();
            return;
        }
        if (LocationUtils.isLocationGroupAndProvider(this, groupName, packageName)) {
            Intent intent = new Intent(this, (Class<?>) LocationProviderInterceptDialog.class);
            intent.putExtra("android.intent.extra.PACKAGE_NAME", packageName);
            startActivityAsUser(intent, userHandle);
            finish();
            return;
        }
        if (LocationUtils.isLocationGroupAndControllerExtraPackage(this, groupName, packageName)) {
            LocationUtils.startLocationControllerExtraPackageSettings(this, userHandle);
            finish();
            return;
        }
        if (DeviceUtils.isAuto(this)) {
            getSupportFragmentManager().beginTransaction().replace(android.R.id.content, AutoAppPermissionFragment.newInstance(packageName, permissionName, groupName, userHandle)).commit();
        } else if (DeviceUtils.isTelevision(this)) {
            Fragment androidXFragment = new AppPermissionFragment();
            androidXFragment.setArguments(AppPermissionFragment.createArgs(packageName, permissionName, groupName, userHandle, null, 0L, null));
            getSupportFragmentManager().beginTransaction().replace(android.R.id.content, androidXFragment).commit();
        } else {
            startActivity(new Intent(getIntent()).setAction("android.intent.action.MANAGE_APP_PERMISSION"));
            finish();
        }
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
}
