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
import defpackage.iq2;
import org.chromium.components.payments.R$drawable;
import org.chromium.components.payments.R$id;
import org.chromium.components.payments.R$layout;
import org.chromium.components.payments.R$string;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.widget.TextViewWithClickableSpans;

/* JADX INFO: compiled from: SecurePaymentConfirmationNoMatchingCredView.java */
/* JADX INFO: loaded from: classes3.dex */
public final class lh2 {
    public final RelativeLayout a;
    public final ScrollView b;

    public lh2(Context context, String str, String str2, boolean z, i22 i22Var, an2 an2Var) {
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R$layout.secure_payment_confirmation_no_credential_match_ui, (ViewGroup) null);
        this.a = relativeLayout;
        this.b = (ScrollView) relativeLayout.findViewById(R$id.scroll_view);
        ImageView imageView = (ImageView) relativeLayout.findViewById(R$id.secure_payment_confirmation_image);
        TextView textView = (TextView) relativeLayout.findViewById(R$id.secure_payment_confirmation_nocredmatch_description);
        Button button = (Button) relativeLayout.findViewById(R$id.ok_button);
        TextViewWithClickableSpans textViewWithClickableSpans = (TextViewWithClickableSpans) relativeLayout.findViewById(R$id.secure_payment_confirmation_nocredmatch_opt_out);
        imageView.setImageResource(R$drawable.save_card);
        textView.setText(String.format(context.getResources().getString(R$string.no_matching_credential_description), str));
        textViewWithClickableSpans.setText(iq2.a(context.getResources().getString(R$string.secure_payment_confirmation_opt_out_label, context.getResources().getString(DeviceFormFactor.a(context) ? R$string.secure_payment_confirmation_this_tablet_label : R$string.secure_payment_confirmation_this_phone_label), str2), new iq2.a(new un1(context, new ur3(1, an2Var)), "BEGIN_LINK", "END_LINK")));
        textViewWithClickableSpans.setMovementMethod(LinkMovementMethod.getInstance());
        textViewWithClickableSpans.setVisibility(z ? 0 : 8);
        button.setOnClickListener(new p10(8, i22Var));
    }
}
