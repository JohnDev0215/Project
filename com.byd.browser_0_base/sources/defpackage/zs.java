package defpackage;

import android.content.Context;
import defpackage.fp3;
import defpackage.tp3;
import java.util.Iterator;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ThreadUtils;
import org.chromium.base.b;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: ChromeWebApkHost.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zs {
    public static ApplicationStatus.d a;

    /* JADX INFO: compiled from: ChromeWebApkHost.java */
    public class a implements ApplicationStatus.d {
        @Override // org.chromium.base.ApplicationStatus.d
        public final void v(int i) {
            if (i == 3 || i == 4) {
                zs.b(false);
                ApplicationStatus.d dVar = zs.a;
                b<ApplicationStatus.d> bVar = ApplicationStatus.f;
                if (bVar != null) {
                    bVar.c(dVar);
                }
                zs.a = null;
            }
        }
    }

    public static void a(String str, fp3.a aVar) {
        Object obj = ThreadUtils.a;
        if (a == null) {
            a aVar2 = new a();
            a = aVar2;
            ApplicationStatus.e(aVar2);
        }
        d73 d73Var = rh3.a;
        if (fp3.b == null) {
            fp3.b = new fp3(d73Var);
        }
        fp3 fp3Var = fp3.b;
        Context context = xz.a;
        fp3Var.getClass();
        fp3Var.a.a(context, str, new ep3(context, str, aVar));
    }

    public static void b(boolean z) {
        boolean z2;
        if (z) {
            rp3 rp3Var = rp3.b;
            boolean z3 = false;
            if (rp3Var != null) {
                Iterator<tp3.a> it = rp3Var.a.f.values().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z2 = true;
                        break;
                    } else if (!it.next().f.isEmpty()) {
                        z2 = false;
                        break;
                    }
                }
                if (!z2) {
                    z3 = true;
                }
            }
            if (z3) {
                PostTask.b(rh3.a, new wz(24), 1000L);
                return;
            }
        }
        Context context = xz.a;
        fp3 fp3Var = fp3.b;
        if (fp3Var != null) {
            fp3Var.a.b(context);
        }
        rp3 rp3Var2 = rp3.b;
        if (rp3Var2 == null) {
            return;
        }
        rp3Var2.a.b(xz.a);
    }

    public static void c() {
        byte[] bArr = a8.h;
        byte[] bArr2 = a8.i;
        if (fq3.a == null) {
            fq3.a = bArr;
        }
        if (fq3.b == null) {
            fq3.b = bArr2;
        }
        if (iv.e().g("skip-webapk-verification")) {
            fq3.d = true;
        }
    }
}
