package b.a.a.k;

import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: StringCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class e1 implements t0, b.a.a.j.j.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static e1 f362a = new e1();

    public static <T> T f(b.a.a.j.a aVar) {
        b.a.a.j.b bVarT = aVar.t();
        if (bVarT.j() == 4) {
            T t = (T) bVarT.V();
            bVarT.F(16);
            return t;
        }
        if (bVarT.j() == 2) {
            T t2 = (T) bVarT.T();
            bVarT.F(16);
            return t2;
        }
        Object objZ = aVar.z();
        if (objZ == null) {
            return null;
        }
        return (T) objZ.toString();
    }

    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        if (type == StringBuffer.class) {
            b.a.a.j.b bVar = aVar.g;
            if (bVar.j() == 4) {
                String strV = bVar.V();
                bVar.F(16);
                return (T) new StringBuffer(strV);
            }
            Object objZ = aVar.z();
            if (objZ == null) {
                return null;
            }
            return (T) new StringBuffer(objZ.toString());
        }
        if (type != StringBuilder.class) {
            return (T) f(aVar);
        }
        b.a.a.j.b bVar2 = aVar.g;
        if (bVar2.j() == 4) {
            String strV2 = bVar2.V();
            bVar2.F(16);
            return (T) new StringBuilder(strV2);
        }
        Object objZ2 = aVar.z();
        if (objZ2 == null) {
            return null;
        }
        return (T) new StringBuilder(objZ2.toString());
    }

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        g(i0Var, (String) obj);
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 4;
    }

    public void g(i0 i0Var, String str) {
        d1 d1Var = i0Var.k;
        if (str == null) {
            d1Var.M(SerializerFeature.WriteNullStringAsEmpty);
        } else {
            d1Var.N(str);
        }
    }
}
