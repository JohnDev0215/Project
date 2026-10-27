package M;

import n0.C1854D;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: M.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0404c extends A7.j implements InterfaceC2782k {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ v f4129t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0404c(v vVar) {
        super(1, A7.l.class, "localToScreen", "startInput$localToScreen(Landroidx/compose/foundation/text/input/internal/LegacyPlatformTextInputServiceAdapter$LegacyPlatformTextInputNode;[F)V", 0);
        this.f4129t = vVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        float[] fArr = ((C1854D) obj).f19374a;
        C0.r rVar = (C0.r) this.f4129t.B.getValue();
        if (rVar != null) {
            if (!rVar.D()) {
                rVar = null;
            }
            if (rVar != null) {
                rVar.E(fArr);
            }
        }
        return m7.q.f19267a;
    }
}
