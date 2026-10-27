package com.google.android.material.datepicker;

import a.b.k.l;
import a.h.k.q;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ListAdapter;
import b.d.a.a.b;
import b.d.a.a.f;
import b.d.a.a.v.c;
import b.d.a.a.v.d;
import b.d.a.a.v.m;
import b.d.a.a.v.n;
import b.d.a.a.v.p;
import b.d.a.a.v.w;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public final class MaterialCalendarGridView extends GridView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Calendar f2432a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f2433b;

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f2432a = w.e();
        if (n.P(getContext())) {
            setNextFocusLeftId(f.cancel_button);
            setNextFocusRightId(f.confirm_button);
        }
        this.f2433b = n.Q(getContext(), b.nestedScrollable);
        q.U(this, new m(this));
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public p getAdapter() {
        return (p) super.getAdapter();
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getAdapter().notifyDataSetChanged();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int iA;
        int width;
        int iA2;
        int width2;
        int width3;
        int i;
        MaterialCalendarGridView materialCalendarGridView = this;
        super.onDraw(canvas);
        p adapter = getAdapter();
        d<?> dVar = adapter.f2112b;
        c cVar = adapter.f2114d;
        Long item = adapter.getItem(adapter.b());
        Long item2 = adapter.getItem(adapter.d());
        for (a.h.j.c<Long, Long> cVar2 : dVar.e()) {
            Long l = cVar2.f801a;
            if (l != null) {
                if (cVar2.f802b != null) {
                    long jLongValue = l.longValue();
                    long jLongValue2 = cVar2.f802b.longValue();
                    Long lValueOf = Long.valueOf(jLongValue);
                    Long lValueOf2 = Long.valueOf(jLongValue2);
                    if (!(item == null || item2 == null || lValueOf == null || lValueOf2 == null || lValueOf.longValue() > item2.longValue() || lValueOf2.longValue() < item.longValue())) {
                        boolean zM = l.i.M(this);
                        if (jLongValue < item.longValue()) {
                            iA = adapter.b();
                            if (iA % adapter.f2111a.f2107d == 0) {
                                width = 0;
                            } else {
                                View childAt = materialCalendarGridView.getChildAt(iA - 1);
                                width = !zM ? childAt.getRight() : childAt.getLeft();
                            }
                        } else {
                            materialCalendarGridView.f2432a.setTimeInMillis(jLongValue);
                            iA = adapter.a(materialCalendarGridView.f2432a.get(5));
                            View childAt2 = materialCalendarGridView.getChildAt(iA);
                            width = (childAt2.getWidth() / 2) + childAt2.getLeft();
                        }
                        if (jLongValue2 > item2.longValue()) {
                            iA2 = Math.min(adapter.d(), getChildCount() - 1);
                            if ((iA2 + 1) % adapter.f2111a.f2107d == 0) {
                                width2 = getWidth();
                            } else {
                                View childAt3 = materialCalendarGridView.getChildAt(iA2);
                                width2 = !zM ? childAt3.getRight() : childAt3.getLeft();
                            }
                        } else {
                            materialCalendarGridView.f2432a.setTimeInMillis(jLongValue2);
                            iA2 = adapter.a(materialCalendarGridView.f2432a.get(5));
                            View childAt4 = materialCalendarGridView.getChildAt(iA2);
                            width2 = (childAt4.getWidth() / 2) + childAt4.getLeft();
                        }
                        int itemId = (int) adapter.getItemId(iA);
                        int itemId2 = (int) adapter.getItemId(iA2);
                        while (itemId <= itemId2) {
                            int numColumns = getNumColumns() * itemId;
                            int numColumns2 = (getNumColumns() + numColumns) - 1;
                            View childAt5 = materialCalendarGridView.getChildAt(numColumns);
                            int top = childAt5.getTop() + cVar.f2077a.f2071a.top;
                            int bottom = childAt5.getBottom() - cVar.f2077a.f2071a.bottom;
                            if (zM) {
                                int i2 = iA2 > numColumns2 ? 0 : width2;
                                width3 = numColumns > iA ? getWidth() : width;
                                i = i2;
                            } else {
                                i = numColumns > iA ? 0 : width;
                                width3 = iA2 > numColumns2 ? getWidth() : width2;
                            }
                            canvas.drawRect(i, top, width3, bottom, cVar.h);
                            itemId++;
                            materialCalendarGridView = this;
                            adapter = adapter;
                        }
                    }
                }
            }
            materialCalendarGridView = this;
            adapter = adapter;
        }
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public void onFocusChanged(boolean z, int i, Rect rect) {
        int iB;
        if (!z) {
            super.onFocusChanged(false, i, rect);
            return;
        }
        if (i == 33) {
            iB = getAdapter().d();
        } else {
            if (i != 130) {
                super.onFocusChanged(true, i, rect);
                return;
            }
            iB = getAdapter().b();
        }
        setSelection(iB);
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (!super.onKeyDown(i, keyEvent)) {
            return false;
        }
        if (getSelectedItemPosition() == -1 || getSelectedItemPosition() >= getAdapter().b()) {
            return true;
        }
        if (19 != i) {
            return false;
        }
        setSelection(getAdapter().b());
        return true;
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public void onMeasure(int i, int i2) {
        if (!this.f2433b) {
            super.onMeasure(i, i2);
            return;
        }
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(16777215, Integer.MIN_VALUE));
        getLayoutParams().height = getMeasuredHeight();
    }

    @Override // android.widget.AdapterView
    public final void setAdapter(ListAdapter listAdapter) {
        if (!(listAdapter instanceof p)) {
            throw new IllegalArgumentException(String.format("%1$s must have its Adapter set to a %2$s", MaterialCalendarGridView.class.getCanonicalName(), p.class.getCanonicalName()));
        }
        super.setAdapter(listAdapter);
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    public void setSelection(int i) {
        if (i < getAdapter().b()) {
            i = getAdapter().b();
        }
        super.setSelection(i);
    }
}
