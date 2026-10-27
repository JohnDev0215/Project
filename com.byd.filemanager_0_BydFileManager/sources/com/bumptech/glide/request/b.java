package com.bumptech.glide.request;

import com.bumptech.glide.request.RequestCoordinator;

/* JADX INFO: compiled from: ErrorRequestCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements RequestCoordinator, c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f3487a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final RequestCoordinator f3488b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile c f3489c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile c f3490d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private RequestCoordinator.RequestState f3491e;
    private RequestCoordinator.RequestState f;

    public b(Object obj, RequestCoordinator requestCoordinator) {
        RequestCoordinator.RequestState requestState = RequestCoordinator.RequestState.CLEARED;
        this.f3491e = requestState;
        this.f = requestState;
        this.f3487a = obj;
        this.f3488b = requestCoordinator;
    }

    private boolean m(c cVar) {
        return cVar.equals(this.f3489c) || (this.f3491e == RequestCoordinator.RequestState.FAILED && cVar.equals(this.f3490d));
    }

    private boolean n() {
        RequestCoordinator requestCoordinator = this.f3488b;
        return requestCoordinator == null || requestCoordinator.l(this);
    }

    private boolean o() {
        RequestCoordinator requestCoordinator = this.f3488b;
        return requestCoordinator == null || requestCoordinator.k(this);
    }

    private boolean p() {
        RequestCoordinator requestCoordinator = this.f3488b;
        return requestCoordinator == null || requestCoordinator.a(this);
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public boolean a(c cVar) {
        boolean z;
        synchronized (this.f3487a) {
            z = p() && m(cVar);
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator, com.bumptech.glide.request.c
    public boolean b() {
        boolean z;
        synchronized (this.f3487a) {
            z = this.f3489c.b() || this.f3490d.b();
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public RequestCoordinator c() {
        RequestCoordinator requestCoordinatorC;
        synchronized (this.f3487a) {
            RequestCoordinator requestCoordinator = this.f3488b;
            requestCoordinatorC = requestCoordinator != null ? requestCoordinator.c() : this;
        }
        return requestCoordinatorC;
    }

    @Override // com.bumptech.glide.request.c
    public void clear() {
        synchronized (this.f3487a) {
            RequestCoordinator.RequestState requestState = RequestCoordinator.RequestState.CLEARED;
            this.f3491e = requestState;
            this.f3489c.clear();
            if (this.f != requestState) {
                this.f = requestState;
                this.f3490d.clear();
            }
        }
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public void d(c cVar) {
        synchronized (this.f3487a) {
            if (cVar.equals(this.f3490d)) {
                this.f = RequestCoordinator.RequestState.FAILED;
                RequestCoordinator requestCoordinator = this.f3488b;
                if (requestCoordinator != null) {
                    requestCoordinator.d(this);
                }
                return;
            }
            this.f3491e = RequestCoordinator.RequestState.FAILED;
            RequestCoordinator.RequestState requestState = this.f;
            RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.RUNNING;
            if (requestState != requestState2) {
                this.f = requestState2;
                this.f3490d.f();
            }
        }
    }

    @Override // com.bumptech.glide.request.c
    public void e() {
        synchronized (this.f3487a) {
            RequestCoordinator.RequestState requestState = this.f3491e;
            RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.RUNNING;
            if (requestState == requestState2) {
                this.f3491e = RequestCoordinator.RequestState.PAUSED;
                this.f3489c.e();
            }
            if (this.f == requestState2) {
                this.f = RequestCoordinator.RequestState.PAUSED;
                this.f3490d.e();
            }
        }
    }

    @Override // com.bumptech.glide.request.c
    public void f() {
        synchronized (this.f3487a) {
            RequestCoordinator.RequestState requestState = this.f3491e;
            RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.RUNNING;
            if (requestState != requestState2) {
                this.f3491e = requestState2;
                this.f3489c.f();
            }
        }
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public void g(c cVar) {
        synchronized (this.f3487a) {
            if (cVar.equals(this.f3489c)) {
                this.f3491e = RequestCoordinator.RequestState.SUCCESS;
            } else if (cVar.equals(this.f3490d)) {
                this.f = RequestCoordinator.RequestState.SUCCESS;
            }
            RequestCoordinator requestCoordinator = this.f3488b;
            if (requestCoordinator != null) {
                requestCoordinator.g(this);
            }
        }
    }

    @Override // com.bumptech.glide.request.c
    public boolean h(c cVar) {
        if (!(cVar instanceof b)) {
            return false;
        }
        b bVar = (b) cVar;
        return this.f3489c.h(bVar.f3489c) && this.f3490d.h(bVar.f3490d);
    }

    @Override // com.bumptech.glide.request.c
    public boolean i() {
        boolean z;
        synchronized (this.f3487a) {
            RequestCoordinator.RequestState requestState = this.f3491e;
            RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.SUCCESS;
            z = requestState == requestState2 || this.f == requestState2;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.c
    public boolean isRunning() {
        boolean z;
        synchronized (this.f3487a) {
            RequestCoordinator.RequestState requestState = this.f3491e;
            RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.RUNNING;
            z = requestState == requestState2 || this.f == requestState2;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.c
    public boolean j() {
        boolean z;
        synchronized (this.f3487a) {
            RequestCoordinator.RequestState requestState = this.f3491e;
            RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.CLEARED;
            z = requestState == requestState2 && this.f == requestState2;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public boolean k(c cVar) {
        boolean z;
        synchronized (this.f3487a) {
            z = o() && m(cVar);
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public boolean l(c cVar) {
        boolean z;
        synchronized (this.f3487a) {
            z = n() && m(cVar);
        }
        return z;
    }

    public void q(c cVar, c cVar2) {
        this.f3489c = cVar;
        this.f3490d = cVar2;
    }
}
