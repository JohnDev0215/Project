package com.android.permissioncontroller.role.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import com.android.car.ui.utils.RotaryConstants;

/* JADX INFO: loaded from: classes8.dex */
public class HomeSettingsActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = DefaultAppActivity.createIntent("android.app.role.HOME", Process.myUserHandle(), this).addFlags(RotaryConstants.ACTION_NUDGE_TO_ANOTHER_FOCUS_AREA);
        startActivity(intent);
        finish();
    }
}
