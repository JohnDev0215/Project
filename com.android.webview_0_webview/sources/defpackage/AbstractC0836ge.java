package defpackage;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* JADX INFO: renamed from: ge, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0836ge extends AbstractC1335pe {
    public final C1614uj b;

    public AbstractC0836ge(int i, C1614uj c1614uj) {
        super(i);
        this.b = c1614uj;
    }

    @Override // defpackage.AbstractC0218Md
    public final void a(C0415Xc c0415Xc) throws DeadObjectException {
        try {
            h(c0415Xc);
        } catch (DeadObjectException e) {
            this.b.c(new C0038Cc(AbstractC0218Md.d(e)));
            throw e;
        } catch (RemoteException e2) {
            this.b.c(new C0038Cc(AbstractC0218Md.d(e2)));
        } catch (RuntimeException e3) {
            this.b.c(e3);
        }
    }

    @Override // defpackage.AbstractC0218Md
    public void c(Exception exc) {
        this.b.c(exc);
    }

    @Override // defpackage.AbstractC0218Md
    public void e(Status status) {
        this.b.c(new C0038Cc(status));
    }

    public abstract void h(C0415Xc c0415Xc);
}
