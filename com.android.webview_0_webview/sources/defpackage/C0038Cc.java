package defpackage;

import com.google.android.gms.common.api.Status;

/* JADX INFO: renamed from: Cc, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0038Cc extends Exception {
    public C0038Cc(Status status) {
        int i = status.t;
        String str = status.u;
        str = str == null ? "" : str;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 13);
        sb.append(i);
        sb.append(": ");
        sb.append(str);
        super(sb.toString());
    }
}
