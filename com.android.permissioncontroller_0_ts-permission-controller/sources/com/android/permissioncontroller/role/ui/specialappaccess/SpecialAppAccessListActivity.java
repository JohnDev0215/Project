package com.android.permissioncontroller.role.ui.specialappaccess;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.android.permissioncontroller.DeviceUtils;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.role.ui.auto.AutoSpecialAppAccessListFragment;
import com.android.permissioncontroller.role.ui.specialappaccess.handheld.HandheldSpecialAppAccessListFragment;

/* JADX INFO: loaded from: classes8.dex */
public class SpecialAppAccessListActivity extends FragmentActivity {
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        Fragment fragment;
        if (DeviceUtils.isAuto(this)) {
            setTheme(R.style.CarSettings);
        }
        super.onCreate(savedInstanceState);
        getWindow().addSystemFlags(524288);
        if (savedInstanceState == null) {
            if (DeviceUtils.isAuto(this)) {
                fragment = AutoSpecialAppAccessListFragment.newInstance();
            } else {
                fragment = HandheldSpecialAppAccessListFragment.newInstance();
            }
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, fragment).commit();
        }
    }
}
