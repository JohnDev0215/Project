package org.chromium.chrome.browser.tasks.tab_management;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.util.Size;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.elevation.ElevationOverlayProvider;
import defpackage.bq;
import defpackage.bz;
import defpackage.d03;
import defpackage.hl1;
import defpackage.ij3;
import defpackage.in3;
import defpackage.iu;
import defpackage.kg;
import defpackage.n3;
import defpackage.nn3;
import defpackage.oj2;
import defpackage.om3;
import defpackage.on0;
import defpackage.p5;
import defpackage.qo;
import defpackage.t02;
import defpackage.u73;
import defpackage.v62;
import defpackage.xl2;
import defpackage.xm1;
import defpackage.yq3;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.chromium.base.Callback;
import org.chromium.chrome.R$attr;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$integer;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tasks.tab_management.SelectableTabGridView;
import org.chromium.chrome.browser.tasks.tab_management.h0;
import org.chromium.chrome.browser.tasks.tab_management.j0;
import org.chromium.chrome.browser.tasks.tab_management.n0;
import org.chromium.chrome.browser.tasks.tab_management.u0;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.widget.ButtonCompat;
import org.chromium.ui.widget.ChromeImageView;
import org.chromium.ui.widget.ViewLookupCachingFrameLayout;

/* JADX INFO: compiled from: TabGridViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class s {
    public static void a(final PropertyModel propertyModel, ViewLookupCachingFrameLayout viewLookupCachingFrameLayout, v62 v62Var) {
        PropertyModel.r<String> rVar;
        PropertyModel.r<j0.l> rVar2 = u0.c;
        if (rVar2 == v62Var) {
            if (propertyModel.j(rVar2) == null) {
                viewLookupCachingFrameLayout.j(R$id.action_button).setOnClickListener(null);
                return;
            } else {
                viewLookupCachingFrameLayout.j(R$id.action_button).setOnClickListener(new ij3(3, propertyModel));
                return;
            }
        }
        PropertyModel.r<j0.l> rVar3 = u0.b;
        if (rVar3 == v62Var) {
            if (propertyModel.j(rVar3) == null) {
                viewLookupCachingFrameLayout.setOnClickListener(null);
                return;
            } else {
                viewLookupCachingFrameLayout.setOnClickListener(new kg(4, propertyModel));
                return;
            }
        }
        PropertyModel.r<j0.l> rVar4 = u0.m;
        if (rVar4 == v62Var) {
            final j0.l lVar = (j0.l) propertyModel.j(rVar4);
            ButtonCompat buttonCompat = (ButtonCompat) viewLookupCachingFrameLayout.j(R$id.create_group_button);
            if (lVar == null) {
                buttonCompat.setVisibility(8);
                buttonCompat.setOnClickListener(null);
                return;
            } else {
                buttonCompat.setVisibility(0);
                buttonCompat.setOnClickListener(new View.OnClickListener() { // from class: f03
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        lVar.a(propertyModel.h(u0.a));
                    }
                });
                return;
            }
        }
        PropertyModel.o oVar = n0.a.b;
        if (oVar == v62Var) {
            viewLookupCachingFrameLayout.setAlpha(propertyModel.g(oVar));
            return;
        }
        PropertyModel.r<String> rVar5 = u0.j;
        if (rVar5 == v62Var) {
            if (oj2.C(viewLookupCachingFrameLayout.getContext())) {
                return;
            }
            viewLookupCachingFrameLayout.j(R$id.action_button).setContentDescription(viewLookupCachingFrameLayout.getResources().getString(R$string.accessibility_tabstrip_btn_close_tab, (String) propertyModel.j(rVar5)));
            return;
        }
        PropertyModel.r<j0.i> rVar6 = u0.i;
        if (rVar6 == v62Var) {
            j0.i iVar = (j0.i) propertyModel.j(rVar6);
            if (iVar != null) {
                iVar.a(viewLookupCachingFrameLayout.j(R$id.tab_thumbnail));
                return;
            }
            return;
        }
        PropertyModel.p pVar = u0.n;
        if (pVar == v62Var) {
            ClosableTabGridView closableTabGridView = (ClosableTabGridView) viewLookupCachingFrameLayout;
            int iH = propertyModel.h(pVar);
            View viewJ = closableTabGridView.j(R$id.background_view);
            View viewJ2 = closableTabGridView.j(R$id.content_view);
            boolean z = iH == 2 || iH == 4;
            boolean z2 = iH == 4 || iH == 3;
            long j = iH == 0 ? 50L : 218L;
            float f = z ? 0.8f : 1.0f;
            if (!z2) {
                viewJ2 = closableTabGridView;
            }
            if (iH == 4) {
                viewJ.setVisibility(0);
            }
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.addListener(new iu(closableTabGridView, z, viewJ));
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewJ2, (Property<View, Float>) View.SCALE_X, f);
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(viewJ2, (Property<View, Float>) View.SCALE_Y, f);
            objectAnimatorOfFloat.setDuration(j);
            objectAnimatorOfFloat2.setDuration(j);
            animatorSet.play(objectAnimatorOfFloat).with(objectAnimatorOfFloat2);
            closableTabGridView.g = true;
            animatorSet.start();
            return;
        }
        PropertyModel.h hVar = u0.q;
        if (hVar == v62Var) {
            boolean zK = propertyModel.k(hVar);
            PropertyModel.n nVar = u0.k;
            f(viewLookupCachingFrameLayout, zK, propertyModel.k(nVar));
            propertyModel.k(hVar);
            propertyModel.k(nVar);
            return;
        }
        PropertyModel.r<View.AccessibilityDelegate> rVar7 = u0.w;
        if (rVar7 == v62Var) {
            viewLookupCachingFrameLayout.setAccessibilityDelegate((View.AccessibilityDelegate) propertyModel.j(rVar7));
            return;
        }
        if (u0.x == v62Var) {
            d(propertyModel, new on0(18, viewLookupCachingFrameLayout), true);
            return;
        }
        PropertyModel.r<j0.g> rVar8 = u0.y;
        if (rVar8 == v62Var) {
            CouponCardView couponCardView = (CouponCardView) viewLookupCachingFrameLayout.j(R$id.coupon_info_box_outer);
            if (propertyModel.j(rVar8) == null) {
                couponCardView.setVisibility(8);
                return;
            } else {
                d(propertyModel, new q0(couponCardView, 1, propertyModel), false);
                return;
            }
        }
        PropertyModel.n nVar2 = u0.B;
        if (nVar2 == v62Var) {
            if (propertyModel.k(nVar2)) {
                View viewFindViewById = ((PriceCardView) viewLookupCachingFrameLayout.j(R$id.price_info_box_outer)).findViewById(R$id.current_price);
                WeakReference<Bitmap> weakReference = LargeMessageCardView.o;
                nn3 nn3Var = new nn3(viewFindViewById);
                Context context = viewFindViewById.getContext();
                int i = R$string.price_drop_spotted_lower_price;
                u73 u73Var = new u73(context, viewFindViewById, i, i, nn3Var, qo.f().c());
                u73Var.f.j.setFocusable(true);
                u73Var.e(true);
                u73Var.f();
                return;
            }
            return;
        }
        PropertyModel.n nVar3 = u0.k;
        if (nVar3 == v62Var) {
            viewLookupCachingFrameLayout.setSelected(propertyModel.k(nVar3));
            propertyModel.k(hVar);
            propertyModel.k(nVar3);
            return;
        }
        if (oj2.C(viewLookupCachingFrameLayout.getContext()) && (rVar = u0.A) == v62Var) {
            viewLookupCachingFrameLayout.j(R$id.action_button).setContentDescription((CharSequence) propertyModel.j(rVar));
        }
    }

    public static void b(PropertyModel propertyModel, ViewLookupCachingFrameLayout viewLookupCachingFrameLayout, v62 v62Var) {
        PropertyModel.r<String> rVar = u0.j;
        if (rVar == v62Var) {
            String str = (String) propertyModel.j(rVar);
            TextView textView = (TextView) viewLookupCachingFrameLayout.j(R$id.tab_title);
            textView.setText(str);
            textView.setContentDescription(viewLookupCachingFrameLayout.getResources().getString(R$string.accessibility_tabstrip_tab, str));
            return;
        }
        PropertyModel.n nVar = u0.k;
        if (nVar == v62Var) {
            f(viewLookupCachingFrameLayout, propertyModel.k(u0.q), propertyModel.k(nVar));
            h(viewLookupCachingFrameLayout, propertyModel);
            return;
        }
        if (u0.d == v62Var) {
            if (oj2.C(viewLookupCachingFrameLayout.getContext())) {
                return;
            }
            h(viewLookupCachingFrameLayout, propertyModel);
            return;
        }
        if (u0.f == v62Var) {
            if (oj2.C(viewLookupCachingFrameLayout.getContext())) {
                h(viewLookupCachingFrameLayout, propertyModel);
                return;
            }
            return;
        }
        if (u0.g == v62Var) {
            i(viewLookupCachingFrameLayout, propertyModel);
            return;
        }
        PropertyModel.r<String> rVar2 = u0.z;
        if (rVar2 == v62Var) {
            viewLookupCachingFrameLayout.setContentDescription((CharSequence) propertyModel.j(rVar2));
            return;
        }
        PropertyModel.r<Size> rVar3 = u0.h;
        if (rVar3 == v62Var) {
            Size size = (Size) propertyModel.j(rVar3);
            viewLookupCachingFrameLayout.setMinimumHeight(size.getHeight());
            viewLookupCachingFrameLayout.setMinimumWidth(size.getWidth());
            viewLookupCachingFrameLayout.getLayoutParams().height = size.getHeight();
            viewLookupCachingFrameLayout.getLayoutParams().width = size.getWidth();
            viewLookupCachingFrameLayout.setLayoutParams(viewLookupCachingFrameLayout.getLayoutParams());
            ((TabGridThumbnailView) viewLookupCachingFrameLayout.j(R$id.tab_thumbnail)).getLayoutParams().height = -1;
            i(viewLookupCachingFrameLayout, propertyModel);
        }
    }

    public static void c(final PropertyModel propertyModel, final ViewLookupCachingFrameLayout viewLookupCachingFrameLayout, v62 v62Var) {
        final int iH = propertyModel.h(u0.a);
        PropertyModel.n nVar = u0.k;
        if (nVar == v62Var) {
            g(viewLookupCachingFrameLayout, propertyModel.k(u0.q), propertyModel.k(nVar));
            return;
        }
        if (u0.o == v62Var) {
            viewLookupCachingFrameLayout.setOnClickListener(new hl1(propertyModel, iH, 1, viewLookupCachingFrameLayout));
            viewLookupCachingFrameLayout.setOnLongClickListener(new View.OnLongClickListener() { // from class: e03
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    ((j0.l) propertyModel.j(u0.o)).a(iH);
                    ViewLookupCachingFrameLayout viewLookupCachingFrameLayout2 = viewLookupCachingFrameLayout;
                    return ((SelectableTabGridView) viewLookupCachingFrameLayout2).onLongClick(viewLookupCachingFrameLayout2);
                }
            });
            return;
        }
        PropertyModel.r<org.chromium.components.browser_ui.widget.selectable_list.a<Integer>> rVar = u0.p;
        if (rVar == v62Var) {
            SelectableTabGridView selectableTabGridView = (SelectableTabGridView) viewLookupCachingFrameLayout;
            selectableTabGridView.setSelectionDelegate((org.chromium.components.browser_ui.widget.selectable_list.a) propertyModel.j(rVar));
            selectableTabGridView.setItem(Integer.valueOf(iH));
        } else {
            PropertyModel.h hVar = u0.q;
            if (hVar == v62Var) {
                f(viewLookupCachingFrameLayout, propertyModel.k(hVar), propertyModel.k(nVar));
                g(viewLookupCachingFrameLayout, propertyModel.k(hVar), propertyModel.k(nVar));
            }
        }
    }

    public static void d(PropertyModel propertyModel, Callback<xl2.c> callback, boolean z) {
        PropertyModel.r<j0.k> rVar = u0.x;
        if (propertyModel.j(rVar) == null) {
            callback.onResult(null);
            return;
        }
        j0.k kVar = (j0.k) propertyModel.j(rVar);
        p0 p0Var = new p0(callback, 1, z);
        kVar.getClass();
        xl2.o(new xm1(kVar, 12, p0Var), kVar.a);
    }

    public static void e(ViewLookupCachingFrameLayout viewLookupCachingFrameLayout, PropertyModel propertyModel, h0.c cVar) {
        ImageView imageView = (ImageView) viewLookupCachingFrameLayout.j(R$id.tab_favicon);
        if (cVar == null) {
            imageView.setImageDrawable(null);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            propertyModel.k(u0.k);
            imageView.setImageDrawable(cVar.a);
            int dimension = (int) imageView.getContext().getResources().getDimension(R$dimen.tab_grid_card_favicon_padding);
            imageView.setPadding(dimension, dimension, dimension, dimension);
        }
    }

    public static void f(ViewLookupCachingFrameLayout viewLookupCachingFrameLayout, boolean z, boolean z2) {
        int iB;
        ColorStateList colorStateListValueOf;
        View viewJ = viewLookupCachingFrameLayout.j(R$id.card_view);
        TabGridThumbnailView tabGridThumbnailView = (TabGridThumbnailView) viewLookupCachingFrameLayout.j(R$id.tab_thumbnail);
        ChromeImageView chromeImageView = (ChromeImageView) viewLookupCachingFrameLayout.j(R$id.background_view);
        viewJ.getBackground().mutate();
        Context context = viewJ.getContext();
        if (z) {
            int i = bq.d.a() ? R$color.default_bg_color_dark_elev_4_gm3_baseline : R$color.incognito_tab_bg_color;
            if (z2) {
                i = R$color.incognito_tab_bg_selected_color;
            }
            Object obj = bz.a;
            iB = context.getColor(i);
        } else {
            float dimension = bq.d.a() ? context.getResources().getDimension(R$dimen.default_elevation_5) : context.getResources().getDimension(R$dimen.tab_bg_elevation);
            if (z2) {
                int i2 = R$color.no_incognito_tab_bg_selected_color;
                Object obj2 = bz.a;
                iB = context.getColor(i2);
            } else {
                ElevationOverlayProvider elevationOverlayProvider = new ElevationOverlayProvider(context);
                iB = elevationOverlayProvider.b(dimension, elevationOverlayProvider.d);
            }
        }
        ColorStateList colorStateListValueOf2 = ColorStateList.valueOf(iB);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.i.j(viewJ, colorStateListValueOf2);
        if (tabGridThumbnailView.getDrawable() == null || (tabGridThumbnailView.getDrawable() instanceof ColorDrawable)) {
            tabGridThumbnailView.setImageDrawable(new ColorDrawable(n3.f0(tabGridThumbnailView.getContext(), z, z2)));
        }
        if (oj2.D(viewLookupCachingFrameLayout.getContext())) {
            Context context2 = chromeImageView.getContext();
            if (z) {
                int i3 = bq.d.a() ? R$color.default_bg_color_dark_elev_1_gm3_baseline : R$color.incognito_tab_group_hovered_bg_color;
                if (z2) {
                    i3 = R$color.incognito_tab_group_hovered_bg_selected_color;
                }
                colorStateListValueOf = bz.b(context2, i3);
            } else if (z2) {
                colorStateListValueOf = ColorStateList.valueOf(oj2.e(oj2.n(R$attr.colorPrimary, context2, "TabUiThemeProvider"), context2.getResources().getInteger(R$integer.tab_grid_hovered_card_background_selected_color_alpha)));
            } else {
                float dimension2 = context2.getResources().getDimension(R$dimen.default_elevation_4);
                ElevationOverlayProvider elevationOverlayProvider2 = new ElevationOverlayProvider(context2);
                colorStateListValueOf = ColorStateList.valueOf(oj2.e(elevationOverlayProvider2.b(dimension2, elevationOverlayProvider2.d), context2.getResources().getInteger(R$integer.tab_grid_hovered_card_background_color_alpha)));
            }
            om3.i.j(chromeImageView, colorStateListValueOf);
        }
    }

    public static void g(ViewLookupCachingFrameLayout viewLookupCachingFrameLayout, boolean z, boolean z2) {
        ColorStateList colorStateListValueOf;
        ColorStateList colorStateListB;
        int integer = viewLookupCachingFrameLayout.getResources().getInteger(R$integer.list_item_level_default);
        int integer2 = viewLookupCachingFrameLayout.getResources().getInteger(R$integer.list_item_level_selected);
        ImageView imageView = (ImageView) viewLookupCachingFrameLayout.j(R$id.action_button);
        Drawable background = imageView.getBackground();
        if (z2) {
            integer = integer2;
        }
        background.setLevel(integer);
        Drawable drawableMutate = imageView.getBackground().mutate();
        Context context = viewLookupCachingFrameLayout.getContext();
        if (z) {
            colorStateListValueOf = bz.b(context, z2 ? R$color.incognito_tab_action_button_selected_color : R$color.incognito_tab_action_button_color);
        } else {
            colorStateListValueOf = ColorStateList.valueOf(z2 ? oj2.n(R$attr.colorOnPrimary, context, "TabUiThemeProvider") : oj2.n(R$attr.colorOnSurfaceVariant, context, "TabUiThemeProvider"));
        }
        drawableMutate.setTintList(colorStateListValueOf);
        imageView.getDrawable().setAlpha(z2 ? 255 : 0);
        if (z2) {
            Context context2 = viewLookupCachingFrameLayout.getContext();
            colorStateListB = z ? bz.b(context2, R$color.incognito_tab_bg_selected_color) : ColorStateList.valueOf(oj2.n(R$attr.colorPrimary, context2, "TabUiThemeProvider"));
        } else {
            colorStateListB = null;
        }
        imageView.setImageTintList(colorStateListB);
        if (z2) {
            ((p5) imageView.getDrawable()).start();
        }
    }

    public static void h(ViewLookupCachingFrameLayout viewLookupCachingFrameLayout, PropertyModel propertyModel) {
        if (!oj2.C(viewLookupCachingFrameLayout.getContext())) {
            e(viewLookupCachingFrameLayout, propertyModel, (h0.c) propertyModel.j(u0.d));
            return;
        }
        h0.d dVar = (h0.d) propertyModel.j(u0.f);
        if (dVar == null) {
            e(viewLookupCachingFrameLayout, propertyModel, null);
        } else {
            dVar.a(new d03(dVar, propertyModel, viewLookupCachingFrameLayout, 0));
        }
    }

    public static void i(ViewLookupCachingFrameLayout viewLookupCachingFrameLayout, PropertyModel propertyModel) {
        j0.o oVar = (j0.o) propertyModel.j(u0.g);
        TabGridThumbnailView tabGridThumbnailView = (TabGridThumbnailView) viewLookupCachingFrameLayout.j(R$id.tab_thumbnail);
        Size size = null;
        if (oVar == null) {
            tabGridThumbnailView.setImageDrawable(null);
            return;
        }
        boolean zK = propertyModel.k(u0.k);
        Size size2 = (Size) propertyModel.j(u0.h);
        yq3 yq3Var = new yq3(oVar, propertyModel, viewLookupCachingFrameLayout, tabGridThumbnailView, 2);
        if (DeviceFormFactor.a(viewLookupCachingFrameLayout.getContext()) && size2 != null) {
            size = new Size(size2.getWidth(), size2.getHeight() - (((int) viewLookupCachingFrameLayout.getContext().getResources().getDimension(R$dimen.tab_grid_card_thumbnail_margin)) * 2));
        }
        oj2.C(viewLookupCachingFrameLayout.getContext());
        t02 t02Var = new t02(yq3Var, 6);
        oVar.a.l(oVar.b, size, t02Var, oVar.c, oVar.d, zK);
    }
}
