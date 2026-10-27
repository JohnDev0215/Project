package defpackage;

import J.N;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import org.chromium.android_webview.AwAutofillClient;
import org.chromium.components.autofill.AutofillSuggestion;

/* JADX INFO: renamed from: ow, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ViewOnClickListenerC1298ow extends FrameLayout implements View.OnClickListener {
    public InterfaceC1326pN s;
    public InterfaceC1353pw t;

    public ViewOnClickListenerC1298ow(C1408qw c1408qw, Context context, InterfaceC1326pN interfaceC1326pN, InterfaceC1353pw interfaceC1353pw) {
        super(context);
        this.s = interfaceC1326pN;
        this.t = interfaceC1353pw;
        FrameLayout.inflate(context, AbstractC0425Xm.F, this);
        ((TextView) findViewById(AbstractC0371Um.Y0)).setText(interfaceC1326pN.a());
        ImageView imageView = (ImageView) findViewById(AbstractC0371Um.X0);
        if (interfaceC1326pN.h() == 0) {
            imageView.setVisibility(8);
        } else {
            imageView.setImageDrawable(AbstractC1631v.b(context, interfaceC1326pN.h()));
        }
        setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        InterfaceC1353pw interfaceC1353pw = this.t;
        InterfaceC1326pN interfaceC1326pN = this.s;
        C1737ww c1737ww = (C1737ww) interfaceC1353pw;
        int i = 0;
        while (true) {
            if (i >= c1737ww.v.size()) {
                i = -1;
                break;
            } else if (((AutofillSuggestion) c1737ww.v.get(i)).e == ((AutofillSuggestion) interfaceC1326pN).e) {
                break;
            } else {
                i++;
            }
        }
        AwAutofillClient awAutofillClient = ((C0845gn) c1737ww.u).a;
        N.Mg2XGOMM(awAutofillClient.a, awAutofillClient, i);
    }
}
