package spi;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.DataSource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import spi.m5;
import spi.n5;
import spi.ti;

/* JADX INFO: loaded from: classes.dex */
public final class ym implements n5, m5.a<Object> {
    public final n5.a a;
    public final v5<?> b;
    public int c;
    public int d = -1;
    public ig e;
    public List<ti<File, ?>> f;
    public int g;
    public volatile ti.a<?> h;
    public File i;
    public zm j;

    public ym(v5<?> v5Var, n5.a aVar) {
        this.b = v5Var;
        this.a = aVar;
    }

    @Override // spi.n5
    public final boolean b() {
        ArrayList arrayList = (ArrayList) this.b.a();
        if (arrayList.isEmpty()) {
            return false;
        }
        List<Class<?>> listE = this.b.e();
        if (listE.isEmpty()) {
            if (File.class.equals(this.b.k)) {
                return false;
            }
            throw new IllegalStateException("Failed to find any load path from " + this.b.d.getClass() + " to " + this.b.k);
        }
        while (true) {
            List<ti<File, ?>> list = this.f;
            if (list != null) {
                if (this.g < list.size()) {
                    this.h = null;
                    boolean z = false;
                    while (!z) {
                        if (!(this.g < this.f.size())) {
                            break;
                        }
                        List<ti<File, ?>> list2 = this.f;
                        int i = this.g;
                        this.g = i + 1;
                        ti<File, ?> tiVar = list2.get(i);
                        File file = this.i;
                        v5<?> v5Var = this.b;
                        this.h = tiVar.b(file, v5Var.e, v5Var.f, v5Var.i);
                        if (this.h != null && this.b.h(this.h.c.a())) {
                            this.h.c.d(this.b.o, this);
                            z = true;
                        }
                    }
                    return z;
                }
            }
            int i2 = this.d + 1;
            this.d = i2;
            if (i2 >= listE.size()) {
                int i3 = this.c + 1;
                this.c = i3;
                if (i3 >= arrayList.size()) {
                    return false;
                }
                this.d = 0;
            }
            ig igVar = (ig) arrayList.get(this.c);
            Class<?> cls = listE.get(this.d);
            qt<Z> qtVarG = this.b.g(cls);
            v5<?> v5Var2 = this.b;
            this.j = new zm(v5Var2.c.a, igVar, v5Var2.n, v5Var2.e, v5Var2.f, qtVarG, cls, v5Var2.i);
            File fileB = this.b.b().b(this.j);
            this.i = fileB;
            if (fileB != null) {
                this.e = igVar;
                this.f = this.b.c.b.f(fileB);
                this.g = 0;
            }
        }
    }

    @Override // spi.m5.a
    public final void c(@NonNull Exception exc) {
        this.a.c(this.j, exc, this.h.c, DataSource.RESOURCE_DISK_CACHE);
    }

    @Override // spi.n5
    public final void cancel() {
        ti.a<?> aVar = this.h;
        if (aVar != null) {
            aVar.c.cancel();
        }
    }

    @Override // spi.m5.a
    public final void f(Object obj) {
        this.a.a(this.e, obj, this.h.c, DataSource.RESOURCE_DISK_CACHE, this.j);
    }
}
