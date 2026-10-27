package defpackage;

import J.N;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;
import defpackage.ll;
import java.util.List;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;
import org.chromium.components.commerce.core.ShoppingService;
import org.chromium.components.commerce.core.a;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: BookmarkSaveFlowMediator.java */
/* JADX INFO: loaded from: classes.dex */
public final class xg extends mg implements aw2 {
    public final Context e;
    public final Runnable f;
    public ll g = new ll();
    public PropertyModel h;
    public BookmarkModel i;
    public BookmarkId j;
    public c32 k;
    public boolean l;
    public boolean m;
    public final ShoppingService n;
    public a o;
    public ll.b p;
    public String q;

    public xg(BookmarkModel bookmarkModel, PropertyModel propertyModel, Context context, wv wvVar, ShoppingService shoppingService) {
        this.i = bookmarkModel;
        bookmarkModel.c(this);
        this.h = propertyModel;
        this.e = context;
        this.f = wvVar;
        this.n = shoppingService;
        if (shoppingService != null) {
            shoppingService.b.a(this);
        }
    }

    @Override // defpackage.aw2
    public final void b(List<a> list, boolean z) {
        if (z) {
            r(!list.contains(this.o));
        }
    }

    @Override // defpackage.aw2
    public final void c(List<a> list, boolean z) {
        if (z) {
            r(list.contains(this.o));
        }
    }

    @Override // defpackage.mg
    public final void f() {
        BookmarkId bookmarkId = this.j;
        if (bookmarkId == null || this.i.h(bookmarkId) == null) {
            this.f.run();
        } else {
            n(this.j, this.l);
        }
    }

    public final void n(BookmarkId bookmarkId, boolean z) {
        BookmarkItem bookmarkItemH = this.i.h(bookmarkId);
        BookmarkItem bookmarkItemH2 = this.i.h(bookmarkItemH.e);
        this.q = bookmarkItemH2 == null ? "" : bookmarkItemH2.a;
        PropertyModel propertyModel = this.h;
        PropertyModel.r<CharSequence> rVar = yg.m;
        Context context = this.e;
        propertyModel.p(rVar, context.getResources().getString(z ? R$string.bookmark_save_flow_title_move : R$string.bookmark_save_flow_title));
        PropertyModel propertyModel2 = this.h;
        PropertyModel.r<Drawable> rVar2 = yg.b;
        bookmarkId.getType();
        propertyModel2.p(rVar2, lh.b(context));
        this.h.l(yg.c, lh.c(bookmarkItemH));
        this.h.p(yg.l, context.getResources().getString(z ? R$string.bookmark_page_moved_location : R$string.bookmark_page_saved_location, this.q));
    }

    public final void o(CompoundButton compoundButton, boolean z) {
        if (this.p == null) {
            this.p = this.g.b(new gp(this, 5, compoundButton));
        }
        if (z) {
            n3.B().b();
        }
        p(z);
        N.M5sY3Y_p(Profile.d(), this.j.getId(), z, this.p, this.m);
        oj2.U(z ? 1 : 2, 3, "PowerBookmarks.BookmarkSaveFlow.PriceTrackingEnabled");
    }

    public final void p(boolean z) {
        this.h.p(yg.e, Integer.valueOf(z ? R$drawable.price_tracking_enabled_filled : R$drawable.price_tracking_disabled));
    }

    public final void q(boolean z) {
        this.h.l(yg.k, z);
        this.h.p(yg.f, this.e.getResources().getString(z ? R$string.price_tracking_save_flow_notification_switch_subtitle : R$string.price_tracking_save_flow_notification_switch_subtitle_error));
    }

    public final void r(boolean z) {
        PropertyModel propertyModel = this.h;
        PropertyModel.r<CompoundButton.OnCheckedChangeListener> rVar = yg.i;
        propertyModel.p(rVar, null);
        this.h.l(yg.h, z);
        p(z);
        this.h.p(rVar, new wg(0, this));
    }
}
