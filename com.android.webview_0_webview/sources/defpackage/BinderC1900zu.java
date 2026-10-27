package defpackage;

import J.N;
import android.os.Binder;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Process;
import android.text.TextUtils;
import java.util.List;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.base.process_launcher.FileDescriptorInfo;
import org.chromium.content.app.ContentChildProcessServiceDelegate;

/* JADX INFO: renamed from: zu, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class BinderC1900zu extends Fu {
    public final /* synthetic */ Bu a;

    public BinderC1900zu(Bu bu) {
        this.a = bu;
    }

    @Override // defpackage.Gu
    public void h(Bundle bundle, Ju ju, List list) {
        synchronized (this.a.d) {
            if (this.a.f && this.a.g == 0) {
                Ts.a("ChildProcessService", "Service has not been bound with bindToCaller()", new Object[0]);
                ju.r(-1);
                return;
            }
            ju.r(Process.myPid());
            Bu bu = this.a;
            bu.n = ju;
            bundle.setClassLoader(bu.c.getClassLoader());
            synchronized (bu.i) {
                if (bu.j == null) {
                    bu.j = bundle.getStringArray("org.chromium.base.process_launcher.extra.command_line");
                    bu.i.notifyAll();
                }
                Parcelable[] parcelableArray = bundle.getParcelableArray("org.chromium.base.process_launcher.extra.extraFiles");
                if (parcelableArray != null) {
                    FileDescriptorInfo[] fileDescriptorInfoArr = new FileDescriptorInfo[parcelableArray.length];
                    bu.k = fileDescriptorInfoArr;
                    System.arraycopy(parcelableArray, 0, fileDescriptorInfoArr, 0, parcelableArray.length);
                }
                ((ContentChildProcessServiceDelegate) bu.a).b(bundle, list);
                bu.i.notifyAll();
            }
        }
    }

    @Override // defpackage.Gu
    public void k() {
        synchronized (this.a.e) {
            if (this.a.l) {
                N.M6Y7Jzgj();
            } else {
                Ts.a("ChildProcessService", "Cannot dump process stack before native is loaded", new Object[0]);
            }
        }
    }

    @Override // defpackage.Gu
    public void l(final int i) {
        ThreadUtils.e(new Runnable(i) { // from class: yu
            public final int s;

            {
                this.s = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                int i2 = this.s;
                Ct ct = Ct.i;
                Objects.requireNonNull(ct);
                ThreadUtils.b();
                if (i2 >= ct.b) {
                    Ct.i.c(i2);
                }
            }
        });
    }

    @Override // defpackage.Gu
    public void m() {
        Process.killProcess(Process.myPid());
    }

    @Override // defpackage.Gu
    public boolean o(String str) {
        synchronized (this.a.d) {
            int callingPid = Binder.getCallingPid();
            if (this.a.g == 0 && this.a.h == null) {
                this.a.g = callingPid;
                this.a.h = str;
            } else {
                if (this.a.g != callingPid) {
                    Ts.a("ChildProcessService", "Service is already bound by pid %d, cannot bind for pid %d", Integer.valueOf(this.a.g), Integer.valueOf(callingPid));
                    return false;
                }
                if (!TextUtils.equals(this.a.h, str)) {
                    Ts.f("ChildProcessService", "Service is already bound by %s, cannot bind for %s", this.a.h, str);
                    return false;
                }
            }
            return true;
        }
    }
}
