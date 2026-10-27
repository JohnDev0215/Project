package org.chromium.chrome.browser.keyboard_accessory;

import defpackage.l41;
import defpackage.m41;
import defpackage.ub1;
import defpackage.vb;
import defpackage.x62;
import defpackage.zp1;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.AsyncViewStub;

/* JADX INFO: compiled from: ManualFillingComponent.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends vb {

    /* JADX INFO: renamed from: org.chromium.chrome.browser.keyboard_accessory.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ManualFillingComponent.java */
    public interface InterfaceC0088a {
        void onDestroy();
    }

    /* JADX INFO: compiled from: ManualFillingComponent.java */
    public interface b {
    }

    boolean J(ub1 ub1Var);

    void O0(AsyncViewStub asyncViewStub, AsyncViewStub asyncViewStub2);

    boolean Q0(ub1 ub1Var);

    boolean R();

    void W0(WebContents webContents, b bVar);

    boolean a();

    void a0(WebContents webContents, int i, x62<l41> x62Var);

    void b();

    void b0();

    void c(boolean z);

    void d0();

    void destroy();

    void dismiss();

    void m0();

    void m1();

    void onPause();

    void onResume();

    int r0();

    void s1(WebContents webContents, x62<m41[]> x62Var);

    zp1<Integer> t0();

    void y();

    void z();
}
