package spi;

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
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import spi.y5;

/* JADX INFO: compiled from: TypeAdapters.java */
/* JADX INFO: loaded from: classes2.dex */
public final class qb {
    public static final nb<String> A;
    public static final nb<BigDecimal> B;
    public static final nb<BigInteger> C;
    public static final rb D;
    public static final nb<StringBuilder> E;
    public static final rb F;
    public static final nb<StringBuffer> G;
    public static final rb H;
    public static final nb<URL> I;
    public static final rb J;
    public static final nb<URI> K;
    public static final rb L;
    public static final nb<InetAddress> M;
    public static final ub N;
    public static final nb<UUID> O;
    public static final rb P;
    public static final nb<Currency> Q;
    public static final rb R;
    public static final r S;
    public static final nb<Calendar> T;
    public static final tb U;
    public static final nb<Locale> V;
    public static final rb W;
    public static final nb<i5> X;
    public static final ub Y;
    public static final w Z;
    public static final nb<Class> a;
    public static final rb b;
    public static final nb<BitSet> c;
    public static final rb d;
    public static final nb<Boolean> e;
    public static final nb<Boolean> f;
    public static final sb g;
    public static final nb<Number> h;
    public static final sb i;
    public static final nb<Number> j;
    public static final sb k;
    public static final nb<Number> l;
    public static final sb m;
    public static final nb<AtomicInteger> n;
    public static final rb o;
    public static final nb<AtomicBoolean> p;
    public static final rb q;
    public static final nb<AtomicIntegerArray> r;
    public static final rb s;
    public static final nb<Number> t;
    public static final nb<Number> u;
    public static final nb<Number> v;
    public static final nb<Number> w;
    public static final rb x;
    public static final nb<Character> y;
    public static final sb z;

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class a extends nb<AtomicIntegerArray> {
        @Override // spi.nb
        public final AtomicIntegerArray a(o5 o5Var) throws IOException {
            ArrayList arrayList = new ArrayList();
            o5Var.a();
            while (o5Var.i()) {
                try {
                    arrayList.add(Integer.valueOf(o5Var.n()));
                } catch (NumberFormatException e) {
                    throw new r5(e);
                }
            }
            o5Var.e();
            int size = arrayList.size();
            AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
            for (int i = 0; i < size; i++) {
                atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
            }
            return atomicIntegerArray;
        }

        @Override // spi.nb
        public final void b(v5 v5Var, AtomicIntegerArray atomicIntegerArray) throws IOException {
            v5Var.b();
            int length = atomicIntegerArray.length();
            for (int i = 0; i < length; i++) {
                v5Var.n(r5.get(i));
            }
            v5Var.e();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class a0 extends nb<Number> {
        @Override // spi.nb
        public final Number a(o5 o5Var) throws IOException {
            if (o5Var.v() == s5.NULL) {
                o5Var.r();
                return null;
            }
            try {
                return Byte.valueOf((byte) o5Var.n());
            } catch (NumberFormatException e) {
                throw new r5(e);
            }
        }

        @Override // spi.nb
        public final void b(v5 v5Var, Number number) throws IOException {
            v5Var.p(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class b extends nb<Number> {
        @Override // spi.nb
        public final Number a(o5 o5Var) throws IOException {
            if (o5Var.v() == s5.NULL) {
                o5Var.r();
                return null;
            }
            try {
                return Long.valueOf(o5Var.o());
            } catch (NumberFormatException e) {
                throw new r5(e);
            }
        }

        @Override // spi.nb
        public final void b(v5 v5Var, Number number) throws IOException {
            v5Var.p(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class b0 extends nb<Number> {
        @Override // spi.nb
        public final Number a(o5 o5Var) throws IOException {
            if (o5Var.v() == s5.NULL) {
                o5Var.r();
                return null;
            }
            try {
                return Short.valueOf((short) o5Var.n());
            } catch (NumberFormatException e) {
                throw new r5(e);
            }
        }

        @Override // spi.nb
        public final void b(v5 v5Var, Number number) throws IOException {
            v5Var.p(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class c extends nb<Number> {
        @Override // spi.nb
        public final Number a(o5 o5Var) throws IOException {
            if (o5Var.v() != s5.NULL) {
                return Float.valueOf((float) o5Var.m());
            }
            o5Var.r();
            return null;
        }

        @Override // spi.nb
        public final void b(v5 v5Var, Number number) throws IOException {
            v5Var.p(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class c0 extends nb<Number> {
        @Override // spi.nb
        public final Number a(o5 o5Var) throws IOException {
            if (o5Var.v() == s5.NULL) {
                o5Var.r();
                return null;
            }
            try {
                return Integer.valueOf(o5Var.n());
            } catch (NumberFormatException e) {
                throw new r5(e);
            }
        }

        @Override // spi.nb
        public final void b(v5 v5Var, Number number) throws IOException {
            v5Var.p(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class d extends nb<Number> {
        @Override // spi.nb
        public final Number a(o5 o5Var) throws IOException {
            if (o5Var.v() != s5.NULL) {
                return Double.valueOf(o5Var.m());
            }
            o5Var.r();
            return null;
        }

        @Override // spi.nb
        public final void b(v5 v5Var, Number number) throws IOException {
            v5Var.p(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class d0 extends nb<AtomicInteger> {
        @Override // spi.nb
        public final AtomicInteger a(o5 o5Var) {
            try {
                return new AtomicInteger(o5Var.n());
            } catch (NumberFormatException e) {
                throw new r5(e);
            }
        }

        @Override // spi.nb
        public final void b(v5 v5Var, AtomicInteger atomicInteger) throws IOException {
            v5Var.n(atomicInteger.get());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class e extends nb<Number> {
        @Override // spi.nb
        public final Number a(o5 o5Var) throws IOException {
            s5 s5VarV = o5Var.v();
            int i = x.a[s5VarV.ordinal()];
            if (i == 1 || i == 3) {
                return new x5(o5Var.t());
            }
            if (i == 4) {
                o5Var.r();
                return null;
            }
            throw new r5("Expecting number, got: " + s5VarV);
        }

        @Override // spi.nb
        public final void b(v5 v5Var, Number number) throws IOException {
            v5Var.p(number);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class e0 extends nb<AtomicBoolean> {
        @Override // spi.nb
        public final AtomicBoolean a(o5 o5Var) {
            return new AtomicBoolean(o5Var.l());
        }

        @Override // spi.nb
        public final void b(v5 v5Var, AtomicBoolean atomicBoolean) throws IOException {
            v5Var.r(atomicBoolean.get());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class f extends nb<Character> {
        @Override // spi.nb
        public final Character a(o5 o5Var) throws IOException {
            if (o5Var.v() == s5.NULL) {
                o5Var.r();
                return null;
            }
            String strT = o5Var.t();
            if (strT.length() == 1) {
                return Character.valueOf(strT.charAt(0));
            }
            throw new r5(spi.c.f("Expecting character, got: ", strT));
        }

        @Override // spi.nb
        public final void b(v5 v5Var, Character ch) throws IOException {
            Character ch2 = ch;
            v5Var.q(ch2 == null ? null : String.valueOf(ch2));
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static final class f0<T extends Enum<T>> extends nb<T> {
        public final Map<String, T> a = new HashMap();
        public final Map<T, String> b = new HashMap();

        public f0(Class<T> cls) {
            try {
                for (T t : cls.getEnumConstants()) {
                    String strName = t.name();
                    ba baVar = (ba) cls.getField(strName).getAnnotation(ba.class);
                    if (baVar != null) {
                        strName = baVar.value();
                        for (String str : baVar.alternate()) {
                            this.a.put(str, t);
                        }
                    }
                    this.a.put(strName, t);
                    this.b.put(t, strName);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError(e);
            }
        }

        @Override // spi.nb
        public final Object a(o5 o5Var) throws IOException {
            if (o5Var.v() != s5.NULL) {
                return (Enum) this.a.get(o5Var.t());
            }
            o5Var.r();
            return null;
        }

        @Override // spi.nb
        public final void b(v5 v5Var, Object obj) throws IOException {
            Enum r2 = (Enum) obj;
            v5Var.q(r2 == null ? null : (String) this.b.get(r2));
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class g extends nb<String> {
        @Override // spi.nb
        public final String a(o5 o5Var) throws IOException {
            s5 s5VarV = o5Var.v();
            if (s5VarV != s5.NULL) {
                return s5VarV == s5.BOOLEAN ? Boolean.toString(o5Var.l()) : o5Var.t();
            }
            o5Var.r();
            return null;
        }

        @Override // spi.nb
        public final void b(v5 v5Var, String str) throws IOException {
            v5Var.q(str);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class h extends nb<BigDecimal> {
        @Override // spi.nb
        public final BigDecimal a(o5 o5Var) throws IOException {
            if (o5Var.v() == s5.NULL) {
                o5Var.r();
                return null;
            }
            try {
                return new BigDecimal(o5Var.t());
            } catch (NumberFormatException e) {
                throw new r5(e);
            }
        }

        @Override // spi.nb
        public final void b(v5 v5Var, BigDecimal bigDecimal) throws IOException {
            v5Var.p(bigDecimal);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class i extends nb<BigInteger> {
        @Override // spi.nb
        public final BigInteger a(o5 o5Var) throws IOException {
            if (o5Var.v() == s5.NULL) {
                o5Var.r();
                return null;
            }
            try {
                return new BigInteger(o5Var.t());
            } catch (NumberFormatException e) {
                throw new r5(e);
            }
        }

        @Override // spi.nb
        public final void b(v5 v5Var, BigInteger bigInteger) throws IOException {
            v5Var.p(bigInteger);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class j extends nb<StringBuilder> {
        @Override // spi.nb
        public final StringBuilder a(o5 o5Var) throws IOException {
            if (o5Var.v() != s5.NULL) {
                return new StringBuilder(o5Var.t());
            }
            o5Var.r();
            return null;
        }

        @Override // spi.nb
        public final void b(v5 v5Var, StringBuilder sb) throws IOException {
            StringBuilder sb2 = sb;
            v5Var.q(sb2 == null ? null : sb2.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class k extends nb<Class> {
        @Override // spi.nb
        public final Class a(o5 o5Var) {
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }

        @Override // spi.nb
        public final void b(v5 v5Var, Class cls) {
            StringBuilder sbK = spi.c.k("Attempted to serialize java.lang.Class: ");
            sbK.append(cls.getName());
            sbK.append(". Forgot to register a type adapter?");
            throw new UnsupportedOperationException(sbK.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class l extends nb<StringBuffer> {
        @Override // spi.nb
        public final StringBuffer a(o5 o5Var) throws IOException {
            if (o5Var.v() != s5.NULL) {
                return new StringBuffer(o5Var.t());
            }
            o5Var.r();
            return null;
        }

        @Override // spi.nb
        public final void b(v5 v5Var, StringBuffer stringBuffer) throws IOException {
            StringBuffer stringBuffer2 = stringBuffer;
            v5Var.q(stringBuffer2 == null ? null : stringBuffer2.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class m extends nb<URL> {
        @Override // spi.nb
        public final URL a(o5 o5Var) throws IOException {
            if (o5Var.v() == s5.NULL) {
                o5Var.r();
                return null;
            }
            String strT = o5Var.t();
            if ("null".equals(strT)) {
                return null;
            }
            return new URL(strT);
        }

        @Override // spi.nb
        public final void b(v5 v5Var, URL url) throws IOException {
            URL url2 = url;
            v5Var.q(url2 == null ? null : url2.toExternalForm());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class n extends nb<URI> {
        @Override // spi.nb
        public final URI a(o5 o5Var) throws IOException {
            if (o5Var.v() == s5.NULL) {
                o5Var.r();
                return null;
            }
            try {
                String strT = o5Var.t();
                if ("null".equals(strT)) {
                    return null;
                }
                return new URI(strT);
            } catch (URISyntaxException e) {
                throw new j5(e);
            }
        }

        @Override // spi.nb
        public final void b(v5 v5Var, URI uri) throws IOException {
            URI uri2 = uri;
            v5Var.q(uri2 == null ? null : uri2.toASCIIString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class o extends nb<InetAddress> {
        @Override // spi.nb
        public final InetAddress a(o5 o5Var) throws IOException {
            if (o5Var.v() != s5.NULL) {
                return InetAddress.getByName(o5Var.t());
            }
            o5Var.r();
            return null;
        }

        @Override // spi.nb
        public final void b(v5 v5Var, InetAddress inetAddress) throws IOException {
            InetAddress inetAddress2 = inetAddress;
            v5Var.q(inetAddress2 == null ? null : inetAddress2.getHostAddress());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class p extends nb<UUID> {
        @Override // spi.nb
        public final UUID a(o5 o5Var) throws IOException {
            if (o5Var.v() != s5.NULL) {
                return UUID.fromString(o5Var.t());
            }
            o5Var.r();
            return null;
        }

        @Override // spi.nb
        public final void b(v5 v5Var, UUID uuid) throws IOException {
            UUID uuid2 = uuid;
            v5Var.q(uuid2 == null ? null : uuid2.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class q extends nb<Currency> {
        @Override // spi.nb
        public final Currency a(o5 o5Var) {
            return Currency.getInstance(o5Var.t());
        }

        @Override // spi.nb
        public final void b(v5 v5Var, Currency currency) throws IOException {
            v5Var.q(currency.getCurrencyCode());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class r implements ob {

        /* JADX INFO: compiled from: TypeAdapters.java */
        public class a extends nb<Timestamp> {
            public final /* synthetic */ nb a;

            public a(r rVar, nb nbVar) {
                this.a = nbVar;
            }

            @Override // spi.nb
            public final Timestamp a(o5 o5Var) {
                Date date = (Date) this.a.a(o5Var);
                if (date != null) {
                    return new Timestamp(date.getTime());
                }
                return null;
            }

            @Override // spi.nb
            public final void b(v5 v5Var, Timestamp timestamp) {
                this.a.b(v5Var, timestamp);
            }
        }

        @Override // spi.ob
        public final <T> nb<T> a(j4 j4Var, vb<T> vbVar) {
            if (vbVar.a != Timestamp.class) {
                return null;
            }
            Objects.requireNonNull(j4Var);
            return new a(this, j4Var.e(new vb<>(Date.class)));
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class s extends nb<Calendar> {
        @Override // spi.nb
        public final Calendar a(o5 o5Var) throws IOException {
            if (o5Var.v() == s5.NULL) {
                o5Var.r();
                return null;
            }
            o5Var.b();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (o5Var.v() != s5.END_OBJECT) {
                String strP = o5Var.p();
                int iN = o5Var.n();
                if ("year".equals(strP)) {
                    i = iN;
                } else if ("month".equals(strP)) {
                    i2 = iN;
                } else if ("dayOfMonth".equals(strP)) {
                    i3 = iN;
                } else if ("hourOfDay".equals(strP)) {
                    i4 = iN;
                } else if ("minute".equals(strP)) {
                    i5 = iN;
                } else if ("second".equals(strP)) {
                    i6 = iN;
                }
            }
            o5Var.f();
            return new GregorianCalendar(i, i2, i3, i4, i5, i6);
        }

        @Override // spi.nb
        public final void b(v5 v5Var, Calendar calendar) throws IOException {
            if (calendar == null) {
                v5Var.i();
                return;
            }
            v5Var.c();
            v5Var.g("year");
            v5Var.n(r4.get(1));
            v5Var.g("month");
            v5Var.n(r4.get(2));
            v5Var.g("dayOfMonth");
            v5Var.n(r4.get(5));
            v5Var.g("hourOfDay");
            v5Var.n(r4.get(11));
            v5Var.g("minute");
            v5Var.n(r4.get(12));
            v5Var.g("second");
            v5Var.n(r4.get(13));
            v5Var.f();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class t extends nb<Locale> {
        @Override // spi.nb
        public final Locale a(o5 o5Var) throws IOException {
            if (o5Var.v() == s5.NULL) {
                o5Var.r();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(o5Var.t(), "_");
            String strNextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String strNextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String strNextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            return (strNextToken2 == null && strNextToken3 == null) ? new Locale(strNextToken) : strNextToken3 == null ? new Locale(strNextToken, strNextToken2) : new Locale(strNextToken, strNextToken2, strNextToken3);
        }

        @Override // spi.nb
        public final void b(v5 v5Var, Locale locale) throws IOException {
            Locale locale2 = locale;
            v5Var.q(locale2 == null ? null : locale2.toString());
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class u extends nb<i5> {
        /* JADX WARN: Type inference failed for: r2v0, types: [java.util.ArrayList, java.util.List<spi.i5>] */
        @Override // spi.nb
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public final i5 a(o5 o5Var) throws IOException {
            switch (x.a[o5Var.v().ordinal()]) {
                case 1:
                    return new n5((Number) new x5(o5Var.t()));
                case 2:
                    return new n5(Boolean.valueOf(o5Var.l()));
                case 3:
                    return new n5(o5Var.t());
                case 4:
                    o5Var.r();
                    return k5.a;
                case 5:
                    g5 g5Var = new g5();
                    o5Var.a();
                    while (o5Var.i()) {
                        i5 i5VarA = a(o5Var);
                        if (i5VarA == null) {
                            i5VarA = k5.a;
                        }
                        g5Var.a.add(i5VarA);
                    }
                    o5Var.e();
                    return g5Var;
                case 6:
                    l5 l5Var = new l5();
                    o5Var.b();
                    while (o5Var.i()) {
                        String strP = o5Var.p();
                        i5 i5VarA2 = a(o5Var);
                        if (i5VarA2 == null) {
                            i5VarA2 = k5.a;
                        }
                        l5Var.a.put(strP, i5VarA2);
                    }
                    o5Var.f();
                    return l5Var;
                default:
                    throw new IllegalArgumentException();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // spi.nb
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public final void b(v5 v5Var, i5 i5Var) throws IOException {
            if (i5Var == null || (i5Var instanceof k5)) {
                v5Var.i();
                return;
            }
            if (i5Var instanceof n5) {
                n5 n5VarA = i5Var.a();
                Object obj = n5VarA.a;
                if (obj instanceof Number) {
                    v5Var.p(n5VarA.c());
                    return;
                } else if (obj instanceof Boolean) {
                    v5Var.r(n5VarA.b());
                    return;
                } else {
                    v5Var.q(n5VarA.d());
                    return;
                }
            }
            boolean z = i5Var instanceof g5;
            if (z) {
                v5Var.b();
                if (!z) {
                    throw new IllegalStateException("Not a JSON Array: " + i5Var);
                }
                Iterator<i5> it = ((g5) i5Var).iterator();
                while (it.hasNext()) {
                    b(v5Var, it.next());
                }
                v5Var.e();
                return;
            }
            boolean z2 = i5Var instanceof l5;
            if (!z2) {
                StringBuilder sbK = spi.c.k("Couldn't write ");
                sbK.append(i5Var.getClass());
                throw new IllegalArgumentException(sbK.toString());
            }
            v5Var.c();
            if (!z2) {
                throw new IllegalStateException("Not a JSON Object: " + i5Var);
            }
            y5.b.a aVar = new y5.b.a((y5.b) ((l5) i5Var).a.entrySet());
            while (aVar.hasNext()) {
                y5.e<K, V> eVarA = aVar.a();
                v5Var.g((String) eVarA.f);
                b(v5Var, (i5) eVarA.g);
            }
            v5Var.f();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class v extends nb<BitSet> {
        /* JADX WARN: Removed duplicated region for block: B:23:0x005d  */
        @Override // spi.nb
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.util.BitSet a(spi.o5 r7) throws java.io.IOException {
            /*
                r6 = this;
                java.util.BitSet r6 = new java.util.BitSet
                r6.<init>()
                r7.a()
                spi.s5 r0 = r7.v()
                r1 = 0
                r2 = r1
            Le:
                spi.s5 r3 = spi.s5.END_ARRAY
                if (r0 == r3) goto L6a
                int[] r3 = spi.qb.x.a
                int r4 = r0.ordinal()
                r3 = r3[r4]
                r4 = 1
                if (r3 == r4) goto L56
                r5 = 2
                if (r3 == r5) goto L51
                r5 = 3
                if (r3 != r5) goto L3a
                java.lang.String r0 = r7.t()
                int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> L2e
                if (r0 == 0) goto L5d
                goto L5e
            L2e:
                spi.r5 r6 = new spi.r5
                java.lang.String r7 = "Error: Expecting: bitset number value (1, 0), Found: "
                java.lang.String r7 = spi.c.f(r7, r0)
                r6.<init>(r7)
                throw r6
            L3a:
                spi.r5 r6 = new spi.r5
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                r7.<init>()
                java.lang.String r1 = "Invalid bitset value type: "
                r7.append(r1)
                r7.append(r0)
                java.lang.String r7 = r7.toString()
                r6.<init>(r7)
                throw r6
            L51:
                boolean r4 = r7.l()
                goto L5e
            L56:
                int r0 = r7.n()
                if (r0 == 0) goto L5d
                goto L5e
            L5d:
                r4 = r1
            L5e:
                if (r4 == 0) goto L63
                r6.set(r2)
            L63:
                int r2 = r2 + 1
                spi.s5 r0 = r7.v()
                goto Le
            L6a:
                r7.e()
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.qb.v.a(spi.o5):java.lang.Object");
        }

        @Override // spi.nb
        public final void b(v5 v5Var, BitSet bitSet) throws IOException {
            BitSet bitSet2 = bitSet;
            v5Var.b();
            int length = bitSet2.length();
            for (int i = 0; i < length; i++) {
                v5Var.n(bitSet2.get(i) ? 1L : 0L);
            }
            v5Var.e();
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class w implements ob {
        @Override // spi.ob
        public final <T> nb<T> a(j4 j4Var, vb<T> vbVar) {
            Class<? super T> superclass = vbVar.a;
            if (!Enum.class.isAssignableFrom(superclass) || superclass == Enum.class) {
                return null;
            }
            if (!superclass.isEnum()) {
                superclass = superclass.getSuperclass();
            }
            return new f0(superclass);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static /* synthetic */ class x {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[s5.values().length];
            a = iArr;
            try {
                iArr[s5.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[s5.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[s5.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[s5.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[s5.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[s5.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[s5.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[s5.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[s5.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[s5.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class y extends nb<Boolean> {
        @Override // spi.nb
        public final Boolean a(o5 o5Var) throws IOException {
            s5 s5VarV = o5Var.v();
            if (s5VarV != s5.NULL) {
                return s5VarV == s5.STRING ? Boolean.valueOf(Boolean.parseBoolean(o5Var.t())) : Boolean.valueOf(o5Var.l());
            }
            o5Var.r();
            return null;
        }

        @Override // spi.nb
        public final void b(v5 v5Var, Boolean bool) throws IOException {
            v5Var.o(bool);
        }
    }

    /* JADX INFO: compiled from: TypeAdapters.java */
    public static class z extends nb<Boolean> {
        @Override // spi.nb
        public final Boolean a(o5 o5Var) throws IOException {
            if (o5Var.v() != s5.NULL) {
                return Boolean.valueOf(o5Var.t());
            }
            o5Var.r();
            return null;
        }

        @Override // spi.nb
        public final void b(v5 v5Var, Boolean bool) throws IOException {
            Boolean bool2 = bool;
            v5Var.q(bool2 == null ? "null" : bool2.toString());
        }
    }

    static {
        mb mbVar = new mb(new k());
        a = mbVar;
        b = new rb(Class.class, mbVar);
        mb mbVar2 = new mb(new v());
        c = mbVar2;
        d = new rb(BitSet.class, mbVar2);
        y yVar = new y();
        e = yVar;
        f = new z();
        g = new sb(Boolean.TYPE, Boolean.class, yVar);
        a0 a0Var = new a0();
        h = a0Var;
        i = new sb(Byte.TYPE, Byte.class, a0Var);
        b0 b0Var = new b0();
        j = b0Var;
        k = new sb(Short.TYPE, Short.class, b0Var);
        c0 c0Var = new c0();
        l = c0Var;
        m = new sb(Integer.TYPE, Integer.class, c0Var);
        mb mbVar3 = new mb(new d0());
        n = mbVar3;
        o = new rb(AtomicInteger.class, mbVar3);
        mb mbVar4 = new mb(new e0());
        p = mbVar4;
        q = new rb(AtomicBoolean.class, mbVar4);
        mb mbVar5 = new mb(new a());
        r = mbVar5;
        s = new rb(AtomicIntegerArray.class, mbVar5);
        t = new b();
        u = new c();
        v = new d();
        e eVar = new e();
        w = eVar;
        x = new rb(Number.class, eVar);
        f fVar = new f();
        y = fVar;
        z = new sb(Character.TYPE, Character.class, fVar);
        g gVar = new g();
        A = gVar;
        B = new h();
        C = new i();
        D = new rb(String.class, gVar);
        j jVar = new j();
        E = jVar;
        F = new rb(StringBuilder.class, jVar);
        l lVar = new l();
        G = lVar;
        H = new rb(StringBuffer.class, lVar);
        m mVar = new m();
        I = mVar;
        J = new rb(URL.class, mVar);
        n nVar = new n();
        K = nVar;
        L = new rb(URI.class, nVar);
        o oVar = new o();
        M = oVar;
        N = new ub(InetAddress.class, oVar);
        p pVar = new p();
        O = pVar;
        P = new rb(UUID.class, pVar);
        mb mbVar6 = new mb(new q());
        Q = mbVar6;
        R = new rb(Currency.class, mbVar6);
        S = new r();
        s sVar = new s();
        T = sVar;
        U = new tb(Calendar.class, GregorianCalendar.class, sVar);
        t tVar = new t();
        V = tVar;
        W = new rb(Locale.class, tVar);
        u uVar = new u();
        X = uVar;
        Y = new ub(i5.class, uVar);
        Z = new w();
    }

    private qb() {
        throw new UnsupportedOperationException();
    }
}
