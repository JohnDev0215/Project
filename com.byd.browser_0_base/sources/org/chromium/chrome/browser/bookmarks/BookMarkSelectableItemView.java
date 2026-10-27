package org.chromium.chrome.browser.bookmarks;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageButton;
import defpackage.ao3;
import defpackage.ii2;
import defpackage.vh3;
import defpackage.xz;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase;

/* JADX INFO: loaded from: classes.dex */
public abstract class BookMarkSelectableItemView<E> extends SelectableItemViewBase<E> {
    public LinearLayout m;
    public ImageView n;
    public AppCompatImageButton o;
    public TextView p;
    public TextView q;
    public Drawable r;
    public int s;
    public boolean t;
    public boolean u;
    public ViewGroup v;
    public ImageView w;

    public BookMarkSelectableItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ColorStateList.valueOf(ii2.d(context));
        this.s = R$layout.modern_list_item_view_divider;
    }

    public ColorStateList getDefaultStartIconTint() {
        return null;
    }

    public Drawable getStartIconDrawable() {
        return this.r;
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase, android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        q();
        this.t = true;
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase
    public final void p(boolean z) {
        if (this.w != null) {
            if (isChecked()) {
                this.w.setImageResource(R$drawable.bookmark_check_yes);
            } else {
                this.w.setImageResource(R$drawable.bookmark_check_no);
            }
        }
    }

    public final void q() {
        LayoutInflater.from(getContext()).inflate(this.s, this);
        this.m = (LinearLayout) findViewById(R$id.content);
        this.w = (ImageView) findViewById(R$id.bt_check);
        this.n = (ImageView) findViewById(R$id.start_icon);
        this.p = (TextView) findViewById(R$id.title);
        this.q = (TextView) findViewById(R$id.description);
        ImageView imageView = this.n;
        if (imageView != null) {
            imageView.setImageTintList(getDefaultStartIconTint());
        }
        if (this.u) {
            this.o = (AppCompatImageButton) findViewById(R$id.optional_button);
            this.v = (ViewGroup) findViewById(R$id.custom_content_container);
            ao3.e(this.n, "SelectableItemView.inflateAndPopulateViewVariables");
        }
    }

    public void setCustomContent(View view) {
        if (this.u) {
            this.v.removeAllViews();
            if (view != null) {
                this.v.addView(view);
            }
        }
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase
    public void setItem(E e) {
        super.setItem(e);
        Activity activityA = xz.a(getContext());
        this.q.setVisibility(activityA == null || vh3.f(activityA) ? 0 : 8);
    }

    public void setStartIconDrawable(Drawable drawable) {
        this.r = drawable;
        this.n.setImageDrawable(drawable);
    }
}
