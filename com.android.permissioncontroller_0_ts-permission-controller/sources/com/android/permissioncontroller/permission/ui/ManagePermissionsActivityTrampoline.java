package com.android.permissioncontroller.permission.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.android.car.ui.utils.RotaryConstants;
import com.android.permissioncontroller.permission.service.PermissionSearchIndexablesProvider;

/* JADX INFO: loaded from: classes8.dex */
public class ManagePermissionsActivityTrampoline extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (!PermissionSearchIndexablesProvider.isIntentValid(intent, this)) {
            finish();
            return;
        }
        String action = intent.getAction();
        if (action == null) {
            finish();
            return;
        }
        Intent newIntent = new Intent(this, (Class<?>) ManagePermissionsActivity.class).addFlags(RotaryConstants.ACTION_NUDGE_TO_ANOTHER_FOCUS_AREA);
        if (action.equals(PermissionSearchIndexablesProvider.ACTION_MANAGE_PERMISSION_APPS)) {
            newIntent.setAction("android.intent.action.MANAGE_PERMISSION_APPS").putExtra("android.intent.extra.PERMISSION_NAME", PermissionSearchIndexablesProvider.getOriginalKey(intent));
            startActivity(newIntent);
            finish();
            return;
        }
        finish();
    }
}
