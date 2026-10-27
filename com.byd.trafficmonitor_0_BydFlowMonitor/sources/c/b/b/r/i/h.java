package c.b.b.r.i;

import c.b.b.o;
import c.b.b.p;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: compiled from: ObjectTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class h extends o<Object> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f4238a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c.b.b.d f4239b;

    /* JADX INFO: compiled from: ObjectTypeAdapter.java */
    public static class a implements p {
        @Override // c.b.b.p
        public <T> o<T> a(c.b.b.d dVar, c.b.b.s.a<T> aVar) {
            if (aVar.d() == Object.class) {
                return new h(dVar);
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: ObjectTypeAdapter.java */
    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f4240a;

        static {
            int[] iArr = new int[JsonToken.values().length];
            f4240a = iArr;
            try {
                iArr[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4240a[JsonToken.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4240a[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4240a[JsonToken.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f4240a[JsonToken.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f4240a[JsonToken.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public h(c.b.b.d dVar) {
        this.f4239b = dVar;
    }

    @Override // c.b.b.o
    public Object b(c.b.b.t.a aVar) throws IOException {
        switch (b.f4240a[aVar.k0().ordinal()]) {
            case 1:
                ArrayList arrayList = new ArrayList();
                aVar.z();
                while (aVar.W()) {
                    arrayList.add(b(aVar));
                }
                aVar.S();
                return arrayList;
            case 2:
                LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
                aVar.O();
                while (aVar.W()) {
                    linkedTreeMap.put(aVar.e0(), b(aVar));
                }
                aVar.T();
                return linkedTreeMap;
            case 3:
                return aVar.i0();
            case 4:
                return Double.valueOf(aVar.b0());
            case 5:
                return Boolean.valueOf(aVar.a0());
            case 6:
                aVar.g0();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    @Override // c.b.b.o
    public void d(c.b.b.t.b bVar, Object obj) throws IOException {
        if (obj == null) {
            bVar.Z();
            return;
        }
        o oVarL = this.f4239b.l(obj.getClass());
        if (!(oVarL instanceof h)) {
            oVarL.d(bVar, obj);
        } else {
            bVar.P();
            bVar.S();
        }
    }
}
