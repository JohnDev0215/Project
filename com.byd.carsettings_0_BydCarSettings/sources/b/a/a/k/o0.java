package b.a.a.k;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: LongCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class o0 implements t0, b.a.a.j.j.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static o0 f394a = new o0();

    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        Object objW;
        b.a.a.j.b bVar = aVar.g;
        try {
            int iJ = bVar.j();
            if (iJ == 2) {
                long jE = bVar.e();
                bVar.F(16);
                objW = (T) Long.valueOf(jE);
            } else if (iJ == 3) {
                objW = (T) Long.valueOf(b.a.a.n.l.D0(bVar.J()));
                bVar.F(16);
            } else {
                if (iJ == 12) {
                    JSONObject jSONObject = new JSONObject(true);
                    aVar.R(jSONObject);
                    objW = (T) b.a.a.n.l.w(jSONObject);
                } else {
                    objW = b.a.a.n.l.w(aVar.z());
                }
                if (objW == null) {
                    return null;
                }
            }
            return type == AtomicLong.class ? (T) new AtomicLong(((Long) objW).longValue()) : (T) objW;
        } catch (Exception e2) {
            throw new JSONException("parseLong error, field : " + obj, e2);
        }
    }

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        d1 d1Var = i0Var.k;
        if (obj == null) {
            d1Var.M(SerializerFeature.WriteNullNumberAsZero);
            return;
        }
        long jLongValue = ((Long) obj).longValue();
        d1Var.H(jLongValue);
        if (!d1Var.l(SerializerFeature.WriteClassName) || jLongValue > 2147483647L || jLongValue < -2147483648L || type == Long.class || type == Long.TYPE) {
            return;
        }
        d1Var.write(76);
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 2;
    }
}
