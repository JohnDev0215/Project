package spi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class gh<T, Y> {
    public final Map<T, a<Y>> a = new LinkedHashMap(100, 0.75f, true);
    public long b;
    public long c;

    public static final class a<Y> {
        public final Y a;
        public final int b;

        public a(Y y, int i) {
            this.a = y;
            this.b = i;
        }
    }

    public gh(long j) {
        this.b = j;
    }

    public final void b() {
        j(0L);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public /* bridge */ /* synthetic */ wm e(@NonNull ig igVar, @Nullable wm wmVar) {
        return (wm) i(igVar, wmVar);
    }

    @Nullable
    public final synchronized Y f(@NonNull T t) {
        a aVar;
        aVar = (a) this.a.get(t);
        return aVar != null ? aVar.a : null;
    }

    public int g(@Nullable Y y) {
        return 1;
    }

    public void h(@NonNull T t, @Nullable Y y) {
    }

    @Nullable
    public final synchronized Y i(@NonNull T t, @Nullable Y y) {
        int iG = g(y);
        long j = iG;
        if (j >= this.b) {
            h(t, y);
            return null;
        }
        if (y != null) {
            this.c += j;
        }
        a<Y> aVarPut = this.a.put(t, y == null ? null : new a<>(y, iG));
        if (aVarPut != null) {
            this.c -= (long) aVarPut.b;
            if (!aVarPut.a.equals(y)) {
                h(t, aVarPut.a);
            }
        }
        j(this.b);
        return aVarPut != null ? aVarPut.a : null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final synchronized void j(long j) {
        while (this.c > j) {
            Iterator it = this.a.entrySet().iterator();
            Map.Entry entry = (Map.Entry) it.next();
            a aVar = (a) entry.getValue();
            this.c -= (long) aVar.b;
            Object key = entry.getKey();
            it.remove();
            h(key, aVar.a);
        }
    }
}
