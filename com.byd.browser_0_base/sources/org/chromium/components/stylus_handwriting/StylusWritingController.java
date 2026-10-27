package org.chromium.components.stylus_handwriting;

import android.content.Context;
import defpackage.ia0;
import defpackage.j5;
import defpackage.ka0;
import defpackage.n3;
import defpackage.tv2;
import defpackage.yj;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: loaded from: classes3.dex */
public class StylusWritingController {
    public final Context a;
    public WebContents b;
    public j5 c;
    public ka0 d;
    public n3 e;

    public StylusWritingController(Context context) {
        this.a = context;
    }

    public final tv2 a() {
        Context context = this.a;
        if (ia0.a(context)) {
            if (this.d == null) {
                this.d = new ka0();
            }
            return this.d;
        }
        if (yj.a() && j5.p(context)) {
            if (this.c == null) {
                this.c = new j5(context);
            }
            return this.c;
        }
        if (this.e == null) {
            this.e = new n3(0);
        }
        return this.e;
    }

    public final void b(WebContents webContents) {
        if (webContents.u() == null) {
            return;
        }
        this.b = webContents;
        tv2 tv2VarA = a();
        tv2VarA.h(this.a, webContents);
        webContents.u().f = tv2VarA.f();
    }
}
