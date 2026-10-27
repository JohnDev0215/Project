package com.vivid.spotify.rootui.category;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.b0;

/* JADX INFO: compiled from: Hilt_CategoryFragment.java */
/* JADX INFO: loaded from: classes.dex */
abstract class r<T extends ViewDataBinding> extends com.vivid.spotify.common.base.h<T> implements o4.c {

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    private ContextWrapper f5484l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    private volatile dagger.hilt.android.internal.managers.g f5485m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    private final Object f5486n0 = new Object();

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    private boolean f5487o0 = false;

    r() {
    }

    private void W1() {
        if (this.f5484l0 == null) {
            this.f5484l0 = dagger.hilt.android.internal.managers.g.b(super.s(), this);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public LayoutInflater D0(Bundle bundle) {
        return LayoutInflater.from(dagger.hilt.android.internal.managers.g.c(super.D0(bundle), this));
    }

    public final dagger.hilt.android.internal.managers.g U1() {
        if (this.f5485m0 == null) {
            synchronized (this.f5486n0) {
                if (this.f5485m0 == null) {
                    this.f5485m0 = V1();
                }
            }
        }
        return this.f5485m0;
    }

    protected dagger.hilt.android.internal.managers.g V1() {
        return new dagger.hilt.android.internal.managers.g(this);
    }

    protected void X1() {
        if (this.f5487o0) {
            return;
        }
        this.f5487o0 = true;
        ((e) f()).d((CategoryFragment) o4.e.a(this));
    }

    @Override // o4.b
    public final Object f() {
        return U1().f();
    }

    @Override // androidx.fragment.app.Fragment, androidx.lifecycle.h
    public b0.b m() {
        return m4.a.b(this, super.m());
    }

    @Override // androidx.fragment.app.Fragment
    public void q0(Activity activity) {
        super.q0(activity);
        ContextWrapper contextWrapper = this.f5484l0;
        o4.d.d(contextWrapper == null || dagger.hilt.android.internal.managers.g.d(contextWrapper) == activity, "onAttach called multiple times with different Context! Hilt Fragments should not be retained.", new Object[0]);
        W1();
        X1();
    }

    @Override // com.vivid.spotify.common.base.h, androidx.fragment.app.Fragment
    public void r0(Context context) {
        super.r0(context);
        W1();
        X1();
    }

    @Override // androidx.fragment.app.Fragment
    public Context s() {
        if (super.s() == null && this.f5484l0 == null) {
            return null;
        }
        W1();
        return this.f5484l0;
    }
}
