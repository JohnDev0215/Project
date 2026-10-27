package com.bumptech.glide.request;

import com.bumptech.glide.request.RequestCoordinator;

/* JADX INFO: compiled from: ThumbnailRequestCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements RequestCoordinator, c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final RequestCoordinator f3492a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Object f3493b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile c f3494c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile c f3495d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private RequestCoordinator.RequestState f3496e;
    private RequestCoordinator.RequestState f;
    private boolean g;

    public g(Object obj, RequestCoordinator requestCoordinator) {
        RequestCoordinator.RequestState requestState = RequestCoordinator.RequestState.CLEARED;
        this.f3496e = requestState;
        this.f = requestState;
        this.f3493b = obj;
        this.f3492a = requestCoordinator;
    }

    private boolean m() {
        RequestCoordinator requestCoordinator = this.f3492a;
        return requestCoordinator == null || requestCoordinator.l(this);
    }

    private boolean n() {
        RequestCoordinator requestCoordinator = this.f3492a;
        return requestCoordinator == null || requestCoordinator.k(this);
    }

    private boolean o() {
        RequestCoordinator requestCoordinator = this.f3492a;
        return requestCoordinator == null || requestCoordinator.a(this);
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public boolean a(c cVar) {
        boolean z;
        synchronized (this.f3493b) {
            z = o() && (cVar.equals(this.f3494c) || this.f3496e != RequestCoordinator.RequestState.SUCCESS);
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator, com.bumptech.glide.request.c
    public boolean b() {
        boolean z;
        synchronized (this.f3493b) {
            z = this.f3495d.b() || this.f3494c.b();
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public RequestCoordinator c() {
        RequestCoordinator requestCoordinatorC;
        synchronized (this.f3493b) {
            RequestCoordinator requestCoordinator = this.f3492a;
            requestCoordinatorC = requestCoordinator != null ? requestCoordinator.c() : this;
        }
        return requestCoordinatorC;
    }

    @Override // com.bumptech.glide.request.c
    public void clear() {
        synchronized (this.f3493b) {
            this.g = false;
            RequestCoordinator.RequestState requestState = RequestCoordinator.RequestState.CLEARED;
            this.f3496e = requestState;
            this.f = requestState;
            this.f3495d.clear();
            this.f3494c.clear();
        }
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public void d(c cVar) {
        synchronized (this.f3493b) {
            if (!cVar.equals(this.f3494c)) {
                this.f = RequestCoordinator.RequestState.FAILED;
                return;
            }
            this.f3496e = RequestCoordinator.RequestState.FAILED;
            RequestCoordinator requestCoordinator = this.f3492a;
            if (requestCoordinator != null) {
                requestCoordinator.d(this);
            }
        }
    }

    @Override // com.bumptech.glide.request.c
    public void e() {
        synchronized (this.f3493b) {
            if (!this.f.isComplete()) {
                this.f = RequestCoordinator.RequestState.PAUSED;
                this.f3495d.e();
            }
            if (!this.f3496e.isComplete()) {
                this.f3496e = RequestCoordinator.RequestState.PAUSED;
                this.f3494c.e();
            }
        }
    }

    @Override // com.bumptech.glide.request.c
    public void f() {
        synchronized (this.f3493b) {
            this.g = true;
            try {
                if (this.f3496e != RequestCoordinator.RequestState.SUCCESS) {
                    RequestCoordinator.RequestState requestState = this.f;
                    RequestCoordinator.RequestState requestState2 = RequestCoordinator.RequestState.RUNNING;
                    if (requestState != requestState2) {
                        this.f = requestState2;
                        this.f3495d.f();
                    }
                }
                if (this.g) {
                    RequestCoordinator.RequestState requestState3 = this.f3496e;
                    RequestCoordinator.RequestState requestState4 = RequestCoordinator.RequestState.RUNNING;
                    if (requestState3 != requestState4) {
                        this.f3496e = requestState4;
                        this.f3494c.f();
                    }
                }
            } finally {
                this.g = false;
            }
        }
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public void g(c cVar) {
        synchronized (this.f3493b) {
            if (cVar.equals(this.f3495d)) {
                this.f = RequestCoordinator.RequestState.SUCCESS;
                return;
            }
            this.f3496e = RequestCoordinator.RequestState.SUCCESS;
            RequestCoordinator requestCoordinator = this.f3492a;
            if (requestCoordinator != null) {
                requestCoordinator.g(this);
            }
            if (!this.f.isComplete()) {
                this.f3495d.clear();
            }
        }
    }

    @Override // com.bumptech.glide.request.c
    public boolean h(c cVar) {
        if (!(cVar instanceof g)) {
            return false;
        }
        g gVar = (g) cVar;
        if (this.f3494c == null) {
            if (gVar.f3494c != null) {
                return false;
            }
        } else if (!this.f3494c.h(gVar.f3494c)) {
            return false;
        }
        if (this.f3495d == null) {
            if (gVar.f3495d != null) {
                return false;
            }
        } else if (!this.f3495d.h(gVar.f3495d)) {
            return false;
        }
        return true;
    }

    @Override // com.bumptech.glide.request.c
    public boolean i() {
        boolean z;
        synchronized (this.f3493b) {
            z = this.f3496e == RequestCoordinator.RequestState.SUCCESS;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.c
    public boolean isRunning() {
        boolean z;
        synchronized (this.f3493b) {
            z = this.f3496e == RequestCoordinator.RequestState.RUNNING;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.c
    public boolean j() {
        boolean z;
        synchronized (this.f3493b) {
            z = this.f3496e == RequestCoordinator.RequestState.CLEARED;
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public boolean k(c cVar) {
        boolean z;
        synchronized (this.f3493b) {
            z = n() && cVar.equals(this.f3494c) && !b();
        }
        return z;
    }

    @Override // com.bumptech.glide.request.RequestCoordinator
    public boolean l(c cVar) {
        boolean z;
        synchronized (this.f3493b) {
            z = m() && cVar.equals(this.f3494c) && this.f3496e != RequestCoordinator.RequestState.PAUSED;
        }
        return z;
    }

    public void p(c cVar, c cVar2) {
        this.f3494c = cVar;
        this.f3495d = cVar2;
    }
}
