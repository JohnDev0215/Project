package com.android.permissioncontroller.permission.ui;

import android.R;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.activity.result.ActivityResultCaller;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.android.permissioncontroller.DeviceUtils;
import com.android.permissioncontroller.permission.ui.ConfirmActionDialogFragment;
import com.android.permissioncontroller.permission.ui.handheld.ReviewPermissionsFragment;
import com.android.permissioncontroller.permission.ui.wear.ReviewPermissionsWearFragment;

/* JADX INFO: loaded from: classes8.dex */
public final class ReviewPermissionsActivity extends FragmentActivity implements ConfirmActionDialogFragment.OnActionConfirmedListener {
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PackageInfo packageInfo = getTargetPackageInfo();
        if (packageInfo == null) {
            finish();
            return;
        }
        if (DeviceUtils.isWear(this)) {
            Fragment fragment = ReviewPermissionsWearFragment.newInstance(packageInfo);
            getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
        } else {
            setContentView(com.android.permissioncontroller.R.layout.review_permissions);
            if (getSupportFragmentManager().findFragmentById(com.android.permissioncontroller.R.id.preferences_frame) == null) {
                getSupportFragmentManager().beginTransaction().add(com.android.permissioncontroller.R.id.preferences_frame, ReviewPermissionsFragment.newInstance(packageInfo)).commit();
            }
        }
    }

    @Override // com.android.permissioncontroller.permission.ui.ConfirmActionDialogFragment.OnActionConfirmedListener
    public void onActionConfirmed(String action) {
        ActivityResultCaller activityResultCallerFindFragmentById = getSupportFragmentManager().findFragmentById(com.android.permissioncontroller.R.id.preferences_frame);
        if (activityResultCallerFindFragmentById instanceof ConfirmActionDialogFragment.OnActionConfirmedListener) {
            ((ConfirmActionDialogFragment.OnActionConfirmedListener) activityResultCallerFindFragmentById).onActionConfirmed(action);
        }
    }

    private PackageInfo getTargetPackageInfo() {
        String packageName = getIntent().getStringExtra("android.intent.extra.PACKAGE_NAME");
        if (TextUtils.isEmpty(packageName)) {
            return null;
        }
        try {
            return getPackageManager().getPackageInfo(packageName, 4096);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }
}
