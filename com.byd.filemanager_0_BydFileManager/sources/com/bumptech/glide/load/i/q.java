package com.bumptech.glide.load.i;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.d;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.i.n;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: MultiModelLoader.java */
/* JADX INFO: loaded from: classes.dex */
class q<Model, Data> implements n<Model, Data> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<n<Model, Data>> f3228a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b.g.j.e<List<Throwable>> f3229b;

    /* JADX INFO: compiled from: MultiModelLoader.java */
    static class a<Data> implements com.bumptech.glide.load.data.d<Data>, d.a<Data> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final List<com.bumptech.glide.load.data.d<Data>> f3230b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final b.g.j.e<List<Throwable>> f3231c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f3232d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private Priority f3233e;
        private d.a<? super Data> f;
        private List<Throwable> g;
        private boolean h;

        a(List<com.bumptech.glide.load.data.d<Data>> list, b.g.j.e<List<Throwable>> eVar) {
            this.f3231c = eVar;
            com.bumptech.glide.o.j.c(list);
            this.f3230b = list;
            this.f3232d = 0;
        }

        private void g() {
            if (this.h) {
                return;
            }
            if (this.f3232d < this.f3230b.size() - 1) {
                this.f3232d++;
                e(this.f3233e, this.f);
            } else {
                com.bumptech.glide.o.j.d(this.g);
                this.f.d(new GlideException("Fetch failed", new ArrayList(this.g)));
            }
        }

        @Override // com.bumptech.glide.load.data.d
        public Class<Data> a() {
            return this.f3230b.get(0).a();
        }

        @Override // com.bumptech.glide.load.data.d
        public void b() {
            List<Throwable> list = this.g;
            if (list != null) {
                this.f3231c.a(list);
            }
            this.g = null;
            Iterator<com.bumptech.glide.load.data.d<Data>> it = this.f3230b.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
        }

        @Override // com.bumptech.glide.load.data.d
        public DataSource c() {
            return this.f3230b.get(0).c();
        }

        @Override // com.bumptech.glide.load.data.d
        public void cancel() {
            this.h = true;
            Iterator<com.bumptech.glide.load.data.d<Data>> it = this.f3230b.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
        }

        @Override // com.bumptech.glide.load.data.d.a
        public void d(Exception exc) {
            ((List) com.bumptech.glide.o.j.d(this.g)).add(exc);
            g();
        }

        @Override // com.bumptech.glide.load.data.d
        public void e(Priority priority, d.a<? super Data> aVar) {
            this.f3233e = priority;
            this.f = aVar;
            this.g = this.f3231c.b();
            this.f3230b.get(this.f3232d).e(priority, this);
            if (this.h) {
                cancel();
            }
        }

        @Override // com.bumptech.glide.load.data.d.a
        public void f(Data data) {
            if (data != null) {
                this.f.f(data);
            } else {
                g();
            }
        }
    }

    q(List<n<Model, Data>> list, b.g.j.e<List<Throwable>> eVar) {
        this.f3228a = list;
        this.f3229b = eVar;
    }

    @Override // com.bumptech.glide.load.i.n
    public n.a<Data> a(Model model, int i, int i2, com.bumptech.glide.load.e eVar) {
        n.a<Data> aVarA;
        int size = this.f3228a.size();
        ArrayList arrayList = new ArrayList(size);
        com.bumptech.glide.load.c cVar = null;
        for (int i3 = 0; i3 < size; i3++) {
            n<Model, Data> nVar = this.f3228a.get(i3);
            if (nVar.b(model) && (aVarA = nVar.a(model, i, i2, eVar)) != null) {
                cVar = aVarA.f3221a;
                arrayList.add(aVarA.f3223c);
            }
        }
        if (arrayList.isEmpty() || cVar == null) {
            return null;
        }
        return new n.a<>(cVar, new a(arrayList, this.f3229b));
    }

    @Override // com.bumptech.glide.load.i.n
    public boolean b(Model model) {
        Iterator<n<Model, Data>> it = this.f3228a.iterator();
        while (it.hasNext()) {
            if (it.next().b(model)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "MultiModelLoader{modelLoaders=" + Arrays.toString(this.f3228a.toArray()) + '}';
    }
}
