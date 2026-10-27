package q1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import q1.i;

/* JADX INFO: compiled from: LoadPath.java */
/* JADX INFO: loaded from: classes.dex */
public class t<Data, ResourceType, Transcode> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Class<Data> f9107a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final h0.e<List<Throwable>> f9108b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<? extends i<Data, ResourceType, Transcode>> f9109c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f9110d;

    public t(Class<Data> cls, Class<ResourceType> cls2, Class<Transcode> cls3, List<i<Data, ResourceType, Transcode>> list, h0.e<List<Throwable>> eVar) {
        this.f9107a = cls;
        this.f9108b = eVar;
        this.f9109c = (List) k2.j.c(list);
        this.f9110d = "Failed LoadPath{" + cls.getSimpleName() + "->" + cls2.getSimpleName() + "->" + cls3.getSimpleName() + "}";
    }

    private v<Transcode> b(o1.e<Data> eVar, n1.i iVar, int i7, int i8, i.a<ResourceType> aVar, List<Throwable> list) throws q {
        int size = this.f9109c.size();
        v<Transcode> vVarA = null;
        for (int i9 = 0; i9 < size; i9++) {
            try {
                vVarA = this.f9109c.get(i9).a(eVar, i7, i8, iVar, aVar);
            } catch (q e7) {
                list.add(e7);
            }
            if (vVarA != null) {
                break;
            }
        }
        if (vVarA != null) {
            return vVarA;
        }
        throw new q(this.f9110d, new ArrayList(list));
    }

    public v<Transcode> a(o1.e<Data> eVar, n1.i iVar, int i7, int i8, i.a<ResourceType> aVar) {
        List<Throwable> list = (List) k2.j.d(this.f9108b.b());
        try {
            return b(eVar, iVar, i7, i8, aVar, list);
        } finally {
            this.f9108b.a(list);
        }
    }

    public String toString() {
        return "LoadPath{decodePaths=" + Arrays.toString(this.f9109c.toArray()) + '}';
    }
}
