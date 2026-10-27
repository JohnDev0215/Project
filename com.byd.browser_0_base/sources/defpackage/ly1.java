package defpackage;

import java.util.Comparator;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.components.payments.PaymentApp;
import org.chromium.components.payments.e;

/* JADX INFO: compiled from: PaymentAppComparator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ly1 implements Comparator<PaymentApp> {
    public final oy1 e;

    public ly1(oy1 oy1Var) {
        this.e = oy1Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [int] */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v27 */
    /* JADX WARN: Type inference failed for: r1v28 */
    /* JADX WARN: Type inference failed for: r1v29 */
    /* JADX WARN: Type inference failed for: r1v30 */
    /* JADX WARN: Type inference failed for: r1v31 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8, types: [int] */
    /* JADX WARN: Type inference failed for: r1v9, types: [int] */
    @Override // java.util.Comparator
    public final int compare(PaymentApp paymentApp, PaymentApp paymentApp2) {
        ?? r1;
        int i;
        PaymentApp paymentApp3 = paymentApp;
        PaymentApp paymentApp4 = paymentApp2;
        int iCompare = Integer.compare(paymentApp4.a(), paymentApp3.a());
        if (iCompare != 0) {
            return iCompare;
        }
        nz1 nz1Var = ((e) this.e).n;
        if (nz1Var != null) {
            if (nz1Var.e) {
                int i2 = (paymentApp4.p() ? 1 : 0) - (paymentApp3.p() ? 1 : 0);
                if (i2 != 0) {
                    return i2;
                }
            }
            if (nz1Var.b) {
                boolean zN = paymentApp3.n();
                if (paymentApp4.n()) {
                    i = 1;
                    r1 = zN;
                } else {
                    i = 0;
                    r1 = zN;
                }
            } else {
                r1 = 0;
                i = 0;
            }
            ?? r12 = r1;
            if (nz1Var.c) {
                ?? r13 = r1;
                if (paymentApp3.m()) {
                    r13 = r1 + 1;
                }
                r12 = r13;
                if (paymentApp4.m()) {
                    i++;
                    r12 = r13;
                }
            }
            ?? r14 = r12;
            if (nz1Var.d) {
                ?? r15 = r12;
                if (paymentApp3.o()) {
                    r15 = r12 + 1;
                }
                r14 = r15;
                if (paymentApp4.o()) {
                    i++;
                    r14 = r15;
                }
            }
            if (i != r14) {
                return i - r14 > 0 ? 1 : -1;
            }
        }
        int i3 = (paymentApp4.f() ? 1 : 0) - (paymentApp3.f() ? 1 : 0);
        if (i3 != 0) {
            return i3;
        }
        String str = paymentApp4.g;
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        i41 i41Var = ar.m;
        int iD = sharedPreferencesManager.d(0, i41Var.b(str));
        int iD2 = SharedPreferencesManager.getInstance().d(0, i41Var.b(paymentApp3.g));
        String str2 = paymentApp4.g;
        SharedPreferencesManager sharedPreferencesManager2 = SharedPreferencesManager.getInstance();
        i41 i41Var2 = ar.n;
        return Double.compare((-Math.log(((System.currentTimeMillis() - sharedPreferencesManager2.f(i41Var2.b(str2))) / 86400000) + 2)) / Math.log(iD + 2), (-Math.log(((System.currentTimeMillis() - SharedPreferencesManager.getInstance().f(i41Var2.b(paymentApp4.g))) / 86400000) + 2)) / Math.log(iD2 + 2));
    }
}
