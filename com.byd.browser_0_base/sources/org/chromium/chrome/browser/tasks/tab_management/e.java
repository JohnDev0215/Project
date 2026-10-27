package org.chromium.chrome.browser.tasks.tab_management;

import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup;
import defpackage.bq;
import defpackage.bz;
import defpackage.cy0;
import defpackage.rg1;
import defpackage.v62;
import defpackage.yp;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.tasks.tab_management.MessageCardView;
import org.chromium.chrome.browser.tasks.tab_management.n0;
import org.chromium.components.browser_ui.widget.text.TemplatePreservingTextView;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.widget.ButtonCompat;
import org.chromium.ui.widget.ChromeImageView;

/* JADX INFO: compiled from: MessageCardViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e {
    public static void a(PropertyModel propertyModel, ViewGroup viewGroup, v62 v62Var) {
        MessageCardView messageCardView = (MessageCardView) viewGroup;
        PropertyModel.r<String> rVar = f.c;
        if (rVar == v62Var) {
            messageCardView.g.setText((String) propertyModel.j(rVar));
            messageCardView.setActionButtonOnClickListener(new rg1(0, propertyModel));
            return;
        }
        PropertyModel.r<String> rVar2 = f.e;
        if (rVar2 == v62Var) {
            messageCardView.f.setText((CharSequence) propertyModel.j(rVar2));
            return;
        }
        PropertyModel.r<String> rVar3 = f.f;
        if (rVar3 == v62Var) {
            messageCardView.f.setTemplate((String) propertyModel.j(rVar3));
            return;
        }
        PropertyModel.r<MessageCardView.b> rVar4 = f.g;
        if (rVar4 == v62Var) {
            messageCardView.e.setImageDrawable(((MessageCardView.b) propertyModel.j(rVar4)).a());
            return;
        }
        PropertyModel.r<String> rVar5 = f.m;
        if (rVar5 == v62Var) {
            messageCardView.h.setContentDescription((String) propertyModel.j(rVar5));
            messageCardView.setDismissButtonOnClickListener(new cy0(1, propertyModel));
            return;
        }
        PropertyModel.o oVar = n0.a.b;
        if (oVar == v62Var) {
            messageCardView.setAlpha(propertyModel.g(oVar));
            return;
        }
        PropertyModel.n nVar = f.p;
        if (nVar == v62Var) {
            boolean zK = propertyModel.k(nVar);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) messageCardView.f.getLayoutParams();
            if (!zK) {
                int dimension = (int) messageCardView.getContext().getResources().getDimension(R$dimen.tab_grid_iph_item_description_margin);
                messageCardView.removeView(messageCardView.e);
                marginLayoutParams.setMargins(dimension, 0, 0, 0);
                return;
            } else {
                if (messageCardView.indexOfChild(messageCardView.e) == -1) {
                    messageCardView.addView(messageCardView.e, 0);
                    marginLayoutParams.setMargins(0, 0, 0, 0);
                    return;
                }
                return;
            }
        }
        PropertyModel.n nVar2 = f.s;
        if (nVar2 == v62Var) {
            boolean zK2 = propertyModel.k(nVar2);
            messageCardView.getClass();
            messageCardView.setBackgroundResource(zK2 ? R$drawable.incognito_card_bg : R$drawable.card_with_corners_background);
            if (!zK2) {
                ((GradientDrawable) messageCardView.getBackground()).setColor(yp.c(messageCardView.getContext(), bq.d.a() ? R$dimen.default_elevation_2 : R$dimen.card_elevation));
            }
            TemplatePreservingTextView templatePreservingTextView = messageCardView.f;
            templatePreservingTextView.setTextAppearance(templatePreservingTextView.getContext(), zK2 ? R$style.TextAppearance_TextMedium_Primary_Baseline_Light : R$style.TextAppearance_TextMedium_Primary);
            ButtonCompat buttonCompat = messageCardView.g;
            buttonCompat.setTextAppearance(buttonCompat.getContext(), zK2 ? R$style.TextAppearance_Button_Text_Blue_Dark : R$style.TextAppearance_Button_Text_Blue);
            ChromeImageView chromeImageView = messageCardView.h;
            chromeImageView.setImageTintList(bz.b(chromeImageView.getContext(), zK2 ? R$color.default_icon_color_light : R$color.default_icon_color_tint_list));
        }
    }
}
