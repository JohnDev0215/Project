package h2;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: LongCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class o0 implements t0, g2.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static o0 f5295a = new o0();

    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        Object objW;
        f2.b bVar = aVar.f4861j;
        try {
            int iQ = bVar.q();
            if (iQ == 2) {
                long jI = bVar.i();
                bVar.V(16);
                objW = (T) Long.valueOf(jI);
            } else if (iQ == 3) {
                objW = (T) Long.valueOf(l2.l.D0(bVar.Z()));
                bVar.V(16);
            } else {
                if (iQ == 12) {
                    JSONObject jSONObject = new JSONObject(true);
                    aVar.h0(jSONObject);
                    objW = (T) l2.l.w(jSONObject);
                } else {
                    objW = l2.l.w(aVar.N());
                }
                if (objW == null) {
                    return null;
                }
            }
            return type == AtomicLong.class ? (T) new AtomicLong(((Long) objW).longValue()) : (T) objW;
        } catch (Exception e8) {
            throw new JSONException("parseLong error, field : " + obj, e8);
        }
    }

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        d1 d1Var = i0Var.f5241k;
        if (obj == null) {
            d1Var.c0(SerializerFeature.WriteNullNumberAsZero);
            return;
        }
        long jLongValue = ((Long) obj).longValue();
        d1Var.Y(jLongValue);
        if (!d1Var.t(SerializerFeature.WriteClassName) || jLongValue > 2147483647L || jLongValue < -2147483648L || type == Long.class || type == Long.TYPE) {
            return;
        }
        d1Var.write(76);
    }

    @Override // g2.t
    public int e() {
        return 2;
    }
}
