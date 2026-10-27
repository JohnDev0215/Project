package org.chromium.chrome.browser.bookmarks;

import J.N;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import defpackage.ax0;
import defpackage.c3;
import defpackage.c32;
import defpackage.d62;
import defpackage.e32;
import defpackage.g20;
import defpackage.oj2;
import defpackage.qf;
import defpackage.sc2;
import defpackage.so;
import defpackage.wd2;
import defpackage.wp2;
import defpackage.xr;
import defpackage.zl2;
import java.util.Locale;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$styleable;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;

/* JADX INFO: loaded from: classes.dex */
public class PowerBookmarkShoppingItemRow extends BookmarkItemRow {
    public static final /* synthetic */ int T = 0;
    public ax0 M;
    public BookmarkModel N;
    public boolean O;
    public g20 P;
    public boolean Q;
    public wp2 R;
    public Profile S;

    public PowerBookmarkShoppingItemRow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void setPriceTrackingButton(boolean z) {
        this.O = z;
        this.o.setContentDescription(getContext().getResources().getString(z ? R$string.disable_price_tracking_menu_item : R$string.enable_price_tracking_menu_item));
        this.o.setVisibility(0);
        this.o.setImageResource(this.O ? R$drawable.price_tracking_enabled_filled : R$drawable.price_tracking_disabled);
        this.o.setOnClickListener(new wd2(this, 1, new so(12, this)));
    }

    public View getPriceTrackingButtonForTesting() {
        return this.o;
    }

    @Override // org.chromium.chrome.browser.bookmarks.BookmarkRow, defpackage.fh
    public final void onDestroy() {
        qf qfVar = this.B;
        if (qfVar != null) {
            qfVar.i(this);
        }
        g20 g20Var = this.P;
        if (g20Var != null) {
            g20Var.a();
        }
    }

    public void setCurrencyFormatterForTesting(g20 g20Var) {
        this.P = g20Var;
    }

    @Override // org.chromium.chrome.browser.bookmarks.BookmarkItemRow, org.chromium.chrome.browser.bookmarks.BookmarkRow
    public final BookmarkItem v(BookmarkId bookmarkId, int i, boolean z) {
        BookmarkItem bookmarkItemV = super.v(bookmarkId, i, z);
        c32 c32VarL = this.N.l(bookmarkId);
        zl2 zl2VarP = c32VarL.P();
        d62 d62VarP = zl2VarP.P();
        d62 d62VarU = zl2VarP.U();
        e32.a(c32VarL);
        this.P = new g20(d62VarP.P(), Locale.getDefault());
        this.O = false;
        String strP = c32VarL.O().P();
        boolean z2 = this.O;
        long jO = d62VarU.O();
        long jO2 = d62VarP.O();
        this.n.setOutlineProvider(new sc2(getResources().getDimensionPixelSize(R$dimen.list_item_v2_start_icon_corner_radius)));
        this.n.setClipToOutline(true);
        this.M.d(ax0.a.a(0, 0, strP, "PowerBookmarks"), new xr(5, this));
        String strB = this.P.b("" + (jO2 / 1000000));
        if (jO <= jO2) {
            TextView textView = new TextView(getContext(), null);
            textView.setTextAppearance(textView.getContext(), R$styleable.ChipView_primaryTextAppearance);
            textView.setText(strB);
            setCustomContent(textView);
        } else {
            View view = (ViewGroup) LayoutInflater.from(getContext()).inflate(R$layout.compact_price_drop_view, (ViewGroup) null, false);
            TextView textView2 = (TextView) view.findViewById(R$id.primary_text);
            TextView textView3 = (TextView) view.findViewById(R$id.secondary_text);
            setCustomContent(view);
            String strB2 = this.P.b("" + (jO / 1000000));
            textView2.setText(strB);
            textView2.setTextColor(getContext().getColor(R$color.price_drop_annotation_text_green));
            textView3.setText(strB2);
            textView3.setPaintFlags(textView3.getPaintFlags() | 16);
        }
        setPriceTrackingButton(z2);
        this.p.setLabelFor(this.o.getId());
        oj2.U(0, 3, "PowerBookmarks.BookmarkManager.PriceTrackingEnabled");
        N.Mkvg2cg$(this.S, bookmarkId.getId(), new c3(12, this));
        return bookmarkItemV;
    }
}
