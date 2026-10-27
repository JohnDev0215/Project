package defpackage;

import org.chromium.content_public.browser.LoadUrlParams;

/* JADX INFO: compiled from: FeedActionDelegateImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class xk0 implements wk0 {
    public final nk1 a;
    public final int b;

    public xk0(nk1 nk1Var, int i) {
        this.a = nk1Var;
        this.b = i;
    }

    @Override // defpackage.wk0
    public final void b(LoadUrlParams loadUrlParams) {
        this.a.b(1, loadUrlParams);
    }

    @Override // defpackage.wk0
    public void c() {
        this.a.b(1, new LoadUrlParams("https://support.google.com/chrome/?p=new_tab", 2));
        n3.Z0(this.b, 3);
    }
}
