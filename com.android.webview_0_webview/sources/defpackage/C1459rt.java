package defpackage;

import android.telephony.TelephonyManager;
import java.util.List;
import org.chromium.base.Callback;

/* JADX INFO: renamed from: rt, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1459rt extends TelephonyManager.CellInfoCallback {
    public final /* synthetic */ Callback a;

    public C1459rt(Callback callback) {
        this.a = callback;
    }

    @Override // android.telephony.TelephonyManager.CellInfoCallback
    public void onCellInfo(List list) {
        this.a.a(list);
    }
}
