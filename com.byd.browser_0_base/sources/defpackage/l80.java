package defpackage;

import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.components.paintpreview.player.b;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.ui.widget.Toast;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: DemoPaintPreview.java */
/* JADX INFO: loaded from: classes2.dex */
public final class l80 implements b.InterfaceC0137b {
    public Tab a;
    public a63 b;
    public final a c;

    /* JADX INFO: compiled from: DemoPaintPreview.java */
    public class a extends nh0 {
        public a() {
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void C0(TabImpl tabImpl, NavigationHandle navigationHandle) {
            l80 l80Var = l80.this;
            if (l80Var.b.m) {
                l80Var.j();
            }
        }
    }

    public l80(Tab tab) {
        this.a = tab;
        this.b = a63.a(tab);
        a aVar = new a();
        this.c = aVar;
        this.a.x(aVar);
    }

    @Override // org.chromium.components.paintpreview.player.b.InterfaceC0137b
    public final boolean a() {
        return qo.f().c();
    }

    @Override // org.chromium.components.paintpreview.player.b.InterfaceC0137b
    public final void b() {
        if (a()) {
            Toast.c(this.a.getContext(), R$string.paint_preview_demo_no_accessibility, 1).e();
        }
    }

    @Override // org.chromium.components.paintpreview.player.b.InterfaceC0137b
    public final void d() {
        j();
    }

    @Override // org.chromium.components.paintpreview.player.b.InterfaceC0137b
    public final void f(int i) {
        Toast.c(this.a.getContext(), R$string.paint_preview_demo_playback_failure, 1).e();
        j();
    }

    @Override // org.chromium.components.paintpreview.player.b.InterfaceC0137b
    public final void g(GURL gurl) {
        if (this.a == null || !gurl.b || gurl.k()) {
            return;
        }
        this.a.b(new LoadUrlParams(gurl.j(), 0));
        j();
    }

    @Override // org.chromium.components.paintpreview.player.b.InterfaceC0137b
    public final void i() {
        Toast.c(this.a.getContext(), R$string.paint_preview_demo_playback_start, 1).e();
    }

    public final void j() {
        if (this.a == null) {
            return;
        }
        this.b.d(false);
        this.a.t(this.c);
        this.a = null;
        this.b = null;
    }

    @Override // org.chromium.components.paintpreview.player.b.InterfaceC0137b
    public final void c() {
    }

    @Override // org.chromium.components.paintpreview.player.b.InterfaceC0137b
    public final void e() {
    }

    @Override // org.chromium.components.paintpreview.player.b.InterfaceC0137b
    public final void h() {
    }
}
