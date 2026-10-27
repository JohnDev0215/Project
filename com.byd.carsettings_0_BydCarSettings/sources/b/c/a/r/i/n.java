package b.c.a.r.i;

import com.google.android.material.badge.BadgeDrawable;
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
/* JADX INFO: loaded from: classes3.dex */
public final class n {
    public static final b.c.a.o<String> A;
    public static final b.c.a.o<BigDecimal> B;
    public static final b.c.a.o<BigInteger> C;
    public static final b.c.a.p D;
    public static final b.c.a.o<StringBuilder> E;
    public static final b.c.a.p F;
    public static final b.c.a.o<StringBuffer> G;
    public static final b.c.a.p H;
    public static final b.c.a.o<URL> I;
    public static final b.c.a.p J;
    public static final b.c.a.o<URI> K;
    public static final b.c.a.p L;
    public static final b.c.a.o<InetAddress> M;
    public static final b.c.a.p N;
    public static final b.c.a.o<UUID> O;
    public static final b.c.a.p P;
    public static final b.c.a.o<Currency> Q;
    public static final b.c.a.p R;
    public static final b.c.a.p S;
    public static final b.c.a.o<Calendar> T;
    public static final b.c.a.p U;
    public static final b.c.a.o<Locale> V;
    public static final b.c.a.p W;
    public static final b.c.a.o<b.c.a.i> X;
    public static final b.c.a.p Y;
    public static final b.c.a.p Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b.c.a.o<Class> f2295a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b.c.a.p f2296b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final b.c.a.o<BitSet> f2297c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b.c.a.p f2298d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b.c.a.o<Boolean> f2299e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final b.c.a.o<Boolean> f2300f;
    public static final b.c.a.p g;
    public static final b.c.a.o<Number> h;
    public static final b.c.a.p i;
    public static final b.c.a.o<Number> j;
    public static final b.c.a.p k;
    public static final b.c.a.o<Number> l;
    public static final b.c.a.p m;
    public static final b.c.a.o<AtomicInteger> n;
    public static final b.c.a.p o;
    public static final b.c.a.o<AtomicBoolean> p;
    public static final b.c.a.p q;
    public static final b.c.a.o<AtomicIntegerArray> r;
    public static final b.c.a.p s;
    public static final b.c.a.o<Number> t;
    public static final b.c.a.o<Number> u;
    public static final b.c.a.o<Number> v;
    public static final b.c.a.o<Number> w;
    public static final b.c.a.p x;
    public static final b.c.a.o<Character> y;
    public static final b.c.a.p z;

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class a extends b.c.a.o<AtomicIntegerArray> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicIntegerArray b(b.c.a.t.a aVar) throws IOException {
            ArrayList arrayList = new ArrayList();
            aVar.c();
            while (aVar.n()) {
                try {
                    arrayList.add(Integer.valueOf(aVar.v()));
                } catch (NumberFormatException e2) {
                    throw new JsonSyntaxException(e2);
                }
            }
            aVar.i();
            int size = arrayList.size();
            AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
            for (int i = 0; i < size; i++) {
                atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
            }
            return atomicIntegerArray;
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, AtomicIntegerArray atomicIntegerArray) throws IOException {
            bVar.e();
            int length = atomicIntegerArray.length();
            for (int i = 0; i < length; i++) {
                bVar.C(atomicIntegerArray.get(i));
            }
            bVar.h();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class a0 implements b.c.a.p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Class f2301a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ b.c.a.o f2302b;

        /* JADX INFO: Add missing generic type declarations: [T1] */
        /* JADX INFO: compiled from: TypeAdapters.java */
        public class a<T1> extends b.c.a.o<T1> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Class f2303a;

            public a(Class cls) {
                this.f2303a = cls;
            }

            @Override // b.c.a.o
            public T1 b(b.c.a.t.a aVar) throws IOException {
                T1 t1 = (T1) a0.this.f2302b.b(aVar);
                if (t1 == null || this.f2303a.isInstance(t1)) {
                    return t1;
                }
                throw new JsonSyntaxException("Expected a " + this.f2303a.getName() + " but was " + t1.getClass().getName());
            }

            @Override // b.c.a.o
            public void d(b.c.a.t.b bVar, T1 t1) throws IOException {
                a0.this.f2302b.d(bVar, t1);
            }
        }

        public a0(Class cls, b.c.a.o oVar) {
            this.f2301a = cls;
            this.f2302b = oVar;
        }

        @Override // b.c.a.p
        public <T2> b.c.a.o<T2> a(b.c.a.d dVar, b.c.a.s.a<T2> aVar) {
            Class<? super T2> clsD = aVar.d();
            if (this.f2301a.isAssignableFrom(clsD)) {
                return new a(clsD);
            }
            return null;
        }

        public String toString() {
            return "Factory[typeHierarchy=" + this.f2301a.getName() + ",adapter=" + this.f2302b + "]";
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class b extends b.c.a.o<Number> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() == JsonToken.NULL) {
                aVar.z();
                return null;
            }
            try {
                return Long.valueOf(aVar.w());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, Number number) throws IOException {
            bVar.F(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static /* synthetic */ class b0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f2305a;

        static {
            int[] iArr = new int[JsonToken.values().length];
            f2305a = iArr;
            try {
                iArr[JsonToken.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2305a[JsonToken.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2305a[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2305a[JsonToken.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2305a[JsonToken.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2305a[JsonToken.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2305a[JsonToken.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f2305a[JsonToken.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f2305a[JsonToken.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f2305a[JsonToken.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class c extends b.c.a.o<Number> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() != JsonToken.NULL) {
                return Float.valueOf((float) aVar.u());
            }
            aVar.z();
            return null;
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, Number number) throws IOException {
            bVar.F(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class c0 extends b.c.a.o<Boolean> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Boolean b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() != JsonToken.NULL) {
                return aVar.E() == JsonToken.STRING ? Boolean.valueOf(Boolean.parseBoolean(aVar.B())) : Boolean.valueOf(aVar.t());
            }
            aVar.z();
            return null;
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, Boolean bool) throws IOException {
            bVar.E(bool);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class d extends b.c.a.o<Number> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() != JsonToken.NULL) {
                return Double.valueOf(aVar.u());
            }
            aVar.z();
            return null;
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, Number number) throws IOException {
            bVar.F(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class d0 extends b.c.a.o<Boolean> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Boolean b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() != JsonToken.NULL) {
                return Boolean.valueOf(aVar.B());
            }
            aVar.z();
            return null;
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, Boolean bool) throws IOException {
            bVar.G(bool == null ? "null" : bool.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class e extends b.c.a.o<Number> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(b.c.a.t.a aVar) throws IOException {
            JsonToken jsonTokenE = aVar.E();
            int i = b0.f2305a[jsonTokenE.ordinal()];
            if (i == 1) {
                return new LazilyParsedNumber(aVar.B());
            }
            if (i == 4) {
                aVar.z();
                return null;
            }
            throw new JsonSyntaxException("Expecting number, got: " + jsonTokenE);
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, Number number) throws IOException {
            bVar.F(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class e0 extends b.c.a.o<Number> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() == JsonToken.NULL) {
                aVar.z();
                return null;
            }
            try {
                return Byte.valueOf((byte) aVar.v());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, Number number) throws IOException {
            bVar.F(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class f extends b.c.a.o<Character> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Character b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() == JsonToken.NULL) {
                aVar.z();
                return null;
            }
            String strB = aVar.B();
            if (strB.length() == 1) {
                return Character.valueOf(strB.charAt(0));
            }
            throw new JsonSyntaxException("Expecting character, got: " + strB);
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, Character ch) throws IOException {
            bVar.G(ch == null ? null : String.valueOf(ch));
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class f0 extends b.c.a.o<Number> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() == JsonToken.NULL) {
                aVar.z();
                return null;
            }
            try {
                return Short.valueOf((short) aVar.v());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, Number number) throws IOException {
            bVar.F(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class g extends b.c.a.o<String> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public String b(b.c.a.t.a aVar) throws IOException {
            JsonToken jsonTokenE = aVar.E();
            if (jsonTokenE != JsonToken.NULL) {
                return jsonTokenE == JsonToken.BOOLEAN ? Boolean.toString(aVar.t()) : aVar.B();
            }
            aVar.z();
            return null;
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, String str) throws IOException {
            bVar.G(str);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class g0 extends b.c.a.o<Number> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() == JsonToken.NULL) {
                aVar.z();
                return null;
            }
            try {
                return Integer.valueOf(aVar.v());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, Number number) throws IOException {
            bVar.F(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class h extends b.c.a.o<BigDecimal> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public BigDecimal b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() == JsonToken.NULL) {
                aVar.z();
                return null;
            }
            try {
                return new BigDecimal(aVar.B());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, BigDecimal bigDecimal) throws IOException {
            bVar.F(bigDecimal);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class h0 extends b.c.a.o<AtomicInteger> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicInteger b(b.c.a.t.a aVar) throws IOException {
            try {
                return new AtomicInteger(aVar.v());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, AtomicInteger atomicInteger) throws IOException {
            bVar.C(atomicInteger.get());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class i extends b.c.a.o<BigInteger> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public BigInteger b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() == JsonToken.NULL) {
                aVar.z();
                return null;
            }
            try {
                return new BigInteger(aVar.B());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, BigInteger bigInteger) throws IOException {
            bVar.F(bigInteger);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class i0 extends b.c.a.o<AtomicBoolean> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicBoolean b(b.c.a.t.a aVar) throws IOException {
            return new AtomicBoolean(aVar.t());
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, AtomicBoolean atomicBoolean) throws IOException {
            bVar.H(atomicBoolean.get());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class j extends b.c.a.o<StringBuilder> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public StringBuilder b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() != JsonToken.NULL) {
                return new StringBuilder(aVar.B());
            }
            aVar.z();
            return null;
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, StringBuilder sb) throws IOException {
            bVar.G(sb == null ? null : sb.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static final class j0<T extends Enum<T>> extends b.c.a.o<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Map<String, T> f2306a = new HashMap();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Map<T, String> f2307b = new HashMap();

        public j0(Class<T> cls) {
            try {
                for (T t : cls.getEnumConstants()) {
                    String strName = t.name();
                    b.c.a.q.c cVar = (b.c.a.q.c) cls.getField(strName).getAnnotation(b.c.a.q.c.class);
                    if (cVar != null) {
                        strName = cVar.value();
                        for (String str : cVar.alternate()) {
                            this.f2306a.put(str, t);
                        }
                    }
                    this.f2306a.put(strName, t);
                    this.f2307b.put(t, strName);
                }
            } catch (NoSuchFieldException e2) {
                throw new AssertionError(e2);
            }
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public T b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() != JsonToken.NULL) {
                return this.f2306a.get(aVar.B());
            }
            aVar.z();
            return null;
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, T t) throws IOException {
            bVar.G(t == null ? null : this.f2307b.get(t));
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class k extends b.c.a.o<Class> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Class b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() != JsonToken.NULL) {
                throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
            }
            aVar.z();
            return null;
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, Class cls) throws IOException {
            if (cls == null) {
                bVar.s();
                return;
            }
            throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?");
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class l extends b.c.a.o<StringBuffer> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public StringBuffer b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() != JsonToken.NULL) {
                return new StringBuffer(aVar.B());
            }
            aVar.z();
            return null;
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, StringBuffer stringBuffer) throws IOException {
            bVar.G(stringBuffer == null ? null : stringBuffer.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class m extends b.c.a.o<URL> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public URL b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() == JsonToken.NULL) {
                aVar.z();
                return null;
            }
            String strB = aVar.B();
            if ("null".equals(strB)) {
                return null;
            }
            return new URL(strB);
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, URL url) throws IOException {
            bVar.G(url == null ? null : url.toExternalForm());
        }
    }

    /* JADX INFO: renamed from: b.c.a.r.i.n$n, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class C0093n extends b.c.a.o<URI> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public URI b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() == JsonToken.NULL) {
                aVar.z();
                return null;
            }
            try {
                String strB = aVar.B();
                if ("null".equals(strB)) {
                    return null;
                }
                return new URI(strB);
            } catch (URISyntaxException e2) {
                throw new JsonIOException(e2);
            }
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, URI uri) throws IOException {
            bVar.G(uri == null ? null : uri.toASCIIString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class o extends b.c.a.o<InetAddress> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public InetAddress b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() != JsonToken.NULL) {
                return InetAddress.getByName(aVar.B());
            }
            aVar.z();
            return null;
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, InetAddress inetAddress) throws IOException {
            bVar.G(inetAddress == null ? null : inetAddress.getHostAddress());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class p extends b.c.a.o<UUID> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public UUID b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() != JsonToken.NULL) {
                return UUID.fromString(aVar.B());
            }
            aVar.z();
            return null;
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, UUID uuid) throws IOException {
            bVar.G(uuid == null ? null : uuid.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class q extends b.c.a.o<Currency> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Currency b(b.c.a.t.a aVar) throws IOException {
            return Currency.getInstance(aVar.B());
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, Currency currency) throws IOException {
            bVar.G(currency.getCurrencyCode());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class r implements b.c.a.p {

        /* JADX INFO: compiled from: TypeAdapters.java */
        public class a extends b.c.a.o<Timestamp> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ b.c.a.o f2308a;

            public a(b.c.a.o oVar) {
                this.f2308a = oVar;
            }

            @Override // b.c.a.o
            /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
            public Timestamp b(b.c.a.t.a aVar) throws IOException {
                Date date = (Date) this.f2308a.b(aVar);
                if (date != null) {
                    return new Timestamp(date.getTime());
                }
                return null;
            }

            @Override // b.c.a.o
            /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
            public void d(b.c.a.t.b bVar, Timestamp timestamp) throws IOException {
                this.f2308a.d(bVar, timestamp);
            }
        }

        @Override // b.c.a.p
        public <T> b.c.a.o<T> a(b.c.a.d dVar, b.c.a.s.a<T> aVar) {
            if (aVar.d() != Timestamp.class) {
                return null;
            }
            return new a(dVar.l(Date.class));
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class s extends b.c.a.o<Calendar> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Calendar b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() == JsonToken.NULL) {
                aVar.z();
                return null;
            }
            aVar.e();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (aVar.E() != JsonToken.END_OBJECT) {
                String strX = aVar.x();
                int iV = aVar.v();
                if ("year".equals(strX)) {
                    i = iV;
                } else if ("month".equals(strX)) {
                    i2 = iV;
                } else if ("dayOfMonth".equals(strX)) {
                    i3 = iV;
                } else if ("hourOfDay".equals(strX)) {
                    i4 = iV;
                } else if ("minute".equals(strX)) {
                    i5 = iV;
                } else if ("second".equals(strX)) {
                    i6 = iV;
                }
            }
            aVar.j();
            return new GregorianCalendar(i, i2, i3, i4, i5, i6);
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, Calendar calendar) throws IOException {
            if (calendar == null) {
                bVar.s();
                return;
            }
            bVar.f();
            bVar.o("year");
            bVar.C(calendar.get(1));
            bVar.o("month");
            bVar.C(calendar.get(2));
            bVar.o("dayOfMonth");
            bVar.C(calendar.get(5));
            bVar.o("hourOfDay");
            bVar.C(calendar.get(11));
            bVar.o("minute");
            bVar.C(calendar.get(12));
            bVar.o("second");
            bVar.C(calendar.get(13));
            bVar.i();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class t extends b.c.a.o<Locale> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Locale b(b.c.a.t.a aVar) throws IOException {
            if (aVar.E() == JsonToken.NULL) {
                aVar.z();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(aVar.B(), "_");
            String strNextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String strNextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String strNextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            return (strNextToken2 == null && strNextToken3 == null) ? new Locale(strNextToken) : strNextToken3 == null ? new Locale(strNextToken, strNextToken2) : new Locale(strNextToken, strNextToken2, strNextToken3);
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, Locale locale) throws IOException {
            bVar.G(locale == null ? null : locale.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class u extends b.c.a.o<b.c.a.i> {
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public b.c.a.i b(b.c.a.t.a aVar) throws IOException {
            switch (b0.f2305a[aVar.E().ordinal()]) {
                case 1:
                    return new b.c.a.l(new LazilyParsedNumber(aVar.B()));
                case 2:
                    return new b.c.a.l(Boolean.valueOf(aVar.t()));
                case 3:
                    return new b.c.a.l(aVar.B());
                case 4:
                    aVar.z();
                    return b.c.a.j.f2198a;
                case 5:
                    b.c.a.f fVar = new b.c.a.f();
                    aVar.c();
                    while (aVar.n()) {
                        fVar.i(b(aVar));
                    }
                    aVar.i();
                    return fVar;
                case 6:
                    b.c.a.k kVar = new b.c.a.k();
                    aVar.e();
                    while (aVar.n()) {
                        kVar.i(aVar.x(), b(aVar));
                    }
                    aVar.j();
                    return kVar;
                default:
                    throw new IllegalArgumentException();
            }
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, b.c.a.i iVar) throws IOException {
            if (iVar == null || iVar.e()) {
                bVar.s();
                return;
            }
            if (iVar.g()) {
                b.c.a.l lVarC = iVar.c();
                if (lVarC.r()) {
                    bVar.F(lVarC.n());
                    return;
                } else if (lVarC.p()) {
                    bVar.H(lVarC.i());
                    return;
                } else {
                    bVar.G(lVarC.o());
                    return;
                }
            }
            if (iVar.d()) {
                bVar.e();
                Iterator<b.c.a.i> it = iVar.a().iterator();
                while (it.hasNext()) {
                    d(bVar, it.next());
                }
                bVar.h();
                return;
            }
            if (!iVar.f()) {
                if (iVar.h()) {
                    bVar.n(iVar.toString());
                    return;
                }
                throw new IllegalArgumentException("Couldn't write " + iVar.getClass());
            }
            bVar.f();
            for (Map.Entry<String, b.c.a.i> entry : iVar.b().j()) {
                bVar.o(entry.getKey());
                d(bVar, entry.getValue());
            }
            bVar.i();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class v extends b.c.a.o<BitSet> {
        /* JADX WARN: Removed duplicated region for block: B:19:0x003b  */
        @Override // b.c.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.util.BitSet b(b.c.a.t.a r8) throws java.io.IOException {
            /*
                r7 = this;
                com.google.gson.stream.JsonToken r0 = r8.E()
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.NULL
                if (r0 != r1) goto Ld
                r8.z()
                r8 = 0
                return r8
            Ld:
                java.util.BitSet r0 = new java.util.BitSet
                r0.<init>()
                r8.c()
                com.google.gson.stream.JsonToken r1 = r8.E()
                r2 = 0
                r3 = r2
            L1b:
                com.google.gson.stream.JsonToken r4 = com.google.gson.stream.JsonToken.END_ARRAY
                if (r1 == r4) goto L82
                int[] r4 = b.c.a.r.i.n.b0.f2305a
                int r5 = r1.ordinal()
                r4 = r4[r5]
                r5 = 1
                if (r4 == r5) goto L70
                r6 = 2
                if (r4 == r6) goto L6b
                r6 = 3
                if (r4 != r6) goto L54
                java.lang.String r1 = r8.B()
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
                boolean r5 = r8.t()
                goto L76
            L70:
                int r1 = r8.v()
                if (r1 == 0) goto L3b
            L76:
                if (r5 == 0) goto L7b
                r0.set(r3)
            L7b:
                int r3 = r3 + 1
                com.google.gson.stream.JsonToken r1 = r8.E()
                goto L1b
            L82:
                r8.i()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: b.c.a.r.i.n.v.b(b.c.a.t.a):java.util.BitSet");
        }

        @Override // b.c.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.c.a.t.b bVar, BitSet bitSet) throws IOException {
            if (bitSet == null) {
                bVar.s();
                return;
            }
            bVar.e();
            for (int i = 0; i < bitSet.length(); i++) {
                bVar.C(bitSet.get(i) ? 1L : 0L);
            }
            bVar.h();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class w implements b.c.a.p {
        @Override // b.c.a.p
        public <T> b.c.a.o<T> a(b.c.a.d dVar, b.c.a.s.a<T> aVar) {
            Class<? super T> clsD = aVar.d();
            if (!Enum.class.isAssignableFrom(clsD) || clsD == Enum.class) {
                return null;
            }
            if (!clsD.isEnum()) {
                clsD = clsD.getSuperclass();
            }
            return new j0(clsD);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class x implements b.c.a.p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Class f2310a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ b.c.a.o f2311b;

        public x(Class cls, b.c.a.o oVar) {
            this.f2310a = cls;
            this.f2311b = oVar;
        }

        @Override // b.c.a.p
        public <T> b.c.a.o<T> a(b.c.a.d dVar, b.c.a.s.a<T> aVar) {
            if (aVar.d() == this.f2310a) {
                return this.f2311b;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.f2310a.getName() + ",adapter=" + this.f2311b + "]";
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class y implements b.c.a.p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Class f2312a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Class f2313b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ b.c.a.o f2314c;

        public y(Class cls, Class cls2, b.c.a.o oVar) {
            this.f2312a = cls;
            this.f2313b = cls2;
            this.f2314c = oVar;
        }

        @Override // b.c.a.p
        public <T> b.c.a.o<T> a(b.c.a.d dVar, b.c.a.s.a<T> aVar) {
            Class<? super T> clsD = aVar.d();
            if (clsD == this.f2312a || clsD == this.f2313b) {
                return this.f2314c;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.f2313b.getName() + BadgeDrawable.DEFAULT_EXCEED_MAX_BADGE_NUMBER_SUFFIX + this.f2312a.getName() + ",adapter=" + this.f2314c + "]";
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class z implements b.c.a.p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Class f2315a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Class f2316b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ b.c.a.o f2317c;

        public z(Class cls, Class cls2, b.c.a.o oVar) {
            this.f2315a = cls;
            this.f2316b = cls2;
            this.f2317c = oVar;
        }

        @Override // b.c.a.p
        public <T> b.c.a.o<T> a(b.c.a.d dVar, b.c.a.s.a<T> aVar) {
            Class<? super T> clsD = aVar.d();
            if (clsD == this.f2315a || clsD == this.f2316b) {
                return this.f2317c;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.f2315a.getName() + BadgeDrawable.DEFAULT_EXCEED_MAX_BADGE_NUMBER_SUFFIX + this.f2316b.getName() + ",adapter=" + this.f2317c + "]";
        }
    }

    static {
        k kVar = new k();
        f2295a = kVar;
        f2296b = a(Class.class, kVar);
        v vVar = new v();
        f2297c = vVar;
        f2298d = a(BitSet.class, vVar);
        c0 c0Var = new c0();
        f2299e = c0Var;
        f2300f = new d0();
        g = b(Boolean.TYPE, Boolean.class, c0Var);
        e0 e0Var = new e0();
        h = e0Var;
        i = b(Byte.TYPE, Byte.class, e0Var);
        f0 f0Var = new f0();
        j = f0Var;
        k = b(Short.TYPE, Short.class, f0Var);
        g0 g0Var = new g0();
        l = g0Var;
        m = b(Integer.TYPE, Integer.class, g0Var);
        b.c.a.o<AtomicInteger> oVarA = new h0().a();
        n = oVarA;
        o = a(AtomicInteger.class, oVarA);
        b.c.a.o<AtomicBoolean> oVarA2 = new i0().a();
        p = oVarA2;
        q = a(AtomicBoolean.class, oVarA2);
        b.c.a.o<AtomicIntegerArray> oVarA3 = new a().a();
        r = oVarA3;
        s = a(AtomicIntegerArray.class, oVarA3);
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
        C0093n c0093n = new C0093n();
        K = c0093n;
        L = a(URI.class, c0093n);
        o oVar = new o();
        M = oVar;
        N = d(InetAddress.class, oVar);
        p pVar = new p();
        O = pVar;
        P = a(UUID.class, pVar);
        b.c.a.o<Currency> oVarA4 = new q().a();
        Q = oVarA4;
        R = a(Currency.class, oVarA4);
        S = new r();
        s sVar = new s();
        T = sVar;
        U = c(Calendar.class, GregorianCalendar.class, sVar);
        t tVar = new t();
        V = tVar;
        W = a(Locale.class, tVar);
        u uVar = new u();
        X = uVar;
        Y = d(b.c.a.i.class, uVar);
        Z = new w();
    }

    public static <TT> b.c.a.p a(Class<TT> cls, b.c.a.o<TT> oVar) {
        return new x(cls, oVar);
    }

    public static <TT> b.c.a.p b(Class<TT> cls, Class<TT> cls2, b.c.a.o<? super TT> oVar) {
        return new y(cls, cls2, oVar);
    }

    public static <TT> b.c.a.p c(Class<TT> cls, Class<? extends TT> cls2, b.c.a.o<? super TT> oVar) {
        return new z(cls, cls2, oVar);
    }

    public static <T1> b.c.a.p d(Class<T1> cls, b.c.a.o<T1> oVar) {
        return new a0(cls, oVar);
    }
}
