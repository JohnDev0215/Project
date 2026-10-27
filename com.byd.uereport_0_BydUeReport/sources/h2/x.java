package h2;

import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;

/* JADX INFO: compiled from: DoubleSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class x implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final x f5310a = new x();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public DecimalFormat f5311b;

    public x() {
        this.f5311b = null;
    }

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        d1 d1Var = i0Var.f5241k;
        if (obj == null) {
            d1Var.c0(SerializerFeature.WriteNullNumberAsZero);
            return;
        }
        double dDoubleValue = ((Double) obj).doubleValue();
        if (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue)) {
            d1Var.Z();
            return;
        }
        DecimalFormat decimalFormat = this.f5311b;
        if (decimalFormat == null) {
            d1Var.B(dDoubleValue, true);
        } else {
            d1Var.write(decimalFormat.format(dDoubleValue));
        }
    }

    public x(DecimalFormat decimalFormat) {
        this.f5311b = null;
        this.f5311b = decimalFormat;
    }

    public x(String str) {
        this(new DecimalFormat(str));
    }
}
