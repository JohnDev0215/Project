package org.chromium.chrome.browser.ui.signin;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.chromium.chrome.R$id;
import org.chromium.ui.widget.ButtonCompat;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes2.dex */
public class SyncConsentView extends LinearLayout {
    public SigninScrollView e;
    public ImageView f;
    public TextView g;
    public TextView h;
    public Button i;
    public Button j;
    public ButtonCompat k;
    public TextView l;

    public SyncConsentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (SigninScrollView) findViewById(R$id.sync_consent_scroll_view);
        this.f = (ImageView) findViewById(R$id.account_image);
        this.g = (TextView) findViewById(R$id.sync_consent_title);
        this.h = (TextView) findViewById(R$id.sync_consent_subtitle);
        this.i = (Button) findViewById(R$id.negative_button);
        this.j = (Button) findViewById(R$id.more_button);
        this.k = (ButtonCompat) findViewById(R$id.positive_button);
        this.l = (TextView) findViewById(R$id.sync_consent_details_description);
    }
}
