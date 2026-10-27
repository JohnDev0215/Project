package com.bumptech.glide.request;

import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.bumptech.glide.request.RequestCoordinator;
import spi.jm;

/* JADX INFO: loaded from: classes.dex */
public final class a implements RequestCoordinator, jm {
    public final Object a;

    @Nullable
    public final RequestCoordinator b;
    public volatile jm c;
    public volatile jm d;

    @GuardedBy("requestLock")
    public RequestCoordinator.RequestState e;

    @GuardedBy("requestLock")
    public RequestCoordinator.RequestState f;

    public a(Object obj, @Nullable RequestCoordinator requestCoordinator) {
        RequestCoordinator.RequestState requestState = RequestCoordinator.RequestState.CLEARED;
        this.e = requestState;
        this.f = requestState;
        this.a = obj;
        this.b = requestCoordinator;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator, spi.jm
    public final boolean a() {
        boolean z;
        synchronized (this.a) {
            z = this.c.a() || this.d.a();
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public final void b(jm jmVar) {
        synchronized (this.a) {
            if (jmVar.equals(this.c)) {
                this.e = RequestCoordinator.RequestState.SUCCESS;
            } else if (jmVar.equals(this.d)) {
                this.f = RequestCoordinator.RequestState.SUCCESS;
            }
            RequestCoordinator requestCoordinator = this.b;
            if (requestCoordinator != null) {
                requestCoordinator.b(this);
            }
        }
    }

    @Override // spi.jm
    public final boolean c(jm jmVar) {
        if (!(jmVar instanceof a)) {
            return false;
        }
        a aVar = (a) jmVar;
        return this.c.c(aVar.c) && this.d.c(aVar.d);
    }

    @Override // spi.jm
    public final void clear() {
        synchronized (this.a) {
            RequestCoordinator.RequestState requestState = RequestCoordinator.RequestState.CLEARED;
            this.e = requestState;
            this.c.clear();
            if (this.f != requestState) {
                this.f = requestState;
                this.d.clear();
            }
        }
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public final void d(jm jmVar) {
        synchronized (this.a) {
            if (jmVar.equals(this.d)) {
                this.f = RequestCoordinator.RequestState.FAILED;
                RequestCoordinator requestCoordinator = this.b;
                if (requestCoordinator != null) {
                    requestCoordinator.d(this);
                }
                return;
            }
            this.e = RequestCoordinator.RequestState.FAILED;
            RequestCoordinator.RequestState requestState = this.f;
            RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.RUNNING;
            if (requestState != requestState2) {
                this.f = requestState2;
                this.d.i();
            }
        }
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public final boolean e(jm jmVar) {
        boolean z;
        synchronized (this.a) {
            RequestCoordinator requestCoordinator = this.b;
            z = false;
            if ((requestCoordinator == null || requestCoordinator.e(this)) && k(jmVar)) {
                z = true;
            }
        }
        return z;
    }

    @Override // spi.jm
    public final boolean f() {
        boolean z;
        synchronized (this.a) {
            RequestCoordinator.RequestState requestState = this.e;
            RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.CLEARED;
            z = requestState == requestState2 && this.f == requestState2;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public final boolean g(jm jmVar) {
        boolean z;
        synchronized (this.a) {
            RequestCoordinator requestCoordinator = this.b;
            z = false;
            if ((requestCoordinator == null || requestCoordinator.g(this)) && k(jmVar)) {
                z = true;
            }
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public final RequestCoordinator getRoot() {
        RequestCoordinator root;
        synchronized (this.a) {
            RequestCoordinator requestCoordinator = this.b;
            root = requestCoordinator != null ? requestCoordinator.getRoot() : this;
        }
        return root;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public final boolean h(jm jmVar) {
        boolean z;
        synchronized (this.a) {
            RequestCoordinator requestCoordinator = this.b;
            z = false;
            if ((requestCoordinator == null || requestCoordinator.h(this)) && k(jmVar)) {
                z = true;
            }
        }
        return z;
    }

    @Override // spi.jm
    public final void i() {
        synchronized (this.a) {
            RequestCoordinator.RequestState requestState = this.e;
            RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.RUNNING;
            if (requestState != requestState2) {
                this.e = requestState2;
                this.c.i();
            }
        }
    }

    @Override // spi.jm
    public final boolean isRunning() {
        boolean z;
        synchronized (this.a) {
            RequestCoordinator.RequestState requestState = this.e;
            RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.RUNNING;
            z = requestState == requestState2 || this.f == requestState2;
        }
        return z;
    }

    @Override // spi.jm
    public final boolean j() {
        boolean z;
        synchronized (this.a) {
            RequestCoordinator.RequestState requestState = this.e;
            RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.SUCCESS;
            z = requestState == requestState2 || this.f == requestState2;
        }
        return z;
    }

    @GuardedBy("requestLock")
    public final boolean k(jm jmVar) {
        return jmVar.equals(this.c) || (this.e == RequestCoordinator.RequestState.FAILED && jmVar.equals(this.d));
    }

    @Override // spi.jm
    public final void pause() {
        synchronized (this.a) {
            RequestCoordinator.RequestState requestState = this.e;
            RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.RUNNING;
            if (requestState == requestState2) {
                this.e = RequestCoordinator.RequestState.PAUSED;
                this.c.pause();
            }
            if (this.f == requestState2) {
                this.f = RequestCoordinator.RequestState.PAUSED;
                this.d.pause();
            }
        }
    }
}
