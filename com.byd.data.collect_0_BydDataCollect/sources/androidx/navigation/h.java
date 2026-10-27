package androidx.navigation;

import android.content.Intent;
import android.net.Uri;

/* JADX INFO: compiled from: NavDeepLinkRequest.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Uri f1696a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f1697b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f1698c;

    h(Intent intent) {
        this(intent.getData(), intent.getAction(), intent.getType());
    }

    public String a() {
        return this.f1697b;
    }

    public String b() {
        return this.f1698c;
    }

    public Uri c() {
        return this.f1696a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NavDeepLinkRequest");
        sb.append("{");
        if (this.f1696a != null) {
            sb.append(" uri=");
            sb.append(this.f1696a.toString());
        }
        if (this.f1697b != null) {
            sb.append(" action=");
            sb.append(this.f1697b);
        }
        if (this.f1698c != null) {
            sb.append(" mimetype=");
            sb.append(this.f1698c);
        }
        sb.append(" }");
        return sb.toString();
    }

    h(Uri uri, String str, String str2) {
        this.f1696a = uri;
        this.f1697b = str;
        this.f1698c = str2;
    }
}
