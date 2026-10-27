package p;

import android.os.Build;
import androidx.work.BackoffPolicy;
import androidx.work.NetworkType;
import androidx.work.OutOfQuotaPolicy;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes.dex */
public final class f90 extends ee0 {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f90(Object obj, i52 i52Var, int i) {
        super(i52Var);
        this.d = i;
        this.e = obj;
        v03.j(i52Var, "database");
    }

    @Override // androidx.room.a
    public final String b() {
        switch (this.d) {
            case 0:
                return "INSERT OR IGNORE INTO `Dependency` (`work_spec_id`,`prerequisite_id`) VALUES (?,?)";
            case 1:
                return "INSERT OR REPLACE INTO `Preference` (`key`,`long_value`) VALUES (?,?)";
            case 2:
                return "INSERT OR REPLACE INTO `SystemIdInfo` (`work_spec_id`,`generation`,`system_id`) VALUES (?,?,?)";
            case 3:
                return "INSERT OR IGNORE INTO `WorkName` (`name`,`work_spec_id`) VALUES (?,?)";
            case 4:
                return "INSERT OR REPLACE INTO `WorkProgress` (`work_spec_id`,`progress`) VALUES (?,?)";
            case 5:
                return "INSERT OR IGNORE INTO `WorkSpec` (`id`,`state`,`worker_class_name`,`input_merger_class_name`,`input`,`output`,`initial_delay`,`interval_duration`,`flex_duration`,`run_attempt_count`,`backoff_policy`,`backoff_delay_duration`,`last_enqueue_time`,`minimum_retention_duration`,`schedule_requested_at`,`run_in_foreground`,`out_of_quota_policy`,`period_count`,`generation`,`next_schedule_time_override`,`next_schedule_time_override_generation`,`stop_reason`,`required_network_type`,`requires_charging`,`requires_device_idle`,`requires_battery_not_low`,`requires_storage_not_low`,`trigger_content_update_delay`,`trigger_max_content_delay`,`content_uri_triggers`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            case 6:
                return "INSERT OR IGNORE INTO `WorkTag` (`tag`,`work_spec_id`) VALUES (?,?)";
            case 7:
                return "INSERT OR REPLACE INTO `EventSequenceNumbers` (`eventName`,`sequenceId`,`sequenceNumberNext`) VALUES (?,?,?)";
            case 8:
                return "INSERT OR ABORT INTO `Events` (`id`,`authenticated`,`eventName`,`sequenceId`,`sequenceNumber`,`fragments`,`owner`,`deviceId`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
            default:
                return "INSERT OR REPLACE INTO `RateLimitedEvents` (`eventName`,`count`,`timestamp`) VALUES (?,?,?)";
        }
    }

    @Override // p.ee0
    public final void d(th2 th2Var, Object obj) throws Throwable {
        int i;
        int i2;
        byte[] byteArray;
        int i3 = 4;
        switch (this.d) {
            case 0:
                c90 c90Var = (c90) obj;
                String str = c90Var.a;
                if (str == null) {
                    th2Var.K(1);
                } else {
                    th2Var.x(1, str);
                }
                String str2 = c90Var.b;
                if (str2 == null) {
                    th2Var.K(2);
                    return;
                } else {
                    th2Var.x(2, str2);
                    return;
                }
            case 1:
                vw1 vw1Var = (vw1) obj;
                String str3 = vw1Var.a;
                if (str3 == null) {
                    th2Var.K(1);
                } else {
                    th2Var.x(1, str3);
                }
                Long l = vw1Var.b;
                if (l == null) {
                    th2Var.K(2);
                    return;
                } else {
                    th2Var.h0(2, l.longValue());
                    return;
                }
            case 2:
                String str4 = ((hi2) obj).a;
                if (str4 == null) {
                    th2Var.K(1);
                } else {
                    th2Var.x(1, str4);
                }
                th2Var.h0(2, r2.b);
                th2Var.h0(3, r2.c);
                return;
            case 3:
                th2Var.K(1);
                th2Var.K(2);
                return;
            case 4:
                u4.u(obj);
                throw null;
            case 5:
                ky2 ky2Var = (ky2) obj;
                String str5 = ky2Var.a;
                if (str5 == null) {
                    th2Var.K(1);
                } else {
                    th2Var.x(1, str5);
                }
                th2Var.h0(2, p02.r(ky2Var.b));
                String str6 = ky2Var.c;
                if (str6 == null) {
                    th2Var.K(3);
                } else {
                    th2Var.x(3, str6);
                }
                String str7 = ky2Var.d;
                if (str7 == null) {
                    th2Var.K(4);
                } else {
                    th2Var.x(4, str7);
                }
                byte[] bArrB = w50.b(ky2Var.e);
                if (bArrB == null) {
                    th2Var.K(5);
                } else {
                    th2Var.B0(bArrB, 5);
                }
                byte[] bArrB2 = w50.b(ky2Var.f);
                if (bArrB2 == null) {
                    th2Var.K(6);
                } else {
                    th2Var.B0(bArrB2, 6);
                }
                th2Var.h0(7, ky2Var.g);
                th2Var.h0(8, ky2Var.h);
                th2Var.h0(9, ky2Var.i);
                th2Var.h0(10, ky2Var.k);
                BackoffPolicy backoffPolicy = ky2Var.l;
                v03.j(backoffPolicy, "backoffPolicy");
                int iOrdinal = backoffPolicy.ordinal();
                if (iOrdinal == 0) {
                    i = 0;
                } else {
                    if (iOrdinal != 1) {
                        throw new NoWhenBranchMatchedException();
                    }
                    i = 1;
                }
                th2Var.h0(11, i);
                th2Var.h0(12, ky2Var.m);
                th2Var.h0(13, ky2Var.n);
                th2Var.h0(14, ky2Var.o);
                th2Var.h0(15, ky2Var.f145p);
                th2Var.h0(16, ky2Var.q ? 1L : 0L);
                OutOfQuotaPolicy outOfQuotaPolicy = ky2Var.r;
                v03.j(outOfQuotaPolicy, "policy");
                int iOrdinal2 = outOfQuotaPolicy.ordinal();
                if (iOrdinal2 == 0) {
                    i2 = 0;
                } else {
                    if (iOrdinal2 != 1) {
                        throw new NoWhenBranchMatchedException();
                    }
                    i2 = 1;
                }
                th2Var.h0(17, i2);
                th2Var.h0(18, ky2Var.s);
                th2Var.h0(19, ky2Var.t);
                th2Var.h0(20, ky2Var.u);
                th2Var.h0(21, ky2Var.v);
                th2Var.h0(22, ky2Var.w);
                wy wyVar = ky2Var.j;
                if (wyVar == null) {
                    th2Var.K(23);
                    th2Var.K(24);
                    th2Var.K(25);
                    th2Var.K(26);
                    th2Var.K(27);
                    th2Var.K(28);
                    th2Var.K(29);
                    th2Var.K(30);
                    return;
                }
                NetworkType networkType = wyVar.a;
                v03.j(networkType, "networkType");
                int iOrdinal3 = networkType.ordinal();
                if (iOrdinal3 == 0) {
                    i3 = 0;
                } else if (iOrdinal3 == 1) {
                    i3 = 1;
                } else if (iOrdinal3 == 2) {
                    i3 = 2;
                } else if (iOrdinal3 == 3) {
                    i3 = 3;
                } else if (iOrdinal3 != 4) {
                    if (Build.VERSION.SDK_INT < 30 || networkType != NetworkType.n) {
                        throw new IllegalArgumentException("Could not convert " + networkType + " to int");
                    }
                    i3 = 5;
                }
                th2Var.h0(23, i3);
                th2Var.h0(24, wyVar.b ? 1L : 0L);
                th2Var.h0(25, wyVar.c ? 1L : 0L);
                th2Var.h0(26, wyVar.d ? 1L : 0L);
                th2Var.h0(27, wyVar.e ? 1L : 0L);
                th2Var.h0(28, wyVar.f);
                th2Var.h0(29, wyVar.g);
                Set<vy> set = wyVar.h;
                v03.j(set, "triggers");
                if (set.isEmpty()) {
                    byteArray = new byte[0];
                } else {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                        try {
                            objectOutputStream.writeInt(set.size());
                            for (vy vyVar : set) {
                                objectOutputStream.writeUTF(vyVar.a.toString());
                                objectOutputStream.writeBoolean(vyVar.b);
                                break;
                            }
                            ux2.e(objectOutputStream, null);
                            ux2.e(byteArrayOutputStream, null);
                            byteArray = byteArrayOutputStream.toByteArray();
                            v03.i(byteArray, "outputStream.toByteArray()");
                        } finally {
                        }
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            ux2.e(byteArrayOutputStream, th);
                            throw th2;
                        }
                    }
                }
                th2Var.B0(byteArray, 30);
                return;
            case 6:
                ny2 ny2Var = (ny2) obj;
                String str8 = ny2Var.a;
                if (str8 == null) {
                    th2Var.K(1);
                } else {
                    th2Var.x(1, str8);
                }
                String str9 = ny2Var.b;
                if (str9 == null) {
                    th2Var.K(2);
                    return;
                } else {
                    th2Var.x(2, str9);
                    return;
                }
            case 7:
                ak0 ak0Var = (ak0) obj;
                String str10 = ak0Var.a;
                if (str10 == null) {
                    th2Var.K(1);
                } else {
                    th2Var.x(1, str10);
                }
                byte[] bArr = ak0Var.b;
                if (bArr == null) {
                    th2Var.K(2);
                } else {
                    th2Var.B0(bArr, 2);
                }
                th2Var.h0(3, ak0Var.c);
                return;
            case 8:
                fi0 fi0Var = (fi0) obj;
                th2Var.h0(1, fi0Var.a);
                Boolean bool = fi0Var.b;
                if ((bool == null ? null : Integer.valueOf(bool.booleanValue() ? 1 : 0)) == null) {
                    th2Var.K(2);
                } else {
                    th2Var.h0(2, r8.intValue());
                }
                String str11 = fi0Var.c;
                if (str11 == null) {
                    th2Var.K(3);
                } else {
                    th2Var.x(3, str11);
                }
                byte[] bArr2 = fi0Var.d;
                if (bArr2 == null) {
                    th2Var.K(4);
                } else {
                    th2Var.B0(bArr2, 4);
                }
                th2Var.h0(5, fi0Var.e);
                byte[] bArr3 = fi0Var.f;
                if (bArr3 == null) {
                    th2Var.K(6);
                } else {
                    th2Var.B0(bArr3, 6);
                }
                String str12 = fi0Var.g;
                if (str12 == null) {
                    th2Var.K(7);
                } else {
                    th2Var.x(7, str12);
                }
                String str13 = fi0Var.h;
                if (str13 == null) {
                    th2Var.K(8);
                    return;
                } else {
                    th2Var.x(8, str13);
                    return;
                }
            default:
                d02 d02Var = (d02) obj;
                String str14 = d02Var.a;
                if (str14 == null) {
                    th2Var.K(1);
                } else {
                    th2Var.x(1, str14);
                }
                th2Var.h0(2, d02Var.b);
                th2Var.h0(3, d02Var.c);
                return;
        }
    }
}
