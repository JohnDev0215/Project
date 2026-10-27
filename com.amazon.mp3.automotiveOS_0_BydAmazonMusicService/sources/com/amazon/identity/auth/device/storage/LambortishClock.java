package com.amazon.identity.auth.device.storage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.amazon.identity.auth.device.f8;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.v7;
import com.amazon.identity.auth.device.va;
import com.amazon.identity.auth.device.w5;
import com.amazon.identity.auth.device.xa;
import java.util.Date;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class LambortishClock {
    public static final String e = "com.amazon.identity.auth.device.storage.LambortishClock";
    public static LambortishClock f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f553a;
    public final w5 b;
    public Long c;
    public Long d;

    /* JADX INFO: compiled from: DCP */
    public static class ChangeTimestampsBroadcastReceiver extends BroadcastReceiver {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int f554a = 0;

        /* JADX INFO: compiled from: DCP */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Intent f555a;
            public final /* synthetic */ Context b;

            public a(ChangeTimestampsBroadcastReceiver changeTimestampsBroadcastReceiver, Intent intent, Context context) {
                this.f555a = intent;
                this.b = context;
            }

            @Override // java.lang.Runnable
            public void run() {
                String action = this.f555a.getAction();
                if (!"android.intent.action.TIME_SET".equals(action)) {
                    ga.b(LambortishClock.e, "Cannot Handle intent with action %s", action);
                    return;
                }
                t5 t5VarA = t5.a(this.b);
                int i = ChangeTimestampsBroadcastReceiver.f554a;
                if (!((v7) t5VarA.getSystemService("dcp_data_storage_factory")).b()) {
                    ga.a(LambortishClock.e);
                    return;
                }
                LambortishClock lambortishClockA = LambortishClock.a(t5VarA);
                synchronized (lambortishClockA) {
                    Date dateA = lambortishClockA.a();
                    String str = LambortishClock.e;
                    lambortishClockA.b.getClass();
                    ga.c(str, "Users clock moved. System time is %s and timestamp is %s", Long.toString(System.currentTimeMillis()), Long.toString(dateA.getTime()));
                }
            }
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.i(ga.a(LambortishClock.e), "Broadcast receiver is notified: ChangeTimestampsBroadcastReceiver");
            va.a(new a(this, intent, context));
        }
    }

    public LambortishClock(Context context) {
        t5 t5VarA = t5.a(context);
        this.f553a = t5VarA;
        this.b = (w5) t5VarA.getSystemService("dcp_system");
    }

    public static synchronized LambortishClock a(Context context) {
        if (f == null || xa.a()) {
            f = new LambortishClock(context.getApplicationContext());
        }
        return f;
    }

    public synchronized boolean a(Date date) {
        if (date == null) {
            return false;
        }
        long time = date.getTime();
        f8 f8Var = new f8(this.f553a, "Lambortish_Clock_Store", 0);
        if (time <= a(f8Var)) {
            return false;
        }
        ga.a(e);
        this.c = Long.valueOf(time);
        return f8Var.a("greatest_timestamp_ms_seen_key", time);
    }

    public final long a(f8 f8Var) {
        if (this.c == null) {
            this.c = Long.valueOf(f8Var.f313a.getLong("greatest_timestamp_ms_seen_key", 0L));
        }
        return this.c.longValue();
    }

    public synchronized Date a() {
        long jLongValue;
        f8 f8Var = new f8(this.f553a, "Lambortish_Clock_Store", 0);
        long jA = a(f8Var);
        this.b.getClass();
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.d == null) {
            this.d = Long.valueOf(f8Var.f313a.getLong("cur_delta_ms_key", 0L));
        }
        jLongValue = this.d.longValue() + jCurrentTimeMillis;
        if (jLongValue <= jA) {
            jLongValue = 100 + jA;
            long j = jLongValue - jCurrentTimeMillis;
            this.d = Long.valueOf(j);
            f8Var.a("cur_delta_ms_key", j);
        }
        this.c = Long.valueOf(jLongValue);
        f8Var.a("greatest_timestamp_ms_seen_key", jLongValue);
        return new Date(jLongValue);
    }
}
