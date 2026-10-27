package b.a.a.k;

import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;

/* JADX INFO: compiled from: DoubleSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class x implements t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final x f407a = new x();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public DecimalFormat f408b;

    public x() {
        this.f408b = null;
    }

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
        d1 d1Var = i0Var.k;
        if (obj == null) {
            d1Var.M(SerializerFeature.WriteNullNumberAsZero);
            return;
        }
        double dDoubleValue = ((Double) obj).doubleValue();
        if (Double.isNaN(dDoubleValue) || Double.isInfinite(dDoubleValue)) {
            d1Var.J();
            return;
        }
        DecimalFormat decimalFormat = this.f408b;
        if (decimalFormat == null) {
            d1Var.s(dDoubleValue, true);
        } else {
            d1Var.write(decimalFormat.format(dDoubleValue));
        }
    }

    public x(DecimalFormat decimalFormat) {
        this.f408b = null;
        this.f408b = decimalFormat;
    }

    public x(String str) {
        this(new DecimalFormat(str));
    }
}
