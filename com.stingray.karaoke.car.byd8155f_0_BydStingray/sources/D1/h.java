package d1;

import android.os.Parcelable;
import android.util.SparseArray;
import m7.q;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class h extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13293m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ o f13294n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h(o oVar, int i7) {
        super(0);
        this.f13293m = i7;
        this.f13294n = oVar;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f13293m) {
            case 0:
                this.f13294n.getLayoutNode().O();
                break;
            case 1:
                o oVar = this.f13294n;
                if (oVar.f13303p && oVar.isAttachedToWindow() && oVar.getView().getParent() == oVar) {
                    oVar.getSnapshotObserver().a(oVar, C1057a.f13276n, oVar.getUpdate());
                }
                break;
            case 2:
                SparseArray<Parcelable> sparseArray = new SparseArray<>();
                this.f13294n.f13328H.saveHierarchyState(sparseArray);
                break;
            case 3:
                o oVar2 = this.f13294n;
                oVar2.getReleaseBlock().b(oVar2.f13328H);
                o.l(oVar2);
                break;
            case 4:
                o oVar3 = this.f13294n;
                oVar3.getResetBlock().b(oVar3.f13328H);
                break;
            default:
                o oVar4 = this.f13294n;
                oVar4.getUpdateBlock().b(oVar4.f13328H);
                break;
        }
        return q.f19267a;
    }
}
