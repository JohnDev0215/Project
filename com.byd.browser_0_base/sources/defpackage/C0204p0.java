package defpackage;

import J.N;
import android.text.style.ClickableSpan;
import android.view.View;
import org.chromium.chrome.browser.password_manager.AccountChooserDialog;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes2.dex
 */
/* JADX INFO: renamed from: p0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AccountChooserDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class C0204p0 extends ClickableSpan {
    public final /* synthetic */ AccountChooserDialog e;

    public C0204p0(AccountChooserDialog accountChooserDialog) {
        this.e = accountChooserDialog;
    }

    @Override // android.text.style.ClickableSpan
    public final void onClick(View view) {
        AccountChooserDialog accountChooserDialog = this.e;
        long j = accountChooserDialog.n;
        if (j != 0) {
            N.MOFoPxaU(j, accountChooserDialog);
        }
        accountChooserDialog.o.dismiss();
    }
}
