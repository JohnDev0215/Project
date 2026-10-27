package org.chromium.chrome.browser.ui.autofill;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import defpackage.dr;
import defpackage.iq2;
import defpackage.un1;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ui.autofill.e;

/* JADX INFO: loaded from: classes2.dex */
public class OtpVerificationDialogView extends RelativeLayout {
    public static final /* synthetic */ int j = 0;
    public View e;
    public View f;
    public EditText g;
    public TextView h;
    public TextView i;

    public OtpVerificationDialogView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        View viewFindViewById = findViewById(R$id.progress_bar_overlay);
        this.e = viewFindViewById;
        viewFindViewById.setVisibility(8);
        this.f = findViewById(R$id.otp_verification_dialog_contents);
        this.g = (EditText) findViewById(R$id.otp_input);
        TextView textView = (TextView) findViewById(R$id.otp_error_message);
        this.h = textView;
        textView.setVisibility(8);
        TextView textView2 = (TextView) findViewById(R$id.otp_resend_message);
        this.i = textView2;
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void setEditTextHint(String str) {
        this.g.setHint(str);
    }

    public void setViewDelegate(e.a aVar) {
        this.g.addTextChangedListener(new f(aVar));
        TextView textView = this.i;
        Context context = getContext();
        textView.setText(iq2.a(context.getResources().getString(R$string.autofill_payments_otp_verification_dialog_cant_find_code_message), new iq2.a(new un1(context, new dr(25, aVar)), "<link>", "</link>")));
    }
}
