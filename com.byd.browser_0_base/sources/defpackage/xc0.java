package defpackage;

import J.N;
import android.os.SystemClock;
import defpackage.z23;
import java.util.Locale;
import org.chromium.base.Callback;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.download.DownloadMessageBridge;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class xc0 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ long f;
    public final /* synthetic */ Object g;

    public /* synthetic */ xc0(long j, int i, Object obj) {
        this.e = i;
        this.g = obj;
        this.f = j;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        long j = this.f;
        Object obj2 = this.g;
        switch (i) {
            case 0:
                DownloadMessageBridge downloadMessageBridge = (DownloadMessageBridge) obj2;
                downloadMessageBridge.getClass();
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                long j2 = downloadMessageBridge.a;
                if (j2 != 0) {
                    N.MQ2U3zKT(j2, j, zBooleanValue);
                }
                break;
            default:
                z23.g gVar = (z23.g) obj2;
                ui2 ui2Var = (ui2) obj;
                z23.j jVar = gVar.a;
                TraceEvent.F(jVar.a, "LoadCriticalPersistedTabData");
                Locale locale = Locale.US;
                Object[] objArr = new Object[1];
                objArr[0] = ui2Var == null ? "Null" : "Exists";
                oj2.b0(SystemClock.elapsedRealtime() - j, String.format(locale, "Tabs.SavedTabLoadTime.CriticalPersistedTabData.%s", objArr));
                CriticalPersistedTabData.a aVar = CriticalPersistedTabData.A;
                if (!(ui2Var == null)) {
                    z23.a(z23.this, jVar, null, ui2Var);
                } else {
                    gVar.a();
                }
                break;
        }
    }
}
