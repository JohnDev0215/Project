package H;

import A7.l;
import A7.o;
import E0.d0;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e extends A7.j implements InterfaceC2772a {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ i f2751t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ d0 f2752u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ o f2753v;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public e(i iVar, d0 d0Var, InterfaceC2772a interfaceC2772a) {
        super(0, l.class, "localRect", "bringChildIntoView$localRect(Landroidx/compose/foundation/relocation/BringIntoViewResponderNode;Landroidx/compose/ui/layout/LayoutCoordinates;Lkotlin/jvm/functions/Function0;)Landroidx/compose/ui/geometry/Rect;", 0);
        this.f2751t = iVar;
        this.f2752u = d0Var;
        this.f2753v = (o) interfaceC2772a;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [A7.o, z7.a] */
    @Override // z7.InterfaceC2772a
    public final Object a() {
        ?? r02 = this.f2753v;
        return i.H0(this.f2751t, this.f2752u, r02);
    }
}
