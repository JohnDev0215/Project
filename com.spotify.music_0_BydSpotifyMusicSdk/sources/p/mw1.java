package p;

import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class mw1<T> implements f.e {
    final Class<T> a;
    final String b;
    final List<String> c;
    final List<Type> d;
    final com.squareup.moshi.f<Object> e;

    public static final class a extends com.squareup.moshi.f<Object> {
        final String a;
        final List<String> b;
        final List<Type> c;
        final List<com.squareup.moshi.f<Object>> d;
        final com.squareup.moshi.f<Object> e;
        final JsonReader.b f;
        final JsonReader.b g;

        public a(String str, List<String> list, List<Type> list2, List<com.squareup.moshi.f<Object>> list3, com.squareup.moshi.f<Object> fVar) {
            this.a = str;
            this.b = list;
            this.c = list2;
            this.d = list3;
            this.e = fVar;
            this.f = JsonReader.b.a(str);
            this.g = JsonReader.b.a((String[]) list.toArray(new String[0]));
        }

        private int a(JsonReader jsonReader) {
            jsonReader.c();
            while (jsonReader.hasNext()) {
                if (jsonReader.M0(this.f) != -1) {
                    int iN0 = jsonReader.N0(this.g);
                    if (iN0 != -1 || this.e != null) {
                        return iN0;
                    }
                    throw new JsonDataException("Expected one of " + this.b + " for key '" + this.a + "' but found '" + jsonReader.u() + "'. Register a subtype for this label.");
                }
                jsonReader.Q0();
                jsonReader.D();
            }
            throw new JsonDataException("Missing label for " + this.a);
        }

        @Override // com.squareup.moshi.f
        public Object fromJson(JsonReader jsonReader) throws IOException {
            JsonReader jsonReaderI0 = jsonReader.I0();
            jsonReaderI0.O0(false);
            try {
                int iA = a(jsonReaderI0);
                jsonReaderI0.close();
                return iA == -1 ? this.e.fromJson(jsonReader) : this.d.get(iA).fromJson(jsonReader);
            } catch (Throwable th) {
                jsonReaderI0.close();
                throw th;
            }
        }

        @Override // com.squareup.moshi.f
        public void toJson(com.squareup.moshi.m mVar, Object obj) {
            com.squareup.moshi.f<Object> fVar;
            int iIndexOf = this.c.indexOf(obj.getClass());
            if (iIndexOf == -1) {
                fVar = this.e;
                if (fVar == null) {
                    throw new IllegalArgumentException("Expected one of " + this.c + " but found " + obj + ", a " + obj.getClass() + ". Register this subtype.");
                }
            } else {
                fVar = this.d.get(iIndexOf);
            }
            mVar.i();
            if (fVar != this.e) {
                mVar.J0(this.a).W0(this.b.get(iIndexOf));
            }
            int iC = mVar.c();
            fVar.toJson(mVar, obj);
            mVar.J(iC);
            mVar.X();
        }

        public String toString() {
            return u4.m(new StringBuilder("PolymorphicJsonAdapter("), this.a, ")");
        }
    }

    public mw1(Class<T> cls, String str, List<String> list, List<Type> list2, com.squareup.moshi.f<Object> fVar) {
        this.a = cls;
        this.b = str;
        this.c = list;
        this.d = list2;
        this.e = fVar;
    }

    public static <T> mw1<T> b(Class<T> cls, String str) {
        if (cls == null) {
            throw new NullPointerException("baseType == null");
        }
        if (str != null) {
            return new mw1<>(cls, str, Collections.emptyList(), Collections.emptyList(), null);
        }
        throw new NullPointerException("labelKey == null");
    }

    @Override // com.squareup.moshi.f.e
    public com.squareup.moshi.f<?> a(Type type, Set<? extends Annotation> set, com.squareup.moshi.o oVar) {
        if (zo2.g(type) != this.a || !set.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(this.d.size());
        int size = this.d.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(oVar.d(this.d.get(i)));
        }
        return new a(this.b, this.c, this.d, arrayList, this.e).nullSafe();
    }

    public mw1<T> c(Class<? extends T> cls, String str) {
        if (cls == null) {
            throw new NullPointerException("subtype == null");
        }
        if (str == null) {
            throw new NullPointerException("label == null");
        }
        if (this.c.contains(str)) {
            throw new IllegalArgumentException("Labels must be unique.");
        }
        ArrayList arrayList = new ArrayList(this.c);
        arrayList.add(str);
        ArrayList arrayList2 = new ArrayList(this.d);
        arrayList2.add(cls);
        return new mw1<>(this.a, this.b, arrayList, arrayList2, this.e);
    }
}
