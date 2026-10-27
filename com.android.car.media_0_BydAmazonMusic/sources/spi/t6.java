package spi;

import android.car.utils.Logs;
import com.android.car.media.byd.MediaApplication;
import com.android.car.media.common.source.MediaSourceViewModel;
import com.blankj.utilcode.util.ProcessUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.Utils;

/* JADX INFO: compiled from: MediaApplication.java */
/* JADX INFO: loaded from: classes.dex */
public final class t6 implements u7 {
    public t6(MediaApplication mediaApplication) {
    }

    @Override // spi.u7
    public final void a() {
        Logs.d("MediaApplication", "NetStateChangeReceiver onNetDisconnected");
    }

    @Override // spi.u7
    public final void b() {
        StringBuilder sbK = c.k("NetStateChangeReceiver onNetConnected:");
        sbK.append(ProcessUtils.isMainProcess());
        sbK.append(" isMainThread = ");
        sbK.append(ThreadUtils.isMainThread());
        Logs.e("MediaApplication", sbK.toString());
        ProcessUtils.isMainProcess();
    }

    @Override // spi.u7
    public final void c(boolean z) {
        Logs.d("MediaApplication", "onNetRealConnected：" + z);
        if (ProcessUtils.isMainProcess() && z) {
            Logs.d("MediaApplication", "reconnect amazon service");
            MediaSourceViewModel mediaSourceViewModelA = MediaSourceViewModel.a(Utils.getApp(), 1);
            mediaSourceViewModelA.e.postDelayed(new d7(mediaSourceViewModelA, 1), 1000L);
        }
    }
}
