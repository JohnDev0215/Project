package defpackage;

import android.R;
import android.view.View;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class P0 implements View.OnClickListener {
    public final K s;
    public final /* synthetic */ Q0 t;

    public P0(Q0 q0) {
        this.t = q0;
        this.s = new K(this.t.a.getContext(), 0, R.id.home, 0, 0, this.t.g);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Objects.requireNonNull(this.t);
    }
}
