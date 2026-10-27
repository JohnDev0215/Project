package com.byd.data.collect.subscribe.present;

import android.content.Context;
import android.hardware.bydauto.TsNormalNum;
import android.util.SparseArray;
import com.byd.bms.business.BmsPresent;
import com.byd.data.common.d.a;
import com.byd.data.common.entiry.extreme.ExtremeBase;
import com.byd.data.common.entiry.gson.AlarmData;
import com.byd.data.common.entiry.gson.DataCollectBean;
import com.byd.data.common.entiry.gson.DataEntity;
import com.byd.data.common.entiry.gson.DriverMotorData;
import com.byd.data.common.entiry.gson.EngineData;
import com.byd.data.common.entiry.gson.VehicleData;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public class NsPresenter extends BasePresent<Integer> implements com.byd.bms.a.b {
    private LinkedHashMap<Byte, byte[]> A;
    private TreeSet<ExtremeBase<Byte>> B;
    private SparseArray<Byte> C;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final byte f3271f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final byte f3272g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final byte f3273h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final byte[] f3274i;
    private final byte[] j;
    private com.byd.bms.a.a k;
    private VehicleData l;
    private DriverMotorData m;
    private ExtensionPresent n;
    private byte o;
    private short p;
    private short q;
    public short r;
    private volatile byte s;
    private byte t;
    private byte u;
    private transient byte v;
    private byte w;
    public volatile short x;
    private LinkedHashMap<Byte, byte[]> y;
    private TreeSet<ExtremeBase<Short>> z;

    public NsPresenter(DataCollectBean dataCollectBean, ExtensionPresent extensionPresent) {
        super(dataCollectBean);
        this.f3271f = (byte) 1;
        this.f3272g = (byte) 2;
        this.f3273h = (byte) 3;
        this.f3274i = new byte[0];
        this.j = new byte[0];
        this.C = new SparseArray<>(4);
        this.y = new LinkedHashMap<>();
        this.z = new TreeSet<>(new Comparator() { // from class: com.byd.data.collect.subscribe.present.h
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return NsPresenter.W((ExtremeBase) obj, (ExtremeBase) obj2);
            }
        });
        this.A = new LinkedHashMap<>();
        this.B = new TreeSet<>(new Comparator() { // from class: com.byd.data.collect.subscribe.present.g
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return NsPresenter.X((ExtremeBase) obj, (ExtremeBase) obj2);
            }
        });
        this.n = extensionPresent;
    }

    private ByteBuffer D() {
        ByteBuffer byteBufferAllocate;
        synchronized (this.j) {
            final ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(this.u);
            this.A.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(new Consumer() { // from class: com.byd.data.collect.subscribe.present.c
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    NsPresenter.R(byteBufferAllocate2, (Map.Entry) obj);
                }
            });
            byteBufferAllocate = ByteBuffer.allocate(byteBufferAllocate2.position() + 5);
            byteBufferAllocate.put((byte) 9);
            byteBufferAllocate.put((byte) 1);
            byteBufferAllocate.put((byte) 1);
            byteBufferAllocate.putShort((short) byteBufferAllocate2.position());
            byteBufferAllocate.put(byteBufferAllocate2.array(), 0, byteBufferAllocate2.position());
        }
        return byteBufferAllocate;
    }

    private List<ByteBuffer> F(boolean z) {
        ArrayList arrayList;
        synchronized (this.f3274i) {
            int i2 = 0;
            final double[] dArr = {0.0d};
            boolean z2 = ((double) this.y.size()) >= Math.ceil(((double) (this.s & 255)) / 3.0d);
            int i3 = (this.s & 255) << 1;
            final ByteBuffer byteBufferAllocate = z2 ? ByteBuffer.allocate(i3) : i(i3);
            final boolean[] zArr = new boolean[1];
            zArr[0] = !z2;
            this.y.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(new Consumer() { // from class: com.byd.data.collect.subscribe.present.a
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    NsPresenter.S(zArr, byteBufferAllocate, dArr, (Map.Entry) obj);
                }
            });
            this.r = (short) (dArr[0] * 0.01d);
            this.f3263b.get(Integer.valueOf(a.e.VEHICLE.ordinal())).putShort(this.l.totalVoltage.a(), this.r);
            r0(this.r, this.m.motorMCUGeneratrixVolt);
            int iMax = z ? Math.max(1, (int) Math.ceil(((double) byteBufferAllocate.position()) / 400.0d)) : 1;
            if ((!z) & this.f3265d.c()) {
                h0(this.l.totalVoltage.a(), this.r, 2);
            }
            int i4 = (this.x & 65535) + TsNormalNum.THOUSAND_FIVE;
            if ((!z) & (i4 != 0)) {
                i4 -= 5000;
            }
            arrayList = new ArrayList(iMax);
            while (i2 < iMax) {
                int iCapacity = i2 == iMax + (-1) ? byteBufferAllocate.capacity() - (i2 * 400) : 400;
                ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(iCapacity + 12);
                byteBufferAllocate2.put((byte) 8);
                byteBufferAllocate2.put((byte) 1);
                byteBufferAllocate2.put((byte) 1).putShort((short) (this.r & 65535)).putShort((short) i4).putShort((short) (this.s & 255)).putShort((short) ((i2 * 200) + 1)).put((byte) (iCapacity >> 1));
                if (byteBufferAllocate2.remaining() >= iCapacity) {
                    byteBufferAllocate2.put(byteBufferAllocate.array(), i2 * 400, iCapacity);
                }
                arrayList.add(byteBufferAllocate2);
                i2++;
            }
        }
        return arrayList;
    }

    private ByteBuffer G() {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(6);
        Calendar calendar = Calendar.getInstance();
        byteBufferAllocate.put((byte) ((calendar.get(1) % 2000) & 255)).put((byte) ((calendar.get(2) + 1) & 255)).put((byte) (calendar.get(5) & 255)).put((byte) (calendar.get(11) & 255)).put((byte) (calendar.get(12) & 255)).put((byte) (calendar.get(13) & 255));
        return byteBufferAllocate;
    }

    private ByteBuffer H() {
        ByteBuffer byteBuffer = this.f3263b.get(Integer.valueOf(a.e.EXTREME.ordinal()));
        if (!this.z.isEmpty()) {
            synchronized (this.f3274i) {
                ExtremeBase<Short> extremeBaseLast = this.z.last();
                byteBuffer.put(1, (byte) 1);
                byteBuffer.put(2, (byte) (extremeBaseLast.a() + (extremeBaseLast.b() * 3)));
                byteBuffer.putShort(3, ((Short) extremeBaseLast.value).shortValue());
                ExtremeBase<Short> extremeBaseFirst = this.z.first();
                byteBuffer.put(5, (byte) 1);
                byteBuffer.put(6, (byte) (extremeBaseFirst.a() + (extremeBaseFirst.b() * 3)));
                byteBuffer.putShort(7, ((Short) extremeBaseFirst.value).shortValue());
            }
        }
        if (!this.B.isEmpty()) {
            synchronized (this.j) {
                ExtremeBase<Byte> extremeBaseLast2 = this.B.last();
                byteBuffer.put(9, (byte) 1);
                byteBuffer.put(10, (byte) (extremeBaseLast2.a() + (extremeBaseLast2.b() * 6)));
                byteBuffer.put(11, (byte) (((Byte) extremeBaseLast2.value).byteValue() & 255));
                ExtremeBase<Byte> extremeBaseFirst2 = this.B.first();
                byteBuffer.put(12, (byte) 1);
                byteBuffer.put(13, (byte) (extremeBaseFirst2.a() + (extremeBaseFirst2.b() * 6)));
                byteBuffer.put(14, (byte) (((Byte) extremeBaseFirst2.value).byteValue() & 255));
            }
        }
        return byteBuffer;
    }

    private TreeSet<ExtremeBase<Byte>> I(byte b2, byte[] bArr) {
        int i2;
        int length = bArr.length - 2;
        if (b2 > 0) {
            byte b3 = this.u;
            if (b3 / 6 == b2 && (i2 = b3 % 6) > 0) {
                length = i2;
            }
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length);
        int i3 = length + 1;
        TreeSet<ExtremeBase<Byte>> treeSet = new TreeSet<>(new Comparator() { // from class: com.byd.data.collect.subscribe.present.b
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return NsPresenter.U((ExtremeBase) obj, (ExtremeBase) obj2);
            }
        });
        for (byte b4 = 1; b4 < i3; b4 = (byte) (b4 + 1)) {
            short s = (short) (bArr[b4] & 255);
            if (s <= 250) {
                treeSet.add(new ExtremeBase<>(b2, b4, Byte.valueOf((byte) s)));
                byteBufferAllocate.put(bArr[b4]);
            }
        }
        this.A.put(Byte.valueOf(b2), byteBufferAllocate.array());
        return treeSet;
    }

    private TreeSet<ExtremeBase<Short>> J(byte b2, byte[] bArr) {
        byte b3 = this.t;
        if (b3 == 0 || (b2 & 255) > b3) {
            return null;
        }
        TreeSet<ExtremeBase<Short>> treeSet = new TreeSet<>(new Comparator() { // from class: com.byd.data.collect.subscribe.present.e
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return NsPresenter.V((ExtremeBase) obj, (ExtremeBase) obj2);
            }
        });
        boolean z = b2 > 0 && this.t == b2;
        int length = bArr.length - 2;
        if (z) {
            length = ((this.s & 255) % 3) << 1;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length);
        int i2 = length + 1;
        for (int i3 = 1; i3 < i2; i3 += 2) {
            int iJ = j(bArr, i3, i3 + 1);
            if (iJ != 0 && iJ < 65534) {
                short s = (short) iJ;
                byteBufferAllocate.putShort(s);
                treeSet.add(new ExtremeBase<>(b2, (byte) Math.ceil(((double) i3) / 2.0d), Short.valueOf(s)));
            }
        }
        this.y.put(Byte.valueOf(b2), byteBufferAllocate.array());
        return treeSet;
    }

    private ByteBuffer K(DriverMotorData driverMotorData, int i2) {
        a.b bVar = a.b.DUAL_DRIVER;
        boolean z = i2 % bVar.ordinal() == 0;
        int i3 = driverMotorData.totalSize;
        if (z) {
            i3 = (i3 << 1) - 2;
        }
        ByteBuffer byteBufferL = l(driverMotorData, i3);
        this.o = (byte) i2;
        byteBufferL.put(driverMotorData.driverMotorSerial.a(), (byte) a.b.FRONT_DRIVER.ordinal());
        if (z) {
            byteBufferL.put(driverMotorData.totalSize, (byte) bVar.ordinal());
        }
        if (2 != i2 && 4 != i2) {
            i2 = 1;
        }
        byteBufferL.put(driverMotorData.driverMotorNum.a(), (byte) i2);
        return byteBufferL;
    }

    static /* synthetic */ boolean P(byte b2, ExtremeBase extremeBase) {
        return extremeBase.b() == b2;
    }

    static /* synthetic */ boolean Q(byte b2, ExtremeBase extremeBase) {
        return extremeBase.b() == b2;
    }

    static /* synthetic */ void R(ByteBuffer byteBuffer, Map.Entry entry) {
        byte[] bArr = (byte[]) entry.getValue();
        if (byteBuffer.remaining() >= bArr.length) {
            byteBuffer.put(bArr);
        }
    }

    static /* synthetic */ void S(boolean[] zArr, ByteBuffer byteBuffer, double[] dArr, Map.Entry entry) {
        byte[] bArr = (byte[]) entry.getValue();
        int iByteValue = ((Byte) entry.getKey()).byteValue() * 6;
        if (iByteValue > 0 && zArr[0] && iByteValue > byteBuffer.position()) {
            try {
                byteBuffer.position(iByteValue);
                zArr[0] = false;
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
            }
        }
        if (byteBuffer.remaining() >= bArr.length) {
            byteBuffer.put(bArr);
            for (int i2 = 0; i2 < bArr.length; i2 += 2) {
                dArr[0] = dArr[0] + ((double) (((bArr[i2] & 255) << 8) | (bArr[i2 + 1] & 255)));
            }
        }
    }

    static /* synthetic */ int U(ExtremeBase extremeBase, ExtremeBase extremeBase2) {
        return (((Byte) extremeBase.value).byteValue() & 255) - (((Byte) extremeBase2.value).byteValue() & 255);
    }

    static /* synthetic */ int V(ExtremeBase extremeBase, ExtremeBase extremeBase2) {
        return (((Short) extremeBase.value).shortValue() & 65535) - (((Short) extremeBase2.value).shortValue() & 65535);
    }

    static /* synthetic */ int W(ExtremeBase extremeBase, ExtremeBase extremeBase2) {
        return (((Short) extremeBase.value).shortValue() & 65535) - (((Short) extremeBase2.value).shortValue() & 65535);
    }

    static /* synthetic */ int X(ExtremeBase extremeBase, ExtremeBase extremeBase2) {
        return (((Byte) extremeBase.value).byteValue() & 255) - (((Byte) extremeBase2.value).byteValue() & 255);
    }

    private byte i0(int i2, boolean z, byte b2) {
        super.p(i2, z);
        if (z) {
            return b2;
        }
        return (byte) 0;
    }

    private void k0(byte[] bArr, boolean z, short s, byte b2) {
        byte b3 = this.v;
        if (!z ? s == 1083 : s == 1082) {
            if (((b3 >>> 1) & 1) > 0) {
                return;
            }
            int i2 = bArr[4] & 255;
            this.v = (byte) (((!z ? i2 == 240 : i2 != 255) ? 0 : 2) | (b3 & 1));
        }
        if (z && s == 1271 && b2 == 1) {
            this.v = (byte) ((((bArr[1] >>> 6) & 3) == 1 ? 1 : 0) | (2 & this.v));
        } else if (!z && s == 842) {
            this.v = (byte) ((((bArr[6] >>> 2) & 3) == 1 ? 1 : 0) | (2 & this.v));
        }
        d.a.a.f.b.b.a.m(this.f3262a, "setBmsSwitch isHev = " + z + " ,canId =" + ((int) s) + ",subId =" + ((int) b2) + ",bmsSwitch =" + ((int) this.v) + ",canData =" + Arrays.toString(bArr));
        byte b4 = this.v;
        if (b4 != 3 || b3 == b4) {
            return;
        }
        this.k.b((byte) -1);
        this.k.x();
    }

    private void l0(byte[] bArr, boolean z) {
        if (!z) {
            if (this.k.h((byte) 0)) {
                this.k.o(220, false);
                this.k.D(bArr, false);
                return;
            }
            return;
        }
        int i2 = bArr[6] & 255;
        int i3 = this.s & 255;
        if (i2 == 255) {
            i2 = i3;
        }
        this.k.o((i2 >= 1 || i3 >= 1) ? i2 : 255, true);
    }

    private void r0(short s, DataEntity dataEntity) {
        s0(s, dataEntity, s, dataEntity);
    }

    private void u(final byte b2, byte[] bArr) {
        synchronized (this.f3274i) {
            TreeSet<ExtremeBase<Short>> treeSetJ = J(b2, bArr);
            if (treeSetJ != null && !treeSetJ.isEmpty()) {
                this.z.removeIf(new Predicate() { // from class: com.byd.data.collect.subscribe.present.d
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return NsPresenter.Q(b2, (ExtremeBase) obj);
                    }
                });
                this.z.add(treeSetJ.first());
                this.z.add(treeSetJ.last());
                treeSetJ.clear();
            }
        }
        com.byd.bms.a.a aVar = this.k;
        if (aVar == null) {
            return;
        }
        aVar.w(bArr[7]);
        if ((b2 & 255) == 253) {
            this.k.g((short) j(bArr, 1, 2));
        }
        if (O()) {
            this.k.y();
        }
    }

    private void u0(byte[] bArr) {
        byte b2;
        if (bArr[bArr.length - 2] != -1) {
            b2 = (byte) (((byte) ((bArr[bArr.length + (-2)] > 0 ? 1 : 0) << 5)) | 0);
        } else {
            b2 = 0;
        }
        if (bArr[bArr.length - 1] != -1) {
            b2 = (byte) (b2 | ((bArr[bArr.length - 1] > 0 ? 1 : 0) << 4));
        }
        int length = bArr.length - 5;
        bArr[length] = (byte) (b2 | bArr[length]);
    }

    private ByteBuffer x() {
        ByteBuffer byteBuffer = this.f3263b.get(Integer.valueOf(a.e.ALARM.ordinal()));
        int i2 = 0;
        byte b2 = 0;
        while (true) {
            if (i2 >= this.C.size()) {
                break;
            }
            byte bByteValue = this.C.valueAt(i2).byteValue();
            if (bByteValue == 3) {
                b2 = bByteValue;
                break;
            }
            if (b2 < bByteValue) {
                b2 = bByteValue;
            }
            i2++;
        }
        byteBuffer.put(1, b2);
        byteBuffer.putInt(2, this.f3266e);
        byteBuffer.putInt(6, 0);
        return byteBuffer;
    }

    private byte z(byte b2, DataEntity dataEntity) {
        return (byte) (((b2 & 255) >> dataEntity.bitStart) & 3);
    }

    public com.byd.bms.a.a A() {
        return this.k;
    }

    public void A0(byte[] bArr) {
        int iF = f(bArr, this.l.totalMileage);
        r(iF, this.l.totalMileage, a.e.VEHICLE);
        h0(this.f3265d.c() ? this.l.totalMileage.a() : (byte) 3, iF, 4);
    }

    @Override // com.byd.bms.a.b
    public int B(boolean z) {
        int iMax = Math.max(((int) Math.ceil(((double) (this.s & 255)) / 3.0d)) * 100, 1000);
        if (!z) {
            return iMax;
        }
        int iR = this.k.r(iMax);
        com.byd.data.common.i.f.b(this.f3262a, "getCollectInterval, ret=" + iMax + ", lastCollect=" + iR + ", Math.ceil((totalVoltNum & 0xFF) / 3.0) =" + Math.ceil(((double) (this.s & 255)) / 3.0d));
        return iR == 0 ? iMax : iR;
    }

    public void B0(short s, byte[] bArr, boolean z) {
        if (!z ? s == 834 : s == 578) {
            byte bE = e(bArr, this.l.gearValue);
            byte b2 = bE == 1 ? (byte) 15 : 2 == bE ? (byte) 13 : 3 == bE ? (byte) 0 : 4 == bE ? (byte) 14 : bE;
            com.byd.data.common.i.f.g(this.f3262a, "gear value = " + ((int) b2) + ", gear =" + ((int) bE));
            r(b2, this.l.gearValue, a.e.VEHICLE);
            if (z) {
                h0(this.l.gearValue.a(), bE, 1);
            }
        }
        if (!z || s == 578) {
            return;
        }
        DataEntity dataEntity = this.l.enginePowerType;
        byte bE2 = e(bArr, dataEntity);
        h0(dataEntity.a(), bArr[4] & 15, 1);
        h0(this.l.acceleratorPedalValue.a(), bArr[1] & 255, 1);
        r((bE2 == 1 || bE2 == 2) ? 1 : 2, dataEntity, a.e.VEHICLE);
    }

    public ByteBuffer C(byte b2, int i2) {
        com.byd.bms.a.a aVar;
        if (this.f3265d == null || (aVar = this.k) == null) {
            return null;
        }
        if (b2 == 2) {
            return aVar.t(i2 + 6, G());
        }
        ByteBuffer byteBufferU = aVar.u();
        if (byteBufferU == null) {
            com.byd.data.common.i.f.g(this.f3262a, "impossible config parse failure or config has not been parsed");
            return null;
        }
        ByteBuffer byteBuffer = F(false).get(0);
        ByteBuffer byteBufferD = D();
        int iCapacity = byteBufferU.capacity();
        DataCollectBean dataCollectBean = this.f3265d;
        ByteBuffer byteBufferA = this.k.a(iCapacity + dataCollectBean.extremeData.totalSize + dataCollectBean.alarmData.totalSize + byteBuffer.position() + byteBufferD.position() + 10, G());
        boolean zC = this.f3265d.c();
        byte[] bArrArray = byteBufferU.array();
        if (zC) {
            byteBufferA.put(bArrArray);
        }
        byteBufferA.put(H().array()).put(x().array());
        if (!zC) {
            byteBufferA.put(bArrArray);
        }
        return this.k.s(byteBufferA, byteBuffer, byteBufferD);
    }

    @Override // com.byd.bms.a.b
    public boolean E() {
        boolean z = this.v == 3;
        if (M(true) & (!z)) {
            com.byd.data.common.i.f.g(this.f3262a, "canSwitch =" + ((int) this.v));
        }
        return z;
    }

    public boolean L() {
        if (this.o > a.c.FRONT.ordinal()) {
            byte b2 = this.o;
            if (b2 % 2 == 0 || b2 == a.b.REAR_DRIVER.ordinal()) {
                return true;
            }
        }
        return false;
    }

    protected boolean M(boolean z) {
        return (z ? com.byd.data.common.server.b.e().p() : n()) & (this.w % 10 == 0);
    }

    public boolean N() {
        return this.o > a.c.FRONT.ordinal() && this.o != a.b.REAR_DRIVER.ordinal();
    }

    public boolean O() {
        if (this.k == null || this.v < 3 || this.f3265d == null || this.t == 0 || this.y == null) {
            return false;
        }
        if (M(true)) {
            com.byd.data.common.i.f.b(this.f3262a, "bmsSwitch =" + ((int) this.v) + ",voltSerId=" + ((int) this.t));
            String str = this.f3262a;
            StringBuilder sb = new StringBuilder();
            sb.append("voltMap.size() >= Math.ceil((totalVoltNum & 0xFF) / 3.0) =");
            sb.append(((double) this.y.size()) >= Math.ceil(((double) (this.s & 255)) / 3.0d));
            com.byd.data.common.i.f.b(str, sb.toString());
            com.byd.data.common.i.f.b(this.f3262a, "voltMap.size() =" + this.y.size());
            com.byd.data.common.i.f.b(this.f3262a, "totalVoltNum =" + ((int) this.s));
        }
        return ((double) this.y.size()) >= Math.ceil(((double) (this.s & 255)) / 3.0d);
    }

    public void Y(byte[] bArr, boolean z) {
        AlarmData alarmData = this.f3265d.alarmData;
        DataEntity dataEntity = alarmData.driverMotorTempAlarm;
        byte b2 = bArr[dataEntity.startIndex];
        int iMax = Math.max(0, (int) i0(dataEntity.a(), z(b2, alarmData.driverMotorTempAlarm) == 2, (byte) 2));
        List<DataEntity> list = alarmData.igbtTempAlarm;
        a.c cVar = a.c.FRONT;
        this.C.put(alarmData.driverMotorTempAlarm.canId, Byte.valueOf((byte) Math.max(iMax, (int) i0(list.get(cVar.ordinal()).a(), z(b2, alarmData.igbtTempAlarm.get(z ? cVar.ordinal() : a.c.REAR.ordinal())) == 2, (byte) 2))));
        int i2 = b2 & 3;
        this.n.p(this.f3265d.extensionAlarm.igbtPowerSystemFault.a(), i2 == 2);
        this.n.p(this.f3265d.extensionAlarm.evFault, i2 == 1);
    }

    public void Z(byte[] bArr, boolean z) {
        AlarmData alarmData = this.f3265d.alarmData;
        byte b2 = bArr[alarmData.highTempAlarm.e()];
        byte bZ = z(b2, alarmData.highTempAlarm);
        int iMax = Math.max(0, (int) i0(alarmData.highTempAlarm.a(), bZ >= 1, bZ == 1 ? (byte) 2 : (byte) 3));
        byte bZ2 = z(b2, alarmData.insulationAlarm);
        int iMax2 = Math.max(iMax, (int) i0(alarmData.insulationAlarm.a(), (bZ2 & 2) == 2, (z && bZ2 == 2) ? (byte) 2 : (byte) 3));
        byte b3 = bArr[alarmData.lowSignalVoltAlarm.e()];
        int iMax3 = Math.max(iMax2, (int) i0(alarmData.lowSignalVoltAlarm.a(), z(b3, alarmData.lowSignalVoltAlarm) >= 2, (byte) 2));
        if (!z) {
            DataEntity dataEntity = alarmData.socLowAlarm.get(a.c.REAR.ordinal());
            iMax3 = Math.max(iMax3, (int) i0(dataEntity.a(), z(b3, dataEntity) == 1, (byte) 1));
        }
        int iMax4 = Math.max(iMax3, (int) i0(alarmData.overSignalVoltAlarm.a(), ((byte) (bArr[alarmData.overSignalVoltAlarm.e()] & 3)) >= 2, z ? (byte) 3 : (byte) 2));
        byte bA = alarmData.highVoltStatusAlarm.a();
        DataEntity dataEntity2 = alarmData.highVoltStatusAlarm;
        this.C.put(alarmData.highTempAlarm.canId, Byte.valueOf((byte) Math.max(iMax4, (int) i0(bA, z(bArr[dataEntity2.startIndex], dataEntity2) >= 1, (byte) 2))));
        this.n.p(this.f3265d.extensionAlarm.powerSystemFault.a(), (bArr[this.f3265d.extensionAlarm.powerSystemFault.startIndex] & 3) == 1);
    }

    public void a0(byte[] bArr) {
        AlarmData alarmData = this.f3265d.alarmData;
        int iMax = Math.max(0, (int) i0(alarmData.dcTempAlarm.a(), z((byte) (bArr[alarmData.dcTempAlarm.e()] & 3), alarmData.dcTempAlarm) == 1, (byte) 1));
        DataEntity dataEntity = alarmData.dcStatusAlarm;
        boolean z = z(bArr[dataEntity.startIndex], dataEntity) == 2;
        this.C.put(alarmData.dcTempAlarm.canId, Byte.valueOf((byte) Math.max(iMax, (int) i0(alarmData.dcStatusAlarm.a(), z, (byte) 2))));
        this.n.p(this.f3265d.extensionAlarm.dcStatusAlarm, z);
    }

    public void b0(byte[] bArr, boolean z) {
        byte bA;
        AlarmData alarmData = this.f3265d.alarmData;
        byte b2 = bArr[alarmData.tempDifferenceAlarm.e()];
        int i2 = 0;
        int iMax = 0;
        while (true) {
            if (i2 >= 8) {
                break;
            }
            boolean z2 = ((byte) (((b2 & 255) >> i2) & 3)) == 2;
            if (alarmData.tempDifferenceAlarm.b() == i2) {
                iMax = Math.max((int) i0(alarmData.tempDifferenceAlarm.a(), z2, (byte) 1), iMax);
            } else if (i2 == alarmData.overGroupVoltAlarm.b()) {
                iMax = Math.max((int) i0(alarmData.overGroupVoltAlarm.a(), z2, z ? (byte) 3 : (byte) 2), iMax);
            } else {
                iMax = Math.max((int) i0(alarmData.batteryCoherenceAlarm.b() == i2 ? alarmData.batteryCoherenceAlarm.a() : alarmData.batteryMismatchAlarm.a(), z2, (byte) 2), iMax);
            }
            i2 += 2;
        }
        byte b3 = bArr[alarmData.lowGroupVoltageAlarm.e()];
        for (int i3 = 0; i3 < 8; i3 += 2) {
            boolean z3 = ((byte) (((b3 & 255) >> i3) & 3)) == 2;
            DataEntity dataEntity = alarmData.overChargeAlarm;
            if (i3 == dataEntity.bitStart) {
                iMax = Math.max((int) i0(dataEntity.a(), z3, (byte) 3), iMax);
            } else {
                if (i3 == 0) {
                    bA = alarmData.lowGroupVoltageAlarm.a();
                } else {
                    DataEntity dataEntity2 = alarmData.socHighAlarm;
                    if (i3 != dataEntity2.bitStart) {
                        dataEntity2 = alarmData.socJumpAlarm;
                    }
                    bA = dataEntity2.a();
                }
                iMax = Math.max((int) i0(bA, z3, (byte) 2), iMax);
                if (alarmData.socJumpAlarm.bitStart == i3) {
                    this.n.p(this.f3265d.extensionAlarm.socJumpFault, z3);
                }
            }
        }
        if (z) {
            DataEntity dataEntity3 = alarmData.socLowAlarm.get(a.c.FRONT.ordinal());
            iMax = Math.max((int) i0(dataEntity3.a(), ((byte) (((bArr[dataEntity3.e()] & 255) >> dataEntity3.bitStart) & 3)) == 2, (byte) 1), iMax);
        }
        this.C.put(alarmData.tempDifferenceAlarm.canId, Byte.valueOf((byte) iMax));
    }

    @Override // com.byd.data.collect.subscribe.present.BasePresent
    public void c() {
        super.c();
        this.n = null;
        this.l = null;
        this.m = null;
        com.byd.bms.a.a aVar = this.k;
        if (aVar != null) {
            aVar.d();
            this.k = null;
        }
        LinkedHashMap<Byte, byte[]> linkedHashMap = this.y;
        if (linkedHashMap != null) {
            linkedHashMap.clear();
            this.y = null;
        }
        TreeSet<ExtremeBase<Short>> treeSet = this.z;
        if (treeSet != null) {
            treeSet.clear();
            this.z = null;
        }
        LinkedHashMap<Byte, byte[]> linkedHashMap2 = this.A;
        if (linkedHashMap2 != null) {
            linkedHashMap2.clear();
            this.A = null;
        }
        TreeSet<ExtremeBase<Byte>> treeSet2 = this.B;
        if (treeSet2 != null) {
            treeSet2.clear();
            this.B = null;
        }
        SparseArray<Byte> sparseArray = this.C;
        if (sparseArray != null) {
            sparseArray.clear();
            this.C = null;
        }
    }

    public void c0(byte[] bArr) {
        int iH = h(bArr, this.l.speed) & 65535;
        short s = (short) (((double) iH) * 0.6875d);
        ByteBuffer byteBuffer = this.f3263b.get(Integer.valueOf(a.e.VEHICLE.ordinal()));
        if (byteBuffer != null) {
            byteBuffer.putShort(this.l.speed.a(), s);
        }
        if (this.k == null || !this.f3265d.c()) {
            return;
        }
        h0(this.l.speed.a(), (short) iH, 2);
    }

    @Override // com.byd.data.collect.subscribe.present.BasePresent
    protected int d() {
        return a.e.ALARM.ordinal() + 1;
    }

    public void d0(byte b2) {
        ByteBuffer byteBuffer;
        ConcurrentHashMap<T, ByteBuffer> concurrentHashMap = this.f3263b;
        a.e eVar = a.e.VEHICLE;
        ByteBuffer byteBuffer2 = concurrentHashMap.get(Integer.valueOf(eVar.ordinal()));
        if (byteBuffer2 == null) {
            return;
        }
        byte b3 = b2 == 1 ? b2 : (byte) 3;
        byte b4 = byteBuffer2.get(this.l.socState.a());
        if ((15 == b2 || b2 == 2) && b4 >= 100) {
            b3 = 4;
        } else if (b2 != 1 && (byteBuffer = this.f3263b.get(Integer.valueOf(a.e.DRIVER.ordinal()))) != null && byteBuffer.get(this.m.driverMotorState.a()) == 2) {
            b3 = 2;
        }
        r(b3, this.l.changeState, eVar);
    }

    public void e0(byte b2) {
        DataEntity dataEntity = this.m.driverMotorState;
        int i2 = 3;
        byte b3 = (byte) ((b2 == 1 || b2 == 2) ? 1 : (b2 == 4 || b2 == 5) ? 2 : 3);
        if (b2 == 1 || b2 == 3) {
            i2 = 1;
        } else if (b2 == 4 || b2 == 6) {
            i2 = 2;
        }
        s0(b3, dataEntity, (byte) i2, dataEntity);
    }

    public void f0(byte[] bArr) {
        ConcurrentHashMap<T, ByteBuffer> concurrentHashMap = this.f3263b;
        a.e eVar = a.e.DRIVER;
        if (concurrentHashMap.get(Integer.valueOf(eVar.ordinal())) == null || !L()) {
            return;
        }
        DataEntity dataEntity = this.m.driverMotorTorque.get(a.c.REAR.ordinal());
        r(h(bArr, dataEntity) + 15000, dataEntity, eVar);
    }

    @Override // com.byd.data.collect.subscribe.present.BasePresent
    public byte[] g(Context context, boolean z, boolean z2) {
        ByteBuffer byteBufferG = G();
        List<ByteBuffer> listF = F(true);
        ByteBuffer byteBuffer = this.f3263b.get(Integer.valueOf(a.e.VEHICLE.ordinal()));
        Objects.requireNonNull(byteBuffer);
        byte[] bArr = (byte[]) byteBuffer.array().clone();
        u0(bArr);
        boolean z3 = bArr[this.l.changeState.a()] == 1;
        byte[] bArr2 = z3 ? new byte[0] : (byte[]) this.f3263b.get(Integer.valueOf(a.e.DRIVER.ordinal())).array().clone();
        byte[] bArr3 = new byte[0];
        if (com.byd.data.common.server.b.h(context).r() && !z3) {
            bArr3 = (byte[]) this.f3263b.get(Integer.valueOf(a.e.ENGINE.ordinal())).array().clone();
        }
        byte[] bArr4 = (byte[]) H().array().clone();
        byte[] bArr5 = (byte[]) x().array().clone();
        byte[] bArr6 = (byte[]) D().array().clone();
        ArrayList arrayList = new ArrayList(listF.size());
        int length = bArr.length + bArr2.length + bArr3.length + bArr4.length + bArr5.length + bArr6.length;
        com.byd.data.common.server.e.a aVarO = com.byd.data.collect.server.i.o(context);
        int i2 = 0;
        int iPosition = 0;
        while (i2 < listF.size()) {
            ByteBuffer byteBuffer2 = listF.get(i2);
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(byteBuffer2.position() + 6 + (i2 == 0 ? length : 0));
            ByteBuffer byteBuffer3 = byteBufferG;
            byteBufferAllocate.put((byte[]) byteBufferG.array().clone());
            if (i2 == 0) {
                byteBufferAllocate.put(bArr).put(bArr2).put(bArr3).put(bArr4).put(bArr5).put((byte[]) byteBuffer2.array().clone()).put(bArr6);
            } else {
                byteBufferAllocate.put((byte[]) byteBuffer2.array().clone());
            }
            String strI = aVarO.i();
            byte[] bArrArray = byteBufferAllocate.array();
            if (z) {
                bArrArray = aVarO.b(bArrArray, strI);
            }
            ByteBuffer byteBufferB = b(strI, bArrArray, z, a.EnumC0065a.REAL_TIME_DATA);
            arrayList.add(byteBufferB);
            iPosition += byteBufferB.position();
            i2++;
            byteBufferG = byteBuffer3;
        }
        final ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(iPosition);
        arrayList.forEach(new Consumer() { // from class: com.byd.data.collect.subscribe.present.f
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ByteBuffer byteBuffer4 = (ByteBuffer) obj;
                byteBufferAllocate2.put(byteBuffer4.array(), 0, byteBuffer4.position());
            }
        });
        byte b2 = this.w;
        this.w = (byte) (b2 >= 127 ? 0 : b2 + 1);
        if (M(false)) {
            com.byd.data.common.i.f.g("BydNsPresent", "vehicle =" + c.a.a.g.d.g(bArr, false));
            com.byd.data.common.i.f.g("BydNsPresent", "driveMotor = " + c.a.a.g.d.g(bArr2, false));
            if (bArr3.length > 2) {
                com.byd.data.common.i.f.g("BydNsPresent", "engine = " + c.a.a.g.d.g(bArr3, false));
            }
            com.byd.data.common.i.f.g("BydNsPresent", "extreme = " + c.a.a.g.d.g(bArr4, false));
            o();
            com.byd.data.common.i.f.g("BydNsPresent", "chargeTemp = " + c.a.a.g.d.g(bArr6, false));
            com.byd.data.common.i.f.g("BydNsPresent", "voltList = " + c.a.a.g.d.g(listF.get(0).array(), false));
        }
        return byteBufferAllocate2.array();
    }

    public void g0(byte b2) {
        ByteBuffer byteBuffer = this.f3263b.get(Integer.valueOf(a.e.VEHICLE.ordinal()));
        DataEntity dataEntity = this.f3265d.vehicleData.carState;
        if (byteBuffer == null || dataEntity == null) {
            return;
        }
        int i2 = 2;
        if (3 == b2) {
            i2 = 1;
        } else if (1 != b2) {
            i2 = 2 == b2 ? 3 : 254;
        }
        byte b3 = (byte) i2;
        boolean z = byteBuffer.get(dataEntity.a()) != b3;
        if (n() && z) {
            d.a.a.f.b.b.a.d(this.f3262a, "power = " + ((int) b2) + " ,ret =" + ((int) b3));
        }
        if (z) {
            byteBuffer.put(dataEntity.a(), b3);
        }
        h0(dataEntity.a(), b2, 1);
    }

    public void h0(int i2, int i3, int i4) {
        com.byd.bms.a.a aVar = this.k;
        if (aVar == null) {
            return;
        }
        aVar.m(i2, i3, i4);
    }

    public void j0(byte b2) {
        com.byd.bms.a.a aVar = this.k;
        boolean z = aVar != null;
        VehicleData vehicleData = this.l;
        if ((vehicleData != null) && z) {
            aVar.m(vehicleData.dcState.a(), b2, 1);
        }
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.byd.data.collect.subscribe.present.BasePresent
    protected void m(DataCollectBean dataCollectBean) {
        this.l = dataCollectBean.vehicleData;
        this.m = dataCollectBean.driverMotorData;
        this.f3263b.put(Integer.valueOf(a.e.VEHICLE.ordinal()), k(dataCollectBean.vehicleData));
        ConcurrentHashMap<T, ByteBuffer> concurrentHashMap = this.f3263b;
        a.e eVar = a.e.DRIVER;
        concurrentHashMap.put(Integer.valueOf(eVar.ordinal()), K(dataCollectBean.driverMotorData, com.byd.data.common.server.b.h(null).q));
        if (dataCollectBean.c()) {
            this.f3263b.put(Integer.valueOf(a.e.ENGINE.ordinal()), k(dataCollectBean.engineData));
        } else if (L()) {
            r(20000, this.m.driverMotorTorque.get(a.c.REAR.ordinal()), eVar);
        }
        this.f3263b.put(Integer.valueOf(a.e.EXTREME.ordinal()), k(dataCollectBean.extremeData));
        this.f3263b.put(Integer.valueOf(a.e.ALARM.ordinal()), k(dataCollectBean.alarmData));
        this.k = new BmsPresent(this);
    }

    public void m0(short s, byte[] bArr) {
        DataEntity dataEntity = this.f3265d.alarmData.brakeSystemAlarm;
        this.C.put(s, Byte.valueOf(i0(dataEntity.a(), (((bArr[dataEntity.startIndex] & 255) >> dataEntity.bitStart) & 1) == 1, (byte) 2)));
    }

    public void n0(byte b2) {
        d0(b2);
        h0(this.l.changeState.a(), b2, 1);
        this.n.A(this.l.changeState.canId, b2);
    }

    public void o0(byte b2) {
        if (this.w == b2) {
        }
    }

    public void p0(byte[] bArr, List<DataEntity> list, a.c cVar) {
        if (this.o <= 0 || this.m == null) {
            return;
        }
        if (cVar == a.c.DUAL) {
            DataEntity dataEntity = list.get(a.c.FRONT.ordinal());
            DataEntity dataEntity2 = list.get(a.c.REAR.ordinal());
            s0(dataEntity.d() == 1 ? e(bArr, dataEntity) : h(bArr, dataEntity), dataEntity, dataEntity2.d() == 1 ? e(bArr, dataEntity2) : h(bArr, dataEntity2), dataEntity2);
            return;
        }
        a.c cVar2 = a.c.REAR;
        if (cVar == cVar2) {
            if (!L()) {
                return;
            }
        } else if (!N()) {
            return;
        }
        DataEntity dataEntity3 = list.get(cVar == cVar2 ? cVar2.ordinal() : a.c.FRONT.ordinal());
        r(dataEntity3.d() == 1 ? e(bArr, dataEntity3) : h(bArr, dataEntity3), dataEntity3, a.e.DRIVER);
    }

    public void q0(byte[] bArr, boolean z) {
        ConcurrentHashMap<T, ByteBuffer> concurrentHashMap = this.f3263b;
        a.e eVar = a.e.DRIVER;
        ByteBuffer byteBuffer = concurrentHashMap.get(Integer.valueOf(eVar.ordinal()));
        if (byteBuffer == null) {
            return;
        }
        if (z || !N()) {
            if (z && L()) {
                r(h(bArr, r5) - 10000, this.m.driverMotorSpeed.get(a.c.REAR.ordinal()), eVar);
                return;
            }
            return;
        }
        List<DataEntity> list = this.m.driverMotorSpeed;
        a.c cVar = a.c.FRONT;
        DataEntity dataEntity = list.get(cVar.ordinal());
        byteBuffer.putShort(dataEntity.a(), (short) (h(bArr, dataEntity) + 5000));
        DataEntity dataEntity2 = this.m.driverMotorTorque.get(cVar.ordinal());
        byteBuffer.putShort(dataEntity2.a(), (short) (h(bArr, dataEntity2) + 15000));
    }

    public void s0(short s, DataEntity dataEntity, short s2, DataEntity dataEntity2) {
        ByteBuffer byteBuffer = this.f3263b.get(Integer.valueOf(a.e.DRIVER.ordinal()));
        if (byteBuffer == null) {
            return;
        }
        boolean z = this.o % a.b.DUAL_DRIVER.ordinal() == 0;
        int iA = dataEntity2.a();
        if (z && iA == dataEntity.a()) {
            iA += this.m.totalSize - 2;
        }
        if (dataEntity.d() == 1) {
            byte bA = dataEntity.a();
            if (!N()) {
                s = s2;
            }
            byteBuffer.put(bA, (byte) s);
            if (z) {
                byteBuffer.put(iA, (byte) s2);
                return;
            }
            return;
        }
        byte bA2 = dataEntity.a();
        if (!N()) {
            s = s2;
        }
        byteBuffer.putShort(bA2, s);
        if (z) {
            byteBuffer.putShort(iA, s2);
        }
    }

    public void t(final byte b2, byte[] bArr) {
        synchronized (this.j) {
            TreeSet<ExtremeBase<Byte>> treeSetI = I(b2, bArr);
            if (!treeSetI.isEmpty()) {
                this.B.removeIf(new Predicate() { // from class: com.byd.data.collect.subscribe.present.i
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return NsPresenter.P(b2, (ExtremeBase) obj);
                    }
                });
                this.B.add(treeSetI.first());
                this.B.add(treeSetI.last());
                treeSetI.clear();
            }
        }
    }

    public void t0(byte[] bArr, boolean z) {
        if (z) {
            EngineData engineData = this.f3265d.engineData;
            ByteBuffer byteBuffer = this.f3263b.get(Integer.valueOf(a.e.ENGINE.ordinal()));
            if (byteBuffer != null) {
                byte bE = e(bArr, engineData.engineStatus);
                int i2 = 1;
                if (bE != 1 && 3 != bE) {
                    i2 = bE == 0 ? 2 : 255;
                }
                byteBuffer.put(engineData.engineStatus.a(), (byte) i2);
                byteBuffer.putShort(engineData.engineSpeed.a(), (short) (((double) h(bArr, engineData.engineSpeed)) * 0.25d));
            }
        }
    }

    public void v0(short s, short s2) {
        int iMax;
        DataEntity dataEntity = this.l.insulationResistance.get(a.c.FRONT.ordinal());
        if ((65535 & s2) > 60000) {
            s2 = -5536;
        }
        if (s == dataEntity.canId && s2 != this.p) {
            this.p = s2;
            iMax = Math.max((int) s2, (int) this.q);
        } else if (this.q != s2) {
            this.q = s2;
            iMax = Math.max((int) s2, (int) this.p);
        } else {
            iMax = 0;
        }
        if (iMax != 0) {
            this.f3263b.get(Integer.valueOf(a.e.VEHICLE.ordinal())).putShort(dataEntity.a(), (short) iMax);
        }
    }

    public void w(short s, byte b2, byte[] bArr, boolean z) {
        if (!z ? s == 1083 || s == 842 : s == 1271 && b2 == 1) {
            k0(bArr, z, s, b2);
        }
        if (this.k == null) {
            return;
        }
        if (z) {
            if (s != 1080) {
                return;
            }
        } else if (s != 1083) {
            return;
        }
        l0(bArr, z);
    }

    public void w0(byte[] bArr, byte b2, boolean z) {
        this.x = (short) (h(bArr, this.l.totalCurrent) + 5000);
        short s = this.x;
        DataEntity dataEntity = this.l.totalCurrent;
        a.e eVar = a.e.VEHICLE;
        r(s, dataEntity, eVar);
        byte bE = e(bArr, this.l.socState);
        r(bE, this.l.socState, eVar);
        h0(this.l.totalCurrent.a(), this.x, 2);
        h0(z ? this.l.socState.a() : (byte) 7, bE, 1);
        d0(b2);
    }

    public void x0(byte[] bArr) {
        if (bArr.length == 8) {
            u((byte) (bArr[0] & 255), bArr);
        }
    }

    @Override // com.byd.data.common.c.b
    public void y(byte[] bArr) {
    }

    public void y0(byte[] bArr, short s, boolean z) {
        DataEntity dataEntity = this.l.insulationResistance.get(a.c.REAR.ordinal());
        short sH = h(bArr, dataEntity);
        v0(s, sH);
        this.s = bArr[0];
        int i2 = this.s & 255;
        if (this.t == 0) {
            this.t = (byte) (i2 / 3);
            d.a.a.f.b.b.a.h(this.f3262a, "tempVolt =" + i2 + ", serialId = " + ((int) this.t));
        }
        this.u = (byte) (bArr[1] & 255);
        k0(bArr, z, s, (byte) 0);
        boolean z2 = (s == 1082) & z;
        com.byd.bms.a.a aVar = this.k;
        if (z2 & (aVar != null)) {
            if (aVar.n()) {
                this.k.o(this.s & 255, true);
            }
            h0(dataEntity.a(), sH, 2);
            if (E()) {
                this.k.D(bArr, true);
            }
        }
        h0(z ? dataEntity.a() : (byte) 8, sH, 2);
    }

    public void z0(byte[] bArr) {
        e0(e(bArr, this.m.driverMotorState));
    }
}
