package r4;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.charging.BYDAutoChargingDevice;
import android.hardware.bydauto.ota.AbsMessageCallback;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import android.hardware.bydauto.test.AbsBYDAutoTestListener;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.os.Handler;
import android.os.Looper;
import com.byd.uereport.R;
import com.byd.uereport.dialog.PlanChargeDialog;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.Arrays;
import java.util.Calendar;

/* JADX INFO: compiled from: PlanUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static PlanChargeDialog f7800a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int[] f7801b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f7802c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f7803d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static AbsBYDAutoTestListener f7804e;

    /* JADX INFO: compiled from: PlanUtils.java */
    public class a extends AbsMessageCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f7805a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f7806b;

        public a(Context context, String str) {
            this.f7805a = context;
            this.f7806b = str;
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onSendCanNM(int i8) throws Throwable {
            super.onSendCanNM(i8);
            f.b("[智慧充电]----[网络报文2]----回调：" + i8);
            c.j(this.f7805a, System.currentTimeMillis(), this.f7806b);
            c.k(this.f7805a, this.f7806b);
            f.b("****************执行完毕***********************\n");
        }
    }

    /* JADX INFO: compiled from: PlanUtils.java */
    public class b extends AbsBYDAutoTestListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f7807a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f7808b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ long f7809c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ long f7810d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ int[] f7811e;

        /* JADX INFO: compiled from: PlanUtils.java */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() throws Throwable {
                b bVar = b.this;
                e.h(bVar.f7807a, "KEY_IMMEDIATELY_TIME_START", bVar.f7809c);
                b bVar2 = b.this;
                e.h(bVar2.f7807a, "KEY_IMMEDIATELY_TIME_END", bVar2.f7810d);
                b bVar3 = b.this;
                c.s(bVar3.f7807a, bVar3.f7811e, 2, c.f7803d, false);
            }
        }

        public b(Context context, boolean z7, long j8, long j9, int[] iArr) {
            this.f7807a = context;
            this.f7808b = z7;
            this.f7809c = j8;
            this.f7810d = j9;
            this.f7811e = iArr;
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onGetOffScreenFlagResult(int i8, int i9) throws Throwable {
            super.onGetOffScreenFlagResult(i8, i9);
            f.b("[预约充电]----[充电计划]----[下电弹框]----onGetOffScreenFlagResult：type=" + i8 + ",resultCode=" + i9);
            if (i8 != 0) {
                if (i8 == 1) {
                    if (i9 == 1) {
                        BYDAutoTestDevice.getInstance(this.f7807a).queryOffScreenFlag(0);
                        return;
                    } else {
                        BYDAutoTestDevice.getInstance(this.f7807a).unregisterListener(c.f7804e);
                        return;
                    }
                }
                return;
            }
            BYDAutoTestDevice.getInstance(this.f7807a).unregisterListener(c.f7804e);
            boolean unused = c.f7803d = i9 == 1;
            if (this.f7808b) {
                f.b("[预约充电]----[充电计划]----[下电弹框]----触发");
                new Handler(Looper.getMainLooper()).postDelayed(new a(), c.f7803d ? 500L : 0L);
                return;
            }
            f.b("[预约充电]----[充电计划]----[下电弹框]----立即充电：不弹框熄屏");
            BYDAutoTestDevice.getInstance(this.f7807a).setOffScreenFlag(1, 0);
            if (c.f7803d) {
                return;
            }
            BYDAutoOtaDevice.getInstance(this.f7807a).setBackLightState(2, 2);
        }
    }

    public static void g(Context context) throws Throwable {
        f.b("[预约充电]----[本地闹钟]----[置空处理]----");
        Intent intent = new Intent("com.byd.smartcharge.plan_alarm");
        intent.putExtra("ALARM_FROM", "TYPE_ALARM_EMPTY");
        ((AlarmManager) context.getSystemService("alarm")).setExactAndAllowWhileIdle(0, System.currentTimeMillis(), PendingIntent.getBroadcast(context, 0, intent, 134217728));
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0100 A[Catch: Exception -> 0x022c, TryCatch #0 {Exception -> 0x022c, blocks: (B:3:0x0004, B:8:0x006a, B:11:0x0075, B:12:0x0079, B:14:0x007f, B:17:0x008c, B:18:0x0090, B:21:0x00bd, B:29:0x00d3, B:31:0x0100, B:33:0x010d, B:35:0x0113, B:37:0x011f, B:39:0x0125, B:40:0x0131, B:42:0x0140, B:44:0x0184, B:45:0x0189, B:52:0x01c7, B:56:0x0203, B:58:0x020c, B:60:0x0212, B:62:0x021d), top: B:67:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x010d A[Catch: Exception -> 0x022c, TryCatch #0 {Exception -> 0x022c, blocks: (B:3:0x0004, B:8:0x006a, B:11:0x0075, B:12:0x0079, B:14:0x007f, B:17:0x008c, B:18:0x0090, B:21:0x00bd, B:29:0x00d3, B:31:0x0100, B:33:0x010d, B:35:0x0113, B:37:0x011f, B:39:0x0125, B:40:0x0131, B:42:0x0140, B:44:0x0184, B:45:0x0189, B:52:0x01c7, B:56:0x0203, B:58:0x020c, B:60:0x0212, B:62:0x021d), top: B:67:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void h(android.content.Context r34, long r35, java.lang.String r37) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 565
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: r4.c.h(android.content.Context, long, java.lang.String):void");
    }

    public static void i(Context context, long j8, String str) throws Throwable {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(j8);
            int i8 = calendar.get(1);
            int i9 = calendar.get(2);
            int i10 = calendar.get(5);
            long jD = e.d(context, "KEY_USECAR_START_TIME");
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTimeInMillis(jD);
            calendar2.set(i8, i9, i10, calendar2.get(11), calendar2.get(12), 0);
            calendar2.set(14, 0);
            long timeInMillis = calendar2.getTimeInMillis();
            int i11 = calendar.get(7);
            String[] strArrSplit = e.f(context, "KEY_USECAR_CYCLE").split("\\|");
            int i12 = i11 - 1;
            int i13 = 0;
            for (int i14 = i12 + 1; i14 <= i12 + 7; i14++) {
                i13++;
                if ("1".equals(strArrSplit[i14 % 7])) {
                    break;
                }
            }
            int i15 = 0;
            for (int i16 = i12 + 6; i16 >= 0; i16--) {
                i15--;
                if ("1".equals(strArrSplit[i16 % 7])) {
                    break;
                }
            }
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTimeInMillis(timeInMillis);
            calendar3.add(5, i15);
            long timeInMillis2 = calendar3.getTimeInMillis();
            Calendar calendar4 = Calendar.getInstance();
            calendar4.setTimeInMillis(timeInMillis);
            calendar4.add(5, i13);
            long timeInMillis3 = calendar4.getTimeInMillis();
            if (!"1".equals(strArrSplit[i12])) {
                timeInMillis = timeInMillis2;
            } else if (j8 < timeInMillis) {
                timeInMillis3 = timeInMillis;
                timeInMillis = timeInMillis2;
            }
            f.b("[时间计划]----[预约用车]----currTime = " + j8 + ",beforeStartTime = " + timeInMillis + ",beforeEndTime = -1,afterStartTime = " + timeInMillis3 + ",afterEndTime = -1");
            Calendar calendar5 = Calendar.getInstance();
            calendar5.setTimeInMillis(timeInMillis3);
            int i17 = calendar5.get(1);
            int i18 = calendar5.get(2);
            int i19 = calendar5.get(5);
            int i20 = calendar5.get(11);
            int i21 = calendar5.get(12);
            if (i17 >= 100) {
                i17 = i8 % 100;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("[充电计划]----[开始时间]----[预约用车]：");
            sb.append(i17);
            sb.append(":");
            int i22 = 1 + i18;
            sb.append(i22);
            sb.append(":");
            sb.append(i19);
            sb.append(":");
            sb.append(i20);
            sb.append(":");
            sb.append(i21);
            f.b(sb.toString());
            if (g5.c.h()) {
                BYDAutoChargingDevice.getInstance(context).setCarPlan(1, 0, 127, i17, i22, i19, i20, i21);
            } else {
                BYDAutoChargingDevice.getInstance(context).setCarPlan(1, 3, 127, i17, i22, i19, i20, i21);
            }
            r(context);
            if (timeInMillis3 >= j8) {
                Intent intent = new Intent("com.byd.smartcharge.plan_alarm");
                intent.putExtra("ALARM_FROM", "TYPE_ALARM_START_USECAR");
                ((AlarmManager) context.getSystemService("alarm")).setExactAndAllowWhileIdle(0, timeInMillis3, PendingIntent.getBroadcast(context, 0, intent, 134217728));
                f.b("[预约用车]----[本地闹钟]----[设置开始]----AlarmManager：" + i19 + "日" + i20 + "时" + i21 + "分");
            }
        } catch (Exception e8) {
            BydLogHelper.logE(e8.getMessage());
        }
    }

    public static void j(Context context, long j8, String str) throws Throwable {
        boolean zB = e.b(context, "KEY_MODE");
        if (("TYPE_ALARM_CLOUD_MODE".equals(str) || "TYPE_ALARM_MODE".equals(str)) && !zB) {
            p(context, 0, -1L, -1L, -1L, str);
            f.b("[预约充电]----[切换到预约用车]----");
            return;
        }
        if (!zB) {
            f.b("[预约充电]----[预约用车操作中]----");
            l(context, false, BYDAutoBodyworkDevice.getInstance(context).getPowerLevel(), str);
        } else if (!e.b(context, "KEY_CHARGE_SWITCH")) {
            p(context, 0, -1L, -1L, -1L, str);
        } else if (r4.b.c(context) == 0) {
            m(context, j8, str);
        } else {
            h(context, j8, str);
        }
    }

    public static void k(Context context, String str) throws Throwable {
        try {
            f.b("*********************************************\n");
            if (g5.c.h()) {
                boolean z7 = BYDAutoBodyworkDevice.getInstance(context).hasMessage(151) == 1;
                if ((BYDAutoStudy.getInstance(context).getCarPlanSupport() == 0) && z7) {
                    z = true;
                }
            } else {
                int hevCarPlanSupport = BYDAutoStudy.getInstance(context).getHevCarPlanSupport();
                z = hevCarPlanSupport == 2;
                f.b("[预约用车配置]----混动：" + hevCarPlanSupport);
            }
            if (z) {
                boolean zB = e.b(context, "KEY_MODE");
                if (("TYPE_ALARM_CLOUD_MODE".equals(str) || "TYPE_ALARM_MODE".equals(str)) && zB) {
                    q(context);
                    f.b("[预约用车]----[切换到预约充电]----");
                    return;
                }
                if (zB) {
                    f.b("[预约用车]----[预约充电操作中]----");
                    return;
                }
                if (!e.b(context, "KEY_USECAR_SWITCH")) {
                    q(context);
                    return;
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (r4.b.d(context) == 0) {
                    n(context, jCurrentTimeMillis, str);
                } else {
                    i(context, jCurrentTimeMillis, str);
                }
            }
        } catch (Exception e8) {
            BydLogHelper.logE(e8.getMessage());
        }
    }

    public static void l(Context context, boolean z7, int i8, String str) throws Throwable {
        if (i8 != 2 && i8 != 3) {
            f.b("[预约充电]----[充电计划]----[下电弹框]----[doSetOffScreenFlag]:OFF挡不下设" + z7);
            return;
        }
        if ("TYPE_ALARM_POWER_NOT_SEND".equals(str)) {
            f.b("[预约充电]----[充电计划]----[下电弹框]----[doSetOffScreenFlag]:ON挡不下设" + z7);
            return;
        }
        if (z7) {
            f.b("[预约充电]----[充电计划]----[下电弹框]----[doSetOffScreenFlag]----阻断熄屏");
            BYDAutoTestDevice.getInstance(context).setOffScreenFlag(1, 1);
        } else {
            f.b("[预约充电]----[充电计划]----[下电弹框]----[doSetOffScreenFlag]----正常熄屏");
            BYDAutoTestDevice.getInstance(context).setOffScreenFlag(1, 0);
        }
    }

    public static void m(Context context, long j8, String str) throws Throwable {
        try {
            long jD = e.d(context, "KEY_CHARGE_START_TIME");
            if (e.b(context, "KEY_CHARGE_FULL")) {
                f.b("[预约充电]----currTime = " + j8 + ",startTime = " + jD + ",endTime = -1");
                if (j8 <= jD - 86400000) {
                    f.b("[预约充电]----[充电计划]----[容错处理]----code：1000");
                    p(context, 0, -1L, -1L, -1L, str);
                    return;
                }
                if (j8 <= jD) {
                    p(context, 1, jD, -1L, jD, str);
                    return;
                }
                f.b("[预约充电]----[充电计划]----[本地计划失效]----code：1,endTime = " + j8);
                e.g(context, "KEY_CHARGE_SWITCH", false);
                context.sendBroadcast(new Intent("com.byd.smartcharge.ui_plan"));
                d.f(context);
                p(context, 0, -1L, -1L, -1L, str);
                return;
            }
            long jD2 = e.d(context, "KEY_CHARGE_END_TIME");
            f.b("[预约充电]----currTime = " + j8 + ",startTime = " + jD + ",endTime = " + jD2);
            if (jD > jD2) {
                f.b("[预约充电]----[充电计划]----[容错处理]----code：1001");
                p(context, 0, -1L, -1L, -1L, str);
                return;
            }
            if (j8 <= jD - 86400000) {
                f.b("[预约充电]----[充电计划]----[容错处理]----code：1002");
                p(context, 0, -1L, -1L, jD, str);
                return;
            }
            if (j8 < jD) {
                p(context, 1, jD, jD2, jD, str);
                return;
            }
            if (j8 < jD2) {
                p(context, 2, jD, jD2, -1L, str);
                return;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(jD2);
            f.b("[预约充电]----[充电计划]----[本地计划失效]----code：2,endtime = " + calendar.toString());
            e.g(context, "KEY_CHARGE_SWITCH", false);
            context.sendBroadcast(new Intent("com.byd.smartcharge.ui_plan"));
            d.f(context);
            p(context, 0, -1L, -1L, -1L, str);
        } catch (Exception e8) {
            BydLogHelper.logE(e8.getMessage());
        }
    }

    public static void n(Context context, long j8, String str) throws Throwable {
        long jD = e.d(context, "KEY_USECAR_START_TIME");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(jD);
        int i8 = calendar.get(1);
        int i9 = calendar.get(2);
        int i10 = calendar.get(5);
        int i11 = calendar.get(11);
        int i12 = calendar.get(12);
        if (j8 >= jD) {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTimeInMillis(jD);
            f.b("[预约用车]----[充电计划]----[本地计划失效]----code：3,startCalendar = " + calendar2.toString());
            e.g(context, "KEY_USECAR_SWITCH", false);
            context.sendBroadcast(new Intent("com.byd.smartcharge.ui_plan"));
            d.f(context);
            q(context);
            return;
        }
        if (i8 >= 100) {
            i8 %= 100;
        }
        int i13 = i8;
        StringBuilder sb = new StringBuilder();
        sb.append("[预约用车]----[充电计划]----[开始时间]：");
        sb.append(i13);
        sb.append(":");
        int i14 = i9 + 1;
        sb.append(i14);
        sb.append(":");
        sb.append(i10);
        sb.append(":");
        sb.append(i11);
        sb.append(":");
        sb.append(i12);
        f.b(sb.toString());
        BYDAutoChargingDevice.getInstance(context).setCarPlan(1, 0, 127, i13, i14, i10, i11, i12);
        r(context);
        if (jD >= j8) {
            Intent intent = new Intent("com.byd.smartcharge.plan_alarm");
            intent.putExtra("ALARM_FROM", "TYPE_ALARM_START_USECAR");
            ((AlarmManager) context.getSystemService("alarm")).setExactAndAllowWhileIdle(0, jD, PendingIntent.getBroadcast(context, 0, intent, 134217728));
            f.b("[预约用车]----[本地闹钟]----[设置开始]----AlarmManager：" + i10 + "日" + i11 + "时" + i12 + "分");
        }
    }

    public static void o(Context context) throws Throwable {
        PlanChargeDialog planChargeDialog = f7800a;
        if (planChargeDialog == null || !planChargeDialog.isShowing()) {
            return;
        }
        f7800a.dismiss();
        f7800a = null;
        int[] iArr = f7801b;
        if (iArr == null || iArr.length != 6) {
            return;
        }
        int i8 = f7802c;
        if (i8 == 2 || i8 == 1) {
            s(context, iArr, i8, f7803d, true);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:120:0x04a7 A[Catch: Exception -> 0x072a, TryCatch #0 {Exception -> 0x072a, blocks: (B:3:0x000e, B:6:0x0033, B:8:0x003b, B:10:0x0043, B:12:0x004b, B:14:0x0053, B:15:0x0065, B:22:0x00b6, B:25:0x00c5, B:29:0x00d4, B:45:0x0126, B:48:0x0143, B:50:0x016f, B:51:0x0171, B:54:0x01ab, B:57:0x01da, B:59:0x01e2, B:71:0x0275, B:73:0x029d, B:74:0x029f, B:77:0x02dc, B:80:0x030b, B:83:0x0315, B:84:0x031b, B:96:0x03b2, B:98:0x03d4, B:100:0x0414, B:109:0x042d, B:117:0x049a, B:118:0x049d, B:120:0x04a7, B:122:0x04ae, B:172:0x06c1, B:177:0x06d4, B:178:0x06e3, B:180:0x06ef, B:183:0x06f7, B:184:0x0717, B:186:0x071b, B:188:0x0721, B:124:0x04c7, B:126:0x04cf, B:128:0x04d6, B:129:0x04e4, B:131:0x04ec, B:170:0x06ba, B:135:0x0500, B:139:0x0532, B:140:0x0537, B:142:0x053f, B:144:0x055c, B:148:0x0599, B:156:0x05b3, B:146:0x0566, B:147:0x0580, B:157:0x05b8, B:159:0x05c3, B:161:0x0635, B:160:0x05e5, B:164:0x0644, B:165:0x0660, B:167:0x0676, B:168:0x0693, B:110:0x0472, B:99:0x040d, B:112:0x047b, B:79:0x02f5, B:87:0x036c, B:90:0x037c, B:92:0x0395, B:56:0x01c4, B:62:0x022f, B:65:0x023f, B:67:0x0258, B:30:0x00e9, B:32:0x00f3, B:34:0x00f9, B:36:0x0101, B:44:0x0111), top: B:193:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x04c7 A[Catch: Exception -> 0x072a, TryCatch #0 {Exception -> 0x072a, blocks: (B:3:0x000e, B:6:0x0033, B:8:0x003b, B:10:0x0043, B:12:0x004b, B:14:0x0053, B:15:0x0065, B:22:0x00b6, B:25:0x00c5, B:29:0x00d4, B:45:0x0126, B:48:0x0143, B:50:0x016f, B:51:0x0171, B:54:0x01ab, B:57:0x01da, B:59:0x01e2, B:71:0x0275, B:73:0x029d, B:74:0x029f, B:77:0x02dc, B:80:0x030b, B:83:0x0315, B:84:0x031b, B:96:0x03b2, B:98:0x03d4, B:100:0x0414, B:109:0x042d, B:117:0x049a, B:118:0x049d, B:120:0x04a7, B:122:0x04ae, B:172:0x06c1, B:177:0x06d4, B:178:0x06e3, B:180:0x06ef, B:183:0x06f7, B:184:0x0717, B:186:0x071b, B:188:0x0721, B:124:0x04c7, B:126:0x04cf, B:128:0x04d6, B:129:0x04e4, B:131:0x04ec, B:170:0x06ba, B:135:0x0500, B:139:0x0532, B:140:0x0537, B:142:0x053f, B:144:0x055c, B:148:0x0599, B:156:0x05b3, B:146:0x0566, B:147:0x0580, B:157:0x05b8, B:159:0x05c3, B:161:0x0635, B:160:0x05e5, B:164:0x0644, B:165:0x0660, B:167:0x0676, B:168:0x0693, B:110:0x0472, B:99:0x040d, B:112:0x047b, B:79:0x02f5, B:87:0x036c, B:90:0x037c, B:92:0x0395, B:56:0x01c4, B:62:0x022f, B:65:0x023f, B:67:0x0258, B:30:0x00e9, B:32:0x00f3, B:34:0x00f9, B:36:0x0101, B:44:0x0111), top: B:193:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:174:0x06cf A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:180:0x06ef A[Catch: Exception -> 0x072a, TryCatch #0 {Exception -> 0x072a, blocks: (B:3:0x000e, B:6:0x0033, B:8:0x003b, B:10:0x0043, B:12:0x004b, B:14:0x0053, B:15:0x0065, B:22:0x00b6, B:25:0x00c5, B:29:0x00d4, B:45:0x0126, B:48:0x0143, B:50:0x016f, B:51:0x0171, B:54:0x01ab, B:57:0x01da, B:59:0x01e2, B:71:0x0275, B:73:0x029d, B:74:0x029f, B:77:0x02dc, B:80:0x030b, B:83:0x0315, B:84:0x031b, B:96:0x03b2, B:98:0x03d4, B:100:0x0414, B:109:0x042d, B:117:0x049a, B:118:0x049d, B:120:0x04a7, B:122:0x04ae, B:172:0x06c1, B:177:0x06d4, B:178:0x06e3, B:180:0x06ef, B:183:0x06f7, B:184:0x0717, B:186:0x071b, B:188:0x0721, B:124:0x04c7, B:126:0x04cf, B:128:0x04d6, B:129:0x04e4, B:131:0x04ec, B:170:0x06ba, B:135:0x0500, B:139:0x0532, B:140:0x0537, B:142:0x053f, B:144:0x055c, B:148:0x0599, B:156:0x05b3, B:146:0x0566, B:147:0x0580, B:157:0x05b8, B:159:0x05c3, B:161:0x0635, B:160:0x05e5, B:164:0x0644, B:165:0x0660, B:167:0x0676, B:168:0x0693, B:110:0x0472, B:99:0x040d, B:112:0x047b, B:79:0x02f5, B:87:0x036c, B:90:0x037c, B:92:0x0395, B:56:0x01c4, B:62:0x022f, B:65:0x023f, B:67:0x0258, B:30:0x00e9, B:32:0x00f3, B:34:0x00f9, B:36:0x0101, B:44:0x0111), top: B:193:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:183:0x06f7 A[Catch: Exception -> 0x072a, TryCatch #0 {Exception -> 0x072a, blocks: (B:3:0x000e, B:6:0x0033, B:8:0x003b, B:10:0x0043, B:12:0x004b, B:14:0x0053, B:15:0x0065, B:22:0x00b6, B:25:0x00c5, B:29:0x00d4, B:45:0x0126, B:48:0x0143, B:50:0x016f, B:51:0x0171, B:54:0x01ab, B:57:0x01da, B:59:0x01e2, B:71:0x0275, B:73:0x029d, B:74:0x029f, B:77:0x02dc, B:80:0x030b, B:83:0x0315, B:84:0x031b, B:96:0x03b2, B:98:0x03d4, B:100:0x0414, B:109:0x042d, B:117:0x049a, B:118:0x049d, B:120:0x04a7, B:122:0x04ae, B:172:0x06c1, B:177:0x06d4, B:178:0x06e3, B:180:0x06ef, B:183:0x06f7, B:184:0x0717, B:186:0x071b, B:188:0x0721, B:124:0x04c7, B:126:0x04cf, B:128:0x04d6, B:129:0x04e4, B:131:0x04ec, B:170:0x06ba, B:135:0x0500, B:139:0x0532, B:140:0x0537, B:142:0x053f, B:144:0x055c, B:148:0x0599, B:156:0x05b3, B:146:0x0566, B:147:0x0580, B:157:0x05b8, B:159:0x05c3, B:161:0x0635, B:160:0x05e5, B:164:0x0644, B:165:0x0660, B:167:0x0676, B:168:0x0693, B:110:0x0472, B:99:0x040d, B:112:0x047b, B:79:0x02f5, B:87:0x036c, B:90:0x037c, B:92:0x0395, B:56:0x01c4, B:62:0x022f, B:65:0x023f, B:67:0x0258, B:30:0x00e9, B:32:0x00f3, B:34:0x00f9, B:36:0x0101, B:44:0x0111), top: B:193:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0717 A[Catch: Exception -> 0x072a, TryCatch #0 {Exception -> 0x072a, blocks: (B:3:0x000e, B:6:0x0033, B:8:0x003b, B:10:0x0043, B:12:0x004b, B:14:0x0053, B:15:0x0065, B:22:0x00b6, B:25:0x00c5, B:29:0x00d4, B:45:0x0126, B:48:0x0143, B:50:0x016f, B:51:0x0171, B:54:0x01ab, B:57:0x01da, B:59:0x01e2, B:71:0x0275, B:73:0x029d, B:74:0x029f, B:77:0x02dc, B:80:0x030b, B:83:0x0315, B:84:0x031b, B:96:0x03b2, B:98:0x03d4, B:100:0x0414, B:109:0x042d, B:117:0x049a, B:118:0x049d, B:120:0x04a7, B:122:0x04ae, B:172:0x06c1, B:177:0x06d4, B:178:0x06e3, B:180:0x06ef, B:183:0x06f7, B:184:0x0717, B:186:0x071b, B:188:0x0721, B:124:0x04c7, B:126:0x04cf, B:128:0x04d6, B:129:0x04e4, B:131:0x04ec, B:170:0x06ba, B:135:0x0500, B:139:0x0532, B:140:0x0537, B:142:0x053f, B:144:0x055c, B:148:0x0599, B:156:0x05b3, B:146:0x0566, B:147:0x0580, B:157:0x05b8, B:159:0x05c3, B:161:0x0635, B:160:0x05e5, B:164:0x0644, B:165:0x0660, B:167:0x0676, B:168:0x0693, B:110:0x0472, B:99:0x040d, B:112:0x047b, B:79:0x02f5, B:87:0x036c, B:90:0x037c, B:92:0x0395, B:56:0x01c4, B:62:0x022f, B:65:0x023f, B:67:0x0258, B:30:0x00e9, B:32:0x00f3, B:34:0x00f9, B:36:0x0101, B:44:0x0111), top: B:193:0x000e }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void p(android.content.Context r51, int r52, long r53, long r55, long r57, java.lang.String r59) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1843
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: r4.c.p(android.content.Context, int, long, long, long, java.lang.String):void");
    }

    public static void q(Context context) throws Throwable {
        f.b("[预约用车]----[设置无效数据]：用车时间无效、电价无效");
        if (g5.c.h()) {
            BYDAutoChargingDevice.getInstance(context).setCarPlan(1, 0, 127, 127, 15, 0, 31, 63);
        } else {
            BYDAutoChargingDevice.getInstance(context).setHEVChargePlanStartTime(100, 0, 0, 24, 60);
            BYDAutoChargingDevice.getInstance(context).setHEVChargePlanEndTime(100, 0, 0, 24, 60);
            BYDAutoChargingDevice.getInstance(context).setCarPlan(1, 0, 127, 127, 15, 0, 31, 63);
        }
        BYDAutoChargingDevice.getInstance(context).setElectricityPriceLevel(new byte[24]);
    }

    public static void r(Context context) throws Throwable {
        boolean zB = e.b(context, "KEY_PRICE_FULL");
        byte[] bArr = new byte[48];
        byte[] bArr2 = new byte[24];
        long jD = e.d(context, "KEY_PRICE_START_TIME");
        long jD2 = e.d(context, "KEY_PRICE_END_TIME");
        f.b("[预约用车]----[优惠区间]：" + (jD / 60) + ":" + (jD % 60) + "----" + (jD2 / 60) + ":" + (jD2 % 60) + "----是否全天优惠：" + zB);
        if (jD == jD2 && !zB) {
            f.b("[预约用车]----[容错处理]----计划修改下发需修正");
            zB = true;
        }
        for (int i8 = 0; i8 < 1440; i8 += 30) {
            if (zB) {
                bArr[i8 / 30] = 2;
            } else if (jD < 0 || jD2 < 0) {
                bArr[i8 / 30] = 2;
            } else if (jD < jD2) {
                long j8 = i8;
                if (j8 < jD || j8 >= jD2) {
                    bArr[i8 / 30] = 2;
                } else {
                    bArr[i8 / 30] = 1;
                }
            } else {
                long j9 = i8;
                if ((j9 < jD || i8 > 1440) && (i8 < 0 || j9 >= jD2)) {
                    bArr[i8 / 30] = 2;
                } else {
                    bArr[i8 / 30] = 1;
                }
            }
        }
        for (int i9 = 0; i9 < 48; i9 += 2) {
            bArr2[i9 / 2] = (byte) ((bArr[i9 + 1] << 4) + bArr[i9]);
        }
        f.b("[预约用车]----[优惠电价]：" + Arrays.toString(bArr2));
        BYDAutoChargingDevice.getInstance(context).setElectricityPriceLevel(bArr2);
    }

    public static void s(Context context, int[] iArr, int i8, boolean z7, boolean z8) throws Throwable {
        String str;
        PlanChargeDialog planChargeDialog = f7800a;
        if (planChargeDialog != null && planChargeDialog.isShowing()) {
            f.b("[预约充电]----[充电计划]----[下电弹框]----插枪/下电弹框互斥 type = " + i8 + ",getType = " + f7800a.g());
            if (i8 != 2 || f7800a.g() != 1) {
                return;
            }
            f7800a.dismiss();
            f7800a = null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int i9 = (((iArr[0] * 60) + iArr[1]) - (calendar.get(11) * 60)) - calendar.get(12);
        if (i9 <= 0) {
            i9 += 1440;
        }
        iArr[4] = i9 / 60;
        iArr[5] = i9 % 60;
        f7801b = iArr;
        f7802c = i8;
        f.b("[预约充电]----[充电计划]----[弹框时间]----mInts：" + Arrays.toString(f7801b));
        int chargingGunState = BYDAutoChargingDevice.getInstance(context).getChargingGunState();
        boolean z9 = chargingGunState == 1 || chargingGunState == 3;
        String string = context.getResources().getString(R.string.smarttravel_title);
        String string2 = context.getResources().getString(z9 ? R.string.smarttravel_gun_connected_time : R.string.smarttravel_gun_notconnected_time);
        if (iArr[4] == 0) {
            if (iArr[5] == 1) {
                string2 = context.getResources().getString(z9 ? R.string.gun_plug_dis_start_1_min : R.string.gun_no_plug_dis_start_1_min2);
            } else {
                string2 = context.getResources().getString(z9 ? R.string.smarttravel_gun_connected_time2 : R.string.smarttravel_gun_notconnected_time3);
            }
        } else if (iArr[5] == 0) {
            string2 = context.getResources().getString(z9 ? R.string.smarttravel_gun_connected_time3 : R.string.smarttravel_gun_notconnected_time2);
        }
        String string3 = context.getResources().getString(R.string.smarttravel_charge_now);
        try {
            if (iArr[2] == -1 || iArr[3] == -1) {
                str = String.format(context.getResources().getString(R.string.smarttravel_title_full), r4.b.b(iArr[0]), r4.b.b(iArr[1]));
            } else {
                str = String.format(string, r4.b.b(iArr[0]), r4.b.b(iArr[1]), r4.b.b(iArr[2]), r4.b.b(iArr[3]), (iArr[0] * 60) + iArr[1] > (iArr[2] * 60) + iArr[3] ? context.getResources().getString(R.string.smarttravel_nextday) : "");
            }
            string = str;
            if (iArr[4] != 0) {
                string2 = iArr[5] == 0 ? String.format(string2, Integer.valueOf(iArr[4])) : String.format(string2, Integer.valueOf(iArr[4]), Integer.valueOf(iArr[5]));
            } else if (iArr[5] != 1) {
                string2 = String.format(string2, Integer.valueOf(iArr[5]));
            }
        } catch (Exception e8) {
            f.b(e8.getMessage());
        }
        try {
            PlanChargeDialog planChargeDialog2 = new PlanChargeDialog(context);
            f7800a = planChargeDialog2;
            planChargeDialog2.j(string, string2, string3, f7802c);
            f7800a.k(z7);
            f7800a.i(z8);
            f7800a.show();
        } catch (Exception e9) {
            f.b(e9.getMessage());
        }
    }

    public static void t(Context context, String str) {
        try {
            f.b("[闹钟广播]----[来源]：" + str);
            if ("TYPE_ALARM_EMPTY".equals(str)) {
                return;
            }
            f.b("****************执行开始***********************");
            int powerLevel = BYDAutoBodyworkDevice.getInstance(context).getPowerLevel();
            f.b("[闹钟广播]----[电源档位]：" + powerLevel);
            if ((powerLevel == 0 || powerLevel == 255) && ("TYPE_ALARM_SERVICE".equals(str) || "TYPE_ALARM_START".equals(str) || "TYPE_ALARM_END".equals(str) || "TYPE_ALARM_CLOUD".equals(str) || "TYPE_ALARM_CLOUD_MODE".equals(str))) {
                boolean zB = e.b(context, "KEY_CHARGE_SWITCH");
                boolean zB2 = e.b(context, "KEY_USECAR_SWITCH");
                boolean zB3 = e.b(context, "KEY_MODE");
                f.b("[智慧充电]----[网络报文2]----发送判断" + zB3 + "：" + zB + ":" + zB2);
                if ((zB3 && zB) || (!zB3 && zB2)) {
                    f.b("[智慧充电]----[网络报文2]----发送");
                    BYDAutoOtaDevice.getInstance(context).sendCanNM(0, 0, new a(context, str));
                    return;
                }
            }
            j(context, System.currentTimeMillis(), str);
            k(context, str);
            if ("TYPE_ALARM_DIALOG".equals(str) || "TYPE_ALARM_SWITCH".equals(str) || "TYPE_ALARM_MODE".equals(str) || "TYPE_ALARM_SWITCH_USECAR".equals(str) || "TYPE_ALARM_START_USECAR".equals(str) || "TYPE_ALARM_PRICE_USECAR".equals(str)) {
                d.f(context);
            }
            f.b("****************执行完毕***********************\n");
        } catch (Exception e8) {
            f.b("[智慧充电]----[AlarmReceiver]----异常：" + e8.getMessage());
        }
    }
}
