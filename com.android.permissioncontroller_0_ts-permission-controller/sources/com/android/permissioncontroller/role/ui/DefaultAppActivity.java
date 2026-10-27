package com.android.permissioncontroller.role.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.os.UserHandle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.android.permissioncontroller.DeviceUtils;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.role.model.Role;
import com.android.permissioncontroller.role.model.Roles;
import com.android.permissioncontroller.role.ui.auto.AutoDefaultAppFragment;
import com.android.permissioncontroller.role.ui.handheld.HandheldDefaultAppFragment;

/* JADX INFO: loaded from: classes8.dex */
public class DefaultAppActivity extends FragmentActivity {
    private static final String LOG_TAG = DefaultAppActivity.class.getSimpleName();

    public static Intent createIntent(String roleName, UserHandle user, Context context) {
        return new Intent(context, (Class<?>) DefaultAppActivity.class).putExtra("android.intent.extra.ROLE_NAME", roleName).putExtra("android.intent.extra.USER", user);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        Fragment fragment;
        if (DeviceUtils.isAuto(this)) {
            setTheme(R.style.CarSettings);
        }
        super.onCreate(savedInstanceState);
        getWindow().addSystemFlags(524288);
        Intent intent = getIntent();
        String roleName = intent.getStringExtra("android.intent.extra.ROLE_NAME");
        UserHandle user = (UserHandle) intent.getParcelableExtra("android.intent.extra.USER");
        if (user == null) {
            user = Process.myUserHandle();
        }
        Role role = Roles.get(this).get(roleName);
        if (role == null) {
            Log.e(LOG_TAG, "Unknown role: " + roleName);
            finish();
            return;
        }
        if (!role.isAvailableAsUser(user, this)) {
            Log.e(LOG_TAG, "Role is unavailable: " + roleName);
            finish();
            return;
        }
        if (!role.isVisibleAsUser(user, this)) {
            Log.e(LOG_TAG, "Role is invisible: " + roleName);
            finish();
            return;
        }
        if (savedInstanceState == null) {
            if (DeviceUtils.isAuto(this)) {
                fragment = AutoDefaultAppFragment.newInstance(roleName, user);
            } else {
                fragment = HandheldDefaultAppFragment.newInstance(roleName, user);
            }
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, fragment).commit();
        }
    }
}
