package org.chromium.components.page_info;

import J.N;
import android.view.View;
import android.widget.FrameLayout;
import defpackage.tu1;
import defpackage.vq;
import defpackage.xu1;
import org.chromium.components.page_info.ConnectionInfoView;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: PageInfoConnectionController.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b implements xu1, ConnectionInfoView.a {
    public final tu1 e;
    public final WebContents f;
    public final PageInfoRowView g;
    public final c h;
    public final String i;
    public final boolean j;
    public String k;
    public ConnectionInfoView l;
    public FrameLayout m;

    public b(tu1 tu1Var, PageInfoRowView pageInfoRowView, WebContents webContents, vq vqVar, String str, boolean z) {
        this.e = tu1Var;
        this.g = pageInfoRowView;
        this.f = webContents;
        this.h = vqVar;
        this.i = str;
        this.j = z;
    }

    @Override // defpackage.xu1
    public final String b() {
        return this.k;
    }

    @Override // defpackage.xu1
    public final View c(PageInfoContainer pageInfoContainer) {
        PageInfoRowView pageInfoRowView = this.g;
        this.m = new FrameLayout(pageInfoRowView.getContext());
        this.l = new ConnectionInfoView(pageInfoRowView.getContext(), this.f, this);
        return this.m;
    }

    @Override // defpackage.xu1
    public final void f() {
        this.m = null;
        ConnectionInfoView connectionInfoView = this.l;
        N.MISU_God(connectionInfoView.k, connectionInfoView);
    }

    @Override // defpackage.xu1
    public final void a() {
    }

    @Override // defpackage.xu1
    public final void d() {
    }
}
