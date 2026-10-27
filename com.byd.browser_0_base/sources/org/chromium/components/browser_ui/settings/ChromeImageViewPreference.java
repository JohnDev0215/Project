package org.chromium.components.browser_ui.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.preference.Preference;
import defpackage.bz;
import defpackage.mb1;
import defpackage.n32;
import defpackage.n40;
import defpackage.nb1;
import defpackage.qj2;

/* JADX INFO: loaded from: classes2.dex */
public class ChromeImageViewPreference extends Preference {
    public mb1 S;
    public View.OnClickListener T;
    public int U;
    public int V;
    public Integer W;
    public int X;
    public boolean Y;
    public ImageView Z;
    public View a0;

    public ChromeImageViewPreference(Context context) {
        this(context, null);
    }

    public final void Y() {
        int i = this.U;
        if (i == 0 || this.Z == null) {
            return;
        }
        this.Z.setImageDrawable(qj2.c(this.e, i, this.V));
        this.Z.setEnabled(this.Y);
        if (this.Y) {
            this.Z.setOnClickListener(this.T);
        }
        if (this.X != 0) {
            ImageView imageView = this.Z;
            imageView.setContentDescription(imageView.getResources().getString(this.X));
        }
    }

    public final void Z(int i, int i2, View.OnClickListener onClickListener) {
        this.U = i;
        this.X = i2;
        this.T = onClickListener;
        Y();
        s();
    }

    public final void a0() {
        Integer num;
        View view = this.a0;
        if (view == null || (num = this.W) == null) {
            return;
        }
        view.setBackgroundColor(bz.b(this.e, num.intValue()).getDefaultColor());
    }

    @Override // androidx.preference.Preference
    public void y(n32 n32Var) {
        super.y(n32Var);
        int i = R$id.image_view_widget;
        ImageView imageView = (ImageView) n32Var.u(i);
        this.Z = imageView;
        imageView.setBackgroundColor(0);
        this.Z.setVisibility(0);
        this.a0 = n32Var.e;
        a0();
        Y();
        mb1 mb1Var = this.S;
        View view = this.a0;
        if (mb1Var == null) {
            return;
        }
        nb1.d(mb1Var, this, view);
        if (mb1Var.r(this) || mb1Var.b(this)) {
            ImageView imageView2 = (ImageView) view.findViewById(i);
            imageView2.setImageDrawable(nb1.b(mb1Var, this));
            if (mb1Var.r(this)) {
                imageView2.setContentDescription(this.e.getResources().getString(R$string.managed_by_your_organization));
            }
            imageView2.setOnClickListener(new n40(mb1Var, 11, this));
        }
    }

    @Override // androidx.preference.Preference
    public final void z() {
        nb1.e(this.S, this);
    }

    public ChromeImageViewPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.Y = true;
        this.K = R$layout.preference_chrome_image_view;
        P();
        int i = R$color.default_icon_color_tint_list;
        if (this.V == i) {
            return;
        }
        this.V = i;
        Y();
    }
}
