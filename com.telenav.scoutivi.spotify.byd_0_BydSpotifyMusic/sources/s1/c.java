package s1;

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
    private final Map<String, a> f9464a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b f9465b = new b();

    /* JADX INFO: compiled from: DiskCacheWriteLocker.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Lock f9466a = new ReentrantLock();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f9467b;

        a() {
        }
    }

    /* JADX INFO: compiled from: DiskCacheWriteLocker.java */
    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Queue<a> f9468a = new ArrayDeque();

        b() {
        }

        a a() {
            a aVarPoll;
            synchronized (this.f9468a) {
                aVarPoll = this.f9468a.poll();
            }
            return aVarPoll == null ? new a() : aVarPoll;
        }

        void b(a aVar) {
            synchronized (this.f9468a) {
                if (this.f9468a.size() < 10) {
                    this.f9468a.offer(aVar);
                }
            }
        }
    }

    c() {
    }

    void a(String str) {
        a aVarA;
        synchronized (this) {
            aVarA = this.f9464a.get(str);
            if (aVarA == null) {
                aVarA = this.f9465b.a();
                this.f9464a.put(str, aVarA);
            }
            aVarA.f9467b++;
        }
        aVarA.f9466a.lock();
    }

    void b(String str) {
        a aVar;
        synchronized (this) {
            aVar = (a) k2.j.d(this.f9464a.get(str));
            int i7 = aVar.f9467b;
            if (i7 < 1) {
                throw new IllegalStateException("Cannot release a lock that is not held, safeKey: " + str + ", interestedThreads: " + aVar.f9467b);
            }
            int i8 = i7 - 1;
            aVar.f9467b = i8;
            if (i8 == 0) {
                a aVarRemove = this.f9464a.remove(str);
                if (!aVarRemove.equals(aVar)) {
                    throw new IllegalStateException("Removed the wrong lock, expected to remove: " + aVar + ", but actually removed: " + aVarRemove + ", safeKey: " + str);
                }
                this.f9465b.b(aVarRemove);
            }
        }
        aVar.f9466a.unlock();
    }
}
