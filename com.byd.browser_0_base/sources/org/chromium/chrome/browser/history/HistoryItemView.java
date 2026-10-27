package org.chromium.chrome.browser.history;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import defpackage.bz;
import defpackage.du;
import defpackage.dv0;
import defpackage.ev0;
import defpackage.ie0;
import defpackage.ii2;
import defpackage.n3;
import defpackage.oj2;
import defpackage.uc2;
import defpackage.vh3;
import defpackage.xz;
import defpackage.za3;
import org.chromium.base.LocaleUtils;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.bookmarks.BookMarkSelectableItemView;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.components.prefs.PrefService;

/* JADX INFO: loaded from: classes.dex */
public class HistoryItemView extends BookMarkSelectableItemView<ev0> {
    public static final /* synthetic */ int E = 0;
    public final int A;
    public final int B;
    public boolean C;
    public ImageView D;
    public ImageView x;
    public final uc2 y;
    public FaviconHelper.a z;

    public HistoryItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.s = R$layout.modern_list_item_view_divider;
        this.A = getResources().getDimensionPixelSize(R$dimen.default_favicon_min_size);
        this.B = (int) (((double) getResources().getDimensionPixelSize(R$dimen.default_favicon_size)) * 1.2d);
        this.y = org.chromium.chrome.browser.ui.favicon.a.b(context, vh3.a(2));
        context.getResources().getDimensionPixelSize(R$dimen.default_list_row_padding);
        ColorStateList.valueOf(ii2.d(context));
    }

    private PrefService getPrefService() {
        return oj2.k(Profile.d());
    }

    public View getRemoveButtonForTests() {
        return this.x;
    }

    @Override // android.view.View
    public final boolean isLongClickable() {
        return false;
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase
    public final void m() {
        ev0 item;
        dv0 dv0Var;
        if (getItem() == null || (dv0Var = (item = getItem()).i) == null) {
            return;
        }
        dv0Var.i.q();
        dv0Var.e(item.c, null, false);
    }

    @Override // org.chromium.chrome.browser.bookmarks.BookMarkSelectableItemView, org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.n.setImageResource(R$drawable.default_favicon);
        LayoutInflater.from(getContext()).inflate(R$layout.list_menu_button_history, this.m);
        this.x = (ImageView) findViewById(R$id.more);
        this.D = (ImageView) findViewById(R$id.bookmark_add);
        this.x.setContentDescription(getContext().getString(R$string.remove));
        this.x.setImageTintList(bz.b(getContext(), R$color.default_icon_color_secondary_tint_list));
        ie0 ie0Var = new ie0(9, this);
        this.x.setOnClickListener(ie0Var);
        this.D.setOnClickListener(ie0Var);
        if (xz.a(getContext()) instanceof ChromeTabbedActivity) {
            findViewById(R$id.divider).setVisibility(8);
            this.D.setVisibility(8);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
            marginLayoutParams.setMarginStart(vh3.a(20));
            marginLayoutParams.setMarginEnd(vh3.a(20));
            setLayoutParams(marginLayoutParams);
        }
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase, android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        return false;
    }

    public final void s() {
        if (getItem() == null) {
            return;
        }
        if (getItem().c()) {
            this.D.setImageResource(R$drawable.btn_star_filled);
        } else {
            this.D.setImageDrawable(za3.b(getContext(), R$drawable.btn_star, R$color.default_icon_color_tint_list));
        }
    }

    public void setFaviconHelper(FaviconHelper.a aVar) {
        this.z = aVar;
    }

    public void setRemoveButtonVisible(boolean z) {
        if (getPrefService().a("history.deleting_enabled")) {
            this.x.setVisibility(z ? 0 : 4);
        }
    }

    @Override // org.chromium.chrome.browser.bookmarks.BookMarkSelectableItemView, org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase
    public void setItem(ev0 ev0Var) {
        LargeIconBridge largeIconBridge;
        super.setItem(ev0Var);
        if (!LocaleUtils.b()) {
            this.p.setTextDirection(3);
        }
        this.p.setText(ev0Var.c.j());
        TextView textView = this.q;
        String str = ev0Var.d;
        textView.setText(str);
        n3.h1(getContext(), this.x, str, 1);
        this.C = false;
        s();
        FaviconHelper.a aVar = this.z;
        Resources resources = getContext().getResources();
        aVar.getClass();
        setStartIconDrawable(new BitmapDrawable(resources, aVar.b(resources, ev0Var.c, true)));
        ev0 item = getItem();
        if (Boolean.valueOf(item.e).booleanValue()) {
            return;
        }
        du duVar = new du(this, item);
        dv0 dv0Var = item.i;
        if (dv0Var == null || (largeIconBridge = dv0Var.q) == null) {
            return;
        }
        largeIconBridge.b(item.c, this.A, duVar);
    }
}
