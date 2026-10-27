package com.bumptech.glide.load.j;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.i.d;
import com.bumptech.glide.load.j.n;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: MultiModelLoader.java */
/* JADX INFO: loaded from: classes.dex */
class q<Model, Data> implements n<Model, Data> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<n<Model, Data>> f3003a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.g.j.e<List<Throwable>> f3004b;

    /* JADX INFO: compiled from: MultiModelLoader.java */
    static class a<Data> implements com.bumptech.glide.load.i.d<Data>, d.a<Data> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final List<com.bumptech.glide.load.i.d<Data>> f3005b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final c.g.j.e<List<Throwable>> f3006c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f3007d;
        private Priority e;
        private d.a<? super Data> f;
        private List<Throwable> g;
        private boolean h;

        a(List<com.bumptech.glide.load.i.d<Data>> list, c.g.j.e<List<Throwable>> eVar) {
            this.f3006c = eVar;
            com.bumptech.glide.m.j.c(list);
            this.f3005b = list;
            this.f3007d = 0;
        }

        private void g() {
            if (this.h) {
                return;
            }
            if (this.f3007d < this.f3005b.size() - 1) {
                this.f3007d++;
                f(this.e, this.f);
            } else {
                com.bumptech.glide.m.j.d(this.g);
                this.f.c(new GlideException("Fetch failed", new ArrayList(this.g)));
            }
        }

        @Override // com.bumptech.glide.load.i.d
        public Class<Data> a() {
            return this.f3005b.get(0).a();
        }

        @Override // com.bumptech.glide.load.i.d
        public void b() {
            List<Throwable> list = this.g;
            if (list != null) {
                this.f3006c.a(list);
            }
            this.g = null;
            Iterator<com.bumptech.glide.load.i.d<Data>> it = this.f3005b.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
        }

        @Override // com.bumptech.glide.load.i.d.a
        public void c(Exception exc) {
            ((List) com.bumptech.glide.m.j.d(this.g)).add(exc);
            g();
        }

        @Override // com.bumptech.glide.load.i.d
        public void cancel() {
            this.h = true;
            Iterator<com.bumptech.glide.load.i.d<Data>> it = this.f3005b.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
        }

        @Override // com.bumptech.glide.load.i.d.a
        public void d(Data data) {
            if (data != null) {
                this.f.d(data);
            } else {
                g();
            }
        }

        @Override // com.bumptech.glide.load.i.d
        public DataSource e() {
            return this.f3005b.get(0).e();
        }

        @Override // com.bumptech.glide.load.i.d
        public void f(Priority priority, d.a<? super Data> aVar) {
            this.e = priority;
            this.f = aVar;
            this.g = this.f3006c.b();
            this.f3005b.get(this.f3007d).f(priority, this);
            if (this.h) {
                cancel();
            }
        }
    }

    q(List<n<Model, Data>> list, c.g.j.e<List<Throwable>> eVar) {
        this.f3003a = list;
        this.f3004b = eVar;
    }

    @Override // com.bumptech.glide.load.j.n
    public n.a<Data> a(Model model, int i, int i2, com.bumptech.glide.load.e eVar) {
        n.a<Data> aVarA;
        int size = this.f3003a.size();
        ArrayList arrayList = new ArrayList(size);
        com.bumptech.glide.load.c cVar = null;
        for (int i3 = 0; i3 < size; i3++) {
            n<Model, Data> nVar = this.f3003a.get(i3);
            if (nVar.b(model) && (aVarA = nVar.a(model, i, i2, eVar)) != null) {
                cVar = aVarA.f2996a;
                arrayList.add(aVarA.f2998c);
            }
        }
        if (arrayList.isEmpty() || cVar == null) {
            return null;
        }
        return new n.a<>(cVar, new a(arrayList, this.f3004b));
    }

    @Override // com.bumptech.glide.load.j.n
    public boolean b(Model model) {
        Iterator<n<Model, Data>> it = this.f3003a.iterator();
        while (it.hasNext()) {
            if (it.next().b(model)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "MultiModelLoader{modelLoaders=" + Arrays.toString(this.f3003a.toArray()) + '}';
    }
}
