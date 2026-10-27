package defpackage;

import android.app.Activity;
import java.lang.ref.WeakReference;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.app.ChromeActivity;

/* JADX INFO: compiled from: DownloadMessageUiDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class dd0 implements yc0 {
    public WeakReference<ChromeActivity> a = new WeakReference<>(null);

    public dd0() {
        a();
    }

    public final boolean a() {
        Activity activity = ApplicationStatus.c;
        if (!((activity instanceof ChromeActivity) && activity != this.a.get())) {
            return false;
        }
        this.a = new WeakReference<>((ChromeActivity) activity);
        return true;
    }
}
