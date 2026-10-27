package defpackage;

import android.app.Activity;
import org.chromium.base.ApplicationStatus;

/* JADX INFO: compiled from: PaymentHandlerMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zy1 implements ApplicationStatus.c {
    public final /* synthetic */ az1 e;

    public zy1(az1 az1Var) {
        this.e = az1Var;
    }

    @Override // org.chromium.base.ApplicationStatus.c
    public final void d(Activity activity, int i) {
        if (i == 6) {
            az1 az1Var = this.e;
            az1Var.o = 2;
            az1Var.k.post(az1Var.g);
        }
    }
}
