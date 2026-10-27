package com.google.gson.internal.bind;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.b;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.AccessController;
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
import p.b71;
import p.i71;
import p.lo2;
import p.mo2;
import p.n71;
import p.p71;
import p.qb2;
import p.qo2;
import p.r71;
import p.sa1;
import p.t71;
import p.u4;
import p.z71;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static final lo2 A;
    public static final lo2 a = new TypeAdapters$30(Class.class, new b() { // from class: com.google.gson.internal.bind.TypeAdapters$1
        @Override // com.google.gson.b
        public final Object b(t71 t71Var) {
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }

        @Override // com.google.gson.b
        public final void c(z71 z71Var, Object obj) {
            throw new UnsupportedOperationException(u4.d((Class) obj, new StringBuilder("Attempted to serialize java.lang.Class: "), ". Forgot to register a type adapter?"));
        }
    }.a());
    public static final lo2 b = new TypeAdapters$30(BitSet.class, new b() { // from class: com.google.gson.internal.bind.TypeAdapters$2
        /* JADX WARN: Removed duplicated region for block: B:22:0x004a  */
        @Override // com.google.gson.b
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object b(p.t71 r6) throws java.io.IOException {
            /*
                r5 = this;
                java.util.BitSet r0 = new java.util.BitSet
                r0.<init>()
                r6.b()
                com.google.gson.stream.JsonToken r1 = r6.K0()
                r2 = 0
            Ld:
                com.google.gson.stream.JsonToken r3 = com.google.gson.stream.JsonToken.e
                if (r1 == r3) goto L60
                int r3 = r1.ordinal()
                r4 = 5
                if (r3 == r4) goto L40
                r4 = 6
                if (r3 == r4) goto L39
                r4 = 7
                if (r3 != r4) goto L25
                boolean r1 = r6.w0()
                if (r1 == 0) goto L4d
                goto L4a
            L25:
                com.google.gson.JsonSyntaxException r6 = new com.google.gson.JsonSyntaxException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r2 = "Invalid bitset value type: "
                r0.<init>(r2)
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r6.<init>(r0)
                throw r6
            L39:
                int r1 = r6.D0()
                if (r1 == 0) goto L4d
                goto L4a
            L40:
                java.lang.String r1 = r6.u()
                int r1 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.NumberFormatException -> L54
                if (r1 == 0) goto L4d
            L4a:
                r0.set(r2)
            L4d:
                int r2 = r2 + 1
                com.google.gson.stream.JsonToken r1 = r6.K0()
                goto Ld
            L54:
                com.google.gson.JsonSyntaxException r6 = new com.google.gson.JsonSyntaxException
                java.lang.String r0 = "Error: Expecting: bitset number value (1, 0), Found: "
                java.lang.String r0 = p.u4.x(r0, r1)
                r6.<init>(r0)
                throw r6
            L60:
                r6.E()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.TypeAdapters$2.b(p.t71):java.lang.Object");
        }

        @Override // com.google.gson.b
        public final void c(z71 z71Var, Object obj) throws IOException {
            BitSet bitSet = (BitSet) obj;
            z71Var.c();
            int length = bitSet.length();
            for (int i2 = 0; i2 < length; i2++) {
                z71Var.I0(bitSet.get(i2) ? 1L : 0L);
            }
            z71Var.E();
        }
    }.a());
    public static final b c;
    public static final lo2 d;
    public static final lo2 e;
    public static final lo2 f;
    public static final lo2 g;
    public static final lo2 h;
    public static final lo2 i;
    public static final lo2 j;
    public static final b k;
    public static final lo2 l;
    public static final b m;
    public static final b n;
    public static final lo2 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final lo2 f39p;
    public static final lo2 q;
    public static final lo2 r;
    public static final lo2 s;
    public static final lo2 t;
    public static final lo2 u;
    public static final lo2 v;
    public static final lo2 w;
    public static final lo2 x;
    public static final b y;
    public static final lo2 z;

    static {
        b bVar = new b() { // from class: com.google.gson.internal.bind.TypeAdapters$3
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) throws IOException {
                JsonToken jsonTokenK0 = t71Var.K0();
                if (jsonTokenK0 != JsonToken.q) {
                    return jsonTokenK0 == JsonToken.n ? Boolean.valueOf(Boolean.parseBoolean(t71Var.u())) : Boolean.valueOf(t71Var.w0());
                }
                t71Var.z0();
                return null;
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                z71Var.J0((Boolean) obj);
            }
        };
        c = new b() { // from class: com.google.gson.internal.bind.TypeAdapters$4
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) throws IOException {
                if (t71Var.K0() != JsonToken.q) {
                    return Boolean.valueOf(t71Var.u());
                }
                t71Var.z0();
                return null;
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                Boolean bool = (Boolean) obj;
                z71Var.L0(bool == null ? "null" : bool.toString());
            }
        };
        d = new TypeAdapters$31(Boolean.TYPE, Boolean.class, bVar);
        e = new TypeAdapters$31(Byte.TYPE, Byte.class, new b() { // from class: com.google.gson.internal.bind.TypeAdapters$5
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) throws IOException {
                if (t71Var.K0() == JsonToken.q) {
                    t71Var.z0();
                    return null;
                }
                try {
                    return Byte.valueOf((byte) t71Var.D0());
                } catch (NumberFormatException e2) {
                    throw new JsonSyntaxException(e2);
                }
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                z71Var.K0((Number) obj);
            }
        });
        f = new TypeAdapters$31(Short.TYPE, Short.class, new b() { // from class: com.google.gson.internal.bind.TypeAdapters$6
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) throws IOException {
                if (t71Var.K0() == JsonToken.q) {
                    t71Var.z0();
                    return null;
                }
                try {
                    return Short.valueOf((short) t71Var.D0());
                } catch (NumberFormatException e2) {
                    throw new JsonSyntaxException(e2);
                }
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                z71Var.K0((Number) obj);
            }
        });
        g = new TypeAdapters$31(Integer.TYPE, Integer.class, new b() { // from class: com.google.gson.internal.bind.TypeAdapters$7
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) throws IOException {
                if (t71Var.K0() == JsonToken.q) {
                    t71Var.z0();
                    return null;
                }
                try {
                    return Integer.valueOf(t71Var.D0());
                } catch (NumberFormatException e2) {
                    throw new JsonSyntaxException(e2);
                }
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                z71Var.K0((Number) obj);
            }
        });
        h = new TypeAdapters$30(AtomicInteger.class, new b() { // from class: com.google.gson.internal.bind.TypeAdapters$8
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) {
                try {
                    return new AtomicInteger(t71Var.D0());
                } catch (NumberFormatException e2) {
                    throw new JsonSyntaxException(e2);
                }
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                z71Var.I0(((AtomicInteger) obj).get());
            }
        }.a());
        i = new TypeAdapters$30(AtomicBoolean.class, new b() { // from class: com.google.gson.internal.bind.TypeAdapters$9
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) {
                return new AtomicBoolean(t71Var.w0());
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                z71Var.M0(((AtomicBoolean) obj).get());
            }
        }.a());
        j = new TypeAdapters$30(AtomicIntegerArray.class, new b() { // from class: com.google.gson.internal.bind.TypeAdapters$10
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) throws IOException {
                ArrayList arrayList = new ArrayList();
                t71Var.b();
                while (t71Var.hasNext()) {
                    try {
                        arrayList.add(Integer.valueOf(t71Var.D0()));
                    } catch (NumberFormatException e2) {
                        throw new JsonSyntaxException(e2);
                    }
                }
                t71Var.E();
                int size = arrayList.size();
                AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
                for (int i2 = 0; i2 < size; i2++) {
                    atomicIntegerArray.set(i2, ((Integer) arrayList.get(i2)).intValue());
                }
                return atomicIntegerArray;
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                z71Var.c();
                int length = ((AtomicIntegerArray) obj).length();
                for (int i2 = 0; i2 < length; i2++) {
                    z71Var.I0(r6.get(i2));
                }
                z71Var.E();
            }
        }.a());
        k = new b() { // from class: com.google.gson.internal.bind.TypeAdapters$11
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) throws IOException {
                if (t71Var.K0() == JsonToken.q) {
                    t71Var.z0();
                    return null;
                }
                try {
                    return Long.valueOf(t71Var.Z());
                } catch (NumberFormatException e2) {
                    throw new JsonSyntaxException(e2);
                }
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                z71Var.K0((Number) obj);
            }
        };
        new b() { // from class: com.google.gson.internal.bind.TypeAdapters$12
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) throws IOException {
                if (t71Var.K0() != JsonToken.q) {
                    return Float.valueOf((float) t71Var.T());
                }
                t71Var.z0();
                return null;
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                z71Var.K0((Number) obj);
            }
        };
        new b() { // from class: com.google.gson.internal.bind.TypeAdapters$13
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) throws IOException {
                if (t71Var.K0() != JsonToken.q) {
                    return Double.valueOf(t71Var.T());
                }
                t71Var.z0();
                return null;
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                z71Var.K0((Number) obj);
            }
        };
        l = new TypeAdapters$31(Character.TYPE, Character.class, new b() { // from class: com.google.gson.internal.bind.TypeAdapters$14
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) throws IOException {
                if (t71Var.K0() == JsonToken.q) {
                    t71Var.z0();
                    return null;
                }
                String strU = t71Var.u();
                if (strU.length() == 1) {
                    return Character.valueOf(strU.charAt(0));
                }
                throw new JsonSyntaxException("Expecting character, got: ".concat(strU));
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                Character ch = (Character) obj;
                z71Var.L0(ch == null ? null : String.valueOf(ch));
            }
        });
        b bVar2 = new b() { // from class: com.google.gson.internal.bind.TypeAdapters$15
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) throws IOException {
                JsonToken jsonTokenK0 = t71Var.K0();
                if (jsonTokenK0 != JsonToken.q) {
                    return jsonTokenK0 == JsonToken.f40p ? Boolean.toString(t71Var.w0()) : t71Var.u();
                }
                t71Var.z0();
                return null;
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                z71Var.L0((String) obj);
            }
        };
        m = new b() { // from class: com.google.gson.internal.bind.TypeAdapters$16
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) throws IOException {
                if (t71Var.K0() == JsonToken.q) {
                    t71Var.z0();
                    return null;
                }
                try {
                    return new BigDecimal(t71Var.u());
                } catch (NumberFormatException e2) {
                    throw new JsonSyntaxException(e2);
                }
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                z71Var.K0((BigDecimal) obj);
            }
        };
        n = new b() { // from class: com.google.gson.internal.bind.TypeAdapters$17
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) throws IOException {
                if (t71Var.K0() == JsonToken.q) {
                    t71Var.z0();
                    return null;
                }
                try {
                    return new BigInteger(t71Var.u());
                } catch (NumberFormatException e2) {
                    throw new JsonSyntaxException(e2);
                }
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                z71Var.K0((BigInteger) obj);
            }
        };
        o = new TypeAdapters$30(String.class, bVar2);
        f39p = new TypeAdapters$30(StringBuilder.class, new b() { // from class: com.google.gson.internal.bind.TypeAdapters$18
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) throws IOException {
                if (t71Var.K0() != JsonToken.q) {
                    return new StringBuilder(t71Var.u());
                }
                t71Var.z0();
                return null;
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                StringBuilder sb = (StringBuilder) obj;
                z71Var.L0(sb == null ? null : sb.toString());
            }
        });
        q = new TypeAdapters$30(StringBuffer.class, new b() { // from class: com.google.gson.internal.bind.TypeAdapters$19
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) throws IOException {
                if (t71Var.K0() != JsonToken.q) {
                    return new StringBuffer(t71Var.u());
                }
                t71Var.z0();
                return null;
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                StringBuffer stringBuffer = (StringBuffer) obj;
                z71Var.L0(stringBuffer == null ? null : stringBuffer.toString());
            }
        });
        r = new TypeAdapters$30(URL.class, new b() { // from class: com.google.gson.internal.bind.TypeAdapters$20
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) throws IOException {
                if (t71Var.K0() == JsonToken.q) {
                    t71Var.z0();
                    return null;
                }
                String strU = t71Var.u();
                if ("null".equals(strU)) {
                    return null;
                }
                return new URL(strU);
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                URL url = (URL) obj;
                z71Var.L0(url == null ? null : url.toExternalForm());
            }
        });
        s = new TypeAdapters$30(URI.class, new b() { // from class: com.google.gson.internal.bind.TypeAdapters$21
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) throws IOException {
                if (t71Var.K0() == JsonToken.q) {
                    t71Var.z0();
                    return null;
                }
                try {
                    String strU = t71Var.u();
                    if ("null".equals(strU)) {
                        return null;
                    }
                    return new URI(strU);
                } catch (URISyntaxException e2) {
                    throw new JsonIOException(e2);
                }
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                URI uri = (URI) obj;
                z71Var.L0(uri == null ? null : uri.toASCIIString());
            }
        });
        final b bVar3 = new b() { // from class: com.google.gson.internal.bind.TypeAdapters$22
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) throws IOException {
                if (t71Var.K0() != JsonToken.q) {
                    return InetAddress.getByName(t71Var.u());
                }
                t71Var.z0();
                return null;
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                InetAddress inetAddress = (InetAddress) obj;
                z71Var.L0(inetAddress == null ? null : inetAddress.getHostAddress());
            }
        };
        final Class<InetAddress> cls = InetAddress.class;
        t = new lo2() { // from class: com.google.gson.internal.bind.TypeAdapters$33
            @Override // p.lo2
            public final b a(com.google.gson.a aVar, qo2 qo2Var) {
                final Class<?> cls2 = qo2Var.a;
                if (cls.isAssignableFrom(cls2)) {
                    return new b() { // from class: com.google.gson.internal.bind.TypeAdapters$33.1
                        @Override // com.google.gson.b
                        public final Object b(t71 t71Var) {
                            Object objB = bVar3.b(t71Var);
                            if (objB != null) {
                                Class cls3 = cls2;
                                if (!cls3.isInstance(objB)) {
                                    throw new JsonSyntaxException("Expected a " + cls3.getName() + " but was " + objB.getClass().getName());
                                }
                            }
                            return objB;
                        }

                        @Override // com.google.gson.b
                        public final void c(z71 z71Var, Object obj) {
                            bVar3.c(z71Var, obj);
                        }
                    };
                }
                return null;
            }

            public final String toString() {
                return "Factory[typeHierarchy=" + cls.getName() + ",adapter=" + bVar3 + "]";
            }
        };
        u = new TypeAdapters$30(UUID.class, new b() { // from class: com.google.gson.internal.bind.TypeAdapters$23
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) throws IOException {
                if (t71Var.K0() != JsonToken.q) {
                    return UUID.fromString(t71Var.u());
                }
                t71Var.z0();
                return null;
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                UUID uuid = (UUID) obj;
                z71Var.L0(uuid == null ? null : uuid.toString());
            }
        });
        v = new TypeAdapters$30(Currency.class, new b() { // from class: com.google.gson.internal.bind.TypeAdapters$24
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) {
                return Currency.getInstance(t71Var.u());
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                z71Var.L0(((Currency) obj).getCurrencyCode());
            }
        }.a());
        final b bVar4 = new b() { // from class: com.google.gson.internal.bind.TypeAdapters$25
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) throws IOException {
                if (t71Var.K0() == JsonToken.q) {
                    t71Var.z0();
                    return null;
                }
                t71Var.c();
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                int i7 = 0;
                while (t71Var.K0() != JsonToken.l) {
                    String strR0 = t71Var.r0();
                    int iD0 = t71Var.D0();
                    if ("year".equals(strR0)) {
                        i2 = iD0;
                    } else if ("month".equals(strR0)) {
                        i3 = iD0;
                    } else if ("dayOfMonth".equals(strR0)) {
                        i4 = iD0;
                    } else if ("hourOfDay".equals(strR0)) {
                        i5 = iD0;
                    } else if ("minute".equals(strR0)) {
                        i6 = iD0;
                    } else if ("second".equals(strR0)) {
                        i7 = iD0;
                    }
                }
                t71Var.J();
                return new GregorianCalendar(i2, i3, i4, i5, i6, i7);
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                if (((Calendar) obj) == null) {
                    z71Var.E0();
                    return;
                }
                z71Var.i();
                z71Var.X("year");
                z71Var.I0(r4.get(1));
                z71Var.X("month");
                z71Var.I0(r4.get(2));
                z71Var.X("dayOfMonth");
                z71Var.I0(r4.get(5));
                z71Var.X("hourOfDay");
                z71Var.I0(r4.get(11));
                z71Var.X("minute");
                z71Var.I0(r4.get(12));
                z71Var.X("second");
                z71Var.I0(r4.get(13));
                z71Var.J();
            }
        };
        w = new lo2() { // from class: com.google.gson.internal.bind.TypeAdapters$32
            public final /* synthetic */ Class c = Calendar.class;
            public final /* synthetic */ Class e = GregorianCalendar.class;

            @Override // p.lo2
            public final b a(com.google.gson.a aVar, qo2 qo2Var) {
                Class cls2 = qo2Var.a;
                if (cls2 == this.c || cls2 == this.e) {
                    return bVar4;
                }
                return null;
            }

            public final String toString() {
                return "Factory[type=" + this.c.getName() + "+" + this.e.getName() + ",adapter=" + bVar4 + "]";
            }
        };
        x = new TypeAdapters$30(Locale.class, new b() { // from class: com.google.gson.internal.bind.TypeAdapters$26
            @Override // com.google.gson.b
            public final Object b(t71 t71Var) throws IOException {
                if (t71Var.K0() == JsonToken.q) {
                    t71Var.z0();
                    return null;
                }
                StringTokenizer stringTokenizer = new StringTokenizer(t71Var.u(), "_");
                String strNextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
                String strNextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
                String strNextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
                return (strNextToken2 == null && strNextToken3 == null) ? new Locale(strNextToken) : strNextToken3 == null ? new Locale(strNextToken, strNextToken2) : new Locale(strNextToken, strNextToken2, strNextToken3);
            }

            @Override // com.google.gson.b
            public final void c(z71 z71Var, Object obj) throws IOException {
                Locale locale = (Locale) obj;
                z71Var.L0(locale == null ? null : locale.toString());
            }
        });
        final b bVar5 = new b() { // from class: com.google.gson.internal.bind.TypeAdapters$27
            public static i71 d(t71 t71Var) throws IOException {
                int iOrdinal = t71Var.K0().ordinal();
                if (iOrdinal == 0) {
                    b71 b71Var = new b71();
                    t71Var.b();
                    while (t71Var.hasNext()) {
                        b71Var.c.add(d(t71Var));
                    }
                    t71Var.E();
                    return b71Var;
                }
                if (iOrdinal == 2) {
                    p71 p71Var = new p71();
                    t71Var.c();
                    while (t71Var.hasNext()) {
                        p71Var.c.put(t71Var.r0(), d(t71Var));
                    }
                    t71Var.J();
                    return p71Var;
                }
                if (iOrdinal == 5) {
                    return new r71(t71Var.u());
                }
                if (iOrdinal == 6) {
                    return new r71(new LazilyParsedNumber(t71Var.u()));
                }
                if (iOrdinal == 7) {
                    return new r71(Boolean.valueOf(t71Var.w0()));
                }
                if (iOrdinal != 8) {
                    throw new IllegalArgumentException();
                }
                t71Var.z0();
                return n71.c;
            }

            public static void e(i71 i71Var, z71 z71Var) throws IOException {
                if (i71Var == null || (i71Var instanceof n71)) {
                    z71Var.E0();
                    return;
                }
                boolean z2 = i71Var instanceof r71;
                if (z2) {
                    if (!z2) {
                        throw new IllegalStateException("Not a JSON Primitive: " + i71Var);
                    }
                    r71 r71Var = (r71) i71Var;
                    Serializable serializable = r71Var.c;
                    if (serializable instanceof Number) {
                        z71Var.K0(r71Var.a());
                        return;
                    } else if (serializable instanceof Boolean) {
                        z71Var.M0(serializable instanceof Boolean ? ((Boolean) serializable).booleanValue() : Boolean.parseBoolean(r71Var.b()));
                        return;
                    } else {
                        z71Var.L0(r71Var.b());
                        return;
                    }
                }
                boolean z3 = i71Var instanceof b71;
                if (z3) {
                    z71Var.c();
                    if (!z3) {
                        throw new IllegalStateException("Not a JSON Array: " + i71Var);
                    }
                    Iterator it = ((b71) i71Var).c.iterator();
                    while (it.hasNext()) {
                        e((i71) it.next(), z71Var);
                    }
                    z71Var.E();
                    return;
                }
                boolean z4 = i71Var instanceof p71;
                if (!z4) {
                    throw new IllegalArgumentException("Couldn't write " + i71Var.getClass());
                }
                z71Var.i();
                if (!z4) {
                    throw new IllegalStateException("Not a JSON Object: " + i71Var);
                }
                sa1 sa1Var = new sa1((com.google.gson.internal.b) ((p71) i71Var).c.entrySet());
                while (sa1Var.hasNext()) {
                    Map.Entry entry = (Map.Entry) sa1Var.next();
                    z71Var.X((String) entry.getKey());
                    e((i71) entry.getValue(), z71Var);
                }
                z71Var.J();
            }

            @Override // com.google.gson.b
            public final /* bridge */ /* synthetic */ Object b(t71 t71Var) {
                return d(t71Var);
            }

            @Override // com.google.gson.b
            public final /* bridge */ /* synthetic */ void c(z71 z71Var, Object obj) throws IOException {
                e((i71) obj, z71Var);
            }
        };
        y = bVar5;
        final Class<i71> cls2 = i71.class;
        z = new lo2() { // from class: com.google.gson.internal.bind.TypeAdapters$33
            @Override // p.lo2
            public final b a(com.google.gson.a aVar, qo2 qo2Var) {
                final Class cls22 = qo2Var.a;
                if (cls2.isAssignableFrom(cls22)) {
                    return new b() { // from class: com.google.gson.internal.bind.TypeAdapters$33.1
                        @Override // com.google.gson.b
                        public final Object b(t71 t71Var) {
                            Object objB = bVar5.b(t71Var);
                            if (objB != null) {
                                Class cls3 = cls22;
                                if (!cls3.isInstance(objB)) {
                                    throw new JsonSyntaxException("Expected a " + cls3.getName() + " but was " + objB.getClass().getName());
                                }
                            }
                            return objB;
                        }

                        @Override // com.google.gson.b
                        public final void c(z71 z71Var, Object obj) {
                            bVar5.c(z71Var, obj);
                        }
                    };
                }
                return null;
            }

            public final String toString() {
                return "Factory[typeHierarchy=" + cls2.getName() + ",adapter=" + bVar5 + "]";
            }
        };
        A = new lo2() { // from class: com.google.gson.internal.bind.TypeAdapters$28
            @Override // p.lo2
            public final b a(com.google.gson.a aVar, qo2 qo2Var) {
                final Class superclass = qo2Var.a;
                if (!Enum.class.isAssignableFrom(superclass) || superclass == Enum.class) {
                    return null;
                }
                if (!superclass.isEnum()) {
                    superclass = superclass.getSuperclass();
                }
                return new b(superclass) { // from class: com.google.gson.internal.bind.TypeAdapters$EnumTypeAdapter
                    public final HashMap a = new HashMap();
                    public final HashMap b = new HashMap();

                    {
                        try {
                            for (Field field : superclass.getDeclaredFields()) {
                                if (field.isEnumConstant()) {
                                    AccessController.doPrivileged(new mo2(field));
                                    Enum r4 = (Enum) field.get(null);
                                    String strName = r4.name();
                                    qb2 qb2Var = (qb2) field.getAnnotation(qb2.class);
                                    if (qb2Var != null) {
                                        strName = qb2Var.value();
                                        for (String str : qb2Var.alternate()) {
                                            this.a.put(str, r4);
                                        }
                                    }
                                    this.a.put(strName, r4);
                                    this.b.put(r4, strName);
                                }
                            }
                        } catch (IllegalAccessException e2) {
                            throw new AssertionError(e2);
                        }
                    }

                    @Override // com.google.gson.b
                    public final Object b(t71 t71Var) throws IOException {
                        if (t71Var.K0() != JsonToken.q) {
                            return (Enum) this.a.get(t71Var.u());
                        }
                        t71Var.z0();
                        return null;
                    }

                    @Override // com.google.gson.b
                    public final void c(z71 z71Var, Object obj) throws IOException {
                        Enum r3 = (Enum) obj;
                        z71Var.L0(r3 == null ? null : (String) this.b.get(r3));
                    }
                };
            }
        };
    }

    public static lo2 a(Class cls, b bVar) {
        return new TypeAdapters$30(cls, bVar);
    }

    public static lo2 b(Class cls, Class cls2, b bVar) {
        return new TypeAdapters$31(cls, cls2, bVar);
    }
}
