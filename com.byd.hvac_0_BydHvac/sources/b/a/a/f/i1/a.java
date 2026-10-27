package b.a.a.f.i1;

import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class a extends AbsBYDAutoBodyworkListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile int f133a = Integer.MIN_VALUE;

    @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
    public void onHasMessageChanged(int i, int i2) {
        super.onHasMessageChanged(i, i2);
        BydLogHelper.logD("onHasMessageChanged id = " + i + "  ---  state = " + i2);
        if (i == 111) {
            EventBus.getDefault().post(new b.a.a.e.a(1803, i2));
            return;
        }
        if (i == 149) {
            EventBus.getDefault().post(new b.a.a.e.a(1804, i2));
            return;
        }
        if (i == 166) {
            EventBus.getDefault().post(new b.a.a.e.a(1801, i2));
            return;
        }
        if (i == 207) {
            EventBus.getDefault().post(new b.a.a.e.a(1802, i2));
        } else if (i == 185) {
            EventBus.getDefault().post(new b.a.a.e.a(1800, i2));
        } else {
            if (i != 186) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1504, i2));
        }
    }

    @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
    public void onMessage5sOnlineStateChanged(int i, int i2) {
        super.onMessage5sOnlineStateChanged(i, i2);
        BydLogHelper.logD("onMessage5sOnlineStateChanged id = " + i + "  ---  state = " + i2);
        if (i == 2) {
            EventBus.getDefault().post(new b.a.a.e.a(1303, i2));
        } else {
            if (i != 59) {
                return;
            }
            EventBus.getDefault().post(new b.a.a.e.a(1013, i2));
        }
    }

    @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
    public void onPowerLevelChanged(int i) {
        super.onPowerLevelChanged(i);
        if (this.f133a == i) {
            return;
        }
        this.f133a = i;
        BydLogHelper.logW(AbsBYDAutoBodyworkListener.TAG, "onPowerLevelChanged level = " + i);
        EventBus.getDefault().post(new b.a.a.e.a(1600, i));
    }

    @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
    public void onReadyIndicatorLightStateChanged(int i) {
        super.onReadyIndicatorLightStateChanged(i);
        BydLogHelper.logW(AbsBYDAutoBodyworkListener.TAG, "onReadyIndicatorLightStateChanged state = " + i);
        EventBus.getDefault().post(new b.a.a.e.a(1603, i));
    }
}
