package defpackage;

import defpackage.ax0;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.components.browser_ui.widget.R$dimen;
import org.chromium.components.query_tiles.QueryTile;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class p82 implements hx0 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ p82(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // defpackage.hx0
    public final void a(gx0 gx0Var, final Callback callback) {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                q82 q82Var = (q82) obj;
                if (q82Var.g == null) {
                    q82Var.g = Integer.valueOf(q82Var.a.getResources().getDimensionPixelSize(R$dimen.tile_ideal_width));
                }
                int iIntValue = q82Var.g.intValue();
                ty2 ty2Var = new ty2(callback, 3);
                List<GURL> list = ((QueryTile) gx0Var).f;
                if (!list.isEmpty()) {
                    boolean z = false;
                    GURL gurl = list.get(0);
                    if (iIntValue > 0 && iIntValue > 0) {
                        z = true;
                    }
                    q82Var.f.d(new ax0.a(iIntValue, iIntValue, 1440, gurl.j(), "QueryTiles", z), ty2Var);
                } else {
                    PostTask.c(rh3.a, new eu1(9, ty2Var));
                }
                break;
            default:
                final ia3 ia3Var = (ia3) obj;
                ia3Var.getClass();
                final long jCurrentTimeMillis = System.currentTimeMillis();
                ia3Var.c.a(gx0Var, new Callback() { // from class: ha3
                    @Override // org.chromium.base.Callback
                    public final void onResult(Object obj2) {
                        List list2 = (List) obj2;
                        ia3 ia3Var2 = ia3Var;
                        ia3Var2.getClass();
                        boolean z2 = (list2 == null || list2.isEmpty()) ? false : true;
                        long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                        x93 x93Var = ia3Var2.a;
                        oj2.O("Search." + x93Var.a + ".Bitmap.Available", z2);
                        oj2.b0(jCurrentTimeMillis2, "Search." + x93Var.a + (z2 ? ".Bitmap" : ".NoBitmap") + ".FetchDuration");
                        callback.onResult(list2);
                    }
                });
                break;
        }
    }
}
