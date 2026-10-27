package defpackage;

import java.io.File;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Ux implements Runnable {
    public final Rx s;
    public final /* synthetic */ Vx t;

    public Ux(Vx vx, Rx rx) {
        this.t = vx;
        this.s = rx;
    }

    @Override // java.lang.Runnable
    public void run() {
        Objects.requireNonNull((C0850gs) this.t.a);
        File fileB = AbstractC0267Oq.b();
        if (!fileB.isDirectory()) {
            Ts.a("MDUploadJobImpl", "Parent crash directory doesn't exist!", new Object[0]);
            ((Wx) this.s).a(false);
            return;
        }
        Objects.requireNonNull(this.t);
        Px px = new Px(fileB);
        if (!px.e().isDirectory()) {
            Ts.a("MDUploadJobImpl", "Crash directory doesn't exist!", new Object[0]);
            ((Wx) this.s).a(false);
            return;
        }
        File[] fileArrH = px.h(3);
        Ts.d("MDUploadJobImpl", "Attempting to upload %d minidumps.", Integer.valueOf(fileArrH.length));
        for (File file : fileArrH) {
            StringBuilder sbF = Z7.f("Attempting to upload ");
            sbF.append(file.getName());
            Ts.d("MDUploadJobImpl", sbF.toString(), new Object[0]);
            Vx vx = this.t;
            File file2 = new File(px.e(), "uploads.log");
            C0850gs c0850gs = (C0850gs) vx.a;
            Objects.requireNonNull(c0850gs);
            int iIntValue = new Qx(file, file2, new C0738es(c0850gs)).call().intValue();
            if (iIntValue == 0) {
                Objects.requireNonNull((C0850gs) this.t.a);
            } else if (iIntValue == 1 && Px.l(file.getName()) + 1 == 3) {
                Objects.requireNonNull((C0850gs) this.t.a);
            }
            if (this.t.b) {
                return;
            }
            if (iIntValue == 1 && Px.o(file) == null) {
                Ts.f("MDUploadJobImpl", "Failed to increment attempt number of " + file, new Object[0]);
            }
        }
        px.a();
        ((Wx) this.s).a(px.h(3).length > 0);
    }
}
