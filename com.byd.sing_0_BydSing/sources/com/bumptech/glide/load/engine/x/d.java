package com.bumptech.glide.load.engine.x;

import com.bumptech.glide.load.engine.x.m;
import java.util.Queue;

/* JADX INFO: compiled from: BaseKeyPool.java */
/* JADX INFO: loaded from: classes.dex */
abstract class d<T extends m> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Queue<T> f2850a = com.bumptech.glide.m.k.e(20);

    d() {
    }

    abstract T a();

    T b() {
        T tPoll = this.f2850a.poll();
        return tPoll == null ? (T) a() : tPoll;
    }

    public void c(T t) {
        if (this.f2850a.size() < 20) {
            this.f2850a.offer(t);
        }
    }
}
