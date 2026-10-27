package org.chromium.chrome.browser.ui.signin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.ui.widget.ButtonCompat;

/* JADX INFO: loaded from: classes2.dex */
public class PersonalizedSigninPromoView extends FrameLayout {
    public ImageView e;
    public ImageButton f;
    public TextView g;
    public TextView h;
    public ButtonCompat i;
    public Button j;

    public PersonalizedSigninPromoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R$layout.sync_promo_view, this);
    }

    public TextView getDescription() {
        return this.h;
    }

    public ImageButton getDismissButton() {
        return this.f;
    }

    public ImageView getImage() {
        return this.e;
    }

    public ButtonCompat getPrimaryButton() {
        return this.i;
    }

    public Button getSecondaryButton() {
        return this.j;
    }

    public TextView getTitle() {
        return this.g;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (ImageView) findViewById(R$id.sync_promo_image);
        this.f = (ImageButton) findViewById(R$id.sync_promo_close_button);
        this.i = (ButtonCompat) findViewById(R$id.sync_promo_signin_button);
        this.j = (Button) findViewById(R$id.sync_promo_choose_account_button);
        this.g = (TextView) findViewById(R$id.sync_promo_title);
        this.h = (TextView) findViewById(R$id.sync_promo_description);
    }
}
