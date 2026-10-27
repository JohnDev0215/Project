package spi;

import androidx.annotation.NonNull;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import spi.m5;
import spi.ti;

/* JADX INFO: loaded from: classes.dex */
public final class bu<Model> implements ti<Model, Model> {
    public static final bu<?> a = new bu<>();

    public static class a<Model> implements ui<Model, Model> {
        public static final a<?> a = new a<>();

        @Deprecated
        public a() {
        }

        @Override // spi.ui
        public final void a() {
        }

        @Override // spi.ui
        @NonNull
        public final ti<Model, Model> c(aj ajVar) {
            return bu.a;
        }
    }

    public static class b<Model> implements m5<Model> {
        public final Model a;

        public b(Model model) {
            this.a = model;
        }

        @Override // spi.m5
        @NonNull
        public final Class<Model> a() {
            return (Class<Model>) this.a.getClass();
        }

        @Override // spi.m5
        public final void b() {
        }

        @Override // spi.m5
        public final void cancel() {
        }

        @Override // spi.m5
        public final void d(@NonNull Priority priority, @NonNull m5.a<? super Model> aVar) {
            aVar.f(this.a);
        }

        @Override // spi.m5
        @NonNull
        public final DataSource e() {
            return DataSource.LOCAL;
        }
    }

    @Deprecated
    public bu() {
    }

    @Override // spi.ti
    public final boolean a(@NonNull Model model) {
        return true;
    }

    @Override // spi.ti
    public final ti.a<Model> b(@NonNull Model model, int i, int i2, @NonNull lk lkVar) {
        return new ti.a<>(new wj(model), new b(model));
    }
}
