package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.g;
import com.google.gson.internal.f;
import com.google.gson.j;
import com.google.gson.k;
import com.google.gson.l;
import com.google.gson.m;
import com.google.gson.o;
import com.google.gson.r;
import com.google.gson.t;
import com.google.gson.u.c;
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

/* JADX INFO: loaded from: classes.dex */
public final class TypeAdapters {
    public static final TypeAdapter<String> A;
    public static final TypeAdapter<BigDecimal> B;
    public static final TypeAdapter<BigInteger> C;
    public static final t D;
    public static final TypeAdapter<StringBuilder> E;
    public static final t F;
    public static final TypeAdapter<StringBuffer> G;
    public static final t H;
    public static final TypeAdapter<URL> I;
    public static final t J;
    public static final TypeAdapter<URI> K;
    public static final t L;
    public static final TypeAdapter<InetAddress> M;
    public static final t N;
    public static final TypeAdapter<UUID> O;
    public static final t P;
    public static final TypeAdapter<Currency> Q;
    public static final t R;
    public static final t S;
    public static final TypeAdapter<Calendar> T;
    public static final t U;
    public static final TypeAdapter<Locale> V;
    public static final t W;
    public static final TypeAdapter<j> X;
    public static final t Y;
    public static final t Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TypeAdapter<Class> f4508a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final t f4509b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final TypeAdapter<BitSet> f4510c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final t f4511d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final TypeAdapter<Boolean> f4512e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final TypeAdapter<Boolean> f4513f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final t f4514g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final TypeAdapter<Number> f4515h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final t f4516i;
    public static final TypeAdapter<Number> j;
    public static final t k;
    public static final TypeAdapter<Number> l;
    public static final t m;
    public static final TypeAdapter<AtomicInteger> n;
    public static final t o;
    public static final TypeAdapter<AtomicBoolean> p;
    public static final t q;
    public static final TypeAdapter<AtomicIntegerArray> r;
    public static final t s;
    public static final TypeAdapter<Number> t;
    public static final TypeAdapter<Number> u;
    public static final TypeAdapter<Number> v;
    public static final TypeAdapter<Number> w;
    public static final t x;
    public static final TypeAdapter<Character> y;
    public static final t z;

    private static final class EnumTypeAdapter<T extends Enum<T>> extends TypeAdapter<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Map<String, T> f4533a = new HashMap();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Map<T, String> f4534b = new HashMap();

        public EnumTypeAdapter(Class<T> cls) {
            try {
                for (T t : cls.getEnumConstants()) {
                    String strName = t.name();
                    c cVar = (c) cls.getField(strName).getAnnotation(c.class);
                    if (cVar != null) {
                        strName = cVar.value();
                        for (String str : cVar.alternate()) {
                            this.f4533a.put(str, t);
                        }
                    }
                    this.f4533a.put(strName, t);
                    this.f4534b.put(t, strName);
                }
            } catch (NoSuchFieldException e2) {
                throw new AssertionError(e2);
            }
        }

        @Override // com.google.gson.TypeAdapter
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public T read(com.google.gson.w.a aVar) throws IOException {
            if (aVar.j0() != com.google.gson.w.b.NULL) {
                return this.f4533a.get(aVar.h0());
            }
            aVar.f0();
            return null;
        }

        @Override // com.google.gson.TypeAdapter
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void write(com.google.gson.w.c cVar, T t) throws IOException {
            cVar.l0(t == null ? null : this.f4534b.get(t));
        }
    }

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f4535a;

        static {
            int[] iArr = new int[com.google.gson.w.b.values().length];
            f4535a = iArr;
            try {
                iArr[com.google.gson.w.b.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4535a[com.google.gson.w.b.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4535a[com.google.gson.w.b.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4535a[com.google.gson.w.b.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f4535a[com.google.gson.w.b.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f4535a[com.google.gson.w.b.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f4535a[com.google.gson.w.b.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f4535a[com.google.gson.w.b.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f4535a[com.google.gson.w.b.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f4535a[com.google.gson.w.b.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    static {
        TypeAdapter<Class> typeAdapterNullSafe = new TypeAdapter<Class>() { // from class: com.google.gson.internal.bind.TypeAdapters.1
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Class read(com.google.gson.w.a aVar) {
                throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, Class cls) {
                throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?");
            }
        }.nullSafe();
        f4508a = typeAdapterNullSafe;
        f4509b = b(Class.class, typeAdapterNullSafe);
        TypeAdapter<BitSet> typeAdapterNullSafe2 = new TypeAdapter<BitSet>() { // from class: com.google.gson.internal.bind.TypeAdapters.2
            /* JADX WARN: Removed duplicated region for block: B:15:0x002e  */
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public java.util.BitSet read(com.google.gson.w.a r8) throws java.io.IOException {
                /*
                    r7 = this;
                    java.util.BitSet r0 = new java.util.BitSet
                    r0.<init>()
                    r8.a()
                    com.google.gson.w.b r1 = r8.j0()
                    r2 = 0
                    r3 = r2
                Le:
                    com.google.gson.w.b r4 = com.google.gson.w.b.END_ARRAY
                    if (r1 == r4) goto L75
                    int[] r4 = com.google.gson.internal.bind.TypeAdapters.a.f4535a
                    int r5 = r1.ordinal()
                    r4 = r4[r5]
                    r5 = 1
                    if (r4 == r5) goto L63
                    r6 = 2
                    if (r4 == r6) goto L5e
                    r6 = 3
                    if (r4 != r6) goto L47
                    java.lang.String r1 = r8.h0()
                    int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.NumberFormatException -> L30
                    if (r1 == 0) goto L2e
                    goto L69
                L2e:
                    r5 = r2
                    goto L69
                L30:
                    com.google.gson.r r8 = new com.google.gson.r
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    java.lang.String r2 = "Error: Expecting: bitset number value (1, 0), Found: "
                    r0.append(r2)
                    r0.append(r1)
                    java.lang.String r0 = r0.toString()
                    r8.<init>(r0)
                    throw r8
                L47:
                    com.google.gson.r r8 = new com.google.gson.r
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    java.lang.String r2 = "Invalid bitset value type: "
                    r0.append(r2)
                    r0.append(r1)
                    java.lang.String r0 = r0.toString()
                    r8.<init>(r0)
                    throw r8
                L5e:
                    boolean r5 = r8.Z()
                    goto L69
                L63:
                    int r1 = r8.b0()
                    if (r1 == 0) goto L2e
                L69:
                    if (r5 == 0) goto L6e
                    r0.set(r3)
                L6e:
                    int r3 = r3 + 1
                    com.google.gson.w.b r1 = r8.j0()
                    goto Le
                L75:
                    r8.R()
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.TypeAdapters.AnonymousClass2.read(com.google.gson.w.a):java.util.BitSet");
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, BitSet bitSet) throws IOException {
                cVar.j();
                int length = bitSet.length();
                for (int i2 = 0; i2 < length; i2++) {
                    cVar.i0(bitSet.get(i2) ? 1L : 0L);
                }
                cVar.R();
            }
        }.nullSafe();
        f4510c = typeAdapterNullSafe2;
        f4511d = b(BitSet.class, typeAdapterNullSafe2);
        TypeAdapter<Boolean> typeAdapter = new TypeAdapter<Boolean>() { // from class: com.google.gson.internal.bind.TypeAdapters.3
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean read(com.google.gson.w.a aVar) throws IOException {
                com.google.gson.w.b bVarJ0 = aVar.j0();
                if (bVarJ0 != com.google.gson.w.b.NULL) {
                    return bVarJ0 == com.google.gson.w.b.STRING ? Boolean.valueOf(Boolean.parseBoolean(aVar.h0())) : Boolean.valueOf(aVar.Z());
                }
                aVar.f0();
                return null;
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, Boolean bool) throws IOException {
                cVar.j0(bool);
            }
        };
        f4512e = typeAdapter;
        f4513f = new TypeAdapter<Boolean>() { // from class: com.google.gson.internal.bind.TypeAdapters.4
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean read(com.google.gson.w.a aVar) throws IOException {
                if (aVar.j0() != com.google.gson.w.b.NULL) {
                    return Boolean.valueOf(aVar.h0());
                }
                aVar.f0();
                return null;
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, Boolean bool) throws IOException {
                cVar.l0(bool == null ? "null" : bool.toString());
            }
        };
        f4514g = c(Boolean.TYPE, Boolean.class, typeAdapter);
        TypeAdapter<Number> typeAdapter2 = new TypeAdapter<Number>() { // from class: com.google.gson.internal.bind.TypeAdapters.5
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Number read(com.google.gson.w.a aVar) throws IOException {
                if (aVar.j0() == com.google.gson.w.b.NULL) {
                    aVar.f0();
                    return null;
                }
                try {
                    return Byte.valueOf((byte) aVar.b0());
                } catch (NumberFormatException e2) {
                    throw new r(e2);
                }
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, Number number) throws IOException {
                cVar.k0(number);
            }
        };
        f4515h = typeAdapter2;
        f4516i = c(Byte.TYPE, Byte.class, typeAdapter2);
        TypeAdapter<Number> typeAdapter3 = new TypeAdapter<Number>() { // from class: com.google.gson.internal.bind.TypeAdapters.6
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Number read(com.google.gson.w.a aVar) throws IOException {
                if (aVar.j0() == com.google.gson.w.b.NULL) {
                    aVar.f0();
                    return null;
                }
                try {
                    return Short.valueOf((short) aVar.b0());
                } catch (NumberFormatException e2) {
                    throw new r(e2);
                }
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, Number number) throws IOException {
                cVar.k0(number);
            }
        };
        j = typeAdapter3;
        k = c(Short.TYPE, Short.class, typeAdapter3);
        TypeAdapter<Number> typeAdapter4 = new TypeAdapter<Number>() { // from class: com.google.gson.internal.bind.TypeAdapters.7
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Number read(com.google.gson.w.a aVar) throws IOException {
                if (aVar.j0() == com.google.gson.w.b.NULL) {
                    aVar.f0();
                    return null;
                }
                try {
                    return Integer.valueOf(aVar.b0());
                } catch (NumberFormatException e2) {
                    throw new r(e2);
                }
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, Number number) throws IOException {
                cVar.k0(number);
            }
        };
        l = typeAdapter4;
        m = c(Integer.TYPE, Integer.class, typeAdapter4);
        TypeAdapter<AtomicInteger> typeAdapterNullSafe3 = new TypeAdapter<AtomicInteger>() { // from class: com.google.gson.internal.bind.TypeAdapters.8
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public AtomicInteger read(com.google.gson.w.a aVar) {
                try {
                    return new AtomicInteger(aVar.b0());
                } catch (NumberFormatException e2) {
                    throw new r(e2);
                }
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, AtomicInteger atomicInteger) throws IOException {
                cVar.i0(atomicInteger.get());
            }
        }.nullSafe();
        n = typeAdapterNullSafe3;
        o = b(AtomicInteger.class, typeAdapterNullSafe3);
        TypeAdapter<AtomicBoolean> typeAdapterNullSafe4 = new TypeAdapter<AtomicBoolean>() { // from class: com.google.gson.internal.bind.TypeAdapters.9
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public AtomicBoolean read(com.google.gson.w.a aVar) {
                return new AtomicBoolean(aVar.Z());
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, AtomicBoolean atomicBoolean) throws IOException {
                cVar.m0(atomicBoolean.get());
            }
        }.nullSafe();
        p = typeAdapterNullSafe4;
        q = b(AtomicBoolean.class, typeAdapterNullSafe4);
        TypeAdapter<AtomicIntegerArray> typeAdapterNullSafe5 = new TypeAdapter<AtomicIntegerArray>() { // from class: com.google.gson.internal.bind.TypeAdapters.10
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public AtomicIntegerArray read(com.google.gson.w.a aVar) throws IOException {
                ArrayList arrayList = new ArrayList();
                aVar.a();
                while (aVar.V()) {
                    try {
                        arrayList.add(Integer.valueOf(aVar.b0()));
                    } catch (NumberFormatException e2) {
                        throw new r(e2);
                    }
                }
                aVar.R();
                int size = arrayList.size();
                AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
                for (int i2 = 0; i2 < size; i2++) {
                    atomicIntegerArray.set(i2, ((Integer) arrayList.get(i2)).intValue());
                }
                return atomicIntegerArray;
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, AtomicIntegerArray atomicIntegerArray) throws IOException {
                cVar.j();
                int length = atomicIntegerArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    cVar.i0(atomicIntegerArray.get(i2));
                }
                cVar.R();
            }
        }.nullSafe();
        r = typeAdapterNullSafe5;
        s = b(AtomicIntegerArray.class, typeAdapterNullSafe5);
        t = new TypeAdapter<Number>() { // from class: com.google.gson.internal.bind.TypeAdapters.11
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Number read(com.google.gson.w.a aVar) throws IOException {
                if (aVar.j0() == com.google.gson.w.b.NULL) {
                    aVar.f0();
                    return null;
                }
                try {
                    return Long.valueOf(aVar.c0());
                } catch (NumberFormatException e2) {
                    throw new r(e2);
                }
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, Number number) throws IOException {
                cVar.k0(number);
            }
        };
        u = new TypeAdapter<Number>() { // from class: com.google.gson.internal.bind.TypeAdapters.12
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Number read(com.google.gson.w.a aVar) throws IOException {
                if (aVar.j0() != com.google.gson.w.b.NULL) {
                    return Float.valueOf((float) aVar.a0());
                }
                aVar.f0();
                return null;
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, Number number) throws IOException {
                cVar.k0(number);
            }
        };
        v = new TypeAdapter<Number>() { // from class: com.google.gson.internal.bind.TypeAdapters.13
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Number read(com.google.gson.w.a aVar) throws IOException {
                if (aVar.j0() != com.google.gson.w.b.NULL) {
                    return Double.valueOf(aVar.a0());
                }
                aVar.f0();
                return null;
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, Number number) throws IOException {
                cVar.k0(number);
            }
        };
        TypeAdapter<Number> typeAdapter5 = new TypeAdapter<Number>() { // from class: com.google.gson.internal.bind.TypeAdapters.14
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Number read(com.google.gson.w.a aVar) throws IOException {
                com.google.gson.w.b bVarJ0 = aVar.j0();
                int i2 = a.f4535a[bVarJ0.ordinal()];
                if (i2 == 1 || i2 == 3) {
                    return new f(aVar.h0());
                }
                if (i2 == 4) {
                    aVar.f0();
                    return null;
                }
                throw new r("Expecting number, got: " + bVarJ0);
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, Number number) throws IOException {
                cVar.k0(number);
            }
        };
        w = typeAdapter5;
        x = b(Number.class, typeAdapter5);
        TypeAdapter<Character> typeAdapter6 = new TypeAdapter<Character>() { // from class: com.google.gson.internal.bind.TypeAdapters.15
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Character read(com.google.gson.w.a aVar) throws IOException {
                if (aVar.j0() == com.google.gson.w.b.NULL) {
                    aVar.f0();
                    return null;
                }
                String strH0 = aVar.h0();
                if (strH0.length() == 1) {
                    return Character.valueOf(strH0.charAt(0));
                }
                throw new r("Expecting character, got: " + strH0);
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, Character ch) throws IOException {
                cVar.l0(ch == null ? null : String.valueOf(ch));
            }
        };
        y = typeAdapter6;
        z = c(Character.TYPE, Character.class, typeAdapter6);
        TypeAdapter<String> typeAdapter7 = new TypeAdapter<String>() { // from class: com.google.gson.internal.bind.TypeAdapters.16
            @Override // com.google.gson.TypeAdapter
            public String read(com.google.gson.w.a aVar) throws IOException {
                com.google.gson.w.b bVarJ0 = aVar.j0();
                if (bVarJ0 != com.google.gson.w.b.NULL) {
                    return bVarJ0 == com.google.gson.w.b.BOOLEAN ? Boolean.toString(aVar.Z()) : aVar.h0();
                }
                aVar.f0();
                return null;
            }

            @Override // com.google.gson.TypeAdapter
            public void write(com.google.gson.w.c cVar, String str) throws IOException {
                cVar.l0(str);
            }
        };
        A = typeAdapter7;
        B = new TypeAdapter<BigDecimal>() { // from class: com.google.gson.internal.bind.TypeAdapters.17
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public BigDecimal read(com.google.gson.w.a aVar) throws IOException {
                if (aVar.j0() == com.google.gson.w.b.NULL) {
                    aVar.f0();
                    return null;
                }
                try {
                    return new BigDecimal(aVar.h0());
                } catch (NumberFormatException e2) {
                    throw new r(e2);
                }
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, BigDecimal bigDecimal) throws IOException {
                cVar.k0(bigDecimal);
            }
        };
        C = new TypeAdapter<BigInteger>() { // from class: com.google.gson.internal.bind.TypeAdapters.18
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public BigInteger read(com.google.gson.w.a aVar) throws IOException {
                if (aVar.j0() == com.google.gson.w.b.NULL) {
                    aVar.f0();
                    return null;
                }
                try {
                    return new BigInteger(aVar.h0());
                } catch (NumberFormatException e2) {
                    throw new r(e2);
                }
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, BigInteger bigInteger) throws IOException {
                cVar.k0(bigInteger);
            }
        };
        D = b(String.class, typeAdapter7);
        TypeAdapter<StringBuilder> typeAdapter8 = new TypeAdapter<StringBuilder>() { // from class: com.google.gson.internal.bind.TypeAdapters.19
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public StringBuilder read(com.google.gson.w.a aVar) throws IOException {
                if (aVar.j0() != com.google.gson.w.b.NULL) {
                    return new StringBuilder(aVar.h0());
                }
                aVar.f0();
                return null;
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, StringBuilder sb) throws IOException {
                cVar.l0(sb == null ? null : sb.toString());
            }
        };
        E = typeAdapter8;
        F = b(StringBuilder.class, typeAdapter8);
        TypeAdapter<StringBuffer> typeAdapter9 = new TypeAdapter<StringBuffer>() { // from class: com.google.gson.internal.bind.TypeAdapters.20
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public StringBuffer read(com.google.gson.w.a aVar) throws IOException {
                if (aVar.j0() != com.google.gson.w.b.NULL) {
                    return new StringBuffer(aVar.h0());
                }
                aVar.f0();
                return null;
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, StringBuffer stringBuffer) throws IOException {
                cVar.l0(stringBuffer == null ? null : stringBuffer.toString());
            }
        };
        G = typeAdapter9;
        H = b(StringBuffer.class, typeAdapter9);
        TypeAdapter<URL> typeAdapter10 = new TypeAdapter<URL>() { // from class: com.google.gson.internal.bind.TypeAdapters.21
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public URL read(com.google.gson.w.a aVar) throws IOException {
                if (aVar.j0() == com.google.gson.w.b.NULL) {
                    aVar.f0();
                    return null;
                }
                String strH0 = aVar.h0();
                if ("null".equals(strH0)) {
                    return null;
                }
                return new URL(strH0);
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, URL url) throws IOException {
                cVar.l0(url == null ? null : url.toExternalForm());
            }
        };
        I = typeAdapter10;
        J = b(URL.class, typeAdapter10);
        TypeAdapter<URI> typeAdapter11 = new TypeAdapter<URI>() { // from class: com.google.gson.internal.bind.TypeAdapters.22
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public URI read(com.google.gson.w.a aVar) throws IOException {
                if (aVar.j0() == com.google.gson.w.b.NULL) {
                    aVar.f0();
                    return null;
                }
                try {
                    String strH0 = aVar.h0();
                    if ("null".equals(strH0)) {
                        return null;
                    }
                    return new URI(strH0);
                } catch (URISyntaxException e2) {
                    throw new k(e2);
                }
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, URI uri) throws IOException {
                cVar.l0(uri == null ? null : uri.toASCIIString());
            }
        };
        K = typeAdapter11;
        L = b(URI.class, typeAdapter11);
        TypeAdapter<InetAddress> typeAdapter12 = new TypeAdapter<InetAddress>() { // from class: com.google.gson.internal.bind.TypeAdapters.23
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public InetAddress read(com.google.gson.w.a aVar) throws IOException {
                if (aVar.j0() != com.google.gson.w.b.NULL) {
                    return InetAddress.getByName(aVar.h0());
                }
                aVar.f0();
                return null;
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, InetAddress inetAddress) throws IOException {
                cVar.l0(inetAddress == null ? null : inetAddress.getHostAddress());
            }
        };
        M = typeAdapter12;
        N = e(InetAddress.class, typeAdapter12);
        TypeAdapter<UUID> typeAdapter13 = new TypeAdapter<UUID>() { // from class: com.google.gson.internal.bind.TypeAdapters.24
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public UUID read(com.google.gson.w.a aVar) throws IOException {
                if (aVar.j0() != com.google.gson.w.b.NULL) {
                    return UUID.fromString(aVar.h0());
                }
                aVar.f0();
                return null;
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, UUID uuid) throws IOException {
                cVar.l0(uuid == null ? null : uuid.toString());
            }
        };
        O = typeAdapter13;
        P = b(UUID.class, typeAdapter13);
        TypeAdapter<Currency> typeAdapterNullSafe6 = new TypeAdapter<Currency>() { // from class: com.google.gson.internal.bind.TypeAdapters.25
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Currency read(com.google.gson.w.a aVar) {
                return Currency.getInstance(aVar.h0());
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, Currency currency) throws IOException {
                cVar.l0(currency.getCurrencyCode());
            }
        }.nullSafe();
        Q = typeAdapterNullSafe6;
        R = b(Currency.class, typeAdapterNullSafe6);
        S = new t() { // from class: com.google.gson.internal.bind.TypeAdapters.26
            @Override // com.google.gson.t
            public <T> TypeAdapter<T> b(Gson gson, com.google.gson.v.a<T> aVar) {
                if (aVar.c() != Timestamp.class) {
                    return null;
                }
                final TypeAdapter<T> typeAdapterL = gson.l(Date.class);
                return (TypeAdapter<T>) new TypeAdapter<Timestamp>() { // from class: com.google.gson.internal.bind.TypeAdapters.26.1
                    @Override // com.google.gson.TypeAdapter
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public Timestamp read(com.google.gson.w.a aVar2) {
                        Date date = (Date) typeAdapterL.read(aVar2);
                        if (date != null) {
                            return new Timestamp(date.getTime());
                        }
                        return null;
                    }

                    @Override // com.google.gson.TypeAdapter
                    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
                    public void write(com.google.gson.w.c cVar, Timestamp timestamp) {
                        typeAdapterL.write(cVar, timestamp);
                    }
                };
            }
        };
        TypeAdapter<Calendar> typeAdapter14 = new TypeAdapter<Calendar>() { // from class: com.google.gson.internal.bind.TypeAdapters.27
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Calendar read(com.google.gson.w.a aVar) throws IOException {
                if (aVar.j0() == com.google.gson.w.b.NULL) {
                    aVar.f0();
                    return null;
                }
                aVar.d();
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                int i7 = 0;
                while (aVar.j0() != com.google.gson.w.b.END_OBJECT) {
                    String strD0 = aVar.d0();
                    int iB0 = aVar.b0();
                    if ("year".equals(strD0)) {
                        i2 = iB0;
                    } else if ("month".equals(strD0)) {
                        i3 = iB0;
                    } else if ("dayOfMonth".equals(strD0)) {
                        i4 = iB0;
                    } else if ("hourOfDay".equals(strD0)) {
                        i5 = iB0;
                    } else if ("minute".equals(strD0)) {
                        i6 = iB0;
                    } else if ("second".equals(strD0)) {
                        i7 = iB0;
                    }
                }
                aVar.S();
                return new GregorianCalendar(i2, i3, i4, i5, i6, i7);
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, Calendar calendar) throws IOException {
                if (calendar == null) {
                    cVar.Y();
                    return;
                }
                cVar.y();
                cVar.W("year");
                cVar.i0(calendar.get(1));
                cVar.W("month");
                cVar.i0(calendar.get(2));
                cVar.W("dayOfMonth");
                cVar.i0(calendar.get(5));
                cVar.W("hourOfDay");
                cVar.i0(calendar.get(11));
                cVar.W("minute");
                cVar.i0(calendar.get(12));
                cVar.W("second");
                cVar.i0(calendar.get(13));
                cVar.S();
            }
        };
        T = typeAdapter14;
        U = d(Calendar.class, GregorianCalendar.class, typeAdapter14);
        TypeAdapter<Locale> typeAdapter15 = new TypeAdapter<Locale>() { // from class: com.google.gson.internal.bind.TypeAdapters.28
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Locale read(com.google.gson.w.a aVar) throws IOException {
                if (aVar.j0() == com.google.gson.w.b.NULL) {
                    aVar.f0();
                    return null;
                }
                StringTokenizer stringTokenizer = new StringTokenizer(aVar.h0(), "_");
                String strNextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
                String strNextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
                String strNextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
                return (strNextToken2 == null && strNextToken3 == null) ? new Locale(strNextToken) : strNextToken3 == null ? new Locale(strNextToken, strNextToken2) : new Locale(strNextToken, strNextToken2, strNextToken3);
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, Locale locale) throws IOException {
                cVar.l0(locale == null ? null : locale.toString());
            }
        };
        V = typeAdapter15;
        W = b(Locale.class, typeAdapter15);
        TypeAdapter<j> typeAdapter16 = new TypeAdapter<j>() { // from class: com.google.gson.internal.bind.TypeAdapters.29
            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public j read(com.google.gson.w.a aVar) throws IOException {
                switch (a.f4535a[aVar.j0().ordinal()]) {
                    case 1:
                        return new o(new f(aVar.h0()));
                    case 2:
                        return new o(Boolean.valueOf(aVar.Z()));
                    case 3:
                        return new o(aVar.h0());
                    case 4:
                        aVar.f0();
                        return l.f4595a;
                    case 5:
                        g gVar = new g();
                        aVar.a();
                        while (aVar.V()) {
                            gVar.m(read(aVar));
                        }
                        aVar.R();
                        return gVar;
                    case 6:
                        m mVar = new m();
                        aVar.d();
                        while (aVar.V()) {
                            mVar.m(aVar.d0(), read(aVar));
                        }
                        aVar.S();
                        return mVar;
                    default:
                        throw new IllegalArgumentException();
                }
            }

            @Override // com.google.gson.TypeAdapter
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void write(com.google.gson.w.c cVar, j jVar) throws IOException {
                if (jVar == null || jVar.j()) {
                    cVar.Y();
                    return;
                }
                if (jVar.l()) {
                    o oVarF = jVar.f();
                    if (oVarF.q()) {
                        cVar.k0(oVarF.n());
                        return;
                    } else if (oVarF.o()) {
                        cVar.m0(oVarF.a());
                        return;
                    } else {
                        cVar.l0(oVarF.h());
                        return;
                    }
                }
                if (jVar.i()) {
                    cVar.j();
                    Iterator<j> it = jVar.d().iterator();
                    while (it.hasNext()) {
                        write(cVar, it.next());
                    }
                    cVar.R();
                    return;
                }
                if (!jVar.k()) {
                    throw new IllegalArgumentException("Couldn't write " + jVar.getClass());
                }
                cVar.y();
                for (Map.Entry<String, j> entry : jVar.e().n()) {
                    cVar.W(entry.getKey());
                    write(cVar, entry.getValue());
                }
                cVar.S();
            }
        };
        X = typeAdapter16;
        Y = e(j.class, typeAdapter16);
        Z = new t() { // from class: com.google.gson.internal.bind.TypeAdapters.30
            @Override // com.google.gson.t
            public <T> TypeAdapter<T> b(Gson gson, com.google.gson.v.a<T> aVar) {
                Class<? super T> clsC = aVar.c();
                if (!Enum.class.isAssignableFrom(clsC) || clsC == Enum.class) {
                    return null;
                }
                if (!clsC.isEnum()) {
                    clsC = clsC.getSuperclass();
                }
                return new EnumTypeAdapter(clsC);
            }
        };
    }

    public static <TT> t a(final com.google.gson.v.a<TT> aVar, final TypeAdapter<TT> typeAdapter) {
        return new t() { // from class: com.google.gson.internal.bind.TypeAdapters.31
            @Override // com.google.gson.t
            public <T> TypeAdapter<T> b(Gson gson, com.google.gson.v.a<T> aVar2) {
                if (aVar2.equals(aVar)) {
                    return typeAdapter;
                }
                return null;
            }
        };
    }

    public static <TT> t b(final Class<TT> cls, final TypeAdapter<TT> typeAdapter) {
        return new t() { // from class: com.google.gson.internal.bind.TypeAdapters.32
            @Override // com.google.gson.t
            public <T> TypeAdapter<T> b(Gson gson, com.google.gson.v.a<T> aVar) {
                if (aVar.c() == cls) {
                    return typeAdapter;
                }
                return null;
            }

            public String toString() {
                return "Factory[type=" + cls.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    public static <TT> t c(final Class<TT> cls, final Class<TT> cls2, final TypeAdapter<? super TT> typeAdapter) {
        return new t() { // from class: com.google.gson.internal.bind.TypeAdapters.33
            @Override // com.google.gson.t
            public <T> TypeAdapter<T> b(Gson gson, com.google.gson.v.a<T> aVar) {
                Class<? super T> clsC = aVar.c();
                if (clsC == cls || clsC == cls2) {
                    return typeAdapter;
                }
                return null;
            }

            public String toString() {
                return "Factory[type=" + cls2.getName() + "+" + cls.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    public static <TT> t d(final Class<TT> cls, final Class<? extends TT> cls2, final TypeAdapter<? super TT> typeAdapter) {
        return new t() { // from class: com.google.gson.internal.bind.TypeAdapters.34
            @Override // com.google.gson.t
            public <T> TypeAdapter<T> b(Gson gson, com.google.gson.v.a<T> aVar) {
                Class<? super T> clsC = aVar.c();
                if (clsC == cls || clsC == cls2) {
                    return typeAdapter;
                }
                return null;
            }

            public String toString() {
                return "Factory[type=" + cls.getName() + "+" + cls2.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    public static <T1> t e(final Class<T1> cls, final TypeAdapter<T1> typeAdapter) {
        return new t() { // from class: com.google.gson.internal.bind.TypeAdapters.35
            @Override // com.google.gson.t
            public <T2> TypeAdapter<T2> b(Gson gson, com.google.gson.v.a<T2> aVar) {
                final Class<? super T2> clsC = aVar.c();
                if (cls.isAssignableFrom(clsC)) {
                    return (TypeAdapter<T2>) new TypeAdapter<T1>() { // from class: com.google.gson.internal.bind.TypeAdapters.35.1
                        @Override // com.google.gson.TypeAdapter
                        public T1 read(com.google.gson.w.a aVar2) {
                            T1 t1 = (T1) typeAdapter.read(aVar2);
                            if (t1 == null || clsC.isInstance(t1)) {
                                return t1;
                            }
                            throw new r("Expected a " + clsC.getName() + " but was " + t1.getClass().getName());
                        }

                        @Override // com.google.gson.TypeAdapter
                        public void write(com.google.gson.w.c cVar, T1 t1) {
                            typeAdapter.write(cVar, t1);
                        }
                    };
                }
                return null;
            }

            public String toString() {
                return "Factory[typeHierarchy=" + cls.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }
}
