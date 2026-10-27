package p;

import android.animation.Animator;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;
import com.spotify.music.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class lv2 extends xn2 {
    public final /* synthetic */ ViewGroup a;
    public final /* synthetic */ View b;
    public final /* synthetic */ View c;
    public final /* synthetic */ xl0 d;

    public lv2(xl0 xl0Var, ViewGroup viewGroup, View view, View view2) {
        this.d = xl0Var;
        this.a = viewGroup;
        this.b = view;
        this.c = view2;
    }

    @Override // p.vn2
    public final void a(wn2 wn2Var) {
        this.c.setTag(R.id.save_overlay_view, null);
        ((ViewGroupOverlay) new oh1(this.a).e).remove(this.b);
        wn2Var.z(this);
    }

    @Override // p.xn2, p.vn2
    public final void c() {
        ((ViewGroupOverlay) new oh1(this.a).e).remove(this.b);
    }

    @Override // p.xn2, p.vn2
    public final void d() {
        View view = this.b;
        if (view.getParent() == null) {
            ((ViewGroupOverlay) new oh1(this.a).e).add(view);
            return;
        }
        xl0 xl0Var = this.d;
        ArrayList arrayList = xl0Var.u;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ((Animator) arrayList.get(size)).cancel();
        }
        ArrayList arrayList2 = xl0Var.y;
        if (arrayList2 == null || arrayList2.size() <= 0) {
            return;
        }
        ArrayList arrayList3 = (ArrayList) xl0Var.y.clone();
        int size2 = arrayList3.size();
        for (int i = 0; i < size2; i++) {
            ((vn2) arrayList3.get(i)).e();
        }
    }
}
