package defpackage;

import android.content.Context;

/* JADX INFO: renamed from: gc, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0834gc extends AbstractC0092Fc {
    public static final C0002Ac i = new C0002Ac();
    public static final AbstractC1552tc j;
    public static final C0020Bc k;

    static {
        C0890hc c0890hc = new C0890hc();
        j = c0890hc;
        k = new C0020Bc("SmsRetriever.API", c0890hc, i);
    }

    public AbstractC0834gc(Context context) {
        super(context, k, null, C0074Ec.c);
    }
}
