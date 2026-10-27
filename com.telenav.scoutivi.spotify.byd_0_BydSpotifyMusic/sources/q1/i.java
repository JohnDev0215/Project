package q1;

import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: DecodePath.java */
/* JADX INFO: loaded from: classes.dex */
public class i<DataType, ResourceType, Transcode> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Class<DataType> f9016a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<? extends n1.k<DataType, ResourceType>> f9017b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c2.e<ResourceType, Transcode> f9018c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final h0.e<List<Throwable>> f9019d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f9020e;

    /* JADX INFO: compiled from: DecodePath.java */
    interface a<ResourceType> {
        v<ResourceType> a(v<ResourceType> vVar);
    }

    public i(Class<DataType> cls, Class<ResourceType> cls2, Class<Transcode> cls3, List<? extends n1.k<DataType, ResourceType>> list, c2.e<ResourceType, Transcode> eVar, h0.e<List<Throwable>> eVar2) {
        this.f9016a = cls;
        this.f9017b = list;
        this.f9018c = eVar;
        this.f9019d = eVar2;
        this.f9020e = "Failed DecodePath{" + cls.getSimpleName() + "->" + cls2.getSimpleName() + "->" + cls3.getSimpleName() + "}";
    }

    private v<ResourceType> b(o1.e<DataType> eVar, int i7, int i8, n1.i iVar) {
        List<Throwable> list = (List) k2.j.d(this.f9019d.b());
        try {
            return c(eVar, i7, i8, iVar, list);
        } finally {
            this.f9019d.a(list);
        }
    }

    private v<ResourceType> c(o1.e<DataType> eVar, int i7, int i8, n1.i iVar, List<Throwable> list) throws q {
        int size = this.f9017b.size();
        v<ResourceType> vVarB = null;
        for (int i9 = 0; i9 < size; i9++) {
            n1.k<DataType, ResourceType> kVar = this.f9017b.get(i9);
            try {
                if (kVar.a(eVar.a(), iVar)) {
                    vVarB = kVar.b(eVar.a(), i7, i8, iVar);
                }
            } catch (IOException | OutOfMemoryError | RuntimeException e7) {
                if (Log.isLoggable("DecodePath", 2)) {
                    Log.v("DecodePath", "Failed to decode data for " + kVar, e7);
                }
                list.add(e7);
            }
            if (vVarB != null) {
                break;
            }
        }
        if (vVarB != null) {
            return vVarB;
        }
        throw new q(this.f9020e, new ArrayList(list));
    }

    public v<Transcode> a(o1.e<DataType> eVar, int i7, int i8, n1.i iVar, a<ResourceType> aVar) {
        return this.f9018c.a(aVar.a(b(eVar, i7, i8, iVar)), iVar);
    }

    public String toString() {
        return "DecodePath{ dataClass=" + this.f9016a + ", decoders=" + this.f9017b + ", transcoder=" + this.f9018c + '}';
    }
}
