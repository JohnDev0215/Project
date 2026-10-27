package p;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteAccessPermException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteDiskIOException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteTableLockedException;
import android.os.Build;
import android.text.TextUtils;
import androidx.work.impl.utils.ForceStopRunnable$BroadcastReceiver;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class dw0 implements Runnable {
    public static final String m = vc1.d("ForceStopRunnable");
    public static final long n = TimeUnit.DAYS.toMillis(3650);
    public final Context c;
    public final wx2 e;
    public final p01 k;
    public int l = 0;

    public dw0(Context context, wx2 wx2Var) {
        this.c = context.getApplicationContext();
        this.e = wx2Var;
        this.k = wx2Var.m;
    }

    public static void b(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        int i = Build.VERSION.SDK_INT >= 31 ? 167772160 : 134217728;
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, (Class<?>) ForceStopRunnable$BroadcastReceiver.class));
        intent.setAction("ACTION_FORCE_STOP_RESCHEDULE");
        PendingIntent broadcast = PendingIntent.getBroadcast(context, -1, intent, i);
        long jCurrentTimeMillis = System.currentTimeMillis() + n;
        if (alarmManager != null) {
            alarmManager.setExact(0, jCurrentTimeMillis, broadcast);
        }
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:138:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0206  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a() {
        /*
            Method dump skipped, instruction units count: 606
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.dw0.a():void");
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str = m;
        wx2 wx2Var = this.e;
        try {
            sw swVar = wx2Var.h;
            swVar.getClass();
            boolean zIsEmpty = TextUtils.isEmpty(null);
            Context context = this.c;
            if (zIsEmpty) {
                vc1.c().getClass();
            } else {
                boolean zA = nx1.a(context, swVar);
                vc1.c().getClass();
                if (!zA) {
                    return;
                }
            }
            while (true) {
                try {
                    ln1.r(context);
                    vc1.c().getClass();
                    try {
                        a();
                        return;
                    } catch (SQLiteAccessPermException | SQLiteCantOpenDatabaseException | SQLiteConstraintException | SQLiteDatabaseCorruptException | SQLiteDatabaseLockedException | SQLiteDiskIOException | SQLiteTableLockedException e) {
                        int i = this.l + 1;
                        this.l = i;
                        if (i >= 3) {
                            String str2 = zr2.a(context) ? "The file system on the device is in a bad state. WorkManager cannot access the app's internal data store." : "WorkManager can't be accessed from direct boot, because credential encrypted storage isn't accessible.\nDon't access or initialise WorkManager from directAware components. See https://developer.android.com/training/articles/direct-boot";
                            vc1.c().b(str, str2, e);
                            IllegalStateException illegalStateException = new IllegalStateException(str2, e);
                            wx2Var.h.getClass();
                            throw illegalStateException;
                        }
                        vc1.c().getClass();
                        try {
                            Thread.sleep(((long) this.l) * 300);
                        } catch (InterruptedException unused) {
                        }
                    }
                } catch (SQLiteException e2) {
                    vc1.c().a(str, "Unexpected SQLite exception during migrations");
                    IllegalStateException illegalStateException2 = new IllegalStateException("Unexpected SQLite exception during migrations", e2);
                    wx2Var.h.getClass();
                    throw illegalStateException2;
                }
            }
        } finally {
            wx2Var.F();
        }
    }
}
