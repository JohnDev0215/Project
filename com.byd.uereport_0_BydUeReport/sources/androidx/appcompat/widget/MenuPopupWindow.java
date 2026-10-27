package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
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
import q.f;
import q.g;
import q.i;
import r.r;
import r.u;

/* JADX INFO: loaded from: classes.dex */
public class MenuPopupWindow extends ListPopupWindow implements u {
    public static Method M;
    public u N;

    public static class MenuDropDownListView extends r {

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public final int f659r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public final int f660s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public u f661t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public MenuItem f662u;

        public MenuDropDownListView(Context context, boolean z7) {
            super(context, z7);
            Configuration configuration = context.getResources().getConfiguration();
            if (Build.VERSION.SDK_INT < 17 || 1 != configuration.getLayoutDirection()) {
                this.f659r = 22;
                this.f660s = 21;
            } else {
                this.f659r = 21;
                this.f660s = 22;
            }
        }

        @Override // r.r
        public /* bridge */ /* synthetic */ int d(int i8, int i9, int i10, int i11, int i12) {
            return super.d(i8, i9, i10, i11, i12);
        }

        @Override // r.r
        public /* bridge */ /* synthetic */ boolean e(MotionEvent motionEvent, int i8) {
            return super.e(motionEvent, i8);
        }

        @Override // r.r, android.view.ViewGroup, android.view.View
        public /* bridge */ /* synthetic */ boolean hasFocus() {
            return super.hasFocus();
        }

        @Override // r.r, android.view.View
        public /* bridge */ /* synthetic */ boolean hasWindowFocus() {
            return super.hasWindowFocus();
        }

        @Override // r.r, android.view.View
        public /* bridge */ /* synthetic */ boolean isFocused() {
            return super.isFocused();
        }

        @Override // r.r, android.view.View
        public /* bridge */ /* synthetic */ boolean isInTouchMode() {
            return super.isInTouchMode();
        }

        @Override // r.r, android.view.View
        public boolean onHoverEvent(MotionEvent motionEvent) {
            int headersCount;
            f fVar;
            int iPointToPosition;
            int i8;
            if (this.f661t != null) {
                ListAdapter adapter = getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                    headersCount = headerViewListAdapter.getHeadersCount();
                    fVar = (f) headerViewListAdapter.getWrappedAdapter();
                } else {
                    headersCount = 0;
                    fVar = (f) adapter;
                }
                i item = null;
                if (motionEvent.getAction() != 10 && (iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) != -1 && (i8 = iPointToPosition - headersCount) >= 0 && i8 < fVar.getCount()) {
                    item = fVar.getItem(i8);
                }
                MenuItem menuItem = this.f662u;
                if (menuItem != item) {
                    g gVarB = fVar.b();
                    if (menuItem != null) {
                        this.f661t.f(gVarB, menuItem);
                    }
                    this.f662u = item;
                    if (item != null) {
                        this.f661t.a(gVarB, item);
                    }
                }
            }
            return super.onHoverEvent(motionEvent);
        }

        @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i8, KeyEvent keyEvent) {
            ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
            if (listMenuItemView != null && i8 == this.f659r) {
                if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                    performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            }
            if (listMenuItemView == null || i8 != this.f660s) {
                return super.onKeyDown(i8, keyEvent);
            }
            setSelection(-1);
            ((f) getAdapter()).b().e(false);
            return true;
        }

        @Override // r.r, android.widget.AbsListView, android.view.View
        public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
            return super.onTouchEvent(motionEvent);
        }

        public void setHoverListener(u uVar) {
            this.f661t = uVar;
        }

        @Override // r.r, android.widget.AbsListView
        public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
            super.setSelector(drawable);
        }
    }

    static {
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                M = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
            }
        } catch (NoSuchMethodException unused) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    public MenuPopupWindow(Context context, AttributeSet attributeSet, int i8, int i9) {
        super(context, attributeSet, i8, i9);
    }

    public void R(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.L.setEnterTransition((Transition) obj);
        }
    }

    public void S(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.L.setExitTransition((Transition) obj);
        }
    }

    public void T(u uVar) {
        this.N = uVar;
    }

    public void U(boolean z7) {
        if (Build.VERSION.SDK_INT > 28) {
            this.L.setTouchModal(z7);
            return;
        }
        Method method = M;
        if (method != null) {
            try {
                method.invoke(this.L, Boolean.valueOf(z7));
            } catch (Exception unused) {
                Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
            }
        }
    }

    @Override // r.u
    public void a(g gVar, MenuItem menuItem) {
        u uVar = this.N;
        if (uVar != null) {
            uVar.a(gVar, menuItem);
        }
    }

    @Override // r.u
    public void f(g gVar, MenuItem menuItem) {
        u uVar = this.N;
        if (uVar != null) {
            uVar.f(gVar, menuItem);
        }
    }

    @Override // androidx.appcompat.widget.ListPopupWindow
    public r s(Context context, boolean z7) {
        MenuDropDownListView menuDropDownListView = new MenuDropDownListView(context, z7);
        menuDropDownListView.setHoverListener(this);
        return menuDropDownListView;
    }
}
