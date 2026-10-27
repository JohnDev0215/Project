package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import org.chromium.chrome.R$attr;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$id;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: SuggestionViewViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class gw2 {
    public static void a(PropertyModel propertyModel, View view, v62 v62Var) {
        PropertyModel.r<ew2> rVar = fw2.b;
        if (v62Var == rVar) {
            ((TextView) view.findViewById(R$id.line_1)).setText((CharSequence) propertyModel.j(rVar));
            return;
        }
        if (v62Var == bw2.a) {
            b(view, propertyModel);
            return;
        }
        PropertyModel.n nVar = fw2.a;
        if (v62Var == nVar) {
            b(view, propertyModel);
            ((TextView) view.findViewById(R$id.line_2)).setTextDirection(propertyModel.k(nVar) ? 0 : 3);
            return;
        }
        PropertyModel.r<ew2> rVar2 = fw2.c;
        if (v62Var != rVar2) {
            PropertyModel.n nVar2 = fw2.d;
            if (v62Var == nVar2) {
                ((TextView) view.findViewById(R$id.line_1)).setMaxLines(propertyModel.k(nVar2) ? 2 : 1);
                return;
            }
            return;
        }
        TextView textView = (TextView) view.findViewById(R$id.line_2);
        ew2 ew2Var = (ew2) propertyModel.j(rVar2);
        if (TextUtils.isEmpty(ew2Var)) {
            textView.setVisibility(8);
        } else {
            textView.setText(ew2Var);
            textView.setVisibility(0);
        }
    }

    public static void b(View view, PropertyModel propertyModel) {
        int color;
        boolean zK = propertyModel.k(fw2.a);
        int iH = propertyModel.h(bw2.a);
        TextView textView = (TextView) view.findViewById(R$id.line_1);
        TextView textView2 = (TextView) view.findViewById(R$id.line_2);
        Context context = view.getContext();
        textView.setTextColor(iH == 2 ? context.getColor(R$color.default_text_color_light) : oj2.n(R$attr.colorOnSurface, context, "OmniboxResourceProvider"));
        if (zK) {
            color = iH == 2 ? context.getColor(R$color.default_text_color_secondary_light) : oj2.n(R$attr.colorOnSurfaceVariant, context, "OmniboxResourceProvider");
        } else if (iH == 2) {
            color = context.getColor(R$color.suggestion_url_color_incognito);
        } else {
            String str = ii2.a;
            color = context.getColor(org.chromium.components.browser_ui.styles.R$color.dialog_color_text_message_info_hint_url);
        }
        textView2.setTextColor(color);
    }
}
