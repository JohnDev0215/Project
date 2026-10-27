package b.c.a.r.i;

import b.c.a.o;
import b.c.a.p;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: compiled from: ObjectTypeAdapter.java */
/* JADX INFO: loaded from: classes3.dex */
public final class h extends o<Object> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f2266a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b.c.a.d f2267b;

    /* JADX INFO: compiled from: ObjectTypeAdapter.java */
    public static class a implements p {
        @Override // b.c.a.p
        public <T> o<T> a(b.c.a.d dVar, b.c.a.s.a<T> aVar) {
            if (aVar.d() == Object.class) {
                return new h(dVar);
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: ObjectTypeAdapter.java */
    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f2268a;

        static {
            int[] iArr = new int[JsonToken.values().length];
            f2268a = iArr;
            try {
                iArr[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2268a[JsonToken.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2268a[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2268a[JsonToken.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2268a[JsonToken.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2268a[JsonToken.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public h(b.c.a.d dVar) {
        this.f2267b = dVar;
    }

    @Override // b.c.a.o
    public Object b(b.c.a.t.a aVar) throws IOException {
        switch (b.f2268a[aVar.E().ordinal()]) {
            case 1:
                ArrayList arrayList = new ArrayList();
                aVar.c();
                while (aVar.n()) {
                    arrayList.add(b(aVar));
                }
                aVar.i();
                return arrayList;
            case 2:
                LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
                aVar.e();
                while (aVar.n()) {
                    linkedTreeMap.put(aVar.x(), b(aVar));
                }
                aVar.j();
                return linkedTreeMap;
            case 3:
                return aVar.B();
            case 4:
                return Double.valueOf(aVar.u());
            case 5:
                return Boolean.valueOf(aVar.t());
            case 6:
                aVar.z();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    @Override // b.c.a.o
    public void d(b.c.a.t.b bVar, Object obj) throws IOException {
        if (obj == null) {
            bVar.s();
            return;
        }
        o oVarL = this.f2267b.l(obj.getClass());
        if (!(oVarL instanceof h)) {
            oVarL.d(bVar, obj);
        } else {
            bVar.f();
            bVar.i();
        }
    }
}
