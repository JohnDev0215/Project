package com.bumptech.glide;

import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.data.e;
import com.bumptech.glide.load.engine.q;
import com.bumptech.glide.load.engine.s;
import com.bumptech.glide.load.i.n;
import com.bumptech.glide.load.i.o;
import com.bumptech.glide.load.i.p;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Registry {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final p f2835a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.m.a f2836b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.m.e f2837c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.bumptech.glide.m.f f2838d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.bumptech.glide.load.data.f f2839e;
    private final com.bumptech.glide.load.j.h.f f;
    private final com.bumptech.glide.m.b g;
    private final com.bumptech.glide.m.d h = new com.bumptech.glide.m.d();
    private final com.bumptech.glide.m.c i = new com.bumptech.glide.m.c();
    private final b.g.j.e<List<Throwable>> j;

    public static class MissingComponentException extends RuntimeException {
        public MissingComponentException(String str) {
            super(str);
        }
    }

    public static final class NoImageHeaderParserException extends MissingComponentException {
        public NoImageHeaderParserException() {
            super("Failed to find image header parser.");
        }
    }

    public static class NoModelLoaderAvailableException extends MissingComponentException {
        public NoModelLoaderAvailableException(Object obj) {
            super("Failed to find any ModelLoaders registered for model class: " + obj.getClass());
        }

        public <M> NoModelLoaderAvailableException(M m, List<n<M, ?>> list) {
            super("Found ModelLoaders for model class: " + list + ", but none that handle this specific model instance: " + m);
        }

        public NoModelLoaderAvailableException(Class<?> cls, Class<?> cls2) {
            super("Failed to find any ModelLoaders for model: " + cls + " and data: " + cls2);
        }
    }

    public static class NoResultEncoderAvailableException extends MissingComponentException {
        public NoResultEncoderAvailableException(Class<?> cls) {
            super("Failed to find result encoder for resource class: " + cls + ", you may need to consider registering a new Encoder for the requested type or DiskCacheStrategy.DATA/DiskCacheStrategy.NONE if caching your transformed resource is unnecessary.");
        }
    }

    public static class NoSourceEncoderAvailableException extends MissingComponentException {
        public NoSourceEncoderAvailableException(Class<?> cls) {
            super("Failed to find source encoder for data class: " + cls);
        }
    }

    public Registry() {
        b.g.j.e<List<Throwable>> eVarE = com.bumptech.glide.o.l.a.e();
        this.j = eVarE;
        this.f2835a = new p(eVarE);
        this.f2836b = new com.bumptech.glide.m.a();
        this.f2837c = new com.bumptech.glide.m.e();
        this.f2838d = new com.bumptech.glide.m.f();
        this.f2839e = new com.bumptech.glide.load.data.f();
        this.f = new com.bumptech.glide.load.j.h.f();
        this.g = new com.bumptech.glide.m.b();
        s(Arrays.asList("Gif", "Bitmap", "BitmapDrawable"));
    }

    private <Data, TResource, Transcode> List<com.bumptech.glide.load.engine.g<Data, TResource, Transcode>> f(Class<Data> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        ArrayList arrayList = new ArrayList();
        for (Class cls4 : this.f2837c.d(cls, cls2)) {
            for (Class cls5 : this.f.b(cls4, cls3)) {
                arrayList.add(new com.bumptech.glide.load.engine.g(cls, cls4, cls5, this.f2837c.b(cls, cls4), this.f.a(cls4, cls5), this.j));
            }
        }
        return arrayList;
    }

    public <Data> Registry a(Class<Data> cls, com.bumptech.glide.load.a<Data> aVar) {
        this.f2836b.a(cls, aVar);
        return this;
    }

    public <TResource> Registry b(Class<TResource> cls, com.bumptech.glide.load.g<TResource> gVar) {
        this.f2838d.a(cls, gVar);
        return this;
    }

    public <Data, TResource> Registry c(Class<Data> cls, Class<TResource> cls2, com.bumptech.glide.load.f<Data, TResource> fVar) {
        e("legacy_append", cls, cls2, fVar);
        return this;
    }

    public <Model, Data> Registry d(Class<Model> cls, Class<Data> cls2, o<Model, Data> oVar) {
        this.f2835a.a(cls, cls2, oVar);
        return this;
    }

    public <Data, TResource> Registry e(String str, Class<Data> cls, Class<TResource> cls2, com.bumptech.glide.load.f<Data, TResource> fVar) {
        this.f2837c.a(str, fVar, cls, cls2);
        return this;
    }

    public List<ImageHeaderParser> g() {
        List<ImageHeaderParser> listB = this.g.b();
        if (listB.isEmpty()) {
            throw new NoImageHeaderParserException();
        }
        return listB;
    }

    public <Data, TResource, Transcode> q<Data, TResource, Transcode> h(Class<Data> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        q<Data, TResource, Transcode> qVarA = this.i.a(cls, cls2, cls3);
        if (this.i.c(qVarA)) {
            return null;
        }
        if (qVarA == null) {
            List<com.bumptech.glide.load.engine.g<Data, TResource, Transcode>> listF = f(cls, cls2, cls3);
            qVarA = listF.isEmpty() ? null : new q<>(cls, cls2, cls3, listF, this.j);
            this.i.d(cls, cls2, cls3, qVarA);
        }
        return qVarA;
    }

    public <Model> List<n<Model, ?>> i(Model model) {
        return this.f2835a.d(model);
    }

    public <Model, TResource, Transcode> List<Class<?>> j(Class<Model> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        List<Class<?>> listA = this.h.a(cls, cls2, cls3);
        if (listA == null) {
            listA = new ArrayList<>();
            Iterator<Class<?>> it = this.f2835a.c(cls).iterator();
            while (it.hasNext()) {
                for (Class<?> cls4 : this.f2837c.d(it.next(), cls2)) {
                    if (!this.f.b(cls4, cls3).isEmpty() && !listA.contains(cls4)) {
                        listA.add(cls4);
                    }
                }
            }
            this.h.b(cls, cls2, cls3, Collections.unmodifiableList(listA));
        }
        return listA;
    }

    public <X> com.bumptech.glide.load.g<X> k(s<X> sVar) throws NoResultEncoderAvailableException {
        com.bumptech.glide.load.g<X> gVarB = this.f2838d.b(sVar.d());
        if (gVarB != null) {
            return gVarB;
        }
        throw new NoResultEncoderAvailableException(sVar.d());
    }

    public <X> com.bumptech.glide.load.data.e<X> l(X x) {
        return this.f2839e.a(x);
    }

    public <X> com.bumptech.glide.load.a<X> m(X x) throws NoSourceEncoderAvailableException {
        com.bumptech.glide.load.a<X> aVarB = this.f2836b.b(x.getClass());
        if (aVarB != null) {
            return aVarB;
        }
        throw new NoSourceEncoderAvailableException(x.getClass());
    }

    public boolean n(s<?> sVar) {
        return this.f2838d.b(sVar.d()) != null;
    }

    public <Model, Data> Registry o(Class<Model> cls, Class<Data> cls2, o<Model, Data> oVar) {
        this.f2835a.f(cls, cls2, oVar);
        return this;
    }

    public Registry p(ImageHeaderParser imageHeaderParser) {
        this.g.a(imageHeaderParser);
        return this;
    }

    public Registry q(e.a<?> aVar) {
        this.f2839e.b(aVar);
        return this;
    }

    public <TResource, Transcode> Registry r(Class<TResource> cls, Class<Transcode> cls2, com.bumptech.glide.load.j.h.e<TResource, Transcode> eVar) {
        this.f.c(cls, cls2, eVar);
        return this;
    }

    public final Registry s(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        arrayList.addAll(list);
        arrayList.add(0, "legacy_prepend_all");
        arrayList.add("legacy_append");
        this.f2837c.e(arrayList);
        return this;
    }
}
