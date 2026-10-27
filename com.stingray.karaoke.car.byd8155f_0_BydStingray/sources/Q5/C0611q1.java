package Q5;

import java.util.List;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: Q5.q1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0611q1 implements InterfaceC2782k {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f7031m;

    public /* synthetic */ C0611q1(InterfaceC2772a interfaceC2772a, int i7) {
        this.l = i7;
        this.f7031m = interfaceC2772a;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.l) {
            case 0:
                A7.m.f((K.Y) obj, "$this$KeyboardActions");
                this.f7031m.a();
                break;
            case 1:
                ((Boolean) obj).booleanValue();
                this.f7031m.a();
                break;
            case 2:
                ((Boolean) obj).booleanValue();
                this.f7031m.a();
                break;
            case 3:
                ((Boolean) obj).booleanValue();
                this.f7031m.a();
                break;
            case 4:
                ((Float) obj).floatValue();
                this.f7031m.a();
                break;
            default:
                A7.m.f((List) obj, "it");
                this.f7031m.a();
                break;
        }
        return m7.q.f19267a;
    }
}
