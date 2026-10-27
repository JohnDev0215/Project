package com.byd.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: TaskQueue.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AtomicInteger f3223a = new AtomicInteger();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final BlockingQueue<b> f3224b = new PriorityBlockingQueue();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final e[] f3225c;

    public f(int i) {
        this.f3225c = new e[i];
    }

    public <T extends b> int a(T t) {
        int size;
        synchronized (this.f3224b) {
            if (!this.f3224b.contains(t)) {
                t.f(this.f3223a.incrementAndGet());
                this.f3224b.add(t);
            }
            size = this.f3224b.size();
        }
        return size;
    }

    public void b() {
        c();
        int i = 0;
        while (true) {
            e[] eVarArr = this.f3225c;
            if (i >= eVarArr.length) {
                return;
            }
            eVarArr[i] = new e(this.f3224b);
            this.f3225c[i].start();
            i++;
        }
    }

    public void c() {
        for (e eVar : this.f3225c) {
            if (eVar != null) {
                eVar.a();
            }
        }
    }
}
