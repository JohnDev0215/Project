package g2;

import com.alibaba.fastjson.JSONException;
import h2.i0;
import h2.t0;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

/* JADX INFO: compiled from: OptionalCodec.java */
/* JADX INFO: loaded from: classes.dex */
public class u implements t0, t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static u f5073a = new u();

    @Override // g2.t
    public <T> T b(f2.a aVar, Type type, Object obj) {
        if (type == OptionalInt.class) {
            Integer numT = l2.l.t(aVar.d0(Integer.class));
            return numT == null ? (T) OptionalInt.empty() : (T) OptionalInt.of(numT.intValue());
        }
        if (type == OptionalLong.class) {
            Long lW = l2.l.w(aVar.d0(Long.class));
            return lW == null ? (T) OptionalLong.empty() : (T) OptionalLong.of(lW.longValue());
        }
        if (type == OptionalDouble.class) {
            Double dQ = l2.l.q(aVar.d0(Double.class));
            return dQ == null ? (T) OptionalDouble.empty() : (T) OptionalDouble.of(dQ.doubleValue());
        }
        Object objE0 = aVar.e0(l2.l.N0(type));
        return objE0 == null ? (T) Optional.empty() : (T) Optional.of(objE0);
    }

    @Override // h2.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i8) throws IOException {
        if (obj == null) {
            i0Var.J();
            return;
        }
        if (obj instanceof Optional) {
            Optional optional = (Optional) obj;
            i0Var.H(optional.isPresent() ? optional.get() : null);
            return;
        }
        if (obj instanceof OptionalDouble) {
            OptionalDouble optionalDouble = (OptionalDouble) obj;
            if (optionalDouble.isPresent()) {
                i0Var.H(Double.valueOf(optionalDouble.getAsDouble()));
                return;
            } else {
                i0Var.J();
                return;
            }
        }
        if (obj instanceof OptionalInt) {
            OptionalInt optionalInt = (OptionalInt) obj;
            if (optionalInt.isPresent()) {
                i0Var.f5241k.V(optionalInt.getAsInt());
                return;
            } else {
                i0Var.J();
                return;
            }
        }
        if (!(obj instanceof OptionalLong)) {
            throw new JSONException("not support optional : " + obj.getClass());
        }
        OptionalLong optionalLong = (OptionalLong) obj;
        if (optionalLong.isPresent()) {
            i0Var.f5241k.Y(optionalLong.getAsLong());
        } else {
            i0Var.J();
        }
    }

    @Override // g2.t
    public int e() {
        return 12;
    }
}
