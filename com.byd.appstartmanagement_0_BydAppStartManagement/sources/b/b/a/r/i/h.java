package b.b.a.r.i;

import b.b.a.o;
import b.b.a.p;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: compiled from: ObjectTypeAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h extends o<Object> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f450a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b.b.a.d f451b;

    /* JADX INFO: compiled from: ObjectTypeAdapter.java */
    public static class a implements p {
        @Override // b.b.a.p
        public <T> o<T> a(b.b.a.d dVar, b.b.a.s.a<T> aVar) {
            if (aVar.d() == Object.class) {
                return new h(dVar);
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: ObjectTypeAdapter.java */
    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f452a;

        static {
            int[] iArr = new int[JsonToken.values().length];
            f452a = iArr;
            try {
                iArr[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f452a[JsonToken.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f452a[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f452a[JsonToken.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f452a[JsonToken.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f452a[JsonToken.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public h(b.b.a.d dVar) {
        this.f451b = dVar;
    }

    @Override // b.b.a.o
    public Object b(b.b.a.t.a aVar) throws IOException {
        switch (b.f452a[aVar.y().ordinal()]) {
            case 1:
                ArrayList arrayList = new ArrayList();
                aVar.b();
                while (aVar.k()) {
                    arrayList.add(b(aVar));
                }
                aVar.g();
                return arrayList;
            case 2:
                LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
                aVar.c();
                while (aVar.k()) {
                    linkedTreeMap.put(aVar.s(), b(aVar));
                }
                aVar.h();
                return linkedTreeMap;
            case 3:
                return aVar.w();
            case 4:
                return Double.valueOf(aVar.p());
            case 5:
                return Boolean.valueOf(aVar.o());
            case 6:
                aVar.u();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    @Override // b.b.a.o
    public void d(b.b.a.t.b bVar, Object obj) throws IOException {
        if (obj == null) {
            bVar.n();
            return;
        }
        o oVarL = this.f451b.l(obj.getClass());
        if (!(oVarL instanceof h)) {
            oVarL.d(bVar, obj);
        } else {
            bVar.d();
            bVar.g();
        }
    }
}
