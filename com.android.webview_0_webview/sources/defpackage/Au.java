package defpackage;

import J.N;
import android.os.Debug;
import android.os.RemoteException;
import android.util.SparseArray;
import java.util.Objects;
import org.chromium.base.process_launcher.FileDescriptorInfo;
import org.chromium.base.task.PostTask;
import org.chromium.content.app.ContentChildProcessServiceDelegate;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Au implements Runnable {
    public final /* synthetic */ Bu s;

    public Au(Bu bu) {
        this.s = bu;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            synchronized (this.s.i) {
                while (this.s.j == null) {
                    this.s.i.wait();
                }
            }
            Es.i(this.s.j);
            if (Es.e().h("renderer-wait-for-java-debugger")) {
                Debug.waitForDebugger();
            }
            ((ContentChildProcessServiceDelegate) this.s.a).a(this.s.c);
            synchronized (this.s.e) {
                this.s.l = true;
                this.s.e.notifyAll();
            }
            synchronized (this.s.i) {
                this.s.i.notifyAll();
                while (this.s.k == null) {
                    this.s.i.wait();
                }
            }
            SparseArray sparseArray = ((ContentChildProcessServiceDelegate) this.s.a).e;
            int[] iArr = new int[this.s.k.length];
            String[] strArr = new String[this.s.k.length];
            int[] iArr2 = new int[this.s.k.length];
            long[] jArr = new long[this.s.k.length];
            long[] jArr2 = new long[this.s.k.length];
            for (int i = 0; i < this.s.k.length; i++) {
                FileDescriptorInfo fileDescriptorInfo = this.s.k[i];
                String str = sparseArray != null ? (String) sparseArray.get(fileDescriptorInfo.s) : null;
                if (str != null) {
                    strArr[i] = str;
                } else {
                    iArr[i] = fileDescriptorInfo.s;
                }
                iArr2[i] = fileDescriptorInfo.t.detachFd();
                jArr[i] = fileDescriptorInfo.u;
                jArr2[i] = fileDescriptorInfo.v;
            }
            N.Ma6rsNQO(strArr, iArr, iArr2, jArr, jArr2);
            ContentChildProcessServiceDelegate contentChildProcessServiceDelegate = (ContentChildProcessServiceDelegate) this.s.a;
            N.MBlO3kR9(contentChildProcessServiceDelegate, contentChildProcessServiceDelegate.c, contentChildProcessServiceDelegate.d);
            PostTask.a(AbstractC0538bE.a, new Runnable() { // from class: zy
                @Override // java.lang.Runnable
                public void run() {
                    Dt.a();
                }
            }, 0L);
            Objects.requireNonNull((ContentChildProcessServiceDelegate) this.s.a);
            N.M1Y_XVCN(false);
            try {
                this.s.n.b();
            } catch (RemoteException e) {
                Ts.a("ChildProcessService", "Failed to call clean exit callback.", e);
            }
            N.McvJWQ0j();
        } catch (InterruptedException e2) {
            Ts.f("ChildProcessService", "%s startup failed: %s", "ChildProcessMain", e2);
        }
    }
}
