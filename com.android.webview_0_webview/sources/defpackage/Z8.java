package defpackage;

import android.content.ContentResolver;
import android.webkit.WebIconDatabase;
import org.chromium.android_webview.AwContents;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class Z8 extends WebIconDatabase {
    public void bulkRequestIconForPageUrl(ContentResolver contentResolver, String str, WebIconDatabase.IconListener iconListener) {
    }

    @Override // android.webkit.WebIconDatabase
    public void close() {
    }

    @Override // android.webkit.WebIconDatabase
    public void open(String str) {
        AwContents.U();
    }

    @Override // android.webkit.WebIconDatabase
    public void releaseIconForPageUrl(String str) {
    }

    @Override // android.webkit.WebIconDatabase
    public void removeAllIcons() {
    }

    @Override // android.webkit.WebIconDatabase
    public void requestIconForPageUrl(String str, WebIconDatabase.IconListener iconListener) {
    }

    @Override // android.webkit.WebIconDatabase
    public void retainIconForPageUrl(String str) {
    }
}
