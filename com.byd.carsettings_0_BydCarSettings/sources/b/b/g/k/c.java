package b.b.g.k;

import android.hardware.bydauto.ac.AbsBYDAutoAcListener;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: BydAcListener.java */
/* JADX INFO: loaded from: classes2.dex */
public class c extends AbsBYDAutoAcListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile int f822b = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile int f823c = Integer.MIN_VALUE;

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onAcRemoteCtrlTimeChanged(int i) {
        if (this.f822b == i) {
            return;
        }
        this.f822b = i;
        super.onAcRemoteCtrlTimeChanged(i);
        BydLogHelper.logD("onAcRemoteCtrlTimeChanged   value :" + i);
        if (b.b.g.r.e.c(30003)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(30003, i));
    }

    @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
    public void onTemperatureUnitChanged(int i) {
        if (this.f823c == i) {
            return;
        }
        this.f823c = i;
        super.onTemperatureUnitChanged(i);
        BydLogHelper.logD("onTemperatureUnitChanged   unit :" + i);
        if (b.b.g.r.e.c(30013)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(30013, i));
    }
}
