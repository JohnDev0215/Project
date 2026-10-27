package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.bookmarks.BookmarkRow;
import org.chromium.chrome.browser.bookmarks.PowerBookmarkShoppingItemRow;
import org.chromium.chrome.browser.signin.LegacySyncPromoView;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;
import org.chromium.components.browser_ui.widget.dragreorder.DragReorderableListAdapter;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: BookmarkItemsAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class xf extends DragReorderableListAdapter<yf> {
    public final c q;
    public final a r;
    public b s;

    /* JADX INFO: compiled from: BookmarkItemsAdapter.java */
    public interface a {
    }

    /* JADX INFO: compiled from: BookmarkItemsAdapter.java */
    public interface b {
    }

    /* JADX INFO: compiled from: BookmarkItemsAdapter.java */
    public interface c {
    }

    public xf(Context context, nf nfVar, c01 c01Var) {
        super(context);
        this.q = nfVar;
        this.r = c01Var;
    }

    @Override // org.chromium.components.browser_ui.widget.dragreorder.DragReorderableListAdapter
    public final boolean E(RecyclerView.a0 a0Var) {
        return F(a0Var) && ((BookmarkRow) a0Var.e).t();
    }

    @Override // org.chromium.components.browser_ui.widget.dragreorder.DragReorderableListAdapter
    public final boolean F(RecyclerView.a0 a0Var) {
        BookmarkItem bookmarkItem;
        b bVar = this.s;
        RecyclerView recyclerView = this.k;
        View view = a0Var.e;
        recyclerView.getClass();
        RecyclerView.a0 a0VarK = RecyclerView.K(view);
        yf yfVar = (yf) this.j.get(a0VarK != null ? a0VarK.g() : -1);
        ((gg) bVar).getClass();
        return (yfVar == null || (bookmarkItem = yfVar.b) == null || !bookmarkItem.b()) ? false : true;
    }

    @Override // org.chromium.components.browser_ui.widget.dragreorder.DragReorderableListAdapter
    public final void G(ArrayList arrayList) {
        ((gg) this.s).z(arrayList);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final int m(int i) {
        return ((yf) this.j.get(i)).a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void t(RecyclerView.a0 a0Var, int i) {
        BookmarkItem bookmarkItem;
        gg ggVar = (gg) this.s;
        ggVar.getClass();
        PropertyModel propertyModel = new PropertyModel(ig.k);
        int i2 = a0Var.j;
        j.a zfVar = null;
        int i3 = 0;
        int i4 = 1;
        if (i2 == 0 || i2 == 1) {
            propertyModel.p(ig.a, ggVar.u);
        } else {
            xf xfVar = ggVar.o;
            if (i2 == 6) {
                propertyModel.p(ig.b, (yf) xfVar.j.get(i));
            } else {
                int i5 = 4;
                if (i2 == 4 || i2 == 3 || i2 == 7) {
                    yf yfVar = (yf) xfVar.j.get(i);
                    BookmarkId bookmarkId = (yfVar == null || (bookmarkItem = yfVar.b) == null) ? null : bookmarkItem.c;
                    propertyModel.p(ig.c, bookmarkId);
                    propertyModel.n(ig.d, (i == ggVar.s() && i == ggVar.q()) ? 3 : i == ggVar.s() ? 0 : i == ggVar.q() ? 2 : 1);
                    propertyModel.l(ig.e, BookmarkId.c.equals(ggVar.C));
                    propertyModel.p(ig.f, xfVar.i);
                    propertyModel.p(ig.g, a0Var);
                    propertyModel.l(ig.h, bookmarkId.equals(ggVar.D));
                    propertyModel.p(ig.i, new fg(ggVar, i3));
                } else if (i2 == 9) {
                    propertyModel.p(ig.j, new xr(i5, ggVar));
                }
            }
        }
        int i6 = a0Var.j;
        ((eg) ((c01) this.r).f).getClass();
        switch (i6) {
            case 0:
            case 1:
                zfVar = new zf(i3);
                break;
            case 2:
                zfVar = new ag(i3);
                break;
            case 3:
                zfVar = new cg(i3);
                break;
            case 4:
                zfVar = new zf(i4);
                break;
            case 5:
                zfVar = new bg(i4);
                break;
            case 6:
                zfVar = new bg(i3);
                break;
            case 7:
                zfVar = new ag(i4);
                break;
            case 9:
                zfVar = new cg(i4);
                break;
        }
        j.b(propertyModel, a0Var.e, zfVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final RecyclerView.a0 v(int i, RecyclerView recyclerView) {
        View viewInflate;
        eg egVar = ((nf) this.q).e;
        if (i == 7) {
            egVar.getClass();
            if (!tf.a()) {
                i = 4;
            }
        }
        pg pgVar = egVar.l;
        gg ggVar = egVar.i;
        switch (i) {
            case 0:
            case 1:
                viewInflate = LayoutInflater.from(pgVar.e).inflate(R$layout.sync_promo_view_bookmarks, (ViewGroup) recyclerView, false);
                break;
            case 2:
                pgVar.getClass();
                viewInflate = LegacySyncPromoView.a(9, recyclerView);
                break;
            case 3:
                BookmarkRow bookmarkRow = (BookmarkRow) eg.d(R$layout.bookmark_folder_row, recyclerView);
                bookmarkRow.u(ggVar);
                viewInflate = bookmarkRow;
                break;
            case 4:
                BookmarkRow bookmarkRow2 = (BookmarkRow) eg.d(R$layout.bookmark_item_row, recyclerView);
                bookmarkRow2.u(ggVar);
                viewInflate = bookmarkRow2;
                break;
            case 5:
                viewInflate = eg.d(R$layout.horizontal_divider, recyclerView);
                break;
            case 6:
                viewInflate = eg.d(R$layout.bookmark_section_header, recyclerView);
                break;
            case 7:
                BookmarkRow bookmarkRow3 = (BookmarkRow) eg.d(R$layout.power_bookmark_shopping_item_row, recyclerView);
                bookmarkRow3.u(ggVar);
                PowerBookmarkShoppingItemRow powerBookmarkShoppingItemRow = (PowerBookmarkShoppingItemRow) bookmarkRow3;
                powerBookmarkShoppingItemRow.M = egVar.j;
                powerBookmarkShoppingItemRow.N = egVar.m;
                powerBookmarkShoppingItemRow.R = egVar.k;
                powerBookmarkShoppingItemRow.S = egVar.n;
                viewInflate = powerBookmarkShoppingItemRow;
                break;
            case 8:
            default:
                viewInflate = null;
                break;
            case 9:
                viewInflate = eg.d(R$layout.shopping_filter_row, recyclerView);
                break;
        }
        return new wf(viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void z(RecyclerView.a0 a0Var) {
        xx2 xx2Var;
        px0 px0Var;
        b bVar = this.s;
        View view = a0Var.e;
        int i = a0Var.j;
        gg ggVar = (gg) bVar;
        ggVar.getClass();
        if ((i != 0 && i != 1) || (xx2Var = ggVar.u.j) == null || (px0Var = xx2Var.b) == null) {
            return;
        }
        px0Var.a(null);
        xx2Var.b = null;
    }
}
