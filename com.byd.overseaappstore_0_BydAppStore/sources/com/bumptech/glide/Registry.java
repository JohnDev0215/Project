package com.bumptech.glide;

import androidx.annotation.NonNull;
import androidx.core.util.Pools;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.data.a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import spi.aj;
import spi.bn;
import spi.cn;
import spi.e;
import spi.en;
import spi.fn;
import spi.g8;
import spi.in;
import spi.lf;
import spi.pt;
import spi.sd;
import spi.ta;
import spi.ti;
import spi.ua;
import spi.ui;
import spi.va;
import spi.vi;
import spi.wg;
import spi.wi;

/* JADX INFO: loaded from: classes.dex */
public final class Registry {
    public final vi a;
    public final g8 b;
    public final cn c;
    public final fn d;
    public final com.bumptech.glide.load.data.b e;
    public final pt f;
    public final lf g;
    public final wi h = new wi();
    public final wg i = new wg();
    public final Pools.Pool<List<Throwable>> j;

    public static class MissingComponentException extends RuntimeException {
        public MissingComponentException(@NonNull String str) {
            super(str);
        }
    }

    public static final class NoImageHeaderParserException extends MissingComponentException {
        public NoImageHeaderParserException() {
            super("Failed to find image header parser.");
        }
    }

    public static class NoModelLoaderAvailableException extends MissingComponentException {
        public NoModelLoaderAvailableException(@NonNull Class<?> cls, @NonNull Class<?> cls2) {
            super("Failed to find any ModelLoaders for model: " + cls + " and data: " + cls2);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public NoModelLoaderAvailableException(@NonNull Object obj) {
            StringBuilder sbK = e.k("Failed to find any ModelLoaders registered for model class: ");
            sbK.append(obj.getClass());
            super(sbK.toString());
        }

        public <M> NoModelLoaderAvailableException(@NonNull M m, @NonNull List<ti<M, ?>> list) {
            super("Found ModelLoaders for model class: " + list + ", but none that handle this specific model instance: " + m);
        }
    }

    public static class NoResultEncoderAvailableException extends MissingComponentException {
        public NoResultEncoderAvailableException(@NonNull Class<?> cls) {
            super("Failed to find result encoder for resource class: " + cls + ", you may need to consider registering a new Encoder for the requested type or DiskCacheStrategy.DATA/DiskCacheStrategy.NONE if caching your transformed resource is unnecessary.");
        }
    }

    public static class NoSourceEncoderAvailableException extends MissingComponentException {
        public NoSourceEncoderAvailableException(@NonNull Class<?> cls) {
            super("Failed to find source encoder for data class: " + cls);
        }
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [java.util.ArrayList, java.util.List<java.lang.String>] */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.util.ArrayList, java.util.List<java.lang.String>] */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.util.ArrayList, java.util.List<java.lang.String>] */
    public Registry() {
        ta.e<Object> eVar = ta.a;
        ta.c cVar = new ta.c(new Pools.SynchronizedPool(20), new ua(), new va());
        this.j = cVar;
        this.a = new vi(cVar);
        this.b = new g8();
        this.c = new cn();
        this.d = new fn();
        this.e = new com.bumptech.glide.load.data.b();
        this.f = new pt();
        this.g = new lf();
        List listAsList = Arrays.asList("Animation", "Bitmap", "BitmapDrawable");
        ArrayList arrayList = new ArrayList(listAsList.size());
        arrayList.add("legacy_prepend_all");
        Iterator it = listAsList.iterator();
        while (it.hasNext()) {
            arrayList.add((String) it.next());
        }
        arrayList.add("legacy_append");
        cn cnVar = this.c;
        synchronized (cnVar) {
            ArrayList<String> arrayList2 = new ArrayList(cnVar.a);
            cnVar.a.clear();
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                cnVar.a.add((String) it2.next());
            }
            for (String str : arrayList2) {
                if (!arrayList.contains(str)) {
                    cnVar.a.add(str);
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r3v3, types: [java.util.HashMap, java.util.Map<java.lang.Class<?>, spi.vi$a$a<?>>] */
    @NonNull
    public final <Model, Data> Registry a(@NonNull Class<Model> cls, @NonNull Class<Data> cls2, @NonNull ui<Model, Data> uiVar) {
        vi viVar = this.a;
        synchronized (viVar) {
            viVar.a.a(cls, cls2, uiVar);
            viVar.b.a.clear();
        }
        return this;
    }

    @NonNull
    public final <Data, TResource> Registry b(@NonNull Class<Data> cls, @NonNull Class<TResource> cls2, @NonNull bn<Data, TResource> bnVar) {
        d("legacy_append", cls, cls2, bnVar);
        return this;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @NonNull
    public final <TResource> Registry c(@NonNull Class<TResource> cls, @NonNull en<TResource> enVar) {
        fn fnVar = this.d;
        synchronized (fnVar) {
            fnVar.a.add(new fn.a<>(cls, enVar));
        }
        return this;
    }

    @NonNull
    public final <Data, TResource> Registry d(@NonNull String str, @NonNull Class<Data> cls, @NonNull Class<TResource> cls2, @NonNull bn<Data, TResource> bnVar) {
        cn cnVar = this.c;
        synchronized (cnVar) {
            cnVar.a(str).add(new cn.a<>(cls, cls2, bnVar));
        }
        return this;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.ArrayList, java.util.List<com.bumptech.glide.load.ImageHeaderParser>] */
    @NonNull
    public final List<ImageHeaderParser> e() {
        ?? r1;
        lf lfVar = this.g;
        synchronized (lfVar) {
            r1 = lfVar.a;
        }
        if (r1.isEmpty()) {
            throw new NoImageHeaderParserException();
        }
        return r1;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.HashMap, java.util.Map<java.lang.Class<?>, spi.vi$a$a<?>>] */
    @NonNull
    public final <Model> List<ti<Model, ?>> f(@NonNull Model model) {
        List<ti<Model, ?>> listUnmodifiableList;
        vi viVar = this.a;
        Objects.requireNonNull(viVar);
        Class<?> cls = model.getClass();
        synchronized (viVar) {
            vi.a.C0143a c0143a = (vi.a.C0143a) viVar.b.a.get(cls);
            listUnmodifiableList = c0143a == null ? null : c0143a.a;
            if (listUnmodifiableList == null) {
                listUnmodifiableList = Collections.unmodifiableList(viVar.a.b(cls));
                viVar.b.a(cls, listUnmodifiableList);
            }
        }
        if (listUnmodifiableList.isEmpty()) {
            throw new NoModelLoaderAvailableException(model);
        }
        int size = listUnmodifiableList.size();
        List<ti<Model, ?>> listEmptyList = Collections.emptyList();
        boolean z = true;
        for (int i = 0; i < size; i++) {
            ti<Model, ?> tiVar = listUnmodifiableList.get(i);
            if (tiVar.a(model)) {
                if (z) {
                    listEmptyList = new ArrayList<>(size - i);
                    z = false;
                }
                listEmptyList.add(tiVar);
            }
        }
        if (listEmptyList.isEmpty()) {
            throw new NoModelLoaderAvailableException(model, listUnmodifiableList);
        }
        return listEmptyList;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.HashMap, java.util.Map<java.lang.Class<?>, com.bumptech.glide.load.data.a$a<?>>] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.HashMap, java.util.Map<java.lang.Class<?>, com.bumptech.glide.load.data.a$a<?>>] */
    @NonNull
    public final <X> com.bumptech.glide.load.data.a<X> g(@NonNull X x) {
        com.bumptech.glide.load.data.a<X> aVar;
        com.bumptech.glide.load.data.b bVar = this.e;
        synchronized (bVar) {
            Objects.requireNonNull(x, "Argument must not be null");
            a.InterfaceC0016a<?> interfaceC0016a = (a.InterfaceC0016a) bVar.a.get(x.getClass());
            if (interfaceC0016a == null) {
                Iterator it = bVar.a.values().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    a.InterfaceC0016a<?> interfaceC0016a2 = (a.InterfaceC0016a) it.next();
                    if (interfaceC0016a2.a().isAssignableFrom(x.getClass())) {
                        interfaceC0016a = interfaceC0016a2;
                        break;
                    }
                }
            }
            if (interfaceC0016a == null) {
                interfaceC0016a = com.bumptech.glide.load.data.b.b;
            }
            aVar = (com.bumptech.glide.load.data.a<X>) interfaceC0016a.b(x);
        }
        return aVar;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.HashMap, java.util.Map<java.lang.Class<?>, com.bumptech.glide.load.data.a$a<?>>] */
    @NonNull
    public final Registry h(@NonNull a.InterfaceC0016a<?> interfaceC0016a) {
        com.bumptech.glide.load.data.b bVar = this.e;
        synchronized (bVar) {
            bVar.a.put(interfaceC0016a.a(), interfaceC0016a);
        }
        return this;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @NonNull
    public final <TResource, Transcode> Registry i(@NonNull Class<TResource> cls, @NonNull Class<Transcode> cls2, @NonNull in<TResource, Transcode> inVar) {
        pt ptVar = this.f;
        synchronized (ptVar) {
            ptVar.a.add(new pt.a<>(cls, cls2, inVar));
        }
        return this;
    }

    /* JADX WARN: Type inference failed for: r5v5, types: [java.util.HashMap, java.util.Map<java.lang.Class<?>, spi.vi$a$a<?>>] */
    @NonNull
    public final Registry j(@NonNull Class cls, @NonNull ui uiVar) {
        List listF;
        vi viVar = this.a;
        synchronized (viVar) {
            aj ajVar = viVar.a;
            synchronized (ajVar) {
                listF = ajVar.f(cls);
                ajVar.a(sd.class, cls, uiVar);
            }
            Iterator it = ((ArrayList) listF).iterator();
            while (it.hasNext()) {
                ((ui) it.next()).a();
            }
            viVar.b.a.clear();
        }
        return this;
    }
}
