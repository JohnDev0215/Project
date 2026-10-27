package b.c.a.f;

import a.p.e.o;
import android.graphics.Canvas;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.globalnotification.R;

/* JADX INFO: loaded from: classes.dex */
public class q extends o.g {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a f1746f;

    public interface a {
        void a(int i);
    }

    public q(a aVar) {
        super(0, 12);
        this.f1746f = aVar;
    }

    @Override // a.p.e.o.d
    public void f(Canvas canvas, RecyclerView recyclerView, RecyclerView.b0 b0Var, float f2, float f3, int i, boolean z) {
        float f4;
        float f5;
        View viewFindViewById = b0Var.f1629a.findViewById(R.id.item_view);
        if (viewFindViewById != null) {
            if (((Boolean) viewFindViewById.getTag()).booleanValue()) {
                float width = b0Var.f1629a.getWidth();
                b0Var.f1629a.setAlpha((width - Math.abs(f2)) / width);
                f5 = f2;
            } else {
                f5 = f2 / 6.0f;
            }
            f4 = f5;
        } else {
            float width2 = b0Var.f1629a.getWidth();
            b0Var.f1629a.setAlpha((width2 - Math.abs(f2)) / width2);
            f4 = f2;
        }
        super.f(canvas, recyclerView, b0Var, f4, f3, i, z);
    }
}
