package defpackage;

import android.view.View;
import android.view.ViewGroup;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class U5 implements InterfaceC1418r6 {
    public final /* synthetic */ W5 a;

    public U5(W5 w5) {
        this.a = w5;
    }

    @Override // defpackage.InterfaceC1418r6
    public View a(int i) {
        return this.a.o(i);
    }

    @Override // defpackage.InterfaceC1418r6
    public int b() {
        W5 w5 = this.a;
        return w5.o - w5.t();
    }

    @Override // defpackage.InterfaceC1418r6
    public int c() {
        return this.a.w();
    }

    @Override // defpackage.InterfaceC1418r6
    public int d(View view) {
        X5 x5 = (X5) view.getLayoutParams();
        Objects.requireNonNull(this.a);
        return view.getBottom() + ((X5) view.getLayoutParams()).a.bottom + ((ViewGroup.MarginLayoutParams) x5).bottomMargin;
    }

    @Override // defpackage.InterfaceC1418r6
    public int e(View view) {
        X5 x5 = (X5) view.getLayoutParams();
        Objects.requireNonNull(this.a);
        return (view.getTop() - ((X5) view.getLayoutParams()).a.top) - ((ViewGroup.MarginLayoutParams) x5).topMargin;
    }
}
