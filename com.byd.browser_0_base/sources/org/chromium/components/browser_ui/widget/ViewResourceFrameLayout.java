package org.chromium.components.browser_ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import defpackage.pn3;
import org.chromium.ui.widget.OptimizedFrameLayout;

/* JADX INFO: loaded from: classes2.dex */
public class ViewResourceFrameLayout extends OptimizedFrameLayout {
    public pn3 f;
    public Rect g;

    public ViewResourceFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public pn3 getResourceAdapter() {
        return this.f;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
        ViewParent viewParentInvalidateChildInParent = super.invalidateChildInParent(iArr, rect);
        if (l()) {
            this.f.g(rect);
        }
        return viewParentInvalidateChildInParent;
    }

    public pn3 j() {
        return new pn3(this, false);
    }

    public boolean l() {
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    @SuppressLint({"NewApi"})
    public final void onDescendantInvalidated(View view, View view2) {
        super.onDescendantInvalidated(view, view2);
        if (l()) {
            if (this.g == null) {
                this.g = new Rect();
            }
            int iFloor = (int) Math.floor(view.getX());
            int iFloor2 = (int) Math.floor(view.getY());
            this.g.set(iFloor, iFloor2, view.getWidth() + iFloor, view.getHeight() + iFloor2);
            this.f.g(this.g);
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.f = j();
    }
}
