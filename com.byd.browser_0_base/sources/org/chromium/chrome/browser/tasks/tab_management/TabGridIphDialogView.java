package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.d92;
import defpackage.l5;
import java.util.Objects;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;

/* JADX INFO: loaded from: classes2.dex */
public class TabGridIphDialogView extends LinearLayout {
    public final int e;
    public final int f;
    public final int g;
    public final int h;
    public final int i;
    public final Context j;
    public View k;
    public Drawable l;
    public Animatable m;
    public a n;
    public ViewGroup.MarginLayoutParams o;
    public ViewGroup.MarginLayoutParams p;
    public int q;

    public class a extends l5.a {
        public a() {
        }

        @Override // l5.a
        public final void b(Drawable drawable) {
            Handler handler = new Handler();
            Animatable animatable = TabGridIphDialogView.this.m;
            Objects.requireNonNull(animatable);
            handler.postDelayed(new d92(9, animatable), 1500L);
        }
    }

    public TabGridIphDialogView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = context;
        this.e = (int) context.getResources().getDimension(R$dimen.tab_grid_iph_dialog_height);
        this.f = (int) context.getResources().getDimension(R$dimen.tab_grid_iph_dialog_top_margin);
        this.g = (int) context.getResources().getDimension(R$dimen.tab_grid_iph_dialog_text_side_margin);
        this.h = (int) context.getResources().getDimension(R$dimen.tab_grid_iph_dialog_text_top_margin_portrait);
        this.i = (int) context.getResources().getDimension(R$dimen.tab_grid_iph_dialog_text_top_margin_landscape);
    }

    public final void a() {
        if (this.q == this.k.getHeight()) {
            return;
        }
        this.q = this.k.getHeight();
        int i = this.j.getResources().getConfiguration().orientation == 1 ? this.h : this.i;
        ViewGroup.MarginLayoutParams marginLayoutParams = this.o;
        int i2 = this.g;
        marginLayoutParams.setMargins(i2, i, i2, i);
        this.p.setMargins(i2, 0, i2, i);
        setMinimumHeight(Math.min(this.e, this.q - (this.f * 2)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        Drawable drawable = ((ImageView) findViewById(R$id.animation_drawable)).getDrawable();
        this.l = drawable;
        this.m = (Animatable) drawable;
        TextView textView = (TextView) findViewById(R$id.title);
        TextView textView2 = (TextView) findViewById(R$id.description);
        this.n = new a();
        this.o = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();
        this.p = (ViewGroup.MarginLayoutParams) textView2.getLayoutParams();
    }

    public void setRootView(View view) {
        this.k = view;
    }
}
