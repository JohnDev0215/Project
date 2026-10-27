package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.h;
import com.google.gson.i;
import com.google.gson.internal.k;
import com.google.gson.j;
import com.google.gson.p;
import com.google.gson.q;
import com.google.gson.t;
import com.google.gson.w.c;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class TreeTypeAdapter<T> extends TypeAdapter<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final q<T> f4492a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final i<T> f4493b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Gson f4494c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.google.gson.v.a<T> f4495d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final t f4496e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final TreeTypeAdapter<T>.b f4497f = new b();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private TypeAdapter<T> f4498g;

    private static final class SingleTypeFactory implements t {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final com.google.gson.v.a<?> f4499b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final boolean f4500c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Class<?> f4501d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final q<?> f4502e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final i<?> f4503f;

        SingleTypeFactory(Object obj, com.google.gson.v.a<?> aVar, boolean z, Class<?> cls) {
            q<?> qVar = obj instanceof q ? (q) obj : null;
            this.f4502e = qVar;
            i<?> iVar = obj instanceof i ? (i) obj : null;
            this.f4503f = iVar;
            com.google.gson.internal.a.a((qVar == null && iVar == null) ? false : true);
            this.f4499b = aVar;
            this.f4500c = z;
            this.f4501d = cls;
        }

        @Override // com.google.gson.t
        public <T> TypeAdapter<T> b(Gson gson, com.google.gson.v.a<T> aVar) {
            com.google.gson.v.a<?> aVar2 = this.f4499b;
            if (aVar2 != null ? aVar2.equals(aVar) || (this.f4500c && this.f4499b.e() == aVar.c()) : this.f4501d.isAssignableFrom(aVar.c())) {
                return new TreeTypeAdapter(this.f4502e, this.f4503f, gson, aVar, this);
            }
            return null;
        }
    }

    private final class b implements p, h {
        private b() {
        }
    }

    public TreeTypeAdapter(q<T> qVar, i<T> iVar, Gson gson, com.google.gson.v.a<T> aVar, t tVar) {
        this.f4492a = qVar;
        this.f4493b = iVar;
        this.f4494c = gson;
        this.f4495d = aVar;
        this.f4496e = tVar;
    }

    private TypeAdapter<T> a() {
        TypeAdapter<T> typeAdapter = this.f4498g;
        if (typeAdapter != null) {
            return typeAdapter;
        }
        TypeAdapter<T> typeAdapterM = this.f4494c.m(this.f4496e, this.f4495d);
        this.f4498g = typeAdapterM;
        return typeAdapterM;
    }

    public static t b(com.google.gson.v.a<?> aVar, Object obj) {
        return new SingleTypeFactory(obj, aVar, aVar.e() == aVar.c(), null);
    }

    @Override // com.google.gson.TypeAdapter
    public T read(com.google.gson.w.a aVar) {
        if (this.f4493b == null) {
            return a().read(aVar);
        }
        j jVarA = k.a(aVar);
        if (jVarA.j()) {
            return null;
        }
        return this.f4493b.deserialize(jVarA, this.f4495d.e(), this.f4497f);
    }

    @Override // com.google.gson.TypeAdapter
    public void write(c cVar, T t) throws IOException {
        q<T> qVar = this.f4492a;
        if (qVar == null) {
            a().write(cVar, t);
        } else if (t == null) {
            cVar.Y();
        } else {
            k.b(qVar.serialize(t, this.f4495d.e(), this.f4497f), cVar);
        }
    }
}
