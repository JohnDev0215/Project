package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import defpackage.s62;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.crash.ChromePureJavaExceptionReporter;
import org.chromium.chrome.browser.download.settings.DownloadSettings;
import org.chromium.chrome.browser.enterprise.util.EnterpriseInfo;
import org.chromium.chrome.browser.settings.SettingsActivity;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class m62 implements Callback {
    public final /* synthetic */ int e;

    public /* synthetic */ m62(int i) {
        this.e = i;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        switch (this.e) {
            case 0:
                throw new s62.b((Exception) obj);
            case 1:
                Context context = (Context) obj;
                String name = DownloadSettings.class.getName();
                Intent intent = new Intent();
                intent.setClass(context, SettingsActivity.class);
                if (!(context instanceof Activity)) {
                    intent.addFlags(268435456);
                    intent.addFlags(67108864);
                }
                intent.putExtra("show_fragment", name);
                ComponentName componentName = q11.a;
                try {
                    context.startActivity(intent, null);
                    return;
                } catch (ActivityNotFoundException unused) {
                    return;
                }
            case 2:
                return;
            case 3:
                EnterpriseInfo.a aVar = (EnterpriseInfo.a) obj;
                if (aVar == null) {
                    return;
                }
                oj2.O("EnterpriseCheck.IsManaged2", aVar.b);
                oj2.O("EnterpriseCheck.IsFullyManaged2", aVar.a);
                return;
            case 4:
                return;
            case 5:
                ((w80) obj).destroy();
                return;
            case 6:
                ChromePureJavaExceptionReporter.d((Throwable) obj);
                return;
            case 7:
                return;
            case 8:
                Log.e("cr_UsageStatsService", "Failed to clear domain events for history deletion");
                return;
            default:
                oj2.U(((List) obj).size(), 50, "Signin.AndroidNumberOfDeviceAccounts");
                return;
        }
    }
}
