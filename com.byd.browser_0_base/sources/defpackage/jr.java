package defpackage;

import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ChromeShareExtras.java */
/* JADX INFO: loaded from: classes2.dex */
public final class jr {
    public final boolean a;
    public final boolean b;
    public final boolean c;
    public final GURL d;
    public final GURL e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final RenderFrameHost i;
    public final int j;

    public jr(boolean z, boolean z2, boolean z3, GURL gurl, GURL gurl2, boolean z4, boolean z5, RenderFrameHost renderFrameHost, int i) {
        this.a = z;
        this.b = z2;
        this.c = z3;
        this.d = gurl == null ? GURL.emptyGURL() : gurl;
        this.e = gurl2 == null ? GURL.emptyGURL() : gurl2;
        this.f = false;
        this.g = z4;
        this.h = z5;
        this.i = renderFrameHost;
        this.j = i;
    }
}
