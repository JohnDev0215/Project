package com.google.gson.internal;

import com.google.gson.p;
import com.google.gson.q;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: Excluder.java */
/* JADX INFO: loaded from: classes.dex */
public final class c implements q, Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c f4032b = new c();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f4036f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private double f4033c = -1.0d;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f4034d = 136;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f4035e = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private List<com.google.gson.a> f4037g = Collections.emptyList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private List<com.google.gson.a> f4038h = Collections.emptyList();

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: Excluder.java */
    class a<T> extends p<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private p<T> f4039a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ boolean f4040b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f4041c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ com.google.gson.d f4042d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ com.google.gson.s.a f4043e;

        a(boolean z, boolean z2, com.google.gson.d dVar, com.google.gson.s.a aVar) {
            this.f4040b = z;
            this.f4041c = z2;
            this.f4042d = dVar;
            this.f4043e = aVar;
        }

        private p<T> e() {
            p<T> pVar = this.f4039a;
            if (pVar != null) {
                return pVar;
            }
            p<T> pVarM = this.f4042d.m(c.this, this.f4043e);
            this.f4039a = pVarM;
            return pVarM;
        }

        @Override // com.google.gson.p
        public T b(com.google.gson.stream.a aVar) throws IOException {
            if (!this.f4040b) {
                return e().b(aVar);
            }
            aVar.H();
            return null;
        }

        @Override // com.google.gson.p
        public void d(com.google.gson.stream.b bVar, T t) throws IOException {
            if (this.f4041c) {
                bVar.m();
            } else {
                e().d(bVar, t);
            }
        }
    }

    private boolean d(Class<?> cls) {
        if (this.f4033c == -1.0d || m((com.google.gson.r.d) cls.getAnnotation(com.google.gson.r.d.class), (com.google.gson.r.e) cls.getAnnotation(com.google.gson.r.e.class))) {
            return (!this.f4035e && i(cls)) || h(cls);
        }
        return true;
    }

    private boolean f(Class<?> cls, boolean z) {
        Iterator<com.google.gson.a> it = (z ? this.f4037g : this.f4038h).iterator();
        while (it.hasNext()) {
            if (it.next().a(cls)) {
                return true;
            }
        }
        return false;
    }

    private boolean h(Class<?> cls) {
        return (Enum.class.isAssignableFrom(cls) || j(cls) || (!cls.isAnonymousClass() && !cls.isLocalClass())) ? false : true;
    }

    private boolean i(Class<?> cls) {
        return cls.isMemberClass() && !j(cls);
    }

    private boolean j(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    private boolean k(com.google.gson.r.d dVar) {
        return dVar == null || dVar.value() <= this.f4033c;
    }

    private boolean l(com.google.gson.r.e eVar) {
        return eVar == null || eVar.value() > this.f4033c;
    }

    private boolean m(com.google.gson.r.d dVar, com.google.gson.r.e eVar) {
        return k(dVar) && l(eVar);
    }

    @Override // com.google.gson.q
    public <T> p<T> a(com.google.gson.d dVar, com.google.gson.s.a<T> aVar) {
        Class<? super T> clsC = aVar.c();
        boolean zD = d(clsC);
        boolean z = zD || f(clsC, true);
        boolean z2 = zD || f(clsC, false);
        if (z || z2) {
            return new a(z2, z, dVar, aVar);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public c clone() {
        try {
            return (c) super.clone();
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public boolean c(Class<?> cls, boolean z) {
        return d(cls) || f(cls, z);
    }

    public boolean g(Field field, boolean z) {
        com.google.gson.r.a aVar;
        if ((this.f4034d & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.f4033c != -1.0d && !m((com.google.gson.r.d) field.getAnnotation(com.google.gson.r.d.class), (com.google.gson.r.e) field.getAnnotation(com.google.gson.r.e.class))) || field.isSynthetic()) {
            return true;
        }
        if (this.f4036f && ((aVar = (com.google.gson.r.a) field.getAnnotation(com.google.gson.r.a.class)) == null || (!z ? aVar.deserialize() : aVar.serialize()))) {
            return true;
        }
        if ((!this.f4035e && i(field.getType())) || h(field.getType())) {
            return true;
        }
        List<com.google.gson.a> list = z ? this.f4037g : this.f4038h;
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
