package spi;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.util.Pools;

/* JADX INFO: loaded from: classes.dex */
public final class ta {
    public static final e<Object> a = new a();

    public class a implements e<Object> {
        @Override // spi.ta.e
        public final void a(@NonNull Object obj) {
        }
    }

    public interface b<T> {
        T a();
    }

    public static final class c<T> implements Pools.Pool<T> {
        public final b<T> a;
        public final e<T> b;
        public final Pools.Pool<T> c;

        public c(@NonNull Pools.Pool<T> pool, @NonNull b<T> bVar, @NonNull e<T> eVar) {
            this.c = pool;
            this.a = bVar;
            this.b = eVar;
        }

        @Override // androidx.core.util.Pools.Pool
        public final T acquire() {
            T tAcquire = this.c.acquire();
            if (tAcquire == null) {
                tAcquire = this.a.a();
                if (Log.isLoggable("FactoryPools", 2)) {
                    StringBuilder sbK = spi.e.k("Created new ");
                    sbK.append(tAcquire.getClass());
                    Log.v("FactoryPools", sbK.toString());
                }
            }
            if (tAcquire instanceof d) {
                ((d) tAcquire).b().a(false);
            }
            return tAcquire;
        }

        @Override // androidx.core.util.Pools.Pool
        public final boolean release(@NonNull T t) {
            if (t instanceof d) {
                ((d) t).b().a(true);
            }
            this.b.a(t);
            return this.c.release(t);
        }
    }

    public interface d {
        @NonNull
        es b();
    }

    public interface e<T> {
        void a(@NonNull T t);
    }

    private ta() {
    }

    @NonNull
    public static <T extends d> Pools.Pool<T> a(int i, @NonNull b<T> bVar) {
        return new c(new Pools.SynchronizedPool(i), bVar, a);
    }
}
