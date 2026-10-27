package com.bumptech.glide.load.o;

import com.bumptech.glide.load.data.d;
import com.bumptech.glide.load.o.n;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class q<Model, Data> implements n<Model, Data> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<n<Model, Data>> f2257a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a.g.j.e<List<Throwable>> f2258b;

    static class a<Data> implements com.bumptech.glide.load.data.d<Data>, d.a<Data> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final List<com.bumptech.glide.load.data.d<Data>> f2259b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final a.g.j.e<List<Throwable>> f2260c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f2261d;
        private com.bumptech.glide.g e;
        private d.a<? super Data> f;
        private List<Throwable> g;
        private boolean h;

        a(List<com.bumptech.glide.load.data.d<Data>> list, a.g.j.e<List<Throwable>> eVar) {
            this.f2260c = eVar;
            com.bumptech.glide.t.j.c(list);
            this.f2259b = list;
            this.f2261d = 0;
        }

        private void g() {
            if (this.h) {
                return;
            }
            if (this.f2261d < this.f2259b.size() - 1) {
                this.f2261d++;
                e(this.e, this.f);
            } else {
                com.bumptech.glide.t.j.d(this.g);
                this.f.d(new com.bumptech.glide.load.n.q("Fetch failed", new ArrayList(this.g)));
            }
        }

        @Override // com.bumptech.glide.load.data.d
        public Class<Data> a() {
            return this.f2259b.get(0).a();
        }

        @Override // com.bumptech.glide.load.data.d
        public void b() {
            List<Throwable> list = this.g;
            if (list != null) {
                this.f2260c.a(list);
            }
            this.g = null;
            Iterator<com.bumptech.glide.load.data.d<Data>> it = this.f2259b.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
        }

        @Override // com.bumptech.glide.load.data.d
        public com.bumptech.glide.load.a c() {
            return this.f2259b.get(0).c();
        }

        @Override // com.bumptech.glide.load.data.d
        public void cancel() {
            this.h = true;
            Iterator<com.bumptech.glide.load.data.d<Data>> it = this.f2259b.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
        }

        @Override // com.bumptech.glide.load.data.d.a
        public void d(Exception exc) {
            ((List) com.bumptech.glide.t.j.d(this.g)).add(exc);
            g();
        }

        @Override // com.bumptech.glide.load.data.d
        public void e(com.bumptech.glide.g gVar, d.a<? super Data> aVar) {
            this.e = gVar;
            this.f = aVar;
            this.g = this.f2260c.b();
            this.f2259b.get(this.f2261d).e(gVar, this);
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

    q(List<n<Model, Data>> list, a.g.j.e<List<Throwable>> eVar) {
        this.f2257a = list;
        this.f2258b = eVar;
    }

    @Override // com.bumptech.glide.load.o.n
    public n.a<Data> a(Model model, int i, int i2, com.bumptech.glide.load.i iVar) {
        n.a<Data> aVarA;
        int size = this.f2257a.size();
        ArrayList arrayList = new ArrayList(size);
        com.bumptech.glide.load.g gVar = null;
        for (int i3 = 0; i3 < size; i3++) {
            n<Model, Data> nVar = this.f2257a.get(i3);
            if (nVar.b(model) && (aVarA = nVar.a(model, i, i2, iVar)) != null) {
                gVar = aVarA.f2250a;
                arrayList.add(aVarA.f2252c);
            }
        }
        if (arrayList.isEmpty() || gVar == null) {
            return null;
        }
        return new n.a<>(gVar, new a(arrayList, this.f2258b));
    }

    @Override // com.bumptech.glide.load.o.n
    public boolean b(Model model) {
        Iterator<n<Model, Data>> it = this.f2257a.iterator();
        while (it.hasNext()) {
            if (it.next().b(model)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "MultiModelLoader{modelLoaders=" + Arrays.toString(this.f2257a.toArray()) + '}';
    }
}
