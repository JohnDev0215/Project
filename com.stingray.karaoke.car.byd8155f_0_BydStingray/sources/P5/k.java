package P5;

import java.lang.reflect.InvocationTargetException;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class k implements InterfaceC2772a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ p f5372m;

    public /* synthetic */ k(p pVar, int i7) {
        this.l = i7;
        this.f5372m = pVar;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() throws IllegalAccessException, InvocationTargetException {
        switch (this.l) {
            case 0:
                this.f5372m.s();
                return m7.q.f19267a;
            case 1:
                this.f5372m.s();
                return m7.q.f19267a;
            default:
                return Boolean.valueOf(this.f5372m.f() != null);
        }
    }
}
