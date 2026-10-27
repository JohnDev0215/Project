package b.a.a.f.i1;

import android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class d extends AbsBYDAutoPM2p5Listener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f137a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f138b = -1;

    @Override // android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener
    public void onPM2p5AnionDetectChanged(int i) {
        super.onPM2p5AnionDetectChanged(i);
        BydLogHelper.logW(AbsBYDAutoPM2p5Listener.TAG, "onPM2p5AnionDetectChanged state = " + i);
        EventBus.getDefault().post(new b.a.a.e.a(1314, i));
        EventBus.getDefault().post(new b.a.a.e.a(1315, i));
    }

    @Override // android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener
    public void onPM2p5CheckStateChanged(int i, int i2) {
        super.onPM2p5CheckStateChanged(i, i2);
        BydLogHelper.logW(AbsBYDAutoPM2p5Listener.TAG, "onPM2p5CheckStateChanged state_in = " + i + " -- state_out = " + i2);
        if (i != this.f137a) {
            b.a.a.h.a.f(1312);
            EventBus.getDefault().post(new b.a.a.e.a(1312, i));
            this.f137a = i;
        }
        if (i2 != this.f138b) {
            b.a.a.h.a.f(1313);
            EventBus.getDefault().post(new b.a.a.e.a(1313, i2));
            this.f138b = i2;
        }
    }

    @Override // android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener
    public void onPM2p5DualChannelDetectChanged(int i) {
        super.onPM2p5DualChannelDetectChanged(i);
        BydLogHelper.logW(AbsBYDAutoPM2p5Listener.TAG, "onPM2p5DualChannelDetectChanged state = " + i);
        if (b.a.a.h.a.a(1304)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1304, i));
    }

    @Override // android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener
    public void onPM2p5LevelChanged(int i, int i2) {
        super.onPM2p5LevelChanged(i, i2);
        BydLogHelper.logD("onPM2p5LevelChanged level_in = " + i + " -- level_out = " + i2);
        b.a.a.h.a.f(1302);
        EventBus.getDefault().post(new b.a.a.e.a(1302, i, i2));
    }

    @Override // android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener
    public void onPM2p5ValueChanged(int i, int i2) {
        super.onPM2p5ValueChanged(i, i2);
        BydLogHelper.logD("onPM2p5ValueChanged value_in = " + i + " -- value_out = " + i2);
        b.a.a.h.a.f(1301);
        EventBus.getDefault().post(new b.a.a.e.a(1301, i, i2));
    }

    @Override // android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener
    public void onRapidPurificationPM2p5StateChanged(int i) {
        super.onRapidPurificationPM2p5StateChanged(i);
        BydLogHelper.logW(AbsBYDAutoPM2p5Listener.TAG, "onRapidPurificationPM2p5StateChanged state = " + i);
        if (b.a.a.h.a.a(1308)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(1308, i));
    }
}
