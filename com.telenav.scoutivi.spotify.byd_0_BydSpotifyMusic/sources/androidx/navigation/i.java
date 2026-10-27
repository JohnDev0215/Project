package androidx.navigation;

import android.content.Intent;
import android.net.Uri;

/* JADX INFO: compiled from: NavDeepLinkRequest.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Uri f2700a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f2701b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f2702c;

    i(Intent intent) {
        this(intent.getData(), intent.getAction(), intent.getType());
    }

    public String a() {
        return this.f2701b;
    }

    public String b() {
        return this.f2702c;
    }

    public Uri c() {
        return this.f2700a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NavDeepLinkRequest");
        sb.append("{");
        if (this.f2700a != null) {
            sb.append(" uri=");
            sb.append(this.f2700a.toString());
        }
        if (this.f2701b != null) {
            sb.append(" action=");
            sb.append(this.f2701b);
        }
        if (this.f2702c != null) {
            sb.append(" mimetype=");
            sb.append(this.f2702c);
        }
        sb.append(" }");
        return sb.toString();
    }

    i(Uri uri, String str, String str2) {
        this.f2700a = uri;
        this.f2701b = str;
        this.f2702c = str2;
    }
}
