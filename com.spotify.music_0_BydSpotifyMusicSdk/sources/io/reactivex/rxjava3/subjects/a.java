package io.reactivex.rxjava3.subjects;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import p.ab;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Disposable, io.reactivex.rxjava3.internal.util.a {
    public final Observer c;
    public final b e;
    public boolean k;
    public boolean l;
    public ab m;
    public boolean n;
    public volatile boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f85p;

    public a(Observer observer, b bVar) {
        this.c = observer;
        this.e = bVar;
    }

    public final void a(long j, Object obj) {
        if (this.o) {
            return;
        }
        if (!this.n) {
            synchronized (this) {
                try {
                    if (this.o) {
                        return;
                    }
                    if (this.f85p == j) {
                        return;
                    }
                    if (this.l) {
                        ab abVar = this.m;
                        if (abVar == null) {
                            abVar = new ab(1);
                            this.m = abVar;
                        }
                        abVar.b(obj);
                        return;
                    }
                    this.k = true;
                    this.n = true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        test(obj);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.o) {
            return;
        }
        this.o = true;
        this.e.b(this);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.o;
    }

    @Override // io.reactivex.rxjava3.functions.p
    public final boolean test(Object obj) {
        return this.o || NotificationLite.a(this.c, obj);
    }
}
