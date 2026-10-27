package b.c.a.r;

import b.c.a.o;
import b.c.a.p;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: Excluder.java */
/* JADX INFO: loaded from: classes3.dex */
public final class c implements p, Cloneable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c f2227a = new c();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2231e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public double f2228b = -1.0d;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2229c = 136;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2230d = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public List<b.c.a.a> f2232f = Collections.emptyList();
    public List<b.c.a.a> g = Collections.emptyList();

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: Excluder.java */
    public class a<T> extends o<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public o<T> f2233a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f2234b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ boolean f2235c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ b.c.a.d f2236d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ b.c.a.s.a f2237e;

        public a(boolean z, boolean z2, b.c.a.d dVar, b.c.a.s.a aVar) {
            this.f2234b = z;
            this.f2235c = z2;
            this.f2236d = dVar;
            this.f2237e = aVar;
        }

        @Override // b.c.a.o
        public T b(b.c.a.t.a aVar) throws IOException {
            if (!this.f2234b) {
                return e().b(aVar);
            }
            aVar.R();
            return null;
        }

        @Override // b.c.a.o
        public void d(b.c.a.t.b bVar, T t) throws IOException {
            if (this.f2235c) {
                bVar.s();
            } else {
                e().d(bVar, t);
            }
        }

        public final o<T> e() {
            o<T> oVar = this.f2233a;
            if (oVar != null) {
                return oVar;
            }
            o<T> oVarM = this.f2236d.m(c.this, this.f2237e);
            this.f2233a = oVarM;
            return oVarM;
        }
    }

    @Override // b.c.a.p
    public <T> o<T> a(b.c.a.d dVar, b.c.a.s.a<T> aVar) {
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
        if (this.f2228b != -1.0d && !j((b.c.a.q.d) cls.getAnnotation(b.c.a.q.d.class), (b.c.a.q.e) cls.getAnnotation(b.c.a.q.e.class))) {
            return true;
        }
        if ((!this.f2230d && f(cls)) || e(cls)) {
            return true;
        }
        Iterator<b.c.a.a> it = (z ? this.f2232f : this.g).iterator();
        while (it.hasNext()) {
            if (it.next().a(cls)) {
                return true;
            }
        }
        return false;
    }

    public boolean d(Field field, boolean z) {
        b.c.a.q.a aVar;
        if ((this.f2229c & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.f2228b != -1.0d && !j((b.c.a.q.d) field.getAnnotation(b.c.a.q.d.class), (b.c.a.q.e) field.getAnnotation(b.c.a.q.e.class))) || field.isSynthetic()) {
            return true;
        }
        if (this.f2231e && ((aVar = (b.c.a.q.a) field.getAnnotation(b.c.a.q.a.class)) == null || (!z ? aVar.deserialize() : aVar.serialize()))) {
            return true;
        }
        if ((!this.f2230d && f(field.getType())) || e(field.getType())) {
            return true;
        }
        List<b.c.a.a> list = z ? this.f2232f : this.g;
        if (list.isEmpty()) {
            return false;
        }
        b.c.a.b bVar = new b.c.a.b(field);
        Iterator<b.c.a.a> it = list.iterator();
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

    public final boolean h(b.c.a.q.d dVar) {
        return dVar == null || dVar.value() <= this.f2228b;
    }

    public final boolean i(b.c.a.q.e eVar) {
        return eVar == null || eVar.value() > this.f2228b;
    }

    public final boolean j(b.c.a.q.d dVar, b.c.a.q.e eVar) {
        return h(dVar) && i(eVar);
    }
}
