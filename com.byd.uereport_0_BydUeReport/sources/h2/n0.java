package h2;

import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/* JADX INFO: compiled from: ListSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public final class n0 implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final n0 f5290a = new n0();

    @Override // h2.t0
    public final void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        char c8;
        int i9;
        Object obj3;
        boolean z7;
        char c9;
        d1 d1Var = i0Var.f5241k;
        SerializerFeature serializerFeature = SerializerFeature.WriteClassName;
        boolean z8 = d1Var.t(serializerFeature) || SerializerFeature.isEnabled(i8, serializerFeature);
        d1 d1Var2 = i0Var.f5241k;
        Type typeU = z8 ? l2.l.U(type) : null;
        if (obj == null) {
            d1Var2.c0(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        List list = (List) obj;
        if (list.size() == 0) {
            d1Var2.append("[]");
            return;
        }
        y0 y0Var = i0Var.f5248r;
        i0Var.D(y0Var, obj, obj2, 0);
        try {
            char c10 = ',';
            char c11 = ']';
            if (d1Var2.t(SerializerFeature.PrettyFormat)) {
                d1Var2.append('[');
                i0Var.z();
                int i10 = 0;
                for (Object obj4 : list) {
                    if (i10 != 0) {
                        d1Var2.append(c10);
                    }
                    i0Var.C();
                    if (obj4 == null) {
                        c9 = c11;
                        i0Var.f5241k.Z();
                    } else if (i0Var.r(obj4)) {
                        i0Var.K(obj4);
                        c9 = c11;
                    } else {
                        t0 t0VarX = i0Var.x(obj4.getClass());
                        c9 = c11;
                        i0Var.f5248r = new y0(y0Var, obj, obj2, 0, 0);
                        t0VarX.c(i0Var, obj4, Integer.valueOf(i10), typeU, i8);
                    }
                    i10++;
                    c11 = c9;
                    c10 = ',';
                }
                i0Var.s();
                i0Var.C();
                d1Var2.append(c11);
                return;
            }
            char c12 = ']';
            d1Var2.append('[');
            int size = list.size();
            int i11 = 0;
            while (i11 < size) {
                Object obj5 = list.get(i11);
                if (i11 != 0) {
                    c8 = ',';
                    d1Var2.append(',');
                } else {
                    c8 = ',';
                }
                if (obj5 == null) {
                    d1Var2.append("null");
                } else {
                    Class<?> cls = obj5.getClass();
                    if (cls == Integer.class) {
                        d1Var2.V(((Integer) obj5).intValue());
                    } else if (cls == Long.class) {
                        long jLongValue = ((Long) obj5).longValue();
                        if (z8) {
                            d1Var2.Y(jLongValue);
                            d1Var2.write(76);
                        } else {
                            d1Var2.Y(jLongValue);
                        }
                    } else if ((SerializerFeature.DisableCircularReferenceDetect.mask & i8) != 0) {
                        i9 = i11;
                        i0Var.x(obj5.getClass()).c(i0Var, obj5, Integer.valueOf(i11), typeU, i8);
                        z7 = z8;
                        i11 = i9 + 1;
                        z8 = z7;
                        c12 = ']';
                    } else {
                        i9 = i11;
                        if (d1Var2.f5220q) {
                            obj3 = obj5;
                            z7 = z8;
                        } else {
                            obj3 = obj5;
                            z7 = z8;
                            i0Var.f5248r = new y0(y0Var, obj, obj2, 0, 0);
                        }
                        if (i0Var.r(obj3)) {
                            i0Var.K(obj3);
                        } else {
                            t0 t0VarX2 = i0Var.x(obj3.getClass());
                            if ((SerializerFeature.WriteClassName.mask & i8) == 0 || !(t0VarX2 instanceof j0)) {
                                t0VarX2.c(i0Var, obj3, Integer.valueOf(i9), typeU, i8);
                            } else {
                                ((j0) t0VarX2).D(i0Var, obj3, Integer.valueOf(i9), typeU, i8);
                            }
                        }
                        i11 = i9 + 1;
                        z8 = z7;
                        c12 = ']';
                    }
                }
                i9 = i11;
                z7 = z8;
                i11 = i9 + 1;
                z8 = z7;
                c12 = ']';
            }
            d1Var2.append(c12);
        } finally {
            i0Var.f5248r = y0Var;
        }
    }
}
