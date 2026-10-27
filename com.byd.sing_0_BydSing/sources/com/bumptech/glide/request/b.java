package com.bumptech.glide.request;

import com.bumptech.glide.request.RequestCoordinator;

/* JADX INFO: compiled from: ErrorRequestCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements RequestCoordinator, c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f3223a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final RequestCoordinator f3224b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile c f3225c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile c f3226d;
    private RequestCoordinator.RequestState e;
    private RequestCoordinator.RequestState f;

    public b(Object obj, RequestCoordinator requestCoordinator) {
        RequestCoordinator.RequestState requestState = RequestCoordinator.RequestState.CLEARED;
        this.e = requestState;
        this.f = requestState;
        this.f3223a = obj;
        this.f3224b = requestCoordinator;
    }

    private boolean l(c cVar) {
        return cVar.equals(this.f3225c) || (this.e == RequestCoordinator.RequestState.FAILED && cVar.equals(this.f3226d));
    }

    private boolean m() {
        RequestCoordinator requestCoordinator = this.f3224b;
        return requestCoordinator == null || requestCoordinator.k(this);
    }

    private boolean n() {
        RequestCoordinator requestCoordinator = this.f3224b;
        return requestCoordinator == null || requestCoordinator.c(this);
    }

    private boolean o() {
        RequestCoordinator requestCoordinator = this.f3224b;
        return requestCoordinator == null || requestCoordinator.f(this);
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public void a(c cVar) {
        synchronized (this.f3223a) {
            if (cVar.equals(this.f3226d)) {
                this.f = RequestCoordinator.RequestState.FAILED;
                RequestCoordinator requestCoordinator = this.f3224b;
                if (requestCoordinator != null) {
                    requestCoordinator.a(this);
                }
                return;
            }
            this.e = RequestCoordinator.RequestState.FAILED;
            RequestCoordinator.RequestState requestState = this.f;
            RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.RUNNING;
            if (requestState != requestState2) {
                this.f = requestState2;
                this.f3226d.h();
            }
        }
    }

    @Override // com.bumptech.glide.request.RequestCoordinator, com.bumptech.glide.request.c
    public boolean b() {
        boolean z;
        synchronized (this.f3223a) {
            z = this.f3225c.b() || this.f3226d.b();
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public boolean c(c cVar) {
        boolean z;
        synchronized (this.f3223a) {
            z = n() && l(cVar);
        }
        return z;
    }

    @Override // com.bumptech.glide.request.c
    public void clear() {
        synchronized (this.f3223a) {
            RequestCoordinator.RequestState requestState = RequestCoordinator.RequestState.CLEARED;
            this.e = requestState;
            this.f3225c.clear();
            if (this.f != requestState) {
                this.f = requestState;
                this.f3226d.clear();
            }
        }
    }

    @Override // com.bumptech.glide.request.c
    public boolean d(c cVar) {
        if (!(cVar instanceof b)) {
            return false;
        }
        b bVar = (b) cVar;
        return this.f3225c.d(bVar.f3225c) && this.f3226d.d(bVar.f3226d);
    }

    @Override // com.bumptech.glide.request.c
    public boolean e() {
        boolean z;
        synchronized (this.f3223a) {
            RequestCoordinator.RequestState requestState = this.e;
            RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.CLEARED;
            z = requestState == requestState2 && this.f == requestState2;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public boolean f(c cVar) {
        boolean z;
        synchronized (this.f3223a) {
            z = o() && l(cVar);
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public RequestCoordinator g() {
        RequestCoordinator requestCoordinatorG;
        synchronized (this.f3223a) {
            RequestCoordinator requestCoordinator = this.f3224b;
            requestCoordinatorG = requestCoordinator != null ? requestCoordinator.g() : this;
        }
        return requestCoordinatorG;
    }

    @Override // com.bumptech.glide.request.c
    public void h() {
        synchronized (this.f3223a) {
            RequestCoordinator.RequestState requestState = this.e;
            RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.RUNNING;
            if (requestState != requestState2) {
                this.e = requestState2;
                this.f3225c.h();
            }
        }
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public void i(c cVar) {
        synchronized (this.f3223a) {
            if (cVar.equals(this.f3225c)) {
                this.e = RequestCoordinator.RequestState.SUCCESS;
            } else if (cVar.equals(this.f3226d)) {
                this.f = RequestCoordinator.RequestState.SUCCESS;
            }
            RequestCoordinator requestCoordinator = this.f3224b;
            if (requestCoordinator != null) {
                requestCoordinator.i(this);
            }
        }
    }

    @Override // com.bumptech.glide.request.c
    public boolean isRunning() {
        boolean z;
        synchronized (this.f3223a) {
            RequestCoordinator.RequestState requestState = this.e;
            RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.RUNNING;
            z = requestState == requestState2 || this.f == requestState2;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.c
    public boolean j() {
        boolean z;
        synchronized (this.f3223a) {
            RequestCoordinator.RequestState requestState = this.e;
            RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.SUCCESS;
            z = requestState == requestState2 || this.f == requestState2;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public boolean k(c cVar) {
        boolean z;
        synchronized (this.f3223a) {
            z = m() && l(cVar);
        }
        return z;
    }

    public void p(c cVar, c cVar2) {
        this.f3225c = cVar;
        this.f3226d = cVar2;
    }

    @Override // com.bumptech.glide.request.c
    public void pause() {
        synchronized (this.f3223a) {
            RequestCoordinator.RequestState requestState = this.e;
            RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.RUNNING;
            if (requestState == requestState2) {
                this.e = RequestCoordinator.RequestState.PAUSED;
                this.f3225c.pause();
            }
            if (this.f == requestState2) {
                this.f = RequestCoordinator.RequestState.PAUSED;
                this.f3226d.pause();
            }
        }
    }
}
