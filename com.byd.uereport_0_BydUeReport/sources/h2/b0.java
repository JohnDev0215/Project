package h2;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/* JADX INFO: compiled from: FloatCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class b0 implements t0, g2.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static b0 f5186a = new b0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public NumberFormat f5187b;

    public b0() {
    }

    public static <T> T f(f2.a aVar) {
        f2.b bVar = aVar.f4861j;
        if (bVar.q() == 2) {
            String strL0 = bVar.l0();
            bVar.V(16);
            return (T) Float.valueOf(Float.parseFloat(strL0));
        }
        if (bVar.q() == 3) {
            float fM = bVar.m();
            bVar.V(16);
            return (T) Float.valueOf(fM);
        }
        Object objN = aVar.N();
        if (objN == null) {
            return null;
        }
        return (T) l2.l.s(objN);
    }

    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        try {
            return (T) f(aVar);
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
        float fFloatValue = ((Float) obj).floatValue();
        NumberFormat numberFormat = this.f5187b;
        if (numberFormat != null) {
            d1Var.write(numberFormat.format(fFloatValue));
        } else {
            d1Var.R(fFloatValue, true);
        }
    }

    @Override // g2.t
    public int e() {
        return 2;
    }

    public b0(DecimalFormat decimalFormat) {
        this.f5187b = decimalFormat;
    }

    public b0(String str) {
        this(new DecimalFormat(str));
    }
}
