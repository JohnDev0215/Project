package defpackage;

import android.app.Activity;
import android.os.Process;
import android.util.Log;
import java.util.Locale;
import org.chromium.base.ApplicationStatus;

/* JADX INFO: compiled from: ChromeBrowserInitializer.java */
/* JADX INFO: loaded from: classes.dex */
public final class up implements ApplicationStatus.c {
    public final /* synthetic */ sp e;

    public up(sp spVar) {
        this.e = spVar;
    }

    @Override // org.chromium.base.ApplicationStatus.c
    public final void d(Activity activity, int i) {
        if ((i != 1 && i != 6) || xs0.d.c || this.e.a.equals(Locale.getDefault())) {
            return;
        }
        Log.e("cr_BrowserInitializer", "Killing process because of locale change.");
        Process.killProcess(Process.myPid());
    }
}
