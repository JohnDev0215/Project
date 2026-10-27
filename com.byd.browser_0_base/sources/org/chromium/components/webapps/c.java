package org.chromium.components.webapps;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import defpackage.bi1;
import defpackage.di1;
import defpackage.y3;
import org.chromium.components.webapps.AppBannerManager;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: AddToHomescreenDialogView.java */
/* JADX INFO: loaded from: classes3.dex */
public final class c implements View.OnClickListener, di1.a {
    public final PropertyModel e;
    public final bi1 f;
    public final y3 g;
    public final EditText h;
    public final LinearLayout i;
    public final TextView j;
    public final TextView k;
    public final RatingBar l;
    public final ImageView m;
    public final View n;
    public final ImageView o;
    public boolean p;

    public c(Context context, bi1 bi1Var, AppBannerManager.a aVar, AddToHomescreenMediator addToHomescreenMediator) {
        this.f = bi1Var;
        this.g = addToHomescreenMediator;
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.add_to_homescreen_dialog, (ViewGroup) null);
        this.n = viewInflate.findViewById(R$id.spinny);
        ImageView imageView = (ImageView) viewInflate.findViewById(R$id.icon);
        this.o = imageView;
        EditText editText = (EditText) viewInflate.findViewById(R$id.text);
        this.h = editText;
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R$id.app_info);
        this.i = linearLayout;
        TextView textView = (TextView) linearLayout.findViewById(R$id.name);
        this.j = textView;
        this.k = (TextView) linearLayout.findViewById(R$id.origin);
        this.l = (RatingBar) linearLayout.findViewById(R$id.control_rating);
        this.m = (ImageView) viewInflate.findViewById(R$id.play_logo);
        textView.setOnClickListener(this);
        imageView.setOnClickListener(this);
        viewInflate.addOnLayoutChangeListener(new a(this));
        editText.addTextChangedListener(new b(this));
        Resources resources = context.getResources();
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        bVar.e(di1.a, this);
        bVar.d(di1.c, resources, aVar.a);
        bVar.d(di1.j, resources, aVar.b);
        bVar.b(di1.l, true);
        bVar.d(di1.m, resources, R$string.cancel);
        bVar.e(di1.h, viewInflate);
        bVar.b(di1.q, true);
        PropertyModel propertyModelA = bVar.a();
        this.e = propertyModelA;
        bi1Var.j(propertyModelA, 0, false);
    }

    public final void a() {
        EditText editText = this.h;
        boolean z = true;
        boolean z2 = editText.getVisibility() == 0 && TextUtils.isEmpty(editText.getText());
        PropertyModel.n nVar = di1.l;
        if (this.p && !z2) {
            z = false;
        }
        this.e.l(nVar, z);
    }

    @Override // di1.a
    public final void b(int i) {
        if (i == 1) {
            return;
        }
        this.g.c();
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        int i2;
        if (i == 0) {
            this.g.b(this.h.getText().toString());
            i2 = 1;
        } else {
            i2 = 2;
        }
        this.f.b(i2, this.e);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if ((view == this.j || view == this.o) && this.g.a()) {
            this.f.b(3, this.e);
        }
    }
}
