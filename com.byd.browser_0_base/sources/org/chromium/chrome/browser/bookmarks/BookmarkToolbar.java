package org.chromium.chrome.browser.bookmarks;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import defpackage.js1;
import defpackage.lh;
import defpackage.n3;
import defpackage.ng;
import defpackage.oj2;
import defpackage.uh3;
import java.util.ArrayList;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.app.bookmarks.BookmarkActivity;
import org.chromium.chrome.browser.app.bookmarks.BookmarkAddEditFolderActivity;
import org.chromium.chrome.browser.app.bookmarks.BookmarkFolderSelectActivity;
import org.chromium.chrome.browser.bookmark_history.BookmarkHistoryActivity;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;

/* JADX INFO: loaded from: classes.dex */
public class BookmarkToolbar extends BookMarkSelectableListToolbar<BookmarkId> implements Toolbar.OnMenuItemClickListener {
    public Callback<BookmarkId> A0;
    public js1 B0;
    public BookmarkModel t0;
    public ng u0;
    public org.chromium.components.browser_ui.widget.selectable_list.a v0;
    public BookmarkItem w0;
    public int x0;
    public Runnable y0;
    public TextView z0;

    public BookmarkToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setNavigationOnClickListener(this);
    }

    @Override // org.chromium.chrome.browser.bookmarks.BookMarkSelectableListToolbar
    public final void D() {
        if (B()) {
            super.D();
            return;
        }
        BookmarkId bookmarkId = this.w0.e;
        if (bookmarkId.getId() == 3) {
            bookmarkId = this.t0.h(bookmarkId).e;
        }
        this.A0.onResult(bookmarkId);
    }

    @Override // org.chromium.chrome.browser.bookmarks.BookMarkSelectableListToolbar
    public final void E() {
        super.E();
    }

    @Override // org.chromium.chrome.browser.bookmarks.BookMarkSelectableListToolbar, org.chromium.components.browser_ui.widget.selectable_list.a.InterfaceC0128a
    public final void f(ArrayList arrayList) {
        BookmarkItem bookmarkItem;
        super.f(arrayList);
        if (this.t0 == null) {
            return;
        }
        if (!this.b0 && arrayList.isEmpty() && this.x0 == 2 && (bookmarkItem = this.w0) != null) {
            setCurrentFolder(bookmarkItem.c);
        }
        js1 js1Var = this.B0;
        if (js1Var != null) {
            ((BookmarkHistoryActivity.a) js1Var).a(arrayList, this.b0);
        }
    }

    @Override // org.chromium.chrome.browser.bookmarks.BookMarkSelectableListToolbar, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.z0 = (TextView) findViewById(R$id.tv_center);
    }

    @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
    public final boolean onMenuItemClick(MenuItem menuItem) {
        n();
        if (menuItem.getItemId() == R$id.edit_menu_id) {
            BookmarkAddEditFolderActivity.O1(getContext(), this.w0.c);
            return true;
        }
        if (menuItem.getItemId() == R$id.close_menu_id) {
            Context context = getContext();
            int i = lh.a;
            if ((context instanceof BookmarkActivity) || (context instanceof BookmarkHistoryActivity)) {
                ((Activity) context).finish();
            }
            return true;
        }
        if (menuItem.getItemId() == R$id.search_menu_id) {
            this.y0.run();
            return true;
        }
        if (menuItem.getItemId() == R$id.selection_mode_edit_menu_id) {
            BookmarkItem bookmarkItemH = this.t0.h((BookmarkId) this.v0.c().get(0));
            boolean z = bookmarkItemH.d;
            BookmarkId bookmarkId = bookmarkItemH.c;
            if (z) {
                BookmarkAddEditFolderActivity.O1(getContext(), bookmarkId);
            } else {
                lh.f(getContext(), bookmarkId);
            }
            return true;
        }
        if (menuItem.getItemId() == R$id.selection_mode_move_menu_id) {
            ArrayList arrayListC = this.v0.c();
            if (arrayListC.size() >= 1) {
                Context context2 = getContext();
                context2.startActivity(BookmarkFolderSelectActivity.K1(context2, false, (BookmarkId[]) arrayListC.toArray(new BookmarkId[0])));
                n3.X0("MobileBookmarkManagerMoveToFolderBulk");
            }
            return true;
        }
        if (menuItem.getItemId() == R$id.selection_mode_delete_menu_id) {
            ArrayList arrayListC2 = this.v0.c();
            if (arrayListC2.size() >= 1) {
                this.t0.u((BookmarkId[]) arrayListC2.toArray(new BookmarkId[0]));
                n3.X0("MobileBookmarkManagerDeleteBulk");
            }
            return true;
        }
        if (menuItem.getItemId() == R$id.selection_open_in_new_tab_id) {
            n3.X0("MobileBookmarkManagerEntryOpenedInNewTab");
            oj2.P(this.v0.c.size(), "Bookmarks.Count.OpenInNewTab");
            this.u0.b(this.v0.c(), false);
            return true;
        }
        if (menuItem.getItemId() == R$id.selection_open_in_incognito_tab_id) {
            n3.X0("MobileBookmarkManagerEntryOpenedInIncognito");
            oj2.P(this.v0.c.size(), "Bookmarks.Count.OpenInIncognito");
            this.u0.b(this.v0.c(), true);
            return true;
        }
        if (menuItem.getItemId() != R$id.reading_list_mark_as_read_id && menuItem.getItemId() != R$id.reading_list_mark_as_unread_id) {
            return false;
        }
        for (int i2 = 0; i2 < this.v0.c().size(); i2++) {
            BookmarkId bookmarkId2 = (BookmarkId) this.v0.c().get(i2);
            if (bookmarkId2.getType() == 2) {
                this.t0.t(this.t0.h(bookmarkId2).b, menuItem.getItemId() == R$id.reading_list_mark_as_read_id);
            }
        }
        this.v0.b();
        return true;
    }

    public void setBookmarkModel(BookmarkModel bookmarkModel) {
        this.t0 = bookmarkModel;
    }

    public void setBookmarkOpener(ng ngVar) {
        this.u0 = ngVar;
    }

    public void setBookmarkUiMode(int i) {
        BookmarkItem bookmarkItem;
        this.x0 = i;
        if (i == 1) {
            setTitle((CharSequence) null);
            setNavigationButton(0);
        } else {
            super.E();
        }
        if (this.x0 == 3) {
            BookmarkItem bookmarkItem2 = this.w0;
            if (bookmarkItem2 == null || !bookmarkItem2.c.equals(this.t0.n())) {
                BookmarkModel bookmarkModel = this.t0;
                if (bookmarkModel != null) {
                    setCurrentFolder(bookmarkModel.n());
                }
            } else {
                F(true);
            }
        } else {
            y(false);
        }
        if (this.x0 != 2 || (bookmarkItem = this.w0) == null) {
            return;
        }
        setCurrentFolder(bookmarkItem.c);
    }

    public void setCurrentFolder(BookmarkId bookmarkId) {
        ArrayList arrayList;
        BookmarkItem bookmarkItemH = this.t0.h(bookmarkId);
        this.w0 = bookmarkItemH;
        if (bookmarkItemH == null) {
            return;
        }
        if (bookmarkId.equals(this.t0.n())) {
            setTitle((CharSequence) null);
            this.f0.setVisibility(0);
            setNavigationButton(0);
            return;
        }
        this.f0.setVisibility(8);
        if (bookmarkId.equals(BookmarkId.c)) {
            setTitle(R$string.price_tracking_bookmarks_filter_title);
        } else {
            BookmarkModel bookmarkModel = this.t0;
            bookmarkModel.getClass();
            Object obj = ThreadUtils.a;
            if (bookmarkModel.a == 0) {
                arrayList = new ArrayList();
            } else {
                ArrayList arrayList2 = new ArrayList();
                N.MHq3fk0e(bookmarkModel.a, bookmarkModel, arrayList2);
                arrayList = arrayList2;
            }
            if (arrayList.contains(this.w0.e) && TextUtils.isEmpty(this.w0.a)) {
                setTitle(R$string.bookmarks);
            } else {
                setTitle(this.w0.a);
            }
        }
        if (!bookmarkId.equals(this.t0.n())) {
            setNavigationButton(1);
        }
        if (getLayoutDirection() == 1) {
            setNavigationIcon(uh3.e(getContext(), R$drawable.btn_right, R$color.default_icon_color_tint_list));
        }
    }

    public void setDragEnabled(boolean z) {
        boolean z2 = !z;
        View viewJ0 = n3.j0(this);
        if (viewJ0 != null) {
            viewJ0.setEnabled(z2);
        }
        setNavigationOnClickListener(z ? null : this);
        setOnMenuItemClickListener(z ? null : this);
    }

    @Override // org.chromium.chrome.browser.bookmarks.BookMarkSelectableListToolbar
    public void setNavigationButton(int i) {
        BookmarkItem bookmarkItem = this.w0;
        if (bookmarkItem != null && bookmarkItem.c.equals(this.t0.n())) {
            i = 0;
        }
        super.setNavigationButton(i);
    }

    public void setOnSelectListener(js1 js1Var) {
        this.B0 = js1Var;
    }

    public void setOpenFolderCallback(Callback<BookmarkId> callback) {
        this.A0 = callback;
    }

    public void setOpenSearchUiRunnable(Runnable runnable) {
        this.y0 = runnable;
    }

    public void setSelectionDelegate(org.chromium.components.browser_ui.widget.selectable_list.a aVar) {
        this.v0 = aVar;
    }

    public void setSoftKeyboardVisible(boolean z) {
        if (z) {
            return;
        }
        x();
    }

    @Override // org.chromium.chrome.browser.bookmarks.BookMarkSelectableListToolbar, androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence charSequence) {
        TextView textView = this.z0;
        if (textView != null) {
            if (TextUtils.isEmpty(charSequence)) {
                charSequence = "";
            }
            textView.setText(charSequence);
        }
        C();
    }

    public void setIsDialogUi(boolean z) {
    }
}
