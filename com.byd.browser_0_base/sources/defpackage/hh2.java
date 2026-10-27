package defpackage;

import android.view.View;
import org.chromium.base.Callback;
import org.chromium.components.payments.R$string;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: SecurePaymentConfirmationAuthnController.java */
/* JADX INFO: loaded from: classes3.dex */
public final class hh2 {
    public final WebContents a;
    public m91 b;
    public Callback<Boolean> c;
    public Runnable d;
    public jh2 e;
    public final j01 f = new j01();
    public final a g = new a();
    public final b h = new b();

    /* JADX INFO: compiled from: SecurePaymentConfirmationAuthnController.java */
    public class a extends bh0 {
        public a() {
        }

        @Override // defpackage.bh0, defpackage.vi
        public final void d(int i, int i2) {
            if (i != 0) {
                return;
            }
            hh2 hh2Var = hh2.this;
            hh2Var.a();
            hh2Var.c.onResult(Boolean.FALSE);
        }
    }

    public hh2(WebContents webContents) {
        this.a = webContents;
    }

    public final void a() {
        m91 m91Var = this.b;
        if (m91Var == null) {
            return;
        }
        m91Var.run();
        this.b = null;
    }

    /* JADX INFO: compiled from: SecurePaymentConfirmationAuthnController.java */
    public class b implements ii {
        public b() {
        }

        @Override // defpackage.ii
        public final View d() {
            return hh2.this.e.a;
        }

        @Override // defpackage.ii
        public final int e() {
            return 0;
        }

        @Override // defpackage.ii
        public final View g() {
            return null;
        }

        @Override // defpackage.ii
        public final int h() {
            return 0;
        }

        @Override // defpackage.ii
        public final int i() {
            return R$string.secure_payment_confirmation_authentication_sheet_closed;
        }

        @Override // defpackage.ii
        public final int j() {
            return -2;
        }

        @Override // defpackage.ii
        public final int m() {
            jh2 jh2Var = hh2.this.e;
            if (jh2Var != null) {
                return jh2Var.b.getScrollY();
            }
            return 0;
        }

        @Override // defpackage.ii
        public final float n() {
            return -2.0f;
        }

        @Override // defpackage.ii
        public final int p() {
            return R$string.secure_payment_confirmation_authentication_sheet_description;
        }

        @Override // defpackage.ii
        public final int r() {
            return R$string.secure_payment_confirmation_authentication_sheet_opened;
        }

        @Override // defpackage.ii
        public final float s() {
            return -1.0f;
        }

        @Override // defpackage.ii
        public final boolean u() {
            return false;
        }

        @Override // defpackage.ii
        public final void destroy() {
        }
    }
}
