package a.b.p;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import androidx.appcompat.view.menu.ListMenuItemView;

/* JADX INFO: loaded from: classes.dex */
public class m0 extends k0 implements l0 {
    public l0 C;

    public static class a extends f0 {
        public final int o;
        public final int p;
        public l0 q;
        public MenuItem r;

        public a(Context context, boolean z) {
            super(context, z);
            if (1 == context.getResources().getConfiguration().getLayoutDirection()) {
                this.o = 21;
                this.p = 22;
            } else {
                this.o = 22;
                this.p = 21;
            }
        }

        @Override // a.b.p.f0, android.view.View
        public boolean onHoverEvent(MotionEvent motionEvent) {
            int headersCount;
            int iPointToPosition;
            int i;
            if (this.q != null) {
                ListAdapter adapter = getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                    headersCount = headerViewListAdapter.getHeadersCount();
                    adapter = headerViewListAdapter.getWrappedAdapter();
                } else {
                    headersCount = 0;
                }
                a.b.o.i.f fVar = (a.b.o.i.f) adapter;
                a.b.o.i.i item = null;
                if (motionEvent.getAction() != 10 && (iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) != -1 && (i = iPointToPosition - headersCount) >= 0 && i < fVar.getCount()) {
                    item = fVar.getItem(i);
                }
                MenuItem menuItem = this.r;
                if (menuItem != item) {
                    a.b.o.i.g gVar = fVar.f170a;
                    if (menuItem != null) {
                        this.q.k(gVar, menuItem);
                    }
                    this.r = item;
                    if (item != null) {
                        this.q.g(gVar, item);
                    }
                }
            }
            return super.onHoverEvent(motionEvent);
        }

        @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
        public boolean onKeyDown(int i, KeyEvent keyEvent) {
            ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
            if (listMenuItemView != null && i == this.o) {
                if (listMenuItemView.isEnabled() && listMenuItemView.getItemData().hasSubMenu()) {
                    performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            }
            if (listMenuItemView == null || i != this.p) {
                return super.onKeyDown(i, keyEvent);
            }
            setSelection(-1);
            ((a.b.o.i.f) getAdapter()).f170a.c(false);
            return true;
        }

        public void setHoverListener(l0 l0Var) {
            this.q = l0Var;
        }

        @Override // a.b.p.f0, android.widget.AbsListView
        public /* bridge */ /* synthetic */ void setSelector(Drawable drawable) {
            super.setSelector(drawable);
        }
    }

    public m0(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, null, i, i2);
    }

    @Override // a.b.p.l0
    public void g(a.b.o.i.g gVar, MenuItem menuItem) {
        l0 l0Var = this.C;
        if (l0Var != null) {
            l0Var.g(gVar, menuItem);
        }
    }

    @Override // a.b.p.l0
    public void k(a.b.o.i.g gVar, MenuItem menuItem) {
        l0 l0Var = this.C;
        if (l0Var != null) {
            l0Var.k(gVar, menuItem);
        }
    }

    @Override // a.b.p.k0
    public f0 q(Context context, boolean z) {
        a aVar = new a(context, z);
        aVar.setHoverListener(this);
        return aVar;
    }
}
