package b.b.g.k;

import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.radar.AbsBYDAutoRadarListener;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: BydRadarListener.java */
/* JADX INFO: loaded from: classes2.dex */
public class n extends AbsBYDAutoRadarListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f861b = Integer.MIN_VALUE;

    @Override // android.hardware.bydauto.radar.AbsBYDAutoRadarListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        super.onDataEventChanged(i, bYDAutoEventValue);
    }

    @Override // android.hardware.bydauto.radar.AbsBYDAutoRadarListener
    public void onError(int i, String str) {
        super.onError(i, str);
    }

    @Override // android.hardware.bydauto.radar.AbsBYDAutoRadarListener
    public void onRadarObstacleDistanceChanged(int i, int i2) {
        super.onRadarObstacleDistanceChanged(i, i2);
    }

    @Override // android.hardware.bydauto.radar.AbsBYDAutoRadarListener
    public void onRadarProbeStateChanged(int i, int i2) {
        super.onRadarProbeStateChanged(i, i2);
    }

    @Override // android.hardware.bydauto.radar.AbsBYDAutoRadarListener
    public void onReverseRadarSwitchStateChanged(int i) {
        if (this.f861b == i) {
            return;
        }
        this.f861b = i;
        super.onReverseRadarSwitchStateChanged(i);
        BydLogHelper.logD("onReverseRadarSwitchStateChanged " + i);
        if (b.b.g.r.e.c(160000)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(160000, i));
    }
}
