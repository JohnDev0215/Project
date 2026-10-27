package a.k;

import a.k.i;

/* JADX INFO: loaded from: classes.dex */
public class a implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public transient p f908a;

    @Override // a.k.i
    public void b(i.a aVar) {
        synchronized (this) {
            if (this.f908a == null) {
                this.f908a = new p();
            }
        }
        this.f908a.a(aVar);
    }

    @Override // a.k.i
    public void c(i.a aVar) {
        synchronized (this) {
            if (this.f908a == null) {
                return;
            }
            this.f908a.f(aVar);
        }
    }

    public void h() {
        synchronized (this) {
            if (this.f908a == null) {
                return;
            }
            this.f908a.c(this, 0, null);
        }
    }

    public void i(int i) {
        synchronized (this) {
            if (this.f908a == null) {
                return;
            }
            this.f908a.c(this, i, null);
        }
    }
}
