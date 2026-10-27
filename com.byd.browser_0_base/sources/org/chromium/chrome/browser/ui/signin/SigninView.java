package org.chromium.chrome.browser.ui.signin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.t5;
import org.chromium.chrome.R$id;
import org.chromium.ui.widget.ButtonCompat;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes2.dex */
public class SigninView extends LinearLayout {
    public SigninScrollView e;
    public TextView f;
    public View g;
    public ImageView h;
    public TextView i;
    public TextView j;
    public ImageView k;
    public TextView l;
    public TextView m;
    public TextView n;
    public ButtonCompat o;
    public Button p;
    public Button q;
    public t5 r;

    public SigninView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (SigninScrollView) findViewById(R$id.signin_scroll_view);
        this.f = (TextView) findViewById(R$id.signin_title);
        this.g = findViewById(R$id.signin_account_picker);
        this.h = (ImageView) findViewById(R$id.account_image);
        this.i = (TextView) findViewById(R$id.account_text_primary);
        this.j = (TextView) findViewById(R$id.account_text_secondary);
        this.k = (ImageView) findViewById(R$id.account_picker_end_image);
        this.l = (TextView) findViewById(R$id.signin_sync_title);
        this.m = (TextView) findViewById(R$id.signin_sync_description);
        this.n = (TextView) findViewById(R$id.signin_details_description);
        this.o = (ButtonCompat) findViewById(R$id.positive_button);
        this.p = (Button) findViewById(R$id.negative_button);
        this.q = (Button) findViewById(R$id.more_button);
        findViewById(R$id.positive_button_end_padding);
        this.r = new t5(((ImageView) findViewById(R$id.signin_header_image)).getDrawable());
    }
}
