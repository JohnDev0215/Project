package org.chromium.chrome.browser.tab;

import android.content.Context;
import android.view.View;
import defpackage.i53;
import defpackage.jk1;
import defpackage.jz2;
import defpackage.mk3;
import defpackage.n23;
import org.chromium.components.embedder_support.view.ContentView;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public interface Tab {
    void A(boolean z);

    mk3 B();

    boolean C(int i);

    WindowAndroid D();

    void E(WindowAndroid windowAndroid, jz2 jz2Var);

    boolean F();

    void G(boolean z);

    void a();

    int b(LoadUrlParams loadUrlParams);

    WebContents c();

    ContentView d();

    void destroy();

    void e();

    boolean f();

    boolean g();

    Context getContext();

    int getId();

    int getLaunchType();

    String getTitle();

    GURL getUrl();

    View getView();

    void h();

    void hide(int i);

    boolean i();

    boolean isCustomTab();

    boolean isDestroyed();

    boolean isFrozen();

    boolean isHidden();

    boolean isIncognito();

    boolean isInitialized();

    boolean isNativePage();

    boolean isUserInteractable();

    int j();

    boolean k();

    void l(int i, int i2);

    boolean m();

    boolean n();

    i53 o();

    LoadUrlParams p();

    float q();

    boolean r();

    boolean s();

    void t(n23 n23Var);

    jk1 u();

    GURL v();

    void w();

    void x(n23 n23Var);

    void y();

    void z();
}
