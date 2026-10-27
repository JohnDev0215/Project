package com.bumptech.glide.load.engine.y;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: DiskCacheWriteLocker.java */
/* JADX INFO: loaded from: classes.dex */
final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<String, a> f2876a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b f2877b = new b();

    /* JADX INFO: compiled from: DiskCacheWriteLocker.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Lock f2878a = new ReentrantLock();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f2879b;

        a() {
        }
    }

    /* JADX INFO: compiled from: DiskCacheWriteLocker.java */
    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Queue<a> f2880a = new ArrayDeque();

        b() {
        }

        a a() {
            a aVarPoll;
            synchronized (this.f2880a) {
                aVarPoll = this.f2880a.poll();
            }
            return aVarPoll == null ? new a() : aVarPoll;
        }

        void b(a aVar) {
            synchronized (this.f2880a) {
                if (this.f2880a.size() < 10) {
                    this.f2880a.offer(aVar);
                }
            }
        }
    }

    c() {
    }

    void a(String str) {
        a aVarA;
        synchronized (this) {
            aVarA = this.f2876a.get(str);
            if (aVarA == null) {
                aVarA = this.f2877b.a();
                this.f2876a.put(str, aVarA);
            }
            aVarA.f2879b++;
        }
        aVarA.f2878a.lock();
    }

    void b(String str) {
        a aVar;
        synchronized (this) {
            aVar = (a) com.bumptech.glide.m.j.d(this.f2876a.get(str));
            int i = aVar.f2879b;
            if (i < 1) {
                throw new IllegalStateException("Cannot release a lock that is not held, safeKey: " + str + ", interestedThreads: " + aVar.f2879b);
            }
            int i2 = i - 1;
            aVar.f2879b = i2;
            if (i2 == 0) {
                a aVarRemove = this.f2876a.remove(str);
                if (!aVarRemove.equals(aVar)) {
                    throw new IllegalStateException("Removed the wrong lock, expected to remove: " + aVar + ", but actually removed: " + aVarRemove + ", safeKey: " + str);
                }
                this.f2877b.b(aVarRemove);
            }
        }
        aVar.f2878a.unlock();
    }
}
