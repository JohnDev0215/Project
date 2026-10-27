package defpackage;

import android.view.View;
import org.chromium.components.payments.R$string;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: SecurePaymentConfirmationNoMatchingCredController.java */
/* JADX INFO: loaded from: classes3.dex */
public final class kh2 {
    public final WebContents a;
    public y70 b;
    public Runnable c;
    public Runnable d;
    public lh2 e;
    public final j01 f = new j01();
    public final a g = new a();
    public final b h = new b();

    /* JADX INFO: compiled from: SecurePaymentConfirmationNoMatchingCredController.java */
    public class a extends bh0 {
        public a() {
        }

        @Override // defpackage.bh0, defpackage.vi
        public final void d(int i, int i2) {
            if (i != 0) {
                return;
            }
            kh2.this.a();
        }
    }

    public kh2(WebContents webContents) {
        this.a = webContents;
    }

    public final void a() {
        Runnable runnable = this.c;
        if (runnable != null) {
            runnable.run();
            this.c = null;
        }
        y70 y70Var = this.b;
        if (y70Var == null) {
            return;
        }
        y70Var.run();
        this.b = null;
    }

    /* JADX INFO: compiled from: SecurePaymentConfirmationNoMatchingCredController.java */
    public class b implements ii {
        public b() {
        }

        @Override // defpackage.ii
        public final View d() {
            return kh2.this.e.a;
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
            return R$string.secure_payment_confirmation_no_matching_credential_sheet_closed;
        }

        @Override // defpackage.ii
        public final int j() {
            return -2;
        }

        @Override // defpackage.ii
        public final int m() {
            lh2 lh2Var = kh2.this.e;
            if (lh2Var != null) {
                return lh2Var.b.getScrollY();
            }
            return 0;
        }

        @Override // defpackage.ii
        public final float n() {
            return -2.0f;
        }

        @Override // defpackage.ii
        public final int p() {
            return R$string.secure_payment_confirmation_no_matching_credential_sheet_description;
        }

        @Override // defpackage.ii
        public final int r() {
            return R$string.secure_payment_confirmation_no_matching_credential_sheet_opened;
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
