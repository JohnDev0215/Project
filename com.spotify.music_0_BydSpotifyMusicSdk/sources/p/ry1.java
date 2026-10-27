package p;

import com.google.protobuf.ProtoSyntax;
import java.nio.charset.Charset;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ry1 {
    public static final ry1 c = new ry1();
    public final ConcurrentHashMap b = new ConcurrentHashMap();
    public final ge1 a = new ge1();

    public final k72 a(Class cls) {
        k72 k72VarE;
        com.google.protobuf.l lVar;
        Class cls2;
        Charset charset = w51.a;
        if (cls == null) {
            throw new NullPointerException("messageType");
        }
        ConcurrentHashMap concurrentHashMap = this.b;
        k72 k72Var = (k72) concurrentHashMap.get(cls);
        if (k72Var != null) {
            return k72Var;
        }
        ge1 ge1Var = this.a;
        ge1Var.getClass();
        Class cls3 = com.google.protobuf.m.a;
        if (!com.google.protobuf.i.class.isAssignableFrom(cls) && (cls2 = com.google.protobuf.m.a) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessageV3 or GeneratedMessageLite");
        }
        tj1 tj1VarA = ge1Var.a.a(cls);
        o02 o02Var = (o02) tj1VarA;
        if ((o02Var.d & 2) == 2) {
            boolean zIsAssignableFrom = com.google.protobuf.i.class.isAssignableFrom(cls);
            wj1 wj1Var = o02Var.a;
            if (zIsAssignableFrom) {
                lVar = new com.google.protobuf.l(com.google.protobuf.m.d, tl0.a, wj1Var);
            } else {
                com.google.protobuf.n nVar = com.google.protobuf.m.b;
                sl0 sl0Var = tl0.b;
                if (sl0Var == null) {
                    throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
                }
                lVar = new com.google.protobuf.l(nVar, sl0Var, wj1Var);
            }
            k72VarE = lVar;
        } else {
            boolean zIsAssignableFrom2 = com.google.protobuf.i.class.isAssignableFrom(cls);
            ProtoSyntax protoSyntax = ProtoSyntax.c;
            if (zIsAssignableFrom2) {
                k72VarE = o02Var.d() == protoSyntax ? com.google.protobuf.k.E(tj1VarA, jn1.b, ya1.b, com.google.protobuf.m.d, tl0.a, qe1.b) : com.google.protobuf.k.E(tj1VarA, jn1.b, ya1.b, com.google.protobuf.m.d, null, qe1.b);
            } else if (o02Var.d() == protoSyntax) {
                in1 in1Var = jn1.a;
                wa1 wa1Var = ya1.a;
                com.google.protobuf.n nVar2 = com.google.protobuf.m.b;
                sl0 sl0Var2 = tl0.b;
                if (sl0Var2 == null) {
                    throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
                }
                k72VarE = com.google.protobuf.k.E(tj1VarA, in1Var, wa1Var, nVar2, sl0Var2, qe1.a);
            } else {
                k72VarE = com.google.protobuf.k.E(tj1VarA, jn1.a, ya1.a, com.google.protobuf.m.c, null, qe1.a);
            }
        }
        k72 k72Var2 = (k72) concurrentHashMap.putIfAbsent(cls, k72VarE);
        return k72Var2 != null ? k72Var2 : k72VarE;
    }

    public final k72 b(Object obj) {
        return a(obj.getClass());
    }
}
