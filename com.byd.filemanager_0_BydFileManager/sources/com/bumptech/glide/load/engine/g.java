package com.bumptech.glide.load.engine;

import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: DecodePath.java */
/* JADX INFO: loaded from: classes.dex */
public class g<DataType, ResourceType, Transcode> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Class<DataType> f3016a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<? extends com.bumptech.glide.load.f<DataType, ResourceType>> f3017b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.load.j.h.e<ResourceType, Transcode> f3018c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final b.g.j.e<List<Throwable>> f3019d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f3020e;

    /* JADX INFO: compiled from: DecodePath.java */
    interface a<ResourceType> {
        s<ResourceType> a(s<ResourceType> sVar);
    }

    public g(Class<DataType> cls, Class<ResourceType> cls2, Class<Transcode> cls3, List<? extends com.bumptech.glide.load.f<DataType, ResourceType>> list, com.bumptech.glide.load.j.h.e<ResourceType, Transcode> eVar, b.g.j.e<List<Throwable>> eVar2) {
        this.f3016a = cls;
        this.f3017b = list;
        this.f3018c = eVar;
        this.f3019d = eVar2;
        this.f3020e = "Failed DecodePath{" + cls.getSimpleName() + "->" + cls2.getSimpleName() + "->" + cls3.getSimpleName() + "}";
    }

    private s<ResourceType> b(com.bumptech.glide.load.data.e<DataType> eVar, int i, int i2, com.bumptech.glide.load.e eVar2) throws GlideException {
        List<Throwable> list = (List) com.bumptech.glide.o.j.d(this.f3019d.b());
        try {
            return c(eVar, i, i2, eVar2, list);
        } finally {
            this.f3019d.a(list);
        }
    }

    private s<ResourceType> c(com.bumptech.glide.load.data.e<DataType> eVar, int i, int i2, com.bumptech.glide.load.e eVar2, List<Throwable> list) throws GlideException {
        int size = this.f3017b.size();
        s<ResourceType> sVarA = null;
        for (int i3 = 0; i3 < size; i3++) {
            com.bumptech.glide.load.f<DataType, ResourceType> fVar = this.f3017b.get(i3);
            try {
                if (fVar.b(eVar.a(), eVar2)) {
                    sVarA = fVar.a(eVar.a(), i, i2, eVar2);
                }
            } catch (IOException | OutOfMemoryError | RuntimeException e2) {
                if (Log.isLoggable("DecodePath", 2)) {
                    Log.v("DecodePath", "Failed to decode data for " + fVar, e2);
                }
                list.add(e2);
            }
            if (sVarA != null) {
                break;
            }
        }
        if (sVarA != null) {
            return sVarA;
        }
        throw new GlideException(this.f3020e, new ArrayList(list));
    }

    public s<Transcode> a(com.bumptech.glide.load.data.e<DataType> eVar, int i, int i2, com.bumptech.glide.load.e eVar2, a<ResourceType> aVar) throws GlideException {
        return this.f3018c.a(aVar.a(b(eVar, i, i2, eVar2)), eVar2);
    }

    public String toString() {
        return "DecodePath{ dataClass=" + this.f3016a + ", decoders=" + this.f3017b + ", transcoder=" + this.f3018c + '}';
    }
}
