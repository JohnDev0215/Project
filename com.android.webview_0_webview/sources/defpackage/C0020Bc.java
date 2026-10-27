package defpackage;

/* JADX INFO: renamed from: Bc, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0020Bc {
    public final AbstractC1552tc a;
    public final C0002Ac b;
    public final String c;

    public C0020Bc(String str, AbstractC1552tc abstractC1552tc, C0002Ac c0002Ac) {
        Cif.g(abstractC1552tc, "Cannot construct an Api with a null ClientBuilder");
        Cif.g(c0002Ac, "Cannot construct an Api with a null ClientKey");
        this.c = str;
        this.a = abstractC1552tc;
        this.b = c0002Ac;
    }

    public final AbstractC1662vc a() {
        C0002Ac c0002Ac = this.b;
        if (c0002Ac != null) {
            return c0002Ac;
        }
        throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
    }
}
