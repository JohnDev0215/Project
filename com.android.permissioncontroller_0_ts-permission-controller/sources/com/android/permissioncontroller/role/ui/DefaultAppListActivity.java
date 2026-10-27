package com.android.permissioncontroller.role.ui;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.android.permissioncontroller.DeviceUtils;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.role.ui.auto.AutoDefaultAppListFragment;
import com.android.permissioncontroller.role.ui.handheld.HandheldDefaultAppListFragment;

/* JADX INFO: loaded from: classes8.dex */
public class DefaultAppListActivity extends FragmentActivity {
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
                fragment = AutoDefaultAppListFragment.newInstance();
            } else {
                fragment = HandheldDefaultAppListFragment.newInstance();
            }
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, fragment).commit();
        }
    }
}
