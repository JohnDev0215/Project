package defpackage;

import J.N;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.FileProvider;
import java.io.File;
import org.chromium.base.Callback;
import org.chromium.base.ContentUriUtils;
import org.chromium.chrome.browser.util.ChromeFileProvider;
import org.chromium.components.browser_ui.share.a;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: OfflinePageUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ir1 extends i9<Uri> {
    public final /* synthetic */ String h;
    public final /* synthetic */ String i;
    public final /* synthetic */ File j;
    public final /* synthetic */ WindowAndroid k;
    public final /* synthetic */ String l;
    public final /* synthetic */ Callback m;

    public ir1(File file, String str, String str2, String str3, Callback callback, WindowAndroid windowAndroid) {
        this.h = str;
        this.i = str2;
        this.j = file;
        this.k = windowAndroid;
        this.l = str3;
        this.m = callback;
    }

    @Override // defpackage.i9
    public final Uri b() {
        String str = this.h;
        if (ContentUriUtils.d(str)) {
            return Uri.parse(str);
        }
        boolean zIsEmpty = str.isEmpty();
        String str2 = this.i;
        if (zIsEmpty) {
            return Uri.parse(str2);
        }
        try {
            File file = this.j;
            Object obj = ChromeFileProvider.i;
            return FileProvider.a(xz.a, xz.a.getPackageName() + ".FileProvider").b(file);
        } catch (IllegalArgumentException e) {
            Log.e("cr_OfflinePageUtils", "Couldn't generate URI for sharing page: " + e);
            return Uri.parse(str2);
        }
    }

    @Override // defpackage.i9
    public final void l(Uri uri) {
        Uri uri2 = uri;
        String str = this.l;
        WindowAndroid windowAndroid = this.k;
        if (!"content".equals(uri2.getScheme())) {
            uri2 = null;
        }
        Uri uri3 = uri2;
        String strJ = this.i;
        if (!TextUtils.isEmpty(strJ) && !TextUtils.isEmpty(strJ)) {
            strJ = ((GURL) N.M1WDPiaY(strJ)).j();
        }
        this.m.onResult(new a(windowAndroid, str, null, null, strJ, null, null, null, uri3, null, null, null, null, null));
    }
}
