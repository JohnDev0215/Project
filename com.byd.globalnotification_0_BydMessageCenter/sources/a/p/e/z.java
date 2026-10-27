package a.p.e;

import a.p.e.c;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class z implements c.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f1269a;

    public z(RecyclerView recyclerView) {
        this.f1269a = recyclerView;
    }

    public View a(int i) {
        return this.f1269a.getChildAt(i);
    }

    public int b() {
        return this.f1269a.getChildCount();
    }

    public void c(int i) {
        View childAt = this.f1269a.getChildAt(i);
        if (childAt != null) {
            this.f1269a.p(childAt);
            childAt.clearAnimation();
        }
        this.f1269a.removeViewAt(i);
    }
}
