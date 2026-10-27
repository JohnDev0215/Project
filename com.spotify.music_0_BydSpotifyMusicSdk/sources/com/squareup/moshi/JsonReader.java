package com.squareup.moshi;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import okio.ByteString;
import p.ar1;
import p.fo;
import p.hf2;
import p.ko;
import p.qo;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
public abstract class JsonReader implements Closeable {
    int c;
    int[] e;
    String[] k;
    int[] l;
    boolean m;
    boolean n;

    public enum Token {
        BEGIN_ARRAY,
        END_ARRAY,
        BEGIN_OBJECT,
        END_OBJECT,
        NAME,
        STRING,
        NUMBER,
        BOOLEAN,
        NULL,
        END_DOCUMENT
    }

    public static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[Token.values().length];
            a = iArr;
            try {
                iArr[Token.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[Token.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[Token.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[Token.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[Token.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[Token.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public static final class b {
        final String[] a;
        final ar1 b;

        private b(String[] strArr, ar1 ar1Var) {
            this.a = strArr;
            this.b = ar1Var;
        }

        public static b a(String... strArr) {
            try {
                ByteString[] byteStringArr = new ByteString[strArr.length];
                fo foVar = new fo();
                for (int i = 0; i < strArr.length; i++) {
                    i.d1(foVar, strArr[i]);
                    foVar.readByte();
                    byteStringArr[i] = foVar.v(foVar.e);
                }
                return new b((String[]) strArr.clone(), qo.r(byteStringArr));
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }
    }

    public JsonReader() {
        this.e = new int[32];
        this.k = new String[32];
        this.l = new int[32];
    }

    public static JsonReader G0(ko koVar) {
        return new h(koVar);
    }

    public abstract void D();

    public abstract int D0();

    public final boolean E() {
        return this.n;
    }

    public abstract ko E0();

    public abstract Token H0();

    public abstract JsonReader I0();

    public final String J() {
        return g.a(this.c, this.e, this.k, this.l);
    }

    public abstract void J0();

    public final void K0(int i) {
        int i2 = this.c;
        int[] iArr = this.e;
        if (i2 == iArr.length) {
            if (i2 == 256) {
                throw new JsonDataException("Nesting too deep at " + J());
            }
            this.e = Arrays.copyOf(iArr, iArr.length * 2);
            String[] strArr = this.k;
            this.k = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
            int[] iArr2 = this.l;
            this.l = Arrays.copyOf(iArr2, iArr2.length * 2);
        }
        int[] iArr3 = this.e;
        int i3 = this.c;
        this.c = i3 + 1;
        iArr3[i3] = i;
    }

    public final Object L0() {
        switch (a.a[H0().ordinal()]) {
            case 1:
                ArrayList arrayList = new ArrayList();
                b();
                while (hasNext()) {
                    arrayList.add(L0());
                }
                i();
                return arrayList;
            case 2:
                LinkedHashTreeMap linkedHashTreeMap = new LinkedHashTreeMap();
                c();
                while (hasNext()) {
                    String strR0 = r0();
                    Object objL0 = L0();
                    Object objPut = linkedHashTreeMap.put(strR0, objL0);
                    if (objPut != null) {
                        StringBuilder sbP = u4.p("Map key '", strR0, "' has multiple values at path ");
                        sbP.append(J());
                        sbP.append(": ");
                        sbP.append(objPut);
                        sbP.append(" and ");
                        sbP.append(objL0);
                        throw new JsonDataException(sbP.toString());
                    }
                }
                q();
                return linkedHashTreeMap;
            case 3:
                return u();
            case 4:
                return Double.valueOf(T());
            case 5:
                return Boolean.valueOf(w0());
            case 6:
                return o0();
            default:
                throw new IllegalStateException("Expected a value but was " + H0() + " at path " + J());
        }
    }

    public abstract int M0(b bVar);

    public abstract int N0(b bVar);

    public final void O0(boolean z) {
        this.n = z;
    }

    public final void P0(boolean z) {
        this.m = z;
    }

    public abstract void Q0();

    public final JsonEncodingException R0(String str) throws JsonEncodingException {
        StringBuilder sbF = hf2.f(str, " at path ");
        sbF.append(J());
        throw new JsonEncodingException(sbF.toString());
    }

    public final JsonDataException S0(Object obj, Object obj2) {
        if (obj == null) {
            return new JsonDataException("Expected " + obj2 + " but was null at path " + J());
        }
        return new JsonDataException("Expected " + obj2 + " but was " + obj + ", a " + obj.getClass().getName() + ", at path " + J());
    }

    public abstract double T();

    public final boolean X() {
        return this.m;
    }

    public abstract long Z();

    public abstract void b();

    public abstract void c();

    public abstract boolean hasNext();

    public abstract void i();

    public abstract <T> T o0();

    public abstract void q();

    public abstract String r0();

    public abstract String u();

    public abstract boolean w0();

    public JsonReader(JsonReader jsonReader) {
        this.c = jsonReader.c;
        this.e = (int[]) jsonReader.e.clone();
        this.k = (String[]) jsonReader.k.clone();
        this.l = (int[]) jsonReader.l.clone();
        this.m = jsonReader.m;
        this.n = jsonReader.n;
    }
}
