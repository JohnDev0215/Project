package defpackage;

import android.view.View;
import android.view.ViewGroup;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class G5 extends H5 {
    public G5(W5 w5) {
        super(w5, null);
    }

    @Override // defpackage.H5
    public int b(View view) {
        X5 x5 = (X5) view.getLayoutParams();
        Objects.requireNonNull(this.a);
        return view.getBottom() + ((X5) view.getLayoutParams()).a.bottom + ((ViewGroup.MarginLayoutParams) x5).bottomMargin;
    }

    @Override // defpackage.H5
    public int c(View view) {
        X5 x5 = (X5) view.getLayoutParams();
        Objects.requireNonNull(this.a);
        return (view.getTop() - ((X5) view.getLayoutParams()).a.top) - ((ViewGroup.MarginLayoutParams) x5).topMargin;
    }

    @Override // defpackage.H5
    public int d() {
        W5 w5 = this.a;
        return w5.o - w5.t();
    }

    @Override // defpackage.H5
    public int e() {
        return this.a.w();
    }

    @Override // defpackage.H5
    public int f() {
        W5 w5 = this.a;
        return (w5.o - w5.w()) - this.a.t();
    }
}
