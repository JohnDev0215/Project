package com.android.permissioncontroller.role.ui;

import android.app.role.RoleManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import com.android.car.ui.utils.RotaryConstants;
import com.android.permissioncontroller.permission.utils.CollectionUtils;
import com.android.permissioncontroller.role.model.Role;
import com.android.permissioncontroller.role.model.Roles;
import com.android.permissioncontroller.role.model.UserDeniedManager;
import com.android.permissioncontroller.role.utils.PackageUtils;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes8.dex */
public class RequestRoleActivity extends FragmentActivity {
    private static final String LOG_TAG = RequestRoleActivity.class.getSimpleName();
    private String mPackageName;
    private String mRoleName;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addSystemFlags(524288);
        this.mRoleName = getIntent().getStringExtra("android.intent.extra.ROLE_NAME");
        this.mPackageName = getCallingPackage();
        if (!handleChangeDefaultDialerDialogCompatibility()) {
            reportRequestResult(1);
            finish();
            return;
        }
        if (!handleSmsDefaultDialogCompatibility()) {
            reportRequestResult(1);
            finish();
            return;
        }
        if (TextUtils.isEmpty(this.mRoleName)) {
            Log.w(LOG_TAG, "Role name cannot be null or empty: " + this.mRoleName);
            reportRequestResult(1);
            finish();
            return;
        }
        if (TextUtils.isEmpty(this.mPackageName)) {
            Log.w(LOG_TAG, "Package name cannot be null or empty: " + this.mPackageName);
            reportRequestResult(1);
            finish();
            return;
        }
        Role role = Roles.get(this).get(this.mRoleName);
        if (role == null) {
            Log.w(LOG_TAG, "Unknown role: " + this.mRoleName);
            reportRequestResult(1);
            finish();
            return;
        }
        if (!role.isAvailable(this)) {
            Log.e(LOG_TAG, "Role is unavailable: " + this.mRoleName);
            reportRequestResult(1);
            finish();
            return;
        }
        if (!role.isVisible(this)) {
            Log.e(LOG_TAG, "Role is invisible: " + this.mRoleName);
            reportRequestResult(1);
            finish();
            return;
        }
        if (!role.isRequestable()) {
            Log.e(LOG_TAG, "Role is not requestable: " + this.mRoleName);
            reportRequestResult(1);
            finish();
            return;
        }
        if (!role.isExclusive()) {
            Log.e(LOG_TAG, "Role is not exclusive: " + this.mRoleName);
            reportRequestResult(1);
            finish();
            return;
        }
        if (PackageUtils.getApplicationInfo(this.mPackageName, this) == null) {
            Log.w(LOG_TAG, "Unknown application: " + this.mPackageName);
            reportRequestResult(1);
            finish();
            return;
        }
        RoleManager roleManager = (RoleManager) getSystemService(RoleManager.class);
        List<String> currentPackageNames = roleManager.getRoleHolders(this.mRoleName);
        if (currentPackageNames.contains(this.mPackageName)) {
            Log.i(LOG_TAG, "Application is already a role holder, role: " + this.mRoleName + ", package: " + this.mPackageName);
            reportRequestResult(2);
            setResult(-1);
            finish();
            return;
        }
        if (!role.isPackageQualified(this.mPackageName, this)) {
            Log.w(LOG_TAG, "Application doesn't qualify for role, role: " + this.mRoleName + ", package: " + this.mPackageName);
            reportRequestResult(3);
            finish();
            return;
        }
        if (UserDeniedManager.getInstance(this).isDeniedAlways(this.mRoleName, this.mPackageName)) {
            Log.w(LOG_TAG, "Application is denied always for role, role: " + this.mRoleName + ", package: " + this.mPackageName);
            reportRequestResult(4);
            finish();
            return;
        }
        if (savedInstanceState == null) {
            RequestRoleFragment fragment = RequestRoleFragment.newInstance(this.mRoleName, this.mPackageName);
            getSupportFragmentManager().beginTransaction().add(fragment, (String) null).commit();
        }
    }

    private boolean handleChangeDefaultDialerDialogCompatibility() {
        Intent intent = getIntent();
        if (!Objects.equals(intent.getAction(), "android.telecom.action.CHANGE_DEFAULT_DIALER")) {
            return true;
        }
        Log.w(LOG_TAG, "TelecomManager.ACTION_CHANGE_DEFAULT_DIALER is deprecated; please use RoleManager.createRequestRoleIntent() and Activity.startActivityForResult() instead");
        this.mRoleName = "android.app.role.DIALER";
        this.mPackageName = null;
        String callingPackageName = intent.getStringExtra("android.intent.extra.CALLING_PACKAGE");
        String extraPackageName = intent.getStringExtra("android.telecom.extra.CHANGE_DEFAULT_DIALER_PACKAGE_NAME");
        if (Objects.equals(extraPackageName, callingPackageName)) {
            this.mPackageName = extraPackageName;
            return true;
        }
        RoleManager roleManager = (RoleManager) getSystemService(RoleManager.class);
        String holderPackageName = (String) CollectionUtils.firstOrNull(roleManager.getRoleHolders("android.app.role.DIALER"));
        if (Objects.equals(callingPackageName, holderPackageName)) {
            this.mPackageName = extraPackageName;
            return true;
        }
        return false;
    }

    private boolean handleSmsDefaultDialogCompatibility() {
        Intent intent = getIntent();
        if (!Objects.equals(intent.getAction(), "android.provider.Telephony.ACTION_CHANGE_DEFAULT")) {
            return true;
        }
        Log.w(LOG_TAG, "Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT is deprecated; please use RoleManager.createRequestRoleIntent() and Activity.startActivityForResult() instead");
        this.mRoleName = "android.app.role.SMS";
        this.mPackageName = null;
        String callingPackageName = intent.getStringExtra("android.intent.extra.CALLING_PACKAGE");
        String extraPackageName = intent.getStringExtra("package");
        if (extraPackageName == null) {
            Intent defaultAppActivityIntent = DefaultAppActivity.createIntent("android.app.role.SMS", Process.myUserHandle(), this).addFlags(RotaryConstants.ACTION_NUDGE_TO_ANOTHER_FOCUS_AREA);
            startActivity(defaultAppActivityIntent);
            return false;
        }
        if (Objects.equals(extraPackageName, callingPackageName)) {
            this.mPackageName = extraPackageName;
            return true;
        }
        RoleManager roleManager = (RoleManager) getSystemService(RoleManager.class);
        String holderPackageName = (String) CollectionUtils.firstOrNull(roleManager.getRoleHolders("android.app.role.SMS"));
        if (!Objects.equals(callingPackageName, holderPackageName)) {
            return false;
        }
        this.mPackageName = extraPackageName;
        return true;
    }

    private void reportRequestResult(int result) {
        RequestRoleFragment.reportRequestResult(getApplicationUid(this.mPackageName, this), this.mPackageName, this.mRoleName, -1, -1, null, -1, null, result);
    }

    private static int getApplicationUid(String packageName, Context context) {
        ApplicationInfo applicationInfo;
        if (packageName == null || (applicationInfo = PackageUtils.getApplicationInfo(packageName, context)) == null) {
            return -1;
        }
        return applicationInfo.uid;
    }
}
