package defpackage;

import android.net.Uri;
import android.webkit.ValueCallback;
import org.chromium.base.Callback;

/* JADX INFO: renamed from: Hb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0127Hb implements ValueCallback {
    public boolean a;
    public final /* synthetic */ Callback b;

    public C0127Hb(C0252Ob c0252Ob, Callback callback) {
        this.b = callback;
    }

    @Override // android.webkit.ValueCallback
    public void onReceiveValue(Object obj) {
        Uri[] uriArr = (Uri[]) obj;
        if (this.a) {
            throw new IllegalStateException("showFileChooser result was already called");
        }
        this.a = true;
        String[] strArr = null;
        if (uriArr != null) {
            strArr = new String[uriArr.length];
            for (int i = 0; i < uriArr.length; i++) {
                strArr[i] = uriArr[i].toString();
            }
        }
        this.b.a(strArr);
    }
}
