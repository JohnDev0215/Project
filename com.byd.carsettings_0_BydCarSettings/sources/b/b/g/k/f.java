package b.b.g.k;

import android.hardware.bydauto.speed.AbsBYDAutoSpeedListener;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: BydCarSpeedListener.java */
/* JADX INFO: loaded from: classes2.dex */
public class f extends AbsBYDAutoSpeedListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile double f834b = Double.MIN_VALUE;

    @Override // android.hardware.bydauto.speed.AbsBYDAutoSpeedListener
    public void onSpeedValueChanged(double d2) {
        if (this.f834b == d2) {
            return;
        }
        this.f834b = d2;
        super.onSpeedValueChanged(d2);
        if (b.b.g.r.e.c(240011)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(240011, d2));
    }
}
