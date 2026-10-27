package c.a.d.g;

import android.hardware.bydauto.power.AbsBYDAutoPowerListener;
import c.a.d.f.e;

/* JADX INFO: compiled from: PowerStrListener.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends AbsBYDAutoPowerListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2494a = "d";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public e f2495b;

    public d(e eVar) {
        c.a.b.d.e.c(f2494a, "init PowerStrListener");
        this.f2495b = eVar;
    }

    @Override // android.hardware.bydauto.power.AbsBYDAutoPowerListener
    public void onPowerSTRChanged(int i2) {
        super.onPowerSTRChanged(i2);
        String str = f2494a;
        c.a.b.d.e.c(str, "onPowerSTRChanged->state = " + i2);
        switch (i2) {
            case 0:
                c.a.b.d.e.c(str, "IVI_PM_OFF");
                break;
            case 1:
                c.a.b.d.e.c(str, "IVI_PM_PRE_STARTUP");
                break;
            case 2:
                c.a.b.d.e.c(str, "IVI_PM_STARTUP");
                break;
            case 3:
                c.a.b.d.e.c(str, "IVI_PM_DEGRADED");
                break;
            case 4:
                c.a.b.d.e.c(str, "IVI_PM_STANDBY");
                break;
            case 5:
                c.a.b.d.e.c(str, "IVI_PM_STR");
                break;
            case 6:
                c.a.b.d.e.c(str, "IVI_PM_REFLASH");
                break;
            case 7:
                c.a.b.d.e.c(str, "IVI_PM_REMOTE_FOTA");
                break;
            case 8:
                c.a.b.d.e.c(str, "IVI_PM_SLEEP");
                break;
            case 9:
                c.a.b.d.e.c(str, "IVI_PM_STR_SUSPENDING");
                break;
            case 10:
                c.a.b.d.e.c(str, "IVI_PM_DISPLAY_ON");
                break;
            case 11:
                c.a.b.d.e.c(str, "IVI_PM_HALF_HOUR_MODE");
                break;
            case 12:
                c.a.b.d.e.c(str, "IVI_PM_TOD");
                break;
            case 13:
                c.a.b.d.e.c(str, "STR_INVALID");
                break;
            case 14:
                c.a.b.d.e.c(str, "STR_WAIT_FOR_VHAL");
                break;
            case 15:
                c.a.b.d.e.c(str, "STR_SUSPEND_ENTER");
                break;
            case 16:
                c.a.b.d.e.c(str, "STR_SUSPEND_EXIT");
                e eVar = this.f2495b;
                if (eVar == null) {
                    c.a.b.d.e.b(str, "appointmentBroadcast is null");
                } else {
                    eVar.a();
                }
                break;
            case 17:
                c.a.b.d.e.c(str, "STR_SHUTDOWN_ENTER");
                break;
            case 18:
                c.a.b.d.e.c(str, "STR_ON");
                break;
            case 19:
                c.a.b.d.e.c(str, "STR_SHUTDOWN_PREPARE");
                break;
            case 20:
                c.a.b.d.e.c(str, "STR_SHUTDOWN_CANCELLED");
                break;
        }
    }
}
