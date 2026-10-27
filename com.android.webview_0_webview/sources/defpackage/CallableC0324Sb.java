package defpackage;

import com.android.webview.chromium.WebViewDatabaseAdapter;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: Sb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CallableC0324Sb implements Callable {
    public final /* synthetic */ String a;
    public final /* synthetic */ String b;
    public final /* synthetic */ WebViewDatabaseAdapter c;

    public CallableC0324Sb(WebViewDatabaseAdapter webViewDatabaseAdapter, String str, String str2) {
        this.c = webViewDatabaseAdapter;
        this.a = str;
        this.b = str2;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        return this.c.b.a(this.a, this.b);
    }
}
