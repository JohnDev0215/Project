package defpackage;

import android.annotation.SuppressLint;
import android.telephony.CellInfo;
import android.telephony.TelephonyManager;
import java.util.List;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: ApiHelperForQ.java */
/* JADX INFO: loaded from: classes.dex */
public final class d6 extends TelephonyManager.CellInfoCallback {
    public final /* synthetic */ Callback a;

    public d6(ty2 ty2Var) {
        this.a = ty2Var;
    }

    @Override // android.telephony.TelephonyManager.CellInfoCallback
    @SuppressLint({"Override"})
    public final void onCellInfo(List<CellInfo> list) {
        this.a.onResult(list);
    }
}
