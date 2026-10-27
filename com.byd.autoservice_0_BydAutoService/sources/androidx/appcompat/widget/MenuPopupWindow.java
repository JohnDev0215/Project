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
import n.f;
import n.g;
import n.i;
import o.r;
import o.u;

/* JADX INFO: loaded from: classes.dex */
public class MenuPopupWindow extends ListPopupWindow implements u {
    public static Method K;
    public u L;

    public static class MenuDropDownListView extends r {

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public final int f560p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public final int f561q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public u f562r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public MenuItem f563s;

        public MenuDropDownListView(Context context, boolean z3) {
            super(context, z3);
            Configuration configuration = context.getResources().getConfiguration();
            if (Build.VERSION.SDK_INT < 17 || 1 != configuration.getLayoutDirection()) {
                this.f560p = 22;
                this.f561q = 21;
            } else {
                this.f560p = 21;
                this.f561q = 22;
            }
        }

        @Override // o.r
        public /* bridge */ /* synthetic */ int d(int i3, int i4, int i5, int i6, int i7) {
            return super.d(i3, i4, i5, i6, i7);
        }

        @Override // o.r
        public /* bridge */ /* synthetic */ boolean e(MotionEvent motionEvent, int i3) {
            return super.e(motionEvent, i3);
        }

        @Override // o.r, android.view.ViewGroup, android.view.View
        public /* bridge */ /* synthetic */ boolean hasFocus() {
            return super.hasFocus();
        }

        @Override // o.r, android.view.View
        public /* bridge */ /* synthetic */ boolean hasWindowFocus() {
            return super.hasWindowFocus();
        }

        @Override // o.r, android.view.View
        public /* bridge */ /* synthetic */ boolean isFocused() {
            return super.isFocused();
        }

        @Override // o.r, android.view.View
        public /* bridge */ /* synthetic */ boolean isInTouchMode() {
            return super.isInTouchMode();
        }

        @Override // o.r, android.view.View
        public boolean onHoverEvent(MotionEvent motionEvent) {
            int headersCount;
            f fVar;
            int iPointToPosition;
            int i3;
            if (this.f562r != null) {
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
                if (motionEvent.getAction() != 10 && (iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) != -1 && (i3 = iPointToPosition - headersCount) >= 0 && i3 < fVar.getCount()) {
                    item = fVar.getItem(i3);
                }
                MenuItem menuItem = this.f563s;
                if (menuItem != item) {
                    g gVarB = fVar.b();
                    if (menuItem != null) {
                        this.f562r.i(gVarB, menuItem);
                    }
                    this.f563s = item;
                    if (item != null) {
                        this.f562r.a(gVarB, item);
                    }
                }
            }
            return super.onHoverEvent(motionEvent);
        }

        @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i3, KeyEvent keyEvent) {
            ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
            if (listMenuItemView != null && i3 == this.f560p) {
                if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                    performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            }
            if (listMenuItemView == null || i3 != this.f561q) {
                return super.onKeyDown(i3, keyEvent);
            }
            setSelection(-1);
            ((f) getAdapter()).b().e(false);
            return true;
        }

        @Override // o.r, android.widget.AbsListView, android.view.View
        public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
            return super.onTouchEvent(motionEvent);
        }

        public void setHoverListener(u uVar) {
            this.f562r = uVar;
        }

        @Override // o.r, android.widget.AbsListView
        public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
            super.setSelector(drawable);
        }
    }

    static {
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                K = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
            }
        } catch (NoSuchMethodException unused) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    public MenuPopupWindow(Context context, AttributeSet attributeSet, int i3, int i4) {
        super(context, attributeSet, i3, i4);
    }

    public void R(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.J.setEnterTransition((Transition) obj);
        }
    }

    public void S(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.J.setExitTransition((Transition) obj);
        }
    }

    public void T(u uVar) {
        this.L = uVar;
    }

    public void U(boolean z3) {
        if (Build.VERSION.SDK_INT > 28) {
            this.J.setTouchModal(z3);
            return;
        }
        Method method = K;
        if (method != null) {
            try {
                method.invoke(this.J, Boolean.valueOf(z3));
            } catch (Exception unused) {
                Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
            }
        }
    }

    @Override // o.u
    public void a(g gVar, MenuItem menuItem) {
        u uVar = this.L;
        if (uVar != null) {
            uVar.a(gVar, menuItem);
        }
    }

    @Override // o.u
    public void i(g gVar, MenuItem menuItem) {
        u uVar = this.L;
        if (uVar != null) {
            uVar.i(gVar, menuItem);
        }
    }

    @Override // androidx.appcompat.widget.ListPopupWindow
    public r s(Context context, boolean z3) {
        MenuDropDownListView menuDropDownListView = new MenuDropDownListView(context, z3);
        menuDropDownListView.setHoverListener(this);
        return menuDropDownListView;
    }
}
