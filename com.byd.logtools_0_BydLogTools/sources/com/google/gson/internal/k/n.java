package com.google.gson.internal.k;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
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
    public static final com.google.gson.p<BigInteger> A;
    public static final com.google.gson.q B;
    public static final com.google.gson.p<StringBuilder> C;
    public static final com.google.gson.q D;
    public static final com.google.gson.p<StringBuffer> E;
    public static final com.google.gson.q F;
    public static final com.google.gson.p<URL> G;
    public static final com.google.gson.q H;
    public static final com.google.gson.p<URI> I;
    public static final com.google.gson.q J;
    public static final com.google.gson.p<InetAddress> K;
    public static final com.google.gson.q L;
    public static final com.google.gson.p<UUID> M;
    public static final com.google.gson.q N;
    public static final com.google.gson.p<Currency> O;
    public static final com.google.gson.q P;
    public static final com.google.gson.p<Calendar> Q;
    public static final com.google.gson.q R;
    public static final com.google.gson.p<Locale> S;
    public static final com.google.gson.q T;
    public static final com.google.gson.p<com.google.gson.i> U;
    public static final com.google.gson.q V;
    public static final com.google.gson.q W;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final com.google.gson.p<Class> f4107a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final com.google.gson.q f4108b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final com.google.gson.p<BitSet> f4109c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final com.google.gson.q f4110d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final com.google.gson.p<Boolean> f4111e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final com.google.gson.p<Boolean> f4112f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final com.google.gson.q f4113g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final com.google.gson.p<Number> f4114h;
    public static final com.google.gson.q i;
    public static final com.google.gson.p<Number> j;
    public static final com.google.gson.q k;
    public static final com.google.gson.p<Number> l;
    public static final com.google.gson.q m;
    public static final com.google.gson.p<AtomicInteger> n;
    public static final com.google.gson.q o;
    public static final com.google.gson.p<AtomicBoolean> p;
    public static final com.google.gson.q q;
    public static final com.google.gson.p<AtomicIntegerArray> r;
    public static final com.google.gson.q s;
    public static final com.google.gson.p<Number> t;
    public static final com.google.gson.p<Number> u;
    public static final com.google.gson.p<Number> v;
    public static final com.google.gson.p<Character> w;
    public static final com.google.gson.q x;
    public static final com.google.gson.p<String> y;
    public static final com.google.gson.p<BigDecimal> z;

    /* JADX INFO: compiled from: TypeAdapters.java */
    class a extends com.google.gson.p<AtomicIntegerArray> {
        a() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicIntegerArray b(com.google.gson.stream.a aVar) throws IOException {
            ArrayList arrayList = new ArrayList();
            aVar.a();
            while (aVar.j()) {
                try {
                    arrayList.add(Integer.valueOf(aVar.p()));
                } catch (NumberFormatException e2) {
                    throw new JsonSyntaxException(e2);
                }
            }
            aVar.f();
            int size = arrayList.size();
            AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
            for (int i = 0; i < size; i++) {
                atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
            }
            return atomicIntegerArray;
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, AtomicIntegerArray atomicIntegerArray) throws IOException {
            bVar.c();
            int length = atomicIntegerArray.length();
            for (int i = 0; i < length; i++) {
                bVar.w(atomicIntegerArray.get(i));
            }
            bVar.f();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class a0 extends com.google.gson.p<Boolean> {
        a0() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Boolean b(com.google.gson.stream.a aVar) throws IOException {
            JsonToken jsonTokenX = aVar.x();
            if (jsonTokenX != JsonToken.NULL) {
                return jsonTokenX == JsonToken.STRING ? Boolean.valueOf(Boolean.parseBoolean(aVar.v())) : Boolean.valueOf(aVar.n());
            }
            aVar.t();
            return null;
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, Boolean bool) throws IOException {
            bVar.x(bool);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class b extends com.google.gson.p<Number> {
        b() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() == JsonToken.NULL) {
                aVar.t();
                return null;
            }
            try {
                return Long.valueOf(aVar.q());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, Number number) throws IOException {
            bVar.y(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class b0 extends com.google.gson.p<Boolean> {
        b0() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Boolean b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() != JsonToken.NULL) {
                return Boolean.valueOf(aVar.v());
            }
            aVar.t();
            return null;
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, Boolean bool) throws IOException {
            bVar.z(bool == null ? "null" : bool.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class c extends com.google.gson.p<Number> {
        c() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() != JsonToken.NULL) {
                return Float.valueOf((float) aVar.o());
            }
            aVar.t();
            return null;
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, Number number) throws IOException {
            bVar.y(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class c0 extends com.google.gson.p<Number> {
        c0() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() == JsonToken.NULL) {
                aVar.t();
                return null;
            }
            try {
                return Byte.valueOf((byte) aVar.p());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, Number number) throws IOException {
            bVar.y(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class d extends com.google.gson.p<Number> {
        d() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() != JsonToken.NULL) {
                return Double.valueOf(aVar.o());
            }
            aVar.t();
            return null;
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, Number number) throws IOException {
            bVar.y(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class d0 extends com.google.gson.p<Number> {
        d0() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() == JsonToken.NULL) {
                aVar.t();
                return null;
            }
            try {
                return Short.valueOf((short) aVar.p());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, Number number) throws IOException {
            bVar.y(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class e extends com.google.gson.p<Character> {
        e() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Character b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() == JsonToken.NULL) {
                aVar.t();
                return null;
            }
            String strV = aVar.v();
            if (strV.length() == 1) {
                return Character.valueOf(strV.charAt(0));
            }
            throw new JsonSyntaxException("Expecting character, got: " + strV);
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, Character ch) throws IOException {
            bVar.z(ch == null ? null : String.valueOf(ch));
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class e0 extends com.google.gson.p<Number> {
        e0() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Number b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() == JsonToken.NULL) {
                aVar.t();
                return null;
            }
            try {
                return Integer.valueOf(aVar.p());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, Number number) throws IOException {
            bVar.y(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class f extends com.google.gson.p<String> {
        f() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public String b(com.google.gson.stream.a aVar) throws IOException {
            JsonToken jsonTokenX = aVar.x();
            if (jsonTokenX != JsonToken.NULL) {
                return jsonTokenX == JsonToken.BOOLEAN ? Boolean.toString(aVar.n()) : aVar.v();
            }
            aVar.t();
            return null;
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, String str) throws IOException {
            bVar.z(str);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class f0 extends com.google.gson.p<AtomicInteger> {
        f0() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicInteger b(com.google.gson.stream.a aVar) throws IOException {
            try {
                return new AtomicInteger(aVar.p());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, AtomicInteger atomicInteger) throws IOException {
            bVar.w(atomicInteger.get());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class g extends com.google.gson.p<BigDecimal> {
        g() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public BigDecimal b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() == JsonToken.NULL) {
                aVar.t();
                return null;
            }
            try {
                return new BigDecimal(aVar.v());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, BigDecimal bigDecimal) throws IOException {
            bVar.y(bigDecimal);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class g0 extends com.google.gson.p<AtomicBoolean> {
        g0() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public AtomicBoolean b(com.google.gson.stream.a aVar) throws IOException {
            return new AtomicBoolean(aVar.n());
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, AtomicBoolean atomicBoolean) throws IOException {
            bVar.A(atomicBoolean.get());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class h extends com.google.gson.p<BigInteger> {
        h() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public BigInteger b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() == JsonToken.NULL) {
                aVar.t();
                return null;
            }
            try {
                return new BigInteger(aVar.v());
            } catch (NumberFormatException e2) {
                throw new JsonSyntaxException(e2);
            }
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, BigInteger bigInteger) throws IOException {
            bVar.y(bigInteger);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    private static final class h0<T extends Enum<T>> extends com.google.gson.p<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Map<String, T> f4115a = new HashMap();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Map<T, String> f4116b = new HashMap();

        /* JADX INFO: compiled from: TypeAdapters.java */
        class a implements PrivilegedAction<Void> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Field f4117a;

            a(Field field) {
                this.f4117a = field;
            }

            @Override // java.security.PrivilegedAction
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Void run() {
                this.f4117a.setAccessible(true);
                return null;
            }
        }

        public h0(Class<T> cls) {
            try {
                for (Field field : cls.getDeclaredFields()) {
                    if (field.isEnumConstant()) {
                        AccessController.doPrivileged(new a(field));
                        Enum r4 = (Enum) field.get(null);
                        String strName = r4.name();
                        com.google.gson.r.c cVar = (com.google.gson.r.c) field.getAnnotation(com.google.gson.r.c.class);
                        if (cVar != null) {
                            strName = cVar.value();
                            for (String str : cVar.alternate()) {
                                this.f4115a.put(str, (T) r4);
                            }
                        }
                        this.f4115a.put(strName, (T) r4);
                        this.f4116b.put((T) r4, strName);
                    }
                }
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public T b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() != JsonToken.NULL) {
                return this.f4115a.get(aVar.v());
            }
            aVar.t();
            return null;
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, T t) throws IOException {
            bVar.z(t == null ? null : this.f4116b.get(t));
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class i extends com.google.gson.p<StringBuilder> {
        i() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public StringBuilder b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() != JsonToken.NULL) {
                return new StringBuilder(aVar.v());
            }
            aVar.t();
            return null;
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, StringBuilder sb) throws IOException {
            bVar.z(sb == null ? null : sb.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class j extends com.google.gson.p<StringBuffer> {
        j() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public StringBuffer b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() != JsonToken.NULL) {
                return new StringBuffer(aVar.v());
            }
            aVar.t();
            return null;
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, StringBuffer stringBuffer) throws IOException {
            bVar.z(stringBuffer == null ? null : stringBuffer.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class k extends com.google.gson.p<Class> {
        k() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Class b(com.google.gson.stream.a aVar) throws IOException {
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, Class cls) throws IOException {
            throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?");
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class l extends com.google.gson.p<URL> {
        l() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public URL b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() == JsonToken.NULL) {
                aVar.t();
                return null;
            }
            String strV = aVar.v();
            if ("null".equals(strV)) {
                return null;
            }
            return new URL(strV);
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, URL url) throws IOException {
            bVar.z(url == null ? null : url.toExternalForm());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class m extends com.google.gson.p<URI> {
        m() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public URI b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() == JsonToken.NULL) {
                aVar.t();
                return null;
            }
            try {
                String strV = aVar.v();
                if ("null".equals(strV)) {
                    return null;
                }
                return new URI(strV);
            } catch (URISyntaxException e2) {
                throw new JsonIOException(e2);
            }
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, URI uri) throws IOException {
            bVar.z(uri == null ? null : uri.toASCIIString());
        }
    }

    /* JADX INFO: renamed from: com.google.gson.internal.k.n$n, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TypeAdapters.java */
    class C0077n extends com.google.gson.p<InetAddress> {
        C0077n() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public InetAddress b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() != JsonToken.NULL) {
                return InetAddress.getByName(aVar.v());
            }
            aVar.t();
            return null;
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, InetAddress inetAddress) throws IOException {
            bVar.z(inetAddress == null ? null : inetAddress.getHostAddress());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class o extends com.google.gson.p<UUID> {
        o() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public UUID b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() != JsonToken.NULL) {
                return UUID.fromString(aVar.v());
            }
            aVar.t();
            return null;
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, UUID uuid) throws IOException {
            bVar.z(uuid == null ? null : uuid.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class p extends com.google.gson.p<Currency> {
        p() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Currency b(com.google.gson.stream.a aVar) throws IOException {
            return Currency.getInstance(aVar.v());
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, Currency currency) throws IOException {
            bVar.z(currency.getCurrencyCode());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class q extends com.google.gson.p<Calendar> {
        q() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Calendar b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() == JsonToken.NULL) {
                aVar.t();
                return null;
            }
            aVar.b();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (aVar.x() != JsonToken.END_OBJECT) {
                String strR = aVar.r();
                int iP = aVar.p();
                if ("year".equals(strR)) {
                    i = iP;
                } else if ("month".equals(strR)) {
                    i2 = iP;
                } else if ("dayOfMonth".equals(strR)) {
                    i3 = iP;
                } else if ("hourOfDay".equals(strR)) {
                    i4 = iP;
                } else if ("minute".equals(strR)) {
                    i5 = iP;
                } else if ("second".equals(strR)) {
                    i6 = iP;
                }
            }
            aVar.g();
            return new GregorianCalendar(i, i2, i3, i4, i5, i6);
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, Calendar calendar) throws IOException {
            if (calendar == null) {
                bVar.m();
                return;
            }
            bVar.d();
            bVar.k("year");
            bVar.w(calendar.get(1));
            bVar.k("month");
            bVar.w(calendar.get(2));
            bVar.k("dayOfMonth");
            bVar.w(calendar.get(5));
            bVar.k("hourOfDay");
            bVar.w(calendar.get(11));
            bVar.k("minute");
            bVar.w(calendar.get(12));
            bVar.k("second");
            bVar.w(calendar.get(13));
            bVar.g();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class r extends com.google.gson.p<Locale> {
        r() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public Locale b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.x() == JsonToken.NULL) {
                aVar.t();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(aVar.v(), "_");
            String strNextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String strNextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String strNextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            return (strNextToken2 == null && strNextToken3 == null) ? new Locale(strNextToken) : strNextToken3 == null ? new Locale(strNextToken, strNextToken2) : new Locale(strNextToken, strNextToken2, strNextToken3);
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, Locale locale) throws IOException {
            bVar.z(locale == null ? null : locale.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class s extends com.google.gson.p<com.google.gson.i> {
        s() {
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public com.google.gson.i b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar instanceof com.google.gson.internal.k.f) {
                return ((com.google.gson.internal.k.f) aVar).K();
            }
            switch (z.f4131a[aVar.x().ordinal()]) {
                case 1:
                    return new com.google.gson.l(new LazilyParsedNumber(aVar.v()));
                case 2:
                    return new com.google.gson.l(Boolean.valueOf(aVar.n()));
                case 3:
                    return new com.google.gson.l(aVar.v());
                case 4:
                    aVar.t();
                    return com.google.gson.j.f4149a;
                case 5:
                    com.google.gson.f fVar = new com.google.gson.f();
                    aVar.a();
                    while (aVar.j()) {
                        fVar.h(b(aVar));
                    }
                    aVar.f();
                    return fVar;
                case 6:
                    com.google.gson.k kVar = new com.google.gson.k();
                    aVar.b();
                    while (aVar.j()) {
                        kVar.h(aVar.r(), b(aVar));
                    }
                    aVar.g();
                    return kVar;
                default:
                    throw new IllegalArgumentException();
            }
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, com.google.gson.i iVar) throws IOException {
            if (iVar == null || iVar.e()) {
                bVar.m();
                return;
            }
            if (iVar.g()) {
                com.google.gson.l lVarC = iVar.c();
                if (lVarC.p()) {
                    bVar.y(lVarC.l());
                    return;
                } else if (lVarC.n()) {
                    bVar.A(lVarC.h());
                    return;
                } else {
                    bVar.z(lVarC.m());
                    return;
                }
            }
            if (iVar.d()) {
                bVar.c();
                Iterator<com.google.gson.i> it = iVar.a().iterator();
                while (it.hasNext()) {
                    d(bVar, it.next());
                }
                bVar.f();
                return;
            }
            if (!iVar.f()) {
                throw new IllegalArgumentException("Couldn't write " + iVar.getClass());
            }
            bVar.d();
            for (Map.Entry<String, com.google.gson.i> entry : iVar.b().i()) {
                bVar.k(entry.getKey());
                d(bVar, entry.getValue());
            }
            bVar.g();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class t implements com.google.gson.q {
        t() {
        }

        @Override // com.google.gson.q
        public <T> com.google.gson.p<T> a(com.google.gson.d dVar, com.google.gson.s.a<T> aVar) {
            Class<? super T> clsC = aVar.c();
            if (!Enum.class.isAssignableFrom(clsC) || clsC == Enum.class) {
                return null;
            }
            if (!clsC.isEnum()) {
                clsC = clsC.getSuperclass();
            }
            return new h0(clsC);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class u extends com.google.gson.p<BitSet> {
        u() {
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x002e  */
        @Override // com.google.gson.p
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.util.BitSet b(com.google.gson.stream.a r8) throws java.io.IOException {
            /*
                r7 = this;
                java.util.BitSet r0 = new java.util.BitSet
                r0.<init>()
                r8.a()
                com.google.gson.stream.JsonToken r1 = r8.x()
                r2 = 0
                r3 = r2
            Le:
                com.google.gson.stream.JsonToken r4 = com.google.gson.stream.JsonToken.END_ARRAY
                if (r1 == r4) goto L75
                int[] r4 = com.google.gson.internal.k.n.z.f4131a
                int r5 = r1.ordinal()
                r4 = r4[r5]
                r5 = 1
                if (r4 == r5) goto L63
                r6 = 2
                if (r4 == r6) goto L5e
                r6 = 3
                if (r4 != r6) goto L47
                java.lang.String r1 = r8.v()
                int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.NumberFormatException -> L30
                if (r1 == 0) goto L2e
                goto L69
            L2e:
                r5 = r2
                goto L69
            L30:
                com.google.gson.JsonSyntaxException r8 = new com.google.gson.JsonSyntaxException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r2 = "Error: Expecting: bitset number value (1, 0), Found: "
                r0.append(r2)
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r8.<init>(r0)
                throw r8
            L47:
                com.google.gson.JsonSyntaxException r8 = new com.google.gson.JsonSyntaxException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r2 = "Invalid bitset value type: "
                r0.append(r2)
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r8.<init>(r0)
                throw r8
            L5e:
                boolean r5 = r8.n()
                goto L69
            L63:
                int r1 = r8.p()
                if (r1 == 0) goto L2e
            L69:
                if (r5 == 0) goto L6e
                r0.set(r3)
            L6e:
                int r3 = r3 + 1
                com.google.gson.stream.JsonToken r1 = r8.x()
                goto Le
            L75:
                r8.f()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.k.n.u.b(com.google.gson.stream.a):java.util.BitSet");
        }

        @Override // com.google.gson.p
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public void d(com.google.gson.stream.b bVar, BitSet bitSet) throws IOException {
            bVar.c();
            int length = bitSet.length();
            for (int i = 0; i < length; i++) {
                bVar.w(bitSet.get(i) ? 1L : 0L);
            }
            bVar.f();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class v implements com.google.gson.q {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Class f4119b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.google.gson.p f4120c;

        v(Class cls, com.google.gson.p pVar) {
            this.f4119b = cls;
            this.f4120c = pVar;
        }

        @Override // com.google.gson.q
        public <T> com.google.gson.p<T> a(com.google.gson.d dVar, com.google.gson.s.a<T> aVar) {
            if (aVar.c() == this.f4119b) {
                return this.f4120c;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.f4119b.getName() + ",adapter=" + this.f4120c + "]";
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class w implements com.google.gson.q {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Class f4121b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Class f4122c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ com.google.gson.p f4123d;

        w(Class cls, Class cls2, com.google.gson.p pVar) {
            this.f4121b = cls;
            this.f4122c = cls2;
            this.f4123d = pVar;
        }

        @Override // com.google.gson.q
        public <T> com.google.gson.p<T> a(com.google.gson.d dVar, com.google.gson.s.a<T> aVar) {
            Class<? super T> clsC = aVar.c();
            if (clsC == this.f4121b || clsC == this.f4122c) {
                return this.f4123d;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.f4122c.getName() + "+" + this.f4121b.getName() + ",adapter=" + this.f4123d + "]";
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class x implements com.google.gson.q {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Class f4124b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Class f4125c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ com.google.gson.p f4126d;

        x(Class cls, Class cls2, com.google.gson.p pVar) {
            this.f4124b = cls;
            this.f4125c = cls2;
            this.f4126d = pVar;
        }

        @Override // com.google.gson.q
        public <T> com.google.gson.p<T> a(com.google.gson.d dVar, com.google.gson.s.a<T> aVar) {
            Class<? super T> clsC = aVar.c();
            if (clsC == this.f4124b || clsC == this.f4125c) {
                return this.f4126d;
            }
            return null;
        }

        public String toString() {
            return "Factory[type=" + this.f4124b.getName() + "+" + this.f4125c.getName() + ",adapter=" + this.f4126d + "]";
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    class y implements com.google.gson.q {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Class f4127b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ com.google.gson.p f4128c;

        /* JADX INFO: Add missing generic type declarations: [T1] */
        /* JADX INFO: compiled from: TypeAdapters.java */
        class a<T1> extends com.google.gson.p<T1> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ Class f4129a;

            a(Class cls) {
                this.f4129a = cls;
            }

            @Override // com.google.gson.p
            public T1 b(com.google.gson.stream.a aVar) throws IOException {
                T1 t1 = (T1) y.this.f4128c.b(aVar);
                if (t1 == null || this.f4129a.isInstance(t1)) {
                    return t1;
                }
                throw new JsonSyntaxException("Expected a " + this.f4129a.getName() + " but was " + t1.getClass().getName());
            }

            @Override // com.google.gson.p
            public void d(com.google.gson.stream.b bVar, T1 t1) throws IOException {
                y.this.f4128c.d(bVar, t1);
            }
        }

        y(Class cls, com.google.gson.p pVar) {
            this.f4127b = cls;
            this.f4128c = pVar;
        }

        @Override // com.google.gson.q
        public <T2> com.google.gson.p<T2> a(com.google.gson.d dVar, com.google.gson.s.a<T2> aVar) {
            Class<? super T2> clsC = aVar.c();
            if (this.f4127b.isAssignableFrom(clsC)) {
                return new a(clsC);
            }
            return null;
        }

        public String toString() {
            return "Factory[typeHierarchy=" + this.f4127b.getName() + ",adapter=" + this.f4128c + "]";
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    static /* synthetic */ class z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f4131a;

        static {
            int[] iArr = new int[JsonToken.values().length];
            f4131a = iArr;
            try {
                iArr[JsonToken.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4131a[JsonToken.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4131a[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4131a[JsonToken.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f4131a[JsonToken.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f4131a[JsonToken.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f4131a[JsonToken.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f4131a[JsonToken.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f4131a[JsonToken.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f4131a[JsonToken.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    static {
        com.google.gson.p<Class> pVarA = new k().a();
        f4107a = pVarA;
        f4108b = a(Class.class, pVarA);
        com.google.gson.p<BitSet> pVarA2 = new u().a();
        f4109c = pVarA2;
        f4110d = a(BitSet.class, pVarA2);
        a0 a0Var = new a0();
        f4111e = a0Var;
        f4112f = new b0();
        f4113g = b(Boolean.TYPE, Boolean.class, a0Var);
        c0 c0Var = new c0();
        f4114h = c0Var;
        i = b(Byte.TYPE, Byte.class, c0Var);
        d0 d0Var = new d0();
        j = d0Var;
        k = b(Short.TYPE, Short.class, d0Var);
        e0 e0Var = new e0();
        l = e0Var;
        m = b(Integer.TYPE, Integer.class, e0Var);
        com.google.gson.p<AtomicInteger> pVarA3 = new f0().a();
        n = pVarA3;
        o = a(AtomicInteger.class, pVarA3);
        com.google.gson.p<AtomicBoolean> pVarA4 = new g0().a();
        p = pVarA4;
        q = a(AtomicBoolean.class, pVarA4);
        com.google.gson.p<AtomicIntegerArray> pVarA5 = new a().a();
        r = pVarA5;
        s = a(AtomicIntegerArray.class, pVarA5);
        t = new b();
        u = new c();
        v = new d();
        e eVar = new e();
        w = eVar;
        x = b(Character.TYPE, Character.class, eVar);
        f fVar = new f();
        y = fVar;
        z = new g();
        A = new h();
        B = a(String.class, fVar);
        i iVar = new i();
        C = iVar;
        D = a(StringBuilder.class, iVar);
        j jVar = new j();
        E = jVar;
        F = a(StringBuffer.class, jVar);
        l lVar = new l();
        G = lVar;
        H = a(URL.class, lVar);
        m mVar = new m();
        I = mVar;
        J = a(URI.class, mVar);
        C0077n c0077n = new C0077n();
        K = c0077n;
        L = d(InetAddress.class, c0077n);
        o oVar = new o();
        M = oVar;
        N = a(UUID.class, oVar);
        com.google.gson.p<Currency> pVarA6 = new p().a();
        O = pVarA6;
        P = a(Currency.class, pVarA6);
        q qVar = new q();
        Q = qVar;
        R = c(Calendar.class, GregorianCalendar.class, qVar);
        r rVar = new r();
        S = rVar;
        T = a(Locale.class, rVar);
        s sVar = new s();
        U = sVar;
        V = d(com.google.gson.i.class, sVar);
        W = new t();
    }

    public static <TT> com.google.gson.q a(Class<TT> cls, com.google.gson.p<TT> pVar) {
        return new v(cls, pVar);
    }

    public static <TT> com.google.gson.q b(Class<TT> cls, Class<TT> cls2, com.google.gson.p<? super TT> pVar) {
        return new w(cls, cls2, pVar);
    }

    public static <TT> com.google.gson.q c(Class<TT> cls, Class<? extends TT> cls2, com.google.gson.p<? super TT> pVar) {
        return new x(cls, cls2, pVar);
    }

    public static <T1> com.google.gson.q d(Class<T1> cls, com.google.gson.p<T1> pVar) {
        return new y(cls, pVar);
    }
}
