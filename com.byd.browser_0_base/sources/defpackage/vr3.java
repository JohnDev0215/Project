package defpackage;

import android.os.Bundle;
import android.text.TextUtils;

/* JADX INFO: compiled from: WebPaymentIntentHelperTypeConverter.java */
/* JADX INFO: loaded from: classes3.dex */
public final class vr3 {
    public static void a(String str, String str2, Bundle bundle) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        bundle.putString(str, str2);
    }
}
