package com.byd.profile.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/* JADX INFO: loaded from: classes2.dex */
public class BaseActivity extends AppCompatActivity {
    public static final String ACTION_CLOSE_BAR = "com.android.systemui.action.byd.BarReceiver";
    public static final String EXTRA_VISIBLE = "bar_visible";
    public static final int NAVIBAR_MODE_MODE_ALL = 1;
    public static final String NAVIBAR_MODE_ON_SCREENSAVER = "navibar_mode_on_screensaver";
    public static final int NAVIBAR_MODE_POWER_OFF = 0;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
    }

    public void setFullScreen() {
        getWindow().getDecorView().setSystemUiVisibility(4871);
    }
}
