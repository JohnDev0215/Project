package androidx.recyclerview.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: DividerItemDecoration.java */
/* JADX INFO: loaded from: classes.dex */
public final class i extends RecyclerView.l {
    public static final int[] d = {R.attr.listDivider};
    public final Drawable a;
    public int b;
    public final Rect c = new Rect();

    @SuppressLint({"UnknownNullness"})
    public i(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(d);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
        this.a = drawable;
        if (drawable == null) {
            Log.w("DividerItem", "@android:attr/listDivider was not set in the theme used for this DividerItemDecoration. Please set that attribute all call setDrawable()");
        }
        typedArrayObtainStyledAttributes.recycle();
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        this.b = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    @SuppressLint({"UnknownNullness"})
    public final void g(Rect rect, View view, RecyclerView recyclerView, RecyclerView.w wVar) {
        Drawable drawable = this.a;
        if (drawable == null) {
            rect.set(0, 0, 0, 0);
        } else if (this.b == 1) {
            rect.set(0, 0, 0, drawable.getIntrinsicHeight());
        } else {
            rect.set(0, 0, drawable.getIntrinsicWidth(), 0);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    @SuppressLint({"UnknownNullness"})
    public final void h(Canvas canvas, RecyclerView recyclerView) {
        Drawable drawable;
        int height;
        int paddingTop;
        int width;
        int paddingLeft;
        if (recyclerView.getLayoutManager() == null || (drawable = this.a) == null) {
            return;
        }
        int i = this.b;
        Rect rect = this.c;
        int i2 = 0;
        if (i == 1) {
            canvas.save();
            if (recyclerView.getClipToPadding()) {
                paddingLeft = recyclerView.getPaddingLeft();
                width = recyclerView.getWidth() - recyclerView.getPaddingRight();
                canvas.clipRect(paddingLeft, recyclerView.getPaddingTop(), width, recyclerView.getHeight() - recyclerView.getPaddingBottom());
            } else {
                width = recyclerView.getWidth();
                paddingLeft = 0;
            }
            int childCount = recyclerView.getChildCount();
            while (i2 < childCount) {
                View childAt = recyclerView.getChildAt(i2);
                RecyclerView.L(childAt, rect);
                int iRound = Math.round(childAt.getTranslationY()) + rect.bottom;
                drawable.setBounds(paddingLeft, iRound - drawable.getIntrinsicHeight(), width, iRound);
                drawable.draw(canvas);
                i2++;
            }
            canvas.restore();
            return;
        }
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            paddingTop = recyclerView.getPaddingTop();
            height = recyclerView.getHeight() - recyclerView.getPaddingBottom();
            canvas.clipRect(recyclerView.getPaddingLeft(), paddingTop, recyclerView.getWidth() - recyclerView.getPaddingRight(), height);
        } else {
            height = recyclerView.getHeight();
            paddingTop = 0;
        }
        int childCount2 = recyclerView.getChildCount();
        while (i2 < childCount2) {
            View childAt2 = recyclerView.getChildAt(i2);
            recyclerView.getLayoutManager().getClass();
            RecyclerView.L(childAt2, rect);
            int iRound2 = Math.round(childAt2.getTranslationX()) + rect.right;
            drawable.setBounds(iRound2 - drawable.getIntrinsicWidth(), paddingTop, iRound2, height);
            drawable.draw(canvas);
            i2++;
        }
        canvas.restore();
    }
}
