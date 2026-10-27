package com.amazon.mp3.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import com.amazon.mp3.activity.GrantPermissionActivity;
import com.amazon.mp3.automotiveOS.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
@RequiresApi(api = 31)
public class GrantBluetoothPermissionActivity extends GrantPermissionActivity {
    public static final String[] BLUETOOTH_PERMISSIONS = {"android.permission.BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_ADVERTISE", "android.permission.BLUETOOTH_SCAN"};

    @Override // com.amazon.mp3.activity.GrantPermissionActivity
    protected void requestPermissions(final Bundle savedInstanceState, final String[] permissions) {
        if (savedInstanceState == null && hasReachedMaximumTimesShown()) {
            finish();
        }
        if (isFirstTimeRequestingPermission("first_time_bluetooth_dialog")) {
            markPermissionRequested("first_time_bluetooth_dialog");
            if (savedInstanceState == null) {
                ActivityCompat.requestPermissions(this, permissions, 0);
                return;
            }
            return;
        }
        if (savedInstanceState == null) {
            incrementSettingsBluetoothViewedCounter();
        }
        if (timesDialogShown() > 0) {
            showPermissionsDialog(true, permissions, R.string.dmusic_alert_bluetooth_permission_text, R.string.dmusic_alert_bluetooth_permission_action, "android.permission.BLUETOOTH_CONNECT");
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        checkIfPermissionGrantedAndFinish("android.permission.BLUETOOTH_CONNECT");
    }

    public static void requestIfNotGranted(final Context context, final String[] permissions, final GrantPermissionActivity.PermissionResultReceiver resultReceiver) {
        ArrayList<String> permissionsToRequest = GrantPermissionActivity.getPermissionsToRequest(context, permissions);
        if (!permissionsToRequest.isEmpty()) {
            GrantPermissionActivity.startGrantPermissionsActivity(context, GrantBluetoothPermissionActivity.class, permissionsToRequest, resultReceiver);
        } else if (resultReceiver != null) {
            resultReceiver.send(1, GrantPermissionActivity.ALL_GRANTED_SUCCESS_BUNDLE);
        }
    }

    private void incrementSettingsBluetoothViewedCounter() {
        SharedPreferences sharedPreferences = getSharedPreferences(GrantPermissionActivity.SHARED_PREF_NAME, 0);
        int i = sharedPreferences.getInt("settings_bluetooth_dialog_counter", 0);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putInt("settings_bluetooth_dialog_counter", i + 1);
        editorEdit.apply();
    }

    private int timesDialogShown() {
        return getSharedPreferences(GrantPermissionActivity.SHARED_PREF_NAME, 0).getInt("settings_bluetooth_dialog_counter", 0);
    }

    private boolean hasReachedMaximumTimesShown() {
        getSharedPreferences(GrantPermissionActivity.SHARED_PREF_NAME, 0);
        return timesDialogShown() >= 2;
    }
}
