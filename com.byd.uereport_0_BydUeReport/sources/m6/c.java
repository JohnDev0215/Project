package m6;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import k6.o;
import k6.p;

/* JADX INFO: compiled from: Excluder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c implements p, Cloneable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final c f6409d = new c();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f6413h;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f6410e = -1.0d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f6411f = 136;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f6412g = true;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public List<k6.a> f6414i = Collections.emptyList();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public List<k6.a> f6415j = Collections.emptyList();

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: Excluder.java */
    public class a<T> extends o<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public o<T> f6416a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f6417b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ boolean f6418c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ k6.d f6419d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ p6.a f6420e;

        public a(boolean z7, boolean z8, k6.d dVar, p6.a aVar) {
            this.f6417b = z7;
            this.f6418c = z8;
            this.f6419d = dVar;
            this.f6420e = aVar;
        }

        @Override // k6.o
        public T b(q6.a aVar) throws IOException {
            if (!this.f6417b) {
                return e().b(aVar);
            }
            aVar.g0();
            return null;
        }

        @Override // k6.o
        public void d(q6.b bVar, T t7) throws IOException {
            if (this.f6418c) {
                bVar.B();
            } else {
                e().d(bVar, t7);
            }
        }

        public final o<T> e() {
            o<T> oVar = this.f6416a;
            if (oVar != null) {
                return oVar;
            }
            o<T> oVarM = this.f6419d.m(c.this, this.f6420e);
            this.f6416a = oVarM;
            return oVarM;
        }
    }

    @Override // k6.p
    public <T> o<T> a(k6.d dVar, p6.a<T> aVar) {
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
        } catch (CloneNotSupportedException e8) {
            throw new AssertionError(e8);
        }
    }

    public boolean c(Class<?> cls, boolean z7) {
        if (this.f6410e != -1.0d && !j((l6.d) cls.getAnnotation(l6.d.class), (l6.e) cls.getAnnotation(l6.e.class))) {
            return true;
        }
        if ((!this.f6412g && f(cls)) || e(cls)) {
            return true;
        }
        Iterator<k6.a> it = (z7 ? this.f6414i : this.f6415j).iterator();
        while (it.hasNext()) {
            if (it.next().a(cls)) {
                return true;
            }
        }
        return false;
    }

    public boolean d(Field field, boolean z7) {
        l6.a aVar;
        if ((this.f6411f & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.f6410e != -1.0d && !j((l6.d) field.getAnnotation(l6.d.class), (l6.e) field.getAnnotation(l6.e.class))) || field.isSynthetic()) {
            return true;
        }
        if (this.f6413h && ((aVar = (l6.a) field.getAnnotation(l6.a.class)) == null || (!z7 ? aVar.deserialize() : aVar.serialize()))) {
            return true;
        }
        if ((!this.f6412g && f(field.getType())) || e(field.getType())) {
            return true;
        }
        List<k6.a> list = z7 ? this.f6414i : this.f6415j;
        if (list.isEmpty()) {
            return false;
        }
        k6.b bVar = new k6.b(field);
        Iterator<k6.a> it = list.iterator();
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

    public final boolean h(l6.d dVar) {
        return dVar == null || dVar.value() <= this.f6410e;
    }

    public final boolean i(l6.e eVar) {
        return eVar == null || eVar.value() > this.f6410e;
    }

    public final boolean j(l6.d dVar, l6.e eVar) {
        return h(dVar) && i(eVar);
    }
}
