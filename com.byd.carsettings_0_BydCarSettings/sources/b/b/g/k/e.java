package b.b.g.k;

import android.hardware.bydauto.light.AbsBYDAutoLightListener;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: BydCarLightListener.java */
/* JADX INFO: loaded from: classes2.dex */
public class e extends AbsBYDAutoLightListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile int f829b = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile int f830c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile int f831d = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile int f832e = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile int f833f = Integer.MIN_VALUE;
    public volatile int g = Integer.MIN_VALUE;
    public volatile int h = Integer.MIN_VALUE;
    public volatile int i = Integer.MIN_VALUE;
    public volatile int j = Integer.MIN_VALUE;
    public volatile int k = Integer.MIN_VALUE;
    public volatile int l = Integer.MIN_VALUE;
    public volatile int m = Integer.MIN_VALUE;
    public volatile int n = Integer.MIN_VALUE;
    public volatile int o = Integer.MIN_VALUE;

    @Override // android.hardware.bydauto.light.AbsBYDAutoLightListener
    public void onAFSSwitchStateChange(int i) {
        if (this.f829b == i) {
            return;
        }
        this.f829b = i;
        super.onAFSSwitchStateChange(i);
        BydLogHelper.logD("onAFSSwitchStateChange: " + i);
        b.b.g.r.e.h(10006);
        b.b.a.c.a.j(new b.b.a.c.a(10006, i));
    }

    @Override // android.hardware.bydauto.light.AbsBYDAutoLightListener
    public void onAmbRingBrightnessChanged(int i) {
        if (this.k == i) {
            return;
        }
        this.k = i;
        super.onAmbRingBrightnessChanged(i);
        BydLogHelper.logD("onAmbRingBrightnessChanged:  , brightness ：" + i);
        if (b.b.g.r.e.c(20004)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(20004, 5, i));
    }

    @Override // android.hardware.bydauto.light.AbsBYDAutoLightListener
    public void onAmbRingColorChanged(int i) {
        if (this.l == i) {
            return;
        }
        this.l = i;
        super.onAmbRingColorChanged(i);
        BydLogHelper.logD("onAmbRingColorChanged:  , color ：" + i);
        if (b.b.g.r.e.c(20005)) {
            return;
        }
        EventBus.getDefault().post(new b.b.a.c.a(20005, 5, i));
    }

    @Override // android.hardware.bydauto.light.AbsBYDAutoLightListener
    public void onAmbientMulticolorModeChanged(int i, int i2) {
        if (this.i == i && this.j == i2) {
            return;
        }
        this.i = i;
        this.j = i2;
        super.onAmbientMulticolorModeChanged(i, i2);
        BydLogHelper.logD("onAmbientMulticolorModeChanged: area  " + i + "value ：" + i2);
        b.b.g.r.e.h(20007);
        EventBus.getDefault().post(new b.b.a.c.a(20007, i, i2));
    }

    @Override // android.hardware.bydauto.light.AbsBYDAutoLightListener
    public void onAmbientMulticolorStateChanged(int i, int i2) {
        if (this.g == i && this.h == i2) {
            return;
        }
        this.g = i;
        this.h = i2;
        super.onAmbientMulticolorStateChanged(i, i2);
        BydLogHelper.logD("onAmbientMulticolorStateChanged: " + i2 + "area ：" + i);
        b.b.g.r.e.h(20003);
        EventBus.getDefault().post(new b.b.a.c.a(20003, i, i2));
    }

    @Override // android.hardware.bydauto.light.AbsBYDAutoLightListener
    public void onAmbientMusicModechanged(int i, int i2) {
        if (this.f832e == i && this.f833f == i2) {
            return;
        }
        this.f832e = i;
        this.f833f = i2;
        super.onAmbientMusicModechanged(i, i2);
        BydLogHelper.logD("onAmbientMusicModechanged: area  " + i + "value ：" + i2);
        b.b.g.r.e.h(20006);
        EventBus.getDefault().post(new b.b.a.c.a(20006, i, i2));
    }

    @Override // android.hardware.bydauto.light.AbsBYDAutoLightListener
    public void onDayTimeLightChanged(int i) {
        if (this.f830c == i) {
            return;
        }
        this.f830c = i;
        super.onDayTimeLightChanged(i);
        BydLogHelper.logD("onDayTimeLightChanged: " + i);
        b.b.g.r.e.h(10000);
        b.b.a.c.a.j(new b.b.a.c.a(10000, i));
    }

    @Override // android.hardware.bydauto.light.AbsBYDAutoLightListener
    public void onDayTimeRunningLitghtStateChanged(int i) {
        if (this.f831d == i) {
            return;
        }
        this.f831d = i;
        super.onDayTimeRunningLitghtStateChanged(i);
        b.b.g.r.e.h(10000);
        b.b.a.c.a.j(new b.b.a.c.a(10000, i));
    }

    @Override // android.hardware.bydauto.light.AbsBYDAutoLightListener
    public void onFrontFogLightSwitchStateChanged(int i) {
        if (this.o == i) {
            return;
        }
        this.o = i;
        super.onFrontFogLightSwitchStateChanged(i);
        BydLogHelper.logD("onFrontFogLightSwitchStateChanged: " + i);
        if (b.b.g.r.e.c(10010)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(10010, i));
    }

    @Override // android.hardware.bydauto.light.AbsBYDAutoLightListener
    public void onHeadlightControlModeChanged(int i) {
        if (this.n == i) {
            return;
        }
        this.n = i;
        super.onHeadlightControlModeChanged(i);
        BydLogHelper.logD("onHeadlightControlModeChanged: " + i);
        if (b.b.g.r.e.c(10008)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(10008, i));
    }

    @Override // android.hardware.bydauto.light.AbsBYDAutoLightListener
    public void onRearFogLightSwitchStateChanged(int i) {
        if (this.m == i) {
            return;
        }
        this.m = i;
        super.onRearFogLightSwitchStateChanged(i);
        BydLogHelper.logD("onRearFogLightSwitchStateChanged: " + i);
        if (b.b.g.r.e.c(10009)) {
            return;
        }
        b.b.a.c.a.j(new b.b.a.c.a(10009, i));
    }
}
