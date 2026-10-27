package ts.car.diag.manager;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.GnssStatus;
import android.os.CountDownTimer;
import com.byd.mediacenter.api.media.MediaControl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class k extends j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f1606b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private SensorManager f1607c;
    private SensorEventListener h;
    private SensorEventListener i;
    private u j;
    GnssStatus k;
    private byte d = 0;
    private boolean e = false;
    final byte[] f = {0, 0, 0, 0, 0, 0};
    final byte[] g = {0, 0, 0, 0, 0, 0};
    boolean l = false;
    boolean m = false;
    boolean n = false;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements SensorEventListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Sensor f1608a;

        a(Sensor sensor) {
            this.f1608a = sensor;
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
            q.h("DIAG-GPSImpl", "onAccuracyChanged accuracy:" + i);
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            k kVar = k.this;
            kVar.m = true;
            kVar.f1607c.unregisterListener(k.this.h, this.f1608a);
            int iRound = Math.round(sensorEvent.values[0] * 1000.0f);
            byte[] bArr = k.this.f;
            bArr[0] = (byte) ((iRound >> 8) & MediaControl.ACTION_NONE);
            bArr[1] = (byte) (iRound & MediaControl.ACTION_NONE);
            int iRound2 = Math.round(sensorEvent.values[1] * 1000.0f);
            byte[] bArr2 = k.this.f;
            bArr2[2] = (byte) ((iRound2 >> 8) & MediaControl.ACTION_NONE);
            bArr2[3] = (byte) (iRound2 & MediaControl.ACTION_NONE);
            int iRound3 = Math.round(sensorEvent.values[2] * 1000.0f);
            byte[] bArr3 = k.this.f;
            bArr3[4] = (byte) ((iRound3 >> 8) & MediaControl.ACTION_NONE);
            bArr3[5] = (byte) (iRound3 & MediaControl.ACTION_NONE);
            q.h("DIAG-GPSImpl", "gyro onSensorChanged source data:" + Arrays.toString(sensorEvent.values));
            q.h("DIAG-GPSImpl", "gyro onSensorChanged:" + Arrays.toString(k.this.f));
            k.this.q();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements SensorEventListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Sensor f1610a;

        b(Sensor sensor) {
            this.f1610a = sensor;
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
            q.h("DIAG-GPSImpl", "acc onAccuracyChanged accuracy:" + i);
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            k kVar = k.this;
            kVar.n = true;
            kVar.f1607c.unregisterListener(k.this.i, this.f1610a);
            int iRound = Math.round(sensorEvent.values[0] * 1000.0f);
            byte[] bArr = k.this.g;
            bArr[0] = (byte) ((iRound >> 8) & MediaControl.ACTION_NONE);
            bArr[1] = (byte) (iRound & MediaControl.ACTION_NONE);
            int iRound2 = Math.round(sensorEvent.values[1] * 1000.0f);
            byte[] bArr2 = k.this.g;
            bArr2[2] = (byte) ((iRound2 >> 8) & MediaControl.ACTION_NONE);
            bArr2[3] = (byte) (iRound2 & MediaControl.ACTION_NONE);
            int iRound3 = Math.round(sensorEvent.values[2] * 1000.0f);
            byte[] bArr3 = k.this.g;
            bArr3[4] = (byte) ((iRound3 >> 8) & MediaControl.ACTION_NONE);
            bArr3[5] = (byte) (iRound3 & MediaControl.ACTION_NONE);
            q.h("DIAG-GPSImpl", "gyro onSensorChanged source data:" + Arrays.toString(sensorEvent.values));
            q.h("DIAG-GPSImpl", "acc onSensorChanged:" + Arrays.toString(k.this.g));
            k.this.q();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class c extends CountDownTimer {
        c(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            q.h("DIAG-GPSImpl", "onFinish  time out(5s)");
            k.this.d = (byte) 0;
            k.this.e = false;
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }
    }

    public k(Context context) {
        new c(5000L, 1000L);
        this.f1606b = context;
    }

    private void p() {
        this.l = true;
        this.m = false;
        this.n = false;
        Sensor defaultSensor = this.f1607c.getDefaultSensor(4);
        a aVar = new a(defaultSensor);
        this.h = aVar;
        this.f1607c.registerListener(aVar, defaultSensor, 3);
        Sensor defaultSensor2 = this.f1607c.getDefaultSensor(1);
        b bVar = new b(defaultSensor2);
        this.i = bVar;
        this.f1607c.registerListener(bVar, defaultSensor2, 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void q() {
        q.h("DIAG-GPSImpl", "sendSensorData mHasGyroSensorData " + this.m + " mHasAccSensorData " + this.n + " isRequesting " + this.l);
        if (this.m && this.n) {
            this.l = false;
            if (this.j != null) {
                this.j.a(new byte[]{this.f[0], this.f[1], this.f[2], this.f[3], this.f[4], this.f[5], this.g[0], this.g[1], this.g[2], this.g[3], this.g[4], this.g[5]});
            }
        }
    }

    @Override // ts.car.diag.manager.j
    public byte a() {
        byte b2 = b();
        byte b3 = !this.e ? (byte) 0 : b2 < 3 ? (byte) 1 : (byte) 4;
        q.h("DIAG-GPSImpl", " getGnssFixStateType status " + this.e + " totalSatelliteCount " + ((int) b2) + " fixStateType " + ((int) b3));
        return b3;
    }

    @Override // ts.car.diag.manager.j
    public byte b() {
        GnssStatus gnssStatus = this.k;
        if (gnssStatus == null) {
            q.b("DIAG-GPSImpl", "getGnssTotalSatelliteCount mGnssStatus is null");
            return (byte) 0;
        }
        int satelliteCount = gnssStatus.getSatelliteCount();
        q.h("DIAG-GPSImpl", "getGnssTotalSatelliteCount " + satelliteCount);
        return (byte) satelliteCount;
    }

    @Override // ts.car.diag.manager.j
    public byte[] d() {
        byte[] bArr = new byte[4];
        if (this.k == null) {
            return bArr;
        }
        ArrayList arrayList = new ArrayList();
        int satelliteCount = this.k.getSatelliteCount();
        int i = 0;
        for (int i2 = 0; i2 < satelliteCount; i2++) {
            arrayList.add(Float.valueOf(this.k.getCn0DbHz(i2)));
        }
        int size = arrayList.size();
        if (size > 1) {
            Collections.sort(arrayList);
        }
        q.h("DIAG-GPSImpl", " getStrongest4Cn0DbHz all is " + arrayList);
        for (int i3 = size - 1; i3 >= 0; i3--) {
            bArr[i] = ((Float) arrayList.get(i3)).byteValue();
            i++;
            if (i >= 4) {
                break;
            }
        }
        q.h("DIAG-GPSImpl", " getStrongest4Cn0DbHz data is " + Arrays.toString(bArr));
        return bArr;
    }

    @Override // ts.car.diag.manager.j
    public byte e() {
        GnssStatus gnssStatus = this.k;
        if (gnssStatus == null) {
            q.b("DIAG-GPSImpl", " getUsedInFixCount mGnssStatus == null");
            return (byte) 0;
        }
        int satelliteCount = gnssStatus.getSatelliteCount();
        byte b2 = 0;
        for (int i = 0; i < satelliteCount; i++) {
            if (this.k.usedInFix(i)) {
                b2 = (byte) (b2 + 1);
            }
        }
        q.h("DIAG-GPSImpl", "getUsedInFixCount " + ((int) b2));
        return b2;
    }

    @Override // ts.car.diag.manager.j
    public void f(Context context) {
        q.h("DIAG-GPSImpl", "gpsInit enter:");
        this.f1607c = (SensorManager) this.f1606b.getSystemService("sensor");
    }

    @Override // ts.car.diag.manager.j
    public byte g() {
        q.h("DIAG-GPSImpl", "readGpsInfo enter -- count ==  " + ((int) this.d));
        return this.d;
    }

    @Override // ts.car.diag.manager.j
    public byte h() {
        q.h("DIAG-GPSImpl", "readGpsLocateStatus enter -- status == " + this.e);
        return this.e ? (byte) 1 : (byte) 2;
    }

    @Override // ts.car.diag.manager.j
    public void i(u uVar) {
        this.j = uVar;
        q.h("DIAG-GPSImpl", "readGyroStatus enter isRequesting " + this.l);
        if (!this.l) {
            p();
            return;
        }
        byte[] bArr = this.f;
        byte[] bArr2 = this.g;
        byte[] bArr3 = {bArr[0], bArr[1], bArr[2], bArr[3], bArr[4], bArr[5], bArr2[0], bArr2[1], bArr2[2], bArr2[3], bArr2[4], bArr2[5]};
        u uVar2 = this.j;
        if (uVar2 != null) {
            uVar2.a(bArr3);
        }
        q.b("DIAG-GPSImpl", "readGyroStatus isRequesting ，send last data");
    }
}
