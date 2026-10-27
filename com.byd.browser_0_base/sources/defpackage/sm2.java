package defpackage;

import android.content.Context;
import android.text.TextUtils;
import org.chromium.chrome.R$string;

/* JADX INFO: compiled from: SigninUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class sm2 {
    public static String a(Context context, fb0 fb0Var) {
        if (!TextUtils.isEmpty(fb0Var.d)) {
            return context.getString(R$string.sync_promo_continue_as, fb0Var.d);
        }
        String str = fb0Var.c;
        return !TextUtils.isEmpty(str) ? context.getString(R$string.sync_promo_continue_as, str) : (fb0Var.e || !(iv.e().g("force-hide-non-displayable-account-email-fre") || bq.C.a())) ? context.getString(R$string.sync_promo_continue_as, fb0Var.a) : context.getString(R$string.sync_promo_continue);
    }
}
