package o;

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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Rect f4361b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4362c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4363d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4364e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4365f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4366g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Field f4367h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public a f4368i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f4369j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f4370k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f4371l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public l0.x f4372m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public n0.f f4373n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public b f4374o;

    /* JADX INFO: compiled from: DropDownListView.java */
    public static class a extends k.c {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f4375c;

        public a(Drawable drawable) {
            super(drawable);
            this.f4375c = true;
        }

        public void c(boolean z3) {
            this.f4375c = z3;
        }

        @Override // k.c, android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            if (this.f4375c) {
                super.draw(canvas);
            }
        }

        @Override // k.c, android.graphics.drawable.Drawable
        public void setHotspot(float f3, float f4) {
            if (this.f4375c) {
                super.setHotspot(f3, f4);
            }
        }

        @Override // k.c, android.graphics.drawable.Drawable
        public void setHotspotBounds(int i3, int i4, int i5, int i6) {
            if (this.f4375c) {
                super.setHotspotBounds(i3, i4, i5, i6);
            }
        }

        @Override // k.c, android.graphics.drawable.Drawable
        public boolean setState(int[] iArr) {
            if (this.f4375c) {
                return super.setState(iArr);
            }
            return false;
        }

        @Override // k.c, android.graphics.drawable.Drawable
        public boolean setVisible(boolean z3, boolean z4) {
            if (this.f4375c) {
                return super.setVisible(z3, z4);
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
            rVar.f4374o = null;
            rVar.removeCallbacks(this);
        }

        public void b() {
            r.this.post(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            r rVar = r.this;
            rVar.f4374o = null;
            rVar.drawableStateChanged();
        }
    }

    public r(Context context, boolean z3) {
        super(context, null, R$attr.dropDownListViewStyle);
        this.f4361b = new Rect();
        this.f4362c = 0;
        this.f4363d = 0;
        this.f4364e = 0;
        this.f4365f = 0;
        this.f4370k = z3;
        setCacheColorHint(0);
        try {
            Field declaredField = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.f4367h = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
        }
    }

    private void setSelectorEnabled(boolean z3) {
        a aVar = this.f4368i;
        if (aVar != null) {
            aVar.c(z3);
        }
    }

    public final void a() {
        this.f4371l = false;
        setPressed(false);
        drawableStateChanged();
        View childAt = getChildAt(this.f4366g - getFirstVisiblePosition());
        if (childAt != null) {
            childAt.setPressed(false);
        }
        l0.x xVar = this.f4372m;
        if (xVar != null) {
            xVar.b();
            this.f4372m = null;
        }
    }

    public final void b(View view, int i3) {
        performItemClick(view, i3, getItemIdAtPosition(i3));
    }

    public final void c(Canvas canvas) {
        Drawable selector;
        if (this.f4361b.isEmpty() || (selector = getSelector()) == null) {
            return;
        }
        selector.setBounds(this.f4361b);
        selector.draw(canvas);
    }

    public int d(int i3, int i4, int i5, int i6, int i7) {
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
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        View view = null;
        while (i8 < count) {
            int itemViewType = adapter.getItemViewType(i8);
            if (itemViewType != i9) {
                view = null;
                i9 = itemViewType;
            }
            view = adapter.getView(i8, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            int i11 = layoutParams.height;
            view.measure(i3, i11 > 0 ? View.MeasureSpec.makeMeasureSpec(i11, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED) : View.MeasureSpec.makeMeasureSpec(0, 0));
            view.forceLayout();
            if (i8 > 0) {
                measuredHeight += dividerHeight;
            }
            measuredHeight += view.getMeasuredHeight();
            if (measuredHeight >= i6) {
                return (i7 < 0 || i8 <= i7 || i10 <= 0 || measuredHeight == i6) ? i6 : i10;
            }
            if (i7 >= 0 && i8 >= i7) {
                i10 = measuredHeight;
            }
            i8++;
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
        if (this.f4374o != null) {
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
            n0.f r9 = r7.f4373n
            if (r9 != 0) goto L5a
            n0.f r9 = new n0.f
            r9.<init>(r7)
            r7.f4373n = r9
        L5a:
            n0.f r9 = r7.f4373n
            r9.m(r2)
            n0.f r9 = r7.f4373n
            r9.onTouch(r7, r8)
            goto L6c
        L65:
            n0.f r8 = r7.f4373n
            if (r8 == 0) goto L6c
            r8.m(r1)
        L6c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: o.r.e(android.view.MotionEvent, int):boolean");
    }

    public final void f(int i3, View view) {
        Rect rect = this.f4361b;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        rect.left -= this.f4362c;
        rect.top -= this.f4363d;
        rect.right += this.f4364e;
        rect.bottom += this.f4365f;
        try {
            boolean z3 = this.f4367h.getBoolean(this);
            if (view.isEnabled() != z3) {
                this.f4367h.set(this, Boolean.valueOf(!z3));
                if (i3 != -1) {
                    refreshDrawableState();
                }
            }
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
    }

    public final void g(int i3, View view) {
        Drawable selector = getSelector();
        boolean z3 = (selector == null || i3 == -1) ? false : true;
        if (z3) {
            selector.setVisible(false, false);
        }
        f(i3, view);
        if (z3) {
            Rect rect = this.f4361b;
            float fExactCenterX = rect.exactCenterX();
            float fExactCenterY = rect.exactCenterY();
            selector.setVisible(getVisibility() == 0, false);
            e0.a.k(selector, fExactCenterX, fExactCenterY);
        }
    }

    public final void h(int i3, View view, float f3, float f4) {
        g(i3, view);
        Drawable selector = getSelector();
        if (selector == null || i3 == -1) {
            return;
        }
        e0.a.k(selector, f3, f4);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean hasFocus() {
        return this.f4370k || super.hasFocus();
    }

    @Override // android.view.View
    public boolean hasWindowFocus() {
        return this.f4370k || super.hasWindowFocus();
    }

    public final void i(View view, int i3, float f3, float f4) {
        View childAt;
        this.f4371l = true;
        int i4 = Build.VERSION.SDK_INT;
        if (i4 >= 21) {
            drawableHotspotChanged(f3, f4);
        }
        if (!isPressed()) {
            setPressed(true);
        }
        layoutChildren();
        int i5 = this.f4366g;
        if (i5 != -1 && (childAt = getChildAt(i5 - getFirstVisiblePosition())) != null && childAt != view && childAt.isPressed()) {
            childAt.setPressed(false);
        }
        this.f4366g = i3;
        float left = f3 - view.getLeft();
        float top = f4 - view.getTop();
        if (i4 >= 21) {
            view.drawableHotspotChanged(left, top);
        }
        if (!view.isPressed()) {
            view.setPressed(true);
        }
        h(i3, view, f3, f4);
        setSelectorEnabled(false);
        refreshDrawableState();
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.f4370k || super.isFocused();
    }

    @Override // android.view.View
    public boolean isInTouchMode() {
        return (this.f4370k && this.f4369j) || super.isInTouchMode();
    }

    public final boolean j() {
        return this.f4371l;
    }

    public final void k() {
        Drawable selector = getSelector();
        if (selector != null && j() && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        this.f4374o = null;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        if (Build.VERSION.SDK_INT < 26) {
            return super.onHoverEvent(motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.f4374o == null) {
            b bVar = new b();
            this.f4374o = bVar;
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
            this.f4366g = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        b bVar = this.f4374o;
        if (bVar != null) {
            bVar.a();
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setListSelectionHidden(boolean z3) {
        this.f4369j = z3;
    }

    @Override // android.widget.AbsListView
    public void setSelector(Drawable drawable) {
        a aVar = drawable != null ? new a(drawable) : null;
        this.f4368i = aVar;
        super.setSelector(aVar);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.f4362c = rect.left;
        this.f4363d = rect.top;
        this.f4364e = rect.right;
        this.f4365f = rect.bottom;
    }
}
