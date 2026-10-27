package defpackage;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.chromium.chrome.browser.ui.fast_checkout.R$id;
import org.chromium.ui.widget.ButtonCompat;

/* JADX INFO: compiled from: HomeScreenViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pv0 {

    /* JADX INFO: compiled from: HomeScreenViewBinder.java */
    public static class a {
        public final Context a;
        public final TextView b;
        public final TextView c;
        public final LinearLayout d;
        public final TextView e;
        public final TextView f;
        public final TextView g;
        public final ImageView h;
        public final LinearLayout i;
        public final LinearLayout j;
        public final ButtonCompat k;

        public a(Context context, View view) {
            this.a = context;
            this.b = (TextView) view.findViewById(R$id.fast_checkout_home_sheet_profile_name);
            this.c = (TextView) view.findViewById(R$id.fast_checkout_home_sheet_profile_street);
            this.d = (LinearLayout) view.findViewById(R$id.fast_checkout_home_sheet_profile_sub_section);
            this.e = (TextView) view.findViewById(R$id.fast_checkout_home_sheet_profile_email);
            this.f = (TextView) view.findViewById(R$id.fast_checkout_home_sheet_profile_phone_number);
            this.g = (TextView) view.findViewById(R$id.fast_checkout_sheet_selected_credit_card_header);
            this.h = (ImageView) view.findViewById(R$id.fast_checkout_credit_card_icon);
            this.i = (LinearLayout) view.findViewById(R$id.selected_address_profile_view);
            this.j = (LinearLayout) view.findViewById(R$id.selected_credit_card_view);
            this.k = (ButtonCompat) view.findViewById(R$id.fast_checkout_button_accept);
        }
    }

    public static void a(TextView textView) {
        textView.setVisibility(textView.length() == 0 ? 8 : 0);
    }
}
