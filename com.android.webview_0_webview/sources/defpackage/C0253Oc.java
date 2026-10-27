package defpackage;

import com.google.android.gms.common.Feature;

/* JADX INFO: renamed from: Oc, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0253Oc extends UnsupportedOperationException {
    public final Feature s;

    public C0253Oc(Feature feature) {
        this.s = feature;
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        String strValueOf = String.valueOf(this.s);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 8);
        sb.append("Missing ");
        sb.append(strValueOf);
        return sb.toString();
    }
}
