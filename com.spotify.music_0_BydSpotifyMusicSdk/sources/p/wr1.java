package p;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import okhttp3.Headers;
import okhttp3.RequestBody;

/* JADX INFO: loaded from: classes.dex */
public final class wr1 extends ux2 {
    public final /* synthetic */ int g = 0;
    public final Method h;
    public final int i;
    public final h00 j;
    public final Object k;

    public wr1(Method method, int i, Headers headers, h00 h00Var) {
        this.h = method;
        this.i = i;
        this.k = headers;
        this.j = h00Var;
    }

    @Override // p.ux2
    public final void c(n32 n32Var, Object obj) {
        int i = this.g;
        h00 h00Var = this.j;
        Object obj2 = this.k;
        Method method = this.h;
        int i2 = this.i;
        switch (i) {
            case 0:
                if (obj == null) {
                    return;
                }
                try {
                    n32Var.c((Headers) obj2, (RequestBody) h00Var.convert(obj));
                    return;
                } catch (IOException e) {
                    throw bk0.Q(method, i2, "Unable to convert " + obj + " to RequestBody", e);
                }
            default:
                Map map = (Map) obj;
                if (map == null) {
                    throw bk0.Q(method, i2, "Part map was null.", new Object[0]);
                }
                for (Map.Entry entry : map.entrySet()) {
                    String str = (String) entry.getKey();
                    if (str == null) {
                        throw bk0.Q(method, i2, "Part map contained null key.", new Object[0]);
                    }
                    Object value = entry.getValue();
                    if (value == null) {
                        throw bk0.Q(method, i2, u4.j("Part map contained null value for key '", str, "'."), new Object[0]);
                    }
                    String[] strArr = {"Content-Disposition", u4.j("form-data; name=\"", str, "\""), "Content-Transfer-Encoding", (String) obj2};
                    Headers.e.getClass();
                    n32Var.c(Headers.Companion.c(strArr), (RequestBody) h00Var.convert(value));
                }
                return;
        }
    }

    public wr1(Method method, int i, h00 h00Var, String str) {
        this.h = method;
        this.i = i;
        this.j = h00Var;
        this.k = str;
    }
}
