package spi;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public final class j6 {
    public final Map<String, a> a = new HashMap();
    public final b b = new b();

    public static class a {
        public final ReentrantLock a = new ReentrantLock();
        public int b;
    }

    public static class b {
        public final Queue<a> a = new ArrayDeque();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.HashMap, java.util.Map<java.lang.String, spi.j6$a>] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.util.HashMap, java.util.Map<java.lang.String, spi.j6$a>] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.util.ArrayDeque, java.util.Queue<spi.j6$a>] */
    /* JADX WARN: Type inference failed for: r6v9, types: [java.util.ArrayDeque, java.util.Queue<spi.j6$a>] */
    public final void a(String str) {
        a aVar;
        synchronized (this) {
            Object obj = this.a.get(str);
            Objects.requireNonNull(obj, "Argument must not be null");
            aVar = (a) obj;
            int i = aVar.b;
            if (i < 1) {
                throw new IllegalStateException("Cannot release a lock that is not held, safeKey: " + str + ", interestedThreads: " + aVar.b);
            }
            int i2 = i - 1;
            aVar.b = i2;
            if (i2 == 0) {
                a aVar2 = (a) this.a.remove(str);
                if (!aVar2.equals(aVar)) {
                    throw new IllegalStateException("Removed the wrong lock, expected to remove: " + aVar + ", but actually removed: " + aVar2 + ", safeKey: " + str);
                }
                b bVar = this.b;
                synchronized (bVar.a) {
                    if (bVar.a.size() < 10) {
                        bVar.a.offer(aVar2);
                    }
                }
            }
        }
        aVar.a.unlock();
    }
}
