package com.bumptech.glide.request;

import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.bumptech.glide.request.RequestCoordinator;
import spi.jm;

/* JADX INFO: loaded from: classes.dex */
public final class b implements RequestCoordinator, jm {

    @Nullable
    public final RequestCoordinator a;
    public final Object b;
    public volatile jm c;
    public volatile jm d;

    @GuardedBy("requestLock")
    public RequestCoordinator.RequestState e;

    @GuardedBy("requestLock")
    public RequestCoordinator.RequestState f;

    @GuardedBy("requestLock")
    public boolean g;

    public b(Object obj, @Nullable RequestCoordinator requestCoordinator) {
        RequestCoordinator.RequestState requestState = RequestCoordinator.RequestState.CLEARED;
        this.e = requestState;
        this.f = requestState;
        this.b = obj;
        this.a = requestCoordinator;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator, spi.jm
    public final boolean a() {
        boolean z;
        synchronized (this.b) {
            z = this.d.a() || this.c.a();
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public final void b(jm jmVar) {
        synchronized (this.b) {
            if (jmVar.equals(this.d)) {
                this.f = RequestCoordinator.RequestState.SUCCESS;
                return;
            }
            this.e = RequestCoordinator.RequestState.SUCCESS;
            RequestCoordinator requestCoordinator = this.a;
            if (requestCoordinator != null) {
                requestCoordinator.b(this);
            }
            if (!this.f.isComplete()) {
                this.d.clear();
            }
        }
    }

    @Override // spi.jm
    public final boolean c(jm jmVar) {
        if (!(jmVar instanceof b)) {
            return false;
        }
        b bVar = (b) jmVar;
        if (this.c == null) {
            if (bVar.c != null) {
                return false;
            }
        } else if (!this.c.c(bVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (bVar.d != null) {
                return false;
            }
        } else if (!this.d.c(bVar.d)) {
            return false;
        }
        return true;
    }

    @Override // spi.jm
    public final void clear() {
        synchronized (this.b) {
            this.g = false;
            RequestCoordinator.RequestState requestState = RequestCoordinator.RequestState.CLEARED;
            this.e = requestState;
            this.f = requestState;
            this.d.clear();
            this.c.clear();
        }
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public final void d(jm jmVar) {
        synchronized (this.b) {
            if (!jmVar.equals(this.c)) {
                this.f = RequestCoordinator.RequestState.FAILED;
                return;
            }
            this.e = RequestCoordinator.RequestState.FAILED;
            RequestCoordinator requestCoordinator = this.a;
            if (requestCoordinator != null) {
                requestCoordinator.d(this);
            }
        }
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public final boolean e(jm jmVar) {
        boolean z;
        synchronized (this.b) {
            RequestCoordinator requestCoordinator = this.a;
            z = false;
            if ((requestCoordinator == null || requestCoordinator.e(this)) && jmVar.equals(this.c) && !a()) {
                z = true;
            }
        }
        return z;
    }

    @Override // spi.jm
    public final boolean f() {
        boolean z;
        synchronized (this.b) {
            z = this.e == RequestCoordinator.RequestState.CLEARED;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public final boolean g(jm jmVar) {
        boolean z;
        synchronized (this.b) {
            RequestCoordinator requestCoordinator = this.a;
            z = false;
            if ((requestCoordinator == null || requestCoordinator.g(this)) && jmVar.equals(this.c) && this.e != RequestCoordinator.RequestState.PAUSED) {
                z = true;
            }
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public final RequestCoordinator getRoot() {
        RequestCoordinator root;
        synchronized (this.b) {
            RequestCoordinator requestCoordinator = this.a;
            root = requestCoordinator != null ? requestCoordinator.getRoot() : this;
        }
        return root;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public final boolean h(jm jmVar) {
        boolean z;
        synchronized (this.b) {
            RequestCoordinator requestCoordinator = this.a;
            z = false;
            if ((requestCoordinator == null || requestCoordinator.h(this)) && (jmVar.equals(this.c) || this.e != RequestCoordinator.RequestState.SUCCESS)) {
                z = true;
            }
        }
        return z;
    }

    @Override // spi.jm
    public final void i() {
        synchronized (this.b) {
            this.g = true;
            try {
                if (this.e != RequestCoordinator.RequestState.SUCCESS) {
                    RequestCoordinator.RequestState requestState = this.f;
                    RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.RUNNING;
                    if (requestState != requestState2) {
                        this.f = requestState2;
                        this.d.i();
                    }
                }
                if (this.g) {
                    RequestCoordinator.RequestState requestState3 = this.e;
                    RequestCoordinator.RequestState requestState4 = RequestCoordinator.RequestState.RUNNING;
                    if (requestState3 != requestState4) {
                        this.e = requestState4;
                        this.c.i();
                    }
                }
            } finally {
                this.g = false;
            }
        }
    }

    @Override // spi.jm
    public final boolean isRunning() {
        boolean z;
        synchronized (this.b) {
            z = this.e == RequestCoordinator.RequestState.RUNNING;
        }
        return z;
    }

    @Override // spi.jm
    public final boolean j() {
        boolean z;
        synchronized (this.b) {
            z = this.e == RequestCoordinator.RequestState.SUCCESS;
        }
        return z;
    }

    @Override // spi.jm
    public final void pause() {
        synchronized (this.b) {
            if (!this.f.isComplete()) {
                this.f = RequestCoordinator.RequestState.PAUSED;
                this.d.pause();
            }
            if (!this.e.isComplete()) {
                this.e = RequestCoordinator.RequestState.PAUSED;
                this.c.pause();
            }
        }
    }
}
