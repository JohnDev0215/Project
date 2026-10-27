package c.a.a.h.b;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ItemDecorationPowerful.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends RecyclerView.n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3446a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3447b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Paint f3448c;

    public c() {
        this(1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void e(Rect rect, View view, RecyclerView recyclerView, RecyclerView.z zVar) {
        int iE0 = recyclerView.e0(view);
        RecyclerView.g adapter = recyclerView.getAdapter();
        if (adapter != null) {
            int iC = adapter.c();
            int i2 = this.f3446a;
            if (i2 == 0) {
                if (iE0 != 0) {
                    rect.set(this.f3447b, 0, 0, 0);
                    return;
                }
                return;
            }
            if (i2 == 1) {
                if (iE0 != 0) {
                    rect.set(0, this.f3447b, 0, 0);
                    return;
                }
                return;
            }
            if (i2 != 2) {
                if (iE0 != iC - 1) {
                    rect.set(0, 0, 0, this.f3447b);
                    return;
                }
                return;
            }
            RecyclerView.o layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                int iX2 = ((GridLayoutManager) layoutManager).X2();
                if (iE0 == 0) {
                    int i3 = this.f3447b;
                    rect.set(i3, i3, i3, i3);
                } else if (iE0 + 1 <= iX2) {
                    int i4 = this.f3447b;
                    rect.set(0, i4, i4, i4);
                } else if ((iE0 + iX2) % iX2 == 0) {
                    int i5 = this.f3447b;
                    rect.set(i5, 0, i5, i5);
                } else {
                    int i6 = this.f3447b;
                    rect.set(0, 0, i6, i6);
                }
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void g(Canvas canvas, RecyclerView recyclerView, RecyclerView.z zVar) {
        int i2 = this.f3446a;
        if (i2 == 0) {
            l(canvas, recyclerView);
            return;
        }
        if (i2 == 1) {
            p(canvas, recyclerView);
        } else if (i2 != 2) {
            p(canvas, recyclerView);
        } else {
            k(canvas, recyclerView);
        }
    }

    public final void j(Canvas canvas, View view, RecyclerView recyclerView) {
        int right;
        int i2;
        RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
        int left = view.getLeft() - ((ViewGroup.MarginLayoutParams) pVar).leftMargin;
        int bottom = view.getBottom() + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        int i3 = this.f3447b + bottom;
        if (q(recyclerView)) {
            right = view.getRight() + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
            i2 = this.f3447b;
        } else {
            right = view.getRight();
            i2 = ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        }
        canvas.drawRect(left, bottom, right + i2, i3, this.f3448c);
    }

    public final void k(Canvas canvas, RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            RecyclerView.o layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                int iX2 = ((GridLayoutManager) layoutManager).X2();
                if (i2 == 0) {
                    o(canvas, childAt, recyclerView);
                    m(canvas, childAt, recyclerView);
                }
                if (i2 + 1 <= iX2) {
                    o(canvas, childAt, recyclerView);
                }
                if ((i2 + iX2) % iX2 == 0) {
                    m(canvas, childAt, recyclerView);
                }
                n(canvas, childAt, recyclerView);
                j(canvas, childAt, recyclerView);
            }
        }
    }

    public final void l(Canvas canvas, RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            m(canvas, recyclerView.getChildAt(i2), recyclerView);
        }
    }

    public final void m(Canvas canvas, View view, RecyclerView recyclerView) {
        int bottom;
        int i2;
        RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
        int left = (view.getLeft() - this.f3447b) - ((ViewGroup.MarginLayoutParams) pVar).leftMargin;
        int top = view.getTop() - ((ViewGroup.MarginLayoutParams) pVar).topMargin;
        int left2 = view.getLeft() - ((ViewGroup.MarginLayoutParams) pVar).leftMargin;
        if (q(recyclerView)) {
            bottom = view.getBottom() + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
            i2 = this.f3447b;
        } else {
            bottom = view.getBottom();
            i2 = ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        }
        canvas.drawRect(left, top, left2, bottom + i2, this.f3448c);
    }

    public final void n(Canvas canvas, View view, RecyclerView recyclerView) {
        int top;
        int i2;
        RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
        int right = view.getRight() + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        int i3 = this.f3447b + right;
        int bottom = view.getBottom() + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
        if (q(recyclerView)) {
            top = view.getTop() - ((ViewGroup.MarginLayoutParams) pVar).topMargin;
            i2 = this.f3447b;
        } else {
            top = view.getTop();
            i2 = ((ViewGroup.MarginLayoutParams) pVar).topMargin;
        }
        canvas.drawRect(right, top - i2, i3, bottom, this.f3448c);
    }

    public final void o(Canvas canvas, View view, RecyclerView recyclerView) {
        int left;
        int i2;
        RecyclerView.p pVar = (RecyclerView.p) view.getLayoutParams();
        int top = (view.getTop() - ((ViewGroup.MarginLayoutParams) pVar).topMargin) - this.f3447b;
        int right = view.getRight() + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
        int top2 = view.getTop() - ((ViewGroup.MarginLayoutParams) pVar).topMargin;
        if (q(recyclerView)) {
            left = view.getLeft() - ((ViewGroup.MarginLayoutParams) pVar).leftMargin;
            i2 = this.f3447b;
        } else {
            left = view.getLeft();
            i2 = ((ViewGroup.MarginLayoutParams) pVar).leftMargin;
        }
        canvas.drawRect(left - i2, top, right, top2, this.f3448c);
    }

    public final void p(Canvas canvas, RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            o(canvas, recyclerView.getChildAt(i2), recyclerView);
        }
    }

    public final boolean q(RecyclerView recyclerView) {
        return recyclerView.getLayoutManager() instanceof GridLayoutManager;
    }

    public void r(int i2) {
        int i3 = this.f3446a;
        if (i3 != 0 && i3 != 1 && i3 != 2) {
            throw new IllegalArgumentException("ItemDecorationPowerful：分割线类型设置异常");
        }
        this.f3446a = i2;
    }

    public c(int i2) {
        this(i2, Color.parseColor("#808080"), 2);
    }

    public c(int i2, int i3, int i4) {
        this.f3447b = 0;
        r(i2);
        this.f3447b = i4;
        Paint paint = new Paint();
        this.f3448c = paint;
        paint.setAntiAlias(true);
        this.f3448c.setColor(i3);
        this.f3448c.setStyle(Paint.Style.FILL);
    }
}
