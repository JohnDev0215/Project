package com.google.gson.internal.k;

import com.google.gson.ToNumberPolicy;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.o;
import com.google.gson.p;
import com.google.gson.q;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: compiled from: ObjectTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class j extends p<Object> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final q f4076a = f(ToNumberPolicy.DOUBLE);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.google.gson.d f4077b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final o f4078c;

    /* JADX INFO: compiled from: ObjectTypeAdapter.java */
    class a implements q {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ o f4079b;

        a(o oVar) {
            this.f4079b = oVar;
        }

        @Override // com.google.gson.q
        public <T> p<T> a(com.google.gson.d dVar, com.google.gson.s.a<T> aVar) {
            a aVar2 = null;
            if (aVar.c() == Object.class) {
                return new j(dVar, this.f4079b, aVar2);
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: ObjectTypeAdapter.java */
    static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f4080a;

        static {
            int[] iArr = new int[JsonToken.values().length];
            f4080a = iArr;
            try {
                iArr[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4080a[JsonToken.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4080a[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4080a[JsonToken.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f4080a[JsonToken.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f4080a[JsonToken.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* synthetic */ j(com.google.gson.d dVar, o oVar, a aVar) {
        this(dVar, oVar);
    }

    public static q e(o oVar) {
        return oVar == ToNumberPolicy.DOUBLE ? f4076a : f(oVar);
    }

    private static q f(o oVar) {
        return new a(oVar);
    }

    @Override // com.google.gson.p
    public Object b(com.google.gson.stream.a aVar) throws IOException {
        switch (b.f4080a[aVar.x().ordinal()]) {
            case 1:
                ArrayList arrayList = new ArrayList();
                aVar.a();
                while (aVar.j()) {
                    arrayList.add(b(aVar));
                }
                aVar.f();
                return arrayList;
            case 2:
                LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
                aVar.b();
                while (aVar.j()) {
                    linkedTreeMap.put(aVar.r(), b(aVar));
                }
                aVar.g();
                return linkedTreeMap;
            case 3:
                return aVar.v();
            case 4:
                return this.f4078c.readNumber(aVar);
            case 5:
                return Boolean.valueOf(aVar.n());
            case 6:
                aVar.t();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    @Override // com.google.gson.p
    public void d(com.google.gson.stream.b bVar, Object obj) throws IOException {
        if (obj == null) {
            bVar.m();
            return;
        }
        p pVarL = this.f4077b.l(obj.getClass());
        if (!(pVarL instanceof j)) {
            pVarL.d(bVar, obj);
        } else {
            bVar.d();
            bVar.g();
        }
    }

    private j(com.google.gson.d dVar, o oVar) {
        this.f4077b = dVar;
        this.f4078c = oVar;
    }
}
