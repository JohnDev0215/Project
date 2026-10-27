package b.b.a.r;

import b.b.a.o;
import b.b.a.p;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: Excluder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c implements p, Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c f416b = new c();
    public boolean f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public double f417c = -1.0d;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f418d = 136;
    public boolean e = true;
    public List<b.b.a.a> g = Collections.emptyList();
    public List<b.b.a.a> h = Collections.emptyList();

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: Excluder.java */
    public class a<T> extends o<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public o<T> f419a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f420b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ boolean f421c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ b.b.a.d f422d;
        public final /* synthetic */ b.b.a.s.a e;

        public a(boolean z, boolean z2, b.b.a.d dVar, b.b.a.s.a aVar) {
            this.f420b = z;
            this.f421c = z2;
            this.f422d = dVar;
            this.e = aVar;
        }

        @Override // b.b.a.o
        public T b(b.b.a.t.a aVar) throws IOException {
            if (!this.f420b) {
                return e().b(aVar);
            }
            aVar.I();
            return null;
        }

        @Override // b.b.a.o
        public void d(b.b.a.t.b bVar, T t) throws IOException {
            if (this.f421c) {
                bVar.n();
            } else {
                e().d(bVar, t);
            }
        }

        public final o<T> e() {
            o<T> oVar = this.f419a;
            if (oVar != null) {
                return oVar;
            }
            o<T> oVarM = this.f422d.m(c.this, this.e);
            this.f419a = oVarM;
            return oVarM;
        }
    }

    @Override // b.b.a.p
    public <T> o<T> a(b.b.a.d dVar, b.b.a.s.a<T> aVar) {
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
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public boolean c(Class<?> cls, boolean z) {
        if (this.f417c != -1.0d && !j((b.b.a.q.d) cls.getAnnotation(b.b.a.q.d.class), (b.b.a.q.e) cls.getAnnotation(b.b.a.q.e.class))) {
            return true;
        }
        if ((!this.e && f(cls)) || e(cls)) {
            return true;
        }
        Iterator<b.b.a.a> it = (z ? this.g : this.h).iterator();
        while (it.hasNext()) {
            if (it.next().a(cls)) {
                return true;
            }
        }
        return false;
    }

    public boolean d(Field field, boolean z) {
        b.b.a.q.a aVar;
        if ((this.f418d & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.f417c != -1.0d && !j((b.b.a.q.d) field.getAnnotation(b.b.a.q.d.class), (b.b.a.q.e) field.getAnnotation(b.b.a.q.e.class))) || field.isSynthetic()) {
            return true;
        }
        if (this.f && ((aVar = (b.b.a.q.a) field.getAnnotation(b.b.a.q.a.class)) == null || (!z ? aVar.deserialize() : aVar.serialize()))) {
            return true;
        }
        if ((!this.e && f(field.getType())) || e(field.getType())) {
            return true;
        }
        List<b.b.a.a> list = z ? this.g : this.h;
        if (list.isEmpty()) {
            return false;
        }
        b.b.a.b bVar = new b.b.a.b(field);
        Iterator<b.b.a.a> it = list.iterator();
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

    public final boolean h(b.b.a.q.d dVar) {
        return dVar == null || dVar.value() <= this.f417c;
    }

    public final boolean i(b.b.a.q.e eVar) {
        return eVar == null || eVar.value() > this.f417c;
    }

    public final boolean j(b.b.a.q.d dVar, b.b.a.q.e eVar) {
        return h(dVar) && i(eVar);
    }
}
