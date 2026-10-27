package com.bumptech.glide;

import com.bumptech.glide.load.ImageHeaderParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import o1.e;
import q1.t;
import q1.v;
import u1.n;
import u1.o;
import u1.p;

/* JADX INFO: compiled from: Registry.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final p f3983a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final f2.a f3984b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final f2.e f3985c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final f2.f f3986d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final o1.f f3987e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final c2.f f3988f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final f2.b f3989g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final f2.d f3990h = new f2.d();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final f2.c f3991i = new f2.c();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final h0.e<List<Throwable>> f3992j;

    /* JADX INFO: compiled from: Registry.java */
    public static class a extends RuntimeException {
        public a(String str) {
            super(str);
        }
    }

    /* JADX INFO: compiled from: Registry.java */
    public static final class b extends a {
        public b() {
            super("Failed to find image header parser.");
        }
    }

    /* JADX INFO: compiled from: Registry.java */
    public static class c extends a {
        public c(Object obj) {
            super("Failed to find any ModelLoaders for model: " + obj);
        }

        public c(Class<?> cls, Class<?> cls2) {
            super("Failed to find any ModelLoaders for model: " + cls + " and data: " + cls2);
        }
    }

    /* JADX INFO: compiled from: Registry.java */
    public static class d extends a {
        public d(Class<?> cls) {
            super("Failed to find result encoder for resource class: " + cls + ", you may need to consider registering a new Encoder for the requested type or DiskCacheStrategy.DATA/DiskCacheStrategy.NONE if caching your transformed resource is unnecessary.");
        }
    }

    /* JADX INFO: compiled from: Registry.java */
    public static class e extends a {
        public e(Class<?> cls) {
            super("Failed to find source encoder for data class: " + cls);
        }
    }

    public i() {
        h0.e<List<Throwable>> eVarE = l2.a.e();
        this.f3992j = eVarE;
        this.f3983a = new p(eVarE);
        this.f3984b = new f2.a();
        this.f3985c = new f2.e();
        this.f3986d = new f2.f();
        this.f3987e = new o1.f();
        this.f3988f = new c2.f();
        this.f3989g = new f2.b();
        s(Arrays.asList("Gif", "Bitmap", "BitmapDrawable"));
    }

    private <Data, TResource, Transcode> List<q1.i<Data, TResource, Transcode>> f(Class<Data> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        ArrayList arrayList = new ArrayList();
        for (Class cls4 : this.f3985c.d(cls, cls2)) {
            for (Class cls5 : this.f3988f.b(cls4, cls3)) {
                arrayList.add(new q1.i(cls, cls4, cls5, this.f3985c.b(cls, cls4), this.f3988f.a(cls4, cls5), this.f3992j));
            }
        }
        return arrayList;
    }

    public <Data, TResource> i a(Class<Data> cls, Class<TResource> cls2, n1.k<Data, TResource> kVar) {
        e("legacy_append", cls, cls2, kVar);
        return this;
    }

    public <Model, Data> i b(Class<Model> cls, Class<Data> cls2, o<Model, Data> oVar) {
        this.f3983a.a(cls, cls2, oVar);
        return this;
    }

    public <Data> i c(Class<Data> cls, n1.d<Data> dVar) {
        this.f3984b.a(cls, dVar);
        return this;
    }

    public <TResource> i d(Class<TResource> cls, n1.l<TResource> lVar) {
        this.f3986d.a(cls, lVar);
        return this;
    }

    public <Data, TResource> i e(String str, Class<Data> cls, Class<TResource> cls2, n1.k<Data, TResource> kVar) {
        this.f3985c.a(str, kVar, cls, cls2);
        return this;
    }

    public List<ImageHeaderParser> g() {
        List<ImageHeaderParser> listB = this.f3989g.b();
        if (listB.isEmpty()) {
            throw new b();
        }
        return listB;
    }

    public <Data, TResource, Transcode> t<Data, TResource, Transcode> h(Class<Data> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        t<Data, TResource, Transcode> tVarA = this.f3991i.a(cls, cls2, cls3);
        if (this.f3991i.c(tVarA)) {
            return null;
        }
        if (tVarA == null) {
            List<q1.i<Data, TResource, Transcode>> listF = f(cls, cls2, cls3);
            tVarA = listF.isEmpty() ? null : new t<>(cls, cls2, cls3, listF, this.f3992j);
            this.f3991i.d(cls, cls2, cls3, tVarA);
        }
        return tVarA;
    }

    public <Model> List<n<Model, ?>> i(Model model) {
        List<n<Model, ?>> listD = this.f3983a.d(model);
        if (listD.isEmpty()) {
            throw new c(model);
        }
        return listD;
    }

    public <Model, TResource, Transcode> List<Class<?>> j(Class<Model> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        List<Class<?>> listA = this.f3990h.a(cls, cls2, cls3);
        if (listA == null) {
            listA = new ArrayList<>();
            Iterator<Class<?>> it = this.f3983a.c(cls).iterator();
            while (it.hasNext()) {
                for (Class<?> cls4 : this.f3985c.d(it.next(), cls2)) {
                    if (!this.f3988f.b(cls4, cls3).isEmpty() && !listA.contains(cls4)) {
                        listA.add(cls4);
                    }
                }
            }
            this.f3990h.b(cls, cls2, cls3, Collections.unmodifiableList(listA));
        }
        return listA;
    }

    public <X> n1.l<X> k(v<X> vVar) {
        n1.l<X> lVarB = this.f3986d.b(vVar.e());
        if (lVarB != null) {
            return lVarB;
        }
        throw new d(vVar.e());
    }

    public <X> o1.e<X> l(X x6) {
        return this.f3987e.a(x6);
    }

    public <X> n1.d<X> m(X x6) {
        n1.d<X> dVarB = this.f3984b.b(x6.getClass());
        if (dVarB != null) {
            return dVarB;
        }
        throw new e(x6.getClass());
    }

    public boolean n(v<?> vVar) {
        return this.f3986d.b(vVar.e()) != null;
    }

    public i o(ImageHeaderParser imageHeaderParser) {
        this.f3989g.a(imageHeaderParser);
        return this;
    }

    public <TResource, Transcode> i p(Class<TResource> cls, Class<Transcode> cls2, c2.e<TResource, Transcode> eVar) {
        this.f3988f.c(cls, cls2, eVar);
        return this;
    }

    public i q(e.a<?> aVar) {
        this.f3987e.b(aVar);
        return this;
    }

    public <Model, Data> i r(Class<Model> cls, Class<Data> cls2, o<? extends Model, ? extends Data> oVar) {
        this.f3983a.f(cls, cls2, oVar);
        return this;
    }

    public final i s(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        arrayList.addAll(list);
        arrayList.add(0, "legacy_prepend_all");
        arrayList.add("legacy_append");
        this.f3985c.e(arrayList);
        return this;
    }
}
