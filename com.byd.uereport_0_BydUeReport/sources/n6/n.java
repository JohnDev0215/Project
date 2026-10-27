package n6;

import com.byd.systemsettings.system.BydPowerManagerDevice;
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
/* JADX INFO: loaded from: classes2.dex */
public final class n {
    public static final k6.o<String> A;
    public static final k6.o<BigDecimal> B;
    public static final k6.o<BigInteger> C;
    public static final k6.p D;
    public static final k6.o<StringBuilder> E;
    public static final k6.p F;
    public static final k6.o<StringBuffer> G;
    public static final k6.p H;
    public static final k6.o<URL> I;
    public static final k6.p J;
    public static final k6.o<URI> K;
    public static final k6.p L;
    public static final k6.o<InetAddress> M;
    public static final k6.p N;
    public static final k6.o<UUID> O;
    public static final k6.p P;
    public static final k6.o<Currency> Q;
    public static final k6.p R;
    public static final k6.p S;
    public static final k6.o<Calendar> T;
    public static final k6.p U;
    public static final k6.o<Locale> V;
    public static final k6.p W;
    public static final k6.o<k6.i> X;
    public static final k6.p Y;
    public static final k6.p Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final k6.o<Class> f6613a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final k6.p f6614b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final k6.o<BitSet> f6615c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final k6.p f6616d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final k6.o<Boolean> f6617e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final k6.o<Boolean> f6618f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final k6.p f6619g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final k6.o<Number> f6620h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final k6.p f6621i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final k6.o<Number> f6622j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final k6.p f6623k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final k6.o<Number> f6624l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final k6.p f6625m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final k6.o<AtomicInteger> f6626n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final k6.p f6627o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final k6.o<AtomicBoolean> f6628p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final k6.p f6629q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final k6.o<AtomicIntegerArray> f6630r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final k6.p f6631s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final k6.o<Number> f6632t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final k6.o<Number> f6633u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final k6.o<Number> f6634v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final k6.o<Number> f6635w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final k6.p f6636x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final k6.o<Character> f6637y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final k6.p f6638z;

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class a extends k6.o<AtomicIntegerArray> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicIntegerArray b(q6.a aVar) throws IOException {
            ArrayList arrayList = new ArrayList();
            aVar.g();
            while (aVar.t()) {
                try {
                    arrayList.add(Integer.valueOf(aVar.E()));
                } catch (NumberFormatException e8) {
                    throw new JsonSyntaxException(e8);
                }
            }
            aVar.o();
            int size = arrayList.size();
            AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
            for (int i8 = 0; i8 < size; i8++) {
                atomicIntegerArray.set(i8, ((Integer) arrayList.get(i8)).intValue());
            }
            return atomicIntegerArray;
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, AtomicIntegerArray atomicIntegerArray) throws IOException {
            bVar.i();
            int length = atomicIntegerArray.length();
            for (int i8 = 0; i8 < length; i8++) {
                bVar.R(atomicIntegerArray.get(i8));
            }
            bVar.n();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class a0 implements k6.p {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Class f6639d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ k6.o f6640e;

        /* JADX INFO: Add missing generic type declarations: [T1] */
        /* JADX INFO: compiled from: TypeAdapters.java */
        public class a<T1> extends k6.o<T1> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Class f6641a;

            public a(Class cls) {
                this.f6641a = cls;
            }

            @Override // k6.o
            public T1 b(q6.a aVar) throws IOException {
                T1 t12 = (T1) a0.this.f6640e.b(aVar);
                if (t12 == null || this.f6641a.isInstance(t12)) {
                    return t12;
                }
                throw new JsonSyntaxException("Expected a " + this.f6641a.getName() + " but was " + t12.getClass().getName());
            }

            @Override // k6.o
            public void d(q6.b bVar, T1 t12) throws IOException {
                a0.this.f6640e.d(bVar, t12);
            }
        }

        public a0(Class cls, k6.o oVar) {
            this.f6639d = cls;
            this.f6640e = oVar;
        }

        @Override // k6.p
        public <T2> k6.o<T2> a(k6.d dVar, p6.a<T2> aVar) {
            Class<? super T2> clsD = aVar.d();
            if (this.f6639d.isAssignableFrom(clsD)) {
                return new a(clsD);
            }
            return null;
        }

        public String toString() {
            return "Factory[typeHierarchy=" + this.f6639d.getName() + ",adapter=" + this.f6640e + "]";
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class b extends k6.o<Number> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(q6.a aVar) throws IOException {
            if (aVar.R() == JsonToken.NULL) {
                aVar.M();
                return null;
            }
            try {
                return Long.valueOf(aVar.F());
            } catch (NumberFormatException e8) {
                throw new JsonSyntaxException(e8);
            }
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, Number number) throws IOException {
            bVar.V(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static /* synthetic */ class b0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f6643a;

        static {
            int[] iArr = new int[JsonToken.values().length];
            f6643a = iArr;
            try {
                iArr[JsonToken.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f6643a[JsonToken.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f6643a[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f6643a[JsonToken.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f6643a[JsonToken.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f6643a[JsonToken.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f6643a[JsonToken.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f6643a[JsonToken.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f6643a[JsonToken.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f6643a[JsonToken.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class c extends k6.o<Number> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(q6.a aVar) throws IOException {
            if (aVar.R() != JsonToken.NULL) {
                return Float.valueOf((float) aVar.C());
            }
            aVar.M();
            return null;
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, Number number) throws IOException {
            bVar.V(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class c0 extends k6.o<Boolean> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Boolean b(q6.a aVar) throws IOException {
            if (aVar.R() != JsonToken.NULL) {
                return aVar.R() == JsonToken.STRING ? Boolean.valueOf(Boolean.parseBoolean(aVar.O())) : Boolean.valueOf(aVar.B());
            }
            aVar.M();
            return null;
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, Boolean bool) throws IOException {
            bVar.T(bool);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class d extends k6.o<Number> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(q6.a aVar) throws IOException {
            if (aVar.R() != JsonToken.NULL) {
                return Double.valueOf(aVar.C());
            }
            aVar.M();
            return null;
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, Number number) throws IOException {
            bVar.V(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class d0 extends k6.o<Boolean> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Boolean b(q6.a aVar) throws IOException {
            if (aVar.R() != JsonToken.NULL) {
                return Boolean.valueOf(aVar.O());
            }
            aVar.M();
            return null;
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, Boolean bool) throws IOException {
            bVar.X(bool == null ? "null" : bool.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class e extends k6.o<Number> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(q6.a aVar) throws IOException {
            JsonToken jsonTokenR = aVar.R();
            int i8 = b0.f6643a[jsonTokenR.ordinal()];
            if (i8 == 1) {
                return new LazilyParsedNumber(aVar.O());
            }
            if (i8 == 4) {
                aVar.M();
                return null;
            }
            throw new JsonSyntaxException("Expecting number, got: " + jsonTokenR);
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, Number number) throws IOException {
            bVar.V(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class e0 extends k6.o<Number> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(q6.a aVar) throws IOException {
            if (aVar.R() == JsonToken.NULL) {
                aVar.M();
                return null;
            }
            try {
                return Byte.valueOf((byte) aVar.E());
            } catch (NumberFormatException e8) {
                throw new JsonSyntaxException(e8);
            }
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, Number number) throws IOException {
            bVar.V(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class f extends k6.o<Character> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Character b(q6.a aVar) throws IOException {
            if (aVar.R() == JsonToken.NULL) {
                aVar.M();
                return null;
            }
            String strO = aVar.O();
            if (strO.length() == 1) {
                return Character.valueOf(strO.charAt(0));
            }
            throw new JsonSyntaxException("Expecting character, got: " + strO);
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, Character ch) throws IOException {
            bVar.X(ch == null ? null : String.valueOf(ch));
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class f0 extends k6.o<Number> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(q6.a aVar) throws IOException {
            if (aVar.R() == JsonToken.NULL) {
                aVar.M();
                return null;
            }
            try {
                return Short.valueOf((short) aVar.E());
            } catch (NumberFormatException e8) {
                throw new JsonSyntaxException(e8);
            }
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, Number number) throws IOException {
            bVar.V(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class g extends k6.o<String> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public String b(q6.a aVar) throws IOException {
            JsonToken jsonTokenR = aVar.R();
            if (jsonTokenR != JsonToken.NULL) {
                return jsonTokenR == JsonToken.BOOLEAN ? Boolean.toString(aVar.B()) : aVar.O();
            }
            aVar.M();
            return null;
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, String str) throws IOException {
            bVar.X(str);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class g0 extends k6.o<Number> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(q6.a aVar) throws IOException {
            if (aVar.R() == JsonToken.NULL) {
                aVar.M();
                return null;
            }
            try {
                return Integer.valueOf(aVar.E());
            } catch (NumberFormatException e8) {
                throw new JsonSyntaxException(e8);
            }
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, Number number) throws IOException {
            bVar.V(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class h extends k6.o<BigDecimal> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public BigDecimal b(q6.a aVar) throws IOException {
            if (aVar.R() == JsonToken.NULL) {
                aVar.M();
                return null;
            }
            try {
                return new BigDecimal(aVar.O());
            } catch (NumberFormatException e8) {
                throw new JsonSyntaxException(e8);
            }
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, BigDecimal bigDecimal) throws IOException {
            bVar.V(bigDecimal);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class h0 extends k6.o<AtomicInteger> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicInteger b(q6.a aVar) throws IOException {
            try {
                return new AtomicInteger(aVar.E());
            } catch (NumberFormatException e8) {
                throw new JsonSyntaxException(e8);
            }
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, AtomicInteger atomicInteger) throws IOException {
            bVar.R(atomicInteger.get());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class i extends k6.o<BigInteger> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public BigInteger b(q6.a aVar) throws IOException {
            if (aVar.R() == JsonToken.NULL) {
                aVar.M();
                return null;
            }
            try {
                return new BigInteger(aVar.O());
            } catch (NumberFormatException e8) {
                throw new JsonSyntaxException(e8);
            }
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, BigInteger bigInteger) throws IOException {
            bVar.V(bigInteger);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class i0 extends k6.o<AtomicBoolean> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicBoolean b(q6.a aVar) throws IOException {
            return new AtomicBoolean(aVar.B());
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, AtomicBoolean atomicBoolean) throws IOException {
            bVar.Y(atomicBoolean.get());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class j extends k6.o<StringBuilder> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public StringBuilder b(q6.a aVar) throws IOException {
            if (aVar.R() != JsonToken.NULL) {
                return new StringBuilder(aVar.O());
            }
            aVar.M();
            return null;
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, StringBuilder sb) throws IOException {
            bVar.X(sb == null ? null : sb.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static final class j0<T extends Enum<T>> extends k6.o<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Map<String, T> f6644a = new HashMap();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Map<T, String> f6645b = new HashMap();

        public j0(Class<T> cls) {
            try {
                for (T t7 : cls.getEnumConstants()) {
                    String strName = t7.name();
                    l6.c cVar = (l6.c) cls.getField(strName).getAnnotation(l6.c.class);
                    if (cVar != null) {
                        strName = cVar.value();
                        for (String str : cVar.alternate()) {
                            this.f6644a.put(str, t7);
                        }
                    }
                    this.f6644a.put(strName, t7);
                    this.f6645b.put(t7, strName);
                }
            } catch (NoSuchFieldException e8) {
                throw new AssertionError(e8);
            }
        }

        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public T b(q6.a aVar) throws IOException {
            if (aVar.R() != JsonToken.NULL) {
                return this.f6644a.get(aVar.O());
            }
            aVar.M();
            return null;
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, T t7) throws IOException {
            bVar.X(t7 == null ? null : this.f6645b.get(t7));
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class k extends k6.o<Class> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Class b(q6.a aVar) throws IOException {
            if (aVar.R() != JsonToken.NULL) {
                throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
            }
            aVar.M();
            return null;
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, Class cls) throws IOException {
            if (cls == null) {
                bVar.B();
                return;
            }
            throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?");
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class l extends k6.o<StringBuffer> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public StringBuffer b(q6.a aVar) throws IOException {
            if (aVar.R() != JsonToken.NULL) {
                return new StringBuffer(aVar.O());
            }
            aVar.M();
            return null;
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, StringBuffer stringBuffer) throws IOException {
            bVar.X(stringBuffer == null ? null : stringBuffer.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class m extends k6.o<URL> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public URL b(q6.a aVar) throws IOException {
            if (aVar.R() == JsonToken.NULL) {
                aVar.M();
                return null;
            }
            String strO = aVar.O();
            if ("null".equals(strO)) {
                return null;
            }
            return new URL(strO);
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, URL url) throws IOException {
            bVar.X(url == null ? null : url.toExternalForm());
        }
    }

    /* JADX INFO: renamed from: n6.n$n, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class C0096n extends k6.o<URI> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public URI b(q6.a aVar) throws IOException {
            if (aVar.R() == JsonToken.NULL) {
                aVar.M();
                return null;
            }
            try {
                String strO = aVar.O();
                if ("null".equals(strO)) {
                    return null;
                }
                return new URI(strO);
            } catch (URISyntaxException e8) {
                throw new JsonIOException(e8);
            }
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, URI uri) throws IOException {
            bVar.X(uri == null ? null : uri.toASCIIString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class o extends k6.o<InetAddress> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public InetAddress b(q6.a aVar) throws IOException {
            if (aVar.R() != JsonToken.NULL) {
                return InetAddress.getByName(aVar.O());
            }
            aVar.M();
            return null;
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, InetAddress inetAddress) throws IOException {
            bVar.X(inetAddress == null ? null : inetAddress.getHostAddress());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class p extends k6.o<UUID> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public UUID b(q6.a aVar) throws IOException {
            if (aVar.R() != JsonToken.NULL) {
                return UUID.fromString(aVar.O());
            }
            aVar.M();
            return null;
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, UUID uuid) throws IOException {
            bVar.X(uuid == null ? null : uuid.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class q extends k6.o<Currency> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Currency b(q6.a aVar) throws IOException {
            return Currency.getInstance(aVar.O());
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, Currency currency) throws IOException {
            bVar.X(currency.getCurrencyCode());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class r implements k6.p {

        /* JADX INFO: compiled from: TypeAdapters.java */
        public class a extends k6.o<Timestamp> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ k6.o f6646a;

            public a(k6.o oVar) {
                this.f6646a = oVar;
            }

            @Override // k6.o
            /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
            public Timestamp b(q6.a aVar) throws IOException {
                Date date = (Date) this.f6646a.b(aVar);
                if (date != null) {
                    return new Timestamp(date.getTime());
                }
                return null;
            }

            @Override // k6.o
            /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
            public void d(q6.b bVar, Timestamp timestamp) throws IOException {
                this.f6646a.d(bVar, timestamp);
            }
        }

        @Override // k6.p
        public <T> k6.o<T> a(k6.d dVar, p6.a<T> aVar) {
            if (aVar.d() != Timestamp.class) {
                return null;
            }
            return new a(dVar.l(Date.class));
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class s extends k6.o<Calendar> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Calendar b(q6.a aVar) throws IOException {
            if (aVar.R() == JsonToken.NULL) {
                aVar.M();
                return null;
            }
            aVar.i();
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            int i11 = 0;
            int i12 = 0;
            int i13 = 0;
            while (aVar.R() != JsonToken.END_OBJECT) {
                String strI = aVar.I();
                int iE = aVar.E();
                if ("year".equals(strI)) {
                    i8 = iE;
                } else if ("month".equals(strI)) {
                    i9 = iE;
                } else if ("dayOfMonth".equals(strI)) {
                    i10 = iE;
                } else if ("hourOfDay".equals(strI)) {
                    i11 = iE;
                } else if ("minute".equals(strI)) {
                    i12 = iE;
                } else if ("second".equals(strI)) {
                    i13 = iE;
                }
            }
            aVar.q();
            return new GregorianCalendar(i8, i9, i10, i11, i12, i13);
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, Calendar calendar) throws IOException {
            if (calendar == null) {
                bVar.B();
                return;
            }
            bVar.j();
            bVar.w("year");
            bVar.R(calendar.get(1));
            bVar.w("month");
            bVar.R(calendar.get(2));
            bVar.w("dayOfMonth");
            bVar.R(calendar.get(5));
            bVar.w("hourOfDay");
            bVar.R(calendar.get(11));
            bVar.w("minute");
            bVar.R(calendar.get(12));
            bVar.w("second");
            bVar.R(calendar.get(13));
            bVar.o();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class t extends k6.o<Locale> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Locale b(q6.a aVar) throws IOException {
            if (aVar.R() == JsonToken.NULL) {
                aVar.M();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(aVar.O(), BydPowerManagerDevice.SEPARATOR_K_V);
            String strNextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String strNextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String strNextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            return (strNextToken2 == null && strNextToken3 == null) ? new Locale(strNextToken) : strNextToken3 == null ? new Locale(strNextToken, strNextToken2) : new Locale(strNextToken, strNextToken2, strNextToken3);
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, Locale locale) throws IOException {
            bVar.X(locale == null ? null : locale.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class u extends k6.o<k6.i> {
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public k6.i b(q6.a aVar) throws IOException {
            switch (b0.f6643a[aVar.R().ordinal()]) {
                case 1:
                    return new k6.l(new LazilyParsedNumber(aVar.O()));
                case 2:
                    return new k6.l(Boolean.valueOf(aVar.B()));
                case 3:
                    return new k6.l(aVar.O());
                case 4:
                    aVar.M();
                    return k6.j.f5922a;
                case 5:
                    k6.f fVar = new k6.f();
                    aVar.g();
                    while (aVar.t()) {
                        fVar.i(b(aVar));
                    }
                    aVar.o();
                    return fVar;
                case 6:
                    k6.k kVar = new k6.k();
                    aVar.i();
                    while (aVar.t()) {
                        kVar.i(aVar.I(), b(aVar));
                    }
                    aVar.q();
                    return kVar;
                default:
                    throw new IllegalArgumentException();
            }
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, k6.i iVar) throws IOException {
            if (iVar == null || iVar.e()) {
                bVar.B();
                return;
            }
            if (iVar.g()) {
                k6.l lVarC = iVar.c();
                if (lVarC.r()) {
                    bVar.V(lVarC.n());
                    return;
                } else if (lVarC.p()) {
                    bVar.Y(lVarC.i());
                    return;
                } else {
                    bVar.X(lVarC.o());
                    return;
                }
            }
            if (iVar.d()) {
                bVar.i();
                Iterator<k6.i> it = iVar.a().iterator();
                while (it.hasNext()) {
                    d(bVar, it.next());
                }
                bVar.n();
                return;
            }
            if (!iVar.f()) {
                if (iVar.h()) {
                    bVar.v(iVar.toString());
                    return;
                }
                throw new IllegalArgumentException("Couldn't write " + iVar.getClass());
            }
            bVar.j();
            for (Map.Entry<String, k6.i> entry : iVar.b().j()) {
                bVar.w(entry.getKey());
                d(bVar, entry.getValue());
            }
            bVar.o();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class v extends k6.o<BitSet> {
        /* JADX WARN: Removed duplicated region for block: B:19:0x003b  */
        @Override // k6.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.util.BitSet b(q6.a r8) throws java.io.IOException {
            /*
                r7 = this;
                com.google.gson.stream.JsonToken r0 = r8.R()
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.NULL
                if (r0 != r1) goto Ld
                r8.M()
                r8 = 0
                return r8
            Ld:
                java.util.BitSet r0 = new java.util.BitSet
                r0.<init>()
                r8.g()
                com.google.gson.stream.JsonToken r1 = r8.R()
                r2 = 0
                r3 = r2
            L1b:
                com.google.gson.stream.JsonToken r4 = com.google.gson.stream.JsonToken.END_ARRAY
                if (r1 == r4) goto L82
                int[] r4 = n6.n.b0.f6643a
                int r5 = r1.ordinal()
                r4 = r4[r5]
                r5 = 1
                if (r4 == r5) goto L70
                r6 = 2
                if (r4 == r6) goto L6b
                r6 = 3
                if (r4 != r6) goto L54
                java.lang.String r1 = r8.O()
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
                boolean r5 = r8.B()
                goto L76
            L70:
                int r1 = r8.E()
                if (r1 == 0) goto L3b
            L76:
                if (r5 == 0) goto L7b
                r0.set(r3)
            L7b:
                int r3 = r3 + 1
                com.google.gson.stream.JsonToken r1 = r8.R()
                goto L1b
            L82:
                r8.o()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: n6.n.v.b(q6.a):java.util.BitSet");
        }

        @Override // k6.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(q6.b bVar, BitSet bitSet) throws IOException {
            if (bitSet == null) {
                bVar.B();
                return;
            }
            bVar.i();
            for (int i8 = 0; i8 < bitSet.length(); i8++) {
                bVar.R(bitSet.get(i8) ? 1L : 0L);
            }
            bVar.n();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class w implements k6.p {
        @Override // k6.p
        public <T> k6.o<T> a(k6.d dVar, p6.a<T> aVar) {
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
    public static class x implements k6.p {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Class f6648d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ k6.o f6649e;

        public x(Class cls, k6.o oVar) {
            this.f6648d = cls;
            this.f6649e = oVar;
        }

        @Override // k6.p
        public <T> k6.o<T> a(k6.d dVar, p6.a<T> aVar) {
            if (aVar.d() == this.f6648d) {
                return this.f6649e;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.f6648d.getName() + ",adapter=" + this.f6649e + "]";
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class y implements k6.p {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Class f6650d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Class f6651e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ k6.o f6652f;

        public y(Class cls, Class cls2, k6.o oVar) {
            this.f6650d = cls;
            this.f6651e = cls2;
            this.f6652f = oVar;
        }

        @Override // k6.p
        public <T> k6.o<T> a(k6.d dVar, p6.a<T> aVar) {
            Class<? super T> clsD = aVar.d();
            if (clsD == this.f6650d || clsD == this.f6651e) {
                return this.f6652f;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.f6651e.getName() + "+" + this.f6650d.getName() + ",adapter=" + this.f6652f + "]";
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class z implements k6.p {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Class f6653d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Class f6654e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ k6.o f6655f;

        public z(Class cls, Class cls2, k6.o oVar) {
            this.f6653d = cls;
            this.f6654e = cls2;
            this.f6655f = oVar;
        }

        @Override // k6.p
        public <T> k6.o<T> a(k6.d dVar, p6.a<T> aVar) {
            Class<? super T> clsD = aVar.d();
            if (clsD == this.f6653d || clsD == this.f6654e) {
                return this.f6655f;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.f6653d.getName() + "+" + this.f6654e.getName() + ",adapter=" + this.f6655f + "]";
        }
    }

    static {
        k kVar = new k();
        f6613a = kVar;
        f6614b = a(Class.class, kVar);
        v vVar = new v();
        f6615c = vVar;
        f6616d = a(BitSet.class, vVar);
        c0 c0Var = new c0();
        f6617e = c0Var;
        f6618f = new d0();
        f6619g = b(Boolean.TYPE, Boolean.class, c0Var);
        e0 e0Var = new e0();
        f6620h = e0Var;
        f6621i = b(Byte.TYPE, Byte.class, e0Var);
        f0 f0Var = new f0();
        f6622j = f0Var;
        f6623k = b(Short.TYPE, Short.class, f0Var);
        g0 g0Var = new g0();
        f6624l = g0Var;
        f6625m = b(Integer.TYPE, Integer.class, g0Var);
        k6.o<AtomicInteger> oVarA = new h0().a();
        f6626n = oVarA;
        f6627o = a(AtomicInteger.class, oVarA);
        k6.o<AtomicBoolean> oVarA2 = new i0().a();
        f6628p = oVarA2;
        f6629q = a(AtomicBoolean.class, oVarA2);
        k6.o<AtomicIntegerArray> oVarA3 = new a().a();
        f6630r = oVarA3;
        f6631s = a(AtomicIntegerArray.class, oVarA3);
        f6632t = new b();
        f6633u = new c();
        f6634v = new d();
        e eVar = new e();
        f6635w = eVar;
        f6636x = a(Number.class, eVar);
        f fVar = new f();
        f6637y = fVar;
        f6638z = b(Character.TYPE, Character.class, fVar);
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
        C0096n c0096n = new C0096n();
        K = c0096n;
        L = a(URI.class, c0096n);
        o oVar = new o();
        M = oVar;
        N = d(InetAddress.class, oVar);
        p pVar = new p();
        O = pVar;
        P = a(UUID.class, pVar);
        k6.o<Currency> oVarA4 = new q().a();
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
        Y = d(k6.i.class, uVar);
        Z = new w();
    }

    public static <TT> k6.p a(Class<TT> cls, k6.o<TT> oVar) {
        return new x(cls, oVar);
    }

    public static <TT> k6.p b(Class<TT> cls, Class<TT> cls2, k6.o<? super TT> oVar) {
        return new y(cls, cls2, oVar);
    }

    public static <TT> k6.p c(Class<TT> cls, Class<? extends TT> cls2, k6.o<? super TT> oVar) {
        return new z(cls, cls2, oVar);
    }

    public static <T1> k6.p d(Class<T1> cls, k6.o<T1> oVar) {
        return new a0(cls, oVar);
    }
}
