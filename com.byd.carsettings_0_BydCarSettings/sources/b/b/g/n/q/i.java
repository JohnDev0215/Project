package b.b.g.n.q;

import android.content.Context;
import android.hardware.bydauto.light.AbsBYDAutoLightListener;
import android.hardware.bydauto.light.BYDAutoLightDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: BYDAutoLightDeviceService.java */
/* JADX INFO: loaded from: classes2.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile i f1278a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BYDAutoLightDevice f1279b;

    public static i k() {
        if (f1278a == null) {
            synchronized (i.class) {
                if (f1278a == null) {
                    f1278a = new i();
                }
            }
        }
        return f1278a;
    }

    public int a() {
        BYDAutoLightDevice bYDAutoLightDevice = this.f1279b;
        if (bYDAutoLightDevice != null) {
            return bYDAutoLightDevice.getAFSSwitch();
        }
        return 0;
    }

    public int b() {
        BYDAutoLightDevice bYDAutoLightDevice = this.f1279b;
        if (bYDAutoLightDevice != null) {
            return bYDAutoLightDevice.getAmbientColorsSupport();
        }
        return 0;
    }

    public int c(int i) {
        BYDAutoLightDevice bYDAutoLightDevice = this.f1279b;
        if (bYDAutoLightDevice != null) {
            return bYDAutoLightDevice.getAmbientMulticolorMode(i);
        }
        return 0;
    }

    public int d(int i) {
        BYDAutoLightDevice bYDAutoLightDevice = this.f1279b;
        if (bYDAutoLightDevice != null) {
            return bYDAutoLightDevice.getAmbientMulticolorState(i);
        }
        return 0;
    }

    public int e(int i) {
        BYDAutoLightDevice bYDAutoLightDevice = this.f1279b;
        if (bYDAutoLightDevice != null) {
            return bYDAutoLightDevice.getAmbientMusicMode(i);
        }
        return 0;
    }

    public boolean f() {
        BYDAutoLightDevice bYDAutoLightDevice = this.f1279b;
        return bYDAutoLightDevice != null && bYDAutoLightDevice.getDayTimeLightState() == 1;
    }

    public int g() {
        BYDAutoLightDevice bYDAutoLightDevice = this.f1279b;
        if (bYDAutoLightDevice != null) {
            return bYDAutoLightDevice.getDayTimeRunningLightState();
        }
        return 0;
    }

    public int h() {
        BYDAutoLightDevice bYDAutoLightDevice = this.f1279b;
        if (bYDAutoLightDevice == null) {
            return 0;
        }
        int frontFogLightConfig = bYDAutoLightDevice.getFrontFogLightConfig();
        BydLogHelper.logD(i.class.getSimpleName(), "getFrontFogLightSwitchState value : " + frontFogLightConfig);
        return frontFogLightConfig;
    }

    public int i() {
        BYDAutoLightDevice bYDAutoLightDevice = this.f1279b;
        if (bYDAutoLightDevice == null) {
            return 0;
        }
        int frontFogLightSwitchState = bYDAutoLightDevice.getFrontFogLightSwitchState();
        BydLogHelper.logD(i.class.getSimpleName(), "getFrontFogLightSwitchState value : " + frontFogLightSwitchState);
        return frontFogLightSwitchState;
    }

    public int j() {
        BYDAutoLightDevice bYDAutoLightDevice = this.f1279b;
        if (bYDAutoLightDevice == null) {
            return 0;
        }
        int headlightControlMode = bYDAutoLightDevice.getHeadlightControlMode();
        BydLogHelper.logD(i.class.getSimpleName(), "getHeadlightControlMode value : " + headlightControlMode);
        return headlightControlMode;
    }

    public int l() {
        BYDAutoLightDevice bYDAutoLightDevice = this.f1279b;
        if (bYDAutoLightDevice == null) {
            return 0;
        }
        int rearFogLightSwitchState = bYDAutoLightDevice.getRearFogLightSwitchState();
        BydLogHelper.logD(i.class.getSimpleName(), "getRearFogLightSwitchState value : " + rearFogLightSwitchState);
        return rearFogLightSwitchState;
    }

    public void m(Context context) {
        this.f1279b = BYDAutoLightDevice.getInstance(context);
    }

    public void n(AbsBYDAutoLightListener absBYDAutoLightListener) {
        if (this.f1279b == null) {
            return;
        }
        String simpleName = i.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("registerListener BydLightListener start listener == null : ");
        sb.append(absBYDAutoLightListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1279b.registerListener(absBYDAutoLightListener);
    }

    public int o(int i) {
        BYDAutoLightDevice bYDAutoLightDevice = this.f1279b;
        if (bYDAutoLightDevice == null) {
            return 0;
        }
        bYDAutoLightDevice.setAFSSwitchSate(i);
        return 0;
    }

    public int p(int i, int i2) {
        BYDAutoLightDevice bYDAutoLightDevice = this.f1279b;
        if (bYDAutoLightDevice != null) {
            return bYDAutoLightDevice.setAmbientMulticolorMode(i, i2);
        }
        return 0;
    }

    public int q(int i, int i2) {
        BYDAutoLightDevice bYDAutoLightDevice = this.f1279b;
        if (bYDAutoLightDevice != null) {
            return bYDAutoLightDevice.setAmbientMulticolorState(i, i2);
        }
        return 0;
    }

    public int r(int i, int i2) {
        BYDAutoLightDevice bYDAutoLightDevice = this.f1279b;
        if (bYDAutoLightDevice != null) {
            return bYDAutoLightDevice.setAmbientMusicMode(i, i2);
        }
        return 0;
    }

    public void s(int i) {
        BYDAutoLightDevice bYDAutoLightDevice = this.f1279b;
        if (bYDAutoLightDevice != null) {
            bYDAutoLightDevice.setDayTimeLightState(i);
        }
    }

    public void t(int i) {
        if (this.f1279b != null) {
            BydLogHelper.logD(i.class.getSimpleName(), "setFrontFogLightSwitchState state : " + i);
            this.f1279b.setFrontFogLightSwitchState(i);
        }
    }

    public void u(int i) {
        if (this.f1279b != null) {
            BydLogHelper.logD(i.class.getSimpleName(), "setHeadlightControlMode mode : " + i);
            this.f1279b.setHeadlightControlMode(i);
        }
    }

    public void v(int i) {
        if (this.f1279b != null) {
            BydLogHelper.logD(i.class.getSimpleName(), "setRearFogLightSwitchState state : " + i);
            this.f1279b.setRearFogLightSwitchState(i);
        }
    }

    public void w(AbsBYDAutoLightListener absBYDAutoLightListener) {
        if (this.f1279b == null) {
            return;
        }
        String simpleName = i.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("unregisterListener BydLightListener start listener == null : ");
        sb.append(absBYDAutoLightListener == null);
        BydLogHelper.logD(simpleName, sb.toString());
        this.f1279b.unregisterListener(absBYDAutoLightListener);
    }
}
