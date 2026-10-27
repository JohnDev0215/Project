package com.bumptech.glide.request;

import com.bumptech.glide.request.RequestCoordinator;

/* JADX INFO: compiled from: ThumbnailRequestCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements RequestCoordinator, c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final RequestCoordinator f3227a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Object f3228b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile c f3229c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile c f3230d;
    private RequestCoordinator.RequestState e;
    private RequestCoordinator.RequestState f;
    private boolean g;

    public g(Object obj, RequestCoordinator requestCoordinator) {
        RequestCoordinator.RequestState requestState = RequestCoordinator.RequestState.CLEARED;
        this.e = requestState;
        this.f = requestState;
        this.f3228b = obj;
        this.f3227a = requestCoordinator;
    }

    private boolean l() {
        RequestCoordinator requestCoordinator = this.f3227a;
        return requestCoordinator == null || requestCoordinator.k(this);
    }

    private boolean m() {
        RequestCoordinator requestCoordinator = this.f3227a;
        return requestCoordinator == null || requestCoordinator.c(this);
    }

    private boolean n() {
        RequestCoordinator requestCoordinator = this.f3227a;
        return requestCoordinator == null || requestCoordinator.f(this);
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public void a(c cVar) {
        synchronized (this.f3228b) {
            if (!cVar.equals(this.f3229c)) {
                this.f = RequestCoordinator.RequestState.FAILED;
                return;
            }
            this.e = RequestCoordinator.RequestState.FAILED;
            RequestCoordinator requestCoordinator = this.f3227a;
            if (requestCoordinator != null) {
                requestCoordinator.a(this);
            }
        }
    }

    @Override // com.bumptech.glide.request.RequestCoordinator, com.bumptech.glide.request.c
    public boolean b() {
        boolean z;
        synchronized (this.f3228b) {
            z = this.f3230d.b() || this.f3229c.b();
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public boolean c(c cVar) {
        boolean z;
        synchronized (this.f3228b) {
            z = m() && cVar.equals(this.f3229c) && !b();
        }
        return z;
    }

    @Override // com.bumptech.glide.request.c
    public void clear() {
        synchronized (this.f3228b) {
            this.g = false;
            RequestCoordinator.RequestState requestState = RequestCoordinator.RequestState.CLEARED;
            this.e = requestState;
            this.f = requestState;
            this.f3230d.clear();
            this.f3229c.clear();
        }
    }

    @Override // com.bumptech.glide.request.c
    public boolean d(c cVar) {
        if (!(cVar instanceof g)) {
            return false;
        }
        g gVar = (g) cVar;
        if (this.f3229c == null) {
            if (gVar.f3229c != null) {
                return false;
            }
        } else if (!this.f3229c.d(gVar.f3229c)) {
            return false;
        }
        if (this.f3230d == null) {
            if (gVar.f3230d != null) {
                return false;
            }
        } else if (!this.f3230d.d(gVar.f3230d)) {
            return false;
        }
        return true;
    }

    @Override // com.bumptech.glide.request.c
    public boolean e() {
        boolean z;
        synchronized (this.f3228b) {
            z = this.e == RequestCoordinator.RequestState.CLEARED;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public boolean f(c cVar) {
        boolean z;
        synchronized (this.f3228b) {
            z = n() && (cVar.equals(this.f3229c) || this.e != RequestCoordinator.RequestState.SUCCESS);
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public RequestCoordinator g() {
        RequestCoordinator requestCoordinatorG;
        synchronized (this.f3228b) {
            RequestCoordinator requestCoordinator = this.f3227a;
            requestCoordinatorG = requestCoordinator != null ? requestCoordinator.g() : this;
        }
        return requestCoordinatorG;
    }

    @Override // com.bumptech.glide.request.c
    public void h() {
        synchronized (this.f3228b) {
            this.g = true;
            try {
                if (this.e != RequestCoordinator.RequestState.SUCCESS) {
                    RequestCoordinator.RequestState requestState = this.f;
                    RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.RUNNING;
                    if (requestState != requestState2) {
                        this.f = requestState2;
                        this.f3230d.h();
                    }
                }
                if (this.g) {
                    RequestCoordinator.RequestState requestState3 = this.e;
                    RequestCoordinator.RequestState requestState4 = RequestCoordinator.RequestState.RUNNING;
                    if (requestState3 != requestState4) {
                        this.e = requestState4;
                        this.f3229c.h();
                    }
                }
            } finally {
                this.g = false;
            }
        }
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public void i(c cVar) {
        synchronized (this.f3228b) {
            if (cVar.equals(this.f3230d)) {
                this.f = RequestCoordinator.RequestState.SUCCESS;
                return;
            }
            this.e = RequestCoordinator.RequestState.SUCCESS;
            RequestCoordinator requestCoordinator = this.f3227a;
            if (requestCoordinator != null) {
                requestCoordinator.i(this);
            }
            if (!this.f.isComplete()) {
                this.f3230d.clear();
            }
        }
    }

    @Override // com.bumptech.glide.request.c
    public boolean isRunning() {
        boolean z;
        synchronized (this.f3228b) {
            z = this.e == RequestCoordinator.RequestState.RUNNING;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.c
    public boolean j() {
        boolean z;
        synchronized (this.f3228b) {
            z = this.e == RequestCoordinator.RequestState.SUCCESS;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public boolean k(c cVar) {
        boolean z;
        synchronized (this.f3228b) {
            z = l() && cVar.equals(this.f3229c) && this.e != RequestCoordinator.RequestState.PAUSED;
        }
        return z;
    }

    public void o(c cVar, c cVar2) {
        this.f3229c = cVar;
        this.f3230d = cVar2;
    }

    @Override // com.bumptech.glide.request.c
    public void pause() {
        synchronized (this.f3228b) {
            if (!this.f.isComplete()) {
                this.f = RequestCoordinator.RequestState.PAUSED;
                this.f3230d.pause();
            }
            if (!this.e.isComplete()) {
                this.e = RequestCoordinator.RequestState.PAUSED;
                this.f3229c.pause();
            }
        }
    }
}
