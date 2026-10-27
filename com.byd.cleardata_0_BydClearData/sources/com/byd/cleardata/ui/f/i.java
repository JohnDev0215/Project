package com.byd.cleardata.ui.f;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.byd.cleardata.f.c.b;

/* JADX INFO: loaded from: classes.dex */
public abstract class i<P extends com.byd.cleardata.f.c.b> extends Fragment {
    protected P Z;
    protected com.byd.cleardata.ui.e.n a0;
    protected com.byd.cleardata.ui.e.i b0;
    protected Context c0;
    private boolean d0;

    public void A1() {
        if (!b0()) {
            this.d0 = true;
            return;
        }
        com.byd.cleardata.ui.e.i iVar = this.b0;
        if (iVar != null) {
            iVar.z1();
        }
        this.b0 = null;
    }

    public void B1() {
        if (!b0()) {
            this.d0 = true;
            return;
        }
        com.byd.cleardata.ui.e.n nVar = this.a0;
        if (nVar != null) {
            nVar.z1();
        }
        this.a0 = null;
    }

    public void C1() {
        com.byd.cleardata.ui.e.n nVar = this.a0;
        if (nVar != null) {
            nVar.W1();
        }
    }

    protected abstract void D1(Bundle bundle);

    protected abstract void E1(View view);

    @Override // androidx.fragment.app.Fragment
    public void F0() {
        super.F0();
        com.byd.cleardata.b.h().v(null);
        if (this.d0) {
            this.d0 = false;
            A1();
            B1();
        }
    }

    protected abstract int F1();

    @Override // androidx.fragment.app.Fragment
    public void G0(Bundle bundle) {
        com.byd.cleardata.i.c.b("onSaveInstanceState");
        super.G0(bundle);
        this.Z.e(true);
        com.byd.cleardata.b.h().v(this.Z);
    }

    public void G1() {
        if (b0()) {
            com.byd.cleardata.ui.e.i iVar = this.b0;
            if (iVar != null) {
                iVar.z1();
            }
            com.byd.cleardata.ui.e.i iVar2 = new com.byd.cleardata.ui.e.i();
            this.b0 = iVar2;
            iVar2.I1(x());
        }
    }

    public void H1(int i, int i2) {
        if (!b0()) {
            this.d0 = true;
            return;
        }
        com.byd.cleardata.ui.e.n nVar = this.a0;
        if (nVar != null) {
            nVar.z1();
        }
        com.byd.cleardata.ui.e.n nVar2 = new com.byd.cleardata.ui.e.n(i, i2);
        this.a0 = nVar2;
        nVar2.V1(x());
    }

    @Override // androidx.fragment.app.Fragment
    public void e0(Bundle bundle) {
        super.e0(bundle);
        if (bundle != null) {
            this.a0 = (com.byd.cleardata.ui.e.n) x().c(com.byd.cleardata.ui.e.n.class.getSimpleName());
            this.b0 = (com.byd.cleardata.ui.e.i) x().c(com.byd.cleardata.ui.e.i.class.getSimpleName());
        }
        P p = (P) com.byd.cleardata.b.h().u();
        this.Z = p;
        if (p == null) {
            this.Z = (P) z1();
        }
        this.Z.e(false);
        D1(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void h0(Context context) {
        super.h0(context);
        this.c0 = (androidx.appcompat.app.c) context;
        org.greenrobot.eventbus.c.c().o(this);
    }

    @Override // androidx.fragment.app.Fragment
    public View o0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(F1(), viewGroup, false);
        E1(viewInflate);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void r0() {
        P p = this.Z;
        if (p != null) {
            p.b();
            this.Z = null;
        }
        com.byd.cleardata.ui.e.n nVar = this.a0;
        if (nVar != null && nVar.N1()) {
            this.a0.z1();
        }
        this.a0 = null;
        com.byd.cleardata.ui.e.i iVar = this.b0;
        if (iVar != null && iVar.H1()) {
            this.b0.z1();
        }
        this.b0 = null;
        super.r0();
    }

    @Override // androidx.fragment.app.Fragment
    public void s0() {
        this.c0 = null;
        org.greenrobot.eventbus.c.c().q(this);
        super.s0();
    }

    protected abstract P z1();
}
