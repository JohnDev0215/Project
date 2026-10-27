package com.bumptech.glide.load.engine.x;

import com.bumptech.glide.load.engine.x.m;
import java.util.Queue;

/* JADX INFO: compiled from: BaseKeyPool.java */
/* JADX INFO: loaded from: classes.dex */
abstract class d<T extends m> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Queue<T> f3097a = com.bumptech.glide.o.k.e(20);

    d() {
    }

    abstract T a();

    T b() {
        T tPoll = this.f3097a.poll();
        return tPoll == null ? (T) a() : tPoll;
    }

    public void c(T t) {
        if (this.f3097a.size() < 20) {
            this.f3097a.offer(t);
        }
    }
}
