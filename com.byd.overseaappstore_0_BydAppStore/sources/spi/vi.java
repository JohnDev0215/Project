package spi;

import androidx.annotation.NonNull;
import androidx.core.util.Pools;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class vi {
    public final aj a;
    public final a b;

    public static class a {
        public final Map<Class<?>, C0143a<?>> a = new HashMap();

        /* JADX INFO: renamed from: spi.vi$a$a, reason: collision with other inner class name */
        public static class C0143a<Model> {
            public final List<ti<Model, ?>> a;

            public C0143a(List<ti<Model, ?>> list) {
                this.a = list;
            }
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public final <Model> void a(Class<Model> cls, List<ti<Model, ?>> list) {
            if (this.a.put((Class<?>) cls, new C0143a<>(list)) == null) {
                return;
            }
            throw new IllegalStateException("Already cached loaders for model: " + cls);
        }
    }

    public vi(@NonNull Pools.Pool<List<Throwable>> pool) {
        this(new aj(pool));
    }

    private vi(@NonNull aj ajVar) {
        this.b = new a();
        this.a = ajVar;
    }
}
