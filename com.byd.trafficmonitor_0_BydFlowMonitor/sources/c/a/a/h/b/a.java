package c.a.a.h.b;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: BaseListRecyclerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<T> extends RecyclerView.g<RecyclerView.c0> {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c.a.a.h.b.d f3436f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public LinearLayout f3438h;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f3433c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f3434d = -2;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List<T> f3435e = new ArrayList();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f3437g = true;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f3439i = false;

    /* JADX INFO: renamed from: c.a.a.h.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BaseListRecyclerAdapter.java */
    public class C0070a extends GridLayoutManager.c {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ GridLayoutManager f3440e;

        public C0070a(GridLayoutManager gridLayoutManager) {
            this.f3440e = gridLayoutManager;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.c
        public int f(int i2) {
            if (a.this.I(i2)) {
                return this.f3440e.X2();
            }
            return 1;
        }
    }

    /* JADX INFO: compiled from: BaseListRecyclerAdapter.java */
    public class b implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.c0 f3442b;

        public b(RecyclerView.c0 c0Var) {
            this.f3442b = c0Var;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            a.this.f3436f.b(this.f3442b.f1185b, this.f3442b.m());
        }
    }

    /* JADX INFO: compiled from: BaseListRecyclerAdapter.java */
    public class c implements View.OnLongClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.c0 f3444b;

        public c(RecyclerView.c0 c0Var) {
            this.f3444b = c0Var;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            a.this.f3436f.a(this.f3444b.f1185b, this.f3444b.m());
            return false;
        }
    }

    /* JADX INFO: compiled from: BaseListRecyclerAdapter.java */
    public static class d extends RecyclerView.c0 {
        public TextView t;

        public d(View view) {
            super(view);
            this.t = (TextView) view.findViewById(c.a.a.b.tv_nomore_des);
        }
    }

    /* JADX INFO: compiled from: BaseListRecyclerAdapter.java */
    public class e extends RecyclerView.c0 {
        public e(View view) {
            super(view);
        }
    }

    public int A(int i2) {
        return 0;
    }

    public List<T> B() {
        return this.f3435e;
    }

    public int C() {
        return c.a.a.d.guide_home_room_footer_desc;
    }

    public int D() {
        return Color.rgb(155, 152, 152);
    }

    public int E() {
        return -1;
    }

    public int F() {
        return c.a.a.c.base_item_nomore_footer;
    }

    public int G() {
        LinearLayout linearLayout = this.f3438h;
        return (linearLayout == null || linearLayout.getChildCount() <= 0) ? 0 : 1;
    }

    public boolean H() {
        return false;
    }

    public boolean I(int i2) {
        return i2 == 0 && G() > 0;
    }

    public void J(RecyclerView.c0 c0Var, int i2, int i3) {
        if (this.f3436f == null || i3 == -1) {
            return;
        }
        c0Var.f1185b.setOnClickListener(new b(c0Var));
        c0Var.f1185b.setOnLongClickListener(new c(c0Var));
    }

    public void K(RecyclerView.c0 c0Var, int i2, int i3, List<Object> list) {
        J(c0Var, i2, i3);
    }

    public abstract RecyclerView.c0 L(LayoutInflater layoutInflater, ViewGroup viewGroup, int i2);

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public final int c() {
        int iZ = z();
        if (!this.f3437g && H() && iZ > 0) {
            iZ++;
        }
        return iZ + G();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public final int e(int i2) {
        if (!this.f3437g && H() && i2 == c() - 1) {
            return -1;
        }
        if (i2 != 0 || G() <= 0) {
            return A(i2);
        }
        return -2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void k(RecyclerView recyclerView) {
        super.k(recyclerView);
        RecyclerView.o layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.f3(new C0070a(gridLayoutManager));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public final void l(RecyclerView.c0 c0Var, int i2) {
        if (!(c0Var instanceof d)) {
            J(c0Var, i2, i2 - G());
            return;
        }
        d dVar = (d) c0Var;
        dVar.f1185b.setBackgroundColor(E());
        dVar.t.setTextColor(D());
        dVar.t.setText(C());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public final void m(RecyclerView.c0 c0Var, int i2, List<Object> list) {
        if (list.isEmpty()) {
            super.m(c0Var, i2, list);
        } else if (c0Var instanceof d) {
            K(c0Var, i2, -1, list);
        } else {
            K(c0Var, i2, i2 - G(), list);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public final RecyclerView.c0 n(ViewGroup viewGroup, int i2) {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(viewGroup.getContext());
        return i2 == -1 ? new d(layoutInflaterFrom.inflate(F(), viewGroup, false)) : i2 == -2 ? new e(this.f3438h) : L(layoutInflaterFrom, viewGroup, i2);
    }

    public void w(List<T> list) {
        this.f3435e = list;
        h();
    }

    public void x(View view) {
        y(view, 1);
    }

    public void y(View view, int i2) {
        if (this.f3438h == null) {
            LinearLayout linearLayout = new LinearLayout(view.getContext());
            this.f3438h = linearLayout;
            linearLayout.setOrientation(i2);
            if (i2 == 1) {
                this.f3438h.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            } else {
                this.f3438h.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
            }
        }
        this.f3438h.removeAllViews();
        this.f3438h.addView(view);
        j(0);
    }

    public int z() {
        List<T> list = this.f3435e;
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
