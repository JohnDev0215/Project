package u1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import o1.d;
import u1.n;

/* JADX INFO: compiled from: MultiModelLoader.java */
/* JADX INFO: loaded from: classes.dex */
class q<Model, Data> implements n<Model, Data> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<n<Model, Data>> f9620a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final h0.e<List<Throwable>> f9621b;

    /* JADX INFO: compiled from: MultiModelLoader.java */
    static class a<Data> implements o1.d<Data>, d.a<Data> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final List<o1.d<Data>> f9622e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final h0.e<List<Throwable>> f9623g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private int f9624h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private com.bumptech.glide.g f9625i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private d.a<? super Data> f9626j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private List<Throwable> f9627k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private boolean f9628l;

        a(List<o1.d<Data>> list, h0.e<List<Throwable>> eVar) {
            this.f9623g = eVar;
            k2.j.c(list);
            this.f9622e = list;
            this.f9624h = 0;
        }

        private void g() {
            if (this.f9628l) {
                return;
            }
            if (this.f9624h < this.f9622e.size() - 1) {
                this.f9624h++;
                c(this.f9625i, this.f9626j);
            } else {
                k2.j.d(this.f9627k);
                this.f9626j.d(new q1.q("Fetch failed", new ArrayList(this.f9627k)));
            }
        }

        @Override // o1.d
        public Class<Data> a() {
            return this.f9622e.get(0).a();
        }

        @Override // o1.d
        public void b() {
            List<Throwable> list = this.f9627k;
            if (list != null) {
                this.f9623g.a(list);
            }
            this.f9627k = null;
            Iterator<o1.d<Data>> it = this.f9622e.iterator();
            while (it.hasNext()) {
                it.next().b();
            }
        }

        @Override // o1.d
        public void c(com.bumptech.glide.g gVar, d.a<? super Data> aVar) {
            this.f9625i = gVar;
            this.f9626j = aVar;
            this.f9627k = this.f9623g.b();
            this.f9622e.get(this.f9624h).c(gVar, this);
            if (this.f9628l) {
                cancel();
            }
        }

        @Override // o1.d
        public void cancel() {
            this.f9628l = true;
            Iterator<o1.d<Data>> it = this.f9622e.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
        }

        @Override // o1.d.a
        public void d(Exception exc) {
            ((List) k2.j.d(this.f9627k)).add(exc);
            g();
        }

        @Override // o1.d
        public n1.a e() {
            return this.f9622e.get(0).e();
        }

        @Override // o1.d.a
        public void f(Data data) {
            if (data != null) {
                this.f9626j.f(data);
            } else {
                g();
            }
        }
    }

    q(List<n<Model, Data>> list, h0.e<List<Throwable>> eVar) {
        this.f9620a = list;
        this.f9621b = eVar;
    }

    @Override // u1.n
    public boolean a(Model model) {
        Iterator<n<Model, Data>> it = this.f9620a.iterator();
        while (it.hasNext()) {
            if (it.next().a(model)) {
                return true;
            }
        }
        return false;
    }

    @Override // u1.n
    public n.a<Data> b(Model model, int i7, int i8, n1.i iVar) {
        n.a<Data> aVarB;
        int size = this.f9620a.size();
        ArrayList arrayList = new ArrayList(size);
        n1.f fVar = null;
        for (int i9 = 0; i9 < size; i9++) {
            n<Model, Data> nVar = this.f9620a.get(i9);
            if (nVar.a(model) && (aVarB = nVar.b(model, i7, i8, iVar)) != null) {
                fVar = aVarB.f9613a;
                arrayList.add(aVarB.f9615c);
            }
        }
        if (arrayList.isEmpty() || fVar == null) {
            return null;
        }
        return new n.a<>(fVar, new a(arrayList, this.f9621b));
    }

    public String toString() {
        return "MultiModelLoader{modelLoaders=" + Arrays.toString(this.f9620a.toArray()) + '}';
    }
}
