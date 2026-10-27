package p;

import android.database.Cursor;
import androidx.work.BackoffPolicy;
import androidx.work.NetworkType;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo$State;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class my2 {
    public final i52 a;
    public final f90 b;
    public final ly2 c;
    public final ly2 d;
    public final ly2 e;
    public final ly2 f;
    public final ly2 g;
    public final ly2 h;
    public final ly2 i;
    public final ly2 j;
    public final ly2 k;
    public final ly2 l;
    public final ly2 m;
    public final ly2 n;

    public my2(i52 i52Var) {
        this.a = i52Var;
        this.b = new f90(this, i52Var, 5);
        new ly2(i52Var);
        this.c = new ly2(i52Var, 9);
        this.d = new ly2(i52Var, 10);
        this.e = new ly2(i52Var, 11);
        this.f = new ly2(i52Var, 12);
        this.g = new ly2(i52Var, 13);
        this.h = new ly2(i52Var, 14);
        this.i = new ly2(i52Var, 15);
        this.j = new ly2(i52Var, 0);
        new ly2(i52Var, 1);
        this.k = new ly2(i52Var, 2);
        this.l = new ly2(i52Var, 3);
        this.m = new ly2(i52Var, 4);
        new ly2(i52Var, 5);
        new ly2(i52Var, 6);
        this.n = new ly2(i52Var, 7);
    }

    public final ArrayList a() throws Throwable {
        m52 m52Var;
        int iR;
        int iR2;
        int iR3;
        int iR4;
        int iR5;
        int iR6;
        int iR7;
        int iR8;
        int iR9;
        int iR10;
        int iR11;
        int iR12;
        int iR13;
        int iR14;
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
        m52 m52VarI = m52.i(1, "SELECT * FROM workspec WHERE state=0 ORDER BY last_enqueue_time LIMIT ?");
        m52VarI.h0(1, 200);
        i52 i52Var = this.a;
        i52Var.b();
        Cursor cursorM = i52Var.m(m52VarI, null);
        try {
            iR = f60.r(cursorM, "id");
            iR2 = f60.r(cursorM, "state");
            iR3 = f60.r(cursorM, "worker_class_name");
            iR4 = f60.r(cursorM, "input_merger_class_name");
            iR5 = f60.r(cursorM, "input");
            iR6 = f60.r(cursorM, "output");
            iR7 = f60.r(cursorM, "initial_delay");
            iR8 = f60.r(cursorM, "interval_duration");
            iR9 = f60.r(cursorM, "flex_duration");
            iR10 = f60.r(cursorM, "run_attempt_count");
            iR11 = f60.r(cursorM, "backoff_policy");
            iR12 = f60.r(cursorM, "backoff_delay_duration");
            iR13 = f60.r(cursorM, "last_enqueue_time");
            iR14 = f60.r(cursorM, "minimum_retention_duration");
            m52Var = m52VarI;
        } catch (Throwable th) {
            th = th;
            m52Var = m52VarI;
        }
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
                int i9 = iR;
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
                iR = i9;
                i6 = i8;
            }
            cursorM.close();
            m52Var.q();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            cursorM.close();
            m52Var.q();
            throw th;
        }
    }

    public final ArrayList b(int i) throws Throwable {
        m52 m52Var;
        int iR;
        int iR2;
        int iR3;
        int iR4;
        int iR5;
        int iR6;
        int iR7;
        int iR8;
        int iR9;
        int iR10;
        int iR11;
        int iR12;
        int iR13;
        int iR14;
        int i2;
        boolean z;
        int i3;
        boolean z2;
        int i4;
        boolean z3;
        int i5;
        boolean z4;
        int i6;
        boolean z5;
        m52 m52VarI = m52.i(1, "SELECT * FROM workspec WHERE state=0 AND schedule_requested_at=-1 ORDER BY last_enqueue_time LIMIT (SELECT MAX(?-COUNT(*), 0) FROM workspec WHERE schedule_requested_at<>-1 AND LENGTH(content_uri_triggers)=0 AND state NOT IN (2, 3, 5))");
        m52VarI.h0(1, i);
        i52 i52Var = this.a;
        i52Var.b();
        Cursor cursorM = i52Var.m(m52VarI, null);
        try {
            iR = f60.r(cursorM, "id");
            iR2 = f60.r(cursorM, "state");
            iR3 = f60.r(cursorM, "worker_class_name");
            iR4 = f60.r(cursorM, "input_merger_class_name");
            iR5 = f60.r(cursorM, "input");
            iR6 = f60.r(cursorM, "output");
            iR7 = f60.r(cursorM, "initial_delay");
            iR8 = f60.r(cursorM, "interval_duration");
            iR9 = f60.r(cursorM, "flex_duration");
            iR10 = f60.r(cursorM, "run_attempt_count");
            iR11 = f60.r(cursorM, "backoff_policy");
            iR12 = f60.r(cursorM, "backoff_delay_duration");
            iR13 = f60.r(cursorM, "last_enqueue_time");
            iR14 = f60.r(cursorM, "minimum_retention_duration");
            m52Var = m52VarI;
        } catch (Throwable th) {
            th = th;
            m52Var = m52VarI;
        }
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
            int i7 = iR14;
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
                int i8 = cursorM.getInt(iR10);
                BackoffPolicy backoffPolicyI = p02.i(cursorM.getInt(iR11));
                long j4 = cursorM.getLong(iR12);
                long j5 = cursorM.getLong(iR13);
                int i9 = i7;
                long j6 = cursorM.getLong(i9);
                int i10 = iR;
                int i11 = iR15;
                long j7 = cursorM.getLong(i11);
                iR15 = i11;
                int i12 = iR16;
                if (cursorM.getInt(i12) != 0) {
                    iR16 = i12;
                    i2 = iR17;
                    z = true;
                } else {
                    iR16 = i12;
                    i2 = iR17;
                    z = false;
                }
                OutOfQuotaPolicy outOfQuotaPolicyK = p02.k(cursorM.getInt(i2));
                iR17 = i2;
                int i13 = iR18;
                int i14 = cursorM.getInt(i13);
                iR18 = i13;
                int i15 = iR19;
                int i16 = cursorM.getInt(i15);
                iR19 = i15;
                int i17 = iR20;
                long j8 = cursorM.getLong(i17);
                iR20 = i17;
                int i18 = iR21;
                int i19 = cursorM.getInt(i18);
                iR21 = i18;
                int i20 = iR22;
                int i21 = cursorM.getInt(i20);
                iR22 = i20;
                int i22 = iR23;
                NetworkType networkTypeJ = p02.j(cursorM.getInt(i22));
                iR23 = i22;
                int i23 = iR24;
                if (cursorM.getInt(i23) != 0) {
                    iR24 = i23;
                    i3 = iR25;
                    z2 = true;
                } else {
                    iR24 = i23;
                    i3 = iR25;
                    z2 = false;
                }
                if (cursorM.getInt(i3) != 0) {
                    iR25 = i3;
                    i4 = iR26;
                    z3 = true;
                } else {
                    iR25 = i3;
                    i4 = iR26;
                    z3 = false;
                }
                if (cursorM.getInt(i4) != 0) {
                    iR26 = i4;
                    i5 = iR27;
                    z4 = true;
                } else {
                    iR26 = i4;
                    i5 = iR27;
                    z4 = false;
                }
                if (cursorM.getInt(i5) != 0) {
                    iR27 = i5;
                    i6 = iR28;
                    z5 = true;
                } else {
                    iR27 = i5;
                    i6 = iR28;
                    z5 = false;
                }
                long j9 = cursorM.getLong(i6);
                iR28 = i6;
                int i24 = iR29;
                long j10 = cursorM.getLong(i24);
                iR29 = i24;
                int i25 = iR30;
                iR30 = i25;
                arrayList.add(new ky2(string, workInfo$StateL, string2, string3, w50VarA, w50VarA2, j, j2, j3, new wy(networkTypeJ, z2, z3, z4, z5, j9, j10, p02.b(cursorM.isNull(i25) ? null : cursorM.getBlob(i25))), i8, backoffPolicyI, j4, j5, j6, j7, z, outOfQuotaPolicyK, i14, i16, j8, i19, i21));
                iR = i10;
                i7 = i9;
            }
            cursorM.close();
            m52Var.q();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            cursorM.close();
            m52Var.q();
            throw th;
        }
    }

    public final ArrayList c() throws Throwable {
        m52 m52Var;
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
        m52 m52VarI = m52.i(0, "SELECT * FROM workspec WHERE state=0 AND schedule_requested_at=-1 AND LENGTH(content_uri_triggers)<>0 ORDER BY last_enqueue_time");
        i52 i52Var = this.a;
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
                    int i9 = iR;
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
                    iR = i9;
                    i6 = i8;
                }
                cursorM.close();
                m52Var.q();
                return arrayList;
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

    public final ArrayList d() throws Throwable {
        m52 m52Var;
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
        m52 m52VarI = m52.i(0, "SELECT * FROM workspec WHERE state=1");
        i52 i52Var = this.a;
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
                    int i9 = iR;
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
                    iR = i9;
                    i6 = i8;
                }
                cursorM.close();
                m52Var.q();
                return arrayList;
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

    public final ArrayList e() {
        m52 m52Var;
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
        m52 m52VarI = m52.i(0, "SELECT * FROM workspec WHERE state=0 AND schedule_requested_at<>-1");
        i52 i52Var = this.a;
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
                    int i9 = iR;
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
                    iR = i9;
                    i6 = i8;
                }
                cursorM.close();
                m52Var.q();
                return arrayList;
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

    public final WorkInfo$State f(String str) {
        m52 m52VarI = m52.i(1, "SELECT state FROM workspec WHERE id=?");
        if (str == null) {
            m52VarI.K(1);
        } else {
            m52VarI.x(1, str);
        }
        i52 i52Var = this.a;
        i52Var.b();
        WorkInfo$State workInfo$StateL = null;
        Cursor cursorM = i52Var.m(m52VarI, null);
        try {
            if (cursorM.moveToFirst()) {
                Integer numValueOf = cursorM.isNull(0) ? null : Integer.valueOf(cursorM.getInt(0));
                if (numValueOf != null) {
                    workInfo$StateL = p02.l(numValueOf.intValue());
                }
            }
            return workInfo$StateL;
        } finally {
            cursorM.close();
            m52VarI.q();
        }
    }

    public final ArrayList g(String str) {
        m52 m52VarI = m52.i(1, "SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM workname WHERE name=?)");
        if (str == null) {
            m52VarI.K(1);
        } else {
            m52VarI.x(1, str);
        }
        i52 i52Var = this.a;
        i52Var.b();
        Cursor cursorM = i52Var.m(m52VarI, null);
        try {
            ArrayList arrayList = new ArrayList(cursorM.getCount());
            while (cursorM.moveToNext()) {
                arrayList.add(cursorM.isNull(0) ? null : cursorM.getString(0));
            }
            return arrayList;
        } finally {
            cursorM.close();
            m52VarI.q();
        }
    }

    public final ky2 h(String str) {
        m52 m52Var;
        int iR;
        int iR2;
        int iR3;
        int iR4;
        int iR5;
        int iR6;
        int iR7;
        int iR8;
        int iR9;
        int iR10;
        int iR11;
        int iR12;
        int iR13;
        int iR14;
        ky2 ky2Var;
        boolean z;
        int i;
        boolean z2;
        int i2;
        boolean z3;
        int i3;
        boolean z4;
        int i4;
        boolean z5;
        int i5;
        m52 m52VarI = m52.i(1, "SELECT * FROM workspec WHERE id=?");
        if (str == null) {
            m52VarI.K(1);
        } else {
            m52VarI.x(1, str);
        }
        i52 i52Var = this.a;
        i52Var.b();
        Cursor cursorM = i52Var.m(m52VarI, null);
        try {
            iR = f60.r(cursorM, "id");
            iR2 = f60.r(cursorM, "state");
            iR3 = f60.r(cursorM, "worker_class_name");
            iR4 = f60.r(cursorM, "input_merger_class_name");
            iR5 = f60.r(cursorM, "input");
            iR6 = f60.r(cursorM, "output");
            iR7 = f60.r(cursorM, "initial_delay");
            iR8 = f60.r(cursorM, "interval_duration");
            iR9 = f60.r(cursorM, "flex_duration");
            iR10 = f60.r(cursorM, "run_attempt_count");
            iR11 = f60.r(cursorM, "backoff_policy");
            iR12 = f60.r(cursorM, "backoff_delay_duration");
            iR13 = f60.r(cursorM, "last_enqueue_time");
            iR14 = f60.r(cursorM, "minimum_retention_duration");
            m52Var = m52VarI;
        } catch (Throwable th) {
            th = th;
            m52Var = m52VarI;
        }
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
            if (cursorM.moveToFirst()) {
                String string = cursorM.isNull(iR) ? null : cursorM.getString(iR);
                WorkInfo$State workInfo$StateL = p02.l(cursorM.getInt(iR2));
                String string2 = cursorM.isNull(iR3) ? null : cursorM.getString(iR3);
                String string3 = cursorM.isNull(iR4) ? null : cursorM.getString(iR4);
                w50 w50VarA = w50.a(cursorM.isNull(iR5) ? null : cursorM.getBlob(iR5));
                w50 w50VarA2 = w50.a(cursorM.isNull(iR6) ? null : cursorM.getBlob(iR6));
                long j = cursorM.getLong(iR7);
                long j2 = cursorM.getLong(iR8);
                long j3 = cursorM.getLong(iR9);
                int i6 = cursorM.getInt(iR10);
                BackoffPolicy backoffPolicyI = p02.i(cursorM.getInt(iR11));
                long j4 = cursorM.getLong(iR12);
                long j5 = cursorM.getLong(iR13);
                long j6 = cursorM.getLong(iR14);
                long j7 = cursorM.getLong(iR15);
                if (cursorM.getInt(iR16) != 0) {
                    i = iR17;
                    z = true;
                } else {
                    z = false;
                    i = iR17;
                }
                OutOfQuotaPolicy outOfQuotaPolicyK = p02.k(cursorM.getInt(i));
                int i7 = cursorM.getInt(iR18);
                int i8 = cursorM.getInt(iR19);
                long j8 = cursorM.getLong(iR20);
                int i9 = cursorM.getInt(iR21);
                int i10 = cursorM.getInt(iR22);
                NetworkType networkTypeJ = p02.j(cursorM.getInt(iR23));
                if (cursorM.getInt(iR24) != 0) {
                    i2 = iR25;
                    z2 = true;
                } else {
                    z2 = false;
                    i2 = iR25;
                }
                if (cursorM.getInt(i2) != 0) {
                    i3 = iR26;
                    z3 = true;
                } else {
                    z3 = false;
                    i3 = iR26;
                }
                if (cursorM.getInt(i3) != 0) {
                    i4 = iR27;
                    z4 = true;
                } else {
                    z4 = false;
                    i4 = iR27;
                }
                if (cursorM.getInt(i4) != 0) {
                    i5 = iR28;
                    z5 = true;
                } else {
                    z5 = false;
                    i5 = iR28;
                }
                ky2Var = new ky2(string, workInfo$StateL, string2, string3, w50VarA, w50VarA2, j, j2, j3, new wy(networkTypeJ, z2, z3, z4, z5, cursorM.getLong(i5), cursorM.getLong(iR29), p02.b(cursorM.isNull(iR30) ? null : cursorM.getBlob(iR30))), i6, backoffPolicyI, j4, j5, j6, j7, z, outOfQuotaPolicyK, i7, i8, j8, i9, i10);
            } else {
                ky2Var = null;
            }
            cursorM.close();
            m52Var.q();
            return ky2Var;
        } catch (Throwable th2) {
            th = th2;
            cursorM.close();
            m52Var.q();
            throw th;
        }
    }

    public final void i(String str, long j) {
        i52 i52Var = this.a;
        i52Var.b();
        ly2 ly2Var = this.l;
        th2 th2VarA = ly2Var.a();
        th2VarA.h0(1, j);
        if (str == null) {
            th2VarA.K(2);
        } else {
            th2VarA.x(2, str);
        }
        i52Var.c();
        try {
            th2VarA.F();
            i52Var.o();
        } finally {
            i52Var.k();
            ly2Var.c(th2VarA);
        }
    }

    public final void j(int i, String str) {
        i52 i52Var = this.a;
        i52Var.b();
        ly2 ly2Var = this.k;
        th2 th2VarA = ly2Var.a();
        if (str == null) {
            th2VarA.K(1);
        } else {
            th2VarA.x(1, str);
        }
        th2VarA.h0(2, i);
        i52Var.c();
        try {
            th2VarA.F();
            i52Var.o();
        } finally {
            i52Var.k();
            ly2Var.c(th2VarA);
        }
    }

    public final void k(String str, long j) {
        i52 i52Var = this.a;
        i52Var.b();
        ly2 ly2Var = this.h;
        th2 th2VarA = ly2Var.a();
        th2VarA.h0(1, j);
        if (str == null) {
            th2VarA.K(2);
        } else {
            th2VarA.x(2, str);
        }
        i52Var.c();
        try {
            th2VarA.F();
            i52Var.o();
        } finally {
            i52Var.k();
            ly2Var.c(th2VarA);
        }
    }

    public final void l(String str, w50 w50Var) throws Throwable {
        i52 i52Var = this.a;
        i52Var.b();
        ly2 ly2Var = this.g;
        th2 th2VarA = ly2Var.a();
        byte[] bArrB = w50.b(w50Var);
        if (bArrB == null) {
            th2VarA.K(1);
        } else {
            th2VarA.B0(bArrB, 1);
        }
        if (str == null) {
            th2VarA.K(2);
        } else {
            th2VarA.x(2, str);
        }
        i52Var.c();
        try {
            th2VarA.F();
            i52Var.o();
        } finally {
            i52Var.k();
            ly2Var.c(th2VarA);
        }
    }

    public final void m(WorkInfo$State workInfo$State, String str) {
        i52 i52Var = this.a;
        i52Var.b();
        ly2 ly2Var = this.d;
        th2 th2VarA = ly2Var.a();
        th2VarA.h0(1, p02.r(workInfo$State));
        if (str == null) {
            th2VarA.K(2);
        } else {
            th2VarA.x(2, str);
        }
        i52Var.c();
        try {
            th2VarA.F();
            i52Var.o();
        } finally {
            i52Var.k();
            ly2Var.c(th2VarA);
        }
    }

    public final void n(int i, String str) {
        i52 i52Var = this.a;
        i52Var.b();
        ly2 ly2Var = this.n;
        th2 th2VarA = ly2Var.a();
        th2VarA.h0(1, i);
        if (str == null) {
            th2VarA.K(2);
        } else {
            th2VarA.x(2, str);
        }
        i52Var.c();
        try {
            th2VarA.F();
            i52Var.o();
        } finally {
            i52Var.k();
            ly2Var.c(th2VarA);
        }
    }
}
