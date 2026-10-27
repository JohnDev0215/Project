package org.chromium.chrome.browser.bookmarks;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import defpackage.uc2;
import defpackage.vh3;
import org.chromium.base.LocaleUtils;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public class BookmarkItemRow extends BookmarkRow implements LargeIconBridge.LargeIconCallback {
    public GURL H;
    public uc2 I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final int f63J;
    public final int K;
    public boolean L;

    public BookmarkItemRow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f63J = this.u ? getResources().getDimensionPixelSize(R$dimen.bookmark_refresh_min_start_icon_size) : getResources().getDimensionPixelSize(R$dimen.default_favicon_min_size);
        int dimensionPixelSize = this.u ? getResources().getDimensionPixelSize(R$dimen.bookmark_refresh_preferred_start_icon_size) : (int) (((double) getResources().getDimensionPixelSize(R$dimen.default_favicon_size)) * 1.2d);
        this.K = dimensionPixelSize;
        if (this.u) {
            this.I = new uc2(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize / 2, getContext().getColor(R$color.default_favicon_background_color), getResources().getDimensionPixelSize(R$dimen.bookmark_refresh_circular_monogram_text_size));
        } else {
            this.I = org.chromium.chrome.browser.ui.favicon.a.b(context, vh3.a(2));
        }
    }

    public boolean getFaviconCancelledForTesting() {
        return this.L;
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase
    public final void m() {
        if (this.B.n() != 1) {
        }
        this.B.d(this.C);
    }

    @Override // org.chromium.components.favicon.LargeIconBridge.LargeIconCallback
    public final void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        if (this.L) {
            return;
        }
        setStartIconDrawable(org.chromium.chrome.browser.ui.favicon.a.f(bitmap, this.H, i, this.I, getResources(), this.K));
    }

    public void setRoundedIconGeneratorForTesting(uc2 uc2Var) {
        this.I = uc2Var;
    }

    @Override // org.chromium.chrome.browser.bookmarks.BookmarkRow
    public BookmarkItem v(BookmarkId bookmarkId, int i, boolean z) {
        BookmarkItem bookmarkItemV = super.v(bookmarkId, i, z);
        GURL gurl = bookmarkItemV.b;
        this.H = gurl;
        this.n.setImageDrawable(null);
        if (!LocaleUtils.b()) {
            this.p.setTextDirection(3);
        }
        this.p.setText(gurl.j());
        this.q.setText(bookmarkItemV.a);
        this.L = false;
        this.B.b().b(this.H, this.f63J, this);
        return bookmarkItemV;
    }
}
