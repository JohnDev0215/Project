package defpackage;

import com.google.protobuf.b1;
import com.google.protobuf.c0;
import com.google.protobuf.c1;
import com.google.protobuf.h0;
import com.google.protobuf.i1;
import com.google.protobuf.t;
import com.google.protobuf.v0;
import com.google.protobuf.w0;
import com.google.protobuf.z;
import java.nio.charset.Charset;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: Protobuf.java */
/* JADX INFO: loaded from: classes.dex */
public final class a72 {
    public static final a72 c = new a72();
    public final ConcurrentHashMap b = new ConcurrentHashMap();
    public final rb1 a = new rb1();

    public final <T> b1<T> a(Class<T> cls) {
        v0 v0VarZ;
        b1<T> b1Var;
        w0 w0Var;
        Class<?> cls2;
        Charset charset = c0.a;
        if (cls == null) {
            throw new NullPointerException("messageType");
        }
        ConcurrentHashMap concurrentHashMap = this.b;
        b1<T> b1Var2 = (b1) concurrentHashMap.get(cls);
        if (b1Var2 != null) {
            return b1Var2;
        }
        rb1 rb1Var = this.a;
        rb1Var.getClass();
        Class<?> cls3 = c1.a;
        if (!z.class.isAssignableFrom(cls) && (cls2 = c1.a) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessageV3 or GeneratedMessageLite");
        }
        zg1 zg1VarA = rb1Var.a.a(cls);
        if (zg1VarA.a()) {
            if (z.class.isAssignableFrom(cls)) {
                w0Var = new w0(c1.d, yi0.a, zg1VarA.b());
            } else {
                i1<?, ?> i1Var = c1.b;
                t<?> tVar = yi0.b;
                if (tVar == null) {
                    throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
                }
                w0Var = new w0(i1Var, tVar, zg1VarA.b());
            }
            b1Var = w0Var;
        } else {
            if (z.class.isAssignableFrom(cls)) {
                v0VarZ = zg1VarA.c() == 1 ? v0.z(zg1VarA, dm1.b, h0.b, c1.d, yi0.a, yb1.b) : v0.z(zg1VarA, dm1.b, h0.b, c1.d, null, yb1.b);
            } else {
                if (zg1VarA.c() == 1) {
                    bm1 bm1Var = dm1.a;
                    h0.a aVar = h0.a;
                    i1<?, ?> i1Var2 = c1.b;
                    t<?> tVar2 = yi0.b;
                    if (tVar2 == null) {
                        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
                    }
                    v0VarZ = v0.z(zg1VarA, bm1Var, aVar, i1Var2, tVar2, yb1.a);
                } else {
                    v0VarZ = v0.z(zg1VarA, dm1.a, h0.a, c1.c, null, yb1.a);
                }
            }
            b1Var = v0VarZ;
        }
        b1<T> b1Var3 = (b1) concurrentHashMap.putIfAbsent(cls, b1Var);
        return b1Var3 != null ? b1Var3 : b1Var;
    }
}
