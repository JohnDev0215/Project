package p;

import com.google.protobuf.ByteString;
import com.google.protobuf.WireFormat$FieldType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class zt0 {
    public final ud2 a = new ud2(16);
    public boolean b;

    static {
        new zt0(0);
    }

    public zt0() {
    }

    public static int a(WireFormat$FieldType wireFormat$FieldType, int i, Object obj) {
        int iL = com.google.protobuf.h.L(i);
        if (wireFormat$FieldType == WireFormat$FieldType.k) {
            iL *= 2;
        }
        return b(wireFormat$FieldType, obj) + iL;
    }

    public static int b(WireFormat$FieldType wireFormat$FieldType, Object obj) {
        switch (wireFormat$FieldType.ordinal()) {
            case 0:
                ((Double) obj).doubleValue();
                Logger logger = com.google.protobuf.h.h;
                return 8;
            case 1:
                ((Float) obj).floatValue();
                Logger logger2 = com.google.protobuf.h.h;
                return 4;
            case 2:
                return com.google.protobuf.h.P(((Long) obj).longValue());
            case 3:
                return com.google.protobuf.h.P(((Long) obj).longValue());
            case 4:
                return com.google.protobuf.h.D(((Integer) obj).intValue());
            case 5:
                ((Long) obj).longValue();
                Logger logger3 = com.google.protobuf.h.h;
                return 8;
            case 6:
                ((Integer) obj).intValue();
                Logger logger4 = com.google.protobuf.h.h;
                return 4;
            case 7:
                ((Boolean) obj).booleanValue();
                Logger logger5 = com.google.protobuf.h.h;
                return 1;
            case 8:
                if (!(obj instanceof ByteString)) {
                    return com.google.protobuf.h.K((String) obj);
                }
                Logger logger6 = com.google.protobuf.h.h;
                int size = ((ByteString) obj).size();
                return com.google.protobuf.h.N(size) + size;
            case 9:
                Logger logger7 = com.google.protobuf.h.h;
                return ((wj1) obj).getSerializedSize();
            case 10:
                Logger logger8 = com.google.protobuf.h.h;
                int serializedSize = ((wj1) obj).getSerializedSize();
                return com.google.protobuf.h.N(serializedSize) + serializedSize;
            case 11:
                if (obj instanceof ByteString) {
                    Logger logger9 = com.google.protobuf.h.h;
                    int size2 = ((ByteString) obj).size();
                    return com.google.protobuf.h.N(size2) + size2;
                }
                Logger logger10 = com.google.protobuf.h.h;
                int length = ((byte[]) obj).length;
                return com.google.protobuf.h.N(length) + length;
            case 12:
                return com.google.protobuf.h.N(((Integer) obj).intValue());
            case 13:
                return obj instanceof n51 ? com.google.protobuf.h.D(((n51) obj).getNumber()) : com.google.protobuf.h.D(((Integer) obj).intValue());
            case 14:
                ((Integer) obj).intValue();
                Logger logger11 = com.google.protobuf.h.h;
                return 4;
            case 15:
                ((Long) obj).longValue();
                Logger logger12 = com.google.protobuf.h.h;
                return 8;
            case 16:
                int iIntValue = ((Integer) obj).intValue();
                return com.google.protobuf.h.N((iIntValue >> 31) ^ (iIntValue << 1));
            case 17:
                long jLongValue = ((Long) obj).longValue();
                return com.google.protobuf.h.P((jLongValue >> 63) ^ (jLongValue << 1));
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void e(p.j01 r2, java.lang.Object r3) {
        /*
            com.google.protobuf.WireFormat$FieldType r0 = r2.e
            java.nio.charset.Charset r1 = p.w51.a
            r3.getClass()
            com.google.protobuf.WireFormat$JavaType r0 = r0.a()
            int r0 = r0.ordinal()
            switch(r0) {
                case 0: goto L37;
                case 1: goto L34;
                case 2: goto L31;
                case 3: goto L2e;
                case 4: goto L2b;
                case 5: goto L28;
                case 6: goto L1f;
                case 7: goto L16;
                case 8: goto L13;
                default: goto L12;
            }
        L12:
            goto L3c
        L13:
            boolean r0 = r3 instanceof p.wj1
            goto L39
        L16:
            boolean r0 = r3 instanceof java.lang.Integer
            if (r0 != 0) goto L3b
            boolean r0 = r3 instanceof p.n51
            if (r0 == 0) goto L3c
            goto L3b
        L1f:
            boolean r0 = r3 instanceof com.google.protobuf.ByteString
            if (r0 != 0) goto L3b
            boolean r0 = r3 instanceof byte[]
            if (r0 == 0) goto L3c
            goto L3b
        L28:
            boolean r0 = r3 instanceof java.lang.String
            goto L39
        L2b:
            boolean r0 = r3 instanceof java.lang.Boolean
            goto L39
        L2e:
            boolean r0 = r3 instanceof java.lang.Double
            goto L39
        L31:
            boolean r0 = r3 instanceof java.lang.Float
            goto L39
        L34:
            boolean r0 = r3 instanceof java.lang.Long
            goto L39
        L37:
            boolean r0 = r3 instanceof java.lang.Integer
        L39:
            if (r0 == 0) goto L3c
        L3b:
            return
        L3c:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            int r1 = r2.c
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            com.google.protobuf.WireFormat$FieldType r2 = r2.e
            com.google.protobuf.WireFormat$JavaType r2 = r2.a()
            java.lang.Class r3 = r3.getClass()
            java.lang.String r3 = r3.getName()
            java.lang.Object[] r2 = new java.lang.Object[]{r1, r2, r3}
            java.lang.String r3 = "Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n"
            java.lang.String r2 = java.lang.String.format(r3, r2)
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p.zt0.e(p.j01, java.lang.Object):void");
    }

    public static void f(com.google.protobuf.h hVar, WireFormat$FieldType wireFormat$FieldType, int i, Object obj) {
        if (wireFormat$FieldType == WireFormat$FieldType.k) {
            hVar.g0(i, 3);
            ((wj1) obj).writeTo(hVar);
            hVar.g0(i, 4);
            return;
        }
        hVar.g0(i, wireFormat$FieldType.b());
        switch (wireFormat$FieldType.ordinal()) {
            case 0:
                hVar.Z(Double.doubleToRawLongBits(((Double) obj).doubleValue()));
                break;
            case 1:
                hVar.X(Float.floatToRawIntBits(((Float) obj).floatValue()));
                break;
            case 2:
                hVar.k0(((Long) obj).longValue());
                break;
            case 3:
                hVar.k0(((Long) obj).longValue());
                break;
            case 4:
                hVar.b0(((Integer) obj).intValue());
                break;
            case 5:
                hVar.Z(((Long) obj).longValue());
                break;
            case 6:
                hVar.X(((Integer) obj).intValue());
                break;
            case 7:
                hVar.R(((Boolean) obj).booleanValue() ? (byte) 1 : (byte) 0);
                break;
            case 8:
                if (!(obj instanceof ByteString)) {
                    hVar.f0((String) obj);
                } else {
                    hVar.V((ByteString) obj);
                }
                break;
            case 9:
                ((wj1) obj).writeTo(hVar);
                break;
            case 10:
                hVar.d0((wj1) obj);
                break;
            case 11:
                if (!(obj instanceof ByteString)) {
                    byte[] bArr = (byte[]) obj;
                    hVar.T(bArr, bArr.length);
                } else {
                    hVar.V((ByteString) obj);
                }
                break;
            case 12:
                hVar.i0(((Integer) obj).intValue());
                break;
            case 13:
                if (!(obj instanceof n51)) {
                    hVar.b0(((Integer) obj).intValue());
                } else {
                    hVar.b0(((n51) obj).getNumber());
                }
                break;
            case 14:
                hVar.X(((Integer) obj).intValue());
                break;
            case 15:
                hVar.Z(((Long) obj).longValue());
                break;
            case 16:
                int iIntValue = ((Integer) obj).intValue();
                hVar.i0((iIntValue >> 31) ^ (iIntValue << 1));
                break;
            case 17:
                long jLongValue = ((Long) obj).longValue();
                hVar.k0((jLongValue >> 63) ^ (jLongValue << 1));
                break;
        }
    }

    public final void c() {
        ud2 ud2Var;
        if (this.b) {
            return;
        }
        int i = 0;
        while (true) {
            ud2Var = this.a;
            if (i >= ud2Var.e.size()) {
                break;
            }
            Map.Entry entryH = ud2Var.h(i);
            if (entryH.getValue() instanceof com.google.protobuf.i) {
                ((com.google.protobuf.i) entryH.getValue()).makeImmutable();
            }
            i++;
        }
        if (!ud2Var.l) {
            for (int i2 = 0; i2 < ud2Var.e.size(); i2++) {
                Map.Entry entryH2 = ud2Var.h(i2);
                if (((j01) entryH2.getKey()).k) {
                    entryH2.setValue(Collections.unmodifiableList((List) entryH2.getValue()));
                }
            }
            for (Map.Entry entry : ud2Var.i()) {
                if (((j01) entry.getKey()).k) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        if (!ud2Var.l) {
            ud2Var.k = ud2Var.k.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(ud2Var.k);
            ud2Var.n = ud2Var.n.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(ud2Var.n);
            ud2Var.l = true;
        }
        this.b = true;
    }

    public final Object clone() {
        ud2 ud2Var;
        zt0 zt0Var = new zt0();
        int i = 0;
        while (true) {
            ud2Var = this.a;
            if (i >= ud2Var.e.size()) {
                break;
            }
            Map.Entry entryH = ud2Var.h(i);
            zt0Var.d((j01) entryH.getKey(), entryH.getValue());
            i++;
        }
        for (Map.Entry entry : ud2Var.i()) {
            zt0Var.d((j01) entry.getKey(), entry.getValue());
        }
        return zt0Var;
    }

    public final void d(j01 j01Var, Object obj) {
        if (!j01Var.k) {
            e(j01Var, obj);
        } else {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                e(j01Var, it.next());
            }
            obj = arrayList;
        }
        this.a.put(j01Var, obj);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zt0)) {
            return false;
        }
        return this.a.equals(((zt0) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public zt0(int i) {
        c();
        c();
    }
}
