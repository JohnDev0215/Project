package spi;

import android.car.utils.Logs;
import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import com.android.car.media.netObserver.NetStateChangeReceiver;
import com.blankj.utilcode.util.ThreadUtils;
import java.util.Iterator;

/* JADX INFO: compiled from: NetStateChangeReceiver.java */
/* JADX INFO: loaded from: classes2.dex */
public final class v7 extends Observable.OnPropertyChangedCallback {
    public final /* synthetic */ NetStateChangeReceiver a;

    public v7(NetStateChangeReceiver netStateChangeReceiver) {
        this.a = netStateChangeReceiver;
    }

    /* JADX WARN: Type inference failed for: r3v11, types: [java.util.ArrayList, java.util.List<spi.u7>] */
    @Override // androidx.databinding.Observable.OnPropertyChangedCallback
    public final void onPropertyChanged(Observable observable, int i) {
        StringBuilder sbK = c.k(" NetStateChangeReceiver:onPropertyChanged:isNetUseful:");
        ObservableBoolean observableBoolean = NetStateChangeReceiver.c;
        sbK.append(observableBoolean.get());
        Logs.e("NetStateChangeReceiver", sbK.toString());
        z7.c = observableBoolean.get();
        if (observableBoolean.get()) {
            n.b.a.execute(m0.g);
        } else {
            NetStateChangeReceiver.d.set(false);
        }
        if (!ThreadUtils.isMainThread()) {
            n.b.a.execute(new f2(this, 2));
            return;
        }
        Iterator it = this.a.a.iterator();
        while (it.hasNext()) {
            NetStateChangeReceiver.a(this.a, (u7) it.next(), NetStateChangeReceiver.c.get());
        }
    }
}
