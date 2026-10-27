package defpackage;

import android.text.TextUtils;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import org.chromium.android_webview.AwContents;
import org.chromium.base.Callback;

/* JADX INFO: renamed from: zn, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1893zn extends Su {
    public final /* synthetic */ String h;
    public final /* synthetic */ Callback i;
    public final /* synthetic */ AwContents j;

    public C1893zn(AwContents awContents, String str, Callback callback) {
        this.j = awContents;
        this.h = str;
        this.i = callback;
    }

    @Override // defpackage.Su
    public Object c() {
        String path;
        String strP = this.j.p();
        String str = this.h;
        if (strP == null || strP.isEmpty()) {
            path = null;
        } else {
            try {
                path = new URL(strP).getPath();
                int iLastIndexOf = path.lastIndexOf(47);
                if (iLastIndexOf > 0) {
                    path = path.substring(iLastIndexOf + 1);
                }
            } catch (MalformedURLException unused) {
                path = null;
            }
        }
        if (TextUtils.isEmpty(path)) {
            path = "index";
        }
        String strD = Z7.d(str, path, ".mht");
        if (!new File(strD).exists()) {
            return strD;
        }
        for (int i = 1; i < 100; i++) {
            String str2 = str + path + "-" + i + ".mht";
            if (!new File(str2).exists()) {
                return str2;
            }
        }
        Ts.a("AwContents", "Unable to auto generate archive name for path: %s", str);
        return null;
    }

    @Override // defpackage.Su
    public void n(Object obj) {
        this.j.M((String) obj, this.i);
    }
}
