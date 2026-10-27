package androidx.work.impl.workers;

import android.content.Context;
import android.database.Cursor;
import androidx.work.BackoffPolicy;
import androidx.work.NetworkType;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo$State;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkDatabase;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import p.ay2;
import p.f60;
import p.i52;
import p.ii2;
import p.ky2;
import p.m52;
import p.my2;
import p.o90;
import p.ob1;
import p.oy2;
import p.p02;
import p.v03;
import p.vc1;
import p.w50;
import p.wx2;
import p.wy;

/* JADX INFO: loaded from: classes.dex */
public final class DiagnosticsWorker extends Worker {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DiagnosticsWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        v03.j(context, "context");
        v03.j(workerParameters, "parameters");
    }

    @Override // androidx.work.Worker
    public final ob1 f() throws Throwable {
        m52 m52Var;
        ii2 ii2Var;
        ay2 ay2Var;
        oy2 oy2Var;
        int i;
        boolean z;
        int i2;
        boolean z2;
        int i3;
        boolean z3;
        int i4;
        boolean z4;
        int i5;
        boolean z5;
        wx2 wx2VarE = wx2.E(this.c);
        WorkDatabase workDatabase = wx2VarE.i;
        v03.i(workDatabase, "workManager.workDatabase");
        my2 my2VarV = workDatabase.v();
        ay2 ay2VarT = workDatabase.t();
        oy2 oy2VarW = workDatabase.w();
        ii2 ii2VarS = workDatabase.s();
        wx2VarE.h.c.getClass();
        long jCurrentTimeMillis = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1L);
        my2VarV.getClass();
        m52 m52VarI = m52.i(1, "SELECT * FROM workspec WHERE last_enqueue_time >= ? AND state IN (2, 3, 5) ORDER BY last_enqueue_time DESC");
        m52VarI.h0(1, jCurrentTimeMillis);
        i52 i52Var = my2VarV.a;
        i52Var.b();
        Cursor cursorM = i52Var.m(m52VarI, null);
        try {
            int iR = f60.r(cursorM, "id");
            int iR2 = f60.r(cursorM, "state");
            int iR3 = f60.r(cursorM, "worker_class_name");
            int iR4 = f60.r(cursorM, "input_merger_class_name");
            int iR5 = f60.r(cursorM, "input");
            int iR6 = f60.r(cursorM, "output");
            int iR7 = f60.r(cursorM, "initial_delay");
            int iR8 = f60.r(cursorM, "interval_duration");
            int iR9 = f60.r(cursorM, "flex_duration");
            int iR10 = f60.r(cursorM, "run_attempt_count");
            int iR11 = f60.r(cursorM, "backoff_policy");
            int iR12 = f60.r(cursorM, "backoff_delay_duration");
            int iR13 = f60.r(cursorM, "last_enqueue_time");
            int iR14 = f60.r(cursorM, "minimum_retention_duration");
            m52Var = m52VarI;
            try {
                int iR15 = f60.r(cursorM, "schedule_requested_at");
                int iR16 = f60.r(cursorM, "run_in_foreground");
                int iR17 = f60.r(cursorM, "out_of_quota_policy");
                int iR18 = f60.r(cursorM, "period_count");
                int iR19 = f60.r(cursorM, "generation");
                int iR20 = f60.r(cursorM, "next_schedule_time_override");
                int iR21 = f60.r(cursorM, "next_schedule_time_override_generation");
                int iR22 = f60.r(cursorM, "stop_reason");
                int iR23 = f60.r(cursorM, "required_network_type");
                int iR24 = f60.r(cursorM, "requires_charging");
                int iR25 = f60.r(cursorM, "requires_device_idle");
                int iR26 = f60.r(cursorM, "requires_battery_not_low");
                int iR27 = f60.r(cursorM, "requires_storage_not_low");
                int iR28 = f60.r(cursorM, "trigger_content_update_delay");
                int iR29 = f60.r(cursorM, "trigger_max_content_delay");
                int iR30 = f60.r(cursorM, "content_uri_triggers");
                int i6 = iR14;
                ArrayList arrayList = new ArrayList(cursorM.getCount());
                while (cursorM.moveToNext()) {
                    String string = cursorM.isNull(iR) ? null : cursorM.getString(iR);
                    WorkInfo$State workInfo$StateL = p02.l(cursorM.getInt(iR2));
                    String string2 = cursorM.isNull(iR3) ? null : cursorM.getString(iR3);
                    String string3 = cursorM.isNull(iR4) ? null : cursorM.getString(iR4);
                    w50 w50VarA = w50.a(cursorM.isNull(iR5) ? null : cursorM.getBlob(iR5));
                    w50 w50VarA2 = w50.a(cursorM.isNull(iR6) ? null : cursorM.getBlob(iR6));
                    long j = cursorM.getLong(iR7);
                    long j2 = cursorM.getLong(iR8);
                    long j3 = cursorM.getLong(iR9);
                    int i7 = cursorM.getInt(iR10);
                    BackoffPolicy backoffPolicyI = p02.i(cursorM.getInt(iR11));
                    long j4 = cursorM.getLong(iR12);
                    long j5 = cursorM.getLong(iR13);
                    int i8 = i6;
                    long j6 = cursorM.getLong(i8);
                    int i9 = iR9;
                    int i10 = iR15;
                    long j7 = cursorM.getLong(i10);
                    iR15 = i10;
                    int i11 = iR16;
                    if (cursorM.getInt(i11) != 0) {
                        iR16 = i11;
                        i = iR17;
                        z = true;
                    } else {
                        iR16 = i11;
                        i = iR17;
                        z = false;
                    }
                    OutOfQuotaPolicy outOfQuotaPolicyK = p02.k(cursorM.getInt(i));
                    iR17 = i;
                    int i12 = iR18;
                    int i13 = cursorM.getInt(i12);
                    iR18 = i12;
                    int i14 = iR19;
                    int i15 = cursorM.getInt(i14);
                    iR19 = i14;
                    int i16 = iR20;
                    long j8 = cursorM.getLong(i16);
                    iR20 = i16;
                    int i17 = iR21;
                    int i18 = cursorM.getInt(i17);
                    iR21 = i17;
                    int i19 = iR22;
                    int i20 = cursorM.getInt(i19);
                    iR22 = i19;
                    int i21 = iR23;
                    NetworkType networkTypeJ = p02.j(cursorM.getInt(i21));
                    iR23 = i21;
                    int i22 = iR24;
                    if (cursorM.getInt(i22) != 0) {
                        iR24 = i22;
                        i2 = iR25;
                        z2 = true;
                    } else {
                        iR24 = i22;
                        i2 = iR25;
                        z2 = false;
                    }
                    if (cursorM.getInt(i2) != 0) {
                        iR25 = i2;
                        i3 = iR26;
                        z3 = true;
                    } else {
                        iR25 = i2;
                        i3 = iR26;
                        z3 = false;
                    }
                    if (cursorM.getInt(i3) != 0) {
                        iR26 = i3;
                        i4 = iR27;
                        z4 = true;
                    } else {
                        iR26 = i3;
                        i4 = iR27;
                        z4 = false;
                    }
                    if (cursorM.getInt(i4) != 0) {
                        iR27 = i4;
                        i5 = iR28;
                        z5 = true;
                    } else {
                        iR27 = i4;
                        i5 = iR28;
                        z5 = false;
                    }
                    long j9 = cursorM.getLong(i5);
                    iR28 = i5;
                    int i23 = iR29;
                    long j10 = cursorM.getLong(i23);
                    iR29 = i23;
                    int i24 = iR30;
                    iR30 = i24;
                    arrayList.add(new ky2(string, workInfo$StateL, string2, string3, w50VarA, w50VarA2, j, j2, j3, new wy(networkTypeJ, z2, z3, z4, z5, j9, j10, p02.b(cursorM.isNull(i24) ? null : cursorM.getBlob(i24))), i7, backoffPolicyI, j4, j5, j6, j7, z, outOfQuotaPolicyK, i13, i15, j8, i18, i20));
                    iR9 = i9;
                    i6 = i8;
                }
                cursorM.close();
                m52Var.q();
                ArrayList arrayListD = my2VarV.d();
                ArrayList arrayListA = my2VarV.a();
                if (!arrayList.isEmpty()) {
                    vc1 vc1VarC = vc1.c();
                    int i25 = o90.a;
                    vc1VarC.getClass();
                    vc1 vc1VarC2 = vc1.c();
                    ii2Var = ii2VarS;
                    ay2Var = ay2VarT;
                    oy2Var = oy2VarW;
                    o90.a(ay2Var, oy2Var, ii2Var, arrayList);
                    vc1VarC2.getClass();
                } else {
                    ii2Var = ii2VarS;
                    ay2Var = ay2VarT;
                    oy2Var = oy2VarW;
                }
                if (!arrayListD.isEmpty()) {
                    vc1 vc1VarC3 = vc1.c();
                    int i26 = o90.a;
                    vc1VarC3.getClass();
                    vc1 vc1VarC4 = vc1.c();
                    o90.a(ay2Var, oy2Var, ii2Var, arrayListD);
                    vc1VarC4.getClass();
                }
                if (!arrayListA.isEmpty()) {
                    vc1 vc1VarC5 = vc1.c();
                    int i27 = o90.a;
                    vc1VarC5.getClass();
                    vc1 vc1VarC6 = vc1.c();
                    o90.a(ay2Var, oy2Var, ii2Var, arrayListA);
                    vc1VarC6.getClass();
                }
                return new ob1(w50.c);
            } catch (Throwable th) {
                th = th;
                cursorM.close();
                m52Var.q();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            m52Var = m52VarI;
        }
    }
}
