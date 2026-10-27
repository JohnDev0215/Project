package defpackage;

import J.N;
import android.content.Context;
import android.os.PersistableBundle;
import defpackage.fc;
import org.chromium.chrome.browser.download.DownloadManagerService;
import org.chromium.chrome.browser.profiles.ProfileKey;
import org.chromium.components.download.internal.BatteryStatusListenerAndroid;

/* JADX INFO: compiled from: DownloadBackgroundTask.java */
/* JADX INFO: loaded from: classes.dex */
public final class tb0 extends fk1 {
    public int f;

    @Override // defpackage.fk1
    public final int c(Context context, a73 a73Var, du duVar) {
        PersistableBundle persistableBundle = a73Var.b;
        boolean z = persistableBundle.getBoolean("extra_battery_requires_charging");
        int i = persistableBundle.getInt("extra_optimal_battery_percentage");
        this.f = persistableBundle.getInt("extra_task_type");
        return (z || BatteryStatusListenerAndroid.getBatteryPercentage() >= i) ? 0 : 1;
    }

    @Override // defpackage.fk1
    public final void d(Context context, a73 a73Var, fc.a aVar) {
        DownloadManagerService.g().k();
        N.MBBY92FO(this, ProfileKey.a(), this.f, new wb(23, aVar));
    }

    @Override // defpackage.fk1
    public final boolean e() {
        return true;
    }

    @Override // defpackage.fk1
    public final boolean f(a73 a73Var) {
        return N.MtXApQ3N(this, ProfileKey.a(), a73Var.b.getInt("extra_task_type"));
    }
}
