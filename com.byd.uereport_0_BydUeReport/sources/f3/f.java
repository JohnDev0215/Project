package f3;

import android.content.Context;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import g5.k;

/* JADX INFO: compiled from: MaintainDBUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile f f4958a;

    public static f a() {
        if (f4958a == null) {
            synchronized (f.class) {
                if (f4958a == null) {
                    f4958a = new f();
                }
            }
        }
        return f4958a;
    }

    public final int b(Context context, int i8) {
        if (i8 != 2) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            long jE = k.e(context.getApplicationContext(), "KEY_MAINTAIN_TIME_TARGET", jCurrentTimeMillis);
            int iD = k.d(context.getApplicationContext(), "KEY_MAINTAIN_TIME", 90);
            if (jCurrentTimeMillis > jE) {
                iD -= Long.valueOf((jCurrentTimeMillis - jE) / 86400000).intValue();
            }
            int i9 = iD >= 0 ? iD : 0;
            g5.a.i("maintain currTime:" + jCurrentTimeMillis + ",targetTime:" + jE + ",timeDay:" + i9);
            return i9;
        }
        double totalMileageValue = BYDAutoStatisticDevice.getInstance(context.getApplicationContext()).getTotalMileageValue();
        double dC = k.c(context.getApplicationContext(), "KEY_MAINTAIN_MILE_TARGET", totalMileageValue);
        double dC2 = k.c(context.getApplicationContext(), "KEY_MAINTAIN_MILE_1", g5.c.d(context.getApplicationContext()));
        boolean z7 = BYDAutoInstrumentDevice.getInstance(context.getApplicationContext()).getMileageUnit() == 2;
        if (z7) {
            totalMileageValue *= 1.6093000173568726d;
        }
        if (totalMileageValue > dC) {
            dC2 -= (double) ((int) Math.round((totalMileageValue - dC) - 0.5d));
        }
        if (dC2 < 0.0d) {
            dC2 = 0.0d;
        }
        g5.a.i("maintain currMile:" + totalMileageValue + ",targetMile:" + dC + ",miles:" + dC2 + ",isMile:" + z7);
        return (int) dC2;
    }

    public void c(Context context, boolean z7) {
        boolean zB = k.b(context.getApplicationContext(), "KEY_MAINTAIN_TIME_STATE", false);
        boolean zB2 = k.b(context.getApplicationContext(), "KEY_MAINTAIN_MILE_STATE", false);
        g5.a.i("maintain sendMaintenanceInfoHint----isTimeState:" + zB + ",isMileState:" + zB2 + ",isForce:" + z7);
        if (!z7) {
            if (zB2) {
                BYDAutoInstrumentDevice.getInstance(context.getApplicationContext()).setMaintenanceInfo(2, b(context, 2));
            }
            if (zB) {
                BYDAutoInstrumentDevice.getInstance(context.getApplicationContext()).setMaintenanceInfo(1, b(context, 1));
                return;
            }
            return;
        }
        if (zB) {
            BYDAutoInstrumentDevice.getInstance(context.getApplicationContext()).setMaintenanceInfo(1, b(context, 1));
        } else {
            BYDAutoInstrumentDevice.getInstance(context.getApplicationContext()).setMaintenanceInfo(1, 991);
        }
        if (zB2) {
            BYDAutoInstrumentDevice.getInstance(context.getApplicationContext()).setMaintenanceInfo(2, b(context, 2));
        } else {
            BYDAutoInstrumentDevice.getInstance(context.getApplicationContext()).setMaintenanceInfo(2, BYDAutoInstrumentDevice.MAINTENANCE_MILEAGE_KILOMETER_MAX);
        }
    }
}
