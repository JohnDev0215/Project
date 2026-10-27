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
    public int E3;
    public Field F3;
    public a G3;
    public boolean H3;
    public boolean I3;
    public boolean J3;
    public b.f.m.w K3;
    public b.f.n.f L3;
    public b M3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Rect f1095c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1096d;
    public int s;
    public int x;
    public int y;

    /* JADX INFO: compiled from: DropDownListView.java */
    public static class a extends b.b.m.a.c {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f1097d;

        public a(Drawable drawable) {
            super(drawable);
            this.f1097d = true;
        }

        public void c(boolean z) {
            this.f1097d = z;
        }

        @Override // b.b.m.a.c, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            if (this.f1097d) {
                super.draw(canvas);
            }
        }

        @Override // b.b.m.a.c, android.graphics.drawable.Drawable
        public void setHotspot(float f2, float f3) {
            if (this.f1097d) {
                super.setHotspot(f2, f3);
            }
        }

        @Override // b.b.m.a.c, android.graphics.drawable.Drawable
        public void setHotspotBounds(int i2, int i3, int i4, int i5) {
            if (this.f1097d) {
                super.setHotspotBounds(i2, i3, i4, i5);
            }
        }

        @Override // b.b.m.a.c, android.graphics.drawable.Drawable
        public boolean setState(int[] iArr) {
            if (this.f1097d) {
                return super.setState(iArr);
            }
            return false;
        }

        @Override // b.b.m.a.c, android.graphics.drawable.Drawable
        public boolean setVisible(boolean z, boolean z2) {
            if (this.f1097d) {
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
            rVar.M3 = null;
            rVar.removeCallbacks(this);
        }

        public void b() {
            r.this.post(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            r rVar = r.this;
            rVar.M3 = null;
            rVar.drawableStateChanged();
        }
    }

    public r(Context context, boolean z) {
        super(context, null, b.b.a.A);
        this.f1095c = new Rect();
        this.f1096d = 0;
        this.s = 0;
        this.x = 0;
        this.y = 0;
        this.I3 = z;
        setCacheColorHint(0);
        try {
            Field declaredField = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.F3 = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }

    private void setSelectorEnabled(boolean z) {
        a aVar = this.G3;
        if (aVar != null) {
            aVar.c(z);
        }
    }

    public final void a() {
        this.J3 = false;
        setPressed(false);
        drawableStateChanged();
        View childAt = getChildAt(this.E3 - getFirstVisiblePosition());
        if (childAt != null) {
            childAt.setPressed(false);
        }
        b.f.m.w wVar = this.K3;
        if (wVar != null) {
            wVar.b();
            this.K3 = null;
        }
    }

    public final void b(View view, int i2) {
        performItemClick(view, i2, getItemIdAtPosition(i2));
    }

    public final void c(Canvas canvas) {
        Drawable selector;
        if (this.f1095c.isEmpty() || (selector = getSelector()) == null) {
            return;
        }
        selector.setBounds(this.f1095c);
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
        if (this.M3 != null) {
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
            b.f.n.f r9 = r7.L3
            if (r9 != 0) goto L5a
            b.f.n.f r9 = new b.f.n.f
            r9.<init>(r7)
            r7.L3 = r9
        L5a:
            b.f.n.f r9 = r7.L3
            r9.m(r2)
            b.f.n.f r9 = r7.L3
            r9.onTouch(r7, r8)
            goto L6c
        L65:
            b.f.n.f r8 = r7.L3
            if (r8 == 0) goto L6c
            r8.m(r1)
        L6c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.q.r.e(android.view.MotionEvent, int):boolean");
    }

    public final void f(int i2, View view) {
        Rect rect = this.f1095c;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        rect.left -= this.f1096d;
        rect.top -= this.s;
        rect.right += this.x;
        rect.bottom += this.y;
        try {
            boolean z = this.F3.getBoolean(this);
            if (view.isEnabled() != z) {
                this.F3.set(this, Boolean.valueOf(!z));
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
            Rect rect = this.f1095c;
            float fExactCenterX = rect.exactCenterX();
            float fExactCenterY = rect.exactCenterY();
            selector.setVisible(getVisibility() == 0, false);
            b.f.g.l.a.j(selector, fExactCenterX, fExactCenterY);
        }
    }

    public final void h(int i2, View view, float f2, float f3) {
        g(i2, view);
        Drawable selector = getSelector();
        if (selector == null || i2 == -1) {
            return;
        }
        b.f.g.l.a.j(selector, f2, f3);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean hasFocus() {
        return this.I3 || super.hasFocus();
    }

    @Override // android.view.View
    public boolean hasWindowFocus() {
        return this.I3 || super.hasWindowFocus();
    }

    public final void i(View view, int i2, float f2, float f3) {
        View childAt;
        this.J3 = true;
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 21) {
            drawableHotspotChanged(f2, f3);
        }
        if (!isPressed()) {
            setPressed(true);
        }
        layoutChildren();
        int i4 = this.E3;
        if (i4 != -1 && (childAt = getChildAt(i4 - getFirstVisiblePosition())) != null && childAt != view && childAt.isPressed()) {
            childAt.setPressed(false);
        }
        this.E3 = i2;
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
        return this.I3 || super.isFocused();
    }

    @Override // android.view.View
    public boolean isInTouchMode() {
        return (this.I3 && this.H3) || super.isInTouchMode();
    }

    public final boolean j() {
        return this.J3;
    }

    public final void k() {
        Drawable selector = getSelector();
        if (selector != null && j() && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        this.M3 = null;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (Build.VERSION.SDK_INT < 26) {
            return super.onHoverEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.M3 == null) {
            b bVar = new b();
            this.M3 = bVar;
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
            this.E3 = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        b bVar = this.M3;
        if (bVar != null) {
            bVar.a();
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setListSelectionHidden(boolean z) {
        this.H3 = z;
    }

    @Override // android.widget.AbsListView
    public void setSelector(Drawable drawable) {
        a aVar = drawable != null ? new a(drawable) : null;
        this.G3 = aVar;
        super.setSelector(aVar);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.f1096d = rect.left;
        this.s = rect.top;
        this.x = rect.right;
        this.y = rect.bottom;
    }
}
