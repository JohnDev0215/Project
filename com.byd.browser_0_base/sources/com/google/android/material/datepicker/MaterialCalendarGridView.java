package com.google.android.material.datepicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R$attr;
import com.google.android.material.R$id;
import defpackage.C0214v;
import defpackage.hl;
import defpackage.il;
import defpackage.om3;
import defpackage.qk3;
import defpackage.qv1;
import defpackage.yn3;
import java.util.Calendar;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
final class MaterialCalendarGridView extends GridView {
    public final Calendar e;
    public final boolean f;

    public class a extends C0214v {
        @Override // defpackage.C0214v
        public final void d(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            this.a.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.a);
            accessibilityNodeInfoCompat.p(null);
        }
    }

    public MaterialCalendarGridView(Context context) {
        this(context, null);
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final g getAdapter() {
        return (g) super.getAdapter();
    }

    public final View b(int i) {
        return getChildAt(i - getFirstVisiblePosition());
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        getAdapter().notifyDataSetChanged();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        g gVar;
        Iterator<qv1<Long, Long>> it;
        int i;
        int i2;
        int iA;
        int width;
        int iA2;
        int width2;
        int i3;
        int width3;
        MaterialCalendarGridView materialCalendarGridView = this;
        super.onDraw(canvas);
        g adapter = getAdapter();
        DateSelector<?> dateSelector = adapter.f;
        il ilVar = adapter.h;
        int iMax = Math.max(adapter.a(), getFirstVisiblePosition());
        int iA3 = adapter.a();
        Month month = adapter.e;
        int iMin = Math.min((iA3 + month.i) - 1, getLastVisiblePosition());
        Long item = adapter.getItem(iMax);
        Long item2 = adapter.getItem(iMin);
        Iterator<qv1<Long, Long>> it2 = dateSelector.q().iterator();
        while (it2.hasNext()) {
            qv1<Long, Long> next = it2.next();
            Long l = next.a;
            if (l != null) {
                Long l2 = next.b;
                if (l2 != null) {
                    long jLongValue = l.longValue();
                    long jLongValue2 = l2.longValue();
                    Long lValueOf = Long.valueOf(jLongValue);
                    Long lValueOf2 = Long.valueOf(jLongValue2);
                    if (!(item == null || item2 == null || lValueOf == null || lValueOf2 == null || lValueOf.longValue() > item2.longValue() || lValueOf2.longValue() < item.longValue())) {
                        boolean zD = yn3.d(this);
                        long jLongValue3 = item.longValue();
                        Calendar calendar = materialCalendarGridView.e;
                        if (jLongValue < jLongValue3) {
                            width = iMax % month.h == 0 ? 0 : !zD ? materialCalendarGridView.b(iMax - 1).getRight() : materialCalendarGridView.b(iMax - 1).getLeft();
                            iA = iMax;
                        } else {
                            calendar.setTimeInMillis(jLongValue);
                            iA = adapter.a() + (calendar.get(5) - 1);
                            View viewB = materialCalendarGridView.b(iA);
                            width = (viewB.getWidth() / 2) + viewB.getLeft();
                        }
                        if (jLongValue2 > item2.longValue()) {
                            width2 = (iMin + 1) % month.h == 0 ? getWidth() : !zD ? materialCalendarGridView.b(iMin).getRight() : materialCalendarGridView.b(iMin).getLeft();
                            iA2 = iMin;
                        } else {
                            calendar.setTimeInMillis(jLongValue2);
                            iA2 = adapter.a() + (calendar.get(5) - 1);
                            View viewB2 = materialCalendarGridView.b(iA2);
                            width2 = (viewB2.getWidth() / 2) + viewB2.getLeft();
                        }
                        int itemId = (int) adapter.getItemId(iA);
                        i = iMax;
                        i2 = iMin;
                        int itemId2 = (int) adapter.getItemId(iA2);
                        while (itemId <= itemId2) {
                            int numColumns = getNumColumns() * itemId;
                            g gVar2 = adapter;
                            int numColumns2 = (getNumColumns() + numColumns) - 1;
                            View viewB3 = materialCalendarGridView.b(numColumns);
                            int top = viewB3.getTop() + ((hl) ilVar.a).a.top;
                            Iterator<qv1<Long, Long>> it3 = it2;
                            int bottom = viewB3.getBottom() - ((hl) ilVar.a).a.bottom;
                            if (zD) {
                                int i4 = iA2 > numColumns2 ? 0 : width2;
                                int width4 = numColumns > iA ? getWidth() : width;
                                i3 = i4;
                                width3 = width4;
                            } else {
                                i3 = numColumns > iA ? 0 : width;
                                width3 = iA2 > numColumns2 ? getWidth() : width2;
                            }
                            canvas.drawRect(i3, top, width3, bottom, (Paint) ilVar.h);
                            itemId++;
                            materialCalendarGridView = this;
                            itemId2 = itemId2;
                            adapter = gVar2;
                            it2 = it3;
                        }
                        gVar = adapter;
                        it = it2;
                    }
                }
            } else {
                gVar = adapter;
                it = it2;
                i = iMax;
                i2 = iMin;
            }
            materialCalendarGridView = this;
            iMax = i;
            iMin = i2;
            adapter = gVar;
            it2 = it;
        }
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public final void onFocusChanged(boolean z, int i, Rect rect) {
        if (!z) {
            super.onFocusChanged(false, i, rect);
            return;
        }
        if (i == 33) {
            g adapter = getAdapter();
            setSelection((adapter.a() + adapter.e.i) - 1);
        } else if (i == 130) {
            setSelection(getAdapter().a());
        } else {
            super.onFocusChanged(true, i, rect);
        }
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (!super.onKeyDown(i, keyEvent)) {
            return false;
        }
        if (getSelectedItemPosition() == -1 || getSelectedItemPosition() >= getAdapter().a()) {
            return true;
        }
        if (19 != i) {
            return false;
        }
        setSelection(getAdapter().a());
        return true;
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public final void onMeasure(int i, int i2) {
        if (!this.f) {
            super.onMeasure(i, i2);
            return;
        }
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(16777215, Integer.MIN_VALUE));
        getLayoutParams().height = getMeasuredHeight();
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    public final void setSelection(int i) {
        if (i < getAdapter().a()) {
            super.setSelection(getAdapter().a());
        } else {
            super.setSelection(i);
        }
    }

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.widget.AdapterView
    public final void setAdapter(ListAdapter listAdapter) {
        if (!(listAdapter instanceof g)) {
            throw new IllegalArgumentException(String.format("%1$s must have its Adapter set to a %2$s", MaterialCalendarGridView.class.getCanonicalName(), g.class.getCanonicalName()));
        }
        super.setAdapter(listAdapter);
    }

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = qk3.d(null);
        if (f.P0(getContext())) {
            setNextFocusLeftId(R$id.cancel_button);
            setNextFocusRightId(R$id.confirm_button);
        }
        this.f = f.Q0(getContext(), R$attr.nestedScrollable);
        om3.k(this, new a());
    }
}
