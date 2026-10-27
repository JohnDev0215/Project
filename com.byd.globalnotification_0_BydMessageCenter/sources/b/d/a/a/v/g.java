package b.d.a.a.v;

import android.graphics.Canvas;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public class g extends RecyclerView.l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Calendar f2092a = w.e();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Calendar f2093b = w.e();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ f f2094c;

    public g(f fVar) {
        this.f2094c = fVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void g(Canvas canvas, RecyclerView recyclerView, RecyclerView.y yVar) {
        if ((recyclerView.getAdapter() instanceof y) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
            y yVar2 = (y) recyclerView.getAdapter();
            GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            for (a.h.j.c<Long, Long> cVar : this.f2094c.X.e()) {
                Long l = cVar.f801a;
                if (l != null && cVar.f802b != null) {
                    this.f2092a.setTimeInMillis(l.longValue());
                    this.f2093b.setTimeInMillis(cVar.f802b.longValue());
                    int iJ = yVar2.j(this.f2092a.get(1));
                    int iJ2 = yVar2.j(this.f2093b.get(1));
                    View viewU = gridLayoutManager.u(iJ);
                    View viewU2 = gridLayoutManager.u(iJ2);
                    int i = gridLayoutManager.I;
                    int i2 = iJ / i;
                    int i3 = iJ2 / i;
                    for (int i4 = i2; i4 <= i3; i4++) {
                        View viewU3 = gridLayoutManager.u(gridLayoutManager.I * i4);
                        if (viewU3 != null) {
                            int top = viewU3.getTop() + this.f2094c.b0.f2080d.f2071a.top;
                            int bottom = viewU3.getBottom() - this.f2094c.b0.f2080d.f2071a.bottom;
                            canvas.drawRect(i4 == i2 ? (viewU.getWidth() / 2) + viewU.getLeft() : 0, top, i4 == i3 ? (viewU2.getWidth() / 2) + viewU2.getLeft() : recyclerView.getWidth(), bottom, this.f2094c.b0.h);
                        }
                    }
                }
            }
        }
    }
}
