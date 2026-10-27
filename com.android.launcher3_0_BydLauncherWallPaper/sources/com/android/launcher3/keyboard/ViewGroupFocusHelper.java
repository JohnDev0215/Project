package com.android.launcher3.keyboard;

import android.graphics.Rect;
import android.view.View;
import com.android.launcher3.PagedView;

/* JADX INFO: loaded from: classes4.dex */
public class ViewGroupFocusHelper extends FocusIndicatorHelper {
    private final View mContainer;

    public ViewGroupFocusHelper(View container) {
        super(container);
        this.mContainer = container;
    }

    @Override // com.android.launcher3.keyboard.FocusIndicatorHelper
    public void viewToRect(View v, Rect outRect) {
        outRect.left = 0;
        outRect.top = 0;
        computeLocationRelativeToContainer(v, outRect);
        outRect.left = (int) (outRect.left + (((1.0f - v.getScaleX()) * v.getWidth()) / 2.0f));
        outRect.top = (int) (outRect.top + (((1.0f - v.getScaleY()) * v.getHeight()) / 2.0f));
        outRect.right = outRect.left + ((int) (v.getScaleX() * v.getWidth()));
        outRect.bottom = outRect.top + ((int) (v.getScaleY() * v.getHeight()));
    }

    private void computeLocationRelativeToContainer(View child, Rect outRect) {
        View view = (View) child.getParent();
        outRect.left = (int) (outRect.left + child.getX());
        outRect.top = (int) (outRect.top + child.getY());
        if (view != this.mContainer) {
            if (view instanceof PagedView) {
                PagedView pagedView = (PagedView) view;
                outRect.left -= pagedView.getScrollForPage(pagedView.indexOfChild(child));
            }
            computeLocationRelativeToContainer(view, outRect);
        }
    }
}
