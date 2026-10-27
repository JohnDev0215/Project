package OooO00o.OooO00o.OooO0o0.OooO00o.OooO0O0;

import OooO00o.OooO00o.OooO0O0.b;
import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0O0;
import android.hardware.usb.UsbDevice;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.g;
import com.loostone.libtuning.channel.byd.tuning.TuningHelper;
import java.util.Locale;
import kotlin.jvm.internal.h;
import kotlin.text.d;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f192a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f193b;

    /* JADX INFO: renamed from: OooO00o.OooO00o.OooO0o0.OooO00o.OooO0O0.a$a, reason: collision with other inner class name */
    public static final class C0014a extends Thread {
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            a.f192a.c("start tuning mode");
            while (a.f193b) {
                try {
                    if (TuningHelper.getInstance().isConfigChanged()) {
                        String config = TuningHelper.getInstance().getConfig();
                        if (config != null) {
                            b.f41a.e("HidTuningService", h.i("hid update config: ", config));
                            String strD = g.d(config);
                            h.c(strD, "encryptMD5ToString(config)");
                            Locale locale = Locale.getDefault();
                            h.c(locale, "getDefault()");
                            String lowerCase = strD.toLowerCase(locale);
                            h.c(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
                            byte[] configMd5 = TuningHelper.getInstance().getConfigMd5();
                            h.c(configMd5, "it");
                            String str = new String(configMd5, d.f4111a);
                            Locale locale2 = Locale.getDefault();
                            h.c(locale2, "getDefault()");
                            String lowerCase2 = str.toLowerCase(locale2);
                            h.c(lowerCase2, "(this as java.lang.String).toLowerCase(locale)");
                            if (h.a(lowerCase2, lowerCase)) {
                                try {
                                    OooO0O0.f354a.q(config);
                                    a.f192a.c("hid update config success");
                                } catch (Exception e) {
                                    a.f192a.c("hid update config error");
                                    e.printStackTrace();
                                }
                            } else {
                                a.f192a.c("md5 error,hidMd5:" + lowerCase2 + ",jsonMd5:" + lowerCase);
                            }
                        }
                        b.f41a.e("HidTuningService", h.i("clearConfigInfo: ", Boolean.valueOf(TuningHelper.getInstance().clearConfigInfo())));
                    }
                    Thread.sleep(1000L);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            a.f192a.c("end tuning mode");
        }
    }

    public final void b(UsbDevice usbDevice) {
        h.d(usbDevice, "usbDevice");
        b bVar = b.f41a;
        bVar.e("HidTuningService", "start");
        if (f193b) {
            return;
        }
        if (!TuningHelper.getInstance().init(usbDevice)) {
            bVar.e("HidTuningService", "start hid tuning error");
        } else {
            f193b = true;
            new C0014a().start();
        }
    }

    public final void c(String str) {
        b.f41a.e("HidTuningService", str);
        ToastUtils.v(str, new Object[0]);
    }

    public final void e() {
        b.f41a.e("HidTuningService", "stop");
        f193b = false;
        TuningHelper.getInstance().release();
    }
}
