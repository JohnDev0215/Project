package ts.car.iap.protocol;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public interface IapServiceListener {
    default int onServiceStatusChanged(int status) {
        Log.i("IapServiceListener", "onServiceStatusChanged: status = " + status);
        return 0;
    }
}
