package defpackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/* JADX INFO: renamed from: ls, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1129ls implements Runnable {
    public C1574ty s;
    public Runnable t;
    public final /* synthetic */ C1239ns u;

    public RunnableC1129ls(C1239ns c1239ns, C1574ty c1574ty, Runnable runnable) {
        this.u = c1239ns;
        this.s = c1574ty;
        this.t = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.u.c = this.s;
            File fileD = AbstractC0626cr.d();
            try {
                if (AbstractC0626cr.i(new FileOutputStream(fileD), this.u.c)) {
                    AbstractC0626cr.g();
                    this.u.d = false;
                } else {
                    Ts.a("VariationsSeedHolder", "Failed to write seed file " + fileD + " for update", new Object[0]);
                }
            } catch (FileNotFoundException unused) {
                Ts.a("VariationsSeedHolder", "Failed to open seed file " + fileD + " for update", new Object[0]);
            }
        } finally {
            this.t.run();
        }
    }
}
