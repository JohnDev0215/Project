package defpackage;

import android.webkit.ValueCallback;
import org.chromium.base.Callback;

/* JADX INFO: renamed from: b8, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0532b8 {
    public static Callback a(final ValueCallback valueCallback) {
        if (valueCallback == null) {
            return null;
        }
        return new Callback(valueCallback) { // from class: a8
            public final ValueCallback a;

            {
                this.a = valueCallback;
            }

            @Override // org.chromium.base.Callback
            public void a(Object obj) {
                this.a.onReceiveValue(obj);
            }
        };
    }
}
