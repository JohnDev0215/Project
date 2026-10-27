package defpackage;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$style;
import org.chromium.components.browser_ui.styles.R$attr;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;
import org.chromium.ui.widget.ViewLookupCachingFrameLayout;

/* JADX INFO: compiled from: BookmarkSaveFlowViewBinder.java */
/* JADX INFO: loaded from: classes.dex */
public final class zg implements j.a<PropertyModel, ViewLookupCachingFrameLayout, v62> {
    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        int color;
        PropertyModel propertyModel = (PropertyModel) kVar;
        ViewLookupCachingFrameLayout viewLookupCachingFrameLayout = (ViewLookupCachingFrameLayout) obj;
        v62 v62Var = (v62) obj2;
        PropertyModel.r<View.OnClickListener> rVar = yg.a;
        if (v62Var == rVar) {
            viewLookupCachingFrameLayout.findViewById(R$id.bookmark_edit).setOnClickListener((View.OnClickListener) propertyModel.j(rVar));
            return;
        }
        PropertyModel.r<Drawable> rVar2 = yg.b;
        if (v62Var == rVar2) {
            ((ImageView) viewLookupCachingFrameLayout.findViewById(R$id.bookmark_select_folder)).setImageDrawable((Drawable) propertyModel.j(rVar2));
            return;
        }
        PropertyModel.n nVar = yg.c;
        if (v62Var == nVar) {
            ((ImageView) viewLookupCachingFrameLayout.findViewById(R$id.bookmark_select_folder)).setEnabled(propertyModel.k(nVar));
            return;
        }
        PropertyModel.r<View.OnClickListener> rVar3 = yg.d;
        if (v62Var == rVar3) {
            viewLookupCachingFrameLayout.findViewById(R$id.bookmark_select_folder).setOnClickListener((View.OnClickListener) propertyModel.j(rVar3));
            return;
        }
        PropertyModel.r<Integer> rVar4 = yg.e;
        if (v62Var == rVar4) {
            ((ImageView) viewLookupCachingFrameLayout.findViewById(R$id.notification_switch_start_icon)).setImageDrawable(AppCompatResources.a(viewLookupCachingFrameLayout.getContext(), ((Integer) propertyModel.j(rVar4)).intValue()));
            return;
        }
        PropertyModel.r<CharSequence> rVar5 = yg.f;
        if (v62Var == rVar5) {
            ((TextView) viewLookupCachingFrameLayout.findViewById(R$id.notification_switch_subtitle)).setText((CharSequence) propertyModel.j(rVar5));
            return;
        }
        PropertyModel.r<CharSequence> rVar6 = yg.g;
        if (v62Var == rVar6) {
            ((TextView) viewLookupCachingFrameLayout.findViewById(R$id.notification_switch_title)).setText((CharSequence) propertyModel.j(rVar6));
            return;
        }
        PropertyModel.n nVar2 = yg.h;
        if (v62Var == nVar2) {
            ((CompoundButton) viewLookupCachingFrameLayout.findViewById(R$id.notification_switch)).setChecked(propertyModel.k(nVar2));
            return;
        }
        PropertyModel.r<CompoundButton.OnCheckedChangeListener> rVar7 = yg.i;
        if (v62Var == rVar7) {
            ((CompoundButton) viewLookupCachingFrameLayout.findViewById(R$id.notification_switch)).setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) propertyModel.j(rVar7));
            return;
        }
        PropertyModel.n nVar3 = yg.j;
        if (v62Var == nVar3) {
            viewLookupCachingFrameLayout.findViewById(R$id.notification_switch_divider).setVisibility(propertyModel.k(nVar3) ? 0 : 8);
            viewLookupCachingFrameLayout.findViewById(R$id.notification_switch_container).setVisibility(propertyModel.k(nVar3) ? 0 : 8);
            return;
        }
        PropertyModel.n nVar4 = yg.k;
        if (v62Var != nVar4) {
            PropertyModel.r<CharSequence> rVar8 = yg.l;
            if (v62Var == rVar8) {
                ((TextView) viewLookupCachingFrameLayout.findViewById(R$id.subtitle_text)).setText((CharSequence) propertyModel.j(rVar8));
                return;
            }
            PropertyModel.r<CharSequence> rVar9 = yg.m;
            if (v62Var == rVar9) {
                ((TextView) viewLookupCachingFrameLayout.findViewById(R$id.title_text)).setText((CharSequence) propertyModel.j(rVar9));
                return;
            }
            return;
        }
        boolean zK = propertyModel.k(nVar4);
        Drawable drawable = ((ImageView) viewLookupCachingFrameLayout.findViewById(R$id.notification_switch_start_icon)).getDrawable();
        if (drawable != null) {
            if (zK) {
                color = ii2.g(viewLookupCachingFrameLayout.getContext(), R$attr.colorOnSurface);
            } else {
                Context context = viewLookupCachingFrameLayout.getContext();
                int i = R$color.default_icon_color_disabled;
                Object obj3 = bz.a;
                color = context.getColor(i);
            }
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        }
        c6.f((TextView) viewLookupCachingFrameLayout.findViewById(R$id.notification_switch_title), zK ? R$style.TextAppearance_TextMedium_Primary : R$style.TextAppearance_TextMedium_Disabled);
    }
}
