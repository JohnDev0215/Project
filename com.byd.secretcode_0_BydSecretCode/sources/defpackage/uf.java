package defpackage;

import android.location.GnssStatus;
import android.os.Handler;
import android.os.Message;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: SatelliteStatusListener.java */
/* JADX INFO: loaded from: classes.dex */
public class uf extends GnssStatus.Callback {
    public final String a = "SatelliteStatusListener";
    public Handler b;

    public uf(Handler handler) {
        this.b = handler;
        BydLogHelper.logE("SatelliteStatusListener 卫星数据更新了！！！！mHandler / " + this.b);
    }

    @Override // android.location.GnssStatus.Callback
    public void onFirstFix(int i) {
        super.onFirstFix(i);
    }

    @Override // android.location.GnssStatus.Callback
    public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
        BydLogHelper.logE("onSatelliteStatusChanged 卫星数据更新了！！！！mHandler / " + this.b + ", gnssStatus / " + gnssStatus);
        Message.obtain(this.b, 0, gnssStatus).sendToTarget();
    }

    @Override // android.location.GnssStatus.Callback
    public void onStarted() {
        super.onStarted();
    }

    @Override // android.location.GnssStatus.Callback
    public void onStopped() {
        super.onStopped();
    }
}
