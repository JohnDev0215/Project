package com.vivid.spotify.details;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.lifecycle.b0;

/* JADX INFO: compiled from: Hilt_DetailChildFragment.java */
/* JADX INFO: loaded from: classes.dex */
abstract class s extends com.vivid.spotify.common.base.g implements o4.c {

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    private ContextWrapper f5301i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    private volatile dagger.hilt.android.internal.managers.g f5302j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    private final Object f5303k0 = new Object();

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    private boolean f5304l0 = false;

    s() {
    }

    private void U1() {
        if (this.f5301i0 == null) {
            this.f5301i0 = dagger.hilt.android.internal.managers.g.b(super.s(), this);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public LayoutInflater D0(Bundle bundle) {
        return LayoutInflater.from(dagger.hilt.android.internal.managers.g.c(super.D0(bundle), this));
    }

    public final dagger.hilt.android.internal.managers.g S1() {
        if (this.f5302j0 == null) {
            synchronized (this.f5303k0) {
                if (this.f5302j0 == null) {
                    this.f5302j0 = T1();
                }
            }
        }
        return this.f5302j0;
    }

    protected dagger.hilt.android.internal.managers.g T1() {
        return new dagger.hilt.android.internal.managers.g(this);
    }

    protected void V1() {
        if (this.f5304l0) {
            return;
        }
        this.f5304l0 = true;
        ((f) f()).i((e) o4.e.a(this));
    }

    @Override // o4.b
    public final Object f() {
        return S1().f();
    }

    @Override // androidx.fragment.app.Fragment, androidx.lifecycle.h
    public b0.b m() {
        return m4.a.b(this, super.m());
    }

    @Override // androidx.fragment.app.Fragment
    public void q0(Activity activity) {
        super.q0(activity);
        ContextWrapper contextWrapper = this.f5301i0;
        o4.d.d(contextWrapper == null || dagger.hilt.android.internal.managers.g.d(contextWrapper) == activity, "onAttach called multiple times with different Context! Hilt Fragments should not be retained.", new Object[0]);
        U1();
        V1();
    }

    @Override // androidx.fragment.app.Fragment
    public void r0(Context context) {
        super.r0(context);
        U1();
        V1();
    }

    @Override // androidx.fragment.app.Fragment
    public Context s() {
        if (super.s() == null && this.f5301i0 == null) {
            return null;
        }
        U1();
        return this.f5301i0;
    }
}
