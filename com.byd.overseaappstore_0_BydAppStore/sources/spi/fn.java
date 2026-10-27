package spi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class fn {
    public final List<a<?>> a = new ArrayList();

    public static final class a<T> {
        public final Class<T> a;
        public final en<T> b;

        public a(@NonNull Class<T> cls, @NonNull en<T> enVar) {
            this.a = cls;
            this.b = enVar;
        }
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Nullable
    public final synchronized <Z> en<Z> a(@NonNull Class<Z> cls) {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            a<?> aVar = this.a.get(i);
            if (aVar.a.isAssignableFrom((Class<?>) cls)) {
                return aVar.b;
            }
        }
        return null;
    }
}
