package c.b.a.t;

import c.b.a.q;
import c.b.a.r;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: Excluder.java */
/* JADX INFO: loaded from: classes.dex */
public final class c implements r, Cloneable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c f2940c = new c();
    public boolean y;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public double f2941d = -1.0d;
    public int s = 136;
    public boolean x = true;
    public List<c.b.a.b> E3 = Collections.emptyList();
    public List<c.b.a.b> F3 = Collections.emptyList();

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: Excluder.java */
    public class a<T> extends q<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public q<T> f2942a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f2943b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ boolean f2944c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ c.b.a.e f2945d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ c.b.a.u.a f2946e;

        public a(boolean z, boolean z2, c.b.a.e eVar, c.b.a.u.a aVar) {
            this.f2943b = z;
            this.f2944c = z2;
            this.f2945d = eVar;
            this.f2946e = aVar;
        }

        @Override // c.b.a.q
        public T b(c.b.a.v.a aVar) throws IOException {
            if (!this.f2943b) {
                return e().b(aVar);
            }
            aVar.l0();
            return null;
        }

        @Override // c.b.a.q
        public void d(c.b.a.v.b bVar, T t) throws IOException {
            if (this.f2944c) {
                bVar.P();
            } else {
                e().d(bVar, t);
            }
        }

        public final q<T> e() {
            q<T> qVar = this.f2942a;
            if (qVar != null) {
                return qVar;
            }
            q<T> qVarO = this.f2945d.o(c.this, this.f2946e);
            this.f2942a = qVarO;
            return qVarO;
        }
    }

    @Override // c.b.a.r
    public <T> q<T> a(c.b.a.e eVar, c.b.a.u.a<T> aVar) {
        Class<? super T> clsC = aVar.c();
        boolean zE = e(clsC, true);
        boolean zE2 = e(clsC, false);
        if (zE || zE2) {
            return new a(zE2, zE, eVar, aVar);
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

    public boolean e(Class<?> cls, boolean z) {
        if (this.f2941d != -1.0d && !l((c.b.a.s.d) cls.getAnnotation(c.b.a.s.d.class), (c.b.a.s.e) cls.getAnnotation(c.b.a.s.e.class))) {
            return true;
        }
        if ((!this.x && h(cls)) || g(cls)) {
            return true;
        }
        Iterator<c.b.a.b> it = (z ? this.E3 : this.F3).iterator();
        while (it.hasNext()) {
            if (it.next().a(cls)) {
                return true;
            }
        }
        return false;
    }

    public boolean f(Field field, boolean z) {
        c.b.a.s.a aVar;
        if ((this.s & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.f2941d != -1.0d && !l((c.b.a.s.d) field.getAnnotation(c.b.a.s.d.class), (c.b.a.s.e) field.getAnnotation(c.b.a.s.e.class))) || field.isSynthetic()) {
            return true;
        }
        if (this.y && ((aVar = (c.b.a.s.a) field.getAnnotation(c.b.a.s.a.class)) == null || (!z ? aVar.deserialize() : aVar.serialize()))) {
            return true;
        }
        if ((!this.x && h(field.getType())) || g(field.getType())) {
            return true;
        }
        List<c.b.a.b> list = z ? this.E3 : this.F3;
        if (list.isEmpty()) {
            return false;
        }
        c.b.a.c cVar = new c.b.a.c(field);
        Iterator<c.b.a.b> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().b(cVar)) {
                return true;
            }
        }
        return false;
    }

    public final boolean g(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    public final boolean h(Class<?> cls) {
        return cls.isMemberClass() && !i(cls);
    }

    public final boolean i(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    public final boolean j(c.b.a.s.d dVar) {
        return dVar == null || dVar.value() <= this.f2941d;
    }

    public final boolean k(c.b.a.s.e eVar) {
        return eVar == null || eVar.value() > this.f2941d;
    }

    public final boolean l(c.b.a.s.d dVar, c.b.a.s.e eVar) {
        return j(dVar) && k(eVar);
    }
}
