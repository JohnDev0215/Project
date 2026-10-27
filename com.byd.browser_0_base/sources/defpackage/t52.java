package defpackage;

import J.N;
import defpackage.an;
import java.util.ArrayList;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.partnercustomizations.PartnerBrowserCustomizations;
import org.chromium.ui.base.SelectFileDialog;

/* JADX INFO: compiled from: ProcessInitializationHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class t52 implements Runnable {
    public final /* synthetic */ b62 e;

    /* JADX INFO: compiled from: ProcessInitializationHandler.java */
    public class a implements Runnable {
        @Override // java.lang.Runnable
        public final void run() {
            String strC = qv0.c();
            boolean zE = qv0.e();
            boolean zK = bk3.k(strC);
            ArrayList arrayList = t51.a;
            if (strC == null) {
                strC = "";
            }
            N.M7xe7yjp(zE, zK, strC);
        }
    }

    public t52(b62 b62Var) {
        this.e = b62Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        b62 b62Var = this.e;
        b62Var.getClass();
        r52 r52Var = new r52(b62Var);
        f8 f8Var = i9.e;
        r52Var.c(f8Var);
        synchronized (j70.a) {
            if (j70.b == null) {
                h70 h70Var = new h70();
                j70.b = h70Var;
                h70Var.c(f8Var);
            }
        }
        N.MSOABg7U();
        PartnerBrowserCustomizations.b().h(new a());
        i9.f.execute(new gg3(14));
        String[] strArr = SelectFileDialog.l;
        d73 d73Var = d73.g;
        PostTask.c(d73Var, new o90(16));
        Object obj = an.e;
        an anVar = an.a.a;
        if (anVar.c && anVar.b.d(-1, "channels_version_key") != anVar.d) {
            PostTask.b(d73Var, new gg3(8), 0L);
        }
    }
}
