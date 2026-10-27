package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.lang.reflect.Field;

/* JADX INFO: compiled from: DropDownListView.java */
/* JADX INFO: loaded from: classes.dex */
class c0 extends ListView {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Rect f1002e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f1003g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f1004h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f1005i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f1006j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f1007k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private Field f1008l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private a f1009m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private boolean f1010n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f1011o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private boolean f1012p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private i0.y f1013q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private androidx.core.widget.f f1014r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    b f1015s;

    /* JADX INFO: compiled from: DropDownListView.java */
    private static class a extends g.a {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private boolean f1016g;

        a(Drawable drawable) {
            super(drawable);
            this.f1016g = true;
        }

        void c(boolean z6) {
            this.f1016g = z6;
        }

        @Override // g.a, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            if (this.f1016g) {
                super.draw(canvas);
            }
        }

        @Override // g.a, android.graphics.drawable.Drawable
        public void setHotspot(float f7, float f8) {
            if (this.f1016g) {
                super.setHotspot(f7, f8);
            }
        }

        @Override // g.a, android.graphics.drawable.Drawable
        public void setHotspotBounds(int i7, int i8, int i9, int i10) {
            if (this.f1016g) {
                super.setHotspotBounds(i7, i8, i9, i10);
            }
        }

        @Override // g.a, android.graphics.drawable.Drawable
        public boolean setState(int[] iArr) {
            if (this.f1016g) {
                return super.setState(iArr);
            }
            return false;
        }

        @Override // g.a, android.graphics.drawable.Drawable
        public boolean setVisible(boolean z6, boolean z7) {
            if (this.f1016g) {
                return super.setVisible(z6, z7);
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: DropDownListView.java */
    private class b implements Runnable {
        b() {
        }

        public void a() {
            c0 c0Var = c0.this;
            c0Var.f1015s = null;
            c0Var.removeCallbacks(this);
        }

        public void b() {
            c0.this.post(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            c0 c0Var = c0.this;
            c0Var.f1015s = null;
            c0Var.drawableStateChanged();
        }
    }

    c0(Context context, boolean z6) {
        super(context, null, e.a.dropDownListViewStyle);
        this.f1002e = new Rect();
        this.f1003g = 0;
        this.f1004h = 0;
        this.f1005i = 0;
        this.f1006j = 0;
        this.f1011o = z6;
        setCacheColorHint(0);
        try {
            Field declaredField = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.f1008l = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e7) {
            e7.printStackTrace();
        }
    }

    private void a() {
        this.f1012p = false;
        setPressed(false);
        drawableStateChanged();
        View childAt = getChildAt(this.f1007k - getFirstVisiblePosition());
        if (childAt != null) {
            childAt.setPressed(false);
        }
        i0.y yVar = this.f1013q;
        if (yVar != null) {
            yVar.b();
            this.f1013q = null;
        }
    }

    private void b(View view, int i7) {
        performItemClick(view, i7, getItemIdAtPosition(i7));
    }

    private void c(Canvas canvas) {
        Drawable selector;
        if (this.f1002e.isEmpty() || (selector = getSelector()) == null) {
            return;
        }
        selector.setBounds(this.f1002e);
        selector.draw(canvas);
    }

    private void f(int i7, View view) {
        Rect rect = this.f1002e;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        rect.left -= this.f1003g;
        rect.top -= this.f1004h;
        rect.right += this.f1005i;
        rect.bottom += this.f1006j;
        try {
            boolean z6 = this.f1008l.getBoolean(this);
            if (view.isEnabled() != z6) {
                this.f1008l.set(this, Boolean.valueOf(!z6));
                if (i7 != -1) {
                    refreshDrawableState();
                }
            }
        } catch (IllegalAccessException e7) {
            e7.printStackTrace();
        }
    }

    private void g(int i7, View view) {
        Drawable selector = getSelector();
        boolean z6 = (selector == null || i7 == -1) ? false : true;
        if (z6) {
            selector.setVisible(false, false);
        }
        f(i7, view);
        if (z6) {
            Rect rect = this.f1002e;
            float fExactCenterX = rect.exactCenterX();
            float fExactCenterY = rect.exactCenterY();
            selector.setVisible(getVisibility() == 0, false);
            a0.a.e(selector, fExactCenterX, fExactCenterY);
        }
    }

    private void h(int i7, View view, float f7, float f8) {
        g(i7, view);
        Drawable selector = getSelector();
        if (selector == null || i7 == -1) {
            return;
        }
        a0.a.e(selector, f7, f8);
    }

    private void i(View view, int i7, float f7, float f8) {
        View childAt;
        this.f1012p = true;
        drawableHotspotChanged(f7, f8);
        if (!isPressed()) {
            setPressed(true);
        }
        layoutChildren();
        int i8 = this.f1007k;
        if (i8 != -1 && (childAt = getChildAt(i8 - getFirstVisiblePosition())) != null && childAt != view && childAt.isPressed()) {
            childAt.setPressed(false);
        }
        this.f1007k = i7;
        view.drawableHotspotChanged(f7 - view.getLeft(), f8 - view.getTop());
        if (!view.isPressed()) {
            view.setPressed(true);
        }
        h(i7, view, f7, f8);
        setSelectorEnabled(false);
        refreshDrawableState();
    }

    private boolean j() {
        return this.f1012p;
    }

    private void k() {
        Drawable selector = getSelector();
        if (selector != null && j() && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    private void setSelectorEnabled(boolean z6) {
        a aVar = this.f1009m;
        if (aVar != null) {
            aVar.c(z6);
        }
    }

    public int d(int i7, int i8, int i9, int i10, int i11) {
        int listPaddingTop = getListPaddingTop();
        int listPaddingBottom = getListPaddingBottom();
        int dividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        if (adapter == null) {
            return listPaddingTop + listPaddingBottom;
        }
        int measuredHeight = listPaddingTop + listPaddingBottom;
        if (dividerHeight <= 0 || divider == null) {
            dividerHeight = 0;
        }
        int count = adapter.getCount();
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        View view = null;
        while (i12 < count) {
            int itemViewType = adapter.getItemViewType(i12);
            if (itemViewType != i13) {
                view = null;
                i13 = itemViewType;
            }
            view = adapter.getView(i12, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            int i15 = layoutParams.height;
            view.measure(i7, i15 > 0 ? View.MeasureSpec.makeMeasureSpec(i15, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED) : View.MeasureSpec.makeMeasureSpec(0, 0));
            view.forceLayout();
            if (i12 > 0) {
                measuredHeight += dividerHeight;
            }
            measuredHeight += view.getMeasuredHeight();
            if (measuredHeight >= i10) {
                return (i11 < 0 || i12 <= i11 || i14 <= 0 || measuredHeight == i10) ? i10 : i14;
            }
            if (i11 >= 0 && i12 >= i11) {
                i14 = measuredHeight;
            }
            i12++;
        }
        return measuredHeight;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        c(canvas);
        super.dispatchDraw(canvas);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        if (this.f1015s != null) {
            return;
        }
        super.drawableStateChanged();
        setSelectorEnabled(true);
        k();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean e(android.view.MotionEvent r8, int r9) {
        /*
            r7 = this;
            int r0 = r8.getActionMasked()
            r1 = 0
            r2 = 1
            if (r0 == r2) goto L16
            r3 = 2
            if (r0 == r3) goto L14
            r9 = 3
            if (r0 == r9) goto L11
        Le:
            r9 = r1
            r3 = r2
            goto L46
        L11:
            r9 = r1
            r3 = r9
            goto L46
        L14:
            r3 = r2
            goto L17
        L16:
            r3 = r1
        L17:
            int r9 = r8.findPointerIndex(r9)
            if (r9 >= 0) goto L1e
            goto L11
        L1e:
            float r4 = r8.getX(r9)
            int r4 = (int) r4
            float r9 = r8.getY(r9)
            int r9 = (int) r9
            int r5 = r7.pointToPosition(r4, r9)
            r6 = -1
            if (r5 != r6) goto L31
            r9 = r2
            goto L46
        L31:
            int r3 = r7.getFirstVisiblePosition()
            int r3 = r5 - r3
            android.view.View r3 = r7.getChildAt(r3)
            float r4 = (float) r4
            float r9 = (float) r9
            r7.i(r3, r5, r4, r9)
            if (r0 != r2) goto Le
            r7.b(r3, r5)
            goto Le
        L46:
            if (r3 == 0) goto L4a
            if (r9 == 0) goto L4d
        L4a:
            r7.a()
        L4d:
            if (r3 == 0) goto L65
            androidx.core.widget.f r9 = r7.f1014r
            if (r9 != 0) goto L5a
            androidx.core.widget.f r9 = new androidx.core.widget.f
            r9.<init>(r7)
            r7.f1014r = r9
        L5a:
            androidx.core.widget.f r9 = r7.f1014r
            r9.m(r2)
            androidx.core.widget.f r9 = r7.f1014r
            r9.onTouch(r7, r8)
            goto L6c
        L65:
            androidx.core.widget.f r8 = r7.f1014r
            if (r8 == 0) goto L6c
            r8.m(r1)
        L6c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.c0.e(android.view.MotionEvent, int):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean hasFocus() {
        return this.f1011o || super.hasFocus();
    }

    @Override // android.view.View
    public boolean hasWindowFocus() {
        return this.f1011o || super.hasWindowFocus();
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.f1011o || super.isFocused();
    }

    @Override // android.view.View
    public boolean isInTouchMode() {
        return (this.f1011o && this.f1010n) || super.isInTouchMode();
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        this.f1015s = null;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.f1015s == null) {
            b bVar = new b();
            this.f1015s = bVar;
            bVar.b();
        }
        boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
        if (actionMasked == 9 || actionMasked == 7) {
            int iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            if (iPointToPosition != -1 && iPointToPosition != getSelectedItemPosition()) {
                View childAt = getChildAt(iPointToPosition - getFirstVisiblePosition());
                if (childAt.isEnabled()) {
                    setSelectionFromTop(iPointToPosition, childAt.getTop() - getTop());
                }
                k();
            }
        } else {
            setSelection(-1);
        }
        return zOnHoverEvent;
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f1007k = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        b bVar = this.f1015s;
        if (bVar != null) {
            bVar.a();
        }
        return super.onTouchEvent(motionEvent);
    }

    void setListSelectionHidden(boolean z6) {
        this.f1010n = z6;
    }

    @Override // android.widget.AbsListView
    public void setSelector(Drawable drawable) {
        a aVar = drawable != null ? new a(drawable) : null;
        this.f1009m = aVar;
        super.setSelector(aVar);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.f1003g = rect.left;
        this.f1004h = rect.top;
        this.f1005i = rect.right;
        this.f1006j = rect.bottom;
    }
}
