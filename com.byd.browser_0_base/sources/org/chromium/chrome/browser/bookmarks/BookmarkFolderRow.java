package org.chromium.chrome.browser.bookmarks;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import defpackage.bz;
import defpackage.kp2;
import defpackage.lh;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;

/* JADX INFO: loaded from: classes.dex */
public class BookmarkFolderRow extends BookmarkRow {
    public BookmarkFolderRow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // org.chromium.chrome.browser.bookmarks.BookMarkSelectableItemView
    public ColorStateList getDefaultStartIconTint() {
        BookmarkId bookmarkId = this.C;
        if (bookmarkId != null) {
            bookmarkId.getType();
        }
        Context context = getContext();
        int i = lh.a;
        return bz.b(context, kp2.a());
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase
    public final void m() {
        this.B.c(this.C);
    }

    @Override // org.chromium.chrome.browser.bookmarks.BookmarkRow, org.chromium.chrome.browser.bookmarks.BookMarkSelectableItemView, org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        setStartIconDrawable(lh.b(getContext()));
    }

    @Override // org.chromium.chrome.browser.bookmarks.BookmarkRow
    public final BookmarkItem v(BookmarkId bookmarkId, int i, boolean z) {
        BookmarkItem bookmarkItemV = super.v(bookmarkId, i, z);
        this.p.setText(bookmarkItemV.a);
        this.q.setText("");
        Context context = getContext();
        bookmarkItemV.c.getType();
        setStartIconDrawable(lh.b(context));
        return bookmarkItemV;
    }
}
