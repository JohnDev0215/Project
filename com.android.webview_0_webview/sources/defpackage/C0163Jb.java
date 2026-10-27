package defpackage;

import android.content.Intent;
import android.webkit.WebChromeClient;

/* JADX INFO: renamed from: Jb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0163Jb extends WebChromeClient.FileChooserParams {
    public final /* synthetic */ C0462Zn a;

    public C0163Jb(C0462Zn c0462Zn) {
        this.a = c0462Zn;
    }

    @Override // android.webkit.WebChromeClient.FileChooserParams
    public Intent createIntent() {
        C0462Zn c0462Zn = this.a;
        String str = c0462Zn.b;
        String str2 = (str == null || str.trim().isEmpty()) ? "*/*" : c0462Zn.b.split(",")[0];
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType(str2);
        return intent;
    }

    @Override // android.webkit.WebChromeClient.FileChooserParams
    public String[] getAcceptTypes() {
        String str = this.a.b;
        return str == null ? new String[0] : str.split(",");
    }

    @Override // android.webkit.WebChromeClient.FileChooserParams
    public String getFilenameHint() {
        return this.a.d;
    }

    @Override // android.webkit.WebChromeClient.FileChooserParams
    public int getMode() {
        return this.a.a;
    }

    @Override // android.webkit.WebChromeClient.FileChooserParams
    public CharSequence getTitle() {
        return this.a.c;
    }

    @Override // android.webkit.WebChromeClient.FileChooserParams
    public boolean isCaptureEnabled() {
        return this.a.e;
    }
}
