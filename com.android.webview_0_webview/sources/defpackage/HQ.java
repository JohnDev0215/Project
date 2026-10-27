package defpackage;

import android.webkit.ValueCallback;
import org.chromium.base.Callback;
import org.chromium.weblayer_private.TabImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class HQ implements Callback {
    public final /* synthetic */ QR a;

    public HQ(TabImpl tabImpl, QR qr) {
        this.a = qr;
    }

    @Override // org.chromium.base.Callback
    public void a(Object obj) {
        String str = (String) obj;
        ValueCallback valueCallback = (ValueCallback) BinderC0998jS.d(this.a, ValueCallback.class);
        if (valueCallback != null) {
            valueCallback.onReceiveValue(str);
        }
    }
}
