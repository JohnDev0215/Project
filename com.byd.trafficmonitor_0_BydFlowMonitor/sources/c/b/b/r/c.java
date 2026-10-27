package c.b.b.r;

import c.b.b.o;
import c.b.b.p;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: Excluder.java */
/* JADX INFO: loaded from: classes.dex */
public final class c implements p, Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c f4198b = new c();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4202f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public double f4199c = -1.0d;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4200d = 136;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f4201e = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public List<c.b.b.a> f4203g = Collections.emptyList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public List<c.b.b.a> f4204h = Collections.emptyList();

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: Excluder.java */
    public class a<T> extends o<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public o<T> f4205a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f4206b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ boolean f4207c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ c.b.b.d f4208d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ c.b.b.s.a f4209e;

        public a(boolean z, boolean z2, c.b.b.d dVar, c.b.b.s.a aVar) {
            this.f4206b = z;
            this.f4207c = z2;
            this.f4208d = dVar;
            this.f4209e = aVar;
        }

        @Override // c.b.b.o
        public T b(c.b.b.t.a aVar) throws IOException {
            if (!this.f4206b) {
                return e().b(aVar);
            }
            aVar.u0();
            return null;
        }

        @Override // c.b.b.o
        public void d(c.b.b.t.b bVar, T t) throws IOException {
            if (this.f4207c) {
                bVar.Z();
            } else {
                e().d(bVar, t);
            }
        }

        public final o<T> e() {
            o<T> oVar = this.f4205a;
            if (oVar != null) {
                return oVar;
            }
            o<T> oVarM = this.f4208d.m(c.this, this.f4209e);
            this.f4205a = oVarM;
            return oVarM;
        }
    }

    @Override // c.b.b.p
    public <T> o<T> a(c.b.b.d dVar, c.b.b.s.a<T> aVar) {
        Class<? super T> clsD = aVar.d();
        boolean zC = c(clsD, true);
        boolean zC2 = c(clsD, false);
        if (zC || zC2) {
            return new a(zC2, zC, dVar, aVar);
        }
        return null;
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public c clone() {
        try {
            return (c) super.clone();
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public boolean c(Class<?> cls, boolean z) {
        if (this.f4199c != -1.0d && !j((c.b.b.q.d) cls.getAnnotation(c.b.b.q.d.class), (c.b.b.q.e) cls.getAnnotation(c.b.b.q.e.class))) {
            return true;
        }
        if ((!this.f4201e && f(cls)) || e(cls)) {
            return true;
        }
        Iterator<c.b.b.a> it = (z ? this.f4203g : this.f4204h).iterator();
        while (it.hasNext()) {
            if (it.next().a(cls)) {
                return true;
            }
        }
        return false;
    }

    public boolean d(Field field, boolean z) {
        c.b.b.q.a aVar;
        if ((this.f4200d & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.f4199c != -1.0d && !j((c.b.b.q.d) field.getAnnotation(c.b.b.q.d.class), (c.b.b.q.e) field.getAnnotation(c.b.b.q.e.class))) || field.isSynthetic()) {
            return true;
        }
        if (this.f4202f && ((aVar = (c.b.b.q.a) field.getAnnotation(c.b.b.q.a.class)) == null || (!z ? aVar.deserialize() : aVar.serialize()))) {
            return true;
        }
        if ((!this.f4201e && f(field.getType())) || e(field.getType())) {
            return true;
        }
        List<c.b.b.a> list = z ? this.f4203g : this.f4204h;
        if (list.isEmpty()) {
            return false;
        }
        c.b.b.b bVar = new c.b.b.b(field);
        Iterator<c.b.b.a> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().b(bVar)) {
                return true;
            }
        }
        return false;
    }

    public final boolean e(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    public final boolean f(Class<?> cls) {
        return cls.isMemberClass() && !g(cls);
    }

    public final boolean g(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    public final boolean h(c.b.b.q.d dVar) {
        return dVar == null || dVar.value() <= this.f4199c;
    }

    public final boolean i(c.b.b.q.e eVar) {
        return eVar == null || eVar.value() > this.f4199c;
    }

    public final boolean j(c.b.b.q.d dVar, c.b.b.q.e eVar) {
        return h(dVar) && i(eVar);
    }
}
