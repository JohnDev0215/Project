package defpackage;

import android.view.ViewGroup;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class E6 extends AbstractC0475a7 {
    public boolean a = false;
    public final /* synthetic */ ViewGroup b;

    public E6(G6 g6, ViewGroup viewGroup) {
        this.b = viewGroup;
    }

    @Override // defpackage.AbstractC0475a7, defpackage.Y6
    public void a(Z6 z6) {
        AbstractC1200n7.a(this.b, false);
    }

    @Override // defpackage.AbstractC0475a7, defpackage.Y6
    public void b(Z6 z6) {
        AbstractC1200n7.a(this.b, true);
    }

    @Override // defpackage.Y6
    public void d(Z6 z6) {
        AbstractC1200n7.a(this.b, false);
        z6.v(this);
    }
}
