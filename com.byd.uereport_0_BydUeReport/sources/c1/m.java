package c1;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ScrollbarHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class m {
    public static int a(RecyclerView.z zVar, j jVar, View view, View view2, RecyclerView.o oVar, boolean z7) {
        if (oVar.getChildCount() == 0 || zVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z7) {
            return Math.abs(oVar.getPosition(view) - oVar.getPosition(view2)) + 1;
        }
        return Math.min(jVar.n(), jVar.d(view2) - jVar.g(view));
    }

    public static int b(RecyclerView.z zVar, j jVar, View view, View view2, RecyclerView.o oVar, boolean z7, boolean z8) {
        if (oVar.getChildCount() == 0 || zVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        int iMax = z8 ? Math.max(0, (zVar.b() - Math.max(oVar.getPosition(view), oVar.getPosition(view2))) - 1) : Math.max(0, Math.min(oVar.getPosition(view), oVar.getPosition(view2)));
        if (z7) {
            return Math.round((iMax * (Math.abs(jVar.d(view2) - jVar.g(view)) / (Math.abs(oVar.getPosition(view) - oVar.getPosition(view2)) + 1))) + (jVar.m() - jVar.g(view)));
        }
        return iMax;
    }

    public static int c(RecyclerView.z zVar, j jVar, View view, View view2, RecyclerView.o oVar, boolean z7) {
        if (oVar.getChildCount() == 0 || zVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z7) {
            return zVar.b();
        }
        return (int) (((jVar.d(view2) - jVar.g(view)) / (Math.abs(oVar.getPosition(view) - oVar.getPosition(view2)) + 1)) * zVar.b());
    }
}
