package defpackage;

import android.os.Handler;
import android.view.View;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RB implements Runnable {
    public final /* synthetic */ SB s;

    public RB(SB sb) {
        this.s = sb;
    }

    @Override // java.lang.Runnable
    public void run() {
        SB sb = this.s;
        WB wb = sb.t;
        View view = sb.s;
        UB ub = wb.d;
        Objects.requireNonNull(wb);
        Handler handler = view.getHandler();
        if (handler == null) {
            return;
        }
        handler.post(new TB(wb, view, ub, 1));
    }
}
