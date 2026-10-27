package b.b.g.n;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.IPackageDataObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.RemoteException;
import android.os.StatFs;
import android.os.storage.StorageManager;
import androidx.annotation.RequiresApi;
import com.byd.carsettings.model.dilink.DiskSize;
import com.byd.carsettings.receiver.BydUsbReceiver;
import com.byd.carsettings.system.BydStorageManager;
import com.byd.carsettings.system.model.UFlashInfo;
import com.byd.carsettings.ui.activity.DiskStatisticsActivity;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: compiled from: DiskStatisticsPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends b.b.m.e.g<DiskStatisticsActivity> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f937a = "b";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BydUsbReceiver f938b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Intent f939c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Uri f940d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CountDownLatch f941e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public StorageManager f942f;

    /* JADX INFO: compiled from: DiskStatisticsPresenter.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Intent f943a;

        public a(Intent intent) {
            this.f943a = intent;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (b.this.f940d == null || !b.this.f940d.equals(this.f943a.getData()) || !"android.intent.action.MEDIA_EJECT".equals(b.this.f939c.getAction()) || b.this.getV() == null) {
                return;
            }
            ((DiskStatisticsActivity) b.this.getV()).k0(b.this.f940d);
        }
    }

    /* JADX INFO: renamed from: b.b.g.n.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DiskStatisticsPresenter.java */
    public class RunnableC0050b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f945a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f946b;

        public RunnableC0050b(int i, int i2) {
            this.f945a = i;
            this.f946b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            StatFs statFsN = BydStorageManager.n();
            long blockSizeLong = statFsN.getBlockSizeLong();
            long blockCountLong = statFsN.getBlockCountLong() * blockSizeLong;
            long availableBlocksLong = blockSizeLong * statFsN.getAvailableBlocksLong();
            long primaryStorageSize = b.this.f942f.getPrimaryStorageSize();
            b.b.m.c.a.a(b.f937a, "getPrimaryStorageSize:" + primaryStorageSize + ", getDataTotalSpace:" + (((Environment.getDataDirectory().getTotalSpace() / 1024) / 1024) / 1024) + "GB, getRootTotalSpace:" + (((Environment.getRootDirectory().getTotalSpace() / 1024) / 1024) / 1024) + "GB");
            DiskSize diskSize = new DiskSize(274877906944L, availableBlocksLong);
            diskSize.setRealTotalSize(blockCountLong);
            if (b.this.getV() != null) {
                ((DiskStatisticsActivity) b.this.getV()).F(this.f945a, this.f946b, diskSize);
            }
            if (b.this.f941e != null) {
                b.this.f941e.countDown();
            }
            System.out.println("countDownLatch getTotalRomStatFs over");
            try {
                b.this.f941e.await();
                System.out.println("所有异步任务执行完成");
                if (b.this.getV() != null) {
                    ((DiskStatisticsActivity) b.this.getV()).F(this.f945a, 263, new Object());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: compiled from: DiskStatisticsPresenter.java */
    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f948a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f949b;

        public c(int i, int i2) {
            this.f948a = i;
            this.f949b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            long jG = BydStorageManager.g((Context) b.this.getV());
            if (b.this.getV() != null) {
                ((DiskStatisticsActivity) b.this.getV()).F(this.f948a, this.f949b, Long.valueOf(jG));
            }
            if (b.this.f941e != null) {
                b.this.f941e.countDown();
            }
            System.out.println("countDownLatch getTotalAppDataSize over");
        }
    }

    /* JADX INFO: compiled from: DiskStatisticsPresenter.java */
    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f951a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f952b;

        public d(int i, int i2) {
            this.f951a = i;
            this.f952b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            long jM = BydStorageManager.m((Context) b.this.getV());
            if (b.this.getV() != null) {
                ((DiskStatisticsActivity) b.this.getV()).F(this.f951a, this.f952b, Long.valueOf(jM));
            }
            if (b.this.f941e != null) {
                b.this.f941e.countDown();
            }
            System.out.println("countDownLatch getTotalMediaDataSize over");
        }
    }

    /* JADX INFO: compiled from: DiskStatisticsPresenter.java */
    public class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f954a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f955b;

        public e(int i, int i2) {
            this.f954a = i;
            this.f955b = i2;
        }

        @Override // java.lang.Runnable
        @RequiresApi(api = 29)
        public void run() {
            long jL = BydStorageManager.l((Context) b.this.getV());
            if (b.this.getV() != null) {
                ((DiskStatisticsActivity) b.this.getV()).F(this.f954a, this.f955b, Long.valueOf(jL));
            }
            if (b.this.f941e != null) {
                b.this.f941e.countDown();
            }
            System.out.println("countDownLatch getTotalDownloadDataSize over");
        }
    }

    /* JADX INFO: compiled from: DiskStatisticsPresenter.java */
    public class f implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f957a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f958b;

        public f(int i, int i2) {
            this.f957a = i;
            this.f958b = i2;
        }

        @Override // java.lang.Runnable
        @RequiresApi(api = 29)
        public void run() {
            long jK = BydStorageManager.k((Context) b.this.getV());
            if (b.this.getV() != null) {
                ((DiskStatisticsActivity) b.this.getV()).F(this.f957a, this.f958b, Long.valueOf(jK));
            }
            if (b.this.f941e != null) {
                b.this.f941e.countDown();
            }
            System.out.println("countDownLatch getTotalCacheDataSize over");
        }
    }

    /* JADX INFO: compiled from: DiskStatisticsPresenter.java */
    public class g implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f960a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f961b;

        public g(int i, int i2) {
            this.f960a = i;
            this.f961b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            List<UFlashInfo> listP = BydStorageManager.p((Context) b.this.getV());
            if (b.this.getV() != null) {
                ((DiskStatisticsActivity) b.this.getV()).F(this.f960a, this.f961b, listP);
            }
        }
    }

    /* JADX INFO: compiled from: DiskStatisticsPresenter.java */
    public class h implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f963a;

        public h(String str) {
            this.f963a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            BydStorageManager.r((Context) b.this.getV(), this.f963a);
        }
    }

    /* JADX INFO: compiled from: DiskStatisticsPresenter.java */
    public class i implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f965a;

        public i(String str) {
            this.f965a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            BydStorageManager.q((Context) b.this.getV(), this.f965a);
        }
    }

    /* JADX INFO: compiled from: DiskStatisticsPresenter.java */
    public class j implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f967a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f968b;

        public j(int i, int i2) {
            this.f967a = i;
            this.f968b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            BydStorageManager.c((Context) b.this.getV(), new k(b.this, this.f967a, this.f968b));
        }
    }

    /* JADX INFO: compiled from: DiskStatisticsPresenter.java */
    public static class k extends IPackageDataObserver.Stub {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<b> f970a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public volatile int f971b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public volatile int f972c = 0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public volatile int f973d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public volatile int f974e;

        public k(b bVar, int i, int i2) {
            this.f973d = 0;
            this.f974e = 0;
            this.f970a = new WeakReference<>(bVar);
            this.f973d = i;
            this.f974e = i2;
        }

        public void d0(int i) {
            this.f972c = i;
        }

        public void onRemoveCompleted(String str, boolean z) throws RemoteException {
            this.f971b++;
            if (this.f971b < this.f972c) {
                return;
            }
            this.f971b = 0;
            WeakReference<b> weakReference = this.f970a;
            if (weakReference == null || weakReference.get() == null || this.f970a.get().getV() == null) {
                return;
            }
            this.f970a.get().T(this.f973d, this.f974e);
        }
    }

    /* JADX INFO: compiled from: DiskStatisticsPresenter.java */
    public static class l implements b.b.g.p.j.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<b> f975a;

        public l(b bVar) {
            this.f975a = new WeakReference<>(bVar);
        }

        @Override // b.b.g.p.j.e
        public void a(Intent intent) {
            WeakReference<b> weakReference = this.f975a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f975a.get().V(intent);
        }

        @Override // b.b.g.p.j.e
        public void b(Intent intent) {
            WeakReference<b> weakReference = this.f975a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f975a.get().U(intent);
        }
    }

    public void L(int i2, int i3, int i4) {
        b.b.m.g.b.a(new j(i2, i4));
    }

    public void M(int i2, int i3) {
        b.b.m.g.b.a(new c(i2, i3));
    }

    public void N(int i2, int i3) {
        b.b.m.g.b.a(new f(i2, i3));
    }

    public void O(int i2, int i3) {
        b.b.m.g.b.a(new e(i2, i3));
    }

    public void P(int i2, int i3) {
        b.b.m.g.b.a(new d(i2, i3));
    }

    public void Q(int i2, int i3) {
        b.b.m.g.b.a(new RunnableC0050b(i2, i3));
    }

    public void R(int i2, int i3) {
        b.b.m.g.b.a(new g(i2, i3));
    }

    public void S(String str) {
        b.b.a.f.h.a().j(new i(str));
    }

    public final void T(int i2, int i3) {
        b.b.m.c.a.a(f937a, ", ---onRemoveCompleted---what" + i2 + ", arg1: " + i3);
        if (getV() != null) {
            getV().F(i2, i3, null);
        }
    }

    public final void U(Intent intent) {
        if (getV() != null) {
            getV().j0(intent.getData());
        }
        b.b.m.c.a.a(f937a, ", ---onUFlashMounted---intent.getData():" + intent);
        Bundle extras = intent.getExtras();
        for (String str : extras.keySet()) {
            b.b.m.c.a.a(f937a, ", ---onUFlashMounted---k: " + str + ", v: " + extras.get(str));
        }
    }

    public final void V(Intent intent) {
        this.f939c = intent;
        this.f940d = intent.getData();
        if ("android.intent.action.MEDIA_UNMOUNTED".equals(intent.getAction())) {
            if (getV() != null) {
                getV().l0(this.f940d);
            }
        } else if ("android.intent.action.MEDIA_REMOVED".equals(intent.getAction())) {
            if (getV() != null) {
                getV().k0(this.f940d);
            }
        } else {
            b.b.a.f.h.a().g(new a(intent), 2000L);
            b.b.m.c.a.a(f937a, ", ---onUFlashUnmounted---:intent=" + intent);
        }
    }

    public void W(String str) {
        b.b.a.f.h.a().j(new h(str));
    }

    public void init(Context context) {
        this.f938b = new BydUsbReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_EJECT");
        intentFilter.addAction("android.intent.action.MEDIA_REMOVED");
        intentFilter.addAction("action:android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter.addAction("action:android.hardware.usb.action.USB_DEVICE_DETACHED");
        intentFilter.addDataScheme("file");
        getV().registerReceiver(this.f938b, intentFilter, "android.permission.READ_EXTERNAL_STORAGE", null);
        b.b.g.e.a.c("usb_state", new l(this));
        this.f941e = new CountDownLatch(5);
        this.f942f = (StorageManager) context.getSystemService(StorageManager.class);
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        if (this.f938b != null) {
            getV().unregisterReceiver(this.f938b);
        }
        super.unbindV();
        b.b.g.e.a.a();
    }
}
