package defpackage;

import android.view.View;
import android.view.ViewGroup;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class T5 implements InterfaceC1418r6 {
    public final /* synthetic */ W5 a;

    public T5(W5 w5) {
        this.a = w5;
    }

    @Override // defpackage.InterfaceC1418r6
    public View a(int i) {
        return this.a.o(i);
    }

    @Override // defpackage.InterfaceC1418r6
    public int b() {
        W5 w5 = this.a;
        return w5.n - w5.v();
    }

    @Override // defpackage.InterfaceC1418r6
    public int c() {
        return this.a.u();
    }

    @Override // defpackage.InterfaceC1418r6
    public int d(View view) {
        X5 x5 = (X5) view.getLayoutParams();
        Objects.requireNonNull(this.a);
        return view.getRight() + ((X5) view.getLayoutParams()).a.right + ((ViewGroup.MarginLayoutParams) x5).rightMargin;
    }

    @Override // defpackage.InterfaceC1418r6
    public int e(View view) {
        X5 x5 = (X5) view.getLayoutParams();
        Objects.requireNonNull(this.a);
        return (view.getLeft() - ((X5) view.getLayoutParams()).a.left) - ((ViewGroup.MarginLayoutParams) x5).leftMargin;
    }
}
