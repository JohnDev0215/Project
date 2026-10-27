package defpackage;

import android.graphics.Bitmap;
import android.webkit.WebHistoryItem;
import org.chromium.content_public.browser.NavigationEntry;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Y8 extends WebHistoryItem {
    public final String s;
    public final String t;
    public final String u;
    public final Bitmap v;

    public Y8(NavigationEntry navigationEntry) {
        this.s = navigationEntry.a;
        this.t = navigationEntry.b;
        this.u = navigationEntry.c;
        this.v = navigationEntry.d;
    }

    @Override // android.webkit.WebHistoryItem
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public synchronized Y8 clone() {
        return new Y8(this.s, this.t, this.u, this.v);
    }

    @Override // android.webkit.WebHistoryItem
    public Bitmap getFavicon() {
        return this.v;
    }

    public int getId() {
        return -1;
    }

    @Override // android.webkit.WebHistoryItem
    public String getOriginalUrl() {
        return this.t;
    }

    @Override // android.webkit.WebHistoryItem
    public String getTitle() {
        return this.u;
    }

    @Override // android.webkit.WebHistoryItem
    public String getUrl() {
        return this.s;
    }

    public Y8(String str, String str2, String str3, Bitmap bitmap) {
        this.s = str;
        this.t = str2;
        this.u = str3;
        this.v = bitmap;
    }
}
