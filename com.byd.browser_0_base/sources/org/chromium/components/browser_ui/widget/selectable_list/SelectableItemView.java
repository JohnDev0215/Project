package org.chromium.components.browser_ui.widget.selectable_list;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageButton;
import defpackage.ii2;
import defpackage.p5;
import org.chromium.components.browser_ui.widget.R$drawable;
import org.chromium.components.browser_ui.widget.R$id;
import org.chromium.components.browser_ui.widget.R$integer;
import org.chromium.components.browser_ui.widget.R$layout;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SelectableItemView<E> extends SelectableItemViewBase<E> {
    public final int m;
    public final int n;
    public final p5 o;
    public LinearLayout p;
    public ImageView q;
    public AppCompatImageButton r;
    public TextView s;
    public TextView t;
    public final ColorStateList u;
    public Drawable v;
    public final int w;
    public final int x;

    public SelectableItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.u = ColorStateList.valueOf(ii2.d(context));
        this.m = getResources().getInteger(R$integer.list_item_level_default);
        this.n = getResources().getInteger(R$integer.list_item_level_selected);
        this.o = p5.b(getContext(), R$drawable.ic_check_googblue_24dp_animated);
        this.x = R$drawable.list_item_icon_modern_bg;
        this.w = R$layout.modern_list_item_view;
    }

    public ColorStateList getDefaultStartIconTint() {
        return null;
    }

    public Drawable getStartIconDrawable() {
        return this.v;
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase, android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(this.w, this);
        this.p = (LinearLayout) findViewById(R$id.content);
        this.q = (ImageView) findViewById(R$id.start_icon);
        this.r = (AppCompatImageButton) findViewById(R$id.end_button);
        this.s = (TextView) findViewById(R$id.title);
        this.t = (TextView) findViewById(R$id.description);
        ImageView imageView = this.q;
        if (imageView != null) {
            imageView.setBackgroundResource(this.x);
            this.q.setImageTintList(getDefaultStartIconTint());
        }
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase
    public void p(boolean z) {
        if (this.q == null) {
            return;
        }
        if (!isChecked()) {
            this.q.getBackground().setLevel(this.m);
            this.q.setImageDrawable(this.v);
            this.q.setImageTintList(getDefaultStartIconTint());
            return;
        }
        this.q.getBackground().setLevel(this.n);
        ImageView imageView = this.q;
        p5 p5Var = this.o;
        imageView.setImageDrawable(p5Var);
        this.q.setImageTintList(this.u);
        if (z) {
            p5Var.start();
        }
    }

    public void setStartIconDrawable(Drawable drawable) {
        this.v = drawable;
        p(false);
    }

    public void setCustomContent(View view) {
    }
}
