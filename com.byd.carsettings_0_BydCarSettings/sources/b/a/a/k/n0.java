package b.a.a.k;

import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/* JADX INFO: compiled from: ListSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public final class n0 implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final n0 f389a = new n0();

    @Override // b.a.a.k.t0
    public final void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        char c2;
        int i2;
        Object obj3;
        boolean z;
        char c3;
        d1 d1Var = i0Var.k;
        SerializerFeature serializerFeature = SerializerFeature.WriteClassName;
        boolean z2 = d1Var.l(serializerFeature) || SerializerFeature.isEnabled(i, serializerFeature);
        d1 d1Var2 = i0Var.k;
        Type typeU = z2 ? b.a.a.n.l.U(type) : null;
        if (obj == null) {
            d1Var2.M(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        List list = (List) obj;
        if (list.size() == 0) {
            d1Var2.append("[]");
            return;
        }
        y0 y0Var = i0Var.r;
        i0Var.D(y0Var, obj, obj2, 0);
        try {
            char c4 = ',';
            char c5 = ']';
            if (d1Var2.l(SerializerFeature.PrettyFormat)) {
                d1Var2.append('[');
                i0Var.z();
                int i3 = 0;
                for (Object obj4 : list) {
                    if (i3 != 0) {
                        d1Var2.append(c4);
                    }
                    i0Var.C();
                    if (obj4 == null) {
                        c3 = c5;
                        i0Var.k.J();
                    } else if (i0Var.r(obj4)) {
                        i0Var.K(obj4);
                        c3 = c5;
                    } else {
                        t0 t0VarX = i0Var.x(obj4.getClass());
                        c3 = c5;
                        i0Var.r = new y0(y0Var, obj, obj2, 0, 0);
                        t0VarX.c(i0Var, obj4, Integer.valueOf(i3), typeU, i);
                    }
                    i3++;
                    c5 = c3;
                    c4 = ',';
                }
                i0Var.s();
                i0Var.C();
                d1Var2.append(c5);
                return;
            }
            char c6 = ']';
            d1Var2.append('[');
            int size = list.size();
            int i4 = 0;
            while (i4 < size) {
                Object obj5 = list.get(i4);
                if (i4 != 0) {
                    c2 = ',';
                    d1Var2.append(',');
                } else {
                    c2 = ',';
                }
                if (obj5 == null) {
                    d1Var2.append("null");
                } else {
                    Class<?> cls = obj5.getClass();
                    if (cls == Integer.class) {
                        d1Var2.F(((Integer) obj5).intValue());
                    } else if (cls == Long.class) {
                        long jLongValue = ((Long) obj5).longValue();
                        if (z2) {
                            d1Var2.H(jLongValue);
                            d1Var2.write(76);
                        } else {
                            d1Var2.H(jLongValue);
                        }
                    } else if ((SerializerFeature.DisableCircularReferenceDetect.mask & i) != 0) {
                        i2 = i4;
                        i0Var.x(obj5.getClass()).c(i0Var, obj5, Integer.valueOf(i4), typeU, i);
                        z = z2;
                        i4 = i2 + 1;
                        z2 = z;
                        c6 = ']';
                    } else {
                        i2 = i4;
                        if (d1Var2.n) {
                            obj3 = obj5;
                            z = z2;
                        } else {
                            obj3 = obj5;
                            z = z2;
                            i0Var.r = new y0(y0Var, obj, obj2, 0, 0);
                        }
                        if (i0Var.r(obj3)) {
                            i0Var.K(obj3);
                        } else {
                            t0 t0VarX2 = i0Var.x(obj3.getClass());
                            if ((SerializerFeature.WriteClassName.mask & i) == 0 || !(t0VarX2 instanceof j0)) {
                                t0VarX2.c(i0Var, obj3, Integer.valueOf(i2), typeU, i);
                            } else {
                                ((j0) t0VarX2).D(i0Var, obj3, Integer.valueOf(i2), typeU, i);
                            }
                        }
                        i4 = i2 + 1;
                        z2 = z;
                        c6 = ']';
                    }
                }
                i2 = i4;
                z = z2;
                i4 = i2 + 1;
                z2 = z;
                c6 = ']';
            }
            d1Var2.append(c6);
        } finally {
            i0Var.r = y0Var;
        }
    }
}
