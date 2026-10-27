package S3;

import T.C0738e0;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class f extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f7898m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ h f7899n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ f(h hVar, int i7) {
        super(0);
        this.f7898m = i7;
        this.f7899n = hVar;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f7898m) {
            case 0:
                h hVar = this.f7899n;
                float f10 = 0.0f;
                if (((O3.a) hVar.f7909t.getValue()) != null) {
                    float fFloatValue = ((Number) hVar.f7906q.getValue()).floatValue();
                    C0738e0 c0738e0 = hVar.f7905p;
                    if (fFloatValue < 0.0f) {
                        if (c0738e0.getValue() != null) {
                            throw new ClassCastException();
                        }
                    } else {
                        if (c0738e0.getValue() != null) {
                            throw new ClassCastException();
                        }
                        f10 = 1.0f;
                    }
                }
                return Float.valueOf(f10);
            case 1:
                h hVar2 = this.f7899n;
                boolean zBooleanValue = ((Boolean) hVar2.o.getValue()).booleanValue();
                C0738e0 c0738e02 = hVar2.f7906q;
                return Float.valueOf((zBooleanValue && hVar2.e() % 2 == 0) ? -((Number) c0738e02.getValue()).floatValue() : ((Number) c0738e02.getValue()).floatValue());
            default:
                h hVar3 = this.f7899n;
                return Boolean.valueOf(hVar3.e() == ((Number) hVar3.f7904n.getValue()).intValue() && ((Number) hVar3.f7911v.getValue()).floatValue() == hVar3.d());
        }
    }
}
