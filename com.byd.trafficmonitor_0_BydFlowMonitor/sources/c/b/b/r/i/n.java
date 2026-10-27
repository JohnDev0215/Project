package c.b.b.r.i;

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
    public static final c.b.b.o<String> A;
    public static final c.b.b.o<BigDecimal> B;
    public static final c.b.b.o<BigInteger> C;
    public static final c.b.b.p D;
    public static final c.b.b.o<StringBuilder> E;
    public static final c.b.b.p F;
    public static final c.b.b.o<StringBuffer> G;
    public static final c.b.b.p H;
    public static final c.b.b.o<URL> I;
    public static final c.b.b.p J;
    public static final c.b.b.o<URI> K;
    public static final c.b.b.p L;
    public static final c.b.b.o<InetAddress> M;
    public static final c.b.b.p N;
    public static final c.b.b.o<UUID> O;
    public static final c.b.b.p P;
    public static final c.b.b.o<Currency> Q;
    public static final c.b.b.p R;
    public static final c.b.b.p S;
    public static final c.b.b.o<Calendar> T;
    public static final c.b.b.p U;
    public static final c.b.b.o<Locale> V;
    public static final c.b.b.p W;
    public static final c.b.b.o<c.b.b.i> X;
    public static final c.b.b.p Y;
    public static final c.b.b.p Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c.b.b.o<Class> f4271a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c.b.b.p f4272b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c.b.b.o<BitSet> f4273c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final c.b.b.p f4274d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final c.b.b.o<Boolean> f4275e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final c.b.b.o<Boolean> f4276f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final c.b.b.p f4277g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final c.b.b.o<Number> f4278h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final c.b.b.p f4279i;
    public static final c.b.b.o<Number> j;
    public static final c.b.b.p k;
    public static final c.b.b.o<Number> l;
    public static final c.b.b.p m;
    public static final c.b.b.o<AtomicInteger> n;
    public static final c.b.b.p o;
    public static final c.b.b.o<AtomicBoolean> p;
    public static final c.b.b.p q;
    public static final c.b.b.o<AtomicIntegerArray> r;
    public static final c.b.b.p s;
    public static final c.b.b.o<Number> t;
    public static final c.b.b.o<Number> u;
    public static final c.b.b.o<Number> v;
    public static final c.b.b.o<Number> w;
    public static final c.b.b.p x;
    public static final c.b.b.o<Character> y;
    public static final c.b.b.p z;

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class a extends c.b.b.o<AtomicIntegerArray> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicIntegerArray b(c.b.b.t.a aVar) throws IOException {
            ArrayList arrayList = new ArrayList();
            aVar.z();
            while (aVar.W()) {
                try {
                    arrayList.add(Integer.valueOf(aVar.c0()));
                } catch (NumberFormatException e2) {
                    throw new JsonSyntaxException(e2);
                }
            }
            aVar.S();
            int size = arrayList.size();
            AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
            for (int i2 = 0; i2 < size; i2++) {
                atomicIntegerArray.set(i2, ((Integer) arrayList.get(i2)).intValue());
            }
            return atomicIntegerArray;
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, AtomicIntegerArray atomicIntegerArray) throws IOException {
            bVar.O();
            int length = atomicIntegerArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                bVar.j0(atomicIntegerArray.get(i2));
            }
            bVar.R();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class a0 implements c.b.b.p {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Class f4280b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ c.b.b.o f4281c;

        /* JADX INFO: Add missing generic type declarations: [T1] */
        /* JADX INFO: compiled from: TypeAdapters.java */
        public class a<T1> extends c.b.b.o<T1> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Class f4282a;

            public a(Class cls) {
                this.f4282a = cls;
            }

            @Override // c.b.b.o
            public T1 b(c.b.b.t.a aVar) throws IOException {
                T1 t1 = (T1) a0.this.f4281c.b(aVar);
                if (t1 == null || this.f4282a.isInstance(t1)) {
                    return t1;
                }
                throw new JsonSyntaxException("Expected a " + this.f4282a.getName() + " but was " + t1.getClass().getName());
            }

            @Override // c.b.b.o
            public void d(c.b.b.t.b bVar, T1 t1) throws IOException {
                a0.this.f4281c.d(bVar, t1);
            }
        }

        public a0(Class cls, c.b.b.o oVar) {
            this.f4280b = cls;
            this.f4281c = oVar;
        }

        @Override // c.b.b.p
        public <T2> c.b.b.o<T2> a(c.b.b.d dVar, c.b.b.s.a<T2> aVar) {
            Class<? super T2> clsD = aVar.d();
            if (this.f4280b.isAssignableFrom(clsD)) {
                return new a(clsD);
            }
            return null;
        }

        public String toString() {
            return "Factory[typeHierarchy=" + this.f4280b.getName() + ",adapter=" + this.f4281c + "]";
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class b extends c.b.b.o<Number> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() == JsonToken.NULL) {
                aVar.g0();
                return null;
            }
            try {
                return Long.valueOf(aVar.d0());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, Number number) throws IOException {
            bVar.l0(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static /* synthetic */ class b0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f4284a;

        static {
            int[] iArr = new int[JsonToken.values().length];
            f4284a = iArr;
            try {
                iArr[JsonToken.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4284a[JsonToken.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4284a[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4284a[JsonToken.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f4284a[JsonToken.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f4284a[JsonToken.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f4284a[JsonToken.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f4284a[JsonToken.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f4284a[JsonToken.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f4284a[JsonToken.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class c extends c.b.b.o<Number> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() != JsonToken.NULL) {
                return Float.valueOf((float) aVar.b0());
            }
            aVar.g0();
            return null;
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, Number number) throws IOException {
            bVar.l0(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class c0 extends c.b.b.o<Boolean> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Boolean b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() != JsonToken.NULL) {
                return aVar.k0() == JsonToken.STRING ? Boolean.valueOf(Boolean.parseBoolean(aVar.i0())) : Boolean.valueOf(aVar.a0());
            }
            aVar.g0();
            return null;
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, Boolean bool) throws IOException {
            bVar.k0(bool);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class d extends c.b.b.o<Number> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() != JsonToken.NULL) {
                return Double.valueOf(aVar.b0());
            }
            aVar.g0();
            return null;
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, Number number) throws IOException {
            bVar.l0(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class d0 extends c.b.b.o<Boolean> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Boolean b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() != JsonToken.NULL) {
                return Boolean.valueOf(aVar.i0());
            }
            aVar.g0();
            return null;
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, Boolean bool) throws IOException {
            bVar.m0(bool == null ? "null" : bool.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class e extends c.b.b.o<Number> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(c.b.b.t.a aVar) throws IOException {
            JsonToken jsonTokenK0 = aVar.k0();
            int i2 = b0.f4284a[jsonTokenK0.ordinal()];
            if (i2 == 1) {
                return new LazilyParsedNumber(aVar.i0());
            }
            if (i2 == 4) {
                aVar.g0();
                return null;
            }
            throw new JsonSyntaxException("Expecting number, got: " + jsonTokenK0);
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, Number number) throws IOException {
            bVar.l0(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class e0 extends c.b.b.o<Number> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() == JsonToken.NULL) {
                aVar.g0();
                return null;
            }
            try {
                return Byte.valueOf((byte) aVar.c0());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, Number number) throws IOException {
            bVar.l0(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class f extends c.b.b.o<Character> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Character b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() == JsonToken.NULL) {
                aVar.g0();
                return null;
            }
            String strI0 = aVar.i0();
            if (strI0.length() == 1) {
                return Character.valueOf(strI0.charAt(0));
            }
            throw new JsonSyntaxException("Expecting character, got: " + strI0);
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, Character ch) throws IOException {
            bVar.m0(ch == null ? null : String.valueOf(ch));
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class f0 extends c.b.b.o<Number> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() == JsonToken.NULL) {
                aVar.g0();
                return null;
            }
            try {
                return Short.valueOf((short) aVar.c0());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, Number number) throws IOException {
            bVar.l0(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class g extends c.b.b.o<String> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public String b(c.b.b.t.a aVar) throws IOException {
            JsonToken jsonTokenK0 = aVar.k0();
            if (jsonTokenK0 != JsonToken.NULL) {
                return jsonTokenK0 == JsonToken.BOOLEAN ? Boolean.toString(aVar.a0()) : aVar.i0();
            }
            aVar.g0();
            return null;
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, String str) throws IOException {
            bVar.m0(str);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class g0 extends c.b.b.o<Number> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() == JsonToken.NULL) {
                aVar.g0();
                return null;
            }
            try {
                return Integer.valueOf(aVar.c0());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, Number number) throws IOException {
            bVar.l0(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class h extends c.b.b.o<BigDecimal> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public BigDecimal b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() == JsonToken.NULL) {
                aVar.g0();
                return null;
            }
            try {
                return new BigDecimal(aVar.i0());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, BigDecimal bigDecimal) throws IOException {
            bVar.l0(bigDecimal);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class h0 extends c.b.b.o<AtomicInteger> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicInteger b(c.b.b.t.a aVar) throws IOException {
            try {
                return new AtomicInteger(aVar.c0());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, AtomicInteger atomicInteger) throws IOException {
            bVar.j0(atomicInteger.get());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class i extends c.b.b.o<BigInteger> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public BigInteger b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() == JsonToken.NULL) {
                aVar.g0();
                return null;
            }
            try {
                return new BigInteger(aVar.i0());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, BigInteger bigInteger) throws IOException {
            bVar.l0(bigInteger);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class i0 extends c.b.b.o<AtomicBoolean> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicBoolean b(c.b.b.t.a aVar) throws IOException {
            return new AtomicBoolean(aVar.a0());
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, AtomicBoolean atomicBoolean) throws IOException {
            bVar.n0(atomicBoolean.get());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class j extends c.b.b.o<StringBuilder> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public StringBuilder b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() != JsonToken.NULL) {
                return new StringBuilder(aVar.i0());
            }
            aVar.g0();
            return null;
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, StringBuilder sb) throws IOException {
            bVar.m0(sb == null ? null : sb.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static final class j0<T extends Enum<T>> extends c.b.b.o<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Map<String, T> f4285a = new HashMap();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Map<T, String> f4286b = new HashMap();

        public j0(Class<T> cls) {
            try {
                for (T t : cls.getEnumConstants()) {
                    String strName = t.name();
                    c.b.b.q.c cVar = (c.b.b.q.c) cls.getField(strName).getAnnotation(c.b.b.q.c.class);
                    if (cVar != null) {
                        strName = cVar.value();
                        for (String str : cVar.alternate()) {
                            this.f4285a.put(str, t);
                        }
                    }
                    this.f4285a.put(strName, t);
                    this.f4286b.put(t, strName);
                }
            } catch (NoSuchFieldException e2) {
                throw new AssertionError(e2);
            }
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public T b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() != JsonToken.NULL) {
                return this.f4285a.get(aVar.i0());
            }
            aVar.g0();
            return null;
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, T t) throws IOException {
            bVar.m0(t == null ? null : this.f4286b.get(t));
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class k extends c.b.b.o<Class> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Class b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() != JsonToken.NULL) {
                throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
            }
            aVar.g0();
            return null;
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, Class cls) throws IOException {
            if (cls == null) {
                bVar.Z();
                return;
            }
            throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?");
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class l extends c.b.b.o<StringBuffer> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public StringBuffer b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() != JsonToken.NULL) {
                return new StringBuffer(aVar.i0());
            }
            aVar.g0();
            return null;
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, StringBuffer stringBuffer) throws IOException {
            bVar.m0(stringBuffer == null ? null : stringBuffer.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class m extends c.b.b.o<URL> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public URL b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() == JsonToken.NULL) {
                aVar.g0();
                return null;
            }
            String strI0 = aVar.i0();
            if ("null".equals(strI0)) {
                return null;
            }
            return new URL(strI0);
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, URL url) throws IOException {
            bVar.m0(url == null ? null : url.toExternalForm());
        }
    }

    /* JADX INFO: renamed from: c.b.b.r.i.n$n, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class C0098n extends c.b.b.o<URI> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public URI b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() == JsonToken.NULL) {
                aVar.g0();
                return null;
            }
            try {
                String strI0 = aVar.i0();
                if ("null".equals(strI0)) {
                    return null;
                }
                return new URI(strI0);
            } catch (URISyntaxException e2) {
                throw new JsonIOException(e2);
            }
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, URI uri) throws IOException {
            bVar.m0(uri == null ? null : uri.toASCIIString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class o extends c.b.b.o<InetAddress> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public InetAddress b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() != JsonToken.NULL) {
                return InetAddress.getByName(aVar.i0());
            }
            aVar.g0();
            return null;
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, InetAddress inetAddress) throws IOException {
            bVar.m0(inetAddress == null ? null : inetAddress.getHostAddress());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class p extends c.b.b.o<UUID> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public UUID b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() != JsonToken.NULL) {
                return UUID.fromString(aVar.i0());
            }
            aVar.g0();
            return null;
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, UUID uuid) throws IOException {
            bVar.m0(uuid == null ? null : uuid.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class q extends c.b.b.o<Currency> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Currency b(c.b.b.t.a aVar) throws IOException {
            return Currency.getInstance(aVar.i0());
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, Currency currency) throws IOException {
            bVar.m0(currency.getCurrencyCode());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class r implements c.b.b.p {

        /* JADX INFO: compiled from: TypeAdapters.java */
        public class a extends c.b.b.o<Timestamp> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ c.b.b.o f4287a;

            public a(c.b.b.o oVar) {
                this.f4287a = oVar;
            }

            @Override // c.b.b.o
            /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
            public Timestamp b(c.b.b.t.a aVar) throws IOException {
                Date date = (Date) this.f4287a.b(aVar);
                if (date != null) {
                    return new Timestamp(date.getTime());
                }
                return null;
            }

            @Override // c.b.b.o
            /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
            public void d(c.b.b.t.b bVar, Timestamp timestamp) throws IOException {
                this.f4287a.d(bVar, timestamp);
            }
        }

        @Override // c.b.b.p
        public <T> c.b.b.o<T> a(c.b.b.d dVar, c.b.b.s.a<T> aVar) {
            if (aVar.d() != Timestamp.class) {
                return null;
            }
            return new a(dVar.l(Date.class));
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class s extends c.b.b.o<Calendar> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Calendar b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() == JsonToken.NULL) {
                aVar.g0();
                return null;
            }
            aVar.O();
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            while (aVar.k0() != JsonToken.END_OBJECT) {
                String strE0 = aVar.e0();
                int iC0 = aVar.c0();
                if ("year".equals(strE0)) {
                    i2 = iC0;
                } else if ("month".equals(strE0)) {
                    i3 = iC0;
                } else if ("dayOfMonth".equals(strE0)) {
                    i4 = iC0;
                } else if ("hourOfDay".equals(strE0)) {
                    i5 = iC0;
                } else if ("minute".equals(strE0)) {
                    i6 = iC0;
                } else if ("second".equals(strE0)) {
                    i7 = iC0;
                }
            }
            aVar.T();
            return new GregorianCalendar(i2, i3, i4, i5, i6, i7);
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, Calendar calendar) throws IOException {
            if (calendar == null) {
                bVar.Z();
                return;
            }
            bVar.P();
            bVar.X("year");
            bVar.j0(calendar.get(1));
            bVar.X("month");
            bVar.j0(calendar.get(2));
            bVar.X("dayOfMonth");
            bVar.j0(calendar.get(5));
            bVar.X("hourOfDay");
            bVar.j0(calendar.get(11));
            bVar.X("minute");
            bVar.j0(calendar.get(12));
            bVar.X("second");
            bVar.j0(calendar.get(13));
            bVar.S();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class t extends c.b.b.o<Locale> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Locale b(c.b.b.t.a aVar) throws IOException {
            if (aVar.k0() == JsonToken.NULL) {
                aVar.g0();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(aVar.i0(), "_");
            String strNextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String strNextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String strNextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            return (strNextToken2 == null && strNextToken3 == null) ? new Locale(strNextToken) : strNextToken3 == null ? new Locale(strNextToken, strNextToken2) : new Locale(strNextToken, strNextToken2, strNextToken3);
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, Locale locale) throws IOException {
            bVar.m0(locale == null ? null : locale.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class u extends c.b.b.o<c.b.b.i> {
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public c.b.b.i b(c.b.b.t.a aVar) throws IOException {
            switch (b0.f4284a[aVar.k0().ordinal()]) {
                case 1:
                    return new c.b.b.l(new LazilyParsedNumber(aVar.i0()));
                case 2:
                    return new c.b.b.l(Boolean.valueOf(aVar.a0()));
                case 3:
                    return new c.b.b.l(aVar.i0());
                case 4:
                    aVar.g0();
                    return c.b.b.j.f4169a;
                case 5:
                    c.b.b.f fVar = new c.b.b.f();
                    aVar.z();
                    while (aVar.W()) {
                        fVar.i(b(aVar));
                    }
                    aVar.S();
                    return fVar;
                case 6:
                    c.b.b.k kVar = new c.b.b.k();
                    aVar.O();
                    while (aVar.W()) {
                        kVar.i(aVar.e0(), b(aVar));
                    }
                    aVar.T();
                    return kVar;
                default:
                    throw new IllegalArgumentException();
            }
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, c.b.b.i iVar) throws IOException {
            if (iVar == null || iVar.e()) {
                bVar.Z();
                return;
            }
            if (iVar.g()) {
                c.b.b.l lVarC = iVar.c();
                if (lVarC.r()) {
                    bVar.l0(lVarC.n());
                    return;
                } else if (lVarC.p()) {
                    bVar.n0(lVarC.i());
                    return;
                } else {
                    bVar.m0(lVarC.o());
                    return;
                }
            }
            if (iVar.d()) {
                bVar.O();
                Iterator<c.b.b.i> it = iVar.a().iterator();
                while (it.hasNext()) {
                    d(bVar, it.next());
                }
                bVar.R();
                return;
            }
            if (!iVar.f()) {
                if (iVar.h()) {
                    bVar.W(iVar.toString());
                    return;
                }
                throw new IllegalArgumentException("Couldn't write " + iVar.getClass());
            }
            bVar.P();
            for (Map.Entry<String, c.b.b.i> entry : iVar.b().j()) {
                bVar.X(entry.getKey());
                d(bVar, entry.getValue());
            }
            bVar.S();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class v extends c.b.b.o<BitSet> {
        /* JADX WARN: Removed duplicated region for block: B:19:0x003b  */
        @Override // c.b.b.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.util.BitSet b(c.b.b.t.a r8) throws java.io.IOException {
            /*
                r7 = this;
                com.google.gson.stream.JsonToken r0 = r8.k0()
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.NULL
                if (r0 != r1) goto Ld
                r8.g0()
                r8 = 0
                return r8
            Ld:
                java.util.BitSet r0 = new java.util.BitSet
                r0.<init>()
                r8.z()
                com.google.gson.stream.JsonToken r1 = r8.k0()
                r2 = 0
                r3 = r2
            L1b:
                com.google.gson.stream.JsonToken r4 = com.google.gson.stream.JsonToken.END_ARRAY
                if (r1 == r4) goto L82
                int[] r4 = c.b.b.r.i.n.b0.f4284a
                int r5 = r1.ordinal()
                r4 = r4[r5]
                r5 = 1
                if (r4 == r5) goto L70
                r6 = 2
                if (r4 == r6) goto L6b
                r6 = 3
                if (r4 != r6) goto L54
                java.lang.String r1 = r8.i0()
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
                boolean r5 = r8.a0()
                goto L76
            L70:
                int r1 = r8.c0()
                if (r1 == 0) goto L3b
            L76:
                if (r5 == 0) goto L7b
                r0.set(r3)
            L7b:
                int r3 = r3 + 1
                com.google.gson.stream.JsonToken r1 = r8.k0()
                goto L1b
            L82:
                r8.S()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: c.b.b.r.i.n.v.b(c.b.b.t.a):java.util.BitSet");
        }

        @Override // c.b.b.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(c.b.b.t.b bVar, BitSet bitSet) throws IOException {
            if (bitSet == null) {
                bVar.Z();
                return;
            }
            bVar.O();
            for (int i2 = 0; i2 < bitSet.length(); i2++) {
                bVar.j0(bitSet.get(i2) ? 1L : 0L);
            }
            bVar.R();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class w implements c.b.b.p {
        @Override // c.b.b.p
        public <T> c.b.b.o<T> a(c.b.b.d dVar, c.b.b.s.a<T> aVar) {
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
    public static class x implements c.b.b.p {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Class f4289b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ c.b.b.o f4290c;

        public x(Class cls, c.b.b.o oVar) {
            this.f4289b = cls;
            this.f4290c = oVar;
        }

        @Override // c.b.b.p
        public <T> c.b.b.o<T> a(c.b.b.d dVar, c.b.b.s.a<T> aVar) {
            if (aVar.d() == this.f4289b) {
                return this.f4290c;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.f4289b.getName() + ",adapter=" + this.f4290c + "]";
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class y implements c.b.b.p {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Class f4291b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Class f4292c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ c.b.b.o f4293d;

        public y(Class cls, Class cls2, c.b.b.o oVar) {
            this.f4291b = cls;
            this.f4292c = cls2;
            this.f4293d = oVar;
        }

        @Override // c.b.b.p
        public <T> c.b.b.o<T> a(c.b.b.d dVar, c.b.b.s.a<T> aVar) {
            Class<? super T> clsD = aVar.d();
            if (clsD == this.f4291b || clsD == this.f4292c) {
                return this.f4293d;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.f4292c.getName() + "+" + this.f4291b.getName() + ",adapter=" + this.f4293d + "]";
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class z implements c.b.b.p {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Class f4294b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Class f4295c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ c.b.b.o f4296d;

        public z(Class cls, Class cls2, c.b.b.o oVar) {
            this.f4294b = cls;
            this.f4295c = cls2;
            this.f4296d = oVar;
        }

        @Override // c.b.b.p
        public <T> c.b.b.o<T> a(c.b.b.d dVar, c.b.b.s.a<T> aVar) {
            Class<? super T> clsD = aVar.d();
            if (clsD == this.f4294b || clsD == this.f4295c) {
                return this.f4296d;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.f4294b.getName() + "+" + this.f4295c.getName() + ",adapter=" + this.f4296d + "]";
        }
    }

    static {
        k kVar = new k();
        f4271a = kVar;
        f4272b = a(Class.class, kVar);
        v vVar = new v();
        f4273c = vVar;
        f4274d = a(BitSet.class, vVar);
        c0 c0Var = new c0();
        f4275e = c0Var;
        f4276f = new d0();
        f4277g = b(Boolean.TYPE, Boolean.class, c0Var);
        e0 e0Var = new e0();
        f4278h = e0Var;
        f4279i = b(Byte.TYPE, Byte.class, e0Var);
        f0 f0Var = new f0();
        j = f0Var;
        k = b(Short.TYPE, Short.class, f0Var);
        g0 g0Var = new g0();
        l = g0Var;
        m = b(Integer.TYPE, Integer.class, g0Var);
        c.b.b.o<AtomicInteger> oVarA = new h0().a();
        n = oVarA;
        o = a(AtomicInteger.class, oVarA);
        c.b.b.o<AtomicBoolean> oVarA2 = new i0().a();
        p = oVarA2;
        q = a(AtomicBoolean.class, oVarA2);
        c.b.b.o<AtomicIntegerArray> oVarA3 = new a().a();
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
        C0098n c0098n = new C0098n();
        K = c0098n;
        L = a(URI.class, c0098n);
        o oVar = new o();
        M = oVar;
        N = d(InetAddress.class, oVar);
        p pVar = new p();
        O = pVar;
        P = a(UUID.class, pVar);
        c.b.b.o<Currency> oVarA4 = new q().a();
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
        Y = d(c.b.b.i.class, uVar);
        Z = new w();
    }

    public static <TT> c.b.b.p a(Class<TT> cls, c.b.b.o<TT> oVar) {
        return new x(cls, oVar);
    }

    public static <TT> c.b.b.p b(Class<TT> cls, Class<TT> cls2, c.b.b.o<? super TT> oVar) {
        return new y(cls, cls2, oVar);
    }

    public static <TT> c.b.b.p c(Class<TT> cls, Class<? extends TT> cls2, c.b.b.o<? super TT> oVar) {
        return new z(cls, cls2, oVar);
    }

    public static <T1> c.b.b.p d(Class<T1> cls, c.b.b.o<T1> oVar) {
        return new a0(cls, oVar);
    }
}
