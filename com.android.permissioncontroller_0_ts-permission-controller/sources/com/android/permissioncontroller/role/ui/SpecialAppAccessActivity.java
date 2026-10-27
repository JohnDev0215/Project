package com.android.permissioncontroller.role.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/* JADX INFO: loaded from: classes8.dex */
public class SpecialAppAccessActivity extends Activity {
    public static Intent createIntent(String roleName, Context context) {
        return new Intent(context, (Class<?>) SpecialAppAccessActivity.class);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finish();
    }
}
