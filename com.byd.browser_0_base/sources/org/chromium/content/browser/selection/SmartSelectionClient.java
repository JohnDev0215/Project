package org.chromium.content.browser.selection;

import J.N;
import android.text.TextUtils;
import defpackage.ci2;
import defpackage.np2;
import defpackage.qp2;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: loaded from: classes3.dex */
public class SmartSelectionClient implements ci2 {
    public long a;
    public final qp2 b;
    public final ci2.b c;
    public final np2 d;

    public SmartSelectionClient(SelectionPopupControllerImpl.b bVar, WebContents webContents) {
        this.c = bVar;
        np2 np2Var = webContents.V().i.get() == null ? null : new np2(webContents);
        this.d = np2Var;
        this.b = new qp2(bVar, webContents, np2Var);
        this.a = N.MFA_dMJC(this, webContents);
    }

    @CalledByNative
    public final void onNativeSideDestroyed(long j) {
        this.a = 0L;
        qp2 qp2Var = this.b;
        qp2.a aVar = qp2Var.c;
        if (aVar != null) {
            aVar.a(false);
            qp2Var.c = null;
        }
    }

    @CalledByNative
    public final void onSurroundingTextReceived(int i, String str, int i2, int i3) {
        if (!(!TextUtils.isEmpty(str) && i2 >= 0 && i2 < i3 && i3 <= str.length())) {
            ((SelectionPopupControllerImpl.b) this.c).a(new ci2.a());
            return;
        }
        qp2 qp2Var = this.b;
        if (i == 0) {
            qp2Var.b(0, i2, i3, str);
        } else {
            if (i != 1) {
                return;
            }
            qp2Var.b(1, i2, i3, str);
        }
    }
}
