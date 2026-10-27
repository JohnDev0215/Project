package com.byd.engineermode.ui.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.byd.engineermode.DiagnoseApplication;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseActivity extends AppCompatActivity {
    protected com.byd.engineermode.ui.dialog.c u;

    public void G(int i) {
    }

    public void H() {
        com.byd.engineermode.ui.dialog.c cVar = this.u;
        if (cVar != null) {
            cVar.n1();
        }
        this.u = null;
    }

    public void I() {
        setResult(0);
        finish();
    }

    public void J() {
        setResult(-1);
        finish();
    }

    public void K() {
        setResult(0);
    }

    public void L() {
        com.byd.engineermode.ui.dialog.c cVar = this.u;
        if (cVar != null) {
            cVar.n1();
        }
        com.byd.engineermode.ui.dialog.c cVar2 = new com.byd.engineermode.ui.dialog.c();
        this.u = cVar2;
        cVar2.v1(p());
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        DiagnoseApplication.e().c(this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        com.byd.engineermode.ui.dialog.c cVar = this.u;
        if (cVar != null && cVar.u1()) {
            this.u.n1();
        }
        this.u = null;
        DiagnoseApplication.e().f(this);
        super.onDestroy();
    }
}
