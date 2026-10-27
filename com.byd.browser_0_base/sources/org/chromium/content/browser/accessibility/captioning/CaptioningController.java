package org.chromium.content.browser.accessibility.captioning;

import J.N;
import defpackage.cm;
import defpackage.cy2;
import defpackage.dm;
import defpackage.m83;
import java.util.Objects;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: loaded from: classes3.dex */
public class CaptioningController implements cy2 {
    public final cm a;
    public long b;

    public CaptioningController(WebContents webContents) {
        if (cm.c == null) {
            cm.c = new cm();
        }
        this.a = cm.c;
        this.b = N.MX95jWaj(this, webContents);
    }

    @Override // defpackage.cy2
    public final void a(m83 m83Var) {
        long j = this.b;
        if (j == 0) {
            return;
        }
        N.MM3_AH7F(j, this, m83Var.a, Objects.toString(m83Var.b, ""), Objects.toString(m83Var.c, ""), Objects.toString(m83Var.d, ""), Objects.toString(m83Var.e, ""), Objects.toString(m83Var.f, ""), Objects.toString(m83Var.g, ""), Objects.toString(m83Var.h, ""));
    }

    @CalledByNative
    public final void onDestroy() {
        this.b = 0L;
    }

    @CalledByNative
    public final void onRenderProcessChange() {
        cm cmVar = this.a;
        dm dmVar = cmVar.a;
        if (!(!dmVar.i.isEmpty())) {
            cmVar.b();
        }
        dmVar.b(this);
    }
}
