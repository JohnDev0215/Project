package p;

import java.lang.reflect.Method;
import okhttp3.Headers;

/* JADX INFO: loaded from: classes.dex */
public final class vr1 extends ux2 {
    public final /* synthetic */ int g;
    public final Method h;
    public final int i;

    public /* synthetic */ vr1(Method method, int i, int i2) {
        this.g = i2;
        this.h = method;
        this.i = i;
    }

    @Override // p.ux2
    public final void c(n32 n32Var, Object obj) {
        int i = this.g;
        int i2 = this.i;
        Method method = this.h;
        switch (i) {
            case 0:
                Headers headers = (Headers) obj;
                if (headers == null) {
                    throw bk0.Q(method, i2, "Headers parameter must not be null.", new Object[0]);
                }
                Headers.Builder builder = n32Var.f;
                builder.getClass();
                int size = headers.size();
                for (int i3 = 0; i3 < size; i3++) {
                    builder.c(headers.b(i3), headers.d(i3));
                }
                return;
            default:
                if (obj == null) {
                    throw bk0.Q(method, i2, "@Url parameter is null.", new Object[0]);
                }
                n32Var.c = obj.toString();
                return;
        }
    }
}
