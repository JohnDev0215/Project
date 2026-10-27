package defpackage;

import J.N;
import android.app.Service;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import defpackage.aw0;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.base.library_loader.a;
import org.chromium.base.process_launcher.FileDescriptorInfo;
import org.chromium.content.app.ContentChildProcessServiceDelegate;

/* JADX INFO: compiled from: ChildProcessService.java */
/* JADX INFO: loaded from: classes.dex */
public final class zn {
    public static boolean p;
    public static int q;
    public static long r;
    public final bo a;
    public final Service b;
    public final Context c;
    public boolean f;
    public int g;
    public String h;
    public Thread i;
    public String[] j;
    public FileDescriptorInfo[] k;
    public boolean l;
    public boolean m;
    public iw0 n;
    public final Object d = new Object();
    public final Object e = new Object();
    public final a o = new a();

    /* JADX INFO: compiled from: ChildProcessService.java */
    public class a extends aw0.a {
        public a() {
        }

        @Override // defpackage.aw0
        public final void b(Bundle bundle) {
            ((ContentChildProcessServiceDelegate) zn.this.a).getClass();
            org.chromium.base.library_loader.a.l.g.f(bundle);
        }

        @Override // defpackage.aw0
        public final ApplicationInfo i() {
            return zn.this.c.getApplicationInfo();
        }

        @Override // defpackage.aw0
        public final void o() {
            synchronized (zn.this.e) {
                if (zn.this.l) {
                    N.M6Y7Jzgj();
                } else {
                    Log.e("cr_ChildProcessService", "Cannot dump process stack before native is loaded");
                }
            }
        }

        @Override // defpackage.aw0
        public final void p(Bundle bundle, iw0 iw0Var, List<IBinder> list) {
            boolean z;
            long j;
            Bundle bundle2;
            int i;
            synchronized (zn.this.d) {
                zn znVar = zn.this;
                if (znVar.f && znVar.g == 0) {
                    Log.e("cr_ChildProcessService", "Service has not been bound with bindToCaller()  ");
                    iw0Var.m(-1, 0, 0L, null);
                    return;
                }
                int iMyPid = Process.myPid();
                org.chromium.base.library_loader.a aVar = org.chromium.base.library_loader.a.l;
                synchronized (aVar.h) {
                    z = aVar.j;
                }
                if (z) {
                    int i2 = zn.q;
                    long j2 = zn.r;
                    a.C0070a c0070a = aVar.g;
                    c0070a.c();
                    Bundle bundle3 = new Bundle();
                    c0070a.e(bundle3);
                    bundle2 = bundle3;
                    j = j2;
                    i = i2;
                } else {
                    j = -1;
                    bundle2 = null;
                    i = 0;
                }
                iw0Var.m(iMyPid, i, j, bundle2);
                zn znVar2 = zn.this;
                znVar2.n = iw0Var;
                bundle.setClassLoader(znVar2.c.getClassLoader());
                synchronized (znVar2.i) {
                    if (znVar2.j == null) {
                        znVar2.j = bundle.getStringArray("org.chromium.base.process_launcher.extra.command_line");
                        znVar2.i.notifyAll();
                    }
                    Parcelable[] parcelableArray = bundle.getParcelableArray("org.chromium.base.process_launcher.extra.extraFiles");
                    if (parcelableArray != null) {
                        FileDescriptorInfo[] fileDescriptorInfoArr = new FileDescriptorInfo[parcelableArray.length];
                        znVar2.k = fileDescriptorInfoArr;
                        System.arraycopy(parcelableArray, 0, fileDescriptorInfoArr, 0, parcelableArray.length);
                    }
                    ((ContentChildProcessServiceDelegate) znVar2.a).b(list, bundle);
                    znVar2.i.notifyAll();
                }
            }
        }

        @Override // defpackage.aw0
        public final boolean r(String str) {
            synchronized (zn.this.d) {
                int callingPid = Binder.getCallingPid();
                zn znVar = zn.this;
                int i = znVar.g;
                if (i == 0 && znVar.h == null) {
                    znVar.g = callingPid;
                    znVar.h = str;
                } else {
                    if (i != callingPid) {
                        fa1.c("ChildProcessService", "Service is already bound by pid %d, cannot bind for pid %d", Integer.valueOf(i), Integer.valueOf(callingPid));
                        return false;
                    }
                    if (!TextUtils.equals(znVar.h, str)) {
                        fa1.l("ChildProcessService", "Service is already bound by %s, cannot bind for %s", zn.this.h, str);
                        return false;
                    }
                }
                return true;
            }
        }

        @Override // defpackage.aw0
        public final void x(final int i) {
            ThreadUtils.e(new Runnable() { // from class: yn
                @Override // java.lang.Runnable
                public final void run() {
                    ve1 ve1Var = ve1.i;
                    ve1Var.getClass();
                    Object obj = ThreadUtils.a;
                    int i2 = ve1Var.b;
                    int i3 = i;
                    if (i3 >= i2) {
                        ve1Var.a(i3);
                    }
                }
            });
        }

        @Override // defpackage.aw0
        public final void y() {
            Process.killProcess(Process.myPid());
        }
    }

    public zn(ContentChildProcessServiceDelegate contentChildProcessServiceDelegate, Service service, Context context) {
        this.a = contentChildProcessServiceDelegate;
        this.b = service;
        this.c = context;
    }
}
