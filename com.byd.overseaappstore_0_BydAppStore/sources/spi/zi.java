package spi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pools;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import spi.m5;
import spi.ti;

/* JADX INFO: loaded from: classes.dex */
public final class zi<Model, Data> implements ti<Model, Data> {
    public final List<ti<Model, Data>> a;
    public final Pools.Pool<List<Throwable>> b;

    public static class a<Data> implements m5<Data>, m5.a<Data> {
        public final List<m5<Data>> a;
        public final Pools.Pool<List<Throwable>> b;
        public int c;
        public Priority d;
        public m5.a<? super Data> e;

        @Nullable
        public List<Throwable> f;
        public boolean g;

        public a(@NonNull List<m5<Data>> list, @NonNull Pools.Pool<List<Throwable>> pool) {
            this.b = pool;
            if (list.isEmpty()) {
                throw new IllegalArgumentException("Must not be empty.");
            }
            this.a = list;
            this.c = 0;
        }

        @Override // spi.m5
        @NonNull
        public final Class<Data> a() {
            return this.a.get(0).a();
        }

        @Override // spi.m5
        public final void b() {
            List<Throwable> list = this.f;
            if (list != null) {
                this.b.release(list);
            }
            this.f = null;
            Iterator<m5<Data>> it = this.a.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
        }

        @Override // spi.m5.a
        public final void c(@NonNull Exception exc) {
            List<Throwable> list = this.f;
            Objects.requireNonNull(list, "Argument must not be null");
            list.add(exc);
            g();
        }

        @Override // spi.m5
        public final void cancel() {
            this.g = true;
            Iterator<m5<Data>> it = this.a.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
        }

        @Override // spi.m5
        public final void d(@NonNull Priority priority, @NonNull m5.a<? super Data> aVar) {
            this.d = priority;
            this.e = aVar;
            this.f = this.b.acquire();
            this.a.get(this.c).d(priority, this);
            if (this.g) {
                cancel();
            }
        }

        @Override // spi.m5
        @NonNull
        public final DataSource e() {
            return this.a.get(0).e();
        }

        @Override // spi.m5.a
        public final void f(@Nullable Data data) {
            if (data != null) {
                this.e.f(data);
            } else {
                g();
            }
        }

        public final void g() {
            if (this.g) {
                return;
            }
            if (this.c < this.a.size() - 1) {
                this.c++;
                d(this.d, this.e);
            } else {
                dl.b(this.f);
                this.e.c(new GlideException("Fetch failed", new ArrayList(this.f)));
            }
        }
    }

    public zi(@NonNull List<ti<Model, Data>> list, @NonNull Pools.Pool<List<Throwable>> pool) {
        this.a = list;
        this.b = pool;
    }

    @Override // spi.ti
    public final boolean a(@NonNull Model model) {
        Iterator<ti<Model, Data>> it = this.a.iterator();
        while (it.hasNext()) {
            if (it.next().a(model)) {
                return true;
            }
        }
        return false;
    }

    @Override // spi.ti
    public final ti.a<Data> b(@NonNull Model model, int i, int i2, @NonNull lk lkVar) {
        ti.a<Data> aVarB;
        int size = this.a.size();
        ArrayList arrayList = new ArrayList(size);
        ig igVar = null;
        for (int i3 = 0; i3 < size; i3++) {
            ti<Model, Data> tiVar = this.a.get(i3);
            if (tiVar.a(model) && (aVarB = tiVar.b(model, i, i2, lkVar)) != null) {
                igVar = aVarB.a;
                arrayList.add(aVarB.c);
            }
        }
        if (arrayList.isEmpty() || igVar == null) {
            return null;
        }
        return new ti.a<>(igVar, new a(arrayList, this.b));
    }

    public final String toString() {
        StringBuilder sbK = e.k("MultiModelLoader{modelLoaders=");
        sbK.append(Arrays.toString(this.a.toArray()));
        sbK.append('}');
        return sbK.toString();
    }
}
