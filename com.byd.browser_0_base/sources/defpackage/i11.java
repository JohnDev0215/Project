package defpackage;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import org.chromium.chrome.R$id;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: InstanceSwitcherItemViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i11 {
    public static void a(PropertyModel propertyModel, View view, v62 v62Var) {
        PropertyModel.r<Drawable> rVar = h11.c;
        if (rVar == v62Var) {
            ((ImageView) view.findViewById(R$id.favicon)).setImageDrawable((Drawable) propertyModel.j(rVar));
            return;
        }
        PropertyModel.r<String> rVar2 = h11.d;
        if (rVar2 == v62Var) {
            TextView textView = (TextView) view.findViewById(R$id.title);
            String str = (String) propertyModel.j(rVar2);
            if (str != null) {
                textView.setText(str);
                return;
            } else {
                textView.setVisibility(8);
                return;
            }
        }
        PropertyModel.r<String> rVar3 = h11.e;
        if (rVar3 == v62Var) {
            TextView textView2 = (TextView) view.findViewById(R$id.desc);
            String str2 = (String) propertyModel.j(rVar3);
            if (str2 != null) {
                textView2.setText(str2);
                return;
            } else {
                textView2.setVisibility(8);
                return;
            }
        }
        PropertyModel.n nVar = h11.a;
        if (nVar == v62Var) {
            boolean zK = propertyModel.k(nVar);
            view.findViewById(R$id.current).setVisibility(zK ? 0 : 4);
            view.findViewById(R$id.more).setVisibility(zK ? 4 : 0);
            return;
        }
        PropertyModel.r<View.OnClickListener> rVar4 = h11.g;
        if (rVar4 == v62Var) {
            view.setOnClickListener((View.OnClickListener) propertyModel.j(rVar4));
            return;
        }
        PropertyModel.r<l81> rVar5 = h11.h;
        if (rVar5 == v62Var) {
            ((ListMenuButton) view.findViewById(R$id.more)).setDelegate((l81) propertyModel.j(rVar5));
            return;
        }
        PropertyModel.n nVar2 = h11.b;
        if (nVar2 == v62Var) {
            View viewFindViewById = view.findViewById(R$id.new_window);
            View viewFindViewById2 = view.findViewById(R$id.max_info);
            boolean zK2 = propertyModel.k(nVar2);
            viewFindViewById.setVisibility(zK2 ? 0 : 8);
            viewFindViewById2.setVisibility(zK2 ? 8 : 0);
        }
    }
}
