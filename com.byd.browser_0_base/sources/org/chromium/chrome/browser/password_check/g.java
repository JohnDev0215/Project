package org.chromium.chrome.browser.password_check;

import android.content.DialogInterface;
import org.chromium.chrome.browser.password_check.PasswordCheckDialogFragment;

/* JADX INFO: compiled from: PasswordCheckMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g implements PasswordCheckDialogFragment.a {
    public final /* synthetic */ f e;

    public g(f fVar) {
        this.e = fVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        f fVar = this.e;
        fVar.c.p(h.d, null);
        fVar.c.p(h.e, null);
    }

    @Override // org.chromium.chrome.browser.password_check.PasswordCheckDialogFragment.a
    public final void onDismiss() {
        this.e.c.p(h.e, null);
    }
}
