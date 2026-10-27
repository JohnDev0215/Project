package com.vivid.spotify.common.base;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.ViewDataBinding;
import androidx.navigation.q;

/* JADX INFO: compiled from: BaseFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class h<T extends ViewDataBinding> extends g {

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public T f5218j0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    private final String f5217i0 = "BaseFragment";

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    androidx.activity.b f5219k0 = new a(false);

    /* JADX INFO: compiled from: BaseFragment.java */
    class a extends androidx.activity.b {
        a(boolean z6) {
            super(z6);
        }

        @Override // androidx.activity.b
        public void b() {
            h.this.T1();
        }
    }

    private boolean S1() {
        T t6 = this.f5218j0;
        if (t6 == null) {
            return false;
        }
        try {
            androidx.navigation.e eVarL = q.a(t6.G()).l();
            if (eVarL != null && !TextUtils.equals("empty", eVarL.e().l())) {
                h3.i.b("BaseFragment").b("fragment can back", new Object[0]);
                return true;
            }
        } catch (Exception e7) {
            h3.i.b("BaseFragment").e(e7, "check fragment canBack error", new Object[0]);
        }
        return false;
    }

    @Override // com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public void B0() {
        super.B0();
        this.f5218j0 = null;
    }

    @Override // androidx.fragment.app.Fragment
    public void C0() {
        super.C0();
    }

    @Override // com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public void K0() {
        super.K0();
        this.f5219k0.f(false);
    }

    @Override // com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public void P0() {
        super.P0();
        this.f5219k0.f(S1());
    }

    @Override // com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public void S0() {
        super.S0();
    }

    protected void T1() {
        q.a(this.f5218j0.G()).s();
    }

    @Override // androidx.fragment.app.Fragment
    public void r0(Context context) {
        super.r0(context);
        j().c().a(this, this.f5219k0);
    }

    @Override // com.vivid.spotify.common.base.g, androidx.fragment.app.Fragment
    public View y0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.y0(layoutInflater, viewGroup, bundle);
    }
}
