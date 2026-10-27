package defpackage;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: SearchBoxViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class eg2 implements j.a<PropertyModel, View, v62> {
    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        PropertyModel propertyModel = (PropertyModel) kVar;
        View view = (View) obj;
        v62 v62Var = (v62) obj2;
        ImageView imageView = (ImageView) view.findViewById(R$id.voice_search_button);
        ImageView imageView2 = (ImageView) view.findViewById(R$id.lens_camera_button);
        TextView textView = (TextView) view.findViewById(R$id.search_box_text);
        PropertyModel.n nVar = dg2.c;
        if (nVar == v62Var) {
            view.setVisibility(propertyModel.k(nVar) ? 0 : 8);
            return;
        }
        PropertyModel.o oVar = dg2.a;
        if (oVar == v62Var) {
            view.setAlpha(propertyModel.g(oVar));
            ao3.g(view, view.getAlpha() == 1.0f);
            return;
        }
        PropertyModel.r<Drawable> rVar = dg2.b;
        if (rVar == v62Var) {
            view.setBackground((Drawable) propertyModel.j(rVar));
            return;
        }
        PropertyModel.r<ColorStateList> rVar2 = dg2.f;
        if (rVar2 == v62Var) {
            imageView.setImageTintList((ColorStateList) propertyModel.j(rVar2));
            imageView2.setImageTintList((ColorStateList) propertyModel.j(rVar2));
            return;
        }
        PropertyModel.r<Drawable> rVar3 = dg2.e;
        if (rVar3 == v62Var) {
            imageView.setImageDrawable((Drawable) propertyModel.j(rVar3));
            return;
        }
        PropertyModel.n nVar2 = dg2.d;
        if (nVar2 == v62Var) {
            imageView.setVisibility(propertyModel.k(nVar2) ? 0 : 8);
            return;
        }
        PropertyModel.n nVar3 = dg2.h;
        if (nVar3 == v62Var) {
            imageView2.setVisibility(propertyModel.k(nVar3) ? 0 : 8);
            return;
        }
        PropertyModel.r<View.OnClickListener> rVar4 = dg2.i;
        if (rVar4 == v62Var) {
            imageView2.setOnClickListener((View.OnClickListener) propertyModel.j(rVar4));
            return;
        }
        PropertyModel.r<View.OnClickListener> rVar5 = dg2.l;
        if (rVar5 == v62Var) {
            textView.setOnClickListener((View.OnClickListener) propertyModel.j(rVar5));
            return;
        }
        PropertyModel.r<TextWatcher> rVar6 = dg2.m;
        if (rVar6 == v62Var) {
            textView.addTextChangedListener((TextWatcher) propertyModel.j(rVar6));
            return;
        }
        PropertyModel.r<String> rVar7 = dg2.j;
        if (rVar7 == v62Var) {
            textView.setText((CharSequence) propertyModel.j(rVar7));
            return;
        }
        PropertyModel.n nVar4 = dg2.k;
        if (nVar4 == v62Var) {
            textView.setHint(propertyModel.k(nVar4) ? view.getContext().getString(R$string.search_or_type_web_address) : null);
            return;
        }
        PropertyModel.r<View.OnClickListener> rVar8 = dg2.g;
        if (rVar8 == v62Var) {
            imageView.setOnClickListener((View.OnClickListener) propertyModel.j(rVar8));
            return;
        }
        PropertyModel.p pVar = dg2.n;
        if (pVar == v62Var) {
            textView.setHintTextColor(propertyModel.h(pVar));
            return;
        }
        PropertyModel.p pVar2 = dg2.o;
        if (pVar2 == v62Var) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = propertyModel.h(pVar2);
            view.setLayoutParams(layoutParams);
            return;
        }
        PropertyModel.p pVar3 = dg2.p;
        if (pVar3 == v62Var) {
            ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin = propertyModel.h(pVar3);
            return;
        }
        PropertyModel.p pVar4 = dg2.q;
        if (pVar4 == v62Var) {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), propertyModel.h(pVar4), view.getPaddingBottom());
            return;
        }
        PropertyModel.o oVar2 = dg2.r;
        if (oVar2 == v62Var) {
            textView.setTranslationX(propertyModel.g(oVar2));
            return;
        }
        PropertyModel.p pVar5 = dg2.s;
        if (pVar5 == v62Var) {
            int iH = propertyModel.h(pVar5);
            ViewGroup.LayoutParams layoutParams2 = imageView.getLayoutParams();
            if (layoutParams2 != null) {
                layoutParams2.height = iH;
                imageView.setLayoutParams(layoutParams2);
            }
            ViewGroup.LayoutParams layoutParams3 = imageView2.getLayoutParams();
            if (layoutParams3 != null) {
                layoutParams3.height = iH;
                imageView2.setLayoutParams(layoutParams3);
                return;
            }
            return;
        }
        PropertyModel.p pVar6 = dg2.t;
        if (pVar6 != v62Var) {
            PropertyModel.p pVar7 = dg2.u;
            if (pVar7 != v62Var || (marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView2.getLayoutParams()) == null) {
                return;
            }
            marginLayoutParams.leftMargin = propertyModel.h(pVar7);
            return;
        }
        int iH2 = propertyModel.h(pVar6);
        ViewGroup.LayoutParams layoutParams4 = imageView.getLayoutParams();
        if (layoutParams4 != null) {
            layoutParams4.width = iH2;
            imageView.setLayoutParams(layoutParams4);
        }
        ViewGroup.LayoutParams layoutParams5 = imageView2.getLayoutParams();
        if (layoutParams5 != null) {
            layoutParams5.width = iH2;
            imageView2.setLayoutParams(layoutParams5);
        }
    }
}
