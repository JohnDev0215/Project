package com.byd.data.common.server;

import android.app.Service;
import android.content.Intent;
import android.hardware.bydauto.power.AbsBYDAutoPowerListener;
import android.hardware.bydauto.power.BYDAutoPowerDevice;
import android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener;
import android.hardware.bydauto.vehiclehealth.BYDAutoVehicleHealthDevice;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import com.byd.data.common.http.ApiRetrofit;
import com.byd.data.common.i.f;
import com.byd.data.common.i.g;
import com.byd.data.common.i.i;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class CloudCanService extends Service implements com.byd.data.common.server.e.b, com.byd.data.common.h.a.a {
    private Handler j;
    private com.byd.data.common.server.c.a k;
    private com.byd.data.common.http.b m;
    private com.byd.data.common.h.b.a n;
    private com.byd.data.common.h.c.a o;

    /* JADX INFO: renamed from: b */
    private final String f3447b = "CloudCanService" + com.byd.data.common.i.b.f3423a;

    /* JADX INFO: renamed from: c */
    private final byte f3448c = 12;

    /* JADX INFO: renamed from: d */
    private final byte f3449d = 24;

    /* JADX INFO: renamed from: e */
    private final byte f3450e = 48;

    /* JADX INFO: renamed from: f */
    private final byte f3451f = 96;

    /* JADX INFO: renamed from: g */
    private final byte f3452g = 97;

    /* JADX INFO: renamed from: h */
    private final byte f3453h = 98;

    /* JADX INFO: renamed from: i */
    private final byte f3454i = 99;
    private boolean l = true;

    private void e(Message message, com.byd.data.common.g.a aVar, com.byd.data.common.c.c.b bVar, int i2) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        int i3 = message.arg2 - 1;
        message.arg2 = i3;
        boolean z = i3 <= 0;
        if (z) {
            message.arg2 = bVar.l(true, (byte) 2);
        }
        if ((message.arg1 & 1) == 1) {
            g(bVar, aVar.f(), (byte) 1, z);
            g(bVar, aVar.f(), (byte) 2, true);
        }
        if ((message.arg1 & 2) == 2) {
            g(bVar, aVar.f(), (byte) 2, true);
            return;
        }
        Handler handler = this.j;
        handler.sendMessageDelayed(handler.obtainMessage(98, 1, message.arg2), i2);
        if (b.e().p()) {
            if (message.arg2 % 3 == 0 || z) {
                long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                f.g(this.f3447b, i2 + ",arg1=" + message.arg1 + ",arg2=" + message.arg2 + ",bms water time =" + jCurrentTimeMillis2);
            }
        }
    }

    private void f(boolean z) {
        if ((z || b.e().i() == 1) && (((!this.j.hasMessages(96)) & this.l) && (true ^ this.j.hasMessages(12)))) {
            f.g(this.f3447b, "has pick 5min msg");
            Handler handler = this.j;
            handler.sendMessageDelayed(handler.obtainMessage(96), 300000L);
        }
    }

    private void g(com.byd.data.common.c.c.b bVar, com.byd.data.common.server.e.a aVar, byte b2, boolean z) {
        int iF = bVar.f(b2);
        if (aVar == null || iF < 1) {
            f.g(this.f3447b, "ret=" + iF);
            return;
        }
        ByteBuffer byteBufferU = bVar.u(b2, iF);
        int iCurrentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        if (byteBufferU != null) {
            aVar.e(byteBufferU, b2, iCurrentTimeMillis, z);
        }
    }

    private void h(Message message, com.byd.data.common.g.a aVar, com.byd.data.common.c.c.b bVar) {
        int i2;
        int iIntValue = ((Integer) message.obj).intValue();
        boolean zP = b.e().p();
        if (zP) {
            f.g(this.f3447b, "uuid = " + iIntValue + ", count =" + message.arg1);
        }
        int i3 = message.arg1;
        if (i3 == 0) {
            message.obj = null;
        }
        message.arg1 = i3 - 1;
        long jCurrentTimeMillis = System.currentTimeMillis();
        ByteBuffer byteBufferQ = bVar.q(iIntValue, jCurrentTimeMillis);
        byte bX = bVar.x((byte) 1);
        if (byteBufferQ != null) {
            aVar.f().f(byteBufferQ, iIntValue, jCurrentTimeMillis, message.arg1 == 0);
        } else if (bX < 6) {
            f.b(this.f3447b, "interrupt event collect step =" + ((int) bX));
            return;
        }
        Handler handler = this.j;
        if (handler != null && (i2 = message.arg1) > 0) {
            this.j.sendMessageDelayed(handler.obtainMessage(message.what, i2, 0, Integer.valueOf(iIntValue)), 1000L);
            return;
        }
        if (zP) {
            f.g(this.f3447b, "remove uuid =" + iIntValue);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public boolean k(Message message) {
        if (this.j != null && !b.e().s()) {
            int i2 = message.what;
            com.byd.data.common.g.a aVarI = com.byd.data.common.g.a.i();
            com.byd.data.common.c.c.b bVarG = aVarI.g();
            if ((i2 == 24 || i2 == 48) && !bVarG.j((byte) 1)) {
                return true;
            }
            int iK = bVarG.k(i2 < 97 ? (byte) 1 : (byte) 2, message.arg2 == 2);
            if (i2 == 12) {
                l(message, i2, bVarG, iK);
            } else if (i2 != 24) {
                if (i2 != 48) {
                    switch (i2) {
                        case 96:
                            f.g(this.f3447b, "receive stop collect message.");
                            this.l = false;
                            break;
                        case 97:
                            bVarG.z((byte) 2, (byte) -1);
                            l(message, i2, bVarG, iK);
                            break;
                        case 98:
                            e(message, aVarI, bVarG, iK);
                            break;
                        case 99:
                            f.g(this.f3447b, "receive stop bms message");
                            this.j.removeMessages(98);
                            f.g(this.f3447b, "receive stop collect message.");
                            this.l = false;
                            break;
                    }
                } else {
                    h(message, aVarI, bVarG);
                }
            } else if (this.l) {
                int i3 = message.arg2 - 1;
                message.arg2 = i3;
                boolean z = i3 <= 0;
                if (z) {
                    message.arg2 = bVarG.l(true, (byte) 1);
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                String strC = bVarG.c();
                if (TextUtils.isEmpty(strC)) {
                    strC = g.b("uuid");
                }
                String str = strC;
                ByteBuffer byteBufferE = bVarG.e(jCurrentTimeMillis);
                i.a(this.f3447b, "ljb, writePeriod before, step=" + ((int) bVarG.x((byte) 1)) + ", periodData=" + byteBufferE);
                if (byteBufferE != null) {
                    aVarI.f().c(byteBufferE, str, (byte) 1, jCurrentTimeMillis, z);
                } else if (bVarG.x((byte) 1) < 6) {
                    return true;
                }
                Handler handler = this.j;
                if (handler != null) {
                    handler.sendMessageDelayed(handler.obtainMessage(24, message.arg1, message.arg2), iK);
                }
            } else {
                f.g(this.f3447b, "stop collect.");
            }
        }
        return true;
    }

    private void l(Message message, int i2, com.byd.data.common.c.c.b bVar, int i3) {
        byte b2 = i2 == 12 ? (byte) 1 : i2 == 97 ? (byte) 2 : (byte) 3;
        byte bX = bVar.x(b2);
        f.g(this.f3447b, "initConfig begin type =" + ((int) b2) + ",step =" + ((int) bX) + ",count=" + message.arg1);
        if (bX == -1) {
            boolean zB = com.byd.data.common.g.a.i().j().B();
            f.g(this.f3447b, "initConfig begin request config apn3= " + zB);
            bX = bVar.s(b2);
            com.byd.data.common.http.b bVar2 = this.m;
            if (bVar2 != null && (zB || bVar2.c())) {
                f.g(this.f3447b, "null != netState && netState.isConnect() | apn3Enable");
                message.arg1 = 4;
            } else if (bX == 4) {
                f.g(this.f3447b, "initConfig invalid network step= " + ((int) bX));
                return;
            }
            i3 = 4000;
        } else {
            if ((bX == 1) && (message.arg1 > 0)) {
                f.g(this.f3447b, "step == 1, msg.arg1 = " + message.arg1);
                i3 = 12000;
                message.arg1 = message.arg1 - 1;
            } else if (3 == bX || bX == 4) {
                f.g(this.f3447b, "step == low_bit or config_file_damage, step = " + ((int) bX));
                int i4 = message.arg1;
                if (i4 <= 0) {
                    f.g(this.f3447b, "step == low_bit or config_file_damage, msg.arg1 <=0, return");
                    return;
                } else {
                    i3 += (Math.abs(5 - i4) << 1) * 1000;
                    bVar.i(b2);
                    message.arg1--;
                }
            } else if (6 == bX) {
                f.g(this.f3447b, "step == complete");
                if (!bVar.j(b2)) {
                    f.g(this.f3447b, "step == complete, not satisfy, return");
                    return;
                }
                i2 = 24;
                f(false);
                if (this.j.hasMessages(24)) {
                    return;
                } else {
                    message.arg2 = bVar.l(true, b2);
                }
            }
        }
        if ((b2 == 2) && (bX > 0)) {
            return;
        }
        f.g(this.f3447b, i2 + ",initConfig end step=" + ((int) bVar.x(b2)) + ", cycle=" + i3 + ",count=" + message.arg1);
        Handler handler = this.j;
        if (handler != null) {
            handler.sendMessageDelayed(handler.obtainMessage(i2, message.arg1, message.arg2), i3);
        }
    }

    private void r() {
        boolean zHasMessages = this.j.hasMessages(12);
        f.g(this.f3447b, "cloudHandle.hasMessages(INIT_MSG), hasInit=" + zHasMessages);
        if (!zHasMessages) {
            this.j.sendMessageDelayed(this.j.obtainMessage(12), 12000);
        }
        boolean zHasMessages2 = this.j.hasMessages(97);
        f.g(this.f3447b, "cloudHandle.hasMessages(BMS_INIT_CONG), hasInit=" + zHasMessages2);
        if (zHasMessages2) {
            return;
        }
        this.j.sendMessageDelayed(this.j.obtainMessage(97), 13000);
    }

    private void t() {
        if (!this.l || this.j == null) {
            return;
        }
        f.g(this.f3447b, "transportBms");
        if ((!this.j.hasMessages(99)) && this.j.hasMessages(98)) {
            Handler handler = this.j;
            handler.sendMessageDelayed(handler.obtainMessage(99), 300000L);
        }
    }

    @Override // com.byd.data.common.h.a.a
    public void a(int i2) {
        b bVarE = b.e();
        byte bI = bVarE.i();
        f.g(this.f3447b, "transport =" + i2 + ",pre =" + ((int) bI));
        Handler handler = this.j;
        if (handler == null || i2 == bI) {
            return;
        }
        if (i2 == 1) {
            f(true);
            t();
        } else {
            handler.removeMessages(96);
            this.j.removeMessages(99);
        }
        bVarE.x((byte) i2);
    }

    @Override // com.byd.data.common.server.e.b
    public void b(boolean z) {
        Handler handler;
        com.byd.data.common.c.c.b bVarG = com.byd.data.common.g.a.i().g();
        if (!z || (handler = this.j) == null || bVarG == null || handler.hasMessages(48) || this.j.hasMessages(12)) {
            return;
        }
        boolean zW = bVarG.w((byte) 1);
        f.g(this.f3447b, "onChangeState local =" + zW);
        if (zW || bVarG.w((byte) 2)) {
            this.j.removeCallbacksAndMessages(null);
            ApiRetrofit.h().a();
            bVarG.b((byte) -1);
            r();
        }
    }

    @Override // com.byd.data.common.h.a.a
    public void c(boolean z) {
        Handler handler;
        com.byd.data.common.c.c.b bVarG = com.byd.data.common.g.a.i().g();
        if (bVarG == null || (handler = this.j) == null) {
            return;
        }
        handler.removeCallbacksAndMessages(null);
        ApiRetrofit.h().a();
        byte bX = bVarG.x((byte) 1);
        f.g(this.f3447b, "updateStrState isLow =" + z + ",step =" + ((int) bX));
        bVarG.b((byte) -1);
        if (z) {
            com.byd.data.common.g.a.i().f().g();
        } else {
            r();
        }
    }

    public void d() {
        Handler handler = this.j;
        if (handler != null) {
            boolean zHasMessages = handler.hasMessages(12);
            f.g(this.f3447b, "agreePrivacyInitConfig hasInit =" + zHasMessages);
            if (zHasMessages) {
                return;
            }
            int i2 = com.byd.data.common.g.a.i().j().x() ? 1000 : 12000;
            Handler handler2 = this.j;
            long j = i2;
            handler2.sendMessageDelayed(handler2.obtainMessage(12, 4, 0), j);
            Handler handler3 = this.j;
            handler3.sendMessageDelayed(handler3.obtainMessage(97, 4, 0), j);
        }
    }

    public String i() {
        return this.f3447b;
    }

    public com.byd.data.common.h.c.a j() {
        return this.o;
    }

    public boolean m(int i2) {
        Handler handler = this.j;
        if (handler != null) {
            return handler.hasMessages(48, Integer.valueOf(i2));
        }
        return false;
    }

    public void o(byte b2) {
        Handler handler = this.j;
        if (handler == null || b2 <= 0) {
            return;
        }
        boolean zHasMessages = handler.hasMessages(98);
        f.g(this.f3447b, "notifyBmsFullBack =" + ((int) b2) + ", hasMsg=" + zHasMessages);
        if (b2 == 1 && zHasMessages) {
            return;
        }
        Message messageObtainMessage = this.j.obtainMessage(98, b2, 0);
        if (messageObtainMessage != null) {
            this.j.sendMessage(messageObtainMessage);
        }
        if (b.e().i() == 1) {
            t();
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        f.g(this.f3447b, "onBind is begin");
        if (this.k == null) {
            this.k = new com.byd.data.common.server.c.a(this);
        }
        return this.k;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        com.byd.data.common.g.a.i().m(this);
        com.byd.data.common.http.b bVar = new com.byd.data.common.http.b();
        this.m = bVar;
        bVar.d(this);
        HandlerThread handlerThread = new HandlerThread(this.f3447b);
        handlerThread.start();
        this.j = new Handler(handlerThread.getLooper(), new Handler.Callback() { // from class: com.byd.data.common.server.a
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.f3455b.k(message);
            }
        });
        r();
        com.byd.data.common.c.c.b bVarG = com.byd.data.common.g.a.i().g();
        if (this.n == null) {
            this.n = new com.byd.data.common.h.b.a(this);
            BYDAutoVehicleHealthDevice.getInstance(this).registerListener((AbsBYDAutoVehicleHealthListener) this.n);
        }
        if (this.o == null) {
            BYDAutoPowerDevice bYDAutoPowerDevice = BYDAutoPowerDevice.getInstance(this);
            com.byd.data.common.h.c.a aVar = new com.byd.data.common.h.c.a(this);
            this.o = aVar;
            bYDAutoPowerDevice.registerListener((AbsBYDAutoPowerListener) aVar);
        }
        f.g(this.f3447b, "run create is end parseHash =" + bVarG.hashCode() + ", isBms=true");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        com.byd.data.common.http.b bVar = this.m;
        if (bVar != null) {
            bVar.e();
            this.m = null;
        }
        Handler handler = this.j;
        if (handler != null) {
            handler.getLooper().quit();
            this.j = null;
        }
        this.k = null;
        if (this.n != null) {
            BYDAutoVehicleHealthDevice.getInstance(this).unregisterListener((AbsBYDAutoVehicleHealthListener) this.n);
            this.n.a();
        }
        this.n = null;
        if (this.o != null) {
            BYDAutoPowerDevice.getInstance(this).unregisterListener((AbsBYDAutoPowerListener) this.o);
            this.o.e();
        }
        this.o = null;
        f.g(this.f3447b, "onDestroy is end");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        f.g(this.f3447b, "run onStartCommand is flags =" + i2 + " startId =" + i3);
        return 1;
    }

    public void p(int i2, int i3) {
        Handler handler;
        f.g(this.f3447b, "bind send msg");
        if (i2 < 1 || i3 < 1 || (handler = this.j) == null) {
            return;
        }
        this.j.sendMessageDelayed(handler.obtainMessage(48, i3, 0, Integer.valueOf(i2)), 1000L);
    }

    public void q(byte b2) {
        if (this.j == null) {
            return;
        }
        if (b2 == 1) {
            f(true);
        }
        if (b2 == 2) {
            t();
        }
    }

    public void s() {
        Handler handler = this.j;
        if (handler == null) {
            return;
        }
        handler.removeCallbacksAndMessages(null);
    }
}
