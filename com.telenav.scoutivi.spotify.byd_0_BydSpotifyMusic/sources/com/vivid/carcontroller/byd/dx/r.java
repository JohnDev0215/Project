package com.vivid.carcontroller.byd.dx;

import android.app.Service;

/* JADX INFO: compiled from: Hilt_RestoreStateService.java */
/* JADX INFO: loaded from: classes.dex */
abstract class r extends Service implements o4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private volatile dagger.hilt.android.internal.managers.h f5164e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Object f5165g = new Object();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f5166h = false;

    r() {
    }

    public final dagger.hilt.android.internal.managers.h a() {
        if (this.f5164e == null) {
            synchronized (this.f5165g) {
                if (this.f5164e == null) {
                    this.f5164e = b();
                }
            }
        }
        return this.f5164e;
    }

    protected dagger.hilt.android.internal.managers.h b() {
        return new dagger.hilt.android.internal.managers.h(this);
    }

    protected void c() {
        if (this.f5166h) {
            return;
        }
        this.f5166h = true;
        ((s) f()).a((RestoreStateService) o4.e.a(this));
    }

    @Override // o4.b
    public final Object f() {
        return a().f();
    }

    @Override // android.app.Service
    public void onCreate() {
        c();
        super.onCreate();
    }
}
