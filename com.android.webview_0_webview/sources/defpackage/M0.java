package defpackage;

import androidx.appcompat.widget.Toolbar;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class M0 implements Runnable {
    public final /* synthetic */ Toolbar s;

    public M0(Toolbar toolbar) {
        this.s = toolbar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Objects.requireNonNull(this.s);
    }
}
