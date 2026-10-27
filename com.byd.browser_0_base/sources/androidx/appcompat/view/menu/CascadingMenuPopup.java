package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.widget.MenuItemHoverListener;
import androidx.appcompat.widget.MenuPopupWindow;
import defpackage.in3;
import defpackage.om3;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
final class CascadingMenuPopup extends MenuPopup implements View.OnKeyListener, PopupWindow.OnDismissListener {
    public static final int F = R.layout.abc_cascading_menu_item_layout;
    public boolean A;
    public MenuPresenter.Callback B;
    public ViewTreeObserver C;
    public PopupWindow.OnDismissListener D;
    public boolean E;
    public final Context f;
    public final int g;
    public final int h;
    public final int i;
    public final boolean j;
    public final Handler k;
    public View s;
    public View t;
    public int u;
    public boolean v;
    public boolean w;
    public int x;
    public int y;
    public final ArrayList l = new ArrayList();
    public final ArrayList m = new ArrayList();
    public final ViewTreeObserver.OnGlobalLayoutListener n = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: androidx.appcompat.view.menu.CascadingMenuPopup.1
        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public final void onGlobalLayout() {
            CascadingMenuPopup cascadingMenuPopup = CascadingMenuPopup.this;
            if (cascadingMenuPopup.b()) {
                ArrayList arrayList = cascadingMenuPopup.m;
                if (arrayList.size() <= 0 || ((CascadingMenuInfo) arrayList.get(0)).a.C) {
                    return;
                }
                View view = cascadingMenuPopup.t;
                if (view == null || !view.isShown()) {
                    cascadingMenuPopup.dismiss();
                    return;
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((CascadingMenuInfo) it.next()).a.a();
                }
            }
        }
    };
    public final View.OnAttachStateChangeListener o = new View.OnAttachStateChangeListener() { // from class: androidx.appcompat.view.menu.CascadingMenuPopup.2
        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            CascadingMenuPopup cascadingMenuPopup = CascadingMenuPopup.this;
            ViewTreeObserver viewTreeObserver = cascadingMenuPopup.C;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    cascadingMenuPopup.C = view.getViewTreeObserver();
                }
                cascadingMenuPopup.C.removeGlobalOnLayoutListener(cascadingMenuPopup.n);
            }
            view.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
        }
    };
    public final MenuItemHoverListener p = new MenuItemHoverListener() { // from class: androidx.appcompat.view.menu.CascadingMenuPopup.3
        @Override // androidx.appcompat.widget.MenuItemHoverListener
        public final void c(final MenuBuilder menuBuilder, final MenuItemImpl menuItemImpl) {
            CascadingMenuPopup cascadingMenuPopup = CascadingMenuPopup.this;
            cascadingMenuPopup.k.removeCallbacksAndMessages(null);
            ArrayList arrayList = cascadingMenuPopup.m;
            int size = arrayList.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    i = -1;
                    break;
                } else if (menuBuilder == ((CascadingMenuInfo) arrayList.get(i)).b) {
                    break;
                } else {
                    i++;
                }
            }
            if (i == -1) {
                return;
            }
            int i2 = i + 1;
            final CascadingMenuInfo cascadingMenuInfo = i2 < arrayList.size() ? (CascadingMenuInfo) arrayList.get(i2) : null;
            cascadingMenuPopup.k.postAtTime(new Runnable() { // from class: androidx.appcompat.view.menu.CascadingMenuPopup.3.1
                @Override // java.lang.Runnable
                public final void run() {
                    CascadingMenuInfo cascadingMenuInfo2 = cascadingMenuInfo;
                    if (cascadingMenuInfo2 != null) {
                        AnonymousClass3 anonymousClass3 = AnonymousClass3.this;
                        CascadingMenuPopup.this.E = true;
                        cascadingMenuInfo2.b.c(false);
                        CascadingMenuPopup.this.E = false;
                    }
                    MenuItem menuItem = menuItemImpl;
                    if (menuItem.isEnabled() && menuItem.hasSubMenu()) {
                        menuBuilder.q(menuItem, null, 4);
                    }
                }
            }, menuBuilder, SystemClock.uptimeMillis() + 200);
        }

        @Override // androidx.appcompat.widget.MenuItemHoverListener
        public final void d(MenuBuilder menuBuilder, MenuItem menuItem) {
            CascadingMenuPopup.this.k.removeCallbacksAndMessages(menuBuilder);
        }
    };
    public int q = 0;
    public int r = 0;
    public boolean z = false;

    public static class CascadingMenuInfo {
        public final MenuPopupWindow a;
        public final MenuBuilder b;
        public final int c;

        public CascadingMenuInfo(MenuPopupWindow menuPopupWindow, MenuBuilder menuBuilder, int i) {
            this.a = menuPopupWindow;
            this.b = menuBuilder;
            this.c = i;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface HorizPosition {
    }

    public CascadingMenuPopup(Context context, View view, int i, int i2, boolean z) {
        this.f = context;
        this.s = view;
        this.h = i;
        this.i = i2;
        this.j = z;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        this.u = om3.e.c(view) != 1 ? 1 : 0;
        Resources resources = context.getResources();
        this.g = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.k = new Handler();
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public final void a() {
        if (b()) {
            return;
        }
        ArrayList arrayList = this.l;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            w((MenuBuilder) it.next());
        }
        arrayList.clear();
        View view = this.s;
        this.t = view;
        if (view != null) {
            boolean z = this.C == null;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.C = viewTreeObserver;
            if (z) {
                viewTreeObserver.addOnGlobalLayoutListener(this.n);
            }
            this.t.addOnAttachStateChangeListener(this.o);
        }
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public final boolean b() {
        ArrayList arrayList = this.m;
        return arrayList.size() > 0 && ((CascadingMenuInfo) arrayList.get(0)).a.b();
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final void c(MenuBuilder menuBuilder, boolean z) {
        ArrayList arrayList = this.m;
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            } else if (menuBuilder == ((CascadingMenuInfo) arrayList.get(i)).b) {
                break;
            } else {
                i++;
            }
        }
        if (i < 0) {
            return;
        }
        int i2 = i + 1;
        if (i2 < arrayList.size()) {
            ((CascadingMenuInfo) arrayList.get(i2)).b.c(false);
        }
        CascadingMenuInfo cascadingMenuInfo = (CascadingMenuInfo) arrayList.remove(i);
        cascadingMenuInfo.b.r(this);
        boolean z2 = this.E;
        MenuPopupWindow menuPopupWindow = cascadingMenuInfo.a;
        if (z2) {
            menuPopupWindow.D.setExitTransition(null);
            menuPopupWindow.D.setAnimationStyle(0);
        }
        menuPopupWindow.dismiss();
        int size2 = arrayList.size();
        if (size2 > 0) {
            this.u = ((CascadingMenuInfo) arrayList.get(size2 - 1)).c;
        } else {
            View view = this.s;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            this.u = om3.e.c(view) == 1 ? 0 : 1;
        }
        if (size2 != 0) {
            if (z) {
                ((CascadingMenuInfo) arrayList.get(0)).b.c(false);
                return;
            }
            return;
        }
        dismiss();
        MenuPresenter.Callback callback = this.B;
        if (callback != null) {
            callback.c(menuBuilder, true);
        }
        ViewTreeObserver viewTreeObserver = this.C;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.C.removeGlobalOnLayoutListener(this.n);
            }
            this.C = null;
        }
        this.t.removeOnAttachStateChangeListener(this.o);
        this.D.onDismiss();
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final void d(boolean z) {
        Iterator it = this.m.iterator();
        while (it.hasNext()) {
            ListAdapter adapter = ((CascadingMenuInfo) it.next()).a.g.getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                adapter = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
            }
            ((MenuAdapter) adapter).notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public final void dismiss() {
        ArrayList arrayList = this.m;
        int size = arrayList.size();
        if (size <= 0) {
            return;
        }
        CascadingMenuInfo[] cascadingMenuInfoArr = (CascadingMenuInfo[]) arrayList.toArray(new CascadingMenuInfo[size]);
        while (true) {
            size--;
            if (size < 0) {
                return;
            }
            CascadingMenuInfo cascadingMenuInfo = cascadingMenuInfoArr[size];
            if (cascadingMenuInfo.a.b()) {
                cascadingMenuInfo.a.dismiss();
            }
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final boolean e() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final void f(MenuPresenter.Callback callback) {
        this.B = callback;
    }

    @Override // androidx.appcompat.view.menu.ShowableListMenu
    public final ListView j() {
        ArrayList arrayList = this.m;
        if (arrayList.isEmpty()) {
            return null;
        }
        return ((CascadingMenuInfo) arrayList.get(arrayList.size() - 1)).a.g;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final boolean k(SubMenuBuilder subMenuBuilder) {
        for (CascadingMenuInfo cascadingMenuInfo : this.m) {
            if (subMenuBuilder == cascadingMenuInfo.b) {
                cascadingMenuInfo.a.g.requestFocus();
                return true;
            }
        }
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        n(subMenuBuilder);
        MenuPresenter.Callback callback = this.B;
        if (callback != null) {
            callback.d(subMenuBuilder);
        }
        return true;
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final Parcelable l() {
        return null;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public final void n(MenuBuilder menuBuilder) {
        menuBuilder.b(this, this.f);
        if (b()) {
            w(menuBuilder);
        } else {
            this.l.add(menuBuilder);
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        CascadingMenuInfo cascadingMenuInfo;
        ArrayList arrayList = this.m;
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                cascadingMenuInfo = null;
                break;
            }
            cascadingMenuInfo = (CascadingMenuInfo) arrayList.get(i);
            if (!cascadingMenuInfo.a.b()) {
                break;
            } else {
                i++;
            }
        }
        if (cascadingMenuInfo != null) {
            cascadingMenuInfo.b.c(false);
        }
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public final void p(View view) {
        if (this.s != view) {
            this.s = view;
            int i = this.q;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            this.r = Gravity.getAbsoluteGravity(i, om3.e.c(view));
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public final void q(boolean z) {
        this.z = z;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public final void r(int i) {
        if (this.q != i) {
            this.q = i;
            View view = this.s;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            this.r = Gravity.getAbsoluteGravity(i, om3.e.c(view));
        }
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public final void s(int i) {
        this.v = true;
        this.x = i;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.D = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public final void t(boolean z) {
        this.A = z;
    }

    @Override // androidx.appcompat.view.menu.MenuPopup
    public final void u(int i) {
        this.w = true;
        this.y = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0127  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void w(androidx.appcompat.view.menu.MenuBuilder r18) {
        /*
            Method dump skipped, instruction units count: 436
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.CascadingMenuPopup.w(androidx.appcompat.view.menu.MenuBuilder):void");
    }

    @Override // androidx.appcompat.view.menu.MenuPresenter
    public final void i(Parcelable parcelable) {
    }
}
