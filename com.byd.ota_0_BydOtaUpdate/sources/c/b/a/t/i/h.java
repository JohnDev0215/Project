package c.b.a.t.i;

import c.b.a.q;
import c.b.a.r;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: compiled from: ObjectTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class h extends q<Object> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final r f2975a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c.b.a.e f2976b;

    /* JADX INFO: compiled from: ObjectTypeAdapter.java */
    public static class a implements r {
        @Override // c.b.a.r
        public <T> q<T> a(c.b.a.e eVar, c.b.a.u.a<T> aVar) {
            if (aVar.c() == Object.class) {
                return new h(eVar);
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: ObjectTypeAdapter.java */
    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f2977a;

        static {
            int[] iArr = new int[JsonToken.values().length];
            f2977a = iArr;
            try {
                iArr[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2977a[JsonToken.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2977a[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2977a[JsonToken.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2977a[JsonToken.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2977a[JsonToken.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public h(c.b.a.e eVar) {
        this.f2976b = eVar;
    }

    @Override // c.b.a.q
    public Object b(c.b.a.v.a aVar) throws IOException {
        switch (b.f2977a[aVar.b0().ordinal()]) {
            case 1:
                ArrayList arrayList = new ArrayList();
                aVar.d();
                while (aVar.N()) {
                    arrayList.add(b(aVar));
                }
                aVar.J();
                return arrayList;
            case 2:
                LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
                aVar.s();
                while (aVar.N()) {
                    linkedTreeMap.put(aVar.V(), b(aVar));
                }
                aVar.K();
                return linkedTreeMap;
            case 3:
                return aVar.Z();
            case 4:
                return Double.valueOf(aVar.S());
            case 5:
                return Boolean.valueOf(aVar.R());
            case 6:
                aVar.X();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    @Override // c.b.a.q
    public void d(c.b.a.v.b bVar, Object obj) throws IOException {
        if (obj == null) {
            bVar.P();
            return;
        }
        q qVarN = this.f2976b.n(obj.getClass());
        if (!(qVarN instanceof h)) {
            qVarN.d(bVar, obj);
        } else {
            bVar.v();
            bVar.J();
        }
    }
}
