package p;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.background.systemalarm.SystemAlarmService;

/* JADX INFO: loaded from: classes.dex */
public abstract class g5 {
    public static final /* synthetic */ int a = 0;

    static {
        vc1.d("Alarms");
    }

    public static void a(Context context, rx2 rx2Var, int i) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        String str = tu.n;
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction("ACTION_DELAY_MET");
        tu.d(intent, rx2Var);
        PendingIntent service = PendingIntent.getService(context, i, intent, 603979776);
        if (service == null || alarmManager == null) {
            return;
        }
        vc1 vc1VarC = vc1.c();
        rx2Var.toString();
        vc1VarC.getClass();
        alarmManager.cancel(service);
    }

    public static void b(Context context, WorkDatabase workDatabase, rx2 rx2Var, long j) {
        cy2 cy2Var = (cy2) workDatabase.s();
        hi2 hi2VarA = cy2Var.a(rx2Var);
        int i = 0;
        if (hi2VarA != null) {
            int i2 = hi2VarA.c;
            a(context, rx2Var, i2);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
            String str = tu.n;
            Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
            intent.setAction("ACTION_DELAY_MET");
            tu.d(intent, rx2Var);
            PendingIntent service = PendingIntent.getService(context, i2, intent, 201326592);
            if (alarmManager != null) {
                f5.a(alarmManager, 0, j, service);
                return;
            }
            return;
        }
        oh1 oh1Var = new oh1(workDatabase);
        Object objN = ((WorkDatabase) oh1Var.e).n(new c31(i, oh1Var));
        v03.i(objN, "workDatabase.runInTransa…ANAGER_ID_KEY)\n        })");
        int iIntValue = ((Number) objN).intValue();
        cy2Var.b(new hi2(rx2Var.a, rx2Var.b, iIntValue));
        AlarmManager alarmManager2 = (AlarmManager) context.getSystemService("alarm");
        String str2 = tu.n;
        Intent intent2 = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent2.setAction("ACTION_DELAY_MET");
        tu.d(intent2, rx2Var);
        PendingIntent service2 = PendingIntent.getService(context, iIntValue, intent2, 201326592);
        if (alarmManager2 != null) {
            f5.a(alarmManager2, 0, j, service2);
        }
    }
}
