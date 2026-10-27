package d1;

import E0.F;
import F0.C0217n;
import F1.H;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.AndroidComposeView;
import java.util.HashMap;
import m7.q;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: d1.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1058b extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13280m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ o f13281n;
    public final /* synthetic */ F o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1058b(o oVar, F f10, int i7) {
        super(1);
        this.f13280m = i7;
        this.f13281n = oVar;
        this.o = f10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f13280m) {
            case 0:
                Owner owner = (Owner) obj;
                AndroidComposeView androidComposeView = owner instanceof AndroidComposeView ? (AndroidComposeView) owner : null;
                o oVar = this.f13281n;
                if (androidComposeView != null) {
                    HashMap<i, F> holderToLayoutNode = androidComposeView.getAndroidViewsHandler$ui_release().getHolderToLayoutNode();
                    F f10 = this.o;
                    holderToLayoutNode.put(oVar, f10);
                    androidComposeView.getAndroidViewsHandler$ui_release().addView(oVar);
                    androidComposeView.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().put(f10, oVar);
                    oVar.setImportantForAccessibility(1);
                    H.g(oVar, new C0217n(androidComposeView, f10, androidComposeView));
                }
                if (oVar.getView().getParent() != oVar) {
                    oVar.addView(oVar.getView());
                }
                break;
            case 1:
                j.d(this.f13281n, this.o);
                break;
            default:
                o oVar2 = this.f13281n;
                j.d(oVar2, this.o);
                ((AndroidComposeView) oVar2.f13302n).f11626F = true;
                break;
        }
        return q.f19267a;
    }
}
