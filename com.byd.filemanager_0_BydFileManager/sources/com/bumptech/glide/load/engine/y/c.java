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
    private final Map<String, a> f3126a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b f3127b = new b();

    /* JADX INFO: compiled from: DiskCacheWriteLocker.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Lock f3128a = new ReentrantLock();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f3129b;

        a() {
        }
    }

    /* JADX INFO: compiled from: DiskCacheWriteLocker.java */
    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Queue<a> f3130a = new ArrayDeque();

        b() {
        }

        a a() {
            a aVarPoll;
            synchronized (this.f3130a) {
                aVarPoll = this.f3130a.poll();
            }
            return aVarPoll == null ? new a() : aVarPoll;
        }

        void b(a aVar) {
            synchronized (this.f3130a) {
                if (this.f3130a.size() < 10) {
                    this.f3130a.offer(aVar);
                }
            }
        }
    }

    c() {
    }

    void a(String str) {
        a aVarA;
        synchronized (this) {
            aVarA = this.f3126a.get(str);
            if (aVarA == null) {
                aVarA = this.f3127b.a();
                this.f3126a.put(str, aVarA);
            }
            aVarA.f3129b++;
        }
        aVarA.f3128a.lock();
    }

    void b(String str) {
        a aVar;
        synchronized (this) {
            aVar = (a) com.bumptech.glide.o.j.d(this.f3126a.get(str));
            int i = aVar.f3129b;
            if (i < 1) {
                throw new IllegalStateException("Cannot release a lock that is not held, safeKey: " + str + ", interestedThreads: " + aVar.f3129b);
            }
            int i2 = i - 1;
            aVar.f3129b = i2;
            if (i2 == 0) {
                a aVarRemove = this.f3126a.remove(str);
                if (!aVarRemove.equals(aVar)) {
                    throw new IllegalStateException("Removed the wrong lock, expected to remove: " + aVar + ", but actually removed: " + aVarRemove + ", safeKey: " + str);
                }
                this.f3127b.b(aVarRemove);
            }
        }
        aVar.f3128a.unlock();
    }
}
