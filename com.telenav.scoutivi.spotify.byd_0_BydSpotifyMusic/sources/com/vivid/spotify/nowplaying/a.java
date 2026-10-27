package com.vivid.spotify.nowplaying;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.b0;

/* JADX INFO: compiled from: Hilt_NowPlayingFragment.java */
/* JADX INFO: loaded from: classes.dex */
abstract class a extends Fragment implements o4.c {

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    private ContextWrapper f5332g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    private volatile dagger.hilt.android.internal.managers.g f5333h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    private final Object f5334i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    private boolean f5335j0;

    a() {
        this.f5334i0 = new Object();
        this.f5335j0 = false;
    }

    private void P1() {
        if (this.f5332g0 == null) {
            this.f5332g0 = dagger.hilt.android.internal.managers.g.b(super.s(), this);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public LayoutInflater D0(Bundle bundle) {
        return LayoutInflater.from(dagger.hilt.android.internal.managers.g.c(super.D0(bundle), this));
    }

    public final dagger.hilt.android.internal.managers.g N1() {
        if (this.f5333h0 == null) {
            synchronized (this.f5334i0) {
                if (this.f5333h0 == null) {
                    this.f5333h0 = O1();
                }
            }
        }
        return this.f5333h0;
    }

    protected dagger.hilt.android.internal.managers.g O1() {
        return new dagger.hilt.android.internal.managers.g(this);
    }

    protected void Q1() {
        if (this.f5335j0) {
            return;
        }
        this.f5335j0 = true;
        ((e) f()).k((NowPlayingFragment) o4.e.a(this));
    }

    @Override // o4.b
    public final Object f() {
        return N1().f();
    }

    @Override // androidx.fragment.app.Fragment, androidx.lifecycle.h
    public b0.b m() {
        return m4.a.b(this, super.m());
    }

    @Override // androidx.fragment.app.Fragment
    public void q0(Activity activity) {
        super.q0(activity);
        ContextWrapper contextWrapper = this.f5332g0;
        o4.d.d(contextWrapper == null || dagger.hilt.android.internal.managers.g.d(contextWrapper) == activity, "onAttach called multiple times with different Context! Hilt Fragments should not be retained.", new Object[0]);
        P1();
        Q1();
    }

    @Override // androidx.fragment.app.Fragment
    public void r0(Context context) {
        super.r0(context);
        P1();
        Q1();
    }

    @Override // androidx.fragment.app.Fragment
    public Context s() {
        if (super.s() == null && this.f5332g0 == null) {
            return null;
        }
        P1();
        return this.f5332g0;
    }

    a(int i7) {
        super(i7);
        this.f5334i0 = new Object();
        this.f5335j0 = false;
    }
}
