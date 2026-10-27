package ts.car.diag.b;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import c.a.a.a.h;
import com.ts.lib.caradapter.CarAdapterManager;
import com.ts.lib.caradapter.setting.SettingAdapterManager;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ts.car.diag.manager.f;
import ts.car.diag.manager.i;
import ts.car.diag.manager.j;
import ts.car.diag.manager.l;
import ts.car.diag.manager.m;
import ts.car.diag.manager.n;
import ts.car.diag.manager.o;
import ts.car.diag.manager.q;
import ts.car.diag.manager.w;
import ts.car.diag.manager.x;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class d extends ts.car.diag.b.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    h f1562a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private o f1563b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private i f1564c;
    private m d;
    private f e;
    private w f;
    private C0052d g;
    private Context h;
    private byte i = -1;
    private byte j = -1;
    private CarAdapterManager k;
    private x l;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements x.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.a.a.a.c f1565a;

        a(c.a.a.a.c cVar) {
            this.f1565a = cVar;
        }

        @Override // ts.car.diag.manager.x.c
        public void a(byte b2) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(Byte.valueOf(b2));
            String strE = d.this.l.e();
            String strF = d.this.l.f();
            String strH = d.this.l.h();
            byte[] bytes = strE.getBytes(StandardCharsets.US_ASCII);
            byte[] bytes2 = strF.getBytes(StandardCharsets.US_ASCII);
            byte[] bytes3 = strH.getBytes(StandardCharsets.US_ASCII);
            for (byte b3 : bytes) {
                arrayList.add(Byte.valueOf(b3));
            }
            for (byte b4 : bytes2) {
                arrayList.add(Byte.valueOf(b4));
            }
            for (byte b5 : bytes3) {
                arrayList.add(Byte.valueOf(b5));
            }
            StringBuffer stringBuffer = new StringBuffer();
            int size = arrayList.size();
            boolean z = false;
            while (size <= 99) {
                stringBuffer.append(" ");
                size++;
                z = true;
            }
            if (z) {
                for (byte b6 : stringBuffer.toString().getBytes(StandardCharsets.US_ASCII)) {
                    arrayList.add(Byte.valueOf(b6));
                }
            }
            q.h("HalRoutineControl", "checkVrResult：" + ((int) b2) + strE + strF + strH);
            d dVar = d.this;
            dVar.L(dVar.n(this.f1565a, arrayList));
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements w.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ c.a.a.a.c f1567a;

        b(c.a.a.a.c cVar) {
            this.f1567a = cVar;
        }

        @Override // ts.car.diag.manager.w.e
        public void a(boolean z) {
            boolean z2 = !z;
            q.h("HalRoutineControl", " playResult " + (z2 ? 1 : 0));
            d dVar = d.this;
            dVar.L(dVar.m(this.f1567a, z2 ? (byte) 1 : (byte) 0));
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class c extends CountDownTimer {
        c(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            q.h("HalRoutineControl", "mPkiTimer onFinish time out(10s)");
            String string = Settings.Global.getString(d.this.h.getContentResolver(), "beanpki_applay_cert");
            if (!"true".equals(string)) {
                q.h("HalRoutineControl", "beanpki_applay_cert pkiStatus : " + string);
                return;
            }
            q.h("HalRoutineControl", "beanpki_applay_cert pkiStatus is: " + string);
            Settings.Global.putString(d.this.h.getContentResolver(), "beanpki_applay_cert", "false");
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            q.h("HalRoutineControl", "CountDownTimer onTick..");
        }
    }

    /* JADX INFO: renamed from: ts.car.diag.b.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    private class C0052d implements f.l {
        private C0052d() {
        }

        @Override // ts.car.diag.manager.f.l
        public void a(short s, byte b2, boolean z) {
            d dVar = d.this;
            dVar.L(dVar.o(s, b2, !z ? (byte) 1 : (byte) 0));
        }

        /* synthetic */ C0052d(d dVar, a aVar) {
            this();
        }
    }

    public d(h hVar) {
        new c(10000L, 1000L);
        this.f1562a = hVar;
    }

    private void C(c.a.a.a.c cVar) {
        ArrayList<Byte> arrayList = cVar.f1286c;
        int i = 2;
        if (arrayList.size() <= 2) {
            q.b("HalRoutineControl", "ROUTINE_PLAY_USB_MUSIC: data error");
            L(m(cVar, (byte) 1));
            return;
        }
        int size = arrayList.size() - 2;
        boolean z = false;
        Byte b2 = arrayList.get(0);
        Byte b3 = arrayList.get(1);
        byte[] bArr = new byte[size];
        int i2 = 0;
        while (i2 < size) {
            bArr[i2] = arrayList.get(i).byteValue();
            i2++;
            i++;
        }
        String str = new String(bArr);
        q.i("HalRoutineControl", "31 01 FE07 usbDeviceType : " + b2 + " optionType : " + b3 + "  videoName : " + str);
        if (b3.byteValue() != 0) {
            boolean z2 = !this.f.e();
            q.h("HalRoutineControl", " stopResult " + (z2 ? 1 : 0));
            L(m(cVar, z2 ? (byte) 1 : (byte) 0));
            return;
        }
        List<File> listA = ts.car.diag.d.c.a(this.h);
        q.h("HalRoutineControl", " StorageUtil.getUpanPath : " + listA);
        if (listA == null || listA.size() == 0) {
            q.b("HalRoutineControl", " playUsbFlashDiskVideo upanPath is null");
            L(m(cVar, (byte) 1));
            return;
        }
        File file = null;
        Iterator<File> it = listA.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            File file2 = new File(it.next(), str);
            boolean zExists = file2.exists();
            q.h("HalRoutineControl", "upanPath " + file2.getAbsolutePath() + " exists() " + zExists);
            if (zExists) {
                z = zExists;
                file = file2;
                break;
            } else {
                z = zExists;
                file = file2;
            }
        }
        String absolutePath = file.getAbsolutePath();
        q.h("HalRoutineControl", "playUsbFlashDiskVideo path " + absolutePath + " exists() " + z);
        if (z) {
            this.f.k(new b(cVar));
            this.f.l(this.h, absolutePath);
            return;
        }
        q.b("HalRoutineControl", " path " + absolutePath + " is not exists, fail");
        L(m(cVar, (byte) 1));
    }

    private void D(c.a.a.a.c cVar) {
        ArrayList<Byte> arrayList = cVar.f1286c;
        int i = 2;
        if (arrayList.size() <= 2) {
            q.b("HalRoutineControl", "ROUTINE_PLAY_USB_MUSIC: data error");
            L(m(cVar, (byte) 1));
            return;
        }
        int size = arrayList.size() - 2;
        int i2 = 0;
        Byte b2 = arrayList.get(0);
        Byte b3 = arrayList.get(1);
        byte[] bArr = new byte[size];
        while (i2 < size) {
            bArr[i2] = arrayList.get(i).byteValue();
            i2++;
            i++;
        }
        String str = new String(bArr);
        q.i("HalRoutineControl", "31 01 FE04 usbDeviceType : " + b2 + " optionType : " + b3 + " musicName : " + str);
        this.e.n(b3.byteValue(), str);
    }

    private void E(c.a.a.a.c cVar) {
        q.e("HalRoutineControl", "resetFactory.in");
        Object carAdapterManager = this.k.getCarAdapterManager("setting");
        byte b2 = 1;
        if (carAdapterManager != null) {
            try {
                x();
                if (((SettingAdapterManager) carAdapterManager).factoryResetAll(1) == 0) {
                    b2 = 0;
                }
            } catch (Exception e) {
                q.b("HalRoutineControl", e.toString());
            }
        } else {
            q.b("HalRoutineControl", "mCarAdapterManager.getCarAdapterManager == null");
        }
        q.h("HalRoutineControl", "resetFactory.out result : " + ((int) b2));
        L(m(cVar, b2));
    }

    private void F(c.a.a.a.c cVar) {
        byte b2 = cVar.f1284a;
        if (b2 == 1) {
            v(cVar);
        } else if (b2 == 3) {
            q(cVar);
        }
    }

    private void G(c.a.a.a.c cVar) {
        byte b2 = cVar.f1284a;
        if (b2 == 1) {
            p(cVar);
        } else if (b2 == 3) {
            r(cVar);
        }
    }

    private void H(c.a.a.a.c cVar) {
        byte b2 = cVar.f1284a;
        if (b2 == 1) {
            P(cVar, 3);
        } else if (b2 == 3) {
            s(cVar, 3);
        }
    }

    private void I(c.a.a.a.c cVar) {
        byte b2 = cVar.f1284a;
        if (b2 == 1) {
            P(cVar, 1);
        } else if (b2 == 3) {
            s(cVar, 1);
        }
    }

    private void J(c.a.a.a.c cVar) {
        byte b2 = cVar.f1284a;
        if (b2 == 1) {
            P(cVar, 0);
        } else if (b2 == 3) {
            s(cVar, 0);
        }
    }

    private void K(c.a.a.a.c cVar) {
        byte b2 = cVar.f1284a;
        if (b2 == 1) {
            Q(cVar);
        } else if (b2 == 3) {
            t(cVar);
        }
    }

    private void M(c.a.a.a.c cVar) {
    }

    private void P(c.a.a.a.c cVar, int i) {
        boolean zQ = this.d.q(i);
        q.h("HalRoutineControl", "startMapVerify map is " + i + " result " + zQ);
        L(m(cVar, !zQ ? (byte) 1 : (byte) 0));
    }

    private void Q(c.a.a.a.c cVar) {
        q.h("HalRoutineControl", "startVrVerify");
        L(m(cVar, !this.l.l() ? (byte) 1 : (byte) 0));
    }

    private void R(c.a.a.a.c cVar) {
        int i;
        int i2;
        ArrayList<Byte> arrayList = cVar.f1286c;
        if (arrayList.size() < 67) {
            L(m(cVar, (byte) 1));
            q.b("HalRoutineControl", " startWifiAutoConnect data error , data size is " + arrayList.size());
            return;
        }
        StringBuffer stringBuffer = new StringBuffer("");
        StringBuffer stringBuffer2 = new StringBuffer("");
        StringBuffer stringBuffer3 = new StringBuffer("");
        int i3 = 0;
        while (true) {
            i = 17;
            if (i3 >= 17) {
                break;
            }
            stringBuffer.append((char) arrayList.get(i3).byteValue());
            i3++;
        }
        while (true) {
            if (i >= 47) {
                break;
            }
            stringBuffer2.append((char) arrayList.get(i).byteValue());
            i++;
        }
        for (i2 = 47; i2 < 67; i2++) {
            stringBuffer3.append((char) arrayList.get(i2).byteValue());
        }
        byte bByteValue = arrayList.get(67).byteValue();
        String string = stringBuffer.toString();
        String string2 = stringBuffer2.toString();
        String string3 = stringBuffer3.toString();
        q.i("HalRoutineControl", "ReadWifiMacfromreq:" + string);
        q.i("HalRoutineControl", "ReadWifiNamefromreq:" + string2);
        byte bL = this.f1563b.l(string, string2, string3, bByteValue);
        q.h("HalRoutineControl", "wifiStationAutoConenct:" + ((int) bL));
        L(m(cVar, bL));
    }

    private void S(c.a.a.a.c cVar) {
        byte b2 = cVar.f1284a;
        if (b2 == 1) {
            R(cVar);
        } else if (b2 == 3) {
            y(cVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public c.a.a.a.d m(c.a.a.a.c cVar, byte b2) {
        c.a.a.a.d dVar = new c.a.a.a.d();
        dVar.f1287a = cVar.f1284a;
        dVar.f1288b = cVar.f1285b;
        dVar.f1289c.add(Byte.valueOf(b2));
        q.h("HalRoutineControl", "Diag_RoutineControlResp is:" + dVar.toString());
        return dVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public c.a.a.a.d n(c.a.a.a.c cVar, ArrayList<Byte> arrayList) {
        c.a.a.a.d dVar = new c.a.a.a.d();
        dVar.f1287a = cVar.f1284a;
        dVar.f1288b = cVar.f1285b;
        dVar.f1289c = arrayList;
        q.h("HalRoutineControl", "Diag_RoutineControlResp is:" + dVar.toString());
        return dVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public c.a.a.a.d o(short s, byte b2, byte b3) {
        c.a.a.a.d dVar = new c.a.a.a.d();
        dVar.f1287a = b2;
        dVar.f1288b = s;
        dVar.f1289c.add(Byte.valueOf(b3));
        q.h("HalRoutineControl", "Diag_RoutineControlResp is:" + dVar.toString());
        return dVar;
    }

    private void p(c.a.a.a.c cVar) {
        byte bA = 1;
        if (cVar.f1284a == 1) {
            ArrayList<Byte> arrayList = cVar.f1286c;
            StringBuffer stringBuffer = new StringBuffer("");
            for (int i = 0; i < 17; i++) {
                stringBuffer.append((char) arrayList.get(i).byteValue());
            }
            Byte b2 = arrayList.get(17);
            q.i("HalRoutineControl", "ReadBTMacfromreq: " + ((Object) stringBuffer) + " device_index : " + b2);
            if (b2.byteValue() == 0 || b2.byteValue() == 1) {
                this.i = b2.byteValue();
                bA = this.f1564c.a(stringBuffer.toString(), b2.byteValue());
            } else {
                q.b("HalRoutineControl", "bt device_index is not BT_0 or BT_1");
            }
            L(m(cVar, bA));
        }
    }

    private void q(c.a.a.a.c cVar) {
        byte bC;
        byte b2 = this.j;
        if (b2 == 0 || b2 == 1) {
            bC = this.f1564c.c(this.j);
            q.h("HalRoutineControl", "checkoutBTConnectResult deviceIndex" + ((int) this.j) + " result " + ((int) bC));
        } else {
            bC = -1;
            q.b("HalRoutineControl", "checkoutBTConnectResult not bt index" + ((int) this.j) + " result -1");
        }
        L(m(cVar, bC));
    }

    private void r(c.a.a.a.c cVar) {
        q.h("HalRoutineControl", "checkBtDiscoveryResult discoveryBtIndex " + ((int) this.i));
        ArrayList<Byte> arrayListD = this.f1564c.d(this.i);
        q.h("HalRoutineControl", "checkBtDiscoveryResult discoveryBtIndex" + ((int) this.i) + " checkResult " + arrayListD);
        L(n(cVar, arrayListD));
    }

    private void s(c.a.a.a.c cVar, int i) {
        boolean zG = this.d.g(i);
        boolean zL = this.d.l(i);
        boolean zN = this.d.n(i);
        String strD = this.d.d(i);
        String strC = this.d.c(i);
        String strI = this.d.i();
        String strE = this.d.e(i);
        Integer numF = this.d.f(i);
        q.h("HalRoutineControl", "checkMapVerify map is " + i + " pairResult：" + zG + "，normalConfig：" + zL + "naviZoneNormal：" + zN + "，mapDataVersion：" + strD + "，mapAreaCode：" + strC + "，systemCountryCode：" + strI + "，mapPackageName：" + strE + "，mapSize：" + numF);
        ArrayList<Byte> arrayList = new ArrayList<>();
        arrayList.add(Byte.valueOf(!zG ? (byte) 1 : (byte) 0));
        arrayList.add(Byte.valueOf(!zL ? (byte) 1 : (byte) 0));
        arrayList.add(Byte.valueOf(!zN ? (byte) 1 : (byte) 0));
        if (TextUtils.isEmpty(strD)) {
            strD = "                              ";
        }
        if (TextUtils.isEmpty(strC)) {
            strC = "  ";
        }
        if (TextUtils.isEmpty(strI)) {
            strI = "  ";
        }
        if (TextUtils.isEmpty(strE)) {
            strE = "  ";
        }
        StringBuffer stringBuffer = new StringBuffer(strD);
        for (int length = stringBuffer.length(); length < 30; length++) {
            stringBuffer.append(" ");
        }
        byte[] bytes = stringBuffer.toString().getBytes(StandardCharsets.US_ASCII);
        q.h("HalRoutineControl", " mapDataVersionData.length " + bytes.length);
        int length2 = bytes.length;
        for (int i2 = 0; i2 < length2; i2++) {
            arrayList.add(Byte.valueOf(bytes[i2]));
        }
        byte[] bytes2 = strC.getBytes(StandardCharsets.US_ASCII);
        q.h("HalRoutineControl", " mapAreaCodeData.length " + bytes2.length);
        int length3 = bytes2.length;
        for (int i3 = 0; i3 < length3; i3++) {
            arrayList.add(Byte.valueOf(bytes2[i3]));
        }
        byte[] bytes3 = strI.getBytes(StandardCharsets.US_ASCII);
        q.h("HalRoutineControl", " systemCountryCodeData.length " + bytes3.length);
        int length4 = bytes3.length;
        for (int i4 = 0; i4 < length4; i4++) {
            arrayList.add(Byte.valueOf(bytes3[i4]));
        }
        byte[] bytes4 = strE.getBytes(StandardCharsets.US_ASCII);
        q.h("HalRoutineControl", " mapPackageNameData.length " + bytes4.length);
        int length5 = bytes4.length;
        for (int i5 = 0; i5 < length5; i5++) {
            arrayList.add(Byte.valueOf(bytes4[i5]));
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        for (int length6 = bytes2.length + bytes3.length + bytes4.length; length6 <= 62; length6++) {
            stringBuffer2.append(" ");
        }
        String string = stringBuffer2.toString();
        if (string.length() > 0) {
            for (byte b2 : string.getBytes(StandardCharsets.US_ASCII)) {
                arrayList.add(Byte.valueOf(b2));
            }
        }
        q.a("HalRoutineControl", " checkMapVerify blank length " + string.length());
        for (byte b3 : ts.car.diag.b.b.d(numF.intValue())) {
            arrayList.add(Byte.valueOf(b3));
        }
        q.a("HalRoutineControl", " checkMapVerify result size " + arrayList.size());
        L(n(cVar, arrayList));
    }

    private void t(c.a.a.a.c cVar) {
        this.l.d(new a(cVar));
    }

    private void u(c.a.a.a.c cVar) {
        byte b2 = cVar.f1284a;
    }

    private void v(c.a.a.a.c cVar) {
        ArrayList<Byte> arrayList = cVar.f1286c;
        if (arrayList.size() < 18) {
            q.b("HalRoutineControl", "connectBtDevice: data error");
            L(m(cVar, (byte) 1));
            return;
        }
        byte[] bArr = new byte[17];
        byte[] bArr2 = new byte[arrayList.size() - 18];
        byte bByteValue = -1;
        for (int i = 0; i < arrayList.size(); i++) {
            Byte b2 = arrayList.get(i);
            if (i < 17) {
                bArr[i] = b2.byteValue();
            } else if (i == 17) {
                bByteValue = b2.byteValue();
            } else {
                bArr2[i - 18] = b2.byteValue();
            }
        }
        this.j = bByteValue;
        String str = new String(bArr, StandardCharsets.US_ASCII);
        String str2 = new String(bArr2, StandardCharsets.US_ASCII);
        q.i("HalRoutineControl", "connectBtDevice: deviceIndex " + ((int) bByteValue) + " macAddress " + str + " name " + str2);
        L(m(cVar, this.f1564c.e(bByteValue, str, str2)));
    }

    private void w(c.a.a.a.c cVar) {
        S(cVar);
    }

    private void x() {
        Intent intent = new Intent("android.intent.action.FACTORY_RESET");
        intent.setPackage("android");
        intent.addFlags(268435456);
        intent.putExtra("android.intent.extra.REASON", "Diag resetFactory");
        intent.putExtra("android.intent.extra.WIPE_EXTERNAL_STORAGE", false);
        intent.putExtra("com.android.internal.intent.extra.WIPE_ESIMS", false);
        this.h.sendBroadcast(intent);
        q.e("HalRoutineControl", "resetFactory sendBroadcast ACTION_FACTORY_RESET success");
    }

    private void y(c.a.a.a.c cVar) {
        byte b2 = 3;
        if (this.f1563b.j() == 1) {
            b2 = 0;
            q.h("HalRoutineControl", "wifiAutoConnect status:0");
        } else if (this.f1563b.j() == 3) {
            q.h("HalRoutineControl", "wifiAutoConnect status:3");
        } else {
            q.h("HalRoutineControl", "wifiAutoConnect status:1");
            b2 = 1;
        }
        L(m(cVar, b2));
    }

    public void A(o oVar, i iVar, j jVar, n nVar, ts.car.diag.manager.h hVar, m mVar, l lVar) {
        this.f1563b = oVar;
        this.f1564c = iVar;
        this.d = mVar;
        this.e = f.g(this.h);
        C0052d c0052d = new C0052d(this, null);
        this.g = c0052d;
        this.e.q(c0052d);
        this.f = new w();
        CarAdapterManager carAdapterManager = CarAdapterManager.getInstance(this.h);
        this.k = carAdapterManager;
        carAdapterManager.connect();
        if (this.l == null) {
            this.l = new x();
        }
    }

    public boolean B(c.a.a.a.c cVar) {
        short s = cVar.f1285b;
        if (s == -508) {
            D(cVar);
            return true;
        }
        if (s == -505) {
            C(cVar);
            return true;
        }
        if (s != -504) {
            return false;
        }
        this.e.j();
        return true;
    }

    public void L(c.a.a.a.d dVar) {
        if (this.f1562a == null) {
            q.b("HalRoutineControl", "sendResultToHal mService == null");
            return;
        }
        try {
            q.h("HalRoutineControl", "sendResultToHal " + Integer.toHexString(dVar.f1288b) + " res " + dVar);
            this.f1562a.s(dVar);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void N(Context context) {
        this.h = context;
        if (this.l == null) {
            this.l = new x();
        }
        this.l.i(context);
    }

    public void O(h hVar) {
        this.f1562a = hVar;
    }

    public void z(c.a.a.a.c cVar) {
        q.g(" handleRoutineControl " + cVar);
        if (B(cVar)) {
        }
        short s = cVar.f1285b;
        if (s == -480) {
            F(cVar);
            return;
        }
        if (s == -464) {
            w(cVar);
            return;
        }
        if (s == -414) {
            E(cVar);
            return;
        }
        if (s == 8979) {
            K(cVar);
            return;
        }
        if (s == 8983) {
            H(cVar);
            return;
        }
        if (s == -112) {
            u(cVar);
            return;
        }
        if (s == -111) {
            M(cVar);
            return;
        }
        switch (s) {
            case -476:
                G(cVar);
                break;
            case -475:
                J(cVar);
                break;
            case -474:
                I(cVar);
                break;
        }
    }
}
