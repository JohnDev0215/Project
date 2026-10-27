package p;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import okhttp3.RequestBody;

/* JADX INFO: loaded from: classes.dex */
public final class rr1 extends ux2 {
    public final /* synthetic */ int g;
    public final Method h;
    public final int i;
    public final h00 j;

    public /* synthetic */ rr1(Method method, int i, h00 h00Var, int i2) {
        this.g = i2;
        this.h = method;
        this.i = i;
        this.j = h00Var;
    }

    @Override // p.ux2
    public final void c(n32 n32Var, Object obj) {
        int i = this.g;
        h00 h00Var = this.j;
        Method method = this.h;
        int i2 = this.i;
        switch (i) {
            case 0:
                if (obj == null) {
                    throw bk0.Q(method, i2, "Body parameter value must not be null.", new Object[0]);
                }
                try {
                    n32Var.k = (RequestBody) h00Var.convert(obj);
                    return;
                } catch (IOException e) {
                    throw bk0.R(method, e, i2, "Unable to convert " + obj + " to RequestBody", new Object[0]);
                }
            default:
                Map map = (Map) obj;
                if (map == null) {
                    throw bk0.Q(method, i2, "Header map was null.", new Object[0]);
                }
                for (Map.Entry entry : map.entrySet()) {
                    String str = (String) entry.getKey();
                    if (str == null) {
                        throw bk0.Q(method, i2, "Header map contained null key.", new Object[0]);
                    }
                    Object value = entry.getValue();
                    if (value == null) {
                        throw bk0.Q(method, i2, u4.j("Header map contained null value for key '", str, "'."), new Object[0]);
                    }
                    n32Var.b(str, (String) h00Var.convert(value));
                }
                return;
        }
    }
}
