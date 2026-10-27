package defpackage;

import android.view.View;
import android.view.ViewGroup;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class F5 extends H5 {
    public F5(W5 w5) {
        super(w5, null);
    }

    @Override // defpackage.H5
    public int b(View view) {
        X5 x5 = (X5) view.getLayoutParams();
        Objects.requireNonNull(this.a);
        return view.getRight() + ((X5) view.getLayoutParams()).a.right + ((ViewGroup.MarginLayoutParams) x5).rightMargin;
    }

    @Override // defpackage.H5
    public int c(View view) {
        X5 x5 = (X5) view.getLayoutParams();
        Objects.requireNonNull(this.a);
        return (view.getLeft() - ((X5) view.getLayoutParams()).a.left) - ((ViewGroup.MarginLayoutParams) x5).leftMargin;
    }

    @Override // defpackage.H5
    public int d() {
        W5 w5 = this.a;
        return w5.n - w5.v();
    }

    @Override // defpackage.H5
    public int e() {
        return this.a.u();
    }

    @Override // defpackage.H5
    public int f() {
        W5 w5 = this.a;
        return (w5.n - w5.u()) - this.a.v();
    }
}
