package defpackage;

import android.content.Context;
import android.os.Looper;

/* JADX INFO: renamed from: tc, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1552tc extends AbstractC1827yc {
    @Deprecated
    public InterfaceC1882zc a(Context context, Looper looper, C0381Ve c0381Ve, Object obj, InterfaceC0110Gc interfaceC0110Gc, InterfaceC0128Hc interfaceC0128Hc) {
        return b(context, looper, c0381Ve, obj, interfaceC0110Gc, interfaceC0128Hc);
    }

    public InterfaceC1882zc b(Context context, Looper looper, C0381Ve c0381Ve, Object obj, InterfaceC0397Wc interfaceC0397Wc, InterfaceC0947id interfaceC0947id) {
        throw new UnsupportedOperationException("buildClient must be implemented");
    }
}
