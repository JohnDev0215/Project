package com.android.permissioncontroller.role.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import com.android.car.ui.utils.RotaryConstants;
import com.android.permissioncontroller.role.service.RoleSearchIndexablesProvider;

/* JADX INFO: loaded from: classes8.dex */
public class RoleSearchTrampolineActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        Intent newIntent;
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (!RoleSearchIndexablesProvider.isIntentValid(intent, this)) {
            finish();
            return;
        }
        String action = intent.getAction();
        if (action == null) {
            finish();
            return;
        }
        byte b = -1;
        int iHashCode = action.hashCode();
        if (iHashCode != 263796501) {
            if (iHashCode == 853022806 && action.equals(RoleSearchIndexablesProvider.ACTION_MANAGE_SPECIAL_APP_ACCESS)) {
                b = 1;
            }
        } else if (action.equals(RoleSearchIndexablesProvider.ACTION_MANAGE_DEFAULT_APP)) {
            b = 0;
        }
        if (b == 0) {
            newIntent = DefaultAppActivity.createIntent(RoleSearchIndexablesProvider.getOriginalKey(intent), Process.myUserHandle(), this);
        } else if (b == 1) {
            newIntent = SpecialAppAccessActivity.createIntent(RoleSearchIndexablesProvider.getOriginalKey(intent), this);
        } else {
            finish();
            return;
        }
        newIntent.addFlags(RotaryConstants.ACTION_NUDGE_TO_ANOTHER_FOCUS_AREA);
        startActivity(newIntent);
        finish();
    }
}
