package h2;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: IntegerCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class d0 implements t0, g2.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static d0 f5206a = new d0();

    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        Object obj2;
        f2.b bVar = aVar.f4861j;
        int iQ = bVar.q();
        if (iQ == 8) {
            bVar.V(16);
            return null;
        }
        try {
            if (iQ == 2) {
                int iN = bVar.N();
                bVar.V(16);
                obj2 = (T) Integer.valueOf(iN);
            } else if (iQ == 3) {
                obj2 = (T) Integer.valueOf(l2.l.k0(bVar.Z()));
                bVar.V(16);
            } else if (iQ == 12) {
                JSONObject jSONObject = new JSONObject(true);
                aVar.h0(jSONObject);
                obj2 = (T) l2.l.t(jSONObject);
            } else {
                obj2 = (T) l2.l.t(aVar.N());
            }
            obj = AtomicInteger.class;
            return type == obj ? (T) new AtomicInteger(((Integer) obj2).intValue()) : (T) obj2;
        } catch (Exception e8) {
            String str = "parseInt error";
            if (obj != null) {
                str = "parseInt error, field : " + obj;
            }
            throw new JSONException(str, e8);
        }
    }

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        d1 d1Var = i0Var.f5241k;
        Number number = (Number) obj;
        if (number == null) {
            d1Var.c0(SerializerFeature.WriteNullNumberAsZero);
            return;
        }
        if (obj instanceof Long) {
            d1Var.Y(number.longValue());
        } else {
            d1Var.V(number.intValue());
        }
        if (d1Var.t(SerializerFeature.WriteClassName)) {
            Class<?> cls = number.getClass();
            if (cls == Byte.class) {
                d1Var.write(66);
            } else if (cls == Short.class) {
                d1Var.write(83);
            }
        }
    }

    @Override // g2.t
    public int e() {
        return 2;
    }
}
