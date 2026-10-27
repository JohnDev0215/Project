package c.b.a.t.i;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* JADX INFO: compiled from: TypeAdapters.java */
/* JADX INFO: loaded from: classes.dex */
public final class n {
    public static final c.b.a.q<String> A;
    public static final c.b.a.q<BigDecimal> B;
    public static final c.b.a.q<BigInteger> C;
    public static final c.b.a.r D;
    public static final c.b.a.q<StringBuilder> E;
    public static final c.b.a.r F;
    public static final c.b.a.q<StringBuffer> G;
    public static final c.b.a.r H;
    public static final c.b.a.q<URL> I;
    public static final c.b.a.r J;
    public static final c.b.a.q<URI> K;
    public static final c.b.a.r L;
    public static final c.b.a.q<InetAddress> M;
    public static final c.b.a.r N;
    public static final c.b.a.q<UUID> O;
    public static final c.b.a.r P;
    public static final c.b.a.q<Currency> Q;
    public static final c.b.a.r R;
    public static final c.b.a.r S;
    public static final c.b.a.q<Calendar> T;
    public static final c.b.a.r U;
    public static final c.b.a.q<Locale> V;
    public static final c.b.a.r W;
    public static final c.b.a.q<c.b.a.k> X;
    public static final c.b.a.r Y;
    public static final c.b.a.r Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c.b.a.q<Class> f3008a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c.b.a.r f3009b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c.b.a.q<BitSet> f3010c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final c.b.a.r f3011d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final c.b.a.q<Boolean> f3012e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final c.b.a.q<Boolean> f3013f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final c.b.a.r f3014g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final c.b.a.q<Number> f3015h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final c.b.a.r f3016i;
    public static final c.b.a.q<Number> j;
    public static final c.b.a.r k;
    public static final c.b.a.q<Number> l;
    public static final c.b.a.r m;
    public static final c.b.a.q<AtomicInteger> n;
    public static final c.b.a.r o;
    public static final c.b.a.q<AtomicBoolean> p;
    public static final c.b.a.r q;
    public static final c.b.a.q<AtomicIntegerArray> r;
    public static final c.b.a.r s;
    public static final c.b.a.q<Number> t;
    public static final c.b.a.q<Number> u;
    public static final c.b.a.q<Number> v;
    public static final c.b.a.q<Number> w;
    public static final c.b.a.r x;
    public static final c.b.a.q<Character> y;
    public static final c.b.a.r z;

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class a extends c.b.a.q<AtomicIntegerArray> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicIntegerArray b(c.b.a.v.a aVar) throws IOException {
            ArrayList arrayList = new ArrayList();
            aVar.d();
            while (aVar.N()) {
                try {
                    arrayList.add(Integer.valueOf(aVar.T()));
                } catch (NumberFormatException e2) {
                    throw new JsonSyntaxException(e2);
                }
            }
            aVar.J();
            int size = arrayList.size();
            AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
            for (int i2 = 0; i2 < size; i2++) {
                atomicIntegerArray.set(i2, ((Integer) arrayList.get(i2)).intValue());
            }
            return atomicIntegerArray;
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, AtomicIntegerArray atomicIntegerArray) throws IOException {
            bVar.s();
            int length = atomicIntegerArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                bVar.Z(atomicIntegerArray.get(i2));
            }
            bVar.I();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class a0 implements c.b.a.r {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Class f3017c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ c.b.a.q f3018d;

        /* JADX INFO: Add missing generic type declarations: [T1] */
        /* JADX INFO: compiled from: TypeAdapters.java */
        public class a<T1> extends c.b.a.q<T1> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Class f3019a;

            public a(Class cls) {
                this.f3019a = cls;
            }

            @Override // c.b.a.q
            public T1 b(c.b.a.v.a aVar) throws IOException {
                T1 t1 = (T1) a0.this.f3018d.b(aVar);
                if (t1 == null || this.f3019a.isInstance(t1)) {
                    return t1;
                }
                throw new JsonSyntaxException("Expected a " + this.f3019a.getName() + " but was " + t1.getClass().getName());
            }

            @Override // c.b.a.q
            public void d(c.b.a.v.b bVar, T1 t1) throws IOException {
                a0.this.f3018d.d(bVar, t1);
            }
        }

        public a0(Class cls, c.b.a.q qVar) {
            this.f3017c = cls;
            this.f3018d = qVar;
        }

        @Override // c.b.a.r
        public <T2> c.b.a.q<T2> a(c.b.a.e eVar, c.b.a.u.a<T2> aVar) {
            Class<? super T2> clsC = aVar.c();
            if (this.f3017c.isAssignableFrom(clsC)) {
                return new a(clsC);
            }
            return null;
        }

        public String toString() {
            return "Factory[typeHierarchy=" + this.f3017c.getName() + ",adapter=" + this.f3018d + "]";
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class b extends c.b.a.q<Number> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() == JsonToken.NULL) {
                aVar.X();
                return null;
            }
            try {
                return Long.valueOf(aVar.U());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, Number number) throws IOException {
            bVar.b0(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static /* synthetic */ class b0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f3021a;

        static {
            int[] iArr = new int[JsonToken.values().length];
            f3021a = iArr;
            try {
                iArr[JsonToken.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3021a[JsonToken.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3021a[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3021a[JsonToken.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3021a[JsonToken.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3021a[JsonToken.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3021a[JsonToken.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f3021a[JsonToken.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f3021a[JsonToken.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f3021a[JsonToken.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class c extends c.b.a.q<Number> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() != JsonToken.NULL) {
                return Float.valueOf((float) aVar.S());
            }
            aVar.X();
            return null;
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, Number number) throws IOException {
            bVar.b0(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class c0 extends c.b.a.q<Boolean> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Boolean b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() != JsonToken.NULL) {
                return aVar.b0() == JsonToken.STRING ? Boolean.valueOf(Boolean.parseBoolean(aVar.Z())) : Boolean.valueOf(aVar.R());
            }
            aVar.X();
            return null;
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, Boolean bool) throws IOException {
            bVar.a0(bool);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class d extends c.b.a.q<Number> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() != JsonToken.NULL) {
                return Double.valueOf(aVar.S());
            }
            aVar.X();
            return null;
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, Number number) throws IOException {
            bVar.b0(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class d0 extends c.b.a.q<Boolean> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Boolean b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() != JsonToken.NULL) {
                return Boolean.valueOf(aVar.Z());
            }
            aVar.X();
            return null;
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, Boolean bool) throws IOException {
            bVar.c0(bool == null ? "null" : bool.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class e extends c.b.a.q<Number> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(c.b.a.v.a aVar) throws IOException {
            JsonToken jsonTokenB0 = aVar.b0();
            int i2 = b0.f3021a[jsonTokenB0.ordinal()];
            if (i2 == 1) {
                return new LazilyParsedNumber(aVar.Z());
            }
            if (i2 == 4) {
                aVar.X();
                return null;
            }
            throw new JsonSyntaxException("Expecting number, got: " + jsonTokenB0);
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, Number number) throws IOException {
            bVar.b0(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class e0 extends c.b.a.q<Number> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() == JsonToken.NULL) {
                aVar.X();
                return null;
            }
            try {
                return Byte.valueOf((byte) aVar.T());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, Number number) throws IOException {
            bVar.b0(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class f extends c.b.a.q<Character> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Character b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() == JsonToken.NULL) {
                aVar.X();
                return null;
            }
            String strZ = aVar.Z();
            if (strZ.length() == 1) {
                return Character.valueOf(strZ.charAt(0));
            }
            throw new JsonSyntaxException("Expecting character, got: " + strZ);
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, Character ch) throws IOException {
            bVar.c0(ch == null ? null : String.valueOf(ch));
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class f0 extends c.b.a.q<Number> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() == JsonToken.NULL) {
                aVar.X();
                return null;
            }
            try {
                return Short.valueOf((short) aVar.T());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, Number number) throws IOException {
            bVar.b0(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class g extends c.b.a.q<String> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public String b(c.b.a.v.a aVar) throws IOException {
            JsonToken jsonTokenB0 = aVar.b0();
            if (jsonTokenB0 != JsonToken.NULL) {
                return jsonTokenB0 == JsonToken.BOOLEAN ? Boolean.toString(aVar.R()) : aVar.Z();
            }
            aVar.X();
            return null;
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, String str) throws IOException {
            bVar.c0(str);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class g0 extends c.b.a.q<Number> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() == JsonToken.NULL) {
                aVar.X();
                return null;
            }
            try {
                return Integer.valueOf(aVar.T());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, Number number) throws IOException {
            bVar.b0(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class h extends c.b.a.q<BigDecimal> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public BigDecimal b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() == JsonToken.NULL) {
                aVar.X();
                return null;
            }
            try {
                return new BigDecimal(aVar.Z());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, BigDecimal bigDecimal) throws IOException {
            bVar.b0(bigDecimal);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class h0 extends c.b.a.q<AtomicInteger> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicInteger b(c.b.a.v.a aVar) throws IOException {
            try {
                return new AtomicInteger(aVar.T());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, AtomicInteger atomicInteger) throws IOException {
            bVar.Z(atomicInteger.get());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class i extends c.b.a.q<BigInteger> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public BigInteger b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() == JsonToken.NULL) {
                aVar.X();
                return null;
            }
            try {
                return new BigInteger(aVar.Z());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, BigInteger bigInteger) throws IOException {
            bVar.b0(bigInteger);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class i0 extends c.b.a.q<AtomicBoolean> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicBoolean b(c.b.a.v.a aVar) throws IOException {
            return new AtomicBoolean(aVar.R());
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, AtomicBoolean atomicBoolean) throws IOException {
            bVar.d0(atomicBoolean.get());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class j extends c.b.a.q<StringBuilder> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public StringBuilder b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() != JsonToken.NULL) {
                return new StringBuilder(aVar.Z());
            }
            aVar.X();
            return null;
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, StringBuilder sb) throws IOException {
            bVar.c0(sb == null ? null : sb.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static final class j0<T extends Enum<T>> extends c.b.a.q<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Map<String, T> f3022a = new HashMap();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Map<T, String> f3023b = new HashMap();

        public j0(Class<T> cls) {
            try {
                for (T t : cls.getEnumConstants()) {
                    String strName = t.name();
                    c.b.a.s.c cVar = (c.b.a.s.c) cls.getField(strName).getAnnotation(c.b.a.s.c.class);
                    if (cVar != null) {
                        strName = cVar.value();
                        for (String str : cVar.alternate()) {
                            this.f3022a.put(str, t);
                        }
                    }
                    this.f3022a.put(strName, t);
                    this.f3023b.put(t, strName);
                }
            } catch (NoSuchFieldException e2) {
                throw new AssertionError(e2);
            }
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public T b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() != JsonToken.NULL) {
                return this.f3022a.get(aVar.Z());
            }
            aVar.X();
            return null;
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, T t) throws IOException {
            bVar.c0(t == null ? null : this.f3023b.get(t));
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class k extends c.b.a.q<Class> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Class b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() != JsonToken.NULL) {
                throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
            }
            aVar.X();
            return null;
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, Class cls) throws IOException {
            if (cls == null) {
                bVar.P();
                return;
            }
            throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?");
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class l extends c.b.a.q<StringBuffer> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public StringBuffer b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() != JsonToken.NULL) {
                return new StringBuffer(aVar.Z());
            }
            aVar.X();
            return null;
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, StringBuffer stringBuffer) throws IOException {
            bVar.c0(stringBuffer == null ? null : stringBuffer.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class m extends c.b.a.q<URL> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public URL b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() == JsonToken.NULL) {
                aVar.X();
                return null;
            }
            String strZ = aVar.Z();
            if ("null".equals(strZ)) {
                return null;
            }
            return new URL(strZ);
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, URL url) throws IOException {
            bVar.c0(url == null ? null : url.toExternalForm());
        }
    }

    /* JADX INFO: renamed from: c.b.a.t.i.n$n, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class C0070n extends c.b.a.q<URI> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public URI b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() == JsonToken.NULL) {
                aVar.X();
                return null;
            }
            try {
                String strZ = aVar.Z();
                if ("null".equals(strZ)) {
                    return null;
                }
                return new URI(strZ);
            } catch (URISyntaxException e2) {
                throw new JsonIOException(e2);
            }
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, URI uri) throws IOException {
            bVar.c0(uri == null ? null : uri.toASCIIString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class o extends c.b.a.q<InetAddress> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public InetAddress b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() != JsonToken.NULL) {
                return InetAddress.getByName(aVar.Z());
            }
            aVar.X();
            return null;
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, InetAddress inetAddress) throws IOException {
            bVar.c0(inetAddress == null ? null : inetAddress.getHostAddress());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class p extends c.b.a.q<UUID> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public UUID b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() != JsonToken.NULL) {
                return UUID.fromString(aVar.Z());
            }
            aVar.X();
            return null;
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, UUID uuid) throws IOException {
            bVar.c0(uuid == null ? null : uuid.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class q extends c.b.a.q<Currency> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Currency b(c.b.a.v.a aVar) throws IOException {
            return Currency.getInstance(aVar.Z());
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, Currency currency) throws IOException {
            bVar.c0(currency.getCurrencyCode());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class r implements c.b.a.r {

        /* JADX INFO: compiled from: TypeAdapters.java */
        public class a extends c.b.a.q<Timestamp> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ c.b.a.q f3024a;

            public a(c.b.a.q qVar) {
                this.f3024a = qVar;
            }

            @Override // c.b.a.q
            /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
            public Timestamp b(c.b.a.v.a aVar) throws IOException {
                Date date = (Date) this.f3024a.b(aVar);
                if (date != null) {
                    return new Timestamp(date.getTime());
                }
                return null;
            }

            @Override // c.b.a.q
            /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
            public void d(c.b.a.v.b bVar, Timestamp timestamp) throws IOException {
                this.f3024a.d(bVar, timestamp);
            }
        }

        @Override // c.b.a.r
        public <T> c.b.a.q<T> a(c.b.a.e eVar, c.b.a.u.a<T> aVar) {
            if (aVar.c() != Timestamp.class) {
                return null;
            }
            return new a(eVar.n(Date.class));
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class s extends c.b.a.q<Calendar> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Calendar b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() == JsonToken.NULL) {
                aVar.X();
                return null;
            }
            aVar.s();
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            while (aVar.b0() != JsonToken.END_OBJECT) {
                String strV = aVar.V();
                int iT = aVar.T();
                if ("year".equals(strV)) {
                    i2 = iT;
                } else if ("month".equals(strV)) {
                    i3 = iT;
                } else if ("dayOfMonth".equals(strV)) {
                    i4 = iT;
                } else if ("hourOfDay".equals(strV)) {
                    i5 = iT;
                } else if ("minute".equals(strV)) {
                    i6 = iT;
                } else if ("second".equals(strV)) {
                    i7 = iT;
                }
            }
            aVar.K();
            return new GregorianCalendar(i2, i3, i4, i5, i6, i7);
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, Calendar calendar) throws IOException {
            if (calendar == null) {
                bVar.P();
                return;
            }
            bVar.v();
            bVar.N("year");
            bVar.Z(calendar.get(1));
            bVar.N("month");
            bVar.Z(calendar.get(2));
            bVar.N("dayOfMonth");
            bVar.Z(calendar.get(5));
            bVar.N("hourOfDay");
            bVar.Z(calendar.get(11));
            bVar.N("minute");
            bVar.Z(calendar.get(12));
            bVar.N("second");
            bVar.Z(calendar.get(13));
            bVar.J();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class t extends c.b.a.q<Locale> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Locale b(c.b.a.v.a aVar) throws IOException {
            if (aVar.b0() == JsonToken.NULL) {
                aVar.X();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(aVar.Z(), "_");
            String strNextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String strNextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String strNextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            return (strNextToken2 == null && strNextToken3 == null) ? new Locale(strNextToken) : strNextToken3 == null ? new Locale(strNextToken, strNextToken2) : new Locale(strNextToken, strNextToken2, strNextToken3);
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, Locale locale) throws IOException {
            bVar.c0(locale == null ? null : locale.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class u extends c.b.a.q<c.b.a.k> {
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public c.b.a.k b(c.b.a.v.a aVar) throws IOException {
            switch (b0.f3021a[aVar.b0().ordinal()]) {
                case 1:
                    return new c.b.a.n(new LazilyParsedNumber(aVar.Z()));
                case 2:
                    return new c.b.a.n(Boolean.valueOf(aVar.R()));
                case 3:
                    return new c.b.a.n(aVar.Z());
                case 4:
                    aVar.X();
                    return c.b.a.l.f2911a;
                case 5:
                    c.b.a.h hVar = new c.b.a.h();
                    aVar.d();
                    while (aVar.N()) {
                        hVar.i(b(aVar));
                    }
                    aVar.J();
                    return hVar;
                case 6:
                    c.b.a.m mVar = new c.b.a.m();
                    aVar.s();
                    while (aVar.N()) {
                        mVar.i(aVar.V(), b(aVar));
                    }
                    aVar.K();
                    return mVar;
                default:
                    throw new IllegalArgumentException();
            }
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, c.b.a.k kVar) throws IOException {
            if (kVar == null || kVar.f()) {
                bVar.P();
                return;
            }
            if (kVar.h()) {
                c.b.a.n nVarC = kVar.c();
                if (nVarC.q()) {
                    bVar.b0(nVarC.n());
                    return;
                } else if (nVarC.o()) {
                    bVar.d0(nVarC.i());
                    return;
                } else {
                    bVar.c0(nVarC.d());
                    return;
                }
            }
            if (kVar.e()) {
                bVar.s();
                Iterator<c.b.a.k> it = kVar.a().iterator();
                while (it.hasNext()) {
                    d(bVar, it.next());
                }
                bVar.I();
                return;
            }
            if (!kVar.g()) {
                throw new IllegalArgumentException("Couldn't write " + kVar.getClass());
            }
            bVar.v();
            for (Map.Entry<String, c.b.a.k> entry : kVar.b().j()) {
                bVar.N(entry.getKey());
                d(bVar, entry.getValue());
            }
            bVar.J();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class v extends c.b.a.q<BitSet> {
        /* JADX WARN: Removed duplicated region for block: B:19:0x003b  */
        @Override // c.b.a.q
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.util.BitSet b(c.b.a.v.a r8) throws java.io.IOException {
            /*
                r7 = this;
                com.google.gson.stream.JsonToken r0 = r8.b0()
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.NULL
                if (r0 != r1) goto Ld
                r8.X()
                r8 = 0
                return r8
            Ld:
                java.util.BitSet r0 = new java.util.BitSet
                r0.<init>()
                r8.d()
                com.google.gson.stream.JsonToken r1 = r8.b0()
                r2 = 0
                r3 = r2
            L1b:
                com.google.gson.stream.JsonToken r4 = com.google.gson.stream.JsonToken.END_ARRAY
                if (r1 == r4) goto L82
                int[] r4 = c.b.a.t.i.n.b0.f3021a
                int r5 = r1.ordinal()
                r4 = r4[r5]
                r5 = 1
                if (r4 == r5) goto L70
                r6 = 2
                if (r4 == r6) goto L6b
                r6 = 3
                if (r4 != r6) goto L54
                java.lang.String r1 = r8.Z()
                int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.NumberFormatException -> L3d
                if (r1 == 0) goto L3b
                goto L76
            L3b:
                r5 = r2
                goto L76
            L3d:
                com.google.gson.JsonSyntaxException r8 = new com.google.gson.JsonSyntaxException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r2 = "Error: Expecting: bitset number value (1, 0), Found: "
                r0.append(r2)
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r8.<init>(r0)
                throw r8
            L54:
                com.google.gson.JsonSyntaxException r8 = new com.google.gson.JsonSyntaxException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r2 = "Invalid bitset value type: "
                r0.append(r2)
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r8.<init>(r0)
                throw r8
            L6b:
                boolean r5 = r8.R()
                goto L76
            L70:
                int r1 = r8.T()
                if (r1 == 0) goto L3b
            L76:
                if (r5 == 0) goto L7b
                r0.set(r3)
            L7b:
                int r3 = r3 + 1
                com.google.gson.stream.JsonToken r1 = r8.b0()
                goto L1b
            L82:
                r8.J()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: c.b.a.t.i.n.v.b(c.b.a.v.a):java.util.BitSet");
        }

        @Override // c.b.a.q
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.a.v.b bVar, BitSet bitSet) throws IOException {
            if (bitSet == null) {
                bVar.P();
                return;
            }
            bVar.s();
            for (int i2 = 0; i2 < bitSet.length(); i2++) {
                bVar.Z(bitSet.get(i2) ? 1L : 0L);
            }
            bVar.I();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class w implements c.b.a.r {
        @Override // c.b.a.r
        public <T> c.b.a.q<T> a(c.b.a.e eVar, c.b.a.u.a<T> aVar) {
            Class<? super T> clsC = aVar.c();
            if (!Enum.class.isAssignableFrom(clsC) || clsC == Enum.class) {
                return null;
            }
            if (!clsC.isEnum()) {
                clsC = clsC.getSuperclass();
            }
            return new j0(clsC);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class x implements c.b.a.r {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Class f3026c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ c.b.a.q f3027d;

        public x(Class cls, c.b.a.q qVar) {
            this.f3026c = cls;
            this.f3027d = qVar;
        }

        @Override // c.b.a.r
        public <T> c.b.a.q<T> a(c.b.a.e eVar, c.b.a.u.a<T> aVar) {
            if (aVar.c() == this.f3026c) {
                return this.f3027d;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.f3026c.getName() + ",adapter=" + this.f3027d + "]";
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class y implements c.b.a.r {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Class f3028c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Class f3029d;
        public final /* synthetic */ c.b.a.q s;

        public y(Class cls, Class cls2, c.b.a.q qVar) {
            this.f3028c = cls;
            this.f3029d = cls2;
            this.s = qVar;
        }

        @Override // c.b.a.r
        public <T> c.b.a.q<T> a(c.b.a.e eVar, c.b.a.u.a<T> aVar) {
            Class<? super T> clsC = aVar.c();
            if (clsC == this.f3028c || clsC == this.f3029d) {
                return this.s;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.f3029d.getName() + "+" + this.f3028c.getName() + ",adapter=" + this.s + "]";
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class z implements c.b.a.r {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Class f3030c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Class f3031d;
        public final /* synthetic */ c.b.a.q s;

        public z(Class cls, Class cls2, c.b.a.q qVar) {
            this.f3030c = cls;
            this.f3031d = cls2;
            this.s = qVar;
        }

        @Override // c.b.a.r
        public <T> c.b.a.q<T> a(c.b.a.e eVar, c.b.a.u.a<T> aVar) {
            Class<? super T> clsC = aVar.c();
            if (clsC == this.f3030c || clsC == this.f3031d) {
                return this.s;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.f3030c.getName() + "+" + this.f3031d.getName() + ",adapter=" + this.s + "]";
        }
    }

    static {
        k kVar = new k();
        f3008a = kVar;
        f3009b = a(Class.class, kVar);
        v vVar = new v();
        f3010c = vVar;
        f3011d = a(BitSet.class, vVar);
        c0 c0Var = new c0();
        f3012e = c0Var;
        f3013f = new d0();
        f3014g = b(Boolean.TYPE, Boolean.class, c0Var);
        e0 e0Var = new e0();
        f3015h = e0Var;
        f3016i = b(Byte.TYPE, Byte.class, e0Var);
        f0 f0Var = new f0();
        j = f0Var;
        k = b(Short.TYPE, Short.class, f0Var);
        g0 g0Var = new g0();
        l = g0Var;
        m = b(Integer.TYPE, Integer.class, g0Var);
        c.b.a.q<AtomicInteger> qVarA = new h0().a();
        n = qVarA;
        o = a(AtomicInteger.class, qVarA);
        c.b.a.q<AtomicBoolean> qVarA2 = new i0().a();
        p = qVarA2;
        q = a(AtomicBoolean.class, qVarA2);
        c.b.a.q<AtomicIntegerArray> qVarA3 = new a().a();
        r = qVarA3;
        s = a(AtomicIntegerArray.class, qVarA3);
        t = new b();
        u = new c();
        v = new d();
        e eVar = new e();
        w = eVar;
        x = a(Number.class, eVar);
        f fVar = new f();
        y = fVar;
        z = b(Character.TYPE, Character.class, fVar);
        g gVar = new g();
        A = gVar;
        B = new h();
        C = new i();
        D = a(String.class, gVar);
        j jVar = new j();
        E = jVar;
        F = a(StringBuilder.class, jVar);
        l lVar = new l();
        G = lVar;
        H = a(StringBuffer.class, lVar);
        m mVar = new m();
        I = mVar;
        J = a(URL.class, mVar);
        C0070n c0070n = new C0070n();
        K = c0070n;
        L = a(URI.class, c0070n);
        o oVar = new o();
        M = oVar;
        N = d(InetAddress.class, oVar);
        p pVar = new p();
        O = pVar;
        P = a(UUID.class, pVar);
        c.b.a.q<Currency> qVarA4 = new q().a();
        Q = qVarA4;
        R = a(Currency.class, qVarA4);
        S = new r();
        s sVar = new s();
        T = sVar;
        U = c(Calendar.class, GregorianCalendar.class, sVar);
        t tVar = new t();
        V = tVar;
        W = a(Locale.class, tVar);
        u uVar = new u();
        X = uVar;
        Y = d(c.b.a.k.class, uVar);
        Z = new w();
    }

    public static <TT> c.b.a.r a(Class<TT> cls, c.b.a.q<TT> qVar) {
        return new x(cls, qVar);
    }

    public static <TT> c.b.a.r b(Class<TT> cls, Class<TT> cls2, c.b.a.q<? super TT> qVar) {
        return new y(cls, cls2, qVar);
    }

    public static <TT> c.b.a.r c(Class<TT> cls, Class<? extends TT> cls2, c.b.a.q<? super TT> qVar) {
        return new z(cls, cls2, qVar);
    }

    public static <T1> c.b.a.r d(Class<T1> cls, c.b.a.q<T1> qVar) {
        return new a0(cls, qVar);
    }
}
