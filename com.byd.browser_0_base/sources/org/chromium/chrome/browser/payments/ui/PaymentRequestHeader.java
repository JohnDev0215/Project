package org.chromium.chrome.browser.payments.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import defpackage.ii2;
import defpackage.yp;
import org.chromium.chrome.R$id;
import org.chromium.components.browser_ui.styles.R$dimen;

/* JADX INFO: loaded from: classes2.dex */
public class PaymentRequestHeader extends FrameLayout {
    public final int e;
    public final Context f;

    public PaymentRequestHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = context;
        String str = ii2.a;
        this.e = yp.c(context, R$dimen.sheet_bg_color_elev);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        setBackgroundColor(this.e);
    }

    public void setTitleBitmap(Bitmap bitmap) {
        ((ImageView) findViewById(R$id.icon_view)).setImageBitmap(bitmap);
    }
}
