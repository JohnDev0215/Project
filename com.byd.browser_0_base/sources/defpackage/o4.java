package defpackage;

import android.app.Activity;
import android.app.Dialog;
import org.chromium.base.ApplicationStatus;

/* JADX INFO: compiled from: AlwaysDismissedDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class o4 extends Dialog implements ApplicationStatus.c {
    public o4(Activity activity, int i) {
        super(activity, i);
        ApplicationStatus.f(this, activity);
        setOwnerActivity(activity);
    }

    @Override // org.chromium.base.ApplicationStatus.c
    public final void d(Activity activity, int i) {
        if (i == 6) {
            dismiss();
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        ApplicationStatus.i(this);
    }
}
