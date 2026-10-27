package kotlin.collections;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import p.bk0;
import p.fb;
import p.he1;
import p.i41;
import p.i51;
import p.py0;
import p.rb;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class b extends rb {
    public static boolean p0(Object[] objArr, Object obj) {
        v03.j(objArr, "<this>");
        return r0(objArr, obj) >= 0;
    }

    public static ArrayList q0(Object[] objArr) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : objArr) {
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static int r0(Object[] objArr, Object obj) {
        v03.j(objArr, "<this>");
        int i = 0;
        if (obj == null) {
            int length = objArr.length;
            while (i < length) {
                if (objArr[i] == null) {
                    return i;
                }
                i++;
            }
            return -1;
        }
        int length2 = objArr.length;
        while (i < length2) {
            if (v03.b(obj, objArr[i])) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static String s0(Object[] objArr) {
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) "");
        int i = 0;
        for (Object obj : objArr) {
            i++;
            if (i > 1) {
                sb.append((CharSequence) ", ");
            }
            v03.a(sb, obj, null);
        }
        sb.append((CharSequence) "");
        String string = sb.toString();
        v03.i(string, "joinTo(StringBuilder(), …ed, transform).toString()");
        return string;
    }

    public static char t0(char[] cArr) {
        v03.j(cArr, "<this>");
        int length = cArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        if (length == 1) {
            return cArr[0];
        }
        throw new IllegalArgumentException("Array has more than one element.");
    }

    public static byte[] u0(byte[] bArr, i51 i51Var) {
        v03.j(bArr, "<this>");
        return i51Var.isEmpty() ? new byte[0] : rb.m0(bArr, Integer.valueOf(i51Var.c).intValue(), Integer.valueOf(i51Var.e).intValue() + 1);
    }

    public static final void v0(AbstractSet abstractSet, Object[] objArr) {
        for (Object obj : objArr) {
            abstractSet.add(obj);
        }
    }

    public static List w0(Object[] objArr) {
        v03.j(objArr, "<this>");
        int length = objArr.length;
        return length != 0 ? length != 1 ? new ArrayList(new fb(objArr, false)) : v03.B(objArr[0]) : EmptyList.c;
    }

    public static Set x0(Object[] objArr) {
        int length = objArr.length;
        if (length == 0) {
            return EmptySet.c;
        }
        if (length != 1) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(he1.B(objArr.length));
            v0(linkedHashSet, objArr);
            return linkedHashSet;
        }
        Set setSingleton = Collections.singleton(objArr[0]);
        v03.i(setSingleton, "singleton(element)");
        return setSingleton;
    }

    public static i41 y0(final Object[] objArr) {
        v03.j(objArr, "<this>");
        return new i41(new py0() { // from class: kotlin.collections.ArraysKt___ArraysKt$withIndex$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // p.py0
            public final Object invoke() {
                return bk0.K(objArr);
            }
        });
    }
}
