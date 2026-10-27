package spi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public interface ti<Model, Data> {

    public static class a<Data> {
        public final ig a;
        public final List<ig> b;
        public final m5<Data> c;

        public a(@NonNull ig igVar, @NonNull List<ig> list, @NonNull m5<Data> m5Var) {
            Objects.requireNonNull(igVar, "Argument must not be null");
            this.a = igVar;
            Objects.requireNonNull(list, "Argument must not be null");
            this.b = list;
            Objects.requireNonNull(m5Var, "Argument must not be null");
            this.c = m5Var;
        }

        public a(@NonNull ig igVar, @NonNull m5<Data> m5Var) {
            this(igVar, Collections.emptyList(), m5Var);
        }
    }

    boolean a(@NonNull Model model);

    @Nullable
    a<Data> b(@NonNull Model model, int i, int i2, @NonNull lk lkVar);
}
