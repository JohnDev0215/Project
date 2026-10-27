package defpackage;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;

/* JADX INFO: renamed from: Tc, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0343Tc extends BasePendingResult implements InterfaceC0361Uc {
    public final AbstractC1662vc p;
    public final C0020Bc q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractC0343Tc(C0020Bc c0020Bc, AbstractC0146Ic abstractC0146Ic) {
        super(abstractC0146Ic);
        Cif.g(abstractC0146Ic, "GoogleApiClient must not be null");
        Cif.g(c0020Bc, "Api must not be null");
        this.p = c0020Bc.a();
        this.q = c0020Bc;
    }

    public abstract void i(InterfaceC1607uc interfaceC1607uc);

    public final void j(InterfaceC1607uc interfaceC1607uc) throws DeadObjectException {
        try {
            i(interfaceC1607uc);
        } catch (DeadObjectException e) {
            k(new Status(1, 8, e.getLocalizedMessage(), null));
            throw e;
        } catch (RemoteException e2) {
            k(new Status(1, 8, e2.getLocalizedMessage(), null));
        }
    }

    public final void k(Status status) {
        Cif.b(!(status.t <= 0), "Failed result must not be success");
        d(status);
    }
}
