package u1;

import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: ModelLoader.java */
/* JADX INFO: loaded from: classes.dex */
public interface n<Model, Data> {

    /* JADX INFO: compiled from: ModelLoader.java */
    public static class a<Data> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final n1.f f9613a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final List<n1.f> f9614b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final o1.d<Data> f9615c;

        public a(n1.f fVar, o1.d<Data> dVar) {
            this(fVar, Collections.emptyList(), dVar);
        }

        public a(n1.f fVar, List<n1.f> list, o1.d<Data> dVar) {
            this.f9613a = (n1.f) k2.j.d(fVar);
            this.f9614b = (List) k2.j.d(list);
            this.f9615c = (o1.d) k2.j.d(dVar);
        }
    }

    boolean a(Model model);

    a<Data> b(Model model, int i7, int i8, n1.i iVar);
}
