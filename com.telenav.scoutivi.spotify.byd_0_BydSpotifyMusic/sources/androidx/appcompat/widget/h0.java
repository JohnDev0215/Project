package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.transition.Transition;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.ListMenuItemView;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: MenuPopupWindow.java */
/* JADX INFO: loaded from: classes.dex */
public class h0 extends f0 implements g0 {
    private static Method O;
    private g0 N;

    /* JADX INFO: compiled from: MenuPopupWindow.java */
    public static class a extends c0 {

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        final int f1076t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        final int f1077u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        private g0 f1078v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        private MenuItem f1079w;

        public a(Context context, boolean z6) {
            super(context, z6);
            if (1 == context.getResources().getConfiguration().getLayoutDirection()) {
                this.f1076t = 21;
                this.f1077u = 22;
            } else {
                this.f1076t = 22;
                this.f1077u = 21;
            }
        }

        @Override // androidx.appcompat.widget.c0
        public /* bridge */ /* synthetic */ int d(int i7, int i8, int i9, int i10, int i11) {
            return super.d(i7, i8, i9, i10, i11);
        }

        @Override // androidx.appcompat.widget.c0
        public /* bridge */ /* synthetic */ boolean e(MotionEvent motionEvent, int i7) {
            return super.e(motionEvent, i7);
        }

        @Override // androidx.appcompat.widget.c0, android.view.ViewGroup, android.view.View
        public /* bridge */ /* synthetic */ boolean hasFocus() {
            return super.hasFocus();
        }

        @Override // androidx.appcompat.widget.c0, android.view.View
        public /* bridge */ /* synthetic */ boolean hasWindowFocus() {
            return super.hasWindowFocus();
        }

        @Override // androidx.appcompat.widget.c0, android.view.View
        public /* bridge */ /* synthetic */ boolean isFocused() {
            return super.isFocused();
        }

        @Override // androidx.appcompat.widget.c0, android.view.View
        public /* bridge */ /* synthetic */ boolean isInTouchMode() {
            return super.isInTouchMode();
        }

        @Override // androidx.appcompat.widget.c0, android.view.View
        public boolean onHoverEvent(MotionEvent motionEvent) {
            int headersCount;
            androidx.appcompat.view.menu.d dVar;
            int iPointToPosition;
            int i7;
            if (this.f1078v != null) {
                ListAdapter adapter = getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                    headersCount = headerViewListAdapter.getHeadersCount();
                    dVar = (androidx.appcompat.view.menu.d) headerViewListAdapter.getWrappedAdapter();
                } else {
                    headersCount = 0;
                    dVar = (androidx.appcompat.view.menu.d) adapter;
                }
                androidx.appcompat.view.menu.g item = null;
                if (motionEvent.getAction() != 10 && (iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) != -1 && (i7 = iPointToPosition - headersCount) >= 0 && i7 < dVar.getCount()) {
                    item = dVar.getItem(i7);
                }
                MenuItem menuItem = this.f1079w;
                if (menuItem != item) {
                    androidx.appcompat.view.menu.e eVarB = dVar.b();
                    if (menuItem != null) {
                        this.f1078v.i(eVarB, menuItem);
                    }
                    this.f1079w = item;
                    if (item != null) {
                        this.f1078v.a(eVarB, item);
                    }
                }
            }
            return super.onHoverEvent(motionEvent);
        }

        @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i7, KeyEvent keyEvent) {
            ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
            if (listMenuItemView != null && i7 == this.f1076t) {
                if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                    performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            }
            if (listMenuItemView == null || i7 != this.f1077u) {
                return super.onKeyDown(i7, keyEvent);
            }
            setSelection(-1);
            ListAdapter adapter = getAdapter();
            (adapter instanceof HeaderViewListAdapter ? (androidx.appcompat.view.menu.d) ((HeaderViewListAdapter) adapter).getWrappedAdapter() : (androidx.appcompat.view.menu.d) adapter).b().e(false);
            return true;
        }

        @Override // androidx.appcompat.widget.c0, android.widget.AbsListView, android.view.View
        public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
            return super.onTouchEvent(motionEvent);
        }

        public void setHoverListener(g0 g0Var) {
            this.f1078v = g0Var;
        }

        @Override // androidx.appcompat.widget.c0, android.widget.AbsListView
        public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
            super.setSelector(drawable);
        }
    }

    static {
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                O = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
            }
        } catch (NoSuchMethodException unused) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    public h0(Context context, AttributeSet attributeSet, int i7, int i8) {
        super(context, attributeSet, i7, i8);
    }

    public void R(Object obj) {
        this.K.setEnterTransition((Transition) obj);
    }

    public void S(Object obj) {
        this.K.setExitTransition((Transition) obj);
    }

    public void T(g0 g0Var) {
        this.N = g0Var;
    }

    public void U(boolean z6) {
        if (Build.VERSION.SDK_INT > 28) {
            this.K.setTouchModal(z6);
            return;
        }
        Method method = O;
        if (method != null) {
            try {
                method.invoke(this.K, Boolean.valueOf(z6));
            } catch (Exception unused) {
                Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
            }
        }
    }

    @Override // androidx.appcompat.widget.g0
    public void a(androidx.appcompat.view.menu.e eVar, MenuItem menuItem) {
        g0 g0Var = this.N;
        if (g0Var != null) {
            g0Var.a(eVar, menuItem);
        }
    }

    @Override // androidx.appcompat.widget.g0
    public void i(androidx.appcompat.view.menu.e eVar, MenuItem menuItem) {
        g0 g0Var = this.N;
        if (g0Var != null) {
            g0Var.i(eVar, menuItem);
        }
    }

    @Override // androidx.appcompat.widget.f0
    c0 s(Context context, boolean z6) {
        a aVar = new a(context, z6);
        aVar.setHoverListener(this);
        return aVar;
    }
}
