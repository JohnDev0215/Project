package E5;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;

/* JADX INFO: renamed from: E5.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0177b extends AbstractC0179d {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f1793h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0177b(Type type, Set set, Object obj, Method method, int i7, int i10, boolean z6, int i11) {
        super(type, set, obj, method, i7, i10, z6);
        this.f1793h = i11;
    }

    @Override // E5.AbstractC0179d
    public Object b(u uVar) {
        switch (this.f1793h) {
            case 1:
                return c(uVar);
            default:
                return super.b(uVar);
        }
    }

    @Override // E5.AbstractC0179d
    public void d(x xVar, Object obj) throws InvocationTargetException {
        switch (this.f1793h) {
            case 0:
                q[] qVarArr = this.f1802f;
                Object[] objArr = new Object[qVarArr.length + 2];
                objArr[0] = xVar;
                objArr[1] = obj;
                System.arraycopy(qVarArr, 0, objArr, 2, qVarArr.length);
                try {
                    this.d.invoke(this.c, objArr);
                    return;
                } catch (IllegalAccessException unused) {
                    throw new AssertionError();
                }
            default:
                super.d(xVar, obj);
                return;
        }
    }
}
