package defpackage;

import android.net.ProxyInfo;
import android.net.Uri;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class XK {
    public static final XK e = new XK("", 0, "", new String[0]);
    public final String a;
    public final int b;
    public final String c;
    public final String[] d;

    public XK(String str, int i, String str2, String[] strArr) {
        this.a = str;
        this.b = i;
        this.c = str2;
        this.d = strArr;
    }

    public static XK a(ProxyInfo proxyInfo) {
        if (proxyInfo == null) {
            return null;
        }
        Uri pacFileUrl = proxyInfo.getPacFileUrl();
        return new XK(proxyInfo.getHost(), proxyInfo.getPort(), Uri.EMPTY.equals(pacFileUrl) ? null : pacFileUrl.toString(), proxyInfo.getExclusionList());
    }
}
