package defpackage;

import android.os.Binder;
import java.util.Objects;
import java.util.concurrent.Callable;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Qu implements Callable {
    public final /* synthetic */ Su a;

    public Qu(Su su) {
        this.a = su;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        this.a.e.set(true);
        Object objC = null;
        try {
            objC = this.a.c();
            Binder.flushPendingCommands();
            return objC;
        } catch (Throwable th) {
            try {
                this.a.d.set(true);
                throw th;
            } finally {
                Su su = this.a;
                Objects.requireNonNull(su);
                if (su instanceof Tu) {
                    su.c = 2;
                } else {
                    ThreadUtils.e(new Ou(su, objC));
                }
            }
        }
    }
}
