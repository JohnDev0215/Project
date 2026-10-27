package b.a.a.k;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* JADX INFO: compiled from: ObjectArrayCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class s0 implements t0, b.a.a.j.j.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final s0 f403a = new s0();

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v1, types: [T, byte[]] */
    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        Type componentType;
        Class clsP;
        b.a.a.j.b bVar = aVar.g;
        int iJ = bVar.j();
        Type type2 = null;
        if (iJ == 8) {
            bVar.F(16);
            return null;
        }
        if (iJ == 4 || iJ == 26) {
            ?? r8 = (T) bVar.O();
            bVar.F(16);
            if (r8.length != 0 || type == byte[].class) {
                return r8;
            }
            return null;
        }
        if (type instanceof GenericArrayType) {
            componentType = ((GenericArrayType) type).getGenericComponentType();
            if (componentType instanceof TypeVariable) {
                TypeVariable typeVariable = (TypeVariable) componentType;
                Type type3 = aVar.j().f249e;
                if (type3 instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type3;
                    Type rawType = parameterizedType.getRawType();
                    if (rawType instanceof Class) {
                        TypeVariable<Class<T>>[] typeParameters = ((Class) rawType).getTypeParameters();
                        for (int i = 0; i < typeParameters.length; i++) {
                            if (typeParameters[i].getName().equals(typeVariable.getName())) {
                                type2 = parameterizedType.getActualTypeArguments()[i];
                            }
                        }
                    }
                    clsP = type2 instanceof Class ? (Class) type2 : Object.class;
                } else {
                    clsP = b.a.a.n.l.P(typeVariable.getBounds()[0]);
                }
            } else {
                clsP = b.a.a.n.l.P(componentType);
            }
        } else {
            componentType = ((Class) type).getComponentType();
            clsP = componentType;
        }
        JSONArray jSONArray = new JSONArray();
        aVar.F(componentType, jSONArray, obj);
        return (T) f(aVar, clsP, jSONArray);
    }

    @Override // b.a.a.k.t0
    public final void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        d1 d1Var = i0Var.k;
        Object[] objArr = (Object[]) obj;
        if (obj == null) {
            d1Var.M(SerializerFeature.WriteNullListAsEmpty);
            return;
        }
        int length = objArr.length;
        int i2 = length - 1;
        if (i2 == -1) {
            d1Var.append("[]");
            return;
        }
        y0 y0Var = i0Var.r;
        i0Var.D(y0Var, obj, obj2, 0);
        try {
            d1Var.append('[');
            if (d1Var.l(SerializerFeature.PrettyFormat)) {
                i0Var.z();
                i0Var.C();
                for (int i3 = 0; i3 < length; i3++) {
                    if (i3 != 0) {
                        d1Var.write(44);
                        i0Var.C();
                    }
                    i0Var.H(objArr[i3]);
                }
                i0Var.s();
                i0Var.C();
                d1Var.write(93);
                return;
            }
            Class<?> cls = null;
            t0 t0VarX = null;
            for (int i4 = 0; i4 < i2; i4++) {
                Object obj3 = objArr[i4];
                if (obj3 == null) {
                    d1Var.append("null,");
                } else {
                    if (i0Var.r(obj3)) {
                        i0Var.K(obj3);
                    } else {
                        Class<?> cls2 = obj3.getClass();
                        if (cls2 == cls) {
                            t0VarX.c(i0Var, obj3, Integer.valueOf(i4), null, 0);
                        } else {
                            t0VarX = i0Var.x(cls2);
                            t0VarX.c(i0Var, obj3, Integer.valueOf(i4), null, 0);
                            cls = cls2;
                        }
                    }
                    d1Var.append(',');
                }
            }
            Object obj4 = objArr[i2];
            if (obj4 == null) {
                d1Var.append("null]");
            } else {
                if (i0Var.r(obj4)) {
                    i0Var.K(obj4);
                } else {
                    i0Var.L(obj4, Integer.valueOf(i2));
                }
                d1Var.append(']');
            }
        } finally {
            i0Var.r = y0Var;
        }
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 14;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final <T> T f(b.a.a.j.a r12, java.lang.Class<?> r13, com.alibaba.fastjson.JSONArray r14) {
        /*
            r11 = this;
            r0 = 0
            if (r14 != 0) goto L4
            return r0
        L4:
            int r1 = r14.size()
            java.lang.Object r2 = java.lang.reflect.Array.newInstance(r13, r1)
            r3 = 0
            r4 = r3
        Le:
            if (r4 >= r1) goto L65
            java.lang.Object r5 = r14.get(r4)
            if (r5 != r14) goto L1a
            java.lang.reflect.Array.set(r2, r4, r2)
            goto L62
        L1a:
            boolean r6 = r13.isArray()
            if (r6 == 0) goto L31
            boolean r6 = r13.isInstance(r5)
            if (r6 == 0) goto L27
            goto L2d
        L27:
            com.alibaba.fastjson.JSONArray r5 = (com.alibaba.fastjson.JSONArray) r5
            java.lang.Object r5 = r11.f(r12, r13, r5)
        L2d:
            java.lang.reflect.Array.set(r2, r4, r5)
            goto L62
        L31:
            boolean r6 = r5 instanceof com.alibaba.fastjson.JSONArray
            if (r6 == 0) goto L54
            r6 = r5
            com.alibaba.fastjson.JSONArray r6 = (com.alibaba.fastjson.JSONArray) r6
            int r7 = r6.size()
            r8 = r3
            r9 = r8
        L3e:
            if (r8 >= r7) goto L4d
            java.lang.Object r10 = r6.get(r8)
            if (r10 != r14) goto L4a
            r6.set(r4, r2)
            r9 = 1
        L4a:
            int r8 = r8 + 1
            goto L3e
        L4d:
            if (r9 == 0) goto L54
            java.lang.Object[] r6 = r6.toArray()
            goto L55
        L54:
            r6 = r0
        L55:
            if (r6 != 0) goto L5f
            b.a.a.j.h r6 = r12.i()
            java.lang.Object r6 = b.a.a.n.l.f(r5, r13, r6)
        L5f:
            java.lang.reflect.Array.set(r2, r4, r6)
        L62:
            int r4 = r4 + 1
            goto Le
        L65:
            r14.setRelatedArray(r2)
            r14.setComponentType(r13)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.k.s0.f(b.a.a.j.a, java.lang.Class, com.alibaba.fastjson.JSONArray):java.lang.Object");
    }
}
