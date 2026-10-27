package E5;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

/* JADX INFO: renamed from: E5.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0179d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Type f1799a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Set f1800b;
    public final Object c;
    public final Method d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1801e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final q[] f1802f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f1803g;

    public AbstractC0179d(Type type, Set set, Object obj, Method method, int i7, int i10, boolean z6) {
        this.f1799a = G5.f.a(type);
        this.f1800b = set;
        this.c = obj;
        this.d = method;
        this.f1801e = i10;
        this.f1802f = new q[i7 - i10];
        this.f1803g = z6;
    }

    public void a(H h10, C0180e c0180e) {
        q[] qVarArr = this.f1802f;
        if (qVarArr.length > 0) {
            Method method = this.d;
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            int length = genericParameterTypes.length;
            int i7 = this.f1801e;
            for (int i10 = i7; i10 < length; i10++) {
                Type type = ((ParameterizedType) genericParameterTypes[i10]).getActualTypeArguments()[0];
                Set setE = G5.f.e(parameterAnnotations[i10]);
                qVarArr[i10 - i7] = (M.c(this.f1799a, type) && this.f1800b.equals(setE)) ? h10.d(c0180e, type, setE) : h10.c(type, setE, null);
            }
        }
    }

    public Object b(u uVar) {
        throw new AssertionError();
    }

    public final Object c(Object obj) {
        q[] qVarArr = this.f1802f;
        Object[] objArr = new Object[qVarArr.length + 1];
        objArr[0] = obj;
        System.arraycopy(qVarArr, 0, objArr, 1, qVarArr.length);
        try {
            return this.d.invoke(this.c, objArr);
        } catch (IllegalAccessException unused) {
            throw new AssertionError();
        }
    }

    public void d(x xVar, Object obj) {
        throw new AssertionError();
    }
}
