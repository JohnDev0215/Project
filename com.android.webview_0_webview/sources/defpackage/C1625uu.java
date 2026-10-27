package defpackage;

import J.N;
import java.io.IOException;
import java.util.Objects;
import org.chromium.base.process_launcher.FileDescriptorInfo;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;

/* JADX INFO: renamed from: uu, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1625uu implements InterfaceC1241nu {
    public final /* synthetic */ C1735wu a;

    public C1625uu(C1735wu c1735wu) {
        this.a = c1735wu;
    }

    public void a(C1406qu c1406qu) {
        C1735wu c1735wu = this.a;
        C1406qu c1406qu2 = c1735wu.g;
        int i = c1406qu2.l;
        Qy qy = (Qy) c1735wu.b;
        Objects.requireNonNull(qy);
        int i2 = c1406qu2.l;
        if (i2 > 0) {
            ChildProcessLauncherHelperImpl.r.put(Integer.valueOf(i2), qy.a);
            Yy yy = qy.a.a;
            if (yy != null) {
                if (yy.t != -1 && yy.u.size() >= yy.t) {
                    StringBuilder sbF = Z7.f("mRankings.size:");
                    sbF.append(yy.u.size());
                    sbF.append(" mMaxSize:");
                    sbF.append(yy.t);
                    throw new RuntimeException(sbF.toString());
                }
                yy.u.add(new Vy(c1406qu2, false, 1L, false, 1));
                yy.g(yy.u.size() - 1);
                Objects.requireNonNull(qy.a);
            }
        }
        long j = qy.a.g;
        if (j != 0) {
            N.MXR$KaDS(j, c1406qu2.l);
        }
        qy.a.g = 0L;
        try {
            for (FileDescriptorInfo fileDescriptorInfo : c1735wu.d) {
                fileDescriptorInfo.t.close();
            }
        } catch (IOException e) {
            Ts.f("ChildProcLauncher", "Failed to close FD.", e);
        }
    }
}
