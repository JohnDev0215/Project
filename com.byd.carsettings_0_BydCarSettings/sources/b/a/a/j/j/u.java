package b.a.a.j.j;

import b.a.a.k.i0;
import b.a.a.k.t0;
import com.alibaba.fastjson.JSONException;
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
    public static u f300a = new u();

    @Override // b.a.a.j.j.t
    public <T> T b(b.a.a.j.a aVar, Type type, Object obj) {
        if (type == OptionalInt.class) {
            Integer numT = b.a.a.n.l.t(aVar.N(Integer.class));
            return numT == null ? (T) OptionalInt.empty() : (T) OptionalInt.of(numT.intValue());
        }
        if (type == OptionalLong.class) {
            Long lW = b.a.a.n.l.w(aVar.N(Long.class));
            return lW == null ? (T) OptionalLong.empty() : (T) OptionalLong.of(lW.longValue());
        }
        if (type == OptionalDouble.class) {
            Double dQ = b.a.a.n.l.q(aVar.N(Double.class));
            return dQ == null ? (T) OptionalDouble.empty() : (T) OptionalDouble.of(dQ.doubleValue());
        }
        Object objO = aVar.O(b.a.a.n.l.N0(type));
        return objO == null ? (T) Optional.empty() : (T) Optional.of(objO);
    }

    @Override // b.a.a.k.t0
    public void c(i0 i0Var, Object obj, Object obj2, Type type, int i) throws IOException {
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
                i0Var.k.F(optionalInt.getAsInt());
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
            i0Var.k.H(optionalLong.getAsLong());
        } else {
            i0Var.J();
        }
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 12;
    }
}
