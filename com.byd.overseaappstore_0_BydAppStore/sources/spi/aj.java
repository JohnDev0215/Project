package spi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Pools;
import com.bumptech.glide.Registry;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import spi.ti;

/* JADX INFO: loaded from: classes.dex */
public final class aj {
    public static final c e = new c();
    public static final ti<Object, Object> f = new a();
    public final List<b<?, ?>> a;
    public final c b;
    public final Set<b<?, ?>> c;
    public final Pools.Pool<List<Throwable>> d;

    public static class a implements ti<Object, Object> {
        @Override // spi.ti
        public final boolean a(@NonNull Object obj) {
            return false;
        }

        @Override // spi.ti
        @Nullable
        public final ti.a<Object> b(@NonNull Object obj, int i, int i2, @NonNull lk lkVar) {
            return null;
        }
    }

    public static class b<Model, Data> {
        public final Class<Model> a;
        public final Class<Data> b;
        public final ui<? extends Model, ? extends Data> c;

        public b(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ui<? extends Model, ? extends Data> uiVar) {
            this.a = cls;
            this.b = cls2;
            this.c = uiVar;
        }

        public final boolean a(@NonNull Class<?> cls) {
            return this.a.isAssignableFrom(cls);
        }
    }

    public static class c {
    }

    public aj(@NonNull Pools.Pool<List<Throwable>> pool) {
        this(pool, e);
    }

    @VisibleForTesting
    public aj(@NonNull Pools.Pool<List<Throwable>> pool, @NonNull c cVar) {
        this.a = new ArrayList();
        this.c = new HashSet();
        this.d = pool;
        this.b = cVar;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final synchronized <Model, Data> void a(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ui<? extends Model, ? extends Data> uiVar) {
        b<?, ?> bVar = new b<>(cls, cls2, uiVar);
        List<b<?, ?>> list = this.a;
        list.add(list.size(), bVar);
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @NonNull
    public final synchronized <Model> List<ti<Model, ?>> b(@NonNull Class<Model> cls) {
        ArrayList arrayList;
        try {
            arrayList = new ArrayList();
            for (b<?, ?> bVar : this.a) {
                if (!this.c.contains(bVar) && bVar.a(cls)) {
                    this.c.add(bVar);
                    ti tiVarC = bVar.c.c(this);
                    Objects.requireNonNull(tiVarC, "Argument must not be null");
                    arrayList.add(tiVarC);
                    this.c.remove(bVar);
                }
            }
        } catch (Throwable th) {
            this.c.clear();
            throw th;
        }
        return arrayList;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.ArrayList, java.util.List<spi.aj$b<?, ?>>] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.util.HashSet, java.util.Set<spi.aj$b<?, ?>>] */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.util.HashSet, java.util.Set<spi.aj$b<?, ?>>] */
    /* JADX WARN: Type inference failed for: r6v7, types: [java.util.HashSet, java.util.Set<spi.aj$b<?, ?>>] */
    /* JADX WARN: Type inference failed for: r9v1, types: [java.util.HashSet, java.util.Set<spi.aj$b<?, ?>>] */
    @NonNull
    public final synchronized <Model, Data> ti<Model, Data> c(@NonNull Class<Model> cls, @NonNull Class<Data> cls2) {
        try {
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            for (b<?, ?> bVar : this.a) {
                if (this.c.contains(bVar)) {
                    z = true;
                } else if (bVar.a(cls) && bVar.b.isAssignableFrom(cls2)) {
                    this.c.add(bVar);
                    arrayList.add(d(bVar));
                    this.c.remove(bVar);
                }
            }
            if (arrayList.size() > 1) {
                c cVar = this.b;
                Pools.Pool<List<Throwable>> pool = this.d;
                Objects.requireNonNull(cVar);
                return new zi(arrayList, pool);
            }
            if (arrayList.size() == 1) {
                return (ti) arrayList.get(0);
            }
            if (z) {
                return (ti<Model, Data>) f;
            }
            throw new Registry.NoModelLoaderAvailableException((Class<?>) cls, (Class<?>) cls2);
        } catch (Throwable th) {
            this.c.clear();
            throw th;
        }
    }

    @NonNull
    public final <Model, Data> ti<Model, Data> d(@NonNull b<?, ?> bVar) {
        ti<Model, Data> tiVar = (ti<Model, Data>) bVar.c.c(this);
        Objects.requireNonNull(tiVar, "Argument must not be null");
        return tiVar;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.ArrayList, java.util.List<spi.aj$b<?, ?>>] */
    @NonNull
    public final synchronized List<Class<?>> e(@NonNull Class<?> cls) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (b bVar : this.a) {
            if (!arrayList.contains(bVar.b) && bVar.a(cls)) {
                arrayList.add(bVar.b);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.ArrayList, java.util.List<spi.aj$b<?, ?>>] */
    @NonNull
    public final synchronized List f(@NonNull Class cls) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (bVar.a(sd.class) && bVar.b.isAssignableFrom(cls)) {
                it.remove();
                arrayList.add(bVar.c);
            }
        }
        return arrayList;
    }
}
