package com.byd.ota.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import c.a.d.h.e;

/* JADX INFO: loaded from: classes.dex */
public class BaseActivity extends AppCompatActivity {
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        e.c(this);
    }
}
