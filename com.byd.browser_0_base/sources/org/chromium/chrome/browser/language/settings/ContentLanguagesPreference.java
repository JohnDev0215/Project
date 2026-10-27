package org.chromium.chrome.browser.language.settings;

import J.N;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.i;
import androidx.recyclerview.widget.l;
import defpackage.bq;
import defpackage.by1;
import defpackage.f51;
import defpackage.gq2;
import defpackage.ii2;
import defpackage.k81;
import defpackage.n32;
import defpackage.o81;
import defpackage.qy;
import defpackage.sk;
import defpackage.xa1;
import defpackage.ya1;
import defpackage.za3;
import defpackage.zd;
import defpackage.zj;
import java.util.HashMap;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.language.settings.ContentLanguagesPreference;
import org.chromium.chrome.browser.language.settings.SelectLanguageFragment;
import org.chromium.chrome.browser.language.settings.b;
import org.chromium.chrome.browser.language.settings.c;
import org.chromium.components.browser_ui.widget.R$color;
import org.chromium.components.browser_ui.widget.dragreorder.DragReorderableListAdapter;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes.dex */
public class ContentLanguagesPreference extends Preference {
    public TextView S;
    public RecyclerView T;
    public final a U;
    public SelectLanguageFragment.c V;

    public static class a extends b implements c.a {
        public final Context q;

        public a(Context context) {
            super(context);
            this.q = context;
        }

        public final void I() {
            if (this.n.a()) {
                if (this.i == null) {
                    this.i = new l(new DragReorderableListAdapter.b());
                }
                this.i.j(this.k);
            } else {
                D();
            }
            H(c.b().e());
        }

        @Override // org.chromium.chrome.browser.language.settings.b, androidx.recyclerview.widget.RecyclerView.e
        public final void t(RecyclerView.a0 a0Var, final int i) {
            super.t(a0Var, i);
            final f51 f51Var = (f51) this.j.get(i);
            final b.c cVar = (b.c) a0Var;
            int i2 = 0;
            if (k() > 1 && this.n.b()) {
                int i3 = R$drawable.ic_drag_handle_grey600_24dp;
                ImageView imageView = cVar.A;
                imageView.setVisibility(0);
                imageView.setImageResource(i3);
                imageView.setOnTouchListener(new View.OnTouchListener() { // from class: i51
                    @Override // android.view.View.OnTouchListener
                    public final boolean onTouch(View view, MotionEvent motionEvent) {
                        b bVar = this;
                        bVar.getClass();
                        if (motionEvent.getActionMasked() != 0) {
                            return false;
                        }
                        bVar.i.u(cVar);
                        return false;
                    }
                });
            }
            ya1 ya1Var = new ya1();
            if (gq2.l("translate.enabled")) {
                sk skVar = bq.a;
                if (!N.M09VlOh_("DetailedLanguageSettings")) {
                    int i4 = N.MeNcRA0y(f51Var.a) ? 0 : R$drawable.ic_check_googblue_24dp;
                    int i5 = R$string.languages_item_option_offer_to_translate;
                    HashMap mapE = PropertyModel.e(o81.k);
                    PropertyModel.p pVar = o81.a;
                    int i6 = zj.a;
                    PropertyModel.d dVar = new PropertyModel.d();
                    dVar.a = i5;
                    mapE.put(pVar, dVar);
                    PropertyModel.p pVar2 = o81.f;
                    PropertyModel.d dVar2 = new PropertyModel.d();
                    dVar2.a = 0;
                    mapE.put(pVar2, dVar2);
                    PropertyModel.p pVar3 = o81.e;
                    PropertyModel.d dVar3 = new PropertyModel.d();
                    dVar3.a = i4;
                    mapE.put(pVar3, dVar3);
                    PropertyModel.n nVar = o81.g;
                    PropertyModel.a aVar = new PropertyModel.a();
                    aVar.a = f51Var.d;
                    mapE.put(nVar, aVar);
                    PropertyModel.p pVar4 = o81.h;
                    int i7 = R$color.default_icon_color_secondary_tint_list;
                    PropertyModel.d dVar4 = new PropertyModel.d();
                    dVar4.a = i7;
                    mapE.put(pVar4, dVar4);
                    PropertyModel propertyModel = new PropertyModel(mapE);
                    xa1 xa1Var = new xa1(1, propertyModel);
                    propertyModel.n(pVar4, org.chromium.chrome.R$color.default_icon_color_accent1_tint_list);
                    ya1Var.t(xa1Var);
                }
            }
            int iK = k();
            ya1Var.t(zd.e(R$string.remove, 0, 0, iK > 1));
            if (!this.n.b()) {
                if (i > 0) {
                    ya1Var.t(zd.c(R$string.menu_item_move_to_top, 0, 0));
                    ya1Var.t(zd.c(R$string.menu_item_move_up, 0, 0));
                }
                if (i < iK - 1) {
                    ya1Var.t(zd.c(R$string.menu_item_move_down, 0, 0));
                }
            }
            cVar.u(new qy(this, ya1Var, new k81.a() { // from class: py
                @Override // k81.a
                public final void a(PropertyModel propertyModel2) {
                    ContentLanguagesPreference.a aVar2 = this.e;
                    aVar2.getClass();
                    int iH = propertyModel2.h(o81.a);
                    int i8 = R$string.languages_item_option_offer_to_translate;
                    f51 f51Var2 = f51Var;
                    if (iH == i8) {
                        boolean z = propertyModel2.h(o81.e) == 0;
                        N.Mt0H9F3d(f51Var2.a, !z);
                        c.g(z ? 7 : 6);
                    } else if (iH == R$string.remove) {
                        c cVarB = c.b();
                        String str = f51Var2.a;
                        cVarB.getClass();
                        N.Me60Lv4_(str, false);
                        c.a aVar3 = cVarB.b;
                        if (aVar3 != null) {
                            ((ContentLanguagesPreference.a) aVar3).I();
                        }
                        c.g(3);
                    } else if (iH == R$string.menu_item_move_up) {
                        c.b().f(f51Var2.a, -1);
                    } else if (iH == R$string.menu_item_move_down) {
                        c.b().f(f51Var2.a, 1);
                    } else if (iH == R$string.menu_item_move_to_top) {
                        c.b().f(f51Var2.a, -i);
                    }
                    if (iH != R$string.remove) {
                        aVar2.o();
                    }
                }
            }, i2));
        }
    }

    public ContentLanguagesPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.U = new a(context);
    }

    @Override // androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        this.S = (TextView) n32Var.u(R$id.add_language);
        int i = R$drawable.plus;
        Context context = this.e;
        za3 za3VarA = za3.a(context, i);
        za3VarA.setTint(ii2.b(context));
        this.S.setCompoundDrawablesRelativeWithIntrinsicBounds(za3VarA, (Drawable) null, (Drawable) null, (Drawable) null);
        this.S.setOnClickListener(new by1(11, this));
        this.T = (RecyclerView) n32Var.u(R$id.language_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        this.T.setLayoutManager(linearLayoutManager);
        if (this.T.getItemDecorationCount() == 0) {
            this.T.g(new i(context, linearLayoutManager.p));
        }
        RecyclerView.e adapter = this.T.getAdapter();
        a aVar = this.U;
        if (adapter != aVar) {
            this.T.setAdapter(aVar);
            c.b().b = aVar;
            aVar.I();
        }
    }
}
