package a.p.e;

import a.p.e.l;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class e implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ArrayList f1155a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ l f1156b;

    public e(l lVar, ArrayList arrayList) {
        this.f1156b = lVar;
        this.f1155a = arrayList;
    }

    @Override // java.lang.Runnable
    public void run() {
        for (l.a aVar : this.f1155a) {
            l lVar = this.f1156b;
            if (lVar == null) {
                throw null;
            }
            RecyclerView.b0 b0Var = aVar.f1194a;
            View view = b0Var == null ? null : b0Var.f1629a;
            RecyclerView.b0 b0Var2 = aVar.f1195b;
            View view2 = b0Var2 != null ? b0Var2.f1629a : null;
            if (view != null) {
                ViewPropertyAnimator duration = view.animate().setDuration(lVar.f1643f);
                lVar.r.add(aVar.f1194a);
                duration.translationX(aVar.f1198e - aVar.f1196c);
                duration.translationY(aVar.f1199f - aVar.f1197d);
                duration.alpha(0.0f).setListener(new j(lVar, aVar, duration, view)).start();
            }
            if (view2 != null) {
                ViewPropertyAnimator viewPropertyAnimatorAnimate = view2.animate();
                lVar.r.add(aVar.f1195b);
                viewPropertyAnimatorAnimate.translationX(0.0f).translationY(0.0f).setDuration(lVar.f1643f).alpha(1.0f).setListener(new k(lVar, aVar, viewPropertyAnimatorAnimate, view2)).start();
            }
        }
        this.f1155a.clear();
        this.f1156b.n.remove(this.f1155a);
    }
}
