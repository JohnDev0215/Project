package com.android.inputmethod.latin.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;

/* JADX INFO: loaded from: classes2.dex */
public final class PermissionsActivity extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback {
    public static final String EXTRA_PERMISSION_REQUESTED_PERMISSIONS = "requested_permissions";
    public static final String EXTRA_PERMISSION_REQUEST_CODE = "request_code";
    private static final int INVALID_REQUEST_CODE = -1;
    private int mPendingRequestCode = -1;

    public static void run(Context context, int requestCode, String... permissionStrings) {
        Intent intent = new Intent(context.getApplicationContext(), (Class<?>) PermissionsActivity.class);
        intent.putExtra(EXTRA_PERMISSION_REQUESTED_PERMISSIONS, permissionStrings);
        intent.putExtra(EXTRA_PERMISSION_REQUEST_CODE, requestCode);
        intent.addFlags(276824064);
        context.startActivity(intent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mPendingRequestCode = savedInstanceState != null ? savedInstanceState.getInt(EXTRA_PERMISSION_REQUEST_CODE, -1) : -1;
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(EXTRA_PERMISSION_REQUEST_CODE, this.mPendingRequestCode);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.mPendingRequestCode == -1) {
            Bundle extras = getIntent().getExtras();
            String[] stringArray = extras.getStringArray(EXTRA_PERMISSION_REQUESTED_PERMISSIONS);
            int i = extras.getInt(EXTRA_PERMISSION_REQUEST_CODE);
            this.mPendingRequestCode = i;
            PermissionsUtil.requestPermissions(this, i, stringArray);
        }
    }

    @Override // android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        this.mPendingRequestCode = -1;
        PermissionsManager.get(this).onRequestPermissionsResult(requestCode, permissions, grantResults);
        finish();
    }
}
