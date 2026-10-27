package com.bumptech.glide.load.n.a0;

import com.bumptech.glide.load.n.a0.m;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
abstract class d<T extends m> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Queue<T> f2036a = com.bumptech.glide.t.k.e(20);

    d() {
    }

    abstract T a();

    T b() {
        T tPoll = this.f2036a.poll();
        return tPoll == null ? (T) a() : tPoll;
    }

    public void c(T t) {
        if (this.f2036a.size() < 20) {
            this.f2036a.offer(t);
        }
    }
}
