package org.chromium.chrome.browser.bookmarks;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import defpackage.bz;
import defpackage.fh;
import defpackage.gg;
import defpackage.gs;
import defpackage.k81;
import defpackage.l81;
import defpackage.n3;
import defpackage.qf;
import defpackage.qg;
import defpackage.tf;
import defpackage.zd;
import java.util.ArrayList;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public abstract class BookmarkRow extends BookMarkSelectableItemView<BookmarkId> implements fh {
    public static final /* synthetic */ int G = 0;
    public ImageView A;
    public qf B;
    public BookmarkId C;
    public boolean D;
    public int E;
    public boolean F;
    public ImageView x;
    public ImageView y;
    public ImageView z;

    public BookmarkRow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (tf.a()) {
            this.u = true;
            this.s = R$layout.modern_list_item_view_v2;
            if (this.t) {
                removeAllViews();
                q();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00aa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private defpackage.ya1 getItems() {
        /*
            Method dump skipped, instruction units count: 209
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.bookmarks.BookmarkRow.getItems():ya1");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public k81 getListMenu() {
        return new zd(getContext(), getItems(), new qg(this, 0));
    }

    private l81 getListMenuButtonDelegate() {
        return new l81() { // from class: rg
            @Override // defpackage.l81
            public final k81 b() {
                return this.e.getListMenu();
            }
        };
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase, org.chromium.components.browser_ui.widget.selectable_list.a.InterfaceC0128a
    public final void f(ArrayList arrayList) {
        super.f(arrayList);
        w();
    }

    public View getDragHandleViewForTesting() {
        return this.A;
    }

    public String getTitle() {
        return String.valueOf(this.p.getText());
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.D = true;
        qf qfVar = this.B;
        if (qfVar != null) {
            qfVar.k(this);
        }
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase, android.view.View.OnClickListener
    public final void onClick(View view) {
        if (!this.B.a().a()) {
            super.onClick(view);
        } else {
            n(getItem());
            n3.X0("MobileBookmarkManagerTapToggleSelect");
        }
    }

    @Override // defpackage.fh
    public void onDestroy() {
        qf qfVar = this.B;
        if (qfVar != null) {
            qfVar.i(this);
        }
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.D = false;
        qf qfVar = this.B;
        if (qfVar != null) {
            qfVar.i(this);
        }
    }

    @Override // org.chromium.chrome.browser.bookmarks.BookMarkSelectableItemView, org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase, android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R$layout.list_menu_button_bookmark, this.m);
        this.x = (ImageView) findViewById(R$id.more);
        if (n3.O0()) {
            this.x.setImageResource(R$drawable.btn_left);
        } else {
            this.x.setImageResource(R$drawable.btn_right);
        }
        this.y = (ImageView) findViewById(R$id.img_card);
        this.z = (ImageView) findViewById(R$id.bookmark_edit);
        ImageView imageView = (ImageView) findViewById(R$id.bt_drag);
        this.A = imageView;
        imageView.setImageResource(R$drawable.bookmark_drag);
        this.A.setImageTintList(bz.b(getContext(), R$color.default_icon_color_tint_list));
        gs gsVar = new gs(2, this);
        this.y.setOnClickListener(gsVar);
        this.z.setOnClickListener(gsVar);
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase, android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        if (this.B.a().a() && t()) {
            return true;
        }
        n3.X0("MobileBookmarkManagerLongPressToggleSelect");
        super.onLongClick(view);
        return true;
    }

    public void setDragHandleOnTouchListener(View.OnTouchListener onTouchListener) {
        this.A.setOnTouchListener(onTouchListener);
    }

    public final boolean t() {
        return this.B.l().d(this.C);
    }

    public final void u(gg ggVar) {
        super.setSelectionDelegate(ggVar.m);
        this.B = ggVar;
        if (this.D) {
            ggVar.k(this);
        }
    }

    public BookmarkItem v(BookmarkId bookmarkId, int i, boolean z) {
        this.E = i;
        this.C = bookmarkId;
        this.F = z;
        BookmarkItem bookmarkItemH = this.B.h().h(bookmarkId);
        n3.h1(getContext(), this.x, bookmarkItemH.a, 0);
        setChecked(t());
        w();
        super.setItem(bookmarkId);
        return bookmarkItemH;
    }

    public final void w() {
        BookmarkItem bookmarkItemH;
        GURL gurl;
        if (this.C == null || (bookmarkItemH = this.B.h().h(this.C)) == null) {
            return;
        }
        boolean z = true;
        if (!(this.B.a().a() || !bookmarkItemH.a()) && !this.g.e()) {
            z = false;
        }
        ImageView imageView = this.y;
        boolean z2 = bookmarkItemH.d;
        imageView.setVisibility((!z || !bookmarkItemH.a() || z2 || (gurl = bookmarkItemH.b) == null || gurl.i().contains("chrome")) ? 8 : 0);
        if (this.g.e() && this.y.getVisibility() == 8) {
            this.y.setVisibility(4);
        }
        this.A.setVisibility(z ? 0 : 8);
        this.z.setVisibility((z && bookmarkItemH.a()) ? 0 : 8);
        this.w.setVisibility((z && bookmarkItemH.a()) ? 0 : 8);
        if (this.w.getVisibility() == 8 && this.g.e()) {
            this.w.setVisibility(4);
        }
        this.x.setVisibility((z || !z2 || bookmarkItemH.c.getType() == 2) ? 8 : 0);
        if (!this.B.a().a()) {
            this.A.setVisibility(8);
        } else {
            this.A.setVisibility((!bookmarkItemH.b() || this.F) ? 8 : 0);
            this.A.setEnabled(t());
        }
    }

    @Override // defpackage.fh
    public final void g() {
    }

    @Override // defpackage.fh
    public final void d(BookmarkId bookmarkId) {
    }
}
