package a.p.e;

import a.p.e.l;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class d implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ArrayList f1149a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ l f1150b;

    public d(l lVar, ArrayList arrayList) {
        this.f1150b = lVar;
        this.f1149a = arrayList;
    }

    @Override // java.lang.Runnable
    public void run() {
        for (l.b bVar : this.f1149a) {
            l lVar = this.f1150b;
            RecyclerView.b0 b0Var = bVar.f1200a;
            int i = bVar.f1201b;
            int i2 = bVar.f1202c;
            int i3 = bVar.f1203d;
            int i4 = bVar.f1204e;
            if (lVar == null) {
                throw null;
            }
            View view = b0Var.f1629a;
            int i5 = i3 - i;
            int i6 = i4 - i2;
            if (i5 != 0) {
                view.animate().translationX(0.0f);
            }
            if (i6 != 0) {
                view.animate().translationY(0.0f);
            }
            ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
            lVar.p.add(b0Var);
            viewPropertyAnimatorAnimate.setDuration(lVar.f1642e).setListener(new i(lVar, b0Var, i5, view, i6, viewPropertyAnimatorAnimate)).start();
        }
        this.f1149a.clear();
        this.f1150b.m.remove(this.f1149a);
    }
}
