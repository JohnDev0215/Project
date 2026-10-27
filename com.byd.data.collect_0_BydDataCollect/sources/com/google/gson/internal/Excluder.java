package com.google.gson.internal;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.t;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class Excluder implements t, Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Excluder f4440b = new Excluder();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f4444f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private double f4441c = -1.0d;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f4442d = 136;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f4443e = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private List<com.google.gson.a> f4445g = Collections.emptyList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private List<com.google.gson.a> f4446h = Collections.emptyList();

    private boolean f(Class<?> cls) {
        if (this.f4441c == -1.0d || o((com.google.gson.u.d) cls.getAnnotation(com.google.gson.u.d.class), (com.google.gson.u.e) cls.getAnnotation(com.google.gson.u.e.class))) {
            return (!this.f4443e && k(cls)) || i(cls);
        }
        return true;
    }

    private boolean g(Class<?> cls, boolean z) {
        Iterator<com.google.gson.a> it = (z ? this.f4445g : this.f4446h).iterator();
        while (it.hasNext()) {
            if (it.next().a(cls)) {
                return true;
            }
        }
        return false;
    }

    private boolean i(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    private boolean k(Class<?> cls) {
        return cls.isMemberClass() && !l(cls);
    }

    private boolean l(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    private boolean m(com.google.gson.u.d dVar) {
        return dVar == null || dVar.value() <= this.f4441c;
    }

    private boolean n(com.google.gson.u.e eVar) {
        return eVar == null || eVar.value() > this.f4441c;
    }

    private boolean o(com.google.gson.u.d dVar, com.google.gson.u.e eVar) {
        return m(dVar) && n(eVar);
    }

    @Override // com.google.gson.t
    public <T> TypeAdapter<T> b(final Gson gson, final com.google.gson.v.a<T> aVar) {
        Class<? super T> clsC = aVar.c();
        boolean zF = f(clsC);
        final boolean z = zF || g(clsC, true);
        final boolean z2 = zF || g(clsC, false);
        if (z || z2) {
            return new TypeAdapter<T>() { // from class: com.google.gson.internal.Excluder.1

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                private TypeAdapter<T> f4447a;

                private TypeAdapter<T> a() {
                    TypeAdapter<T> typeAdapter = this.f4447a;
                    if (typeAdapter != null) {
                        return typeAdapter;
                    }
                    TypeAdapter<T> typeAdapterM = gson.m(Excluder.this, aVar);
                    this.f4447a = typeAdapterM;
                    return typeAdapterM;
                }

                @Override // com.google.gson.TypeAdapter
                public T read(com.google.gson.w.a aVar2) throws IOException {
                    if (!z2) {
                        return a().read(aVar2);
                    }
                    aVar2.t0();
                    return null;
                }

                @Override // com.google.gson.TypeAdapter
                public void write(com.google.gson.w.c cVar, T t) throws IOException {
                    if (z) {
                        cVar.Y();
                    } else {
                        a().write(cVar, t);
                    }
                }
            };
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public Excluder clone() {
        try {
            return (Excluder) super.clone();
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public boolean e(Class<?> cls, boolean z) {
        return f(cls) || g(cls, z);
    }

    public boolean h(Field field, boolean z) {
        com.google.gson.u.a aVar;
        if ((this.f4442d & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.f4441c != -1.0d && !o((com.google.gson.u.d) field.getAnnotation(com.google.gson.u.d.class), (com.google.gson.u.e) field.getAnnotation(com.google.gson.u.e.class))) || field.isSynthetic()) {
            return true;
        }
        if (this.f4444f && ((aVar = (com.google.gson.u.a) field.getAnnotation(com.google.gson.u.a.class)) == null || (!z ? aVar.deserialize() : aVar.serialize()))) {
            return true;
        }
        if ((!this.f4443e && k(field.getType())) || i(field.getType())) {
            return true;
        }
        List<com.google.gson.a> list = z ? this.f4445g : this.f4446h;
        if (list.isEmpty()) {
            return false;
        }
        com.google.gson.b bVar = new com.google.gson.b(field);
        Iterator<com.google.gson.a> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().b(bVar)) {
                return true;
            }
        }
        return false;
    }
}
