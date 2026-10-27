package com.vivid.spotify.common.base;

import android.content.Context;
import androidx.lifecycle.b0;

/* JADX INFO: compiled from: Hilt_BaseActivity.java */
/* JADX INFO: loaded from: classes.dex */
abstract class j extends androidx.appcompat.app.c implements o4.c {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private volatile dagger.hilt.android.internal.managers.a f5221x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private final Object f5222y = new Object();

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private boolean f5223z = false;

    /* JADX INFO: compiled from: Hilt_BaseActivity.java */
    class a implements c.b {
        a() {
        }

        @Override // c.b
        public void a(Context context) {
            j.this.R();
        }
    }

    j() {
        O();
    }

    private void O() {
        o(new a());
    }

    public final dagger.hilt.android.internal.managers.a P() {
        if (this.f5221x == null) {
            synchronized (this.f5222y) {
                if (this.f5221x == null) {
                    this.f5221x = Q();
                }
            }
        }
        return this.f5221x;
    }

    protected dagger.hilt.android.internal.managers.a Q() {
        return new dagger.hilt.android.internal.managers.a(this);
    }

    protected void R() {
        if (this.f5223z) {
            return;
        }
        this.f5223z = true;
        ((b) f()).c((com.vivid.spotify.common.base.a) o4.e.a(this));
    }

    @Override // o4.b
    public final Object f() {
        return P().f();
    }

    @Override // androidx.activity.ComponentActivity, androidx.lifecycle.h
    public b0.b m() {
        return m4.a.a(this, super.m());
    }
}
