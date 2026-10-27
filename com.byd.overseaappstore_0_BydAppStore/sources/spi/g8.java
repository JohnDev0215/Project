package spi;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class g8 {
    public final List<a<?>> a = new ArrayList();

    public static final class a<T> {
        public final Class<T> a;
        public final f8<T> b;

        public a(@NonNull Class<T> cls, @NonNull f8<T> f8Var) {
            this.a = cls;
            this.b = f8Var;
        }
    }
}
