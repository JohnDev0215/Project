package r;

import B5.RunnableC0079c;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.stingray.drivemodedetector.R;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: renamed from: r.T, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2231T extends ListView {
    public final Rect l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f21446m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f21447n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f21448p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f21449q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public C2229Q f21450r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f21451s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final boolean f21452t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f21453u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public I1.d f21454v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public RunnableC0079c f21455w;

    public AbstractC2231T(Context context, boolean z6) {
        super(context, null, R.attr.dropDownListViewStyle);
        this.l = new Rect();
        this.f21446m = 0;
        this.f21447n = 0;
        this.o = 0;
        this.f21448p = 0;
        this.f21452t = z6;
        setCacheColorHint(0);
    }

    public final int a(int i7, int i10) {
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
        int i11 = 0;
        View view = null;
        for (int i12 = 0; i12 < count; i12++) {
            int itemViewType = adapter.getItemViewType(i12);
            if (itemViewType != i11) {
                view = null;
                i11 = itemViewType;
            }
            view = adapter.getView(i12, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            int i13 = layoutParams.height;
            view.measure(i7, i13 > 0 ? View.MeasureSpec.makeMeasureSpec(i13, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED) : View.MeasureSpec.makeMeasureSpec(0, 0));
            view.forceLayout();
            if (i12 > 0) {
                measuredHeight += dividerHeight;
            }
            measuredHeight += view.getMeasuredHeight();
            if (measuredHeight >= i10) {
                return i10;
            }
        }
        return measuredHeight;
    }

    /* JADX WARN: Removed duplicated region for block: B:84:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean b(android.view.MotionEvent r17, int r18) {
        /*
            Method dump skipped, instruction units count: 393
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: r.AbstractC2231T.b(android.view.MotionEvent, int):boolean");
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        Drawable selector;
        Rect rect = this.l;
        if (!rect.isEmpty() && (selector = getSelector()) != null) {
            selector.setBounds(rect);
            selector.draw(canvas);
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        if (this.f21455w != null) {
            return;
        }
        super.drawableStateChanged();
        C2229Q c2229q = this.f21450r;
        if (c2229q != null) {
            c2229q.f21444m = true;
        }
        Drawable selector = getSelector();
        if (selector != null && this.f21453u && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean hasFocus() {
        return this.f21452t || super.hasFocus();
    }

    @Override // android.view.View
    public final boolean hasWindowFocus() {
        return this.f21452t || super.hasWindowFocus();
    }

    @Override // android.view.View
    public final boolean isFocused() {
        return this.f21452t || super.isFocused();
    }

    @Override // android.view.View
    public final boolean isInTouchMode() {
        return (this.f21452t && this.f21451s) || super.isInTouchMode();
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        this.f21455w = null;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int i7 = Build.VERSION.SDK_INT;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.f21455w == null) {
            RunnableC0079c runnableC0079c = new RunnableC0079c(this, 27);
            this.f21455w = runnableC0079c;
            post(runnableC0079c);
        }
        boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
        if (actionMasked == 9 || actionMasked == 7) {
            int iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            if (iPointToPosition != -1 && iPointToPosition != getSelectedItemPosition()) {
                View childAt = getChildAt(iPointToPosition - getFirstVisiblePosition());
                if (childAt.isEnabled()) {
                    requestFocus();
                    if (i7 < 30 || !AbstractC2227O.d) {
                        setSelectionFromTop(iPointToPosition, childAt.getTop() - getTop());
                    } else {
                        try {
                            AbstractC2227O.f21442a.invoke(this, Integer.valueOf(iPointToPosition), childAt, Boolean.FALSE, -1, -1);
                            AbstractC2227O.f21443b.invoke(this, Integer.valueOf(iPointToPosition));
                            AbstractC2227O.c.invoke(this, Integer.valueOf(iPointToPosition));
                        } catch (IllegalAccessException e10) {
                            e10.printStackTrace();
                        } catch (InvocationTargetException e11) {
                            e11.printStackTrace();
                        }
                    }
                }
                Drawable selector = getSelector();
                if (selector != null && this.f21453u && isPressed()) {
                    selector.setState(getDrawableState());
                }
            }
        } else {
            setSelection(-1);
        }
        return zOnHoverEvent;
    }

    @Override // android.widget.AbsListView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f21449q = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        RunnableC0079c runnableC0079c = this.f21455w;
        if (runnableC0079c != null) {
            AbstractC2231T abstractC2231T = (AbstractC2231T) runnableC0079c.f673m;
            abstractC2231T.f21455w = null;
            abstractC2231T.removeCallbacks(runnableC0079c);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setListSelectionHidden(boolean z6) {
        this.f21451s = z6;
    }

    @Override // android.widget.AbsListView
    public void setSelector(Drawable drawable) {
        C2229Q c2229q = null;
        if (drawable != null) {
            C2229Q c2229q2 = new C2229Q();
            Drawable drawable2 = c2229q2.l;
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            c2229q2.l = drawable;
            drawable.setCallback(c2229q2);
            c2229q2.f21444m = true;
            c2229q = c2229q2;
        }
        this.f21450r = c2229q;
        super.setSelector(c2229q);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.f21446m = rect.left;
        this.f21447n = rect.top;
        this.o = rect.right;
        this.f21448p = rect.bottom;
    }
}
