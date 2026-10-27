package ts.car.diag.manager;

import android.content.Context;
import com.byd.mediacenter.api.media.MediaControl;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class f {
    private static f f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f1591a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private MediaControl f1592b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private k f1593c;
    private m d;
    private l e;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements MediaControl.Callback {
        a() {
        }

        @Override // com.byd.mediacenter.api.media.MediaControl.Callback
        public void onControlResult(int i, int i2, MediaControl.Result result) {
            boolean zEquals = result.getResult().getString(MediaControl.PARAM_SUCCESS).equals(MediaControl.TRUE);
            q.h("BydMediaControl", "readTunerDabBer isSuccess : " + zEquals);
            int i3 = -1;
            if (zEquals) {
                i3 = result.getInt(MediaControl.PARAM_DAB_BER, -1);
                q.h("BydMediaControl", "22 FD4C ber : " + i3);
            } else {
                q.b("BydMediaControl", "22 FD4C fail : " + result.getResult().getString(MediaControl.PARAM_REASON));
            }
            byte[] bArrD = ts.car.diag.b.b.d(i3);
            if (f.this.f1593c != null) {
                f.this.f1593c.a((short) -692, bArrD);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements MediaControl.Callback {
        b() {
        }

        @Override // com.byd.mediacenter.api.media.MediaControl.Callback
        public void onControlResult(int i, int i2, MediaControl.Result result) {
            boolean zEquals = result.getResult().getString(MediaControl.PARAM_SUCCESS).equals(MediaControl.TRUE);
            q.h("BydMediaControl", "readTunerAmFmFieldStrength isSuccess : " + zEquals);
            int i3 = 65535;
            if (zEquals) {
                i3 = result.getInt(MediaControl.PARAM_FM2_FIELD_STRENGTH, 65535);
                q.h("BydMediaControl", "22 FD4D : fm2 : " + i3);
            } else {
                q.b("BydMediaControl", "22 FD4D fail : " + result.getResult().getString(MediaControl.PARAM_REASON));
            }
            byte[] bArrF = ts.car.diag.b.b.f(i3);
            if (f.this.f1593c != null) {
                f.this.f1593c.a((short) -691, bArrF);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class c implements MediaControl.Callback {
        c(f fVar) {
        }

        @Override // com.byd.mediacenter.api.media.MediaControl.Callback
        public void onControlResult(int i, int i2, MediaControl.Result result) {
            boolean zEquals = result.getResult().getString(MediaControl.PARAM_SUCCESS).equals(MediaControl.TRUE);
            q.h("BydMediaControl", "openMediaApp isSuccess : " + zEquals);
            if (zEquals) {
                return;
            }
            q.b("BydMediaControl", "31 FE 08 01 fail : " + result.getResult().getString(MediaControl.PARAM_REASON));
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class d implements MediaControl.Callback {
        d() {
        }

        @Override // com.byd.mediacenter.api.media.MediaControl.Callback
        public void onControlResult(int i, int i2, MediaControl.Result result) {
            boolean zEquals = result.getResult().getString(MediaControl.PARAM_SUCCESS).equals(MediaControl.TRUE);
            q.h("BydMediaControl", "getRadioFrequency isSuccess : " + zEquals);
            int i3 = -1;
            if (zEquals) {
                i3 = result.getInt(MediaControl.PARAM_RADIO_FREQ, -1);
                q.h("BydMediaControl", "22 FD40 : radioFreq : " + i3);
            } else {
                q.b("BydMediaControl", "22 FD40 fail : " + result.getResult().getString(MediaControl.PARAM_REASON));
            }
            byte[] bArrD = ts.car.diag.b.b.d(i3);
            if (f.this.f1593c != null) {
                f.this.f1593c.a((short) -704, bArrD);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class e implements MediaControl.Callback {
        e() {
        }

        @Override // com.byd.mediacenter.api.media.MediaControl.Callback
        public void onControlResult(int i, int i2, MediaControl.Result result) {
            boolean zEquals = result.getResult().getString(MediaControl.PARAM_SUCCESS).equals(MediaControl.TRUE);
            q.h("BydMediaControl", "setRadioFrequency isSuccess : " + zEquals);
            if (!zEquals) {
                q.b("BydMediaControl", "2E FD40 fail : " + result.getResult().getString(MediaControl.PARAM_REASON));
            }
            if (f.this.d != null) {
                f.this.d.a((short) -704, zEquals);
            }
        }
    }

    /* JADX INFO: renamed from: ts.car.diag.manager.f$f, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    class C0054f implements MediaControl.Callback {
        C0054f() {
        }

        @Override // com.byd.mediacenter.api.media.MediaControl.Callback
        public void onControlResult(int i, int i2, MediaControl.Result result) {
            int i3;
            boolean zEquals = result.getResult().getString(MediaControl.PARAM_SUCCESS).equals(MediaControl.TRUE);
            q.h("BydMediaControl", "getFieldStrength isSuccess : " + zEquals);
            int i4 = 65535;
            if (zEquals) {
                int i5 = result.getInt(MediaControl.PARAM_AM_FM1_FIELD_STRENGTH, 65535);
                int i6 = result.getInt(MediaControl.PARAM_FM2_FIELD_STRENGTH, 65535);
                q.h("BydMediaControl", "22 FD46 : amfm1 : " + i5 + " fm2 : " + i6);
                i4 = i5;
                i3 = i6;
            } else {
                q.b("BydMediaControl", "22 FD46 fail : " + result.getResult().getString(MediaControl.PARAM_REASON));
                i3 = 65535;
            }
            byte[] bArrE = ts.car.diag.b.b.e(i4, i3);
            if (f.this.f1593c != null) {
                f.this.f1593c.a((short) -698, bArrE);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class g implements MediaControl.Callback {
        g() {
        }

        @Override // com.byd.mediacenter.api.media.MediaControl.Callback
        public void onControlResult(int i, int i2, MediaControl.Result result) {
            boolean zEquals = result.getResult().getString(MediaControl.PARAM_SUCCESS).equals(MediaControl.TRUE);
            q.h("BydMediaControl", "changeRadioSource isSuccess : " + zEquals);
            if (!zEquals) {
                q.b("BydMediaControl", "2E FD47 fail : " + result.getResult().getString(MediaControl.PARAM_REASON));
            }
            if (f.this.d != null) {
                f.this.d.a((short) -697, zEquals);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class h implements MediaControl.Callback {
        h() {
        }

        @Override // com.byd.mediacenter.api.media.MediaControl.Callback
        public void onControlResult(int i, int i2, MediaControl.Result result) {
            boolean zEquals = result.getResult().getString(MediaControl.PARAM_SUCCESS).equals(MediaControl.TRUE);
            q.h("BydMediaControl", " setPlayUsbMusic isSuccess : " + zEquals);
            if (!zEquals) {
                q.b("BydMediaControl", "31 01 FE 04 fail : " + result.getResult().getString(MediaControl.PARAM_REASON));
            }
            f.this.e.a((short) -508, (byte) 1, zEquals);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class i implements MediaControl.Callback {
        i() {
        }

        @Override // com.byd.mediacenter.api.media.MediaControl.Callback
        public void onControlResult(int i, int i2, MediaControl.Result result) {
            int i3;
            boolean zEquals = result.getResult().getString(MediaControl.PARAM_SUCCESS).equals(MediaControl.TRUE);
            q.h("BydMediaControl", "readTunerDabFrequencyServiceId isSuccess : " + zEquals);
            int i4 = -1;
            if (zEquals) {
                int i5 = result.getInt(MediaControl.PARAM_DAB_SERVICE_ID, -1);
                int i6 = result.getInt(MediaControl.PARAM_RADIO_FREQ, -1);
                q.h("BydMediaControl", "22 FD4B freq : " + i6 + " serviceId : " + i5);
                i4 = i5;
                i3 = i6;
            } else {
                q.b("BydMediaControl", "22 FD4B fail : " + result.getResult().getString(MediaControl.PARAM_REASON));
                i3 = -1;
            }
            byte[] bArrD = ts.car.diag.b.b.d(i4);
            byte[] bArrD2 = ts.car.diag.b.b.d(i3);
            byte[] bArr = new byte[8];
            int i7 = 0;
            while (i7 < 8) {
                bArr[i7] = i7 < 4 ? bArrD2[i7] : bArrD[i7 - 4];
                i7++;
            }
            if (f.this.f1593c != null) {
                f.this.f1593c.a((short) -693, bArr);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class j implements MediaControl.Callback {
        j() {
        }

        @Override // com.byd.mediacenter.api.media.MediaControl.Callback
        public void onControlResult(int i, int i2, MediaControl.Result result) {
            boolean zEquals = result.getResult().getString(MediaControl.PARAM_SUCCESS).equals(MediaControl.TRUE);
            q.h("BydMediaControl", "writeTunerDabFrequencyServiceId isSuccess : " + zEquals);
            if (!zEquals) {
                q.b("BydMediaControl", "2E FD4B fail : " + result.getResult().getString(MediaControl.PARAM_REASON));
            }
            if (f.this.f1593c != null) {
                f.this.d.a((short) -693, zEquals);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface k {
        void a(short s, byte[] bArr);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface l {
        void a(short s, byte b2, boolean z);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface m {
        void a(short s, boolean z);
    }

    public static synchronized f g(Context context) {
        if (f == null) {
            f fVar = new f();
            f = fVar;
            fVar.i(context);
        }
        return f;
    }

    public void d(byte b2) {
        int i2;
        q.h("BydMediaControl", "changeRadioSource: source : " + ((int) b2));
        int i3 = 19;
        if (b2 == 1) {
            i2 = 11;
        } else if (b2 == 2) {
            i2 = 12;
        } else if (b2 != 6) {
            i2 = -1;
            i3 = 20;
        } else {
            i2 = 13;
        }
        boolean zE = e(i2, i3, null, new g());
        q.h("BydMediaControl", "changeRadioSource controlMedia isSuccess : " + zE);
        if (zE) {
            return;
        }
        this.d.a((short) -697, false);
    }

    public boolean e(int i2, int i3, MediaControl.Param param, MediaControl.Callback callback) {
        try {
            this.f1592b.controlMedia(i2, i3, param, callback);
            return true;
        } catch (Exception e2) {
            q.b("BydMediaControl", "controlMedia : " + e2);
            return false;
        }
    }

    public void f() {
        q.h("BydMediaControl", "getFieldStrength: in");
        boolean zE = e(0, 69, null, new C0054f());
        q.h("BydMediaControl", "getFieldStrength controlMedia isSuccess : " + zE);
        if (zE) {
            return;
        }
        this.f1593c.a((short) -698, ts.car.diag.b.b.e(65535, 65535));
    }

    public void h() {
        q.h("BydMediaControl", "getRadioFrequency: in");
        boolean zE = e(-1, 68, null, new d());
        q.h("BydMediaControl", "getRadioFrequency controlMedia isSuccess : " + zE);
        if (zE) {
            return;
        }
        this.f1593c.a((short) -704, ts.car.diag.b.b.d(-1));
    }

    public void i(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f1591a = applicationContext;
        this.f1592b = MediaControl.getInstance(applicationContext);
    }

    public void j() {
        q.h("BydMediaControl", "openMediaApp in");
        MediaControl.Param param = new MediaControl.Param();
        param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
        boolean zE = e(-1, MediaControl.ACTION_NONE, param, new c(this));
        q.h("BydMediaControl", "openMediaApp controlMedia isSuccess : " + zE);
        this.e.a((short) -504, (byte) 1, zE);
    }

    public void k() {
        q.h("BydMediaControl", "readTunerAmFmFieldStrength: in");
        boolean zE = e(0, 69, null, new b());
        q.h("BydMediaControl", "readTunerAmFmFieldStrength controlMedia isSuccess : " + zE);
        if (zE) {
            return;
        }
        byte[] bArr = new byte[2];
        for (int i2 = 0; i2 < 2; i2++) {
            bArr[i2] = -1;
        }
        this.f1593c.a((short) -691, bArr);
    }

    public void l() {
        q.h("BydMediaControl", "readTunerDabBer: in");
        boolean zE = e(13, 73, null, new a());
        q.h("BydMediaControl", "readTunerDabBer controlMedia isSuccess : " + zE);
        if (zE) {
            return;
        }
        this.f1593c.a((short) -692, ts.car.diag.b.b.d(-1));
    }

    public void m() {
        q.h("BydMediaControl", "readTunerDabFrequencyServiceId: in");
        boolean zE = e(13, 62, null, new i());
        q.h("BydMediaControl", "readTunerDabFrequencyServiceId controlMedia isSuccess : " + zE);
        if (zE) {
            return;
        }
        byte[] bArr = new byte[8];
        for (int i2 = 0; i2 < 8; i2++) {
            bArr[i2] = -1;
        }
        this.f1593c.a((short) -693, bArr);
    }

    public void n(int i2, String str) {
        q.h("BydMediaControl", "setPlayUsbMusic: in");
        MediaControl.Param param = new MediaControl.Param();
        param.setParam(MediaControl.PARAM_SOURCE, 1);
        param.setParam(MediaControl.PARAM_FILE_NAME, str);
        boolean zE = e(1, i2 == 0 ? 19 : 20, param, new h());
        q.h("BydMediaControl", "setPlayUsbMusic controlMedia isSuccess : " + zE);
        if (zE) {
            return;
        }
        this.e.a((short) -508, (byte) 1, zE);
    }

    public void o(int i2) {
        q.h("BydMediaControl", "setRadioFrequency: currentFrequency : " + i2);
        int i3 = i2 / 1000;
        q.a("BydMediaControl", "setRadioFrequency: " + i3 + " khz ");
        MediaControl.Param param = new MediaControl.Param();
        param.setParam(MediaControl.PARAM_RADIO_FREQ, i3);
        boolean zE = e(0, 19, param, new e());
        q.h("BydMediaControl", "setRadioFrequency controlMedia isSuccess : " + zE);
        if (zE) {
            return;
        }
        this.d.a((short) -704, false);
    }

    public void p(k kVar) {
        this.f1593c = kVar;
    }

    public void q(l lVar) {
        this.e = lVar;
    }

    public void r(m mVar) {
        this.d = mVar;
    }

    public void s(int i2, int i3) {
        q.h("BydMediaControl", "writeTunerDabFrequencyServiceId: in frequency : " + i2 + " serviceID : " + i3);
        MediaControl.Param param = new MediaControl.Param();
        param.setParam(MediaControl.PARAM_DAB_SERVICE_ID, i3);
        param.setParam(MediaControl.PARAM_RADIO_FREQ, i2);
        boolean zE = e(13, 19, param, new j());
        q.h("BydMediaControl", "writeTunerDabFrequencyServiceId controlMedia isSuccess : " + zE);
        if (zE) {
            return;
        }
        this.d.a((short) -693, false);
    }
}
