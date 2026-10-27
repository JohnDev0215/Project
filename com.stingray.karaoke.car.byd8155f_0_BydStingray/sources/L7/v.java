package L7;

import java.lang.reflect.Method;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class v extends x implements d {
    public final Object d;

    public v(Method method, Object obj) {
        super(method, n7.u.l);
        this.d = obj;
    }

    @Override // L7.e
    public final Object q(Object[] objArr) {
        l9.d.H(this, objArr);
        return this.f4066a.invoke(this.d, Arrays.copyOf(objArr, objArr.length));
    }
}
