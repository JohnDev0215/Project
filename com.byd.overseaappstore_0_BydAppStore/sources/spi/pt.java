package spi;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class pt {
    public final List<a<?, ?>> a = new ArrayList();

    public static final class a<Z, R> {
        public final Class<Z> a;
        public final Class<R> b;
        public final in<Z, R> c;

        public a(@NonNull Class<Z> cls, @NonNull Class<R> cls2, @NonNull in<Z, R> inVar) {
            this.a = cls;
            this.b = cls2;
            this.c = inVar;
        }
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.ArrayList, java.util.List<spi.pt$a<?, ?>>] */
    @NonNull
    public final synchronized <Z, R> List<Class<R>> a(@NonNull Class<Z> cls, @NonNull Class<R> cls2) {
        ArrayList arrayList = new ArrayList();
        if (cls2.isAssignableFrom(cls)) {
            arrayList.add(cls2);
            return arrayList;
        }
        for (a aVar : this.a) {
            if ((aVar.a.isAssignableFrom(cls) && cls2.isAssignableFrom(aVar.b)) && !arrayList.contains(aVar.b)) {
                arrayList.add(aVar.b);
            }
        }
        return arrayList;
    }
}
