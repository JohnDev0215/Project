package defpackage;

import org.chromium.base.ApplicationStatus;
import org.chromium.base.ThreadUtils;
import org.chromium.components.crash.CrashKeys;

/* JADX INFO: compiled from: ApplicationStatusTracker.java */
/* JADX INFO: loaded from: classes.dex */
public final class w7 implements ApplicationStatus.d {
    public String e;

    @Override // org.chromium.base.ApplicationStatus.d
    public final void v(int i) {
        Object obj = ThreadUtils.a;
        boolean z = true;
        if (i != 1 && i != 2) {
            z = false;
        }
        String str = z ? "app_foreground" : "app_background";
        if (str.equals(this.e)) {
            return;
        }
        this.e = str;
        CrashKeys.getInstance().set(2, str);
    }
}
