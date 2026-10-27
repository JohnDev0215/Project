package defpackage;

import com.google.android.gms.common.Feature;

/* JADX INFO: renamed from: Rg, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0311Rg {
    public static final Feature a = new Feature("sms_code_autofill", 2);
    public static final Feature b;
    public static final Feature[] c;

    static {
        Feature feature = new Feature("user_consent", 3L);
        b = feature;
        c = new Feature[]{a, feature};
    }
}
