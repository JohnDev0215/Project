package c.a.j;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import android.hardware.bydauto.version.BYDAutoVersionDevice;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.StatFs;
import android.os.SystemProperties;
import android.os.storage.DiskInfo;
import android.os.storage.StorageEventListener;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.os.storage.VolumeRecord;
import android.text.TextUtils;
import c.a.c.a;
import c.a.c.b;
import c.a.i.a.b;
import c.a.i.a.f;
import c.a.i.a.g;
import c.a.i.a.h;
import c.a.i.a.i;
import c.a.j.b;
import com.byd.ota.R;
import com.byd.ota.master.VehicleVersionInfo;
import com.byd.ui.dialogs.TimesDialog;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: UpdatePresenter.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f2818a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public HandlerThread f2819b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Handler f2820c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public HandlerThread f2821d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public n f2822e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public k f2823f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c.a.j.c f2824g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public HandlerThread f2825h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public l f2826i;
    public StorageManager j;
    public c.a.j.b k;
    public File l;
    public File m;
    public File n;
    public File o;
    public c.a.j.f.c r;
    public BYDAutoOtaDevice s;
    public BYDAutoVersionDevice t;
    public String u;
    public c.a.c.a w;
    public boolean p = true;
    public boolean q = false;
    public int v = 3;
    public final StorageEventListener x = new StorageEventListenerC0065d();

    /* JADX INFO: compiled from: UpdatePresenter.java */
    public class a implements ServiceConnection {
        public a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            c.a.k.b.c("----onServiceConnected componentName =" + componentName);
            d.this.w = a.AbstractBinderC0047a.B(iBinder);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            c.a.k.b.c("----onServiceDisconnected componentName =" + componentName);
        }
    }

    /* JADX INFO: compiled from: UpdatePresenter.java */
    public class b extends b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ i.f f2828a;

        public b(i.f fVar) {
            this.f2828a = fVar;
        }

        @Override // c.a.c.b
        public void g(int i2, int i3, String str) {
            c.a.k.b.c("----onResult----businessType = " + i2 + ", code = " + i3 + ", message = " + str);
            if (i3 == 1) {
                d.this.m = this.f2828a.f2806a;
                c.a.k.b.c("select file: " + d.this.m.getPath());
                d.this.f2823f.f2838a = 0;
                StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
                float availableBlocksLong = (float) ((statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong()) / 1048576);
                float f2 = Float.parseFloat(this.f2828a.f2809d.replace("MB", "")) + 100.0f;
                c.a.k.b.c("size : " + availableBlocksLong + " select:" + f2);
                if (f2 >= availableBlocksLong) {
                    d.this.f2823f.sendEmptyMessage(25);
                    return;
                }
                int i4 = this.f2828a.f2808c;
                if (i4 == 1) {
                    d.this.f2822e.sendEmptyMessage(3);
                } else {
                    d.this.a0(i4);
                }
            }
        }
    }

    /* JADX INFO: compiled from: UpdatePresenter.java */
    public class c implements m {
        public c() {
        }

        @Override // c.a.j.d.m
        public void a(boolean z) {
            d.this.f2822e.sendEmptyMessage(12);
        }

        @Override // c.a.j.d.m
        public void onUpdateProgress(int i2) {
            d.this.O(i2);
        }
    }

    /* JADX INFO: renamed from: c.a.j.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: UpdatePresenter.java */
    public class StorageEventListenerC0065d extends StorageEventListener {
        public StorageEventListenerC0065d() {
        }

        public void onDiskDestroyed(DiskInfo diskInfo) {
            c.a.k.b.d();
        }

        public void onDiskScanned(DiskInfo diskInfo, int i2) {
            c.a.k.b.d();
        }

        public void onStorageStateChanged(String str, String str2, String str3) {
            c.a.k.b.d();
        }

        public void onUsbMassStorageConnectionChanged(boolean z) {
            c.a.k.b.d();
        }

        public void onVolumeForgotten(String str) {
            c.a.k.b.d();
        }

        public void onVolumeRecordChanged(VolumeRecord volumeRecord) {
            c.a.k.b.d();
        }

        public void onVolumeStateChanged(VolumeInfo volumeInfo, int i2, int i3) {
            if (i2 == 1 && i3 == 2) {
                c.a.k.b.c("STATE_MOUNTED--scanUpdateFile" + volumeInfo);
                c.a.k.b.c("STATE_MOUNTED--isAllowScan" + d.this.q);
                if (d.this.q && d.this.Q(volumeInfo)) {
                    d.this.q = false;
                    d dVar = d.this;
                    dVar.o = dVar.k.g();
                    c.a.k.b.c("mAuthorizedFile = " + d.this.o);
                    if (d.this.o != null) {
                        d.this.f2822e.sendEmptyMessage(18);
                    }
                    d dVar2 = d.this;
                    dVar2.l = dVar2.k.f();
                    if (d.this.l != null) {
                        d.this.f2822e.sendEmptyMessage(10);
                    }
                    d dVar3 = d.this;
                    dVar3.n = dVar3.k.h();
                    if (d.this.n != null) {
                        d.this.f2822e.sendEmptyMessage(17);
                    }
                    if (d.this.k.i()) {
                        d.this.f2822e.sendEmptyMessage(19);
                    }
                }
            }
            if (i2 == 5 && i3 == 0) {
                DiskInfo diskInfo = volumeInfo.disk;
                if (diskInfo != null && diskInfo.isUsb()) {
                    d.this.q = true;
                    if (d.this.k.k(volumeInfo)) {
                        d.this.f2823f.sendEmptyMessage(13);
                        d.this.p = true;
                    }
                }
                c.a.k.b.c("STATE_UNMOUNTED--removeAllUpdateFile" + volumeInfo);
            }
        }
    }

    /* JADX INFO: compiled from: UpdatePresenter.java */
    public class e implements b.c {
        public e() {
        }

        @Override // c.a.i.a.b.c
        public void a(int i2) {
            if (i2 == 1) {
                d.this.M();
            }
            if (i2 == 2) {
                d.this.f2823f.sendEmptyMessage(10);
            }
        }
    }

    /* JADX INFO: compiled from: UpdatePresenter.java */
    public class f implements i.c {
        public f() {
        }

        @Override // c.a.i.a.i.c
        public void a(i.f fVar) {
            d.this.P(0, "U盘升级", fVar);
        }

        @Override // c.a.i.a.i.c
        public void b() {
            d.this.f2823f.f2838a = 0;
        }
    }

    /* JADX INFO: compiled from: UpdatePresenter.java */
    public class g implements f.c {
        public g() {
        }

        @Override // c.a.i.a.f.c
        public void a() {
        }

        @Override // c.a.i.a.f.c
        public void b() {
        }
    }

    /* JADX INFO: compiled from: UpdatePresenter.java */
    public class h implements h.c {
        public h() {
        }

        @Override // c.a.i.a.h.c
        public void a() {
            d.this.f2822e.sendEmptyMessage(3);
        }
    }

    /* JADX INFO: compiled from: UpdatePresenter.java */
    public class i implements TimesDialog.b {
        public i() {
        }

        @Override // com.byd.ui.dialogs.TimesDialog.b
        public void a() {
            d.this.f2826i.sendEmptyMessage(2);
            c.a.k.b.c("----onActionClose---- = ");
        }
    }

    /* JADX INFO: compiled from: UpdatePresenter.java */
    public class j implements g.c {
        public j() {
        }

        @Override // c.a.i.a.g.c
        public void a() {
            d.this.f2822e.sendEmptyMessage(3);
        }
    }

    /* JADX INFO: compiled from: UpdatePresenter.java */
    public class k extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2838a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2839b;

        public k(Looper looper) {
            super(looper);
            this.f2838a = 0;
            this.f2839b = 0;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2 = message.what;
            this.f2838a = i2;
            switch (i2) {
                case 9:
                    d.this.I();
                    break;
                case 10:
                    d.this.m0();
                    break;
                case 11:
                    d.this.V();
                    break;
                case 12:
                    d.this.e0();
                    break;
                case 14:
                    d.this.j0();
                    break;
                case 15:
                    d.this.c0();
                    break;
                case 16:
                    d.this.l0();
                    break;
                case 17:
                    d.this.k0();
                    break;
                case 18:
                    d.this.d0();
                    break;
                case 19:
                    c.a.k.b.c("showUpdateFileDecryptFailDialog");
                    d.this.b0();
                    break;
                case 20:
                    d.this.f0();
                    break;
                case 21:
                    d.this.h0();
                    d.this.H();
                    break;
                case 22:
                    d.this.Y();
                    break;
                case 23:
                    d.this.S();
                    break;
                case 24:
                    d.this.U();
                    break;
                case 25:
                    d.this.X();
                    break;
                case 26:
                    int iIntValue = ((Integer) message.obj).intValue();
                    this.f2839b = iIntValue;
                    d.this.g0(iIntValue);
                    d.this.H();
                    break;
                case 27:
                    d.this.J();
                    break;
                case 28:
                    d.this.W();
                    break;
            }
        }
    }

    /* JADX INFO: compiled from: UpdatePresenter.java */
    public class l extends Handler {
        public l(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 != 1) {
                if (i2 != 2) {
                    return;
                }
                d.this.r.j();
                d.this.r.k();
                return;
            }
            String strR = d.this.r.r();
            if (strR != null) {
                d.this.Z(strR.replace(".times", ""));
            } else {
                d.this.r.C(VehicleVersionInfo.FORCE_UPGRADE);
                d.this.Z(VehicleVersionInfo.FORCE_UPGRADE);
            }
        }
    }

    /* JADX INFO: compiled from: UpdatePresenter.java */
    public interface m {
        void a(boolean z);

        void onUpdateProgress(int i2);
    }

    /* JADX INFO: compiled from: UpdatePresenter.java */
    public class n extends Handler {
        public n(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) throws Throwable {
            switch (message.what) {
                case 1:
                    d.this.R();
                    break;
                case 2:
                    String bydSystemVersion = d.this.t.getBydSystemVersion();
                    String strC = c.a.b.d.h.c("ro.product.version.soc.name");
                    if (!TextUtils.isEmpty(bydSystemVersion)) {
                        d.this.u = bydSystemVersion;
                    } else if (TextUtils.isEmpty(strC)) {
                        c.a.k.b.c("mediaSoftVersion is empty");
                    } else {
                        d.this.u = strC;
                    }
                    c.a.k.b.c("mediaSoftVersion =" + d.this.u);
                    if (TextUtils.isEmpty(d.this.u) || d.this.u.length() <= 1 || !d.this.u.substring(d.this.u.length() - 1).equals("2")) {
                        SystemProperties.set("sys.otamaster.copy.state", "COPYING");
                        c.a.k.b.c("删除缓存文件..." + System.currentTimeMillis());
                        c.a.j.b.b("/data/vendor/nfs/nfs_ota/usb/");
                        c.a.j.b.b("/data/vendor/nfs/nfs_ota/update/");
                        c.a.j.b.b(d.this.f2818a.getFilesDir().getPath() + "/decrypt.zip");
                        d.this.r.l();
                        c.a.k.b.c("从U盘拷贝升级包到车机..." + System.currentTimeMillis());
                        if (d.this.s == null) {
                            d dVar = d.this;
                            dVar.s = BYDAutoOtaDevice.getInstance(dVar.f2818a);
                        }
                        c.a.k.b.c("从U盘拷贝升级包到车机...setUsbUpdateState----0x2");
                        for (int i2 = 0; i2 <= d.this.v; i2++) {
                            try {
                                if (c.a.j.b.a((File) message.obj, "/data/vendor/nfs/nfs_ota/usb/")) {
                                    d.this.f2823f.sendEmptyMessage(14);
                                    d.this.f2822e.sendEmptyMessage(4);
                                    c.a.k.b.c("完成从U盘拷贝升级包到车机..." + System.currentTimeMillis());
                                } else {
                                    if (i2 >= d.this.v) {
                                        d.this.f2823f.sendEmptyMessage(11);
                                    } else {
                                        c.a.k.b.b("U盘拷贝升级包到车机失败" + i2 + "次");
                                        try {
                                            Thread.sleep(1000L);
                                        } catch (InterruptedException e2) {
                                            e2.printStackTrace();
                                        }
                                    }
                                }
                            } catch (IOException e3) {
                                d.this.f2823f.sendEmptyMessage(11);
                                c.a.k.b.b("performCopyUpdateFileFromUSB --IOException " + e3);
                                return;
                            }
                            d.this.f2823f.sendEmptyMessage(11);
                            c.a.k.b.b("performCopyUpdateFileFromUSB --IOException " + e3);
                        }
                    } else {
                        c.a.k.b.b("debug版本无法升级");
                        d.this.f2823f.sendEmptyMessage(28);
                    }
                    break;
                case 3:
                    boolean zC = d.this.r.c();
                    c.a.k.b.c("checkCarResult=" + zC);
                    if (zC) {
                        d.this.f2823f.sendEmptyMessage(24);
                        d dVar2 = d.this;
                        dVar2.N(dVar2.m);
                    } else {
                        d.this.f2823f.sendEmptyMessage(23);
                    }
                    break;
                case 4:
                    SystemProperties.set("sys.otamaster.copy.state", "DONE");
                    c.a.k.b.c("证书有效性认证..." + System.currentTimeMillis());
                    int iG = d.this.r.G();
                    c.a.k.b.c("证书有效性认证...result = " + iG);
                    if (iG == 0) {
                        d.this.f2822e.sendEmptyMessage(11);
                    } else if (iG == 1) {
                        c.a.k.b.c("证书有效性认证失败");
                        d.this.f2823f.sendEmptyMessage(16);
                    } else if (iG == 2) {
                        c.a.k.b.c("证书有效性认证配置失败");
                        d.this.f2823f.sendEmptyMessage(17);
                    } else if (iG == 3) {
                        d.this.f2823f.sendEmptyMessage(15);
                        d.this.f2822e.sendEmptyMessage(13);
                    }
                    break;
                case 5:
                    c.a.k.b.c("解密密钥和IV向量..." + System.currentTimeMillis());
                    if (d.this.r.i()) {
                        d.this.f2822e.sendEmptyMessage(7);
                    } else {
                        c.a.k.b.c("解密密钥和IV向量失败");
                        d.this.f2823f.sendEmptyMessage(19);
                    }
                    break;
                case 7:
                    c.a.k.b.c("一车一密文件解密..." + System.currentTimeMillis());
                    if (d.this.r.g(d.this.f2818a.getFilesDir().getPath())) {
                        c.a.j.b.b("/data/vendor/nfs/nfs_ota/usb/");
                        d.this.f2823f.sendEmptyMessage(20);
                        d.this.f2822e.sendEmptyMessage(8);
                    } else {
                        c.a.k.b.c("一车一密文件解密失败");
                        d.this.f2823f.sendEmptyMessage(19);
                    }
                    break;
                case 8:
                    c.a.k.b.c("解压文件到升级指定的路径..." + System.currentTimeMillis());
                    try {
                        c.a.j.b.d(d.this.f2818a.getFilesDir().getPath() + "/decrypt.zip", "/data/vendor/nfs/nfs_ota/");
                        c.a.j.b.b(d.this.f2818a.getFilesDir().getPath() + "/decrypt.zip");
                        d.this.f2822e.sendEmptyMessage(9);
                    } catch (Exception e4) {
                        c.a.k.b.c("解压文件失败");
                        d.this.f2823f.sendEmptyMessage(12);
                        e4.printStackTrace();
                        return;
                    }
                    break;
                case 9:
                    d.this.r.y();
                    d.this.r.B(d.this.r.o(), d.this.f2818a.getFilesDir().getPath());
                    SystemProperties.set("persist.sys.otamaster.state", "UPGRADE");
                    try {
                        c.a.k.b.c("删除缓存文件..." + System.currentTimeMillis());
                        c.a.j.b.b("/data/vendor/nfs/nfs_ota/usb/");
                    } catch (Exception e5) {
                        e5.printStackTrace();
                    }
                    c.a.k.b.c("start升级..." + System.currentTimeMillis());
                    d.this.f2823f.sendEmptyMessage(21);
                    d.this.r.E();
                    break;
                case 10:
                    c.a.k.b.c("has adb file...");
                    if (d.this.l != null) {
                        try {
                            if (d.this.r.a(c.a.j.f.f.f(d.this.l))) {
                                c.a.k.b.b("符合开启adb条件...");
                                d.this.r.D();
                            } else {
                                c.a.k.b.b("标志无法开启adb...");
                            }
                        } catch (Exception e6) {
                            e6.printStackTrace();
                            return;
                        }
                    }
                    break;
                case 11:
                    c.a.k.b.c("判断时间有效性..." + System.currentTimeMillis());
                    if (d.this.r.s()) {
                        d.this.f2823f.sendEmptyMessage(15);
                        d.this.f2822e.sendEmptyMessage(5);
                    } else {
                        c.a.k.b.c("升级包已过有效期...");
                        d.this.f2823f.sendEmptyMessage(18);
                    }
                    break;
                case 12:
                    SystemProperties.set("persist.sys.otamaster.state", "DONE");
                    c.a.k.b.c("升级完成...等待重启...");
                    if (d.this.s == null) {
                        d dVar3 = d.this;
                        dVar3.s = BYDAutoOtaDevice.getInstance(dVar3.f2818a);
                    }
                    c.a.k.b.c("升级完成...setUsbUpdateState---0x1");
                    String strR = d.this.r.r();
                    if (strR != null) {
                        try {
                            d.this.r.A(Integer.toString(Integer.parseInt(strR.replace(".times", "")) + 1), d.this.f2818a.getFilesDir().getPath());
                            Runtime.getRuntime().exec("sync");
                        } catch (Exception e7) {
                            e7.printStackTrace();
                        }
                    }
                    d.this.r.w();
                    break;
                case 13:
                    c.a.k.b.c("通用升级包解密..." + System.currentTimeMillis());
                    for (int i3 = 0; i3 <= d.this.v; i3++) {
                        if (d.this.r.h(d.this.f2818a.getFilesDir().getPath())) {
                            c.a.j.b.b("/data/vendor/nfs/nfs_ota/usb/");
                            d.this.f2823f.sendEmptyMessage(20);
                            d.this.f2822e.sendEmptyMessage(8);
                        } else {
                            if (i3 >= d.this.v) {
                                c.a.k.b.c("通用升级包解密超过10次，失败");
                                d.this.f2823f.sendEmptyMessage(19);
                            } else {
                                c.a.k.b.b("通用升级包解密失败" + i3 + "次");
                                try {
                                    Thread.sleep(1000L);
                                } catch (InterruptedException e8) {
                                    e8.printStackTrace();
                                }
                            }
                        }
                        break;
                    }
                    break;
                case 14:
                    d.this.n0();
                    break;
                case 15:
                    if (d.this.r.o() != null) {
                        if (d.this.r.d(d.this.f2818a.getFilesDir().getPath())) {
                            d.this.r.x();
                        } else {
                            d.this.f2822e.sendEmptyMessageDelayed(16, 8000L);
                        }
                    }
                    if (SystemProperties.get("sys.otamaster.copy.state", "NULL").equals("COPYING")) {
                        c.a.k.b.c("MSG_CHECK_UPGRADE_RESULT");
                        d.this.f2823f.sendEmptyMessage(11);
                    }
                    break;
                case 16:
                    if (c.a.j.f.k.d() && c.a.j.f.k.a()) {
                        d.this.i0();
                        c.a.j.f.k.b();
                        break;
                    }
                    break;
                case 17:
                    if (d.this.n != null) {
                        try {
                            String strF = c.a.j.f.f.f(d.this.n);
                            d.this.r.n(strF, d.this.n.getParent() + "/USB.info");
                        } catch (Exception e9) {
                            e9.printStackTrace();
                            return;
                        }
                    }
                    break;
                case 18:
                    if (d.this.o != null) {
                        try {
                            if (d.this.r.b(c.a.j.f.f.f(d.this.o))) {
                                d.this.p = true;
                                c.a.k.b.b("符合条件授权升级...");
                            }
                        } catch (Exception e10) {
                            e10.printStackTrace();
                            return;
                        }
                    }
                    break;
                case 19:
                    if (d.this.p) {
                        d.this.f2823f.sendEmptyMessage(9);
                    } else {
                        d.this.f2824g.j(R.string.usb_update_package_not_match_sn);
                    }
                    break;
            }
        }
    }

    public d(Context context) {
        this.f2818a = context;
    }

    public void G() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.byd.logtools", "com.byd.logtools.auth.AuthService"));
        this.f2818a.bindService(intent, new a(), 1);
    }

    public final void H() {
        this.f2823f.removeMessages(27);
        this.f2823f.sendEmptyMessageDelayed(27, 600000L);
    }

    public final void I() {
        if (this.s.getBcmOtaState() == 1) {
            c.a.k.b.b("is in OTA mode, usb upgrade is forbidden");
            return;
        }
        String str = SystemProperties.get("persist.sys.otamaster.state", "DONE");
        c.a.k.b.c("upgrade state = " + str);
        if (this.r.q()) {
            this.f2822e.sendEmptyMessageDelayed(14, 2000L);
            return;
        }
        if (str.equals("DONE") || str.equals("COPY")) {
            c.a.k.b.c("show dialog check file...");
            T();
        } else if (str.equals("DECRYPT") || str.equals("UNZIP")) {
            c.a.k.b.c("show dialog check file...");
            T();
        } else if (str.equals("UPGRADE")) {
            c.a.k.b.c("show dialog upgrade again...");
            this.f2822e.sendEmptyMessage(9);
        }
    }

    public void J() {
        SystemProperties.set("persist.sys.otamaster.state", "DONE");
        this.f2824g.a();
        c.a.k.b.b("10 min timeout to hide dialog");
    }

    public void K() {
        c.a.k.b.b("UpdatePresenter init() 2023.10.16 ");
        c.a.j.f.c cVarP = c.a.j.f.c.p(this.f2818a);
        this.r = cVarP;
        cVarP.z(new c());
        String str = SystemProperties.get("ro.build.type", "null");
        c.a.k.b.b("build type:" + str);
        if (!str.equals("userdebug")) {
            try {
                c.a.k.b.c("disableAdb start");
                this.r.m();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        this.s = BYDAutoOtaDevice.getInstance(this.f2818a);
        this.t = BYDAutoVersionDevice.getInstance(this.f2818a);
        this.f2823f = new k(Looper.getMainLooper());
        this.f2824g = new c.a.j.c(this.f2818a);
        HandlerThread handlerThread = new HandlerThread("deviceWatcher", 0);
        this.f2819b = handlerThread;
        handlerThread.start();
        this.f2820c = new Handler(this.f2819b.getLooper());
        HandlerThread handlerThread2 = new HandlerThread("updateWorker", 10);
        this.f2821d = handlerThread2;
        handlerThread2.start();
        this.f2822e = new n(this.f2821d.getLooper());
        HandlerThread handlerThread3 = new HandlerThread("toastWatcher", 10);
        this.f2825h = handlerThread3;
        handlerThread3.start();
        this.f2826i = new l(this.f2825h.getLooper());
        this.q = true;
        StorageManager storageManager = (StorageManager) this.f2818a.getSystemService(StorageManager.class);
        this.j = storageManager;
        storageManager.registerListener(this.x);
        this.k = new c.a.j.b();
        this.f2822e.sendEmptyMessage(15);
        c.a.k.b.c("UpdatePresenter init() end ");
        G();
    }

    public void L(Configuration configuration) {
        c.a.k.b.c("----onConfigurationChanged----newConfig = " + configuration);
        k kVar = this.f2823f;
        int i2 = kVar.f2838a;
        if (i2 == 21 || i2 == 26) {
            kVar.sendEmptyMessage(21);
        } else if (i2 == 10) {
            kVar.sendEmptyMessage(10);
        }
    }

    public final void M() {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("byd_pad://byd_car_settings:2933/launcher?activity=Main&module_index=1&page_index=4&function_index=0&sub_function_index=0"));
        intent.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
        List<ResolveInfo> listQueryIntentServices = this.f2818a.getPackageManager().queryIntentServices(intent, 0);
        c.a.k.b.c("----startServiceByPath----resolveInfos = " + listQueryIntentServices);
        if (listQueryIntentServices.isEmpty()) {
            return;
        }
        this.f2818a.getApplicationContext().startService(intent);
    }

    public void N(File file) {
        c.a.k.b.c("performCopyUpdateFileFromUSB");
        Message message = new Message();
        message.what = 2;
        message.obj = file;
        this.f2822e.sendMessageAtTime(message, 0L);
    }

    public void O(int i2) {
        Message message = new Message();
        message.what = 26;
        message.obj = Integer.valueOf(i2);
        this.f2823f.sendMessageAtTime(message, 0L);
    }

    public void P(int i2, String str, i.f fVar) {
        c.a.k.b.c("----requestAuth mAuthBinder =" + this.w);
        c.a.c.a aVar = this.w;
        if (aVar != null) {
            try {
                aVar.e(i2, str, new b(fVar));
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    public final boolean Q(VolumeInfo volumeInfo) {
        DiskInfo diskInfo = volumeInfo.disk;
        if (diskInfo == null || !diskInfo.isUsb()) {
            return false;
        }
        return this.k.c(volumeInfo);
    }

    public final void R() {
        Iterator it = this.j.getVolumes().iterator();
        while (it.hasNext()) {
            Q((VolumeInfo) it.next());
        }
        if (this.k.i()) {
            this.f2823f.sendEmptyMessage(9);
        }
        this.l = this.k.f();
        c.a.k.b.c("mAdbFile = " + this.l);
        if (this.l != null) {
            this.f2822e.sendEmptyMessage(10);
        }
    }

    public void S() {
        this.f2824g.c(new h());
    }

    public void T() {
        this.f2824g.f(new e());
    }

    public void U() {
        this.f2824g.g();
    }

    public void V() {
        this.f2824g.h();
    }

    public void W() {
        this.f2824g.i();
    }

    public void X() {
        this.f2824g.k();
    }

    public void Y() {
        this.f2824g.p(new g());
    }

    public void Z(String str) {
        this.f2824g.q(str, new i());
    }

    public void a0(int i2) {
        this.f2824g.r(new j(), i2);
    }

    public void b0() {
        this.f2824g.m();
    }

    public void c0() {
        this.f2824g.n();
    }

    public void d0() {
        this.f2824g.l();
    }

    public void e0() {
        this.f2824g.t();
    }

    public void f0() {
        this.f2824g.o();
    }

    public void g0(int i2) {
        this.f2824g.x(i2);
    }

    public void h0() {
        this.f2824g.u(this.f2823f.f2839b);
    }

    public void i0() {
        this.f2824g.v(this.r.o());
    }

    public void j0() {
        this.f2824g.w();
    }

    public void k0() {
        this.f2824g.d();
    }

    public void l0() {
        this.f2824g.e();
    }

    public final void m0() {
        String strO = this.r.o();
        ArrayList arrayList = new ArrayList();
        for (b.a aVar : this.k.e()) {
            if (aVar.f2813a.getName().contains(".dx.update")) {
                i.f fVar = new i.f();
                File file = aVar.f2813a;
                fVar.f2806a = file;
                String strReplace = file.getName().replace(".dx.update", "");
                int iCompareTo = strO.compareTo(strReplace);
                fVar.f2808c = iCompareTo == 0 ? 3 : iCompareTo > 0 ? 2 : 1;
                fVar.f2807b = strReplace;
                fVar.f2809d = Float.toString(c.a.j.f.f.d(aVar.f2813a) / 1048576.0f) + "MB";
                arrayList.add(fVar);
            }
        }
        this.f2824g.s(strO, arrayList, new f());
    }

    public void n0() {
        for (b.a aVar : this.k.e()) {
            if (aVar.f2813a.getName().contains(".dx.update")) {
                this.m = aVar.f2813a;
                this.f2822e.sendEmptyMessage(3);
                this.f2826i.sendEmptyMessageDelayed(1, 1000L);
                return;
            }
        }
    }
}
