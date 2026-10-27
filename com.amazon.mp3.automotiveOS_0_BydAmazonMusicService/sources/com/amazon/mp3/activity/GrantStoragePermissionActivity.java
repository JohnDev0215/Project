package com.amazon.mp3.activity;

import android.content.Context;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import com.amazon.mp3.activity.GrantPermissionActivity;
import com.amazon.mp3.automotiveOS.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class GrantStoragePermissionActivity extends GrantPermissionActivity {
    @Override // com.amazon.mp3.activity.GrantPermissionActivity
    protected void requestPermissions(final Bundle savedInstanceState, final String[] permissions) {
        boolean z = getSharedPreferences(GrantPermissionActivity.SHARED_PREF_NAME, 0).getBoolean("first_time_dialog", true);
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            showPermissionsDialog(false, permissions, R.string.dmusic_alert_storage_permission_text, R.string.dmusic_alert_storage_permission_action, "android.permission.WRITE_EXTERNAL_STORAGE");
            return;
        }
        if (z) {
            markPermissionRequested("first_time_dialog");
            if (savedInstanceState == null) {
                ActivityCompat.requestPermissions(this, permissions, 0);
                return;
            }
            return;
        }
        showPermissionsDialog(true, permissions, R.string.dmusic_alert_storage_permission_text, R.string.dmusic_alert_storage_permission_action, "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        checkIfPermissionGrantedAndFinish("android.permission.WRITE_EXTERNAL_STORAGE");
    }

    public static void requestIfNotGranted(final Context context, final String permission, final GrantPermissionActivity.PermissionResultReceiver resultReceiver) {
        requestIfNotGranted(context, new String[]{permission}, resultReceiver);
    }

    public static void requestIfNotGranted(final Context context, final String[] permissions, final GrantPermissionActivity.PermissionResultReceiver resultReceiver) {
        ArrayList<String> permissionsToRequest = GrantPermissionActivity.getPermissionsToRequest(context, permissions);
        if (!permissionsToRequest.isEmpty()) {
            GrantPermissionActivity.startGrantPermissionsActivity(context, GrantStoragePermissionActivity.class, permissionsToRequest, resultReceiver);
        } else if (resultReceiver != null) {
            resultReceiver.send(1, GrantPermissionActivity.ALL_GRANTED_SUCCESS_BUNDLE);
        }
    }
}
