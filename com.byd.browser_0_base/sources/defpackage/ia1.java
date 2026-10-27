package defpackage;

import android.util.Log;
import java.io.File;
import org.chromium.chrome.browser.crash.MinidumpUploadServiceImpl;

/* JADX INFO: compiled from: LogcatExtractionRunnable.java */
/* JADX INFO: loaded from: classes.dex */
public final class ia1 implements Runnable {
    public final File e;
    public final n3 f;

    public ia1(File file) {
        n3 n3Var = new n3(0);
        this.e = file;
        this.f = n3Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        d10 d10Var = new d10(xz.a.getCacheDir());
        this.f.getClass();
        n3.n(this.e, d10Var);
        try {
            MinidumpUploadServiceImpl.f();
        } catch (SecurityException e) {
            Log.w("cr_LogcatExtraction", e.toString());
            throw e;
        }
    }
}
