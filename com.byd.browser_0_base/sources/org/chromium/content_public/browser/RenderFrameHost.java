package org.chromium.content_public.browser;

import defpackage.y11;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.url.GURL;
import org.chromium.url.Origin;

/* JADX INFO: loaded from: classes3.dex */
public interface RenderFrameHost {

    public static final class WebAuthSecurityChecksResults {
    }

    boolean a();

    Origin b();

    List<RenderFrameHost> c();

    void d();

    <I extends y11, P extends y11.c> P e(y11.b<I, P> bVar);

    boolean f(int i);

    GURL g();

    void h(Callback<GURL> callback);

    boolean i();

    boolean isIncognito();

    void j();

    boolean k();
}
