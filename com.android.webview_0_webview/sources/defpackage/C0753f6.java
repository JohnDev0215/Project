package defpackage;

import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: renamed from: f6, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0753f6 extends AbstractC0750f3 {
    public final C0809g6 c;

    public C0753f6(C0809g6 c0809g6) {
        this.c = c0809g6;
    }

    @Override // defpackage.AbstractC0750f3
    public void b(View view, C1525t3 c1525t3) {
        super.b(view, c1525t3);
        if (this.c.d() || this.c.c.B == null) {
            return;
        }
        RecyclerView.p(view);
    }

    @Override // defpackage.AbstractC0750f3
    public boolean c(View view, int i, Bundle bundle) {
        W5 w5;
        if (super.c(view, i, bundle)) {
            return true;
        }
        if (this.c.d() || (w5 = this.c.c.B) == null) {
            return false;
        }
        C0474a6 c0474a6 = w5.b.s;
        return w5.W();
    }
}
