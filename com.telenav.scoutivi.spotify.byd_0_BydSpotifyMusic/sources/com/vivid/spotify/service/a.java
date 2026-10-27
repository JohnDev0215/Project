package com.vivid.spotify.service;

import android.app.Service;
import dagger.hilt.android.internal.managers.h;
import o4.e;

/* JADX INFO: compiled from: Hilt_MediaManagerService.java */
/* JADX INFO: loaded from: classes.dex */
abstract class a extends Service implements o4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private volatile h f5595e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Object f5596g = new Object();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f5597h = false;

    a() {
    }

    public final h a() {
        if (this.f5595e == null) {
            synchronized (this.f5596g) {
                if (this.f5595e == null) {
                    this.f5595e = b();
                }
            }
        }
        return this.f5595e;
    }

    protected h b() {
        return new h(this);
    }

    protected void c() {
        if (this.f5597h) {
            return;
        }
        this.f5597h = true;
        ((b) f()).b((MediaManagerService) e.a(this));
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
