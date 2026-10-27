package h2;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: BooleanCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class n implements t0, g2.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final n f5289a = new n();

    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        Object obj2;
        f2.b bVar = aVar.f4861j;
        try {
            if (bVar.q() == 6) {
                bVar.V(16);
                obj2 = (T) Boolean.TRUE;
            } else if (bVar.q() == 7) {
                bVar.V(16);
                obj2 = (T) Boolean.FALSE;
            } else if (bVar.q() == 2) {
                int iN = bVar.N();
                bVar.V(16);
                obj2 = iN == 1 ? (T) Boolean.TRUE : (T) Boolean.FALSE;
            } else {
                Object objN = aVar.N();
                if (objN == null) {
                    return null;
                }
                obj2 = (T) l2.l.k(objN);
            }
            return type == AtomicBoolean.class ? (T) new AtomicBoolean(((Boolean) obj2).booleanValue()) : (T) obj2;
        } catch (Exception e8) {
            throw new JSONException("parseBoolean error, field : " + obj, e8);
        }
    }

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        d1 d1Var = i0Var.f5241k;
        Boolean bool = (Boolean) obj;
        if (bool == null) {
            d1Var.c0(SerializerFeature.WriteNullBooleanAsFalse);
        } else if (bool.booleanValue()) {
            d1Var.write("true");
        } else {
            d1Var.write("false");
        }
    }

    @Override // g2.t
    public int e() {
        return 6;
    }
}
