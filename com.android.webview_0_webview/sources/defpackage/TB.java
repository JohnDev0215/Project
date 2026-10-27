package defpackage;

import android.os.Handler;
import android.view.View;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class TB implements Runnable {
    public final /* synthetic */ View s;
    public final /* synthetic */ UB t;
    public final /* synthetic */ int u;
    public final /* synthetic */ WB v;

    public TB(WB wb, View view, UB ub, int i) {
        this.v = wb;
        this.s = view;
        this.t = ub;
        this.u = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        WB wb = this.v;
        View view = this.s;
        UB ub = this.t;
        int i = this.u;
        if (((WA) wb.a).c(wb.b)) {
            return;
        }
        if (i > 0) {
            int i2 = i - 1;
            Handler handler = view.getHandler();
            if (handler == null) {
                return;
            }
            handler.post(new TB(wb, view, ub, i2));
            return;
        }
        Objects.requireNonNull(ub);
        UA.a();
        if (ub.a) {
            return;
        }
        Ts.f("Ime", "onRegisterProxyViewFailure", new Object[0]);
    }
}
