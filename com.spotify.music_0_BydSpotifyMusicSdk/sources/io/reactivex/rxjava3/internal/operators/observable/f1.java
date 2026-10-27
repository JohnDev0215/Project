package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import java.util.Objects;
import p.u11;

/* JADX INFO: loaded from: classes.dex */
public final class f1 extends io.reactivex.rxjava3.internal.observers.a {
    public final io.reactivex.rxjava3.functions.o n;
    public final io.reactivex.rxjava3.functions.d o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object f77p;
    public boolean q;

    public f1(Observer observer, io.reactivex.rxjava3.functions.o oVar, io.reactivex.rxjava3.functions.d dVar) {
        super(observer);
        this.n = oVar;
        this.o = dVar;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.l) {
            return;
        }
        int i = this.m;
        Observer observer = this.c;
        if (i != 0) {
            observer.onNext(obj);
            return;
        }
        try {
            Object objApply = this.n.apply(obj);
            if (this.q) {
                io.reactivex.rxjava3.functions.d dVar = this.o;
                Object obj2 = this.f77p;
                ((u11) dVar).getClass();
                boolean zEquals = Objects.equals(obj2, objApply);
                this.f77p = objApply;
                if (zEquals) {
                    return;
                }
            } else {
                this.q = true;
                this.f77p = objApply;
            }
            observer.onNext(obj);
        } catch (Throwable th) {
            a(th);
        }
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        while (true) {
            Object objPoll = this.k.poll();
            if (objPoll == null) {
                return null;
            }
            Object objApply = this.n.apply(objPoll);
            if (!this.q) {
                this.q = true;
                this.f77p = objApply;
                return objPoll;
            }
            Object obj = this.f77p;
            ((u11) this.o).getClass();
            if (!Objects.equals(obj, objApply)) {
                this.f77p = objApply;
                return objPoll;
            }
            this.f77p = objApply;
        }
    }
}
