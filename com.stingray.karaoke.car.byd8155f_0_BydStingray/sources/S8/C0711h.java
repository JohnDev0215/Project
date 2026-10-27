package S8;

import a9.C0933c;
import a9.InterfaceC0934d;
import java.lang.reflect.InvocationTargetException;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: S8.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0711h implements InterfaceC2786o {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f8114m;

    public /* synthetic */ C0711h(Object obj, int i7) {
        this.l = i7;
        this.f8114m = obj;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, final Object obj3) {
        switch (this.l) {
            case 0:
                ((Q5.O) this.f8114m).b((Throwable) obj);
                return m7.q.f19267a;
            case 1:
                final InterfaceC0934d interfaceC0934d = (InterfaceC0934d) obj;
                final U8.l lVar = (U8.l) this.f8114m;
                return new InterfaceC2786o() { // from class: U8.c
                    @Override // z7.InterfaceC2786o
                    public final Object g(Object obj4, Object obj5, Object obj6) throws IllegalAccessException, InvocationTargetException {
                        B2.q qVar = n.l;
                        Object obj7 = obj3;
                        if (obj7 != qVar) {
                            X8.a.a(lVar.f9248m, obj7, ((C0933c) interfaceC0934d).l);
                        }
                        return m7.q.f19267a;
                    }
                };
            default:
                ((b9.h) this.f8114m).b();
                return m7.q.f19267a;
        }
    }
}
