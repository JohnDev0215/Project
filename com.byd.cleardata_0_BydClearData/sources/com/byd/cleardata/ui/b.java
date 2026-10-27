package com.byd.cleardata.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import com.byd.cleardata.f.c.b;
import com.byd.cleardata.ui.e.i;
import com.byd.cleardata.ui.e.n;

/* JADX INFO: loaded from: classes.dex */
public abstract class b<P extends com.byd.cleardata.f.c.b> extends androidx.appcompat.app.c {
    protected P u;
    protected n v;
    protected i w;

    protected abstract P Q();

    public void R() {
        i iVar = this.w;
        if (iVar != null) {
            iVar.z1();
        }
        this.w = null;
    }

    public void S() {
        i iVar = this.w;
        if (iVar != null) {
            iVar.z1();
        }
        i iVar2 = new i();
        this.w = iVar2;
        iVar2.I1(y());
    }

    @Override // androidx.appcompat.app.c, androidx.fragment.app.d, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // androidx.appcompat.app.c, androidx.fragment.app.d, androidx.activity.ComponentActivity, androidx.core.app.d, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.u = (P) Q();
    }

    @Override // androidx.appcompat.app.c, androidx.fragment.app.d, android.app.Activity
    protected void onDestroy() {
        P p = this.u;
        if (p != null) {
            p.b();
            this.u = null;
        }
        n nVar = this.v;
        if (nVar != null && nVar.N1()) {
            this.v.z1();
        }
        this.v = null;
        i iVar = this.w;
        if (iVar != null && iVar.H1()) {
            this.w.z1();
        }
        this.w = null;
        super.onDestroy();
    }
}
