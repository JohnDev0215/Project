package U8;

import java.lang.reflect.InvocationTargetException;
import q7.InterfaceC2194h;
import z7.InterfaceC2782k;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e extends A7.j implements InterfaceC2786o {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ int f9231t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e(int i7, Object obj, Class cls, String str, String str2, int i10, int i11) {
        super(i7, i10, cls, obj, str, str2);
        this.f9231t = i11;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) throws IllegalAccessException, InvocationTargetException {
        switch (this.f9231t) {
            case 0:
                InterfaceC2782k interfaceC2782k = ((l) this.f455m).f9248m;
                A7.m.c(interfaceC2782k);
                X8.a.a(interfaceC2782k, obj2, (InterfaceC2194h) obj3);
                break;
            default:
                Object obj4 = ((s) obj2).f9271a;
                InterfaceC2782k interfaceC2782k2 = ((l) this.f455m).f9248m;
                A7.m.c(interfaceC2782k2);
                Object objA = s.a(obj4);
                A7.m.c(objA);
                X8.a.a(interfaceC2782k2, objA, (InterfaceC2194h) obj3);
                break;
        }
        return m7.q.f19267a;
    }
}
