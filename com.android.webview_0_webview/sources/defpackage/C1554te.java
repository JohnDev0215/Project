package defpackage;

import android.os.DeadObjectException;
import com.google.android.gms.common.api.Status;
import java.util.Objects;

/* JADX INFO: renamed from: te, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1554te extends AbstractC0218Md {
    public final AbstractC0343Tc b;

    public C1554te(int i, AbstractC0343Tc abstractC0343Tc) {
        super(i);
        this.b = abstractC0343Tc;
    }

    @Override // defpackage.AbstractC0218Md
    public final void a(C0415Xc c0415Xc) throws DeadObjectException {
        try {
            this.b.j(c0415Xc.b);
        } catch (RuntimeException e) {
            c(e);
        }
    }

    @Override // defpackage.AbstractC0218Md
    public final void b(C1389qd c1389qd, boolean z) {
        AbstractC0343Tc abstractC0343Tc = this.b;
        c1389qd.a.put(abstractC0343Tc, Boolean.valueOf(z));
        C1334pd c1334pd = new C1334pd(c1389qd, abstractC0343Tc);
        Objects.requireNonNull(abstractC0343Tc);
        Cif.b(true, "Callback cannot be null.");
        synchronized (abstractC0343Tc.a) {
            if (abstractC0343Tc.c()) {
                c1334pd.b.a.remove(c1334pd.a);
            } else {
                abstractC0343Tc.e.add(c1334pd);
            }
        }
    }

    @Override // defpackage.AbstractC0218Md
    public final void c(Exception exc) {
        String simpleName = exc.getClass().getSimpleName();
        String localizedMessage = exc.getLocalizedMessage();
        StringBuilder sb = new StringBuilder(String.valueOf(localizedMessage).length() + simpleName.length() + 2);
        sb.append(simpleName);
        sb.append(": ");
        sb.append(localizedMessage);
        this.b.k(new Status(10, sb.toString()));
    }

    @Override // defpackage.AbstractC0218Md
    public final void e(Status status) {
        this.b.k(status);
    }
}
