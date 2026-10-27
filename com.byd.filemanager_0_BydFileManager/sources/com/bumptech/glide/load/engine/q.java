package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.engine.g;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: LoadPath.java */
/* JADX INFO: loaded from: classes.dex */
public class q<Data, ResourceType, Transcode> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Class<Data> f3067a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b.g.j.e<List<Throwable>> f3068b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<? extends g<Data, ResourceType, Transcode>> f3069c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f3070d;

    public q(Class<Data> cls, Class<ResourceType> cls2, Class<Transcode> cls3, List<g<Data, ResourceType, Transcode>> list, b.g.j.e<List<Throwable>> eVar) {
        this.f3067a = cls;
        this.f3068b = eVar;
        this.f3069c = (List) com.bumptech.glide.o.j.c(list);
        this.f3070d = "Failed LoadPath{" + cls.getSimpleName() + "->" + cls2.getSimpleName() + "->" + cls3.getSimpleName() + "}";
    }

    private s<Transcode> b(com.bumptech.glide.load.data.e<Data> eVar, com.bumptech.glide.load.e eVar2, int i, int i2, g.a<ResourceType> aVar, List<Throwable> list) throws GlideException {
        int size = this.f3069c.size();
        s<Transcode> sVarA = null;
        for (int i3 = 0; i3 < size; i3++) {
            try {
                sVarA = this.f3069c.get(i3).a(eVar, i, i2, eVar2, aVar);
            } catch (GlideException e2) {
                list.add(e2);
            }
            if (sVarA != null) {
                break;
            }
        }
        if (sVarA != null) {
            return sVarA;
        }
        throw new GlideException(this.f3070d, new ArrayList(list));
    }

    public s<Transcode> a(com.bumptech.glide.load.data.e<Data> eVar, com.bumptech.glide.load.e eVar2, int i, int i2, g.a<ResourceType> aVar) throws GlideException {
        List<Throwable> list = (List) com.bumptech.glide.o.j.d(this.f3068b.b());
        try {
            return b(eVar, eVar2, i, i2, aVar, list);
        } finally {
            this.f3068b.a(list);
        }
    }

    public String toString() {
        return "LoadPath{decodePaths=" + Arrays.toString(this.f3069c.toArray()) + '}';
    }
}
