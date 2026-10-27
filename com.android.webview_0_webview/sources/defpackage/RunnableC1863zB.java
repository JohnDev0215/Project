package defpackage;

import J.N;
import android.R;
import java.util.Objects;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;

/* JADX INFO: renamed from: zB, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1863zB implements Runnable {
    public final /* synthetic */ int s;
    public final /* synthetic */ QB t;

    public RunnableC1863zB(QB qb, int i) {
        this.t = qb;
        this.s = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        ImeAdapterImpl imeAdapterImpl = this.t.e;
        int i = this.s;
        Objects.requireNonNull(imeAdapterImpl);
        switch (i) {
            case R.id.selectAll:
                WebContentsImpl webContentsImpl = imeAdapterImpl.x;
                webContentsImpl.O();
                N.MNvj1u1S(webContentsImpl.t, webContentsImpl);
                break;
            case R.id.cut:
                imeAdapterImpl.x.Q();
                break;
            case R.id.copy:
                imeAdapterImpl.x.P();
                break;
            case R.id.paste:
                imeAdapterImpl.x.V();
                break;
        }
    }
}
