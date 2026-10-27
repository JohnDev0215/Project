package a.p.e;

import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class f implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ArrayList f1164a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ l f1165b;

    public f(l lVar, ArrayList arrayList) {
        this.f1165b = lVar;
        this.f1164a = arrayList;
    }

    @Override // java.lang.Runnable
    public void run() {
        for (RecyclerView.b0 b0Var : this.f1164a) {
            l lVar = this.f1165b;
            if (lVar == null) {
                throw null;
            }
            View view = b0Var.f1629a;
            ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
            lVar.o.add(b0Var);
            viewPropertyAnimatorAnimate.alpha(1.0f).setDuration(lVar.f1640c).setListener(new h(lVar, b0Var, view, viewPropertyAnimatorAnimate)).start();
        }
        this.f1164a.clear();
        this.f1165b.l.remove(this.f1164a);
    }
}
