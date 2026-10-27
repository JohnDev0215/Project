package b.a.a.k;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/* JADX INFO: compiled from: FloatCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class b0 implements t0, b.a.a.j.j.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static b0 f336a = new b0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public NumberFormat f337b;

    public b0() {
    }

    public static <T> T f(b.a.a.j.a aVar) {
        b.a.a.j.b bVar = aVar.g;
        if (bVar.j() == 2) {
            String strT = bVar.T();
            bVar.F(16);
            return (T) Float.valueOf(Float.parseFloat(strT));
        }
        if (bVar.j() == 3) {
            float fG = bVar.g();
            bVar.F(16);
            return (T) Float.valueOf(fG);
        }
        Object objZ = aVar.z();
        if (objZ == null) {
            return null;
        }
        return (T) b.a.a.n.l.s(objZ);
    }

    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        try {
            return (T) f(aVar);
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
        float fFloatValue = ((Float) obj).floatValue();
        NumberFormat numberFormat = this.f337b;
        if (numberFormat != null) {
            d1Var.write(numberFormat.format(fFloatValue));
        } else {
            d1Var.C(fFloatValue, true);
        }
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 2;
    }

    public b0(DecimalFormat decimalFormat) {
        this.f337b = decimalFormat;
    }

    public b0(String str) {
        this(new DecimalFormat(str));
    }
}
