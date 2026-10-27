package spi;

import android.car.utils.Logs;
import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import com.android.car.media.netObserver.NetStateChangeReceiver;
import com.blankj.utilcode.util.ThreadUtils;
import java.util.Iterator;

/* JADX INFO: compiled from: NetStateChangeReceiver.java */
/* JADX INFO: loaded from: classes2.dex */
public final class w7 extends Observable.OnPropertyChangedCallback {
    public final /* synthetic */ NetStateChangeReceiver a;

    public w7(NetStateChangeReceiver netStateChangeReceiver) {
        this.a = netStateChangeReceiver;
    }

    /* JADX WARN: Type inference failed for: r3v6, types: [java.util.ArrayList, java.util.List<spi.u7>] */
    @Override // androidx.databinding.Observable.OnPropertyChangedCallback
    public final void onPropertyChanged(Observable observable, int i) {
        ObservableBoolean observableBoolean = NetStateChangeReceiver.d;
        if (observableBoolean.get()) {
            StringBuilder sbK = c.k(" NetStateChangeReceiver:onPropertyChanged:isNetRealUseful:");
            sbK.append(observableBoolean.get());
            Logs.e("NetStateChangeReceiver", sbK.toString());
        }
        if (!ThreadUtils.isMainThread()) {
            n.b.a.execute(new f2(this, 3));
            return;
        }
        Iterator it = this.a.a.iterator();
        while (it.hasNext()) {
            NetStateChangeReceiver.b(this.a, (u7) it.next(), NetStateChangeReceiver.d.get());
        }
    }
}
