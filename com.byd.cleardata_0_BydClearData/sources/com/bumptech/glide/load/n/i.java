package com.bumptech.glide.load.n;

import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class i<DataType, ResourceType, Transcode> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Class<DataType> f2137a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<? extends com.bumptech.glide.load.k<DataType, ResourceType>> f2138b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.load.p.i.e<ResourceType, Transcode> f2139c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final a.g.j.e<List<Throwable>> f2140d;
    private final String e;

    interface a<ResourceType> {
        v<ResourceType> a(v<ResourceType> vVar);
    }

    public i(Class<DataType> cls, Class<ResourceType> cls2, Class<Transcode> cls3, List<? extends com.bumptech.glide.load.k<DataType, ResourceType>> list, com.bumptech.glide.load.p.i.e<ResourceType, Transcode> eVar, a.g.j.e<List<Throwable>> eVar2) {
        this.f2137a = cls;
        this.f2138b = list;
        this.f2139c = eVar;
        this.f2140d = eVar2;
        this.e = "Failed DecodePath{" + cls.getSimpleName() + "->" + cls2.getSimpleName() + "->" + cls3.getSimpleName() + "}";
    }

    private v<ResourceType> b(com.bumptech.glide.load.data.e<DataType> eVar, int i, int i2, com.bumptech.glide.load.i iVar) {
        List<Throwable> list = (List) com.bumptech.glide.t.j.d(this.f2140d.b());
        try {
            return c(eVar, i, i2, iVar, list);
        } finally {
            this.f2140d.a(list);
        }
    }

    private v<ResourceType> c(com.bumptech.glide.load.data.e<DataType> eVar, int i, int i2, com.bumptech.glide.load.i iVar, List<Throwable> list) throws q {
        int size = this.f2138b.size();
        v<ResourceType> vVarA = null;
        for (int i3 = 0; i3 < size; i3++) {
            com.bumptech.glide.load.k<DataType, ResourceType> kVar = this.f2138b.get(i3);
            try {
                if (kVar.b(eVar.a(), iVar)) {
                    vVarA = kVar.a(eVar.a(), i, i2, iVar);
                }
            } catch (IOException | OutOfMemoryError | RuntimeException e) {
                if (Log.isLoggable("DecodePath", 2)) {
                    Log.v("DecodePath", "Failed to decode data for " + kVar, e);
                }
                list.add(e);
            }
            if (vVarA != null) {
                break;
            }
        }
        if (vVarA != null) {
            return vVarA;
        }
        throw new q(this.e, new ArrayList(list));
    }

    public v<Transcode> a(com.bumptech.glide.load.data.e<DataType> eVar, int i, int i2, com.bumptech.glide.load.i iVar, a<ResourceType> aVar) {
        return this.f2139c.a(aVar.a(b(eVar, i, i2, iVar)), iVar);
    }

    public String toString() {
        return "DecodePath{ dataClass=" + this.f2137a + ", decoders=" + this.f2138b + ", transcoder=" + this.f2139c + '}';
    }
}
