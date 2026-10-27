package defpackage;

import J.N;
import android.os.Debug;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import org.chromium.base.EarlyTraceEvent;
import org.chromium.base.ThreadUtils;
import org.chromium.base.process_launcher.FileDescriptorInfo;
import org.chromium.content.app.ContentChildProcessServiceDelegate;

/* JADX INFO: compiled from: ChildProcessService.java */
/* JADX INFO: loaded from: classes.dex */
public final class ao implements Runnable {
    public final /* synthetic */ zn e;

    public ao(zn znVar) {
        this.e = znVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String[] strArr;
        zn znVar;
        FileDescriptorInfo[] fileDescriptorInfoArr;
        try {
            synchronized (this.e.i) {
                while (true) {
                    zn znVar2 = this.e;
                    strArr = znVar2.j;
                    if (strArr != null) {
                        break;
                    } else {
                        znVar2.i.wait();
                    }
                }
            }
            iv.h(strArr);
            if (iv.e().g("renderer-wait-for-java-debugger")) {
                Debug.waitForDebugger();
            }
            EarlyTraceEvent.g();
            zn znVar3 = this.e;
            ((ContentChildProcessServiceDelegate) znVar3.a).a(znVar3.c);
            synchronized (this.e.e) {
                zn znVar4 = this.e;
                znVar4.l = true;
                znVar4.e.notifyAll();
            }
            synchronized (this.e.i) {
                this.e.i.notifyAll();
                while (true) {
                    znVar = this.e;
                    fileDescriptorInfoArr = znVar.k;
                    if (fileDescriptorInfoArr != null) {
                        break;
                    } else {
                        znVar.i.wait();
                    }
                }
            }
            SparseArray<String> sparseArray = ((ContentChildProcessServiceDelegate) znVar.a).d;
            int[] iArr = new int[fileDescriptorInfoArr.length];
            String[] strArr2 = new String[fileDescriptorInfoArr.length];
            int[] iArr2 = new int[fileDescriptorInfoArr.length];
            long[] jArr = new long[fileDescriptorInfoArr.length];
            long[] jArr2 = new long[fileDescriptorInfoArr.length];
            int i = 0;
            while (true) {
                FileDescriptorInfo[] fileDescriptorInfoArr2 = this.e.k;
                if (i >= fileDescriptorInfoArr2.length) {
                    break;
                }
                FileDescriptorInfo fileDescriptorInfo = fileDescriptorInfoArr2[i];
                String str = sparseArray != null ? sparseArray.get(fileDescriptorInfo.e) : null;
                if (str != null) {
                    strArr2[i] = str;
                } else {
                    iArr[i] = fileDescriptorInfo.e;
                }
                iArr2[i] = fileDescriptorInfo.f.detachFd();
                jArr[i] = fileDescriptorInfo.g;
                jArr2[i] = fileDescriptorInfo.h;
                i++;
            }
            N.Ma6rsNQO(strArr2, iArr, iArr2, jArr, jArr2);
            ContentChildProcessServiceDelegate contentChildProcessServiceDelegate = (ContentChildProcessServiceDelegate) this.e.a;
            N.MBlO3kR9(contentChildProcessServiceDelegate, contentChildProcessServiceDelegate.b, contentChildProcessServiceDelegate.c);
            ThreadUtils.c().post(new wz(26));
            long jUptimeMillis = SystemClock.uptimeMillis() - Process.getStartUptimeMillis();
            String str2 = Process.isIsolated() ? ".Isolated" : ".NotIsolated";
            oj2.Y(jUptimeMillis, "Android.ChildProcessStartTimeV2.All");
            oj2.Y(jUptimeMillis, "Android.ChildProcessStartTimeV2".concat(str2));
            ((ContentChildProcessServiceDelegate) this.e.a).getClass();
            N.M1Y_XVCN(false);
            try {
                this.e.n.l();
            } catch (RemoteException e) {
                Log.e("cr_ChildProcessService", "Failed to call clean exit callback.", e);
            }
            N.McvJWQ0j();
        } catch (Throwable th) {
            try {
                this.e.n.f(zn.class.getName() + "\n" + Log.getStackTraceString(th));
            } catch (RemoteException e2) {
                Log.e("cr_ChildProcessService", "Failed to call reportExceptionInInit.", e2);
            }
            throw new uv(th);
        }
    }
}
