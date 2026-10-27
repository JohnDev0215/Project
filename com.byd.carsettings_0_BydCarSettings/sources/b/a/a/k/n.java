package b.a.a.k;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: BooleanCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class n implements t0, b.a.a.j.j.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final n f388a = new n();

    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        Object obj2;
        b.a.a.j.b bVar = aVar.g;
        try {
            if (bVar.j() == 6) {
                bVar.F(16);
                obj2 = (T) Boolean.TRUE;
            } else if (bVar.j() == 7) {
                bVar.F(16);
                obj2 = (T) Boolean.FALSE;
            } else if (bVar.j() == 2) {
                int iZ = bVar.z();
                bVar.F(16);
                obj2 = iZ == 1 ? (T) Boolean.TRUE : (T) Boolean.FALSE;
            } else {
                Object objZ = aVar.z();
                if (objZ == null) {
                    return null;
                }
                obj2 = (T) b.a.a.n.l.k(objZ);
            }
            return type == AtomicBoolean.class ? (T) new AtomicBoolean(((Boolean) obj2).booleanValue()) : (T) obj2;
        } catch (Exception e2) {
            throw new JSONException("parseBoolean error, field : " + obj, e2);
        }
    }

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        d1 d1Var = i0Var.k;
        Boolean bool = (Boolean) obj;
        if (bool == null) {
            d1Var.M(SerializerFeature.WriteNullBooleanAsFalse);
        } else if (bool.booleanValue()) {
            d1Var.write("true");
        } else {
            d1Var.write("false");
        }
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 6;
    }
}
