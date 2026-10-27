package com.google.gson.internal.bind;

import com.google.gson.b;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import p.f60;
import p.lo2;
import p.qo2;
import p.t71;
import p.x42;
import p.xo1;
import p.z71;

/* JADX INFO: loaded from: classes.dex */
public final class CollectionTypeAdapterFactory implements lo2 {
    public final x42 c;

    public static final class Adapter<E> extends b {
        public final b a;
        public final xo1 b;

        public Adapter(com.google.gson.a aVar, Type type, b bVar, xo1 xo1Var) {
            this.a = new TypeAdapterRuntimeTypeWrapper(aVar, bVar, type);
            this.b = xo1Var;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.gson.b
        public final Object b(t71 t71Var) throws IOException {
            if (t71Var.K0() == JsonToken.q) {
                t71Var.z0();
                return null;
            }
            Collection collection = (Collection) this.b.k();
            t71Var.b();
            while (t71Var.hasNext()) {
                collection.add(((TypeAdapterRuntimeTypeWrapper) this.a).b.b(t71Var));
            }
            t71Var.E();
            return collection;
        }

        @Override // com.google.gson.b
        public final void c(z71 z71Var, Object obj) throws IOException {
            Collection collection = (Collection) obj;
            if (collection == null) {
                z71Var.E0();
                return;
            }
            z71Var.c();
            Iterator<E> it = collection.iterator();
            while (it.hasNext()) {
                this.a.c(z71Var, it.next());
            }
            z71Var.E();
        }
    }

    public CollectionTypeAdapterFactory(x42 x42Var) {
        this.c = x42Var;
    }

    @Override // p.lo2
    public final b a(com.google.gson.a aVar, qo2 qo2Var) {
        Type type = qo2Var.b;
        Class cls = qo2Var.a;
        if (!Collection.class.isAssignableFrom(cls)) {
            return null;
        }
        if (type instanceof WildcardType) {
            type = ((WildcardType) type).getUpperBounds()[0];
        }
        f60.e(Collection.class.isAssignableFrom(cls));
        Type typeG = com.google.gson.internal.a.g(type, cls, com.google.gson.internal.a.e(type, cls, Collection.class), new HashMap());
        if (typeG instanceof WildcardType) {
            typeG = ((WildcardType) typeG).getUpperBounds()[0];
        }
        Class cls2 = typeG instanceof ParameterizedType ? ((ParameterizedType) typeG).getActualTypeArguments()[0] : Object.class;
        return new Adapter(aVar, cls2, aVar.d(new qo2(cls2)), this.c.d(qo2Var));
    }
}
