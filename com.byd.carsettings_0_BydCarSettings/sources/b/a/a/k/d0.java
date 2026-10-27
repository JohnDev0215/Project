package b.a.a.k;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: IntegerCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class d0 implements t0, b.a.a.j.j.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static d0 f353a = new d0();

    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        Object obj2;
        b.a.a.j.b bVar = aVar.g;
        int iJ = bVar.j();
        if (iJ == 8) {
            bVar.F(16);
            return null;
        }
        try {
            if (iJ == 2) {
                int iZ = bVar.z();
                bVar.F(16);
                obj2 = (T) Integer.valueOf(iZ);
            } else if (iJ == 3) {
                obj2 = (T) Integer.valueOf(b.a.a.n.l.k0(bVar.J()));
                bVar.F(16);
            } else if (iJ == 12) {
                JSONObject jSONObject = new JSONObject(true);
                aVar.R(jSONObject);
                obj2 = (T) b.a.a.n.l.t(jSONObject);
            } else {
                obj2 = (T) b.a.a.n.l.t(aVar.z());
            }
            obj = AtomicInteger.class;
            return type == obj ? (T) new AtomicInteger(((Integer) obj2).intValue()) : (T) obj2;
        } catch (Exception e2) {
            String str = "parseInt error";
            if (obj != null) {
                str = "parseInt error, field : " + obj;
            }
            throw new JSONException(str, e2);
        }
    }

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        d1 d1Var = i0Var.k;
        Number number = (Number) obj;
        if (number == null) {
            d1Var.M(SerializerFeature.WriteNullNumberAsZero);
            return;
        }
        if (obj instanceof Long) {
            d1Var.H(number.longValue());
        } else {
            d1Var.F(number.intValue());
        }
        if (d1Var.l(SerializerFeature.WriteClassName)) {
            Class<?> cls = number.getClass();
            if (cls == Byte.class) {
                d1Var.write(66);
            } else if (cls == Short.class) {
                d1Var.write(83);
            }
        }
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 2;
    }
}
