package com.bumptech.glide.load.n.b0;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<String, a> f2062a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b f2063b = new b();

    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Lock f2064a = new ReentrantLock();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f2065b;

        a() {
        }
    }

    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Queue<a> f2066a = new ArrayDeque();

        b() {
        }

        a a() {
            a aVarPoll;
            synchronized (this.f2066a) {
                aVarPoll = this.f2066a.poll();
            }
            return aVarPoll == null ? new a() : aVarPoll;
        }

        void b(a aVar) {
            synchronized (this.f2066a) {
                if (this.f2066a.size() < 10) {
                    this.f2066a.offer(aVar);
                }
            }
        }
    }

    c() {
    }

    void a(String str) {
        a aVarA;
        synchronized (this) {
            aVarA = this.f2062a.get(str);
            if (aVarA == null) {
                aVarA = this.f2063b.a();
                this.f2062a.put(str, aVarA);
            }
            aVarA.f2065b++;
        }
        aVarA.f2064a.lock();
    }

    void b(String str) {
        a aVar;
        synchronized (this) {
            aVar = (a) com.bumptech.glide.t.j.d(this.f2062a.get(str));
            int i = aVar.f2065b;
            if (i < 1) {
                throw new IllegalStateException("Cannot release a lock that is not held, safeKey: " + str + ", interestedThreads: " + aVar.f2065b);
            }
            int i2 = i - 1;
            aVar.f2065b = i2;
            if (i2 == 0) {
                a aVarRemove = this.f2062a.remove(str);
                if (!aVarRemove.equals(aVar)) {
                    throw new IllegalStateException("Removed the wrong lock, expected to remove: " + aVar + ", but actually removed: " + aVarRemove + ", safeKey: " + str);
                }
                this.f2063b.b(aVarRemove);
            }
        }
        aVar.f2064a.unlock();
    }
}
