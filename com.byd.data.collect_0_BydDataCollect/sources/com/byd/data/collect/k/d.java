package com.byd.data.collect.k;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.byd.can.CanNativeHelper;
import com.byd.data.collect.k.b;
import com.byd.data.collect.server.DataCollectService;
import com.byd.data.collect.server.i;
import com.byd.data.collect.subscribe.present.BasePresent;
import com.byd.data.collect.subscribe.present.ExtensionPresent;
import com.byd.data.collect.subscribe.present.NsPresenter;
import com.byd.data.common.d.a;
import com.byd.data.common.entiry.extension.ExtensionAlarm;
import com.byd.data.common.entiry.gson.AlarmData;
import com.byd.data.common.entiry.gson.DataCollectBean;
import com.byd.data.common.entiry.gson.DataEntity;
import com.byd.data.common.entiry.gson.DriverMotorData;
import com.byd.data.common.entiry.gson.VehicleData;
import com.byd.data.common.i.f;
import com.byd.data.common.present.cloud.CloudCanPresent;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: compiled from: ParseCanData.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements e, CanNativeHelper.a, com.byd.data.common.c.c.a, com.byd.data.common.c.c.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile d f3203a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3204b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f3205c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private DataCollectBean f3206d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private VehicleData f3207e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private DriverMotorData f3208f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private ExtensionAlarm f3209g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private NsPresenter f3210h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private ExtensionPresent f3211i;
    private com.byd.data.collect.k.c j;
    private volatile CanNativeHelper k;
    private transient byte l;
    private transient byte m;
    private boolean n;
    private volatile TreeMap<Short, byte[]> o;
    private com.byd.data.common.c.c.c p;
    private com.byd.bms.a.a q;
    private com.byd.data.common.a r;
    private volatile boolean s;
    private b.e t;
    private com.byd.data.collect.k.b<b.C0061b> u;
    private b.d v;
    private b.d w;

    /* JADX INFO: compiled from: ParseCanData.java */
    class a implements b.e {
        a() {
        }

        @Override // com.byd.data.collect.k.b.e
        public b.C0061b a(int i2, byte[] bArr, int i3, boolean z) {
            b.C0061b c0061b = new b.C0061b();
            c0061b.f3189a = (short) (((bArr[1] & 255) << 8) | (bArr[0] & 255));
            c0061b.f3190b = bArr[2];
            c0061b.f3191c = d.this.R(bArr);
            c0061b.f3192d = z;
            c0061b.f3193e = i2;
            c0061b.f3194f = bArr;
            c0061b.f3195g = i3;
            return c0061b;
        }
    }

    /* JADX INFO: compiled from: ParseCanData.java */
    class b implements b.d {
        b() {
        }

        @Override // com.byd.data.collect.k.b.d
        public void a(b.C0061b c0061b) {
            if (c0061b == null || d.this.p == null || c0061b.f3192d || com.byd.data.common.server.b.e().s()) {
                return;
            }
            d.this.p.E(c0061b.f3189a, c0061b.f3190b, c0061b.f3191c);
        }
    }

    /* JADX INFO: compiled from: ParseCanData.java */
    class c implements b.d {
        c() {
        }

        @Override // com.byd.data.collect.k.b.d
        public void a(b.C0061b c0061b) {
            if (c0061b == null) {
                return;
            }
            if (d.this.o != null && !c0061b.f3192d) {
                d.this.o.put(Short.valueOf(c0061b.f3189a), c0061b.f3191c);
            }
            d dVar = d.this;
            if (dVar.X(c0061b.f3189a, c0061b.f3190b, dVar.f3206d.c())) {
                return;
            }
            if (c0061b.f3192d) {
                d.this.j.c(c0061b.f3193e, c0061b.f3194f, c0061b.f3195g);
            } else if (c0061b.f3194f.length > 3) {
                d.this.L(c0061b.f3189a, c0061b.f3190b, c0061b.f3191c);
            }
        }
    }

    private d(Context context) {
        String str = "ParseCanData" + com.byd.data.common.i.b.f3423a;
        this.f3204b = str;
        this.n = true;
        this.t = new a();
        this.u = new com.byd.data.collect.k.b<>();
        this.v = new b();
        this.w = new c();
        this.f3205c = context;
        f.g(str, "construct hashCode =" + hashCode() + ", version =1.17.1-202507021749");
        U();
    }

    private void M(boolean z) {
        Context context = this.f3205c;
        if (!(context instanceof DataCollectService) || this.n == z) {
            return;
        }
        this.n = z;
        ((DataCollectService) context).a(z);
        f.b(this.f3204b, "checkPowerChange isSaveData =" + z);
    }

    private void N(short s, byte b2, byte[] bArr, boolean z) {
        boolean zA = this.f3206d.a();
        DriverMotorData driverMotorData = this.f3208f;
        if (s == driverMotorData.driverMotorNum.canId) {
            this.f3210h.z0(bArr);
            this.f3211i.w(bArr, s);
            return;
        }
        List<DataEntity> list = driverMotorData.driverMotorTemperature;
        a.c cVar = a.c.FRONT;
        if (s == list.get(cVar.ordinal()).canId) {
            if (!z) {
                this.f3210h.p0(bArr, this.f3208f.driverMotorControlTemp, cVar);
                this.f3210h.p0(bArr, this.f3208f.driverMotorTemperature, cVar);
                return;
            }
            if (zA || this.f3206d.b()) {
                if (!this.f3206d.b()) {
                    NsPresenter nsPresenter = this.f3210h;
                    List<DataEntity> list2 = this.f3208f.driverMotorControlTemp;
                    a.c cVar2 = a.c.DUAL;
                    nsPresenter.p0(bArr, list2, cVar2);
                    this.f3210h.p0(bArr, this.f3208f.driverMotorTemperature, cVar2);
                } else if (b2 == 6) {
                    NsPresenter nsPresenter2 = this.f3210h;
                    List<DataEntity> list3 = this.f3208f.driverMotorControlTemp;
                    a.c cVar3 = a.c.DUAL;
                    nsPresenter2.p0(bArr, list3, cVar3);
                    this.f3210h.p0(bArr, this.f3208f.driverMotorTemperature, cVar3);
                }
                if (zA) {
                    this.f3210h.f0(bArr);
                }
                if (s == 1124) {
                    if (b2 == 10 || b2 == 2) {
                        this.f3211i.t(s, b2, bArr, true);
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        List<DataEntity> list4 = this.f3208f.driverMotorControlTemp;
        a.c cVar4 = a.c.REAR;
        if (list4.get(cVar4.ordinal()).canId == s && !zA) {
            this.f3210h.p0(bArr, this.f3208f.driverMotorTemperature, cVar4);
            this.f3210h.p0(bArr, this.f3208f.driverMotorControlTemp, cVar4);
            return;
        }
        if (s == this.f3208f.driverMotorSpeed.get(cVar.ordinal()).canId) {
            this.f3210h.q0(bArr, false);
            this.f3211i.w(bArr, s);
            return;
        }
        if (s == this.f3208f.driverMotorSpeed.get(cVar4.ordinal()).canId) {
            this.f3210h.q0(bArr, true);
            return;
        }
        if (s == 594) {
            if (z) {
                return;
            }
            this.f3210h.f0(bArr);
            return;
        }
        DriverMotorData driverMotorData2 = this.f3208f;
        if (s == driverMotorData2.motorMCUGeneratrixVolt.canId) {
            if ((!zA) && z) {
                this.f3210h.f0(bArr);
            }
        } else {
            if (driverMotorData2.motorMCUGeneratrixCurrent.get(cVar.ordinal()).canId != s) {
                O(s, b2, bArr, z);
                return;
            }
            DataEntity dataEntity = this.f3208f.motorMCUGeneratrixCurrent.get(cVar.ordinal());
            DataEntity dataEntity2 = this.f3208f.motorMCUGeneratrixCurrent.get(cVar4.ordinal());
            this.f3210h.s0(this.f3210h.h(bArr, dataEntity), dataEntity, this.f3210h.h(bArr, dataEntity2), dataEntity2);
        }
    }

    private void O(short s, byte b2, byte[] bArr, boolean z) {
        DataCollectBean dataCollectBean = this.f3206d;
        AlarmData alarmData = dataCollectBean.alarmData;
        if (s == dataCollectBean.engineData.engineStatus.canId) {
            this.f3210h.t0(bArr, z);
            return;
        }
        if (s == dataCollectBean.extremeData.tempSerialNub.canId) {
            this.f3210h.t((byte) (bArr[0] & 255), bArr);
            return;
        }
        if (s == alarmData.tempDifferenceAlarm.canId) {
            this.f3210h.b0(bArr, z);
            this.f3211i.p(this.f3209g.thermalRunaway.a(), (bArr[this.f3209g.thermalRunaway.e()] & 3) == 2);
            if (z) {
                return;
            }
            this.f3211i.w(bArr, s);
            return;
        }
        if (s == alarmData.highTempAlarm.canId) {
            this.f3210h.Z(bArr, z);
            this.f3211i.p(this.f3209g.powerSystemFault.a(), (bArr[this.f3209g.powerSystemFault.e()] & 3) > 0);
            if (z) {
                this.f3211i.w(bArr, s);
                return;
            }
            return;
        }
        if (s == alarmData.driverMotorTempAlarm.canId) {
            this.f3210h.Y(bArr, z);
            if (z) {
                return;
            }
            this.f3211i.w(bArr, s);
            return;
        }
        if (s != alarmData.dcTempAlarm.canId) {
            Q(s, b2, bArr, z);
        } else {
            this.f3210h.a0(bArr);
            this.f3211i.w(bArr, s);
        }
    }

    private void P(short s, byte b2, byte[] bArr, boolean z) {
        VehicleData vehicleData = this.f3207e;
        if (s == vehicleData.totalVoltage.canId) {
            this.f3210h.x0(bArr);
            return;
        }
        if (s == vehicleData.totalCurrent.canId) {
            this.f3210h.w0(bArr, this.m, z);
            if (z) {
                return;
            }
            this.f3211i.w(bArr, s);
            return;
        }
        DataEntity dataEntity = vehicleData.dcState;
        boolean z2 = true;
        if (s == dataEntity.canId) {
            byte bE = this.f3210h.e(bArr, dataEntity);
            if (z) {
                this.f3210h.j0(bE);
            }
            this.f3210h.r(bE != 0 ? 3 == bE ? 254 : 1 : 2, this.f3207e.dcState, a.e.VEHICLE);
            if (z) {
                return;
            }
            this.f3211i.w(bArr, s);
            return;
        }
        if (s == 578) {
            if (z) {
                this.f3210h.B0(s, bArr, true);
                return;
            }
            return;
        }
        List<DataEntity> list = vehicleData.insulationResistance;
        a.c cVar = a.c.FRONT;
        if (s == list.get(cVar.ordinal()).canId) {
            byte b3 = (byte) (bArr[this.f3209g.leakageFault.e()] & 3);
            NsPresenter nsPresenter = this.f3210h;
            nsPresenter.v0(s, nsPresenter.h(bArr, this.f3207e.insulationResistance.get(cVar.ordinal())));
            ExtensionPresent extensionPresent = this.f3211i;
            byte bA = this.f3209g.leakageFault.a();
            if (b3 != 1 && b3 != 2) {
                z2 = false;
            }
            extensionPresent.p(bA, z2);
            if (z) {
                return;
            }
            this.f3211i.w(bArr, s);
            return;
        }
        if (s == this.f3207e.insulationResistance.get(a.c.REAR.ordinal()).canId) {
            this.f3210h.y0(bArr, s, z);
            if (z) {
                return;
            }
            this.f3211i.w(bArr, s);
            return;
        }
        DataEntity dataEntity2 = this.f3207e.brakePedalState;
        if (s != dataEntity2.canId) {
            if (85 == s) {
                this.f3210h.o0((byte) (((bArr[0] & 255) >> 5) & 1));
                return;
            } else {
                N(s, b2, bArr, z);
                return;
            }
        }
        byte bE2 = this.f3210h.e(bArr, dataEntity2);
        this.f3210h.r(bE2 > 0 ? 101 : 0, this.f3207e.brakePedalState, a.e.VEHICLE);
        if (z) {
            this.f3210h.h0(this.f3207e.brakePedalState.a(), bE2, 1);
        }
        this.f3211i.w(bArr, s);
    }

    private void Q(short s, byte b2, byte[] bArr, boolean z) {
        com.byd.data.collect.k.c cVar = this.j;
        if (cVar != null && 612 == s) {
            cVar.d((bArr[1] & 255) >> 6);
            return;
        }
        ExtensionAlarm extensionAlarm = this.f3209g;
        DataEntity dataEntity = extensionAlarm.chargeStop;
        if (s == dataEntity.canId) {
            byte b3 = (byte) (bArr[dataEntity.e()] & 255);
            byte b4 = (byte) (b3 & 3);
            this.f3211i.A(s, (byte) ((b3 >> this.f3209g.chargeStop.bitStart) & 3));
            if (!z) {
                this.f3211i.w(bArr, s);
            }
            ExtensionPresent extensionPresent = this.f3211i;
            byte bA = this.f3209g.vehicleSystemFailure.a();
            if (b4 != 1 && b4 != 2) {
                z = false;
            }
            extensionPresent.p(bA, z);
            return;
        }
        List<DataEntity> list = extensionAlarm.chargeSystemFault;
        a.c cVar2 = a.c.FRONT;
        if (s == list.get(cVar2.ordinal()).canId) {
            DataEntity dataEntity2 = this.f3209g.chargeSystemFault.get(cVar2.ordinal());
            if ((bArr[dataEntity2.e()] & 3) != 1 && (bArr[dataEntity2.e() + 1] & 15) != 2) {
                z = false;
            }
            this.f3211i.p(dataEntity2.a(), z);
            if (z) {
                return;
            }
            this.f3211i.w(bArr, s);
            return;
        }
        ExtensionAlarm extensionAlarm2 = this.f3209g;
        DataEntity dataEntity3 = extensionAlarm2.vacuumPumpFault;
        if (s == dataEntity3.canId) {
            int iB = (bArr[dataEntity3.e()] >> dataEntity3.b()) & 3;
            this.f3211i.p(dataEntity3.a(), iB == 1);
            this.f3211i.p(this.f3209g.vacuumPumpSeriousFault, iB == 2);
            this.f3211i.p(this.f3209g.milLamp.a(), 3 == ((bArr[this.f3209g.milLamp.e()] >> this.f3209g.milLamp.b()) & 3));
            return;
        }
        DataEntity dataEntity4 = extensionAlarm2.absActionAlarm;
        if (s != dataEntity4.canId) {
            if (s != 1124 || b2 == 10 || b2 == 2) {
                this.f3211i.t(s, b2, bArr, z);
                return;
            }
            return;
        }
        int i2 = bArr[dataEntity4.startIndex] & 255;
        int i3 = ((i2 >> dataEntity4.bitStart) & 1) | (((i2 >> extensionAlarm2.ebdActionAlarm.bitStart) & 1) << 1);
        DataEntity dataEntity5 = extensionAlarm2.tcsActionAlarm;
        int i4 = i3 | (((bArr[dataEntity5.startIndex] >> dataEntity5.bitStart) & 1) << 2);
        DataEntity dataEntity6 = extensionAlarm2.vdcActionAlarm;
        int i5 = (((i2 >> extensionAlarm2.ebdFailureAlarm.bitStart) & 1) << 4) | i4 | (((bArr[dataEntity6.startIndex] >> dataEntity6.bitStart) & 1) << 3);
        byte bA2 = dataEntity4.a();
        this.f3211i.C(i5 << bA2, bA2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] R(byte[] bArr) {
        int length = bArr.length - 2;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 2, bArr2, 0, length);
        return bArr2;
    }

    public static d S(Context context) {
        if (f3203a == null) {
            synchronized (d.class) {
                if (f3203a == null) {
                    f3203a = new d(context);
                }
            }
        }
        return f3203a;
    }

    private void U() {
        this.u.e(this.v);
        this.u.e(this.w);
        if (this.u.h() > 0) {
            this.u.l();
        }
    }

    private void V() {
        if (this.k != null) {
            f.g(this.f3204b, "canHelp != null, return");
            return;
        }
        f.g(this.f3204b, "initCanHelp version =1.17.1-202507021749");
        try {
            this.k = new CanNativeHelper();
            this.k.init();
        } catch (Exception | UnsatisfiedLinkError e2) {
            f.d(this.f3204b, "CanNativeHelper so exception " + e2.getMessage());
        }
    }

    private void W() {
        if (this.f3210h == null || this.f3207e == null) {
            return;
        }
        this.n = Y();
        this.f3210h.r(1, this.f3207e.enginePowerType, a.e.VEHICLE);
        f.g(this.f3204b, "initValue is end encrypt =" + this.f3206d.isEncrypt);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean X(short s, byte b2, boolean z) {
        if (1189 == s || 1028 == s || 893 == s) {
            return true;
        }
        if (!z) {
            return 1097 == s || 981 == s || 724 == s || 1037 == s || 1242 == s || 1115 == s || 269 == s || 302 == s || 694 == s || 802 == s || 546 == s;
        }
        if (1124 == s) {
            return (b2 != 2) & (b2 != 10) & this.f3206d.b() & (b2 != 6) & (b2 != 7);
        }
        if (1231 == s) {
            return (b2 != 4) & (b2 != 6);
        }
        return 594 == s || 1094 == s || 302 == s || 892 == s || 1095 == s || 1136 == s;
    }

    private void Z() {
        if (this.k == null || this.s || this.f3210h == null) {
            return;
        }
        this.k.setListener(this);
        boolean z = true;
        this.s = true;
        f.g(this.f3204b, "CanNativeHelper setListener success");
        if (this.o != null || com.byd.data.common.server.b.e().s()) {
            return;
        }
        com.byd.data.common.c.c.c cVar = this.p;
        if (cVar != null && !cVar.i() && this.p.j() >= 6) {
            z = false;
        }
        if (z) {
            this.o = new TreeMap<>();
        }
        f.g(this.f3204b, "registerDataListener cloudCanTree local=" + z);
    }

    @Override // com.byd.data.common.c.c.b
    public void A(com.byd.data.common.a aVar) {
        this.r = aVar;
        f.g(this.f3204b, "setCloudBind is end");
    }

    @Override // com.byd.data.collect.k.e
    public void C() {
        if (this.f3205c instanceof DataCollectService) {
            f.b(this.f3204b, "flushBufferData is begin");
            ((DataCollectService) this.f3205c).c();
        }
    }

    @Override // com.byd.data.common.c.c.b
    public int D(byte b2) {
        com.byd.data.common.c.a aVar = b2 == 2 ? this.q : this.p;
        if (aVar == null) {
            return 0;
        }
        return aVar.C();
    }

    public void L(short s, byte b2, byte[] bArr) {
        NsPresenter nsPresenter;
        boolean zC = this.f3206d.c();
        VehicleData vehicleData = this.f3207e;
        if (vehicleData == null || this.f3208f == null || (nsPresenter = this.f3210h) == null) {
            return;
        }
        if (s == vehicleData.carState.canId) {
            byte bE = nsPresenter.e(bArr, this.f3206d.vehicleData.carState);
            if (bE != this.l) {
                this.l = bE;
                M(Y());
            }
            this.f3210h.g0(bE);
            this.f3211i.w(bArr, s);
        } else {
            DataEntity dataEntity = vehicleData.changeState;
            if (s == dataEntity.canId) {
                byte bE2 = nsPresenter.e(bArr, dataEntity);
                if (bE2 != this.m) {
                    this.m = bE2;
                    M(Y());
                }
                this.f3210h.n0(bE2);
                this.f3211i.w(bArr, s);
            } else {
                DataCollectBean dataCollectBean = this.f3206d;
                if (s == dataCollectBean.vehicleData.enginePowerType.canId) {
                    byte bE3 = nsPresenter.e(bArr, vehicleData.acceleratorPedalValue);
                    this.f3210h.B0(s, bArr, zC);
                    this.f3210h.r(bE3, this.f3207e.acceleratorPedalValue, a.e.VEHICLE);
                    this.f3211i.p(this.f3209g.acceleratorPedal, bE3 >= 90);
                    this.f3211i.w(bArr, s);
                } else if (s == vehicleData.speed.canId) {
                    nsPresenter.c0(bArr);
                    this.f3211i.w(bArr, s);
                } else {
                    DataEntity dataEntity2 = vehicleData.totalMileage;
                    if (s != dataEntity2.canId) {
                        P(s, b2, bArr, zC);
                        this.f3210h.w(s, b2, bArr, zC);
                    } else if (b2 == dataEntity2.subId) {
                        nsPresenter.A0(bArr);
                    } else if (b2 != 1) {
                        if (b2 == 3) {
                            nsPresenter.m0(s, bArr);
                        } else {
                            DataEntity dataEntity3 = dataCollectBean.engineData.last50KMOilConAvg;
                            if (b2 == dataEntity3.subId && zC) {
                                nsPresenter.s(bArr, dataEntity3, a.e.ENGINE);
                            }
                        }
                    }
                }
            }
        }
        if ((s == 1271 && b2 == 1) || s == 842) {
            d.a.a.f.b.b.a.m(this.f3204b, "canDataNotify isHev = " + zC + " ,canId =0x" + Integer.toHexString(s & 65535) + ",subCanId =0x" + Integer.toHexString(b2 & 65535) + ",canData =" + Arrays.toString(bArr));
        }
    }

    public byte[] T(Context context, boolean z, boolean z2, boolean z3) {
        BasePresent basePresent = z ? this.f3210h : this.f3211i;
        return basePresent == null ? new byte[0] : basePresent.g(context, z2, z3);
    }

    public boolean Y() {
        byte b2;
        byte b3;
        return this.f3210h == null || (b2 = this.l) <= 0 || (b3 = this.m) == 0 || 1 == b3 || b2 != 1;
    }

    @Override // com.byd.data.common.c.c.a
    public void a(int i2, int i3) {
        if (this.r == null || com.byd.data.common.server.b.e().d()) {
            return;
        }
        try {
            this.r.a(i2, i3);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.byd.data.common.c.c.b
    public void b(byte b2) {
        z((byte) 1, b2);
        z((byte) 2, b2);
    }

    @Override // com.byd.data.common.c.c.b
    public String c() {
        com.byd.data.common.c.c.c cVar = this.p;
        if (cVar == null) {
            return null;
        }
        return cVar.c();
    }

    @Override // com.byd.data.common.c.c.b
    public void d() {
        this.j = null;
        NsPresenter nsPresenter = this.f3210h;
        if (nsPresenter != null) {
            nsPresenter.c();
            this.f3210h = null;
        }
        ExtensionPresent extensionPresent = this.f3211i;
        if (extensionPresent != null) {
            extensionPresent.c();
            this.f3211i = null;
        }
        if (this.k != null) {
            this.k.destroy();
            this.k = null;
        }
        com.byd.data.common.c.c.c cVar = this.p;
        if (cVar != null) {
            cVar.d();
            this.p = null;
        }
        this.o = null;
        this.r = null;
        this.s = false;
        f3203a = null;
        com.byd.data.collect.k.b<b.C0061b> bVar = this.u;
        if (bVar != null) {
            bVar.j();
        }
        this.w = null;
        this.v = null;
        this.u = null;
        this.t = null;
        this.f3205c = null;
    }

    @Override // com.byd.data.common.c.c.b
    public ByteBuffer e(long j) {
        com.byd.data.common.c.c.c cVar = this.p;
        if (cVar != null) {
            return cVar.e(j);
        }
        return null;
    }

    @Override // com.byd.data.common.c.c.b
    public int f(byte b2) {
        com.byd.bms.a.a aVar;
        if (this.v == null || (aVar = this.q) == null || this.f3210h == null) {
            return 0;
        }
        boolean zF = aVar.f();
        f.b(this.f3204b, "isBmsSatisfy =" + zF + ",dataType =" + ((int) b2) + ",bms.isBmsTimeSatisfy() =" + this.q.B());
        if (b2 != 2 || !zF) {
            return zF ? 1 : 0;
        }
        int iZ = this.q.z();
        f.b(this.f3204b, "cumulativeTimeSize =" + iZ);
        return iZ;
    }

    @Override // com.byd.data.common.c.c.b
    public void g(DataCollectBean dataCollectBean) {
        this.f3206d = dataCollectBean;
        this.f3207e = dataCollectBean.vehicleData;
        this.f3208f = dataCollectBean.driverMotorData;
        this.f3209g = dataCollectBean.extensionAlarm;
        ExtensionPresent extensionPresent = new ExtensionPresent(dataCollectBean);
        this.f3211i = extensionPresent;
        NsPresenter nsPresenter = new NsPresenter(dataCollectBean, extensionPresent);
        this.f3210h = nsPresenter;
        this.q = nsPresenter.A();
        this.j = new com.byd.data.collect.k.c(dataCollectBean, this);
        W();
        V();
        Z();
    }

    @Override // com.byd.data.common.c.c.b
    public void h() {
        com.byd.data.common.a aVar = this.r;
        if (aVar == null || this.v == null) {
            return;
        }
        try {
            aVar.n(true);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.byd.data.common.c.c.b
    public void i(byte b2) {
        com.byd.bms.a.a aVar;
        if (b2 != 1) {
            if (b2 == 2 && (aVar = this.q) != null) {
                aVar.x();
                return;
            }
            return;
        }
        com.byd.data.common.c.c.c cVar = this.p;
        if (cVar != null) {
            cVar.F();
        }
    }

    @Override // com.byd.data.common.c.c.b
    public boolean j(byte b2) {
        com.byd.data.common.c.c.c cVar;
        return (this.v == null || b2 == 2 || (cVar = this.p) == null || !cVar.f()) ? false : true;
    }

    @Override // com.byd.data.common.c.c.b
    public int k(byte b2, boolean z) {
        if (b2 == 2) {
            return this.f3210h.B(z);
        }
        com.byd.data.common.c.c.c cVar = this.p;
        if (cVar == null) {
            return 1000;
        }
        return cVar.q();
    }

    @Override // com.byd.data.common.c.c.b
    public int l(boolean z, byte b2) {
        com.byd.data.common.c.a aVar = b2 == 2 ? this.q : this.p;
        if (aVar == null) {
            return 60;
        }
        return aVar.l(z);
    }

    @Override // com.byd.data.common.c.c.b
    public void m() {
        com.byd.data.common.a aVar = this.r;
        if (aVar == null || this.v == null || this.p != null) {
            return;
        }
        try {
            aVar.k();
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.byd.data.common.c.c.b
    public void n() {
        com.byd.data.common.a aVar = this.r;
        if (aVar == null || this.v == null) {
            return;
        }
        try {
            if (this.p != null) {
                aVar.f((byte) 1);
            }
            if (this.q != null) {
                this.r.f((byte) 2);
            }
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.byd.data.common.c.c.b
    public void o(byte b2) {
        try {
            if (this.r != null) {
                f.b(this.f3204b, "notifyBmsStartCollect is begin =" + ((int) b2));
                this.r.m(b2);
            }
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.byd.can.CanNativeHelper.a
    public void p(int i2, byte[] bArr, int i3) {
        boolean z = i2 != 561049648;
        if (!z || 557872139 <= i2) {
            b.C0061b c0061bA = this.t.a(i2, bArr, i3, z);
            if (f.f3426a) {
                String strValueOf = z ? String.valueOf(i2) : Integer.toHexString(c0061bA.f3189a).toUpperCase();
                StringBuilder sb = new StringBuilder();
                sb.append(z ? "propId" : "canId");
                sb.append(" =%1s, canData= %2s");
                String string = sb.toString();
                Object[] objArr = new Object[2];
                objArr[0] = strValueOf;
                if (!z) {
                    bArr = c0061bA.f3191c;
                }
                objArr[1] = c.a.a.g.d.g(bArr, false);
                f.k("BydParseCan", String.format(string, objArr));
            }
            this.u.i(c0061bA);
        }
    }

    @Override // com.byd.data.common.c.c.b
    public ByteBuffer q(int i2, long j) {
        com.byd.data.common.c.c.c cVar = this.p;
        if (cVar != null) {
            return cVar.v(i2, j);
        }
        return null;
    }

    @Override // com.byd.data.collect.k.e
    public ExtensionPresent r() {
        return this.f3211i;
    }

    @Override // com.byd.data.common.c.c.b
    public byte s(byte b2) {
        NsPresenter nsPresenter;
        Log.i(this.f3204b, "initCloud，initCloud == " + ((int) b2) + ",cloud ==" + this.p + ",nsPresenter == " + this.f3210h + ",bms ==" + this.q);
        V();
        if ((this.p == null) & (1 == b2)) {
            this.p = new CloudCanPresent(this);
        }
        byte bF = 1 == b2 ? this.p.F() : (byte) 0;
        if (2 == b2 && (nsPresenter = this.f3210h) != null && this.q != null) {
            boolean z = !nsPresenter.E();
            Log.i(this.f3204b, "oldProtocol == " + z);
            bF = 3;
            if (z) {
                this.q.b((byte) 3);
            }
            if (!z) {
                bF = this.q.x();
            }
        }
        if (1 == b2) {
            f.g(this.f3204b, "initCloud is end version =1.17.1-202507021749");
        }
        return bF;
    }

    @Override // com.byd.data.common.c.c.b
    public void t() {
        f.g(this.f3204b, "stopCloudMsg is begin -- disposer=" + this.v);
        com.byd.data.common.a aVar = this.r;
        if (aVar == null || this.v == null) {
            if (this.v == null) {
                f.g(this.f3204b, "not reset cloud dispatch data or io");
                return;
            }
            return;
        }
        try {
            aVar.j();
            b.d dVar = this.v;
            if (dVar != null) {
                this.u.k(dVar);
            }
            this.v = null;
            i.o(this.f3205c).l();
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.byd.data.common.c.c.b
    public ByteBuffer u(byte b2, int i2) {
        NsPresenter nsPresenter;
        if (this.q == null || (nsPresenter = this.f3210h) == null) {
            return null;
        }
        return nsPresenter.C(b2, i2);
    }

    @Override // com.byd.data.common.c.c.a
    public void v(boolean z) {
        if (this.o == null || this.p == null) {
            return;
        }
        TreeMap<Short, byte[]> treeMap = this.o;
        this.o = null;
        f.g(this.f3204b, "notifyFillCan is begin fillData=" + z);
        if (!z || treeMap.isEmpty()) {
            treeMap.clear();
            return;
        }
        Iterator<Map.Entry<Short, byte[]>> it = treeMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Short, byte[]> next = it.next();
            byte[] value = next.getValue();
            if (value != null) {
                this.p.E(next.getKey().shortValue(), value[0], value);
                it.remove();
            }
        }
        f.g(this.f3204b, "notifyFillCan is end");
    }

    @Override // com.byd.data.common.c.c.b
    public boolean w(byte b2) {
        com.byd.data.common.c.a aVar = b2 == 2 ? this.q : this.p;
        return aVar == null || aVar.i();
    }

    @Override // com.byd.data.common.c.c.b
    public byte x(byte b2) {
        com.byd.data.common.c.a aVar = b2 == 1 ? this.p : this.q;
        if (aVar == null) {
            return (byte) -1;
        }
        return aVar.j();
    }

    @Override // com.byd.data.common.c.b
    public void y(byte[] bArr) {
        byte[] bytes = "{\"CloudCommands\": {\"CmdList\":[{\"Name\": \"UpdateCloudConfig\",\"ParameNum\":0,\"Parames\":\"null\"}]}}".getBytes(StandardCharsets.UTF_8);
        if (this.k != null) {
            this.k.sendCloudCmd(bytes, bytes.length);
        }
        Z();
    }

    @Override // com.byd.data.common.c.c.b
    public void z(byte b2, byte b3) {
        com.byd.bms.a.a aVar;
        com.byd.data.common.c.c.c cVar;
        if (b2 == 1 && (cVar = this.p) != null) {
            cVar.b(b3);
        }
        if (b2 != 2 || (aVar = this.q) == null) {
            return;
        }
        aVar.b(b3);
    }
}
