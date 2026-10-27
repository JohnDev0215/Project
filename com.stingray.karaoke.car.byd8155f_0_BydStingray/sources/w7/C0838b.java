package W7;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import n7.AbstractC1996k;
import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: W7.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0838b extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C0838b f9874n = new C0838b(1, 0);
    public static final C0838b o = new C0838b(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C0838b f9875p = new C0838b(1, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C0838b f9876q = new C0838b(1, 3);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f9877m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0838b(int i7, int i10) {
        super(i7);
        this.f9877m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f9877m) {
            case 0:
                ParameterizedType parameterizedType = (ParameterizedType) obj;
                A7.m.f(parameterizedType, "it");
                Type ownerType = parameterizedType.getOwnerType();
                if (ownerType instanceof ParameterizedType) {
                    return (ParameterizedType) ownerType;
                }
                return null;
            case 1:
                ParameterizedType parameterizedType2 = (ParameterizedType) obj;
                A7.m.f(parameterizedType2, "it");
                Type[] actualTypeArguments = parameterizedType2.getActualTypeArguments();
                A7.m.e(actualTypeArguments, "it.actualTypeArguments");
                return AbstractC1996k.y(actualTypeArguments);
            case 2:
                return Boolean.valueOf(((Class) obj).getSimpleName().length() == 0);
            default:
                String simpleName = ((Class) obj).getSimpleName();
                if (!C2061f.f(simpleName)) {
                    simpleName = null;
                }
                if (simpleName != null) {
                    return C2061f.e(simpleName);
                }
                return null;
        }
    }
}
