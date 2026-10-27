package com.squareup.moshi;

import com.squareup.moshi.f;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
abstract class e<C extends Collection<T>, T> extends f<C> {
    public static final f.e b = new a();
    private final f<T> a;

    public class a implements f.e {
        @Override // com.squareup.moshi.f.e
        public f<?> a(Type type, Set<? extends Annotation> set, o oVar) {
            Class<?> clsG = zo2.g(type);
            if (!set.isEmpty()) {
                return null;
            }
            if (clsG == List.class || clsG == Collection.class) {
                return e.b(type, oVar).nullSafe();
            }
            if (clsG == Set.class) {
                return e.d(type, oVar).nullSafe();
            }
            return null;
        }
    }

    public class b extends e<Collection<T>, T> {
        public b(f fVar) {
            super(fVar, null);
        }

        @Override // com.squareup.moshi.e
        public Collection<T> c() {
            return new ArrayList();
        }

        @Override // com.squareup.moshi.f
        public /* bridge */ /* synthetic */ Object fromJson(JsonReader jsonReader) {
            return super.a(jsonReader);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.squareup.moshi.f
        public /* bridge */ /* synthetic */ void toJson(m mVar, Object obj) {
            super.e(mVar, (Collection) obj);
        }
    }

    public class c extends e<Set<T>, T> {
        public c(f fVar) {
            super(fVar, null);
        }

        @Override // com.squareup.moshi.e
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public Set<T> c() {
            return new LinkedHashSet();
        }

        @Override // com.squareup.moshi.f
        public /* bridge */ /* synthetic */ Object fromJson(JsonReader jsonReader) {
            return super.a(jsonReader);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.squareup.moshi.f
        public /* bridge */ /* synthetic */ void toJson(m mVar, Object obj) {
            super.e(mVar, (Collection) obj);
        }
    }

    public /* synthetic */ e(f fVar, a aVar) {
        this(fVar);
    }

    public static <T> f<Collection<T>> b(Type type, o oVar) {
        return new b(oVar.d(zo2.c(type, Collection.class)));
    }

    public static <T> f<Set<T>> d(Type type, o oVar) {
        return new c(oVar.d(zo2.c(type, Collection.class)));
    }

    public C a(JsonReader jsonReader) {
        C c2 = (C) c();
        jsonReader.b();
        while (jsonReader.hasNext()) {
            c2.add(this.a.fromJson(jsonReader));
        }
        jsonReader.i();
        return c2;
    }

    public abstract C c();

    public void e(m mVar, C c2) {
        mVar.b();
        Iterator it = c2.iterator();
        while (it.hasNext()) {
            this.a.toJson(mVar, (T) it.next());
        }
        mVar.E();
    }

    public String toString() {
        return this.a + ".collection()";
    }

    private e(f<T> fVar) {
        this.a = fVar;
    }
}
