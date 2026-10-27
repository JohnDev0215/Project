package n6;

import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.util.ArrayList;
import k6.o;
import k6.p;

/* JADX INFO: compiled from: ObjectTypeAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h extends o<Object> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f6579a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k6.d f6580b;

    /* JADX INFO: compiled from: ObjectTypeAdapter.java */
    public static class a implements p {
        @Override // k6.p
        public <T> o<T> a(k6.d dVar, p6.a<T> aVar) {
            if (aVar.d() == Object.class) {
                return new h(dVar);
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: ObjectTypeAdapter.java */
    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f6581a;

        static {
            int[] iArr = new int[JsonToken.values().length];
            f6581a = iArr;
            try {
                iArr[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f6581a[JsonToken.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f6581a[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f6581a[JsonToken.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f6581a[JsonToken.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f6581a[JsonToken.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public h(k6.d dVar) {
        this.f6580b = dVar;
    }

    @Override // k6.o
    public Object b(q6.a aVar) throws IOException {
        switch (b.f6581a[aVar.R().ordinal()]) {
            case 1:
                ArrayList arrayList = new ArrayList();
                aVar.g();
                while (aVar.t()) {
                    arrayList.add(b(aVar));
                }
                aVar.o();
                return arrayList;
            case 2:
                LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
                aVar.i();
                while (aVar.t()) {
                    linkedTreeMap.put(aVar.I(), b(aVar));
                }
                aVar.q();
                return linkedTreeMap;
            case 3:
                return aVar.O();
            case 4:
                return Double.valueOf(aVar.C());
            case 5:
                return Boolean.valueOf(aVar.B());
            case 6:
                aVar.M();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    @Override // k6.o
    public void d(q6.b bVar, Object obj) throws IOException {
        if (obj == null) {
            bVar.B();
            return;
        }
        o oVarL = this.f6580b.l(obj.getClass());
        if (!(oVarL instanceof h)) {
            oVarL.d(bVar, obj);
        } else {
            bVar.j();
            bVar.o();
        }
    }
}
