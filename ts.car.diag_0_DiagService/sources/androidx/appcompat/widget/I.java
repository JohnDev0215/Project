package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.ListMenuItemView;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class I extends G implements H {
    private static Method L;
    private H K;

    /* JADX INFO: compiled from: caradapter-bug */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static class a extends D {
        final int q;
        final int r;
        private H s;
        private MenuItem t;

        public a(Context context, boolean z) {
            super(context, z);
            Configuration configuration = context.getResources().getConfiguration();
            if (Build.VERSION.SDK_INT < 17 || 1 != configuration.getLayoutDirection()) {
                this.q = 22;
                this.r = 21;
            } else {
                this.q = 21;
                this.r = 22;
            }
        }

        public void n(H h) {
            this.s = h;
        }

        @Override // androidx.appcompat.widget.D, android.view.View
        public boolean onHoverEvent(MotionEvent motionEvent) {
            int headersCount;
            androidx.appcompat.view.menu.e eVar;
            int iPointToPosition;
            int i;
            if (this.s != null) {
                ListAdapter adapter = getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                    headersCount = headerViewListAdapter.getHeadersCount();
                    eVar = (androidx.appcompat.view.menu.e) headerViewListAdapter.getWrappedAdapter();
                } else {
                    headersCount = 0;
                    eVar = (androidx.appcompat.view.menu.e) adapter;
                }
                androidx.appcompat.view.menu.h item = null;
                if (motionEvent.getAction() != 10 && (iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) != -1 && (i = iPointToPosition - headersCount) >= 0 && i < eVar.getCount()) {
                    item = eVar.getItem(i);
                }
                MenuItem menuItem = this.t;
                if (menuItem != item) {
                    androidx.appcompat.view.menu.f fVarB = eVar.b();
                    if (menuItem != null) {
                        this.s.h(fVarB, menuItem);
                    }
                    this.t = item;
                    if (item != null) {
                        this.s.e(fVarB, item);
                    }
                }
            }
            return super.onHoverEvent(motionEvent);
        }

        @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
            if (listMenuItemView != null && i == this.q) {
                if (listMenuItemView.isEnabled() && listMenuItemView.b().hasSubMenu()) {
                    performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            }
            if (listMenuItemView == null || i != this.r) {
                return super.onKeyDown(i, keyEvent);
            }
            setSelection(-1);
            ((androidx.appcompat.view.menu.e) getAdapter()).b().e(false);
            return true;
        }
    }

    static {
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                L = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
            }
        } catch (NoSuchMethodException unused) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    public I(@NonNull Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void N(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.G.setEnterTransition((Transition) obj);
        }
    }

    public void O(Object obj) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.G.setExitTransition((Transition) obj);
        }
    }

    public void P(H h) {
        this.K = h;
    }

    public void Q(boolean z) {
        if (Build.VERSION.SDK_INT > 28) {
            this.G.setTouchModal(z);
            return;
        }
        Method method = L;
        if (method != null) {
            try {
                method.invoke(this.G, Boolean.valueOf(z));
            } catch (Exception unused) {
                Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
            }
        }
    }

    @Override // androidx.appcompat.widget.H
    public void e(@NonNull androidx.appcompat.view.menu.f fVar, @NonNull MenuItem menuItem) {
        H h = this.K;
        if (h != null) {
            h.e(fVar, menuItem);
        }
    }

    @Override // androidx.appcompat.widget.H
    public void h(@NonNull androidx.appcompat.view.menu.f fVar, @NonNull MenuItem menuItem) {
        H h = this.K;
        if (h != null) {
            h.h(fVar, menuItem);
        }
    }

    @Override // androidx.appcompat.widget.G
    @NonNull
    D s(Context context, boolean z) {
        a aVar = new a(context, z);
        aVar.n(this);
        return aVar;
    }
}
