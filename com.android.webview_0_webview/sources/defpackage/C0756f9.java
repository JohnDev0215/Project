package defpackage;

import android.webkit.ValueCallback;
import java.util.HashMap;
import org.chromium.base.Callback;

/* JADX INFO: renamed from: f9, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0756f9 implements Callback {
    public final /* synthetic */ ValueCallback a;

    public C0756f9(C1091l9 c1091l9, ValueCallback valueCallback) {
        this.a = valueCallback;
    }

    @Override // org.chromium.base.Callback
    public void a(Object obj) {
        C0409Wo c0409Wo = (C0409Wo) obj;
        HashMap map = new HashMap();
        for (int i = 0; i < c0409Wo.a.length; i++) {
            map.put(c0409Wo.a[i], new C0700e9(this, c0409Wo.a[i], c0409Wo.c[i], c0409Wo.b[i]));
        }
        this.a.onReceiveValue(map);
    }
}
