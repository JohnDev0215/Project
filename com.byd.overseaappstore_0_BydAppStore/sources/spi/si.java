package spi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
public final class si<A, B> {
    public final gh<b<A>, B> a;

    public class a extends gh<b<A>, B> {
        public a(si siVar, long j) {
            super(j);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // spi.gh
        public final void h(@NonNull Object obj, @Nullable Object obj2) {
            b<?> bVar = (b) obj;
            Objects.requireNonNull(bVar);
            Queue<b<?>> queue = b.d;
            synchronized (queue) {
                queue.offer(bVar);
            }
        }
    }

    @VisibleForTesting
    public static final class b<A> {
        public static final Queue<b<?>> d;
        public int a;
        public int b;
        public A c;

        static {
            char[] cArr = tu.a;
            d = new ArrayDeque(0);
        }

        private b() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [java.util.ArrayDeque, java.util.Queue<spi.si$b<?>>] */
        public static b a(Object obj) {
            b bVar;
            ?? r0 = d;
            synchronized (r0) {
                bVar = (b) r0.poll();
            }
            if (bVar == null) {
                bVar = new b();
            }
            bVar.c = obj;
            bVar.b = 0;
            bVar.a = 0;
            return bVar;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.b == bVar.b && this.a == bVar.a && this.c.equals(bVar.c);
        }

        public final int hashCode() {
            return this.c.hashCode() + (((this.a * 31) + this.b) * 31);
        }
    }

    public si() {
        this(250L);
    }

    public si(long j) {
        this.a = new a(this, j);
    }
}
