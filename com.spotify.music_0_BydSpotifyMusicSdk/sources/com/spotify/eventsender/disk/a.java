package com.spotify.eventsender.disk;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.functions.k;
import io.reactivex.rxjava3.internal.operators.completable.c;
import io.reactivex.rxjava3.internal.operators.completable.e;
import io.reactivex.rxjava3.internal.operators.completable.j;
import io.reactivex.rxjava3.internal.operators.completable.m;
import io.reactivex.rxjava3.internal.operators.flowable.i0;
import io.reactivex.rxjava3.internal.operators.flowable.v0;
import io.reactivex.rxjava3.internal.operators.flowable.z;
import io.reactivex.rxjava3.internal.operators.mixed.b;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import p.cj0;
import p.dj0;
import p.du;
import p.ej0;
import p.fj0;
import p.ic2;
import p.lq2;
import p.lu;
import p.qo;
import p.ry0;
import p.ul;
import p.ux1;
import p.ux2;
import p.v03;
import p.wc1;
import p.y3;
import p.zb0;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public final Set a;
    public final y3 b;
    public final wc1 c;
    public final Observable d;
    public final long e;
    public final ul f;
    public Disposable g;
    public final io.reactivex.rxjava3.disposables.a h;

    public a(Set set, y3 y3Var, wc1 wc1Var, Observable observable) {
        v03.j(set, "diskOperations");
        v03.j(wc1Var, "logger");
        this.a = set;
        this.b = y3Var;
        this.c = wc1Var;
        this.d = observable;
        this.e = 30L;
        this.f = new ul();
        this.g = b();
        this.h = new io.reactivex.rxjava3.disposables.a();
    }

    public static final c a(final a aVar) {
        aVar.getClass();
        return d(aVar.a, new ry0() { // from class: com.spotify.eventsender.disk.EventSenderDiskOperationsSchedulerImpl$writeToDiskCompletable$1
            {
                super(1);
            }

            @Override // p.ry0
            public final Object invoke(Object obj) throws FileNotFoundException {
                cj0 cj0Var = (cj0) obj;
                v03.j(cj0Var, "operation");
                y3 y3Var = this.this$0.b;
                String strB = cj0Var.b();
                y3Var.getClass();
                v03.j(strB, "fileName");
                FileOutputStream fileOutputStreamOpenFileOutput = y3Var.a.openFileOutput(strB, 0);
                v03.i(fileOutputStreamOpenFileOutput, "context.openFileOutput(f…me, Context.MODE_PRIVATE)");
                try {
                    cj0Var.e(fileOutputStreamOpenFileOutput);
                    ux2.e(fileOutputStreamOpenFileOutput, null);
                    return lq2.a;
                } finally {
                }
            }
        });
    }

    public static c d(Set set, ry0 ry0Var) {
        ArrayList arrayList = new ArrayList(lu.W(set));
        Iterator it = set.iterator();
        while (it.hasNext()) {
            arrayList.add(new e(1, new ic2(ry0Var, 1, (cj0) it.next())));
        }
        Completable[] completableArr = (Completable[]) arrayList.toArray(new Completable[0]);
        CompletableSource[] completableSourceArr = (CompletableSource[]) Arrays.copyOf(completableArr, completableArr.length);
        Objects.requireNonNull(completableSourceArr, "sources is null");
        return new c(completableSourceArr, 2);
    }

    public final Disposable b() {
        c cVarD = d(this.a, new ry0() { // from class: com.spotify.eventsender.disk.EventSenderDiskOperationsSchedulerImpl$readFromDiskCompletable$1
            {
                super(1);
            }

            @Override // p.ry0
            public final Object invoke(Object obj) throws FileNotFoundException {
                cj0 cj0Var = (cj0) obj;
                v03.j(cj0Var, "operation");
                y3 y3Var = this.this$0.b;
                String strB = cj0Var.b();
                y3Var.getClass();
                v03.j(strB, "fileName");
                FileInputStream fileInputStreamOpenFileInput = y3Var.a.openFileInput(strB);
                v03.i(fileInputStreamOpenFileInput, "context.openFileInput(fileName)");
                try {
                    cj0Var.c(fileInputStreamOpenFileInput);
                    ux2.e(fileInputStreamOpenFileInput, null);
                    return lq2.a;
                } finally {
                }
            }
        });
        Scheduler scheduler = io.reactivex.rxjava3.schedulers.e.c;
        Objects.requireNonNull(scheduler, "scheduler is null");
        int i = 0;
        Disposable disposableSubscribe = new j(new j(cVarD, scheduler, 1), scheduler, 0).subscribe(new dj0(this, i), new ej0(this, i));
        v03.i(disposableSubscribe, "private fun readDataFrom…)\n            }\n        }");
        return disposableSubscribe;
    }

    public final void c() {
        io.reactivex.rxjava3.disposables.a aVar = this.h;
        aVar.d();
        boolean zIsDisposed = this.g.isDisposed();
        ul ulVar = this.f;
        if (zIsDisposed && ulVar.c.get() == null) {
            this.g.dispose();
            this.g = b();
        }
        Observable observableStartWithItem = this.d.filter(zb0.m).map(fj0.c).startWithItem(lq2.a);
        long j = this.e;
        Flowable flowable = Observable.combineLatest(ulVar, Observable.interval(j, j, TimeUnit.SECONDS, io.reactivex.rxjava3.schedulers.e.b).startWithItem(0L), observableStartWithItem, qo.n).toFlowable(BackpressureStrategy.c);
        flowable.getClass();
        int i = 1;
        i0 i0Var = new i0(flowable, 1L, 1);
        Scheduler scheduler = io.reactivex.rxjava3.schedulers.e.c;
        Objects.requireNonNull(scheduler, "scheduler is null");
        v0 v0Var = new v0(i0Var, scheduler);
        int i2 = Flowable.c;
        k.c(i2, "bufferSize");
        k.c(2, "prefetch");
        CompletableSource bVar = new b(new z(v0Var, scheduler, i2), new ux1(7, this), ErrorMode.c, 2, 0);
        aVar.c(new e(3, (bVar instanceof io.reactivex.rxjava3.internal.fuseable.c ? ((io.reactivex.rxjava3.internal.fuseable.c) bVar).b() : new m(bVar)).l(Long.MAX_VALUE, new du(i, this))).subscribe(new dj0(this, i), new ej0(this, i)));
    }
}
