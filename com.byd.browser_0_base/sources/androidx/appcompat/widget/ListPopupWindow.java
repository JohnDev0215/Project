package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.ShowableListMenu;
import defpackage.in3;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ListPopupWindow implements ShowableListMenu {
    public final Rect A;
    public Rect B;
    public boolean C;
    public final PopupWindow D;
    public final Context e;
    public ListAdapter f;
    public DropDownListView g;
    public final int h;
    public int i;
    public int j;
    public int k;
    public final int l;
    public boolean m;
    public boolean n;
    public boolean o;
    public int p;
    public final int q;
    public DataSetObserver r;
    public View s;
    public AdapterView.OnItemClickListener t;
    public AdapterView.OnItemSelectedListener u;
    public final ResizePopupRunnable v;
    public final PopupTouchInterceptor w;
    public final PopupScrollListener x;
    public final ListSelectorHider y;
    public final Handler z;

    /* JADX INFO: renamed from: androidx.appcompat.widget.ListPopupWindow$1, reason: invalid class name */
    class AnonymousClass1 extends ForwardingListener {
        @Override // androidx.appcompat.widget.ForwardingListener
        public final /* bridge */ /* synthetic */ ShowableListMenu b() {
            return null;
        }
    }

    /* JADX INFO: renamed from: androidx.appcompat.widget.ListPopupWindow$2, reason: invalid class name */
    class AnonymousClass2 implements Runnable {
        public AnonymousClass2() {
            throw null;
        }

        @Override // java.lang.Runnable
        public final void run() {
            throw null;
        }
    }

    public static class Api24Impl {
        private Api24Impl() {
        }
    }

    public static class Api29Impl {
        private Api29Impl() {
        }
    }

    public class ListSelectorHider implements Runnable {
        public ListSelectorHider() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            DropDownListView dropDownListView = ListPopupWindow.this.g;
            if (dropDownListView != null) {
                dropDownListView.setListSelectionHidden(true);
                dropDownListView.requestLayout();
            }
        }
    }

    public class PopupDataSetObserver extends DataSetObserver {
        public PopupDataSetObserver() {
        }

        @Override // android.database.DataSetObserver
        public final void onChanged() {
            ListPopupWindow listPopupWindow = ListPopupWindow.this;
            if (listPopupWindow.b()) {
                listPopupWindow.a();
            }
        }

        @Override // android.database.DataSetObserver
        public final void onInvalidated() {
            ListPopupWindow.this.dismiss();
        }
    }

    public class PopupTouchInterceptor implements View.OnTouchListener {
        public PopupTouchInterceptor() {
        }

        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            PopupWindow popupWindow;
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            ListPopupWindow listPopupWindow = ListPopupWindow.this;
            if (action == 0 && (popupWindow = listPopupWindow.D) != null && popupWindow.isShowing() && x >= 0 && x < listPopupWindow.D.getWidth() && y >= 0 && y < listPopupWindow.D.getHeight()) {
                listPopupWindow.z.postDelayed(listPopupWindow.v, 250L);
                return false;
            }
            if (action != 1) {
                return false;
            }
            listPopupWindow.z.removeCallbacks(listPopupWindow.v);
            return false;
        }
    }

    public class ResizePopupRunnable implements Runnable {
        public ResizePopupRunnable() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            ListPopupWindow listPopupWindow = ListPopupWindow.this;
            DropDownListView dropDownListView = listPopupWindow.g;
            if (dropDownListView != null) {
                WeakHashMap<View, in3> weakHashMap = om3.a;
                if (!om3.g.b(dropDownListView) || listPopupWindow.g.getCount() <= listPopupWindow.g.getChildCount() || listPopupWindow.g.getChildCount() > listPopupWindow.q) {
                    return;
                }
                listPopupWindow.D.setInputMethodMode(2);
                listPopupWindow.a();
            }
        }
    }

    public ListPopupWindow(Context context) {
        this(context, null, R.attr.listPopupWindowStyle);
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public final void a() {
        int i;
        int paddingBottom;
        DropDownListView dropDownListView;
        DropDownListView dropDownListView2 = this.g;
        PopupWindow popupWindow = this.D;
        Context context = this.e;
        if (dropDownListView2 == null) {
            DropDownListView dropDownListViewE = e(context, !this.C);
            this.g = dropDownListViewE;
            dropDownListViewE.setAdapter(this.f);
            this.g.setOnItemClickListener(this.t);
            this.g.setFocusable(true);
            this.g.setFocusableInTouchMode(true);
            this.g.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: androidx.appcompat.widget.ListPopupWindow.3
                @Override // android.widget.AdapterView.OnItemSelectedListener
                public final void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
                    DropDownListView dropDownListView3;
                    if (i2 == -1 || (dropDownListView3 = ListPopupWindow.this.g) == null) {
                        return;
                    }
                    dropDownListView3.setListSelectionHidden(false);
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public final void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            this.g.setOnScrollListener(this.x);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.u;
            if (onItemSelectedListener != null) {
                this.g.setOnItemSelectedListener(onItemSelectedListener);
            }
            popupWindow.setContentView(this.g);
        }
        Drawable background = popupWindow.getBackground();
        Rect rect = this.A;
        if (background != null) {
            background.getPadding(rect);
            int i2 = rect.top;
            i = rect.bottom + i2;
            if (!this.m) {
                this.k = -i2;
            }
        } else {
            rect.setEmpty();
            i = 0;
        }
        int maxAvailableHeight = popupWindow.getMaxAvailableHeight(this.s, this.k, popupWindow.getInputMethodMode() == 2);
        int i3 = this.h;
        if (i3 == -1) {
            paddingBottom = maxAvailableHeight + i;
        } else {
            int i4 = this.i;
            int iA = this.g.a(i4 != -2 ? i4 != -1 ? View.MeasureSpec.makeMeasureSpec(i4, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED) : View.MeasureSpec.makeMeasureSpec(context.getResources().getDisplayMetrics().widthPixels - (rect.left + rect.right), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED) : View.MeasureSpec.makeMeasureSpec(context.getResources().getDisplayMetrics().widthPixels - (rect.left + rect.right), Integer.MIN_VALUE), maxAvailableHeight + 0);
            paddingBottom = iA + (iA > 0 ? this.g.getPaddingBottom() + this.g.getPaddingTop() + i + 0 : 0);
        }
        boolean z = popupWindow.getInputMethodMode() == 2;
        popupWindow.setWindowLayoutType(this.l);
        if (popupWindow.isShowing()) {
            View view = this.s;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            if (om3.g.b(view)) {
                int width = this.i;
                if (width == -1) {
                    width = -1;
                } else if (width == -2) {
                    width = this.s.getWidth();
                }
                if (i3 == -1) {
                    i3 = z ? paddingBottom : -1;
                    if (z) {
                        popupWindow.setWidth(this.i == -1 ? -1 : 0);
                        popupWindow.setHeight(0);
                    } else {
                        popupWindow.setWidth(this.i == -1 ? -1 : 0);
                        popupWindow.setHeight(-1);
                    }
                } else if (i3 == -2) {
                    i3 = paddingBottom;
                }
                popupWindow.setOutsideTouchable(true);
                View view2 = this.s;
                int i5 = this.j;
                int i6 = this.k;
                if (width < 0) {
                    width = -1;
                }
                popupWindow.update(view2, i5, i6, width, i3 < 0 ? -1 : i3);
                return;
            }
            return;
        }
        int width2 = this.i;
        if (width2 == -1) {
            width2 = -1;
        } else if (width2 == -2) {
            width2 = this.s.getWidth();
        }
        if (i3 == -1) {
            i3 = -1;
        } else if (i3 == -2) {
            i3 = paddingBottom;
        }
        popupWindow.setWidth(width2);
        popupWindow.setHeight(i3);
        popupWindow.setIsClippedToScreen(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchInterceptor(this.w);
        if (this.o) {
            popupWindow.setOverlapAnchor(this.n);
        }
        popupWindow.setEpicenterBounds(this.B);
        popupWindow.showAsDropDown(this.s, this.j, this.k, this.p);
        this.g.setSelection(-1);
        if ((!this.C || this.g.isInTouchMode()) && (dropDownListView = this.g) != null) {
            dropDownListView.setListSelectionHidden(true);
            dropDownListView.requestLayout();
        }
        if (this.C) {
            return;
        }
        this.z.post(this.y);
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public final boolean b() {
        return this.D.isShowing();
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public final void dismiss() {
        PopupWindow popupWindow = this.D;
        popupWindow.dismiss();
        popupWindow.setContentView(null);
        this.g = null;
        this.z.removeCallbacks(this.v);
    }

    public DropDownListView e(Context context, boolean z) {
        return new DropDownListView(context, z);
    }

    public final void f(int i) {
        Drawable background = this.D.getBackground();
        if (background == null) {
            this.i = i;
            return;
        }
        Rect rect = this.A;
        background.getPadding(rect);
        this.i = rect.left + rect.right + i;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public final ListView j() {
        return this.g;
    }

    public final int m() {
        return this.j;
    }

    public final void n(int i) {
        this.j = i;
    }

    public final Drawable p() {
        return this.D.getBackground();
    }

    public final void r(Drawable drawable) {
        this.D.setBackgroundDrawable(drawable);
    }

    public final void s(int i) {
        this.k = i;
        this.m = true;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.D.setOnDismissListener(onDismissListener);
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.t = onItemClickListener;
    }

    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.u = onItemSelectedListener;
    }

    public final int u() {
        if (this.m) {
            return this.k;
        }
        return 0;
    }

    public void v(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.r;
        if (dataSetObserver == null) {
            this.r = new PopupDataSetObserver();
        } else {
            ListAdapter listAdapter2 = this.f;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.r);
        }
        DropDownListView dropDownListView = this.g;
        if (dropDownListView != null) {
            dropDownListView.setAdapter(this.f);
        }
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listPopupWindowStyle);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i, int i2) {
        this.h = -2;
        this.i = -2;
        this.l = 1002;
        this.p = 0;
        this.q = Integer.MAX_VALUE;
        this.v = new ResizePopupRunnable();
        this.w = new PopupTouchInterceptor();
        this.x = new PopupScrollListener();
        this.y = new ListSelectorHider();
        this.A = new Rect();
        this.e = context;
        this.z = new Handler(context.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ListPopupWindow, i, i2);
        this.j = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        this.k = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.m = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        AppCompatPopupWindow appCompatPopupWindow = new AppCompatPopupWindow(context, attributeSet, i, i2);
        this.D = appCompatPopupWindow;
        appCompatPopupWindow.setInputMethodMode(1);
    }

    public class PopupScrollListener implements AbsListView.OnScrollListener {
        public PopupScrollListener() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public final void onScrollStateChanged(AbsListView absListView, int i) {
            if (i == 1) {
                ListPopupWindow listPopupWindow = ListPopupWindow.this;
                if ((listPopupWindow.D.getInputMethodMode() == 2) || listPopupWindow.D.getContentView() == null) {
                    return;
                }
                Handler handler = listPopupWindow.z;
                ResizePopupRunnable resizePopupRunnable = listPopupWindow.v;
                handler.removeCallbacks(resizePopupRunnable);
                resizePopupRunnable.run();
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }
    }
}
