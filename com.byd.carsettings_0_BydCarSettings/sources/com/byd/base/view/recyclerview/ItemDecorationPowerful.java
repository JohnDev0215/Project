package com.byd.base.view.recyclerview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class ItemDecorationPowerful extends RecyclerView.ItemDecoration {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2510a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2511b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Paint f2512c;

    public ItemDecorationPowerful() {
        this(1);
    }

    public final void a(Canvas canvas, View view, RecyclerView recyclerView) {
        int right;
        int i;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        int left = view.getLeft() - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
        int bottom = view.getBottom() + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        int i2 = this.f2511b + bottom;
        if (f(recyclerView)) {
            right = view.getRight() + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
            i = this.f2511b;
        } else {
            right = view.getRight();
            i = ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
        }
        canvas.drawRect(left, bottom, right + i, i2, this.f2512c);
    }

    public final void b(Canvas canvas, RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
                if (i == 0) {
                    e(canvas, childAt, recyclerView);
                    c(canvas, childAt, recyclerView);
                }
                if (i + 1 <= spanCount) {
                    e(canvas, childAt, recyclerView);
                }
                if ((i + spanCount) % spanCount == 0) {
                    c(canvas, childAt, recyclerView);
                }
                d(canvas, childAt, recyclerView);
                a(canvas, childAt, recyclerView);
            }
        }
    }

    public final void c(Canvas canvas, View view, RecyclerView recyclerView) {
        int bottom;
        int i;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        int left = (view.getLeft() - this.f2511b) - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
        int top = view.getTop() - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
        int left2 = view.getLeft() - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
        if (f(recyclerView)) {
            bottom = view.getBottom() + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
            i = this.f2511b;
        } else {
            bottom = view.getBottom();
            i = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }
        canvas.drawRect(left, top, left2, bottom + i, this.f2512c);
    }

    public final void d(Canvas canvas, View view, RecyclerView recyclerView) {
        int top;
        int i;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        int right = view.getRight() + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
        int i2 = this.f2511b + right;
        int bottom = view.getBottom() + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        if (f(recyclerView)) {
            top = view.getTop() - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
            i = this.f2511b;
        } else {
            top = view.getTop();
            i = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
        }
        canvas.drawRect(right, top - i, i2, bottom, this.f2512c);
    }

    public final void drawHorizontal(Canvas canvas, RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            c(canvas, recyclerView.getChildAt(i), recyclerView);
        }
    }

    public final void drawVertical(Canvas canvas, RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            e(canvas, recyclerView.getChildAt(i), recyclerView);
        }
    }

    public final void e(Canvas canvas, View view, RecyclerView recyclerView) {
        int left;
        int i;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        int top = (view.getTop() - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin) - this.f2511b;
        int right = view.getRight() + ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
        int top2 = view.getTop() - ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
        if (f(recyclerView)) {
            left = view.getLeft() - ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
            i = this.f2511b;
        } else {
            left = view.getLeft();
            i = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
        }
        canvas.drawRect(left - i, top, right, top2, this.f2512c);
    }

    public final boolean f(RecyclerView recyclerView) {
        return recyclerView.getLayoutManager() instanceof GridLayoutManager;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            int itemCount = adapter.getItemCount();
            int i = this.f2510a;
            if (i == 0) {
                if (childAdapterPosition != 0) {
                    rect.set(this.f2511b, 0, 0, 0);
                    return;
                }
                return;
            }
            if (i == 1) {
                if (childAdapterPosition != 0) {
                    rect.set(0, this.f2511b, 0, 0);
                    return;
                }
                return;
            }
            if (i != 2) {
                if (childAdapterPosition != itemCount - 1) {
                    rect.set(0, 0, 0, this.f2511b);
                    return;
                }
                return;
            }
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
                if (childAdapterPosition == 0) {
                    int i2 = this.f2511b;
                    rect.set(i2, i2, i2, i2);
                } else if (childAdapterPosition + 1 <= spanCount) {
                    int i3 = this.f2511b;
                    rect.set(0, i3, i3, i3);
                } else if ((childAdapterPosition + spanCount) % spanCount == 0) {
                    int i4 = this.f2511b;
                    rect.set(i4, 0, i4, i4);
                } else {
                    int i5 = this.f2511b;
                    rect.set(0, 0, i5, i5);
                }
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        int i = this.f2510a;
        if (i == 0) {
            drawHorizontal(canvas, recyclerView);
            return;
        }
        if (i == 1) {
            drawVertical(canvas, recyclerView);
        } else if (i != 2) {
            drawVertical(canvas, recyclerView);
        } else {
            b(canvas, recyclerView);
        }
    }

    public void setOrientation(int i) {
        int i2 = this.f2510a;
        if (i2 != 0 && i2 != 1 && i2 != 2) {
            throw new IllegalArgumentException("ItemDecorationPowerful：分割线类型设置异常");
        }
        this.f2510a = i;
    }

    public ItemDecorationPowerful(int i) {
        this(i, Color.parseColor("#808080"), 2);
    }

    public ItemDecorationPowerful(int i, int i2, int i3) {
        this.f2511b = 0;
        setOrientation(i);
        this.f2511b = i3;
        Paint paint = new Paint();
        this.f2512c = paint;
        paint.setAntiAlias(true);
        this.f2512c.setColor(i2);
        this.f2512c.setStyle(Paint.Style.FILL);
    }
}
