package com.byd.engineermode.a.c;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/* JADX INFO: compiled from: GridDividerItemDecoration.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends RecyclerView.n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Paint f2274a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2275b;

    public a(int i, int i2) {
        this.f2275b = i;
        Paint paint = new Paint(1);
        this.f2274a = paint;
        paint.setColor(i2);
        this.f2274a.setStyle(Paint.Style.FILL);
    }

    private void j(Canvas canvas, RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            RecyclerView.p pVar = (RecyclerView.p) childAt.getLayoutParams();
            int left = childAt.getLeft();
            int right = childAt.getRight();
            int bottom = childAt.getBottom() + ((ViewGroup.MarginLayoutParams) pVar).bottomMargin;
            int i2 = this.f2275b + bottom;
            Paint paint = this.f2274a;
            if (paint != null) {
                canvas.drawRect(left, bottom, right, i2, paint);
            }
            int top = childAt.getTop();
            int bottom2 = childAt.getBottom() + this.f2275b;
            int right2 = childAt.getRight() + ((ViewGroup.MarginLayoutParams) pVar).rightMargin;
            int i3 = this.f2275b + right2;
            Paint paint2 = this.f2274a;
            if (paint2 != null) {
                canvas.drawRect(right2, top, i3, bottom2, paint2);
            }
        }
    }

    private int k(RecyclerView recyclerView) {
        RecyclerView.o layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).X2();
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).r2();
        }
        return -1;
    }

    private boolean l(RecyclerView recyclerView, int i, int i2, int i3) {
        RecyclerView.o layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int i4 = i3 % i2;
            int i5 = i3 / i2;
            if (i4 != 0) {
                i5++;
            }
            return i5 == (i / i2) + 1;
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            if (((StaggeredGridLayoutManager) layoutManager).q2() == 1) {
                if (i >= i3 - (i3 % i2)) {
                    return true;
                }
            } else if ((i + 1) % i2 == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean m(RecyclerView recyclerView, int i, int i2, int i3) {
        RecyclerView.o layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int i4 = i3 % i2;
            int i5 = i3 / i2;
            return (i / i2) + 1 == 1;
        }
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            if (((StaggeredGridLayoutManager) layoutManager).q2() == 1) {
                if (i >= i3 - (i3 % i2)) {
                    return true;
                }
            } else if ((i + 1) % i2 == 0) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void e(Rect rect, View view, RecyclerView recyclerView, RecyclerView.z zVar) {
        super.e(rect, view, recyclerView, zVar);
        int iA = ((RecyclerView.p) view.getLayoutParams()).a();
        int iK = k(recyclerView);
        int iC = recyclerView.getAdapter().c();
        l(recyclerView, iA, iK, iC);
        boolean zM = m(recyclerView, iA, iK, iC);
        int i = iK - 1;
        int i2 = this.f2275b;
        int i3 = (i * i2) / iK;
        int i4 = (iA % iK) * (i2 - i3);
        rect.set(i4, zM ? (i * i2) / iK : 0, i3 - i4, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void g(Canvas canvas, RecyclerView recyclerView, RecyclerView.z zVar) {
        super.g(canvas, recyclerView, zVar);
        j(canvas, recyclerView);
    }
}
