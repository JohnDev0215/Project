package com.squareup.moshi;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import p.ds2;
import p.u4;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
abstract class q {
    public static final f.e a = new c();
    static final com.squareup.moshi.f<Boolean> b = new d();
    static final com.squareup.moshi.f<Byte> c = new e();
    static final com.squareup.moshi.f<Character> d = new f();
    static final com.squareup.moshi.f<Double> e = new g();
    static final com.squareup.moshi.f<Float> f = new h();
    static final com.squareup.moshi.f<Integer> g = new i();
    static final com.squareup.moshi.f<Long> h = new j();
    static final com.squareup.moshi.f<Short> i = new k();
    static final com.squareup.moshi.f<String> j = new a();

    public class a extends com.squareup.moshi.f<String> {
        @Override // com.squareup.moshi.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public String fromJson(JsonReader jsonReader) {
            return jsonReader.u();
        }

        @Override // com.squareup.moshi.f
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void toJson(com.squareup.moshi.m mVar, String str) {
            mVar.W0(str);
        }

        public String toString() {
            return "JsonAdapter(String)";
        }
    }

    public static /* synthetic */ class b {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[JsonReader.Token.values().length];
            a = iArr;
            try {
                iArr[JsonReader.Token.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[JsonReader.Token.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[JsonReader.Token.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[JsonReader.Token.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[JsonReader.Token.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[JsonReader.Token.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public class c implements f.e {
        @Override // com.squareup.moshi.f.e
        public com.squareup.moshi.f<?> a(Type type, Set<? extends Annotation> set, o oVar) {
            if (!set.isEmpty()) {
                return null;
            }
            if (type == Boolean.TYPE) {
                return q.b;
            }
            if (type == Byte.TYPE) {
                return q.c;
            }
            if (type == Character.TYPE) {
                return q.d;
            }
            if (type == Double.TYPE) {
                return q.e;
            }
            if (type == Float.TYPE) {
                return q.f;
            }
            if (type == Integer.TYPE) {
                return q.g;
            }
            if (type == Long.TYPE) {
                return q.h;
            }
            if (type == Short.TYPE) {
                return q.i;
            }
            if (type == Boolean.class) {
                return q.b.nullSafe();
            }
            if (type == Byte.class) {
                return q.c.nullSafe();
            }
            if (type == Character.class) {
                return q.d.nullSafe();
            }
            if (type == Double.class) {
                return q.e.nullSafe();
            }
            if (type == Float.class) {
                return q.f.nullSafe();
            }
            if (type == Integer.class) {
                return q.g.nullSafe();
            }
            if (type == Long.class) {
                return q.h.nullSafe();
            }
            if (type == Short.class) {
                return q.i.nullSafe();
            }
            if (type == String.class) {
                return q.j.nullSafe();
            }
            if (type == Object.class) {
                return new m(oVar).nullSafe();
            }
            Class<?> clsG = zo2.g(type);
            com.squareup.moshi.f<?> fVarD = ds2.d(oVar, type, clsG);
            if (fVarD != null) {
                return fVarD;
            }
            if (clsG.isEnum()) {
                return new l(clsG).nullSafe();
            }
            return null;
        }
    }

    public class d extends com.squareup.moshi.f<Boolean> {
        @Override // com.squareup.moshi.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean fromJson(JsonReader jsonReader) {
            return Boolean.valueOf(jsonReader.w0());
        }

        @Override // com.squareup.moshi.f
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void toJson(com.squareup.moshi.m mVar, Boolean bool) {
            mVar.X0(bool.booleanValue());
        }

        public String toString() {
            return "JsonAdapter(Boolean)";
        }
    }

    public class e extends com.squareup.moshi.f<Byte> {
        @Override // com.squareup.moshi.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Byte fromJson(JsonReader jsonReader) {
            return Byte.valueOf((byte) q.a(jsonReader, "a byte", -128, 255));
        }

        @Override // com.squareup.moshi.f
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void toJson(com.squareup.moshi.m mVar, Byte b) {
            mVar.U0(b.intValue() & 255);
        }

        public String toString() {
            return "JsonAdapter(Byte)";
        }
    }

    public class f extends com.squareup.moshi.f<Character> {
        @Override // com.squareup.moshi.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Character fromJson(JsonReader jsonReader) {
            String strU = jsonReader.u();
            if (strU.length() <= 1) {
                return Character.valueOf(strU.charAt(0));
            }
            throw new JsonDataException(String.format("Expected %s but was %s at path %s", "a char", "\"" + strU + '\"', jsonReader.J()));
        }

        @Override // com.squareup.moshi.f
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void toJson(com.squareup.moshi.m mVar, Character ch) {
            mVar.W0(ch.toString());
        }

        public String toString() {
            return "JsonAdapter(Character)";
        }
    }

    public class g extends com.squareup.moshi.f<Double> {
        @Override // com.squareup.moshi.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Double fromJson(JsonReader jsonReader) {
            return Double.valueOf(jsonReader.T());
        }

        @Override // com.squareup.moshi.f
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void toJson(com.squareup.moshi.m mVar, Double d) {
            mVar.T0(d.doubleValue());
        }

        public String toString() {
            return "JsonAdapter(Double)";
        }
    }

    public class h extends com.squareup.moshi.f<Float> {
        @Override // com.squareup.moshi.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float fromJson(JsonReader jsonReader) {
            float fT = (float) jsonReader.T();
            if (jsonReader.X() || !Float.isInfinite(fT)) {
                return Float.valueOf(fT);
            }
            throw new JsonDataException("JSON forbids NaN and infinities: " + fT + " at path " + jsonReader.J());
        }

        @Override // com.squareup.moshi.f
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void toJson(com.squareup.moshi.m mVar, Float f) {
            f.getClass();
            mVar.V0(f);
        }

        public String toString() {
            return "JsonAdapter(Float)";
        }
    }

    public class i extends com.squareup.moshi.f<Integer> {
        @Override // com.squareup.moshi.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Integer fromJson(JsonReader jsonReader) {
            return Integer.valueOf(jsonReader.D0());
        }

        @Override // com.squareup.moshi.f
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void toJson(com.squareup.moshi.m mVar, Integer num) {
            mVar.U0(num.intValue());
        }

        public String toString() {
            return "JsonAdapter(Integer)";
        }
    }

    public class j extends com.squareup.moshi.f<Long> {
        @Override // com.squareup.moshi.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Long fromJson(JsonReader jsonReader) {
            return Long.valueOf(jsonReader.Z());
        }

        @Override // com.squareup.moshi.f
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void toJson(com.squareup.moshi.m mVar, Long l) {
            mVar.U0(l.longValue());
        }

        public String toString() {
            return "JsonAdapter(Long)";
        }
    }

    public class k extends com.squareup.moshi.f<Short> {
        @Override // com.squareup.moshi.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Short fromJson(JsonReader jsonReader) {
            return Short.valueOf((short) q.a(jsonReader, "a short", -32768, 32767));
        }

        @Override // com.squareup.moshi.f
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void toJson(com.squareup.moshi.m mVar, Short sh) {
            mVar.U0(sh.intValue());
        }

        public String toString() {
            return "JsonAdapter(Short)";
        }
    }

    public static final class l<T extends Enum<T>> extends com.squareup.moshi.f<T> {
        private final Class<T> a;
        private final String[] b;
        private final T[] c;
        private final JsonReader.b d;

        public l(Class<T> cls) {
            this.a = cls;
            try {
                T[] enumConstants = cls.getEnumConstants();
                this.c = enumConstants;
                this.b = new String[enumConstants.length];
                int i = 0;
                while (true) {
                    T[] tArr = this.c;
                    if (i >= tArr.length) {
                        this.d = JsonReader.b.a(this.b);
                        return;
                    } else {
                        String strName = tArr[i].name();
                        this.b[i] = ds2.m(strName, cls.getField(strName));
                        i++;
                    }
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError("Missing field in ".concat(cls.getName()), e);
            }
        }

        @Override // com.squareup.moshi.f
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public T fromJson(JsonReader jsonReader) {
            int iN0 = jsonReader.N0(this.d);
            if (iN0 != -1) {
                return this.c[iN0];
            }
            String strJ = jsonReader.J();
            throw new JsonDataException("Expected one of " + Arrays.asList(this.b) + " but was " + jsonReader.u() + " at path " + strJ);
        }

        @Override // com.squareup.moshi.f
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void toJson(com.squareup.moshi.m mVar, T t) {
            mVar.W0(this.b[t.ordinal()]);
        }

        public String toString() {
            return u4.d(this.a, new StringBuilder("JsonAdapter("), ")");
        }
    }

    public static final class m extends com.squareup.moshi.f<Object> {
        private final o a;
        private final com.squareup.moshi.f<List> b;
        private final com.squareup.moshi.f<Map> c;
        private final com.squareup.moshi.f<String> d;
        private final com.squareup.moshi.f<Double> e;
        private final com.squareup.moshi.f<Boolean> f;

        public m(o oVar) {
            this.a = oVar;
            this.b = oVar.c(List.class);
            this.c = oVar.c(Map.class);
            this.d = oVar.c(String.class);
            this.e = oVar.c(Double.class);
            this.f = oVar.c(Boolean.class);
        }

        private Class<?> a(Class<?> cls) {
            return Map.class.isAssignableFrom(cls) ? Map.class : Collection.class.isAssignableFrom(cls) ? Collection.class : cls;
        }

        @Override // com.squareup.moshi.f
        public Object fromJson(JsonReader jsonReader) {
            switch (b.a[jsonReader.H0().ordinal()]) {
                case 1:
                    return this.b.fromJson(jsonReader);
                case 2:
                    return this.c.fromJson(jsonReader);
                case 3:
                    return this.d.fromJson(jsonReader);
                case 4:
                    return this.e.fromJson(jsonReader);
                case 5:
                    return this.f.fromJson(jsonReader);
                case 6:
                    return jsonReader.o0();
                default:
                    throw new IllegalStateException("Expected a value but was " + jsonReader.H0() + " at path " + jsonReader.J());
            }
        }

        @Override // com.squareup.moshi.f
        public void toJson(com.squareup.moshi.m mVar, Object obj) {
            Class<?> cls = obj.getClass();
            if (cls != Object.class) {
                this.a.e(a(cls), ds2.a).toJson(mVar, obj);
            } else {
                mVar.i();
                mVar.X();
            }
        }

        public String toString() {
            return "JsonAdapter(Object)";
        }
    }

    public static int a(JsonReader jsonReader, String str, int i2, int i3) {
        int iD0 = jsonReader.D0();
        if (iD0 < i2 || iD0 > i3) {
            throw new JsonDataException(String.format("Expected %s but was %s at path %s", str, Integer.valueOf(iD0), jsonReader.J()));
        }
        return iD0;
    }
}
