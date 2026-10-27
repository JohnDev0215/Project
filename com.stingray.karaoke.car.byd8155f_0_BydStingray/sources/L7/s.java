package L7;

import java.lang.reflect.Method;
import n7.AbstractC1996k;

/* JADX INFO: loaded from: classes.dex */
public final class s extends o {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f4061g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(int i7, Method method) {
        super(method, false, 6);
        this.f4061g = i7;
        switch (i7) {
            case 1:
                A7.m.f(method, "method");
                super(method, true, 4);
                break;
            case 2:
                A7.m.f(method, "method");
                super(method, false, 6);
                break;
            default:
                A7.m.f(method, "method");
                break;
        }
    }

    @Override // L7.o, L7.e
    public final Object q(Object[] objArr) {
        switch (this.f4061g) {
            case 0:
                l9.d.H(this, objArr);
                return c(objArr[0], objArr.length <= 1 ? new Object[0] : AbstractC1996k.I(objArr, 1, objArr.length));
            case 1:
                l9.d.H(this, objArr);
                b(objArr.length == 0 ? null : objArr[0]);
                return c(null, objArr.length <= 1 ? new Object[0] : AbstractC1996k.I(objArr, 1, objArr.length));
            default:
                l9.d.H(this, objArr);
                return c(null, objArr);
        }
    }
}
