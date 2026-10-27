package defpackage;

import J.N;
import android.content.Context;
import android.view.ViewGroup;
import android.view.animation.LayoutAnimationController;
import defpackage.x93;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.widget.image_tiles.b;

/* JADX INFO: compiled from: QueryTileSection.java */
/* JADX INFO: loaded from: classes2.dex */
public final class q82 {
    public final ViewGroup a;
    public final Callback<a> b;
    public final y93 c;
    public final ja3 d;
    public final la3 e;
    public final ax0 f;
    public Integer g;
    public float h;
    public boolean i = true;

    /* JADX INFO: compiled from: QueryTileSection.java */
    public static class a {
        public final String a;
        public final List<String> b;

        public a(String str, List<String> list) {
            this.a = str;
            this.b = list;
        }
    }

    public q82(ViewGroup viewGroup, Profile profile, Callback<a> callback) {
        this.a = viewGroup;
        this.b = callback;
        this.d = (ja3) N.MnrpS$PN(profile);
        x93.a aVar = new x93.a();
        aVar.a = "QueryTiles.NTP";
        x93 x93Var = new x93(aVar);
        this.e = new la3();
        y93 y93Var = new y93(viewGroup.getContext(), x93Var, new td2(18, this), new p82(0, this));
        this.c = y93Var;
        viewGroup.addView(y93Var.b.b, new ViewGroup.LayoutParams(-1, -2));
        this.f = dx0.c(profile.g(), ys0.a);
        c();
    }

    public static void a(q82 q82Var, List list) {
        da3 da3Var;
        la3 la3Var = q82Var.e;
        la3Var.getClass();
        if (list != null && !list.isEmpty() && la3Var.b == null) {
            la3Var.b = list;
            oj2.R(la3Var.b.size(), gq2.i(new StringBuilder("Search."), la3Var.a, ".TileCount"));
        }
        ArrayList arrayList = new ArrayList(list);
        y93 y93Var = q82Var.c;
        y93Var.getClass();
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        while (true) {
            da3Var = y93Var.a;
            if (i >= da3Var.size()) {
                break;
            }
            arrayList2.add(da3Var.get(i));
            i++;
        }
        boolean z = (arrayList2.isEmpty() || arrayList.isEmpty() || arrayList2.equals(arrayList)) ? false : true;
        da3Var.A(arrayList);
        b bVar = y93Var.b;
        org.chromium.components.browser_ui.widget.image_tiles.a aVar = bVar.b;
        if (aVar.computeHorizontalScrollOffset() != 0) {
            aVar.getLayoutManager().v0(0);
        }
        if (z) {
            LayoutAnimationController layoutAnimationController = bVar.d;
            org.chromium.components.browser_ui.widget.image_tiles.a aVar2 = bVar.b;
            aVar2.setLayoutAnimation(layoutAnimationController);
            aVar2.scheduleLayoutAnimation();
        }
        q82Var.a.setVisibility(list.isEmpty() ? 8 : 0);
    }

    public static int b(Context context) {
        int i = (int) ((r3.c.y / ya0.a(context).d) + 0.5f);
        sk skVar = bq.a;
        return N.M37SqSAy("QueryTiles", i < N.M37SqSAy("QueryTiles", "small_screen_height_threshold_dp", 700) ? "most_visited_max_rows_small_screen" : "most_visited_max_rows_normal_screen", 1);
    }

    public final void c() {
        if (this.i) {
            this.d.b(null, new dm0(25, this));
            this.i = false;
        } else {
            org.chromium.components.browser_ui.widget.image_tiles.a aVar = this.c.b.b;
            if (aVar.computeHorizontalScrollOffset() != 0) {
                aVar.getLayoutManager().v0(0);
            }
        }
    }
}
