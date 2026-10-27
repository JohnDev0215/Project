package com.google.android.material.datepicker;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import p.bs2;
import p.cl1;
import p.ff1;
import p.mf1;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
final class MaterialCalendarGridView extends GridView {
    public final boolean c;

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        bs2.c(null);
        if (mf1.J(getContext(), R.attr.windowFullscreen)) {
            setNextFocusLeftId(com.spotify.music.R.id.cancel_button);
            setNextFocusRightId(com.spotify.music.R.id.confirm_button);
        }
        this.c = mf1.J(getContext(), com.spotify.music.R.attr.nestedScrollable);
        zt2.k(this, new ff1(2, this));
    }

    public final cl1 a() {
        return (cl1) super.getAdapter();
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    public final Adapter getAdapter() {
        return (cl1) super.getAdapter();
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ((cl1) super.getAdapter()).notifyDataSetChanged();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        cl1 cl1Var = (cl1) super.getAdapter();
        cl1Var.getClass();
        int iMax = Math.max(cl1Var.c.c(), getFirstVisiblePosition());
        int iMin = Math.min(cl1Var.b(), getLastVisiblePosition());
        cl1Var.getItem(iMax);
        cl1Var.getItem(iMin);
        throw null;
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public final void onFocusChanged(boolean z, int i, Rect rect) {
        if (!z) {
            super.onFocusChanged(false, i, rect);
            return;
        }
        if (i == 33) {
            setSelection(((cl1) super.getAdapter()).b());
        } else if (i == 130) {
            setSelection(((cl1) super.getAdapter()).c.c());
        } else {
            super.onFocusChanged(true, i, rect);
        }
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (!super.onKeyDown(i, keyEvent)) {
            return false;
        }
        if (getSelectedItemPosition() == -1 || getSelectedItemPosition() >= ((cl1) super.getAdapter()).c.c()) {
            return true;
        }
        if (19 != i) {
            return false;
        }
        setSelection(((cl1) super.getAdapter()).c.c());
        return true;
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public final void onMeasure(int i, int i2) {
        if (!this.c) {
            super.onMeasure(i, i2);
            return;
        }
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(16777215, Integer.MIN_VALUE));
        getLayoutParams().height = getMeasuredHeight();
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    public final void setSelection(int i) {
        if (i < ((cl1) super.getAdapter()).c.c()) {
            super.setSelection(((cl1) super.getAdapter()).c.c());
        } else {
            super.setSelection(i);
        }
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    public final ListAdapter getAdapter() {
        return (cl1) super.getAdapter();
    }

    @Override // android.widget.AdapterView
    public final void setAdapter(ListAdapter listAdapter) {
        if (!(listAdapter instanceof cl1)) {
            throw new IllegalArgumentException(String.format("%1$s must have its Adapter set to a %2$s", MaterialCalendarGridView.class.getCanonicalName(), cl1.class.getCanonicalName()));
        }
        super.setAdapter(listAdapter);
    }
}
