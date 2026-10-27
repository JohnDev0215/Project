package okio.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Pair;
import kotlin.collections.c;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlin.jvm.internal.Ref$LongRef;
import kotlin.jvm.internal.Ref$ObjectRef;
import p.bk0;
import p.fo;
import p.fz0;
import p.he1;
import p.ko;
import p.lq2;
import p.mz2;
import p.ng2;
import p.os1;
import p.ou;
import p.p00;
import p.rb;
import p.u11;
import p.u4;
import p.v03;
import p.w02;
import p.x50;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {
    public static final LinkedHashMap a(ArrayList arrayList) {
        List<mz2> listH0;
        String str = os1.e;
        os1 os1VarZ = u11.z("/", false);
        Pair[] pairArr = {new Pair(os1VarZ, new mz2(os1VarZ))};
        LinkedHashMap linkedHashMap = new LinkedHashMap(he1.B(1));
        c.j0(linkedHashMap, pairArr);
        p00 p00Var = new p00(3);
        if (arrayList.size() <= 1) {
            listH0 = ou.n0(arrayList);
        } else {
            Object[] array = arrayList.toArray(new Object[0]);
            v03.j(array, "<this>");
            if (array.length > 1) {
                Arrays.sort(array, p00Var);
            }
            listH0 = rb.h0(array);
        }
        for (mz2 mz2Var : listH0) {
            if (((mz2) linkedHashMap.put(mz2Var.a, mz2Var)) == null) {
                while (true) {
                    os1 os1Var = mz2Var.a;
                    os1 os1VarB = os1Var.b();
                    if (os1VarB != null) {
                        mz2 mz2Var2 = (mz2) linkedHashMap.get(os1VarB);
                        if (mz2Var2 != null) {
                            mz2Var2.h.add(os1Var);
                            break;
                        }
                        mz2 mz2Var3 = new mz2(os1VarB);
                        linkedHashMap.put(os1VarB, mz2Var3);
                        mz2Var3.h.add(os1Var);
                        mz2Var = mz2Var3;
                    }
                }
            }
        }
        return linkedHashMap;
    }

    public static final String b(int i) {
        bk0.k(16);
        String string = Integer.toString(i, 16);
        v03.i(string, "toString(...)");
        return "0x".concat(string);
    }

    public static final mz2 c(final w02 w02Var) throws IOException {
        Long lValueOf;
        int i;
        long j;
        int iO = w02Var.O();
        if (iO != 33639248) {
            throw new IOException("bad zip: expected " + b(33639248) + " but was " + b(iO));
        }
        w02Var.y(4L);
        short sI = w02Var.i();
        int i2 = sI & 65535;
        if ((sI & 1) != 0) {
            throw new IOException("unsupported zip: general purpose bit flag=" + b(i2));
        }
        int i3 = w02Var.i() & 65535;
        short sI2 = w02Var.i();
        int i4 = sI2 & 65535;
        short sI3 = w02Var.i();
        int i5 = sI3 & 65535;
        if (i4 == -1) {
            lValueOf = null;
        } else {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.set(14, 0);
            gregorianCalendar.set(((i5 >> 9) & 127) + 1980, ((i5 >> 5) & 15) - 1, sI3 & 31, (i4 >> 11) & 31, (i4 >> 5) & 63, (sI2 & 31) << 1);
            lValueOf = Long.valueOf(gregorianCalendar.getTime().getTime());
        }
        Long l = lValueOf;
        w02Var.O();
        final Ref$LongRef ref$LongRef = new Ref$LongRef();
        ref$LongRef.element = ((long) w02Var.O()) & 4294967295L;
        final Ref$LongRef ref$LongRef2 = new Ref$LongRef();
        ref$LongRef2.element = ((long) w02Var.O()) & 4294967295L;
        int i6 = w02Var.i() & 65535;
        int i7 = w02Var.i() & 65535;
        int i8 = w02Var.i() & 65535;
        w02Var.y(8L);
        final Ref$LongRef ref$LongRef3 = new Ref$LongRef();
        ref$LongRef3.element = ((long) w02Var.O()) & 4294967295L;
        String strQ = w02Var.q(i6);
        if (kotlin.text.b.h0(strQ, (char) 0)) {
            throw new IOException("bad zip: filename contains 0x00");
        }
        if (ref$LongRef2.element == 4294967295L) {
            j = 8;
            i = i3;
        } else {
            i = i3;
            j = 0;
        }
        if (ref$LongRef.element == 4294967295L) {
            j += (long) 8;
        }
        if (ref$LongRef3.element == 4294967295L) {
            j += (long) 8;
        }
        final long j2 = j;
        final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
        d(w02Var, i7, new fz0() { // from class: okio.internal.ZipFilesKt$readEntry$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // p.fz0
            public final Object g(Object obj, Object obj2) throws IOException {
                int iIntValue = ((Number) obj).intValue();
                long jLongValue = ((Number) obj2).longValue();
                if (iIntValue == 1) {
                    Ref$BooleanRef ref$BooleanRef2 = ref$BooleanRef;
                    if (ref$BooleanRef2.element) {
                        throw new IOException("bad zip: zip64 extra repeated");
                    }
                    ref$BooleanRef2.element = true;
                    if (jLongValue < j2) {
                        throw new IOException("bad zip: zip64 extra too short");
                    }
                    Ref$LongRef ref$LongRef4 = ref$LongRef2;
                    long jF0 = ref$LongRef4.element;
                    if (jF0 == 4294967295L) {
                        jF0 = w02Var.f0();
                    }
                    ref$LongRef4.element = jF0;
                    Ref$LongRef ref$LongRef5 = ref$LongRef;
                    ref$LongRef5.element = ref$LongRef5.element == 4294967295L ? w02Var.f0() : 0L;
                    Ref$LongRef ref$LongRef6 = ref$LongRef3;
                    ref$LongRef6.element = ref$LongRef6.element == 4294967295L ? w02Var.f0() : 0L;
                }
                return lq2.a;
            }
        });
        if (j2 > 0 && !ref$BooleanRef.element) {
            throw new IOException("bad zip: zip64 extra required but absent");
        }
        String strQ2 = w02Var.q(i8);
        String str = os1.e;
        return new mz2(u11.z("/", false).d(strQ), ng2.a0(strQ, "/", false), strQ2, ref$LongRef.element, ref$LongRef2.element, i, l, ref$LongRef3.element);
    }

    public static final void d(w02 w02Var, int i, fz0 fz0Var) throws IOException {
        long j = i;
        while (j != 0) {
            if (j < 4) {
                throw new IOException("bad zip: truncated header in extra field");
            }
            int i2 = w02Var.i() & 65535;
            long jI = ((long) w02Var.i()) & 65535;
            long j2 = j - ((long) 4);
            if (j2 < jI) {
                throw new IOException("bad zip: truncated value in extra field");
            }
            w02Var.q0(jI);
            fo foVar = w02Var.e;
            long j3 = foVar.e;
            fz0Var.g(Integer.valueOf(i2), Long.valueOf(jI));
            long j4 = (foVar.e + jI) - j3;
            if (j4 < 0) {
                throw new IOException(u4.e("unsupported zip: too many bytes processed for ", i2));
            }
            if (j4 > 0) {
                foVar.y(j4);
            }
            j = j2 - jI;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final x50 e(final w02 w02Var, x50 x50Var) throws IOException {
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        ref$ObjectRef.element = x50Var != null ? (Long) x50Var.g : 0;
        final Ref$ObjectRef ref$ObjectRef2 = new Ref$ObjectRef();
        final Ref$ObjectRef ref$ObjectRef3 = new Ref$ObjectRef();
        int iO = w02Var.O();
        if (iO != 67324752) {
            throw new IOException("bad zip: expected " + b(67324752) + " but was " + b(iO));
        }
        w02Var.y(2L);
        short sI = w02Var.i();
        int i = sI & 65535;
        if ((sI & 1) != 0) {
            throw new IOException("unsupported zip: general purpose bit flag=" + b(i));
        }
        w02Var.y(18L);
        long jI = ((long) w02Var.i()) & 65535;
        int i2 = w02Var.i() & 65535;
        w02Var.y(jI);
        if (x50Var == null) {
            w02Var.y(i2);
            return null;
        }
        d(w02Var, i2, new fz0() { // from class: okio.internal.ZipFilesKt$readOrSkipLocalHeader$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            /* JADX WARN: Type inference failed for: r11v12, types: [T, java.lang.Long] */
            /* JADX WARN: Type inference failed for: r1v12, types: [T, java.lang.Long] */
            /* JADX WARN: Type inference failed for: r1v8, types: [T, java.lang.Long] */
            @Override // p.fz0
            public final Object g(Object obj, Object obj2) throws IOException {
                int iIntValue = ((Number) obj).intValue();
                long jLongValue = ((Number) obj2).longValue();
                if (iIntValue == 21589) {
                    if (jLongValue < 1) {
                        throw new IOException("bad zip: extended timestamp extra too short");
                    }
                    byte b = w02Var.readByte();
                    boolean z = (b & 1) == 1;
                    boolean z2 = (b & 2) == 2;
                    boolean z3 = (b & 4) == 4;
                    ko koVar = w02Var;
                    long j = z ? 5L : 1L;
                    if (z2) {
                        j += 4;
                    }
                    if (z3) {
                        j += 4;
                    }
                    if (jLongValue < j) {
                        throw new IOException("bad zip: extended timestamp extra too short");
                    }
                    if (z) {
                        ref$ObjectRef.element = Long.valueOf(((long) koVar.O()) * 1000);
                    }
                    if (z2) {
                        ref$ObjectRef2.element = Long.valueOf(((long) w02Var.O()) * 1000);
                    }
                    if (z3) {
                        ref$ObjectRef3.element = Long.valueOf(((long) w02Var.O()) * 1000);
                    }
                }
                return lq2.a;
            }
        });
        return new x50(x50Var.b, x50Var.c, null, (Long) x50Var.e, (Long) ref$ObjectRef3.element, (Long) ref$ObjectRef.element, (Long) ref$ObjectRef2.element);
    }
}
