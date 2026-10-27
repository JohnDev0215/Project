package defpackage;

import android.util.Log;
import defpackage.qn;
import defpackage.un;
import defpackage.xn;
import java.util.ArrayList;
import org.chromium.content.browser.ChildProcessLauncherHelperImpl;

/* JADX INFO: compiled from: ChildProcessLauncher.java */
/* JADX INFO: loaded from: classes.dex */
public final class tn implements qn.e {
    public final /* synthetic */ boolean a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ un c;

    /* JADX INFO: compiled from: ChildProcessLauncher.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            tn.this.c.g = null;
            tn tnVar = tn.this;
            tnVar.c.c(tnVar.a, tnVar.b);
        }
    }

    public tn(un unVar, boolean z, boolean z2) {
        this.c = unVar;
        this.a = z;
        this.b = z2;
    }

    @Override // qn.e
    public final void a(qn qnVar) {
        un unVar = this.c;
        int i = 0;
        if ((unVar.g == null ? 0 : unVar.g.o) != 0) {
            un.c cVar = unVar.b;
            qn qnVar2 = unVar.g;
            ChildProcessLauncherHelperImpl.a aVar = (ChildProcessLauncherHelperImpl.a) cVar;
            aVar.getClass();
            int i2 = qnVar2.o;
            if (i2 == 0 || ((ChildProcessLauncherHelperImpl) ChildProcessLauncherHelperImpl.s.remove(Integer.valueOf(i2))) == null) {
                return;
            }
            ChildProcessLauncherHelperImpl childProcessLauncherHelperImpl = ChildProcessLauncherHelperImpl.this;
            re reVar = childProcessLauncherHelperImpl.b;
            if (reVar != null && reVar.e.remove(qnVar2)) {
                if (qnVar2 == reVar.i) {
                    reVar.i = null;
                } else {
                    re.d(qnVar2);
                }
            }
            xn xnVar = childProcessLauncherHelperImpl.a;
            if (xnVar != null) {
                while (true) {
                    ArrayList arrayList = xnVar.g;
                    if (i >= arrayList.size()) {
                        i = -1;
                        break;
                    } else if (((xn.a) arrayList.get(i)).a == qnVar2) {
                        break;
                    } else {
                        i++;
                    }
                }
                xnVar.g.remove(i);
                re reVar2 = childProcessLauncherHelperImpl.b;
                if (reVar2 != null) {
                    reVar2.c();
                }
            }
            if (childProcessLauncherHelperImpl.c) {
                rn.a().c.remove(qnVar2);
            }
        }
    }

    @Override // qn.e
    public final void b(qn qnVar) {
        Log.e("cr_ChildProcLauncher", "ChildProcessConnection.start failed, trying again");
        this.c.a.post(new a());
    }

    @Override // qn.e
    public final void c() {
    }
}
