package r;

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
import androidx.appcompat.R$attr;
import java.lang.reflect.Field;

/* JADX INFO: compiled from: DropDownListView.java */
/* JADX INFO: loaded from: classes.dex */
public class r extends ListView {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Rect f7620d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7621e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f7622f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f7623g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f7624h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f7625i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Field f7626j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public a f7627k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f7628l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f7629m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f7630n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public o0.x f7631o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public r0.f f7632p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public b f7633q;

    /* JADX INFO: compiled from: DropDownListView.java */
    public static class a extends n.c {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f7634e;

        public a(Drawable drawable) {
            super(drawable);
            this.f7634e = true;
        }

        public void c(boolean z7) {
            this.f7634e = z7;
        }

        @Override // n.c, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            if (this.f7634e) {
                super.draw(canvas);
            }
        }

        @Override // n.c, android.graphics.drawable.Drawable
        public void setHotspot(float f8, float f9) {
            if (this.f7634e) {
                super.setHotspot(f8, f9);
            }
        }

        @Override // n.c, android.graphics.drawable.Drawable
        public void setHotspotBounds(int i8, int i9, int i10, int i11) {
            if (this.f7634e) {
                super.setHotspotBounds(i8, i9, i10, i11);
            }
        }

        @Override // n.c, android.graphics.drawable.Drawable
        public boolean setState(int[] iArr) {
            if (this.f7634e) {
                return super.setState(iArr);
            }
            return false;
        }

        @Override // n.c, android.graphics.drawable.Drawable
        public boolean setVisible(boolean z7, boolean z8) {
            if (this.f7634e) {
                return super.setVisible(z7, z8);
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
            rVar.f7633q = null;
            rVar.removeCallbacks(this);
        }

        public void b() {
            r.this.post(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            r rVar = r.this;
            rVar.f7633q = null;
            rVar.drawableStateChanged();
        }
    }

    public r(Context context, boolean z7) {
        super(context, null, R$attr.dropDownListViewStyle);
        this.f7620d = new Rect();
        this.f7621e = 0;
        this.f7622f = 0;
        this.f7623g = 0;
        this.f7624h = 0;
        this.f7629m = z7;
        setCacheColorHint(0);
        try {
            Field declaredField = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.f7626j = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e8) {
            e8.printStackTrace();
        }
    }

    private void setSelectorEnabled(boolean z7) {
        a aVar = this.f7627k;
        if (aVar != null) {
            aVar.c(z7);
        }
    }

    public final void a() {
        this.f7630n = false;
        setPressed(false);
        drawableStateChanged();
        View childAt = getChildAt(this.f7625i - getFirstVisiblePosition());
        if (childAt != null) {
            childAt.setPressed(false);
        }
        o0.x xVar = this.f7631o;
        if (xVar != null) {
            xVar.b();
            this.f7631o = null;
        }
    }

    public final void b(View view, int i8) {
        performItemClick(view, i8, getItemIdAtPosition(i8));
    }

    public final void c(Canvas canvas) {
        Drawable selector;
        if (this.f7620d.isEmpty() || (selector = getSelector()) == null) {
            return;
        }
        selector.setBounds(this.f7620d);
        selector.draw(canvas);
    }

    public int d(int i8, int i9, int i10, int i11, int i12) {
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
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        View view = null;
        while (i13 < count) {
            int itemViewType = adapter.getItemViewType(i13);
            if (itemViewType != i14) {
                view = null;
                i14 = itemViewType;
            }
            view = adapter.getView(i13, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            int i16 = layoutParams.height;
            view.measure(i8, i16 > 0 ? View.MeasureSpec.makeMeasureSpec(i16, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED) : View.MeasureSpec.makeMeasureSpec(0, 0));
            view.forceLayout();
            if (i13 > 0) {
                measuredHeight += dividerHeight;
            }
            measuredHeight += view.getMeasuredHeight();
            if (measuredHeight >= i11) {
                return (i12 < 0 || i13 <= i12 || i15 <= 0 || measuredHeight == i11) ? i11 : i15;
            }
            if (i12 >= 0 && i13 >= i12) {
                i15 = measuredHeight;
            }
            i13++;
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
        if (this.f7633q != null) {
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
            r0.f r9 = r7.f7632p
            if (r9 != 0) goto L5a
            r0.f r9 = new r0.f
            r9.<init>(r7)
            r7.f7632p = r9
        L5a:
            r0.f r9 = r7.f7632p
            r9.m(r2)
            r0.f r9 = r7.f7632p
            r9.onTouch(r7, r8)
            goto L6c
        L65:
            r0.f r8 = r7.f7632p
            if (r8 == 0) goto L6c
            r8.m(r1)
        L6c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: r.r.e(android.view.MotionEvent, int):boolean");
    }

    public final void f(int i8, View view) {
        Rect rect = this.f7620d;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        rect.left -= this.f7621e;
        rect.top -= this.f7622f;
        rect.right += this.f7623g;
        rect.bottom += this.f7624h;
        try {
            boolean z7 = this.f7626j.getBoolean(this);
            if (view.isEnabled() != z7) {
                this.f7626j.set(this, Boolean.valueOf(!z7));
                if (i8 != -1) {
                    refreshDrawableState();
                }
            }
        } catch (IllegalAccessException e8) {
            e8.printStackTrace();
        }
    }

    public final void g(int i8, View view) {
        Drawable selector = getSelector();
        boolean z7 = (selector == null || i8 == -1) ? false : true;
        if (z7) {
            selector.setVisible(false, false);
        }
        f(i8, view);
        if (z7) {
            Rect rect = this.f7620d;
            float fExactCenterX = rect.exactCenterX();
            float fExactCenterY = rect.exactCenterY();
            selector.setVisible(getVisibility() == 0, false);
            h0.a.k(selector, fExactCenterX, fExactCenterY);
        }
    }

    public final void h(int i8, View view, float f8, float f9) {
        g(i8, view);
        Drawable selector = getSelector();
        if (selector == null || i8 == -1) {
            return;
        }
        h0.a.k(selector, f8, f9);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean hasFocus() {
        return this.f7629m || super.hasFocus();
    }

    @Override // android.view.View
    public boolean hasWindowFocus() {
        return this.f7629m || super.hasWindowFocus();
    }

    public final void i(View view, int i8, float f8, float f9) {
        View childAt;
        this.f7630n = true;
        int i9 = Build.VERSION.SDK_INT;
        if (i9 >= 21) {
            drawableHotspotChanged(f8, f9);
        }
        if (!isPressed()) {
            setPressed(true);
        }
        layoutChildren();
        int i10 = this.f7625i;
        if (i10 != -1 && (childAt = getChildAt(i10 - getFirstVisiblePosition())) != null && childAt != view && childAt.isPressed()) {
            childAt.setPressed(false);
        }
        this.f7625i = i8;
        float left = f8 - view.getLeft();
        float top = f9 - view.getTop();
        if (i9 >= 21) {
            view.drawableHotspotChanged(left, top);
        }
        if (!view.isPressed()) {
            view.setPressed(true);
        }
        h(i8, view, f8, f9);
        setSelectorEnabled(false);
        refreshDrawableState();
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.f7629m || super.isFocused();
    }

    @Override // android.view.View
    public boolean isInTouchMode() {
        return (this.f7629m && this.f7628l) || super.isInTouchMode();
    }

    public final boolean j() {
        return this.f7630n;
    }

    public final void k() {
        Drawable selector = getSelector();
        if (selector != null && j() && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        this.f7633q = null;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (Build.VERSION.SDK_INT < 26) {
            return super.onHoverEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.f7633q == null) {
            b bVar = new b();
            this.f7633q = bVar;
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
            this.f7625i = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        b bVar = this.f7633q;
        if (bVar != null) {
            bVar.a();
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setListSelectionHidden(boolean z7) {
        this.f7628l = z7;
    }

    @Override // android.widget.AbsListView
    public void setSelector(Drawable drawable) {
        a aVar = drawable != null ? new a(drawable) : null;
        this.f7627k = aVar;
        super.setSelector(aVar);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.f7621e = rect.left;
        this.f7622f = rect.top;
        this.f7623g = rect.right;
        this.f7624h = rect.bottom;
    }
}
