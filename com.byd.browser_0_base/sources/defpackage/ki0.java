package defpackage;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: ExistingAccountRowViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ki0 implements j.a<PropertyModel, View, v62> {
    public static void a(fb0 fb0Var, View view) {
        ((ImageView) view.findViewById(R$id.account_image)).setImageDrawable(fb0Var.b);
        TextView textView = (TextView) view.findViewById(R$id.account_text_primary);
        String str = fb0Var.c;
        boolean zIsEmpty = TextUtils.isEmpty(str);
        String str2 = fb0Var.a;
        boolean z = fb0Var.e;
        if (!zIsEmpty) {
            textView.setText(str);
        } else if (z || !(iv.e().g("force-hide-non-displayable-account-email-fre") || bq.C.a())) {
            textView.setText(str2);
        } else {
            textView.setText(R$string.default_google_account_username);
        }
        TextView textView2 = (TextView) view.findViewById(R$id.account_text_secondary);
        if (!z && (iv.e().g("force-hide-non-displayable-account-email-fre") || bq.C.a())) {
            textView2.setVisibility(8);
            return;
        }
        int i = TextUtils.isEmpty(str) ? 8 : 0;
        if (i == 0) {
            textView2.setText(str2);
        }
        textView2.setVisibility(i);
    }

    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        PropertyModel propertyModel = (PropertyModel) kVar;
        View view = (View) obj;
        v62 v62Var = (v62) obj2;
        PropertyModel.r<fb0> rVar = h1.a;
        fb0 fb0Var = (fb0) propertyModel.j(rVar);
        if (v62Var == h1.b) {
            view.setOnClickListener(new rf(propertyModel, 10, fb0Var));
        } else if (v62Var == rVar) {
            a(fb0Var, view);
        } else {
            throw new IllegalArgumentException("Cannot update the view for propertyKey: " + v62Var);
        }
    }
}
