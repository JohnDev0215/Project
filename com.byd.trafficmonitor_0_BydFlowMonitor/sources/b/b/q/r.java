package b.b.q;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.lang.reflect.Field;

/* JADX INFO: compiled from: DropDownListView.java */
/* JADX INFO: loaded from: classes.dex */
public class r extends ListView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Rect f1810b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1811c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1812d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1813e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1814f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1815g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Field f1816h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public a f1817i;
    public boolean j;
    public boolean k;
    public boolean l;
    public b.h.m.x m;
    public b.h.n.f n;
    public b o;

    /* JADX INFO: compiled from: DropDownListView.java */
    public static class a extends b.b.m.a.c {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1818c;

        public a(Drawable drawable) {
            super(drawable);
            this.f1818c = true;
        }

        public void c(boolean z) {
            this.f1818c = z;
        }

        @Override // b.b.m.a.c, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            if (this.f1818c) {
                super.draw(canvas);
            }
        }

        @Override // b.b.m.a.c, android.graphics.drawable.Drawable
        public void setHotspot(float f2, float f3) {
            if (this.f1818c) {
                super.setHotspot(f2, f3);
            }
        }

        @Override // b.b.m.a.c, android.graphics.drawable.Drawable
        public void setHotspotBounds(int i2, int i3, int i4, int i5) {
            if (this.f1818c) {
                super.setHotspotBounds(i2, i3, i4, i5);
            }
        }

        @Override // b.b.m.a.c, android.graphics.drawable.Drawable
        public boolean setState(int[] iArr) {
            if (this.f1818c) {
                return super.setState(iArr);
            }
            return false;
        }

        @Override // b.b.m.a.c, android.graphics.drawable.Drawable
        public boolean setVisible(boolean z, boolean z2) {
            if (this.f1818c) {
                return super.setVisible(z, z2);
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: DropDownListView.java */
    public class b implements Runnable {
        public b() {
        }

        public void a() {
            r rVar = r.this;
            rVar.o = null;
            rVar.removeCallbacks(this);
        }

        public void b() {
            r.this.post(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            r rVar = r.this;
            rVar.o = null;
            rVar.drawableStateChanged();
        }
    }

    public r(Context context, boolean z) {
        super(context, null, b.b.a.dropDownListViewStyle);
        this.f1810b = new Rect();
        this.f1811c = 0;
        this.f1812d = 0;
        this.f1813e = 0;
        this.f1814f = 0;
        this.k = z;
        setCacheColorHint(0);
        try {
            Field declaredField = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.f1816h = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }

    private void setSelectorEnabled(boolean z) {
        a aVar = this.f1817i;
        if (aVar != null) {
            aVar.c(z);
        }
    }

    public final void a() {
        this.l = false;
        setPressed(false);
        drawableStateChanged();
        View childAt = getChildAt(this.f1815g - getFirstVisiblePosition());
        if (childAt != null) {
            childAt.setPressed(false);
        }
        b.h.m.x xVar = this.m;
        if (xVar != null) {
            xVar.b();
            this.m = null;
        }
    }

    public final void b(View view, int i2) {
        performItemClick(view, i2, getItemIdAtPosition(i2));
    }

    public final void c(Canvas canvas) {
        Drawable selector;
        if (this.f1810b.isEmpty() || (selector = getSelector()) == null) {
            return;
        }
        selector.setBounds(this.f1810b);
        selector.draw(canvas);
    }

    public int d(int i2, int i3, int i4, int i5, int i6) {
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
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        View view = null;
        while (i7 < count) {
            int itemViewType = adapter.getItemViewType(i7);
            if (itemViewType != i8) {
                view = null;
                i8 = itemViewType;
            }
            view = adapter.getView(i7, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            int i10 = layoutParams.height;
            view.measure(i2, i10 > 0 ? View.MeasureSpec.makeMeasureSpec(i10, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED) : View.MeasureSpec.makeMeasureSpec(0, 0));
            view.forceLayout();
            if (i7 > 0) {
                measuredHeight += dividerHeight;
            }
            measuredHeight += view.getMeasuredHeight();
            if (measuredHeight >= i5) {
                return (i6 < 0 || i7 <= i6 || i9 <= 0 || measuredHeight == i5) ? i5 : i9;
            }
            if (i6 >= 0 && i7 >= i6) {
                i9 = measuredHeight;
            }
            i7++;
        }
        return measuredHeight;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        c(canvas);
        super.dispatchDraw(canvas);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        if (this.o != null) {
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
            b.h.n.f r9 = r7.n
            if (r9 != 0) goto L5a
            b.h.n.f r9 = new b.h.n.f
            r9.<init>(r7)
            r7.n = r9
        L5a:
            b.h.n.f r9 = r7.n
            r9.m(r2)
            b.h.n.f r9 = r7.n
            r9.onTouch(r7, r8)
            goto L6c
        L65:
            b.h.n.f r8 = r7.n
            if (r8 == 0) goto L6c
            r8.m(r1)
        L6c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.q.r.e(android.view.MotionEvent, int):boolean");
    }

    public final void f(int i2, View view) {
        Rect rect = this.f1810b;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        rect.left -= this.f1811c;
        rect.top -= this.f1812d;
        rect.right += this.f1813e;
        rect.bottom += this.f1814f;
        try {
            boolean z = this.f1816h.getBoolean(this);
            if (view.isEnabled() != z) {
                this.f1816h.set(this, Boolean.valueOf(!z));
                if (i2 != -1) {
                    refreshDrawableState();
                }
            }
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }

    public final void g(int i2, View view) {
        Drawable selector = getSelector();
        boolean z = (selector == null || i2 == -1) ? false : true;
        if (z) {
            selector.setVisible(false, false);
        }
        f(i2, view);
        if (z) {
            Rect rect = this.f1810b;
            float fExactCenterX = rect.exactCenterX();
            float fExactCenterY = rect.exactCenterY();
            selector.setVisible(getVisibility() == 0, false);
            b.h.f.l.a.k(selector, fExactCenterX, fExactCenterY);
        }
    }

    public final void h(int i2, View view, float f2, float f3) {
        g(i2, view);
        Drawable selector = getSelector();
        if (selector == null || i2 == -1) {
            return;
        }
        b.h.f.l.a.k(selector, f2, f3);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean hasFocus() {
        return this.k || super.hasFocus();
    }

    @Override // android.view.View
    public boolean hasWindowFocus() {
        return this.k || super.hasWindowFocus();
    }

    public final void i(View view, int i2, float f2, float f3) {
        View childAt;
        this.l = true;
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 21) {
            drawableHotspotChanged(f2, f3);
        }
        if (!isPressed()) {
            setPressed(true);
        }
        layoutChildren();
        int i4 = this.f1815g;
        if (i4 != -1 && (childAt = getChildAt(i4 - getFirstVisiblePosition())) != null && childAt != view && childAt.isPressed()) {
            childAt.setPressed(false);
        }
        this.f1815g = i2;
        float left = f2 - view.getLeft();
        float top = f3 - view.getTop();
        if (i3 >= 21) {
            view.drawableHotspotChanged(left, top);
        }
        if (!view.isPressed()) {
            view.setPressed(true);
        }
        h(i2, view, f2, f3);
        setSelectorEnabled(false);
        refreshDrawableState();
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.k || super.isFocused();
    }

    @Override // android.view.View
    public boolean isInTouchMode() {
        return (this.k && this.j) || super.isInTouchMode();
    }

    public final boolean j() {
        return this.l;
    }

    public final void k() {
        Drawable selector = getSelector();
        if (selector != null && j() && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        this.o = null;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (Build.VERSION.SDK_INT < 26) {
            return super.onHoverEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.o == null) {
            b bVar = new b();
            this.o = bVar;
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
            this.f1815g = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        b bVar = this.o;
        if (bVar != null) {
            bVar.a();
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setListSelectionHidden(boolean z) {
        this.j = z;
    }

    @Override // android.widget.AbsListView
    public void setSelector(Drawable drawable) {
        a aVar = drawable != null ? new a(drawable) : null;
        this.f1817i = aVar;
        super.setSelector(aVar);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.f1811c = rect.left;
        this.f1812d = rect.top;
        this.f1813e = rect.right;
        this.f1814f = rect.bottom;
    }
}
