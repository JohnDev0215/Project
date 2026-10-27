package defpackage;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import org.chromium.components.payments.R$drawable;
import org.chromium.components.payments.R$id;
import org.chromium.components.payments.R$layout;
import org.chromium.ui.widget.TextViewWithClickableSpans;

/* JADX INFO: compiled from: SecurePaymentConfirmationAuthnView.java */
/* JADX INFO: loaded from: classes3.dex */
public final class jh2 {
    public final RelativeLayout a;
    public final ScrollView b;
    public final Context c;
    public final TextView d;
    public final TextView e;
    public final ImageView f;
    public final TextView g;
    public final TextView h;
    public final Button i;
    public final Button j;
    public final TextViewWithClickableSpans k;

    /* JADX INFO: compiled from: SecurePaymentConfirmationAuthnView.java */
    public static class a {
        public final boolean a;
        public final String b;
        public final Runnable c;

        public a(i22 i22Var, String str, boolean z) {
            this.a = z;
            this.b = str;
            this.c = i22Var;
        }
    }

    public jh2(Context context) {
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R$layout.secure_payment_confirmation_authn_ui, (ViewGroup) null);
        this.a = relativeLayout;
        this.b = (ScrollView) relativeLayout.findViewById(R$id.scroll_view);
        this.c = context;
        ImageView imageView = (ImageView) relativeLayout.findViewById(R$id.secure_payment_confirmation_image);
        this.d = (TextView) relativeLayout.findViewById(R$id.store);
        this.e = (TextView) relativeLayout.findViewById(R$id.payment);
        this.f = (ImageView) relativeLayout.findViewById(R$id.payment_icon);
        this.g = (TextView) relativeLayout.findViewById(R$id.total);
        this.h = (TextView) relativeLayout.findViewById(R$id.currency);
        this.i = (Button) relativeLayout.findViewById(R$id.continue_button);
        this.j = (Button) relativeLayout.findViewById(R$id.cancel_button);
        TextViewWithClickableSpans textViewWithClickableSpans = (TextViewWithClickableSpans) relativeLayout.findViewById(R$id.secure_payment_confirmation_nocredmatch_opt_out);
        this.k = textViewWithClickableSpans;
        imageView.setImageResource(R$drawable.save_card);
        textViewWithClickableSpans.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
