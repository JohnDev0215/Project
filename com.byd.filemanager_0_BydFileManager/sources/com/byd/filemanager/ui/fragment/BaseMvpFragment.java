package com.byd.filemanager.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.byd.filemanager.i.c.a;
import com.byd.filemanager.ui.c.h;
import com.byd.filemanager.ui.c.m;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseMvpFragment<P extends com.byd.filemanager.i.c.a> extends Fragment {
    protected String Z = getClass().getSimpleName();
    protected P a0;
    protected m b0;
    protected h c0;
    protected Context d0;
    protected volatile boolean e0;

    public void A1() {
        if (!b0()) {
            com.byd.filemanager.k.b.b("dismissLoadingDialog isResumed false");
            com.byd.filemanager.f.h().E(true);
        } else {
            h hVar = this.c0;
            if (hVar != null) {
                hVar.z1();
            }
            this.c0 = null;
        }
    }

    public void B1() {
        if (!b0()) {
            com.byd.filemanager.k.b.b("dismissProgressDialog isResumed false");
            com.byd.filemanager.f.h().E(true);
        } else {
            m mVar = this.b0;
            if (mVar != null) {
                mVar.z1();
            }
            this.b0 = null;
        }
    }

    public void C1() {
        m mVar = this.b0;
        if (mVar != null) {
            mVar.d2();
        }
    }

    protected abstract void D1(Bundle bundle);

    protected abstract void E1(View view);

    @Override // androidx.fragment.app.Fragment
    public void F0() {
        super.F0();
        com.byd.filemanager.f.h().C(null);
        if (com.byd.filemanager.f.h().t()) {
            com.byd.filemanager.f.h().E(false);
            A1();
            B1();
        }
    }

    protected abstract int F1();

    @Override // androidx.fragment.app.Fragment
    public void G0(Bundle bundle) {
        super.G0(bundle);
        this.a0.e(true);
        com.byd.filemanager.f.h().C(this.a0);
    }

    public void G1(int i, int i2) {
        if (!b0()) {
            com.byd.filemanager.f.h().E(true);
            return;
        }
        m mVar = this.b0;
        if (mVar != null) {
            mVar.z1();
        }
        m mVar2 = new m(i, i2);
        this.b0 = mVar2;
        mVar2.c2(w());
    }

    @Override // androidx.fragment.app.Fragment
    public void e0(Bundle bundle) {
        super.e0(bundle);
        if (bundle != null) {
            this.b0 = (m) w().d(m.class.getSimpleName());
            this.c0 = (h) w().d(h.class.getSimpleName());
        }
        P p = (P) com.byd.filemanager.f.h().B();
        this.a0 = p;
        if (p == null) {
            this.a0 = (P) z1();
        }
        this.a0.e(false);
        D1(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void h0(Context context) {
        super.h0(context);
        com.byd.filemanager.k.b.c(this.Z, "onAttach is begin");
        this.d0 = (AppCompatActivity) context;
        if (this.e0) {
            return;
        }
        org.greenrobot.eventbus.c.c().o(this);
        this.e0 = true;
    }

    @Override // androidx.fragment.app.Fragment
    public View o0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(F1(), viewGroup, false);
        E1(viewInflate);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void r0() {
        P p = this.a0;
        if (p != null) {
            p.b();
            this.a0 = null;
        }
        m mVar = this.b0;
        if (mVar != null && mVar.U1()) {
            this.b0.z1();
        }
        this.b0 = null;
        h hVar = this.c0;
        if (hVar != null && hVar.I1()) {
            this.c0.z1();
        }
        this.c0 = null;
        super.r0();
    }

    @Override // androidx.fragment.app.Fragment
    public void s0() {
        this.d0 = null;
        if (this.e0) {
            org.greenrobot.eventbus.c.c().q(this);
        }
        this.e0 = false;
        com.byd.filemanager.k.b.c(this.Z, "onDetach is end");
        super.s0();
    }

    protected abstract P z1();
}
