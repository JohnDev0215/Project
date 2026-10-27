package org.chromium.components.browser_ui.widget.selectable_list;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import java.util.ArrayList;
import org.chromium.components.browser_ui.widget.selectable_list.a;
import org.chromium.ui.widget.ViewLookupCachingFrameLayout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SelectableItemViewBase<E> extends ViewLookupCachingFrameLayout implements Checkable, View.OnClickListener, View.OnLongClickListener, View.OnTouchListener, a.InterfaceC0128a<E> {
    public org.chromium.components.browser_ui.widget.selectable_list.a<E> g;
    public E h;
    public Boolean i;
    public boolean j;
    public float k;
    public float l;

    public class a extends View.AccessibilityDelegate {
        public a() {
        }

        @Override // android.view.View.AccessibilityDelegate
        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            SelectableItemViewBase selectableItemViewBase = SelectableItemViewBase.this;
            org.chromium.components.browser_ui.widget.selectable_list.a<E> aVar = selectableItemViewBase.g;
            accessibilityNodeInfo.setCheckable((aVar == null || !aVar.e() || selectableItemViewBase.h == null) ? false : true);
            accessibilityNodeInfo.setChecked(selectableItemViewBase.isChecked());
        }
    }

    public SelectableItemViewBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = true;
    }

    public void f(ArrayList arrayList) {
        setChecked(this.g.d(this.h));
    }

    public E getItem() {
        return this.h;
    }

    @Override // android.widget.Checkable
    public final boolean isChecked() {
        Boolean bool = this.i;
        return bool != null && bool.booleanValue();
    }

    public abstract void m();

    public boolean n(E e) {
        return this.g.i(e);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        org.chromium.components.browser_ui.widget.selectable_list.a<E> aVar = this.g;
        if (aVar != null) {
            setChecked(aVar.d(this.h));
        }
    }

    public void onClick(View view) {
        if (!this.j) {
            setChecked(n(this.h));
        } else if (this.g.e()) {
            onLongClick(view);
        } else {
            m();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChecked(false);
        this.i = null;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        setOnTouchListener(this);
        setOnClickListener(this);
        setOnLongClickListener(this);
        setAccessibilityDelegate(new a());
    }

    public boolean onLongClick(View view) {
        if (Math.abs(this.l - this.k) >= 100.0f) {
            return true;
        }
        setChecked(n(this.h));
        return true;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            float x = motionEvent.getX();
            this.l = x;
            this.k = x;
            return false;
        }
        if (actionMasked != 2) {
            return false;
        }
        this.l = motionEvent.getX();
        return false;
    }

    public void setChecked(boolean z) {
        Boolean bool = this.i;
        if (bool == null || z != bool.booleanValue()) {
            boolean z2 = this.i != null;
            this.i = Boolean.valueOf(z);
            p(z2);
        }
    }

    public void setItem(E e) {
        this.h = e;
        setChecked(this.g.d(e));
    }

    public void setSelectionDelegate(org.chromium.components.browser_ui.widget.selectable_list.a<E> aVar) {
        org.chromium.components.browser_ui.widget.selectable_list.a<E> aVar2 = this.g;
        if (aVar2 != aVar) {
            if (aVar2 != null) {
                aVar2.g(this);
            }
            this.g = aVar;
            aVar.a(this);
        }
    }

    public void setSelectionOnLongClick(boolean z) {
        this.j = z;
    }

    @Override // android.widget.Checkable
    public final void toggle() {
        setChecked(!isChecked());
    }

    public void p(boolean z) {
    }
}
