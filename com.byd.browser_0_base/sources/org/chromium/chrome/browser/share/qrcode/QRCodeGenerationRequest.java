package org.chromium.chrome.browser.share.qrcode;

import J.N;
import android.graphics.Bitmap;
import defpackage.h82;
import defpackage.i82;
import defpackage.m82;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$string;

/* JADX INFO: loaded from: classes2.dex */
public class QRCodeGenerationRequest {
    public long a;
    public a b;

    public interface a {
    }

    public QRCodeGenerationRequest(String str, h82 h82Var) {
        this.b = h82Var;
        this.a = N.Ms6T0$zG(this, str);
    }

    @CalledByNative
    public final void onQRCodeAvailable(Bitmap bitmap) {
        a aVar = this.b;
        if (aVar != null) {
            h82 h82Var = (h82) aVar;
            i82 i82Var = h82Var.b;
            if (bitmap != null) {
                i82Var.b.p(m82.a, bitmap);
            } else {
                String str = h82Var.a;
                i82Var.b.p(m82.b, (str == null || str.length() <= 122) ? i82Var.a.getResources().getString(R$string.qr_code_error_unknown) : i82Var.a.getResources().getString(R$string.qr_code_error_too_long, 122));
            }
            this.b = null;
        }
        long j = this.a;
        if (j != 0) {
            N.MQBcgs6S(j);
            this.a = 0L;
        }
    }
}
