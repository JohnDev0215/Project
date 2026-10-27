package h2;

import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: StringCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class e1 implements t0, g2.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static e1 f5232a = new e1();

    public static <T> T f(f2.a aVar) {
        f2.b bVarC = aVar.C();
        if (bVarC.q() == 4) {
            T t7 = (T) bVarC.n0();
            bVarC.V(16);
            return t7;
        }
        if (bVarC.q() == 2) {
            T t8 = (T) bVarC.l0();
            bVarC.V(16);
            return t8;
        }
        Object objN = aVar.N();
        if (objN == null) {
            return null;
        }
        return (T) objN.toString();
    }

    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        if (type == StringBuffer.class) {
            f2.b bVar = aVar.f4861j;
            if (bVar.q() == 4) {
                String strN0 = bVar.n0();
                bVar.V(16);
                return (T) new StringBuffer(strN0);
            }
            Object objN = aVar.N();
            if (objN == null) {
                return null;
            }
            return (T) new StringBuffer(objN.toString());
        }
        if (type != StringBuilder.class) {
            return (T) f(aVar);
        }
        f2.b bVar2 = aVar.f4861j;
        if (bVar2.q() == 4) {
            String strN02 = bVar2.n0();
            bVar2.V(16);
            return (T) new StringBuilder(strN02);
        }
        Object objN2 = aVar.N();
        if (objN2 == null) {
            return null;
        }
        return (T) new StringBuilder(objN2.toString());
    }

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        g(i0Var, (String) obj);
    }

    @Override // g2.t
    public int e() {
        return 4;
    }

    public void g(i0 i0Var, String str) {
        d1 d1Var = i0Var.f5241k;
        if (str == null) {
            d1Var.c0(SerializerFeature.WriteNullStringAsEmpty);
        } else {
            d1Var.d0(str);
        }
    }
}
