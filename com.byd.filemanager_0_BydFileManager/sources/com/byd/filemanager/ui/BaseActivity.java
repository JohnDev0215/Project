package com.byd.filemanager.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.byd.filemanager.i.c.a;
import com.byd.filemanager.ui.c.h;
import com.byd.filemanager.ui.c.m;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseActivity<P extends com.byd.filemanager.i.c.a> extends AppCompatActivity {
    protected P u;
    protected m v;
    protected h w;

    protected abstract P P();

    public void Q() {
        h hVar = this.w;
        if (hVar != null) {
            hVar.z1();
        }
        this.w = null;
    }

    public void R() {
        h hVar = this.w;
        if (hVar != null) {
            hVar.A1(true);
        }
        this.w = null;
    }

    public void S() {
        h hVar = this.w;
        if (hVar != null) {
            hVar.z1();
        }
        h hVar2 = new h();
        this.w = hVar2;
        hVar2.J1(x());
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.u = (P) P();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        P p = this.u;
        if (p != null) {
            p.b();
            this.u = null;
        }
        m mVar = this.v;
        if (mVar != null && mVar.U1()) {
            this.v.z1();
        }
        this.v = null;
        h hVar = this.w;
        if (hVar != null && hVar.I1()) {
            this.w.z1();
        }
        this.w = null;
        super.onDestroy();
    }
}
