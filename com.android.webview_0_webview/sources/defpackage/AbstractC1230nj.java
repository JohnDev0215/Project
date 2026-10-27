package defpackage;

import android.text.TextUtils;

/* JADX INFO: renamed from: nj, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1230nj {
    public static final C0002Ac a = new C0002Ac();
    public static final C0002Ac b = new C0002Ac();
    public static final AbstractC1552tc c = new C1340pj();
    public static final AbstractC1552tc d = new C1285oj();
    public static final C0020Bc e;

    static {
        if (TextUtils.isEmpty("profile")) {
            throw new IllegalArgumentException("scopeUri must not be null or empty");
        }
        if (TextUtils.isEmpty("email")) {
            throw new IllegalArgumentException("scopeUri must not be null or empty");
        }
        e = new C0020Bc("SignIn.API", c, a);
        AbstractC1552tc abstractC1552tc = d;
        C0002Ac c0002Ac = b;
        Cif.g(abstractC1552tc, "Cannot construct an Api with a null ClientBuilder");
        Cif.g(c0002Ac, "Cannot construct an Api with a null ClientKey");
    }
}
