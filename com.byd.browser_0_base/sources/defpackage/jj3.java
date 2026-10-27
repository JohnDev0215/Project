package defpackage;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: UpdateMenuItemViewBinder.java */
/* JADX INFO: loaded from: classes.dex */
public final class jj3 implements m50 {
    public final if1 a = (if1) hj3.a().d.a;

    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        PropertyModel propertyModel = (PropertyModel) kVar;
        View view = (View) obj;
        v62 v62Var = (v62) obj2;
        r7.a(propertyModel, view, v62Var);
        PropertyModel.p pVar = j7.a;
        if1 if1Var = this.a;
        if (v62Var == pVar) {
            view.setId(propertyModel.h(pVar));
            if (if1Var != null) {
                TextView textView = (TextView) view.findViewById(R$id.menu_item_summary);
                if (TextUtils.isEmpty(if1Var.c)) {
                    textView.setText("");
                    textView.setVisibility(8);
                    return;
                } else {
                    textView.setText(if1Var.c);
                    textView.setVisibility(0);
                    return;
                }
            }
            return;
        }
        PropertyModel.r<CharSequence> rVar = j7.b;
        if (v62Var == rVar) {
            TextView textView2 = (TextView) view.findViewById(R$id.menu_item_text);
            if (if1Var == null) {
                textView2.setText((CharSequence) propertyModel.j(rVar));
                return;
            } else {
                textView2.setText(if1Var.a);
                textView2.setTextColor(bz.b(view.getContext(), if1Var.b));
                return;
            }
        }
        PropertyModel.r<CharSequence> rVar2 = j7.c;
        if (v62Var == rVar2) {
            TextView textView3 = (TextView) view.findViewById(R$id.menu_item_text);
            if (if1Var == null) {
                textView3.setContentDescription((CharSequence) propertyModel.j(rVar2));
                return;
            } else {
                textView3.setContentDescription(view.getResources().getString(if1Var.a));
                return;
            }
        }
        PropertyModel.r<Drawable> rVar3 = j7.h;
        if (v62Var == rVar3) {
            ImageView imageView = (ImageView) view.findViewById(R$id.menu_item_icon);
            if (if1Var != null) {
                imageView.setImageResource(if1Var.d);
                return;
            } else {
                imageView.setImageDrawable((Drawable) propertyModel.j(rVar3));
                imageView.setVisibility(0);
                return;
            }
        }
        PropertyModel.n nVar = j7.d;
        if (v62Var != nVar) {
            if (v62Var == j7.l) {
                view.setOnClickListener(new ij3(0, propertyModel));
            }
        } else {
            view.findViewById(R$id.menu_item_text).setEnabled(propertyModel.k(nVar));
            if (if1Var != null) {
                view.setEnabled(if1Var.e);
            }
        }
    }

    @Override // defpackage.m50
    public final int c(int i) {
        if (i == 0) {
            return R$layout.update_menu_item;
        }
        return -1;
    }

    @Override // defpackage.m50
    public final int d(ContextThemeWrapper contextThemeWrapper) {
        int dimensionPixelSize = contextThemeWrapper.getResources().getDimensionPixelSize(R$dimen.overflow_menu_update_min_height);
        return (contextThemeWrapper.getResources().getDimensionPixelSize(R$dimen.overflow_menu_update_padding) * 2) + Math.max(dimensionPixelSize, AppCompatResources.a(contextThemeWrapper, R$drawable.menu_update).getIntrinsicHeight());
    }

    @Override // defpackage.m50
    public final int getItemViewType(int i) {
        return i == R$id.update_menu_id ? 0 : -1;
    }

    @Override // defpackage.m50
    public final void getViewTypeCount() {
    }
}
