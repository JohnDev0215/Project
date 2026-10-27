package org.chromium.chrome.browser.ui.appmenu;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import defpackage.b7;
import defpackage.bz;
import defpackage.c30;
import defpackage.j7;
import defpackage.pc;
import defpackage.q7;
import defpackage.r7;
import defpackage.rf;
import defpackage.v62;
import defpackage.wm3;
import defpackage.ya1;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.components.browser_ui.widget.text.TextViewWithCompoundDrawables;
import org.chromium.ui.base.LocalizationUtils;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.widget.ChromeImageButton;

/* JADX INFO: compiled from: AppMenuItemViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {
    public static final int[] a = {R$id.button_one, R$id.button_two, R$id.button_three, R$id.button_four, R$id.button_five};

    public static void a(PropertyModel propertyModel, View view, v62 v62Var) {
        int[] iArr;
        AnimatorSet animatorSet;
        PropertyModel.r<ya1> rVar = j7.n;
        if (v62Var == rVar) {
            ya1 ya1Var = (ya1) propertyModel.j(rVar);
            int size = ya1Var.size();
            ImageButton[] imageButtonArr = new ImageButton[size];
            int i = 0;
            while (true) {
                iArr = a;
                if (i >= size) {
                    break;
                }
                imageButtonArr[i] = (ImageButton) view.findViewById(iArr[i]);
                i++;
            }
            for (int i2 = size; i2 < 5; i2++) {
                ((ViewGroup) view).removeView(view.findViewById(iArr[i2]));
            }
            b7 b7Var = (b7) propertyModel.j(j7.l);
            for (int i3 = 0; i3 < size; i3++) {
                c(imageButtonArr[i3], ya1Var.get(i3).b, b7Var);
            }
            boolean zK = propertyModel.k(j7.m);
            int i4 = R$id.menu_item_enter_anim_id;
            if (size < 1) {
                animatorSet = new AnimatorSet();
            } else {
                float f = imageButtonArr[0].getContext().getResources().getDisplayMetrics().density * 10.0f * (LocalizationUtils.isLayoutRtl() == zK ? 1.0f : -1.0f);
                animatorSet = new AnimatorSet();
                AnimatorSet.Builder builderPlay = null;
                for (int i5 = 0; i5 < size; i5++) {
                    ImageButton imageButton = imageButtonArr[i5];
                    ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(imageButton, (Property<ImageButton, Float>) View.ALPHA, 0.0f, 1.0f);
                    ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(imageButton, (Property<ImageButton, Float>) View.TRANSLATION_X, f, 0.0f);
                    long j = i5 * 30;
                    objectAnimatorOfFloat.setStartDelay(j);
                    objectAnimatorOfFloat2.setStartDelay(j);
                    objectAnimatorOfFloat.setDuration(350L);
                    objectAnimatorOfFloat2.setDuration(350L);
                    if (builderPlay == null) {
                        builderPlay = animatorSet.play(objectAnimatorOfFloat);
                    } else {
                        builderPlay.with(objectAnimatorOfFloat);
                    }
                    builderPlay.with(objectAnimatorOfFloat2);
                }
                animatorSet.setStartDelay(80L);
                animatorSet.setInterpolator(pc.e);
                animatorSet.addListener(new q7(size, imageButtonArr));
            }
            view.setTag(i4, animatorSet);
            view.setBackgroundResource(R$drawable.menu_action_bar_bg);
            view.setEnabled(false);
        }
    }

    public static void b(PropertyModel propertyModel, View view, v62 v62Var) {
        boolean zK;
        boolean zK2;
        PropertyModel propertyModel2;
        r7.a(propertyModel, view, v62Var);
        PropertyModel.r<ya1> rVar = j7.n;
        if (v62Var != rVar) {
            PropertyModel.n nVar = j7.e;
            if (v62Var == nVar) {
                if (propertyModel.k(nVar)) {
                    wm3.b(view, new wm3.a(2));
                    return;
                } else {
                    wm3.a(view);
                    return;
                }
            }
            return;
        }
        ya1 ya1Var = (ya1) propertyModel.j(rVar);
        PropertyModel propertyModel3 = ya1Var.get(0).b;
        view.setId(propertyModel3.h(j7.a));
        TextViewWithCompoundDrawables textViewWithCompoundDrawables = (TextViewWithCompoundDrawables) view.findViewById(R$id.title);
        textViewWithCompoundDrawables.setText((CharSequence) propertyModel3.j(j7.b));
        PropertyModel.n nVar2 = j7.d;
        textViewWithCompoundDrawables.setEnabled(propertyModel3.k(nVar2));
        textViewWithCompoundDrawables.setFocusable(propertyModel3.k(nVar2));
        PropertyModel.r<Drawable> rVar2 = j7.h;
        Drawable drawable = null;
        textViewWithCompoundDrawables.setCompoundDrawablesRelative((Drawable) propertyModel3.j(rVar2), null, null, null);
        CharSequence charSequence = (CharSequence) propertyModel3.j(j7.c);
        if (TextUtils.isEmpty(charSequence)) {
            textViewWithCompoundDrawables.setContentDescription(null);
        } else {
            textViewWithCompoundDrawables.setContentDescription(charSequence);
        }
        b7 b7Var = (b7) propertyModel.j(j7.l);
        textViewWithCompoundDrawables.setOnClickListener(new rf(b7Var, 7, propertyModel3));
        if (propertyModel3.k(j7.e)) {
            wm3.b(view, new wm3.a(2));
        } else {
            wm3.a(view);
        }
        AppMenuItemIcon appMenuItemIcon = (AppMenuItemIcon) view.findViewById(R$id.checkbox);
        ChromeImageButton chromeImageButton = (ChromeImageButton) view.findViewById(R$id.button);
        boolean zK3 = true;
        if (ya1Var.size() == 2) {
            PropertyModel propertyModel4 = ya1Var.get(1).b;
            zK = propertyModel4.k(j7.f);
            zK2 = propertyModel4.k(j7.g);
            zK3 = propertyModel4.k(nVar2);
            drawable = (Drawable) propertyModel4.j(rVar2);
            propertyModel2 = propertyModel4;
        } else {
            zK = false;
            zK2 = false;
            propertyModel2 = null;
        }
        if (zK) {
            chromeImageButton.setVisibility(8);
            appMenuItemIcon.setVisibility(0);
            appMenuItemIcon.setChecked(zK2);
            appMenuItemIcon.setImageTintList(bz.b(appMenuItemIcon.getContext(), R$color.selection_control_button_tint_list));
            d(appMenuItemIcon, b7Var, propertyModel2);
            return;
        }
        if (drawable == null) {
            appMenuItemIcon.setVisibility(8);
            chromeImageButton.setVisibility(8);
            return;
        }
        appMenuItemIcon.setVisibility(8);
        chromeImageButton.setVisibility(0);
        if (!zK3) {
            Drawable drawable2 = (Drawable) propertyModel2.j(rVar2);
            drawable2.setTintList(bz.b(chromeImageButton.getContext(), R$color.default_icon_color_secondary_tint_list));
            propertyModel2.p(rVar2, drawable2);
        }
        c(chromeImageButton, propertyModel2, b7Var);
    }

    public static void c(ImageButton imageButton, PropertyModel propertyModel, b7 b7Var) {
        Drawable drawable = (Drawable) propertyModel.j(j7.h);
        int level = drawable.getLevel();
        imageButton.setImageDrawable(drawable);
        drawable.setLevel(level);
        if (propertyModel.k(j7.g)) {
            imageButton.setImageTintList(bz.b(imageButton.getContext(), R$color.default_icon_color_accent1_tint_list));
        }
        d(imageButton, b7Var, propertyModel);
    }

    public static void d(final View view, final b7 b7Var, final PropertyModel propertyModel) {
        PropertyModel.n nVar = j7.d;
        view.setEnabled(propertyModel.k(nVar));
        view.setFocusable(propertyModel.k(nVar));
        CharSequence charSequence = (CharSequence) propertyModel.j(j7.c);
        if (TextUtils.isEmpty(charSequence)) {
            view.setImportantForAccessibility(2);
        } else {
            view.setContentDescription(charSequence);
            view.setImportantForAccessibility(0);
        }
        view.setOnClickListener(new c30(b7Var, 7, propertyModel));
        view.setOnLongClickListener(new View.OnLongClickListener() { // from class: k7
            @Override // android.view.View.OnLongClickListener
            public final boolean onLongClick(View view2) {
                return b7Var.b(view, propertyModel);
            }
        });
        if (propertyModel.k(j7.e)) {
            wm3.b(view, new wm3.a(1));
        } else {
            wm3.a(view);
        }
        view.setVisibility(0);
    }
}
