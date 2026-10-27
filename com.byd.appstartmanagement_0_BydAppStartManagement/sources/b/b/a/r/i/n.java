package b.b.a.r.i;

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
/* JADX INFO: loaded from: classes2.dex */
public final class n {
    public static final b.b.a.o<String> A;
    public static final b.b.a.o<BigDecimal> B;
    public static final b.b.a.o<BigInteger> C;
    public static final b.b.a.p D;
    public static final b.b.a.o<StringBuilder> E;
    public static final b.b.a.p F;
    public static final b.b.a.o<StringBuffer> G;
    public static final b.b.a.p H;
    public static final b.b.a.o<URL> I;
    public static final b.b.a.p J;
    public static final b.b.a.o<URI> K;
    public static final b.b.a.p L;
    public static final b.b.a.o<InetAddress> M;
    public static final b.b.a.p N;
    public static final b.b.a.o<UUID> O;
    public static final b.b.a.p P;
    public static final b.b.a.o<Currency> Q;
    public static final b.b.a.p R;
    public static final b.b.a.p S;
    public static final b.b.a.o<Calendar> T;
    public static final b.b.a.p U;
    public static final b.b.a.o<Locale> V;
    public static final b.b.a.p W;
    public static final b.b.a.o<b.b.a.i> X;
    public static final b.b.a.p Y;
    public static final b.b.a.p Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b.b.a.o<Class> f474a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b.b.a.p f475b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final b.b.a.o<BitSet> f476c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b.b.a.p f477d;
    public static final b.b.a.o<Boolean> e;
    public static final b.b.a.o<Boolean> f;
    public static final b.b.a.p g;
    public static final b.b.a.o<Number> h;
    public static final b.b.a.p i;
    public static final b.b.a.o<Number> j;
    public static final b.b.a.p k;
    public static final b.b.a.o<Number> l;
    public static final b.b.a.p m;
    public static final b.b.a.o<AtomicInteger> n;
    public static final b.b.a.p o;
    public static final b.b.a.o<AtomicBoolean> p;
    public static final b.b.a.p q;
    public static final b.b.a.o<AtomicIntegerArray> r;
    public static final b.b.a.p s;
    public static final b.b.a.o<Number> t;
    public static final b.b.a.o<Number> u;
    public static final b.b.a.o<Number> v;
    public static final b.b.a.o<Number> w;
    public static final b.b.a.p x;
    public static final b.b.a.o<Character> y;
    public static final b.b.a.p z;

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class a extends b.b.a.o<AtomicIntegerArray> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicIntegerArray b(b.b.a.t.a aVar) throws IOException {
            ArrayList arrayList = new ArrayList();
            aVar.b();
            while (aVar.k()) {
                try {
                    arrayList.add(Integer.valueOf(aVar.q()));
                } catch (NumberFormatException e) {
                    throw new JsonSyntaxException(e);
                }
            }
            aVar.g();
            int size = arrayList.size();
            AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
            for (int i = 0; i < size; i++) {
                atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
            }
            return atomicIntegerArray;
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, AtomicIntegerArray atomicIntegerArray) throws IOException {
            bVar.c();
            int length = atomicIntegerArray.length();
            for (int i = 0; i < length; i++) {
                bVar.x(atomicIntegerArray.get(i));
            }
            bVar.f();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class a0 implements b.b.a.p {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Class f478b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ b.b.a.o f479c;

        /* JADX INFO: Add missing generic type declarations: [T1] */
        /* JADX INFO: compiled from: TypeAdapters.java */
        public class a<T1> extends b.b.a.o<T1> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Class f480a;

            public a(Class cls) {
                this.f480a = cls;
            }

            @Override // b.b.a.o
            public T1 b(b.b.a.t.a aVar) throws IOException {
                T1 t1 = (T1) a0.this.f479c.b(aVar);
                if (t1 == null || this.f480a.isInstance(t1)) {
                    return t1;
                }
                throw new JsonSyntaxException("Expected a " + this.f480a.getName() + " but was " + t1.getClass().getName());
            }

            @Override // b.b.a.o
            public void d(b.b.a.t.b bVar, T1 t1) throws IOException {
                a0.this.f479c.d(bVar, t1);
            }
        }

        public a0(Class cls, b.b.a.o oVar) {
            this.f478b = cls;
            this.f479c = oVar;
        }

        @Override // b.b.a.p
        public <T2> b.b.a.o<T2> a(b.b.a.d dVar, b.b.a.s.a<T2> aVar) {
            Class<? super T2> clsD = aVar.d();
            if (this.f478b.isAssignableFrom(clsD)) {
                return new a(clsD);
            }
            return null;
        }

        public String toString() {
            return "Factory[typeHierarchy=" + this.f478b.getName() + ",adapter=" + this.f479c + "]";
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class b extends b.b.a.o<Number> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() == JsonToken.NULL) {
                aVar.u();
                return null;
            }
            try {
                return Long.valueOf(aVar.r());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, Number number) throws IOException {
            bVar.z(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static /* synthetic */ class b0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f482a;

        static {
            int[] iArr = new int[JsonToken.values().length];
            f482a = iArr;
            try {
                iArr[JsonToken.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f482a[JsonToken.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f482a[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f482a[JsonToken.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f482a[JsonToken.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f482a[JsonToken.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f482a[JsonToken.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f482a[JsonToken.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f482a[JsonToken.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f482a[JsonToken.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class c extends b.b.a.o<Number> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() != JsonToken.NULL) {
                return Float.valueOf((float) aVar.p());
            }
            aVar.u();
            return null;
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, Number number) throws IOException {
            bVar.z(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class c0 extends b.b.a.o<Boolean> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Boolean b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() != JsonToken.NULL) {
                return aVar.y() == JsonToken.STRING ? Boolean.valueOf(Boolean.parseBoolean(aVar.w())) : Boolean.valueOf(aVar.o());
            }
            aVar.u();
            return null;
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, Boolean bool) throws IOException {
            bVar.y(bool);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class d extends b.b.a.o<Number> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() != JsonToken.NULL) {
                return Double.valueOf(aVar.p());
            }
            aVar.u();
            return null;
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, Number number) throws IOException {
            bVar.z(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class d0 extends b.b.a.o<Boolean> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Boolean b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() != JsonToken.NULL) {
                return Boolean.valueOf(aVar.w());
            }
            aVar.u();
            return null;
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, Boolean bool) throws IOException {
            bVar.A(bool == null ? "null" : bool.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class e extends b.b.a.o<Number> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(b.b.a.t.a aVar) throws IOException {
            JsonToken jsonTokenY = aVar.y();
            int i = b0.f482a[jsonTokenY.ordinal()];
            if (i == 1) {
                return new LazilyParsedNumber(aVar.w());
            }
            if (i == 4) {
                aVar.u();
                return null;
            }
            throw new JsonSyntaxException("Expecting number, got: " + jsonTokenY);
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, Number number) throws IOException {
            bVar.z(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class e0 extends b.b.a.o<Number> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() == JsonToken.NULL) {
                aVar.u();
                return null;
            }
            try {
                return Byte.valueOf((byte) aVar.q());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, Number number) throws IOException {
            bVar.z(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class f extends b.b.a.o<Character> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Character b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() == JsonToken.NULL) {
                aVar.u();
                return null;
            }
            String strW = aVar.w();
            if (strW.length() == 1) {
                return Character.valueOf(strW.charAt(0));
            }
            throw new JsonSyntaxException("Expecting character, got: " + strW);
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, Character ch) throws IOException {
            bVar.A(ch == null ? null : String.valueOf(ch));
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class f0 extends b.b.a.o<Number> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() == JsonToken.NULL) {
                aVar.u();
                return null;
            }
            try {
                return Short.valueOf((short) aVar.q());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, Number number) throws IOException {
            bVar.z(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class g extends b.b.a.o<String> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public String b(b.b.a.t.a aVar) throws IOException {
            JsonToken jsonTokenY = aVar.y();
            if (jsonTokenY != JsonToken.NULL) {
                return jsonTokenY == JsonToken.BOOLEAN ? Boolean.toString(aVar.o()) : aVar.w();
            }
            aVar.u();
            return null;
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, String str) throws IOException {
            bVar.A(str);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class g0 extends b.b.a.o<Number> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() == JsonToken.NULL) {
                aVar.u();
                return null;
            }
            try {
                return Integer.valueOf(aVar.q());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, Number number) throws IOException {
            bVar.z(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class h extends b.b.a.o<BigDecimal> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public BigDecimal b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() == JsonToken.NULL) {
                aVar.u();
                return null;
            }
            try {
                return new BigDecimal(aVar.w());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, BigDecimal bigDecimal) throws IOException {
            bVar.z(bigDecimal);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class h0 extends b.b.a.o<AtomicInteger> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicInteger b(b.b.a.t.a aVar) throws IOException {
            try {
                return new AtomicInteger(aVar.q());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, AtomicInteger atomicInteger) throws IOException {
            bVar.x(atomicInteger.get());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class i extends b.b.a.o<BigInteger> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public BigInteger b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() == JsonToken.NULL) {
                aVar.u();
                return null;
            }
            try {
                return new BigInteger(aVar.w());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, BigInteger bigInteger) throws IOException {
            bVar.z(bigInteger);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class i0 extends b.b.a.o<AtomicBoolean> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicBoolean b(b.b.a.t.a aVar) throws IOException {
            return new AtomicBoolean(aVar.o());
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, AtomicBoolean atomicBoolean) throws IOException {
            bVar.B(atomicBoolean.get());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class j extends b.b.a.o<StringBuilder> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public StringBuilder b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() != JsonToken.NULL) {
                return new StringBuilder(aVar.w());
            }
            aVar.u();
            return null;
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, StringBuilder sb) throws IOException {
            bVar.A(sb == null ? null : sb.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static final class j0<T extends Enum<T>> extends b.b.a.o<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Map<String, T> f483a = new HashMap();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Map<T, String> f484b = new HashMap();

        public j0(Class<T> cls) {
            try {
                for (T t : cls.getEnumConstants()) {
                    String strName = t.name();
                    b.b.a.q.c cVar = (b.b.a.q.c) cls.getField(strName).getAnnotation(b.b.a.q.c.class);
                    if (cVar != null) {
                        strName = cVar.value();
                        for (String str : cVar.alternate()) {
                            this.f483a.put(str, t);
                        }
                    }
                    this.f483a.put(strName, t);
                    this.f484b.put(t, strName);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError(e);
            }
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public T b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() != JsonToken.NULL) {
                return this.f483a.get(aVar.w());
            }
            aVar.u();
            return null;
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, T t) throws IOException {
            bVar.A(t == null ? null : this.f484b.get(t));
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class k extends b.b.a.o<Class> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Class b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() != JsonToken.NULL) {
                throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
            }
            aVar.u();
            return null;
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, Class cls) throws IOException {
            if (cls == null) {
                bVar.n();
                return;
            }
            throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?");
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class l extends b.b.a.o<StringBuffer> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public StringBuffer b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() != JsonToken.NULL) {
                return new StringBuffer(aVar.w());
            }
            aVar.u();
            return null;
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, StringBuffer stringBuffer) throws IOException {
            bVar.A(stringBuffer == null ? null : stringBuffer.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class m extends b.b.a.o<URL> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public URL b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() == JsonToken.NULL) {
                aVar.u();
                return null;
            }
            String strW = aVar.w();
            if ("null".equals(strW)) {
                return null;
            }
            return new URL(strW);
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, URL url) throws IOException {
            bVar.A(url == null ? null : url.toExternalForm());
        }
    }

    /* JADX INFO: renamed from: b.b.a.r.i.n$n, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class C0031n extends b.b.a.o<URI> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public URI b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() == JsonToken.NULL) {
                aVar.u();
                return null;
            }
            try {
                String strW = aVar.w();
                if ("null".equals(strW)) {
                    return null;
                }
                return new URI(strW);
            } catch (URISyntaxException e) {
                throw new JsonIOException(e);
            }
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, URI uri) throws IOException {
            bVar.A(uri == null ? null : uri.toASCIIString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class o extends b.b.a.o<InetAddress> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public InetAddress b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() != JsonToken.NULL) {
                return InetAddress.getByName(aVar.w());
            }
            aVar.u();
            return null;
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, InetAddress inetAddress) throws IOException {
            bVar.A(inetAddress == null ? null : inetAddress.getHostAddress());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class p extends b.b.a.o<UUID> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public UUID b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() != JsonToken.NULL) {
                return UUID.fromString(aVar.w());
            }
            aVar.u();
            return null;
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, UUID uuid) throws IOException {
            bVar.A(uuid == null ? null : uuid.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class q extends b.b.a.o<Currency> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Currency b(b.b.a.t.a aVar) throws IOException {
            return Currency.getInstance(aVar.w());
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, Currency currency) throws IOException {
            bVar.A(currency.getCurrencyCode());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class r implements b.b.a.p {

        /* JADX INFO: compiled from: TypeAdapters.java */
        public class a extends b.b.a.o<Timestamp> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ b.b.a.o f485a;

            public a(b.b.a.o oVar) {
                this.f485a = oVar;
            }

            @Override // b.b.a.o
            /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
            public Timestamp b(b.b.a.t.a aVar) throws IOException {
                Date date = (Date) this.f485a.b(aVar);
                if (date != null) {
                    return new Timestamp(date.getTime());
                }
                return null;
            }

            @Override // b.b.a.o
            /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
            public void d(b.b.a.t.b bVar, Timestamp timestamp) throws IOException {
                this.f485a.d(bVar, timestamp);
            }
        }

        @Override // b.b.a.p
        public <T> b.b.a.o<T> a(b.b.a.d dVar, b.b.a.s.a<T> aVar) {
            if (aVar.d() != Timestamp.class) {
                return null;
            }
            return new a(dVar.l(Date.class));
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class s extends b.b.a.o<Calendar> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Calendar b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() == JsonToken.NULL) {
                aVar.u();
                return null;
            }
            aVar.c();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (aVar.y() != JsonToken.END_OBJECT) {
                String strS = aVar.s();
                int iQ = aVar.q();
                if ("year".equals(strS)) {
                    i = iQ;
                } else if ("month".equals(strS)) {
                    i2 = iQ;
                } else if ("dayOfMonth".equals(strS)) {
                    i3 = iQ;
                } else if ("hourOfDay".equals(strS)) {
                    i4 = iQ;
                } else if ("minute".equals(strS)) {
                    i5 = iQ;
                } else if ("second".equals(strS)) {
                    i6 = iQ;
                }
            }
            aVar.h();
            return new GregorianCalendar(i, i2, i3, i4, i5, i6);
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, Calendar calendar) throws IOException {
            if (calendar == null) {
                bVar.n();
                return;
            }
            bVar.d();
            bVar.l("year");
            bVar.x(calendar.get(1));
            bVar.l("month");
            bVar.x(calendar.get(2));
            bVar.l("dayOfMonth");
            bVar.x(calendar.get(5));
            bVar.l("hourOfDay");
            bVar.x(calendar.get(11));
            bVar.l("minute");
            bVar.x(calendar.get(12));
            bVar.l("second");
            bVar.x(calendar.get(13));
            bVar.g();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class t extends b.b.a.o<Locale> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Locale b(b.b.a.t.a aVar) throws IOException {
            if (aVar.y() == JsonToken.NULL) {
                aVar.u();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(aVar.w(), "_");
            String strNextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String strNextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String strNextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            return (strNextToken2 == null && strNextToken3 == null) ? new Locale(strNextToken) : strNextToken3 == null ? new Locale(strNextToken, strNextToken2) : new Locale(strNextToken, strNextToken2, strNextToken3);
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, Locale locale) throws IOException {
            bVar.A(locale == null ? null : locale.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class u extends b.b.a.o<b.b.a.i> {
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public b.b.a.i b(b.b.a.t.a aVar) throws IOException {
            switch (b0.f482a[aVar.y().ordinal()]) {
                case 1:
                    return new b.b.a.l(new LazilyParsedNumber(aVar.w()));
                case 2:
                    return new b.b.a.l(Boolean.valueOf(aVar.o()));
                case 3:
                    return new b.b.a.l(aVar.w());
                case 4:
                    aVar.u();
                    return b.b.a.j.f387a;
                case 5:
                    b.b.a.f fVar = new b.b.a.f();
                    aVar.b();
                    while (aVar.k()) {
                        fVar.i(b(aVar));
                    }
                    aVar.g();
                    return fVar;
                case 6:
                    b.b.a.k kVar = new b.b.a.k();
                    aVar.c();
                    while (aVar.k()) {
                        kVar.i(aVar.s(), b(aVar));
                    }
                    aVar.h();
                    return kVar;
                default:
                    throw new IllegalArgumentException();
            }
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, b.b.a.i iVar) throws IOException {
            if (iVar == null || iVar.e()) {
                bVar.n();
                return;
            }
            if (iVar.g()) {
                b.b.a.l lVarC = iVar.c();
                if (lVarC.r()) {
                    bVar.z(lVarC.n());
                    return;
                } else if (lVarC.p()) {
                    bVar.B(lVarC.i());
                    return;
                } else {
                    bVar.A(lVarC.o());
                    return;
                }
            }
            if (iVar.d()) {
                bVar.c();
                Iterator<b.b.a.i> it = iVar.a().iterator();
                while (it.hasNext()) {
                    d(bVar, it.next());
                }
                bVar.f();
                return;
            }
            if (!iVar.f()) {
                if (iVar.h()) {
                    bVar.k(iVar.toString());
                    return;
                }
                throw new IllegalArgumentException("Couldn't write " + iVar.getClass());
            }
            bVar.d();
            for (Map.Entry<String, b.b.a.i> entry : iVar.b().j()) {
                bVar.l(entry.getKey());
                d(bVar, entry.getValue());
            }
            bVar.g();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class v extends b.b.a.o<BitSet> {
        /* JADX WARN: Removed duplicated region for block: B:19:0x003b  */
        @Override // b.b.a.o
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.util.BitSet b(b.b.a.t.a r8) throws java.io.IOException {
            /*
                r7 = this;
                com.google.gson.stream.JsonToken r0 = r8.y()
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.NULL
                if (r0 != r1) goto Ld
                r8.u()
                r8 = 0
                return r8
            Ld:
                java.util.BitSet r0 = new java.util.BitSet
                r0.<init>()
                r8.b()
                com.google.gson.stream.JsonToken r1 = r8.y()
                r2 = 0
                r3 = r2
            L1b:
                com.google.gson.stream.JsonToken r4 = com.google.gson.stream.JsonToken.END_ARRAY
                if (r1 == r4) goto L82
                int[] r4 = b.b.a.r.i.n.b0.f482a
                int r5 = r1.ordinal()
                r4 = r4[r5]
                r5 = 1
                if (r4 == r5) goto L70
                r6 = 2
                if (r4 == r6) goto L6b
                r6 = 3
                if (r4 != r6) goto L54
                java.lang.String r1 = r8.w()
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
                boolean r5 = r8.o()
                goto L76
            L70:
                int r1 = r8.q()
                if (r1 == 0) goto L3b
            L76:
                if (r5 == 0) goto L7b
                r0.set(r3)
            L7b:
                int r3 = r3 + 1
                com.google.gson.stream.JsonToken r1 = r8.y()
                goto L1b
            L82:
                r8.g()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: b.b.a.r.i.n.v.b(b.b.a.t.a):java.util.BitSet");
        }

        @Override // b.b.a.o
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(b.b.a.t.b bVar, BitSet bitSet) throws IOException {
            if (bitSet == null) {
                bVar.n();
                return;
            }
            bVar.c();
            for (int i = 0; i < bitSet.length(); i++) {
                bVar.x(bitSet.get(i) ? 1L : 0L);
            }
            bVar.f();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class w implements b.b.a.p {
        @Override // b.b.a.p
        public <T> b.b.a.o<T> a(b.b.a.d dVar, b.b.a.s.a<T> aVar) {
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
    public static class x implements b.b.a.p {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Class f487b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ b.b.a.o f488c;

        public x(Class cls, b.b.a.o oVar) {
            this.f487b = cls;
            this.f488c = oVar;
        }

        @Override // b.b.a.p
        public <T> b.b.a.o<T> a(b.b.a.d dVar, b.b.a.s.a<T> aVar) {
            if (aVar.d() == this.f487b) {
                return this.f488c;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.f487b.getName() + ",adapter=" + this.f488c + "]";
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class y implements b.b.a.p {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Class f489b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Class f490c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ b.b.a.o f491d;

        public y(Class cls, Class cls2, b.b.a.o oVar) {
            this.f489b = cls;
            this.f490c = cls2;
            this.f491d = oVar;
        }

        @Override // b.b.a.p
        public <T> b.b.a.o<T> a(b.b.a.d dVar, b.b.a.s.a<T> aVar) {
            Class<? super T> clsD = aVar.d();
            if (clsD == this.f489b || clsD == this.f490c) {
                return this.f491d;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.f490c.getName() + BadgeDrawable.DEFAULT_EXCEED_MAX_BADGE_NUMBER_SUFFIX + this.f489b.getName() + ",adapter=" + this.f491d + "]";
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class z implements b.b.a.p {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Class f492b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Class f493c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ b.b.a.o f494d;

        public z(Class cls, Class cls2, b.b.a.o oVar) {
            this.f492b = cls;
            this.f493c = cls2;
            this.f494d = oVar;
        }

        @Override // b.b.a.p
        public <T> b.b.a.o<T> a(b.b.a.d dVar, b.b.a.s.a<T> aVar) {
            Class<? super T> clsD = aVar.d();
            if (clsD == this.f492b || clsD == this.f493c) {
                return this.f494d;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.f492b.getName() + BadgeDrawable.DEFAULT_EXCEED_MAX_BADGE_NUMBER_SUFFIX + this.f493c.getName() + ",adapter=" + this.f494d + "]";
        }
    }

    static {
        k kVar = new k();
        f474a = kVar;
        f475b = a(Class.class, kVar);
        v vVar = new v();
        f476c = vVar;
        f477d = a(BitSet.class, vVar);
        c0 c0Var = new c0();
        e = c0Var;
        f = new d0();
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
        b.b.a.o<AtomicInteger> oVarA = new h0().a();
        n = oVarA;
        o = a(AtomicInteger.class, oVarA);
        b.b.a.o<AtomicBoolean> oVarA2 = new i0().a();
        p = oVarA2;
        q = a(AtomicBoolean.class, oVarA2);
        b.b.a.o<AtomicIntegerArray> oVarA3 = new a().a();
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
        C0031n c0031n = new C0031n();
        K = c0031n;
        L = a(URI.class, c0031n);
        o oVar = new o();
        M = oVar;
        N = d(InetAddress.class, oVar);
        p pVar = new p();
        O = pVar;
        P = a(UUID.class, pVar);
        b.b.a.o<Currency> oVarA4 = new q().a();
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
        Y = d(b.b.a.i.class, uVar);
        Z = new w();
    }

    public static <TT> b.b.a.p a(Class<TT> cls, b.b.a.o<TT> oVar) {
        return new x(cls, oVar);
    }

    public static <TT> b.b.a.p b(Class<TT> cls, Class<TT> cls2, b.b.a.o<? super TT> oVar) {
        return new y(cls, cls2, oVar);
    }

    public static <TT> b.b.a.p c(Class<TT> cls, Class<? extends TT> cls2, b.b.a.o<? super TT> oVar) {
        return new z(cls, cls2, oVar);
    }

    public static <T1> b.b.a.p d(Class<T1> cls, b.b.a.o<T1> oVar) {
        return new a0(cls, oVar);
    }
}
