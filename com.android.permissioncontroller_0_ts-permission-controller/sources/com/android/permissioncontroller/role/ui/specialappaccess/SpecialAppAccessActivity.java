package com.android.permissioncontroller.role.ui.specialappaccess;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.android.permissioncontroller.DeviceUtils;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.role.model.Role;
import com.android.permissioncontroller.role.model.Roles;
import com.android.permissioncontroller.role.ui.auto.AutoSpecialAppAccessFragment;
import com.android.permissioncontroller.role.ui.specialappaccess.handheld.HandheldSpecialAppAccessFragment;

/* JADX INFO: loaded from: classes8.dex */
public class SpecialAppAccessActivity extends FragmentActivity {
    private static final String LOG_TAG = SpecialAppAccessActivity.class.getSimpleName();

    public static Intent createIntent(String roleName, Context context) {
        return new Intent(context, (Class<?>) SpecialAppAccessActivity.class).putExtra("android.intent.extra.ROLE_NAME", roleName);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        Fragment fragment;
        if (DeviceUtils.isAuto(this)) {
            setTheme(R.style.CarSettings);
        }
        super.onCreate(savedInstanceState);
        getWindow().addSystemFlags(524288);
        String roleName = getIntent().getStringExtra("android.intent.extra.ROLE_NAME");
        Role role = Roles.get(this).get(roleName);
        if (role == null) {
            Log.e(LOG_TAG, "Unknown role: " + roleName);
            finish();
            return;
        }
        if (!role.isAvailable(this)) {
            Log.e(LOG_TAG, "Role is unavailable: " + roleName);
            finish();
            return;
        }
        if (!role.isVisible(this)) {
            Log.e(LOG_TAG, "Role is invisible: " + roleName);
            finish();
            return;
        }
        if (savedInstanceState == null) {
            if (DeviceUtils.isAuto(this)) {
                fragment = AutoSpecialAppAccessFragment.newInstance(roleName);
            } else {
                fragment = HandheldSpecialAppAccessFragment.newInstance(roleName);
            }
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, fragment).commit();
        }
    }
}
